package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import org.apache.tools.ant.taskdefs.condition.ParserSupports;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u0002H\u00050\u0004\"\b\b\u0000\u0010\u0005*\u00020\u0001J}\u0010\u0006\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001a\u0002H\u00052Q\b\u0004\u0010\b\u001aK\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u0011H\u0005¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u0011H\u0005¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\tH\u0086\b¢\u0006\u0002\u0010\u0011J}\u0010\u0012\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001a\u0002H\u00052Q\b\u0004\u0010\b\u001aK\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u0011H\u0005¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u0011H\u0005¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00130\tH\u0086\b¢\u0006\u0002\u0010\u0011¨\u0006\u0014"}, m8860e = {"Lkotlin/properties/Delegates;", "", "()V", "notNull", "Lkotlin/properties/ReadWriteProperty;", TessBaseAPI.f9204e, "observable", "initialValue", "onChange", "Lkotlin/Function3;", "Lkotlin/reflect/KProperty;", "Lkotlin/ParameterName;", "name", ParserSupports.PROPERTY, "oldValue", "newValue", "", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Lkotlin/properties/ReadWriteProperty;", "vetoable", "", "kotlin-stdlib"})
/* renamed from: z1.clg */
/* loaded from: classes3.dex */
public final class Delegates {

    /* renamed from: a */
    public static final Delegates f20798a = new Delegates();

    private Delegates() {
    }

    @NotNull
    /* renamed from: a */
    public final <T> clk<Object, T> m4917a() {
        return new clh();
    }

    /* compiled from: Delegates.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J)\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\b¨\u0006\t"}, m8860e = {"kotlin/properties/Delegates$observable$1", "Lkotlin/properties/ObservableProperty;", "afterChange", "", ParserSupports.PROPERTY, "Lkotlin/reflect/KProperty;", "oldValue", "newValue", "(Lkotlin/reflect/KProperty;Ljava/lang/Object;Ljava/lang/Object;)V", "kotlin-stdlib"})
    /* renamed from: z1.clg$a */
    /* loaded from: classes3.dex */
    public static final class C4977a extends ObservableProperty<T> {

        /* renamed from: a */
        final /* synthetic */ chs f20799a;

        /* renamed from: b */
        final /* synthetic */ Object f20800b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C4977a(chs chsVar, Object obj, Object obj2) {
            super(obj2);
            this.f20799a = chsVar;
            this.f20800b = obj;
        }

        @Override // p110z1.ObservableProperty
        /* renamed from: a */
        protected void mo4914a(@NotNull cnf<?> cnfVar, T t, T t2) {
            cji.m5162f(cnfVar, ParserSupports.PROPERTY);
            this.f20799a.invoke(cnfVar, t, t2);
        }
    }

    @NotNull
    /* renamed from: a */
    public final <T> clk<Object, T> m4916a(T t, @NotNull chs<? super cnf<?>, ? super T, ? super T, Unit> chsVar) {
        cji.m5162f(chsVar, "onChange");
        return new C4977a(chsVar, t, t);
    }

    /* compiled from: Delegates.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J)\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\b¨\u0006\t"}, m8860e = {"kotlin/properties/Delegates$vetoable$1", "Lkotlin/properties/ObservableProperty;", "beforeChange", "", ParserSupports.PROPERTY, "Lkotlin/reflect/KProperty;", "oldValue", "newValue", "(Lkotlin/reflect/KProperty;Ljava/lang/Object;Ljava/lang/Object;)Z", "kotlin-stdlib"})
    /* renamed from: z1.clg$b */
    /* loaded from: classes3.dex */
    public static final class C4978b extends ObservableProperty<T> {

        /* renamed from: a */
        final /* synthetic */ chs f20801a;

        /* renamed from: b */
        final /* synthetic */ Object f20802b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C4978b(chs chsVar, Object obj, Object obj2) {
            super(obj2);
            this.f20801a = chsVar;
            this.f20802b = obj;
        }

        @Override // p110z1.ObservableProperty
        /* renamed from: b */
        protected boolean mo4913b(@NotNull cnf<?> cnfVar, T t, T t2) {
            cji.m5162f(cnfVar, ParserSupports.PROPERTY);
            return ((Boolean) this.f20801a.invoke(cnfVar, t, t2)).booleanValue();
        }
    }

    @NotNull
    /* renamed from: b */
    public final <T> clk<Object, T> m4915b(T t, @NotNull chs<? super cnf<?>, ? super T, ? super T, Boolean> chsVar) {
        cji.m5162f(chsVar, "onChange");
        return new C4978b(chsVar, t, t);
    }
}
