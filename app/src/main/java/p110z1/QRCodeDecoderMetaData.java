package p110z1;

/* renamed from: z1.no */
/* loaded from: classes3.dex */
public final class QRCodeDecoderMetaData {

    /* renamed from: a */
    private final boolean f22619a = true;

    /* renamed from: a */
    private boolean m1813a() {
        return this.f22619a;
    }

    /* renamed from: a */
    public final void m1812a(ResultPoint[] onVarArr) {
        if (this.f22619a && onVarArr != null && onVarArr.length >= 3) {
            ResultPoint onVar = onVarArr[0];
            onVarArr[0] = onVarArr[2];
            onVarArr[2] = onVar;
        }
    }
}
