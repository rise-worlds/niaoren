package p110z1;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bji */
/* loaded from: classes3.dex */
public final class ObservableDebounce<T, U> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final Function<? super T, ? extends ObservableSource<U>> f18978b;

    public ObservableDebounce(ObservableSource<T> asqVar, Function<? super T, ? extends ObservableSource<U>> aunVar) {
        super(asqVar);
        this.f18978b = aunVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new C4422a(new SerializedObserver(assVar), this.f18978b));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableDebounce.java */
    /* renamed from: z1.bji$a */
    /* loaded from: classes3.dex */
    public static final class C4422a<T, U> implements Observer<T>, Disposable {

        /* renamed from: a */
        final Observer<? super T> f18979a;

        /* renamed from: b */
        final Function<? super T, ? extends ObservableSource<U>> f18980b;

        /* renamed from: c */
        Disposable f18981c;

        /* renamed from: d */
        final AtomicReference<Disposable> f18982d = new AtomicReference<>();

        /* renamed from: e */
        volatile long f18983e;

        /* renamed from: f */
        boolean f18984f;

        C4422a(Observer<? super T> assVar, Function<? super T, ? extends ObservableSource<U>> aunVar) {
            this.f18979a = assVar;
            this.f18980b = aunVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18981c, atrVar)) {
                this.f18981c = atrVar;
                this.f18979a.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.f18984f) {
                long j = this.f18983e + 1;
                this.f18983e = j;
                Disposable atrVar = this.f18982d.get();
                if (atrVar != null) {
                    atrVar.dispose();
                }
                try {
                    ObservableSource asqVar = (ObservableSource) ObjectHelper.m9873a(this.f18980b.apply(t), "The ObservableSource supplied is null");
                    C4423a aVar = new C4423a(this, j, t);
                    if (this.f18982d.compareAndSet(atrVar, aVar)) {
                        asqVar.subscribe(aVar);
                    }
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    dispose();
                    this.f18979a.onError(th);
                }
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            DisposableHelper.dispose(this.f18982d);
            this.f18979a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f18984f) {
                this.f18984f = true;
                Disposable atrVar = this.f18982d.get();
                if (atrVar != DisposableHelper.DISPOSED) {
                    ((C4423a) atrVar).m9637a();
                    DisposableHelper.dispose(this.f18982d);
                    this.f18979a.onComplete();
                }
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18981c.dispose();
            DisposableHelper.dispose(this.f18982d);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18981c.isDisposed();
        }

        /* renamed from: a */
        void m9638a(long j, T t) {
            if (j == this.f18983e) {
                this.f18979a.onNext(t);
            }
        }

        /* compiled from: ObservableDebounce.java */
        /* renamed from: z1.bji$a$a */
        /* loaded from: classes3.dex */
        static final class C4423a<T, U> extends DisposableObserver<U> {

            /* renamed from: a */
            final C4422a<T, U> f18985a;

            /* renamed from: b */
            final long f18986b;

            /* renamed from: c */
            final T f18987c;

            /* renamed from: d */
            boolean f18988d;

            /* renamed from: e */
            final AtomicBoolean f18989e = new AtomicBoolean();

            C4423a(C4422a<T, U> aVar, long j, T t) {
                this.f18985a = aVar;
                this.f18986b = j;
                this.f18987c = t;
            }

            @Override // p110z1.Observer
            public void onNext(U u) {
                if (!this.f18988d) {
                    this.f18988d = true;
                    dispose();
                    m9637a();
                }
            }

            /* renamed from: a */
            void m9637a() {
                if (this.f18989e.compareAndSet(false, true)) {
                    this.f18985a.m9638a(this.f18986b, this.f18987c);
                }
            }

            @Override // p110z1.Observer
            public void onError(Throwable th) {
                if (this.f18988d) {
                    RxJavaPlugins.m9212a(th);
                    return;
                }
                this.f18988d = true;
                this.f18985a.onError(th);
            }

            @Override // p110z1.Observer
            public void onComplete() {
                if (!this.f18988d) {
                    this.f18988d = true;
                    m9637a();
                }
            }
        }
    }
}
