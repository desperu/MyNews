package org.desperu.mynews.controllers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.MenuItemCompat;

import org.desperu.mynews.controllers.fragments.ShowArticleFragment;
import org.desperu.mynews.R;

public class ShowArticleActivity extends BaseActivity {

    // For data
    public static final String ARTICLE_URL = "articleUrl";

    private ShowArticleFragment showArticleFragment;
    private ShareActionProvider miShareAction;

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
        showArticleFragment = (ShowArticleFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_show_article_frame_layout);

        if (showArticleFragment == null) {
            showArticleFragment = new ShowArticleFragment();
            Bundle bundle = new Bundle();
            bundle.putString(ARTICLE_URL, getArticleUrlIntent());
            showArticleFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_show_article_frame_layout, showArticleFragment)
                    .commit();
        }
    }

    /**
     * Get article url from extra intent.
     */
    private String getArticleUrlIntent() { return getIntent().getStringExtra(ARTICLE_URL); }

    /**
     * Attaches the share intent to the share action provider.
     */
    private void attachShareIntentAction() {
        // Create intent with article url, and set in ShareActionProvider.
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, showArticleFragment.getArticleUrl());
        shareIntent.setType("text/plain");
        if (miShareAction != null)
            miShareAction.setShareIntent(shareIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu resource file.
        getMenuInflater().inflate(R.menu.share, menu);
        // Locate MenuItem with ShareActionProvider.
        MenuItem item = menu.findItem(R.id.menu_item_share);
        // Fetch reference to the share action provider.
        miShareAction = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        attachShareIntentAction(); // call here in case this method fires second.
        // Return true to display menu.
        return true;
    }

    @Override
    public void onUserInteraction() {
        attachShareIntentAction(); // call here to refresh intent data.
        super.onUserInteraction();
    }
}
