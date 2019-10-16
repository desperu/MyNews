package org.desperu.mynews.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyNewsUtils {

    /**
     * Convert date format from "yyyy-MM-dd'T'HH:mm:ss.SSSXXX" or "yyyy-MM-dd" to "dd/MM/yy".
     * @param givenDate The given date.
     * @return Returned date with new format.
     */
    public static String convertDate(String givenDate) { //TODO Good to set static?
        String returnDate;

        SimpleDateFormat givenDateFormat;
        SimpleDateFormat completeDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.US);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yy", Locale.US);

        if (givenDate.length() > 10) givenDateFormat = completeDateFormat;
        else givenDateFormat = simpleDateFormat;

        try {
            Date date = givenDateFormat.parse(givenDate);
            returnDate = newDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            returnDate = "An error occurred !";
        }

        return returnDate;
    }

    public static String dateToString(Date givenDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return simpleDateFormat.format(givenDate);
    }
}
