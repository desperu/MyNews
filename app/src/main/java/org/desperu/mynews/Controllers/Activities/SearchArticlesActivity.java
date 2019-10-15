package org.desperu.mynews.Controllers.Activities;

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
        this.configureAndShowSearchFragment();
        this.configureToolbar();
        this.configureUpButton();
    }

    @Override
    protected void updateDesign() { }

    /**
     * Configure and show article fragment.
     */
    private void configureAndShowSearchFragment() {
        searchArticlesFragment = (SearchArticlesFragment) getSupportFragmentManager().findFragmentById(R.id.activity_search_frame_layout);

        if (searchArticlesFragment == null) {
            searchArticlesFragment = new SearchArticlesFragment(dateListArray, arrayAdapter);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_search_frame_layout, searchArticlesFragment)
                    .commit();
        }
    }

    private void configureArrayAdapter() {
        dateListArray = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(SearchArticlesActivity.this,
                R.layout.layout_spinner, dateListArray);
    }
}