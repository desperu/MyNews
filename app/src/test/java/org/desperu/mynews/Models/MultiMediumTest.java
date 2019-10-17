package org.desperu.mynews.Models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MultiMediumTest {

    private MultiMedium multiMedium = new MultiMedium();

    private String output;

    @Test
    public void Given_setUrl_When_getJson_Then_checkGetUrl() {
        String url = "www.an-url.org";
        multiMedium.setUrl(url);
        output = multiMedium.getUrl();

        assertEquals(url, output);
    }

    @Test
    public void Given_setFormat_When_getJson_Then_checkGetFormat() {
        String format = "A format";
        multiMedium.setFormat(format);
        output = multiMedium.getFormat();

        assertEquals(format, output);
    }

    @Test
    public void Given_setHeight_When_getJson_Then_checkGetHeight() {
        int height = 75;
        multiMedium.setHeight(height);
        int output = multiMedium.getHeight();

        assertEquals(height, output);
    }

    @Test
    public void Given_setWidth_When_getJson_Then_checkGetWidth() {
        int width = 75;
        multiMedium.setWidth(width);
        int output = multiMedium.getWidth();

        assertEquals(width, output);
    }

    @Test
    public void Given_setType_When_getJson_Then_checkGetType() {
        String type = "Image";
        multiMedium.setType(type);
        output = multiMedium.getType();

        assertEquals(type, output);
    }

    @Test
    public void Given_setSubType_When_getJson_Then_checkGetSubType() {
        String subType = "Photo";
        multiMedium.setSubtype(subType);
        output = multiMedium.getSubtype();

        assertEquals(subType, output);
    }

    @Test
    public void Given_setCaption_When_getJson_Then_checkGetCaption() {
        String caption = "A description";
        multiMedium.setCaption(caption);
        output = multiMedium.getCaption();

        assertEquals(caption, output);
    }

    @Test
    public void Given_setCopyright_When_getJson_Then_checkGetCopyright() {
        String copyright = "Copyright rules";
        multiMedium.setCopyright(copyright);
        output = multiMedium.getCopyright();

        assertEquals(copyright, output);
    }
}