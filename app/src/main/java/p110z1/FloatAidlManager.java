package p110z1;

import android.content.Intent;
import android.support.annotation.NonNull;
import com.nrzs.p067ft.FloatApp;
import com.nrzs.p067ft.service.AidlFloatConnection;

/* renamed from: z1.anh */
/* loaded from: classes3.dex */
public class FloatAidlManager {

    /* renamed from: c */
    private static final Object f16716c = new Object();

    /* renamed from: d */
    private static FloatAidlManager f16717d;

    /* renamed from: a */
    private AidlFloatConnection f16718a;

    /* renamed from: b */
    private boolean f16719b = false;

    /* renamed from: a */
    public boolean m12432a() {
        if (this.f16718a == null) {
            this.f16718a = new AidlFloatConnection();
        }
        Intent intent = new Intent("com.angel.nrzs.aidl.service.action");
        intent.setPackage("com.angel.nrzs.NRZSAidlService");
        boolean bindService = FloatApp.m18899b().m18901a().bindService(intent, this.f16718a, 1);
        this.f16719b = bindService;
        return bindService;
    }

    /* renamed from: a */
    public void m12431a(String str) {
        AidlFloatConnection aVar = this.f16718a;
        if (aVar != null) {
            aVar.m18887a(str);
        }
    }

    /* renamed from: b */
    public void m12430b() {
        m12428d();
        this.f16718a = null;
    }

    /* renamed from: d */
    private void m12428d() {
        if (this.f16718a != null) {
            FloatApp.m18899b().m18901a().unbindService(this.f16718a);
        }
    }

    private FloatAidlManager() {
    }

    @NonNull
    /* renamed from: c */
    public static FloatAidlManager m12429c() {
        FloatAidlManager anhVar;
        synchronized (f16716c) {
            if (f16717d == null) {
                f16717d = new FloatAidlManager();
            }
            anhVar = f16717d;
        }
        return anhVar;
    }
}
