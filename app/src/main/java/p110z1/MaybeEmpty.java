package p110z1;

/* renamed from: z1.bfl */
/* loaded from: classes3.dex */
public final class MaybeEmpty extends Maybe<Object> implements ScalarCallable<Object> {

    /* renamed from: a */
    public static final MaybeEmpty f18594a = new MaybeEmpty();

    @Override // p110z1.ScalarCallable, java.util.concurrent.Callable
    public Object call() {
        return null;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super Object> asfVar) {
        EmptyDisposable.complete(asfVar);
    }
}
