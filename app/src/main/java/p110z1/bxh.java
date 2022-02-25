package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.List;

/* compiled from: Tuples.kt */
@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\u001a2\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0002H\u00022\u0006\u0010\u0004\u001a\u0002H\u0003H\u0086\u0004¢\u0006\u0002\u0010\u0005\u001a\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b*\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\b0\u0001\u001a(\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b*\u0014\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\b0\t¨\u0006\n"}, m8860e = {"to", "Lkotlin/Pair;", "A", "B", "that", "(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;", "toList", "", TessBaseAPI.f9204e, "Lkotlin/Triple;", "kotlin-stdlib"})
@cgo(m5270a = "TuplesKt")
/* renamed from: z1.bxh */
/* loaded from: classes3.dex */
public final class bxh {
    @NotNull
    /* renamed from: a */
    public static final <A, B> Tuples<A, B> m8730a(A a, B b) {
        return new Tuples<>(a, b);
    }

    @NotNull
    /* renamed from: a */
    public static final <T> List<T> m8729a(@NotNull Tuples<? extends T, ? extends T> bwoVar) {
        cji.m5162f(bwoVar, "$this$toList");
        return bzk.m6795b(bwoVar.getFirst(), bwoVar.getSecond());
    }

    @NotNull
    /* renamed from: a */
    public static final <T> List<T> m8728a(@NotNull bxg<? extends T, ? extends T, ? extends T> bxgVar) {
        cji.m5162f(bxgVar, "$this$toList");
        return bzk.m6795b(bxgVar.getFirst(), bxgVar.getSecond(), bxgVar.getThird());
    }
}
