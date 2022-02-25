package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p110z1.MaybeMap;

/* renamed from: z1.bhk */
/* loaded from: classes3.dex */
public final class MaybeZipArray<T, R> extends Maybe<R> {

    /* renamed from: a */
    final MaybeSource<? extends T>[] f18729a;

    /* renamed from: b */
    final Function<? super Object[], ? extends R> f18730b;

    public MaybeZipArray(MaybeSource<? extends T>[] asiVarArr, Function<? super Object[], ? extends R> aunVar) {
        this.f18729a = asiVarArr;
        this.f18730b = aunVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super R> asfVar) {
        MaybeSource<? extends T>[] asiVarArr = this.f18729a;
        int length = asiVarArr.length;
        if (length == 1) {
            asiVarArr[0].mo10646a(new MaybeMap.C4309a(asfVar, new C4343a()));
            return;
        }
        C4344b bVar = new C4344b(asfVar, length, this.f18730b);
        asfVar.onSubscribe(bVar);
        for (int i = 0; i < length && !bVar.isDisposed(); i++) {
            MaybeSource<? extends T> asiVar = asiVarArr[i];
            if (asiVar == null) {
                bVar.innerError(new NullPointerException("One of the sources is null"), i);
                return;
            } else {
                asiVar.mo10646a(bVar.observers[i]);
            }
        }
    }

    /* compiled from: MaybeZipArray.java */
    /* renamed from: z1.bhk$b */
    /* loaded from: classes3.dex */
    static final class C4344b<T, R> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = -5556924161382950569L;
        final MaybeObserver<? super R> downstream;
        final C4345c<T>[] observers;
        final Object[] values;
        final Function<? super Object[], ? extends R> zipper;

        /* JADX INFO: Access modifiers changed from: package-private */
        public C4344b(MaybeObserver<? super R> asfVar, int i, Function<? super Object[], ? extends R> aunVar) {
            super(i);
            this.downstream = asfVar;
            this.zipper = aunVar;
            C4345c<T>[] cVarArr = new C4345c[i];
            for (int i2 = 0; i2 < i; i2++) {
                cVarArr[i2] = new C4345c<>(this, i2);
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
                for (C4345c<T> cVar : this.observers) {
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
            C4345c<T>[] cVarArr = this.observers;
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

        void innerComplete(int i) {
            if (getAndSet(0) > 0) {
                disposeExcept(i);
                this.downstream.onComplete();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MaybeZipArray.java */
    /* renamed from: z1.bhk$c */
    /* loaded from: classes3.dex */
    public static final class C4345c<T> extends AtomicReference<Disposable> implements MaybeObserver<T> {
        private static final long serialVersionUID = 3323743579927613702L;
        final int index;
        final C4344b<T, ?> parent;

        C4345c(C4344b<T, ?> bVar, int i) {
            this.parent = bVar;
            this.index = i;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this, atrVar);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            this.parent.innerSuccess(t, this.index);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.parent.innerError(th, this.index);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.parent.innerComplete(this.index);
        }
    }

    /* compiled from: MaybeZipArray.java */
    /* renamed from: z1.bhk$a */
    /* loaded from: classes3.dex */
    final class C4343a implements Function<T, R> {
        C4343a() {
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object[], java.lang.Object] */
        @Override // p110z1.Function
        public R apply(T t) throws Exception {
            return (R) ObjectHelper.m9873a(MaybeZipArray.this.f18730b.apply(new Object[]{t}), "The zipper returned a null value");
        }
    }
}
