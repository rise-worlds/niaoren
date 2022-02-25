package p110z1;

import java.util.concurrent.atomic.AtomicLong;

/* renamed from: z1.bcc */
/* loaded from: classes3.dex */
public final class FlowableOnBackpressureDrop<T> extends AbstractFlowableWithUpstream<T, T> implements Consumer<T> {

    /* renamed from: c */
    final Consumer<? super T> f18215c;

    @Override // p110z1.Consumer
    public void accept(T t) {
    }

    public FlowableOnBackpressureDrop(Flowable<T> arvVar) {
        super(arvVar);
        this.f18215c = this;
    }

    public FlowableOnBackpressureDrop(Flowable<T> arvVar, Consumer<? super T> aumVar) {
        super(arvVar);
        this.f18215c = aumVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4134a(dbxVar, this.f18215c));
    }

    /* compiled from: FlowableOnBackpressureDrop.java */
    /* renamed from: z1.bcc$a */
    /* loaded from: classes3.dex */
    static final class C4134a<T> extends AtomicLong implements FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = -6246093802440953054L;
        boolean done;
        final Subscriber<? super T> downstream;
        final Consumer<? super T> onDrop;
        dby upstream;

        C4134a(Subscriber<? super T> dbxVar, Consumer<? super T> aumVar) {
            this.downstream = dbxVar;
            this.onDrop = aumVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                this.downstream.onSubscribe(this);
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.done) {
                if (get() != 0) {
                    this.downstream.onNext(t);
                    BackpressureHelper.m9446c(this, 1L);
                    return;
                }
                try {
                    this.onDrop.accept(t);
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    cancel();
                    onError(th);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.done = true;
            this.downstream.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.downstream.onComplete();
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.m9449a(this, j);
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            this.upstream.cancel();
        }
    }
}
