package org.desperu.mynews.Models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MostPopularMediaMetadatumTest {

    private MostPopularMediaMetadatum mostPopularMediaMetadatum = new MostPopularMediaMetadatum();

    private String url = "www.an-url.org";
    private String format = "A format";
    private int height = 75;
    private int width = 75;

    private String output;

    @Test
    public void Given_setUrl_When_getJson_Then_checkGetUrl() {
        mostPopularMediaMetadatum.setUrl(url);
        output = mostPopularMediaMetadatum.getUrl();

        assertEquals(url, output);
    }

    @Test
    public void Given_setFormat_When_getJson_Then_checkGetFormat() {
        mostPopularMediaMetadatum.setFormat(format);
        output = mostPopularMediaMetadatum.getFormat();

        assertEquals(format, output);
    }

    @Test
    public void Given_setHeight_When_getJson_Then_checkGetHeight() {
        mostPopularMediaMetadatum.setHeight(height);
        int output = mostPopularMediaMetadatum.getHeight();

        assertEquals(height, output);
    }

    @Test
    public void Given_setWidth_When_getJson_Then_checkGetWidth() {
        mostPopularMediaMetadatum.setWidth(width);
        int output = mostPopularMediaMetadatum.getWidth();

        assertEquals(width, output);
    }
}
