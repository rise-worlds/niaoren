package p110z1;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bko */
/* loaded from: classes3.dex */
public final class ObservableGroupBy<T, K, V> extends AbstractObservableWithUpstream<T, GroupedObservable<K, V>> {

    /* renamed from: b */
    final Function<? super T, ? extends K> f19156b;

    /* renamed from: c */
    final Function<? super T, ? extends V> f19157c;

    /* renamed from: d */
    final int f19158d;

    /* renamed from: e */
    final boolean f19159e;

    public ObservableGroupBy(ObservableSource<T> asqVar, Function<? super T, ? extends K> aunVar, Function<? super T, ? extends V> aunVar2, int i, boolean z) {
        super(asqVar);
        this.f19156b = aunVar;
        this.f19157c = aunVar2;
        this.f19158d = i;
        this.f19159e = z;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super GroupedObservable<K, V>> assVar) {
        this.f18807a.subscribe(new C4459a(assVar, this.f19156b, this.f19157c, this.f19158d, this.f19159e));
    }

    /* compiled from: ObservableGroupBy.java */
    /* renamed from: z1.bko$a */
    /* loaded from: classes3.dex */
    public static final class C4459a<T, K, V> extends AtomicInteger implements Observer<T>, Disposable {
        static final Object NULL_KEY = new Object();
        private static final long serialVersionUID = -3688291656102519502L;
        final int bufferSize;
        final boolean delayError;
        final Observer<? super GroupedObservable<K, V>> downstream;
        final Function<? super T, ? extends K> keySelector;
        Disposable upstream;
        final Function<? super T, ? extends V> valueSelector;
        final AtomicBoolean cancelled = new AtomicBoolean();
        final Map<Object, C4460b<K, V>> groups = new ConcurrentHashMap();

        public C4459a(Observer<? super GroupedObservable<K, V>> assVar, Function<? super T, ? extends K> aunVar, Function<? super T, ? extends V> aunVar2, int i, boolean z) {
            this.downstream = assVar;
            this.keySelector = aunVar;
            this.valueSelector = aunVar2;
            this.bufferSize = i;
            this.delayError = z;
            lazySet(1);
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p110z1.Observer
        public void onNext(T t) {
            try {
                Object apply = this.keySelector.apply(t);
                Object obj = apply != null ? apply : NULL_KEY;
                C4460b<K, V> bVar = this.groups.get(obj);
                if (bVar == null) {
                    if (!this.cancelled.get()) {
                        bVar = C4460b.m9622a(apply, this.bufferSize, (C4459a<?, Object, T>) this, this.delayError);
                        this.groups.put(obj, bVar);
                        getAndIncrement();
                        this.downstream.onNext(bVar);
                    } else {
                        return;
                    }
                }
                try {
                    bVar.m9620m((C4460b) ObjectHelper.m9873a(this.valueSelector.apply(t), "The value supplied is null"));
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.upstream.dispose();
                    onError(th);
                }
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                this.upstream.dispose();
                onError(th2);
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            ArrayList<C4460b> arrayList = new ArrayList(this.groups.values());
            this.groups.clear();
            for (C4460b bVar : arrayList) {
                bVar.m9621b(th);
            }
            this.downstream.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            ArrayList<C4460b> arrayList = new ArrayList(this.groups.values());
            this.groups.clear();
            for (C4460b bVar : arrayList) {
                bVar.m9623a();
            }
            this.downstream.onComplete();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (this.cancelled.compareAndSet(false, true) && decrementAndGet() == 0) {
                this.upstream.dispose();
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.cancelled.get();
        }

        public void cancel(K k) {
            if (k == null) {
                k = (K) NULL_KEY;
            }
            this.groups.remove(k);
            if (decrementAndGet() == 0) {
                this.upstream.dispose();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableGroupBy.java */
    /* renamed from: z1.bko$b */
    /* loaded from: classes3.dex */
    public static final class C4460b<K, T> extends GroupedObservable<K, T> {

        /* renamed from: a */
        final C4461c<T, K> f19160a;

        /* renamed from: a */
        public static <T, K> C4460b<K, T> m9622a(K k, int i, C4459a<?, K, T> aVar, boolean z) {
            return new C4460b<>(k, new C4461c(i, aVar, k, z));
        }

        protected C4460b(K k, C4461c<T, K> cVar) {
            super(k);
            this.f19160a = cVar;
        }

        @Override // p110z1.Observable
        /* renamed from: a */
        protected void mo34a(Observer<? super T> assVar) {
            this.f19160a.subscribe(assVar);
        }

        /* renamed from: m */
        public void m9620m(T t) {
            this.f19160a.onNext(t);
        }

        /* renamed from: b */
        public void m9621b(Throwable th) {
            this.f19160a.onError(th);
        }

        /* renamed from: a */
        public void m9623a() {
            this.f19160a.onComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableGroupBy.java */
    /* renamed from: z1.bko$c */
    /* loaded from: classes3.dex */
    public static final class C4461c<T, K> extends AtomicInteger implements ObservableSource<T>, Disposable {
        private static final long serialVersionUID = -3852313036005250360L;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final K key;
        final C4459a<?, K, T> parent;
        final SpscLinkedArrayQueue<T> queue;
        final AtomicBoolean cancelled = new AtomicBoolean();
        final AtomicBoolean once = new AtomicBoolean();
        final AtomicReference<Observer<? super T>> actual = new AtomicReference<>();

        C4461c(int i, C4459a<?, K, T> aVar, K k, boolean z) {
            this.queue = new SpscLinkedArrayQueue<>(i);
            this.parent = aVar;
            this.key = k;
            this.delayError = z;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (this.cancelled.compareAndSet(false, true) && getAndIncrement() == 0) {
                this.actual.lazySet(null);
                this.parent.cancel(this.key);
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.cancelled.get();
        }

        @Override // p110z1.ObservableSource
        public void subscribe(Observer<? super T> assVar) {
            if (this.once.compareAndSet(false, true)) {
                assVar.onSubscribe(this);
                this.actual.lazySet(assVar);
                if (this.cancelled.get()) {
                    this.actual.lazySet(null);
                } else {
                    drain();
                }
            } else {
                EmptyDisposable.error(new IllegalStateException("Only one Observer allowed!"), assVar);
            }
        }

        public void onNext(T t) {
            this.queue.offer(t);
            drain();
        }

        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            drain();
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        void drain() {
            if (getAndIncrement() == 0) {
                SpscLinkedArrayQueue<T> bqlVar = this.queue;
                boolean z = this.delayError;
                Observer<? super T> assVar = this.actual.get();
                int i = 1;
                while (true) {
                    if (assVar != null) {
                        while (true) {
                            boolean z2 = this.done;
                            Object obj = (T) bqlVar.poll();
                            boolean z3 = obj == null;
                            if (!checkTerminated(z2, z3, assVar, z)) {
                                if (z3) {
                                    break;
                                }
                                assVar.onNext(obj);
                            } else {
                                return;
                            }
                        }
                    }
                    i = addAndGet(-i);
                    if (i != 0) {
                        if (assVar == null) {
                            assVar = this.actual.get();
                        }
                    } else {
                        return;
                    }
                }
            }
        }

        boolean checkTerminated(boolean z, boolean z2, Observer<? super T> assVar, boolean z3) {
            if (this.cancelled.get()) {
                this.queue.clear();
                this.parent.cancel(this.key);
                this.actual.lazySet(null);
                return true;
            } else if (!z) {
                return false;
            } else {
                if (!z3) {
                    Throwable th = this.error;
                    if (th != null) {
                        this.queue.clear();
                        this.actual.lazySet(null);
                        assVar.onError(th);
                        return true;
                    } else if (!z2) {
                        return false;
                    } else {
                        this.actual.lazySet(null);
                        assVar.onComplete();
                        return true;
                    }
                } else if (!z2) {
                    return false;
                } else {
                    Throwable th2 = this.error;
                    this.actual.lazySet(null);
                    if (th2 != null) {
                        assVar.onError(th2);
                    } else {
                        assVar.onComplete();
                    }
                    return true;
                }
            }
        }
    }
}
