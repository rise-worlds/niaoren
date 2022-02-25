package com.alipay.apmobilesecuritysdk.otherid;

import android.content.Context;
import p110z1.C5097cq;
import p110z1.C5257dc;

/* loaded from: classes.dex */
public class UmidSdkWrapper {
    private static final String UMIDTOKEN_FILE_NAME = "xxxwww_v2";
    private static final String UMIDTOKEN_KEY_NAME = "umidtk";
    private static volatile String cachedUmidToken = "";
    private static volatile boolean initUmidFinished = false;

    private static String compatUmidBug(Context context, String str) {
        if (!C5097cq.m3699a(str) && !C5097cq.m3698a(str, "000000000000000000000000")) {
            return str;
        }
        String utdid = UtdidWrapper.getUtdid(context);
        if (utdid != null && utdid.contains("?")) {
            utdid = "";
        }
        return C5097cq.m3699a(utdid) ? "" : utdid;
    }

    public static synchronized String getSecurityToken(Context context) {
        String str;
        synchronized (UmidSdkWrapper.class) {
            str = cachedUmidToken;
        }
        return str;
    }

    public static String startUmidTaskSync(Context context, int i) {
        return "";
    }

    private static synchronized void updateLocalUmidToken(Context context, String str) {
        synchronized (UmidSdkWrapper.class) {
            if (C5097cq.m3695b(str)) {
                C5257dc.m3244a(context, UMIDTOKEN_FILE_NAME, UMIDTOKEN_KEY_NAME, str);
                cachedUmidToken = str;
            }
        }
    }
}
