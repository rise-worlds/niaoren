package p110z1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.json.alipay.C3265a;
import org.json.alipay.C3266b;

/* renamed from: z1.ae */
/* loaded from: classes3.dex */
public final class C3382ae {

    /* renamed from: a */
    private static List<AbstractC3490ai> f15423a;

    static {
        ArrayList arrayList = new ArrayList();
        f15423a = arrayList;
        arrayList.add(new C3556ak());
        f15423a.add(new C3328ac());
        f15423a.add(new C3308ab());
        f15423a.add(new C3435ag());
        f15423a.add(new C3299aa());
        f15423a.add(new C5617z());
        f15423a.add(new C3402af());
    }

    /* renamed from: a */
    public static String m14205a(Object obj) {
        if (obj == null) {
            return null;
        }
        Object b = m14204b(obj);
        if (C3639al.m12657a(b.getClass())) {
            return C3266b.m14753c(b.toString());
        }
        if (Collection.class.isAssignableFrom(b.getClass())) {
            return new C3265a((Collection) ((List) b)).toString();
        }
        if (Map.class.isAssignableFrom(b.getClass())) {
            return new C3266b((Map) b).toString();
        }
        throw new IllegalArgumentException("Unsupported Class : " + b.getClass());
    }

    /* renamed from: b */
    public static Object m14204b(Object obj) {
        Object a;
        if (obj == null) {
            return null;
        }
        for (AbstractC3490ai aiVar : f15423a) {
            if (aiVar.mo46a(obj.getClass()) && (a = aiVar.mo45a(obj)) != null) {
                return a;
            }
        }
        throw new IllegalArgumentException("Unsupported Class : " + obj.getClass());
    }
}
