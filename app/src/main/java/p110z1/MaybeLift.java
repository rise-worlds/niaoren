package p110z1;

/* renamed from: z1.bgl */
/* loaded from: classes3.dex */
public final class MaybeLift<T, R> extends AbstractMaybeWithUpstream<T, R> {

    /* renamed from: b */
    final MaybeOperator<? extends R, ? super T> f18665b;

    public MaybeLift(MaybeSource<T> asiVar, MaybeOperator<? extends R, ? super T> ashVar) {
        super(asiVar);
        this.f18665b = ashVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super R> asfVar) {
        try {
            this.f18529a.mo10646a((MaybeObserver) ObjectHelper.m9873a(this.f18665b.m10647a(asfVar), "The operator returned a null MaybeObserver"));
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptyDisposable.error(th, asfVar);
        }
    }
}
