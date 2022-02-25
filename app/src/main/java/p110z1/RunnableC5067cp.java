package p110z1;

import android.app.Activity;

/* renamed from: z1.cp */
/* loaded from: classes3.dex */
final class RunnableC5067cp implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Activity f21005a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC5067cp(Activity activity) {
        this.f21005a = activity;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f21005a.finish();
    }
}
