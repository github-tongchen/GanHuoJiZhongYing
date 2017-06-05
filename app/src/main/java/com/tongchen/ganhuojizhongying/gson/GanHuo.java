
package com.tongchen.ganhuojizhongying.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GanHuo {

    @SerializedName("category")
    private List<String> mCategory;
    @SerializedName("error")
    private Boolean mError;
    @SerializedName("results")
    private Results mResults;

    public List<String> getCategory() {
        return mCategory;
    }

    public void setCategory(List<String> category) {
        mCategory = category;
    }

    public Boolean getError() {
        return mError;
    }

    public void setError(Boolean error) {
        mError = error;
    }

    public Results getResults() {
        return mResults;
    }

    public void setResults(Results results) {
        mResults = results;
    }

}
