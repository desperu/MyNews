package org.desperu.mynews.Controllers.Activities;

import org.desperu.mynews.Controllers.Fragments.SearchArticlesFragment;
import org.desperu.mynews.MyNewsTools;
import org.desperu.mynews.R;

public class NotificationsActivity extends BaseActivity implements SearchArticlesFragment.OnClickedActionListener{

    private SearchArticlesFragment searchArticlesFragment;

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

    // -----------------
    // ACTION
    // -----------------

    @Override
    public void OnClickedSearchButton(String queryTerms, String beginDate,
                                      String endDate, String sections) {
    }

    @Override
    public void OnClickedNotificationSwitch(boolean switchState) {
        this.configureNotifications(switchState);
    }

    // -----------------
    // NOTIFICATIONS
    // -----------------

    /**
     * Configure notifications.
     * @param switchState State of switch notifications.
     */
    private void configureNotifications(boolean switchState) {

    }
}
