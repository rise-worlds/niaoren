package p110z1;

import com.github.kevinsawicki.http.HttpRequest;
import com.googlecode.tesseract.android.TessBaseAPI;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;

@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u00002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\u001a/\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0000¢\u0006\u0002\u0010\u0006\u001a\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H\u0001\u001a!\u0010\n\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0001H\u0001¢\u0006\u0004\b\u000b\u0010\f\u001a,\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u0001H\u0086\b¢\u0006\u0002\u0010\u000e\u001a\u0015\u0010\u000f\u001a\u00020\u0010*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0087\b\u001a&\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\b\u0012\u0004\u0012\u0002H\u00020\u0015H\u0086\b¢\u0006\u0002\u0010\u0016¨\u0006\u0017"}, m8860e = {"arrayOfNulls", "", TessBaseAPI.f9204e, "reference", "size", "", "([Ljava/lang/Object;I)[Ljava/lang/Object;", "copyOfRangeToIndexCheck", "", "toIndex", "contentDeepHashCodeImpl", "contentDeepHashCode", "([Ljava/lang/Object;)I", "orEmpty", "([Ljava/lang/Object;)[Ljava/lang/Object;", "toString", "", "", HttpRequest.PARAM_CHARSET, "Ljava/nio/charset/Charset;", "toTypedArray", "", "(Ljava/util/Collection;)[Ljava/lang/Object;", "kotlin-stdlib"}, m8859f = "kotlin/collections/ArraysKt", m8857h = 1)
/* renamed from: z1.bzc */
/* loaded from: classes3.dex */
class ArraysJVM {
    @NotNull
    /* renamed from: a */
    public static final /* synthetic */ <T> T[] m8284a(@dbs T[] tArr) {
        if (tArr != null) {
            return tArr;
        }
        cji.m5192a(0, "T?");
        return (T[]) new Object[0];
    }

    @cey
    /* renamed from: a */
    private static final String m8285a(@NotNull byte[] bArr, Charset charset) {
        return new String(bArr, charset);
    }

    @NotNull
    /* renamed from: a */
    public static final /* synthetic */ <T> T[] m8286a(@NotNull Collection<? extends T> collection) {
        cji.m5162f(collection, "$this$toTypedArray");
        cji.m5192a(0, "T?");
        T[] tArr = (T[]) collection.toArray(new Object[0]);
        if (tArr != null) {
            return tArr;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    @NotNull
    /* renamed from: a */
    public static final <T> T[] m8283a(@NotNull T[] tArr, int i) {
        cji.m5162f(tArr, "reference");
        Object newInstance = Array.newInstance(tArr.getClass().getComponentType(), i);
        if (newInstance != null) {
            return (T[]) ((Object[]) newInstance);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    @bwy(m8750a = "1.3")
    /* renamed from: a */
    public static final void m8287a(int i, int i2) {
        if (i > i2) {
            throw new IndexOutOfBoundsException("toIndex (" + i + ") is greater than size (" + i2 + ").");
        }
    }

    @bwy(m8750a = "1.3")
    @bwt
    @cgo(m5270a = "contentDeepHashCode")
    /* renamed from: b */
    public static final <T> int m8282b(@NotNull T[] tArr) {
        cji.m5162f(tArr, "$this$contentDeepHashCodeImpl");
        return Arrays.deepHashCode(tArr);
    }
}
