package p110z1;

import java.io.Serializable;
import p110z1.CoroutineContext;
import p110z1.Ref;

/* compiled from: CoroutineContextImpl.kt */
@bwy(m8750a = "1.3")
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003:\u0001!B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0000H\u0002J\u0013\u0010\f\u001a\u00020\t2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0002J5\u0010\u000f\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u00102\u0006\u0010\u0011\u001a\u0002H\u00102\u0018\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u00100\u0013H\u0016¢\u0006\u0002\u0010\u0014J(\u0010\u0015\u001a\u0004\u0018\u0001H\u0016\"\b\b\u0000\u0010\u0016*\u00020\u00062\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00160\u0018H\u0096\u0002¢\u0006\u0002\u0010\u0019J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u0014\u0010\u001c\u001a\u00020\u00012\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u0018H\u0016J\b\u0010\u001d\u001a\u00020\u001bH\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u000eH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, m8860e = {"Lkotlin/coroutines/CombinedContext;", "Lkotlin/coroutines/CoroutineContext;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "left", "element", "Lkotlin/coroutines/CoroutineContext$Element;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/CoroutineContext$Element;)V", "contains", "", "containsAll", "context", "equals", "other", "", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", "E", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "hashCode", "", "minusKey", "size", "toString", "", "writeReplace", "Serialized", "kotlin-stdlib"})
/* renamed from: z1.cco */
/* loaded from: classes3.dex */
public final class cco implements Serializable, CoroutineContext {
    private final CoroutineContext.AbstractC4914b element;
    private final CoroutineContext left;

    /* compiled from: CoroutineContextImpl.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, m8860e = {"<anonymous>", "", "<anonymous parameter 0>", "element", "Lkotlin/coroutines/CoroutineContext$Element;", "invoke", "(Lkotlin/Unit;Lkotlin/coroutines/CoroutineContext$Element;)V"})
    /* renamed from: z1.cco$c */
    /* loaded from: classes3.dex */
    static final class C4908c extends Lambda implements cho<Unit, CoroutineContext.AbstractC4914b, Unit> {
        final /* synthetic */ CoroutineContext[] $elements;
        final /* synthetic */ Ref.C4970f $index;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C4908c(CoroutineContext[] ccsVarArr, Ref.C4970f fVar) {
            super(2);
            this.$elements = ccsVarArr;
            this.$index = fVar;
        }

        @Override // p110z1.cho
        public /* bridge */ /* synthetic */ Unit invoke(Unit bydVar, CoroutineContext.AbstractC4914b bVar) {
            invoke2(bydVar, bVar);
            return Unit.f20418a;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull Unit bydVar, @NotNull CoroutineContext.AbstractC4914b bVar) {
            cji.m5162f(bydVar, "<anonymous parameter 0>");
            cji.m5162f(bVar, "element");
            CoroutineContext[] ccsVarArr = this.$elements;
            Ref.C4970f fVar = this.$index;
            int i = fVar.element;
            fVar.element = i + 1;
            ccsVarArr[i] = bVar;
        }
    }

    public cco(@NotNull CoroutineContext ccsVar, @NotNull CoroutineContext.AbstractC4914b bVar) {
        cji.m5162f(ccsVar, "left");
        cji.m5162f(bVar, "element");
        this.left = ccsVar;
        this.element = bVar;
    }

    @Override // p110z1.CoroutineContext
    @NotNull
    public CoroutineContext plus(@NotNull CoroutineContext ccsVar) {
        cji.m5162f(ccsVar, "context");
        return CoroutineContext.C4912a.m5637a(this, ccsVar);
    }

    @Override // p110z1.CoroutineContext
    @dbs
    public <E extends CoroutineContext.AbstractC4914b> E get(@NotNull CoroutineContext.AbstractC4916c<E> cVar) {
        cji.m5162f(cVar, "key");
        CoroutineContext ccsVar = this;
        while (true) {
            cco ccoVar = (cco) ccsVar;
            E e = (E) ccoVar.element.get(cVar);
            if (e != null) {
                return e;
            }
            ccsVar = ccoVar.left;
            if (!(ccsVar instanceof cco)) {
                return (E) ccsVar.get(cVar);
            }
        }
    }

    @Override // p110z1.CoroutineContext
    public <R> R fold(R r, @NotNull cho<? super R, ? super CoroutineContext.AbstractC4914b, ? extends R> choVar) {
        cji.m5162f(choVar, "operation");
        return (R) choVar.invoke((Object) this.left.fold(r, choVar), this.element);
    }

    @Override // p110z1.CoroutineContext
    @NotNull
    public CoroutineContext minusKey(@NotNull CoroutineContext.AbstractC4916c<?> cVar) {
        cji.m5162f(cVar, "key");
        if (this.element.get(cVar) != null) {
            return this.left;
        }
        CoroutineContext minusKey = this.left.minusKey(cVar);
        return minusKey == this.left ? this : minusKey == cct.INSTANCE ? this.element : new cco(minusKey, this.element);
    }

    private final int size() {
        cco ccoVar = this;
        int i = 2;
        while (true) {
            CoroutineContext ccsVar = ccoVar.left;
            if (!(ccsVar instanceof cco)) {
                ccsVar = null;
            }
            ccoVar = (cco) ccsVar;
            if (ccoVar == null) {
                return i;
            }
            i++;
        }
    }

    private final boolean contains(CoroutineContext.AbstractC4914b bVar) {
        return cji.m5184a(get(bVar.mo5567a()), bVar);
    }

    private final boolean containsAll(cco ccoVar) {
        while (contains(ccoVar.element)) {
            CoroutineContext ccsVar = ccoVar.left;
            if (ccsVar instanceof cco) {
                ccoVar = (cco) ccsVar;
            } else if (ccsVar != null) {
                return contains((CoroutineContext.AbstractC4914b) ccsVar);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.CoroutineContext.Element");
            }
        }
        return false;
    }

    public boolean equals(@dbs Object obj) {
        if (this != obj) {
            if (obj instanceof cco) {
                cco ccoVar = (cco) obj;
                if (ccoVar.size() != size() || !ccoVar.containsAll(this)) {
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.left.hashCode() + this.element.hashCode();
    }

    /* compiled from: CoroutineContextImpl.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, m8860e = {"<anonymous>", "", "acc", "element", "Lkotlin/coroutines/CoroutineContext$Element;", "invoke"})
    /* renamed from: z1.cco$b */
    /* loaded from: classes3.dex */
    static final class C4907b extends Lambda implements cho<String, CoroutineContext.AbstractC4914b, String> {
        public static final C4907b INSTANCE = new C4907b();

        C4907b() {
            super(2);
        }

        @NotNull
        public final String invoke(@NotNull String str, @NotNull CoroutineContext.AbstractC4914b bVar) {
            cji.m5162f(str, "acc");
            cji.m5162f(bVar, "element");
            if (str.length() == 0) {
                return bVar.toString();
            }
            return str + ", " + bVar;
        }
    }

    @NotNull
    public String toString() {
        return "[" + ((String) fold("", C4907b.INSTANCE)) + "]";
    }

    private final Object writeReplace() {
        int size = size();
        CoroutineContext[] ccsVarArr = new CoroutineContext[size];
        Ref.C4970f fVar = new Ref.C4970f();
        boolean z = false;
        fVar.element = 0;
        fold(Unit.f20418a, new C4908c(ccsVarArr, fVar));
        if (fVar.element == size) {
            z = true;
        }
        if (z) {
            return new C4905a(ccsVarArr);
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    /* compiled from: CoroutineContextImpl.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u0000 \f2\u00060\u0001j\u0002`\u0002:\u0001\fB\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J\b\u0010\n\u001a\u00020\u000bH\u0002R\u0019\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, m8860e = {"Lkotlin/coroutines/CombinedContext$Serialized;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "elements", "", "Lkotlin/coroutines/CoroutineContext;", "([Lkotlin/coroutines/CoroutineContext;)V", "getElements", "()[Lkotlin/coroutines/CoroutineContext;", "[Lkotlin/coroutines/CoroutineContext;", "readResolve", "", "Companion", "kotlin-stdlib"})
    /* renamed from: z1.cco$a */
    /* loaded from: classes3.dex */
    private static final class C4905a implements Serializable {
        public static final C4906a Companion = new C4906a(null);
        private static final long serialVersionUID = 0;
        @NotNull
        private final CoroutineContext[] elements;

        /* compiled from: CoroutineContextImpl.kt */
        @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, m8860e = {"Lkotlin/coroutines/CombinedContext$Serialized$Companion;", "", "()V", "serialVersionUID", "", "kotlin-stdlib"})
        /* renamed from: z1.cco$a$a */
        /* loaded from: classes3.dex */
        public static final class C4906a {
            private C4906a() {
            }

            public /* synthetic */ C4906a(DefaultConstructorMarker civVar) {
                this();
            }
        }

        public C4905a(@NotNull CoroutineContext[] ccsVarArr) {
            cji.m5162f(ccsVarArr, "elements");
            this.elements = ccsVarArr;
        }

        @NotNull
        public final CoroutineContext[] getElements() {
            return this.elements;
        }

        private final Object readResolve() {
            CoroutineContext[] ccsVarArr = this.elements;
            CoroutineContext ccsVar = cct.INSTANCE;
            for (CoroutineContext ccsVar2 : ccsVarArr) {
                ccsVar = ccsVar.plus(ccsVar2);
            }
            return ccsVar;
        }
    }
}
