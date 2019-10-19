package org.desperu.mynews.Controllers.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.widget.Toast;

import org.desperu.mynews.Controllers.Fragments.SearchArticlesFragment;
import org.desperu.mynews.MyNewsTools;
import org.desperu.mynews.R;
import org.desperu.mynews.Utils.NotificationsAlarmService;

public class NotificationsActivity extends BaseActivity implements SearchArticlesFragment.OnClickedActionListener{

    private SearchArticlesFragment searchArticlesFragment;
    private PendingIntent pendingIntent;

    // --------------
    // BASE METHODS
    // --------------

    @Override
    protected int getActivityLayout() { return R.layout.activity_search; }

    @Override
    protected void configureDesign() {
        this.configureAndShowSearchArticlesFragment();
        this.configureToolbar();
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
        searchArticlesFragment = (SearchArticlesFragment) getSupportFragmentManager().findFragmentById(R.id.activity_search_frame_layout);

        if (searchArticlesFragment == null) {
            searchArticlesFragment = new SearchArticlesFragment(MyNewsTools.FragmentsKeys.NOTIFICATION_FRAGMENT);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_search_frame_layout, searchArticlesFragment)
                    .commit();
        }
    }

    /**
     * Configure alarm manager for notifications.
     */
    private void configureAlarmManager() {
        Intent alarmIntent = new Intent(this, NotificationsAlarmService.class);
        pendingIntent = PendingIntent.getService(getBaseContext(), 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    // -----------------
    // ACTION
    // -----------------

    @Override
    public void OnClickedSearchButton(String queryTerms, String beginDate,
                                      String endDate, String sections) {
    }

    @Override
    public void OnClickedNotificationSwitch(boolean isChecked) {
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
        manager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), AlarmManager.INTERVAL_DAY, pendingIntent);
        Toast.makeText(this, R.string.toast_notification_enable, Toast.LENGTH_SHORT).show();
    }

    /**
     * Disable notification alarm.
     */
    private void stopNotificationsAlarm() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(this, R.string.toast_notification_disable, Toast.LENGTH_SHORT).show();
    }
}