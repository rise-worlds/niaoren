package com.tencent.smtt.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.cyjh.ddysdk.device.base.constants.DdyConstants;
import com.tencent.smtt.sdk.TbsConfig;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.sdk.WebView;
import org.slf4j.Marker;
import p110z1.C4745bt;
import p110z1.SimpleComparison;

/* renamed from: com.tencent.smtt.utils.l */
/* loaded from: classes2.dex */
public class SysCoreQUA2Utils {

    /* renamed from: a */
    private static String f13384a = null;

    /* renamed from: b */
    private static String f13385b = "GA";

    /* renamed from: c */
    private static String f13386c = "GE";

    /* renamed from: d */
    private static String f13387d = "9422";

    /* renamed from: e */
    private static String f13388e = "0";

    /* renamed from: f */
    private static String f13389f = "";

    /* renamed from: g */
    private static boolean f13390g = false;

    /* renamed from: h */
    private static boolean f13391h = false;

    /* renamed from: i */
    private static boolean f13392i = false;

    /* renamed from: a */
    public static String m16401a(Context context) {
        if (!TextUtils.isEmpty(f13384a)) {
            return f13384a;
        }
        f13384a = m16400a(context, String.valueOf(WebView.getTbsSDKVersion(context)), ResultTypeConstant.f7213z, f13385b, f13386c, f13387d, f13388e, f13389f, f13390g);
        return f13384a;
    }

    /* renamed from: a */
    private static String m16400a(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, boolean z) {
        String str8 = "";
        String str9 = "";
        String str10 = "PHONE";
        StringBuilder sb = new StringBuilder();
        String str11 = m16397b(context) + Marker.ANY_MARKER + m16396c(context);
        try {
            ApplicationInfo applicationInfo = context.getApplicationContext().getApplicationInfo();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(applicationInfo.packageName, 0);
            str8 = applicationInfo.packageName;
            str9 = !TextUtils.isEmpty(str7) ? str7 : packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String a = m16399a(str8);
        if ("QB".equals(a)) {
            if (z) {
                str10 = "PAD";
            }
        } else if (m16395d(context)) {
            str10 = "PAD";
        }
        sb.append("QV");
        sb.append(SimpleComparison.f23609c);
        sb.append(DdyConstants.APP_INSTALL_ERROR);
        m16398a(sb, "PL", "ADR");
        m16398a(sb, "PR", a);
        m16398a(sb, "PP", str8);
        m16398a(sb, "PPVN", str9);
        if (!TextUtils.isEmpty(str)) {
            m16398a(sb, "TBSVC", str);
        }
        m16398a(sb, "CO", "SYS");
        if (!TextUtils.isEmpty(str2)) {
            m16398a(sb, "COVC", str2);
        }
        m16398a(sb, "PB", str4);
        m16398a(sb, "VE", str3);
        m16398a(sb, "DE", str10);
        m16398a(sb, "CHID", TextUtils.isEmpty(str6) ? ResultTypeConstant.f7213z : str6);
        m16398a(sb, "LCID", str5);
        String a2 = m16402a();
        try {
            a2 = new String(a2.getBytes("UTF-8"), "ISO8859-1");
        } catch (Exception unused) {
        }
        if (!TextUtils.isEmpty(a2)) {
            m16398a(sb, "MO", a2);
        }
        m16398a(sb, "RL", str11);
        String str12 = Build.VERSION.RELEASE;
        try {
            str12 = new String(str12.getBytes("UTF-8"), "ISO8859-1");
        } catch (Exception unused2) {
        }
        if (!TextUtils.isEmpty(str12)) {
            m16398a(sb, "OS", str12);
        }
        m16398a(sb, "API", Build.VERSION.SDK_INT + "");
        return sb.toString();
    }

    /* renamed from: a */
    private static void m16398a(StringBuilder sb, String str, String str2) {
        sb.append(C4745bt.f20071b);
        sb.append(str);
        sb.append(SimpleComparison.f23609c);
        sb.append(str2);
    }

    /* renamed from: a */
    private static String m16399a(String str) {
        return TbsConfig.APP_WX.equals(str) ? "WX" : TbsConfig.APP_QQ.equals(str) ? "QQ" : TbsConfig.APP_QZONE.equals(str) ? "QZ" : TbsConfig.APP_QB.equals(str) ? "QB" : "TRD";
    }

    /* renamed from: b */
    private static int m16397b(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (defaultDisplay != null) {
            return defaultDisplay.getWidth();
        }
        return -1;
    }

    /* renamed from: c */
    private static int m16396c(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (defaultDisplay != null) {
            return defaultDisplay.getHeight();
        }
        return -1;
    }

    /* renamed from: a */
    private static String m16402a() {
        return ExpandableTextView.f6958c + Build.MODEL.replaceAll("[ |\\/|\\_|\\&|\\|]", "") + ExpandableTextView.f6958c;
    }

    /* renamed from: d */
    private static boolean m16395d(Context context) {
        if (f13391h) {
            return f13392i;
        }
        try {
            f13392i = (Math.min(m16397b(context), m16396c(context)) * TbsListener.ErrorCode.STARTDOWNLOAD_1) / m16394e(context) >= 700;
            f13391h = true;
            return f13392i;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: e */
    private static int m16394e(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (defaultDisplay == null) {
            return TbsListener.ErrorCode.STARTDOWNLOAD_1;
        }
        defaultDisplay.getMetrics(displayMetrics);
        return displayMetrics.densityDpi;
    }
}
