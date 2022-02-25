package p110z1;

import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import java.util.Map;

/* renamed from: z1.lx */
/* loaded from: classes3.dex */
public final class UPCAWriter implements Writer {

    /* renamed from: a */
    private final EAN13Writer f22384a = new EAN13Writer();

    @Override // p110z1.Writer
    /* renamed from: a */
    public final BitMatrix mo1619a(String str, BarcodeFormat fuVar, int i, int i2) throws WriterException {
        return mo1618a(str, fuVar, i, i2, null);
    }

    @Override // p110z1.Writer
    /* renamed from: a */
    public final BitMatrix mo1618a(String str, BarcodeFormat fuVar, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (fuVar == BarcodeFormat.UPC_A) {
            return this.f22384a.mo1618a(ResultTypeConstant.f7213z.concat(String.valueOf(str)), BarcodeFormat.EAN_13, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode UPC-A, but got ".concat(String.valueOf(fuVar)));
    }
}
