package com.kaopu.tiantian.com.tencent.tmgp.hjol;

import android.util.Log;
import com.lody.virtual.helper.utils.VLog;
import java.util.List;

/* loaded from: classes.dex */
public class Hook_gsdk {
    public static String className = "com.tencent.gsdk.api.g";
    public static String methodName = "a";
    public static String methodSig = "(I)Ljava/util/List;";

    public static List<Double> hook(int i) {
        VLog.m18993d("sunya", "GSDKSystem in", new Object[0]);
        return null;
    }

    public static List<Double> backup(int i) {
        try {
            Log.w("TianTian", "load should not be here");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
