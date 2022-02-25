package p110z1;

import java.util.concurrent.TimeUnit;

/* renamed from: z1.boo */
/* loaded from: classes3.dex */
public final class SingleDelay<T> extends Single<T> {

    /* renamed from: a */
    final SingleSource<? extends T> f19704a;

    /* renamed from: b */
    final long f19705b;

    /* renamed from: c */
    final TimeUnit f19706c;

    /* renamed from: d */
    final Scheduler f19707d;

    /* renamed from: e */
    final boolean f19708e;

    public SingleDelay(SingleSource<? extends T> ataVar, long j, TimeUnit timeUnit, Scheduler astVar, boolean z) {
        this.f19704a = ataVar;
        this.f19705b = j;
        this.f19706c = timeUnit;
        this.f19707d = astVar;
        this.f19708e = z;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        SequentialDisposable avfVar = new SequentialDisposable();
        asxVar.onSubscribe(avfVar);
        this.f19704a.mo10018a(new C4656a(avfVar, asxVar));
    }

    /* compiled from: SingleDelay.java */
    /* renamed from: z1.boo$a */
    /* loaded from: classes3.dex */
    final class C4656a implements SingleObserver<T> {

        /* renamed from: a */
        final SingleObserver<? super T> f19709a;

        /* renamed from: c */
        private final SequentialDisposable f19711c;

        C4656a(SequentialDisposable avfVar, SingleObserver<? super T> asxVar) {
            this.f19711c = avfVar;
            this.f19709a = asxVar;
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            this.f19711c.replace(atrVar);
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            this.f19711c.replace(SingleDelay.this.f19707d.mo9480a(new RunnableC4658b(t), SingleDelay.this.f19705b, SingleDelay.this.f19706c));
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f19711c.replace(SingleDelay.this.f19707d.mo9480a(new RunnableC4657a(th), SingleDelay.this.f19708e ? SingleDelay.this.f19705b : 0L, SingleDelay.this.f19706c));
        }

        /* compiled from: SingleDelay.java */
        /* renamed from: z1.boo$a$b */
        /* loaded from: classes3.dex */
        final class RunnableC4658b implements Runnable {

            /* renamed from: b */
            private final T f19715b;

            RunnableC4658b(T t) {
                this.f19715b = t;
            }

            @Override // java.lang.Runnable
            public void run() {
                C4656a.this.f19709a.onSuccess((T) this.f19715b);
            }
        }

        /* compiled from: SingleDelay.java */
        /* renamed from: z1.boo$a$a */
        /* loaded from: classes3.dex */
        final class RunnableC4657a implements Runnable {

            /* renamed from: b */
            private final Throwable f19713b;

            RunnableC4657a(Throwable th) {
                this.f19713b = th;
            }

            @Override // java.lang.Runnable
            public void run() {
                C4656a.this.f19709a.onError(this.f19713b);
            }
        }
    }
}
