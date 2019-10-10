package org.desperu.mynews.Models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MostPopularMediumTest {

    private MostPopularMedium mostPopularMedium = new MostPopularMedium();

    private String type = "Image";
    private String subType = "Photo";
    private String caption = "A description";
    private String copyright = "Copyright rules";
    private int approvedForSyndication = 1;
    private List<MostPopularMediaMetadatum> mostPopularMediaMetadata = new ArrayList<>();

    private String output;

    @Test
    public void Given_setType_When_getJson_Then_checkGetType() {
        mostPopularMedium.setType(type);
        output = mostPopularMedium.getType();

        assertEquals(type, output);
    }

    @Test
    public void Given_setSubType_When_getJson_Then_checkGetSubType() {
        mostPopularMedium.setSubtype(subType);
        output = mostPopularMedium.getSubtype();

        assertEquals(subType, output);
    }

    @Test
    public void Given_setCaption_When_getJson_Then_checkGetCaption() {
        mostPopularMedium.setCaption(caption);
        output = mostPopularMedium.getCaption();

        assertEquals(caption, output);
    }

    @Test
    public void Given_setCopyright_When_getJson_Then_checkGetCopyright() {
        mostPopularMedium.setCopyright(copyright);
        output = mostPopularMedium.getCopyright();

        assertEquals(copyright, output);
    }

    @Test
    public void Given_setApprovedForSyndication_When_getJson_Then_checkGetApprovedForSyndication() {
        mostPopularMedium.setApprovedForSyndication(approvedForSyndication);
        int output = mostPopularMedium.getApprovedForSyndication();

        assertEquals(approvedForSyndication, output);
    }

    @Test
    public void Given_setMediaMetadata_When_getJson_Then_checkGetMediaMetadata() {
        mostPopularMedium.setMediaMetadata(mostPopularMediaMetadata);
        List<MostPopularMediaMetadatum> output = mostPopularMedium.getMediaMetadata();

        assertEquals(mostPopularMediaMetadata, output);
    }
}
