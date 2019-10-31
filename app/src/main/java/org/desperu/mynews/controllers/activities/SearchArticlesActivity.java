package org.desperu.mynews.controllers.activities;

import android.content.Intent;
import android.os.Bundle;

import org.desperu.mynews.controllers.fragments.SearchAndNotificationFragment;
import org.desperu.mynews.R;
import org.desperu.mynews.utils.MyNewsAlarmManager;

import static org.desperu.mynews.MyNewsTools.FragmentsKeys.*;

public class SearchArticlesActivity extends BaseActivity implements SearchAndNotificationFragment.OnClickSearchAndNotifySearchButtonListener{

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
        SearchAndNotificationFragment searchAndNotificationFragment = (SearchAndNotificationFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.activity_search_and_notifications_frame_layout);

        if (searchAndNotificationFragment == null) {
            searchAndNotificationFragment = new SearchAndNotificationFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(KEY_FRAGMENT, SEARCH_FRAGMENT);
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
    public void OnClickSearchListener(String queryTerms, String beginDate,
                                String endDate, String sections) {
        this.showSearchResultsActivity(queryTerms, beginDate, endDate, sections);
    }

    @Override
    public void OnClickNotifySearchListener(boolean isNotificationEnabled) {
        if (!isNotificationEnabled) {
            MyNewsAlarmManager.startNotificationsAlarm(getApplicationContext());
            MyNewsAlarmManager.configureAlarmManager(getApplicationContext());
        }
    }

    // -----------------
    // ACTIVITY
    // -----------------

    /**
     * Start search results activity.
     * @param queryTerms Terms of the query.
     * @param beginDate Begin date for search.
     * @param endDate End date for search.
     * @param sections Sections into search.
     */
    private void showSearchResultsActivity(String queryTerms, String beginDate,
                                           String endDate, String sections) {
        startActivity(new Intent(this, SearchResultsActivity.class)
                .putExtra(SearchResultsActivity.QUERY_TERMS, queryTerms)
                .putExtra(SearchResultsActivity.BEGIN_DATE, beginDate)
                .putExtra(SearchResultsActivity.END_DATE, endDate)
                .putExtra(SearchResultsActivity.SECTIONS, sections));
    }
}