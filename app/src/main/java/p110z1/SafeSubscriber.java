package p110z1;

/* renamed from: z1.bve */
/* loaded from: classes3.dex */
public final class SafeSubscriber<T> implements FlowableSubscriber<T>, dby {

    /* renamed from: a */
    final Subscriber<? super T> f20339a;

    /* renamed from: b */
    dby f20340b;

    /* renamed from: c */
    boolean f20341c;

    public SafeSubscriber(Subscriber<? super T> dbxVar) {
        this.f20339a = dbxVar;
    }

    @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
    public void onSubscribe(dby dbyVar) {
        if (SubscriptionHelper.validate(this.f20340b, dbyVar)) {
            this.f20340b = dbyVar;
            try {
                this.f20339a.onSubscribe(this);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.f20341c = true;
                try {
                    dbyVar.cancel();
                    RxJavaPlugins.m9212a(th);
                } catch (Throwable th2) {
                    Exceptions.m9983b(th2);
                    RxJavaPlugins.m9212a(new CompositeException(th, th2));
                }
            }
        }
    }

    @Override // p110z1.Subscriber
    public void onNext(T t) {
        if (!this.f20341c) {
            if (this.f20340b == null) {
                m8923a();
            } else if (t == null) {
                NullPointerException nullPointerException = new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
                try {
                    this.f20340b.cancel();
                    onError(nullPointerException);
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    onError(new CompositeException(nullPointerException, th));
                }
            } else {
                try {
                    this.f20339a.onNext(t);
                } catch (Throwable th2) {
                    Exceptions.m9983b(th2);
                    try {
                        this.f20340b.cancel();
                        onError(th2);
                    } catch (Throwable th3) {
                        Exceptions.m9983b(th3);
                        onError(new CompositeException(th2, th3));
                    }
                }
            }
        }
    }

    /* renamed from: a */
    void m8923a() {
        this.f20341c = true;
        NullPointerException nullPointerException = new NullPointerException("Subscription not set!");
        try {
            this.f20339a.onSubscribe(EmptySubscription.INSTANCE);
            try {
                this.f20339a.onError(nullPointerException);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                RxJavaPlugins.m9212a(new CompositeException(nullPointerException, th));
            }
        } catch (Throwable th2) {
            Exceptions.m9983b(th2);
            RxJavaPlugins.m9212a(new CompositeException(nullPointerException, th2));
        }
    }

    @Override // p110z1.Subscriber
    public void onError(Throwable th) {
        if (this.f20341c) {
            RxJavaPlugins.m9212a(th);
            return;
        }
        this.f20341c = true;
        if (this.f20340b == null) {
            NullPointerException nullPointerException = new NullPointerException("Subscription not set!");
            try {
                this.f20339a.onSubscribe(EmptySubscription.INSTANCE);
                try {
                    this.f20339a.onError(new CompositeException(th, nullPointerException));
                } catch (Throwable th2) {
                    Exceptions.m9983b(th2);
                    RxJavaPlugins.m9212a(new CompositeException(th, nullPointerException, th2));
                }
            } catch (Throwable th3) {
                Exceptions.m9983b(th3);
                RxJavaPlugins.m9212a(new CompositeException(th, nullPointerException, th3));
            }
        } else {
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            try {
                this.f20339a.onError(th);
            } catch (Throwable th4) {
                Exceptions.m9983b(th4);
                RxJavaPlugins.m9212a(new CompositeException(th, th4));
            }
        }
    }

    @Override // p110z1.Subscriber
    public void onComplete() {
        if (!this.f20341c) {
            this.f20341c = true;
            if (this.f20340b == null) {
                m8922b();
                return;
            }
            try {
                this.f20339a.onComplete();
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                RxJavaPlugins.m9212a(th);
            }
        }
    }

    /* renamed from: b */
    void m8922b() {
        NullPointerException nullPointerException = new NullPointerException("Subscription not set!");
        try {
            this.f20339a.onSubscribe(EmptySubscription.INSTANCE);
            try {
                this.f20339a.onError(nullPointerException);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                RxJavaPlugins.m9212a(new CompositeException(nullPointerException, th));
            }
        } catch (Throwable th2) {
            Exceptions.m9983b(th2);
            RxJavaPlugins.m9212a(new CompositeException(nullPointerException, th2));
        }
    }

    @Override // p110z1.dby
    public void request(long j) {
        try {
            this.f20340b.request(j);
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            try {
                this.f20340b.cancel();
                RxJavaPlugins.m9212a(th);
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                RxJavaPlugins.m9212a(new CompositeException(th, th2));
            }
        }
    }

    @Override // p110z1.dby
    public void cancel() {
        try {
            this.f20340b.cancel();
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            RxJavaPlugins.m9212a(th);
        }
    }
}
