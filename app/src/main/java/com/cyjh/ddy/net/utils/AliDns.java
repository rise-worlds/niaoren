package com.cyjh.ddy.net.utils;

import android.content.Context;
import com.alibaba.sdk.android.httpdns.HttpDns;
import com.alibaba.sdk.android.httpdns.HttpDnsService;
import com.cyjh.ddy.net.inf.CustomDns;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class AliDns implements CustomDns {

    /* renamed from: b */
    private static AliDns f7530b;

    /* renamed from: a */
    HttpDnsService f7531a;

    private AliDns(Context context) {
        this.f7531a = HttpDns.getService(context, "187642");
    }

    public static AliDns getInstance(Context context) {
        if (f7530b == null) {
            f7530b = new AliDns(context);
        }
        return f7530b;
    }

    @Override // com.cyjh.ddy.net.inf.CustomDns
    public void setPreResolveHosts(ArrayList<String> arrayList) {
        this.f7531a.setPreResolveHosts(arrayList);
    }

    @Override // com.cyjh.ddy.net.inf.CustomDns
    public String getIpByHost(String str) {
        return this.f7531a.getIpByHostAsync(str);
    }
}
