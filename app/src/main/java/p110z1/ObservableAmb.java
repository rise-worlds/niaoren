package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bim */
/* loaded from: classes3.dex */
public final class ObservableAmb<T> extends Observable<T> {

    /* renamed from: a */
    final ObservableSource<? extends T>[] f18840a;

    /* renamed from: b */
    final Iterable<? extends ObservableSource<? extends T>> f18841b;

    public ObservableAmb(ObservableSource<? extends T>[] asqVarArr, Iterable<? extends ObservableSource<? extends T>> iterable) {
        this.f18840a = asqVarArr;
        this.f18841b = iterable;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        int i;
        ObservableSource<? extends T>[] asqVarArr = this.f18840a;
        if (asqVarArr == null) {
            asqVarArr = new Observable[8];
            try {
                i = 0;
                for (ObservableSource<? extends T> asqVar : this.f18841b) {
                    if (asqVar == null) {
                        EmptyDisposable.error(new NullPointerException("One of the sources is null"), assVar);
                        return;
                    }
                    if (i == asqVarArr.length) {
                        ObservableSource<? extends T>[] asqVarArr2 = new ObservableSource[(i >> 2) + i];
                        System.arraycopy(asqVarArr, 0, asqVarArr2, 0, i);
                        asqVarArr = asqVarArr2;
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
            EmptyDisposable.complete(assVar);
        } else if (i == 1) {
            asqVarArr[0].subscribe(assVar);
        } else {
            new C4385a(assVar, i).m9662a(asqVarArr);
        }
    }

    /* compiled from: ObservableAmb.java */
    /* renamed from: z1.bim$a */
    /* loaded from: classes3.dex */
    static final class C4385a<T> implements Disposable {

        /* renamed from: a */
        final Observer<? super T> f18842a;

        /* renamed from: b */
        final C4386b<T>[] f18843b;

        /* renamed from: c */
        final AtomicInteger f18844c = new AtomicInteger();

        C4385a(Observer<? super T> assVar, int i) {
            this.f18842a = assVar;
            this.f18843b = new C4386b[i];
        }

        /* renamed from: a */
        public void m9662a(ObservableSource<? extends T>[] asqVarArr) {
            C4386b<T>[] bVarArr = this.f18843b;
            int length = bVarArr.length;
            int i = 0;
            while (i < length) {
                int i2 = i + 1;
                bVarArr[i] = new C4386b<>(this, i2, this.f18842a);
                i = i2;
            }
            this.f18844c.lazySet(0);
            this.f18842a.onSubscribe(this);
            for (int i3 = 0; i3 < length && this.f18844c.get() == 0; i3++) {
                asqVarArr[i3].subscribe(bVarArr[i3]);
            }
        }

        /* renamed from: a */
        public boolean m9663a(int i) {
            int i2 = this.f18844c.get();
            int i3 = 0;
            if (i2 != 0) {
                return i2 == i;
            }
            if (!this.f18844c.compareAndSet(0, i)) {
                return false;
            }
            C4386b<T>[] bVarArr = this.f18843b;
            int length = bVarArr.length;
            while (i3 < length) {
                int i4 = i3 + 1;
                if (i4 != i) {
                    bVarArr[i3].dispose();
                }
                i3 = i4;
            }
            return true;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (this.f18844c.get() != -1) {
                this.f18844c.lazySet(-1);
                for (C4386b<T> bVar : this.f18843b) {
                    bVar.dispose();
                }
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18844c.get() == -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ObservableAmb.java */
    /* renamed from: z1.bim$b */
    /* loaded from: classes3.dex */
    public static final class C4386b<T> extends AtomicReference<Disposable> implements Observer<T> {
        private static final long serialVersionUID = -1185974347409665484L;
        final Observer<? super T> downstream;
        final int index;
        final C4385a<T> parent;
        boolean won;

        C4386b(C4385a<T> aVar, int i, Observer<? super T> assVar) {
            this.parent = aVar;
            this.index = i;
            this.downstream = assVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this, atrVar);
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (this.won) {
                this.downstream.onNext(t);
            } else if (this.parent.m9663a(this.index)) {
                this.won = true;
                this.downstream.onNext(t);
            } else {
                get().dispose();
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.won) {
                this.downstream.onError(th);
            } else if (this.parent.m9663a(this.index)) {
                this.won = true;
                this.downstream.onError(th);
            } else {
                RxJavaPlugins.m9212a(th);
            }
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (this.won) {
                this.downstream.onComplete();
            } else if (this.parent.m9663a(this.index)) {
                this.won = true;
                this.downstream.onComplete();
            }
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }
    }
}
