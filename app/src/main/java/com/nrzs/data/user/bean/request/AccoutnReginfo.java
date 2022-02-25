package com.nrzs.data.user.bean.request;

import com.nrzs.data.base.BaseRequest;
import p110z1.apb;

/* loaded from: classes2.dex */
public class AccoutnReginfo extends BaseRequest {
    public String deviceCode;
    public String deviceModel;
    public String password;
    public String username;

    public void setPassWord(String str) {
        this.password = apb.m11874a(str);
    }
}
