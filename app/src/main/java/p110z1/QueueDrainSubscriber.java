package p110z1;

/* renamed from: z1.brs */
/* loaded from: classes3.dex */
public abstract class QueueDrainSubscriber<T, U, V> extends brw implements FlowableSubscriber<T>, QueueDrain<U, V> {

    /* renamed from: n */
    protected final Subscriber<? super V> f20003n;

    /* renamed from: o */
    protected final SimplePlainQueue<U> f20004o;

    /* renamed from: p */
    protected volatile boolean f20005p;

    /* renamed from: q */
    protected volatile boolean f20006q;

    /* renamed from: r */
    protected Throwable f20007r;

    /* renamed from: a */
    public boolean mo9383a(Subscriber<? super V> dbxVar, U u) {
        return false;
    }

    public QueueDrainSubscriber(Subscriber<? super V> dbxVar, SimplePlainQueue<U> avvVar) {
        this.f20003n = dbxVar;
        this.f20004o = avvVar;
    }

    @Override // p110z1.QueueDrain
    /* renamed from: c */
    public final boolean mo9382c() {
        return this.f20005p;
    }

    @Override // p110z1.QueueDrain
    /* renamed from: d */
    public final boolean mo9381d() {
        return this.f20006q;
    }

    @Override // p110z1.QueueDrain
    /* renamed from: e */
    public final boolean mo9380e() {
        return this.f20054am.getAndIncrement() == 0;
    }

    /* renamed from: f */
    public final boolean m9464f() {
        return this.f20054am.get() == 0 && this.f20054am.compareAndSet(0, 1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final void m9467a(U u, boolean z, Disposable atrVar) {
        Subscriber<? super V> dbxVar = this.f20003n;
        SimplePlainQueue<U> avvVar = this.f20004o;
        if (m9464f()) {
            long j = this.f20038W.get();
            if (j != 0) {
                if (mo9383a(dbxVar, u) && j != cjm.f20759b) {
                    mo9384a(1L);
                }
                if (mo9385a(-1) == 0) {
                    return;
                }
            } else {
                atrVar.dispose();
                dbxVar.onError(new MissingBackpressureException("Could not emit buffer due to lack of requests"));
                return;
            }
        } else {
            avvVar.offer(u);
            if (!mo9380e()) {
                return;
            }
        }
        QueueDrainHelper.m9373a(avvVar, dbxVar, z, atrVar, this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public final void m9465b(U u, boolean z, Disposable atrVar) {
        Subscriber<? super V> dbxVar = this.f20003n;
        SimplePlainQueue<U> avvVar = this.f20004o;
        if (m9464f()) {
            long j = this.f20038W.get();
            if (j == 0) {
                this.f20005p = true;
                atrVar.dispose();
                dbxVar.onError(new MissingBackpressureException("Could not emit buffer due to lack of requests"));
                return;
            } else if (avvVar.isEmpty()) {
                if (mo9383a(dbxVar, u) && j != cjm.f20759b) {
                    mo9384a(1L);
                }
                if (mo9385a(-1) == 0) {
                    return;
                }
            } else {
                avvVar.offer(u);
            }
        } else {
            avvVar.offer(u);
            if (!mo9380e()) {
                return;
            }
        }
        QueueDrainHelper.m9373a(avvVar, dbxVar, z, atrVar, this);
    }

    @Override // p110z1.QueueDrain
    /* renamed from: g */
    public final Throwable mo9379g() {
        return this.f20007r;
    }

    @Override // p110z1.QueueDrain
    /* renamed from: a */
    public final int mo9385a(int i) {
        return this.f20054am.addAndGet(i);
    }

    @Override // p110z1.QueueDrain
    /* renamed from: h */
    public final long mo9378h() {
        return this.f20038W.get();
    }

    @Override // p110z1.QueueDrain
    /* renamed from: a */
    public final long mo9384a(long j) {
        return this.f20038W.addAndGet(-j);
    }

    /* renamed from: b */
    public final void m9466b(long j) {
        if (SubscriptionHelper.validate(j)) {
            BackpressureHelper.m9449a(this.f20038W, j);
        }
    }
}
