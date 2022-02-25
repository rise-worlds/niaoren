package p110z1;

import java.util.UUID;
import java.util.Vector;

/* renamed from: z1.alf */
/* loaded from: classes3.dex */
public class UUIDManager {

    /* renamed from: b */
    private static UUIDManager f16347b;

    /* renamed from: a */
    private Vector<String> f16348a = new Vector<>();

    /* renamed from: a */
    public static UUIDManager m12598a() {
        UUIDManager alfVar = f16347b;
        if (alfVar == null) {
            synchronized (UUIDManager.class) {
                alfVar = f16347b;
                if (alfVar == null) {
                    alfVar = new UUIDManager();
                    f16347b = alfVar;
                }
            }
        }
        return alfVar;
    }

    /* renamed from: a */
    public void m12597a(String str) {
        Vector<String> vector = this.f16348a;
        if (vector != null) {
            vector.add(str);
        }
    }

    /* renamed from: b */
    public boolean m12595b(String str) {
        Vector<String> vector = this.f16348a;
        return vector != null && vector.remove(str);
    }

    /* renamed from: b */
    public String m12596b() {
        String uuid = UUID.randomUUID().toString();
        m12597a(uuid);
        return uuid;
    }
}
