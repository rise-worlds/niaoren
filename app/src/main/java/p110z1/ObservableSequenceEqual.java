package p110z1;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.bmf */
/* loaded from: classes3.dex */
public final class ObservableSequenceEqual<T> extends Observable<Boolean> {

    /* renamed from: a */
    final ObservableSource<? extends T> f19342a;

    /* renamed from: b */
    final ObservableSource<? extends T> f19343b;

    /* renamed from: c */
    final BiPredicate<? super T, ? super T> f19344c;

    /* renamed from: d */
    final int f19345d;

    public ObservableSequenceEqual(ObservableSource<? extends T> asqVar, ObservableSource<? extends T> asqVar2, BiPredicate<? super T, ? super T> aujVar, int i) {
        this.f19342a = asqVar;
        this.f19343b = asqVar2;
        this.f19344c = aujVar;
        this.f19345d = i;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super Boolean> assVar) {
        C4550a aVar = new C4550a(assVar, this.f19345d, this.f19342a, this.f19343b, this.f19344c);
        assVar.onSubscribe(aVar);
        aVar.subscribe();
    }

    /* compiled from: ObservableSequenceEqual.java */
    /* renamed from: z1.bmf$a */
    /* loaded from: classes3.dex */
    static final class C4550a<T> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = -6178010334400373240L;
        volatile boolean cancelled;
        final BiPredicate<? super T, ? super T> comparer;
        final Observer<? super Boolean> downstream;
        final ObservableSource<? extends T> first;
        final C4551b<T>[] observers;
        final ArrayCompositeDisposable resources = new ArrayCompositeDisposable(2);
        final ObservableSource<? extends T> second;

        /* renamed from: v1 */
        T f19346v1;

        /* renamed from: v2 */
        T f19347v2;

        C4550a(Observer<? super Boolean> assVar, int i, ObservableSource<? extends T> asqVar, ObservableSource<? extends T> asqVar2, BiPredicate<? super T, ? super T> aujVar) {
            this.downstream = assVar;
            this.first = asqVar;
            this.second = asqVar2;
            this.comparer = aujVar;
            this.observers = r3;
            C4551b<T>[] bVarArr = {new C4551b<>(this, 0, i), new C4551b<>(this, 1, i)};
        }

        boolean setDisposable(Disposable atrVar, int i) {
            return this.resources.setResource(i, atrVar);
        }

        void subscribe() {
            C4551b<T>[] bVarArr = this.observers;
            this.first.subscribe(bVarArr[0]);
            this.second.subscribe(bVarArr[1]);
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.resources.dispose();
                if (getAndIncrement() == 0) {
                    C4551b<T>[] bVarArr = this.observers;
                    bVarArr[0].f19349b.clear();
                    bVarArr[1].f19349b.clear();
                }
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        void cancel(SpscLinkedArrayQueue<T> bqlVar, SpscLinkedArrayQueue<T> bqlVar2) {
            this.cancelled = true;
            bqlVar.clear();
            bqlVar2.clear();
        }

        void drain() {
            Throwable th;
            Throwable th2;
            if (getAndIncrement() == 0) {
                C4551b<T>[] bVarArr = this.observers;
                C4551b<T> bVar = bVarArr[0];
                SpscLinkedArrayQueue<T> bqlVar = bVar.f19349b;
                C4551b<T> bVar2 = bVarArr[1];
                SpscLinkedArrayQueue<T> bqlVar2 = bVar2.f19349b;
                int i = 1;
                while (!this.cancelled) {
                    boolean z = bVar.f19351d;
                    if (!z || (th2 = bVar.f19352e) == null) {
                        boolean z2 = bVar2.f19351d;
                        if (!z2 || (th = bVar2.f19352e) == null) {
                            if (this.f19346v1 == null) {
                                this.f19346v1 = bqlVar.poll();
                            }
                            boolean z3 = this.f19346v1 == null;
                            if (this.f19347v2 == null) {
                                this.f19347v2 = bqlVar2.poll();
                            }
                            boolean z4 = this.f19347v2 == null;
                            if (z && z2 && z3 && z4) {
                                this.downstream.onNext(true);
                                this.downstream.onComplete();
                                return;
                            } else if (!z || !z2 || z3 == z4) {
                                if (!z3 && !z4) {
                                    try {
                                        if (!this.comparer.mo9871a((T) this.f19346v1, (T) this.f19347v2)) {
                                            cancel(bqlVar, bqlVar2);
                                            this.downstream.onNext(false);
                                            this.downstream.onComplete();
                                            return;
                                        }
                                        this.f19346v1 = null;
                                        this.f19347v2 = null;
                                    } catch (Throwable th3) {
                                        Exceptions.m9983b(th3);
                                        cancel(bqlVar, bqlVar2);
                                        this.downstream.onError(th3);
                                        return;
                                    }
                                }
                                if (z3 || z4) {
                                    i = addAndGet(-i);
                                    if (i == 0) {
                                        return;
                                    }
                                }
                            } else {
                                cancel(bqlVar, bqlVar2);
                                this.downstream.onNext(false);
                                this.downstream.onComplete();
                                return;
                            }
                        } else {
                            cancel(bqlVar, bqlVar2);
                            this.downstream.onError(th);
                            return;
                        }
                    } else {
                        cancel(bqlVar, bqlVar2);
                        this.downstream.onError(th2);
                        return;
                    }
                }
                bqlVar.clear();
                bqlVar2.clear();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableSequenceEqual.java */
    /* renamed from: z1.bmf$b */
    /* loaded from: classes3.dex */
    public static final class C4551b<T> implements Observer<T> {

        /* renamed from: a */
        final C4550a<T> f19348a;

        /* renamed from: b */
        final SpscLinkedArrayQueue<T> f19349b;

        /* renamed from: c */
        final int f19350c;

        /* renamed from: d */
        volatile boolean f19351d;

        /* renamed from: e */
        Throwable f19352e;

        C4551b(C4550a<T> aVar, int i, int i2) {
            this.f19348a = aVar;
            this.f19350c = i;
            this.f19349b = new SpscLinkedArrayQueue<>(i2);
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            this.f19348a.setDisposable(atrVar, this.f19350c);
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.f19349b.offer(t);
            this.f19348a.drain();
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f19352e = th;
            this.f19351d = true;
            this.f19348a.drain();
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f19351d = true;
            this.f19348a.drain();
        }
    }
}
