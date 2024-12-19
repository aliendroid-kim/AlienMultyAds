
class AdCloseControlsNativeBridge {

    constructor(controls) {
        this.controls = controls;
        this.queue = [];
        this.requestInFlight = false;
    }

    // Interface methods for the native layer:

    setCloseButtonCountdownMode(countdownMode) {
        this.controls.setCloseButtonCountdownMode(countdownMode);
    }

    showCloseButton(delaySeconds) {
        this.controls.showCloseButton(delaySeconds);
    }

    setThumbnailMode(thumbnailMode) {
        this.controls.setThumbnailMode(thumbnailMode);
    }

}

class AdCloseControls {

    constructor(options) {
        options = options || {};
        this.bridge = new AdCloseControlsNativeBridge(this);

        // Controls:
        this.closeButton = new CloseButton(options.closeButton);
    }

    setCloseButtonCountdownMode(countdownMode) {
        this.closeButton.setCountdownMode(countdownMode);
    }

    showCloseButton(delaySeconds) {
        this.closeButton.show(delaySeconds);
    }

    setThumbnailMode(thumbnailMode) {
        if (thumbnailMode) {
            this.closeButton.enterThumbnailMode();
        } else {
            this.closeButton.exitThumbnailMode();
        }
    }

}
