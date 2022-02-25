package p110z1;

import org.apache.tools.ant.types.selectors.SizeSelector;

/* compiled from: PrimitiveSpreadBuilders.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\u0002J\f\u0010\f\u001a\u00020\u0004*\u00020\u0002H\u0014R\u000e\u0010\u0006\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, m8860e = {"Lkotlin/jvm/internal/FloatSpreadBuilder;", "Lkotlin/jvm/internal/PrimitiveSpreadBuilder;", "", "size", "", "(I)V", "values", "add", "", SizeSelector.SIZE_KEY, "", "toArray", "getSize", "kotlin-stdlib"})
/* renamed from: z1.cja */
/* loaded from: classes3.dex */
public final class cja extends cjy<float[]> {

    /* renamed from: a */
    private final float[] f20751a;

    public cja(int i) {
        super(i);
        this.f20751a = new float[i];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public int mo5085a(@NotNull float[] fArr) {
        cji.m5162f(fArr, "$this$getSize");
        return fArr.length;
    }

    /* renamed from: a */
    public final void m5207a(float f) {
        float[] fArr = this.f20751a;
        int b = m5133b();
        m5132b(b + 1);
        fArr[b] = f;
    }

    @NotNull
    /* renamed from: a */
    public final float[] m5208a() {
        return m5134a(this.f20751a, new float[m5130c()]);
    }
}
