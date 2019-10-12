package org.desperu.mynews;

public final class MyNewsTools {

    /**
     * Constant to set your preferences.
     */
    public static final class Constant {

        // My News Adapter
        // Number of pages. If you want change it, you must add/del corresponding values in switch of
        // ArticleListFragment executeHttpRequestWithRetrofit, it's name in TAB_TITLES, and create the corresponding request.
        public static final int numberOfPage = 3;

        // TODO clean
        // History
        // To set history size (number of days).
        public static final int numberOfDays = 7;

        // MainActivity
        // Enable or disable wrongDate function.
        public static final boolean wrongDateEnabled = true;

        // Mood Adapter
        // Number of moods. If you want change it, you must add/del corresponding values in switch of
        // MoodFragment onCreateView, MoodHistoryActivity onCreateHistoryView and MoodUtils moodShareText.
//        public static final int numberOfPage = 5;

        // MoodHistoryActivity
        // To set history day number in screen, so the size of each.
        public static final int numberInScreen = 7;

        // MoodUtils
        // One day un millis -> (24 * 60 * 60 * 1000) = 86 400 000.
        public static final int oneDayMillis = 86400000;
    }
}
