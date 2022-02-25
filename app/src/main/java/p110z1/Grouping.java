package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Iterator;

@bwy(m8750a = "1.1")
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010(\n\u0000\bg\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0006\b\u0001\u0010\u0002 \u00012\u00020\u0003J\u0015\u0010\u0004\u001a\u00028\u00012\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH&¨\u0006\t"}, m8860e = {"Lkotlin/collections/Grouping;", TessBaseAPI.f9204e, "K", "", "keyOf", "element", "(Ljava/lang/Object;)Ljava/lang/Object;", "sourceIterator", "", "kotlin-stdlib"})
/* renamed from: z1.cab */
/* loaded from: classes3.dex */
public interface Grouping<T, K> {
    /* renamed from: a */
    K mo3704a(T t);

    @NotNull
    /* renamed from: a */
    Iterator<T> mo3706a();
}
