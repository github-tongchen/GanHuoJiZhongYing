
package com.tongchen.ganhuojizhongying.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * App版块
 */
public class App {

    @SerializedName("error")
    private Boolean mError;
    @SerializedName("results")
    private List<Result> mResults;

    public Boolean getError() {
        return mError;
    }

    public void setError(Boolean error) {
        mError = error;
    }

    public List<Result> getResults() {
        return mResults;
    }

    public void setResults(List<Result> results) {
        mResults = results;
    }

}
