package p110z1;

import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.tencent.smtt.sdk.TbsListener;

/* renamed from: z1.jh */
/* loaded from: classes3.dex */
public class SymbolInfo {

    /* renamed from: a */
    static final SymbolInfo[] f22112a;

    /* renamed from: g */
    private static SymbolInfo[] f22113g;

    /* renamed from: b */
    final int f22114b;

    /* renamed from: c */
    final int f22115c;

    /* renamed from: d */
    public final int f22116d;

    /* renamed from: e */
    public final int f22117e;

    /* renamed from: f */
    final int f22118f;

    /* renamed from: h */
    private final boolean f22119h;

    /* renamed from: i */
    private final int f22120i;

    /* renamed from: j */
    private final int f22121j;

    static {
        SymbolInfo[] jhVarArr = {new SymbolInfo(false, 3, 5, 8, 8, 1), new SymbolInfo(false, 5, 7, 10, 10, 1), new SymbolInfo(true, 5, 7, 16, 6, 1), new SymbolInfo(false, 8, 10, 12, 12, 1), new SymbolInfo(true, 10, 11, 14, 6, 2), new SymbolInfo(false, 12, 12, 14, 14, 1), new SymbolInfo(true, 16, 14, 24, 10, 1), new SymbolInfo(false, 18, 14, 16, 16, 1), new SymbolInfo(false, 22, 18, 18, 18, 1), new SymbolInfo(true, 22, 18, 16, 10, 2), new SymbolInfo(false, 30, 20, 20, 20, 1), new SymbolInfo(true, 32, 24, 16, 14, 2), new SymbolInfo(false, 36, 24, 22, 22, 1), new SymbolInfo(false, 44, 28, 24, 24, 1), new SymbolInfo(true, 49, 28, 22, 14, 2), new SymbolInfo(false, 62, 36, 14, 14, 4), new SymbolInfo(false, 86, 42, 16, 16, 4), new SymbolInfo(false, 114, 48, 18, 18, 4), new SymbolInfo(false, TbsListener.ErrorCode.NEEDDOWNLOAD_5, 56, 20, 20, 4), new SymbolInfo(false, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_4, 68, 22, 22, 4), new SymbolInfo(false, 204, 84, 24, 24, 4, 102, 42), new SymbolInfo(false, 280, 112, 14, 14, 16, TbsListener.ErrorCode.NEEDDOWNLOAD_1, 56), new SymbolInfo(false, 368, TbsListener.ErrorCode.NEEDDOWNLOAD_5, 16, 16, 16, 92, 36), new SymbolInfo(false, 456, 192, 18, 18, 16, 114, 48), new SymbolInfo(false, 576, TbsListener.ErrorCode.EXCEED_INCR_UPDATE, 20, 20, 16, TbsListener.ErrorCode.NEEDDOWNLOAD_5, 56), new SymbolInfo(false, 696, ResultTypeConstant.f7199l, 22, 22, 16, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_4, 68), new SymbolInfo(false, 816, 336, 24, 24, 16, 136, 56), new SymbolInfo(false, 1050, 408, 18, 18, 36, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_5, 68), new SymbolInfo(false, 1304, 496, 20, 20, 36, TbsListener.ErrorCode.STARTDOWNLOAD_4, 62), new DataMatrixSymbolInfo144()};
        f22112a = jhVarArr;
        f22113g = jhVarArr;
    }

    /* renamed from: a */
    private static void m2287a(SymbolInfo[] jhVarArr) {
        f22113g = jhVarArr;
    }

    private SymbolInfo(boolean z, int i, int i2, int i3, int i4, int i5) {
        this(z, i, i2, i3, i4, i5, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SymbolInfo(boolean z, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.f22119h = z;
        this.f22114b = i;
        this.f22115c = i2;
        this.f22116d = i3;
        this.f22117e = i4;
        this.f22120i = i5;
        this.f22121j = i6;
        this.f22118f = i7;
    }

    /* renamed from: b */
    private static SymbolInfo m2285b(int i) {
        return m2289a(i, SymbolShapeHint.FORCE_NONE, true);
    }

    /* renamed from: a */
    private static SymbolInfo m2291a(int i, SymbolShapeHint jiVar) {
        return m2289a(i, jiVar, true);
    }

    /* renamed from: a */
    private static SymbolInfo m2288a(int i, boolean z, boolean z2) {
        return m2289a(i, z ? SymbolShapeHint.FORCE_NONE : SymbolShapeHint.FORCE_SQUARE, z2);
    }

    /* renamed from: a */
    private static SymbolInfo m2289a(int i, SymbolShapeHint jiVar, boolean z) {
        return m2290a(i, jiVar, null, null, z);
    }

    /* renamed from: a */
    public static SymbolInfo m2290a(int i, SymbolShapeHint jiVar, C5384jq jqVar, C5384jq jqVar2, boolean z) {
        SymbolInfo[] jhVarArr;
        for (SymbolInfo jhVar : f22113g) {
            if ((jiVar != SymbolShapeHint.FORCE_SQUARE || !jhVar.f22119h) && ((jiVar != SymbolShapeHint.FORCE_RECTANGLE || jhVar.f22119h) && ((jqVar == null || (jhVar.m2283d() >= jqVar.f22166a && jhVar.m2282e() >= jqVar.f22167b)) && ((jqVar2 == null || (jhVar.m2283d() <= jqVar2.f22166a && jhVar.m2282e() <= jqVar2.f22167b)) && i <= jhVar.f22114b)))) {
                return jhVar;
            }
        }
        if (!z) {
            return null;
        }
        throw new IllegalArgumentException("Can't find a symbol arrangement that matches the message. Data codewords: ".concat(String.valueOf(i)));
    }

    /* renamed from: f */
    private int m2281f() {
        int i = this.f22120i;
        if (i == 4) {
            return 2;
        }
        if (i == 16) {
            return 4;
        }
        if (i == 36) {
            return 6;
        }
        switch (i) {
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                throw new IllegalStateException("Cannot handle this number of data regions");
        }
    }

    /* renamed from: g */
    private int m2280g() {
        int i = this.f22120i;
        if (i == 4) {
            return 2;
        }
        if (i == 16) {
            return 4;
        }
        if (i == 36) {
            return 6;
        }
        switch (i) {
            case 1:
            case 2:
                return 1;
            default:
                throw new IllegalStateException("Cannot handle this number of data regions");
        }
    }

    /* renamed from: b */
    public final int m2286b() {
        return m2281f() * this.f22116d;
    }

    /* renamed from: c */
    public final int m2284c() {
        return m2280g() * this.f22117e;
    }

    /* renamed from: d */
    public final int m2283d() {
        return m2286b() + (m2281f() << 1);
    }

    /* renamed from: e */
    public final int m2282e() {
        return m2284c() + (m2280g() << 1);
    }

    /* renamed from: h */
    private int m2279h() {
        return this.f22114b + this.f22115c;
    }

    /* renamed from: a */
    public int mo2293a() {
        return this.f22114b / this.f22121j;
    }

    /* renamed from: i */
    private int m2278i() {
        return this.f22114b;
    }

    /* renamed from: j */
    private int m2277j() {
        return this.f22115c;
    }

    /* renamed from: a */
    public int mo2292a(int i) {
        return this.f22121j;
    }

    /* renamed from: k */
    private int m2276k() {
        return this.f22118f;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f22119h ? "Rectangular Symbol:" : "Square Symbol:");
        sb.append(" data region ");
        sb.append(this.f22116d);
        sb.append('x');
        sb.append(this.f22117e);
        sb.append(", symbol size ");
        sb.append(m2283d());
        sb.append('x');
        sb.append(m2282e());
        sb.append(", symbol data size ");
        sb.append(m2286b());
        sb.append('x');
        sb.append(m2284c());
        sb.append(", codewords ");
        sb.append(this.f22114b);
        sb.append('+');
        sb.append(this.f22115c);
        return sb.toString();
    }
}
