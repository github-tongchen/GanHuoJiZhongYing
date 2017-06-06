
package com.tongchen.ganhuojizhongying.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 拓展资源版块
 */
public class Resource {

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
