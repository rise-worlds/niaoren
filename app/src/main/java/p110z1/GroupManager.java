package p110z1;

import com.nrzs.data.ddy.bean.respond.GroupInfo;
import com.nrzs.data.ddy.bean.respond.OrderDaileInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: z1.ajp */
/* loaded from: classes3.dex */
public class GroupManager {

    /* renamed from: a */
    private Map<Long, List<OrderDaileInfo>> f16074a = new HashMap();

    /* renamed from: b */
    private List<GroupInfo> f16075b = new ArrayList();

    /* renamed from: c */
    private List<OrderDaileInfo> f16076c = new ArrayList();

    /* compiled from: GroupManager.java */
    /* renamed from: z1.ajp$a */
    /* loaded from: classes3.dex */
    private static class C3527a {

        /* renamed from: a */
        private static final GroupManager f16077a = new GroupManager();

        private C3527a() {
        }
    }

    /* renamed from: a */
    public static GroupManager m12887a() {
        return C3527a.f16077a;
    }

    /* renamed from: b */
    public Map<Long, List<OrderDaileInfo>> m12883b() {
        return this.f16074a;
    }

    /* renamed from: a */
    public void m12884a(Map<Long, List<OrderDaileInfo>> map) {
        this.f16074a = map;
    }

    /* renamed from: c */
    public List<GroupInfo> m12881c() {
        return this.f16075b;
    }

    /* renamed from: a */
    public void m12885a(List<GroupInfo> list) {
        this.f16075b = list;
    }

    /* renamed from: d */
    public List<OrderDaileInfo> m12880d() {
        return this.f16076c;
    }

    /* renamed from: b */
    public void m12882b(List<OrderDaileInfo> list) {
        this.f16076c = list;
    }

    /* renamed from: a */
    public void m12886a(long j, String str) {
        for (int i = 0; i < this.f16076c.size(); i++) {
            if (this.f16076c.get(i).f10629Id == j) {
                this.f16076c.get(i).DeviceName = str;
            }
        }
    }
}
