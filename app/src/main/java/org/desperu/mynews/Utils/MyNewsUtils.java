package org.desperu.mynews.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyNewsUtils {

    /**
     * Convert string date format from "yyyy-MM-dd'T'HH:mm:ssXXX" or "yyyy-MM-dd'T'HH:mm:ssZ" or "yyyy-MM-dd" to "dd/MM/yy".
     * @param givenDate The given date.
     * @return String date with new format.
     */
    public static String convertDate(String givenDate) {
        SimpleDateFormat givenDateFormat;
        SimpleDateFormat completeDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.US);
        SimpleDateFormat mediumDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH); // TODO problem for search with local time!!
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yy", Locale.getDefault()); // TODO date decal ... for search, because it's not same time around the world!!!

        if (givenDate.length() > 24) givenDateFormat = completeDateFormat;
        else if (givenDate.length() > 10) givenDateFormat = mediumDateFormat;
        else givenDateFormat = simpleDateFormat;

        try {
            Date date = givenDateFormat.parse(givenDate);
            assert date != null;
            return newDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "An error occurred !";
        }
    }

    /**
     * Concatenate date from int to string.
     * @param day Selected day.
     * @param month Selected month.
     * @param year Selected year.
     * @return String date.
     */
    public static String concatenateIntDateToString(int day, int month, int year) {
        month += 1;
        String stringDay;
        if (day < 10) stringDay = "0"+day;
        else stringDay = String.valueOf(day);
        String stringMonth;
        if (month < 10) stringMonth = "0"+month;
        else stringMonth = String.valueOf(month);
        return stringDay+"/"+stringMonth+"/"+year;
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
     * Convert string date format from "dd/MM/yyyy" to Date.
     * @param givenDate The given date.
     * @return Date object.
     */
    public static Date stringToDate(String givenDate) {
        SimpleDateFormat givenDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date = new Date();
        try {
            date = givenDateFormat.parse(givenDate);
        } catch (ParseException e) { e.printStackTrace(); }
        return date;
    }

    /**
     * Convert Date object to string format "yyyyMMdd".
     * @param givenDate The given Date object.
     * @return String date with new format.
     */
    public static String dateToStringForNyTimes(Date givenDate) {
        SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.US); // TODO problem for search with local time!!
        return newDateFormat.format(givenDate);
    }

    /**
     * Concatenate array list of sections to good string format for API request.
     * @param arrayList The given sections array list.
     * @return String good format for API request.
     */
    public static String concatenateStringSectionsFromArrayList(ArrayList<String> arrayList) {
        StringBuilder sections;
        String sectionFilterName = "news_desk.contains:(";
        if (arrayList.isEmpty()) return "";
        else sections = new StringBuilder(sectionFilterName);
        for (int i = 0; i < arrayList.size(); i++) {
            sections.append("\"");
            sections.append(arrayList.get(i));
            sections.append("\"");
            if ((i + 1) < arrayList.size()) sections.append(" ");
        }
        sections.append(")");
        return sections.toString();
    }

    /**
     * DeConcatenate string sections to List of string.
     * @param sections The concatenated sections string.
     * @return List of sections.
     */
    public static List<String> deConcatenateStringSectionToArrayList(String sections) {
        String[] str = sections.split(" ");
        List<String> list = Arrays.asList(str);

        for (int i = 0; i < list.size(); i++) {
            if (i == 0) list.set(i, list.get(i).replace("news_desk.contains:(\"", ""));
            list.set(i, list.get(i).replaceAll("\"", ""));
            if (i == list.size() - 1) list.set(i, list.get(i).replace(")", ""));
        }

        return list;
    }
}