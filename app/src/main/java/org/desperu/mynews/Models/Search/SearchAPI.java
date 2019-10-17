package org.desperu.mynews.Models.Search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchAPI {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("response")
    @Expose
    private SearchResponse response;

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public String getCopyright() { return copyright; }

    public void setCopyright(String copyright) { this.copyright = copyright; }

    public SearchResponse getResponse() { return response; }

    public void setResponse(SearchResponse response) { this.response = response; }
}
