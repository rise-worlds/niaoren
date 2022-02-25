package p110z1;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.TextView;
import com.alipay.mobilesecuritysdk.face.SecurityClientMobile;
import com.alipay.sdk.app.C0650b;
import com.alipay.sdk.app.C0662k;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.tools.tar.TarConstants;

/* renamed from: z1.aw */
/* loaded from: classes3.dex */
public class C3937aw {

    /* renamed from: a */
    private static final String f17607a = "virtualImeiAndImsi";

    /* renamed from: b */
    private static final String f17608b = "virtual_imei";

    /* renamed from: c */
    private static final String f17609c = "virtual_imsi";

    /* renamed from: d */
    private static volatile C3937aw f17610d;

    /* renamed from: e */
    private String f17611e;

    /* renamed from: f */
    private String f17612f = "sdk-and-lite";

    /* renamed from: g */
    private String f17613g;

    /* renamed from: e */
    private static String m9853e() {
        return "1";
    }

    /* renamed from: f */
    private static String m9852f() {
        return "-1;-1";
    }

    /* renamed from: a */
    public String m9866a() {
        return this.f17613g;
    }

    private C3937aw() {
        String a = C0662k.m25290a();
        if (!C0662k.m25288b()) {
            this.f17612f += '_' + a;
        }
    }

    /* renamed from: b */
    public static synchronized C3937aw m9861b() {
        C3937aw awVar;
        synchronized (C3937aw.class) {
            if (f17610d == null) {
                f17610d = new C3937aw();
            }
            awVar = f17610d;
        }
        return awVar;
    }

    /* renamed from: a */
    public static synchronized void m9864a(String str) {
        synchronized (C3937aw.class) {
            if (!TextUtils.isEmpty(str)) {
                PreferenceManager.getDefaultSharedPreferences(C4759bu.m9273a().m9271b()).edit().putString(C3877as.f17457i, str).apply();
                C3876ar.f17428e = str;
            }
        }
    }

    /* renamed from: b */
    private static String m9860b(Context context) {
        return Float.toString(new TextView(context).getTextSize());
    }

    /* renamed from: a */
    public String m9862a(C4745bt btVar, C4814by byVar) {
        Context b = C4759bu.m9273a().m9271b();
        C4873ca a = C4873ca.m6517a(b);
        if (TextUtils.isEmpty(this.f17611e)) {
            String b2 = C5019co.m4487b();
            String c = C5019co.m4481c();
            String d = C5019co.m4477d(b);
            String f = C5019co.m4473f(b);
            String e = C5019co.m4475e(b);
            String b3 = m9860b(b);
            this.f17611e = "Msp/15.7.3 (" + b2 + C4963cj.f20745b + c + C4963cj.f20745b + d + C4963cj.f20745b + f + C4963cj.f20745b + e + C4963cj.f20745b + b3;
        }
        String b4 = C4873ca.m6515b(b).m5536b();
        String g = C5019co.m4471g(b);
        String e2 = m9853e();
        String a2 = a.m6518a();
        String b5 = a.m6516b();
        String d2 = m9855d();
        String c2 = m9858c();
        if (byVar != null) {
            this.f17613g = byVar.m8373b();
        }
        String replace = Build.MANUFACTURER.replace(C4963cj.f20745b, ExpandableTextView.f6958c);
        String replace2 = Build.MODEL.replace(C4963cj.f20745b, ExpandableTextView.f6958c);
        boolean d3 = C4759bu.m9269d();
        String d4 = a.m6512d();
        String c3 = m9857c(b);
        String d5 = m9854d(b);
        StringBuilder sb = new StringBuilder();
        sb.append(this.f17611e);
        sb.append(C4963cj.f20745b);
        sb.append(b4);
        sb.append(C4963cj.f20745b);
        sb.append(g);
        sb.append(C4963cj.f20745b);
        sb.append(e2);
        sb.append(C4963cj.f20745b);
        sb.append(a2);
        sb.append(C4963cj.f20745b);
        sb.append(b5);
        sb.append(C4963cj.f20745b);
        sb.append(this.f17613g);
        sb.append(C4963cj.f20745b);
        sb.append(replace);
        sb.append(C4963cj.f20745b);
        sb.append(replace2);
        sb.append(C4963cj.f20745b);
        sb.append(d3);
        sb.append(C4963cj.f20745b);
        sb.append(d4);
        sb.append(C4963cj.f20745b);
        sb.append(m9852f());
        sb.append(C4963cj.f20745b);
        sb.append(this.f17612f);
        sb.append(C4963cj.f20745b);
        sb.append(d2);
        sb.append(C4963cj.f20745b);
        sb.append(c2);
        sb.append(C4963cj.f20745b);
        sb.append(c3);
        sb.append(C4963cj.f20745b);
        sb.append(d5);
        if (byVar != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("tid", C4814by.m8377a(b).m8378a());
            hashMap.put(C3877as.f17455g, C4759bu.m9273a().m9268e());
            String c4 = m9856c(btVar, b, hashMap);
            if (!TextUtils.isEmpty(c4)) {
                sb.append(C4963cj.f20745b);
                sb.append(c4);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: c */
    public static String m9858c() {
        Context b = C4759bu.m9273a().m9271b();
        SharedPreferences sharedPreferences = b.getSharedPreferences(f17607a, 0);
        String string = sharedPreferences.getString(f17608b, null);
        if (TextUtils.isEmpty(string)) {
            if (TextUtils.isEmpty(C4814by.m8377a(b).m8378a())) {
                string = m9851g();
            } else {
                string = C4873ca.m6517a(b).m6516b();
            }
            sharedPreferences.edit().putString(f17608b, string).apply();
        }
        return string;
    }

    /* renamed from: d */
    public static String m9855d() {
        Context b = C4759bu.m9273a().m9271b();
        SharedPreferences sharedPreferences = b.getSharedPreferences(f17607a, 0);
        String string = sharedPreferences.getString(f17609c, null);
        if (TextUtils.isEmpty(string)) {
            if (TextUtils.isEmpty(C4814by.m8377a(b).m8378a())) {
                String e = C4759bu.m9273a().m9268e();
                if (TextUtils.isEmpty(e)) {
                    string = m9851g();
                } else {
                    string = e.substring(3, 18);
                }
            } else {
                string = C4873ca.m6517a(b).m6518a();
            }
            sharedPreferences.edit().putString(f17609c, string).apply();
        }
        return string;
    }

    /* renamed from: g */
    private static String m9851g() {
        String hexString = Long.toHexString(System.currentTimeMillis());
        Random random = new Random();
        return hexString + (random.nextInt(C0650b.f298a) + 1000);
    }

    /* renamed from: c */
    private static String m9857c(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
        return connectionInfo != null ? connectionInfo.getSSID() : "-1";
    }

    /* renamed from: d */
    private static String m9854d(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
        return connectionInfo != null ? connectionInfo.getBSSID() : TarConstants.VERSION_POSIX;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static String m9859b(C4745bt btVar, Context context, HashMap<String, String> hashMap) {
        String str = "";
        try {
            str = SecurityClientMobile.GetApdid(context, hashMap);
        } catch (Throwable th) {
            C4921cd.m5618a(th);
            C3754ao.m12155a(btVar, C3857aq.f17254e, C3857aq.f17257h, th);
        }
        if (TextUtils.isEmpty(str)) {
            C3754ao.m12156a(btVar, C3857aq.f17254e, C3857aq.f17258i, "apdid == null");
        }
        C4921cd.m5620a(C3876ar.f17447x, "apdid:" + str);
        return str;
    }

    /* renamed from: c */
    private static String m9856c(C4745bt btVar, Context context, HashMap<String, String> hashMap) {
        try {
            return (String) Executors.newFixedThreadPool(2).submit(new CallableC3942ax(btVar, context, hashMap)).get(3000L, TimeUnit.MILLISECONDS);
        } catch (Throwable th) {
            C3754ao.m12155a(btVar, C3857aq.f17254e, C3857aq.f17259j, th);
            return "";
        }
    }

    /* renamed from: a */
    public static String m9865a(Context context) {
        if (context == null) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            String packageName = context.getPackageName();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            sb.append("(");
            sb.append(packageName);
            sb.append(C4963cj.f20745b);
            sb.append(packageInfo.versionCode);
            sb.append(")");
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }
}
