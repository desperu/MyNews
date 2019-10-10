package org.desperu.mynews.Utils;

import org.desperu.mynews.Models.NyTimesAPI;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NyTimesService {

    public String apiKey = "rWAsKkWDKqfM3G5yBmm37TmPHTSrLznA";

//    https://api.nytimes.com/svc/topstories/v2/science.json?api-key=rWAsKkWDKqfM3G5yBmm37TmPHTSrLznA
    @GET("topstories/v2/{section}.json?api-key="+apiKey)
    Observable<NyTimesAPI> getNYTTopStories(@Path("section") String section);

    //    https://api.nytimes.com/svc/mostpopular/v2/viewed/1.json?api-key=yourkey
    @GET("mostpopular/v2/viewed/1.json?api-key="+apiKey)
    Observable<NyTimesAPI> getNYTMostPopular();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}