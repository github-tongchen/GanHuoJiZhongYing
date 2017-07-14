package com.tongchen.ganhuojizhongying.model;

import org.litepal.crud.DataSupport;

/**
 * Created by TongChen on 2017/7/13.
 * <p>
 * Description:
 */

public class SkinModel extends DataSupport {

    private String name;
    private int imgId;
    private boolean used;
    private String skinName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(int used) {
        if (used == 1) {
            this.used = true;
        } else {
            this.used = false;
        }
    }

    public String getSkinName() {
        return skinName;
    }

    public void setSkinName(String skinName) {
        this.skinName = skinName;
    }
}
