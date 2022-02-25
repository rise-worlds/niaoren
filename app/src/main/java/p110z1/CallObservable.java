package p110z1;

import retrofit2.Call;
import retrofit2.Response;

/* renamed from: z1.wx */
/* loaded from: classes3.dex */
final class CallObservable<T> extends Observable<Response<T>> {

    /* renamed from: a */
    private final Call<T> f23648a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CallObservable(Call<T> call) {
        this.f23648a = call;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super Response<T>> assVar) {
        boolean z;
        Throwable th;
        Call<T> clone = this.f23648a.clone();
        assVar.onSubscribe(new C5587a(clone));
        try {
            Response<T> execute = clone.execute();
            if (!clone.isCanceled()) {
                assVar.onNext(execute);
            }
            if (!clone.isCanceled()) {
                try {
                    assVar.onComplete();
                } catch (Throwable th2) {
                    th = th2;
                    z = true;
                    Exceptions.m9983b(th);
                    if (z) {
                        RxJavaPlugins.m9212a(th);
                    } else if (!clone.isCanceled()) {
                        try {
                            assVar.onError(th);
                        } catch (Throwable th3) {
                            Exceptions.m9983b(th3);
                            RxJavaPlugins.m9212a(new CompositeException(th, th3));
                        }
                    }
                }
            }
        } catch (Throwable th4) {
            th = th4;
            z = false;
        }
    }

    /* compiled from: CallObservable.java */
    /* renamed from: z1.wx$a */
    /* loaded from: classes3.dex */
    private static final class C5587a implements Disposable {

        /* renamed from: a */
        private final Call<?> f23649a;

        C5587a(Call<?> call) {
            this.f23649a = call;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f23649a.cancel();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f23649a.isCanceled();
        }
    }
}
