package p110z1;

import p110z1.CoroutineContext;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, m8860e = {"Lkotlin/coroutines/experimental/migration/ContextMigration;", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "(Lkotlin/coroutines/experimental/CoroutineContext;)V", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", "Key", "kotlin-stdlib-coroutines"})
/* renamed from: z1.cdo */
/* loaded from: classes3.dex */
final class CoroutinesMigration extends CoroutineContextImpl {

    /* renamed from: a */
    public static final C4933a f20593a = new C4933a(null);
    @NotNull

    /* renamed from: b */
    private final cda f20594b;

    /* compiled from: CoroutinesMigration.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, m8860e = {"Lkotlin/coroutines/experimental/migration/ContextMigration$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlin/coroutines/experimental/migration/ContextMigration;", "()V", "kotlin-stdlib-coroutines"})
    /* renamed from: z1.cdo$a */
    /* loaded from: classes3.dex */
    public static final class C4933a implements CoroutineContext.AbstractC4916c<CoroutinesMigration> {
        private C4933a() {
        }

        public /* synthetic */ C4933a(DefaultConstructorMarker civVar) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutinesMigration(@NotNull cda cdaVar) {
        super(f20593a);
        cji.m5162f(cdaVar, "context");
        this.f20594b = cdaVar;
    }

    @NotNull
    /* renamed from: b */
    public final cda m5568b() {
        return this.f20594b;
    }
}
