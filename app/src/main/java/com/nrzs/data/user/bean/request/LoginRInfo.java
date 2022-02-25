package com.nrzs.data.user.bean.request;

import p110z1.apb;

/* loaded from: classes2.dex */
public class LoginRInfo extends LoginRequest2 {
    public int LoginType;
    public String PassWord;
    public String UserName;
    public String uuid;

    public void setPassWord(String str) {
        this.PassWord = apb.m11874a(str);
    }
}
