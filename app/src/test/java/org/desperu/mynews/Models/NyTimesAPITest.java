package org.desperu.mynews.Models;

import org.desperu.mynews.Models.Search.SearchResponse;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NyTimesAPITest {

    private NyTimesAPI nyTimesAPI = new NyTimesAPI();

    private String status = "OK";
    private String copyright = "Copyright rules";
    private int numResults = 33;
    private List<NyTimesResults> test = new ArrayList<>();
    private SearchResponse response = new SearchResponse();

    private String output;

    @Test
    public void Given_setStatus_When_getJson_Then_checkGetStatus() {
        nyTimesAPI.setStatus(status);
        output = nyTimesAPI.getStatus();

        assertEquals(status, output);
    }

    @Test
    public void Given_setCopyright_When_getJson_Then_checkGetCopyright() {
        nyTimesAPI.setCopyright(copyright);
        output = nyTimesAPI.getCopyright();

        assertEquals(copyright, output);
    }

    @Test
    public void Given_setNumResults_When_getJson_Then_checkNumResults() {
        nyTimesAPI.setNumResults(numResults);
        int output = nyTimesAPI.getNumResults();

        assertEquals(numResults, output);
    }

    @Test
    public void Given_setResults_When_getJson_Then_checkGetResults() {
        nyTimesAPI.setResults(test);
        List<NyTimesResults> output = nyTimesAPI.getResults();

        assertEquals(test, output);
    }

    @Test
    public void Given_setResponse_When_getJson_Then_checkGetResponse() {
        nyTimesAPI.setResponse(response);
        SearchResponse output = nyTimesAPI.getResponse();

        assertEquals(response, output);
    }
}