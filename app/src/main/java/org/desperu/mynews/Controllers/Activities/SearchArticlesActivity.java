package org.desperu.mynews.Controllers.Activities;

import android.view.View;
import android.widget.ArrayAdapter;

import org.desperu.mynews.Controllers.Fragments.SearchArticlesFragment;
import org.desperu.mynews.R;

import java.util.ArrayList;

public class SearchArticlesActivity extends BaseActivity {

    private SearchArticlesFragment searchArticlesFragment;
    private ArrayList<String> dateListArray;
    private ArrayAdapter<String> arrayAdapter;

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
            searchArticlesFragment = new SearchArticlesFragment(dateListArray, arrayAdapter);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_search_frame_layout, searchArticlesFragment)
                    .commit();
        }
    }

    /**
     * Configure Array adapter for dates spinners.
     */
    private void configureArrayAdapter() {
        dateListArray = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(SearchArticlesActivity.this,
                R.layout.layout_spinner, dateListArray);
    }

    /**
     * Search button on click listener.
     * @param view The clicked view.
     */
    public void searchOnClickListener(View view) {
        searchArticlesFragment.searchOnClickListener();
    }
}