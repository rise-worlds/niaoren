package p110z1;

import org.apache.tools.ant.types.selectors.SizeSelector;

/* compiled from: PrimitiveSpreadBuilders.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0017\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\n\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\u0002J\f\u0010\f\u001a\u00020\u0004*\u00020\u0002H\u0014R\u000e\u0010\u0006\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, m8860e = {"Lkotlin/jvm/internal/ShortSpreadBuilder;", "Lkotlin/jvm/internal/PrimitiveSpreadBuilder;", "", "size", "", "(I)V", "values", "add", "", SizeSelector.SIZE_KEY, "", "toArray", "getSize", "kotlin-stdlib"})
/* renamed from: z1.ckk */
/* loaded from: classes3.dex */
public final class ckk extends cjy<short[]> {

    /* renamed from: a */
    private final short[] f20779a;

    public ckk(int i) {
        super(i);
        this.f20779a = new short[i];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public int mo5085a(@NotNull short[] sArr) {
        cji.m5162f(sArr, "$this$getSize");
        return sArr.length;
    }

    /* renamed from: a */
    public final void m5084a(short s) {
        short[] sArr = this.f20779a;
        int b = m5133b();
        m5132b(b + 1);
        sArr[b] = s;
    }

    @NotNull
    /* renamed from: a */
    public final short[] m5086a() {
        return m5134a(this.f20779a, new short[m5130c()]);
    }
}
