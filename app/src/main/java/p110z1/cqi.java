package p110z1;

import java.util.concurrent.TimeUnit;

/* compiled from: Duration.kt */
@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001f\u0010%\u001a\u00020\u0007*\u00020\b2\u0006\u0010&\u001a\u00020\u0007H\u0087\nø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001a\u001f\u0010%\u001a\u00020\u0007*\u00020\r2\u0006\u0010&\u001a\u00020\u0007H\u0087\nø\u0001\u0000¢\u0006\u0004\b)\u0010*\u001a \u0010+\u001a\u00020\u0007*\u00020\b2\n\u0010,\u001a\u00060\u0001j\u0002`-H\u0007ø\u0001\u0000¢\u0006\u0002\u0010.\u001a \u0010+\u001a\u00020\u0007*\u00020\r2\n\u0010,\u001a\u00060\u0001j\u0002`-H\u0007ø\u0001\u0000¢\u0006\u0002\u0010/\u001a \u0010+\u001a\u00020\u0007*\u00020\u00102\n\u0010,\u001a\u00060\u0001j\u0002`-H\u0007ø\u0001\u0000¢\u0006\u0002\u00100\"\u001b\u0010\u0000\u001a\u00020\u00018Â\u0002X\u0082\u0004¢\u0006\f\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"!\u0010\u0006\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"!\u0010\u0006\u001a\u00020\u0007*\u00020\r8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\t\u0010\u000e\u001a\u0004\b\u000b\u0010\u000f\"!\u0010\u0006\u001a\u00020\u0007*\u00020\u00108FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\t\u0010\u0011\u001a\u0004\b\u000b\u0010\u0012\"!\u0010\u0013\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0014\u0010\n\u001a\u0004\b\u0015\u0010\f\"!\u0010\u0013\u001a\u00020\u0007*\u00020\r8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0014\u0010\u000e\u001a\u0004\b\u0015\u0010\u000f\"!\u0010\u0013\u001a\u00020\u0007*\u00020\u00108FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0012\"!\u0010\u0016\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0017\u0010\n\u001a\u0004\b\u0018\u0010\f\"!\u0010\u0016\u001a\u00020\u0007*\u00020\r8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0017\u0010\u000e\u001a\u0004\b\u0018\u0010\u000f\"!\u0010\u0016\u001a\u00020\u0007*\u00020\u00108FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0017\u0010\u0011\u001a\u0004\b\u0018\u0010\u0012\"!\u0010\u0019\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001a\u0010\n\u001a\u0004\b\u001b\u0010\f\"!\u0010\u0019\u001a\u00020\u0007*\u00020\r8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001a\u0010\u000e\u001a\u0004\b\u001b\u0010\u000f\"!\u0010\u0019\u001a\u00020\u0007*\u00020\u00108FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001a\u0010\u0011\u001a\u0004\b\u001b\u0010\u0012\"!\u0010\u001c\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001d\u0010\n\u001a\u0004\b\u001e\u0010\f\"!\u0010\u001c\u001a\u00020\u0007*\u00020\r8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001d\u0010\u000e\u001a\u0004\b\u001e\u0010\u000f\"!\u0010\u001c\u001a\u00020\u0007*\u00020\u00108FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u001d\u0010\u0011\u001a\u0004\b\u001e\u0010\u0012\"!\u0010\u001f\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b \u0010\n\u001a\u0004\b!\u0010\f\"!\u0010\u001f\u001a\u00020\u0007*\u00020\r8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b \u0010\u000e\u001a\u0004\b!\u0010\u000f\"!\u0010\u001f\u001a\u00020\u0007*\u00020\u00108FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b \u0010\u0011\u001a\u0004\b!\u0010\u0012\"!\u0010\"\u001a\u00020\u0007*\u00020\b8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b#\u0010\n\u001a\u0004\b$\u0010\f\"!\u0010\"\u001a\u00020\u0007*\u00020\r8FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b#\u0010\u000e\u001a\u0004\b$\u0010\u000f\"!\u0010\"\u001a\u00020\u0007*\u00020\u00108FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b#\u0010\u0011\u001a\u0004\b$\u0010\u0012\u0082\u0002\u0004\n\u0002\b\u0019¨\u00061"}, m8860e = {"storageUnit", "Ljava/util/concurrent/TimeUnit;", "storageUnit$annotations", "()V", "getStorageUnit", "()Ljava/util/concurrent/TimeUnit;", "days", "Lkotlin/time/Duration;", "", "days$annotations", "(D)V", "getDays", "(D)D", "", "(I)V", "(I)D", "", "(J)V", "(J)D", "hours", "hours$annotations", "getHours", "microseconds", "microseconds$annotations", "getMicroseconds", "milliseconds", "milliseconds$annotations", "getMilliseconds", "minutes", "minutes$annotations", "getMinutes", "nanoseconds", "nanoseconds$annotations", "getNanoseconds", "seconds", "seconds$annotations", "getSeconds", "times", "duration", "times-kIfJnKk", "(DD)D", "times-mvk6XK0", "(ID)D", "toDuration", "unit", "Lkotlin/time/DurationUnit;", "(DLjava/util/concurrent/TimeUnit;)D", "(ILjava/util/concurrent/TimeUnit;)D", "(JLjava/util/concurrent/TimeUnit;)D", "kotlin-stdlib"})
/* renamed from: z1.cqi */
/* loaded from: classes3.dex */
public final class cqi {
    /* renamed from: a */
    public static final /* synthetic */ TimeUnit m3600a() {
        return m3587c();
    }

    @bwy(m8750a = "1.3")
    @ExperimentalTime
    /* renamed from: a */
    public static /* synthetic */ void m3599a(double d) {
    }

    @bwy(m8750a = "1.3")
    @ExperimentalTime
    /* renamed from: a */
    public static /* synthetic */ void m3596a(int i) {
    }

    @bwy(m8750a = "1.3")
    @ExperimentalTime
    /* renamed from: a */
    public static /* synthetic */ void m3593a(long j) {
    }

    /* renamed from: b */
    private static /* synthetic */ void m3591b() {
    }

    @bwy(m8750a = "1.3")
    @ExperimentalTime
    /* renamed from: c */
    public static /* synthetic */ void m3586c(double d) {
    }

    @bwy(m8750a = "1.3")
    @ExperimentalTime
    /* renamed from: c */
    public static /* synthetic */ void m3585c(int i) {
    }

    @bwy(m8750a = "1.3")
    @ExperimentalTime
    /* renamed from: c */
    public static /* synthetic */ void m3584c(long j) {
    }

    @bwy(m8750a = "1.3")
    @ExperimentalTime
    /* renamed from: e */
    public static /* synthetic */ void m3580e(double d) {
    }

    @bwy(m8750a = "1.3")
    @ExperimentalTime
    /* renamed from: e */
    public static /* synthetic */ void m3579e(int i) {
    }

    @bwy(m8750a = "1.3")
    @ExperimentalTime
    /* renamed from: e */
    public static /* synthetic */ void m3578e(long j) {
    }

    @bwy(m8750a = "1.3")
    @ExperimentalTime
    /* renamed from: g */
    public static /* synthetic */ void m3574g(double d) {
    }

    @bwy(m8750a = "1.3")
    @ExperimentalTime
    /* renamed from: g */
    public static /* synthetic */ void m3573g(int i) {
    }

    @bwy(m8750a = "1.3")
    @ExperimentalTime
    /* renamed from: g */
    public static /* synthetic */ void m3572g(long j) {
    }

    @bwy(m8750a = "1.3")
    @ExperimentalTime
    /* renamed from: i */
    public static /* synthetic */ void m3568i(double d) {
    }

    @bwy(m8750a = "1.3")
    @ExperimentalTime
    /* renamed from: i */
    public static /* synthetic */ void m3567i(int i) {
    }

    @bwy(m8750a = "1.3")
    @ExperimentalTime
    /* renamed from: i */
    public static /* synthetic */ void m3566i(long j) {
    }

    @bwy(m8750a = "1.3")
    @ExperimentalTime
    /* renamed from: k */
    public static /* synthetic */ void m3562k(double d) {
    }

    @bwy(m8750a = "1.3")
    @ExperimentalTime
    /* renamed from: k */
    public static /* synthetic */ void m3561k(int i) {
    }

    @bwy(m8750a = "1.3")
    @ExperimentalTime
    /* renamed from: k */
    public static /* synthetic */ void m3560k(long j) {
    }

    @bwy(m8750a = "1.3")
    @ExperimentalTime
    /* renamed from: m */
    public static /* synthetic */ void m3556m(double d) {
    }

    @bwy(m8750a = "1.3")
    @ExperimentalTime
    /* renamed from: m */
    public static /* synthetic */ void m3555m(int i) {
    }

    @bwy(m8750a = "1.3")
    @ExperimentalTime
    /* renamed from: m */
    public static /* synthetic */ void m3554m(long j) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public static final TimeUnit m3587c() {
        return TimeUnit.NANOSECONDS;
    }

    @bwy(m8750a = "1.3")
    @ExperimentalTime
    /* renamed from: a */
    public static final double m3594a(int i, @NotNull TimeUnit timeUnit) {
        cji.m5162f(timeUnit, "unit");
        return m3597a(i, timeUnit);
    }

    @bwy(m8750a = "1.3")
    @ExperimentalTime
    /* renamed from: a */
    public static final double m3592a(long j, @NotNull TimeUnit timeUnit) {
        cji.m5162f(timeUnit, "unit");
        return m3597a(j, timeUnit);
    }

    /* renamed from: b */
    public static final double m3589b(int i) {
        return m3594a(i, TimeUnit.NANOSECONDS);
    }

    /* renamed from: b */
    public static final double m3588b(long j) {
        return m3592a(j, TimeUnit.NANOSECONDS);
    }

    /* renamed from: b */
    public static final double m3590b(double d) {
        return m3597a(d, TimeUnit.NANOSECONDS);
    }

    /* renamed from: d */
    public static final double m3582d(int i) {
        return m3594a(i, TimeUnit.MICROSECONDS);
    }

    /* renamed from: d */
    public static final double m3581d(long j) {
        return m3592a(j, TimeUnit.MICROSECONDS);
    }

    /* renamed from: d */
    public static final double m3583d(double d) {
        return m3597a(d, TimeUnit.MICROSECONDS);
    }

    /* renamed from: f */
    public static final double m3576f(int i) {
        return m3594a(i, TimeUnit.MILLISECONDS);
    }

    /* renamed from: f */
    public static final double m3575f(long j) {
        return m3592a(j, TimeUnit.MILLISECONDS);
    }

    /* renamed from: f */
    public static final double m3577f(double d) {
        return m3597a(d, TimeUnit.MILLISECONDS);
    }

    /* renamed from: h */
    public static final double m3570h(int i) {
        return m3594a(i, TimeUnit.SECONDS);
    }

    /* renamed from: h */
    public static final double m3569h(long j) {
        return m3592a(j, TimeUnit.SECONDS);
    }

    /* renamed from: h */
    public static final double m3571h(double d) {
        return m3597a(d, TimeUnit.SECONDS);
    }

    /* renamed from: j */
    public static final double m3564j(int i) {
        return m3594a(i, TimeUnit.MINUTES);
    }

    /* renamed from: j */
    public static final double m3563j(long j) {
        return m3592a(j, TimeUnit.MINUTES);
    }

    /* renamed from: j */
    public static final double m3565j(double d) {
        return m3597a(d, TimeUnit.MINUTES);
    }

    /* renamed from: l */
    public static final double m3558l(int i) {
        return m3594a(i, TimeUnit.HOURS);
    }

    /* renamed from: l */
    public static final double m3557l(long j) {
        return m3592a(j, TimeUnit.HOURS);
    }

    /* renamed from: l */
    public static final double m3559l(double d) {
        return m3597a(d, TimeUnit.HOURS);
    }

    /* renamed from: n */
    public static final double m3552n(int i) {
        return m3594a(i, TimeUnit.DAYS);
    }

    /* renamed from: n */
    public static final double m3551n(long j) {
        return m3592a(j, TimeUnit.DAYS);
    }

    /* renamed from: n */
    public static final double m3553n(double d) {
        return m3597a(d, TimeUnit.DAYS);
    }

    @bwy(m8750a = "1.3")
    @cey
    @ExperimentalTime
    /* renamed from: a */
    private static final double m3595a(int i, double d) {
        return Duration.m3652a(d, i);
    }

    @bwy(m8750a = "1.3")
    @cey
    @ExperimentalTime
    /* renamed from: a */
    private static final double m3598a(double d, double d2) {
        return Duration.m3636c(d2, d);
    }

    @bwy(m8750a = "1.3")
    @ExperimentalTime
    /* renamed from: a */
    public static final double m3597a(double d, @NotNull TimeUnit timeUnit) {
        cji.m5162f(timeUnit, "unit");
        return Duration.m3606w(cqj.m3549a(d, timeUnit, TimeUnit.NANOSECONDS));
    }
}
