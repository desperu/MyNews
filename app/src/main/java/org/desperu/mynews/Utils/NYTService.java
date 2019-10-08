package org.desperu.mynews.Utils;

import org.desperu.mynews.Models.NYTMostPopular.NYTMostPopular;
import org.desperu.mynews.Models.NYTTopStories.NYTTopStories;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NYTService {

    public String apiKey = "rWAsKkWDKqfM3G5yBmm37TmPHTSrLznA";
//    https://api.nytimes.com/svc/topstories/v2/science.json?api-key=rWAsKkWDKqfM3G5yBmm37TmPHTSrLznA
    // TODO change username var
//    @GET("users/{username}/following")
//    Observable<List<TopStoriesResult>> getNYTTopStories(@Path("username") String username);

    @GET("topstories/v2/{category}.json?api-key="+apiKey)
    Observable<NYTTopStories> getNYTTopStories(@Path("category") String category);

    @GET("mostpopular/v2/viewed/1.json?api-key="+apiKey)
    Observable<NYTMostPopular> getNYTMostPopular();

//    https://api.nytimes.com/svc/mostpopular/v2/viewed/1.json?api-key=yourkey

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}
