
package com.tongchen.ganhuojizhongying.gson;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 其他的8个Model共用的版块
 */
public class Result implements Serializable{

    @SerializedName("createdAt")
    private String mCreatedAt;
    @SerializedName("desc")
    private String mDesc;

    //  Video和Welfare没有images字段，要做null处理
    @SerializedName("images")
    private List<String> mImages;

    @SerializedName("publishedAt")
    private String mPublishedAt;
    @SerializedName("source")
    private String mSource;
    @SerializedName("type")
    private String mType;
    @SerializedName("url")
    private String mUrl;
    @SerializedName("used")
    private Boolean mUsed;
    @SerializedName("who")
    private String mWho;
    @SerializedName("_id")
    private String mId;

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String desc) {
        mDesc = desc;
    }

    public List<String> getImages() {
        return mImages;
    }

    public void setImages(List<String> images) {
        mImages = images;
    }

    public String getPublishedAt() {
        return mPublishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        mPublishedAt = publishedAt;
    }

    public String getSource() {
        return mSource;
    }

    public void setSource(String source) {
        mSource = source;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public Boolean getUsed() {
        return mUsed;
    }

    public void setUsed(Boolean used) {
        mUsed = used;
    }

    public String getWho() {
        return mWho;
    }

    public void setWho(String who) {
        mWho = who;
    }

    public String getId() {
        return mId;
    }

    public void setId(String _id) {
        mId = _id;
    }

}

