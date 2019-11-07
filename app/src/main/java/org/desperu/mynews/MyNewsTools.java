package org.desperu.mynews;

public final class MyNewsTools {

    /**
     * Constant to set your preferences.
     */
    public static final class Constant {

        // My News Adapter.
        // Number of pages. If you want change it, you must add/del corresponding values in switch of
        // ArticleListFragment executeHttpRequestWithRetrofit, it's name in MyNewsAdapter TAB_TITLES,
        // and create the corresponding http request.
        public static final int numberOfPage = 3;

        // NyTimes Streams.
        // Application api key for New York Times API.
        public static final String apiKey = "rWAsKkWDKqfM3G5yBmm37TmPHTSrLznA";

        // NyTimes View Holder.
        // Base url for search result images.
        public static final String nyTimesImageUrl = "https://static01.nyt.com/";

        // NyTimes Service.
        // To sort articles.
        public static final String SORT_BY = "newest";

        // SearchAndNotificationFragment and NotificationAlarmService.
        // Begin date, default value for api request.
        public static final String BEGIN_DATE_DEFAULT = "01/01/1800";

        // MyNewsUtils.
        // History max age in millis 30*24*60*60*1000, 30 days.
        public static final long HISTORY_AGE = 2592000000L;

        // MainActivity.
        // Documentation url.
        public static final String DOCUMENTATION_URL = "https://github.com/desperu/MyNews/blob/master/Documentation/Documentation%20Fonctionnelle%20MyNews.pdf";
    }

    /**
     * Keys for shared preferences.
     */
    public static final class Keys {

        // SearchAndNotificationFragment, for notifications.
        public static final String NOTIFICATION_SWITCH_STATE = "NotificationSwitchState";
        public static final String NOTIFICATION_QUERY_TERMS = "NotificationQueryTerms";
        public static final String NOTIFICATION_SECTIONS = "NotificationSections";

        // History already read articles.
        public static final String MAX_HISTORY_VALUE = "MaxHistoryValue";
        public static final String ARTICLE_READ_URL = "ReadArticles";
        public static final String ARTICLE_READ_TIME = "ReadTime";
    }

    /**
     * Keys for fragments.
     */
    public static final class FragmentsKeys {

        // String key for bundle.
        public static final String KEY_FRAGMENT = "fragmentKey";

        // TopStories Fragment.
        public static final int TOP_STORIES_FRAGMENT = 0;

        // MostPopular Fragment.
        public static final int MOST_POPULAR_FRAGMENT = 1;

        // Sciences Fragment.
        public static final int SCIENCES_FRAGMENT = 2;

        // Search Results Fragment.
        public static final int SEARCH_RESULTS_FRAGMENT = 3;

        // Search Fragment.
        public static final int SEARCH_FRAGMENT = 4;

        // Notification Fragment.
        public static final int NOTIFICATION_FRAGMENT = 5;
    }

    /**
     * Keys for error dialog and get date picker in SearchAndNotificationFragment.
     */
    public static final class SearchKeys {

        // SearchAndNotificationFragment.
        // Dialog error sections.
        public static final int SECTIONS_DIALOG = 0;

        // Dialog error dates.
        public static final int DATES_DIALOG = 1;

        // Date picker begin.
        public static final int BEGIN_DATE = 0;

        // Date picker end.
        public static final int END_DATE = 1;
    }
}
