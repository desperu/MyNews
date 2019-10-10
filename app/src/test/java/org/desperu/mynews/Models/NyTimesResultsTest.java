package org.desperu.mynews.Models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NyTimesResultsTest {

    private NyTimesResults nyTimesResults = new NyTimesResults();

    private String section = "Section";
    private String subSection = "Subsection";
    private String title = "Title";
    private String Abstract = "Article description";
    private String url = "www.an-url.org";
    private String byline = "author";
    private String itemType = "Item Type";
    private String updatedDate = "2019/10/10";
    private String createdDate = "2019/10/08";
    private String publishedDate = "2019/10/09";
    private String materialTypeFacet = "Material Type";
    private String kicker = "kicker";
    private List<TopStoriesMultiMedium> topStoriesMultiMedia = new ArrayList<>();
    private List<MostPopularMedium> mostPopularMedia = new ArrayList<>();
    private String shortUrl = "www.a-shorturl.org";

    private String output;

    @Test
    public void Given_setSection_When_getJson_Then_checkGetSection() {
        nyTimesResults.setSection(section);
        output = nyTimesResults.getSection();

        assertEquals(section, output);
    }

    @Test
    public void Given_setSubSection_When_getJson_Then_checkGetSubsection() {
        nyTimesResults.setSubsection(subSection);
        output = nyTimesResults.getSubsection();

        assertEquals(subSection, output);
    }

    @Test
    public void Given_setTitle_When_getJson_Then_checkGetTitle() {
        nyTimesResults.setTitle(title);
        output = nyTimesResults.getTitle();

        assertEquals(title, output);
    }

    @Test
    public void Given_setAbstract_When_getJson_Then_checkGetAbstract() {
        nyTimesResults.setAbstract(Abstract);
        output = nyTimesResults.getAbstract();

        assertEquals(Abstract, output);
    }

    @Test
    public void Given_setUrl_When_getJson_Then_checkGetUrl() {
        nyTimesResults.setUrl(url);
        output = nyTimesResults.getUrl();

        assertEquals(url, output);
    }

    @Test
    public void Given_setByline_When_getJson_Then_checkGetByline() {
        nyTimesResults.setByline(byline);
        output = nyTimesResults.getByline();

        assertEquals(byline, output);
    }

    @Test
    public void Given_setItemType_When_getJson_Then_checkGetItemType() {
        nyTimesResults.setItemType(itemType);
        output = nyTimesResults.getItemType();

        assertEquals(itemType, output);
    }

    @Test
    public void Given_setUpdatedDate_When_getJson_Then_checkGetUpdatedDate() {
        nyTimesResults.setUpdatedDate(updatedDate);
        output = nyTimesResults.getUpdatedDate();

        assertEquals(updatedDate, output);
    }

    @Test
    public void Given_setCreatedDate_When_getJson_Then_checkGetCreatedDate() {
        nyTimesResults.setCreatedDate(createdDate);
        output = nyTimesResults.getCreatedDate();

        assertEquals(createdDate, output);
    }

    @Test
    public void Given_setPublishedDate_When_getJson_Then_checkGetPublishedDate() {
        nyTimesResults.setPublishedDate(publishedDate);
        output = nyTimesResults.getPublishedDate();

        assertEquals(publishedDate, output);
    }

    @Test
    public void Given_setMaterialTypeFacet_When_getJson_Then_checkGetMaterialTypeFacet() {
        nyTimesResults.setMaterialTypeFacet(materialTypeFacet);
        output = nyTimesResults.getMaterialTypeFacet();

        assertEquals(materialTypeFacet, output);
    }

    @Test
    public void Given_setKicker_When_getJson_Then_checkGetKicker() {
        nyTimesResults.setKicker(kicker);
        output = nyTimesResults.getKicker();

        assertEquals(kicker, output);
    }

    @Test
    public void Given_setMultimedia_When_getJson_Then_checkGetMultimedia() {
        nyTimesResults.setMultimedia(topStoriesMultiMedia);
        List<TopStoriesMultiMedium> output = nyTimesResults.getMultimedia();

        assertEquals(topStoriesMultiMedia, output);
    }

    @Test
    public void Given_setMedia_When_getJson_Then_checkGetMedia() {
        nyTimesResults.setMedia(mostPopularMedia);
        List<MostPopularMedium> output = nyTimesResults.getMedia();

        assertEquals(mostPopularMedia, output);
    }

    @Test
    public void Given_setShortUrl_When_getJson_Then_checkGetShortUrl() {
        nyTimesResults.setShortUrl(shortUrl);
        output = nyTimesResults.getShortUrl();

        assertEquals(shortUrl, output);
    }
}