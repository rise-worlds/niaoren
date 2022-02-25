package p110z1;

import java.util.concurrent.CountDownLatch;

/* renamed from: z1.brh */
/* loaded from: classes3.dex */
public abstract class BlockingBaseSubscriber<T> extends CountDownLatch implements FlowableSubscriber<T> {

    /* renamed from: a */
    T f19996a;

    /* renamed from: b */
    Throwable f19997b;

    /* renamed from: c */
    dby f19998c;

    /* renamed from: d */
    volatile boolean f19999d;

    public BlockingBaseSubscriber() {
        super(1);
    }

    @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
    public final void onSubscribe(dby dbyVar) {
        if (SubscriptionHelper.validate(this.f19998c, dbyVar)) {
            this.f19998c = dbyVar;
            if (!this.f19999d) {
                dbyVar.request(cjm.f20759b);
                if (this.f19999d) {
                    this.f19998c = SubscriptionHelper.CANCELLED;
                    dbyVar.cancel();
                }
            }
        }
    }

    @Override // p110z1.Subscriber
    public final void onComplete() {
        countDown();
    }

    /* renamed from: a */
    public final T m9468a() {
        if (getCount() != 0) {
            try {
                BlockingHelper.m9444a();
                await();
            } catch (InterruptedException e) {
                dby dbyVar = this.f19998c;
                this.f19998c = SubscriptionHelper.CANCELLED;
                if (dbyVar != null) {
                    dbyVar.cancel();
                }
                throw ExceptionHelper.m9432a(e);
            }
        }
        Throwable th = this.f19997b;
        if (th == null) {
            return this.f19996a;
        }
        throw ExceptionHelper.m9432a(th);
    }
}
