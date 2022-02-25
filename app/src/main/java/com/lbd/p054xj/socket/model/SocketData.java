package com.lbd.p054xj.socket.model;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import p110z1.GsonUtil;

/* renamed from: com.lbd.xj.socket.model.SocketData */
/* loaded from: classes.dex */
public class SocketData<T> {
    public String data;
    public int length;
    public T mData;
    public String type;

    public SocketData(String str, String str2) {
        this.type = str;
        this.data = str2;
        this.length = TextUtils.isEmpty(str2) ? 0 : str2.length();
    }

    public void initDataBean() {
        Class classWithKey;
        if (this.mData == null && !TextUtils.isEmpty(this.data) && (classWithKey = SocketClassType.getClassWithKey(this.type)) != null && !classWithKey.getName().equals(String.class.getName())) {
            this.mData = (T) GsonUtil.m13967a(this.data, classWithKey);
        }
    }

    @NonNull
    public String toString() {
        return GsonUtil.m13969a(this);
    }
}
