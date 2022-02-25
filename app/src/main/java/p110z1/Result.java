package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import com.stripe.android.CustomerSession;
import java.io.Serializable;
import org.apache.tools.ant.types.selectors.SizeSelector;

@bwy(m8750a = "1.3")
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0087@\u0018\u0000 \u001e*\u0006\b\u0000\u0010\u0001 \u00012\u00060\u0002j\u0002`\u0003:\u0002\u001e\u001fB\u0016\b\u0001\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\u0010\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÖ\u0003J\u000f\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u0014\u0010\u0015J\u0012\u0010\u0016\u001a\u0004\u0018\u00018\u0000H\u0087\b¢\u0006\u0004\b\u0017\u0010\u0007J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\u000f\u0010\u001a\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001c\u0010\u001dR\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u000e\u0010\u000fø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, m8860e = {"Lkotlin/Result;", TessBaseAPI.f9204e, "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", SizeSelector.SIZE_KEY, "", "constructor-impl", "(Ljava/lang/Object;)Ljava/lang/Object;", "isFailure", "", "isFailure-impl", "(Ljava/lang/Object;)Z", "isSuccess", "isSuccess-impl", "value$annotations", "()V", "equals", "other", "exceptionOrNull", "", "exceptionOrNull-impl", "(Ljava/lang/Object;)Ljava/lang/Throwable;", "getOrNull", "getOrNull-impl", "hashCode", "", "toString", "", "toString-impl", "(Ljava/lang/Object;)Ljava/lang/String;", "Companion", "Failure", "kotlin-stdlib"})
/* renamed from: z1.bwv */
/* loaded from: classes3.dex */
public final class Result<T> implements Serializable {
    public static final C4802a Companion = new C4802a(null);
    @dbs
    private final Object value;

    @NotNull
    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ Result m25722boximpl(@dbs Object obj) {
        return new Result(obj);
    }

    @bwt
    @NotNull
    /* renamed from: constructor-impl  reason: not valid java name */
    public static Object m25723constructorimpl(@dbs Object obj) {
        return obj;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m25724equalsimpl(Object obj, @dbs Object obj2) {
        return (obj2 instanceof Result) && cji.m5184a(obj, ((Result) obj2).m25732unboximpl());
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m25725equalsimpl0(@dbs Object obj, @dbs Object obj2) {
        throw null;
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m25728hashCodeimpl(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    @bwt
    public static /* synthetic */ void value$annotations() {
    }

    public boolean equals(Object obj) {
        return m25724equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m25728hashCodeimpl(this.value);
    }

    @NotNull
    public String toString() {
        return m25731toStringimpl(this.value);
    }

    @dbs
    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ Object m25732unboximpl() {
        return this.value;
    }

    @bwt
    private /* synthetic */ Result(@dbs Object obj) {
        this.value = obj;
    }

    /* renamed from: isSuccess-impl  reason: not valid java name */
    public static final boolean m25730isSuccessimpl(Object obj) {
        return !(obj instanceof C4803b);
    }

    /* renamed from: isFailure-impl  reason: not valid java name */
    public static final boolean m25729isFailureimpl(Object obj) {
        return obj instanceof C4803b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @cey
    /* renamed from: getOrNull-impl  reason: not valid java name */
    private static final T m25727getOrNullimpl(Object obj) {
        if (m25729isFailureimpl(obj)) {
            return null;
        }
        return obj;
    }

    @dbs
    /* renamed from: exceptionOrNull-impl  reason: not valid java name */
    public static final Throwable m25726exceptionOrNullimpl(Object obj) {
        if (obj instanceof C4803b) {
            return ((C4803b) obj).exception;
        }
        return null;
    }

    @NotNull
    /* renamed from: toString-impl  reason: not valid java name */
    public static String m25731toStringimpl(Object obj) {
        if (obj instanceof C4803b) {
            return obj.toString();
        }
        return "Success(" + obj + ')';
    }

    /* compiled from: Result.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\bJ%\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\n\u001a\u0002H\u0005H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, m8860e = {"Lkotlin/Result$Companion;", "", "()V", "failure", "Lkotlin/Result;", TessBaseAPI.f9204e, CustomerSession.f11798b, "", "(Ljava/lang/Throwable;)Ljava/lang/Object;", "success", SizeSelector.SIZE_KEY, "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-stdlib"})
    /* renamed from: z1.bwv$a */
    /* loaded from: classes3.dex */
    public static final class C4802a {
        private C4802a() {
        }

        public /* synthetic */ C4802a(DefaultConstructorMarker civVar) {
            this();
        }

        @cey
        /* renamed from: a */
        private final <T> Object m8766a(T t) {
            return Result.m25723constructorimpl(t);
        }

        @cey
        /* renamed from: a */
        private final <T> Object m8765a(Throwable th) {
            return Result.m25723constructorimpl(bww.m8760a(th));
        }
    }

    /* compiled from: Result.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00060\u0001j\u0002`\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0096\u0002J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m8860e = {"Lkotlin/Result$Failure;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", CustomerSession.f11798b, "", "(Ljava/lang/Throwable;)V", "equals", "", "other", "", "hashCode", "", "toString", "", "kotlin-stdlib"})
    /* renamed from: z1.bwv$b */
    /* loaded from: classes3.dex */
    public static final class C4803b implements Serializable {
        @JvmPlatformAnnotations
        @NotNull
        public final Throwable exception;

        public C4803b(@NotNull Throwable th) {
            cji.m5162f(th, CustomerSession.f11798b);
            this.exception = th;
        }

        public boolean equals(@dbs Object obj) {
            return (obj instanceof C4803b) && cji.m5184a(this.exception, ((C4803b) obj).exception);
        }

        public int hashCode() {
            return this.exception.hashCode();
        }

        @NotNull
        public String toString() {
            return "Failure(" + this.exception + ')';
        }
    }
}
