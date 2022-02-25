package com.nrzs.data.user.bean.request;

import com.nrzs.data.base.BaseRequest;
import p110z1.apb;

/* loaded from: classes2.dex */
public class AlterPwinfm extends BaseRequest {
    public String CheckCode;
    public String PassWord;
    public String Tel;

    public void setPassWord(String str) {
        this.PassWord = apb.m11874a(str);
    }
}
