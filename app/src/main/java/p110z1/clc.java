package p110z1;

import org.apache.tools.ant.types.selectors.DepthSelector;

/* compiled from: MathJVM.kt */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\"\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b7\u001a\u0011\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\tH\u0087\b\u001a\u0011\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\fH\u0087\b\u001a\u0011\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0010\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0007\u001a\u0011\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0010\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0007\u001a\u0011\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0019\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0019\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0010\u0010 \u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0007\u001a\u0011\u0010 \u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010!\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010!\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010\"\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010\"\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010#\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010#\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010$\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010$\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010%\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010%\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010&\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010&\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0019\u0010'\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u0001H\u0087\b\u001a\u0019\u0010'\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010(\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010(\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010)\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010)\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0018\u0010*\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00012\u0006\u0010+\u001a\u00020\u0001H\u0007\u001a\u0018\u0010*\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010+\u001a\u00020\u0006H\u0007\u001a\u0011\u0010,\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010,\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0010\u0010-\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0007\u001a\u0010\u0010-\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0007\u001a\u0019\u0010.\u001a\u00020\u00012\u0006\u0010/\u001a\u00020\u00012\u0006\u00100\u001a\u00020\u0001H\u0087\b\u001a\u0019\u0010.\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u00062\u0006\u00100\u001a\u00020\u0006H\u0087\b\u001a\u0019\u0010.\u001a\u00020\t2\u0006\u0010/\u001a\u00020\t2\u0006\u00100\u001a\u00020\tH\u0087\b\u001a\u0019\u0010.\u001a\u00020\f2\u0006\u0010/\u001a\u00020\f2\u0006\u00100\u001a\u00020\fH\u0087\b\u001a\u0019\u00101\u001a\u00020\u00012\u0006\u0010/\u001a\u00020\u00012\u0006\u00100\u001a\u00020\u0001H\u0087\b\u001a\u0019\u00101\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u00062\u0006\u00100\u001a\u00020\u0006H\u0087\b\u001a\u0019\u00101\u001a\u00020\t2\u0006\u0010/\u001a\u00020\t2\u0006\u00100\u001a\u00020\tH\u0087\b\u001a\u0019\u00101\u001a\u00020\f2\u0006\u0010/\u001a\u00020\f2\u0006\u00100\u001a\u00020\fH\u0087\b\u001a\u0011\u00102\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u00102\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u00103\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u00103\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u00104\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u00104\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u00105\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u00105\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u00106\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u00106\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0011\u00107\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0011\u00107\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0010\u00108\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0007\u001a\u0010\u00108\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0007\u001a\u0015\u00109\u001a\u00020\u0001*\u00020\u00012\u0006\u0010:\u001a\u00020\u0001H\u0087\b\u001a\u0015\u00109\u001a\u00020\u0006*\u00020\u00062\u0006\u0010:\u001a\u00020\u0006H\u0087\b\u001a\r\u0010;\u001a\u00020\u0001*\u00020\u0001H\u0087\b\u001a\r\u0010;\u001a\u00020\u0006*\u00020\u0006H\u0087\b\u001a\u0015\u0010<\u001a\u00020\u0001*\u00020\u00012\u0006\u0010=\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010<\u001a\u00020\u0006*\u00020\u00062\u0006\u0010=\u001a\u00020\u0006H\u0087\b\u001a\r\u0010>\u001a\u00020\u0001*\u00020\u0001H\u0087\b\u001a\r\u0010>\u001a\u00020\u0006*\u00020\u0006H\u0087\b\u001a\u0015\u0010?\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010?\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0018\u001a\u00020\tH\u0087\b\u001a\u0015\u0010?\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0087\b\u001a\u0015\u0010?\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0018\u001a\u00020\tH\u0087\b\u001a\f\u0010@\u001a\u00020\t*\u00020\u0001H\u0007\u001a\f\u0010@\u001a\u00020\t*\u00020\u0006H\u0007\u001a\f\u0010A\u001a\u00020\f*\u00020\u0001H\u0007\u001a\f\u0010A\u001a\u00020\f*\u00020\u0006H\u0007\u001a\u0015\u0010B\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0087\b\u001a\u0015\u0010B\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\tH\u0087\b\u001a\u0015\u0010B\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0087\b\u001a\u0015\u0010B\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u000f\u001a\u00020\tH\u0087\b\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00018Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"\u001f\u0010\u0000\u001a\u00020\u0006*\u00020\u00068Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0002\u0010\u0007\u001a\u0004\b\u0004\u0010\b\"\u001f\u0010\u0000\u001a\u00020\t*\u00020\t8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0002\u0010\n\u001a\u0004\b\u0004\u0010\u000b\"\u001f\u0010\u0000\u001a\u00020\f*\u00020\f8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0002\u0010\r\u001a\u0004\b\u0004\u0010\u000e\"\u001f\u0010\u000f\u001a\u00020\u0001*\u00020\u00018Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0010\u0010\u0003\u001a\u0004\b\u0011\u0010\u0005\"\u001f\u0010\u000f\u001a\u00020\u0006*\u00020\u00068Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0010\u0010\u0007\u001a\u0004\b\u0011\u0010\b\"\u001e\u0010\u000f\u001a\u00020\t*\u00020\t8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0010\u0010\n\u001a\u0004\b\u0011\u0010\u000b\"\u001e\u0010\u000f\u001a\u00020\t*\u00020\f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u0012\"\u001f\u0010\u0013\u001a\u00020\u0001*\u00020\u00018Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0014\u0010\u0003\u001a\u0004\b\u0015\u0010\u0005\"\u001f\u0010\u0013\u001a\u00020\u0006*\u00020\u00068Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0014\u0010\u0007\u001a\u0004\b\u0015\u0010\b¨\u0006C"}, m8860e = {"absoluteValue", "", "absoluteValue$annotations", "(D)V", "getAbsoluteValue", "(D)D", "", "(F)V", "(F)F", "", "(I)V", "(I)I", "", "(J)V", "(J)J", "sign", "sign$annotations", "getSign", "(J)I", "ulp", "ulp$annotations", "getUlp", "abs", "x", "n", "acos", "acosh", "asin", "asinh", "atan", "atan2", "y", "atanh", "ceil", "cos", "cosh", "exp", "expm1", "floor", "hypot", "ln", "ln1p", "log", "base", "log10", "log2", DepthSelector.MAX_KEY, "a", "b", DepthSelector.MIN_KEY, "round", "sin", "sinh", "sqrt", "tan", "tanh", "truncate", "IEEErem", "divisor", "nextDown", "nextTowards", "to", "nextUp", "pow", "roundToInt", "roundToLong", "withSign", "kotlin-stdlib"}, m8859f = "kotlin/math/MathKt", m8857h = 1)
/* renamed from: z1.clc */
/* loaded from: classes3.dex */
class clc extends MathH {
    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: a */
    public static /* synthetic */ void m5003a(int i) {
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: a */
    public static /* synthetic */ void m5001a(long j) {
    }

    @bwy(m8750a = "1.2")
    /* renamed from: b */
    public static /* synthetic */ void m4993b(int i) {
    }

    @bwy(m8750a = "1.2")
    /* renamed from: b */
    public static /* synthetic */ void m4991b(long j) {
    }

    /* renamed from: c */
    public static final int m4985c(int i) {
        if (i < 0) {
            return -1;
        }
        return i > 0 ? 1 : 0;
    }

    /* renamed from: c */
    public static final int m4984c(long j) {
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        return i > 0 ? 1 : 0;
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: c */
    public static /* synthetic */ void m4987c(float f) {
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: d */
    public static /* synthetic */ void m4981d(float f) {
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: e */
    public static /* synthetic */ void m4975e(float f) {
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: f */
    public static /* synthetic */ void m4971f(double d) {
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: g */
    public static /* synthetic */ void m4967g(double d) {
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: h */
    public static /* synthetic */ void m4963h(double d) {
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: k */
    private static final double m4953k(double d) {
        return Math.sin(d);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: l */
    private static final double m4951l(double d) {
        return Math.cos(d);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: m */
    private static final double m4949m(double d) {
        return Math.tan(d);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: n */
    private static final double m4947n(double d) {
        return Math.asin(d);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: o */
    private static final double m4945o(double d) {
        return Math.acos(d);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: p */
    private static final double m4943p(double d) {
        return Math.atan(d);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: b */
    private static final double m4998b(double d, double d2) {
        return Math.atan2(d, d2);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: q */
    private static final double m4941q(double d) {
        return Math.sinh(d);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: r */
    private static final double m4939r(double d) {
        return Math.cosh(d);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: s */
    private static final double m4937s(double d) {
        return Math.tanh(d);
    }

    @bwy(m8750a = "1.2")
    /* renamed from: a */
    public static final double m5009a(double d) {
        if (d < MathJVM.f20789d) {
            return d <= (-MathJVM.f20789d) ? -cla.m5009a(-d) : Math.abs(d) >= MathJVM.f20788c ? d - (((d * d) * d) / 6) : d;
        }
        if (d <= MathJVM.f20791f) {
            return Math.log(d + Math.sqrt((d * d) + 1));
        }
        if (d > MathJVM.f20790e) {
            return Math.log(d) + MathJVM.f20786a;
        }
        double d2 = d * 2;
        return Math.log(d2 + (1 / d2));
    }

    @bwy(m8750a = "1.2")
    /* renamed from: b */
    public static final double m4999b(double d) {
        double d2 = 1;
        if (d < d2) {
            return ciw.f20730a.m5220e();
        }
        if (d > MathJVM.f20790e) {
            return Math.log(d) + MathJVM.f20786a;
        }
        double d3 = d - d2;
        if (d3 >= MathJVM.f20789d) {
            return Math.log(d + Math.sqrt((d * d) - d2));
        }
        double sqrt = Math.sqrt(d3);
        if (sqrt >= MathJVM.f20788c) {
            sqrt -= ((sqrt * sqrt) * sqrt) / 12;
        }
        return sqrt * Math.sqrt(2.0d);
    }

    @bwy(m8750a = "1.2")
    /* renamed from: c */
    public static final double m4989c(double d) {
        if (Math.abs(d) < MathJVM.f20789d) {
            return Math.abs(d) > MathJVM.f20788c ? d + (((d * d) * d) / 3) : d;
        }
        double d2 = 1;
        return Math.log((d2 + d) / (d2 - d)) / 2;
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: c */
    private static final double m4988c(double d, double d2) {
        return Math.hypot(d, d2);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: t */
    private static final double m4935t(double d) {
        return Math.sqrt(d);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: u */
    private static final double m4933u(double d) {
        return Math.exp(d);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: v */
    private static final double m4931v(double d) {
        return Math.expm1(d);
    }

    @bwy(m8750a = "1.2")
    /* renamed from: a */
    public static final double m5008a(double d, double d2) {
        if (d2 <= 0.0d || d2 == 1.0d) {
            return ciw.f20730a.m5220e();
        }
        return Math.log(d) / Math.log(d2);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: w */
    private static final double m4929w(double d) {
        return Math.log(d);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: x */
    private static final double m4927x(double d) {
        return Math.log10(d);
    }

    @bwy(m8750a = "1.2")
    /* renamed from: d */
    public static final double m4983d(double d) {
        return Math.log(d) / MathJVM.f20786a;
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: y */
    private static final double m4925y(double d) {
        return Math.log1p(d);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: z */
    private static final double m4923z(double d) {
        return Math.ceil(d);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: A */
    private static final double m5027A(double d) {
        return Math.floor(d);
    }

    @bwy(m8750a = "1.2")
    /* renamed from: e */
    public static final double m4977e(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            return d;
        }
        if (d > 0) {
            return Math.floor(d);
        }
        return Math.ceil(d);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: B */
    private static final double m5025B(double d) {
        return Math.rint(d);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: C */
    private static final double m5023C(double d) {
        return Math.abs(d);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: D */
    private static final double m5021D(double d) {
        return Math.signum(d);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: d */
    private static final double m4982d(double d, double d2) {
        return Math.min(d, d2);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: e */
    private static final double m4976e(double d, double d2) {
        return Math.max(d, d2);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: f */
    private static final double m4970f(double d, double d2) {
        return Math.pow(d, d2);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: a */
    private static final double m5007a(double d, int i) {
        return Math.pow(d, i);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: g */
    private static final double m4966g(double d, double d2) {
        return Math.IEEEremainder(d, d2);
    }

    /* renamed from: E */
    private static final double m5019E(double d) {
        return Math.abs(d);
    }

    /* renamed from: F */
    private static final double m5017F(double d) {
        return Math.signum(d);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: h */
    private static final double m4962h(double d, double d2) {
        return Math.copySign(d, d2);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: b */
    private static final double m4997b(double d, int i) {
        return Math.copySign(d, i);
    }

    /* renamed from: G */
    private static final double m5015G(double d) {
        return Math.ulp(d);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: H */
    private static final double m5013H(double d) {
        return Math.nextUp(d);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: I */
    private static final double m5011I(double d) {
        return Math.nextAfter(d, ciw.f20730a.m5221d());
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: i */
    private static final double m4958i(double d, double d2) {
        return Math.nextAfter(d, d2);
    }

    @bwy(m8750a = "1.2")
    /* renamed from: i */
    public static final int m4959i(double d) {
        if (Double.isNaN(d)) {
            throw new IllegalArgumentException("Cannot round NaN value.");
        } else if (d > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else {
            if (d < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            return (int) Math.round(d);
        }
    }

    @bwy(m8750a = "1.2")
    /* renamed from: j */
    public static final long m4955j(double d) {
        if (!Double.isNaN(d)) {
            return Math.round(d);
        }
        throw new IllegalArgumentException("Cannot round NaN value.");
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: h */
    private static final float m4961h(float f) {
        return (float) Math.sin(f);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: i */
    private static final float m4957i(float f) {
        return (float) Math.cos(f);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: j */
    private static final float m4954j(float f) {
        return (float) Math.tan(f);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: k */
    private static final float m4952k(float f) {
        return (float) Math.asin(f);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: l */
    private static final float m4950l(float f) {
        return (float) Math.acos(f);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: m */
    private static final float m4948m(float f) {
        return (float) Math.atan(f);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: b */
    private static final float m4995b(float f, float f2) {
        return (float) Math.atan2(f, f2);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: n */
    private static final float m4946n(float f) {
        return (float) Math.sinh(f);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: o */
    private static final float m4944o(float f) {
        return (float) Math.cosh(f);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: p */
    private static final float m4942p(float f) {
        return (float) Math.tanh(f);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: q */
    private static final float m4940q(float f) {
        return (float) cla.m5009a(f);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: r */
    private static final float m4938r(float f) {
        return (float) cla.m4999b(f);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: s */
    private static final float m4936s(float f) {
        return (float) cla.m4989c(f);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: c */
    private static final float m4986c(float f, float f2) {
        return (float) Math.hypot(f, f2);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: t */
    private static final float m4934t(float f) {
        return (float) Math.sqrt(f);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: u */
    private static final float m4932u(float f) {
        return (float) Math.exp(f);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: v */
    private static final float m4930v(float f) {
        return (float) Math.expm1(f);
    }

    @bwy(m8750a = "1.2")
    /* renamed from: a */
    public static final float m5005a(float f, float f2) {
        if (f2 <= 0.0f || f2 == 1.0f) {
            return ciz.f20738a.m5212e();
        }
        return (float) (Math.log(f) / Math.log(f2));
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: w */
    private static final float m4928w(float f) {
        return (float) Math.log(f);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: x */
    private static final float m4926x(float f) {
        return (float) Math.log10(f);
    }

    @bwy(m8750a = "1.2")
    /* renamed from: a */
    public static final float m5006a(float f) {
        return (float) (Math.log(f) / MathJVM.f20786a);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: y */
    private static final float m4924y(float f) {
        return (float) Math.log1p(f);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: z */
    private static final float m4922z(float f) {
        return (float) Math.ceil(f);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: A */
    private static final float m5026A(float f) {
        return (float) Math.floor(f);
    }

    @bwy(m8750a = "1.2")
    /* renamed from: b */
    public static final float m4996b(float f) {
        if (Float.isNaN(f) || Float.isInfinite(f)) {
            return f;
        }
        if (f > 0) {
            return (float) Math.floor(f);
        }
        return (float) Math.ceil(f);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: B */
    private static final float m5024B(float f) {
        return (float) Math.rint(f);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: C */
    private static final float m5022C(float f) {
        return Math.abs(f);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: D */
    private static final float m5020D(float f) {
        return Math.signum(f);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: d */
    private static final float m4980d(float f, float f2) {
        return Math.min(f, f2);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: e */
    private static final float m4974e(float f, float f2) {
        return Math.max(f, f2);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: f */
    private static final float m4968f(float f, float f2) {
        return (float) Math.pow(f, f2);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: a */
    private static final float m5004a(float f, int i) {
        return (float) Math.pow(f, i);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: g */
    private static final float m4964g(float f, float f2) {
        return (float) Math.IEEEremainder(f, f2);
    }

    /* renamed from: E */
    private static final float m5018E(float f) {
        return Math.abs(f);
    }

    /* renamed from: F */
    private static final float m5016F(float f) {
        return Math.signum(f);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: h */
    private static final float m4960h(float f, float f2) {
        return Math.copySign(f, f2);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: b */
    private static final float m4994b(float f, int i) {
        return Math.copySign(f, i);
    }

    /* renamed from: G */
    private static final float m5014G(float f) {
        return Math.ulp(f);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: H */
    private static final float m5012H(float f) {
        return Math.nextUp(f);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: I */
    private static final float m5010I(float f) {
        return Math.nextAfter(f, ciw.f20730a.m5221d());
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: i */
    private static final float m4956i(float f, float f2) {
        return Math.nextAfter(f, f2);
    }

    @bwy(m8750a = "1.2")
    /* renamed from: f */
    public static final int m4969f(float f) {
        if (!Float.isNaN(f)) {
            return Math.round(f);
        }
        throw new IllegalArgumentException("Cannot round NaN value.");
    }

    @bwy(m8750a = "1.2")
    /* renamed from: g */
    public static final long m4965g(float f) {
        return cla.m4955j(f);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: d */
    private static final int m4979d(int i) {
        return Math.abs(i);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: a */
    private static final int m5002a(int i, int i2) {
        return Math.min(i, i2);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: b */
    private static final int m4992b(int i, int i2) {
        return Math.max(i, i2);
    }

    /* renamed from: e */
    private static final int m4973e(int i) {
        return Math.abs(i);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: d */
    private static final long m4978d(long j) {
        return Math.abs(j);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: a */
    private static final long m5000a(long j, long j2) {
        return Math.min(j, j2);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: b */
    private static final long m4990b(long j, long j2) {
        return Math.max(j, j2);
    }

    /* renamed from: e */
    private static final long m4972e(long j) {
        return Math.abs(j);
    }
}
