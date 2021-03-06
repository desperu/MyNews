package org.desperu.mynews.views;

import android.content.Context;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.desperu.mynews.controllers.fragments.ArticleListFragment;
import org.desperu.mynews.MyNewsTools;
import org.desperu.mynews.R;

public class MyNewsAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[] {R.string.tab_title_1, R.string.tab_title_2, R.string.tab_title_3};
    private final Context mContext;

    public MyNewsAdapter(Context context, FragmentManager fm, int behavior) {
        super(fm, behavior);
        mContext = context;
    }

    @Override
    public int getCount() {
        return MyNewsTools.Constant.numberOfPage;
    }

    @Override
    public Fragment getItem(int position) { return ArticleListFragment.newInstance(position); }

    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }
}