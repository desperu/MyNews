package org.desperu.mynews.Controllers.Fragments;

import org.desperu.mynews.R;

public class MainFragment extends BaseFragment {

    // --------------
    // BASE METHODS
    // --------------

    @Override
    protected BaseFragment getNewInstance() { return MainFragment.newInstance(); }

    @Override
    protected int getFragmentLayout() { return R.layout.fragment_main; }

    @Override
    protected void configureDesign() { }

    @Override
    protected void updateDesign() { }

    public static MainFragment newInstance() {
        return new MainFragment();
    }
}
