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

        // NyTimes Streams
        // Application api key for New York Times API.
        public static final String apiKey = "rWAsKkWDKqfM3G5yBmm37TmPHTSrLznA";

        // SearchArticlesFragment
        // Spinner date limit search in past, in days.
        public static final int DATE_LIMIT = 365;

        // Spinner begin, default date.
        public static final String BEGIN_DATE_DEFAULT = "01/10/1000";
    }

    /**
     * Keys for shared preferences.
     */
    public static final class Keys {

        // Main activity.
        public static final String CURRENT_PAGE = "CurrentPage";
    }

    public static final class FragmentsKeys {

        // Search Fragment
        public static final int SEARCH_FRAGMENT = 1;

        // Notification Fragment
        public static final int NOTIFICATION_FRAGMENT = 2;
    }
}
