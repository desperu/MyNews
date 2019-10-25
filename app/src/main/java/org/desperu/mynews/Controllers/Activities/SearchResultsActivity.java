package org.desperu.mynews.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;

import org.desperu.mynews.Controllers.Fragments.ArticleListFragment;
import org.desperu.mynews.MyNewsTools;
import org.desperu.mynews.R;

public class SearchResultsActivity extends BaseActivity implements ArticleListFragment.OnClickedArticleListener {

    private ArticleListFragment articleListFragment;

    // FOR DATA
    public static final String QUERY_TERMS = "Query terms";
    public static final String BEGIN_DATE = "Begin date";
    public static final String END_DATE = "End date";
    public static final String SECTIONS = "Sections";
    // For bundle
    public static final String KEY_FRAGMENT = "fragmentKey";
    public static final String KEY_QUERY_TERMS = "queryTerms";
    public static final String KEY_BEGIN_DATE = "beginDate";
    public static final String KEY_END_DATE = "endDate";
    public static final String KEY_SECTIONS = "sections";

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

    @Override
    protected void updateDesign() { }

    // -----------------
    // CONFIGURATION
    // -----------------

    /**
     * Configure and show article list fragment.
     */
    private void configureAndShowArticleListFragment() {
        articleListFragment = (ArticleListFragment) getSupportFragmentManager().findFragmentById(R.id.activity_search_and_notifications_frame_layout);

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
        bundle.putInt(KEY_FRAGMENT, MyNewsTools.FragmentsKeys.SEARCH_RESULTS_FRAGMENT);
        bundle.putString(KEY_QUERY_TERMS, getIntent().getStringExtra(QUERY_TERMS));
        bundle.putString(KEY_BEGIN_DATE, getIntent().getStringExtra(BEGIN_DATE));
        bundle.putString(KEY_END_DATE, getIntent().getStringExtra(END_DATE));
        bundle.putString(KEY_SECTIONS, getIntent().getStringExtra(SECTIONS));
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
