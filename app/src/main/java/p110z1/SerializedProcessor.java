package p110z1;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.bul */
/* loaded from: classes3.dex */
public final class SerializedProcessor<T> extends FlowableProcessor<T> {

    /* renamed from: b */
    final FlowableProcessor<T> f20229b;

    /* renamed from: c */
    boolean f20230c;

    /* renamed from: d */
    AppendOnlyLinkedArrayList<Object> f20231d;

    /* renamed from: e */
    volatile boolean f20232e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SerializedProcessor(FlowableProcessor<T> buhVar) {
        this.f20229b = buhVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f20229b.subscribe(dbxVar);
    }

    @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
    public void onSubscribe(dby dbyVar) {
        boolean z = true;
        if (!this.f20232e) {
            synchronized (this) {
                if (!this.f20232e) {
                    if (this.f20230c) {
                        AppendOnlyLinkedArrayList<Object> bslVar = this.f20231d;
                        if (bslVar == null) {
                            bslVar = new AppendOnlyLinkedArrayList<>(4);
                            this.f20231d = bslVar;
                        }
                        bslVar.m9456a((AppendOnlyLinkedArrayList<Object>) NotificationLite.subscription(dbyVar));
                        return;
                    }
                    this.f20230c = true;
                    z = false;
                }
            }
        }
        if (z) {
            dbyVar.cancel();
            return;
        }
        this.f20229b.onSubscribe(dbyVar);
        m9066T();
    }

    @Override // p110z1.Subscriber
    public void onNext(T t) {
        if (!this.f20232e) {
            synchronized (this) {
                if (!this.f20232e) {
                    if (this.f20230c) {
                        AppendOnlyLinkedArrayList<Object> bslVar = this.f20231d;
                        if (bslVar == null) {
                            bslVar = new AppendOnlyLinkedArrayList<>(4);
                            this.f20231d = bslVar;
                        }
                        bslVar.m9456a((AppendOnlyLinkedArrayList<Object>) NotificationLite.next(t));
                        return;
                    }
                    this.f20230c = true;
                    this.f20229b.onNext(t);
                    m9066T();
                }
            }
        }
    }

    @Override // p110z1.Subscriber
    public void onError(Throwable th) {
        boolean z;
        if (this.f20232e) {
            RxJavaPlugins.m9212a(th);
            return;
        }
        synchronized (this) {
            if (this.f20232e) {
                z = true;
            } else {
                this.f20232e = true;
                if (this.f20230c) {
                    AppendOnlyLinkedArrayList<Object> bslVar = this.f20231d;
                    if (bslVar == null) {
                        bslVar = new AppendOnlyLinkedArrayList<>(4);
                        this.f20231d = bslVar;
                    }
                    bslVar.m9451b(NotificationLite.error(th));
                    return;
                }
                z = false;
                this.f20230c = true;
            }
            if (z) {
                RxJavaPlugins.m9212a(th);
            } else {
                this.f20229b.onError(th);
            }
        }
    }

    @Override // p110z1.Subscriber
    public void onComplete() {
        if (!this.f20232e) {
            synchronized (this) {
                if (!this.f20232e) {
                    this.f20232e = true;
                    if (this.f20230c) {
                        AppendOnlyLinkedArrayList<Object> bslVar = this.f20231d;
                        if (bslVar == null) {
                            bslVar = new AppendOnlyLinkedArrayList<>(4);
                            this.f20231d = bslVar;
                        }
                        bslVar.m9456a((AppendOnlyLinkedArrayList<Object>) NotificationLite.complete());
                        return;
                    }
                    this.f20230c = true;
                    this.f20229b.onComplete();
                }
            }
        }
    }

    /* renamed from: T */
    void m9066T() {
        AppendOnlyLinkedArrayList<Object> bslVar;
        while (true) {
            synchronized (this) {
                bslVar = this.f20231d;
                if (bslVar == null) {
                    this.f20230c = false;
                    return;
                }
                this.f20231d = null;
            }
            bslVar.m9452a((Subscriber) this.f20229b);
        }
    }

    @Override // p110z1.FlowableProcessor
    /* renamed from: U */
    public boolean mo9064U() {
        return this.f20229b.mo9064U();
    }

    @Override // p110z1.FlowableProcessor
    /* renamed from: V */
    public boolean mo9063V() {
        return this.f20229b.mo9063V();
    }

    @Override // p110z1.FlowableProcessor
    @atn
    /* renamed from: X */
    public Throwable mo9061X() {
        return this.f20229b.mo9061X();
    }

    @Override // p110z1.FlowableProcessor
    /* renamed from: W */
    public boolean mo9062W() {
        return this.f20229b.mo9062W();
    }
}
