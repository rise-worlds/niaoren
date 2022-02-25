package com.p018b.p019a.p020a.p027g;

import com.p018b.p019a.p020a.Util;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/* compiled from: JdkWithJettyBootPlatform.java */
/* renamed from: com.b.a.a.g.f */
/* loaded from: classes.dex */
final class C0893f implements InvocationHandler {

    /* renamed from: a */
    boolean f6037a;

    /* renamed from: b */
    String f6038b;

    /* renamed from: c */
    private final List<String> f6039c;

    public C0893f(List<String> list) {
        this.f6039c = list;
    }

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        String name = method.getName();
        Class<?> returnType = method.getReturnType();
        if (objArr == null) {
            objArr = Util.f5778b;
        }
        if (name.equals("supports") && Boolean.TYPE == returnType) {
            return true;
        }
        if (name.equals("unsupported") && Void.TYPE == returnType) {
            this.f6037a = true;
            return null;
        } else if (name.equals("protocols") && objArr.length == 0) {
            return this.f6039c;
        } else {
            if ((name.equals("selectProtocol") || name.equals("select")) && String.class == returnType && objArr.length == 1 && (objArr[0] instanceof List)) {
                List list = (List) objArr[0];
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    if (this.f6039c.contains(list.get(i))) {
                        String str = (String) list.get(i);
                        this.f6038b = str;
                        return str;
                    }
                }
                String str2 = this.f6039c.get(0);
                this.f6038b = str2;
                return str2;
            } else if ((!name.equals("protocolSelected") && !name.equals("selected")) || objArr.length != 1) {
                return method.invoke(this, objArr);
            } else {
                this.f6038b = (String) objArr[0];
                return null;
            }
        }
    }
}
