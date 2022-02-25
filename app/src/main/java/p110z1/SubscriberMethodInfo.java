package p110z1;

/* renamed from: z1.czz */
/* loaded from: classes3.dex */
public class SubscriberMethodInfo {

    /* renamed from: a */
    final String f21207a;

    /* renamed from: b */
    final ThreadMode f21208b;

    /* renamed from: c */
    final Class<?> f21209c;

    /* renamed from: d */
    final int f21210d;

    /* renamed from: e */
    final boolean f21211e;

    public SubscriberMethodInfo(String str, Class<?> cls, ThreadMode czuVar, int i, boolean z) {
        this.f21207a = str;
        this.f21208b = czuVar;
        this.f21209c = cls;
        this.f21210d = i;
        this.f21211e = z;
    }

    public SubscriberMethodInfo(String str, Class<?> cls) {
        this(str, cls, ThreadMode.POSTING, 0, false);
    }

    public SubscriberMethodInfo(String str, Class<?> cls, ThreadMode czuVar) {
        this(str, cls, czuVar, 0, false);
    }
}
