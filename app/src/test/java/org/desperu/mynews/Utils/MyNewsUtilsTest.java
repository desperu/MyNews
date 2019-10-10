package org.desperu.mynews.Utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyNewsUtilsTest {

//    private MyNewsUtils myNewsUtils = new MyNewsUtils();

    private String givenDate = "2019-10-09T15:28:13-04:00";
    private String returnedDate = "09/10/19";

    private String output;

    @Test
    public void Given_givenDate_When_dateChangeFormat_Then_returnedDateNewFormat() {
        output = MyNewsUtils.convertDate(givenDate);

        assertEquals(returnedDate, output);
    }
}
