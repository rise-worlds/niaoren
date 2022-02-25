package p110z1;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.bmg */
/* loaded from: classes3.dex */
public final class ObservableSequenceEqualSingle<T> extends Single<Boolean> implements FuseToObservable<Boolean> {

    /* renamed from: a */
    final ObservableSource<? extends T> f19353a;

    /* renamed from: b */
    final ObservableSource<? extends T> f19354b;

    /* renamed from: c */
    final BiPredicate<? super T, ? super T> f19355c;

    /* renamed from: d */
    final int f19356d;

    public ObservableSequenceEqualSingle(ObservableSource<? extends T> asqVar, ObservableSource<? extends T> asqVar2, BiPredicate<? super T, ? super T> aujVar, int i) {
        this.f19353a = asqVar;
        this.f19354b = asqVar2;
        this.f19355c = aujVar;
        this.f19356d = i;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    public void mo8961b(SingleObserver<? super Boolean> asxVar) {
        C4552a aVar = new C4552a(asxVar, this.f19356d, this.f19353a, this.f19354b, this.f19355c);
        asxVar.onSubscribe(aVar);
        aVar.subscribe();
    }

    @Override // p110z1.FuseToObservable
    /* renamed from: w_ */
    public Observable<Boolean> mo9572w_() {
        return RxJavaPlugins.m9203a(new ObservableSequenceEqual(this.f19353a, this.f19354b, this.f19355c, this.f19356d));
    }

    /* compiled from: ObservableSequenceEqualSingle.java */
    /* renamed from: z1.bmg$a */
    /* loaded from: classes3.dex */
    static final class C4552a<T> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = -6178010334400373240L;
        volatile boolean cancelled;
        final BiPredicate<? super T, ? super T> comparer;
        final SingleObserver<? super Boolean> downstream;
        final ObservableSource<? extends T> first;
        final C4553b<T>[] observers;
        final ArrayCompositeDisposable resources = new ArrayCompositeDisposable(2);
        final ObservableSource<? extends T> second;

        /* renamed from: v1 */
        T f19357v1;

        /* renamed from: v2 */
        T f19358v2;

        C4552a(SingleObserver<? super Boolean> asxVar, int i, ObservableSource<? extends T> asqVar, ObservableSource<? extends T> asqVar2, BiPredicate<? super T, ? super T> aujVar) {
            this.downstream = asxVar;
            this.first = asqVar;
            this.second = asqVar2;
            this.comparer = aujVar;
            this.observers = r3;
            C4553b<T>[] bVarArr = {new C4553b<>(this, 0, i), new C4553b<>(this, 1, i)};
        }

        boolean setDisposable(Disposable atrVar, int i) {
            return this.resources.setResource(i, atrVar);
        }

        void subscribe() {
            C4553b<T>[] bVarArr = this.observers;
            this.first.subscribe(bVarArr[0]);
            this.second.subscribe(bVarArr[1]);
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.resources.dispose();
                if (getAndIncrement() == 0) {
                    C4553b<T>[] bVarArr = this.observers;
                    bVarArr[0].f19360b.clear();
                    bVarArr[1].f19360b.clear();
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
                C4553b<T>[] bVarArr = this.observers;
                C4553b<T> bVar = bVarArr[0];
                SpscLinkedArrayQueue<T> bqlVar = bVar.f19360b;
                C4553b<T> bVar2 = bVarArr[1];
                SpscLinkedArrayQueue<T> bqlVar2 = bVar2.f19360b;
                int i = 1;
                while (!this.cancelled) {
                    boolean z = bVar.f19362d;
                    if (!z || (th2 = bVar.f19363e) == null) {
                        boolean z2 = bVar2.f19362d;
                        if (!z2 || (th = bVar2.f19363e) == null) {
                            if (this.f19357v1 == null) {
                                this.f19357v1 = bqlVar.poll();
                            }
                            boolean z3 = this.f19357v1 == null;
                            if (this.f19358v2 == null) {
                                this.f19358v2 = bqlVar2.poll();
                            }
                            boolean z4 = this.f19358v2 == null;
                            if (z && z2 && z3 && z4) {
                                this.downstream.onSuccess(true);
                                return;
                            } else if (!z || !z2 || z3 == z4) {
                                if (!z3 && !z4) {
                                    try {
                                        if (!this.comparer.mo9871a((T) this.f19357v1, (T) this.f19358v2)) {
                                            cancel(bqlVar, bqlVar2);
                                            this.downstream.onSuccess(false);
                                            return;
                                        }
                                        this.f19357v1 = null;
                                        this.f19358v2 = null;
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
                                this.downstream.onSuccess(false);
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
    /* compiled from: ObservableSequenceEqualSingle.java */
    /* renamed from: z1.bmg$b */
    /* loaded from: classes3.dex */
    public static final class C4553b<T> implements Observer<T> {

        /* renamed from: a */
        final C4552a<T> f19359a;

        /* renamed from: b */
        final SpscLinkedArrayQueue<T> f19360b;

        /* renamed from: c */
        final int f19361c;

        /* renamed from: d */
        volatile boolean f19362d;

        /* renamed from: e */
        Throwable f19363e;

        C4553b(C4552a<T> aVar, int i, int i2) {
            this.f19359a = aVar;
            this.f19361c = i;
            this.f19360b = new SpscLinkedArrayQueue<>(i2);
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            this.f19359a.setDisposable(atrVar, this.f19361c);
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.f19360b.offer(t);
            this.f19359a.drain();
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f19363e = th;
            this.f19362d = true;
            this.f19359a.drain();
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f19362d = true;
            this.f19359a.drain();
        }
    }
}
