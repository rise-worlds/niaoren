package com.nrzs.data.game.bean;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class UserListBean implements Serializable {
    private String AuthorTitle;
    private String HeadImgPath;
    private String Ico;
    private int IfAuthentic;
    private String NickName;
    private int UserID;

    public int getUserID() {
        return this.UserID;
    }

    public void setUserID(int i) {
        this.UserID = i;
    }

    public String getNickName() {
        return this.NickName;
    }

    public void setNickName(String str) {
        this.NickName = str;
    }

    public String getHeadImgPath() {
        return this.HeadImgPath;
    }

    public void setHeadImgPath(String str) {
        this.HeadImgPath = str;
    }

    public String getAuthorTitle() {
        return this.AuthorTitle;
    }

    public void setAuthorTitle(String str) {
        this.AuthorTitle = str;
    }

    public String getIco() {
        return this.Ico;
    }

    public void setIco(String str) {
        this.Ico = str;
    }

    public int getIfAuthentic() {
        return this.IfAuthentic;
    }

    public void setIfAuthentic(int i) {
        this.IfAuthentic = i;
    }
}
