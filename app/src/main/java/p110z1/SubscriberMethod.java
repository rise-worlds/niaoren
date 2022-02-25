package p110z1;

import java.lang.reflect.Method;

/* renamed from: z1.czr */
/* loaded from: classes3.dex */
public class SubscriberMethod {

    /* renamed from: a */
    final Method f21177a;

    /* renamed from: b */
    final ThreadMode f21178b;

    /* renamed from: c */
    final Class<?> f21179c;

    /* renamed from: d */
    final int f21180d;

    /* renamed from: e */
    final boolean f21181e;

    /* renamed from: f */
    String f21182f;

    public SubscriberMethod(Method method, Class<?> cls, ThreadMode czuVar, int i, boolean z) {
        this.f21177a = method;
        this.f21178b = czuVar;
        this.f21179c = cls;
        this.f21180d = i;
        this.f21181e = z;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SubscriberMethod)) {
            return false;
        }
        m3386a();
        SubscriberMethod czrVar = (SubscriberMethod) obj;
        czrVar.m3386a();
        return this.f21182f.equals(czrVar.f21182f);
    }

    /* renamed from: a */
    private synchronized void m3386a() {
        if (this.f21182f == null) {
            StringBuilder sb = new StringBuilder(64);
            sb.append(this.f21177a.getDeclaringClass().getName());
            sb.append('#');
            sb.append(this.f21177a.getName());
            sb.append('(');
            sb.append(this.f21179c.getName());
            this.f21182f = sb.toString();
        }
    }

    public int hashCode() {
        return this.f21177a.hashCode();
    }
}
