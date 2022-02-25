package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p110z1.Scheduler;

/* renamed from: z1.azx */
/* loaded from: classes3.dex */
public final class FlowableDebounceTimed<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final long f17989c;

    /* renamed from: d */
    final TimeUnit f17990d;

    /* renamed from: e */
    final Scheduler f17991e;

    public FlowableDebounceTimed(Flowable<T> arvVar, long j, TimeUnit timeUnit, Scheduler astVar) {
        super(arvVar);
        this.f17989c = j;
        this.f17990d = timeUnit;
        this.f17991e = astVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4038b(new SerializedSubscriber(dbxVar), this.f17989c, this.f17990d, this.f17991e.mo9034b()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableDebounceTimed.java */
    /* renamed from: z1.azx$b */
    /* loaded from: classes3.dex */
    public static final class C4038b<T> extends AtomicLong implements FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = -9102637559663639004L;
        boolean done;
        final Subscriber<? super T> downstream;
        volatile long index;
        final long timeout;
        Disposable timer;
        final TimeUnit unit;
        dby upstream;
        final Scheduler.AbstractC3881c worker;

        C4038b(Subscriber<? super T> dbxVar, long j, TimeUnit timeUnit, Scheduler.AbstractC3881c cVar) {
            this.downstream = dbxVar;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = cVar;
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
                long j = this.index + 1;
                this.index = j;
                Disposable atrVar = this.timer;
                if (atrVar != null) {
                    atrVar.dispose();
                }
                RunnableC4037a aVar = new RunnableC4037a(t, j, this);
                this.timer = aVar;
                aVar.setResource(this.worker.mo9030a(aVar, this.timeout, this.unit));
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.done = true;
            Disposable atrVar = this.timer;
            if (atrVar != null) {
                atrVar.dispose();
            }
            this.downstream.onError(th);
            this.worker.dispose();
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                Disposable atrVar = this.timer;
                if (atrVar != null) {
                    atrVar.dispose();
                }
                RunnableC4037a aVar = (RunnableC4037a) atrVar;
                if (aVar != null) {
                    aVar.emit();
                }
                this.downstream.onComplete();
                this.worker.dispose();
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
            this.worker.dispose();
        }

        void emit(long j, T t, RunnableC4037a<T> aVar) {
            if (j != this.index) {
                return;
            }
            if (get() != 0) {
                this.downstream.onNext(t);
                BackpressureHelper.m9446c(this, 1L);
                aVar.dispose();
                return;
            }
            cancel();
            this.downstream.onError(new MissingBackpressureException("Could not deliver value due to lack of requests"));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableDebounceTimed.java */
    /* renamed from: z1.azx$a */
    /* loaded from: classes3.dex */
    public static final class RunnableC4037a<T> extends AtomicReference<Disposable> implements Runnable, Disposable {
        private static final long serialVersionUID = 6812032969491025141L;
        final long idx;
        final AtomicBoolean once = new AtomicBoolean();
        final C4038b<T> parent;
        final T value;

        RunnableC4037a(T t, long j, C4038b<T> bVar) {
            this.value = t;
            this.idx = j;
            this.parent = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            emit();
        }

        void emit() {
            if (this.once.compareAndSet(false, true)) {
                this.parent.emit(this.idx, this.value, this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return get() == DisposableHelper.DISPOSED;
        }

        public void setResource(Disposable atrVar) {
            DisposableHelper.replace(this, atrVar);
        }
    }
}
