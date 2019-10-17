package org.desperu.mynews.Controllers.Activities;

import android.widget.ArrayAdapter;

import org.desperu.mynews.Controllers.Fragments.SearchArticlesFragment;
import org.desperu.mynews.MyNewsTools;
import org.desperu.mynews.R;

import java.util.ArrayList;

public class SearchArticlesActivity extends BaseActivity {

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
    protected int getActivityLayout() { return R.layout.activity_search_articles; }

    @Override
    protected void configureDesign() {
        this.configureArrayAdapter();
        this.configureAndShowSearchArticlesFragment();
        this.configureToolbar();
        this.configureUpButton();
    }

    @Override
    protected void updateDesign() { }

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
}