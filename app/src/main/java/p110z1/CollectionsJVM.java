package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u00002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0081\b\u001a\u0011\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0081\b\u001a\"\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tH\u0081\b¢\u0006\u0002\u0010\n\u001a4\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0006\"\u0004\b\u0000\u0010\u000b2\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0006H\u0081\b¢\u0006\u0002\u0010\r\u001a\u001f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u000f\"\u0004\b\u0000\u0010\u000b2\u0006\u0010\u0010\u001a\u0002H\u000b¢\u0006\u0002\u0010\u0011\u001a1\u0010\u0012\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00070\u0006\"\u0004\b\u0000\u0010\u000b*\n\u0012\u0006\b\u0001\u0012\u0002H\u000b0\u00062\u0006\u0010\u0013\u001a\u00020\u0014H\u0000¢\u0006\u0002\u0010\u0015\u001a\u001f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u000f\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u0017H\u0087\b¨\u0006\u0018"}, m8860e = {"checkCountOverflow", "", "count", "checkIndexOverflow", "index", "copyToArrayImpl", "", "", "collection", "", "(Ljava/util/Collection;)[Ljava/lang/Object;", TessBaseAPI.f9204e, "array", "(Ljava/util/Collection;[Ljava/lang/Object;)[Ljava/lang/Object;", "listOf", "", "element", "(Ljava/lang/Object;)Ljava/util/List;", "copyToArrayOfAny", "isVarargs", "", "([Ljava/lang/Object;Z)[Ljava/lang/Object;", "toList", "Ljava/util/Enumeration;", "kotlin-stdlib"}, m8859f = "kotlin/collections/CollectionsKt", m8857h = 1)
/* renamed from: z1.bzl */
/* loaded from: classes3.dex */
public class CollectionsJVM {
    @NotNull
    /* renamed from: a */
    public static final <T> List<T> m6822a(T t) {
        List<T> singletonList = Collections.singletonList(t);
        cji.m5175b(singletonList, "java.util.Collections.singletonList(element)");
        return singletonList;
    }

    @cey
    /* renamed from: a */
    private static final <T> List<T> m6819a(@NotNull Enumeration<T> enumeration) {
        ArrayList list = Collections.list(enumeration);
        cji.m5175b(list, "java.util.Collections.list(this)");
        return list;
    }

    @cey
    /* renamed from: a */
    private static final Object[] m6821a(Collection<?> collection) {
        return CollectionToArray.m5227a(collection);
    }

    @cey
    /* renamed from: a */
    private static final <T> T[] m6820a(Collection<?> collection, T[] tArr) {
        if (tArr != null) {
            T[] tArr2 = (T[]) CollectionToArray.m5225a(collection, tArr);
            if (tArr2 != null) {
                return tArr2;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
    }

    @NotNull
    /* renamed from: a */
    public static final <T> Object[] m6818a(@NotNull T[] tArr, boolean z) {
        cji.m5162f(tArr, "$this$copyToArrayOfAny");
        if (z && cji.m5184a(tArr.getClass(), Object[].class)) {
            return tArr;
        }
        Object[] copyOf = Arrays.copyOf(tArr, tArr.length, Object[].class);
        cji.m5175b(copyOf, "java.util.Arrays.copyOf(… Array<Any?>::class.java)");
        return copyOf;
    }

    @bwy(m8750a = "1.3")
    @bwt
    @cey
    /* renamed from: a */
    private static final int m6823a(int i) {
        if (i < 0) {
            if (cfe.m5471a(1, 3, 0)) {
                bzk.m6800b();
            } else {
                throw new ArithmeticException("Index overflow has happened.");
            }
        }
        return i;
    }

    @bwy(m8750a = "1.3")
    @bwt
    @cey
    /* renamed from: b */
    private static final int m6817b(int i) {
        if (i < 0) {
            if (cfe.m5471a(1, 3, 0)) {
                bzk.m6794c();
            } else {
                throw new ArithmeticException("Count overflow has happened.");
            }
        }
        return i;
    }
}
