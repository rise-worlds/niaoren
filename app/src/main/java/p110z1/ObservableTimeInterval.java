package p110z1;

import java.util.concurrent.TimeUnit;

/* renamed from: z1.bnb */
/* loaded from: classes3.dex */
public final class ObservableTimeInterval<T> extends AbstractObservableWithUpstream<T, Timed<T>> {

    /* renamed from: b */
    final Scheduler f19446b;

    /* renamed from: c */
    final TimeUnit f19447c;

    public ObservableTimeInterval(ObservableSource<T> asqVar, TimeUnit timeUnit, Scheduler astVar) {
        super(asqVar);
        this.f19446b = astVar;
        this.f19447c = timeUnit;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super Timed<T>> assVar) {
        this.f18807a.subscribe(new C4578a(assVar, this.f19447c, this.f19446b));
    }

    /* compiled from: ObservableTimeInterval.java */
    /* renamed from: z1.bnb$a */
    /* loaded from: classes3.dex */
    static final class C4578a<T> implements Observer<T>, Disposable {

        /* renamed from: a */
        final Observer<? super Timed<T>> f19448a;

        /* renamed from: b */
        final TimeUnit f19449b;

        /* renamed from: c */
        final Scheduler f19450c;

        /* renamed from: d */
        long f19451d;

        /* renamed from: e */
        Disposable f19452e;

        C4578a(Observer<? super Timed<T>> assVar, TimeUnit timeUnit, Scheduler astVar) {
            this.f19448a = assVar;
            this.f19450c = astVar;
            this.f19449b = timeUnit;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19452e, atrVar)) {
                this.f19452e = atrVar;
                this.f19451d = this.f19450c.mo9035a(this.f19449b);
                this.f19448a.onSubscribe(this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19452e.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19452e.isDisposed();
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            long a = this.f19450c.mo9035a(this.f19449b);
            long j = this.f19451d;
            this.f19451d = a;
            this.f19448a.onNext(new Timed(t, a - j, this.f19449b));
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f19448a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f19448a.onComplete();
        }
    }
}
