package org.desperu.mynews.Utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class MyNewsUtilsTest {

    private String returnedDate = "09/10/19";
    private String errorDateFormat = "An error occurred !";

    private String output;

    @Test
    public void Given_completeDate_When_askConvertDateFormat_Then_checkNewDateFormat() {
        String givenDate = "2019-10-09T15:28:13-04:00";
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
    public void Given_badSimpleDateFormat_When_askConvertDateFormat_Then_checkErrorDateFormat() {
        String givenDate = "20191009";
        output = MyNewsUtils.convertDate(givenDate);

        assertEquals(errorDateFormat, output);
    }

    @Test
    public void Given_badCompleteDateFormat_When_askConvertDateFormat_Then_checkErrorDateFormat() {
        String givenDate = "2019-10-09T15:28:13/04:00";
        output = MyNewsUtils.convertDate(givenDate);

        assertEquals(errorDateFormat, output);
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
    public void Given_dateFormat_When_askChangeDateFormat_Then_checkNewDateFormat() {
        String givenDate = "15/10/2019";
        String returnedDate = "20191015";
        output = MyNewsUtils.changeDateFormat(givenDate);

        assertEquals(returnedDate, output);
    }

    @Test
    public void Given_badDateFormat_When_askChangeDateFormat_Then_checkErrorDateFormat() {
        String givenDate = "15-10-2019";
        output = MyNewsUtils.changeDateFormat(givenDate);

        assertEquals(errorDateFormat, output);
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
}