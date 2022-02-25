package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.btz */
/* loaded from: classes3.dex */
public class TestObserver<T> extends BaseTestConsumer<T, TestObserver<T>> implements CompletableObserver, MaybeObserver<T>, Observer<T>, SingleObserver<T>, Disposable {

    /* renamed from: k */
    private final Observer<? super T> f20140k;

    /* renamed from: l */
    private final AtomicReference<Disposable> f20141l;

    /* renamed from: m */
    private QueueDisposable<T> f20142m;

    /* compiled from: TestObserver.java */
    /* renamed from: z1.btz$a */
    /* loaded from: classes3.dex */
    enum EnumC4758a implements Observer<Object> {
        INSTANCE;

        @Override // p110z1.Observer
        public void onComplete() {
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
        }

        @Override // p110z1.Observer
        public void onNext(Object obj) {
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
        }
    }

    /* renamed from: x */
    public static <T> TestObserver<T> m9276x() {
        return new TestObserver<>();
    }

    /* renamed from: a */
    public static <T> TestObserver<T> m9281a(Observer<? super T> assVar) {
        return new TestObserver<>(assVar);
    }

    public TestObserver() {
        this(EnumC4758a.INSTANCE);
    }

    public TestObserver(Observer<? super T> assVar) {
        this.f20141l = new AtomicReference<>();
        this.f20140k = assVar;
    }

    @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
    public void onSubscribe(Disposable atrVar) {
        this.f20111e = Thread.currentThread();
        if (atrVar == null) {
            this.f20109c.add(new NullPointerException("onSubscribe received a null Subscription"));
        } else if (!this.f20141l.compareAndSet(null, atrVar)) {
            atrVar.dispose();
            if (this.f20141l.get() != DisposableHelper.DISPOSED) {
                this.f20109c.add(new IllegalStateException("onSubscribe received multiple subscriptions: " + atrVar));
            }
        } else {
            if (this.f20113g != 0 && (atrVar instanceof QueueDisposable)) {
                this.f20142m = (QueueDisposable) atrVar;
                int requestFusion = this.f20142m.requestFusion(this.f20113g);
                this.f20114h = requestFusion;
                if (requestFusion == 1) {
                    this.f20112f = true;
                    this.f20111e = Thread.currentThread();
                    while (true) {
                        try {
                            T poll = this.f20142m.poll();
                            if (poll != null) {
                                this.f20108b.add(poll);
                            } else {
                                this.f20110d++;
                                this.f20141l.lazySet(DisposableHelper.DISPOSED);
                                return;
                            }
                        } catch (Throwable th) {
                            this.f20109c.add(th);
                            return;
                        }
                    }
                }
            }
            this.f20140k.onSubscribe(atrVar);
        }
    }

    @Override // p110z1.Observer
    public void onNext(T t) {
        if (!this.f20112f) {
            this.f20112f = true;
            if (this.f20141l.get() == null) {
                this.f20109c.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        this.f20111e = Thread.currentThread();
        if (this.f20114h == 2) {
            while (true) {
                try {
                    T poll = this.f20142m.poll();
                    if (poll != null) {
                        this.f20108b.add(poll);
                    } else {
                        return;
                    }
                } catch (Throwable th) {
                    this.f20109c.add(th);
                    this.f20142m.dispose();
                    return;
                }
            }
        } else {
            this.f20108b.add(t);
            if (t == null) {
                this.f20109c.add(new NullPointerException("onNext received a null value"));
            }
            this.f20140k.onNext(t);
        }
    }

    @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
    public void onError(Throwable th) {
        if (!this.f20112f) {
            this.f20112f = true;
            if (this.f20141l.get() == null) {
                this.f20109c.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        try {
            this.f20111e = Thread.currentThread();
            if (th == null) {
                this.f20109c.add(new NullPointerException("onError received a null Throwable"));
            } else {
                this.f20109c.add(th);
            }
            this.f20140k.onError(th);
        } finally {
            this.f20107a.countDown();
        }
    }

    @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
    public void onComplete() {
        if (!this.f20112f) {
            this.f20112f = true;
            if (this.f20141l.get() == null) {
                this.f20109c.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        try {
            this.f20111e = Thread.currentThread();
            this.f20110d++;
            this.f20140k.onComplete();
        } finally {
            this.f20107a.countDown();
        }
    }

    /* renamed from: y */
    public final boolean m9275y() {
        return isDisposed();
    }

    /* renamed from: z */
    public final void m9274z() {
        dispose();
    }

    @Override // p110z1.Disposable
    public final void dispose() {
        DisposableHelper.dispose(this.f20141l);
    }

    @Override // p110z1.Disposable
    public final boolean isDisposed() {
        return DisposableHelper.isDisposed(this.f20141l.get());
    }

    /* renamed from: A */
    public final boolean m9286A() {
        return this.f20141l.get() != null;
    }

    /* renamed from: B */
    public final TestObserver<T> mo8908q() {
        if (this.f20141l.get() != null) {
            return this;
        }
        throw m9341a("Not subscribed!");
    }

    /* renamed from: C */
    public final TestObserver<T> mo8907r() {
        if (this.f20141l.get() != null) {
            throw m9341a("Subscribed!");
        } else if (this.f20109c.isEmpty()) {
            return this;
        } else {
            throw m9341a("Not subscribed but errors found");
        }
    }

    /* renamed from: a */
    public final TestObserver<T> m9280a(Consumer<? super TestObserver<T>> aumVar) {
        try {
            aumVar.accept(this);
            return this;
        } catch (Throwable th) {
            throw ExceptionHelper.m9432a(th);
        }
    }

    /* renamed from: c */
    final TestObserver<T> m9279c(int i) {
        this.f20113g = i;
        return this;
    }

    /* renamed from: d */
    final TestObserver<T> m9278d(int i) {
        int i2 = this.f20114h;
        if (i2 == i) {
            return this;
        }
        if (this.f20142m != null) {
            throw new AssertionError("Fusion mode different. Expected: " + m9277e(i) + ", actual: " + m9277e(i2));
        }
        throw m9341a("Upstream is not fuseable");
    }

    /* renamed from: e */
    static String m9277e(int i) {
        switch (i) {
            case 0:
                return "NONE";
            case 1:
                return "SYNC";
            case 2:
                return "ASYNC";
            default:
                return "Unknown(" + i + ")";
        }
    }

    /* renamed from: D */
    final TestObserver<T> m9283D() {
        if (this.f20142m != null) {
            return this;
        }
        throw new AssertionError("Upstream is not fuseable.");
    }

    /* renamed from: E */
    final TestObserver<T> m9282E() {
        if (this.f20142m == null) {
            return this;
        }
        throw new AssertionError("Upstream is fuseable.");
    }

    @Override // p110z1.MaybeObserver, p110z1.SingleObserver
    public void onSuccess(T t) {
        onNext(t);
        onComplete();
    }
}
