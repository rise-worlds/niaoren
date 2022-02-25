package p110z1;

/* renamed from: z1.hl */
/* loaded from: classes3.dex */
public final class ProductParsedResult extends ParsedResult {

    /* renamed from: a */
    private final String f21891a;

    /* renamed from: b */
    private final String f21892b;

    private ProductParsedResult(String str) {
        this(str, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProductParsedResult(String str, String str2) {
        super(ParsedResultType.f21880c);
        this.f21891a = str;
        this.f21892b = str2;
    }

    /* renamed from: b */
    private String m2593b() {
        return this.f21891a;
    }

    /* renamed from: c */
    private String m2592c() {
        return this.f21892b;
    }

    @Override // p110z1.ParsedResult
    /* renamed from: a */
    public final String mo2565a() {
        return this.f21891a;
    }
}
