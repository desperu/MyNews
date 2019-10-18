package org.desperu.mynews.Controllers.Activities;

import android.content.Intent;
import android.widget.ArrayAdapter;

import org.desperu.mynews.Controllers.Fragments.SearchArticlesFragment;
import org.desperu.mynews.MyNewsTools;
import org.desperu.mynews.R;

import java.util.ArrayList;

public class SearchArticlesActivity extends BaseActivity implements SearchArticlesFragment.OnClickedActionListener {

    private SearchArticlesFragment searchArticlesFragment;

    // For spinners
    private ArrayList<String> beginDateListArray;
    private ArrayAdapter<String> beginDateArrayAdapter;
    private ArrayList<String> endDateListArray;
    private ArrayAdapter<String> endDateArrayAdapter;

    // --------------
    // BASE METHODS
    // --------------

    @Override
    protected int getActivityLayout() { return R.layout.activity_search; }

    @Override
    protected void configureDesign() {
        this.configureArrayAdapter();
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
            searchArticlesFragment = new SearchArticlesFragment(MyNewsTools.FragmentsKeys.SEARCH_FRAGMENT,
                    beginDateListArray, beginDateArrayAdapter, endDateListArray, endDateArrayAdapter);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_search_frame_layout, searchArticlesFragment)
                    .commit();
        }
    }

    /**
     * Configure Array adapter for dates spinners.
     */
    private void configureArrayAdapter() {
        beginDateListArray = new ArrayList<>();
        beginDateArrayAdapter = new ArrayAdapter<>(SearchArticlesActivity.this,
                R.layout.layout_spinner, beginDateListArray);
        endDateListArray = new ArrayList<>();
        endDateArrayAdapter = new ArrayAdapter<>(SearchArticlesActivity.this,
                R.layout.layout_spinner, endDateListArray);
    }

    // -----------------
    // ACTION
    // -----------------

    @Override
    public void OnClickedSearchButton(String queryTerms, String beginDate,
                                      String endDate, String sections) {
        this.startShowSearchResultsActivity(queryTerms, beginDate, endDate, sections);
    }

    @Override
    public void OnClickedNotificationSwitch() {

    }

    // -----------------
    // ACTIVITY
    // -----------------

    /**
     * Start show search results activity.
     * @param queryTerms Terms of the query.
     * @param beginDate Begin date for search.
     * @param endDate End date for search.
     * @param sections Sections into search.
     */
    private void startShowSearchResultsActivity(String queryTerms, String beginDate,
                                                String endDate, String sections) {
        startActivity(new Intent(this, SearchResultsActivity.class)
                .putExtra(SearchResultsActivity.QUERY_TERMS,queryTerms)
                .putExtra(SearchResultsActivity.BEGIN_DATE,beginDate)
                .putExtra(SearchResultsActivity.END_DATE,endDate)
                .putExtra(SearchResultsActivity.SECTIONS,sections));
    }
}