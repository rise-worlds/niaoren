package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import p110z1.ccz;
import p110z1.cda;

/* compiled from: CoroutinesMigration.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\"\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\r0\f\"\u0004\b\u0000\u0010\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000f"}, m8860e = {"Lkotlin/coroutines/experimental/migration/ExperimentalContinuationInterceptorMigration;", "Lkotlin/coroutines/experimental/ContinuationInterceptor;", "interceptor", "Lkotlin/coroutines/ContinuationInterceptor;", "(Lkotlin/coroutines/ContinuationInterceptor;)V", "getInterceptor", "()Lkotlin/coroutines/ContinuationInterceptor;", "key", "Lkotlin/coroutines/experimental/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/experimental/CoroutineContext$Key;", "interceptContinuation", "Lkotlin/coroutines/experimental/Continuation;", TessBaseAPI.f9204e, "continuation", "kotlin-stdlib-coroutines"})
/* renamed from: z1.cdt */
/* loaded from: classes3.dex */
final class cdt implements ccz {
    @NotNull

    /* renamed from: b */
    private final ContinuationInterceptor f20600b;

    public cdt(@NotNull ContinuationInterceptor ccqVar) {
        cji.m5162f(ccqVar, "interceptor");
        this.f20600b = ccqVar;
    }

    @Override // p110z1.cda.AbstractC4924b, p110z1.cda
    /* renamed from: a */
    public <R> R mo5551a(R r, @NotNull cho<? super R, ? super cda.AbstractC4924b, ? extends R> choVar) {
        cji.m5162f(choVar, "operation");
        return (R) ccz.C4919a.m5625a(this, r, choVar);
    }

    @Override // p110z1.cda.AbstractC4924b, p110z1.cda
    @dbs
    /* renamed from: a */
    public <E extends cda.AbstractC4924b> E mo5549a(@NotNull cda.AbstractC4926c<E> cVar) {
        cji.m5162f(cVar, "key");
        return (E) ccz.C4919a.m5624a(this, cVar);
    }

    @Override // p110z1.cda
    @NotNull
    /* renamed from: a */
    public cda mo5548a(@NotNull cda cdaVar) {
        cji.m5162f(cdaVar, "context");
        return ccz.C4919a.m5623a(this, cdaVar);
    }

    @NotNull
    /* renamed from: b */
    public final ContinuationInterceptor m5547b() {
        return this.f20600b;
    }

    @Override // p110z1.cda.AbstractC4924b, p110z1.cda
    @NotNull
    /* renamed from: b */
    public cda mo5546b(@NotNull cda.AbstractC4926c<?> cVar) {
        cji.m5162f(cVar, "key");
        return ccz.C4919a.m5622b(this, cVar);
    }

    @Override // p110z1.cda.AbstractC4924b
    @NotNull
    /* renamed from: a */
    public cda.AbstractC4926c<?> mo5552a() {
        return ccz.f20561a;
    }

    @Override // p110z1.ccz
    @NotNull
    /* renamed from: a */
    public <T> Coroutines<T> mo5550a(@NotNull Coroutines<? super T> ccyVar) {
        cji.m5162f(ccyVar, "continuation");
        return cdr.m5562a(this.f20600b.mo5566a(cdr.m5559a(ccyVar)));
    }
}
