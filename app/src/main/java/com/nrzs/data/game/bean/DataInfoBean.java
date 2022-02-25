package com.nrzs.data.game.bean;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class DataInfoBean implements Serializable {
    private String AdName;
    private int AdPosition;
    private int AdPriority;
    private int AdType;
    private String Appid;
    private String BigImg;
    private String CreateTime;
    private String EffectiveTime;
    private String ExecArgs;
    private String ExecCommand;
    private String FailureTime;

    /* renamed from: Id */
    private int f10644Id;
    private String ImgUrl;
    private int SamePositionOrder;
    private String SubTitle;
    private String Title;
    private int UserType;

    public int getId() {
        return this.f10644Id;
    }

    public void setId(int i) {
        this.f10644Id = i;
    }

    public String getAppid() {
        return this.Appid;
    }

    public void setAppid(String str) {
        this.Appid = str;
    }

    public String getAdName() {
        return this.AdName;
    }

    public void setAdName(String str) {
        this.AdName = str;
    }

    public String getImgUrl() {
        return this.ImgUrl;
    }

    public void setImgUrl(String str) {
        this.ImgUrl = str;
    }

    public String getBigImg() {
        return this.BigImg;
    }

    public void setBigImg(String str) {
        this.BigImg = str;
    }

    public String getTitle() {
        return this.Title;
    }

    public void setTitle(String str) {
        this.Title = str;
    }

    public String getSubTitle() {
        return this.SubTitle;
    }

    public void setSubTitle(String str) {
        this.SubTitle = str;
    }

    public String getExecCommand() {
        return this.ExecCommand;
    }

    public void setExecCommand(String str) {
        this.ExecCommand = str;
    }

    public String getExecArgs() {
        return this.ExecArgs;
    }

    public void setExecArgs(String str) {
        this.ExecArgs = str;
    }

    public String getEffectiveTime() {
        return this.EffectiveTime;
    }

    public void setEffectiveTime(String str) {
        this.EffectiveTime = str;
    }

    public String getFailureTime() {
        return this.FailureTime;
    }

    public void setFailureTime(String str) {
        this.FailureTime = str;
    }

    public String getCreateTime() {
        return this.CreateTime;
    }

    public void setCreateTime(String str) {
        this.CreateTime = str;
    }

    public int getAdPosition() {
        return this.AdPosition;
    }

    public void setAdPosition(int i) {
        this.AdPosition = i;
    }

    public int getAdPriority() {
        return this.AdPriority;
    }

    public void setAdPriority(int i) {
        this.AdPriority = i;
    }

    public int getSamePositionOrder() {
        return this.SamePositionOrder;
    }

    public void setSamePositionOrder(int i) {
        this.SamePositionOrder = i;
    }

    public int getUserType() {
        return this.UserType;
    }

    public void setUserType(int i) {
        this.UserType = i;
    }

    public int getAdType() {
        return this.AdType;
    }

    public void setAdType(int i) {
        this.AdType = i;
    }
}
