package org.desperu.mynews.Controllers.Activities;

import org.desperu.mynews.Controllers.Fragments.SearchArticlesFragment;
import org.desperu.mynews.R;

public class SearchArticlesActivity extends BaseActivity {

    private SearchArticlesFragment searchArticlesFragment;

    // --------------
    // BASE METHODS
    // --------------

    @Override
    protected int getActivityLayout() { return R.layout.activity_search_articles; }

    @Override
    protected void configureDesign() {
//        this.configureAndShowSearchFragment();
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
            searchArticlesFragment = new SearchArticlesFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_search_frame_layout, searchArticlesFragment)
                    .commit();
        }
    }
}
