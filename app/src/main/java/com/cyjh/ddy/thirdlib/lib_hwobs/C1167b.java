package com.cyjh.ddy.thirdlib.lib_hwobs;

import android.os.Environment;
import com.blankj.utilcode.util.AppUtils;
import java.io.File;
import org.apache.commons.p105io.FileUtils;

/* renamed from: com.cyjh.ddy.thirdlib.lib_hwobs.b */
/* loaded from: classes.dex */
public class C1167b {
    /* renamed from: a */
    public static String m21356a(long j) {
        return j >= FileUtils.ONE_GB ? String.format("%.2f GB", Float.valueOf(((float) j) / ((float) FileUtils.ONE_GB))) : j >= 1048576 ? String.format("%.2f MB", Float.valueOf(((float) j) / ((float) 1048576))) : j >= 1024 ? String.format("%.2f KB", Float.valueOf(((float) j) / ((float) 1024))) : String.format("%d B", Long.valueOf(j));
    }

    /* renamed from: a */
    public static String m21355a(String str) {
        String[] split = str.split("/");
        return Environment.getExternalStorageDirectory() + File.separator + AppUtils.m22920i() + File.separator + split[split.length - 1];
    }

    /* renamed from: a */
    public static String m21354a(String str, int i) {
        return str + "(" + i + ")";
    }

    /* renamed from: b */
    public static String m21353b(long j) {
        return j >= FileUtils.ONE_GB ? String.format("%.1f GB/s", Float.valueOf(((float) j) / ((float) FileUtils.ONE_GB))) : j >= 1048576 ? String.format("%.1f MB/s", Float.valueOf(((float) j) / ((float) 1048576))) : j >= 1024 ? String.format("%d KB/s", Long.valueOf(j / 1024)) : String.format("%d B/s", Long.valueOf(j));
    }

    /* renamed from: b */
    public static String m21352b(String str) {
        return str + "/apk/";
    }

    /* renamed from: c */
    public static String m21351c(String str) {
        return str + "/file/";
    }

    /* renamed from: d */
    public static String m21350d(String str) {
        return str + "/apkinfo/";
    }

    /* renamed from: e */
    public static String m21349e(String str) {
        int indexOf = str.indexOf("/apk/");
        String substring = indexOf == -1 ? "" : str.substring(0, indexOf + 1);
        String x = com.blankj.utilcode.util.FileUtils.m22173x(str);
        return substring + "apkinfo/" + x + ".png";
    }
}
