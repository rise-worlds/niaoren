package p110z1;

/* compiled from: CoroutineContext.kt */
@bwy(m8750a = "1.1")
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001:\u0002\u0011\u0012J5\u0010\u0002\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u00032\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002H\u00030\u0006H&¢\u0006\u0002\u0010\bJ(\u0010\t\u001a\u0004\u0018\u0001H\n\"\b\b\u0000\u0010\n*\u00020\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\n0\fH¦\u0002¢\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\u00020\u00002\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH&J\u0011\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0000H\u0096\u0002¨\u0006\u0013"}, m8860e = {"Lkotlin/coroutines/experimental/CoroutineContext;", "", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "Lkotlin/coroutines/experimental/CoroutineContext$Element;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", "E", "key", "Lkotlin/coroutines/experimental/CoroutineContext$Key;", "(Lkotlin/coroutines/experimental/CoroutineContext$Key;)Lkotlin/coroutines/experimental/CoroutineContext$Element;", "minusKey", "plus", "context", "Element", "Key", "kotlin-stdlib-coroutines"})
/* renamed from: z1.cda */
/* loaded from: classes3.dex */
public interface cda {

    /* compiled from: CoroutineContext.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003¨\u0006\u0004"}, m8860e = {"Lkotlin/coroutines/experimental/CoroutineContext$Key;", "E", "Lkotlin/coroutines/experimental/CoroutineContext$Element;", "", "kotlin-stdlib-coroutines"})
    /* renamed from: z1.cda$c */
    /* loaded from: classes3.dex */
    public interface AbstractC4926c<E extends AbstractC4924b> {
    }

    /* renamed from: a */
    <R> R mo5551a(R r, @NotNull cho<? super R, ? super AbstractC4924b, ? extends R> choVar);

    @dbs
    /* renamed from: a */
    <E extends AbstractC4924b> E mo5549a(@NotNull AbstractC4926c<E> cVar);

    @NotNull
    /* renamed from: a */
    cda mo5548a(@NotNull cda cdaVar);

    @NotNull
    /* renamed from: b */
    cda mo5546b(@NotNull AbstractC4926c<?> cVar);

    /* compiled from: CoroutineContext.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3})
    /* renamed from: z1.cda$a */
    /* loaded from: classes3.dex */
    public static final class C4922a {
        @NotNull
        /* renamed from: a */
        public static cda m5611a(cda cdaVar, @NotNull cda cdaVar2) {
            cji.m5162f(cdaVar2, "context");
            return cdaVar2 == cdc.f20565a ? cdaVar : (cda) cdaVar2.mo5551a(cdaVar, C4923a.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: CoroutineContext.kt */
        @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, m8860e = {"<anonymous>", "Lkotlin/coroutines/experimental/CoroutineContext;", "acc", "element", "Lkotlin/coroutines/experimental/CoroutineContext$Element;", "invoke"})
        /* renamed from: z1.cda$a$a */
        /* loaded from: classes3.dex */
        public static final class C4923a extends Lambda implements cho<cda, AbstractC4924b, cda> {
            public static final C4923a INSTANCE = new C4923a();

            C4923a() {
                super(2);
            }

            @NotNull
            public final cda invoke(@NotNull cda cdaVar, @NotNull AbstractC4924b bVar) {
                ccx ccxVar;
                cji.m5162f(cdaVar, "acc");
                cji.m5162f(bVar, "element");
                cda b = cdaVar.mo5546b(bVar.mo5552a());
                if (b == cdc.f20565a) {
                    return bVar;
                }
                ccz cczVar = (ccz) b.mo5549a(ccz.f20561a);
                if (cczVar == null) {
                    ccxVar = new ccx(b, bVar);
                } else {
                    cda b2 = b.mo5546b(ccz.f20561a);
                    ccxVar = b2 == cdc.f20565a ? new ccx(bVar, cczVar) : new ccx(new ccx(b2, bVar), cczVar);
                }
                return ccxVar;
            }
        }
    }

    /* compiled from: CoroutineContext.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J5\u0010\u0006\u001a\u0002H\u0007\"\u0004\b\u0000\u0010\u00072\u0006\u0010\b\u001a\u0002H\u00072\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u0002H\u00070\nH\u0016¢\u0006\u0002\u0010\u000bJ(\u0010\f\u001a\u0004\u0018\u0001H\r\"\b\b\u0000\u0010\r*\u00020\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\r0\u0003H\u0096\u0002¢\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\u00020\u00012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0016R\u0016\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0010"}, m8860e = {"Lkotlin/coroutines/experimental/CoroutineContext$Element;", "Lkotlin/coroutines/experimental/CoroutineContext;", "key", "Lkotlin/coroutines/experimental/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/experimental/CoroutineContext$Key;", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", "E", "(Lkotlin/coroutines/experimental/CoroutineContext$Key;)Lkotlin/coroutines/experimental/CoroutineContext$Element;", "minusKey", "kotlin-stdlib-coroutines"})
    /* renamed from: z1.cda$b */
    /* loaded from: classes3.dex */
    public interface AbstractC4924b extends cda {
        @Override // p110z1.cda
        /* renamed from: a */
        <R> R mo5551a(R r, @NotNull cho<? super R, ? super AbstractC4924b, ? extends R> choVar);

        @Override // p110z1.cda
        @dbs
        /* renamed from: a */
        <E extends AbstractC4924b> E mo5549a(@NotNull AbstractC4926c<E> cVar);

        @NotNull
        /* renamed from: a */
        AbstractC4926c<?> mo5552a();

        @Override // p110z1.cda
        @NotNull
        /* renamed from: b */
        cda mo5546b(@NotNull AbstractC4926c<?> cVar);

        /* compiled from: CoroutineContext.kt */
        @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3})
        /* renamed from: z1.cda$b$a */
        /* loaded from: classes3.dex */
        public static final class C4925a {
            @NotNull
            /* renamed from: a */
            public static cda m5608a(AbstractC4924b bVar, @NotNull cda cdaVar) {
                cji.m5162f(cdaVar, "context");
                return C4922a.m5611a(bVar, cdaVar);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @dbs
            /* renamed from: a */
            public static <E extends AbstractC4924b> E m5609a(AbstractC4924b bVar, @NotNull AbstractC4926c<E> cVar) {
                cji.m5162f(cVar, "key");
                if (bVar.mo5552a() != cVar) {
                    return null;
                }
                if (bVar != 0) {
                    return bVar;
                }
                throw new TypeCastException("null cannot be cast to non-null type E");
            }

            /* renamed from: a */
            public static <R> R m5610a(AbstractC4924b bVar, R r, @NotNull cho<? super R, ? super AbstractC4924b, ? extends R> choVar) {
                cji.m5162f(choVar, "operation");
                return (R) choVar.invoke(r, bVar);
            }

            @NotNull
            /* renamed from: b */
            public static cda m5607b(AbstractC4924b bVar, @NotNull AbstractC4926c<?> cVar) {
                cji.m5162f(cVar, "key");
                AbstractC4926c<?> a = bVar.mo5552a();
                cda cdaVar = bVar;
                if (a == cVar) {
                    cdaVar = cdc.f20565a;
                }
                return cdaVar;
            }
        }
    }
}
