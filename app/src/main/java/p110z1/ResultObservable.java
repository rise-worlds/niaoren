package p110z1;

import retrofit2.Response;

/* renamed from: z1.xa */
/* loaded from: classes3.dex */
final class ResultObservable<T> extends Observable<C5588wz<T>> {

    /* renamed from: a */
    private final Observable<Response<T>> f23654a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResultObservable(Observable<Response<T>> aslVar) {
        this.f23654a = aslVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super C5588wz<T>> assVar) {
        this.f23654a.subscribe(new C5590a(assVar));
    }

    /* compiled from: ResultObservable.java */
    /* renamed from: z1.xa$a */
    /* loaded from: classes3.dex */
    private static class C5590a<R> implements Observer<Response<R>> {

        /* renamed from: a */
        private final Observer<? super C5588wz<R>> f23655a;

        C5590a(Observer<? super C5588wz<R>> assVar) {
            this.f23655a = assVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            this.f23655a.onSubscribe(atrVar);
        }

        /* renamed from: a */
        public void onNext(Response<R> response) {
            this.f23655a.onNext(C5588wz.m137a(response));
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            try {
                this.f23655a.onNext(C5588wz.m138a(th));
                this.f23655a.onComplete();
            } catch (Throwable th2) {
                try {
                    this.f23655a.onError(th2);
                } catch (Throwable th3) {
                    Exceptions.m9983b(th3);
                    RxJavaPlugins.m9212a(new CompositeException(th2, th3));
                }
            }
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f23655a.onComplete();
        }
    }
}
