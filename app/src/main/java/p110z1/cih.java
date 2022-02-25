package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Iterator;

/* compiled from: ArrayIterator.kt */
@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0012\n\u0000\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\u001a%\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m8860e = {"iterator", "", TessBaseAPI.f9204e, "array", "", "([Ljava/lang/Object;)Ljava/util/Iterator;", "kotlin-stdlib"})
/* renamed from: z1.cih */
/* loaded from: classes3.dex */
public final class cih {
    @NotNull
    /* renamed from: a */
    public static final <T> Iterator<T> m5258a(@NotNull T[] tArr) {
        cji.m5162f(tArr, "array");
        return new ArrayIterator(tArr);
    }
}
