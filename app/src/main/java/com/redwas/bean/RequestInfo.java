package com.redwas.bean;

import com.nrzs.data.base.BaseRequest;

/* loaded from: classes2.dex */
public class RequestInfo extends BaseRequest {
    private int AndroidVersion;
    private String WeChatVersion;

    public int getAndroidVersion() {
        return this.AndroidVersion;
    }

    public void setAndroidVersion(int i) {
        this.AndroidVersion = i;
    }

    public String getWeChatVersion() {
        return this.WeChatVersion;
    }

    public void setWeChatVersion(String str) {
        this.WeChatVersion = str;
    }
}
