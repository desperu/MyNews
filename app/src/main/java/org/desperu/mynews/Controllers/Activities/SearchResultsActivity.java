package org.desperu.mynews.Controllers.Activities;

import android.content.Intent;

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
    private String queryTerms;
    private String beginDate;
    private String endDate;
    private String sections;

    // --------------
    // BASE METHODS
    // --------------

    @Override
    protected int getActivityLayout() { return R.layout.activity_search; }

    @Override
    protected void configureDesign() {
        this.getIntentDataAndSetToFields();
        this.configureAndShowArticleListFragment();
        this.configureToolbar();
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
        articleListFragment = (ArticleListFragment) getSupportFragmentManager().findFragmentById(R.id.activity_search_frame_layout);

        if (articleListFragment == null) {
            articleListFragment = new ArticleListFragment(MyNewsTools.FragmentsKeys.SEARCH_RESULTS_FRAGMENT,
                    queryTerms, beginDate, endDate, sections);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_search_frame_layout, articleListFragment)
                    .commit();
        }
    }

    /**
     * Get intent data and set to fields.
     */
    private void getIntentDataAndSetToFields() {
        this.queryTerms = getIntent().getStringExtra(QUERY_TERMS);
        this.beginDate = getIntent().getStringExtra(BEGIN_DATE);
        this.endDate = getIntent().getStringExtra(END_DATE);
        this.sections = getIntent().getStringExtra(SECTIONS);
    }

    // -----------------
    // ACTION
    // -----------------

    @Override
    public void onClickedArticle(String clickedArticle) {
        this.showArticleActivity(clickedArticle);
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
