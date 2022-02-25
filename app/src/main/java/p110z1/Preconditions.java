package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import com.stripe.android.PaymentResultListener;
import org.apache.tools.ant.types.selectors.SizeSelector;

@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u0001\n\u0002\b\u0004\u001a\u001c\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\b\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\b\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001a/\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u00062\b\u0010\u0002\u001a\u0004\u0018\u0001H\bH\u0087\b\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001¢\u0006\u0002\u0010\t\u001a=\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u00062\b\u0010\u0002\u001a\u0004\u0018\u0001H\b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\b\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001¢\u0006\u0002\u0010\n\u001a\u0011\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006H\u0087\b\u001a\u001c\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\b\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001a*\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\b\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001a/\u0010\u000f\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u00062\b\u0010\u0002\u001a\u0004\u0018\u0001H\bH\u0087\b\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001¢\u0006\u0002\u0010\t\u001a=\u0010\u000f\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u00062\b\u0010\u0002\u001a\u0004\u0018\u0001H\b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\b\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001¢\u0006\u0002\u0010\n¨\u0006\u0010"}, m8860e = {"check", "", SizeSelector.SIZE_KEY, "", "lazyMessage", "Lkotlin/Function0;", "", "checkNotNull", TessBaseAPI.f9204e, "(Ljava/lang/Object;)Ljava/lang/Object;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", PaymentResultListener.f11903c, "", "message", "require", "requireNotNull", "kotlin-stdlib"}, m8859f = "kotlin/PreconditionsKt", m8857h = 1)
/* renamed from: z1.bws */
/* loaded from: classes3.dex */
class Preconditions extends AssertionsJVM {
    @cey
    /* renamed from: a */
    private static final void m8775a(boolean z) {
        if (!z) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    @cey
    /* renamed from: a */
    private static final void m8774a(boolean z, chc<? extends Object> chcVar) {
        if (!z) {
            throw new IllegalArgumentException(chcVar.invoke().toString());
        }
    }

    @cey
    /* renamed from: a */
    private static final <T> T m8777a(T t) {
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }

    @cey
    /* renamed from: a */
    private static final <T> T m8776a(T t, chc<? extends Object> chcVar) {
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException(chcVar.invoke().toString());
    }

    @cey
    /* renamed from: b */
    private static final void m8771b(boolean z) {
        if (!z) {
            throw new IllegalStateException("Check failed.".toString());
        }
    }

    @cey
    /* renamed from: b */
    private static final void m8770b(boolean z, chc<? extends Object> chcVar) {
        if (!z) {
            throw new IllegalStateException(chcVar.invoke().toString());
        }
    }

    @cey
    /* renamed from: b */
    private static final <T> T m8773b(T t) {
        if (t != null) {
            return t;
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    @cey
    /* renamed from: b */
    private static final <T> T m8772b(T t, chc<? extends Object> chcVar) {
        if (t != null) {
            return t;
        }
        throw new IllegalStateException(chcVar.invoke().toString());
    }

    @cey
    /* renamed from: c */
    private static final Void m8769c(Object obj) {
        throw new IllegalStateException(obj.toString());
    }
}
