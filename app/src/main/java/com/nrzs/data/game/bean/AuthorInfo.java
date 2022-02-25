package com.nrzs.data.game.bean;

/* loaded from: classes2.dex */
public class AuthorInfo {
    private String AuthorHeadImg;
    private long AuthorId;
    private String AuthorNotice;
    private String NickName;
    private String RewardSGBTotalNum;
    private int ScriptNum;

    public void setAuthorHeadImg(String str) {
    }

    public long getAuthorId() {
        return this.AuthorId;
    }

    public void setAuthorId(long j) {
        this.AuthorId = j;
    }

    public String getNickName() {
        return this.NickName;
    }

    public void setNickName(String str) {
        this.NickName = str;
    }

    public String getAuthorNotice() {
        return this.AuthorNotice;
    }

    public void setAuthorNotice(String str) {
        this.AuthorNotice = str;
    }

    public String getRewardSGBTotalNum() {
        return this.RewardSGBTotalNum;
    }

    public void setRewardSGBTotalNum(String str) {
        this.RewardSGBTotalNum = str;
    }

    public int getScriptNum() {
        return this.ScriptNum;
    }

    public void setScriptNum(int i) {
        this.ScriptNum = i;
    }

    public String getAuthorHeadImg() {
        return this.AuthorHeadImg;
    }

    public String toString() {
        return "AuthorInfo{AuthorId=" + this.AuthorId + ", NicKName='" + this.NickName + "', AuthorNotice='" + this.AuthorNotice + "', RewardSGBTotalNum='" + this.RewardSGBTotalNum + "', ScriptNum=" + this.ScriptNum + ", AuthorHeadImg=" + this.AuthorHeadImg + '}';
    }
}
