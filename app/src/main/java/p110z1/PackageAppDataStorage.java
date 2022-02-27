package p110z1;

import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.remote.InstalledAppInfo;
import com.nrzs.core.models.PackageAppData;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/* renamed from: z1.ajg */
/* loaded from: classes3.dex */
public class PackageAppDataStorage {

    /* renamed from: a */
    private static final PackageAppDataStorage f16046a = new PackageAppDataStorage();

    /* renamed from: b */
    private final Map<String, PackageAppData> f16047b = new HashMap();

    /* renamed from: a */
    public static PackageAppDataStorage m12941a() {
        return f16046a;
    }

    /* renamed from: a */
    public PackageAppData m12937c(String str) {
        PackageAppData dVar;
        synchronized (this.f16047b) {
            dVar = this.f16047b.get(str);
            if (dVar == null) {
                dVar = m12938b(str);
            }
        }
        return dVar;
    }

    /* renamed from: a */
    public void m12939a(final String str, final ajc<PackageAppData> ajcVar) {
        Promise a = VUiKit.m11713a().mo3332a(new Callable() { // from class: z1.-$$Lambda$ajg$5RmtMkHhVxFTKv8EjAjCygC3ABw
            @Override // java.util.concurrent.Callable
            public final Object call() {
                PackageAppData c;
                c = PackageAppDataStorage.this.m12937c(str);
                return c;
            }
        });
        ajcVar.getClass();
        a.mo3282b(new DoneCallback() { // from class: z1.-$$Lambda$1T9IDChlgUNIjWCrlRGYdplWRIQ
            @Override // p110z1.DoneCallback
            public final void onDone(Object obj) {
                ajc.this.callback((PackageAppData) obj);
            }
        }).mo3285a(new FailCallback<Throwable>() { // from class: z1.ajg.1
            /* renamed from: a */
            public void onFail(Throwable th) {
                th.printStackTrace();
            }
        });
    }

    /* renamed from: b */
    private PackageAppData m12938b(String str) {
        InstalledAppInfo installedAppInfo = VirtualCore.get().getInstalledAppInfo(str, 0);
        if (installedAppInfo == null) {
            return null;
        }
        PackageAppData dVar = new PackageAppData(GameApp.getInstance().m13006b(), installedAppInfo);
        synchronized (this.f16047b) {
            this.f16047b.put(str, dVar);
        }
        return dVar;
    }
}
