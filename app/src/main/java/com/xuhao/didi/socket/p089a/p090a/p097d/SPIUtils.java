package com.xuhao.didi.socket.p089a.p090a.p097d;

import com.xuhao.didi.p082a.p088e.SLog;
import java.util.Iterator;
import java.util.ServiceLoader;

/* renamed from: com.xuhao.didi.socket.a.a.d.a */
/* loaded from: classes2.dex */
public class SPIUtils {
    /* renamed from: a */
    public static <E> E m15143a(Class<E> cls) {
        if (cls == null) {
            SLog.m15176a("load null clz error!");
            return null;
        }
        Iterator it = ServiceLoader.load(cls, cls.getClassLoader()).iterator();
        try {
            if (it.hasNext()) {
                return (E) it.next();
            }
        } catch (Throwable th) {
            SLog.m15176a("load " + cls.getSimpleName() + " error! " + th.getMessage());
        }
        return null;
    }
}
