package com.lbd.p054xj.socket.model;

import java.util.Map;

/* renamed from: com.lbd.xj.socket.model.MessageMapBean */
/* loaded from: classes.dex */
public class MessageMapBean {
    private int code;
    private Map data;
    private String msg;

    public int getCode() {
        return this.code;
    }

    public Map getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(Map map) {
        this.data = map;
    }

    public void setMsg(String str) {
        this.msg = str;
    }
}
