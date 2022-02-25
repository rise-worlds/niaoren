package p110z1;

import java.util.NoSuchElementException;

/* renamed from: z1.bjv */
/* loaded from: classes3.dex */
public final class ObservableElementAt<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final long f19058b;

    /* renamed from: c */
    final T f19059c;

    /* renamed from: d */
    final boolean f19060d;

    public ObservableElementAt(ObservableSource<T> asqVar, long j, T t, boolean z) {
        super(asqVar);
        this.f19058b = j;
        this.f19059c = t;
        this.f19060d = z;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new C4439a(assVar, this.f19058b, this.f19059c, this.f19060d));
    }

    /* compiled from: ObservableElementAt.java */
    /* renamed from: z1.bjv$a */
    /* loaded from: classes3.dex */
    static final class C4439a<T> implements Observer<T>, Disposable {

        /* renamed from: a */
        final Observer<? super T> f19061a;

        /* renamed from: b */
        final long f19062b;

        /* renamed from: c */
        final T f19063c;

        /* renamed from: d */
        final boolean f19064d;

        /* renamed from: e */
        Disposable f19065e;

        /* renamed from: f */
        long f19066f;

        /* renamed from: g */
        boolean f19067g;

        C4439a(Observer<? super T> assVar, long j, T t, boolean z) {
            this.f19061a = assVar;
            this.f19062b = j;
            this.f19063c = t;
            this.f19064d = z;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19065e, atrVar)) {
                this.f19065e = atrVar;
                this.f19061a.onSubscribe(this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19065e.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19065e.isDisposed();
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.f19067g) {
                long j = this.f19066f;
                if (j == this.f19062b) {
                    this.f19067g = true;
                    this.f19065e.dispose();
                    this.f19061a.onNext(t);
                    this.f19061a.onComplete();
                    return;
                }
                this.f19066f = j + 1;
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.f19067g) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19067g = true;
            this.f19061a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f19067g) {
                this.f19067g = true;
                T t = this.f19063c;
                if (t != null || !this.f19064d) {
                    if (t != null) {
                        this.f19061a.onNext(t);
                    }
                    this.f19061a.onComplete();
                    return;
                }
                this.f19061a.onError(new NoSuchElementException());
            }
        }
    }
}
