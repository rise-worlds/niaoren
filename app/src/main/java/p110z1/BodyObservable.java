package p110z1;

import retrofit2.Response;

/* renamed from: z1.ww */
/* loaded from: classes3.dex */
final class BodyObservable<T> extends Observable<T> {

    /* renamed from: a */
    private final Observable<Response<T>> f23645a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BodyObservable(Observable<Response<T>> aslVar) {
        this.f23645a = aslVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        this.f23645a.subscribe(new C5586a(assVar));
    }

    /* compiled from: BodyObservable.java */
    /* renamed from: z1.ww$a */
    /* loaded from: classes3.dex */
    private static class C5586a<R> implements Observer<Response<R>> {

        /* renamed from: a */
        private final Observer<? super R> f23646a;

        /* renamed from: b */
        private boolean f23647b;

        C5586a(Observer<? super R> assVar) {
            this.f23646a = assVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            this.f23646a.onSubscribe(atrVar);
        }

        /* renamed from: a */
        public void onNext(Response<R> response) {
            if (response.isSuccessful()) {
                this.f23646a.onNext(response.body());
                return;
            }
            this.f23647b = true;
            HttpException wyVar = new HttpException(response);
            try {
                this.f23646a.onError(wyVar);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                RxJavaPlugins.m9212a(new CompositeException(wyVar, th));
            }
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f23647b) {
                this.f23646a.onComplete();
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (!this.f23647b) {
                this.f23646a.onError(th);
                return;
            }
            AssertionError assertionError = new AssertionError("This should never happen! Report as a bug with the full stacktrace.");
            assertionError.initCause(th);
            RxJavaPlugins.m9212a(assertionError);
        }
    }
}
