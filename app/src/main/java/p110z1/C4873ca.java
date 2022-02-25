package p110z1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

/* renamed from: z1.ca */
/* loaded from: classes3.dex */
public class C4873ca {

    /* renamed from: a */
    private static final String f20483a = "00:00:00:00:00:00";

    /* renamed from: c */
    private static C4873ca f20484c;

    /* renamed from: b */
    private String f20485b;

    /* renamed from: a */
    public String m6518a() {
        return "000000000000000";
    }

    /* renamed from: b */
    public String m6516b() {
        return "000000000000000";
    }

    /* renamed from: a */
    public static C4873ca m6517a(Context context) {
        if (f20484c == null) {
            f20484c = new C4873ca(context);
        }
        return f20484c;
    }

    private C4873ca(Context context) {
        try {
            try {
                this.f20485b = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getMacAddress();
                if (!TextUtils.isEmpty(this.f20485b)) {
                    return;
                }
            } catch (Exception e) {
                C4921cd.m5618a(e);
                if (!TextUtils.isEmpty(this.f20485b)) {
                    return;
                }
            }
            this.f20485b = f20483a;
        } catch (Throwable th) {
            if (TextUtils.isEmpty(this.f20485b)) {
                this.f20485b = f20483a;
            }
            throw th;
        }
    }

    /* renamed from: c */
    public String m6514c() {
        String str = m6516b() + "|";
        String a = m6518a();
        if (TextUtils.isEmpty(a)) {
            return str + "000000000000000";
        }
        return str + a;
    }

    /* renamed from: d */
    public String m6512d() {
        return this.f20485b;
    }

    /* renamed from: b */
    public static EnumC4935ce m6515b(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.getType() == 0) {
                return EnumC4935ce.m5537a(activeNetworkInfo.getSubtype());
            }
            if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) {
                return EnumC4935ce.NONE;
            }
            return EnumC4935ce.WIFI;
        } catch (Exception unused) {
            return EnumC4935ce.NONE;
        }
    }

    /* renamed from: c */
    public static String m6513c(Context context) {
        return m6517a(context).m6514c().substring(0, 8);
    }

    /* renamed from: d */
    public static String m6511d(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getResources().getConfiguration().locale.toString();
        } catch (Throwable unused) {
            return "";
        }
    }
}
