package org.desperu.mynews.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;

import org.desperu.mynews.Controllers.Fragments.SearchArticlesFragment;
import org.desperu.mynews.R;

import static org.desperu.mynews.MyNewsTools.FragmentsKeys.*;

public class SearchArticlesActivity extends BaseActivity implements SearchArticlesFragment.OnClickedSearchButtonListener {

    public static final String KEY_FRAGMENT = "fragmentKey";

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
        SearchArticlesFragment searchArticlesFragment = (SearchArticlesFragment) getSupportFragmentManager().findFragmentById(R.id.activity_search_and_notifications_frame_layout);

        if (searchArticlesFragment == null) {
            searchArticlesFragment = new SearchArticlesFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(KEY_FRAGMENT, SEARCH_FRAGMENT);
            searchArticlesFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_search_and_notifications_frame_layout, searchArticlesFragment)
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