// vast.js
// Copyright 2021 MobileFuse LLC

class VastNativeBridge {
    // See mraid.js NativeBridge

    constructor(vast) {
        this.vast = vast;
        this.queue = [];
        this.requestInFlight = false;
    }

    call(method, args){
        let callUri = "vast://" + method;
        if(args){
            callUri += "?" + this._buildQueryString(args);
        }

        this.queue.push(callUri);

        if( ! this.requestInFlight){
            this._next();
        }
    }

    _buildQueryString(obj) {
        const str = [];
        for (let p in obj) {
            if (obj.hasOwnProperty(p)) {
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            }
        }
        return str.join("&");
    }

    _next() {
        if(this.queue.length === 0 || this.requestInFlight){
            return;
        }
        const call = this.queue.shift();
        this.requestInFlight = true;
        this.vast._debug("Bridge, calling native " + call);
        window.location = call;
        this.nativeCallComplete();
    }

    nativeCallComplete() {
        if( ! this.requestInFlight) return;
        this.requestInFlight = false;
        this._next();
    }

    // Interface methods for the native layer:

    setCurrentTime(time){
        this.vast.setCurrentTime(time);
    }

    setVideoDuration(duration){
        this.vast.setVideoDuration(duration);
    }

    setMuteAllowed(){
        this.vast.setMuteAllowed();
    }

    setMuted(muted){
        this.vast.setMuted(muted);
    }

    setFullscreenAllowed(){
        this.vast.setFullscreenAllowed();
    }

    setEnterFullscreenOnVideoTap(enabled){
        this.vast.setEnterFullscreenOnVideoTap(enabled);
    }

    setFullscreen(fullscreen){
        this.vast.setFullscreen(fullscreen);
    }

    setSkipTime(skipTime){
        this.vast.setSkipTime(skipTime);
    }

    setCtaText(ctaText){
        this.vast.setCtaText(decodeURIComponent(ctaText));
    }

    addIcon(type, data) {
        this.vast.addIcon(type, data);
    }
}

class IconResourceType {
    static STATIC = "static";
    static IFRAME = "iframe";
    static HTML   = "html";
}

class Vast {

    constructor(options) {
        options = options || {};
        this.VERSION = "4.0";
        this.DEBUG = options.DEBUG || false;
        this.bridge = new VastNativeBridge(this);

        // Internal vast state:
        this.duration = 10;
        this.position = 0;
        this.skipPosition = -1;
        this.ctaShowDelay = 5;
        this.muted = false;
        this.fullscreen = false;
        this.ctaButtonVisible = false;
        this.closeButtonVisible = false;
        this.muteButtonVisible = false;
        this.fullscreenButtonVisible = false;
        this.enterFullscreenOnVideoTap = false;
        this.icons = [];

        // Controls:
        this.overlayUi = options.overlayUi;
        this.ctaButton = options.ctaButton;
        this.closeButton = options.closeButton;
        this.muteButton = options.muteButton;
        this.fullscreenButton = options.fullscreenButton;
        this.videoCover = options.videoCover;
        this.skipMarker = options.skipMarker;
        this.progressBar = options.progressBar;
        this.iconContainer = options.iconContainer;

        // Event listeners:
        this.ctaButton.addEventListener('click', this._handleClickthrough.bind(this));
        this.videoCover.addEventListener('click', this._handleClickthrough.bind(this));
        this.closeButton.addEventListener('click', this._handleClose.bind(this));
        this.muteButton.addEventListener('click', this._handleMute.bind(this));
        this.fullscreenButton.addEventListener('click', this._handleFullscreen.bind(this));
    }

    setVideoDuration(duration) {
        this.duration = duration;
        this._updateProgressBar();
        this._updateIcons();
    }

    setMuteAllowed() {
        this._showMuteButton();
    }

    setFullscreenAllowed() {
        this._showFullscreenButton();
    }

    setEnterFullscreenOnVideoTap(enabled) {
        this.enterFullscreenOnVideoTap = enabled;
        if (this.enterFullscreenOnVideoTap) {
            this.fullscreenButton.classList.add("enter-fullscreen-on-video-tap");
        } else {
            this.fullscreenButton.classList.remove("enter-fullscreen-on-video-tap");
        }
    }

    setCurrentTime(time) {
        this.position = time;
        this._updateProgressBar();
        this._updateIcons();

        if (!this.closeButtonVisible && this.skipPosition >= 0 && this.position > this.skipPosition) {
            this._debug("Showing close button. Position:", this.position, "Skip position:", this.skipPosition);
            this._showCloseButton();

            if (!this.ctaButtonVisible) {
                this._debug("Showing CTA button with close button. Position:", this.position, "Skip position:", this.skipPosition);
                this._showCtaButton();
            }
        } else if (this.skipPosition == -1 && !this.ctaButtonVisible && this.position >= this.ctaShowDelay) {
            this._debug("Showing CTA button after ", this.ctaShowDelay, "delay. Position:", this.position, "Skip position:", this.skipPosition);
            this._showCtaButton();
        }
    }

    setSkipTime(time) {
        this.skipPosition = time;
        this._updateProgressBar();
    }

    setCtaText(text) {
        this.ctaButton.innerText = text;
    }

    setMuted(muted) {
        this.muted = muted;
        if (this.muted) {
            this.muteButton.querySelector(".toggle_on").style.display = "none";
            this.muteButton.querySelector(".toggle_off").style.display = "unset";
        } else {
            this.muteButton.querySelector(".toggle_on").style.display = "unset";
            this.muteButton.querySelector(".toggle_off").style.display = "none";
        }
    }

    setFullscreen(fullscreen) {
        this.fullscreen = fullscreen;
        if (this.fullscreen) {
            this.fullscreenButton.querySelector(".toggle_on").style.display = "none";
            this.fullscreenButton.querySelector(".toggle_off").style.display = "unset";
        } else {
            this.fullscreenButton.querySelector(".toggle_on").style.display = "unset";
            this.fullscreenButton.querySelector(".toggle_off").style.display = "none";
        }
    }

    addIcon(type, data) {
        // Icon data structure:
        // { duration: number, offset: number, width: number, height: number, resource: ... }
        let container = this._createResourceContainer(type, data);
        this.icons.push({
            displayStart: data.offset || 0,
            displayEnd:   data.duration ? (data.duration + (data.offset || 0)) : Number.POSITIVE_INFINITY,
            container:    container,
            visible:      false,
            viewPayload:  data.viewPayload
        });
    }

    // Internal methods

    _debug() {
        if(!this.DEBUG){
            return;
        }
        let logOutput = [];
        for(let arg of arguments){
            if(arg === null) {
                continue;
            }
            if(typeof arg !== 'object') {
                logOutput.push(arg.toString());
                continue;
            }
            logOutput.push(JSON.stringify(arg));
        }
        console.log("[vast.js] " + logOutput.join(' '));
    }

    _createResourceContainer(type, data) {
        let container = document.createElement("div");
        container.className = "icon";

        switch(type){
            case IconResourceType.STATIC:
                let img = document.createElement("div");
                img.className = "image";
                img.style.backgroundImage = "url(\"" + data.resource + "\")";
                container.append(img);
                break;
            case IconResourceType.IFRAME:
                let frame = document.createElement("iframe");
                frame.src = data.resource;
                container.append(frame);
                break;
            case IconResourceType.HTML:
                let injectedStyle = "<style>body{overflow:hidden;margin:0;padding:0;}</style>";
                let dynamicFrame = document.createElement("iframe");
                dynamicFrame.src = "about:blank";
                dynamicFrame.onload = () => {
                    dynamicFrame.contentDocument.documentElement.innerHTML = injectedStyle + data.resource;
                }
                container.append(dynamicFrame);
                break;
        }

        let cover = document.createElement("div");
        cover.style.position = "absolute";
        cover.style.left = 0;
        cover.style.top = 0;
        cover.style.width = "100%";
        cover.style.height = "100%";
        container.append(cover);

        container.onclick = () => {
            this.bridge.call("iconClick", {payload: data.clickPayload});
        };

        if ((data.width || 0) && (data.height || 0)) {
            let aspect = data.width / data.height;
            if(aspect < 10 && aspect > 0.1) {
                let width = aspect * Math.min(data.height, 32);
                container.style.width = width + "px";
            }
        }

        return container;
    }

    _showElement(element) {
        element.style.visibility = "visible";
        element.style.opacity = 1;
    }

    _showCloseButton() {
        this.closeButtonVisible = true;
        this._showElement(this.closeButton);
        this.bridge.call("closeButtonVisible");
    }

    _showMuteButton() {
        this.muteButtonVisible = true;
        this._showElement(this.muteButton);
    }

    _showFullscreenButton() {
        this.fullscreenButtonVisible = true;
        this._showElement(this.fullscreenButton);
    }

    _showCtaButton() {
        this.ctaButtonVisible = true;
        this._showElement(this.ctaButton);
    }

    _updateIcons() {
        for (let i = 0; i < this.icons.length; i++) {
            let icon = this.icons[i];

            let shouldRender = this.position >= icon.displayStart && this.position <= icon.displayEnd;
            if (shouldRender === icon.visible) continue;

            if (shouldRender) {
                icon.visible = true;
                this.iconContainer.append(icon.container);
                setTimeout(() => {
                    icon.container.style.opacity = "1.0";
                    this.bridge.call("iconView", {payload: icon.viewPayload});
                }, 0);
            } else {
                icon.visible = false;
                icon.container.style.opacity = "0.0";
                setTimeout(icon.container.remove.bind(icon.container), 1000);
            }
            this.icons[i] = icon;
        }
    }

    _updateProgressBar() {
        if (this.duration < 0.0001) {
            return;
        }
        this.progressBar.style.width = ((this.position / this.duration) * 100).toFixed(2) + "%";
        this.skipMarker.style.left = ((this.skipPosition / this.duration) * 100).toFixed(2) + "%";
    }

    _handleClickthrough(event) {
        let source = event.target == this.ctaButton ? "ctaBtn" : "video"
        this.bridge.call('open', {source: source});
    }

    _handleClose() {
        this.bridge.call('close');
    }

    _handleMute() {
        this.setMuted(!this.muted);
        this.bridge.call('setMute', {muted: this.muted});
    }

    _handleFullscreen() {
        this.setFullscreen(!this.fullscreen);
        this.bridge.call('setFullscreen', {fullscreen: this.fullscreen});
    }
}