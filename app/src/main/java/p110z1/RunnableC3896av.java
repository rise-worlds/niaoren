package p110z1;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.av */
/* loaded from: classes3.dex */
public class RunnableC3896av implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C4745bt f17550a;

    /* renamed from: b */
    final /* synthetic */ Context f17551b;

    /* renamed from: c */
    final /* synthetic */ C3894au f17552c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3896av(C3894au auVar, C4745bt btVar, Context context) {
        this.f17552c = auVar;
        this.f17550a = btVar;
        this.f17551b = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            C4330bh a = new C4538bm().m9630a(this.f17550a, this.f17551b);
            if (a != null) {
                this.f17552c.m9974b(a.m9690b());
                this.f17552c.m9978a(C4745bt.m9418a());
            }
        } catch (Throwable th) {
            C4921cd.m5618a(th);
        }
    }
}
