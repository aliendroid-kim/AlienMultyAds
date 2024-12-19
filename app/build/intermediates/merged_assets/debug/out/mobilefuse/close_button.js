function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); return Constructor; }

var CloseButton = /*#__PURE__*/function () {
  function CloseButton(button) {
    _classCallCheck(this, CloseButton);

    this.isClosable = false;
    this.isVisible = false;
    this.countdownMode = true;
    this.delaySeconds = -1;
    this.button = button;
    this.spinSvg = this.button.querySelector("#c_p");
    this.closeSvg = this.button.querySelector("#c_x");
    this.countdownDiv = document.createElement("div");
    this.button.innerHTML = '';
    this.button.style.opacity = 0;
  }

  _createClass(CloseButton, [{
    key: "setVisible",
    value: function setVisible() {
      if (this.isVisible) {
        return;
      }

      this.isVisible = true;
      this.button.style.visibility = "visible";
      this.button.style.opacity = 1;
    }
  }, {
    key: "setDelaySeconds",
    value: function setDelaySeconds(delaySeconds) {
      this.delaySeconds = delaySeconds;
    }
  }, {
    key: "setCountdownMode",
    value: function setCountdownMode(countdownMode) {
      this.countdownMode = countdownMode;
    }
  }, {
    key: "show",
    value: function show(delaySeconds) {
      this.delaySeconds = delaySeconds;

      if (this.isClosable) {
        return;
      }

      if (this.delaySeconds == -1) {
        return;
      }

      if (this.countdownMode) {
        this.setVisible();
      }

      this.setDelaySeconds(delaySeconds);
      this.updateButtonState(this.delaySeconds);
      this.startTimer();
    }
  }, {
    key: "setClosable",
    value: function setClosable() {
      this.isClosable = true;
      this.updateButtonState(0);
      this.setVisible();
    }
  }, {
    key: "startTimer",
    value: function startTimer() {
      var _this = this;

      var remainingSeconds = this.delaySeconds;
      var remainingMs = this.delaySeconds * 1000;
      this.timerInterval = setInterval(function () {
        remainingSeconds--;

        if (remainingSeconds <= 0) {
          _this.clearTimer();

          _this.updateButtonState(0, 1);

          _this.setClosable();
        } else {
          _this.updateButtonState(remainingSeconds);
        }
      }, 1000);
      this.timerInterval2 = setInterval(function () {
        remainingMs -= 50;

        _this.updateFractionalGraphic(1 - remainingMs / 1000 / _this.delaySeconds);
      }, 50);
    }
  }, {
    key: "updateButtonState",
    value: function updateButtonState(remainingSeconds) {
      while (this.button.firstChild) {
        this.button.removeChild(this.button.lastChild);
      }

      if (remainingSeconds === 0) {
        this.button.appendChild(this.closeSvg);
      } else {
        this.countdownDiv.innerText = "".concat(remainingSeconds);
        this.button.appendChild(this.spinSvg);
        this.button.appendChild(this.countdownDiv);
      }
    }
  }, {
    key: "updateFractionalGraphic",
    value: function updateFractionalGraphic(percent) {
      this.spinSvg.querySelector('.pc-prog').style.strokeDasharray = (percent * 88).toFixed(0) + ' 88';
    }
  }, {
    key: "clearTimer",
    value: function clearTimer() {
      clearInterval(this.timerInterval);
      clearInterval(this.timerInterval2);
    }
  }, {
    key: "enterThumbnailMode",
    value: function enterThumbnailMode() {
      this.nonThumbnailTop = this.button.style.top;
      this.nonThumbnailRight = this.button.style.right;
      this.button.style.top = '4px';
      this.button.style.right = '4px';
    }
  }, {
    key: "exitThumbnailMode",
    value: function exitThumbnailMode() {
      if (typeof this.nonThumbnailTop === 'undefined') {
        return;
      }

      this.button.style.top = this.nonThumbnailTop;
      this.button.style.right = this.nonThumbnailRight;
    }
  }]);

  return CloseButton;
}();