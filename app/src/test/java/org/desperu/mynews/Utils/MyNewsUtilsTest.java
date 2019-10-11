package org.desperu.mynews.Utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyNewsUtilsTest {

    private String returnedDate = "09/10/19";
    private String errorDateFormat = "An error occurred !";

    private String output;

    @Test
    public void Given_givenCompleteDate_When_askChangeDateFormat_Then_returnNewDateFormat() {
        String givenDate = "2019-10-09T15:28:13-04:00";
        output = MyNewsUtils.convertDate(givenDate);

        assertEquals(returnedDate, output);
    }

    @Test
    public void Given_givenSimpleDateFormat_When_askChangeDateFormat_Then_returnNewDateFormat() {
        String givenDate = "2019-10-09";
        output = MyNewsUtils.convertDate(givenDate);

        assertEquals(returnedDate, output);
    }

    @Test
    public void Given_givenBadSimpleDateFormat_When_askChangeDateFormat_Then_returnErrorDateFormat() {
        String givenDate = "20191009";
        output = MyNewsUtils.convertDate(givenDate);

        assertEquals(errorDateFormat, output);
    }

    @Test
    public void Given_givenBadCompleteDateFormat_When_askChangeDateFormat_Then_returnErrorDateFormat() {
        String givenDate = "2019-10-09T15:28:13/04:00";
        output = MyNewsUtils.convertDate(givenDate);

        assertEquals(errorDateFormat, output);
    }
}
