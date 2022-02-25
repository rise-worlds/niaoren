package com.lbd.p054xj.socket.model;

import java.util.List;

/* renamed from: com.lbd.xj.socket.model.MessgeBean */
/* loaded from: classes.dex */
public class MessgeBean {
    private int code;
    private List data;
    private String msg;

    public int getCode() {
        return this.code;
    }

    public List getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(List list) {
        this.data = list;
    }

    public void setMsg(String str) {
        this.msg = str;
    }
}
