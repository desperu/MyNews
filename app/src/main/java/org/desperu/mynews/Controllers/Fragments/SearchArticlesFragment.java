package org.desperu.mynews.Controllers.Fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import org.desperu.mynews.Controllers.Activities.SearchArticlesActivity;
import org.desperu.mynews.MyNewsTools;
import org.desperu.mynews.R;
import org.desperu.mynews.Utils.MyNewsPrefs;
import org.desperu.mynews.Utils.MyNewsUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import icepick.State;

public class SearchArticlesFragment extends BaseFragment {

    // Edit text to enter query search terms
    @BindView(R.id.fragment_search_articles_edit_text) EditText searchEditText;
    // Date picker, text view and dividers for begin date and end date.
    @BindView(R.id.fragment_search_articles_text_view_begin_date) TextView textViewBeginDate;
    @BindView(R.id.fragment_search_articles_text_view_date_picker_begin) TextView showBeginDate;
    @BindView(R.id.fragment_search_articles_begin_date_divider) View beginDateDivider;
    @BindView(R.id.fragment_search_articles_text_view_end_date) TextView textViewEndDate;
    @BindView(R.id.fragment_search_articles_text_view_date_picker_end) TextView showEndDate;
    @BindView(R.id.fragment_search_articles_end_date_divider) View endDateDivider;
    // Check boxes to select section.
    @BindView(R.id.fragment_search_articles_checkbox_arts) CheckBox checkBoxArts;
    @BindView(R.id.fragment_search_articles_checkbox_business) CheckBox checkBoxBusiness;
    @BindView(R.id.fragment_search_articles_checkbox_entrepreneurs) CheckBox checkBoxEntrepreneurs;
    @BindView(R.id.fragment_search_articles_checkbox_politics) CheckBox checkBoxPolitics;
    @BindView(R.id.fragment_search_articles_checkbox_sports) CheckBox checkBoxSports;
    @BindView(R.id.fragment_search_articles_checkbox_travel) CheckBox checkBoxTravel;
    // Button, switch, and divider.
    @BindView(R.id.fragment_search_articles_button_search) Button buttonSearch;
    @BindView(R.id.fragment_search_articles_bottom_divider) View bottomDivider;
    @BindView(R.id.fragment_search_articles_switch_notifications) Switch switchNotifications;

    // Switch between fragment
    @State int fragmentKey;
    // Date picker
    private DatePickerDialog.OnDateSetListener datePickerBegin;
    private DatePickerDialog.OnDateSetListener datePickerEnd;
    private String beginDate = "";
    private String endDate = "";

    // Callback Search button
    public interface OnClickedSearchButtonListener {
        void OnClickSearchListener(String queryTerms, String beginDate, String endDate, String sections);
    }

    private SearchArticlesFragment.OnClickedSearchButtonListener searchCallback;

    // Callback Notification Switch
    public interface OnClickedNotificationSwitchListener {
        void OnClickNotificationListener(boolean isChecked);
    }

    private SearchArticlesFragment.OnClickedNotificationSwitchListener notificationCallback;

    // --------------
    // BASE METHODS
    // --------------

    @Override
    protected int getFragmentLayout() { return R.layout.fragment_search_articles; }

    @Override
    protected void configureDesign() {
        this.setFragmentKey();
        this.restoreNotificationData();
        this.configureAskedFragment(fragmentKey);
    }

    @Override
    protected void updateDesign() { }

    // --------------
    // CONSTRUCTOR
    // --------------

    public SearchArticlesFragment() {}

    // --------------
    // CONFIGURATION
    // --------------

    /**
     * Set fragmentKey with bundle argument if not null.
     */
    private void setFragmentKey() {
        assert getArguments() != null;
        this.fragmentKey = getArguments().getInt(SearchArticlesActivity.KEY_FRAGMENT, 0);
    }

    /**
     * Configure fragment search or notifications and hide unused items.
     * @param fragmentKey Fragment id.
     */
    private void configureAskedFragment(int fragmentKey) {
        switch (fragmentKey) {
            case MyNewsTools.FragmentsKeys.SEARCH_FRAGMENT :
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    this.configureDatePicker();
                } else this.hideDateItems(); // TODO EditText for API < 24 ??
                this.configureSearchButtonOnClickListener();
                this.createSearchCallbackToParentActivity();
                bottomDivider.setVisibility(View.GONE);
                switchNotifications.setVisibility(View.GONE);
                break;
            case MyNewsTools.FragmentsKeys.NOTIFICATION_FRAGMENT :
                this.configureNotificationSwitchListener();
                this.createNotificationCallbackToParentActivity();
                this.hideDateItems();
                buttonSearch.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * Hide date layout items.
     */
    private void hideDateItems() {
        textViewBeginDate.setVisibility(View.GONE);
        showBeginDate.setVisibility(View.GONE);
        beginDateDivider.setVisibility(View.GONE);
        textViewEndDate.setVisibility(View.GONE);
        showEndDate.setVisibility(View.GONE);
        endDateDivider.setVisibility(View.GONE);
    }

    // --------------
    // NOTIFICATION DATA MANAGEMENT
    // --------------

    /**
     * Save notification layout data.
     */
    private void saveNotificationsFragmentData() {
        if (fragmentKey == MyNewsTools.FragmentsKeys.NOTIFICATION_FRAGMENT) {
            MyNewsPrefs.savePref(getContext(), MyNewsTools.Keys.NOTIFICATION_SWITCH_STATE, switchNotifications.isChecked());
            MyNewsPrefs.savePref(getContext(), MyNewsTools.Keys.NOTIFICATION_QUERY_TERMS, getSearchQueryTerms());
            MyNewsPrefs.savePref(getContext(), MyNewsTools.Keys.NOTIFICATION_SECTIONS, getCheckboxesSections());
            Toast.makeText(getContext(), R.string.toast_notification_data_saved, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Restore notification layout data.
     */
    private void restoreNotificationData() {
        if (fragmentKey == MyNewsTools.FragmentsKeys.NOTIFICATION_FRAGMENT) {
            switchNotifications.setChecked(MyNewsPrefs.getBoolean(getContext(), MyNewsTools.Keys.NOTIFICATION_SWITCH_STATE, false));
            searchEditText.setText(MyNewsPrefs.getString(getContext(), MyNewsTools.Keys.NOTIFICATION_QUERY_TERMS, null));
            String sections = MyNewsPrefs.getString(getContext(), MyNewsTools.Keys.NOTIFICATION_SECTIONS, null);
            if (sections != null) {
                List<String> sectionList = MyNewsUtils.deConcatenateStringSectionToArrayList(sections);
                for ( int i = 0; i < sectionList.size(); i++) {
                    if (sectionList.get(i).equals(getString(R.string.fragment_search_articles_checkbox_arts))) checkBoxArts.setChecked(true);
                    if (sectionList.get(i).equals(getString(R.string.fragment_search_articles_checkbox_business))) checkBoxBusiness.setChecked(true);
                    if (sectionList.get(i).equals(getString(R.string.fragment_search_articles_checkbox_entrepreneurs))) checkBoxEntrepreneurs.setChecked(true);
                    if (sectionList.get(i).equals(getString(R.string.fragment_search_articles_checkbox_politics))) checkBoxPolitics.setChecked(true);
                    if (sectionList.get(i).equals(getString(R.string.fragment_search_articles_checkbox_sports))) checkBoxSports.setChecked(true);
                    if (sectionList.get(i).equals(getString(R.string.fragment_search_articles_checkbox_travel))) checkBoxTravel.setChecked(true);
                }
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        this.saveNotificationsFragmentData();
    }

    // --------------
    // FRAGMENT SUPPORT
    // --------------

    /**
     * Configure callback search button to parent activity for manage click item.
     */
    private void createSearchCallbackToParentActivity(){
        try {
            searchCallback = (SearchArticlesFragment.OnClickedSearchButtonListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(e.toString()+ " must implement OnClickedSearchButtonListener");
        }
    }

    /**
     * Configure callback notification switch to parent activity for manage click item.
     */
    private void createNotificationCallbackToParentActivity(){
        try {
            notificationCallback = (SearchArticlesFragment.OnClickedNotificationSwitchListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(e.toString()+ " must implement OnClickedNotificationSwitchListener");
        }
    }

    // --------------
    // DATE PICKER API >= 24
    // --------------

    /**
     * Configure date picker.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void configureDatePicker() {
        showBeginDate.setOnClickListener(v -> configureDatePickerDialog(datePickerBegin));
        datePickerBegin = (view, year, month, dayOfMonth) -> {
            beginDate = MyNewsUtils.concatenateIntDateToString(dayOfMonth, month, year);
            showBeginDate.setText(beginDate);
        };
        showEndDate.setOnClickListener(v -> configureDatePickerDialog(datePickerEnd));
        datePickerEnd = (view, year, month, dayOfMonth) -> {
                endDate = MyNewsUtils.concatenateIntDateToString(dayOfMonth, month, year);
                showEndDate.setText(endDate);
        };
    }

    /**
     * Configure Date picker dialog.
     * @param dateSetListener Date picker dialog listener.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void configureDatePickerDialog(DatePickerDialog.OnDateSetListener dateSetListener) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int monthOfYear = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                Objects.requireNonNull(getActivity()), R.style.DatePickerDialogTheme,
                dateSetListener, year, monthOfYear, dayOfMonth);
        datePickerDialog.show();
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
            else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                searchCallback.OnClickSearchListener(getSearchQueryTerms(),
                        getSelectedDatePicker(beginDate, 0),
                        getSelectedDatePicker(endDate, 1),
                        getCheckboxesSections());
            }
//            } else {
//                searchCallback.OnClickSearchListener(getSearchQueryTerms(),
//                        getSpinnersDates(spinnerBegin, beginDateListArray),
//                        getSpinnersDates(spinnerEnd, endDateListArray),
//                        getCheckboxesSections());
//            }
        });
    }

    /**
     * Configure notifications switch.
     */
    private void configureNotificationSwitchListener() {
        switchNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (getCheckboxesSections().isEmpty()) {
                    this.errorNoSectionSelectedDialog();
                    switchNotifications.setChecked(false);
                } else {
                    this.saveNotificationsFragmentData();
                    notificationCallback.OnClickNotificationListener(true);
                }
            } else notificationCallback.OnClickNotificationListener(false);
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
     * Get date picker selected.
     */
    private String getSelectedDatePicker(String selectedDate, int when) {
        if (selectedDate.length() == 0) {
            if (when == 0)
                selectedDate = MyNewsTools.Constant.BEGIN_DATE_DEFAULT;
            if (when == 1)
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