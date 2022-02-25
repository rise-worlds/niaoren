package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import com.stripe.android.CustomerSession;
import org.apache.tools.ant.types.selectors.SizeSelector;
import p110z1.Result;

/* compiled from: Result.kt */
@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000:\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001\u001a+\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00060\bH\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\t\u001a\u0084\u0001\u0010\n\u001a\u0002H\u0006\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u00052!\u0010\f\u001a\u001d\u0012\u0013\u0012\u0011H\u000b¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u0002H\u00060\r2!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u0002H\u00060\rH\u0087\bø\u0001\u0000\u0082\u0002\u0014\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0000¢\u0006\u0002\u0010\u0012\u001a3\u0010\u0013\u001a\u0002H\u0006\"\u0004\b\u0000\u0010\u0006\"\b\b\u0001\u0010\u000b*\u0002H\u0006*\b\u0012\u0004\u0012\u0002H\u000b0\u00052\u0006\u0010\u0014\u001a\u0002H\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a[\u0010\u0016\u001a\u0002H\u0006\"\u0004\b\u0000\u0010\u0006\"\b\b\u0001\u0010\u000b*\u0002H\u0006*\b\u0012\u0004\u0012\u0002H\u000b0\u00052!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u0002H\u00060\rH\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0002\u0010\u0017\u001a!\u0010\u0018\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u0005H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001a]\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u00052!\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u0011H\u000b¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u0002H\u00060\rH\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0002\u0010\u0017\u001aP\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u00052!\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u0011H\u000b¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u0002H\u00060\rH\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001aW\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0005\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u00052!\u0010\u001d\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\u001e0\rH\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0002\u0010\u0017\u001aW\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0005\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u00052!\u0010\u001d\u001a\u001d\u0012\u0013\u0012\u0011H\u000b¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u001e0\rH\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0002\u0010\u0017\u001aa\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u0006\"\b\b\u0001\u0010\u000b*\u0002H\u0006*\b\u0012\u0004\u0012\u0002H\u000b0\u00052!\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u0002H\u00060\rH\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0002\u0010\u0017\u001aT\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u0006\"\b\b\u0001\u0010\u000b*\u0002H\u0006*\b\u0012\u0004\u0012\u0002H\u000b0\u00052!\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u0002H\u00060\rH\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001a@\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\u0006*\u0002H\u000b2\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\u00060\r¢\u0006\u0002\b!H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001a\u0018\u0010\"\u001a\u00020\u001e*\u0006\u0012\u0002\b\u00030\u0005H\u0001ø\u0001\u0000¢\u0006\u0002\u0010#\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006$"}, m8860e = {"createFailure", "", CustomerSession.f11798b, "", "runCatching", "Lkotlin/Result;", "R", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "fold", TessBaseAPI.f9204e, "onSuccess", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", SizeSelector.SIZE_KEY, "onFailure", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "getOrDefault", "defaultValue", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "getOrElse", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "getOrThrow", "(Ljava/lang/Object;)Ljava/lang/Object;", "map", "transform", "mapCatching", "action", "", "recover", "recoverCatching", "Lkotlin/ExtensionFunctionType;", "throwOnFailure", "(Ljava/lang/Object;)V", "kotlin-stdlib"})
/* renamed from: z1.bww */
/* loaded from: classes3.dex */
public final class bww {
    @bwy(m8750a = "1.3")
    @bwt
    @NotNull
    /* renamed from: a */
    public static final Object m8760a(@NotNull Throwable th) {
        cji.m5162f(th, CustomerSession.f11798b);
        return new Result.C4803b(th);
    }

    @bwy(m8750a = "1.3")
    @bwt
    /* renamed from: a */
    public static final void m8764a(@NotNull Object obj) {
        if (obj instanceof Result.C4803b) {
            throw ((Result.C4803b) obj).exception;
        }
    }

    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: a */
    private static final <R> Object m8759a(chc<? extends R> chcVar) {
        try {
            Result.C4802a aVar = Result.Companion;
            return Result.m25723constructorimpl(chcVar.invoke());
        } catch (Throwable th) {
            Result.C4802a aVar2 = Result.Companion;
            return Result.m25723constructorimpl(m8760a(th));
        }
    }

    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: a */
    private static final <T, R> Object m8762a(T t, chd<? super T, ? extends R> chdVar) {
        try {
            Result.C4802a aVar = Result.Companion;
            return Result.m25723constructorimpl(chdVar.invoke(t));
        } catch (Throwable th) {
            Result.C4802a aVar2 = Result.Companion;
            return Result.m25723constructorimpl(m8760a(th));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: b */
    private static final <T> T m8758b(@NotNull Object obj) {
        m8764a(obj);
        return obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: b */
    private static final <R, T extends R> R m8757b(@NotNull Object obj, chd<? super Throwable, ? extends R> chdVar) {
        Throwable th = Result.m25726exceptionOrNullimpl(obj);
        return th == null ? obj : (R) chdVar.invoke(th);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: a */
    private static final <R, T extends R> R m8763a(@NotNull Object obj, R r) {
        return Result.m25729isFailureimpl(obj) ? r : obj;
    }

    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: a */
    private static final <R, T> R m8761a(@NotNull Object obj, chd<? super T, ? extends R> chdVar, chd<? super Throwable, ? extends R> chdVar2) {
        Throwable th = Result.m25726exceptionOrNullimpl(obj);
        if (th == null) {
            return (R) chdVar.invoke(obj);
        }
        return (R) chdVar2.invoke(th);
    }

    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: c */
    private static final <R, T> Object m8756c(@NotNull Object obj, chd<? super T, ? extends R> chdVar) {
        if (!Result.m25730isSuccessimpl(obj)) {
            return Result.m25723constructorimpl(obj);
        }
        Result.C4802a aVar = Result.Companion;
        return Result.m25723constructorimpl(chdVar.invoke(obj));
    }

    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: d */
    private static final <R, T> Object m8755d(@NotNull Object obj, chd<? super T, ? extends R> chdVar) {
        if (!Result.m25730isSuccessimpl(obj)) {
            return Result.m25723constructorimpl(obj);
        }
        try {
            Result.C4802a aVar = Result.Companion;
            return Result.m25723constructorimpl(chdVar.invoke(obj));
        } catch (Throwable th) {
            Result.C4802a aVar2 = Result.Companion;
            return Result.m25723constructorimpl(m8760a(th));
        }
    }

    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: e */
    private static final <R, T extends R> Object m8754e(@NotNull Object obj, chd<? super Throwable, ? extends R> chdVar) {
        Throwable th = Result.m25726exceptionOrNullimpl(obj);
        if (th == null) {
            return obj;
        }
        Result.C4802a aVar = Result.Companion;
        return Result.m25723constructorimpl(chdVar.invoke(th));
    }

    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: f */
    private static final <R, T extends R> Object m8753f(@NotNull Object obj, chd<? super Throwable, ? extends R> chdVar) {
        Throwable th = Result.m25726exceptionOrNullimpl(obj);
        if (th == null) {
            return obj;
        }
        try {
            Result.C4802a aVar = Result.Companion;
            return Result.m25723constructorimpl(chdVar.invoke(th));
        } catch (Throwable th2) {
            Result.C4802a aVar2 = Result.Companion;
            return Result.m25723constructorimpl(m8760a(th2));
        }
    }

    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: g */
    private static final <T> Object m8752g(@NotNull Object obj, chd<? super Throwable, Unit> chdVar) {
        Throwable th = Result.m25726exceptionOrNullimpl(obj);
        if (th != null) {
            chdVar.invoke(th);
        }
        return obj;
    }

    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: h */
    private static final <T> Object m8751h(@NotNull Object obj, chd<? super T, Unit> chdVar) {
        if (Result.m25730isSuccessimpl(obj)) {
            chdVar.invoke(obj);
        }
        return obj;
    }
}
