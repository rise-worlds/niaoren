package p110z1;

import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import org.apache.tools.ant.types.selectors.SizeSelector;

@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000.\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\u001a\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0002\u001a\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000\u001a\u0018\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000bH\u0000\u001a\u0018\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000bH\u0000\"\u001c\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m8860e = {"precisionFormats", "", "Ljava/lang/ThreadLocal;", "Ljava/text/DecimalFormat;", "[Ljava/lang/ThreadLocal;", "rootNegativeExpFormatSymbols", "Ljava/text/DecimalFormatSymbols;", "rootPositiveExpFormatSymbols", "scientificFormat", "createFormatForDecimals", "decimals", "", "formatScientific", "", SizeSelector.SIZE_KEY, "", "formatToExactDecimals", "formatUpToDecimals", "kotlin-stdlib"})
/* renamed from: z1.cqo */
/* loaded from: classes3.dex */
public final class formatToDecimals {

    /* renamed from: a */
    private static final DecimalFormatSymbols f21090a;

    /* renamed from: b */
    private static final DecimalFormatSymbols f21091b;

    /* renamed from: c */
    private static final ThreadLocal<DecimalFormat>[] f21092c;

    /* renamed from: d */
    private static final ThreadLocal<DecimalFormat> f21093d;

    static {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.ROOT);
        decimalFormatSymbols.setExponentSeparator("e");
        f21090a = decimalFormatSymbols;
        DecimalFormatSymbols decimalFormatSymbols2 = new DecimalFormatSymbols(Locale.ROOT);
        decimalFormatSymbols2.setExponentSeparator("e+");
        f21091b = decimalFormatSymbols2;
        ThreadLocal<DecimalFormat>[] threadLocalArr = new ThreadLocal[4];
        for (int i = 0; i < 4; i++) {
            threadLocalArr[i] = new ThreadLocal<>();
        }
        f21092c = threadLocalArr;
        f21093d = new ThreadLocal<>();
    }

    /* renamed from: a */
    private static final DecimalFormat m3545a(int i) {
        DecimalFormat decimalFormat = new DecimalFormat(ResultTypeConstant.f7213z, f21090a);
        if (i > 0) {
            decimalFormat.setMinimumFractionDigits(i);
        }
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return decimalFormat;
    }

    @NotNull
    /* renamed from: a */
    public static final String m3546a(double d, int i) {
        DecimalFormat decimalFormat;
        ThreadLocal<DecimalFormat>[] threadLocalArr = f21092c;
        if (i < threadLocalArr.length) {
            ThreadLocal<DecimalFormat> threadLocal = threadLocalArr[i];
            DecimalFormat decimalFormat2 = threadLocal.get();
            if (decimalFormat2 == null) {
                decimalFormat2 = m3545a(i);
                threadLocal.set(decimalFormat2);
            }
            decimalFormat = decimalFormat2;
        } else {
            decimalFormat = m3545a(i);
        }
        String format = decimalFormat.format(d);
        cji.m5175b(format, "format.format(value)");
        return format;
    }

    @NotNull
    /* renamed from: b */
    public static final String m3544b(double d, int i) {
        DecimalFormat a = m3545a(0);
        a.setMaximumFractionDigits(i);
        String format = a.format(d);
        cji.m5175b(format, "createFormatForDecimals(… }\n        .format(value)");
        return format;
    }

    @NotNull
    /* renamed from: a */
    public static final String m3547a(double d) {
        ThreadLocal<DecimalFormat> threadLocal = f21093d;
        DecimalFormat decimalFormat = threadLocal.get();
        if (decimalFormat == null) {
            decimalFormat = new DecimalFormat("0E0", f21090a);
            decimalFormat.setMinimumFractionDigits(2);
            threadLocal.set(decimalFormat);
        }
        DecimalFormat decimalFormat2 = decimalFormat;
        decimalFormat2.setDecimalFormatSymbols((d >= ((double) 1) || d <= ((double) (-1))) ? f21091b : f21090a);
        String format = decimalFormat2.format(d);
        cji.m5175b(format, "scientificFormat.getOrSe… }\n        .format(value)");
        return format;
    }
}
