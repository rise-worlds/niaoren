package com.alipay.sdk.widget;

import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;

/* renamed from: com.alipay.sdk.widget.i */
/* loaded from: classes.dex */
class C0674i implements DownloadListener {

    /* renamed from: a */
    final /* synthetic */ C0673h f363a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0674i(C0673h hVar) {
        this.f363a = hVar;
    }

    @Override // android.webkit.DownloadListener
    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.setFlags(268435456);
            this.f363a.f360a.startActivity(intent);
        } catch (Throwable unused) {
        }
    }
}
