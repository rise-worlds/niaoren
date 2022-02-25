package com.tendcloud.tenddata;

import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;
import org.apache.commons.mail.EmailConstants;
import org.json.JSONObject;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ig */
/* loaded from: classes2.dex */
public class C2969ig {

    /* renamed from: d */
    static byte[] f14242d;

    /* renamed from: a */
    static HashMap f14239a = new HashMap();

    /* renamed from: b */
    static HashMap f14240b = new HashMap();

    /* renamed from: c */
    static String f14241c = EmailConstants.UTF_8;

    /* renamed from: e */
    private static volatile C2969ig f14243e = null;

    static {
        try {
            C2858ev.m15778a().register(m15443a());
        } catch (Throwable unused) {
        }
    }

    public final synchronized void onTDEBEventDataStore(C2947ho hoVar) {
        if (hoVar != null) {
            try {
                if (hoVar.f14180a.getMessageFormat().equals(AbstractC2790d.MF_JSON)) {
                    if (C2664ab.m16347d()) {
                        C2979il.m15417a().setAntiCheatingstatus(1);
                    } else {
                        C2979il.m15417a().setAntiCheatingstatus(0);
                    }
                    if (hoVar.f14180a != null) {
                        C2981in inVar = new C2981in(hoVar.f14181b, hoVar.f14182c);
                        inVar.setData(hoVar.f14183d);
                        JSONObject jSONObject = null;
                        if (!hoVar.f14180a.name().equals("BG")) {
                            jSONObject = C2998jc.m15383a().m15380a(inVar, true, hoVar.f14180a, hoVar.f14184e);
                        }
                        if (jSONObject != null) {
                            C2973ii.m15429a().m15426a(new C2970ih(C2866fb.m15767a(jSONObject.toString().getBytes())), hoVar);
                        }
                    }
                } else if (hoVar.f14180a.getMessageFormat().equals(AbstractC2790d.MF_STRING)) {
                    String str = hoVar.f14186g;
                    if (!C2855es.m15791b(str)) {
                        C2973ii.m15429a().m15426a(new C2970ih(C2866fb.m15767a(str.getBytes())), hoVar);
                    }
                }
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
    }

    /* renamed from: a */
    public TreeSet m15442a(AbstractC2790d dVar) {
        TreeSet treeSet = new TreeSet();
        TreeSet treeSet2 = null;
        try {
            treeSet2 = C2973ii.m15429a().m15428a(dVar, 100, (String) null);
            if (treeSet2 != null && treeSet2.size() > 0) {
                Iterator it = treeSet2.iterator();
                while (it.hasNext()) {
                    C2970ih ihVar = (C2970ih) it.next();
                    try {
                        ihVar.writeData(C2855es.m15788b(ihVar.m15433c(), f14242d));
                    } catch (Throwable unused) {
                    }
                }
                treeSet.addAll(treeSet2);
                treeSet2.clear();
            }
            int size = treeSet2 == null ? 0 : treeSet2.size();
            if (size < 100 && (treeSet2 = C2973ii.m15429a().m15428a(dVar, 100 - size, dVar.getRootFolder())) != null && treeSet2.size() > 0) {
                Iterator it2 = treeSet2.iterator();
                while (it2.hasNext()) {
                    C2970ih ihVar2 = (C2970ih) it2.next();
                    try {
                        byte[] b = C2866fb.m15766b(ihVar2.m15433c());
                        if (!(b == null || b.length == 0)) {
                            ihVar2.writeData(b);
                        }
                    } catch (Throwable unused2) {
                    }
                }
            }
            if (treeSet.size() > 0 && treeSet2 != null) {
                treeSet2.addAll(treeSet);
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
        return treeSet2;
    }

    public void sendMessageSuccess(AbstractC2790d dVar) {
        try {
            C2973ii.m15429a().confirmRead(dVar);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    public void sendMessageFaild(AbstractC2790d dVar) {
        try {
            C2973ii.m15429a().clearDataCache(dVar);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    public static C2969ig m15443a() {
        if (f14243e == null) {
            synchronized (C2969ig.class) {
                if (f14243e == null) {
                    f14243e = new C2969ig();
                }
            }
        }
        return f14243e;
    }

    private C2969ig() {
        String c = C2855es.m15786c(C2664ab.f13513g.getPackageName());
        if (C2664ab.f13513g == null || c == null) {
            f14242d = C2664ab.class.getSimpleName().getBytes();
        } else {
            f14242d = c.getBytes();
        }
    }
}
