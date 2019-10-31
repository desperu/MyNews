package org.desperu.mynews.controllers.activities;

import android.os.Bundle;

import org.desperu.mynews.R;
import org.desperu.mynews.controllers.fragments.SearchAndNotificationFragment;
import org.desperu.mynews.utils.MyNewsAlarmManager;

import static org.desperu.mynews.MyNewsTools.FragmentsKeys.*;

public class NotificationsActivity extends BaseActivity implements SearchAndNotificationFragment.OnClickNotificationSwitchListener{

    // --------------
    // BASE METHODS
    // --------------

    @Override
    protected int getActivityLayout() { return R.layout.activity_search_and_notifications; }

    @Override
    protected void configureDesign() {
        this.configureAndShowSearchOrNotificationFragment();
        this.configureToolBar();
        this.configureUpButton();
    }

    @Override
    protected void updateDesign() { }

    // -----------------
    // CONFIGURATION
    // -----------------

    /**
     * Configure and show search or notification fragment.
     */
    private void configureAndShowSearchOrNotificationFragment() {
        SearchAndNotificationFragment searchAndNotificationFragment = (SearchAndNotificationFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_search_and_notifications_frame_layout);

        if (searchAndNotificationFragment == null) {
            searchAndNotificationFragment = new SearchAndNotificationFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(KEY_FRAGMENT, NOTIFICATION_FRAGMENT);
            searchAndNotificationFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_search_and_notifications_frame_layout, searchAndNotificationFragment)
                    .commit();
        }
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
        MyNewsAlarmManager.configureAlarmManager(getApplicationContext());
        if (isChecked) MyNewsAlarmManager.startNotificationsAlarm(getApplicationContext());
        else MyNewsAlarmManager.stopNotificationsAlarm(getApplicationContext());
    }
}