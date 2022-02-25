package com.tendcloud.tenddata;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.du */
/* loaded from: classes2.dex */
public final class RunnableC2820du implements Runnable {
    final /* synthetic */ Context val$context;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2820du(Context context) {
        this.val$context = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            boolean unused = C2819dt.f13823j = true;
            String unused2 = C2819dt.f13822i = (String) Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info").getMethod("getId", new Class[0]).invoke(Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", Context.class).invoke(null, this.val$context), new Object[0]);
        } catch (Throwable unused3) {
        }
    }
}
