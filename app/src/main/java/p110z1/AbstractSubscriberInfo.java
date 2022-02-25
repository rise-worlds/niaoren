package p110z1;

/* renamed from: z1.czv */
/* loaded from: classes3.dex */
public abstract class AbstractSubscriberInfo implements SubscriberInfo {

    /* renamed from: a */
    private final Class f21203a;

    /* renamed from: b */
    private final Class<? extends SubscriberInfo> f21204b;

    /* renamed from: c */
    private final boolean f21205c;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractSubscriberInfo(Class cls, Class<? extends SubscriberInfo> cls2, boolean z) {
        this.f21203a = cls;
        this.f21204b = cls2;
        this.f21205c = z;
    }

    @Override // p110z1.SubscriberInfo
    /* renamed from: a */
    public Class mo3369a() {
        return this.f21203a;
    }

    @Override // p110z1.SubscriberInfo
    /* renamed from: b */
    public SubscriberInfo mo3368b() {
        Class<? extends SubscriberInfo> cls = this.f21204b;
        if (cls == null) {
            return null;
        }
        try {
            return (SubscriberInfo) cls.newInstance();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // p110z1.SubscriberInfo
    /* renamed from: c */
    public boolean mo3367c() {
        return this.f21205c;
    }

    /* renamed from: a */
    protected SubscriberMethod m3372a(String str, Class<?> cls) {
        return m3370a(str, cls, ThreadMode.POSTING, 0, false);
    }

    /* renamed from: a */
    protected SubscriberMethod m3371a(String str, Class<?> cls, ThreadMode czuVar) {
        return m3370a(str, cls, czuVar, 0, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public SubscriberMethod m3370a(String str, Class<?> cls, ThreadMode czuVar, int i, boolean z) {
        try {
            return new SubscriberMethod(this.f21203a.getDeclaredMethod(str, cls), cls, czuVar, i, z);
        } catch (NoSuchMethodException e) {
            throw new EventBusException("Could not find subscriber method in " + this.f21203a + ". Maybe a missing ProGuard rule?", e);
        }
    }
}
