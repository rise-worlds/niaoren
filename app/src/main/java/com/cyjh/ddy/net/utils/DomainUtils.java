package com.cyjh.ddy.net.utils;

import android.content.Context;
import android.text.TextUtils;
import com.cyjh.ddy.base.utils.SdkKeyUtil;
import com.cyjh.ddy.net.inf.CustomDns;
import java.net.URL;
import java.util.ArrayList;

/* renamed from: com.cyjh.ddy.net.utils.a */
/* loaded from: classes.dex */
public class DomainUtils {
    /* renamed from: d */
    public static String m21390d() {
        return "http://obs.ddyun.com/";
    }

    /* renamed from: a */
    public static void m21393a(boolean z) {
        String sdkType = SdkKeyUtil.getInstance().getSdkType();
        if (z) {
            if (sdkType.endsWith("_proxy")) {
                sdkType = sdkType.replace("_proxy", "_ipport");
            } else if (!sdkType.contains("_")) {
                sdkType = sdkType + "_proxy";
            }
        } else if (sdkType.contains("_")) {
            sdkType = sdkType.substring(0, sdkType.indexOf("_"));
        }
        SdkKeyUtil.getInstance().setSdkType(sdkType);
    }

    /* renamed from: a */
    public static String m21397a() {
        String sdkType = SdkKeyUtil.getInstance().getSdkType();
        return (sdkType.equals("yungame") || sdkType.contains("yungame_")) ? "http://gameapp.ddyun.com/" : (sdkType.equals("yungame2") || sdkType.contains("yungame2_")) ? sdkType.endsWith("_ipport") ? "http://124.70.152.179:8081/" : sdkType.endsWith("_proxy") ? "http://gapp.ddyun.com/" : "http://gapp1.ddyun.com/" : (sdkType.equals("yungame3") || sdkType.contains("yungame3_")) ? (sdkType.endsWith("_ipport") || sdkType.endsWith("_proxy")) ? "http://yybapp.ddyun123.com/" : "http://yybapp.ddyun.com/" : (sdkType.endsWith("_ipport") || sdkType.endsWith("_proxy")) ? "http://appcdn.ddyun.com/" : "http://app.ddyun.com/";
    }

    /* renamed from: b */
    public static String m21392b() {
        String sdkType = SdkKeyUtil.getInstance().getSdkType();
        return (sdkType.equals("yungame") || sdkType.contains("yungame_")) ? "http://ess.ddyun.com/" : (sdkType.equals("yungame2") || sdkType.contains("yungame2_")) ? sdkType.endsWith("_ipport") ? "http://124.70.152.179:8082/" : sdkType.endsWith("_proxy") ? "http://gess.ddyun.com/" : "http://gess1.ddyun.com/" : (sdkType.equals("yungame3") || sdkType.contains("yungame3_")) ? (sdkType.endsWith("_ipport") || sdkType.endsWith("_proxy")) ? "http://yybess.ddyun123.com/" : "http://yybess.ddyun.com/" : (sdkType.endsWith("_ipport") || sdkType.endsWith("_proxy")) ? "http://esscdn.ddyun.com/" : "http://ess.ddyun.com/";
    }

    /* renamed from: c */
    public static String m21391c() {
        String sdkType = SdkKeyUtil.getInstance().getSdkType();
        return (sdkType.equals("yungame") || sdkType.contains("yungame_")) ? "http://gamedata.ddyun.com/" : (sdkType.equals("yungame2") || sdkType.contains("yungame2_")) ? "http://gdata.ddyun.com/" : (sdkType.equals("yungame3") || sdkType.contains("yungame3_")) ? (sdkType.endsWith("_ipport") || sdkType.endsWith("_proxy")) ? "http://yybdata.ddyun123.com/" : "http://yybdata.ddyun.com/" : "http://data.ddyun.com/";
    }

    /* renamed from: e */
    public static String m21389e() {
        String sdkType = SdkKeyUtil.getInstance().getSdkType();
        return (sdkType.equals("yungame") || sdkType.contains("yungame_")) ? "http://gamedapi.ddyun.com/" : (sdkType.equals("yungame2") || sdkType.contains("yungame2_")) ? (sdkType.endsWith("_ipport") || sdkType.endsWith("_proxy")) ? "http://gdapi.ddyun.com/" : "http://gdapi1.ddyun.com/" : (sdkType.equals("yungame3") || sdkType.contains("yungame3_")) ? (sdkType.endsWith("_ipport") || sdkType.endsWith("_proxy")) ? "http://yybdapi.ddyun123.com/" : "http://yybdapi.ddyun.com/" : "http://dapi.ddyun.com/";
    }

    /* renamed from: a */
    public static void m21395a(Context context, boolean z) {
        if (z) {
            m21396a(context, AliDns.getInstance(context));
        }
    }

    /* renamed from: a */
    public static void m21396a(Context context, CustomDns customDns) {
        OkHttpDns.getInstance(context).setCustomDns(customDns);
        ArrayList<String> arrayList = new ArrayList<>();
        m21394a(arrayList, m21397a());
        m21394a(arrayList, m21392b());
        m21394a(arrayList, m21391c());
        m21394a(arrayList, m21390d());
        m21394a(arrayList, m21389e());
        OkHttpDns.getInstance(context).setPreResolveHosts(arrayList);
    }

    /* renamed from: a */
    private static void m21394a(ArrayList<String> arrayList, String str) {
        String str2 = "";
        try {
            str2 = new URL(str).getHost();
        } catch (Exception unused) {
        }
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
        }
    }
}
