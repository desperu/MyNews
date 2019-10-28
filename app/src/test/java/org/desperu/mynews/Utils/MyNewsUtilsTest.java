package org.desperu.mynews.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import org.desperu.mynews.MyNewsTools;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.desperu.mynews.MyNewsTools.Keys.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyNewsUtilsTest {

    private String returnedDate = "09/10/19";
    private String errorDateFormat = "An error occurred !";

    private String output;

    @Test
    public void Given_completeDateFormat_When_askConvertDateFormat_Then_checkNewDateFormat() {
        String givenDate = "2019-10-09T15:28:13-0400"; // "2019-10-09T15:28:13-04:00" pattern work on emulator test
        output = MyNewsUtils.convertDate(givenDate);

        assertEquals(returnedDate, output);
    }

    @Test
    public void Given_mediumDateFormat_When_askConvertDateFormat_Then_checkNewDateFormat() {
        String givenDate = "2019-10-09T15:28:13+0000";
        output = MyNewsUtils.convertDate(givenDate);

        assertEquals(returnedDate, output);
    }

    @Test
    public void Given_simpleDateFormat_When_askConvertDateFormat_Then_checkNewDateFormat() {
        String givenDate = "2019-10-09";
        output = MyNewsUtils.convertDate(givenDate);

        assertEquals(returnedDate, output);
    }

    @Test
    public void Given_badCompleteDateFormat_When_askConvertDateFormat_Then_checkErrorDateFormat() {
        String givenDate = "2019-10-09T15:28:13/04:00";
        output = MyNewsUtils.convertDate(givenDate);

        assertEquals(errorDateFormat, output);
    }

    @Test
    public void Given_badMediumDateFormat_When_askConvertDateFormat_Then_checkErrorDateFormat() {
        String givenDate = "2019-10-09T15:28:13/0400";
        output = MyNewsUtils.convertDate(givenDate);

        assertEquals(errorDateFormat, output);
    }

    @Test
    public void Given_badSimpleDateFormat_When_askConvertDateFormat_Then_checkErrorDateFormat() {
        String givenDate = "20191009";
        output = MyNewsUtils.convertDate(givenDate);

        assertEquals(errorDateFormat, output);
    }

    @Test
    public void Given_intDateMonthSeptember_When_askConcatenateIntDateToString_Then_checkStringDate() {
        int day = 1;
        int month = 8;
        int year = 2019;
        output = MyNewsUtils.concatenateIntDateToString(day, month, year);

        String expected ="01/09/2019";
        assertEquals(expected, output);
    }

    @Test
    public void Given_intDateMonthNovember_When_askConcatenateIntDateToString_Then_checkStringDate() {
        int day = 21;
        int month = 10;
        int year = 2019;
        output = MyNewsUtils.concatenateIntDateToString(day, month, year);

        String expected ="21/11/2019";
        assertEquals(expected, output);
    }

    @Test
    public void Given_dateObject_When_askDateToString_Then_checkNewDateFormat() {
        String expected = "15/10/2019";
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 9, 15);
        Date date = calendar.getTime();
        output = MyNewsUtils.dateToString(date);

        assertEquals(expected, output);
    }

    @Test
    public void Given_stringDate_When_askStringToDate_Then_checkNewDateFormat() throws ParseException {
        String givenDate = "5/9/2019";
        SimpleDateFormat givenDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date returnedDate = givenDateFormat.parse(givenDate);

        Date output = MyNewsUtils.stringToDate(givenDate);

        assertEquals(returnedDate, output);
    }

    @Test
    public void Given_dateObject_When_askDateToStringForNyTimes_Then_checkStringDateFormat() throws ParseException {
        String givenDate = "5/9/2019";
        SimpleDateFormat givenDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date = givenDateFormat.parse(givenDate);
        String returnedDate = "20190905";
        output = MyNewsUtils.dateToStringForNyTimes(date);

        assertEquals(returnedDate, output);
    }

    @Test
    public void Given_sectionsArrayList_When_askConcatenateStringSectionsFromArrayList_Then_checkString() {
        String expected = "news_desk.contains:(\"Politics\" \"Business\" \"Entrepreneurs\")";
        ArrayList<String> sections = new ArrayList<>();
        sections.add("Politics");
        sections.add("Business");
        sections.add("Entrepreneurs");

        output = MyNewsUtils.concatenateStringSectionsFromArrayList(sections);

        assertEquals(expected, output);
    }

    @Test
    public void Given_emptySectionsArrayList_When_askConcatenateStringSectionsFromArrayList_Then_checkEmptyString() {
        String expected = "";
        ArrayList<String> sections = new ArrayList<>();

        output = MyNewsUtils.concatenateStringSectionsFromArrayList(sections);

        assertEquals(expected, output);
    }

    @Test
    public void Given_stringSection_When_askDeConcatenateStringSectionsToArrayList_Then_checkArrayList() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Politics");
        expected.add("Business");
        expected.add("Entrepreneurs");
        expected.add("Arts");
        String sections = "news_desk.contains:(\"Politics\" \"Business\" \"Entrepreneurs\" \"Arts\")";
        List<String> output = MyNewsUtils.deConcatenateStringSectionToArrayList(sections);

        assertEquals(expected, output);
    }

    @Mock Context mockContext;
    @Mock SharedPreferences mockPrefs;

    @Test
    public void Given_articleUrl_When_searchUrlInHistory_Then_returnPosition() {
        int expected = 0;
        String url = MyNewsTools.Constant.nyTimesImageUrl;

        when(mockContext.getSharedPreferences(anyString(), anyInt())).thenReturn(mockPrefs);
        when(mockPrefs.getString(ARTICLE_READ_URL + 0, "")).thenReturn(url);
        when(mockPrefs.getInt(MAX_HISTORY_VALUE, 0)).thenReturn(expected);

        int output = MyNewsUtils.searchReadArticle(mockContext, url);

        assertEquals(expected, output);
    }

    @Test
    public void Given_articleUrl_When_searchUrlInHistory_Then_returnDefault() {
        int expected = -1;
        String url = MyNewsTools.Constant.nyTimesImageUrl;

        when(mockContext.getSharedPreferences(anyString(), anyInt())).thenReturn(mockPrefs);
        when(mockPrefs.getString(ARTICLE_READ_URL + 0, "")).thenReturn("test");
        when(mockPrefs.getInt(MAX_HISTORY_VALUE, 0)).thenReturn(0);

        int output = MyNewsUtils.searchReadArticle(mockContext, url);

        assertEquals(expected, output);
    }
}