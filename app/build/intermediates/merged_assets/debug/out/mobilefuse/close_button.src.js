class CloseButton {
    constructor(button) {
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

    setVisible() {
        if (this.isVisible) {
            return;
        }
        this.isVisible = true;
        this.button.style.visibility = "visible";
        this.button.style.opacity = 1;
    }

    setDelaySeconds(delaySeconds) {
        this.delaySeconds = delaySeconds;
    }

    setCountdownMode(countdownMode) {
        this.countdownMode = countdownMode;
    }

    show(delaySeconds) {
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

    setClosable() {
        this.isClosable = true;
        this.updateButtonState(0);
        this.setVisible();
    }

    startTimer() {
        let remainingSeconds = this.delaySeconds;
        let remainingMs = this.delaySeconds * 1000;

        this.timerInterval = setInterval(() => {
            remainingSeconds--;

            if (remainingSeconds <= 0) {
                this.clearTimer();
                this.updateButtonState(0, 1);
                this.setClosable();
            } else {
                this.updateButtonState(remainingSeconds);
            }
        }, 1000);

        this.timerInterval2 = setInterval(() => {
            remainingMs -= 50;
            this.updateFractionalGraphic(1 - ((remainingMs / 1000) / this.delaySeconds))
        }, 50);
    }

    updateButtonState(remainingSeconds) {
        while (this.button.firstChild) {
            this.button.removeChild(this.button.lastChild);
        }

        if (remainingSeconds === 0) {
            this.button.appendChild(this.closeSvg);
        } else {
            this.countdownDiv.innerText = `${remainingSeconds}`;
            this.button.appendChild(this.spinSvg);
            this.button.appendChild(this.countdownDiv);
        }
    }

    updateFractionalGraphic(percent) {
        this.spinSvg.querySelector('.pc-prog').style.strokeDasharray = (percent * 88).toFixed(0) + ' 88'
    }

    clearTimer() {
        clearInterval(this.timerInterval);
        clearInterval(this.timerInterval2);
    }

    enterThumbnailMode() {
        this.nonThumbnailTop = this.button.style.top;
        this.nonThumbnailRight = this.button.style.right;
        this.button.style.top = '4px';
        this.button.style.right = '4px';

    }

    exitThumbnailMode() {
        if (typeof this.nonThumbnailTop === 'undefined') {
            return;
        }
        this.button.style.top = this.nonThumbnailTop;
        this.button.style.right = this.nonThumbnailRight;
    }
}
