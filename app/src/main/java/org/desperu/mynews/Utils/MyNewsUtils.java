package org.desperu.mynews.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyNewsUtils {

    /**
     * Convert string date format from "yyyy-MM-dd'T'HH:mm:ss.SSSXXX" or "yyyy-MM-dd" to "dd/MM/yy".
     * @param givenDate The given date.
     * @return String date with new format.
     */
    public static String convertDate(String givenDate) { //TODO Good to set static?
        SimpleDateFormat givenDateFormat;
        SimpleDateFormat completeDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.US);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yy", Locale.US);

        if (givenDate.length() > 10) givenDateFormat = completeDateFormat;
        else givenDateFormat = simpleDateFormat;

        try {
            Date date = givenDateFormat.parse(givenDate);
            return newDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "An error occurred !";
        }
    }

    /**
     * Convert date object to string format "dd/MM/yyyy".
     * @param givenDate Given date object.
     * @return String date with good format.
     */
    public static String dateToString(Date givenDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return simpleDateFormat.format(givenDate);
    }

    /**
     * Convert string date format from "dd/MM/yyyy" to "yyyyMMdd".
     * @param givenDate The given date.
     * @return String date with new format.
     */
    public static String changeDateFormat(String givenDate) {
        SimpleDateFormat givenDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());

        try {
            Date date = givenDateFormat.parse(givenDate);
            return newDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "An error occurred !";
        }
    }
}
