package p110z1;

/* renamed from: z1.awk */
/* loaded from: classes3.dex */
public final class DisposableLambdaObserver<T> implements Observer<T>, Disposable {

    /* renamed from: a */
    final Observer<? super T> f17622a;

    /* renamed from: b */
    final Consumer<? super Disposable> f17623b;

    /* renamed from: c */
    final Action f17624c;

    /* renamed from: d */
    Disposable f17625d;

    public DisposableLambdaObserver(Observer<? super T> assVar, Consumer<? super Disposable> aumVar, Action augVar) {
        this.f17622a = assVar;
        this.f17623b = aumVar;
        this.f17624c = augVar;
    }

    @Override // p110z1.Observer
    public void onSubscribe(Disposable atrVar) {
        try {
            this.f17623b.accept(atrVar);
            if (DisposableHelper.validate(this.f17625d, atrVar)) {
                this.f17625d = atrVar;
                this.f17622a.onSubscribe(this);
            }
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            atrVar.dispose();
            this.f17625d = DisposableHelper.DISPOSED;
            EmptyDisposable.error(th, this.f17622a);
        }
    }

    @Override // p110z1.Observer
    public void onNext(T t) {
        this.f17622a.onNext(t);
    }

    @Override // p110z1.Observer
    public void onError(Throwable th) {
        if (this.f17625d != DisposableHelper.DISPOSED) {
            this.f17625d = DisposableHelper.DISPOSED;
            this.f17622a.onError(th);
            return;
        }
        RxJavaPlugins.m9212a(th);
    }

    @Override // p110z1.Observer
    public void onComplete() {
        if (this.f17625d != DisposableHelper.DISPOSED) {
            this.f17625d = DisposableHelper.DISPOSED;
            this.f17622a.onComplete();
        }
    }

    @Override // p110z1.Disposable
    public void dispose() {
        Disposable atrVar = this.f17625d;
        if (atrVar != DisposableHelper.DISPOSED) {
            this.f17625d = DisposableHelper.DISPOSED;
            try {
                this.f17624c.mo9442a();
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                RxJavaPlugins.m9212a(th);
            }
            atrVar.dispose();
        }
    }

    @Override // p110z1.Disposable
    public boolean isDisposed() {
        return this.f17625d.isDisposed();
    }
}
