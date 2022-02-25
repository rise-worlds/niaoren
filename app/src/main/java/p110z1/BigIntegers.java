package p110z1;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\n\u001a\r\u0010\u0005\u001a\u00020\u0001*\u00020\u0001H\u0087\n\u001a\r\u0010\u0006\u001a\u00020\u0001*\u00020\u0001H\u0087\b\u001a\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\u000b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001a\u00020\rH\u0087\f\u001a\u0015\u0010\u000e\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001a\u00020\rH\u0087\f\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\n\u001a\r\u0010\u0010\u001a\u00020\u0011*\u00020\u0001H\u0087\b\u001a!\u0010\u0010\u001a\u00020\u0011*\u00020\u00012\b\b\u0002\u0010\u0012\u001a\u00020\r2\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0087\b\u001a\r\u0010\u0015\u001a\u00020\u0001*\u00020\rH\u0087\b\u001a\r\u0010\u0015\u001a\u00020\u0001*\u00020\u0016H\u0087\b\u001a\r\u0010\u0017\u001a\u00020\u0001*\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0018\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f¨\u0006\u0019"}, m8860e = {"and", "Ljava/math/BigInteger;", "other", "dec", "div", "inc", "inv", "minus", "or", "plus", "rem", "shl", "n", "", "shr", "times", "toBigDecimal", "Ljava/math/BigDecimal;", "scale", "mathContext", "Ljava/math/MathContext;", "toBigInteger", "", "unaryMinus", "xor", "kotlin-stdlib"}, m8859f = "kotlin/NumbersKt", m8857h = 1)
/* renamed from: z1.bwk */
/* loaded from: classes3.dex */
public class BigIntegers extends BigDecimals {
    @cey
    /* renamed from: a */
    private static final BigInteger m8833a(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        cji.m5162f(bigInteger, "$this$plus");
        BigInteger add = bigInteger.add(bigInteger2);
        cji.m5175b(add, "this.add(other)");
        return add;
    }

    @cey
    /* renamed from: b */
    private static final BigInteger m8830b(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        cji.m5162f(bigInteger, "$this$minus");
        BigInteger subtract = bigInteger.subtract(bigInteger2);
        cji.m5175b(subtract, "this.subtract(other)");
        return subtract;
    }

    @cey
    /* renamed from: c */
    private static final BigInteger m8828c(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        cji.m5162f(bigInteger, "$this$times");
        BigInteger multiply = bigInteger.multiply(bigInteger2);
        cji.m5175b(multiply, "this.multiply(other)");
        return multiply;
    }

    @cey
    /* renamed from: d */
    private static final BigInteger m8826d(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        cji.m5162f(bigInteger, "$this$div");
        BigInteger divide = bigInteger.divide(bigInteger2);
        cji.m5175b(divide, "this.divide(other)");
        return divide;
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: e */
    private static final BigInteger m8824e(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        cji.m5162f(bigInteger, "$this$rem");
        BigInteger remainder = bigInteger.remainder(bigInteger2);
        cji.m5175b(remainder, "this.remainder(other)");
        return remainder;
    }

    @cey
    /* renamed from: a */
    private static final BigInteger m8837a(@NotNull BigInteger bigInteger) {
        cji.m5162f(bigInteger, "$this$unaryMinus");
        BigInteger negate = bigInteger.negate();
        cji.m5175b(negate, "this.negate()");
        return negate;
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: b */
    private static final BigInteger m8832b(@NotNull BigInteger bigInteger) {
        cji.m5162f(bigInteger, "$this$inc");
        BigInteger add = bigInteger.add(BigInteger.ONE);
        cji.m5175b(add, "this.add(BigInteger.ONE)");
        return add;
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: c */
    private static final BigInteger m8829c(@NotNull BigInteger bigInteger) {
        cji.m5162f(bigInteger, "$this$dec");
        BigInteger subtract = bigInteger.subtract(BigInteger.ONE);
        cji.m5175b(subtract, "this.subtract(BigInteger.ONE)");
        return subtract;
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: d */
    private static final BigInteger m8827d(@NotNull BigInteger bigInteger) {
        BigInteger not = bigInteger.not();
        cji.m5175b(not, "this.not()");
        return not;
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: f */
    private static final BigInteger m8823f(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger and = bigInteger.and(bigInteger2);
        cji.m5175b(and, "this.and(other)");
        return and;
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: g */
    private static final BigInteger m8822g(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger or = bigInteger.or(bigInteger2);
        cji.m5175b(or, "this.or(other)");
        return or;
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: h */
    private static final BigInteger m8821h(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger xor = bigInteger.xor(bigInteger2);
        cji.m5175b(xor, "this.xor(other)");
        return xor;
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: a */
    private static final BigInteger m8836a(@NotNull BigInteger bigInteger, int i) {
        BigInteger shiftLeft = bigInteger.shiftLeft(i);
        cji.m5175b(shiftLeft, "this.shiftLeft(n)");
        return shiftLeft;
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: b */
    private static final BigInteger m8831b(@NotNull BigInteger bigInteger, int i) {
        BigInteger shiftRight = bigInteger.shiftRight(i);
        cji.m5175b(shiftRight, "this.shiftRight(n)");
        return shiftRight;
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: a */
    private static final BigInteger m8839a(int i) {
        BigInteger valueOf = BigInteger.valueOf(i);
        cji.m5175b(valueOf, "BigInteger.valueOf(this.toLong())");
        return valueOf;
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: a */
    private static final BigInteger m8838a(long j) {
        BigInteger valueOf = BigInteger.valueOf(j);
        cji.m5175b(valueOf, "BigInteger.valueOf(this)");
        return valueOf;
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: e */
    private static final BigDecimal m8825e(@NotNull BigInteger bigInteger) {
        return new BigDecimal(bigInteger);
    }

    /* renamed from: a */
    static /* synthetic */ BigDecimal m8834a(BigInteger bigInteger, int i, MathContext mathContext, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            mathContext = MathContext.UNLIMITED;
            cji.m5175b(mathContext, "MathContext.UNLIMITED");
        }
        return new BigDecimal(bigInteger, i, mathContext);
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: a */
    private static final BigDecimal m8835a(@NotNull BigInteger bigInteger, int i, MathContext mathContext) {
        return new BigDecimal(bigInteger, i, mathContext);
    }
}
