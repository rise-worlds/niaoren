package com.tendcloud.tenddata;

import java.lang.reflect.Method;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ez */
/* loaded from: classes2.dex */
final class C2863ez {

    /* renamed from: a */
    private final Object f13931a;

    /* renamed from: b */
    private final Method f13932b;

    /* renamed from: c */
    private final int f13933c;

    /* renamed from: d */
    private boolean f13934d = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2863ez(Object obj, Method method) {
        if (obj == null) {
            throw new NullPointerException("EventHandler target cannot be null.");
        } else if (method != null) {
            this.f13931a = obj;
            this.f13932b = method;
            method.setAccessible(true);
            this.f13933c = ((method.hashCode() + 31) * 31) + obj.hashCode();
        } else {
            throw new NullPointerException("EventHandler method cannot be null.");
        }
    }

    /* renamed from: a */
    public boolean m15769a() {
        return this.f13934d;
    }

    /* renamed from: b */
    public void m15768b() {
        this.f13934d = false;
    }

    public void handleEvent(Object obj) {
        if (!this.f13934d) {
            C2811dq.eForInternal(toString() + " has been invalidated and can no longer handle events.");
        }
        try {
            this.f13932b.invoke(this.f13931a, obj);
        } catch (Throwable unused) {
        }
    }

    public String toString() {
        return "[EventHandler " + this.f13932b + "]";
    }

    public int hashCode() {
        return this.f13933c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        try {
            if (getClass() != obj.getClass()) {
                return false;
            }
            C2863ez ezVar = (C2863ez) obj;
            if (this.f13932b.equals(ezVar.f13932b)) {
                if (this.f13931a == ezVar.f13931a) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return false;
        }
    }
}
