package p110z1;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bvd */
/* loaded from: classes3.dex */
public abstract class ResourceSubscriber<T> implements FlowableSubscriber<T>, Disposable {

    /* renamed from: a */
    private final AtomicReference<dby> f20336a = new AtomicReference<>();

    /* renamed from: b */
    private final ListCompositeDisposable f20337b = new ListCompositeDisposable();

    /* renamed from: c */
    private final AtomicLong f20338c = new AtomicLong();

    /* renamed from: a */
    public final void m8924a(Disposable atrVar) {
        ObjectHelper.m9873a(atrVar, "resource is null");
        this.f20337b.mo9939a(atrVar);
    }

    @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
    public final void onSubscribe(dby dbyVar) {
        if (EndConsumerHelper.m9436a(this.f20336a, dbyVar, getClass())) {
            long andSet = this.f20338c.getAndSet(0L);
            if (andSet != 0) {
                dbyVar.request(andSet);
            }
            m8926a();
        }
    }

    /* renamed from: a */
    protected void m8926a() {
        m8925a((long) cjm.f20759b);
    }

    /* renamed from: a */
    protected final void m8925a(long j) {
        SubscriptionHelper.deferredRequest(this.f20336a, this.f20338c, j);
    }

    @Override // p110z1.Disposable
    public final void dispose() {
        if (SubscriptionHelper.cancel(this.f20336a)) {
            this.f20337b.dispose();
        }
    }

    @Override // p110z1.Disposable
    public final boolean isDisposed() {
        return this.f20336a.get() == SubscriptionHelper.CANCELLED;
    }
}
