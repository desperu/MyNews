package org.desperu.mynews.Controllers.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import org.desperu.mynews.Controllers.Fragments.SearchArticlesFragment;
import org.desperu.mynews.R;
import org.desperu.mynews.Utils.NotificationsAlarmService;

import java.util.Objects;

import static org.desperu.mynews.MyNewsTools.FragmentsKeys.*;

public class NotificationsActivity extends BaseActivity implements SearchArticlesFragment.OnClickedNotificationSwitchListener{

    private PendingIntent pendingIntent;

    // --------------
    // BASE METHODS
    // --------------

    @Override
    protected int getActivityLayout() { return R.layout.activity_search_and_notifications; }

    @Override
    protected void configureDesign() {
        this.configureAndShowSearchArticlesFragment();
        this.configureToolBar();
        this.configureUpButton();
        this.configureAlarmManager();
    }

    @Override
    protected void updateDesign() { }

    // -----------------
    // CONFIGURATION
    // -----------------

    /**
     * Configure and show search articles fragment.
     */
    private void configureAndShowSearchArticlesFragment() {
        SearchArticlesFragment searchArticlesFragment = (SearchArticlesFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_search_and_notifications_frame_layout);

        if (searchArticlesFragment == null) {
            searchArticlesFragment = new SearchArticlesFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(KEY_FRAGMENT, NOTIFICATION_FRAGMENT);
            searchArticlesFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_search_and_notifications_frame_layout, searchArticlesFragment)
                    .commit();
        }
    }

    /**
     * Configure alarm manager for notifications.
     */
    private void configureAlarmManager() {
        Intent alarmIntent = new Intent(getApplicationContext(), NotificationsAlarmService.class);
        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, alarmIntent, 0);
    }

    // -----------------
    // ACTION
    // -----------------

    @Override
    public void OnClickNotificationListener(boolean isChecked) {
        this.configureNotifications(isChecked);
    }

    // -----------------
    // NOTIFICATIONS
    // -----------------

    /**
     * Configure notifications.
     * @param isChecked State of switch notifications.
     */
    private void configureNotifications(boolean isChecked) {
        if (isChecked) this.startNotificationsAlarm();
        else this.stopNotificationsAlarm();
    }

    /**
     * Enable notification alarm.
     */
    private void startNotificationsAlarm() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Objects.requireNonNull(manager).setRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        Toast.makeText(this, R.string.toast_notification_enable, Toast.LENGTH_SHORT).show();
    }

    /**
     * Disable notification alarm.
     */
    private void stopNotificationsAlarm() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Objects.requireNonNull(manager).cancel(pendingIntent);
        Toast.makeText(this, R.string.toast_notification_disable, Toast.LENGTH_SHORT).show();
    }
}