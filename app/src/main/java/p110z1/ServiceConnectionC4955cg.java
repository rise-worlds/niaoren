package p110z1;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.alipay.android.app.IAlixPay;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.cg */
/* loaded from: classes3.dex */
public class ServiceConnectionC4955cg implements ServiceConnection {

    /* renamed from: a */
    final /* synthetic */ C4943cf f20681a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ServiceConnectionC4955cg(C4943cf cfVar) {
        this.f20681a = cfVar;
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        this.f20681a.f20636d = null;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Object obj;
        Object obj2;
        obj = this.f20681a.f20637e;
        synchronized (obj) {
            this.f20681a.f20636d = IAlixPay.Stub.asInterface(iBinder);
            obj2 = this.f20681a.f20637e;
            obj2.notify();
        }
    }
}
