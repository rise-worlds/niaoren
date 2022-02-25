package p110z1;

import android.text.TextUtils;
import com.nrzs.data.DataApp;

/* renamed from: z1.ajr */
/* loaded from: classes3.dex */
public class OrcDownManager {

    /* renamed from: a */
    private static OrcDownManager f16097a;

    /* renamed from: a */
    public static OrcDownManager m12866a() {
        if (f16097a == null) {
            synchronized (OrcDownManager.class) {
                if (f16097a == null) {
                    f16097a = new OrcDownManager();
                }
            }
        }
        return f16097a;
    }

    /* renamed from: b */
    public boolean m12864b() {
        String b = apf.m11837b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16580K, "");
        String b2 = apf.m11837b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16581L, "");
        if (TextUtils.isEmpty(b)) {
            return true;
        }
        if (TextUtils.isEmpty(b2)) {
            return false;
        }
        try {
            return Long.valueOf(m12865a(b)).longValue() < Long.valueOf(m12865a(b2)).longValue();
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: a */
    public String m12865a(String str) {
        StringBuffer stringBuffer = new StringBuffer("");
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '.') {
                stringBuffer.append(str.charAt(i));
            }
        }
        return stringBuffer.toString();
    }
}
