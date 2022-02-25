package p110z1;

/* renamed from: z1.kk */
/* loaded from: classes3.dex */
public abstract class AbstractExpandedDecoder {

    /* renamed from: b */
    final BitArray f22233b;

    /* renamed from: c */
    final GeneralAppIdDecoder f22234c;

    /* renamed from: a */
    public abstract String mo2216a() throws NotFoundException, FormatException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractExpandedDecoder(BitArray huVar) {
        this.f22233b = huVar;
        this.f22234c = new GeneralAppIdDecoder(huVar);
    }

    /* renamed from: b */
    private BitArray m2218b() {
        return this.f22233b;
    }

    /* renamed from: c */
    private GeneralAppIdDecoder m2217c() {
        return this.f22234c;
    }

    /* renamed from: a */
    private static AbstractExpandedDecoder m2219a(BitArray huVar) {
        if (huVar.m2551a(1)) {
            return new AI01AndOtherAIs(huVar);
        }
        if (!huVar.m2551a(2)) {
            return new AnyAIDecoder(huVar);
        }
        switch (GeneralAppIdDecoder.m2183a(huVar, 1, 4)) {
            case 4:
                return new AI013103decoder(huVar);
            case 5:
                return new AI01320xDecoder(huVar);
            default:
                switch (GeneralAppIdDecoder.m2183a(huVar, 1, 5)) {
                    case 12:
                        return new AI01392xDecoder(huVar);
                    case 13:
                        return new AI01393xDecoder(huVar);
                    default:
                        switch (GeneralAppIdDecoder.m2183a(huVar, 1, 7)) {
                            case 56:
                                return new AI013x0x1xDecoder(huVar, "310", "11");
                            case 57:
                                return new AI013x0x1xDecoder(huVar, "320", "11");
                            case 58:
                                return new AI013x0x1xDecoder(huVar, "310", "13");
                            case 59:
                                return new AI013x0x1xDecoder(huVar, "320", "13");
                            case 60:
                                return new AI013x0x1xDecoder(huVar, "310", "15");
                            case 61:
                                return new AI013x0x1xDecoder(huVar, "320", "15");
                            case 62:
                                return new AI013x0x1xDecoder(huVar, "310", "17");
                            case 63:
                                return new AI013x0x1xDecoder(huVar, "320", "17");
                            default:
                                throw new IllegalStateException("unknown decoder: ".concat(String.valueOf(huVar)));
                        }
                }
        }
    }
}
