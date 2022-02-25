package p110z1;

/* renamed from: z1.awj */
/* loaded from: classes3.dex */
public abstract class DeferredScalarObserver<T, R> extends DeferredScalarDisposable<R> implements Observer<T> {
    private static final long serialVersionUID = -266195175408988651L;
    protected Disposable upstream;

    public DeferredScalarObserver(Observer<? super R> assVar) {
        super(assVar);
    }

    @Override // p110z1.Observer
    public void onSubscribe(Disposable atrVar) {
        if (DisposableHelper.validate(this.upstream, atrVar)) {
            this.upstream = atrVar;
            this.downstream.onSubscribe(this);
        }
    }

    @Override // p110z1.Observer
    public void onError(Throwable th) {
        this.value = null;
        error(th);
    }

    @Override // p110z1.Observer
    public void onComplete() {
        T t = this.value;
        if (t != null) {
            this.value = null;
            complete(t);
            return;
        }
        complete();
    }

    @Override // p110z1.DeferredScalarDisposable, p110z1.Disposable
    public void dispose() {
        super.dispose();
        this.upstream.dispose();
    }
}
