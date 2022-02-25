package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bqb */
/* loaded from: classes3.dex */
public final class SingleTimeout<T> extends Single<T> {

    /* renamed from: a */
    final SingleSource<T> f19828a;

    /* renamed from: b */
    final long f19829b;

    /* renamed from: c */
    final TimeUnit f19830c;

    /* renamed from: d */
    final Scheduler f19831d;

    /* renamed from: e */
    final SingleSource<? extends T> f19832e;

    public SingleTimeout(SingleSource<T> ataVar, long j, TimeUnit timeUnit, Scheduler astVar, SingleSource<? extends T> ataVar2) {
        this.f19828a = ataVar;
        this.f19829b = j;
        this.f19830c = timeUnit;
        this.f19831d = astVar;
        this.f19832e = ataVar2;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        RunnableC4699a aVar = new RunnableC4699a(asxVar, this.f19832e, this.f19829b, this.f19830c);
        asxVar.onSubscribe(aVar);
        DisposableHelper.replace(aVar.task, this.f19831d.mo9480a(aVar, this.f19829b, this.f19830c));
        this.f19828a.mo10018a(aVar);
    }

    /* compiled from: SingleTimeout.java */
    /* renamed from: z1.bqb$a */
    /* loaded from: classes3.dex */
    static final class RunnableC4699a<T> extends AtomicReference<Disposable> implements Runnable, SingleObserver<T>, Disposable {
        private static final long serialVersionUID = 37497744973048446L;
        final SingleObserver<? super T> downstream;
        final C4700a<T> fallback;
        SingleSource<? extends T> other;
        final AtomicReference<Disposable> task = new AtomicReference<>();
        final long timeout;
        final TimeUnit unit;

        /* compiled from: SingleTimeout.java */
        /* renamed from: z1.bqb$a$a */
        /* loaded from: classes3.dex */
        static final class C4700a<T> extends AtomicReference<Disposable> implements SingleObserver<T> {
            private static final long serialVersionUID = 2071387740092105509L;
            final SingleObserver<? super T> downstream;

            C4700a(SingleObserver<? super T> asxVar) {
                this.downstream = asxVar;
            }

            @Override // p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(this, atrVar);
            }

            @Override // p110z1.SingleObserver
            public void onSuccess(T t) {
                this.downstream.onSuccess(t);
            }

            @Override // p110z1.SingleObserver
            public void onError(Throwable th) {
                this.downstream.onError(th);
            }
        }

        RunnableC4699a(SingleObserver<? super T> asxVar, SingleSource<? extends T> ataVar, long j, TimeUnit timeUnit) {
            this.downstream = asxVar;
            this.other = ataVar;
            this.timeout = j;
            this.unit = timeUnit;
            if (ataVar != null) {
                this.fallback = new C4700a<>(asxVar);
            } else {
                this.fallback = null;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            Disposable atrVar = get();
            if (atrVar != DisposableHelper.DISPOSED && compareAndSet(atrVar, DisposableHelper.DISPOSED)) {
                if (atrVar != null) {
                    atrVar.dispose();
                }
                SingleSource<? extends T> ataVar = this.other;
                if (ataVar == null) {
                    this.downstream.onError(new TimeoutException(ExceptionHelper.m9433a(this.timeout, this.unit)));
                    return;
                }
                this.other = null;
                ataVar.mo10018a(this.fallback);
            }
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this, atrVar);
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            Disposable atrVar = get();
            if (atrVar != DisposableHelper.DISPOSED && compareAndSet(atrVar, DisposableHelper.DISPOSED)) {
                DisposableHelper.dispose(this.task);
                this.downstream.onSuccess(t);
            }
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            Disposable atrVar = get();
            if (atrVar == DisposableHelper.DISPOSED || !compareAndSet(atrVar, DisposableHelper.DISPOSED)) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            DisposableHelper.dispose(this.task);
            this.downstream.onError(th);
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
            DisposableHelper.dispose(this.task);
            C4700a<T> aVar = this.fallback;
            if (aVar != null) {
                DisposableHelper.dispose(aVar);
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }
    }
}
