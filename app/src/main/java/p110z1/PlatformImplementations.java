package p110z1;

import com.stripe.android.CustomerSession;
import java.lang.reflect.Method;
import java.util.regex.MatchResult;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\b\u001a\u00020\tH\u0016J\u001a\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¨\u0006\u0011"}, m8860e = {"Lkotlin/internal/PlatformImplementations;", "", "()V", "addSuppressed", "", "cause", "", CustomerSession.f11798b, "defaultPlatformRandom", "Lkotlin/random/Random;", "getMatchResultNamedGroup", "Lkotlin/text/MatchGroup;", "matchResult", "Ljava/util/regex/MatchResult;", "name", "", "ReflectAddSuppressedMethod", "kotlin-stdlib"})
/* renamed from: z1.cfd */
/* loaded from: classes3.dex */
public class PlatformImplementations {

    /* compiled from: PlatformImplementations.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, m8860e = {"Lkotlin/internal/PlatformImplementations$ReflectAddSuppressedMethod;", "", "()V", AbstractC4442bk.f19102q, "Ljava/lang/reflect/Method;", "kotlin-stdlib"})
    /* renamed from: z1.cfd$a */
    /* loaded from: classes3.dex */
    private static final class C4945a {
        @JvmPlatformAnnotations
        @dbs

        /* renamed from: a */
        public static final Method f20644a;

        /* renamed from: b */
        public static final C4945a f20645b = new C4945a();

        /* JADX WARN: Removed duplicated region for block: B:11:0x0045 A[LOOP:0: B:3:0x0015->B:11:0x0045, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:15:0x0049 A[EDGE_INSN: B:15:0x0049->B:13:0x0049 ?: BREAK  , SYNTHETIC] */
        static {
            /*
                z1.cfd$a r0 = new z1.cfd$a
                r0.<init>()
                p110z1.PlatformImplementations.C4945a.f20645b = r0
                java.lang.Class<java.lang.Throwable> r0 = java.lang.Throwable.class
                java.lang.reflect.Method[] r1 = r0.getMethods()
                java.lang.String r2 = "throwableClass.methods"
                p110z1.cji.m5175b(r1, r2)
                int r2 = r1.length
                r3 = 0
                r4 = 0
            L_0x0015:
                if (r4 >= r2) goto L_0x0048
                r5 = r1[r4]
                java.lang.String r6 = "it"
                p110z1.cji.m5175b(r5, r6)
                java.lang.String r6 = r5.getName()
                java.lang.String r7 = "addSuppressed"
                boolean r6 = p110z1.cji.m5184a(r6, r7)
                if (r6 == 0) goto L_0x0041
                java.lang.Class[] r6 = r5.getParameterTypes()
                java.lang.String r7 = "it.parameterTypes"
                p110z1.cji.m5175b(r6, r7)
                java.lang.Object r6 = p110z1.bzb.m7103k(r6)
                java.lang.Class r6 = (java.lang.Class) r6
                boolean r6 = p110z1.cji.m5184a(r6, r0)
                if (r6 == 0) goto L_0x0041
                r6 = 1
                goto L_0x0042
            L_0x0041:
                r6 = 0
            L_0x0042:
                if (r6 == 0) goto L_0x0045
                goto L_0x0049
            L_0x0045:
                int r4 = r4 + 1
                goto L_0x0015
            L_0x0048:
                r5 = 0
            L_0x0049:
                p110z1.PlatformImplementations.C4945a.f20644a = r5
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p110z1.PlatformImplementations.C4945a.<clinit>():void");
        }

        private C4945a() {
        }
    }

    /* renamed from: a */
    public void mo5454a(@NotNull Throwable th, @NotNull Throwable th2) {
        cji.m5162f(th, "cause");
        cji.m5162f(th2, CustomerSession.f11798b);
        Method method = C4945a.f20644a;
        if (method != null) {
            method.invoke(th, th2);
        }
    }

    @dbs
    /* renamed from: a */
    public cpc m5473a(@NotNull MatchResult matchResult, @NotNull String str) {
        cji.m5162f(matchResult, "matchResult");
        cji.m5162f(str, "name");
        throw new UnsupportedOperationException("Retrieving groups by name is not supported on this platform.");
    }

    @NotNull
    /* renamed from: a */
    public Random m5474a() {
        return new clm();
    }
}
