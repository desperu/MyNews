package org.desperu.mynews;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
//import androidx.test.runner.AndroidJUnit4;

import org.desperu.mynews.Models.NYTTopStories.NYTTopStories;
import org.desperu.mynews.Utils.NYTStreams;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4ClassRunner.class)
public class TopStoriesFragmentTest {

    @Test
    public void fetchNYTTopStories() throws Exception {
        Observable<NYTTopStories> nytTopStoriesObservable = NYTStreams.streamFetchNYTTopStories("home");
        TestObserver<NYTTopStories> testObserver = new TestObserver<>();

        nytTopStoriesObservable.subscribeWith(testObserver)
                .assertNoErrors()
                .assertNoTimeout()
                .awaitTerminalEvent();

        NYTTopStories topStoriesFetched = testObserver.values().get(0);

        assertThat("Something was downloaded !", topStoriesFetched.getResults() != null);
    }
}
