package p110z1;

import org.apache.tools.ant.types.selectors.SizeSelector;

/* compiled from: PrimitiveSpreadBuilders.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004J\u0006\u0010\n\u001a\u00020\u0002J\f\u0010\u000b\u001a\u00020\u0004*\u00020\u0002H\u0014R\u000e\u0010\u0006\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, m8860e = {"Lkotlin/jvm/internal/IntSpreadBuilder;", "Lkotlin/jvm/internal/PrimitiveSpreadBuilder;", "", "size", "", "(I)V", "values", "add", "", SizeSelector.SIZE_KEY, "toArray", "getSize", "kotlin-stdlib"})
/* renamed from: z1.cjh */
/* loaded from: classes3.dex */
public final class cjh extends cjy<int[]> {

    /* renamed from: a */
    private final int[] f20757a;

    public cjh(int i) {
        super(i);
        this.f20757a = new int[i];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public int mo5085a(@NotNull int[] iArr) {
        cji.m5162f(iArr, "$this$getSize");
        return iArr.length;
    }

    /* renamed from: a */
    public final void m5198a(int i) {
        int[] iArr = this.f20757a;
        int b = m5133b();
        m5132b(b + 1);
        iArr[b] = i;
    }

    @NotNull
    /* renamed from: a */
    public final int[] m5199a() {
        return m5134a(this.f20757a, new int[m5130c()]);
    }
}
