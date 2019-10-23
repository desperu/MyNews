package org.desperu.mynews.Controllers.Activities;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.desperu.mynews.Controllers.Fragments.ArticleListFragment;
import org.desperu.mynews.R;
import org.desperu.mynews.Views.MyNewsAdapter;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements ArticleListFragment.OnClickedArticleListener {

    @BindView(R.id.activity_main_view_pager) ViewPager viewPager;

    // --------------
    // BASE METHODS
    // --------------

    @Override
    protected int getActivityLayout() { return R.layout.activity_main; }

    @Override
    protected void configureDesign() {
        this.configureToolbar();
        this.configureViewPagerAndTabs();
    }

    @Override
    protected void updateDesign() { }

    // -----------------
    // CONFIGURATION
    // -----------------

    /**
     * Configure Tab layout and View pager.
     */
    private void configureViewPagerAndTabs() {
        viewPager.setAdapter(new MyNewsAdapter(getBaseContext(), getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));

        TabLayout tabLayout = findViewById(R.id.activity_main_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    // -----------------
    // METHODS OVERRIDE
    // -----------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_activity_main_params:
                this.showNotificationsActivity();
                return true;
            case R.id.menu_activity_main_search:
                this.showSearchArticlesActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    /**
     * Start search articles activity.
     */
    private void showSearchArticlesActivity() {
        startActivity(new Intent(this, SearchArticlesActivity.class));
    }

    /**
     * Start notifications activity.
     */
    private void showNotificationsActivity() {
        startActivity(new Intent(this, NotificationsActivity.class));
    }
}