package com.nrzs.http;

/* compiled from: ApiException.java */
/* renamed from: com.nrzs.http.b */
/* loaded from: classes2.dex */
public class C2163b extends Exception {
    private int code;
    private String msg;

    public C2163b(Throwable th, int i) {
        super(th);
        this.code = i;
    }

    public C2163b(int i, String str) {
        this.code = i;
        this.msg = str;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }
}
