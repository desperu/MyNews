package org.desperu.mynews.controllers.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.desperu.mynews.R;
import org.desperu.mynews.utils.MyNewsPrefs;
import org.desperu.mynews.utils.MyNewsUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import icepick.State;

import static org.desperu.mynews.MyNewsTools.Constant.*;
import static org.desperu.mynews.MyNewsTools.Keys.*;
import static org.desperu.mynews.MyNewsTools.FragmentsKeys.*;
import static org.desperu.mynews.MyNewsTools.SearchKeys.*;

public class SearchAndNotificationFragment extends BaseFragment {

    // Edit text to enter query search terms
    @BindView(R.id.fragment_search_and_notifications_edit_text) EditText searchEditText;
    // Date picker, text view and dividers for begin date and end date.
    @BindView(R.id.fragment_search_and_notifications_text_view_begin_date) TextView textViewBeginDate;
    @BindView(R.id.fragment_search_and_notifications_text_view_date_picker_begin) TextView showBeginDate;
    @BindView(R.id.fragment_search_and_notifications_begin_date_divider) View beginDateDivider;
    @BindView(R.id.fragment_search_and_notifications_text_view_end_date) TextView textViewEndDate;
    @BindView(R.id.fragment_search_and_notifications_text_view_date_picker_end) TextView showEndDate;
    @BindView(R.id.fragment_search_and_notifications_end_date_divider) View endDateDivider;
    // Check boxes to select sections.
    @BindView(R.id.fragment_search_and_notifications_checkbox_arts) CheckBox checkBoxArts;
    @BindView(R.id.fragment_search_and_notifications_checkbox_business) CheckBox checkBoxBusiness;
    @BindView(R.id.fragment_search_and_notifications_checkbox_entrepreneurs) CheckBox checkBoxEntrepreneurs;
    @BindView(R.id.fragment_search_and_notifications_checkbox_politics) CheckBox checkBoxPolitics;
    @BindView(R.id.fragment_search_and_notifications_checkbox_sports) CheckBox checkBoxSports;
    @BindView(R.id.fragment_search_and_notifications_checkbox_travel) CheckBox checkBoxTravel;
    // Button, switch, and divider.
    @BindView(R.id.fragment_search_and_notifications_button_search) Button buttonSearch;
    @BindView(R.id.fragment_search_and_notifications_button_notify_search) Button buttonNotifySearch;
    @BindView(R.id.fragment_search_and_notifications_bottom_divider) View bottomDivider;
    @BindView(R.id.fragment_search_and_notifications_switch_notifications) Switch switchNotifications;

    // Switch between fragment
    @State int fragmentKey;
    // Date picker
    private DatePickerDialog.OnDateSetListener datePickerBegin;
    private DatePickerDialog.OnDateSetListener datePickerEnd;
    @State String beginDate = "";
    @State String endDate = "";

    // Callback Search button
    public interface OnClickSearchAndNotifySearchButtonListener {
        void OnClickSearchListener(String queryTerms, String beginDate, String endDate, String sections);
        void OnClickNotifySearchListener(boolean isNotificationEnabled);
    }

    private SearchAndNotificationFragment.OnClickSearchAndNotifySearchButtonListener searchCallback;

    // Callback Notification Switch
    public interface OnClickNotificationSwitchListener {
        void OnClickNotificationListener(boolean isChecked);
    }

    private SearchAndNotificationFragment.OnClickNotificationSwitchListener notificationCallback;

    // --------------
    // BASE METHODS
    // --------------

    @Override
    protected int getFragmentLayout() { return R.layout.fragment_search_and_notifications; }

    @Override
    protected void configureDesign() {
        this.setFragmentKey();
        this.restoreNotificationData();
        this.configureAskedFragment(fragmentKey);
    }

    // --------------
    // CONSTRUCTOR
    // --------------

    public SearchAndNotificationFragment() {}

    // --------------
    // CONFIGURATION
    // --------------

    /**
     * Set fragmentKey with bundle argument if not null.
     */
    private void setFragmentKey() {
        assert getArguments() != null;
        this.fragmentKey = getArguments().getInt(KEY_FRAGMENT, 0);
    }

    /**
     * Configure fragment search or notifications and hide unused items.
     * @param fragmentKey Fragment id.
     */
    private void configureAskedFragment(int fragmentKey) {
        switch (fragmentKey) {
            case SEARCH_FRAGMENT :
                this.configureDatePicker();
                this.configureSearchButtonOnClickListener();
                this.configureNotifySearchButtonOnClickListener();
                this.createSearchCallbackToParentActivity();
                bottomDivider.setVisibility(View.GONE);
                switchNotifications.setVisibility(View.GONE);
                break;
            case NOTIFICATION_FRAGMENT :
                this.configureNotificationSwitchListener();
                this.createNotificationCallbackToParentActivity();
                this.hideDateItems();
                buttonSearch.setVisibility(View.GONE);
                buttonNotifySearch.setVisibility(View.GONE);
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
     * Save notification data.
     * @param notifySearch If is called from notify search button.
     * @param showToast Hide toast data when enable notifications, to not show too toasts.
     */
    private void saveNotificationsData(boolean notifySearch, boolean showToast) {
        if (fragmentKey == NOTIFICATION_FRAGMENT || notifySearch) {
            if (notifySearch) MyNewsPrefs.savePref(getContext(), NOTIFICATION_SWITCH_STATE, true);
            else MyNewsPrefs.savePref(getContext(), NOTIFICATION_SWITCH_STATE, switchNotifications.isChecked());
            MyNewsPrefs.savePref(getContext(), NOTIFICATION_QUERY_TERMS, getSearchQueryTerms());
            if (getCheckboxesSections().isEmpty() && switchNotifications.isChecked())
                Toast.makeText(getContext(), R.string.toast_notification_section_data_not_saved, Toast.LENGTH_LONG).show();
            else {
                MyNewsPrefs.savePref(getContext(), NOTIFICATION_SECTIONS, getCheckboxesSections());
                if (showToast) Toast.makeText(getContext(), R.string.toast_notification_data_saved, Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * Restore notification data.
     */
    private void restoreNotificationData() {
        if (fragmentKey == NOTIFICATION_FRAGMENT) {
            switchNotifications.setChecked(MyNewsPrefs.getBoolean(getContext(), NOTIFICATION_SWITCH_STATE, false));
            searchEditText.setText(MyNewsPrefs.getString(getContext(), NOTIFICATION_QUERY_TERMS, null));
            String sections = MyNewsPrefs.getString(getContext(), NOTIFICATION_SECTIONS, null);
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
        this.saveNotificationsData(false, true);
    }

    // --------------
    // FRAGMENT SUPPORT
    // --------------

    /**
     * Configure callback search and notify search button to parent activity for manage click.
     */
    private void createSearchCallbackToParentActivity(){
        try {
            searchCallback = (SearchAndNotificationFragment.OnClickSearchAndNotifySearchButtonListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(e.toString()+ " must implement OnClickSearchAndNotifySearchButtonListener");
        }
    }

    /**
     * Configure callback notification switch to parent activity for manage click.
     */
    private void createNotificationCallbackToParentActivity(){
        try {
            notificationCallback = (SearchAndNotificationFragment.OnClickNotificationSwitchListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(e.toString()+ " must implement OnClickNotificationSwitchListener");
        }
    }

    // --------------
    // DATE PICKERS
    // --------------

    /**
     * Configure date picker.
     */
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
    private void configureDatePickerDialog(DatePickerDialog.OnDateSetListener dateSetListener) {
        Calendar cal = Calendar.getInstance();
        if (dateSetListener == datePickerBegin && beginDate.length() > 0)
            cal.setTime(MyNewsUtils.stringToDate(beginDate));
        if (dateSetListener == datePickerEnd && endDate.length() > 0)
            cal.setTime(MyNewsUtils.stringToDate(endDate));
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
            if (getCheckboxesSections().isEmpty()) this.searchErrorDialog(SECTIONS_DIALOG);
            else if (Integer.parseInt(getSelectedDatePicker(beginDate, BEGIN_DATE))
                    > Integer.parseInt(getSelectedDatePicker(endDate, END_DATE)))
                this.searchErrorDialog(DATES_DIALOG);
            else { searchCallback.OnClickSearchListener(getSearchQueryTerms(),
                    this.getSelectedDatePicker(beginDate, BEGIN_DATE),
                    this.getSelectedDatePicker(endDate, END_DATE),
                    this.getCheckboxesSections());
            }
        });
    }

    /**
     * Configure notify search button on click listener.
     */
    private void configureNotifySearchButtonOnClickListener() {
        buttonNotifySearch.setOnClickListener(v -> {
            if (this.getCheckboxesSections().isEmpty()) this.searchErrorDialog(SECTIONS_DIALOG);
            else {
                this.searchCallback.OnClickNotifySearchListener(MyNewsPrefs.getBoolean(getContext(), NOTIFICATION_SWITCH_STATE, false));
                this.saveNotificationsData(true, true);
            }
        });
    }

    /**
     * Configure notifications switch listener.
     */
    private void configureNotificationSwitchListener() {
        switchNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (this.getCheckboxesSections().isEmpty()) {
                    this.searchErrorDialog(SECTIONS_DIALOG);
                    this.switchNotifications.setChecked(false);
                } else {
                    this.notificationCallback.OnClickNotificationListener(true);
                    this.saveNotificationsData(false, false);
                }

            } else this.notificationCallback.OnClickNotificationListener(false);
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
     * @param selectedDate Date picker selected date.
     * @param beginOrEndDate Key to switch between beginDate and endDate.
     * @return Corresponding string date.
     */
    private String getSelectedDatePicker(String selectedDate, int beginOrEndDate) {
        if (selectedDate.isEmpty()) {
            if (beginOrEndDate == BEGIN_DATE) selectedDate = BEGIN_DATE_DEFAULT;
            if (beginOrEndDate == END_DATE) selectedDate = MyNewsUtils.dateToString(new Date());
        }
        return MyNewsUtils.dateToStringForNyTimes(MyNewsUtils.stringToDate(selectedDate));
    }

    /**
     * Create and show dialog box when no section selected or beginDate is bigger than endDate.
     * @param sectionsOrDates Key for switch dialog between section error and date error.
     */
    private void searchErrorDialog(int sectionsOrDates) {
        AlertDialog.Builder errorNoSection = new AlertDialog.Builder(getContext());
        if (sectionsOrDates == SECTIONS_DIALOG) {
            errorNoSection.setTitle(R.string.fragment_search_articles_dialog_no_section_title);
            errorNoSection.setMessage(R.string.fragment_search_articles_dialog_no_section_message);
        } else if (sectionsOrDates == DATES_DIALOG) {
            errorNoSection.setTitle(R.string.fragment_search_articles_dialog_date_problem_title);
            errorNoSection.setMessage(R.string.fragment_search_articles_dialog_date_problem_message);
        }
        errorNoSection.setPositiveButton(R.string.fragment_search_articles_dialog_no_section_positive_button, (dialog, which) -> dialog.cancel());
        errorNoSection.show();
    }
}