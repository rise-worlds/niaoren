package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.blc */
/* loaded from: classes3.dex */
public final class ObservableMapNotification<T, R> extends AbstractObservableWithUpstream<T, ObservableSource<? extends R>> {

    /* renamed from: b */
    final Function<? super T, ? extends ObservableSource<? extends R>> f19226b;

    /* renamed from: c */
    final Function<? super Throwable, ? extends ObservableSource<? extends R>> f19227c;

    /* renamed from: d */
    final Callable<? extends ObservableSource<? extends R>> f19228d;

    public ObservableMapNotification(ObservableSource<T> asqVar, Function<? super T, ? extends ObservableSource<? extends R>> aunVar, Function<? super Throwable, ? extends ObservableSource<? extends R>> aunVar2, Callable<? extends ObservableSource<? extends R>> callable) {
        super(asqVar);
        this.f19226b = aunVar;
        this.f19227c = aunVar2;
        this.f19228d = callable;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super ObservableSource<? extends R>> assVar) {
        this.f18807a.subscribe(new C4492a(assVar, this.f19226b, this.f19227c, this.f19228d));
    }

    /* compiled from: ObservableMapNotification.java */
    /* renamed from: z1.blc$a */
    /* loaded from: classes3.dex */
    static final class C4492a<T, R> implements Observer<T>, Disposable {

        /* renamed from: a */
        final Observer<? super ObservableSource<? extends R>> f19229a;

        /* renamed from: b */
        final Function<? super T, ? extends ObservableSource<? extends R>> f19230b;

        /* renamed from: c */
        final Function<? super Throwable, ? extends ObservableSource<? extends R>> f19231c;

        /* renamed from: d */
        final Callable<? extends ObservableSource<? extends R>> f19232d;

        /* renamed from: e */
        Disposable f19233e;

        C4492a(Observer<? super ObservableSource<? extends R>> assVar, Function<? super T, ? extends ObservableSource<? extends R>> aunVar, Function<? super Throwable, ? extends ObservableSource<? extends R>> aunVar2, Callable<? extends ObservableSource<? extends R>> callable) {
            this.f19229a = assVar;
            this.f19230b = aunVar;
            this.f19231c = aunVar2;
            this.f19232d = callable;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19233e, atrVar)) {
                this.f19233e = atrVar;
                this.f19229a.onSubscribe(this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19233e.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19233e.isDisposed();
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            try {
                this.f19229a.onNext((ObservableSource) ObjectHelper.m9873a(this.f19230b.apply(t), "The onNext ObservableSource returned is null"));
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.f19229a.onError(th);
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            try {
                this.f19229a.onNext((ObservableSource) ObjectHelper.m9873a(this.f19231c.apply(th), "The onError ObservableSource returned is null"));
                this.f19229a.onComplete();
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                this.f19229a.onError(new CompositeException(th, th2));
            }
        }

        @Override // p110z1.Observer
        public void onComplete() {
            try {
                this.f19229a.onNext((ObservableSource) ObjectHelper.m9873a(this.f19232d.call(), "The onComplete ObservableSource returned is null"));
                this.f19229a.onComplete();
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.f19229a.onError(th);
            }
        }
    }
}
