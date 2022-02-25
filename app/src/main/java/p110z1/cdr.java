package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;

/* compiled from: CoroutinesMigration.kt */
@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007\u001a\f\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0007\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\tH\u0007\u001a\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0007\u001a\f\u0010\u000b\u001a\u00020\u0006*\u00020\u0005H\u0007\u001a\f\u0010\f\u001a\u00020\t*\u00020\bH\u0007\u001a^\u0010\r\u001a\"\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000e\"\u0004\b\u0000\u0010\u000f\"\u0004\b\u0001\u0010\u0010\"\u0004\b\u0002\u0010\u0011*\"\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000eH\u0000\u001aL\u0010\r\u001a\u001c\u0012\u0004\u0012\u0002H\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0013\"\u0004\b\u0000\u0010\u000f\"\u0004\b\u0001\u0010\u0011*\u001c\u0012\u0004\u0012\u0002H\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0013H\u0000\u001a:\u0010\r\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0014\"\u0004\b\u0000\u0010\u0011*\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0014H\u0000¨\u0006\u0015"}, m8860e = {"toContinuation", "Lkotlin/coroutines/Continuation;", TessBaseAPI.f9204e, "Lkotlin/coroutines/experimental/Continuation;", "toContinuationInterceptor", "Lkotlin/coroutines/ContinuationInterceptor;", "Lkotlin/coroutines/experimental/ContinuationInterceptor;", "toCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "Lkotlin/coroutines/experimental/CoroutineContext;", "toExperimentalContinuation", "toExperimentalContinuationInterceptor", "toExperimentalCoroutineContext", "toExperimentalSuspendFunction", "Lkotlin/Function3;", "T1", "T2", "R", "", "Lkotlin/Function2;", "Lkotlin/Function1;", "kotlin-stdlib-coroutines"})
/* renamed from: z1.cdr */
/* loaded from: classes3.dex */
public final class cdr {
    @bwy(m8750a = "1.3")
    @NotNull
    /* renamed from: a */
    public static final <T> Coroutines<T> m5562a(@NotNull Continuation<? super T> ccpVar) {
        Coroutines<T> a;
        cji.m5162f(ccpVar, "$this$toExperimentalContinuation");
        cdq cdqVar = (cdq) (!(ccpVar instanceof cdq) ? null : ccpVar);
        return (cdqVar == null || (a = cdqVar.m5563a()) == null) ? new cdu(ccpVar) : a;
    }

    @bwy(m8750a = "1.3")
    @NotNull
    /* renamed from: a */
    public static final <T> Continuation<T> m5559a(@NotNull Coroutines<? super T> ccyVar) {
        Continuation<T> a;
        cji.m5162f(ccyVar, "$this$toContinuation");
        cdu cduVar = (cdu) (!(ccyVar instanceof cdu) ? null : ccyVar);
        return (cduVar == null || (a = cduVar.m5545a()) == null) ? new cdq(ccyVar) : a;
    }

    @bwy(m8750a = "1.3")
    @NotNull
    /* renamed from: a */
    public static final cda m5560a(@NotNull CoroutineContext ccsVar) {
        cdc cdcVar;
        cji.m5162f(ccsVar, "$this$toExperimentalCoroutineContext");
        ContinuationInterceptor ccqVar = (ContinuationInterceptor) ccsVar.get(ContinuationInterceptor.f20550a);
        CoroutinesMigration cdoVar = (CoroutinesMigration) ccsVar.get(CoroutinesMigration.f20593a);
        CoroutineContext minusKey = ccsVar.minusKey(ContinuationInterceptor.f20550a).minusKey(CoroutinesMigration.f20593a);
        if (cdoVar == null || (cdcVar = cdoVar.m5568b()) == null) {
            cdcVar = cdc.f20565a;
        }
        if (minusKey != cct.INSTANCE) {
            cdcVar = cdcVar.mo5548a(new cds(minusKey));
        }
        return ccqVar == null ? cdcVar : cdcVar.mo5548a(m5561a(ccqVar));
    }

    @bwy(m8750a = "1.3")
    @NotNull
    /* renamed from: a */
    public static final CoroutineContext m5557a(@NotNull cda cdaVar) {
        cct cctVar;
        cji.m5162f(cdaVar, "$this$toCoroutineContext");
        ccz cczVar = (ccz) cdaVar.mo5549a(ccz.f20561a);
        cds cdsVar = (cds) cdaVar.mo5549a(cds.f20598a);
        cda b = cdaVar.mo5546b(ccz.f20561a).mo5546b(cds.f20598a);
        if (cdsVar == null || (cctVar = cdsVar.m5553b()) == null) {
            cctVar = cct.INSTANCE;
        }
        if (b != cdc.f20565a) {
            cctVar = cctVar.plus(new CoroutinesMigration(b));
        }
        return cczVar == null ? cctVar : cctVar.plus(m5558a(cczVar));
    }

    @bwy(m8750a = "1.3")
    @NotNull
    /* renamed from: a */
    public static final ccz m5561a(@NotNull ContinuationInterceptor ccqVar) {
        ccz b;
        cji.m5162f(ccqVar, "$this$toExperimentalContinuationInterceptor");
        cdp cdpVar = (cdp) (!(ccqVar instanceof cdp) ? null : ccqVar);
        return (cdpVar == null || (b = cdpVar.m5565b()) == null) ? new cdt(ccqVar) : b;
    }

    @bwy(m8750a = "1.3")
    @NotNull
    /* renamed from: a */
    public static final ContinuationInterceptor m5558a(@NotNull ccz cczVar) {
        ContinuationInterceptor b;
        cji.m5162f(cczVar, "$this$toContinuationInterceptor");
        cdt cdtVar = (cdt) (!(cczVar instanceof cdt) ? null : cczVar);
        return (cdtVar == null || (b = cdtVar.m5547b()) == null) ? new cdp(cczVar) : b;
    }

    @NotNull
    /* renamed from: a */
    public static final <R> chd<Coroutines<? super R>, Object> m5556a(@NotNull chd<? super Continuation<? super R>, ? extends Object> chdVar) {
        cji.m5162f(chdVar, "$this$toExperimentalSuspendFunction");
        return new cdv(chdVar);
    }

    @NotNull
    /* renamed from: a */
    public static final <T1, R> cho<T1, Coroutines<? super R>, Object> m5555a(@NotNull cho<? super T1, ? super Continuation<? super R>, ? extends Object> choVar) {
        cji.m5162f(choVar, "$this$toExperimentalSuspendFunction");
        return new cdw(choVar);
    }

    @NotNull
    /* renamed from: a */
    public static final <T1, T2, R> chs<T1, T2, Coroutines<? super R>, Object> m5554a(@NotNull chs<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> chsVar) {
        cji.m5162f(chsVar, "$this$toExperimentalSuspendFunction");
        return new cdx(chsVar);
    }
}
