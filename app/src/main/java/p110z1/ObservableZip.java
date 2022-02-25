package p110z1;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bnq */
/* loaded from: classes3.dex */
public final class ObservableZip<T, R> extends Observable<R> {

    /* renamed from: a */
    final ObservableSource<? extends T>[] f19562a;

    /* renamed from: b */
    final Iterable<? extends ObservableSource<? extends T>> f19563b;

    /* renamed from: c */
    final Function<? super Object[], ? extends R> f19564c;

    /* renamed from: d */
    final int f19565d;

    /* renamed from: e */
    final boolean f19566e;

    public ObservableZip(ObservableSource<? extends T>[] asqVarArr, Iterable<? extends ObservableSource<? extends T>> iterable, Function<? super Object[], ? extends R> aunVar, int i, boolean z) {
        this.f19562a = asqVarArr;
        this.f19563b = iterable;
        this.f19564c = aunVar;
        this.f19565d = i;
        this.f19566e = z;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super R> assVar) {
        int i;
        ObservableSource<? extends T>[] asqVarArr = this.f19562a;
        if (asqVarArr == null) {
            asqVarArr = new Observable[8];
            i = 0;
            for (ObservableSource<? extends T> asqVar : this.f19563b) {
                if (i == asqVarArr.length) {
                    ObservableSource<? extends T>[] asqVarArr2 = new ObservableSource[(i >> 2) + i];
                    System.arraycopy(asqVarArr, 0, asqVarArr2, 0, i);
                    asqVarArr = asqVarArr2;
                }
                i++;
                asqVarArr[i] = asqVar;
            }
        } else {
            i = asqVarArr.length;
        }
        if (i == 0) {
            EmptyDisposable.complete(assVar);
        } else {
            new C4615a(assVar, this.f19564c, i, this.f19566e).subscribe(asqVarArr, this.f19565d);
        }
    }

    /* compiled from: ObservableZip.java */
    /* renamed from: z1.bnq$a */
    /* loaded from: classes3.dex */
    static final class C4615a<T, R> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = 2983708048395377667L;
        volatile boolean cancelled;
        final boolean delayError;
        final Observer<? super R> downstream;
        final C4616b<T, R>[] observers;
        final T[] row;
        final Function<? super Object[], ? extends R> zipper;

        C4615a(Observer<? super R> assVar, Function<? super Object[], ? extends R> aunVar, int i, boolean z) {
            this.downstream = assVar;
            this.zipper = aunVar;
            this.observers = new C4616b[i];
            this.row = (T[]) new Object[i];
            this.delayError = z;
        }

        public void subscribe(ObservableSource<? extends T>[] asqVarArr, int i) {
            C4616b<T, R>[] bVarArr = this.observers;
            int length = bVarArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                bVarArr[i2] = new C4616b<>(this, i);
            }
            lazySet(0);
            this.downstream.onSubscribe(this);
            for (int i3 = 0; i3 < length && !this.cancelled; i3++) {
                asqVarArr[i3].subscribe(bVarArr[i3]);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelSources();
                if (getAndIncrement() == 0) {
                    clear();
                }
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        void cancel() {
            clear();
            cancelSources();
        }

        void cancelSources() {
            for (C4616b<T, R> bVar : this.observers) {
                bVar.m9557a();
            }
        }

        void clear() {
            for (C4616b<T, R> bVar : this.observers) {
                bVar.f19568b.clear();
            }
        }

        public void drain() {
            Throwable th;
            if (getAndIncrement() == 0) {
                C4616b<T, R>[] bVarArr = this.observers;
                Observer<? super R> assVar = this.downstream;
                T[] tArr = this.row;
                boolean z = this.delayError;
                int i = 1;
                while (true) {
                    int i2 = 0;
                    int i3 = 0;
                    for (C4616b<T, R> bVar : bVarArr) {
                        if (tArr[i3] == null) {
                            boolean z2 = bVar.f19569c;
                            T poll = bVar.f19568b.poll();
                            boolean z3 = poll == null;
                            if (checkTerminated(z2, z3, assVar, z, bVar)) {
                                return;
                            }
                            if (!z3) {
                                tArr[i3] = poll;
                            } else {
                                i2++;
                            }
                        } else if (bVar.f19569c && !z && (th = bVar.f19570d) != null) {
                            this.cancelled = true;
                            cancel();
                            assVar.onError(th);
                            return;
                        }
                        i3++;
                    }
                    if (i2 != 0) {
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        try {
                            assVar.onNext((Object) ObjectHelper.m9873a(this.zipper.apply(tArr.clone()), "The zipper returned a null value"));
                            Arrays.fill(tArr, (Object) null);
                        } catch (Throwable th2) {
                            Exceptions.m9983b(th2);
                            cancel();
                            assVar.onError(th2);
                            return;
                        }
                    }
                }
            }
        }

        boolean checkTerminated(boolean z, boolean z2, Observer<? super R> assVar, boolean z3, C4616b<?, ?> bVar) {
            if (this.cancelled) {
                cancel();
                return true;
            } else if (!z) {
                return false;
            } else {
                if (!z3) {
                    Throwable th = bVar.f19570d;
                    if (th != null) {
                        this.cancelled = true;
                        cancel();
                        assVar.onError(th);
                        return true;
                    } else if (!z2) {
                        return false;
                    } else {
                        this.cancelled = true;
                        cancel();
                        assVar.onComplete();
                        return true;
                    }
                } else if (!z2) {
                    return false;
                } else {
                    Throwable th2 = bVar.f19570d;
                    this.cancelled = true;
                    cancel();
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableZip.java */
    /* renamed from: z1.bnq$b */
    /* loaded from: classes3.dex */
    public static final class C4616b<T, R> implements Observer<T> {

        /* renamed from: a */
        final C4615a<T, R> f19567a;

        /* renamed from: b */
        final SpscLinkedArrayQueue<T> f19568b;

        /* renamed from: c */
        volatile boolean f19569c;

        /* renamed from: d */
        Throwable f19570d;

        /* renamed from: e */
        final AtomicReference<Disposable> f19571e = new AtomicReference<>();

        C4616b(C4615a<T, R> aVar, int i) {
            this.f19567a = aVar;
            this.f19568b = new SpscLinkedArrayQueue<>(i);
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this.f19571e, atrVar);
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.f19568b.offer(t);
            this.f19567a.drain();
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f19570d = th;
            this.f19569c = true;
            this.f19567a.drain();
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f19569c = true;
            this.f19567a.drain();
        }

        /* renamed from: a */
        public void m9557a() {
            DisposableHelper.dispose(this.f19571e);
        }
    }
}
