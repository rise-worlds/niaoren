package p110z1;

import java.util.concurrent.TimeUnit;
import p110z1.Scheduler;

/* renamed from: z1.bjl */
/* loaded from: classes3.dex */
public final class ObservableDelay<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final long f19002b;

    /* renamed from: c */
    final TimeUnit f19003c;

    /* renamed from: d */
    final Scheduler f19004d;

    /* renamed from: e */
    final boolean f19005e;

    public ObservableDelay(ObservableSource<T> asqVar, long j, TimeUnit timeUnit, Scheduler astVar, boolean z) {
        super(asqVar);
        this.f19002b = j;
        this.f19003c = timeUnit;
        this.f19004d = astVar;
        this.f19005e = z;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new C4426a(this.f19005e ? assVar : new SerializedObserver(assVar), this.f19002b, this.f19003c, this.f19004d.mo9034b(), this.f19005e));
    }

    /* compiled from: ObservableDelay.java */
    /* renamed from: z1.bjl$a */
    /* loaded from: classes3.dex */
    static final class C4426a<T> implements Observer<T>, Disposable {

        /* renamed from: a */
        final Observer<? super T> f19006a;

        /* renamed from: b */
        final long f19007b;

        /* renamed from: c */
        final TimeUnit f19008c;

        /* renamed from: d */
        final Scheduler.AbstractC3881c f19009d;

        /* renamed from: e */
        final boolean f19010e;

        /* renamed from: f */
        Disposable f19011f;

        C4426a(Observer<? super T> assVar, long j, TimeUnit timeUnit, Scheduler.AbstractC3881c cVar, boolean z) {
            this.f19006a = assVar;
            this.f19007b = j;
            this.f19008c = timeUnit;
            this.f19009d = cVar;
            this.f19010e = z;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19011f, atrVar)) {
                this.f19011f = atrVar;
                this.f19006a.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.f19009d.mo9030a(new RunnableC4429c(t), this.f19007b, this.f19008c);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f19009d.mo9030a(new RunnableC4428b(th), this.f19010e ? this.f19007b : 0L, this.f19008c);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f19009d.mo9030a(new RunnableC4427a(), this.f19007b, this.f19008c);
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19011f.dispose();
            this.f19009d.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19009d.isDisposed();
        }

        /* compiled from: ObservableDelay.java */
        /* renamed from: z1.bjl$a$c */
        /* loaded from: classes3.dex */
        final class RunnableC4429c implements Runnable {

            /* renamed from: b */
            private final T f19016b;

            RunnableC4429c(T t) {
                this.f19016b = t;
            }

            @Override // java.lang.Runnable
            public void run() {
                C4426a.this.f19006a.onNext((T) this.f19016b);
            }
        }

        /* compiled from: ObservableDelay.java */
        /* renamed from: z1.bjl$a$b */
        /* loaded from: classes3.dex */
        final class RunnableC4428b implements Runnable {

            /* renamed from: b */
            private final Throwable f19014b;

            RunnableC4428b(Throwable th) {
                this.f19014b = th;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    C4426a.this.f19006a.onError(this.f19014b);
                } finally {
                    C4426a.this.f19009d.dispose();
                }
            }
        }

        /* compiled from: ObservableDelay.java */
        /* renamed from: z1.bjl$a$a */
        /* loaded from: classes3.dex */
        final class RunnableC4427a implements Runnable {
            RunnableC4427a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    C4426a.this.f19006a.onComplete();
                } finally {
                    C4426a.this.f19009d.dispose();
                }
            }
        }
    }
}
