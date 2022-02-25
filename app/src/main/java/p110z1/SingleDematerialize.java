package p110z1;

@Experimental
/* renamed from: z1.bot */
/* loaded from: classes3.dex */
public final class SingleDematerialize<T, R> extends Maybe<R> {

    /* renamed from: a */
    final Single<T> f19724a;

    /* renamed from: b */
    final Function<? super T, Notification<R>> f19725b;

    public SingleDematerialize(Single<T> asuVar, Function<? super T, Notification<R>> aunVar) {
        this.f19724a = asuVar;
        this.f19725b = aunVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super R> asfVar) {
        this.f19724a.mo10018a((SingleObserver) new C4663a(asfVar, this.f19725b));
    }

    /* compiled from: SingleDematerialize.java */
    /* renamed from: z1.bot$a */
    /* loaded from: classes3.dex */
    static final class C4663a<T, R> implements SingleObserver<T>, Disposable {

        /* renamed from: a */
        final MaybeObserver<? super R> f19726a;

        /* renamed from: b */
        final Function<? super T, Notification<R>> f19727b;

        /* renamed from: c */
        Disposable f19728c;

        C4663a(MaybeObserver<? super R> asfVar, Function<? super T, Notification<R>> aunVar) {
            this.f19726a = asfVar;
            this.f19727b = aunVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19728c.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19728c.isDisposed();
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19728c, atrVar)) {
                this.f19728c = atrVar;
                this.f19726a.onSubscribe(this);
            }
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            try {
                Notification askVar = (Notification) ObjectHelper.m9873a(this.f19727b.apply(t), "The selector returned a null Notification");
                if (askVar.m10640c()) {
                    this.f19726a.onSuccess((Object) askVar.m10639d());
                } else if (askVar.m10644a()) {
                    this.f19726a.onComplete();
                } else {
                    this.f19726a.onError(askVar.m10638e());
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.f19726a.onError(th);
            }
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f19726a.onError(th);
        }
    }
}
