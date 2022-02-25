package p110z1;

import com.stripe.android.CustomerSession;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¨\u0006\b"}, m8860e = {"Lkotlin/internal/jdk7/JDK7PlatformImplementations;", "Lkotlin/internal/PlatformImplementations;", "()V", "addSuppressed", "", "cause", "", CustomerSession.f11798b, "kotlin-stdlib-jdk7"})
/* renamed from: z1.cfk */
/* loaded from: classes3.dex */
public class JDK7PlatformImplementations extends PlatformImplementations {
    @Override // p110z1.PlatformImplementations
    /* renamed from: a */
    public void mo5454a(@NotNull Throwable th, @NotNull Throwable th2) {
        cji.m5162f(th, "cause");
        cji.m5162f(th2, CustomerSession.f11798b);
        th.addSuppressed(th2);
    }
}
