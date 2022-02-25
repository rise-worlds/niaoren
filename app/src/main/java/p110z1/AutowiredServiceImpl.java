package p110z1;

import android.content.Context;
import android.util.LruCache;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.AutowiredService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import java.util.ArrayList;
import java.util.List;

@Route(path = "/arouter/service/autowired")
/* renamed from: z1.g */
/* loaded from: classes3.dex */
public class AutowiredServiceImpl implements AutowiredService {

    /* renamed from: a */
    private LruCache<String, ISyringe> f21746a;

    /* renamed from: b */
    private List<String> f21747b;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
        this.f21746a = new LruCache<>(66);
        this.f21747b = new ArrayList();
    }

    @Override // com.alibaba.android.arouter.facade.service.AutowiredService
    public void autowire(Object obj) {
        String name = obj.getClass().getName();
        try {
            if (!this.f21747b.contains(name)) {
                ISyringe iSyringe = this.f21746a.get(name);
                if (iSyringe == null) {
                    iSyringe = (ISyringe) Class.forName(obj.getClass().getName() + Consts.f23429g).getConstructor(new Class[0]).newInstance(new Object[0]);
                }
                iSyringe.inject(obj);
                this.f21746a.put(name, iSyringe);
            }
        } catch (Exception unused) {
            this.f21747b.add(name);
        }
    }
}
