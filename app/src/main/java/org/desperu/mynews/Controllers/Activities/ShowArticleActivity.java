package org.desperu.mynews.Controllers.Activities;

import org.desperu.mynews.Controllers.Fragments.ShowArticleFragment;
import org.desperu.mynews.R;

public class ShowArticleActivity extends BaseActivity {

    public static final String ARTICLE_URL = "nytimes.com";
    private ShowArticleFragment showArticleFragment;

    // --------------
    // BASE METHODS
    // --------------

    @Override
    protected int getActivityLayout() { return R.layout.activity_show_article; }

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
        showArticleFragment = (ShowArticleFragment) getSupportFragmentManager().findFragmentById(R.id.activity_show_article_frame_layout);

        if (showArticleFragment == null) {
            showArticleFragment = new ShowArticleFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_show_article_frame_layout, showArticleFragment)
                    .commit();
        }
    }

    /**
     * Get article url and give to article fragment.
     */
    private void getArticleUrlIntentAndGiveToArticleFragment() {
        showArticleFragment.setArticleUrl(getIntent().getStringExtra(ARTICLE_URL));
    }

    //TODO add share with ActionProvider
}
