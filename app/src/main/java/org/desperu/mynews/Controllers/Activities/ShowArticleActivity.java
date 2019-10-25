package org.desperu.mynews.Controllers.Activities;

import android.os.Bundle;

import org.desperu.mynews.Controllers.Fragments.ShowArticleFragment;
import org.desperu.mynews.R;

public class ShowArticleActivity extends BaseActivity {

    // For intent extra.
    public static final String ARTICLE_URL = "nytimes.com";
    // For bundle.
    public static final String KEY_ARTICLE_URL = "articleUrl";

    // --------------
    // BASE METHODS
    // --------------

    @Override
    protected int getActivityLayout() { return R.layout.activity_show_article; }

    @Override
    protected void configureDesign() {
        this.configureAndShowArticleWebFragment();
        this.configureToolBar();
        this.configureUpButton();
    }

    @Override
    protected void updateDesign() { }

    // --------------
    // CONFIGURATION
    // --------------

    /**
     * Configure and show article fragment.
     */
    private void configureAndShowArticleWebFragment() {
        ShowArticleFragment showArticleFragment = (ShowArticleFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_show_article_frame_layout);

        if (showArticleFragment == null) {
            Bundle bundle = new Bundle();
            bundle.putString(KEY_ARTICLE_URL, getArticleUrlIntent());
            showArticleFragment = new ShowArticleFragment();
            showArticleFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_show_article_frame_layout, showArticleFragment)
                    .commit();
        }
    }

    /**
     * Get article url from extra intent.
     */
    private String getArticleUrlIntent() {
        return getIntent().getStringExtra(ARTICLE_URL);
    }

    //TODO add share with ActionProvider get new article url from fragment
}
