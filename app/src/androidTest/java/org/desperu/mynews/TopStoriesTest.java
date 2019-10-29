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
public class TopStoriesTest {

    @Test
    public void fetchNyTimesTopStories() throws Exception {
        Observable<NyTimesAPI> nytTopStoriesObservable = NyTimesStreams.streamFetchNyTimesTopStories("home");
        TestObserver<NyTimesAPI> testObserver = new TestObserver<>();

        nytTopStoriesObservable.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        NyTimesAPI topStoriesFetched = testObserver.values().get(0);

        assertThat("Something was downloaded !", topStoriesFetched.getResults() != null);
    }
}