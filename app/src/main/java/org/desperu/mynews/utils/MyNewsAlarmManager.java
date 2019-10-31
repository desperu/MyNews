package org.desperu.mynews.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import org.desperu.mynews.R;

import java.util.Objects;

public class MyNewsAlarmManager {

    public static PendingIntent pendingIntent;

    /**
     * Configure alarm manager for notifications.
     * @param context Context from this method is called.
     */
    public static void configureAlarmManager(Context context) {
        Intent alarmIntent = new Intent(context, NotificationsAlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
    }

    /**
     * Enable notification alarm.
     * @param context Context from this method is called.
     */
    public static void startNotificationsAlarm(Context context) {
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Objects.requireNonNull(manager).setRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        Toast.makeText(context, R.string.toast_notification_enable, Toast.LENGTH_SHORT).show();
    }

    /**
     * Disable notification alarm.
     * @param context Context from this method is called.
     */
    public static void stopNotificationsAlarm(Context context) {
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Objects.requireNonNull(manager).cancel(pendingIntent);
        Toast.makeText(context, R.string.toast_notification_disable, Toast.LENGTH_SHORT).show();
    }
}