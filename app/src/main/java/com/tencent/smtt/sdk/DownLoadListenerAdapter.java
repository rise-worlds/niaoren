package com.tencent.smtt.sdk;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import com.tencent.smtt.export.external.interfaces.DownloadListener;
import com.tencent.smtt.sdk.p078a.MttLoader;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.tencent.smtt.sdk.b */
/* loaded from: classes2.dex */
public class DownLoadListenerAdapter implements DownloadListener {

    /* renamed from: a */
    private DownloadListener f13093a;

    /* renamed from: b */
    private WebView f13094b;

    @Override // com.tencent.smtt.export.external.interfaces.DownloadListener
    public void onDownloadVideo(String str, long j, int i) {
    }

    public DownLoadListenerAdapter(WebView webView, DownloadListener downloadListener, boolean z) {
        this.f13093a = downloadListener;
        this.f13094b = webView;
    }

    @Override // com.tencent.smtt.export.external.interfaces.DownloadListener
    public void onDownloadStart(String str, String str2, byte[] bArr, String str3, String str4, String str5, long j, String str6, String str7) {
        DownloadListener downloadListener = this.f13093a;
        if (downloadListener != null) {
            downloadListener.onDownloadStart(str, str3, str4, str5, j);
        } else if (QbSdk.canOpenMimeFileType(this.f13094b.getContext(), str5)) {
            Intent intent = new Intent("com.tencent.QQBrowser.action.sdk.document");
            intent.setFlags(268435456);
            intent.putExtra("key_reader_sdk_url", str);
            intent.putExtra("key_reader_sdk_type", 1);
            intent.setData(Uri.parse(str));
            this.f13094b.getContext().startActivity(intent);
        } else {
            ApplicationInfo applicationInfo = this.f13094b.getContext().getApplicationInfo();
            if (applicationInfo == null || !TbsConfig.APP_WX.equals(applicationInfo.packageName)) {
                MttLoader.m16877a(this.f13094b.getContext(), str, null, null);
            }
        }
    }

    @Override // com.tencent.smtt.export.external.interfaces.DownloadListener
    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        onDownloadStart(str, null, null, str2, str3, str4, j, null, null);
    }
}
