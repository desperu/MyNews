package org.desperu.mynews.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NyTimesResults {

    @SerializedName(value = "section", alternate = "section_name")
    @Expose
    private String section;
    @SerializedName(value = "subsection", alternate = "subsection_name")
    @Expose
    private String subsection;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("headline")
    @Expose
    private Headline headline;
    @SerializedName("abstract")
    @Expose
    private String _abstract;
    @SerializedName(value = "url", alternate = "web_url")
    @Expose
    private String url;
    @SerializedName("item_type")
    @Expose
    private String itemType;
    @SerializedName("updated_date")
    @Expose
    private String updatedDate;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName(value = "published_date", alternate = "pub_date")
    @Expose
    private String publishedDate;
    @SerializedName("material_type_facet")
    @Expose
    private String materialTypeFacet;
    @SerializedName("kicker")
    @Expose
    private String kicker;
    @SerializedName("multimedia")
    @Expose
    private List<MultiMedium> multimedia = null;
    @SerializedName("media")
    @Expose
    private List<MostPopularMedium> media = null;
    @SerializedName("short_url")
    @Expose
    private String shortUrl;

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Headline getHeadline() {
        return headline;
    }

    public void setHeadline(Headline headline) {
        this.headline = headline;
    }

    public String getAbstract() {
        return _abstract;
    }

    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getMaterialTypeFacet() {
        return materialTypeFacet;
    }

    public void setMaterialTypeFacet(String materialTypeFacet) { this.materialTypeFacet = materialTypeFacet; }

    public String getKicker() {
        return kicker;
    }

    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

    public List<MultiMedium> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<MultiMedium> multimedia) { this.multimedia = multimedia; }

    public List<MostPopularMedium> getMedia() {
        return media;
    }

    public void setMedia(List<MostPopularMedium> media) {
        this.media = media;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) { this.shortUrl = shortUrl; }

}