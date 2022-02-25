package p110z1;

import java.util.ArrayList;
import java.util.List;

/* renamed from: z1.czm */
/* loaded from: classes3.dex */
final class PendingPost {

    /* renamed from: d */
    private static final List<PendingPost> f21167d = new ArrayList();

    /* renamed from: a */
    Object f21168a;

    /* renamed from: b */
    Subscription f21169b;

    /* renamed from: c */
    PendingPost f21170c;

    private PendingPost(Object obj, Subscription cztVar) {
        this.f21168a = obj;
        this.f21169b = cztVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static PendingPost m3394a(Subscription cztVar, Object obj) {
        synchronized (f21167d) {
            int size = f21167d.size();
            if (size <= 0) {
                return new PendingPost(obj, cztVar);
            }
            PendingPost remove = f21167d.remove(size - 1);
            remove.f21168a = obj;
            remove.f21169b = cztVar;
            remove.f21170c = null;
            return remove;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m3395a(PendingPost czmVar) {
        czmVar.f21168a = null;
        czmVar.f21169b = null;
        czmVar.f21170c = null;
        synchronized (f21167d) {
            if (f21167d.size() < 10000) {
                f21167d.add(czmVar);
            }
        }
    }
}
