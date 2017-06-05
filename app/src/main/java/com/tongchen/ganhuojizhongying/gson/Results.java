
package com.tongchen.ganhuojizhongying.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Results {

    @SerializedName("Android")
    private List<Android> mAndroid;
    @SerializedName("iOS")
    private List<IOS> mIOS;
    @SerializedName("Recommend")
    private List<Recommend> mRecommend;
    @SerializedName("Resource")
    private List<Resource> mResource;
    @SerializedName("Video")
    private List<Video> mVideo;
    @SerializedName("Welfare")
    private List<Welfare> mWelfare;

    public List<Android> getAndroid() {
        return mAndroid;
    }

    public void setAndroid(List<Android> Android) {
        mAndroid = Android;
    }

    public List<IOS> getIOS() {
        return mIOS;
    }

    public void setIOS(List<IOS> iOS) {
        mIOS = iOS;
    }

    public List<Recommend> getRecommend() {
        return mRecommend;
    }

    public void setRecommend(List<Recommend> Recommend) {
        mRecommend = Recommend;
    }

    public List<Resource> getResource() {
        return mResource;
    }

    public void setResource(List<Resource> Resource) {
        mResource = Resource;
    }

    public List<Video> getVideo() {
        return mVideo;
    }

    public void setVideo(List<Video> Video) {
        mVideo = Video;
    }

    public List<Welfare> getWelfare() {
        return mWelfare;
    }

    public void setWelfare(List<Welfare> Welfare) {
        mWelfare = Welfare;
    }

}
