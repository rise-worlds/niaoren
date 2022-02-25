package p110z1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import com.alipay.android.app.IRemoteServiceCallback;
import p110z1.C4943cf;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.ci */
/* loaded from: classes3.dex */
public class BinderC4961ci extends IRemoteServiceCallback.Stub {

    /* renamed from: a */
    final /* synthetic */ C4943cf f20688a;

    @Override // com.alipay.android.app.IRemoteServiceCallback
    public boolean isHideLoadingScreen() throws RemoteException {
        return false;
    }

    @Override // com.alipay.android.app.IRemoteServiceCallback
    public void payEnd(boolean z, String str) throws RemoteException {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BinderC4961ci(C4943cf cfVar) {
        this.f20688a = cfVar;
    }

    @Override // com.alipay.android.app.IRemoteServiceCallback
    public void startActivity(String str, String str2, int i, Bundle bundle) throws RemoteException {
        Activity activity;
        C4943cf.AbstractC4944a aVar;
        C4745bt btVar;
        C4745bt btVar2;
        Activity activity2;
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        if (bundle == null) {
            bundle = new Bundle();
        }
        try {
            bundle.putInt("CallingPid", i);
            intent.putExtras(bundle);
        } catch (Exception unused) {
        }
        intent.setClassName(str, str2);
        activity = this.f20688a.f20635c;
        if (activity != null) {
            activity2 = this.f20688a.f20635c;
            activity2.startActivity(intent);
        } else {
            btVar = this.f20688a.f20640h;
            C3754ao.m12156a(btVar, C3857aq.f17251b, C3857aq.f17225Q, "");
            btVar2 = this.f20688a.f20640h;
            Context b = btVar2.m9412b();
            if (b != null) {
                b.startActivity(intent);
            }
        }
        aVar = this.f20688a.f20639g;
        aVar.mo5475b();
    }
}
