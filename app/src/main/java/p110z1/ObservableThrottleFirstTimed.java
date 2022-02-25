package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p110z1.Scheduler;

/* renamed from: z1.bmz */
/* loaded from: classes3.dex */
public final class ObservableThrottleFirstTimed<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final long f19439b;

    /* renamed from: c */
    final TimeUnit f19440c;

    /* renamed from: d */
    final Scheduler f19441d;

    public ObservableThrottleFirstTimed(ObservableSource<T> asqVar, long j, TimeUnit timeUnit, Scheduler astVar) {
        super(asqVar);
        this.f19439b = j;
        this.f19440c = timeUnit;
        this.f19441d = astVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new RunnableC4575a(new SerializedObserver(assVar), this.f19439b, this.f19440c, this.f19441d.mo9034b()));
    }

    /* compiled from: ObservableThrottleFirstTimed.java */
    /* renamed from: z1.bmz$a */
    /* loaded from: classes3.dex */
    static final class RunnableC4575a<T> extends AtomicReference<Disposable> implements Runnable, Observer<T>, Disposable {
        private static final long serialVersionUID = 786994795061867455L;
        boolean done;
        final Observer<? super T> downstream;
        volatile boolean gate;
        final long timeout;
        final TimeUnit unit;
        Disposable upstream;
        final Scheduler.AbstractC3881c worker;

        RunnableC4575a(Observer<? super T> assVar, long j, TimeUnit timeUnit, Scheduler.AbstractC3881c cVar) {
            this.downstream = assVar;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = cVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.gate && !this.done) {
                this.gate = true;
                this.downstream.onNext(t);
                Disposable atrVar = get();
                if (atrVar != null) {
                    atrVar.dispose();
                }
                DisposableHelper.replace(this, this.worker.mo9030a(this, this.timeout, this.unit));
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.gate = false;
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.done = true;
            this.downstream.onError(th);
            this.worker.dispose();
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.downstream.onComplete();
                this.worker.dispose();
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.upstream.dispose();
            this.worker.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.worker.isDisposed();
        }
    }
}
