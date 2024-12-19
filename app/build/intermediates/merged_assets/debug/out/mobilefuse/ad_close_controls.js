function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); return Constructor; }

var AdCloseControlsNativeBridge = /*#__PURE__*/function () {
  function AdCloseControlsNativeBridge(controls) {
    _classCallCheck(this, AdCloseControlsNativeBridge);

    this.controls = controls;
    this.queue = [];
    this.requestInFlight = false;
  } // Interface methods for the native layer:


  _createClass(AdCloseControlsNativeBridge, [{
    key: "setCloseButtonCountdownMode",
    value: function setCloseButtonCountdownMode(countdownMode) {
      this.controls.setCloseButtonCountdownMode(countdownMode);
    }
  }, {
    key: "showCloseButton",
    value: function showCloseButton(delaySeconds) {
      this.controls.showCloseButton(delaySeconds);
    }
  }, {
    key: "setThumbnailMode",
    value: function setThumbnailMode(thumbnailMode) {
      this.controls.setThumbnailMode(thumbnailMode);
    }
  }]);

  return AdCloseControlsNativeBridge;
}();

var AdCloseControls = /*#__PURE__*/function () {
  function AdCloseControls(options) {
    _classCallCheck(this, AdCloseControls);

    options = options || {};
    this.bridge = new AdCloseControlsNativeBridge(this); // Controls:

    this.closeButton = new CloseButton(options.closeButton);
  }

  _createClass(AdCloseControls, [{
    key: "setCloseButtonCountdownMode",
    value: function setCloseButtonCountdownMode(countdownMode) {
      this.closeButton.setCountdownMode(countdownMode);
    }
  }, {
    key: "showCloseButton",
    value: function showCloseButton(delaySeconds) {
      this.closeButton.show(delaySeconds);
    }
  }, {
    key: "setThumbnailMode",
    value: function setThumbnailMode(thumbnailMode) {
      if (thumbnailMode) {
        this.closeButton.enterThumbnailMode();
      } else {
        this.closeButton.exitThumbnailMode();
      }
    }
  }]);

  return AdCloseControls;
}();