package org.desperu.mynews.Models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HeadlineTest {

    private Headline headline = new Headline();

    private String output;
    private Object outputObject;

    @Test
    public void Given_setMain_When_getJson_Then_checkGetMain() {
        String main = "main";
        headline.setMain(main);
        output = headline.getMain();

        assertEquals(main, output);
    }

    @Test
    public void Given_setKicker_When_getJson_Then_checkGetKicker() {
        Object kicker = new Object();
        headline.setKicker(kicker);
        outputObject = headline.getKicker();

        assertEquals(kicker, outputObject);
    }

    @Test
    public void Given_setContentKicker_When_getJson_Then_checkGetContentKicker() {
        Object contentKicker = new Object();
        headline.setContentKicker(contentKicker);
        outputObject = headline.getContentKicker();

        assertEquals(contentKicker, outputObject);
    }

    @Test
    public void Given_setPrintHeadline_When_getJson_Then_checkGetPrintHeadline() {
        String printHeadline = "printHeadline";
        headline.setPrintHeadline(printHeadline);
        output = headline.getPrintHeadline();

        assertEquals(printHeadline, output);
    }

    @Test
    public void Given_setName_When_getJson_Then_checkGetName() {
        Object name = new Object();
        headline.setName(name);
        outputObject = headline.getName();

        assertEquals(name, outputObject);
    }

    @Test
    public void Given_setSeo_When_getJson_Then_checkGetSeo() {
        Object seo = new Object();
        headline.setSeo(seo);
        outputObject = headline.getSeo();

        assertEquals(seo, outputObject);
    }

    @Test
    public void Given_setSub_When_getJson_Then_checkGetSub() {
        Object sub = new Object();
        headline.setSub(sub);
        outputObject = headline.getSub();

        assertEquals(sub, outputObject);
    }
}
