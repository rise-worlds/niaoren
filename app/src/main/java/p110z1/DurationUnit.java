package p110z1;

import com.cyjh.mobileanjian.ipc.utils.RpcError;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u0001*\u00060\u0002j\u0002`\u0003H\u0001¨\u0006\u0004"}, m8860e = {"shortName", "", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "kotlin-stdlib"}, m8859f = "kotlin/time/DurationUnitKt", m8857h = 1)
/* renamed from: z1.cqm */
/* loaded from: classes3.dex */
public class DurationUnit extends DurationUnitJvm {
    @bwy(m8750a = "1.3")
    @ExperimentalTime
    @NotNull
    /* renamed from: a */
    public static final String m3548a(@NotNull TimeUnit timeUnit) {
        cji.m5162f(timeUnit, "$this$shortName");
        switch (cqk.f21089a[timeUnit.ordinal()]) {
            case 1:
                return "ns";
            case 2:
                return "us";
            case 3:
                return "ms";
            case 4:
                return "s";
            case 5:
                return RpcError.f8691a;
            case 6:
                return "h";
            case 7:
                return "d";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
