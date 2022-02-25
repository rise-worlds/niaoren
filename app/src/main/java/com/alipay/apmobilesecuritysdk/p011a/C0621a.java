package com.alipay.apmobilesecuritysdk.p011a;

import android.content.Context;
import android.os.Environment;
import com.alipay.apmobilesecuritysdk.otherid.UmidSdkWrapper;
import com.alipay.apmobilesecuritysdk.p012b.C0622a;
import com.alipay.apmobilesecuritysdk.p013c.C0623a;
import com.alipay.apmobilesecuritysdk.p014d.C0630e;
import com.alipay.apmobilesecuritysdk.p015e.C0631a;
import com.alipay.apmobilesecuritysdk.p015e.C0632b;
import com.alipay.apmobilesecuritysdk.p015e.C0633c;
import com.alipay.apmobilesecuritysdk.p015e.C0634d;
import com.alipay.apmobilesecuritysdk.p015e.C0637g;
import com.alipay.apmobilesecuritysdk.p015e.C0638h;
import com.alipay.apmobilesecuritysdk.p015e.C0639i;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import p110z1.C5097cq;
import p110z1.C5268dn;
import p110z1.C5271dq;
import p110z1.C5272dr;

/* renamed from: com.alipay.apmobilesecuritysdk.a.a */
/* loaded from: classes.dex */
public final class C0621a {

    /* renamed from: a */
    private Context f215a;

    /* renamed from: b */
    private C0622a f216b = C0622a.m25434a();

    /* renamed from: c */
    private int f217c = 4;

    public C0621a(Context context) {
        this.f215a = context;
    }

    /* renamed from: a */
    public static String m25440a(Context context) {
        String b = m25436b(context);
        return C5097cq.m3699a(b) ? C0638h.m25372f(context) : b;
    }

    /* renamed from: a */
    public static String m25439a(Context context, String str) {
        try {
            m25437b();
            String a = C0639i.m25364a(str);
            if (!C5097cq.m3699a(a)) {
                return a;
            }
            String a2 = C0637g.m25387a(context, str);
            C0639i.m25363a(str, a2);
            return !C5097cq.m3699a(a2) ? a2 : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: a */
    private static boolean m25441a() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] strArr = {"2017-01-27 2017-01-28", "2017-11-10 2017-11-11", "2017-12-11 2017-12-12"};
        int random = ((int) (Math.random() * 24.0d * 60.0d * 60.0d)) * 1;
        for (int i = 0; i < 3; i++) {
            try {
                String[] split = strArr[i].split(ExpandableTextView.f6958c);
                if (split != null && split.length == 2) {
                    Date date = new Date();
                    Date parse = simpleDateFormat.parse(split[0] + " 00:00:00");
                    Date parse2 = simpleDateFormat.parse(split[1] + " 23:59:59");
                    Calendar instance = Calendar.getInstance();
                    instance.setTime(parse2);
                    instance.add(13, random);
                    Date time = instance.getTime();
                    if (date.after(parse) && date.before(time)) {
                        return true;
                    }
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    /* renamed from: b */
    private static String m25436b(Context context) {
        try {
            String b = C0639i.m25362b();
            if (!C5097cq.m3699a(b)) {
                return b;
            }
            C0633c b2 = C0634d.m25402b(context);
            if (b2 != null) {
                C0639i.m25365a(b2);
                String str = b2.f225a;
                if (C5097cq.m3695b(str)) {
                    return str;
                }
            }
            C0632b b3 = C0631a.m25409b(context);
            if (b3 == null) {
                return "";
            }
            C0639i.m25366a(b3);
            String str2 = b3.f222a;
            return C5097cq.m3695b(str2) ? str2 : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: b */
    private C5271dq m25435b(Map<String, String> map) {
        C0632b b;
        C0632b c;
        try {
            Context context = this.f215a;
            C5272dr drVar = new C5272dr();
            String a = C5097cq.m3696a(map, "appName", "");
            String a2 = C5097cq.m3696a(map, "sessionId", "");
            String a3 = C5097cq.m3696a(map, "rpcVersion", "");
            String a4 = m25439a(context, a);
            String securityToken = UmidSdkWrapper.getSecurityToken(context);
            String d = C0638h.m25376d(context);
            if (C5097cq.m3695b(a2)) {
                drVar.f21334c = a2;
            } else {
                drVar.f21334c = a4;
            }
            drVar.f21335d = securityToken;
            drVar.f21336e = d;
            drVar.f21332a = "android";
            String str = "";
            String str2 = "";
            String str3 = "";
            String str4 = "";
            C0633c c2 = C0634d.m25401c(context);
            if (c2 != null) {
                str2 = c2.f225a;
                str3 = c2.f227c;
            }
            if (C5097cq.m3699a(str2) && (c = C0631a.m25408c(context)) != null) {
                str2 = c.f222a;
                str3 = c.f224c;
            }
            C0633c b2 = C0634d.m25403b();
            if (b2 != null) {
                str = b2.f225a;
                str4 = b2.f227c;
            }
            if (C5097cq.m3699a(str) && (b = C0631a.m25410b()) != null) {
                str = b.f222a;
                str4 = b.f224c;
            }
            drVar.f21339h = str2;
            drVar.f21338g = str;
            drVar.f21341j = a3;
            if (C5097cq.m3699a(str2)) {
                drVar.f21333b = str;
                drVar.f21340i = str4;
            } else {
                drVar.f21333b = str2;
                drVar.f21340i = str3;
            }
            drVar.f21337f = C0630e.m25418a(context, map);
            return C5268dn.m3209b(this.f215a, this.f216b.m25431c()).mo3201a(drVar);
        } catch (Throwable th) {
            th.printStackTrace();
            C0623a.m25428a(th);
            return null;
        }
    }

    /* renamed from: b */
    private static void m25437b() {
        try {
            String[] strArr = {"device_feature_file_name", "wallet_times", "wxcasxx_v3", "wxcasxx_v4", "wxxzyy_v1"};
            for (int i = 0; i < 5; i++) {
                String str = strArr[i];
                File externalStorageDirectory = Environment.getExternalStorageDirectory();
                File file = new File(externalStorageDirectory, ".SystemConfig/" + str);
                if (file.exists() && file.canWrite()) {
                    file.delete();
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00e0 A[Catch: Exception -> 0x0251, TryCatch #0 {Exception -> 0x0251, blocks: (B:2:0x0000, B:4:0x003b, B:7:0x0045, B:10:0x005a, B:12:0x0070, B:15:0x007b, B:17:0x0081, B:20:0x008c, B:23:0x0095, B:26:0x00a2, B:29:0x00af, B:32:0x00bd, B:36:0x00cb, B:39:0x00e0, B:41:0x00f8, B:47:0x0105, B:48:0x0115, B:50:0x011c, B:54:0x012e, B:56:0x0182, B:58:0x018c, B:59:0x0190, B:60:0x0194, B:62:0x01a5, B:64:0x01af, B:65:0x01b3, B:66:0x01b7, B:67:0x01f9, B:69:0x0214, B:71:0x021a, B:73:0x0220, B:77:0x0229, B:79:0x022f), top: B:84:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0214 A[Catch: Exception -> 0x0251, TryCatch #0 {Exception -> 0x0251, blocks: (B:2:0x0000, B:4:0x003b, B:7:0x0045, B:10:0x005a, B:12:0x0070, B:15:0x007b, B:17:0x0081, B:20:0x008c, B:23:0x0095, B:26:0x00a2, B:29:0x00af, B:32:0x00bd, B:36:0x00cb, B:39:0x00e0, B:41:0x00f8, B:47:0x0105, B:48:0x0115, B:50:0x011c, B:54:0x012e, B:56:0x0182, B:58:0x018c, B:59:0x0190, B:60:0x0194, B:62:0x01a5, B:64:0x01af, B:65:0x01b3, B:66:0x01b7, B:67:0x01f9, B:69:0x0214, B:71:0x021a, B:73:0x0220, B:77:0x0229, B:79:0x022f), top: B:84:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x021a A[Catch: Exception -> 0x0251, TryCatch #0 {Exception -> 0x0251, blocks: (B:2:0x0000, B:4:0x003b, B:7:0x0045, B:10:0x005a, B:12:0x0070, B:15:0x007b, B:17:0x0081, B:20:0x008c, B:23:0x0095, B:26:0x00a2, B:29:0x00af, B:32:0x00bd, B:36:0x00cb, B:39:0x00e0, B:41:0x00f8, B:47:0x0105, B:48:0x0115, B:50:0x011c, B:54:0x012e, B:56:0x0182, B:58:0x018c, B:59:0x0190, B:60:0x0194, B:62:0x01a5, B:64:0x01af, B:65:0x01b3, B:66:0x01b7, B:67:0x01f9, B:69:0x0214, B:71:0x021a, B:73:0x0220, B:77:0x0229, B:79:0x022f), top: B:84:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0229 A[Catch: Exception -> 0x0251, TryCatch #0 {Exception -> 0x0251, blocks: (B:2:0x0000, B:4:0x003b, B:7:0x0045, B:10:0x005a, B:12:0x0070, B:15:0x007b, B:17:0x0081, B:20:0x008c, B:23:0x0095, B:26:0x00a2, B:29:0x00af, B:32:0x00bd, B:36:0x00cb, B:39:0x00e0, B:41:0x00f8, B:47:0x0105, B:48:0x0115, B:50:0x011c, B:54:0x012e, B:56:0x0182, B:58:0x018c, B:59:0x0190, B:60:0x0194, B:62:0x01a5, B:64:0x01af, B:65:0x01b3, B:66:0x01b7, B:67:0x01f9, B:69:0x0214, B:71:0x021a, B:73:0x0220, B:77:0x0229, B:79:0x022f), top: B:84:0x0000 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int m25438a(java.util.Map<java.lang.String, java.lang.String> r8) {
        /*
            Method dump skipped, instructions count: 600
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.apmobilesecuritysdk.p011a.C0621a.m25438a(java.util.Map):int");
    }
}
