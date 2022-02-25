package com.tendcloud.tenddata;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/* compiled from: td */
@TargetApi(14)
/* renamed from: com.tendcloud.tenddata.i */
/* loaded from: classes2.dex */
public class C2961i implements Application.ActivityLifecycleCallbacks {
    C2696bb mEditState;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        try {
            C2664ab.f13485B = false;
            C2681ap.m16302b(activity, AbstractC2790d.APP);
            if (this.mEditState != null) {
                this.mEditState.remove(activity);
                if (this.mEditState.m16276b()) {
                    C2744bq.m16223a().m16217d();
                }
            }
        } catch (Throwable unused) {
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        C2664ab.f13487D = true;
        C2664ab.f13485B = true;
        try {
            C2681ap.m16303a(activity, AbstractC2790d.APP);
            C2744bq.m16223a().m16219b();
            C2744bq.m16223a().m16218c();
            if (this.mEditState != null) {
                this.mEditState.add(activity);
            }
            C2916gp.m15567a();
            C2664ab.f13519m = true;
        } catch (Throwable unused) {
        }
    }

    public void setEditState(C2696bb bbVar) {
        this.mEditState = bbVar;
    }
}
