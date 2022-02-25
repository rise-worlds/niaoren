package p110z1;

import java.lang.reflect.Field;
import java.util.ArrayList;
import org.apache.commons.p105io.IOUtils;

/* compiled from: DebugMetadata.kt */
@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0002\u001a\u000e\u0010\u0006\u001a\u0004\u0018\u00010\u0007*\u00020\bH\u0002\u001a\f\u0010\t\u001a\u00020\u0001*\u00020\bH\u0002\u001a\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b*\u00020\bH\u0001¢\u0006\u0002\u0010\r\u001a\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\bH\u0001¢\u0006\u0002\b\u0010\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m8860e = {"COROUTINES_DEBUG_METADATA_VERSION", "", "checkDebugMetadataVersion", "", "expected", "actual", "getDebugMetadataAnnotation", "Lkotlin/coroutines/jvm/internal/DebugMetadata;", "Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;", "getLabel", "getSpilledVariableFieldMapping", "", "", "(Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;)[Ljava/lang/String;", "getStackTraceElementImpl", "Ljava/lang/StackTraceElement;", "getStackTraceElement", "kotlin-stdlib"})
/* renamed from: z1.cei */
/* loaded from: classes3.dex */
public final class cei {

    /* renamed from: a */
    private static final int f20625a = 1;

    @bwy(m8750a = "1.3")
    @dbs
    @cgo(m5270a = "getStackTraceElement")
    /* renamed from: a */
    public static final StackTraceElement m5509a(@NotNull ContinuationImpl cecVar) {
        String str;
        cji.m5162f(cecVar, "$this$getStackTraceElementImpl");
        DebugMetadata c = m5507c(cecVar);
        if (c == null) {
            return null;
        }
        m5510a(1, c.m5518a());
        int d = m5506d(cecVar);
        int i = d < 0 ? -1 : c.m5516c()[d];
        String a = cek.f20627b.m5502a(cecVar);
        if (a == null) {
            str = c.m5511h();
        } else {
            str = a + IOUtils.DIR_SEPARATOR_UNIX + c.m5511h();
        }
        return new StackTraceElement(str, c.m5512g(), c.m5517b(), i);
    }

    /* renamed from: c */
    private static final DebugMetadata m5507c(@NotNull ContinuationImpl cecVar) {
        return (DebugMetadata) cecVar.getClass().getAnnotation(DebugMetadata.class);
    }

    /* renamed from: d */
    private static final int m5506d(@NotNull ContinuationImpl cecVar) {
        try {
            Field declaredField = cecVar.getClass().getDeclaredField("label");
            cji.m5175b(declaredField, "field");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(cecVar);
            if (!(obj instanceof Integer)) {
                obj = null;
            }
            Integer num = (Integer) obj;
            return (num != null ? num.intValue() : 0) - 1;
        } catch (Exception unused) {
            return -1;
        }
    }

    /* renamed from: a */
    private static final void m5510a(int i, int i2) {
        if (i2 > i) {
            throw new IllegalStateException(("Debug metadata version mismatch. Expected: " + i + ", got " + i2 + ". Please update the Kotlin standard library.").toString());
        }
    }

    @bwy(m8750a = "1.3")
    @dbs
    @cgo(m5270a = "getSpilledVariableFieldMapping")
    /* renamed from: b */
    public static final String[] m5508b(@NotNull ContinuationImpl cecVar) {
        cji.m5162f(cecVar, "$this$getSpilledVariableFieldMapping");
        DebugMetadata c = m5507c(cecVar);
        if (c == null) {
            return null;
        }
        m5510a(1, c.m5518a());
        ArrayList arrayList = new ArrayList();
        int d = m5506d(cecVar);
        int[] f = c.m5513f();
        int length = f.length;
        for (int i = 0; i < length; i++) {
            if (f[i] == d) {
                arrayList.add(c.m5514e()[i]);
                arrayList.add(c.m5515d()[i]);
            }
        }
        Object[] array = arrayList.toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }
}
