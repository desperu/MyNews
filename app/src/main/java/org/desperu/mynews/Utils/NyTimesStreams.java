package org.desperu.mynews.Utils;

import org.desperu.mynews.Models.NyTimesAPI;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NyTimesStreams {

    public static Observable<NyTimesAPI> streamFetchNYTTopStories(String section){
        NyTimesService nyTimesService = NyTimesService.retrofit.create(NyTimesService.class);
        return nyTimesService.getNYTTopStories(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<NyTimesAPI> streamFetchNYTMostPopular(){
        NyTimesService nyTimesService = NyTimesService.retrofit.create(NyTimesService.class);
        return nyTimesService.getNYTMostPopular()
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