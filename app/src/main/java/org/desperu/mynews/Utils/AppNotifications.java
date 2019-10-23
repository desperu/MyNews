package org.desperu.mynews.Utils;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import java.util.Objects;

public class AppNotifications extends Application {

    public static final String CHANNEL_ID = "MyNewsNotification";
    private static final String NOTIFICATION_NAME = "NewsArticles";
    private static final String CHANNEL_DESCRIPTION = "My News notifications channel";

    @Override
    public void onCreate() {
        super.onCreate();
        this.createNotificationChannel();
    }

    /**
     * Create notification channel for news articles notification.
     */
    private void createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    CHANNEL_ID,
                    NOTIFICATION_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(CHANNEL_DESCRIPTION);
            NotificationManager manager = getSystemService(NotificationManager.class);
            Objects.requireNonNull(manager).createNotificationChannel(notificationChannel);
        }
    }
}
