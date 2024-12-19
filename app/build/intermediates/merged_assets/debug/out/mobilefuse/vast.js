"use strict";

function _typeof(obj) { "@babel/helpers - typeof"; if (typeof Symbol === "function" && typeof Symbol.iterator === "symbol") { _typeof = function _typeof(obj) { return typeof obj; }; } else { _typeof = function _typeof(obj) { return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj; }; } return _typeof(obj); }

function _createForOfIteratorHelper(o, allowArrayLike) { var it = typeof Symbol !== "undefined" && o[Symbol.iterator] || o["@@iterator"]; if (!it) { if (Array.isArray(o) || (it = _unsupportedIterableToArray(o)) || allowArrayLike && o && typeof o.length === "number") { if (it) o = it; var i = 0; var F = function F() {}; return { s: F, n: function n() { if (i >= o.length) return { done: true }; return { done: false, value: o[i++] }; }, e: function e(_e) { throw _e; }, f: F }; } throw new TypeError("Invalid attempt to iterate non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method."); } var normalCompletion = true, didErr = false, err; return { s: function s() { it = it.call(o); }, n: function n() { var step = it.next(); normalCompletion = step.done; return step; }, e: function e(_e2) { didErr = true; err = _e2; }, f: function f() { try { if (!normalCompletion && it.return != null) it.return(); } finally { if (didErr) throw err; } } }; }

function _unsupportedIterableToArray(o, minLen) { if (!o) return; if (typeof o === "string") return _arrayLikeToArray(o, minLen); var n = Object.prototype.toString.call(o).slice(8, -1); if (n === "Object" && o.constructor) n = o.constructor.name; if (n === "Map" || n === "Set") return Array.from(o); if (n === "Arguments" || /^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)) return _arrayLikeToArray(o, minLen); }

function _arrayLikeToArray(arr, len) { if (len == null || len > arr.length) len = arr.length; for (var i = 0, arr2 = new Array(len); i < len; i++) { arr2[i] = arr[i]; } return arr2; }

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); return Constructor; }

// vast.js
// Copyright 2021 MobileFuse LLC
var VastNativeBridge = /*#__PURE__*/function () {
  // See mraid.js NativeBridge
  function VastNativeBridge(vast) {
    _classCallCheck(this, VastNativeBridge);

    this.vast = vast;
    this.queue = [];
    this.requestInFlight = false;
  }

  _createClass(VastNativeBridge, [{
    key: "call",
    value: function call(method, args) {
      var callUri = "vast://" + method;

      if (args) {
        callUri += "?" + this._buildQueryString(args);
      }

      this.queue.push(callUri);

      if (!this.requestInFlight) {
        this._next();
      }
    }
  }, {
    key: "_buildQueryString",
    value: function _buildQueryString(obj) {
      var str = [];

      for (var p in obj) {
        if (obj.hasOwnProperty(p)) {
          str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
        }
      }

      return str.join("&");
    }
  }, {
    key: "_next",
    value: function _next() {
      if (this.queue.length === 0 || this.requestInFlight) {
        return;
      }

      var call = this.queue.shift();
      this.requestInFlight = true;

      this.vast._debug("Bridge, calling native " + call);

      window.location = call;
      this.nativeCallComplete();
    }
  }, {
    key: "nativeCallComplete",
    value: function nativeCallComplete() {
      if (!this.requestInFlight) return;
      this.requestInFlight = false;

      this._next();
    } // Interface methods for the native layer:

  }, {
    key: "setCurrentTime",
    value: function setCurrentTime(time) {
      this.vast.setCurrentTime(time);
    }
  }, {
    key: "setVideoDuration",
    value: function setVideoDuration(duration) {
      this.vast.setVideoDuration(duration);
    }
  }, {
    key: "setMuteAllowed",
    value: function setMuteAllowed() {
      this.vast.setMuteAllowed();
    }
  }, {
    key: "setMuted",
    value: function setMuted(muted) {
      this.vast.setMuted(muted);
    }
  }, {
    key: "setFullscreenAllowed",
    value: function setFullscreenAllowed() {
      this.vast.setFullscreenAllowed();
    }
  }, {
    key: "setEnterFullscreenOnVideoTap",
    value: function setEnterFullscreenOnVideoTap(enabled) {
      this.vast.setEnterFullscreenOnVideoTap(enabled);
    }
  }, {
    key: "setFullscreen",
    value: function setFullscreen(fullscreen) {
      this.vast.setFullscreen(fullscreen);
    }
  }, {
    key: "setSkipTime",
    value: function setSkipTime(skipTime) {
      this.vast.setSkipTime(skipTime);
    }
  }, {
    key: "setCtaText",
    value: function setCtaText(ctaText) {
      this.vast.setCtaText(decodeURIComponent(ctaText));
    }
  }, {
    key: "addIcon",
    value: function addIcon(type, data) {
      this.vast.addIcon(type, data);
    }
  }]);

  return VastNativeBridge;
}();

var IconResourceType = function IconResourceType() {
  _classCallCheck(this, IconResourceType);
};

_defineProperty(IconResourceType, "STATIC", "static");

_defineProperty(IconResourceType, "IFRAME", "iframe");

_defineProperty(IconResourceType, "HTML", "html");

var Vast = /*#__PURE__*/function () {
  function Vast(options) {
    _classCallCheck(this, Vast);

    options = options || {};
    this.VERSION = "4.0";
    this.DEBUG = options.DEBUG || false;
    this.bridge = new VastNativeBridge(this); // Internal vast state:

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
    this.icons = []; // Controls:

    this.overlayUi = options.overlayUi;
    this.ctaButton = options.ctaButton;
    this.closeButton = options.closeButton;
    this.muteButton = options.muteButton;
    this.fullscreenButton = options.fullscreenButton;
    this.videoCover = options.videoCover;
    this.skipMarker = options.skipMarker;
    this.progressBar = options.progressBar;
    this.iconContainer = options.iconContainer; // Event listeners:

    this.ctaButton.addEventListener('click', this._handleClickthrough.bind(this));
    this.videoCover.addEventListener('click', this._handleClickthrough.bind(this));
    this.closeButton.addEventListener('click', this._handleClose.bind(this));
    this.muteButton.addEventListener('click', this._handleMute.bind(this));
    this.fullscreenButton.addEventListener('click', this._handleFullscreen.bind(this));
  }

  _createClass(Vast, [{
    key: "setVideoDuration",
    value: function setVideoDuration(duration) {
      this.duration = duration;

      this._updateProgressBar();

      this._updateIcons();
    }
  }, {
    key: "setMuteAllowed",
    value: function setMuteAllowed() {
      this._showMuteButton();
    }
  }, {
    key: "setFullscreenAllowed",
    value: function setFullscreenAllowed() {
      this._showFullscreenButton();
    }
  }, {
    key: "setEnterFullscreenOnVideoTap",
    value: function setEnterFullscreenOnVideoTap(enabled) {
      this.enterFullscreenOnVideoTap = enabled;

      if (this.enterFullscreenOnVideoTap) {
        this.fullscreenButton.classList.add("enter-fullscreen-on-video-tap");
      } else {
        this.fullscreenButton.classList.remove("enter-fullscreen-on-video-tap");
      }
    }
  }, {
    key: "setCurrentTime",
    value: function setCurrentTime(time) {
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
  }, {
    key: "setSkipTime",
    value: function setSkipTime(time) {
      this.skipPosition = time;

      this._updateProgressBar();
    }
  }, {
    key: "setCtaText",
    value: function setCtaText(text) {
      this.ctaButton.innerText = text;
    }
  }, {
    key: "setMuted",
    value: function setMuted(muted) {
      this.muted = muted;

      if (this.muted) {
        this.muteButton.querySelector(".toggle_on").style.display = "none";
        this.muteButton.querySelector(".toggle_off").style.display = "unset";
      } else {
        this.muteButton.querySelector(".toggle_on").style.display = "unset";
        this.muteButton.querySelector(".toggle_off").style.display = "none";
      }
    }
  }, {
    key: "setFullscreen",
    value: function setFullscreen(fullscreen) {
      this.fullscreen = fullscreen;

      if (this.fullscreen) {
        this.fullscreenButton.querySelector(".toggle_on").style.display = "none";
        this.fullscreenButton.querySelector(".toggle_off").style.display = "unset";
      } else {
        this.fullscreenButton.querySelector(".toggle_on").style.display = "unset";
        this.fullscreenButton.querySelector(".toggle_off").style.display = "none";
      }
    }
  }, {
    key: "addIcon",
    value: function addIcon(type, data) {
      // Icon data structure:
      // { duration: number, offset: number, width: number, height: number, resource: ... }
      var container = this._createResourceContainer(type, data);

      this.icons.push({
        displayStart: data.offset || 0,
        displayEnd: data.duration ? data.duration + (data.offset || 0) : Number.POSITIVE_INFINITY,
        container: container,
        visible: false,
        viewPayload: data.viewPayload
      });
    } // Internal methods

  }, {
    key: "_debug",
    value: function _debug() {
      if (!this.DEBUG) {
        return;
      }

      var logOutput = [];

      var _iterator = _createForOfIteratorHelper(arguments),
          _step;

      try {
        for (_iterator.s(); !(_step = _iterator.n()).done;) {
          var arg = _step.value;

          if (arg === null) {
            continue;
          }

          if (_typeof(arg) !== 'object') {
            logOutput.push(arg.toString());
            continue;
          }

          logOutput.push(JSON.stringify(arg));
        }
      } catch (err) {
        _iterator.e(err);
      } finally {
        _iterator.f();
      }

      console.log("[vast.js] " + logOutput.join(' '));
    }
  }, {
    key: "_createResourceContainer",
    value: function _createResourceContainer(type, data) {
      var _this = this;

      var container = document.createElement("div");
      container.className = "icon";

      switch (type) {
        case IconResourceType.STATIC:
          var img = document.createElement("div");
          img.className = "image";
          img.style.backgroundImage = "url(\"" + data.resource + "\")";
          container.append(img);
          break;

        case IconResourceType.IFRAME:
          var frame = document.createElement("iframe");
          frame.src = data.resource;
          container.append(frame);
          break;

        case IconResourceType.HTML:
          var injectedStyle = "<style>body{overflow:hidden;margin:0;padding:0;}</style>";
          var dynamicFrame = document.createElement("iframe");
          dynamicFrame.src = "about:blank";

          dynamicFrame.onload = function () {
            dynamicFrame.contentDocument.documentElement.innerHTML = injectedStyle + data.resource;
          };

          container.append(dynamicFrame);
          break;
      }

      var cover = document.createElement("div");
      cover.style.position = "absolute";
      cover.style.left = 0;
      cover.style.top = 0;
      cover.style.width = "100%";
      cover.style.height = "100%";
      container.append(cover);

      container.onclick = function () {
        _this.bridge.call("iconClick", {
          payload: data.clickPayload
        });
      };

      if ((data.width || 0) && (data.height || 0)) {
        var aspect = data.width / data.height;

        if (aspect < 10 && aspect > 0.1) {
          var width = aspect * Math.min(data.height, 32);
          container.style.width = width + "px";
        }
      }

      return container;
    }
  }, {
    key: "_showElement",
    value: function _showElement(element) {
      element.style.visibility = "visible";
      element.style.opacity = 1;
    }
  }, {
    key: "_showCloseButton",
    value: function _showCloseButton() {
      this.closeButtonVisible = true;

      this._showElement(this.closeButton);

      this.bridge.call("closeButtonVisible");
    }
  }, {
    key: "_showMuteButton",
    value: function _showMuteButton() {
      this.muteButtonVisible = true;

      this._showElement(this.muteButton);
    }
  }, {
    key: "_showFullscreenButton",
    value: function _showFullscreenButton() {
      this.fullscreenButtonVisible = true;

      this._showElement(this.fullscreenButton);
    }
  }, {
    key: "_showCtaButton",
    value: function _showCtaButton() {
      this.ctaButtonVisible = true;

      this._showElement(this.ctaButton);
    }
  }, {
    key: "_updateIcons",
    value: function _updateIcons() {
      var _this2 = this;

      var _loop = function _loop(i) {
        var icon = _this2.icons[i];
        var shouldRender = _this2.position >= icon.displayStart && _this2.position <= icon.displayEnd;
        if (shouldRender === icon.visible) return "continue";

        if (shouldRender) {
          icon.visible = true;

          _this2.iconContainer.append(icon.container);

          setTimeout(function () {
            icon.container.style.opacity = "1.0";

            _this2.bridge.call("iconView", {
              payload: icon.viewPayload
            });
          }, 0);
        } else {
          icon.visible = false;
          icon.container.style.opacity = "0.0";
          setTimeout(icon.container.remove.bind(icon.container), 1000);
        }

        _this2.icons[i] = icon;
      };

      for (var i = 0; i < this.icons.length; i++) {
        var _ret = _loop(i);

        if (_ret === "continue") continue;
      }
    }
  }, {
    key: "_updateProgressBar",
    value: function _updateProgressBar() {
      if (this.duration < 0.0001) {
        return;
      }

      this.progressBar.style.width = (this.position / this.duration * 100).toFixed(2) + "%";
      this.skipMarker.style.left = (this.skipPosition / this.duration * 100).toFixed(2) + "%";
    }
  }, {
    key: "_handleClickthrough",
    value: function _handleClickthrough(event) {
      var source = event.target == this.ctaButton ? "ctaBtn" : "video";
      this.bridge.call('open', {
        source: source
      });
    }
  }, {
    key: "_handleClose",
    value: function _handleClose() {
      this.bridge.call('close');
    }
  }, {
    key: "_handleMute",
    value: function _handleMute() {
      this.setMuted(!this.muted);
      this.bridge.call('setMute', {
        muted: this.muted
      });
    }
  }, {
    key: "_handleFullscreen",
    value: function _handleFullscreen() {
      this.setFullscreen(!this.fullscreen);
      this.bridge.call('setFullscreen', {
        fullscreen: this.fullscreen
      });
    }
  }]);

  return Vast;
}();