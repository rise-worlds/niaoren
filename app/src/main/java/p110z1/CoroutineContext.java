package p110z1;

@bwy(m8750a = "1.3")
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001:\u0002\u0011\u0012J5\u0010\u0002\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u00032\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002H\u00030\u0006H&¢\u0006\u0002\u0010\bJ(\u0010\t\u001a\u0004\u0018\u0001H\n\"\b\b\u0000\u0010\n*\u00020\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\n0\fH¦\u0002¢\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\u00020\u00002\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH&J\u0011\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0000H\u0096\u0002¨\u0006\u0013"}, m8860e = {"Lkotlin/coroutines/CoroutineContext;", "", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "Lkotlin/coroutines/CoroutineContext$Element;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", "E", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "minusKey", "plus", "context", "Element", "Key", "kotlin-stdlib"})
/* renamed from: z1.ccs */
/* loaded from: classes3.dex */
public interface CoroutineContext {

    /* compiled from: CoroutineContext.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003¨\u0006\u0004"}, m8860e = {"Lkotlin/coroutines/CoroutineContext$Key;", "E", "Lkotlin/coroutines/CoroutineContext$Element;", "", "kotlin-stdlib"})
    /* renamed from: z1.ccs$c */
    /* loaded from: classes3.dex */
    public interface AbstractC4916c<E extends AbstractC4914b> {
    }

    <R> R fold(R r, @NotNull cho<? super R, ? super AbstractC4914b, ? extends R> choVar);

    @dbs
    <E extends AbstractC4914b> E get(@NotNull AbstractC4916c<E> cVar);

    @NotNull
    CoroutineContext minusKey(@NotNull AbstractC4916c<?> cVar);

    @NotNull
    CoroutineContext plus(@NotNull CoroutineContext ccsVar);

    /* compiled from: CoroutineContext.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3})
    /* renamed from: z1.ccs$a */
    /* loaded from: classes3.dex */
    public static final class C4912a {
        @NotNull
        /* renamed from: a */
        public static CoroutineContext m5637a(CoroutineContext ccsVar, @NotNull CoroutineContext ccsVar2) {
            cji.m5162f(ccsVar2, "context");
            return ccsVar2 == cct.INSTANCE ? ccsVar : (CoroutineContext) ccsVar2.fold(ccsVar, C4913a.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: CoroutineContext.kt */
        @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, m8860e = {"<anonymous>", "Lkotlin/coroutines/CoroutineContext;", "acc", "element", "Lkotlin/coroutines/CoroutineContext$Element;", "invoke"})
        /* renamed from: z1.ccs$a$a */
        /* loaded from: classes3.dex */
        public static final class C4913a extends Lambda implements cho<CoroutineContext, AbstractC4914b, CoroutineContext> {
            public static final C4913a INSTANCE = new C4913a();

            C4913a() {
                super(2);
            }

            @NotNull
            public final CoroutineContext invoke(@NotNull CoroutineContext ccsVar, @NotNull AbstractC4914b bVar) {
                cco ccoVar;
                cji.m5162f(ccsVar, "acc");
                cji.m5162f(bVar, "element");
                CoroutineContext minusKey = ccsVar.minusKey(bVar.mo5567a());
                if (minusKey == cct.INSTANCE) {
                    return bVar;
                }
                ContinuationInterceptor ccqVar = (ContinuationInterceptor) minusKey.get(ContinuationInterceptor.f20550a);
                if (ccqVar == null) {
                    ccoVar = new cco(minusKey, bVar);
                } else {
                    CoroutineContext minusKey2 = minusKey.minusKey(ContinuationInterceptor.f20550a);
                    ccoVar = minusKey2 == cct.INSTANCE ? new cco(bVar, ccqVar) : new cco(new cco(minusKey2, bVar), ccqVar);
                }
                return ccoVar;
            }
        }
    }

    /* compiled from: CoroutineContext.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J5\u0010\u0006\u001a\u0002H\u0007\"\u0004\b\u0000\u0010\u00072\u0006\u0010\b\u001a\u0002H\u00072\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u0002H\u00070\nH\u0016¢\u0006\u0002\u0010\u000bJ(\u0010\f\u001a\u0004\u0018\u0001H\r\"\b\b\u0000\u0010\r*\u00020\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\r0\u0003H\u0096\u0002¢\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\u00020\u00012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0016R\u0016\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0010"}, m8860e = {"Lkotlin/coroutines/CoroutineContext$Element;", "Lkotlin/coroutines/CoroutineContext;", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", "E", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "minusKey", "kotlin-stdlib"})
    /* renamed from: z1.ccs$b */
    /* loaded from: classes3.dex */
    public interface AbstractC4914b extends CoroutineContext {
        @NotNull
        /* renamed from: a */
        AbstractC4916c<?> mo5567a();

        @Override // p110z1.CoroutineContext
        <R> R fold(R r, @NotNull cho<? super R, ? super AbstractC4914b, ? extends R> choVar);

        @Override // p110z1.CoroutineContext
        @dbs
        <E extends AbstractC4914b> E get(@NotNull AbstractC4916c<E> cVar);

        @Override // p110z1.CoroutineContext
        @NotNull
        CoroutineContext minusKey(@NotNull AbstractC4916c<?> cVar);

        /* compiled from: CoroutineContext.kt */
        @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3})
        /* renamed from: z1.ccs$b$a */
        /* loaded from: classes3.dex */
        public static final class C4915a {
            @NotNull
            /* renamed from: a */
            public static CoroutineContext m5634a(AbstractC4914b bVar, @NotNull CoroutineContext ccsVar) {
                cji.m5162f(ccsVar, "context");
                return C4912a.m5637a(bVar, ccsVar);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @dbs
            /* renamed from: a */
            public static <E extends AbstractC4914b> E m5635a(AbstractC4914b bVar, @NotNull AbstractC4916c<E> cVar) {
                cji.m5162f(cVar, "key");
                if (!cji.m5184a(bVar.mo5567a(), cVar)) {
                    return null;
                }
                if (bVar != 0) {
                    return bVar;
                }
                throw new TypeCastException("null cannot be cast to non-null type E");
            }

            /* renamed from: a */
            public static <R> R m5636a(AbstractC4914b bVar, R r, @NotNull cho<? super R, ? super AbstractC4914b, ? extends R> choVar) {
                cji.m5162f(choVar, "operation");
                return (R) choVar.invoke(r, bVar);
            }

            @NotNull
            /* renamed from: b */
            public static CoroutineContext m5633b(AbstractC4914b bVar, @NotNull AbstractC4916c<?> cVar) {
                cji.m5162f(cVar, "key");
                boolean a = cji.m5184a(bVar.mo5567a(), cVar);
                CoroutineContext ccsVar = bVar;
                if (a) {
                    ccsVar = cct.INSTANCE;
                }
                return ccsVar;
            }
        }
    }
}
