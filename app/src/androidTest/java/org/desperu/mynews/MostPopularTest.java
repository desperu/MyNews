package org.desperu.mynews;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.desperu.mynews.models.NyTimesAPI;
import org.desperu.mynews.utils.NyTimesStreams;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4ClassRunner.class)
public class MostPopularTest {

    @Test
    public void fetchNyTimesMostPopular() throws Exception {
        Observable<NyTimesAPI> nytMostPopularObservable = NyTimesStreams.streamFetchNyTimesMostPopular();
        TestObserver<NyTimesAPI> testObserver = new TestObserver<>();

        nytMostPopularObservable.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        NyTimesAPI mostPopularFetched = testObserver.values().get(0);

        assertThat("Something was downloaded !", mostPopularFetched.getResults() != null);
    }
}
