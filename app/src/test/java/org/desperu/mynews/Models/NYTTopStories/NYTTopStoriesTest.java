package org.desperu.mynews.Models.NYTTopStories;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class NYTTopStoriesTest {

    private NYTTopStories nytTopStories = mock(NYTTopStories.class);
    private NYTTopStories nytTopStories1 = new NYTTopStories();
    private String status = "OK";
    private String copyright = "Copyright rules";
    private String section = "Section";
    private String lastUpdated = "20191010";
    private int numResults = 33;
    private List<TopStoriesResult> test = new ArrayList<>();
    private String output;

    @Test
    public void Given_setStatus_When_Then_checkGetStatus() {
//        when(nytTopStories.getStatus()).thenReturn(status);
        nytTopStories1.setStatus(status);
        output = nytTopStories1.getStatus();

        assertEquals(status, output);
    }

    @Test
    public void Given_setCopyright_When_Then_checkGetCopyright() {
        nytTopStories1.setCopyright(copyright);
        output = nytTopStories1.getCopyright();

        assertEquals(copyright, output);
    }

    @Test
    public void Given_setSection_When_Then_checkGetSection() {
        nytTopStories1.setSection(section);
        output = nytTopStories1.getSection();

        assertEquals(section, output);
    }

    @Test
    public void Given_setLastUpdated_When_Then_checkGetLastUpdated() {
        nytTopStories1.setLastUpdated(lastUpdated);
        output = nytTopStories1.getLastUpdated();

        assertEquals(lastUpdated, output);
    }

    @Test
    public void Given_setNumResults_When_Then_checkNumResults() {
        nytTopStories1.setNumResults(numResults);
        int output = nytTopStories1.getNumResults();

        assertEquals(numResults, output);
    }

    @Test
    public void Given_setResults_When_Then_checkGetResults() {
        nytTopStories1.setResults(test);
        List<TopStoriesResult> output = nytTopStories1.getResults();

        assertEquals(test, output);
    }
}
