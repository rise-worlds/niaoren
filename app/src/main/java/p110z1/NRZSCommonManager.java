package p110z1;

import android.content.Context;

/* renamed from: z1.apl */
/* loaded from: classes3.dex */
public class NRZSCommonManager {

    /* renamed from: b */
    private static final Object f17159b = new Object();

    /* renamed from: c */
    private static NRZSCommonManager f17160c;

    /* renamed from: a */
    private NRZSCommonLoadingDialg f17161a;

    /* renamed from: a */
    public void m11699a(Context context, String str) {
        if (this.f17161a == null) {
            this.f17161a = new NRZSCommonLoadingDialg(context, str);
        }
        if (!this.f17161a.isShowing()) {
            this.f17161a.show();
        }
    }

    /* renamed from: a */
    public void m11700a() {
        NRZSCommonLoadingDialg apkVar = this.f17161a;
        if (apkVar != null) {
            apkVar.dismiss();
            this.f17161a = null;
        }
    }

    /* renamed from: b */
    public static NRZSCommonManager m11698b() {
        NRZSCommonManager aplVar;
        synchronized (f17159b) {
            if (f17160c == null) {
                f17160c = new NRZSCommonManager();
            }
            aplVar = f17160c;
        }
        return aplVar;
    }
}
