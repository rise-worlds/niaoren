package p110z1;

import android.text.TextUtils;
import com.nrzs.data.DataApp;

/* compiled from: OrcDownManager.java */
/* renamed from: z1.ane */
/* loaded from: classes3.dex */
public class ane {

    /* renamed from: a */
    private static ane f16686a;

    /* renamed from: a */
    public static ane m12491a() {
        if (f16686a == null) {
            synchronized (ane.class) {
                if (f16686a == null) {
                    f16686a = new ane();
                }
            }
        }
        return f16686a;
    }

    /* renamed from: b */
    public boolean m12489b() {
        String b = apf.m11837b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16580K, "");
        String b2 = apf.m11837b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16581L, "");
        if (TextUtils.isEmpty(b)) {
            return true;
        }
        if (TextUtils.isEmpty(b2)) {
            return false;
        }
        try {
            return Long.valueOf(m12490a(b)).longValue() < Long.valueOf(m12490a(b2)).longValue();
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: a */
    public String m12490a(String str) {
        StringBuffer stringBuffer = new StringBuffer("");
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '.') {
                stringBuffer.append(str.charAt(i));
            }
        }
        return stringBuffer.toString();
    }
}
