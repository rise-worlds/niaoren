package p110z1;

/* renamed from: z1.aws */
/* loaded from: classes3.dex */
public abstract class QueueDrainObserver<T, U, V> extends awu implements Observer<T>, ObservableQueueDrain<U, V> {

    /* renamed from: a */
    protected final Observer<? super V> f17632a;

    /* renamed from: b */
    protected final SimplePlainQueue<U> f17633b;

    /* renamed from: c */
    protected volatile boolean f17634c;

    /* renamed from: d */
    protected volatile boolean f17635d;

    /* renamed from: e */
    protected Throwable f17636e;

    @Override // p110z1.ObservableQueueDrain
    /* renamed from: a */
    public void mo9398a(Observer<? super V> assVar, U u) {
    }

    public QueueDrainObserver(Observer<? super V> assVar, SimplePlainQueue<U> avvVar) {
        this.f17632a = assVar;
        this.f17633b = avvVar;
    }

    @Override // p110z1.ObservableQueueDrain
    /* renamed from: a */
    public final boolean mo9400a() {
        return this.f17634c;
    }

    @Override // p110z1.ObservableQueueDrain
    /* renamed from: b */
    public final boolean mo9397b() {
        return this.f17635d;
    }

    @Override // p110z1.ObservableQueueDrain
    /* renamed from: c */
    public final boolean mo9396c() {
        return this.f17667J.getAndIncrement() == 0;
    }

    /* renamed from: d */
    public final boolean m9841d() {
        return this.f17667J.get() == 0 && this.f17667J.compareAndSet(0, 1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final void m9843a(U u, boolean z, Disposable atrVar) {
        Observer<? super V> assVar = this.f17632a;
        SimplePlainQueue<U> avvVar = this.f17633b;
        if (this.f17667J.get() != 0 || !this.f17667J.compareAndSet(0, 1)) {
            avvVar.offer(u);
            if (!mo9396c()) {
                return;
            }
        } else {
            mo9398a(assVar, u);
            if (mo9399a(-1) == 0) {
                return;
            }
        }
        QueueDrainHelper.m9374a(avvVar, assVar, z, atrVar, this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public final void m9842b(U u, boolean z, Disposable atrVar) {
        Observer<? super V> assVar = this.f17632a;
        SimplePlainQueue<U> avvVar = this.f17633b;
        if (this.f17667J.get() != 0 || !this.f17667J.compareAndSet(0, 1)) {
            avvVar.offer(u);
            if (!mo9396c()) {
                return;
            }
        } else if (avvVar.isEmpty()) {
            mo9398a(assVar, u);
            if (mo9399a(-1) == 0) {
                return;
            }
        } else {
            avvVar.offer(u);
        }
        QueueDrainHelper.m9374a(avvVar, assVar, z, atrVar, this);
    }

    @Override // p110z1.ObservableQueueDrain
    /* renamed from: e */
    public final Throwable mo9395e() {
        return this.f17636e;
    }

    @Override // p110z1.ObservableQueueDrain
    /* renamed from: a */
    public final int mo9399a(int i) {
        return this.f17667J.addAndGet(i);
    }
}
