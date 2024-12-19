// mraid.js
// Copyright 2021 MobileFuse LLC

class EventListener {
    constructor(event) {
        this.event = event;
        this.listeners = {};
    }

    add(callback) {
        const id = String(callback);
        this.listeners[id] = callback;
    }

    remove(callback) {
        const id = String(callback);
        if(this.listeners[id]) {
            delete this.listeners[id];
            return true;
        }
        return false;
    }

    removeAll() {
        this.listeners = {};
    }

    invoke(scope, args) {
        for(const callbackId in this.listeners){
            if( ! this.listeners.hasOwnProperty(callbackId)){
                continue;
            }
            this.listeners[callbackId].apply(scope, args);
        }
    }
}

class NativeBridge {
    constructor(mraid) {
        this.mraid = mraid;
        this.queue = [];
        this.requestInFlight = false;
    }

    call(method, args){
        let callUri = "mraid://" + method;
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
        this.mraid._debug("Bridge, calling native " + call);
        window.location = call;
    }

    nativeCallComplete() {
        if( ! this.requestInFlight) return;
        this.requestInFlight = false;
        this._next();
    }

    // Interface methods for the native layer:

    init(appDetails){
        this.call("initBridge", appDetails);
    }

    setCurrentPosition(properties){
        console.log("[mraid] :: setCurrentPosition("+JSON.stringify(properties)+")");
        this.mraid.currentPosition = this.mraid._assignProperties(properties, this.mraid.currentPosition);
    }

    setCurrentAppOrientation(properties){
        console.log("[mraid] :: setCurrentAppOrientation("+JSON.stringify(properties)+")");
        this.mraid.currentAppOrientation = this.mraid._assignProperties(properties, this.mraid.currentAppOrientation);
    }

    setDefaultPosition(properties){
        this.mraid.defaultPosition = this.mraid._assignProperties(properties, this.mraid.defaultPosition);
    }

    setMaxSize(properties){
        this.mraid.maxSize = this.mraid._assignProperties(properties, this.mraid.maxSize);
    }

    setPlacementType(placementType) {
        this.mraid.placementType = placementType;
    }

    setScreenSize(properties){
        console.log("[mraid] :: setScreenSize("+JSON.stringify(properties)+")");
        this.mraid.screenSize = this.mraid._assignProperties(properties, this.mraid.screenSize);
    }

    setState(state){
        this.mraid.state = state;
        this.mraid._broadcastEvent(Mraid.Event.STATE_CHANGE, state);
    }

    setIsViewable(isViewable){
        if (isViewable === this.mraid.viewable) return;
        this.mraid.viewable = isViewable;
        this.mraid._broadcastEvent(Mraid.Event.VIEWABLE_CHANGE, isViewable);
    }

    setExposureChange(exposurePrcnt){
        this.mraid._broadcastEvent(Mraid.Event.EXPOSURE_CHANGE, exposurePrcnt);
    }

    setSupports(supports){
        this.mraid.supportProperties = this.mraid._assignProperties(supports, this.mraid.supportProperties);
    }

    notifyReadyEvent() {
        this.mraid._broadcastEvent(Mraid.Event.READY);
    }

    notifySizeChangeEvent(width, height) {
        this.mraid._broadcastEvent(Mraid.Event.SIZE_CHANGE, width, height);
    }

    notifyStateChangeEvent() {
        this.mraid._broadcastEvent(Mraid.Event.STATE_CHANGE, this.mraid.state);
    }

    notifyViewableChangeEvent() {
        this.mraid._broadcastEvent(Mraid.Event.VIEWABLE_CHANGE, this.mraid.isViewable());
    }

}

class SplashAd {

    constructor(bridge) {
        this.bridge = bridge;
    }

    transition() {
        this.bridge.call('splashAdTransition');
    }

    expand() {
        this.bridge.call('splashAdExpand');
    }
}

class Mraid {
    constructor(options) {
        options = options || {};
        this.VERSION = "3.0";
        this.isMobileFuseSdk = true;
        this.DEBUG = options.DEBUG || false;
        this.bridge = new NativeBridge(this);


        // Internal mraid state:
        this.state = Mraid.State.LOADING;
        this.viewable = false;
        this.eventListeners = {};
        this.placementType = Mraid.PlacementType.UNKNOWN;

        // Our mraid properties:

        this.expandProperties = {
            width: false,
            height: false,
            useCustomClose: false,
            isModal: true,
        };

        this.resizeProperties = {
            width: false,
            height: false,
            offsetX: false,
            offsetY: false,
            customClosePosition: 'top-right',
            allowOffscreen: true,
        };

        this.orientationProperties = {
            allowOrientationChange: true,
            forceOrientation: "none",
        };

        this.supportProperties = {
            sms: false,
            tel: false,
            calendar: false,
            storePicture: false,
            inlineVideo: false,

            // Extensions:
            ar: false,
            barometricPressure: false,
            transparentBackground: false,
            splashAd: false
        };

        this.locationProperties = {
            lat: 0,
            lon: 0,
            type: Mraid.LocationType.INVALID,
            accuracy: -1,
            lastfix: -1,
            ipservice: null
        };

        // State:
        this.maxSize = { width: 0, height: 0 };
        this.currentPosition = { x:0, y:0, width:0, height:0 };
        this.currentAppOrientation = { orientation: "none", locked: false };
        this.defaultPosition = { x:0, y:0, width:0, height:0 };
        this.screenSize = { width: 0, height: 0 };

        // SplashAd
        this.splashAd = new SplashAd(this.bridge);

    }

    static Event = {
        ERROR:               'error',
        READY:               'ready',
        SIZE_CHANGE:         'sizeChange',
        STATE_CHANGE:        'stateChange',
        EXPOSURE_CHANGE:     'exposureChange',
        AUDIO_VOLUME_CHANGE: 'audioVolumeChange',

        VIEWABLE_CHANGE:     'viewableChange', // Deprecated
        DEBUG_LOG:           'debugLog',       // Extension
    };

    static LocationType = {
        INVALID:        0,
        GPS:            1,
        IP_ADDRESS:     2,
        USER_PROVIDED:  3
    };

    static State = {
        LOADING:  'loading',
        DEFAULT:  'default',
        EXPANDED: 'expanded',
        HIDDEN:   'hidden',
        RESIZED:  'resized'
    };

    static PlacementType = {
        UNKNOWN:      'unknown',
        INLINE:       'inline',
        INTERSTITIAL: 'interstitial'
    };

    // Private/Helper methods:
    _broadcastEvent() {
        const args = [...arguments];
        const event = args.shift();

        if (this.eventListeners[event]) {
            this.eventListeners[event].invoke(this, args);
        }

        if(event !== Mraid.Event.DEBUG_LOG) {
            this._debug("_broadcastEvent", event, args);
        }
    }

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
        console.log("[mraid.js] " + logOutput.join(' '));
        this._broadcastEvent(Mraid.Event.DEBUG_LOG, logOutput.join(' '));
    }

    _assignProperties(from, to, properties){
        if(!properties) properties = Object.keys(to);
        const obj = {...to};
        for(const p of properties){
            if(from.hasOwnProperty(p)){
                obj[p] = from[p];
            }
        }
        return obj;
    }

    // Core MRAID 3.0 interface:
    // See: https://www.iab.com/wp-content/uploads/2017/07/MRAID_3.0_FINAL.pdf
    // Section 2.3 - Interface, Page 14

    getVersion() {
        return this.VERSION;
    }

    addEventListener(event, listener) {
        if (!this.eventListeners[event]) {
            this.eventListeners[event] = new EventListener(event);
        }
        this.eventListeners[event].add(listener);
    }

    removeEventListener(event, listener) {
        if (!event) {
            this._broadcastEvent(Mraid.Event.ERROR, 'Event is required.', 'removeEventListener');
            return;
        }

        if (listener) {
            // If we have a valid event, we'll try to remove the listener from it.
            let success = false;
            if (this.eventListeners[event]) {
                success = this.eventListeners[event].remove(listener);
            }

            // If we didn't have a valid event or couldn't remove the listener from the event, broadcast an error and return early.
            if (!success) {
                this._broadcastEvent(Mraid.Event.ERROR, 'Listener not currently registered for event.', 'removeEventListener');
            }
        } else if (!listener && this.eventListeners[event]) {
            this.eventListeners[event].removeAll();
        }
    }

    open(url) {
        if (!url){
            this._broadcastEvent(Mraid.Event.ERROR, 'Url is required for the open() call.', 'open');
        } else {
            this.bridge.call('open', {url: url});
        }
    }

    close() {
        if (this.state === Mraid.State.HIDDEN) {
            this._broadcastEvent(Mraid.Event.ERROR, 'Ad cannot be closed when it is already hidden.', 'close');
        } else {
            this.bridge.call('close');
        }
    }

    // Deprecated
    useCustomClose(shouldUseCustomClose) {
        this.expandProperties.useCustomClose = shouldUseCustomClose;
        this.bridge.call('useCustomClose', {shouldUseCustomClose:shouldUseCustomClose});
    }

    unload() {
        console.log("[mraid] :: unload");
        this.bridge.call('unload');
    }

    expand(url) {
        if (!(this.state === Mraid.State.DEFAULT || this.state === Mraid.State.RESIZED)) {
            this._broadcastEvent(Mraid.Event.ERROR, 'Ad can only be expanded from the default or resized state.', 'expand');
            return;
        }

        var args = {...this.expandProperties};
        args.allowOrientationChange = this.orientationProperties.allowOrientationChange;
        args.forceOrientation = this.orientationProperties.forceOrientation;

//        args.allowOrientationChange = true;
//        args.forceOrientation = "landscape";

        if (url) {
            args.url = url;
        }

        this.bridge.call('expand', args);
    }

    // Deprecated
    isViewable() {
        return this.viewable;
    }

    playVideo(uri) {
        if (!mraid.isViewable()) {
            this._broadcastEvent(Mraid.Event.ERROR, 'playVideo cannot be called until the ad is viewable', 'playVideo');
            return;
        }

        if (!uri) {
            this._broadcastEvent(Mraid.Event.ERROR, 'playVideo must be called with a valid URI', 'playVideo');
        } else {
            this.bridge.call('playVideo', {uri: uri});
        }
    }

    resize() {
        if (!(this.getState() === Mraid.State.DEFAULT || this.getState() === Mraid.State.RESIZED)) {
            this._broadcastEvent(Mraid.Event.ERROR, 'Ad can only be resized from the default or resized state.', 'resize');
        } else if (!this.resizeProperties.width || !this.resizeProperties.height) {
            this._broadcastEvent(Mraid.Event.ERROR, 'Must set resize properties before calling resize()', 'resize');
        } else {
            this.bridge.call('resize', this.resizeProperties);
        }
    }

    storePicture(uri) {
        if (!mraid.isViewable()) {
            this._broadcastEvent(Mraid.Event.ERROR, 'storePicture cannot be called until the ad is viewable', 'storePicture');
            return;
        }

        if (!uri) {
            this._broadcastEvent(Mraid.Event.ERROR, 'storePicture must be called with a valid URI', 'storePicture');
        } else {
            this.bridge.call('storePicture', {uri: uri});
        }
    }

    setTransparentBackground(transparent) {
        console.log("[mraid Transparent Interstitial] :: setTransparentBackground("+transparent+")");
        this.bridge.call('setTransparentBackground', { transparent: transparent } );
    }

    createCalendarEvent(parameters) {
//        throw "Not implemented :: createCalendarEvent()";
        if (!mraid.isViewable()) {
            this._broadcastEvent(Mraid.Event.ERROR, 'createCalendarEvent cannot be called until the ad is viewable', 'createCalendarEvent');
            return;
        }

        if (!parameters) {
            this._broadcastEvent(Mraid.Event.ERROR, 'createCalendarEvent must be called with a valid parameters', 'createCalendarEvent');
        } else {
            this.bridge.call('createCalendarEvent', { details: JSON.stringify(parameters) } );
        }
    }

    // VPAID --
    initVpaid(vpaidObject) {
        throw "Not implemented :: initVpaid()";
    }

    // Properties --

    supports(feature) {
        return this.supportProperties[feature];
    }

    getPlacementType() {
        return this.placementType;
    }

    getOrientationProperties() {
        console.log("[mraid] :: getOrientationProperties()");
        return {...this.orientationProperties};
    }

    setOrientationProperties(properties) {
        console.log("[mraid] :: setOrientationProperties("+JSON.stringify(properties)+")");

        if ( this.getState() === Mraid.State.LOADING ) {
            this._broadcastEvent(Mraid.Event.ERROR, "Method 'mraid.setOrientationProperties()' called during loading state.", 'setOrientationProperties');
            return;
        }

        if (typeof properties === "undefined") {
            this._broadcastEvent(Mraid.Event.ERROR, "Invalid orientationProperties.' called during loading state.", 'setOrientationProperties');
            return;
        }

        if (!this.getCurrentAppOrientation().locked) {
            if (properties.forceOrientation === 'portrait' || properties.forceOrientation === 'landscape' || properties.forceOrientation === 'none') {
                this.orientationProperties.forceOrientation = properties.forceOrientation;
            } else {
                this._broadcastEvent(Mraid.Event.ERROR, "Invalid orientationProperties forceOrientation property.", 'setOrientationProperties');
            }
        }

        if (typeof properties.allowOrientationChange === "boolean") {
            orientationProperties.allowOrientationChange = properties.allowOrientationChange;
        } else {
            this._broadcastEvent(Mraid.Event.ERROR, "Invalid orientationProperties allowOrientationChange property.", 'setOrientationProperties');
        }

        /*this.orientationProperties = this._assignProperties(
            properties,
            this.orientationProperties
        );*/
        this.bridge.call('setOrientationProperties', this.orientationProperties);
    }

    getCurrentAppOrientation() {
        console.log("[mraid] :: getCurrentAppOrientation()");
        return {...this.currentAppOrientation};
    }

    getCurrentPosition() {
        return {...this.currentPosition};
    }

    getDefaultPosition() {
        return {...this.defaultPosition};
    }

    getState() {
        return this.state;
    }

    getExpandProperties() {
        return {...this.expandProperties};
    }

    setExpandProperties(properties) {
        this.expandProperties = this._assignProperties(
            properties,
            this.expandProperties
        );
    }

    getMaxSize() {
        console.log("[mraid] :: getMaxSize("+JSON.stringify(this.maxSize)+")");
        return {...this.maxSize};
    }

    getScreenSize() {
        console.log("[mraid] :: getScreenSize("+JSON.stringify(this.screenSize)+")");
        return {...this.screenSize};
    }

    getResizeProperties() {
        return {...this.resizeProperties};
    }

    setResizeProperties(properties) {
        this._assignProperties(properties, this.resizeProperties);
    }

    getLocation() {
        const location = {...this.locationProperties};
        if(location.lastfix instanceof Date){
            // 'lastfix' should be number of seconds since the last GPS fix:
            location.lastfix = (new Date() - location.lastfix)/1000;
        }
        return location;
    }
}

if (!window.mraid || !window.mraid.isMobileFuseSdk){
    const mraid = window.mraid = new Mraid({
        DEBUG: MRAID_ENV.debug || false
    });
}