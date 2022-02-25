package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.List;

/* compiled from: ReversedViews.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\n\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\u001d\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000eJ\b\u0010\u000f\u001a\u00020\u000bH\u0016J\u0016\u0010\u0010\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\u0007H\u0096\u0002¢\u0006\u0002\u0010\u0011J\u0015\u0010\u0012\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\u0007H\u0016¢\u0006\u0002\u0010\u0011J\u001e\u0010\u0013\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0014R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0015"}, m8860e = {"Lkotlin/collections/ReversedList;", TessBaseAPI.f9204e, "Lkotlin/collections/AbstractMutableList;", "delegate", "", "(Ljava/util/List;)V", "size", "", "getSize", "()I", "add", "", "index", "element", "(ILjava/lang/Object;)V", "clear", "get", "(I)Ljava/lang/Object;", "removeAt", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "kotlin-stdlib"})
/* renamed from: z1.cav */
/* loaded from: classes3.dex */
final class cav<T> extends AbstractMutableList<T> {

    /* renamed from: a */
    private final List<T> f20499a;

    public cav(@NotNull List<T> list) {
        cji.m5162f(list, "delegate");
        this.f20499a = list;
    }

    @Override // p110z1.AbstractMutableList
    /* renamed from: a */
    public int mo6371a() {
        return this.f20499a.size();
    }

    @Override // java.util.AbstractList, java.util.List
    public T get(int i) {
        int c;
        List<T> list = this.f20499a;
        c = ReversedViews.m6731c(this, i);
        return list.get(c);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        this.f20499a.clear();
    }

    @Override // p110z1.AbstractMutableList
    /* renamed from: a */
    public T mo6370a(int i) {
        int c;
        List<T> list = this.f20499a;
        c = ReversedViews.m6731c(this, i);
        return list.remove(c);
    }

    @Override // p110z1.AbstractMutableList, java.util.AbstractList, java.util.List
    public T set(int i, T t) {
        int c;
        List<T> list = this.f20499a;
        c = ReversedViews.m6731c(this, i);
        return list.set(c, t);
    }

    @Override // p110z1.AbstractMutableList, java.util.AbstractList, java.util.List
    public void add(int i, T t) {
        int d;
        List<T> list = this.f20499a;
        d = ReversedViews.m6729d(this, i);
        list.add(d, t);
    }
}
