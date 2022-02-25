package p110z1;

import java.util.List;
import java.util.RandomAccess;
import org.apache.tools.ant.taskdefs.optional.j2ee.HotDeploymentTool;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u0004B\u0013\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\u0016\u0010\u000e\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00020\tH\u0096\u0002¢\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\tR\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0014"}, m8860e = {"Lkotlin/collections/MovingSubList;", "E", "Lkotlin/collections/AbstractList;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", HotDeploymentTool.ACTION_LIST, "", "(Ljava/util/List;)V", "_size", "", "fromIndex", "size", "getSize", "()I", "get", "index", "(I)Ljava/lang/Object;", "move", "", "toIndex", "kotlin-stdlib"})
/* renamed from: z1.cas */
/* loaded from: classes3.dex */
public final class SlidingWindow<E> extends AbstractList<E> implements RandomAccess {

    /* renamed from: b */
    private int f20494b;

    /* renamed from: c */
    private int f20495c;

    /* renamed from: d */
    private final List<E> f20496d;

    /* JADX WARN: Multi-variable type inference failed */
    public SlidingWindow(@NotNull List<? extends E> list) {
        cji.m5162f(list, HotDeploymentTool.ACTION_LIST);
        this.f20496d = list;
    }

    /* renamed from: a */
    public final void m6378a(int i, int i2) {
        AbstractList.f20424a.m8321a(i, i2, this.f20496d.size());
        this.f20494b = i;
        this.f20495c = i2 - i;
    }

    @Override // p110z1.AbstractList, java.util.List
    public E get(int i) {
        AbstractList.f20424a.m8322a(i, this.f20495c);
        return this.f20496d.get(this.f20494b + i);
    }

    @Override // p110z1.AbstractList, p110z1.AbstractCollection
    /* renamed from: a */
    public int mo4172a() {
        return this.f20495c;
    }
}
