package org.desperu.mynews.Controllers.Activities;

import org.desperu.mynews.Controllers.Fragments.ArticleWebFragment;
import org.desperu.mynews.R;

public class ArticleActivity extends BaseActivity {

    //TODO test butterkniff for framelayout

    public static final String ARTICLE_URL = "nytimes.com";
    private ArticleWebFragment articleWebFragment;

    // --------------
    // BASE METHODS
    // --------------

    @Override
    protected int getActivityLayout() { return R.layout.activity_article; }

    @Override
    protected void configureDesign() {
        this.configureAndShowArticleWebFragment();
        this.getArticleUrlIntentAndGiveToArticleFragment();
        this.configureToolbar();
        this.configureUpButton();
    }

    @Override
    protected void updateDesign() { }

    /**
     * Configure and show article fragment.
     */
    private void configureAndShowArticleWebFragment() {
        articleWebFragment = (ArticleWebFragment) getSupportFragmentManager().findFragmentById(R.id.activity_article_frame_layout);

        if (articleWebFragment == null) {
            articleWebFragment = new ArticleWebFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_article_frame_layout, articleWebFragment)
                    .commit();
        }
    }

    /**
     * Get article url and give to article fragment.
     */
    private void getArticleUrlIntentAndGiveToArticleFragment() {
        articleWebFragment.setArticleUrl(getIntent().getStringExtra(ARTICLE_URL));
    }

    //TODO add share with ActionProvider
}
