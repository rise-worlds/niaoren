package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.biz */
/* loaded from: classes3.dex */
public final class ObservableCombineLatest<T, R> extends Observable<R> {

    /* renamed from: a */
    final ObservableSource<? extends T>[] f18953a;

    /* renamed from: b */
    final Iterable<? extends ObservableSource<? extends T>> f18954b;

    /* renamed from: c */
    final Function<? super Object[], ? extends R> f18955c;

    /* renamed from: d */
    final int f18956d;

    /* renamed from: e */
    final boolean f18957e;

    public ObservableCombineLatest(ObservableSource<? extends T>[] asqVarArr, Iterable<? extends ObservableSource<? extends T>> iterable, Function<? super Object[], ? extends R> aunVar, int i, boolean z) {
        this.f18953a = asqVarArr;
        this.f18954b = iterable;
        this.f18955c = aunVar;
        this.f18956d = i;
        this.f18957e = z;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super R> assVar) {
        int i;
        ObservableSource<? extends T>[] asqVarArr = this.f18953a;
        if (asqVarArr == null) {
            asqVarArr = new Observable[8];
            int i2 = 0;
            for (ObservableSource<? extends T> asqVar : this.f18954b) {
                if (i2 == asqVarArr.length) {
                    ObservableSource<? extends T>[] asqVarArr2 = new ObservableSource[(i2 >> 2) + i2];
                    System.arraycopy(asqVarArr, 0, asqVarArr2, 0, i2);
                    asqVarArr = asqVarArr2;
                }
                i2++;
                asqVarArr[i2] = asqVar;
            }
            i = i2;
        } else {
            i = asqVarArr.length;
        }
        if (i == 0) {
            EmptyDisposable.complete(assVar);
        } else {
            new C4408b(assVar, this.f18955c, i, this.f18956d, this.f18957e).subscribe(asqVarArr);
        }
    }

    /* compiled from: ObservableCombineLatest.java */
    /* renamed from: z1.biz$b */
    /* loaded from: classes3.dex */
    static final class C4408b<T, R> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = 8567835998786448817L;
        int active;
        volatile boolean cancelled;
        final Function<? super Object[], ? extends R> combiner;
        int complete;
        final boolean delayError;
        volatile boolean done;
        final Observer<? super R> downstream;
        final AtomicThrowable errors = new AtomicThrowable();
        Object[] latest;
        final C4407a<T, R>[] observers;
        final SpscLinkedArrayQueue<Object[]> queue;

        C4408b(Observer<? super R> assVar, Function<? super Object[], ? extends R> aunVar, int i, int i2, boolean z) {
            this.downstream = assVar;
            this.combiner = aunVar;
            this.delayError = z;
            this.latest = new Object[i];
            C4407a<T, R>[] aVarArr = new C4407a[i];
            for (int i3 = 0; i3 < i; i3++) {
                aVarArr[i3] = new C4407a<>(this, i3);
            }
            this.observers = aVarArr;
            this.queue = new SpscLinkedArrayQueue<>(i2);
        }

        public void subscribe(ObservableSource<? extends T>[] asqVarArr) {
            C4407a<T, R>[] aVarArr = this.observers;
            int length = aVarArr.length;
            this.downstream.onSubscribe(this);
            for (int i = 0; i < length && !this.done && !this.cancelled; i++) {
                asqVarArr[i].subscribe(aVarArr[i]);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelSources();
                if (getAndIncrement() == 0) {
                    clear(this.queue);
                }
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        void cancelSources() {
            for (C4407a<T, R> aVar : this.observers) {
                aVar.dispose();
            }
        }

        void clear(SpscLinkedArrayQueue<?> bqlVar) {
            synchronized (this) {
                this.latest = null;
            }
            bqlVar.clear();
        }

        void drain() {
            if (getAndIncrement() == 0) {
                SpscLinkedArrayQueue<Object[]> bqlVar = this.queue;
                Observer<? super R> assVar = this.downstream;
                boolean z = this.delayError;
                int i = 1;
                while (!this.cancelled) {
                    if (z || this.errors.get() == null) {
                        boolean z2 = this.done;
                        Object[] poll = bqlVar.poll();
                        boolean z3 = poll == null;
                        if (z2 && z3) {
                            clear(bqlVar);
                            Throwable terminate = this.errors.terminate();
                            if (terminate == null) {
                                assVar.onComplete();
                                return;
                            } else {
                                assVar.onError(terminate);
                                return;
                            }
                        } else if (z3) {
                            i = addAndGet(-i);
                            if (i == 0) {
                                return;
                            }
                        } else {
                            try {
                                assVar.onNext((Object) ObjectHelper.m9873a(this.combiner.apply(poll), "The combiner returned a null value"));
                            } catch (Throwable th) {
                                Exceptions.m9983b(th);
                                this.errors.addThrowable(th);
                                cancelSources();
                                clear(bqlVar);
                                assVar.onError(this.errors.terminate());
                                return;
                            }
                        }
                    } else {
                        cancelSources();
                        clear(bqlVar);
                        assVar.onError(this.errors.terminate());
                        return;
                    }
                }
                clear(bqlVar);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        void innerNext(int i, T t) {
            boolean z;
            synchronized (this) {
                Object[] objArr = this.latest;
                if (objArr != null) {
                    Object obj = objArr[i];
                    int i2 = this.active;
                    if (obj == null) {
                        i2++;
                        this.active = i2;
                    }
                    objArr[i] = t;
                    if (i2 == objArr.length) {
                        this.queue.offer(objArr.clone());
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        drain();
                    }
                }
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:17:0x0023, code lost:
            if (r1 == r4.length) goto L_0x0025;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void innerError(int r3, java.lang.Throwable r4) {
            /*
                r2 = this;
                z1.bsn r0 = r2.errors
                boolean r0 = r0.addThrowable(r4)
                if (r0 == 0) goto L_0x0036
                boolean r4 = r2.delayError
                r0 = 1
                if (r4 == 0) goto L_0x002c
                monitor-enter(r2)
                java.lang.Object[] r4 = r2.latest     // Catch: all -> 0x0029
                if (r4 != 0) goto L_0x0014
                monitor-exit(r2)     // Catch: all -> 0x0029
                return
            L_0x0014:
                r3 = r4[r3]     // Catch: all -> 0x0029
                if (r3 != 0) goto L_0x001a
                r3 = 1
                goto L_0x001b
            L_0x001a:
                r3 = 0
            L_0x001b:
                if (r3 != 0) goto L_0x0025
                int r1 = r2.complete     // Catch: all -> 0x0029
                int r1 = r1 + r0
                r2.complete = r1     // Catch: all -> 0x0029
                int r4 = r4.length     // Catch: all -> 0x0029
                if (r1 != r4) goto L_0x0027
            L_0x0025:
                r2.done = r0     // Catch: all -> 0x0029
            L_0x0027:
                monitor-exit(r2)     // Catch: all -> 0x0029
                goto L_0x002d
            L_0x0029:
                r3 = move-exception
                monitor-exit(r2)     // Catch: all -> 0x0029
                throw r3
            L_0x002c:
                r3 = 1
            L_0x002d:
                if (r3 == 0) goto L_0x0032
                r2.cancelSources()
            L_0x0032:
                r2.drain()
                goto L_0x0039
            L_0x0036:
                p110z1.RxJavaPlugins.m9212a(r4)
            L_0x0039:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p110z1.ObservableCombineLatest.C4408b.innerError(int, java.lang.Throwable):void");
        }

        /* JADX WARN: Code restructure failed: missing block: B:13:0x0017, code lost:
            if (r2 == r0.length) goto L_0x0019;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void innerComplete(int r4) {
            /*
                r3 = this;
                monitor-enter(r3)
                java.lang.Object[] r0 = r3.latest     // Catch: all -> 0x0025
                if (r0 != 0) goto L_0x0007
                monitor-exit(r3)     // Catch: all -> 0x0025
                return
            L_0x0007:
                r4 = r0[r4]     // Catch: all -> 0x0025
                r1 = 1
                if (r4 != 0) goto L_0x000e
                r4 = 1
                goto L_0x000f
            L_0x000e:
                r4 = 0
            L_0x000f:
                if (r4 != 0) goto L_0x0019
                int r2 = r3.complete     // Catch: all -> 0x0025
                int r2 = r2 + r1
                r3.complete = r2     // Catch: all -> 0x0025
                int r0 = r0.length     // Catch: all -> 0x0025
                if (r2 != r0) goto L_0x001b
            L_0x0019:
                r3.done = r1     // Catch: all -> 0x0025
            L_0x001b:
                monitor-exit(r3)     // Catch: all -> 0x0025
                if (r4 == 0) goto L_0x0021
                r3.cancelSources()
            L_0x0021:
                r3.drain()
                return
            L_0x0025:
                r4 = move-exception
                monitor-exit(r3)     // Catch: all -> 0x0025
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: p110z1.ObservableCombineLatest.C4408b.innerComplete(int):void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableCombineLatest.java */
    /* renamed from: z1.biz$a */
    /* loaded from: classes3.dex */
    public static final class C4407a<T, R> extends AtomicReference<Disposable> implements Observer<T> {
        private static final long serialVersionUID = -4823716997131257941L;
        final int index;
        final C4408b<T, R> parent;

        C4407a(C4408b<T, R> bVar, int i) {
            this.parent = bVar;
            this.index = i;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this, atrVar);
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.parent.innerNext(this.index, t);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.parent.innerError(this.index, th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.parent.innerComplete(this.index);
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }
    }
}
