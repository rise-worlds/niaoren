package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import p110z1.ContinuationInterceptor;
import p110z1.CoroutineContext;

/* compiled from: CoroutinesMigration.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\"\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\r0\f\"\u0004\b\u0000\u0010\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000f"}, m8860e = {"Lkotlin/coroutines/experimental/migration/ContinuationInterceptorMigration;", "Lkotlin/coroutines/ContinuationInterceptor;", "interceptor", "Lkotlin/coroutines/experimental/ContinuationInterceptor;", "(Lkotlin/coroutines/experimental/ContinuationInterceptor;)V", "getInterceptor", "()Lkotlin/coroutines/experimental/ContinuationInterceptor;", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "interceptContinuation", "Lkotlin/coroutines/Continuation;", TessBaseAPI.f9204e, "continuation", "kotlin-stdlib-coroutines"})
/* renamed from: z1.cdp */
/* loaded from: classes3.dex */
final class cdp implements ContinuationInterceptor {
    @NotNull

    /* renamed from: b */
    private final ccz f20595b;

    public cdp(@NotNull ccz cczVar) {
        cji.m5162f(cczVar, "interceptor");
        this.f20595b = cczVar;
    }

    @NotNull
    /* renamed from: b */
    public final ccz m5565b() {
        return this.f20595b;
    }

    @Override // p110z1.ContinuationInterceptor
    /* renamed from: b */
    public void mo5564b(@NotNull Continuation<?> ccpVar) {
        cji.m5162f(ccpVar, "continuation");
        ContinuationInterceptor.C4909a.m5651a(this, ccpVar);
    }

    @Override // p110z1.CoroutineContext.AbstractC4914b, p110z1.CoroutineContext
    public <R> R fold(R r, @NotNull cho<? super R, ? super CoroutineContext.AbstractC4914b, ? extends R> choVar) {
        cji.m5162f(choVar, "operation");
        return (R) ContinuationInterceptor.C4909a.m5652a(this, r, choVar);
    }

    @Override // p110z1.ContinuationInterceptor, p110z1.CoroutineContext.AbstractC4914b, p110z1.CoroutineContext
    @dbs
    public <E extends CoroutineContext.AbstractC4914b> E get(@NotNull CoroutineContext.AbstractC4916c<E> cVar) {
        cji.m5162f(cVar, "key");
        return (E) ContinuationInterceptor.C4909a.m5650a(this, cVar);
    }

    @Override // p110z1.ContinuationInterceptor, p110z1.CoroutineContext.AbstractC4914b, p110z1.CoroutineContext
    @NotNull
    public CoroutineContext minusKey(@NotNull CoroutineContext.AbstractC4916c<?> cVar) {
        cji.m5162f(cVar, "key");
        return ContinuationInterceptor.C4909a.m5648b(this, cVar);
    }

    @Override // p110z1.CoroutineContext
    @NotNull
    public CoroutineContext plus(@NotNull CoroutineContext ccsVar) {
        cji.m5162f(ccsVar, "context");
        return ContinuationInterceptor.C4909a.m5649a(this, ccsVar);
    }

    @Override // p110z1.CoroutineContext.AbstractC4914b
    @NotNull
    /* renamed from: a */
    public CoroutineContext.AbstractC4916c<?> mo5567a() {
        return ContinuationInterceptor.f20550a;
    }

    @Override // p110z1.ContinuationInterceptor
    @NotNull
    /* renamed from: a */
    public <T> Continuation<T> mo5566a(@NotNull Continuation<? super T> ccpVar) {
        cji.m5162f(ccpVar, "continuation");
        return cdr.m5559a(this.f20595b.mo5550a(cdr.m5562a(ccpVar)));
    }
}
