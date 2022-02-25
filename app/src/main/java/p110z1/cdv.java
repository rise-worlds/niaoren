package p110z1;

/* compiled from: CoroutinesMigration.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0002B!\u0012\u001a\u0010\u0005\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0002¢\u0006\u0002\u0010\u0007J\u0019\u0010\n\u001a\u0004\u0018\u00010\u00042\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096\u0002R%\u0010\u0005\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0002¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\f"}, m8860e = {"Lkotlin/coroutines/experimental/migration/ExperimentalSuspendFunction0Migration;", "R", "Lkotlin/Function1;", "Lkotlin/coroutines/experimental/Continuation;", "", "function", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function1;)V", "getFunction", "()Lkotlin/jvm/functions/Function1;", "invoke", "continuation", "kotlin-stdlib-coroutines"})
/* renamed from: z1.cdv */
/* loaded from: classes3.dex */
final class cdv<R> implements chd<Coroutines<? super R>, Object> {
    @NotNull

    /* renamed from: a */
    private final chd<Continuation<? super R>, Object> f20603a;

    /* JADX WARN: Multi-variable type inference failed */
    public cdv(@NotNull chd<? super Continuation<? super R>, ? extends Object> chdVar) {
        cji.m5162f(chdVar, "function");
        this.f20603a = chdVar;
    }

    @NotNull
    /* renamed from: a */
    public final chd<Continuation<? super R>, Object> m5544a() {
        return this.f20603a;
    }

    @dbs
    /* renamed from: a */
    public Object invoke(@NotNull Coroutines<? super R> ccyVar) {
        cji.m5162f(ccyVar, "continuation");
        return this.f20603a.invoke(cdr.m5559a(ccyVar));
    }
}
