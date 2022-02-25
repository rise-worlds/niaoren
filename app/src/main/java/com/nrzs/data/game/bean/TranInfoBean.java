package com.nrzs.data.game.bean;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class TranInfoBean implements Serializable {
    private String NickName;
    private String ShareContent;
    private int ShareTwitterID;
    private int TwitterID;
    private int UserID;

    public String getNickName() {
        return this.NickName;
    }

    public void setNickName(String str) {
        this.NickName = str;
    }

    public int getTwitterID() {
        return this.TwitterID;
    }

    public void setTwitterID(int i) {
        this.TwitterID = i;
    }

    public int getUserID() {
        return this.UserID;
    }

    public void setUserID(int i) {
        this.UserID = i;
    }

    public String getShareContent() {
        return this.ShareContent;
    }

    public void setShareContent(String str) {
        this.ShareContent = str;
    }

    public int getShareTwitterID() {
        return this.ShareTwitterID;
    }

    public void setShareTwitterID(int i) {
        this.ShareTwitterID = i;
    }
}
