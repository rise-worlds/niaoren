package com.angel.nrzs.bean;

import com.nrzs.data.base.BaseRequest;

/* loaded from: classes.dex */
public class UrlBaseRequest extends BaseRequest {
    private String UserName;
    private int payBusType = 1;
    private long userId;

    public int getPayBusType() {
        return this.payBusType;
    }

    public void setPayBusType(int i) {
        this.payBusType = i;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long j) {
        this.userId = j;
    }

    public String getUserName() {
        return this.UserName;
    }

    public void setUserName(String str) {
        this.UserName = str;
    }
}
