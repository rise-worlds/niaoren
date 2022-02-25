package p110z1;

import org.apache.tools.ant.types.selectors.SizeSelector;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0018\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\u0002J\f\u0010\f\u001a\u00020\u0004*\u00020\u0002H\u0014R\u000e\u0010\u0006\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, m8860e = {"Lkotlin/jvm/internal/BooleanSpreadBuilder;", "Lkotlin/jvm/internal/PrimitiveSpreadBuilder;", "", "size", "", "(I)V", "values", "add", "", SizeSelector.SIZE_KEY, "", "toArray", "getSize", "kotlin-stdlib"})
/* renamed from: z1.cim */
/* loaded from: classes3.dex */
public final class PrimitiveSpreadBuilders extends cjy<boolean[]> {

    /* renamed from: a */
    private final boolean[] f20708a;

    public PrimitiveSpreadBuilders(int i) {
        super(i);
        this.f20708a = new boolean[i];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public int mo5085a(@NotNull boolean[] zArr) {
        cji.m5162f(zArr, "$this$getSize");
        return zArr.length;
    }

    /* renamed from: a */
    public final void m5247a(boolean z) {
        boolean[] zArr = this.f20708a;
        int b = m5133b();
        m5132b(b + 1);
        zArr[b] = z;
    }

    @NotNull
    /* renamed from: a */
    public final boolean[] m5248a() {
        return m5134a(this.f20708a, new boolean[m5130c()]);
    }
}
