package p110z1;

/* renamed from: z1.ask */
/* loaded from: classes3.dex */
public final class Notification<T> {

    /* renamed from: b */
    static final Notification<Object> f17460b = new Notification<>(null);

    /* renamed from: a */
    final Object f17461a;

    private Notification(Object obj) {
        this.f17461a = obj;
    }

    /* renamed from: a */
    public boolean m10644a() {
        return this.f17461a == null;
    }

    /* renamed from: b */
    public boolean m10641b() {
        return NotificationLite.isError(this.f17461a);
    }

    /* renamed from: c */
    public boolean m10640c() {
        Object obj = this.f17461a;
        return obj != null && !NotificationLite.isError(obj);
    }

    @atn
    /* renamed from: d */
    public T m10639d() {
        Object obj = this.f17461a;
        if (obj == null || NotificationLite.isError(obj)) {
            return null;
        }
        return (T) this.f17461a;
    }

    @atn
    /* renamed from: e */
    public Throwable m10638e() {
        Object obj = this.f17461a;
        if (NotificationLite.isError(obj)) {
            return NotificationLite.getError(obj);
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Notification) {
            return ObjectHelper.m9874a(this.f17461a, ((Notification) obj).f17461a);
        }
        return false;
    }

    public int hashCode() {
        Object obj = this.f17461a;
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    public String toString() {
        Object obj = this.f17461a;
        if (obj == null) {
            return "OnCompleteNotification";
        }
        if (NotificationLite.isError(obj)) {
            return "OnErrorNotification[" + NotificationLite.getError(obj) + "]";
        }
        return "OnNextNotification[" + this.f17461a + "]";
    }

    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Notification<T> m10643a(@AbstractC3889atm T t) {
        ObjectHelper.m9873a((Object) t, "value is null");
        return new Notification<>(t);
    }

    @AbstractC3889atm
    /* renamed from: a */
    public static <T> Notification<T> m10642a(@AbstractC3889atm Throwable th) {
        ObjectHelper.m9873a(th, "error is null");
        return new Notification<>(NotificationLite.error(th));
    }

    @AbstractC3889atm
    /* renamed from: f */
    public static <T> Notification<T> m10637f() {
        return (Notification<T>) f17460b;
    }
}
