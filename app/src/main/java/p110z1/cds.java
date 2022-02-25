package p110z1;

import p110z1.cda;

/* compiled from: CoroutinesMigration.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, m8860e = {"Lkotlin/coroutines/experimental/migration/ExperimentalContextMigration;", "Lkotlin/coroutines/experimental/AbstractCoroutineContextElement;", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "Key", "kotlin-stdlib-coroutines"})
/* renamed from: z1.cds */
/* loaded from: classes3.dex */
final class cds extends ccw {

    /* renamed from: a */
    public static final C4934a f20598a = new C4934a(null);
    @NotNull

    /* renamed from: b */
    private final CoroutineContext f20599b;

    /* compiled from: CoroutinesMigration.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, m8860e = {"Lkotlin/coroutines/experimental/migration/ExperimentalContextMigration$Key;", "Lkotlin/coroutines/experimental/CoroutineContext$Key;", "Lkotlin/coroutines/experimental/migration/ExperimentalContextMigration;", "()V", "kotlin-stdlib-coroutines"})
    /* renamed from: z1.cds$a */
    /* loaded from: classes3.dex */
    public static final class C4934a implements cda.AbstractC4926c<cds> {
        private C4934a() {
        }

        public /* synthetic */ C4934a(DefaultConstructorMarker civVar) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public cds(@NotNull CoroutineContext ccsVar) {
        super(f20598a);
        cji.m5162f(ccsVar, "context");
        this.f20599b = ccsVar;
    }

    @NotNull
    /* renamed from: b */
    public final CoroutineContext m5553b() {
        return this.f20599b;
    }
}
