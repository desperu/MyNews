package org.desperu.mynews.Controllers.Fragments;

import android.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import org.desperu.mynews.MyNewsTools;
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
    @BindView(R.id.fragment_search_articles_bottom_divider) View bottomDivider;
    @BindView(R.id.fragment_search_articles_switch_notifications) Switch switchNotifications;

    // Switch between fragment
    private int fragment;
    // For spinners
    private ArrayList<String> beginDateListArray;
    private ArrayAdapter<String> beginDateArrayAdapter;
    private ArrayList<String> endDateListArray;
    private ArrayAdapter<String> endDateArrayAdapter;

    // Callback
    public interface OnClickedActionListener {
        void OnClickedSearchButton(String queryTerms, String beginDate, String endDate, String sections);
        void OnClickedNotificationSwitch();
    }

    private SearchArticlesFragment.OnClickedActionListener mCallback;

    // --------------
    // BASE METHODS
    // --------------

    @Override
    protected int getFragmentLayout() { return R.layout.fragment_search_articles; }

    @Override
    protected void configureDesign() {
        this.configureAskedFragment(fragment);
        this.createCallbackToParentActivity();
    }

    @Override
    protected void updateDesign() { }

    // --------------
    // CONSTRUCTORS
    // --------------

    public SearchArticlesFragment() {}

    public SearchArticlesFragment(int fragment) { this.fragment = fragment; }

    public SearchArticlesFragment(int fragment, ArrayList<String> beginDateListArray,
                                  ArrayAdapter<String> beginDateArrayAdapter,
                                  ArrayList<String> endDateListArray,
                                  ArrayAdapter<String> endDateArrayAdapter) {
        this.fragment = fragment;
        this.beginDateListArray = beginDateListArray;
        this.beginDateArrayAdapter = beginDateArrayAdapter;
        this.endDateListArray = endDateListArray;
        this.endDateArrayAdapter = endDateArrayAdapter;
    }

    // --------------
    // CONFIGURATION
    // --------------

    /**
     * Configure fragment search or notifications and hide unused items.
     * @param fragment Fragment id.
     */
    private void configureAskedFragment(int fragment) {
        switch (fragment) {
            case MyNewsTools.FragmentsKeys.SEARCH_FRAGMENT :
                this.configureDateSpinners(spinnerBegin, beginDateListArray, beginDateArrayAdapter);
                this.configureDateSpinners(spinnerEnd, endDateListArray, endDateArrayAdapter);
                this.configureSearchButtonOnClickListener();
                bottomDivider.setVisibility(View.GONE);
                switchNotifications.setVisibility(View.GONE);
                break;
            case MyNewsTools.FragmentsKeys.NOTIFICATION_FRAGMENT :

                spinnerBegin.setVisibility(View.GONE);
                spinnerEnd.setVisibility(View.GONE);
                buttonSearch.setVisibility(View.GONE);
                break;
        }
    }

    // --------------
    // FRAGMENT SUPPORT
    // --------------

    /**
     * Configure callback to parent activity for manage click item.
     */
    private void createCallbackToParentActivity(){
        try {
            mCallback = (SearchArticlesFragment.OnClickedActionListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(e.toString()+ " must implement OnClickedActionListener");
        }
    }

    // --------------
    // SPINNERS
    // --------------

    /**
     * Configure date spinners.
     */
    private void configureDateSpinners(Spinner spinner, ArrayList<String> dateArrayList,
                                       ArrayAdapter<String> arrayAdapter) {
        this.configureDateArrayList(dateArrayList);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(0);
        spinner.setOnLongClickListener(v -> {
            editDateDialog(v, dateArrayList, arrayAdapter);
            return true;
        });
    }

    /**
     * Configure array list date.
     * @param dateArrayList Array list to configure.
     */
    private void configureDateArrayList(ArrayList<String> dateArrayList) {
        dateArrayList.add(0, "");
        for (int i = 0; i <= MyNewsTools.Constant.DATE_LIMIT; i++) {
            Date currentDate = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(currentDate);
            cal.add(Calendar.DATE, -i);
            dateArrayList.add(i + 1, MyNewsUtils.dateToString(cal.getTime()));
        }
    }

    /**
     * Edit spinner date dialog.
     * @param v Spinner view from this method is called.
     * @param dateArrayList Date array list given.
     * @param arrayAdapter Array adapter given.
     */
    private void editDateDialog(View v, ArrayList<String> dateArrayList, ArrayAdapter<String> arrayAdapter) {
        AlertDialog.Builder editDate = new AlertDialog.Builder(getContext());
        if (v.getId() == spinnerBegin.getId()) editDate.setTitle(R.string.dialog_spinners_edit_begin_date_title);
        if (v.getId() == spinnerEnd.getId()) editDate.setTitle(R.string.dialog_spinners_edit_end_date_title);
        editDate.setMessage(R.string.dialog_spinners_edit_date_message);
        final EditText editText = new EditText(getContext());
        editDate.setView(editText);
        editDate.setPositiveButton(R.string.dialog_spinners_edit_date_positive_button, (dialog, which) -> {
            dateArrayList.set(0, String.valueOf(editText.getText()));
            arrayAdapter.notifyDataSetChanged();
            dialog.cancel();
        });
        editDate.setNegativeButton(R.string.dialog_spinners_edit_date_negative_button, (dialog, which) -> dialog.cancel());
        editDate.show();
    }

    // -----------------
    // ACTION
    // -----------------

    /**
     * Configure search button on click listener.
     */
    private void configureSearchButtonOnClickListener() {
        buttonSearch.setOnClickListener(v -> {
            if (getCheckboxesSections().isEmpty()) this.errorNoSectionSelectedDialog();
            else {
                mCallback.OnClickedSearchButton(getSearchQueryTerms(),
                        getSpinnersDates(spinnerBegin, beginDateListArray),
                        getSpinnersDates(spinnerEnd, endDateListArray),
                        getCheckboxesSections());
            }
        });
    }

    // --------------
    // GET LAYOUT INFORMATION
    // --------------

    /**
     * Get search query terms.
     */
    private String getSearchQueryTerms() {
        return String.valueOf(searchEditText.getText());
    }

    /**
     * Get check boxes status.
     */
    private String getCheckboxesSections() {
        ArrayList<String> sections = new ArrayList<>();
        if (checkBoxArts.isChecked()) sections.add(getString(R.string.fragment_search_articles_checkbox_arts));
        if (checkBoxBusiness.isChecked()) sections.add(getString(R.string.fragment_search_articles_checkbox_business));
        if (checkBoxEntrepreneurs.isChecked()) sections.add(getString(R.string.fragment_search_articles_checkbox_entrepreneurs));
        if (checkBoxPolitics.isChecked()) sections.add(getString(R.string.fragment_search_articles_checkbox_politics));
        if (checkBoxSports.isChecked()) sections.add(getString(R.string.fragment_search_articles_checkbox_sports));
        if (checkBoxTravel.isChecked()) sections.add(getString(R.string.fragment_search_articles_checkbox_travel));

        return MyNewsUtils.concatenateStringSectionsFromArrayList(sections);
    }

    /**
     * Get spinners dates selected.
     */
    private String getSpinnersDates(Spinner spinner, ArrayList<String> dateArrayList) {
        String selectedDate = dateArrayList.get(spinner.getSelectedItemPosition());
        if (selectedDate.length() == 0) {
            if (spinner.getId() == spinnerBegin.getId())
                selectedDate = MyNewsTools.Constant.BEGIN_DATE_DEFAULT;
            if (spinner.getId() == spinnerEnd.getId())
                selectedDate = MyNewsUtils.dateToString(new Date());
        }
        return MyNewsUtils.changeDateFormat(selectedDate);
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