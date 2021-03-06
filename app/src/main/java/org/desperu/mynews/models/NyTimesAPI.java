package org.desperu.mynews.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NyTimesAPI {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("num_results")
    @Expose
    private Integer numResults;
    @SerializedName(value = "results", alternate = "docs")
    @Expose
    private List<NyTimesResults> results = null;
    @SerializedName("response")
    @Expose
    private NyTimesAPI response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Integer getNumResults() {
        return numResults;
    }

    public void setNumResults(Integer numResults) { this.numResults = numResults; }

    public List<NyTimesResults> getResults() { return results; }

    public void setResults(List<NyTimesResults> results) {
        this.results = results;
    }

    public NyTimesAPI getResponse() { return response; }

    public void setResponse(NyTimesAPI response) { this.response = response; }
}
