package p110z1;

/* renamed from: z1.czw */
/* loaded from: classes3.dex */
public class SimpleSubscriberInfo extends AbstractSubscriberInfo {

    /* renamed from: a */
    private final SubscriberMethodInfo[] f21206a;

    public SimpleSubscriberInfo(Class cls, boolean z, SubscriberMethodInfo[] czzVarArr) {
        super(cls, null, z);
        this.f21206a = czzVarArr;
    }

    @Override // p110z1.SubscriberInfo
    /* renamed from: d */
    public synchronized SubscriberMethod[] mo3366d() {
        SubscriberMethod[] czrVarArr;
        int length = this.f21206a.length;
        czrVarArr = new SubscriberMethod[length];
        for (int i = 0; i < length; i++) {
            SubscriberMethodInfo czzVar = this.f21206a[i];
            czrVarArr[i] = m3370a(czzVar.f21207a, czzVar.f21209c, czzVar.f21208b, czzVar.f21210d, czzVar.f21211e);
        }
        return czrVarArr;
    }
}
