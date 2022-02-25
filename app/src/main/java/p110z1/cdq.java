package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CoroutinesMigration.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\u001e\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u0010R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, m8860e = {"Lkotlin/coroutines/experimental/migration/ContinuationMigration;", TessBaseAPI.f9204e, "Lkotlin/coroutines/Continuation;", "continuation", "Lkotlin/coroutines/experimental/Continuation;", "(Lkotlin/coroutines/experimental/Continuation;)V", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "getContinuation", "()Lkotlin/coroutines/experimental/Continuation;", "resumeWith", "", C4985cm.f20833c, "Lkotlin/Result;", "(Ljava/lang/Object;)V", "kotlin-stdlib-coroutines"})
/* renamed from: z1.cdq */
/* loaded from: classes3.dex */
public final class cdq<T> implements Continuation<T> {
    @NotNull

    /* renamed from: a */
    private final CoroutineContext f20596a;
    @NotNull

    /* renamed from: b */
    private final Coroutines<T> f20597b;

    /* JADX WARN: Multi-variable type inference failed */
    public cdq(@NotNull Coroutines<? super T> ccyVar) {
        cji.m5162f(ccyVar, "continuation");
        this.f20597b = ccyVar;
        this.f20596a = cdr.m5557a(this.f20597b.getContext());
    }

    @NotNull
    /* renamed from: a */
    public final Coroutines<T> m5563a() {
        return this.f20597b;
    }

    @Override // p110z1.Continuation
    @NotNull
    public CoroutineContext getContext() {
        return this.f20596a;
    }

    @Override // p110z1.Continuation
    public void resumeWith(@NotNull Object obj) {
        if (Result.m25730isSuccessimpl(obj)) {
            this.f20597b.resume(obj);
        }
        Throwable th = Result.m25726exceptionOrNullimpl(obj);
        if (th != null) {
            this.f20597b.resumeWithException(th);
        }
    }
}
