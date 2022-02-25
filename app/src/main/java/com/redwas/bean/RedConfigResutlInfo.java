package com.redwas.bean;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class RedConfigResutlInfo implements Serializable {
    private String AndroidVersion;
    private RedConfigResutlInfoItem ConfigInfo;
    private String WeChatVersion;

    public String getAndroidVersion() {
        return this.AndroidVersion;
    }

    public void setAndroidVersion(String str) {
        this.AndroidVersion = str;
    }

    public String getWeChatVersion() {
        return this.WeChatVersion;
    }

    public void setWeChatVersion(String str) {
        this.WeChatVersion = str;
    }

    public RedConfigResutlInfoItem getConfigInfo() {
        return this.ConfigInfo;
    }

    public void setConfigInfo(RedConfigResutlInfoItem redConfigResutlInfoItem) {
        this.ConfigInfo = redConfigResutlInfoItem;
    }
}
