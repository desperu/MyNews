package org.desperu.mynews.Utils;

import org.desperu.mynews.Models.NyTimesAPI;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NyTimesService {

    /**
     * Request get for Top Stories.
     * @param section Section name.
     * @param apiKey Api key of this application.
     * @return An Observable object of NyTimesAPI model.
     */
    @GET("topstories/v2/{section}.json")
    Observable<NyTimesAPI> getNyTimesTopStories(@Path("section") String section,
                                                @Query("api-key") String apiKey);
// TODO sort for all
    /**
     * Request get for Most Popular.
     * @param apiKey Api key of this application.
     * @return An Observable object of NyTimesAPI model.
     */
    @GET("mostpopular/v2/viewed/1.json")
    Observable<NyTimesAPI> getNyTimesMostPopular(@Query("api-key") String apiKey);

    /**
     * Request get for New York Times Search.
     * @param queryTerms Search query terms.
     * @param beginDate Begin date to search.
     * @param endDate End date to search.
     * @param sections Sections into search.
     * @param sort Order to sort.
     * @param apiKey Api key of this application.
     * @return An Observable object of NyTimesAPI model.
     */
    @GET("search/v2/articlesearch.json")
    Observable<NyTimesAPI> getNyTimesSearch(@Query(value = "q", encoded = true) String queryTerms,
                                            @Query("begin_date") String beginDate,
                                            @Query("end_date") String endDate,
                                            @Query("fq") String sections,
                                            @Query("sort") String sort,
                                            @Query("api-key") String apiKey);

    /**
     * Build retrofit request with Gson converter and Rxjava adapter.
     */
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}