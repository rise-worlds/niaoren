package p110z1;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\r\u0010\n\u001a\u00020\u0001*\u00020\u000bH\u0087\b\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0087\b\u001a\r\u0010\n\u001a\u00020\u0001*\u00020\u000eH\u0087\b\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\f\u001a\u00020\rH\u0087\b\u001a\r\u0010\n\u001a\u00020\u0001*\u00020\u000fH\u0087\b\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u000f2\u0006\u0010\f\u001a\u00020\rH\u0087\b\u001a\r\u0010\n\u001a\u00020\u0001*\u00020\u0010H\u0087\b\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u00102\u0006\u0010\f\u001a\u00020\rH\u0087\b\u001a\r\u0010\u0011\u001a\u00020\u0001*\u00020\u0001H\u0087\n¨\u0006\u0012"}, m8860e = {"dec", "Ljava/math/BigDecimal;", "div", "other", "inc", "minus", "mod", "plus", "rem", "times", "toBigDecimal", "", "mathContext", "Ljava/math/MathContext;", "", "", "", "unaryMinus", "kotlin-stdlib"}, m8859f = "kotlin/NumbersKt", m8857h = 1)
/* renamed from: z1.bwj */
/* loaded from: classes3.dex */
class BigDecimals {
    @cey
    /* renamed from: a */
    private static final BigDecimal m8847a(@NotNull BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        cji.m5162f(bigDecimal, "$this$plus");
        BigDecimal add = bigDecimal.add(bigDecimal2);
        cji.m5175b(add, "this.add(other)");
        return add;
    }

    @cey
    /* renamed from: b */
    private static final BigDecimal m8845b(@NotNull BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        cji.m5162f(bigDecimal, "$this$minus");
        BigDecimal subtract = bigDecimal.subtract(bigDecimal2);
        cji.m5175b(subtract, "this.subtract(other)");
        return subtract;
    }

    @cey
    /* renamed from: c */
    private static final BigDecimal m8843c(@NotNull BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        cji.m5162f(bigDecimal, "$this$times");
        BigDecimal multiply = bigDecimal.multiply(bigDecimal2);
        cji.m5175b(multiply, "this.multiply(other)");
        return multiply;
    }

    @cey
    /* renamed from: d */
    private static final BigDecimal m8842d(@NotNull BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        cji.m5162f(bigDecimal, "$this$div");
        BigDecimal divide = bigDecimal.divide(bigDecimal2, RoundingMode.HALF_EVEN);
        cji.m5175b(divide, "this.divide(other, RoundingMode.HALF_EVEN)");
        return divide;
    }

    @Annotations(m8902a = "Use rem(other) instead", m8901b = @bwu(m8768a = "rem(other)", m8767b = {}), m8900c = bvk.ERROR)
    @cey
    /* renamed from: e */
    private static final BigDecimal m8841e(@NotNull BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        cji.m5162f(bigDecimal, "$this$mod");
        BigDecimal remainder = bigDecimal.remainder(bigDecimal2);
        cji.m5175b(remainder, "this.remainder(other)");
        return remainder;
    }

    @cey
    /* renamed from: f */
    private static final BigDecimal m8840f(@NotNull BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        cji.m5162f(bigDecimal, "$this$rem");
        BigDecimal remainder = bigDecimal.remainder(bigDecimal2);
        cji.m5175b(remainder, "this.remainder(other)");
        return remainder;
    }

    @cey
    /* renamed from: a */
    private static final BigDecimal m8848a(@NotNull BigDecimal bigDecimal) {
        cji.m5162f(bigDecimal, "$this$unaryMinus");
        BigDecimal negate = bigDecimal.negate();
        cji.m5175b(negate, "this.negate()");
        return negate;
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: b */
    private static final BigDecimal m8846b(@NotNull BigDecimal bigDecimal) {
        cji.m5162f(bigDecimal, "$this$inc");
        BigDecimal add = bigDecimal.add(BigDecimal.ONE);
        cji.m5175b(add, "this.add(BigDecimal.ONE)");
        return add;
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: c */
    private static final BigDecimal m8844c(@NotNull BigDecimal bigDecimal) {
        cji.m5162f(bigDecimal, "$this$dec");
        BigDecimal subtract = bigDecimal.subtract(BigDecimal.ONE);
        cji.m5175b(subtract, "this.subtract(BigDecimal.ONE)");
        return subtract;
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: a */
    private static final BigDecimal m8852a(int i) {
        BigDecimal valueOf = BigDecimal.valueOf(i);
        cji.m5175b(valueOf, "BigDecimal.valueOf(this.toLong())");
        return valueOf;
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: a */
    private static final BigDecimal m8851a(int i, MathContext mathContext) {
        return new BigDecimal(i, mathContext);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: a */
    private static final BigDecimal m8850a(long j) {
        BigDecimal valueOf = BigDecimal.valueOf(j);
        cji.m5175b(valueOf, "BigDecimal.valueOf(this)");
        return valueOf;
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: a */
    private static final BigDecimal m8849a(long j, MathContext mathContext) {
        return new BigDecimal(j, mathContext);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: a */
    private static final BigDecimal m8854a(float f) {
        return new BigDecimal(String.valueOf(f));
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: a */
    private static final BigDecimal m8853a(float f, MathContext mathContext) {
        return new BigDecimal(String.valueOf(f), mathContext);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: a */
    private static final BigDecimal m8856a(double d) {
        return new BigDecimal(String.valueOf(d));
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: a */
    private static final BigDecimal m8855a(double d, MathContext mathContext) {
        return new BigDecimal(String.valueOf(d), mathContext);
    }
}
