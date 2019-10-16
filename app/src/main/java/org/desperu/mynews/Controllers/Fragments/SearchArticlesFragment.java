package org.desperu.mynews.Controllers.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import org.desperu.mynews.R;
import org.desperu.mynews.Utils.MyNewsUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;

public class SearchArticlesFragment extends BaseFragment {

    // Edit text to enter query search terms
    @BindView(R.id.fragment_search_articles_edit_text) EditText searchEditText;
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

    /**
     * Configure spinners with dates.
     */
    private void configureSpinners() {
        dateListArray.add(0, getString(R.string.spinners_no_limit_date));
        for (int i = 0; i <= 365; i++) { // TODO use tools
            Date currentDate = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(currentDate);
            cal.add(Calendar.DATE, -i);
            dateListArray.add(i + 1, MyNewsUtils.dateToString(cal.getTime()));
        }
        arrayAdapter.notifyDataSetChanged();
        spinnerBegin.setAdapter(arrayAdapter);
        spinnerBegin.setSelection(0);
        spinnerEnd.setAdapter(arrayAdapter);
        spinnerEnd.setSelection(0);
        // TODO edit dates
//        spinnerBegin.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                AlertDialog.Builder editDate = new AlertDialog.Builder(getContext(), R.style.AppTheme);
//                editDate.setTitle("toto");
//
//                editDate.show();
//
//                return true;
//            }
//        });
    }

    public void searchOnClickListener() {
        String searchTerm = String.valueOf(searchEditText.getText());
//        String beginDate = spinnerBegin.getOnItemClickListener();
//        String endDate = spinnerEnd.getSelectedItemPosition();
        this.getCheckboxesStatus();
    }

    /**
     * Get check boxes status.
     */
    private void getCheckboxesStatus() {
        ArrayList<String> sections = new ArrayList<>();
        if (checkBoxArts.isActivated()) sections.add(getString(R.string.fragment_search_articles_checkbox_arts));
        if (checkBoxBusiness.isActivated()) sections.add(getString(R.string.fragment_search_articles_checkbox_business));
        if (checkBoxEntrepreneurs.isActivated()) sections.add(getString(R.string.fragment_search_articles_checkbox_entrepreneurs));
        if (checkBoxPolitics.isActivated()) sections.add(getString(R.string.fragment_search_articles_checkbox_politics));
        if (checkBoxSports.isActivated()) sections.add(getString(R.string.fragment_search_articles_checkbox_sports));
        if (checkBoxTravel.isActivated()) sections.add(getString(R.string.fragment_search_articles_checkbox_travel));
        if (sections.isEmpty()) this.errorNoSectionSelectedDialog(); // TODO empty not function
    }

    /**
     * Create and show dialog box when no section selected.
     */
    private void errorNoSectionSelectedDialog() {
        AlertDialog.Builder errorNoSection = new AlertDialog.Builder(getContext());
        errorNoSection.setTitle(R.string.dialog_no_section_title);
        errorNoSection.setMessage(R.string.dialog_no_section_message);
        errorNoSection.setPositiveButton(R.string.dialog_no_section_positive_button, (dialog, which) -> dialog.cancel());
        errorNoSection.show();
    }
}