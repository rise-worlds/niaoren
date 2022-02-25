package p110z1;

import com.nrzs.data.xnkj.bean.AppUpdateInfo;

/* renamed from: z1.acl */
/* loaded from: classes3.dex */
public class AppUpdateEvent {

    /* renamed from: a */
    private AppUpdateInfo f15265a;

    /* renamed from: b */
    private boolean f15266b;

    /* renamed from: a */
    public boolean m14352a() {
        return this.f15266b;
    }

    public AppUpdateEvent(AppUpdateInfo appUpdateInfo) {
        this.f15265a = appUpdateInfo;
    }

    public AppUpdateEvent(AppUpdateInfo appUpdateInfo, boolean z) {
        this.f15265a = appUpdateInfo;
        this.f15266b = z;
    }

    /* renamed from: b */
    public AppUpdateInfo m14350b() {
        return this.f15265a;
    }

    /* renamed from: a */
    public void m14351a(AppUpdateInfo appUpdateInfo) {
        this.f15265a = appUpdateInfo;
    }
}
