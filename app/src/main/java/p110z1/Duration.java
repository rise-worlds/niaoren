package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.concurrent.TimeUnit;
import org.apache.commons.p105io.FilenameUtils;
import org.apache.tools.ant.types.selectors.SizeSelector;

@bwy(m8750a = "1.3")
@ExperimentalTime
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b&\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0087@\u0018\u0000 s2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001sB\u0014\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010%\u001a\u00020\t2\u0006\u0010&\u001a\u00020\u0000H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b'\u0010(J\u001b\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\u0003H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b+\u0010,J\u001b\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\tH\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b+\u0010-J\u001b\u0010)\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b.\u0010,J\u0013\u0010/\u001a\u0002002\b\u0010&\u001a\u0004\u0018\u000101HÖ\u0003J\t\u00102\u001a\u00020\tHÖ\u0001J\r\u00103\u001a\u000200¢\u0006\u0004\b4\u00105J\r\u00106\u001a\u000200¢\u0006\u0004\b7\u00105J\r\u00108\u001a\u000200¢\u0006\u0004\b9\u00105J\r\u0010:\u001a\u000200¢\u0006\u0004\b;\u00105J\u001b\u0010<\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b=\u0010,J\u001b\u0010>\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b?\u0010,J\u0017\u0010@\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¢\u0006\u0004\bA\u0010(J\u001b\u0010B\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\u0003H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\bC\u0010,J\u001b\u0010B\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\tH\u0086\u0002ø\u0001\u0000¢\u0006\u0004\bC\u0010-J\u008d\u0001\u0010D\u001a\u0002HE\"\u0004\b\u0000\u0010E2u\u0010F\u001aq\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(J\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(K\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(L\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(N\u0012\u0004\u0012\u0002HE0GH\u0086\b¢\u0006\u0004\bO\u0010PJx\u0010D\u001a\u0002HE\"\u0004\b\u0000\u0010E2`\u0010F\u001a\\\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(K\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(L\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(N\u0012\u0004\u0012\u0002HE0QH\u0086\b¢\u0006\u0004\bO\u0010RJc\u0010D\u001a\u0002HE\"\u0004\b\u0000\u0010E2K\u0010F\u001aG\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(L\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(N\u0012\u0004\u0012\u0002HE0SH\u0086\b¢\u0006\u0004\bO\u0010TJN\u0010D\u001a\u0002HE\"\u0004\b\u0000\u0010E26\u0010F\u001a2\u0012\u0013\u0012\u00110V¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(N\u0012\u0004\u0012\u0002HE0UH\u0086\b¢\u0006\u0004\bO\u0010WJ\u0019\u0010X\u001a\u00020\u00032\n\u0010Y\u001a\u00060Zj\u0002`[¢\u0006\u0004\b\\\u0010]J\u0019\u0010^\u001a\u00020\t2\n\u0010Y\u001a\u00060Zj\u0002`[¢\u0006\u0004\b_\u0010`J\r\u0010a\u001a\u00020b¢\u0006\u0004\bc\u0010dJ\u0019\u0010e\u001a\u00020V2\n\u0010Y\u001a\u00060Zj\u0002`[¢\u0006\u0004\bf\u0010gJ\r\u0010h\u001a\u00020V¢\u0006\u0004\bi\u0010jJ\r\u0010k\u001a\u00020V¢\u0006\u0004\bl\u0010jJ\u000f\u0010m\u001a\u00020bH\u0016¢\u0006\u0004\bn\u0010dJ#\u0010m\u001a\u00020b2\n\u0010Y\u001a\u00060Zj\u0002`[2\b\b\u0002\u0010o\u001a\u00020\t¢\u0006\u0004\bn\u0010pJ\u0013\u0010q\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\br\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00008Fø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u001a\u0010\b\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0005R\u0011\u0010\u0010\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0005R\u0011\u0010\u0012\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0005R\u0011\u0010\u0014\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0005R\u0011\u0010\u0016\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0005R\u0011\u0010\u0018\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0005R\u0011\u0010\u001a\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0005R\u001a\u0010\u001c\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b\u001d\u0010\u000b\u001a\u0004\b\u001e\u0010\rR\u001a\u0010\u001f\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b \u0010\u000b\u001a\u0004\b!\u0010\rR\u001a\u0010\"\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b#\u0010\u000b\u001a\u0004\b$\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\u0002\n\u0000ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006t"}, m8860e = {"Lkotlin/time/Duration;", "", SizeSelector.SIZE_KEY, "", "constructor-impl", "(D)D", "absoluteValue", "getAbsoluteValue-impl", "hoursComponent", "", "hoursComponent$annotations", "()V", "getHoursComponent-impl", "(D)I", "inDays", "getInDays-impl", "inHours", "getInHours-impl", "inMicroseconds", "getInMicroseconds-impl", "inMilliseconds", "getInMilliseconds-impl", "inMinutes", "getInMinutes-impl", "inNanoseconds", "getInNanoseconds-impl", "inSeconds", "getInSeconds-impl", "minutesComponent", "minutesComponent$annotations", "getMinutesComponent-impl", "nanosecondsComponent", "nanosecondsComponent$annotations", "getNanosecondsComponent-impl", "secondsComponent", "secondsComponent$annotations", "getSecondsComponent-impl", "compareTo", "other", "compareTo-LRDsOJo", "(DD)I", "div", "scale", "div-impl", "(DD)D", "(DI)D", "div-LRDsOJo", "equals", "", "", "hashCode", "isFinite", "isFinite-impl", "(D)Z", "isInfinite", "isInfinite-impl", "isNegative", "isNegative-impl", "isPositive", "isPositive-impl", "minus", "minus-LRDsOJo", "plus", "plus-LRDsOJo", "precision", "precision-impl", "times", "times-impl", "toComponents", TessBaseAPI.f9204e, "action", "Lkotlin/Function5;", "Lkotlin/ParameterName;", "name", "days", "hours", "minutes", "seconds", "nanoseconds", "toComponents-impl", "(DLkotlin/jvm/functions/Function5;)Ljava/lang/Object;", "Lkotlin/Function4;", "(DLkotlin/jvm/functions/Function4;)Ljava/lang/Object;", "Lkotlin/Function3;", "(DLkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "Lkotlin/Function2;", "", "(DLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "toDouble", "unit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "toDouble-impl", "(DLjava/util/concurrent/TimeUnit;)D", "toInt", "toInt-impl", "(DLjava/util/concurrent/TimeUnit;)I", "toIsoString", "", "toIsoString-impl", "(D)Ljava/lang/String;", "toLong", "toLong-impl", "(DLjava/util/concurrent/TimeUnit;)J", "toLongMilliseconds", "toLongMilliseconds-impl", "(D)J", "toLongNanoseconds", "toLongNanoseconds-impl", "toString", "toString-impl", "decimals", "(DLjava/util/concurrent/TimeUnit;I)Ljava/lang/String;", "unaryMinus", "unaryMinus-impl", "Companion", "kotlin-stdlib"})
/* renamed from: z1.cqh */
/* loaded from: classes3.dex */
public final class Duration implements Comparable<Duration> {

    /* renamed from: a */
    public static final C5100a f21085a = new C5100a(null);

    /* renamed from: c */
    private static final double f21086c = m3606w(0.0d);

    /* renamed from: d */
    private static final double f21087d = m3606w(ciw.f20730a.m5222c());

    /* renamed from: b */
    private final double f21088b;

    @bwt
    /* renamed from: a */
    public static /* synthetic */ void m3655a() {
    }

    /* renamed from: a */
    public static boolean m3651a(double d, @dbs Object obj) {
        return (obj instanceof Duration) && Double.compare(d, ((Duration) obj).m3631e()) == 0;
    }

    @bwt
    /* renamed from: b */
    public static /* synthetic */ void m3643b() {
    }

    @bwt
    /* renamed from: c */
    public static /* synthetic */ void m3638c() {
    }

    /* renamed from: c */
    public static final boolean m3637c(double d) {
        return d < ((double) 0);
    }

    @bwt
    /* renamed from: d */
    public static /* synthetic */ void m3634d() {
    }

    /* renamed from: d */
    public static final boolean m3633d(double d) {
        return d > ((double) 0);
    }

    /* renamed from: e */
    public static final double m3629e(double d, double d2) {
        return d / d2;
    }

    /* renamed from: g */
    public static final boolean m3623g(double d, double d2) {
        throw null;
    }

    /* renamed from: h */
    private static final int m3621h(double d, double d2) {
        if (d2 < 1) {
            return 3;
        }
        if (d2 < 10) {
            return 2;
        }
        return d2 < ((double) 100) ? 1 : 0;
    }

    /* renamed from: w */
    public static double m3606w(double d) {
        return d;
    }

    @NotNull
    /* renamed from: x */
    public static final /* synthetic */ Duration m3605x(double d) {
        return new Duration(d);
    }

    /* renamed from: y */
    public static int m3604y(double d) {
        long doubleToLongBits = Double.doubleToLongBits(d);
        return (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
    }

    /* renamed from: a */
    public int m3654a(double d) {
        return m3626f(this.f21088b, d);
    }

    /* renamed from: e */
    public final /* synthetic */ double m3631e() {
        return this.f21088b;
    }

    public boolean equals(Object obj) {
        return m3651a(this.f21088b, obj);
    }

    public int hashCode() {
        return m3604y(this.f21088b);
    }

    @NotNull
    public String toString() {
        return m3608u(this.f21088b);
    }

    private /* synthetic */ Duration(double d) {
        this.f21088b = d;
    }

    @Override // java.lang.Comparable
    public /* synthetic */ int compareTo(Duration cqhVar) {
        return m3654a(cqhVar.m3631e());
    }

    /* compiled from: Duration.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\n\u0010\r\u001a\u00060\u000ej\u0002`\u000f2\n\u0010\u0010\u001a\u00060\u000ej\u0002`\u000fR\u0016\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0016\u0010\b\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, m8860e = {"Lkotlin/time/Duration$Companion;", "", "()V", "INFINITE", "Lkotlin/time/Duration;", "getINFINITE", "()D", "D", "ZERO", "getZERO", "convert", "", SizeSelector.SIZE_KEY, "sourceUnit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "targetUnit", "kotlin-stdlib"})
    /* renamed from: z1.cqh$a */
    /* loaded from: classes3.dex */
    public static final class C5100a {
        private C5100a() {
        }

        public /* synthetic */ C5100a(DefaultConstructorMarker civVar) {
            this();
        }

        /* renamed from: a */
        public final double m3603a() {
            return Duration.f21086c;
        }

        /* renamed from: b */
        public final double m3601b() {
            return Duration.f21087d;
        }

        /* renamed from: a */
        public final double m3602a(double d, @NotNull TimeUnit timeUnit, @NotNull TimeUnit timeUnit2) {
            cji.m5162f(timeUnit, "sourceUnit");
            cji.m5162f(timeUnit2, "targetUnit");
            return cqj.m3549a(d, timeUnit, timeUnit2);
        }
    }

    /* renamed from: b */
    public static final double m3642b(double d) {
        return m3606w(-d);
    }

    /* renamed from: a */
    public static final double m3653a(double d, double d2) {
        return m3606w(d + d2);
    }

    /* renamed from: b */
    public static final double m3641b(double d, double d2) {
        return m3606w(d - d2);
    }

    /* renamed from: a */
    public static final double m3652a(double d, int i) {
        return m3606w(d * i);
    }

    /* renamed from: c */
    public static final double m3636c(double d, double d2) {
        return m3606w(d * d2);
    }

    /* renamed from: b */
    public static final double m3640b(double d, int i) {
        return m3606w(d / i);
    }

    /* renamed from: d */
    public static final double m3632d(double d, double d2) {
        return m3606w(d / d2);
    }

    /* renamed from: e */
    public static final boolean m3630e(double d) {
        return Double.isInfinite(d);
    }

    /* renamed from: f */
    public static final boolean m3627f(double d) {
        return !Double.isInfinite(d) && !Double.isNaN(d);
    }

    /* renamed from: g */
    public static final double m3624g(double d) {
        return m3637c(d) ? m3642b(d) : d;
    }

    /* renamed from: f */
    public static int m3626f(double d, double d2) {
        return Double.compare(d, d2);
    }

    /* renamed from: a */
    public static final <T> T m3644a(double d, @NotNull chu<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> chuVar) {
        cji.m5162f(chuVar, "action");
        return (T) chuVar.invoke(Integer.valueOf((int) m3617l(d)), Integer.valueOf(m3622h(d)), Integer.valueOf(m3620i(d)), Integer.valueOf(m3619j(d)), Integer.valueOf(m3618k(d)));
    }

    /* renamed from: a */
    public static final <T> T m3645a(double d, @NotNull cht<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> chtVar) {
        cji.m5162f(chtVar, "action");
        return (T) chtVar.invoke(Integer.valueOf((int) m3616m(d)), Integer.valueOf(m3620i(d)), Integer.valueOf(m3619j(d)), Integer.valueOf(m3618k(d)));
    }

    /* renamed from: a */
    public static final <T> T m3646a(double d, @NotNull chs<? super Integer, ? super Integer, ? super Integer, ? extends T> chsVar) {
        cji.m5162f(chsVar, "action");
        return (T) chsVar.invoke(Integer.valueOf((int) m3615n(d)), Integer.valueOf(m3619j(d)), Integer.valueOf(m3618k(d)));
    }

    /* renamed from: a */
    public static final <T> T m3647a(double d, @NotNull cho<? super Long, ? super Integer, ? extends T> choVar) {
        cji.m5162f(choVar, "action");
        return (T) choVar.invoke(Long.valueOf((long) m3614o(d)), Integer.valueOf(m3618k(d)));
    }

    /* renamed from: h */
    public static final int m3622h(double d) {
        return (int) (m3616m(d) % 24);
    }

    /* renamed from: i */
    public static final int m3620i(double d) {
        return (int) (m3615n(d) % 60);
    }

    /* renamed from: j */
    public static final int m3619j(double d) {
        return (int) (m3614o(d) % 60);
    }

    /* renamed from: k */
    public static final int m3618k(double d) {
        return (int) (m3611r(d) % 1.0E9d);
    }

    /* renamed from: a */
    public static final double m3650a(double d, @NotNull TimeUnit timeUnit) {
        cji.m5162f(timeUnit, "unit");
        return cqj.m3549a(d, cqi.m3600a(), timeUnit);
    }

    /* renamed from: b */
    public static final long m3639b(double d, @NotNull TimeUnit timeUnit) {
        cji.m5162f(timeUnit, "unit");
        return (long) m3650a(d, timeUnit);
    }

    /* renamed from: c */
    public static final int m3635c(double d, @NotNull TimeUnit timeUnit) {
        cji.m5162f(timeUnit, "unit");
        return (int) m3650a(d, timeUnit);
    }

    /* renamed from: l */
    public static final double m3617l(double d) {
        return m3650a(d, TimeUnit.DAYS);
    }

    /* renamed from: m */
    public static final double m3616m(double d) {
        return m3650a(d, TimeUnit.HOURS);
    }

    /* renamed from: n */
    public static final double m3615n(double d) {
        return m3650a(d, TimeUnit.MINUTES);
    }

    /* renamed from: o */
    public static final double m3614o(double d) {
        return m3650a(d, TimeUnit.SECONDS);
    }

    /* renamed from: p */
    public static final double m3613p(double d) {
        return m3650a(d, TimeUnit.MILLISECONDS);
    }

    /* renamed from: q */
    public static final double m3612q(double d) {
        return m3650a(d, TimeUnit.MICROSECONDS);
    }

    /* renamed from: r */
    public static final double m3611r(double d) {
        return m3650a(d, TimeUnit.NANOSECONDS);
    }

    /* renamed from: s */
    public static final long m3610s(double d) {
        return m3639b(d, TimeUnit.NANOSECONDS);
    }

    /* renamed from: t */
    public static final long m3609t(double d) {
        return m3639b(d, TimeUnit.MILLISECONDS);
    }

    @NotNull
    /* renamed from: u */
    public static String m3608u(double d) {
        int i;
        TimeUnit timeUnit;
        String str;
        if (m3630e(d)) {
            return String.valueOf(d);
        }
        if (d == 0.0d) {
            return "0s";
        }
        double r = m3611r(m3624g(d));
        boolean z = false;
        if (r < 1.0E-6d) {
            timeUnit = TimeUnit.SECONDS;
            i = 0;
            z = true;
        } else if (r < 1) {
            timeUnit = TimeUnit.NANOSECONDS;
            i = 7;
        } else if (r < 1000.0d) {
            timeUnit = TimeUnit.NANOSECONDS;
            i = 0;
        } else if (r < 1000000.0d) {
            timeUnit = TimeUnit.MICROSECONDS;
            i = 0;
        } else if (r < 1.0E9d) {
            timeUnit = TimeUnit.MILLISECONDS;
            i = 0;
        } else if (r < 1.0E12d) {
            timeUnit = TimeUnit.SECONDS;
            i = 0;
        } else if (r < 6.0E13d) {
            timeUnit = TimeUnit.MINUTES;
            i = 0;
        } else if (r < 3.6E15d) {
            timeUnit = TimeUnit.HOURS;
            i = 0;
        } else if (r < 8.64E20d) {
            timeUnit = TimeUnit.DAYS;
            i = 0;
        } else {
            timeUnit = TimeUnit.DAYS;
            i = 0;
            z = true;
        }
        double a = m3650a(d, timeUnit);
        StringBuilder sb = new StringBuilder();
        if (z) {
            str = formatToDecimals.m3547a(a);
        } else if (i > 0) {
            str = formatToDecimals.m3544b(a, i);
        } else {
            str = formatToDecimals.m3546a(a, m3621h(d, Math.abs(a)));
        }
        sb.append(str);
        sb.append(cqj.m3548a(timeUnit));
        return sb.toString();
    }

    /* renamed from: a */
    public static /* synthetic */ String m3648a(double d, TimeUnit timeUnit, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return m3649a(d, timeUnit, i);
    }

    @NotNull
    /* renamed from: a */
    public static final String m3649a(double d, @NotNull TimeUnit timeUnit, int i) {
        cji.m5162f(timeUnit, "unit");
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("decimals must be not negative, but was " + i).toString());
        } else if (m3630e(d)) {
            return String.valueOf(d);
        } else {
            double a = m3650a(d, timeUnit);
            StringBuilder sb = new StringBuilder();
            sb.append(Math.abs(a) < 1.0E14d ? formatToDecimals.m3546a(a, cmi.m4701d(i, 12)) : formatToDecimals.m3547a(a));
            sb.append(cqj.m3548a(timeUnit));
            return sb.toString();
        }
    }

    @NotNull
    /* renamed from: v */
    public static final String m3607v(double d) {
        StringBuilder sb = new StringBuilder();
        if (m3637c(d)) {
            sb.append('-');
        }
        sb.append("PT");
        double g = m3624g(d);
        int m = (int) m3616m(g);
        int i = m3620i(g);
        int j = m3619j(g);
        int k = m3618k(g);
        boolean z = true;
        boolean z2 = m != 0;
        boolean z3 = (j == 0 && k == 0) ? false : true;
        if (i == 0 && (!z3 || !z2)) {
            z = false;
        }
        if (z2) {
            sb.append(m);
            sb.append('H');
        }
        if (z) {
            sb.append(i);
            sb.append('M');
        }
        if (z3 || (!z2 && !z)) {
            sb.append(j);
            if (k != 0) {
                sb.append(FilenameUtils.EXTENSION_SEPARATOR);
                String a = cpl.m3948a(String.valueOf(k), 9, '0');
                if (k % 1000000 == 0) {
                    sb.append((CharSequence) a, 0, 3);
                } else if (k % 1000 == 0) {
                    sb.append((CharSequence) a, 0, 6);
                } else {
                    sb.append(a);
                }
            }
            sb.append('S');
        }
        String sb2 = sb.toString();
        cji.m5175b(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }
}
