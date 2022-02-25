package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.List;

@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0018\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001\u001a#\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007¢\u0006\u0002\b\u0004\u001a\u001d\u0010\u0005\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0002\b\b\u001a\u001d\u0010\t\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0002\b\n¨\u0006\u000b"}, m8860e = {"asReversed", "", TessBaseAPI.f9204e, "", "asReversedMutable", "reverseElementIndex", "", "index", "reverseElementIndex$CollectionsKt__ReversedViewsKt", "reversePositionIndex", "reversePositionIndex$CollectionsKt__ReversedViewsKt", "kotlin-stdlib"}, m8859f = "kotlin/collections/CollectionsKt", m8857h = 1)
/* renamed from: z1.bzs */
/* loaded from: classes3.dex */
class ReversedViews extends MutableCollections {
    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public static final int m6731c(@NotNull List<?> list, int i) {
        int a = bzk.m6810a((List) list);
        if (i >= 0 && a >= i) {
            return bzk.m6810a((List) list) - i;
        }
        throw new IndexOutOfBoundsException("Element index " + i + " must be in range [" + new cme(0, bzk.m6810a((List) list)) + "].");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public static final int m6729d(@NotNull List<?> list, int i) {
        int size = list.size();
        if (i >= 0 && size >= i) {
            return list.size() - i;
        }
        throw new IndexOutOfBoundsException("Position index " + i + " must be in range [" + new cme(0, list.size()) + "].");
    }

    @NotNull
    /* renamed from: d */
    public static final <T> List<T> m6730d(@NotNull List<? extends T> list) {
        cji.m5162f(list, "$this$asReversed");
        return new caw(list);
    }

    @cgo(m5270a = "asReversedMutable")
    @NotNull
    /* renamed from: e */
    public static final <T> List<T> m6728e(@NotNull List<T> list) {
        cji.m5162f(list, "$this$asReversed");
        return new cav(list);
    }
}
