package org.desperu.mynews.Utils;

import org.desperu.mynews.Models.NyTimesAPI;
import org.desperu.mynews.Utils.HttpRequest.MyHttpRequest;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static org.desperu.mynews.MyNewsTools.Constant.*;

public class NyTimesStreams {

    /**
     * Fetch New York Times Top Stories articles.
     * @param section Section name.
     * @return An Observable object of NyTimesAPI model.
     */
    public static Observable<NyTimesAPI> streamFetchNyTimesTopStories(String section) {
        NyTimesService nyTimesService = NyTimesService.retrofit.create(NyTimesService.class);
        return nyTimesService.getNyTimesTopStories(section, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    /**
     * Fetch New York Times Most Popular articles.
     * @return An Observable object of NyTimesAPI model.
     */
    public static Observable<NyTimesAPI> streamFetchNyTimesMostPopular() {
        NyTimesService nyTimesService = NyTimesService.retrofit.create(NyTimesService.class);
        return nyTimesService.getNyTimesMostPopular(apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    /**
     * Fetch New York times Search articles.
     * @param queryTerms Search query terms.
     * @param beginDate Begin date to search.
     * @param endDate End date to search.
     * @param sections Sections into search.
     * @param sort Order to sort.
     * @return An Observable object of NyTimesAPI model.
     */
    public static Observable<NyTimesAPI> streamFetchNyTimesSearch(String queryTerms, String beginDate,
                                                                  String endDate, String sections, String sort) {
        NyTimesService nyTimesService = NyTimesService.retrofit.create(NyTimesService.class);
        return nyTimesService.getNyTimesSearch(queryTerms, beginDate, endDate, sections, sort, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
}