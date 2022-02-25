package com.goldcoast.sdk.domain;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: EntryPoint.java */
/* renamed from: com.goldcoast.sdk.domain.c */
/* loaded from: classes.dex */
public final class C1396c extends BroadcastReceiver {

    /* renamed from: a */
    final /* synthetic */ EntryPoint f9061a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C1396c(EntryPoint entryPoint) {
        this.f9061a = entryPoint;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager;
        connectivityManager = this.f9061a.f9053v;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            this.f9061a.m20281f();
        }
    }
}
