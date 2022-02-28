package com.nrzs.va;

import android.app.Activity;

/* renamed from: com.nrzs.va.AppCallback */
/* loaded from: classes2.dex */
public abstract class AppCallback implements com.lody.virtual.client.core.AppCallback {
    public boolean isNeedCallActivity = false;

    @Override // com.lody.virtual.client.core.AppCallback
    public abstract void afterActivityCreate(Activity activity);

    @Override // com.lody.virtual.client.core.AppCallback
    public abstract void afterActivityResume(Activity activity);

    @Override // com.lody.virtual.client.core.AppCallback
    public abstract void beforeActivityCreate(Activity activity);
}
