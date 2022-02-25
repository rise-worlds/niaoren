package p110z1;

import java.util.concurrent.TimeUnit;
import org.apache.http.cookie.ClientCookie;
import org.apache.tools.ant.types.selectors.SizeSelector;

@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000$\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u00052\n\u0010\u0006\u001a\u00060\u0004j\u0002`\u0005H\u0001*\u001e\b\u0007\u0010\u0007\"\u00020\u00042\u00020\u0004B\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\nB\u0002\b\u000b¨\u0006\f"}, m8860e = {"convertDurationUnit", "", SizeSelector.SIZE_KEY, "sourceUnit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "targetUnit", "DurationUnit", "Lkotlin/SinceKotlin;", ClientCookie.VERSION_ATTR, "1.3", "Lkotlin/time/ExperimentalTime;", "kotlin-stdlib"}, m8859f = "kotlin/time/DurationUnitKt", m8857h = 1)
/* renamed from: z1.cql */
/* loaded from: classes3.dex */
class DurationUnitJvm {
    @bwy(m8750a = "1.3")
    @ExperimentalTime
    /* renamed from: a */
    public static /* synthetic */ void m3550a() {
    }

    @bwy(m8750a = "1.3")
    @ExperimentalTime
    /* renamed from: a */
    public static final double m3549a(double d, @NotNull TimeUnit timeUnit, @NotNull TimeUnit timeUnit2) {
        cji.m5162f(timeUnit, "sourceUnit");
        cji.m5162f(timeUnit2, "targetUnit");
        long convert = timeUnit2.convert(1L, timeUnit);
        return convert > 0 ? d * convert : d / timeUnit.convert(1L, timeUnit2);
    }
}
