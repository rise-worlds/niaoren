package p110z1;

import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.k */
/* loaded from: classes3.dex */
public class Warehouse {

    /* renamed from: a */
    static Map<String, Class<? extends IRouteGroup>> f22213a = new HashMap();

    /* renamed from: b */
    static Map<String, RouteMeta> f22214b = new HashMap();

    /* renamed from: c */
    static Map<Class, IProvider> f22215c = new HashMap();

    /* renamed from: d */
    static Map<String, RouteMeta> f22216d = new HashMap();

    /* renamed from: e */
    static Map<Integer, Class<? extends IInterceptor>> f22217e = new UniqueKeyTreeMap("More than one interceptors use same priority [%s]");

    /* renamed from: f */
    static List<IInterceptor> f22218f = new ArrayList();

    Warehouse() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m2228a() {
        f22214b.clear();
        f22213a.clear();
        f22215c.clear();
        f22216d.clear();
        f22218f.clear();
        f22217e.clear();
    }
}
