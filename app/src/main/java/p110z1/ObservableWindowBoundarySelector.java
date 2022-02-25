package p110z1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bnl */
/* loaded from: classes3.dex */
public final class ObservableWindowBoundarySelector<T, B, V> extends AbstractObservableWithUpstream<T, Observable<T>> {

    /* renamed from: b */
    final ObservableSource<B> f19489b;

    /* renamed from: c */
    final Function<? super B, ? extends ObservableSource<V>> f19490c;

    /* renamed from: d */
    final int f19491d;

    public ObservableWindowBoundarySelector(ObservableSource<T> asqVar, ObservableSource<B> asqVar2, Function<? super B, ? extends ObservableSource<V>> aunVar, int i) {
        super(asqVar);
        this.f19489b = asqVar2;
        this.f19490c = aunVar;
        this.f19491d = i;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super Observable<T>> assVar) {
        this.f18807a.subscribe(new C4600c(new SerializedObserver(assVar), this.f19489b, this.f19490c, this.f19491d));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableWindowBoundarySelector.java */
    /* renamed from: z1.bnl$c */
    /* loaded from: classes3.dex */
    public static final class C4600c<T, B, V> extends QueueDrainObserver<T, Object, Observable<T>> implements Disposable {

        /* renamed from: K */
        final ObservableSource<B> f19496K;

        /* renamed from: L */
        final Function<? super B, ? extends ObservableSource<V>> f19497L;

        /* renamed from: M */
        final int f19498M;

        /* renamed from: O */
        Disposable f19500O;

        /* renamed from: P */
        final AtomicReference<Disposable> f19501P = new AtomicReference<>();

        /* renamed from: R */
        final AtomicLong f19503R = new AtomicLong();

        /* renamed from: S */
        final AtomicBoolean f19504S = new AtomicBoolean();

        /* renamed from: N */
        final CompositeDisposable f19499N = new CompositeDisposable();

        /* renamed from: Q */
        final List<UnicastSubject<T>> f19502Q = new ArrayList();

        @Override // p110z1.QueueDrainObserver, p110z1.ObservableQueueDrain
        /* renamed from: a */
        public void mo9398a(Observer<? super Observable<T>> assVar, Object obj) {
        }

        C4600c(Observer<? super Observable<T>> assVar, ObservableSource<B> asqVar, Function<? super B, ? extends ObservableSource<V>> aunVar, int i) {
            super(assVar, new MpscLinkedQueue());
            this.f19496K = asqVar;
            this.f19497L = aunVar;
            this.f19498M = i;
            this.f19503R.lazySet(1L);
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19500O, atrVar)) {
                this.f19500O = atrVar;
                this.f17632a.onSubscribe(this);
                if (!this.f19504S.get()) {
                    C4599b bVar = new C4599b(this);
                    if (this.f19501P.compareAndSet(null, bVar)) {
                        this.f19496K.subscribe(bVar);
                    }
                }
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (m9841d()) {
                for (UnicastSubject<T> bvaVar : this.f19502Q) {
                    bvaVar.onNext(t);
                }
                if (mo9399a(-1) == 0) {
                    return;
                }
            } else {
                this.f17633b.offer(NotificationLite.next(t));
                if (!mo9396c()) {
                    return;
                }
            }
            m9567g();
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.f17635d) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f17636e = th;
            this.f17635d = true;
            if (mo9396c()) {
                m9567g();
            }
            if (this.f19503R.decrementAndGet() == 0) {
                this.f19499N.dispose();
            }
            this.f17632a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f17635d) {
                this.f17635d = true;
                if (mo9396c()) {
                    m9567g();
                }
                if (this.f19503R.decrementAndGet() == 0) {
                    this.f19499N.dispose();
                }
                this.f17632a.onComplete();
            }
        }

        /* renamed from: a */
        void m9570a(Throwable th) {
            this.f19500O.dispose();
            this.f19499N.dispose();
            onError(th);
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (this.f19504S.compareAndSet(false, true)) {
                DisposableHelper.dispose(this.f19501P);
                if (this.f19503R.decrementAndGet() == 0) {
                    this.f19500O.dispose();
                }
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19504S.get();
        }

        /* renamed from: f */
        void m9568f() {
            this.f19499N.dispose();
            DisposableHelper.dispose(this.f19501P);
        }

        /* renamed from: g */
        void m9567g() {
            MpscLinkedQueue bqjVar = (MpscLinkedQueue) this.f17633b;
            Observer<? super V> assVar = this.f17632a;
            List<UnicastSubject<T>> list = this.f19502Q;
            int i = 1;
            while (true) {
                boolean z = this.f17635d;
                Object poll = bqjVar.poll();
                boolean z2 = poll == null;
                if (z && z2) {
                    m9568f();
                    Throwable th = this.f17636e;
                    if (th != null) {
                        for (UnicastSubject<T> bvaVar : list) {
                            bvaVar.onError(th);
                        }
                    } else {
                        for (UnicastSubject<T> bvaVar2 : list) {
                            bvaVar2.onComplete();
                        }
                    }
                    list.clear();
                    return;
                } else if (z2) {
                    i = mo9399a(-i);
                    if (i == 0) {
                        return;
                    }
                } else if (poll instanceof C4601d) {
                    C4601d dVar = (C4601d) poll;
                    if (dVar.f19505a != null) {
                        if (list.remove(dVar.f19505a)) {
                            dVar.f19505a.onComplete();
                            if (this.f19503R.decrementAndGet() == 0) {
                                m9568f();
                                return;
                            }
                        } else {
                            continue;
                        }
                    } else if (!this.f19504S.get()) {
                        UnicastSubject<T> i2 = UnicastSubject.m8933i(this.f19498M);
                        list.add(i2);
                        assVar.onNext(i2);
                        try {
                            ObservableSource asqVar = (ObservableSource) ObjectHelper.m9873a(this.f19497L.apply((B) dVar.f19506b), "The ObservableSource supplied is null");
                            C4598a aVar = new C4598a(this, i2);
                            if (this.f19499N.mo9939a(aVar)) {
                                this.f19503R.getAndIncrement();
                                asqVar.subscribe(aVar);
                            }
                        } catch (Throwable th2) {
                            Exceptions.m9983b(th2);
                            this.f19504S.set(true);
                            assVar.onError(th2);
                        }
                    }
                } else {
                    for (UnicastSubject<T> bvaVar3 : list) {
                        bvaVar3.onNext((T) NotificationLite.getValue(poll));
                    }
                }
            }
        }

        /* renamed from: a */
        void m9571a(B b) {
            this.f17633b.offer(new C4601d(null, b));
            if (mo9396c()) {
                m9567g();
            }
        }

        /* renamed from: a */
        void m9569a(C4598a<T, V> aVar) {
            this.f19499N.mo9936c(aVar);
            this.f17633b.offer(new C4601d(aVar.f19493b, null));
            if (mo9396c()) {
                m9567g();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableWindowBoundarySelector.java */
    /* renamed from: z1.bnl$d */
    /* loaded from: classes3.dex */
    public static final class C4601d<T, B> {

        /* renamed from: a */
        final UnicastSubject<T> f19505a;

        /* renamed from: b */
        final B f19506b;

        C4601d(UnicastSubject<T> bvaVar, B b) {
            this.f19505a = bvaVar;
            this.f19506b = b;
        }
    }

    /* compiled from: ObservableWindowBoundarySelector.java */
    /* renamed from: z1.bnl$b */
    /* loaded from: classes3.dex */
    static final class C4599b<T, B> extends DisposableObserver<B> {

        /* renamed from: a */
        final C4600c<T, B, ?> f19495a;

        C4599b(C4600c<T, B, ?> cVar) {
            this.f19495a = cVar;
        }

        @Override // p110z1.Observer
        public void onNext(B b) {
            this.f19495a.m9571a((C4600c<T, B, ?>) b);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f19495a.m9570a(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f19495a.onComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableWindowBoundarySelector.java */
    /* renamed from: z1.bnl$a */
    /* loaded from: classes3.dex */
    public static final class C4598a<T, V> extends DisposableObserver<V> {

        /* renamed from: a */
        final C4600c<T, ?, V> f19492a;

        /* renamed from: b */
        final UnicastSubject<T> f19493b;

        /* renamed from: c */
        boolean f19494c;

        C4598a(C4600c<T, ?, V> cVar, UnicastSubject<T> bvaVar) {
            this.f19492a = cVar;
            this.f19493b = bvaVar;
        }

        @Override // p110z1.Observer
        public void onNext(V v) {
            dispose();
            onComplete();
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.f19494c) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19494c = true;
            this.f19492a.m9570a(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f19494c) {
                this.f19494c = true;
                this.f19492a.m9569a((C4598a) this);
            }
        }
    }
}
