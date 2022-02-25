package com.nrzs.data.user.bean.request;

import com.nrzs.data.base.BaseRequest;
import p110z1.apb;

/* loaded from: classes2.dex */
public class AlterPwinfmZh extends BaseRequest {
    public String OldPassWord;
    public String PassWord;
    public String UserName;

    public void setPassWord(String str) {
        this.PassWord = apb.m11874a(str);
    }

    public void setOldPassWord(String str) {
        this.OldPassWord = apb.m11874a(str);
    }
}
