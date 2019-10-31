package org.desperu.mynews.controllers.activities;

import android.content.Intent;
import android.os.Bundle;

import org.desperu.mynews.controllers.fragments.ArticleListFragment;
import org.desperu.mynews.R;

import static org.desperu.mynews.MyNewsTools.FragmentsKeys.*;

public class SearchResultsActivity extends BaseActivity implements ArticleListFragment.OnClickedArticleListener {

    // FOR DATA
    public static final String QUERY_TERMS = "queryTerms";
    public static final String BEGIN_DATE = "beginDate";
    public static final String END_DATE = "endDate";
    public static final String SECTIONS = "sections";

    // --------------
    // BASE METHODS
    // --------------

    @Override
    protected int getActivityLayout() { return R.layout.activity_search_and_notifications; }

    @Override
    protected void configureDesign() {
        this.getIntentDataAndPutInBundle();
        this.configureAndShowArticleListFragment();
        this.configureToolBar();
        this.configureUpButton();
    }

    // -----------------
    // CONFIGURATION
    // -----------------

    /**
     * Configure and show article list fragment.
     */
    private void configureAndShowArticleListFragment() {
        ArticleListFragment articleListFragment = (ArticleListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_search_and_notifications_frame_layout);

        if (articleListFragment == null) {
            articleListFragment = new ArticleListFragment();
            articleListFragment.setArguments(getIntentDataAndPutInBundle());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_search_and_notifications_frame_layout, articleListFragment)
                    .commit();
        }
    }

    /**
     * Get intent data and put in bundle.
     */
    private Bundle getIntentDataAndPutInBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_FRAGMENT, SEARCH_RESULTS_FRAGMENT);
        bundle.putString(QUERY_TERMS, getIntent().getStringExtra(QUERY_TERMS));
        bundle.putString(BEGIN_DATE, getIntent().getStringExtra(BEGIN_DATE));
        bundle.putString(END_DATE, getIntent().getStringExtra(END_DATE));
        bundle.putString(SECTIONS, getIntent().getStringExtra(SECTIONS));
        return bundle;
    }

    // -----------------
    // ACTION
    // -----------------

    @Override
    public void onClickedArticle(String articleUrl) {
        this.showArticleActivity(articleUrl);
    }

    // -----------------
    // ACTIVITY
    // -----------------

    /**
     * Start show article activity.
     * @param articleUrl The url's article.
     */
    private void showArticleActivity(String articleUrl) {
        startActivity(new Intent(this, ShowArticleActivity.class).putExtra(ShowArticleActivity.ARTICLE_URL, articleUrl));
    }
}
