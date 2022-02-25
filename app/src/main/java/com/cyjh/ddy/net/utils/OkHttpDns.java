package com.cyjh.ddy.net.utils;

import android.content.Context;
import android.text.TextUtils;
import com.cyjh.ddy.base.p033a.NoProGuard;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.net.inf.CustomDns;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import okhttp3.Dns;

/* loaded from: classes.dex */
public class OkHttpDns implements NoProGuard, Dns {

    /* renamed from: a */
    private static OkHttpDns f7532a;

    /* renamed from: b */
    private Context f7533b;

    /* renamed from: c */
    private CustomDns f7534c;

    private OkHttpDns(Context context) {
        this.f7533b = context;
    }

    public static OkHttpDns getInstance(Context context) {
        if (f7532a == null) {
            f7532a = new OkHttpDns(context);
        }
        return f7532a;
    }

    @Override // okhttp3.Dns
    public List<InetAddress> lookup(String str) throws UnknownHostException {
        CustomDns customDns = this.f7534c;
        if (customDns != null) {
            String ipByHost = customDns.getIpByHost(str);
            if (!TextUtils.isEmpty(ipByHost)) {
                List<InetAddress> asList = Arrays.asList(InetAddress.getAllByName(ipByHost));
                CLog.m21882i("OkHttpDns", "inetAddresses:" + asList);
                return asList;
            }
        }
        return Dns.SYSTEM.lookup(str);
    }

    public void setCustomDns(CustomDns customDns) {
        this.f7534c = customDns;
    }

    public void setPreResolveHosts(ArrayList<String> arrayList) {
        CustomDns customDns = this.f7534c;
        if (customDns != null) {
            customDns.setPreResolveHosts(arrayList);
        }
    }
}
