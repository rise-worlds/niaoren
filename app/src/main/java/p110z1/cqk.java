package p110z1;

import java.util.concurrent.TimeUnit;

@Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3})
/* renamed from: z1.cqk */
/* loaded from: classes3.dex */
public final /* synthetic */ class cqk {

    /* renamed from: a */
    public static final /* synthetic */ int[] f21089a = new int[TimeUnit.values().length];

    static {
        f21089a[TimeUnit.NANOSECONDS.ordinal()] = 1;
        f21089a[TimeUnit.MICROSECONDS.ordinal()] = 2;
        f21089a[TimeUnit.MILLISECONDS.ordinal()] = 3;
        f21089a[TimeUnit.SECONDS.ordinal()] = 4;
        f21089a[TimeUnit.MINUTES.ordinal()] = 5;
        f21089a[TimeUnit.HOURS.ordinal()] = 6;
        f21089a[TimeUnit.DAYS.ordinal()] = 7;
    }
}
