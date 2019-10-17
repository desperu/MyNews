package org.desperu.mynews.Models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NyTimesResultsTest {

    private NyTimesResults nyTimesResults = new NyTimesResults();

    private String output;

    @Test
    public void Given_setSection_When_getJson_Then_checkGetSection() {
        String section = "Section";
        nyTimesResults.setSection(section);
        output = nyTimesResults.getSection();

        assertEquals(section, output);
    }

    @Test
    public void Given_setSubSection_When_getJson_Then_checkGetSubsection() {
        String subSection = "Subsection";
        nyTimesResults.setSubsection(subSection);
        output = nyTimesResults.getSubsection();

        assertEquals(subSection, output);
    }

    @Test
    public void Given_setTitle_When_getJson_Then_checkGetTitle() {
        String title = "Title";
        nyTimesResults.setTitle(title);
        output = nyTimesResults.getTitle();

        assertEquals(title, output);
    }

    @Test
    public void Given_setHeadline_When_getJson_Then_checkGetHeadline() {
        Headline headline = new Headline();
        nyTimesResults.setHeadline(headline);
        Headline output = nyTimesResults.getHeadline();

        assertEquals(headline, output);
    }

    @Test
    public void Given_setAbstract_When_getJson_Then_checkGetAbstract() {
        String anAbstract = "Article description";
        nyTimesResults.setAbstract(anAbstract);
        output = nyTimesResults.getAbstract();

        assertEquals(anAbstract, output);
    }

    @Test
    public void Given_setUrl_When_getJson_Then_checkGetUrl() {
        String url = "www.an-url.org";
        nyTimesResults.setUrl(url);
        output = nyTimesResults.getUrl();

        assertEquals(url, output);
    }

    @Test
    public void Given_setItemType_When_getJson_Then_checkGetItemType() {
        String itemType = "Item Type";
        nyTimesResults.setItemType(itemType);
        output = nyTimesResults.getItemType();

        assertEquals(itemType, output);
    }

    @Test
    public void Given_setUpdatedDate_When_getJson_Then_checkGetUpdatedDate() {
        String updatedDate = "2019/10/10";
        nyTimesResults.setUpdatedDate(updatedDate);
        output = nyTimesResults.getUpdatedDate();

        assertEquals(updatedDate, output);
    }

    @Test
    public void Given_setCreatedDate_When_getJson_Then_checkGetCreatedDate() {
        String createdDate = "2019/10/08";
        nyTimesResults.setCreatedDate(createdDate);
        output = nyTimesResults.getCreatedDate();

        assertEquals(createdDate, output);
    }

    @Test
    public void Given_setPublishedDate_When_getJson_Then_checkGetPublishedDate() {
        String publishedDate = "2019/10/09";
        nyTimesResults.setPublishedDate(publishedDate);
        output = nyTimesResults.getPublishedDate();

        assertEquals(publishedDate, output);
    }

    @Test
    public void Given_setMaterialTypeFacet_When_getJson_Then_checkGetMaterialTypeFacet() {
        String materialTypeFacet = "Material Type";
        nyTimesResults.setMaterialTypeFacet(materialTypeFacet);
        output = nyTimesResults.getMaterialTypeFacet();

        assertEquals(materialTypeFacet, output);
    }

    @Test
    public void Given_setKicker_When_getJson_Then_checkGetKicker() {
        String kicker = "kicker";
        nyTimesResults.setKicker(kicker);
        output = nyTimesResults.getKicker();

        assertEquals(kicker, output);
    }

    @Test
    public void Given_setMultimedia_When_getJson_Then_checkGetMultimedia() {
        List<MultiMedium> multiMedia = new ArrayList<>();
        nyTimesResults.setMultimedia(multiMedia);
        List<MultiMedium> output = nyTimesResults.getMultimedia();

        assertEquals(multiMedia, output);
    }

    @Test
    public void Given_setMedia_When_getJson_Then_checkGetMedia() {
        List<MostPopularMedium> mostPopularMedia = new ArrayList<>();
        nyTimesResults.setMedia(mostPopularMedia);
        List<MostPopularMedium> output = nyTimesResults.getMedia();

        assertEquals(mostPopularMedia, output);
    }

    @Test
    public void Given_setShortUrl_When_getJson_Then_checkGetShortUrl() {
        String shortUrl = "www.a-shorturl.org";
        nyTimesResults.setShortUrl(shortUrl);
        output = nyTimesResults.getShortUrl();

        assertEquals(shortUrl, output);
    }
}