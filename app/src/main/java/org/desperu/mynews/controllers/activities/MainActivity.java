package org.desperu.mynews.controllers.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import org.desperu.mynews.controllers.fragments.ArticleListFragment;
import org.desperu.mynews.R;
import org.desperu.mynews.utils.MyNewsUtils;
import org.desperu.mynews.views.MyNewsAdapter;

import java.util.Objects;

import butterknife.BindView;

import static org.desperu.mynews.MyNewsTools.Constant.*;
import static org.desperu.mynews.MyNewsTools.FragmentsKeys.*;

public class MainActivity extends BaseActivity implements ArticleListFragment.OnClickedArticleListener,
        NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.activity_main_view_pager) ViewPager viewPager;
    // FOR DESIGN
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    // --------------
    // BASE METHODS
    // --------------

    @Override
    protected int getActivityLayout() { return R.layout.activity_main; }

    @Override
    protected void configureDesign() {
        this.configureToolBar();
        this.configureDrawerLayout();
        this.configureNavigationView();
        this.configureViewPagerAndTabs();
        MyNewsUtils.manageArticleHistory(getBaseContext());
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

    /**
     * Configure ToolBar.
     */
    protected void configureToolBar() {
        this.toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * Configure Drawer layout.
     */
    private void configureDrawerLayout() {
        this.drawerLayout = findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    /**
     * Configure Navigation Drawer.
     */
    private void configureNavigationView() {
        this.navigationView = findViewById(R.id.activity_main_nav_view);
        // Support status bar for KitKat.
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT)
            navigationView.setPadding(0, 0, 0, 0);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(viewPager.getCurrentItem()).setChecked(true);
    }

    // -----------------
    // METHODS OVERRIDE
    // -----------------

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch(id) {
            case R.id.activity_main_drawer_top_stories:
                this.viewPager.setCurrentItem(TOP_STORIES_FRAGMENT);
                break;
            case R.id.activity_main_drawer_most_popular:
                this.viewPager.setCurrentItem(MOST_POPULAR_FRAGMENT);
                break;
            case R.id.activity_main_drawer_sciences:
                this.viewPager.setCurrentItem(SCIENCES_FRAGMENT);
                break;
            case R.id.activity_main_drawer_search:
                this.showSearchArticlesActivity();
                break;
            case R.id.activity_main_drawer_notifications:
                this.showNotificationsActivity();
                break;
            case R.id.activity_main_drawer_about:
                this.showAboutDialog();
                break;
            case R.id.activity_main_drawer_help:
                this.showHelpDocumentation();
                break;
            default:
                break;
        }
        this.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START))
            this.drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_activity_main_search:
                this.showSearchArticlesActivity();
                return true;
            case R.id.menu_activity_main_notifications:
                this.showNotificationsActivity();
                return true;
            case R.id.menu_activity_about:
                this.showAboutDialog();
                return true;
            case R.id.menu_activity_help:
                this.showHelpDocumentation();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        // To check nav drawer item when view pager change page.
        navigationView.getMenu().getItem(viewPager.getCurrentItem()).setChecked(true);
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

    /**
     * Show about dialog.
     */
    private void showAboutDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.activity_main_dialog_about_title) + " " + getString(R.string.app_name))
                .setMessage(R.string.activity_main_dialog_about_message)
                .setPositiveButton(R.string.activity_main_dialog_about_positive_button, null)
                .show();
        ((TextView) Objects.requireNonNull(dialog.findViewById(android.R.id.message)))
                .setMovementMethod(LinkMovementMethod.getInstance());
    }

    /**
     * Show help documentation.
     */
    private void showHelpDocumentation() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
        browserIntent.setDataAndType(Uri.parse(DOCUMENTATION_URL), "text/html");

        startActivity(browserIntent);
    }
}