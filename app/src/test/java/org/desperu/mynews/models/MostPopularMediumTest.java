package org.desperu.mynews.models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MostPopularMediumTest {

    private MostPopularMedium mostPopularMedium = new MostPopularMedium();

    private String output;

    @Test
    public void Given_setType_When_getJson_Then_checkGetType() {
        String type = "Image";
        mostPopularMedium.setType(type);
        output = mostPopularMedium.getType();

        assertEquals(type, output);
    }

    @Test
    public void Given_setSubType_When_getJson_Then_checkGetSubType() {
        String subType = "Photo";
        mostPopularMedium.setSubtype(subType);
        output = mostPopularMedium.getSubtype();

        assertEquals(subType, output);
    }

    @Test
    public void Given_setCaption_When_getJson_Then_checkGetCaption() {
        String caption = "A description";
        mostPopularMedium.setCaption(caption);
        output = mostPopularMedium.getCaption();

        assertEquals(caption, output);
    }

    @Test
    public void Given_setCopyright_When_getJson_Then_checkGetCopyright() {
        String copyright = "Copyright rules";
        mostPopularMedium.setCopyright(copyright);
        output = mostPopularMedium.getCopyright();

        assertEquals(copyright, output);
    }

    @Test
    public void Given_setApprovedForSyndication_When_getJson_Then_checkGetApprovedForSyndication() {
        int approvedForSyndication = 1;
        mostPopularMedium.setApprovedForSyndication(approvedForSyndication);
        int output = mostPopularMedium.getApprovedForSyndication();

        assertEquals(approvedForSyndication, output);
    }

    @Test
    public void Given_setMediaMetadata_When_getJson_Then_checkGetMediaMetadata() {
        List<MostPopularMediaMetadatum> mostPopularMediaMetadata = new ArrayList<>();
        mostPopularMedium.setMediaMetadata(mostPopularMediaMetadata);
        List<MostPopularMediaMetadatum> output = mostPopularMedium.getMediaMetadata();

        assertEquals(mostPopularMediaMetadata, output);
    }
}
