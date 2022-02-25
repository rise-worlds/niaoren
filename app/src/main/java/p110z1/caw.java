package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.List;

/* compiled from: ReversedViews.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0012\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\u0016\u0010\n\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00020\u0007H\u0096\u0002¢\u0006\u0002\u0010\fR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\r"}, m8860e = {"Lkotlin/collections/ReversedListReadOnly;", TessBaseAPI.f9204e, "Lkotlin/collections/AbstractList;", "delegate", "", "(Ljava/util/List;)V", "size", "", "getSize", "()I", "get", "index", "(I)Ljava/lang/Object;", "kotlin-stdlib"})
/* renamed from: z1.caw */
/* loaded from: classes3.dex */
class caw<T> extends AbstractList<T> {

    /* renamed from: b */
    private final List<T> f20500b;

    /* JADX WARN: Multi-variable type inference failed */
    public caw(@NotNull List<? extends T> list) {
        cji.m5162f(list, "delegate");
        this.f20500b = list;
    }

    @Override // p110z1.AbstractList, p110z1.AbstractCollection
    /* renamed from: a */
    public int mo4172a() {
        return this.f20500b.size();
    }

    @Override // p110z1.AbstractList, java.util.List
    public T get(int i) {
        int c;
        List<T> list = this.f20500b;
        c = ReversedViews.m6731c(this, i);
        return list.get(c);
    }
}
