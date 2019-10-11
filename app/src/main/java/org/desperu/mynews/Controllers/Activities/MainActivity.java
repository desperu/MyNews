package org.desperu.mynews.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.desperu.mynews.Controllers.Fragments.SciencesFragment;
import org.desperu.mynews.Controllers.Fragments.MostPopularFragment;
import org.desperu.mynews.Controllers.Fragments.TopStoriesFragment;
import org.desperu.mynews.MyNewsTools;
import org.desperu.mynews.R;
import org.desperu.mynews.Views.MyNewsAdapter;

import butterknife.BindView;
import icepick.State;

public class MainActivity extends BaseActivity implements TopStoriesFragment.OnClickedArticleListener,
        MostPopularFragment.OnClickedArticleListener, SciencesFragment.OnClickedArticleListener {

    @State int selectedTab;

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
        this.onRestoreTab();
    }

    @Override
    protected void updateDesign() {

    }

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

    //TODO do nothing, use sharePref!!! because use the same icepick with articleActivity??
    /**
     * Restore selected tab before switch activity.
     */
    private void onRestoreTab() {
        if (selectedTab >= 0 && selectedTab <= MyNewsTools.Constant.numberOfPage)
            viewPager.setCurrentItem(selectedTab);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        selectedTab = viewPager.getCurrentItem();
        super.onSaveInstanceState(outState);
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
                Toast.makeText(getBaseContext(), "You clicked on params !", Toast.LENGTH_SHORT).show();
//                this.showParamsActivity();
                return true;
            case R.id.menu_activity_main_search:
                Toast.makeText(getBaseContext(), "You clicked on search !", Toast.LENGTH_SHORT).show();
//                this.showSearchActivity();
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
     * Start article activity.
     * @param articleUrl The url's article.
     */
    private void showArticleActivity(String articleUrl) {
        startActivity(new Intent(this, ShowArticleActivity.class).putExtra(ShowArticleActivity.ARTICLE_URL, articleUrl));
    }
}