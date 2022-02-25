package p110z1;

import retrofit2.Response;

/* compiled from: Result.java */
/* renamed from: z1.wz */
/* loaded from: classes3.dex */
public final class C5588wz<T> {

    /* renamed from: a */
    private final Response<T> f23650a;

    /* renamed from: b */
    private final Throwable f23651b;

    /* renamed from: a */
    public static <T> C5588wz<T> m138a(Throwable th) {
        if (th != null) {
            return new C5588wz<>(null, th);
        }
        throw new NullPointerException("error == null");
    }

    /* renamed from: a */
    public static <T> C5588wz<T> m137a(Response<T> response) {
        if (response != null) {
            return new C5588wz<>(response, null);
        }
        throw new NullPointerException("response == null");
    }

    private C5588wz(Response<T> response, Throwable th) {
        this.f23650a = response;
        this.f23651b = th;
    }

    /* renamed from: a */
    public Response<T> m139a() {
        return this.f23650a;
    }

    /* renamed from: b */
    public Throwable m136b() {
        return this.f23651b;
    }

    /* renamed from: c */
    public boolean m135c() {
        return this.f23651b != null;
    }
}
