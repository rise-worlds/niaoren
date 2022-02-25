package p110z1;

/* renamed from: z1.bvf */
/* loaded from: classes3.dex */
public final class SerializedSubscriber<T> implements FlowableSubscriber<T>, dby {

    /* renamed from: c */
    static final int f20342c = 4;

    /* renamed from: a */
    final Subscriber<? super T> f20343a;

    /* renamed from: b */
    final boolean f20344b;

    /* renamed from: d */
    dby f20345d;

    /* renamed from: e */
    boolean f20346e;

    /* renamed from: f */
    AppendOnlyLinkedArrayList<Object> f20347f;

    /* renamed from: g */
    volatile boolean f20348g;

    public SerializedSubscriber(Subscriber<? super T> dbxVar) {
        this(dbxVar, false);
    }

    public SerializedSubscriber(Subscriber<? super T> dbxVar, boolean z) {
        this.f20343a = dbxVar;
        this.f20344b = z;
    }

    @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
    public void onSubscribe(dby dbyVar) {
        if (SubscriptionHelper.validate(this.f20345d, dbyVar)) {
            this.f20345d = dbyVar;
            this.f20343a.onSubscribe(this);
        }
    }

    @Override // p110z1.Subscriber
    public void onNext(T t) {
        if (!this.f20348g) {
            if (t == null) {
                this.f20345d.cancel();
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                return;
            }
            synchronized (this) {
                if (!this.f20348g) {
                    if (this.f20346e) {
                        AppendOnlyLinkedArrayList<Object> bslVar = this.f20347f;
                        if (bslVar == null) {
                            bslVar = new AppendOnlyLinkedArrayList<>(4);
                            this.f20347f = bslVar;
                        }
                        bslVar.m9456a((AppendOnlyLinkedArrayList<Object>) NotificationLite.next(t));
                        return;
                    }
                    this.f20346e = true;
                    this.f20343a.onNext(t);
                    m8921a();
                }
            }
        }
    }

    @Override // p110z1.Subscriber
    public void onError(Throwable th) {
        if (this.f20348g) {
            RxJavaPlugins.m9212a(th);
            return;
        }
        synchronized (this) {
            boolean z = true;
            if (!this.f20348g) {
                if (this.f20346e) {
                    this.f20348g = true;
                    AppendOnlyLinkedArrayList<Object> bslVar = this.f20347f;
                    if (bslVar == null) {
                        bslVar = new AppendOnlyLinkedArrayList<>(4);
                        this.f20347f = bslVar;
                    }
                    Object error = NotificationLite.error(th);
                    if (this.f20344b) {
                        bslVar.m9456a((AppendOnlyLinkedArrayList<Object>) error);
                    } else {
                        bslVar.m9451b(error);
                    }
                    return;
                }
                this.f20348g = true;
                this.f20346e = true;
                z = false;
            }
            if (z) {
                RxJavaPlugins.m9212a(th);
            } else {
                this.f20343a.onError(th);
            }
        }
    }

    @Override // p110z1.Subscriber
    public void onComplete() {
        if (!this.f20348g) {
            synchronized (this) {
                if (!this.f20348g) {
                    if (this.f20346e) {
                        AppendOnlyLinkedArrayList<Object> bslVar = this.f20347f;
                        if (bslVar == null) {
                            bslVar = new AppendOnlyLinkedArrayList<>(4);
                            this.f20347f = bslVar;
                        }
                        bslVar.m9456a((AppendOnlyLinkedArrayList<Object>) NotificationLite.complete());
                        return;
                    }
                    this.f20348g = true;
                    this.f20346e = true;
                    this.f20343a.onComplete();
                }
            }
        }
    }

    /* renamed from: a */
    void m8921a() {
        AppendOnlyLinkedArrayList<Object> bslVar;
        do {
            synchronized (this) {
                bslVar = this.f20347f;
                if (bslVar == null) {
                    this.f20346e = false;
                    return;
                }
                this.f20347f = null;
            }
        } while (!bslVar.m9452a((Subscriber<? super T>) this.f20343a));
    }

    @Override // p110z1.dby
    public void request(long j) {
        this.f20345d.request(j);
    }

    @Override // p110z1.dby
    public void cancel() {
        this.f20345d.cancel();
    }
}
