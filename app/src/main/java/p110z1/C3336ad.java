package p110z1;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.json.alipay.C3265a;
import org.json.alipay.C3266b;

/* renamed from: z1.ad */
/* loaded from: classes3.dex */
public final class C3336ad {

    /* renamed from: a */
    static List<AbstractC3469ah> f15281a;

    static {
        ArrayList arrayList = new ArrayList();
        f15281a = arrayList;
        arrayList.add(new C3556ak());
        f15281a.add(new C3328ac());
        f15281a.add(new C3308ab());
        f15281a.add(new C3435ag());
        f15281a.add(new C3521aj());
        f15281a.add(new C3299aa());
        f15281a.add(new C5617z());
        f15281a.add(new C3402af());
    }

    /* renamed from: a */
    public static final <T> T m14325a(Object obj, Type type) {
        T t;
        for (AbstractC3469ah ahVar : f15281a) {
            if (ahVar.mo46a(C3639al.m12656a(type)) && (t = (T) ahVar.mo44a(obj, type)) != null) {
                return t;
            }
        }
        return null;
    }

    /* renamed from: a */
    public static final Object m14324a(String str, Type type) {
        Object bVar;
        if (str == null || str.length() == 0) {
            return null;
        }
        String trim = str.trim();
        if (trim.startsWith("[") && trim.endsWith("]")) {
            bVar = new C3265a(trim);
        } else if (!trim.startsWith("{") || !trim.endsWith(C4963cj.f20747d)) {
            return m14325a((Object) trim, type);
        } else {
            bVar = new C3266b(trim);
        }
        return m14325a(bVar, type);
    }
}
