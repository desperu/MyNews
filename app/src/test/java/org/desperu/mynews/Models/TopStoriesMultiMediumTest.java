package org.desperu.mynews.Models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TopStoriesMultiMediumTest {

    private TopStoriesMultiMedium topStoriesMultiMedium = new TopStoriesMultiMedium();

    private String url = "www.an-url.org";
    private String format = "A format";
    private int height = 75;
    private int width = 75;
    private String type = "Image";
    private String subType = "Photo";
    private String caption = "A description";
    private String copyright = "Copyright rules";

    private String output;

    @Test
    public void Given_setUrl_When_getJson_Then_checkGetUrl() {
        topStoriesMultiMedium.setUrl(url);
        output = topStoriesMultiMedium.getUrl();

        assertEquals(url, output);
    }

    @Test
    public void Given_setFormat_When_getJson_Then_checkGetFormat() {
        topStoriesMultiMedium.setFormat(format);
        output = topStoriesMultiMedium.getFormat();

        assertEquals(format, output);
    }

    @Test
    public void Given_setHeight_When_getJson_Then_checkGetHeight() {
        topStoriesMultiMedium.setHeight(height);
        int output = topStoriesMultiMedium.getHeight();

        assertEquals(height, output);
    }

    @Test
    public void Given_setWidth_When_getJson_Then_checkGetWidth() {
        topStoriesMultiMedium.setWidth(width);
        int output = topStoriesMultiMedium.getWidth();

        assertEquals(width, output);
    }

    @Test
    public void Given_setType_When_getJson_Then_checkGetType() {
        topStoriesMultiMedium.setType(type);
        output = topStoriesMultiMedium.getType();

        assertEquals(type, output);
    }

    @Test
    public void Given_setSubType_When_getJson_Then_checkGetSubType() {
        topStoriesMultiMedium.setSubtype(subType);
        output = topStoriesMultiMedium.getSubtype();

        assertEquals(subType, output);
    }

    @Test
    public void Given_setCaption_When_getJson_Then_checkGetCaption() {
        topStoriesMultiMedium.setCaption(caption);
        output = topStoriesMultiMedium.getCaption();

        assertEquals(caption, output);
    }

    @Test
    public void Given_setCopyright_When_getJson_Then_checkGetCopyright() {
        topStoriesMultiMedium.setCopyright(copyright);
        output = topStoriesMultiMedium.getCopyright();

        assertEquals(copyright, output);
    }
}