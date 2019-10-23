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
        // Begin date, default value for api request.
        public static final String BEGIN_DATE_DEFAULT = "01/01/1800";

        // NyTimesService & Service
        // To sort articles.
        public static final String SORT_BY = "newest";
    }

    /**
     * Keys for shared preferences.
     */
    public static final class Keys {

        // SearchArticlesFragment, for notifications.
        public static final String NOTIFICATION_SWITCH_STATE = "NotificationSwitchState";
        public static final String NOTIFICATION_QUERY_TERMS = "NotificationQueryTerms";
        public static final String NOTIFICATION_SECTIONS = "NotificationsSections";
    }

    /**
     * Keys for fragments.
     */
    public static final class FragmentsKeys {

        // TopStories Fragment
        public static final int TOP_STORIES_FRAGMENT = 0;

        // MostPopular Fragment
        public static final int MOST_POPULAR_FRAGMENT = 1;

        // Sciences Fragment
        public static final int SCIENCES_FRAGMENT = 2;

        // Search Results Fragment
        public static final int SEARCH_RESULTS_FRAGMENT = 3;

        // Search Fragment
        public static final int SEARCH_FRAGMENT = 4;

        // Notification Fragment
        public static final int NOTIFICATION_FRAGMENT = 5;
    }
}
