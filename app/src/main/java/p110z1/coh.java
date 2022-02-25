package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Sequences.kt */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\u001c\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\u001a+\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0014\b\u0004\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004H\u0087\b\u001a\u0012\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002\u001a&\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0004\u001a<\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\b2\u000e\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u00042\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u000b\u001a=\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\b2\b\u0010\f\u001a\u0004\u0018\u0001H\u00022\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u000bH\u0007¢\u0006\u0002\u0010\r\u001a+\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0012\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0010\"\u0002H\u0002¢\u0006\u0002\u0010\u0011\u001a\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0005\u001a\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001\u001aC\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00150\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0015*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00150\u00050\u000bH\u0002¢\u0006\u0002\b\u0016\u001a)\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00170\u0001H\u0007¢\u0006\u0002\b\u0018\u001a\"\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0001\u001a2\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0012\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0004H\u0007\u001a!\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001H\u0087\b\u001a@\u0010\u001c\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00150\u001e0\u001d\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0015*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00150\u001d0\u0001¨\u0006\u001f"}, m8860e = {"Sequence", "Lkotlin/sequences/Sequence;", TessBaseAPI.f9204e, "iterator", "Lkotlin/Function0;", "", "emptySequence", "generateSequence", "", "nextFunction", "seedFunction", "Lkotlin/Function1;", "seed", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Lkotlin/sequences/Sequence;", "sequenceOf", "elements", "", "([Ljava/lang/Object;)Lkotlin/sequences/Sequence;", "asSequence", "constrainOnce", "flatten", "R", "flatten$SequencesKt__SequencesKt", "", "flattenSequenceOfIterable", "ifEmpty", "defaultValue", "orEmpty", "unzip", "Lkotlin/Pair;", "", "kotlin-stdlib"}, m8859f = "kotlin/sequences/SequencesKt", m8857h = 1)
/* renamed from: z1.coh */
/* loaded from: classes3.dex */
public class coh extends cog {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Sequences.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0004\n\u0002\b\u0006\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, m8860e = {"<anonymous>", TessBaseAPI.f9204e, "R", "it", "invoke", "(Ljava/lang/Object;)Ljava/lang/Object;"})
    /* renamed from: z1.coh$e */
    /* loaded from: classes3.dex */
    public static final class C5028e extends Lambda implements chd<T, T> {
        public static final C5028e INSTANCE = new C5028e();

        C5028e() {
            super(1);
        }

        @Override // p110z1.chd
        public final T invoke(T t) {
            return t;
        }
    }

    /* compiled from: Sequences.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u000f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096\u0002¨\u0006\u0004"}, m8860e = {"kotlin/sequences/SequencesKt__SequencesKt$Sequence$1", "Lkotlin/sequences/Sequence;", "iterator", "", "kotlin-stdlib"})
    /* renamed from: z1.coh$a */
    /* loaded from: classes3.dex */
    public static final class C5024a implements Sequence<T> {

        /* renamed from: a */
        final /* synthetic */ chc f20949a;

        public C5024a(chc chcVar) {
            this.f20949a = chcVar;
        }

        @Override // p110z1.Sequence
        @NotNull
        /* renamed from: a */
        public Iterator<T> mo3707a() {
            return (Iterator) this.f20949a.invoke();
        }
    }

    /* compiled from: Sequences.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u000f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096\u0002¨\u0006\u0004¸\u0006\u0000"}, m8860e = {"kotlin/sequences/SequencesKt__SequencesKt$Sequence$1", "Lkotlin/sequences/Sequence;", "iterator", "", "kotlin-stdlib"})
    /* renamed from: z1.coh$b */
    /* loaded from: classes3.dex */
    public static final class C5025b implements Sequence<T> {

        /* renamed from: a */
        final /* synthetic */ Iterator f20950a;

        public C5025b(Iterator it) {
            this.f20950a = it;
        }

        @Override // p110z1.Sequence
        @NotNull
        /* renamed from: a */
        public Iterator<T> mo3707a() {
            return this.f20950a;
        }
    }

    @cey
    /* renamed from: b */
    private static final <T> Sequence<T> m4439b(chc<? extends Iterator<? extends T>> chcVar) {
        return new C5024a(chcVar);
    }

    @NotNull
    /* renamed from: a */
    public static final <T> Sequence<T> m4447a(@NotNull Iterator<? extends T> it) {
        cji.m5162f(it, "$this$asSequence");
        return coe.m4436d(new C5025b(it));
    }

    @NotNull
    /* renamed from: a */
    public static final <T> Sequence<T> m4441a(@NotNull T... tArr) {
        cji.m5162f(tArr, "elements");
        return tArr.length == 0 ? coe.m4440b() : bzb.m7962D(tArr);
    }

    @NotNull
    /* renamed from: b */
    public static final <T> Sequence<T> m4440b() {
        return cnv.f20899a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: e */
    private static final <T> Sequence<T> m4435e(@dbs Sequence<? extends T> cobVar) {
        return cobVar != 0 ? cobVar : coe.m4440b();
    }

    /* compiled from: Sequences.kt */
    @DebugMetadata(m5517b = "Sequences.kt", m5516c = {67, 69}, m5515d = {"$this$sequence", "iterator", "$this$sequence", "iterator"}, m5514e = {"L$0", "L$1", "L$0", "L$1"}, m5513f = {0, 0, 1, 1}, m5512g = "invokeSuspend", m5511h = "kotlin.sequences.SequencesKt__SequencesKt$ifEmpty$1")
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, m8860e = {"<anonymous>", "", TessBaseAPI.f9204e, "Lkotlin/sequences/SequenceScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"})
    /* renamed from: z1.coh$h */
    /* loaded from: classes3.dex */
    static final class C5031h extends cem implements cho<cod<? super T>, Continuation<? super Unit>, Object> {
        final /* synthetic */ chc $defaultValue;
        final /* synthetic */ Sequence $this_ifEmpty;
        Object L$0;
        Object L$1;
        int label;

        /* renamed from: p$ */
        private cod f20951p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C5031h(Sequence cobVar, chc chcVar, Continuation ccpVar) {
            super(2, ccpVar);
            this.$this_ifEmpty = cobVar;
            this.$defaultValue = chcVar;
        }

        @Override // p110z1.ContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@dbs Object obj, @NotNull Continuation<?> ccpVar) {
            cji.m5162f(ccpVar, "completion");
            C5031h hVar = new C5031h(this.$this_ifEmpty, this.$defaultValue, ccpVar);
            hVar.f20951p$ = (cod) obj;
            return hVar;
        }

        @Override // p110z1.cho
        public final Object invoke(Object obj, Continuation<? super Unit> ccpVar) {
            return ((C5031h) create(obj, ccpVar)).invokeSuspend(Unit.f20418a);
        }

        @Override // p110z1.ContinuationImpl
        @dbs
        public final Object invokeSuspend(@NotNull Object obj) {
            Object b = cdz.m5528b();
            switch (this.label) {
                case 0:
                    bww.m8764a(obj);
                    cod codVar = this.f20951p$;
                    Iterator a = this.$this_ifEmpty.mo3707a();
                    if (a.hasNext()) {
                        this.L$0 = codVar;
                        this.L$1 = a;
                        this.label = 1;
                        if (codVar.mo4456a(a, (Continuation<? super Unit>) this) == b) {
                            return b;
                        }
                    } else {
                        this.L$0 = codVar;
                        this.L$1 = a;
                        this.label = 2;
                        if (codVar.m4455a((Sequence) this.$defaultValue.invoke(), (Continuation<? super Unit>) this) == b) {
                            return b;
                        }
                    }
                    break;
                case 1:
                case 2:
                    Iterator it = (Iterator) this.L$1;
                    cod codVar2 = (cod) this.L$0;
                    bww.m8764a(obj);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.f20418a;
        }
    }

    @bwy(m8750a = "1.3")
    @NotNull
    /* renamed from: a */
    public static final <T> Sequence<T> m4443a(@NotNull Sequence<? extends T> cobVar, @NotNull chc<? extends Sequence<? extends T>> chcVar) {
        cji.m5162f(cobVar, "$this$ifEmpty");
        cji.m5162f(chcVar, "defaultValue");
        return coe.m4453a(new C5031h(cobVar, chcVar, null));
    }

    /* compiled from: Sequences.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0010\n\u0000\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, m8860e = {"<anonymous>", "", TessBaseAPI.f9204e, "it", "Lkotlin/sequences/Sequence;", "invoke"})
    /* renamed from: z1.coh$c */
    /* loaded from: classes3.dex */
    static final class C5026c extends Lambda implements chd<Sequence<? extends T>, Iterator<? extends T>> {
        public static final C5026c INSTANCE = new C5026c();

        C5026c() {
            super(1);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @NotNull
        public final Iterator<T> invoke(@NotNull Sequence<? extends T> cobVar) {
            cji.m5162f(cobVar, "it");
            return cobVar.mo3707a();
        }
    }

    @NotNull
    /* renamed from: a */
    public static final <T> Sequence<T> m4444a(@NotNull Sequence<? extends Sequence<? extends T>> cobVar) {
        cji.m5162f(cobVar, "$this$flatten");
        return m4442a((Sequence) cobVar, (chd) C5026c.INSTANCE);
    }

    /* compiled from: Sequences.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0010\n\u0000\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, m8860e = {"<anonymous>", "", TessBaseAPI.f9204e, "it", "", "invoke"})
    /* renamed from: z1.coh$d */
    /* loaded from: classes3.dex */
    static final class C5027d extends Lambda implements chd<Iterable<? extends T>, Iterator<? extends T>> {
        public static final C5027d INSTANCE = new C5027d();

        C5027d() {
            super(1);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @NotNull
        public final Iterator<T> invoke(@NotNull Iterable<? extends T> iterable) {
            cji.m5162f(iterable, "it");
            return iterable.iterator();
        }
    }

    @cgo(m5270a = "flattenSequenceOfIterable")
    @NotNull
    /* renamed from: b */
    public static final <T> Sequence<T> m4438b(@NotNull Sequence<? extends Iterable<? extends T>> cobVar) {
        cji.m5162f(cobVar, "$this$flatten");
        return m4442a((Sequence) cobVar, (chd) C5027d.INSTANCE);
    }

    /* renamed from: a */
    private static final <T, R> Sequence<R> m4442a(@NotNull Sequence<? extends T> cobVar, chd<? super T, ? extends Iterator<? extends R>> chdVar) {
        if (cobVar instanceof coo) {
            return ((coo) cobVar).m4263a(chdVar);
        }
        return new cnx(cobVar, C5028e.INSTANCE, chdVar);
    }

    @NotNull
    /* renamed from: c */
    public static final <T, R> Tuples<List<T>, List<R>> m4437c(@NotNull Sequence<? extends Tuples<? extends T, ? extends R>> cobVar) {
        cji.m5162f(cobVar, "$this$unzip");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<? extends Tuples<? extends T, ? extends R>> a = cobVar.mo3707a();
        while (a.hasNext()) {
            Tuples bwoVar = (Tuples) a.next();
            arrayList.add(bwoVar.getFirst());
            arrayList2.add(bwoVar.getSecond());
        }
        return bxh.m8730a(arrayList, arrayList2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    /* renamed from: d */
    public static final <T> Sequence<T> m4436d(@NotNull Sequence<? extends T> cobVar) {
        cji.m5162f(cobVar, "$this$constrainOnce");
        return cobVar instanceof SequencesJVM ? cobVar : new SequencesJVM(cobVar);
    }

    /* compiled from: Sequences.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u0002H\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, m8860e = {"<anonymous>", TessBaseAPI.f9204e, "", "it", "invoke", "(Ljava/lang/Object;)Ljava/lang/Object;"})
    /* renamed from: z1.coh$f */
    /* loaded from: classes3.dex */
    static final class C5029f extends Lambda implements chd<T, T> {
        final /* synthetic */ chc $nextFunction;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C5029f(chc chcVar) {
            super(1);
            this.$nextFunction = chcVar;
        }

        /* JADX WARN: Type inference failed for: r2v2, types: [T, java.lang.Object] */
        @Override // p110z1.chd
        @dbs
        public final T invoke(@NotNull T t) {
            cji.m5162f(t, "it");
            return this.$nextFunction.invoke();
        }
    }

    @NotNull
    /* renamed from: a */
    public static final <T> Sequence<T> m4446a(@NotNull chc<? extends T> chcVar) {
        cji.m5162f(chcVar, "nextFunction");
        return coe.m4436d(new cny(chcVar, new C5029f(chcVar)));
    }

    @cez
    @NotNull
    /* renamed from: a */
    public static final <T> Sequence<T> m4448a(@dbs T t, @NotNull chd<? super T, ? extends T> chdVar) {
        cji.m5162f(chdVar, "nextFunction");
        if (t == null) {
            return cnv.f20899a;
        }
        return new cny(new C5030g(t), chdVar);
    }

    /* compiled from: Sequences.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, m8860e = {"<anonymous>", TessBaseAPI.f9204e, "", "invoke", "()Ljava/lang/Object;"})
    /* renamed from: z1.coh$g */
    /* loaded from: classes3.dex */
    static final class C5030g extends Lambda implements chc<T> {
        final /* synthetic */ Object $seed;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C5030g(Object obj) {
            super(0);
            this.$seed = obj;
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [T, java.lang.Object] */
        @Override // p110z1.chc
        @dbs
        public final T invoke() {
            return this.$seed;
        }
    }

    @NotNull
    /* renamed from: a */
    public static final <T> Sequence<T> m4445a(@NotNull chc<? extends T> chcVar, @NotNull chd<? super T, ? extends T> chdVar) {
        cji.m5162f(chcVar, "seedFunction");
        cji.m5162f(chdVar, "nextFunction");
        return new cny(chcVar, chdVar);
    }
}
