package org.desperu.mynews.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MostPopularMediaMetadatumTest {

    private MostPopularMediaMetadatum mostPopularMediaMetadatum = new MostPopularMediaMetadatum();

    private String output;

    @Test
    public void Given_setUrl_When_getJson_Then_checkGetUrl() {
        String url = "www.an-url.org";
        mostPopularMediaMetadatum.setUrl(url);
        output = mostPopularMediaMetadatum.getUrl();

        assertEquals(url, output);
    }

    @Test
    public void Given_setFormat_When_getJson_Then_checkGetFormat() {
        String format = "A format";
        mostPopularMediaMetadatum.setFormat(format);
        output = mostPopularMediaMetadatum.getFormat();

        assertEquals(format, output);
    }

    @Test
    public void Given_setHeight_When_getJson_Then_checkGetHeight() {
        int height = 75;
        mostPopularMediaMetadatum.setHeight(height);
        int output = mostPopularMediaMetadatum.getHeight();

        assertEquals(height, output);
    }

    @Test
    public void Given_setWidth_When_getJson_Then_checkGetWidth() {
        int width = 75;
        mostPopularMediaMetadatum.setWidth(width);
        int output = mostPopularMediaMetadatum.getWidth();

        assertEquals(width, output);
    }
}
