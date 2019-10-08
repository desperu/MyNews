package org.desperu.mynews.Utils;

import org.desperu.mynews.Models.NYTMostPopular.NYTMostPopular;
import org.desperu.mynews.Models.NYTTopStories.NYTTopStories;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NYTStreams {

    public static Observable<NYTTopStories> streamFetchNYTTopStories(String category){
        NYTService nytService = NYTService.retrofit.create(NYTService.class);
        return nytService.getNYTTopStories(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<NYTMostPopular> streamFetchNYTMostPopular(){
        NYTService nytService = NYTService.retrofit.create(NYTService.class);
        return nytService.getNYTMostPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

//    public static Observable<GithubUserInfo> streamFetchUserFollowingAndFetchFirstUserInfos(String username){
//        return streamFetchUserFollowing(username) // 1 - Fetch all users that user follows
//                .map(new Function<List<GithubUser>, GithubUser>() {
//                    @Override
//                    public GithubUser apply(List<GithubUser> users) throws Exception {
//                        return users.get(0); // 2 - Return the user with the most followers
//                    }
//                })
//                .flatMap(new Function<GithubUser, Observable<GithubUserInfo>>() {
//                    @Override
//                    public Observable<GithubUserInfo> apply(GithubUser user) throws Exception {
//                        return streamFetchUserInfos(user.getLogin()); // 3 - Get all repos for this user
//                    }
//                });
//    }
}