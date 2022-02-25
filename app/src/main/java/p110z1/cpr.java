package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/* compiled from: StringNumberConversionsJVM.kt */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000X\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\u001a4\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00010\u0005H\u0082\b¢\u0006\u0004\b\u0006\u0010\u0007\u001a\r\u0010\b\u001a\u00020\t*\u00020\u0003H\u0087\b\u001a\u0015\u0010\b\u001a\u00020\t*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0087\b\u001a\u000e\u0010\f\u001a\u0004\u0018\u00010\t*\u00020\u0003H\u0007\u001a\u0016\u0010\f\u001a\u0004\u0018\u00010\t*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0007\u001a\r\u0010\r\u001a\u00020\u000e*\u00020\u0003H\u0087\b\u001a\u0015\u0010\r\u001a\u00020\u000e*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u000e\u0010\u0011\u001a\u0004\u0018\u00010\u000e*\u00020\u0003H\u0007\u001a\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u000e*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0007\u001a\r\u0010\u0012\u001a\u00020\u0013*\u00020\u0003H\u0087\b\u001a\r\u0010\u0014\u001a\u00020\u0015*\u00020\u0003H\u0087\b\u001a\u0015\u0010\u0014\u001a\u00020\u0015*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\r\u0010\u0016\u001a\u00020\u0017*\u00020\u0003H\u0087\b\u001a\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0017*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0019\u001a\r\u0010\u001a\u001a\u00020\u001b*\u00020\u0003H\u0087\b\u001a\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u001b*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u001d\u001a\r\u0010\u001e\u001a\u00020\u0010*\u00020\u0003H\u0087\b\u001a\u0015\u0010\u001e\u001a\u00020\u0010*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\r\u0010\u001f\u001a\u00020 *\u00020\u0003H\u0087\b\u001a\u0015\u0010\u001f\u001a\u00020 *\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\r\u0010!\u001a\u00020\"*\u00020\u0003H\u0087\b\u001a\u0015\u0010!\u001a\u00020\"*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u0015\u0010#\u001a\u00020\u0003*\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u0015\u0010#\u001a\u00020\u0003*\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u0015\u0010#\u001a\u00020\u0003*\u00020 2\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u0015\u0010#\u001a\u00020\u0003*\u00020\"2\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b¨\u0006$"}, m8860e = {"screenFloatValue", TessBaseAPI.f9204e, "str", "", "parse", "Lkotlin/Function1;", "screenFloatValue$StringsKt__StringNumberConversionsJVMKt", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "toBigDecimal", "Ljava/math/BigDecimal;", "mathContext", "Ljava/math/MathContext;", "toBigDecimalOrNull", "toBigInteger", "Ljava/math/BigInteger;", "radix", "", "toBigIntegerOrNull", "toBoolean", "", "toByte", "", "toDouble", "", "toDoubleOrNull", "(Ljava/lang/String;)Ljava/lang/Double;", "toFloat", "", "toFloatOrNull", "(Ljava/lang/String;)Ljava/lang/Float;", "toInt", "toLong", "", "toShort", "", "toString", "kotlin-stdlib"}, m8859f = "kotlin/text/StringsKt", m8857h = 1)
/* renamed from: z1.cpr */
/* loaded from: classes3.dex */
class cpr extends StringBuilder {
    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: a */
    private static final String m4109a(byte b, int i) {
        String num = Integer.toString(b, cov.m4246a(cov.m4246a(i)));
        cji.m5175b(num, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return num;
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: a */
    private static final String m4103a(short s, int i) {
        String num = Integer.toString(s, cov.m4246a(cov.m4246a(i)));
        cji.m5175b(num, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return num;
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: a */
    private static final String m4108a(int i, int i2) {
        String num = Integer.toString(i, cov.m4246a(i2));
        cji.m5175b(num, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return num;
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: a */
    private static final String m4107a(long j, int i) {
        String l = Long.toString(j, cov.m4246a(i));
        cji.m5175b(l, "java.lang.Long.toString(this, checkRadix(radix))");
        return l;
    }

    @cey
    /* renamed from: f */
    private static final boolean m4093f(@NotNull String str) {
        return Boolean.parseBoolean(str);
    }

    @cey
    /* renamed from: g */
    private static final byte m4091g(@NotNull String str) {
        return Byte.parseByte(str);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: b */
    private static final byte m4101b(@NotNull String str, int i) {
        return Byte.parseByte(str, cov.m4246a(i));
    }

    @cey
    /* renamed from: h */
    private static final short m4090h(@NotNull String str) {
        return Short.parseShort(str);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: c */
    private static final short m4098c(@NotNull String str, int i) {
        return Short.parseShort(str, cov.m4246a(i));
    }

    @cey
    /* renamed from: i */
    private static final int m4089i(@NotNull String str) {
        return Integer.parseInt(str);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: d */
    private static final int m4096d(@NotNull String str, int i) {
        return Integer.parseInt(str, cov.m4246a(i));
    }

    @cey
    /* renamed from: j */
    private static final long m4088j(@NotNull String str) {
        return Long.parseLong(str);
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: e */
    private static final long m4094e(@NotNull String str, int i) {
        return Long.parseLong(str, cov.m4246a(i));
    }

    @cey
    /* renamed from: k */
    private static final float m4087k(@NotNull String str) {
        return Float.parseFloat(str);
    }

    @cey
    /* renamed from: l */
    private static final double m4086l(@NotNull String str) {
        return Double.parseDouble(str);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: m */
    private static final BigInteger m4085m(@NotNull String str) {
        return new BigInteger(str);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: f */
    private static final BigInteger m4092f(@NotNull String str, int i) {
        return new BigInteger(str, cov.m4246a(i));
    }

    @bwy(m8750a = "1.2")
    @dbs
    /* renamed from: d */
    public static final BigInteger m4097d(@NotNull String str) {
        cji.m5162f(str, "$this$toBigIntegerOrNull");
        return cpl.m4106a(str, 10);
    }

    @bwy(m8750a = "1.2")
    @dbs
    /* renamed from: a */
    public static final BigInteger m4106a(@NotNull String str, int i) {
        cji.m5162f(str, "$this$toBigIntegerOrNull");
        cov.m4246a(i);
        int length = str.length();
        int i2 = 0;
        switch (length) {
            case 0:
                return null;
            case 1:
                if (cov.m4247a(str.charAt(0), i) < 0) {
                    return null;
                }
                break;
            default:
                if (str.charAt(0) == '-') {
                    i2 = 1;
                }
                while (i2 < length) {
                    if (cov.m4247a(str.charAt(i2), i) < 0) {
                        return null;
                    }
                    i2++;
                }
                break;
        }
        return new BigInteger(str, cov.m4246a(i));
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: n */
    private static final BigDecimal m4084n(@NotNull String str) {
        return new BigDecimal(str);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: b */
    private static final BigDecimal m4100b(@NotNull String str, MathContext mathContext) {
        return new BigDecimal(str, mathContext);
    }

    /* renamed from: a */
    private static final <T> T m4104a(String str, chd<? super String, ? extends T> chdVar) {
        try {
            if (StringNumberConversionsJVM.f21025a.matches(str)) {
                return (T) chdVar.invoke(str);
            }
            return null;
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    @bwy(m8750a = "1.1")
    @dbs
    /* renamed from: b */
    public static final Float m4102b(@NotNull String str) {
        cji.m5162f(str, "$this$toFloatOrNull");
        try {
            if (StringNumberConversionsJVM.f21025a.matches(str)) {
                return Float.valueOf(Float.parseFloat(str));
            }
            return null;
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    @bwy(m8750a = "1.1")
    @dbs
    /* renamed from: c */
    public static final Double m4099c(@NotNull String str) {
        cji.m5162f(str, "$this$toDoubleOrNull");
        try {
            if (StringNumberConversionsJVM.f21025a.matches(str)) {
                return Double.valueOf(Double.parseDouble(str));
            }
            return null;
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    @bwy(m8750a = "1.2")
    @dbs
    /* renamed from: e */
    public static final BigDecimal m4095e(@NotNull String str) {
        cji.m5162f(str, "$this$toBigDecimalOrNull");
        try {
            if (StringNumberConversionsJVM.f21025a.matches(str)) {
                return new BigDecimal(str);
            }
            return null;
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    @bwy(m8750a = "1.2")
    @dbs
    /* renamed from: a */
    public static final BigDecimal m4105a(@NotNull String str, @NotNull MathContext mathContext) {
        cji.m5162f(str, "$this$toBigDecimalOrNull");
        cji.m5162f(mathContext, "mathContext");
        try {
            if (StringNumberConversionsJVM.f21025a.matches(str)) {
                return new BigDecimal(str, mathContext);
            }
            return null;
        } catch (NumberFormatException unused) {
            return null;
        }
    }
}
