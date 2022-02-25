package p110z1;

/* renamed from: z1.bpb */
/* loaded from: classes3.dex */
public final class SingleDoOnSubscribe<T> extends Single<T> {

    /* renamed from: a */
    final SingleSource<T> f19754a;

    /* renamed from: b */
    final Consumer<? super Disposable> f19755b;

    public SingleDoOnSubscribe(SingleSource<T> ataVar, Consumer<? super Disposable> aumVar) {
        this.f19754a = ataVar;
        this.f19755b = aumVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        this.f19754a.mo10018a(new C4672a(asxVar, this.f19755b));
    }

    /* compiled from: SingleDoOnSubscribe.java */
    /* renamed from: z1.bpb$a */
    /* loaded from: classes3.dex */
    static final class C4672a<T> implements SingleObserver<T> {

        /* renamed from: a */
        final SingleObserver<? super T> f19756a;

        /* renamed from: b */
        final Consumer<? super Disposable> f19757b;

        /* renamed from: c */
        boolean f19758c;

        C4672a(SingleObserver<? super T> asxVar, Consumer<? super Disposable> aumVar) {
            this.f19756a = asxVar;
            this.f19757b = aumVar;
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            try {
                this.f19757b.accept(atrVar);
                this.f19756a.onSubscribe(atrVar);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.f19758c = true;
                atrVar.dispose();
                EmptyDisposable.error(th, this.f19756a);
            }
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            if (!this.f19758c) {
                this.f19756a.onSuccess(t);
            }
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            if (this.f19758c) {
                RxJavaPlugins.m9212a(th);
            } else {
                this.f19756a.onError(th);
            }
        }
    }
}
