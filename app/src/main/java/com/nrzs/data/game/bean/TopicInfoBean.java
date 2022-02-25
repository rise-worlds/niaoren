package com.nrzs.data.game.bean;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class TopicInfoBean implements Serializable {
    private String BeatityAdImg;
    private BeautityInfoBean BeautityInfo;
    private String BgImg;
    private String ImgPath;
    private String Package;
    private int ScriptNum;
    private String TopicDesc;
    private int TopicID;
    private String TopicName;

    public int getTopicID() {
        return this.TopicID;
    }

    public void setTopicID(int i) {
        this.TopicID = i;
    }

    public String getTopicName() {
        return this.TopicName;
    }

    public void setTopicName(String str) {
        this.TopicName = str;
    }

    public String getPackage() {
        return this.Package;
    }

    public void setPackage(String str) {
        this.Package = str;
    }

    public String getImgPath() {
        return this.ImgPath;
    }

    public void setImgPath(String str) {
        this.ImgPath = str;
    }

    public String getBgImg() {
        return this.BgImg;
    }

    public void setBgImg(String str) {
        this.BgImg = str;
    }

    public int getScriptNum() {
        return this.ScriptNum;
    }

    public void setScriptNum(int i) {
        this.ScriptNum = i;
    }

    public String getBeatityAdImg() {
        return this.BeatityAdImg;
    }

    public void setBeatityAdImg(String str) {
        this.BeatityAdImg = str;
    }

    public String getTopicDesc() {
        return this.TopicDesc;
    }

    public void setTopicDesc(String str) {
        this.TopicDesc = str;
    }

    public BeautityInfoBean getBeautityInfo() {
        return this.BeautityInfo;
    }

    public void setBeautityInfo(BeautityInfoBean beautityInfoBean) {
        this.BeautityInfo = beautityInfoBean;
    }
}
