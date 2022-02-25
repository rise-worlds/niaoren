package com.nrzs.data.game.bean;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class TopicListBean implements Serializable {
    private String ImgPath;
    private int ScriptCount;
    private int SportBackGround;
    private int TopicID;
    private String TopicName;
    private int VAScriptCount;

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

    public String getImgPath() {
        return this.ImgPath;
    }

    public void setImgPath(String str) {
        this.ImgPath = str;
    }

    public int getVAScriptCount() {
        return this.VAScriptCount;
    }

    public void setVAScriptCount(int i) {
        this.VAScriptCount = i;
    }

    public int getScriptCount() {
        return this.ScriptCount;
    }

    public void setScriptCount(int i) {
        this.ScriptCount = i;
    }

    public int getSportBackGround() {
        return this.SportBackGround;
    }

    public void setSportBackGround(int i) {
        this.SportBackGround = i;
    }
}
