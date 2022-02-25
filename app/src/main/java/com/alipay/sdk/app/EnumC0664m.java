package com.alipay.sdk.app;

import p110z1.MyToast;

/* renamed from: com.alipay.sdk.app.m */
/* loaded from: classes.dex */
public enum EnumC0664m {
    SUCCEEDED(C0650b.f298a, "处理成功"),
    FAILED(C0650b.f301d, "系统繁忙，请稍后再试"),
    CANCELED(6001, "用户取消"),
    NETWORK_ERROR(6002, "网络连接异常"),
    PARAMS_ERROR(C0650b.f300c, "参数错误"),
    DOUBLE_REQUEST(5000, "重复请求"),
    PAY_WAITTING(MyToast.f17148g, "支付结果确认中");
    

    /* renamed from: h */
    private int f342h;

    /* renamed from: i */
    private String f343i;

    EnumC0664m(int i, String str) {
        this.f342h = i;
        this.f343i = str;
    }

    /* renamed from: a */
    public void m25278a(int i) {
        this.f342h = i;
    }

    /* renamed from: a */
    public int m25279a() {
        return this.f342h;
    }

    /* renamed from: a */
    public void m25277a(String str) {
        this.f343i = str;
    }

    /* renamed from: b */
    public String m25276b() {
        return this.f343i;
    }

    /* renamed from: b */
    public static EnumC0664m m25275b(int i) {
        if (i == 4001) {
            return PARAMS_ERROR;
        }
        if (i == 5000) {
            return DOUBLE_REQUEST;
        }
        if (i == 8000) {
            return PAY_WAITTING;
        }
        if (i == 9000) {
            return SUCCEEDED;
        }
        switch (i) {
            case 6001:
                return CANCELED;
            case 6002:
                return NETWORK_ERROR;
            default:
                return FAILED;
        }
    }
}
