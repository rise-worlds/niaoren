package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bma */
/* loaded from: classes3.dex */
public final class ObservableSampleTimed<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final long f19320b;

    /* renamed from: c */
    final TimeUnit f19321c;

    /* renamed from: d */
    final Scheduler f19322d;

    /* renamed from: e */
    final boolean f19323e;

    public ObservableSampleTimed(ObservableSource<T> asqVar, long j, TimeUnit timeUnit, Scheduler astVar, boolean z) {
        super(asqVar);
        this.f19320b = j;
        this.f19321c = timeUnit;
        this.f19322d = astVar;
        this.f19323e = z;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        SerializedObserver btyVar = new SerializedObserver(assVar);
        if (this.f19323e) {
            this.f18807a.subscribe(new C4539a(btyVar, this.f19320b, this.f19321c, this.f19322d));
        } else {
            this.f18807a.subscribe(new C4540b(btyVar, this.f19320b, this.f19321c, this.f19322d));
        }
    }

    /* compiled from: ObservableSampleTimed.java */
    /* renamed from: z1.bma$c */
    /* loaded from: classes3.dex */
    static abstract class AbstractRunnableC4541c<T> extends AtomicReference<T> implements Runnable, Observer<T>, Disposable {
        private static final long serialVersionUID = -3517602651313910099L;
        final Observer<? super T> downstream;
        final long period;
        final Scheduler scheduler;
        final AtomicReference<Disposable> timer = new AtomicReference<>();
        final TimeUnit unit;
        Disposable upstream;

        abstract void complete();

        AbstractRunnableC4541c(Observer<? super T> assVar, long j, TimeUnit timeUnit, Scheduler astVar) {
            this.downstream = assVar;
            this.period = j;
            this.unit = timeUnit;
            this.scheduler = astVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
                Scheduler astVar = this.scheduler;
                long j = this.period;
                DisposableHelper.replace(this.timer, astVar.mo9485a(this, j, j, this.unit));
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            lazySet(t);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            cancelTimer();
            this.downstream.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            cancelTimer();
            complete();
        }

        void cancelTimer() {
            DisposableHelper.dispose(this.timer);
        }

        @Override // p110z1.Disposable
        public void dispose() {
            cancelTimer();
            this.upstream.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        void emit() {
            T andSet = getAndSet(null);
            if (andSet != null) {
                this.downstream.onNext(andSet);
            }
        }
    }

    /* compiled from: ObservableSampleTimed.java */
    /* renamed from: z1.bma$b */
    /* loaded from: classes3.dex */
    static final class C4540b<T> extends AbstractRunnableC4541c<T> {
        private static final long serialVersionUID = -7139995637533111443L;

        C4540b(Observer<? super T> assVar, long j, TimeUnit timeUnit, Scheduler astVar) {
            super(assVar, j, timeUnit, astVar);
        }

        @Override // p110z1.ObservableSampleTimed.AbstractRunnableC4541c
        void complete() {
            this.downstream.onComplete();
        }

        @Override // java.lang.Runnable
        public void run() {
            emit();
        }
    }

    /* compiled from: ObservableSampleTimed.java */
    /* renamed from: z1.bma$a */
    /* loaded from: classes3.dex */
    static final class C4539a<T> extends AbstractRunnableC4541c<T> {
        private static final long serialVersionUID = -7139995637533111443L;
        final AtomicInteger wip = new AtomicInteger(1);

        C4539a(Observer<? super T> assVar, long j, TimeUnit timeUnit, Scheduler astVar) {
            super(assVar, j, timeUnit, astVar);
        }

        @Override // p110z1.ObservableSampleTimed.AbstractRunnableC4541c
        void complete() {
            emit();
            if (this.wip.decrementAndGet() == 0) {
                this.downstream.onComplete();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.wip.incrementAndGet() == 2) {
                emit();
                if (this.wip.decrementAndGet() == 0) {
                    this.downstream.onComplete();
                }
            }
        }
    }
}
