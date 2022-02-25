package p110z1;

import java.util.Map;

/* renamed from: z1.of */
/* loaded from: classes3.dex */
public final class MultiFormatWriter implements Writer {
    @Override // p110z1.Writer
    /* renamed from: a */
    public final BitMatrix mo1619a(String str, BarcodeFormat fuVar, int i, int i2) throws WriterException {
        return mo1618a(str, fuVar, i, i2, null);
    }

    @Override // p110z1.Writer
    /* renamed from: a */
    public final BitMatrix mo1618a(String str, BarcodeFormat fuVar, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        AztecWriter fzVar;
        switch (fuVar) {
            case EAN_8:
                fzVar = new EAN8Writer();
                break;
            case UPC_E:
                fzVar = new UPCEWriter();
                break;
            case EAN_13:
                fzVar = new EAN13Writer();
                break;
            case UPC_A:
                fzVar = new UPCAWriter();
                break;
            case QR_CODE:
                fzVar = new QRCodeWriter();
                break;
            case CODE_39:
                fzVar = new Code39Writer();
                break;
            case CODE_93:
                fzVar = new Code93Writer();
                break;
            case CODE_128:
                fzVar = new Code128Writer();
                break;
            case ITF:
                fzVar = new ITFWriter();
                break;
            case PDF_417:
                fzVar = new PDF417Writer();
                break;
            case CODABAR:
                fzVar = new CodaBarWriter();
                break;
            case DATA_MATRIX:
                fzVar = new DataMatrixWriter();
                break;
            case AZTEC:
                fzVar = new AztecWriter();
                break;
            default:
                throw new IllegalArgumentException("No encoder available for format ".concat(String.valueOf(fuVar)));
        }
        return fzVar.mo1618a(str, fuVar, i, i2, map);
    }
}
