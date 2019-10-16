package org.desperu.mynews.Controllers.Fragments;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import org.desperu.mynews.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;

public class SearchArticlesFragment extends BaseFragment {

    // Edit text to enter query search terms
    @BindView(R.id.fragment_search_articles_edit_text) EditText editText;
    // Spinners for begin date and end date
    @BindView(R.id.fragment_search_articles_spinner_begin) Spinner spinnerBegin;
    @BindView(R.id.fragment_search_articles_spinner_end) Spinner spinnerEnd;
    // Check boxes to select section.
    @BindView(R.id.fragment_search_articles_checkbox_arts) CheckBox checkBoxArts;
    @BindView(R.id.fragment_search_articles_checkbox_business) CheckBox checkBoxBusiness;
    @BindView(R.id.fragment_search_articles_checkbox_entrepreneurs) CheckBox checkBoxEntrepreneurs;
    @BindView(R.id.fragment_search_articles_checkbox_politics) CheckBox checkBoxPolitics;
    @BindView(R.id.fragment_search_articles_checkbox_sports) CheckBox checkBoxSports;
    @BindView(R.id.fragment_search_articles_checkbox_travel) CheckBox checkBoxTravel;
    // Button, switch, and separator.
    @BindView(R.id.fragment_search_articles_button_search) Button buttonSearch;
    @BindView(R.id.fragment_search_articles_switch_notifications) Switch switchNotifications;
    @BindView(R.id.fragment_search_articles_bottom_divider) View bottomDivider;

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
        this.hideUnusedItems();
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

    /**
     * Hide unused items.
     */
    private void hideUnusedItems() {
        // TODO switch between search and notifications
        bottomDivider.setVisibility(View.GONE);
        switchNotifications.setVisibility(View.GONE);
    }

    private void configureSpinners() {
        dateListArray.add(0, "");
        Date currentDate = new Date();
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i <= 50; i++) {
            cal.setTime(currentDate);
            cal.add(Calendar.DATE, -i);
            dateListArray.add(i + 1, String.valueOf(cal));
        }
        arrayAdapter.notifyDataSetChanged();
        spinnerBegin.setAdapter(arrayAdapter);
        spinnerBegin.setSelection(0);
        spinnerEnd.setAdapter(arrayAdapter);
        spinnerEnd.setSelection(0);
    }
}