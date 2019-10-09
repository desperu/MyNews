package org.desperu.mynews.Controllers.Activities;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.desperu.mynews.Controllers.Fragments.SciencesFragment;
import org.desperu.mynews.Controllers.Fragments.MostPopularFragment;
import org.desperu.mynews.Controllers.Fragments.TopStoriesFragment;
import org.desperu.mynews.R;
import org.desperu.mynews.Views.MyNewsAdapter;

public class MainActivity extends BaseActivity implements TopStoriesFragment.OnClickedArticleListener,
        MostPopularFragment.OnClickedArticleListener, SciencesFragment.OnClickedArticleListener {

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

    /**
     * Configure Tab layout and View pager
     */
    private void configureViewPagerAndTabs() {
        ViewPager viewPager = findViewById(R.id.activity_main_view_pager);
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
                Toast.makeText(getBaseContext(), "You clicked on params !", Toast.LENGTH_SHORT).show();
//                this.showParamsActivity();
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
        Toast.makeText(getBaseContext(), "You clicked on an Article !", Toast.LENGTH_SHORT).show();
    }
}