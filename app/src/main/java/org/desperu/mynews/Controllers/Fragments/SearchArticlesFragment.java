package org.desperu.mynews.Controllers.Fragments;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import org.desperu.mynews.R;

import java.util.ArrayList;

import butterknife.BindView;

public class SearchArticlesFragment extends BaseFragment {

    @BindView(R.id.fragment_search_articles_edit_text) EditText editText;

    @BindView(R.id.fragment_search_articles_spinner_begin) Spinner spinnerBegin;
    @BindView(R.id.fragment_search_articles_spinner_end) Spinner spinnerEnd;

    @BindView(R.id.fragment_search_articles_checkbox_arts) CheckBox checkBoxArts;
    @BindView(R.id.fragment_search_articles_checkbox_business) CheckBox checkBoxBusiness;
    @BindView(R.id.fragment_search_articles_checkbox_entrepreneurs) CheckBox checkBoxEntrepreneurs;
    @BindView(R.id.fragment_search_articles_checkbox_politics) CheckBox checkBoxPolitics;
    @BindView(R.id.fragment_search_articles_checkbox_sports) CheckBox checkBoxSports;
    @BindView(R.id.fragment_search_articles_checkbox_travel) CheckBox checkBoxTravel;
    @BindView(R.id.fragment_search_articles_button_search) Button buttonSearch;
    @BindView(R.id.fragment_search_articles_switch_notifications) Switch switchNotifications;

    private ArrayList<String> dateListArray;
    private ArrayAdapter<String> arrayAdapter;

    // --------------
    // BASE METHODS
    // --------------

    @Override
    protected int getFragmentLayout() { return R.layout.fragment_search_articles; }

    @Override
    protected void configureDesign() {
        this.configureSpinners();
    }

    @Override
    protected void updateDesign() { }

    // --------------
    // CONSTRUCTORS
    // --------------

    public SearchArticlesFragment() {}

    public SearchArticlesFragment(ArrayList<String> dateListArray, ArrayAdapter<String> arrayAdapter) {
        this.dateListArray = dateListArray;
        this.arrayAdapter = arrayAdapter;
    }

    private void configureSpinners() {
        dateListArray.add(0, "20/10/2019");
        dateListArray.add(1, "19/10/2019");
        arrayAdapter.notifyDataSetChanged();
        spinnerBegin.setAdapter(arrayAdapter);
        spinnerBegin.setSelection(0);
        spinnerEnd.setAdapter(arrayAdapter);
        spinnerEnd.setSelection(0);
    }
}