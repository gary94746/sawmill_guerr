package controllers.utils;

import javafx.scene.control.Tooltip;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class Messages {
    public static void setMessage(String title, String message, NotificationType type) {
        TrayNotification trayNotification = new TrayNotification();
        trayNotification.setTitle(title);
        trayNotification.setMessage(message);
        trayNotification.setNotificationType(type);
        trayNotification.setAnimationType(AnimationType.POPUP);
        trayNotification.showAndDismiss(Duration.millis(3000));
    }

    public static Tooltip setTooltipMessage(String tooltip){
        Tooltip t = new Tooltip(tooltip);
        t.setShowDelay(Duration.millis(40));
        t.setShowDuration(Duration.INDEFINITE);
        return t;
    }
}
