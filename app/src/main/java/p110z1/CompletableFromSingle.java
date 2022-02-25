package p110z1;

/* renamed from: z1.axt */
/* loaded from: classes3.dex */
public final class CompletableFromSingle<T> extends Completable {

    /* renamed from: a */
    final SingleSource<T> f17730a;

    public CompletableFromSingle(SingleSource<T> ataVar) {
        this.f17730a = ataVar;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        this.f17730a.mo10018a(new C3956a(arpVar));
    }

    /* compiled from: CompletableFromSingle.java */
    /* renamed from: z1.axt$a */
    /* loaded from: classes3.dex */
    static final class C3956a<T> implements SingleObserver<T> {

        /* renamed from: a */
        final CompletableObserver f17731a;

        C3956a(CompletableObserver arpVar) {
            this.f17731a = arpVar;
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f17731a.onError(th);
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            this.f17731a.onSubscribe(atrVar);
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            this.f17731a.onComplete();
        }
    }
}
