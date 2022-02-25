package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bvc */
/* loaded from: classes3.dex */
public abstract class DisposableSubscriber<T> implements FlowableSubscriber<T>, Disposable {

    /* renamed from: f */
    final AtomicReference<dby> f20335f = new AtomicReference<>();

    @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
    public final void onSubscribe(dby dbyVar) {
        if (EndConsumerHelper.m9436a(this.f20335f, dbyVar, getClass())) {
            m8928c();
        }
    }

    /* renamed from: c */
    protected void m8928c() {
        this.f20335f.get().request(cjm.f20759b);
    }

    /* renamed from: a */
    protected final void m8929a(long j) {
        this.f20335f.get().request(j);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public final void m8927d() {
        dispose();
    }

    @Override // p110z1.Disposable
    public final boolean isDisposed() {
        return this.f20335f.get() == SubscriptionHelper.CANCELLED;
    }

    @Override // p110z1.Disposable
    public final void dispose() {
        SubscriptionHelper.cancel(this.f20335f);
    }
}
