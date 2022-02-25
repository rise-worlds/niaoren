package org.greenrobot.eventbus.util;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;

/* renamed from: org.greenrobot.eventbus.util.d */
/* loaded from: classes2.dex */
public class ExceptionToResourceMapping {

    /* renamed from: a */
    public final Map<Class<? extends Throwable>, Integer> f14827a = new HashMap();

    /* renamed from: a */
    public Integer m14779a(Throwable th) {
        Throwable th2 = th;
        int i = 20;
        do {
            Integer b = m14778b(th2);
            if (b == null) {
                th2 = th2.getCause();
                i--;
                if (i <= 0 || th2 == th) {
                    break;
                }
            } else {
                return b;
            }
        } while (th2 != null);
        Log.d("EventBus", "No specific message resource ID found for " + th);
        return null;
    }

    /* renamed from: b */
    protected Integer m14778b(Throwable th) {
        Class<?> cls = th.getClass();
        Integer num = this.f14827a.get(cls);
        if (num == null) {
            Class<? extends Throwable> cls2 = null;
            for (Map.Entry<Class<? extends Throwable>, Integer> entry : this.f14827a.entrySet()) {
                Class<? extends Throwable> key = entry.getKey();
                if (key.isAssignableFrom(cls) && (cls2 == null || cls2.isAssignableFrom(key))) {
                    num = entry.getValue();
                    cls2 = key;
                }
            }
        }
        return num;
    }

    /* renamed from: a */
    public ExceptionToResourceMapping m14780a(Class<? extends Throwable> cls, int i) {
        this.f14827a.put(cls, Integer.valueOf(i));
        return this;
    }
}
