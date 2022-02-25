package com.nrzs.data.other.bean.request;

import com.nrzs.data.base.BaseRequest;

/* loaded from: classes2.dex */
public class EventCollectRequestInfo extends BaseRequest {
    private String Data;
    private long UserId;
    private String appCode;
    private String appKey;

    public String getData() {
        return this.Data;
    }

    public void setData(String str) {
        this.Data = str;
    }

    public String getAppCode() {
        return this.appCode;
    }

    public void setAppCode(String str) {
        this.appCode = str;
    }

    public String getAppKey() {
        return this.appKey;
    }

    public void setAppKey(String str) {
        this.appKey = str;
    }

    public long getUserId() {
        return this.UserId;
    }

    public void setUserId(long j) {
        this.UserId = j;
    }
}
