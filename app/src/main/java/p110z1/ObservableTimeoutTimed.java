package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p110z1.Scheduler;

/* renamed from: z1.bnd */
/* loaded from: classes3.dex */
public final class ObservableTimeoutTimed<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final long f19456b;

    /* renamed from: c */
    final TimeUnit f19457c;

    /* renamed from: d */
    final Scheduler f19458d;

    /* renamed from: e */
    final ObservableSource<? extends T> f19459e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableTimeoutTimed.java */
    /* renamed from: z1.bnd$d */
    /* loaded from: classes3.dex */
    public interface AbstractC4586d {
        void onTimeout(long j);
    }

    public ObservableTimeoutTimed(Observable<T> aslVar, long j, TimeUnit timeUnit, Scheduler astVar, ObservableSource<? extends T> asqVar) {
        super(aslVar);
        this.f19456b = j;
        this.f19457c = timeUnit;
        this.f19458d = astVar;
        this.f19459e = asqVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        if (this.f19459e == null) {
            C4585c cVar = new C4585c(assVar, this.f19456b, this.f19457c, this.f19458d.mo9034b());
            assVar.onSubscribe(cVar);
            cVar.startTimeout(0L);
            this.f18807a.subscribe(cVar);
            return;
        }
        C4584b bVar = new C4584b(assVar, this.f19456b, this.f19457c, this.f19458d.mo9034b(), this.f19459e);
        assVar.onSubscribe(bVar);
        bVar.startTimeout(0L);
        this.f18807a.subscribe(bVar);
    }

    /* compiled from: ObservableTimeoutTimed.java */
    /* renamed from: z1.bnd$c */
    /* loaded from: classes3.dex */
    static final class C4585c<T> extends AtomicLong implements Observer<T>, Disposable, AbstractC4586d {
        private static final long serialVersionUID = 3764492702657003550L;
        final Observer<? super T> downstream;
        final long timeout;
        final TimeUnit unit;
        final Scheduler.AbstractC3881c worker;
        final SequentialDisposable task = new SequentialDisposable();
        final AtomicReference<Disposable> upstream = new AtomicReference<>();

        C4585c(Observer<? super T> assVar, long j, TimeUnit timeUnit, Scheduler.AbstractC3881c cVar) {
            this.downstream = assVar;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = cVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this.upstream, atrVar);
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            long j = get();
            if (j != cjm.f20759b) {
                long j2 = 1 + j;
                if (compareAndSet(j, j2)) {
                    this.task.get().dispose();
                    this.downstream.onNext(t);
                    startTimeout(j2);
                }
            }
        }

        void startTimeout(long j) {
            this.task.replace(this.worker.mo9030a(new RunnableC4587e(j, this), this.timeout, this.unit));
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (getAndSet(cjm.f20759b) != cjm.f20759b) {
                this.task.dispose();
                this.downstream.onError(th);
                this.worker.dispose();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (getAndSet(cjm.f20759b) != cjm.f20759b) {
                this.task.dispose();
                this.downstream.onComplete();
                this.worker.dispose();
            }
        }

        @Override // p110z1.ObservableTimeoutTimed.AbstractC4586d
        public void onTimeout(long j) {
            if (compareAndSet(j, cjm.f20759b)) {
                DisposableHelper.dispose(this.upstream);
                this.downstream.onError(new TimeoutException(ExceptionHelper.m9433a(this.timeout, this.unit)));
                this.worker.dispose();
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            this.worker.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.upstream.get());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableTimeoutTimed.java */
    /* renamed from: z1.bnd$e */
    /* loaded from: classes3.dex */
    public static final class RunnableC4587e implements Runnable {

        /* renamed from: a */
        final AbstractC4586d f19462a;

        /* renamed from: b */
        final long f19463b;

        RunnableC4587e(long j, AbstractC4586d dVar) {
            this.f19463b = j;
            this.f19462a = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f19462a.onTimeout(this.f19463b);
        }
    }

    /* compiled from: ObservableTimeoutTimed.java */
    /* renamed from: z1.bnd$b */
    /* loaded from: classes3.dex */
    static final class C4584b<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable, AbstractC4586d {
        private static final long serialVersionUID = 3764492702657003550L;
        final Observer<? super T> downstream;
        ObservableSource<? extends T> fallback;
        final long timeout;
        final TimeUnit unit;
        final Scheduler.AbstractC3881c worker;
        final SequentialDisposable task = new SequentialDisposable();
        final AtomicLong index = new AtomicLong();
        final AtomicReference<Disposable> upstream = new AtomicReference<>();

        C4584b(Observer<? super T> assVar, long j, TimeUnit timeUnit, Scheduler.AbstractC3881c cVar, ObservableSource<? extends T> asqVar) {
            this.downstream = assVar;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = cVar;
            this.fallback = asqVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this.upstream, atrVar);
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            long j = this.index.get();
            if (j != cjm.f20759b) {
                long j2 = 1 + j;
                if (this.index.compareAndSet(j, j2)) {
                    this.task.get().dispose();
                    this.downstream.onNext(t);
                    startTimeout(j2);
                }
            }
        }

        void startTimeout(long j) {
            this.task.replace(this.worker.mo9030a(new RunnableC4587e(j, this), this.timeout, this.unit));
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.index.getAndSet(cjm.f20759b) != cjm.f20759b) {
                this.task.dispose();
                this.downstream.onError(th);
                this.worker.dispose();
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (this.index.getAndSet(cjm.f20759b) != cjm.f20759b) {
                this.task.dispose();
                this.downstream.onComplete();
                this.worker.dispose();
            }
        }

        @Override // p110z1.ObservableTimeoutTimed.AbstractC4586d
        public void onTimeout(long j) {
            if (this.index.compareAndSet(j, cjm.f20759b)) {
                DisposableHelper.dispose(this.upstream);
                ObservableSource<? extends T> asqVar = this.fallback;
                this.fallback = null;
                asqVar.subscribe(new C4583a(this.downstream, this));
                this.worker.dispose();
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            DisposableHelper.dispose(this);
            this.worker.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }
    }

    /* compiled from: ObservableTimeoutTimed.java */
    /* renamed from: z1.bnd$a */
    /* loaded from: classes3.dex */
    static final class C4583a<T> implements Observer<T> {

        /* renamed from: a */
        final Observer<? super T> f19460a;

        /* renamed from: b */
        final AtomicReference<Disposable> f19461b;

        /* JADX INFO: Access modifiers changed from: package-private */
        public C4583a(Observer<? super T> assVar, AtomicReference<Disposable> atomicReference) {
            this.f19460a = assVar;
            this.f19461b = atomicReference;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.replace(this.f19461b, atrVar);
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.f19460a.onNext(t);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f19460a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f19460a.onComplete();
        }
    }
}
