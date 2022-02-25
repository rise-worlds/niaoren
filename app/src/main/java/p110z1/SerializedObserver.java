package p110z1;

/* renamed from: z1.bty */
/* loaded from: classes3.dex */
public final class SerializedObserver<T> implements Observer<T>, Disposable {

    /* renamed from: c */
    static final int f20133c = 4;

    /* renamed from: a */
    final Observer<? super T> f20134a;

    /* renamed from: b */
    final boolean f20135b;

    /* renamed from: d */
    Disposable f20136d;

    /* renamed from: e */
    boolean f20137e;

    /* renamed from: f */
    AppendOnlyLinkedArrayList<Object> f20138f;

    /* renamed from: g */
    volatile boolean f20139g;

    public SerializedObserver(@AbstractC3889atm Observer<? super T> assVar) {
        this(assVar, false);
    }

    public SerializedObserver(@AbstractC3889atm Observer<? super T> assVar, boolean z) {
        this.f20134a = assVar;
        this.f20135b = z;
    }

    @Override // p110z1.Observer
    public void onSubscribe(@AbstractC3889atm Disposable atrVar) {
        if (DisposableHelper.validate(this.f20136d, atrVar)) {
            this.f20136d = atrVar;
            this.f20134a.onSubscribe(this);
        }
    }

    @Override // p110z1.Disposable
    public void dispose() {
        this.f20136d.dispose();
    }

    @Override // p110z1.Disposable
    public boolean isDisposed() {
        return this.f20136d.isDisposed();
    }

    @Override // p110z1.Observer
    public void onNext(@AbstractC3889atm T t) {
        if (!this.f20139g) {
            if (t == null) {
                this.f20136d.dispose();
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                return;
            }
            synchronized (this) {
                if (!this.f20139g) {
                    if (this.f20137e) {
                        AppendOnlyLinkedArrayList<Object> bslVar = this.f20138f;
                        if (bslVar == null) {
                            bslVar = new AppendOnlyLinkedArrayList<>(4);
                            this.f20138f = bslVar;
                        }
                        bslVar.m9456a((AppendOnlyLinkedArrayList<Object>) NotificationLite.next(t));
                        return;
                    }
                    this.f20137e = true;
                    this.f20134a.onNext(t);
                    m9287a();
                }
            }
        }
    }

    @Override // p110z1.Observer
    public void onError(@AbstractC3889atm Throwable th) {
        if (this.f20139g) {
            RxJavaPlugins.m9212a(th);
            return;
        }
        synchronized (this) {
            boolean z = true;
            if (!this.f20139g) {
                if (this.f20137e) {
                    this.f20139g = true;
                    AppendOnlyLinkedArrayList<Object> bslVar = this.f20138f;
                    if (bslVar == null) {
                        bslVar = new AppendOnlyLinkedArrayList<>(4);
                        this.f20138f = bslVar;
                    }
                    Object error = NotificationLite.error(th);
                    if (this.f20135b) {
                        bslVar.m9456a((AppendOnlyLinkedArrayList<Object>) error);
                    } else {
                        bslVar.m9451b(error);
                    }
                    return;
                }
                this.f20139g = true;
                this.f20137e = true;
                z = false;
            }
            if (z) {
                RxJavaPlugins.m9212a(th);
            } else {
                this.f20134a.onError(th);
            }
        }
    }

    @Override // p110z1.Observer
    public void onComplete() {
        if (!this.f20139g) {
            synchronized (this) {
                if (!this.f20139g) {
                    if (this.f20137e) {
                        AppendOnlyLinkedArrayList<Object> bslVar = this.f20138f;
                        if (bslVar == null) {
                            bslVar = new AppendOnlyLinkedArrayList<>(4);
                            this.f20138f = bslVar;
                        }
                        bslVar.m9456a((AppendOnlyLinkedArrayList<Object>) NotificationLite.complete());
                        return;
                    }
                    this.f20139g = true;
                    this.f20137e = true;
                    this.f20134a.onComplete();
                }
            }
        }
    }

    /* renamed from: a */
    void m9287a() {
        AppendOnlyLinkedArrayList<Object> bslVar;
        do {
            synchronized (this) {
                bslVar = this.f20138f;
                if (bslVar == null) {
                    this.f20137e = false;
                    return;
                }
                this.f20138f = null;
            }
        } while (!bslVar.m9454a((Observer<? super T>) this.f20134a));
    }
}
