package org.desperu.mynews.models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NyTimesAPITest {

    private NyTimesAPI nyTimesAPI = new NyTimesAPI();

    private String output;

    @Test
    public void Given_setStatus_When_getJson_Then_checkGetStatus() {
        String status = "OK";
        nyTimesAPI.setStatus(status);
        output = nyTimesAPI.getStatus();

        assertEquals(status, output);
    }

    @Test
    public void Given_setCopyright_When_getJson_Then_checkGetCopyright() {
        String copyright = "Copyright rules";
        nyTimesAPI.setCopyright(copyright);
        output = nyTimesAPI.getCopyright();

        assertEquals(copyright, output);
    }

    @Test
    public void Given_setNumResults_When_getJson_Then_checkNumResults() {
        int numResults = 33;
        nyTimesAPI.setNumResults(numResults);
        int output = nyTimesAPI.getNumResults();

        assertEquals(numResults, output);
    }

    @Test
    public void Given_setResults_When_getJson_Then_checkGetResults() {
        List<NyTimesResults> test = new ArrayList<>();
        nyTimesAPI.setResults(test);
        List<NyTimesResults> output = nyTimesAPI.getResults();

        assertEquals(test, output);
    }

    @Test
    public void Given_setResponse_When_getJson_Then_checkGetResponse() {
        NyTimesAPI response = new NyTimesAPI();
        nyTimesAPI.setResponse(response);
        NyTimesAPI output = nyTimesAPI.getResponse();

        assertEquals(response, output);
    }
}