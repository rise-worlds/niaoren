package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p110z1.SingleMap;

/* renamed from: z1.bqh */
/* loaded from: classes3.dex */
public final class SingleZipArray<T, R> extends Single<R> {

    /* renamed from: a */
    final SingleSource<? extends T>[] f19845a;

    /* renamed from: b */
    final Function<? super Object[], ? extends R> f19846b;

    public SingleZipArray(SingleSource<? extends T>[] ataVarArr, Function<? super Object[], ? extends R> aunVar) {
        this.f19845a = ataVarArr;
        this.f19846b = aunVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super R> asxVar) {
        SingleSource<? extends T>[] ataVarArr = this.f19845a;
        int length = ataVarArr.length;
        if (length == 1) {
            ataVarArr[0].mo10018a(new SingleMap.C4691a(asxVar, new C4706a()));
            return;
        }
        C4707b bVar = new C4707b(asxVar, length, this.f19846b);
        asxVar.onSubscribe(bVar);
        for (int i = 0; i < length && !bVar.isDisposed(); i++) {
            SingleSource<? extends T> ataVar = ataVarArr[i];
            if (ataVar == null) {
                bVar.innerError(new NullPointerException("One of the sources is null"), i);
                return;
            } else {
                ataVar.mo10018a(bVar.observers[i]);
            }
        }
    }

    /* compiled from: SingleZipArray.java */
    /* renamed from: z1.bqh$b */
    /* loaded from: classes3.dex */
    static final class C4707b<T, R> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = -5556924161382950569L;
        final SingleObserver<? super R> downstream;
        final C4708c<T>[] observers;
        final Object[] values;
        final Function<? super Object[], ? extends R> zipper;

        /* JADX INFO: Access modifiers changed from: package-private */
        public C4707b(SingleObserver<? super R> asxVar, int i, Function<? super Object[], ? extends R> aunVar) {
            super(i);
            this.downstream = asxVar;
            this.zipper = aunVar;
            C4708c<T>[] cVarArr = new C4708c[i];
            for (int i2 = 0; i2 < i; i2++) {
                cVarArr[i2] = new C4708c<>(this, i2);
            }
            this.observers = cVarArr;
            this.values = new Object[i];
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return get() <= 0;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (getAndSet(0) > 0) {
                for (C4708c<T> cVar : this.observers) {
                    cVar.dispose();
                }
            }
        }

        void innerSuccess(T t, int i) {
            this.values[i] = t;
            if (decrementAndGet() == 0) {
                try {
                    this.downstream.onSuccess(ObjectHelper.m9873a(this.zipper.apply(this.values), "The zipper returned a null value"));
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.downstream.onError(th);
                }
            }
        }

        void disposeExcept(int i) {
            C4708c<T>[] cVarArr = this.observers;
            int length = cVarArr.length;
            for (int i2 = 0; i2 < i; i2++) {
                cVarArr[i2].dispose();
            }
            while (true) {
                i++;
                if (i < length) {
                    cVarArr[i].dispose();
                } else {
                    return;
                }
            }
        }

        void innerError(Throwable th, int i) {
            if (getAndSet(0) > 0) {
                disposeExcept(i);
                this.downstream.onError(th);
                return;
            }
            RxJavaPlugins.m9212a(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: SingleZipArray.java */
    /* renamed from: z1.bqh$c */
    /* loaded from: classes3.dex */
    public static final class C4708c<T> extends AtomicReference<Disposable> implements SingleObserver<T> {
        private static final long serialVersionUID = 3323743579927613702L;
        final int index;
        final C4707b<T, ?> parent;

        C4708c(C4707b<T, ?> bVar, int i) {
            this.parent = bVar;
            this.index = i;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this, atrVar);
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            this.parent.innerSuccess(t, this.index);
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            this.parent.innerError(th, this.index);
        }
    }

    /* compiled from: SingleZipArray.java */
    /* renamed from: z1.bqh$a */
    /* loaded from: classes3.dex */
    final class C4706a implements Function<T, R> {
        C4706a() {
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object[], java.lang.Object] */
        @Override // p110z1.Function
        public R apply(T t) throws Exception {
            return (R) ObjectHelper.m9873a(SingleZipArray.this.f19846b.apply(new Object[]{t}), "The zipper returned a null value");
        }
    }
}
