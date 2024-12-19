"use strict";

function ownKeys(object, enumerableOnly) { var keys = Object.keys(object); if (Object.getOwnPropertySymbols) { var symbols = Object.getOwnPropertySymbols(object); if (enumerableOnly) { symbols = symbols.filter(function (sym) { return Object.getOwnPropertyDescriptor(object, sym).enumerable; }); } keys.push.apply(keys, symbols); } return keys; }

function _objectSpread(target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i] != null ? arguments[i] : {}; if (i % 2) { ownKeys(Object(source), true).forEach(function (key) { _defineProperty(target, key, source[key]); }); } else if (Object.getOwnPropertyDescriptors) { Object.defineProperties(target, Object.getOwnPropertyDescriptors(source)); } else { ownKeys(Object(source)).forEach(function (key) { Object.defineProperty(target, key, Object.getOwnPropertyDescriptor(source, key)); }); } } return target; }

function _typeof(obj) { "@babel/helpers - typeof"; if (typeof Symbol === "function" && typeof Symbol.iterator === "symbol") { _typeof = function _typeof(obj) { return typeof obj; }; } else { _typeof = function _typeof(obj) { return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj; }; } return _typeof(obj); }

function _createForOfIteratorHelper(o, allowArrayLike) { var it = typeof Symbol !== "undefined" && o[Symbol.iterator] || o["@@iterator"]; if (!it) { if (Array.isArray(o) || (it = _unsupportedIterableToArray(o)) || allowArrayLike && o && typeof o.length === "number") { if (it) o = it; var i = 0; var F = function F() {}; return { s: F, n: function n() { if (i >= o.length) return { done: true }; return { done: false, value: o[i++] }; }, e: function e(_e) { throw _e; }, f: F }; } throw new TypeError("Invalid attempt to iterate non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method."); } var normalCompletion = true, didErr = false, err; return { s: function s() { it = it.call(o); }, n: function n() { var step = it.next(); normalCompletion = step.done; return step; }, e: function e(_e2) { didErr = true; err = _e2; }, f: function f() { try { if (!normalCompletion && it.return != null) it.return(); } finally { if (didErr) throw err; } } }; }

function _unsupportedIterableToArray(o, minLen) { if (!o) return; if (typeof o === "string") return _arrayLikeToArray(o, minLen); var n = Object.prototype.toString.call(o).slice(8, -1); if (n === "Object" && o.constructor) n = o.constructor.name; if (n === "Map" || n === "Set") return Array.from(o); if (n === "Arguments" || /^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)) return _arrayLikeToArray(o, minLen); }

function _arrayLikeToArray(arr, len) { if (len == null || len > arr.length) len = arr.length; for (var i = 0, arr2 = new Array(len); i < len; i++) { arr2[i] = arr[i]; } return arr2; }

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); return Constructor; }

// mraid.js
// Copyright 2021 MobileFuse LLC
var EventListener = /*#__PURE__*/function () {
  function EventListener(event) {
    _classCallCheck(this, EventListener);

    this.event = event;
    this.listeners = {};
  }

  _createClass(EventListener, [{
    key: "add",
    value: function add(callback) {
      var id = String(callback);
      this.listeners[id] = callback;
    }
  }, {
    key: "remove",
    value: function remove(callback) {
      var id = String(callback);

      if (this.listeners[id]) {
        delete this.listeners[id];
        return true;
      }

      return false;
    }
  }, {
    key: "removeAll",
    value: function removeAll() {
      this.listeners = {};
    }
  }, {
    key: "invoke",
    value: function invoke(scope, args) {
      for (var callbackId in this.listeners) {
        if (!this.listeners.hasOwnProperty(callbackId)) {
          continue;
        }

        this.listeners[callbackId].apply(scope, args);
      }
    }
  }]);

  return EventListener;
}();

var NativeBridge = /*#__PURE__*/function () {
  function NativeBridge(mraid) {
    _classCallCheck(this, NativeBridge);

    this.mraid = mraid;
    this.queue = [];
    this.requestInFlight = false;
  }

  _createClass(NativeBridge, [{
    key: "call",
    value: function call(method, args) {
      var callUri = "mraid://" + method;

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

      this.mraid._debug("Bridge, calling native " + call);

      window.location = call;
    }
  }, {
    key: "nativeCallComplete",
    value: function nativeCallComplete() {
      if (!this.requestInFlight) return;
      this.requestInFlight = false;

      this._next();
    } // Interface methods for the native layer:

  }, {
    key: "init",
    value: function init(appDetails) {
      this.call("initBridge", appDetails);
    }
  }, {
    key: "setCurrentPosition",
    value: function setCurrentPosition(properties) {
      console.log("[mraid] :: setCurrentPosition(" + JSON.stringify(properties) + ")");
      this.mraid.currentPosition = this.mraid._assignProperties(properties, this.mraid.currentPosition);
    }
  }, {
    key: "setCurrentAppOrientation",
    value: function setCurrentAppOrientation(properties) {
      console.log("[mraid] :: setCurrentAppOrientation(" + JSON.stringify(properties) + ")");
      this.mraid.currentAppOrientation = this.mraid._assignProperties(properties, this.mraid.currentAppOrientation);
    }
  }, {
    key: "setDefaultPosition",
    value: function setDefaultPosition(properties) {
      this.mraid.defaultPosition = this.mraid._assignProperties(properties, this.mraid.defaultPosition);
    }
  }, {
    key: "setMaxSize",
    value: function setMaxSize(properties) {
      this.mraid.maxSize = this.mraid._assignProperties(properties, this.mraid.maxSize);
    }
  }, {
    key: "setPlacementType",
    value: function setPlacementType(placementType) {
      this.mraid.placementType = placementType;
    }
  }, {
    key: "setScreenSize",
    value: function setScreenSize(properties) {
      console.log("[mraid] :: setScreenSize(" + JSON.stringify(properties) + ")");
      this.mraid.screenSize = this.mraid._assignProperties(properties, this.mraid.screenSize);
    }
  }, {
    key: "setState",
    value: function setState(state) {
      this.mraid.state = state;

      this.mraid._broadcastEvent(Mraid.Event.STATE_CHANGE, state);
    }
  }, {
    key: "setIsViewable",
    value: function setIsViewable(isViewable) {
      if (isViewable === this.mraid.viewable) return;
      this.mraid.viewable = isViewable;

      this.mraid._broadcastEvent(Mraid.Event.VIEWABLE_CHANGE, isViewable);
    }
  }, {
    key: "setExposureChange",
    value: function setExposureChange(exposurePrcnt) {
      this.mraid._broadcastEvent(Mraid.Event.EXPOSURE_CHANGE, exposurePrcnt);
    }
  }, {
    key: "setSupports",
    value: function setSupports(supports) {
      this.mraid.supportProperties = this.mraid._assignProperties(supports, this.mraid.supportProperties);
    }
  }, {
    key: "notifyReadyEvent",
    value: function notifyReadyEvent() {
      this.mraid._broadcastEvent(Mraid.Event.READY);
    }
  }, {
    key: "notifySizeChangeEvent",
    value: function notifySizeChangeEvent(width, height) {
      this.mraid._broadcastEvent(Mraid.Event.SIZE_CHANGE, width, height);
    }
  }, {
    key: "notifyStateChangeEvent",
    value: function notifyStateChangeEvent() {
      this.mraid._broadcastEvent(Mraid.Event.STATE_CHANGE, this.mraid.state);
    }
  }, {
    key: "notifyViewableChangeEvent",
    value: function notifyViewableChangeEvent() {
      this.mraid._broadcastEvent(Mraid.Event.VIEWABLE_CHANGE, this.mraid.isViewable());
    }
  }]);

  return NativeBridge;
}();

var SplashAd = /*#__PURE__*/function () {
  function SplashAd(bridge) {
    _classCallCheck(this, SplashAd);

    this.bridge = bridge;
  }

  _createClass(SplashAd, [{
    key: "transition",
    value: function transition() {
      this.bridge.call('splashAdTransition');
    }
  }, {
    key: "expand",
    value: function expand() {
      this.bridge.call('splashAdExpand');
    }
  }]);

  return SplashAd;
}();

var Mraid = /*#__PURE__*/function () {
  function Mraid(options) {
    _classCallCheck(this, Mraid);

    options = options || {};
    this.VERSION = "3.0";
    this.isMobileFuseSdk = true;
    this.DEBUG = options.DEBUG || false;
    this.bridge = new NativeBridge(this); // Internal mraid state:

    this.state = Mraid.State.LOADING;
    this.viewable = false;
    this.eventListeners = {};
    this.placementType = Mraid.PlacementType.UNKNOWN; // Our mraid properties:

    this.expandProperties = {
      width: false,
      height: false,
      useCustomClose: false,
      isModal: true
    };
    this.resizeProperties = {
      width: false,
      height: false,
      offsetX: false,
      offsetY: false,
      customClosePosition: 'top-right',
      allowOffscreen: true
    };
    this.orientationProperties = {
      allowOrientationChange: true,
      forceOrientation: "none"
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
    }; // State:

    this.maxSize = {
      width: 0,
      height: 0
    };
    this.currentPosition = {
      x: 0,
      y: 0,
      width: 0,
      height: 0
    };
    this.currentAppOrientation = {
      orientation: "none",
      locked: false
    };
    this.defaultPosition = {
      x: 0,
      y: 0,
      width: 0,
      height: 0
    };
    this.screenSize = {
      width: 0,
      height: 0
    }; // SplashAd

    this.splashAd = new SplashAd(this.bridge);
  }

  _createClass(Mraid, [{
    key: "_broadcastEvent",
    value: // Private/Helper methods:
    function _broadcastEvent() {
      var args = Array.prototype.slice.call(arguments);
      var event = args.shift();

      if (this.eventListeners[event]) {
        this.eventListeners[event].invoke(this, args);
      }

      if (event !== Mraid.Event.DEBUG_LOG) {
        this._debug("_broadcastEvent", event, args);
      }
    }
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

      console.log("[mraid.js] " + logOutput.join(' '));

      this._broadcastEvent(Mraid.Event.DEBUG_LOG, logOutput.join(' '));
    }
  }, {
    key: "_assignProperties",
    value: function _assignProperties(from, to, properties) {
      if (!properties) properties = Object.keys(to);

      var obj = _objectSpread({}, to);

      var _iterator2 = _createForOfIteratorHelper(properties),
          _step2;

      try {
        for (_iterator2.s(); !(_step2 = _iterator2.n()).done;) {
          var p = _step2.value;

          if (from.hasOwnProperty(p)) {
            obj[p] = from[p];
          }
        }
      } catch (err) {
        _iterator2.e(err);
      } finally {
        _iterator2.f();
      }

      return obj;
    } // Core MRAID 3.0 interface:
    // See: https://www.iab.com/wp-content/uploads/2017/07/MRAID_3.0_FINAL.pdf
    // Section 2.3 - Interface, Page 14

  }, {
    key: "getVersion",
    value: function getVersion() {
      return this.VERSION;
    }
  }, {
    key: "addEventListener",
    value: function addEventListener(event, listener) {
      if (!this.eventListeners[event]) {
        this.eventListeners[event] = new EventListener(event);
      }

      this.eventListeners[event].add(listener);
    }
  }, {
    key: "removeEventListener",
    value: function removeEventListener(event, listener) {
      if (!event) {
        this._broadcastEvent(Mraid.Event.ERROR, 'Event is required.', 'removeEventListener');

        return;
      }

      if (listener) {
        // If we have a valid event, we'll try to remove the listener from it.
        var success = false;

        if (this.eventListeners[event]) {
          success = this.eventListeners[event].remove(listener);
        } // If we didn't have a valid event or couldn't remove the listener from the event, broadcast an error and return early.


        if (!success) {
          this._broadcastEvent(Mraid.Event.ERROR, 'Listener not currently registered for event.', 'removeEventListener');
        }
      } else if (!listener && this.eventListeners[event]) {
        this.eventListeners[event].removeAll();
      }
    }
  }, {
    key: "open",
    value: function open(url) {
      if (!url) {
        this._broadcastEvent(Mraid.Event.ERROR, 'Url is required for the open() call.', 'open');
      } else {
        this.bridge.call('open', {
          url: url
        });
      }
    }
  }, {
    key: "close",
    value: function close() {
      if (this.state === Mraid.State.HIDDEN) {
        this._broadcastEvent(Mraid.Event.ERROR, 'Ad cannot be closed when it is already hidden.', 'close');
      } else {
        this.bridge.call('close');
      }
    } // Deprecated

  }, {
    key: "useCustomClose",
    value: function useCustomClose(shouldUseCustomClose) {
      this.expandProperties.useCustomClose = shouldUseCustomClose;
      this.bridge.call('useCustomClose', {
        shouldUseCustomClose: shouldUseCustomClose
      });
    }
  }, {
    key: "unload",
    value: function unload() {
      console.log("[mraid] :: unload");
      this.bridge.call('unload');
    }
  }, {
    key: "expand",
    value: function expand(url) {
      if (!(this.state === Mraid.State.DEFAULT || this.state === Mraid.State.RESIZED)) {
        this._broadcastEvent(Mraid.Event.ERROR, 'Ad can only be expanded from the default or resized state.', 'expand');

        return;
      }

      var args = _objectSpread({}, this.expandProperties);

      args.allowOrientationChange = this.orientationProperties.allowOrientationChange;
      args.forceOrientation = this.orientationProperties.forceOrientation; //        args.allowOrientationChange = true;
      //        args.forceOrientation = "landscape";

      if (url) {
        args.url = url;
      }

      this.bridge.call('expand', args);
    } // Deprecated

  }, {
    key: "isViewable",
    value: function isViewable() {
      return this.viewable;
    }
  }, {
    key: "playVideo",
    value: function playVideo(uri) {
      if (!mraid.isViewable()) {
        this._broadcastEvent(Mraid.Event.ERROR, 'playVideo cannot be called until the ad is viewable', 'playVideo');

        return;
      }

      if (!uri) {
        this._broadcastEvent(Mraid.Event.ERROR, 'playVideo must be called with a valid URI', 'playVideo');
      } else {
        this.bridge.call('playVideo', {
          uri: uri
        });
      }
    }
  }, {
    key: "resize",
    value: function resize() {
      if (!(this.getState() === Mraid.State.DEFAULT || this.getState() === Mraid.State.RESIZED)) {
        this._broadcastEvent(Mraid.Event.ERROR, 'Ad can only be resized from the default or resized state.', 'resize');
      } else if (!this.resizeProperties.width || !this.resizeProperties.height) {
        this._broadcastEvent(Mraid.Event.ERROR, 'Must set resize properties before calling resize()', 'resize');
      } else {
        this.bridge.call('resize', this.resizeProperties);
      }
    }
  }, {
    key: "storePicture",
    value: function storePicture(uri) {
      if (!mraid.isViewable()) {
        this._broadcastEvent(Mraid.Event.ERROR, 'storePicture cannot be called until the ad is viewable', 'storePicture');

        return;
      }

      if (!uri) {
        this._broadcastEvent(Mraid.Event.ERROR, 'storePicture must be called with a valid URI', 'storePicture');
      } else {
        this.bridge.call('storePicture', {
          uri: uri
        });
      }
    }
  }, {
    key: "setTransparentBackground",
    value: function setTransparentBackground(transparent) {
      console.log("[mraid Transparent Interstitial] :: setTransparentBackground(" + transparent + ")");
      this.bridge.call('setTransparentBackground', {
        transparent: transparent
      });
    }
  }, {
    key: "createCalendarEvent",
    value: function createCalendarEvent(parameters) {
      //        throw "Not implemented :: createCalendarEvent()";
      if (!mraid.isViewable()) {
        this._broadcastEvent(Mraid.Event.ERROR, 'createCalendarEvent cannot be called until the ad is viewable', 'createCalendarEvent');

        return;
      }

      if (!parameters) {
        this._broadcastEvent(Mraid.Event.ERROR, 'createCalendarEvent must be called with a valid parameters', 'createCalendarEvent');
      } else {
        this.bridge.call('createCalendarEvent', {
          details: JSON.stringify(parameters)
        });
      }
    } // VPAID --

  }, {
    key: "initVpaid",
    value: function initVpaid(vpaidObject) {
      throw "Not implemented :: initVpaid()";
    } // Properties --

  }, {
    key: "supports",
    value: function supports(feature) {
      return this.supportProperties[feature];
    }
  }, {
    key: "getPlacementType",
    value: function getPlacementType() {
      return this.placementType;
    }
  }, {
    key: "getOrientationProperties",
    value: function getOrientationProperties() {
      console.log("[mraid] :: getOrientationProperties()");
      return _objectSpread({}, this.orientationProperties);
    }
  }, {
    key: "setOrientationProperties",
    value: function setOrientationProperties(properties) {
      console.log("[mraid] :: setOrientationProperties(" + JSON.stringify(properties) + ")");

      if (this.getState() === Mraid.State.LOADING) {
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
  }, {
    key: "getCurrentAppOrientation",
    value: function getCurrentAppOrientation() {
      console.log("[mraid] :: getCurrentAppOrientation()");
      return _objectSpread({}, this.currentAppOrientation);
    }
  }, {
    key: "getCurrentPosition",
    value: function getCurrentPosition() {
      return _objectSpread({}, this.currentPosition);
    }
  }, {
    key: "getDefaultPosition",
    value: function getDefaultPosition() {
      return _objectSpread({}, this.defaultPosition);
    }
  }, {
    key: "getState",
    value: function getState() {
      return this.state;
    }
  }, {
    key: "getExpandProperties",
    value: function getExpandProperties() {
      return _objectSpread({}, this.expandProperties);
    }
  }, {
    key: "setExpandProperties",
    value: function setExpandProperties(properties) {
      this.expandProperties = this._assignProperties(properties, this.expandProperties);
    }
  }, {
    key: "getMaxSize",
    value: function getMaxSize() {
      console.log("[mraid] :: getMaxSize(" + JSON.stringify(this.maxSize) + ")");
      return _objectSpread({}, this.maxSize);
    }
  }, {
    key: "getScreenSize",
    value: function getScreenSize() {
      console.log("[mraid] :: getScreenSize(" + JSON.stringify(this.screenSize) + ")");
      return _objectSpread({}, this.screenSize);
    }
  }, {
    key: "getResizeProperties",
    value: function getResizeProperties() {
      return _objectSpread({}, this.resizeProperties);
    }
  }, {
    key: "setResizeProperties",
    value: function setResizeProperties(properties) {
      this._assignProperties(properties, this.resizeProperties);
    }
  }, {
    key: "getLocation",
    value: function getLocation() {
      var location = _objectSpread({}, this.locationProperties);

      if (location.lastfix instanceof Date) {
        // 'lastfix' should be number of seconds since the last GPS fix:
        location.lastfix = (new Date() - location.lastfix) / 1000;
      }

      return location;
    }
  }]);

  return Mraid;
}();

_defineProperty(Mraid, "Event", {
  ERROR: 'error',
  READY: 'ready',
  SIZE_CHANGE: 'sizeChange',
  STATE_CHANGE: 'stateChange',
  EXPOSURE_CHANGE: 'exposureChange',
  AUDIO_VOLUME_CHANGE: 'audioVolumeChange',
  VIEWABLE_CHANGE: 'viewableChange',
  // Deprecated
  DEBUG_LOG: 'debugLog' // Extension

});

_defineProperty(Mraid, "LocationType", {
  INVALID: 0,
  GPS: 1,
  IP_ADDRESS: 2,
  USER_PROVIDED: 3
});

_defineProperty(Mraid, "State", {
  LOADING: 'loading',
  DEFAULT: 'default',
  EXPANDED: 'expanded',
  HIDDEN: 'hidden',
  RESIZED: 'resized'
});

_defineProperty(Mraid, "PlacementType", {
  UNKNOWN: 'unknown',
  INLINE: 'inline',
  INTERSTITIAL: 'interstitial'
});

if (!window.mraid || !window.mraid.isMobileFuseSdk) {
  var _mraid = window.mraid = new Mraid({
    DEBUG: MRAID_ENV.debug || false
  });
}