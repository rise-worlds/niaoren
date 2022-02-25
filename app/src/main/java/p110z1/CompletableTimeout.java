package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: z1.ayk */
/* loaded from: classes3.dex */
public final class CompletableTimeout extends Completable {

    /* renamed from: a */
    final CompletableSource f17783a;

    /* renamed from: b */
    final long f17784b;

    /* renamed from: c */
    final TimeUnit f17785c;

    /* renamed from: d */
    final Scheduler f17786d;

    /* renamed from: e */
    final CompletableSource f17787e;

    public CompletableTimeout(CompletableSource arsVar, long j, TimeUnit timeUnit, Scheduler astVar, CompletableSource arsVar2) {
        this.f17783a = arsVar;
        this.f17784b = j;
        this.f17785c = timeUnit;
        this.f17786d = astVar;
        this.f17787e = arsVar2;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    public void mo9001b(CompletableObserver arpVar) {
        CompositeDisposable atqVar = new CompositeDisposable();
        arpVar.onSubscribe(atqVar);
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        atqVar.mo9939a(this.f17786d.mo9480a(new RunnableC3971a(atomicBoolean, atqVar, arpVar), this.f17784b, this.f17785c));
        this.f17783a.mo11309a(new C3973b(atqVar, atomicBoolean, arpVar));
    }

    /* compiled from: CompletableTimeout.java */
    /* renamed from: z1.ayk$b */
    /* loaded from: classes3.dex */
    static final class C3973b implements CompletableObserver {

        /* renamed from: a */
        private final CompositeDisposable f17793a;

        /* renamed from: b */
        private final AtomicBoolean f17794b;

        /* renamed from: c */
        private final CompletableObserver f17795c;

        C3973b(CompositeDisposable atqVar, AtomicBoolean atomicBoolean, CompletableObserver arpVar) {
            this.f17793a = atqVar;
            this.f17794b = atomicBoolean;
            this.f17795c = arpVar;
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            this.f17793a.mo9939a(atrVar);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            if (this.f17794b.compareAndSet(false, true)) {
                this.f17793a.dispose();
                this.f17795c.onError(th);
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
        public void onComplete() {
            if (this.f17794b.compareAndSet(false, true)) {
                this.f17793a.dispose();
                this.f17795c.onComplete();
            }
        }
    }

    /* compiled from: CompletableTimeout.java */
    /* renamed from: z1.ayk$a */
    /* loaded from: classes3.dex */
    final class RunnableC3971a implements Runnable {

        /* renamed from: a */
        final CompositeDisposable f17788a;

        /* renamed from: b */
        final CompletableObserver f17789b;

        /* renamed from: d */
        private final AtomicBoolean f17791d;

        RunnableC3971a(AtomicBoolean atomicBoolean, CompositeDisposable atqVar, CompletableObserver arpVar) {
            this.f17791d = atomicBoolean;
            this.f17788a = atqVar;
            this.f17789b = arpVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f17791d.compareAndSet(false, true)) {
                this.f17788a.m9999a();
                if (CompletableTimeout.this.f17787e == null) {
                    this.f17789b.onError(new TimeoutException(ExceptionHelper.m9433a(CompletableTimeout.this.f17784b, CompletableTimeout.this.f17785c)));
                } else {
                    CompletableTimeout.this.f17787e.mo11309a(new C3972a());
                }
            }
        }

        /* compiled from: CompletableTimeout.java */
        /* renamed from: z1.ayk$a$a */
        /* loaded from: classes3.dex */
        final class C3972a implements CompletableObserver {
            C3972a() {
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                RunnableC3971a.this.f17788a.mo9939a(atrVar);
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
            public void onError(Throwable th) {
                RunnableC3971a.this.f17788a.dispose();
                RunnableC3971a.this.f17789b.onError(th);
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
            public void onComplete() {
                RunnableC3971a.this.f17788a.dispose();
                RunnableC3971a.this.f17789b.onComplete();
            }
        }
    }
}
