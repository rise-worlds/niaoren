package p110z1;

import p110z1.AppendOnlyLinkedArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.bux */
/* loaded from: classes3.dex */
public final class SerializedSubject<T> extends Subject<T> implements AppendOnlyLinkedArrayList.AbstractC4743a<Object> {

    /* renamed from: a */
    final Subject<T> f20311a;

    /* renamed from: b */
    boolean f20312b;

    /* renamed from: c */
    AppendOnlyLinkedArrayList<Object> f20313c;

    /* renamed from: d */
    volatile boolean f20314d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SerializedSubject(Subject<T> buzVar) {
        this.f20311a = buzVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        this.f20311a.subscribe(assVar);
    }

    @Override // p110z1.Observer
    public void onSubscribe(Disposable atrVar) {
        boolean z = true;
        if (!this.f20314d) {
            synchronized (this) {
                if (!this.f20314d) {
                    if (this.f20312b) {
                        AppendOnlyLinkedArrayList<Object> bslVar = this.f20313c;
                        if (bslVar == null) {
                            bslVar = new AppendOnlyLinkedArrayList<>(4);
                            this.f20313c = bslVar;
                        }
                        bslVar.m9456a((AppendOnlyLinkedArrayList<Object>) NotificationLite.disposable(atrVar));
                        return;
                    }
                    this.f20312b = true;
                    z = false;
                }
            }
        }
        if (z) {
            atrVar.dispose();
            return;
        }
        this.f20311a.onSubscribe(atrVar);
        m8963a();
    }

    @Override // p110z1.Observer
    public void onNext(T t) {
        if (!this.f20314d) {
            synchronized (this) {
                if (!this.f20314d) {
                    if (this.f20312b) {
                        AppendOnlyLinkedArrayList<Object> bslVar = this.f20313c;
                        if (bslVar == null) {
                            bslVar = new AppendOnlyLinkedArrayList<>(4);
                            this.f20313c = bslVar;
                        }
                        bslVar.m9456a((AppendOnlyLinkedArrayList<Object>) NotificationLite.next(t));
                        return;
                    }
                    this.f20312b = true;
                    this.f20311a.onNext(t);
                    m8963a();
                }
            }
        }
    }

    @Override // p110z1.Observer
    public void onError(Throwable th) {
        boolean z;
        if (this.f20314d) {
            RxJavaPlugins.m9212a(th);
            return;
        }
        synchronized (this) {
            if (this.f20314d) {
                z = true;
            } else {
                this.f20314d = true;
                if (this.f20312b) {
                    AppendOnlyLinkedArrayList<Object> bslVar = this.f20313c;
                    if (bslVar == null) {
                        bslVar = new AppendOnlyLinkedArrayList<>(4);
                        this.f20313c = bslVar;
                    }
                    bslVar.m9451b(NotificationLite.error(th));
                    return;
                }
                z = false;
                this.f20312b = true;
            }
            if (z) {
                RxJavaPlugins.m9212a(th);
            } else {
                this.f20311a.onError(th);
            }
        }
    }

    @Override // p110z1.Observer
    public void onComplete() {
        if (!this.f20314d) {
            synchronized (this) {
                if (!this.f20314d) {
                    this.f20314d = true;
                    if (this.f20312b) {
                        AppendOnlyLinkedArrayList<Object> bslVar = this.f20313c;
                        if (bslVar == null) {
                            bslVar = new AppendOnlyLinkedArrayList<>(4);
                            this.f20313c = bslVar;
                        }
                        bslVar.m9456a((AppendOnlyLinkedArrayList<Object>) NotificationLite.complete());
                        return;
                    }
                    this.f20312b = true;
                    this.f20311a.onComplete();
                }
            }
        }
    }

    /* renamed from: a */
    void m8963a() {
        AppendOnlyLinkedArrayList<Object> bslVar;
        while (true) {
            synchronized (this) {
                bslVar = this.f20313c;
                if (bslVar == null) {
                    this.f20312b = false;
                    return;
                }
                this.f20313c = null;
            }
            bslVar.m9453a((AppendOnlyLinkedArrayList.AbstractC4743a<? super Object>) this);
        }
    }

    @Override // p110z1.AppendOnlyLinkedArrayList.AbstractC4743a, p110z1.Predicate
    public boolean test(Object obj) {
        return NotificationLite.acceptFull(obj, this.f20311a);
    }

    @Override // p110z1.Subject
    /* renamed from: b */
    public boolean mo8939b() {
        return this.f20311a.mo8939b();
    }

    @Override // p110z1.Subject
    /* renamed from: c */
    public boolean mo8936c() {
        return this.f20311a.mo8936c();
    }

    @Override // p110z1.Subject
    @atn
    /* renamed from: S */
    public Throwable mo8946S() {
        return this.f20311a.mo8946S();
    }

    @Override // p110z1.Subject
    /* renamed from: R */
    public boolean mo8947R() {
        return this.f20311a.mo8947R();
    }
}
