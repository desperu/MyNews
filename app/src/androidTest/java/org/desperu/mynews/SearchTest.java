package org.desperu.mynews;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.desperu.mynews.Models.NyTimesAPI;
import org.desperu.mynews.Utils.NyTimesStreams;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4ClassRunner.class)
public class SearchTest {

    @Test
    public void fetchNyTimesSearch() throws Exception {
        String queryTerms = "trump";
        String beginDate = "20190101";
        String endDate = "20191016";
        String sections = "news_desk:(\"politics\")";

        Observable<NyTimesAPI> nytTopStoriesObservable = NyTimesStreams.streamFetchNyTimesSearch(
                queryTerms, beginDate, endDate, sections);
        TestObserver<NyTimesAPI> testObserver = new TestObserver<>();

        nytTopStoriesObservable.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        NyTimesAPI searchFetched = testObserver.values().get(0);

        assertThat("Something was downloaded !", searchFetched.getResponse().getDocs() != null);
    }
}