package p110z1;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* renamed from: z1.bnp */
/* loaded from: classes3.dex */
public final class ObservableWithLatestFromMany<T, R> extends AbstractObservableWithUpstream<T, R> {
    @atn

    /* renamed from: b */
    final ObservableSource<?>[] f19558b;
    @atn

    /* renamed from: c */
    final Iterable<? extends ObservableSource<?>> f19559c;
    @AbstractC3889atm

    /* renamed from: d */
    final Function<? super Object[], R> f19560d;

    public ObservableWithLatestFromMany(@AbstractC3889atm ObservableSource<T> asqVar, @AbstractC3889atm ObservableSource<?>[] asqVarArr, @AbstractC3889atm Function<? super Object[], R> aunVar) {
        super(asqVar);
        this.f19558b = asqVarArr;
        this.f19559c = null;
        this.f19560d = aunVar;
    }

    public ObservableWithLatestFromMany(@AbstractC3889atm ObservableSource<T> asqVar, @AbstractC3889atm Iterable<? extends ObservableSource<?>> iterable, @AbstractC3889atm Function<? super Object[], R> aunVar) {
        super(asqVar);
        this.f19558b = null;
        this.f19559c = iterable;
        this.f19560d = aunVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super R> assVar) {
        int i;
        ObservableSource<?>[] asqVarArr = this.f19558b;
        if (asqVarArr == null) {
            asqVarArr = new ObservableSource[8];
            try {
                i = 0;
                for (ObservableSource<?> asqVar : this.f19559c) {
                    if (i == asqVarArr.length) {
                        asqVarArr = (ObservableSource[]) Arrays.copyOf(asqVarArr, (i >> 1) + i);
                    }
                    i++;
                    asqVarArr[i] = asqVar;
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                EmptyDisposable.error(th, assVar);
                return;
            }
        } else {
            i = asqVarArr.length;
        }
        if (i == 0) {
            new ObservableMap(this.f18807a, new C4612a()).mo34a((Observer) assVar);
            return;
        }
        C4613b bVar = new C4613b(assVar, this.f19560d, i);
        assVar.onSubscribe(bVar);
        bVar.subscribe(asqVarArr, i);
        this.f18807a.subscribe(bVar);
    }

    /* compiled from: ObservableWithLatestFromMany.java */
    /* renamed from: z1.bnp$b */
    /* loaded from: classes3.dex */
    static final class C4613b<T, R> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = 1577321883966341961L;
        final Function<? super Object[], R> combiner;
        volatile boolean done;
        final Observer<? super R> downstream;
        final AtomicThrowable error;
        final C4614c[] observers;
        final AtomicReference<Disposable> upstream;
        final AtomicReferenceArray<Object> values;

        C4613b(Observer<? super R> assVar, Function<? super Object[], R> aunVar, int i) {
            this.downstream = assVar;
            this.combiner = aunVar;
            C4614c[] cVarArr = new C4614c[i];
            for (int i2 = 0; i2 < i; i2++) {
                cVarArr[i2] = new C4614c(this, i2);
            }
            this.observers = cVarArr;
            this.values = new AtomicReferenceArray<>(i);
            this.upstream = new AtomicReference<>();
            this.error = new AtomicThrowable();
        }

        void subscribe(ObservableSource<?>[] asqVarArr, int i) {
            C4614c[] cVarArr = this.observers;
            AtomicReference<Disposable> atomicReference = this.upstream;
            for (int i2 = 0; i2 < i && !DisposableHelper.isDisposed(atomicReference.get()) && !this.done; i2++) {
                asqVarArr[i2].subscribe(cVarArr[i2]);
            }
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this.upstream, atrVar);
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.done) {
                AtomicReferenceArray<Object> atomicReferenceArray = this.values;
                int length = atomicReferenceArray.length();
                Object[] objArr = new Object[length + 1];
                int i = 0;
                objArr[0] = t;
                while (i < length) {
                    Object obj = atomicReferenceArray.get(i);
                    if (obj != null) {
                        i++;
                        objArr[i] = obj;
                    } else {
                        return;
                    }
                }
                try {
                    HalfSerializer.m9427a(this.downstream, ObjectHelper.m9873a(this.combiner.apply(objArr), "combiner returned a null value"), this, this.error);
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    dispose();
                    onError(th);
                }
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.done = true;
            cancelAllBut(-1);
            HalfSerializer.m9426a((Observer<?>) this.downstream, th, (AtomicInteger) this, this.error);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                cancelAllBut(-1);
                HalfSerializer.m9425a(this.downstream, this, this.error);
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.upstream.get());
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            for (C4614c cVar : this.observers) {
                cVar.dispose();
            }
        }

        void innerNext(int i, Object obj) {
            this.values.set(i, obj);
        }

        void innerError(int i, Throwable th) {
            this.done = true;
            DisposableHelper.dispose(this.upstream);
            cancelAllBut(i);
            HalfSerializer.m9426a((Observer<?>) this.downstream, th, (AtomicInteger) this, this.error);
        }

        void innerComplete(int i, boolean z) {
            if (!z) {
                this.done = true;
                cancelAllBut(i);
                HalfSerializer.m9425a(this.downstream, this, this.error);
            }
        }

        void cancelAllBut(int i) {
            C4614c[] cVarArr = this.observers;
            for (int i2 = 0; i2 < cVarArr.length; i2++) {
                if (i2 != i) {
                    cVarArr[i2].dispose();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableWithLatestFromMany.java */
    /* renamed from: z1.bnp$c */
    /* loaded from: classes3.dex */
    public static final class C4614c extends AtomicReference<Disposable> implements Observer<Object> {
        private static final long serialVersionUID = 3256684027868224024L;
        boolean hasValue;
        final int index;
        final C4613b<?, ?> parent;

        C4614c(C4613b<?, ?> bVar, int i) {
            this.parent = bVar;
            this.index = i;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this, atrVar);
        }

        @Override // p110z1.Observer
        public void onNext(Object obj) {
            if (!this.hasValue) {
                this.hasValue = true;
            }
            this.parent.innerNext(this.index, obj);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.parent.innerError(this.index, th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.parent.innerComplete(this.index, this.hasValue);
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }
    }

    /* compiled from: ObservableWithLatestFromMany.java */
    /* renamed from: z1.bnp$a */
    /* loaded from: classes3.dex */
    final class C4612a implements Function<T, R> {
        C4612a() {
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object[], java.lang.Object] */
        @Override // p110z1.Function
        public R apply(T t) throws Exception {
            return (R) ObjectHelper.m9873a(ObservableWithLatestFromMany.this.f19560d.apply(new Object[]{t}), "The combiner returned a null value");
        }
    }
}
