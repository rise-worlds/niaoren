package com.tendcloud.tenddata;

import android.support.v4.app.NotificationCompat;

import java.util.HashMap;
import java.util.TreeMap;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.gz */
/* loaded from: classes2.dex */
public class C2929gz {

    /* renamed from: a */
    private static volatile C2929gz f14140a;

    /* renamed from: b */
    private HashMap f14141b = new HashMap();

    /* renamed from: a */
    public static C2929gz m15530a() {
        if (f14140a == null) {
            synchronized (C2936he.class) {
                if (f14140a == null) {
                    f14140a = new C2929gz();
                }
            }
        }
        return f14140a;
    }

    private C2929gz() {
    }

    static {
        try {
            C2858ev.m15778a().register(m15530a());
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    public final void onTDEBEventPage(C3034zz.C3035a aVar) {
        if (aVar != null) {
            try {
                if (aVar.paraMap != null) {
                    int parseInt = Integer.parseInt(String.valueOf(aVar.paraMap.get("apiType")));
                    if ((parseInt == 4 || parseInt == 5) && !String.valueOf(aVar.paraMap.get("occurTime")).trim().isEmpty()) {
                        m15527a(aVar.paraMap);
                    }
                }
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
    }

    /* renamed from: a */
    private void m15527a(HashMap hashMap) {
        try {
            int parseInt = Integer.parseInt(String.valueOf(hashMap.get("apiType")));
            AbstractC2790d dVar = (AbstractC2790d) hashMap.get(NotificationCompat.CATEGORY_SERVICE);
            switch (parseInt) {
                case 4:
                    m15529a(Long.parseLong(String.valueOf(hashMap.get("occurTime"))), String.valueOf(hashMap.get("pageName")), C2664ab.f13486C == null ? C2812dr.m16016d() : C2664ab.f13486C, dVar);
                    return;
                case 5:
                    m15528a(String.valueOf(hashMap.get("pageName")), dVar);
                    return;
                default:
                    return;
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0047 A[Catch: Throwable -> 0x009e, TRY_LEAVE, TryCatch #0 {Throwable -> 0x009e, blocks: (B:6:0x000e, B:8:0x0014, B:9:0x0031, B:11:0x0047), top: B:15:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void m15529a(long r9, java.lang.String r11, java.lang.String r12, com.tendcloud.tenddata.AbstractC2790d r13) {
        /*
            r8 = this;
            r0 = 1
            com.tendcloud.tenddata.C3034zz.f14346b = r0
            java.util.HashMap r0 = r8.f14141b
            boolean r0 = r0.containsKey(r11)
            if (r0 == 0) goto L_0x000c
            return
        L_0x000c:
            if (r12 == 0) goto L_0x0031
            boolean r0 = r12.isEmpty()     // Catch: Throwable -> 0x009e
            if (r0 != 0) goto L_0x0031
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: Throwable -> 0x009e
            r0.<init>()     // Catch: Throwable -> 0x009e
            java.lang.String r1 = "onPageStart being called!  pagename: "
            r0.append(r1)     // Catch: Throwable -> 0x009e
            r0.append(r11)     // Catch: Throwable -> 0x009e
            java.lang.String r1 = ", refer: "
            r0.append(r1)     // Catch: Throwable -> 0x009e
            r0.append(r12)     // Catch: Throwable -> 0x009e
            java.lang.String r0 = r0.toString()     // Catch: Throwable -> 0x009e
            com.tendcloud.tenddata.C2811dq.iForDeveloper(r0)     // Catch: Throwable -> 0x009e
            goto L_0x0045
        L_0x0031:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: Throwable -> 0x009e
            r0.<init>()     // Catch: Throwable -> 0x009e
            java.lang.String r1 = "onPageStart being called!  pagename: "
            r0.append(r1)     // Catch: Throwable -> 0x009e
            r0.append(r11)     // Catch: Throwable -> 0x009e
            java.lang.String r0 = r0.toString()     // Catch: Throwable -> 0x009e
            com.tendcloud.tenddata.C2811dq.iForDeveloper(r0)     // Catch: Throwable -> 0x009e
        L_0x0045:
            if (r11 == 0) goto L_0x00a2
            com.tendcloud.tenddata.gz$a r0 = new com.tendcloud.tenddata.gz$a     // Catch: Throwable -> 0x009e
            r7 = 0
            r1 = r0
            r2 = r8
            r3 = r9
            r5 = r12
            r6 = r11
            r1.<init>(r3, r5, r6)     // Catch: Throwable -> 0x009e
            java.util.HashMap r9 = r8.f14141b     // Catch: Throwable -> 0x009e
            r9.put(r11, r0)     // Catch: Throwable -> 0x009e
            com.tendcloud.tenddata.ho r9 = new com.tendcloud.tenddata.ho     // Catch: Throwable -> 0x009e
            r9.<init>()     // Catch: Throwable -> 0x009e
            java.lang.String r10 = "page"
            r9.f14181b = r10     // Catch: Throwable -> 0x009e
            java.lang.String r10 = "enter"
            r9.f14182c = r10     // Catch: Throwable -> 0x009e
            java.util.TreeMap r10 = new java.util.TreeMap     // Catch: Throwable -> 0x009e
            r10.<init>()     // Catch: Throwable -> 0x009e
            java.lang.String r11 = "startTime"
            long r1 = com.tendcloud.tenddata.C2929gz.C2930a.access$100(r0)     // Catch: Throwable -> 0x009e
            java.lang.Long r12 = java.lang.Long.valueOf(r1)     // Catch: Throwable -> 0x009e
            r10.put(r11, r12)     // Catch: Throwable -> 0x009e
            java.lang.String r11 = "duration"
            r12 = 0
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch: Throwable -> 0x009e
            r10.put(r11, r12)     // Catch: Throwable -> 0x009e
            java.lang.String r11 = "name"
            java.lang.String r12 = com.tendcloud.tenddata.C2929gz.C2930a.access$200(r0)     // Catch: Throwable -> 0x009e
            r10.put(r11, r12)     // Catch: Throwable -> 0x009e
            java.lang.String r11 = "from"
            java.lang.String r12 = com.tendcloud.tenddata.C2929gz.C2930a.access$300(r0)     // Catch: Throwable -> 0x009e
            r10.put(r11, r12)     // Catch: Throwable -> 0x009e
            r9.f14183d = r10     // Catch: Throwable -> 0x009e
            r9.f14180a = r13     // Catch: Throwable -> 0x009e
            com.tendcloud.tenddata.ev r10 = com.tendcloud.tenddata.C2858ev.m15778a()     // Catch: Throwable -> 0x009e
            r10.post(r9)     // Catch: Throwable -> 0x009e
            goto L_0x00a2
        L_0x009e:
            r9 = move-exception
            com.tendcloud.tenddata.C2933hb.postSDKError(r9)
        L_0x00a2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tendcloud.tenddata.C2929gz.m15529a(long, java.lang.String, java.lang.String, com.tendcloud.tenddata.d):void");
    }

    /* renamed from: a */
    private final void m15528a(String str, AbstractC2790d dVar) {
        if (this.f14141b.containsKey(str)) {
            try {
                C2811dq.iForDeveloper("onPageEnd being called! pageName: " + str);
                C2930a aVar = (C2930a) this.f14141b.remove(str);
                if (aVar != null) {
                    C2947ho hoVar = new C2947ho();
                    hoVar.f14181b = "page";
                    hoVar.f14182c = "leave";
                    long round = Math.round((System.currentTimeMillis() - aVar.getStartTime()) / 1000.0d);
                    TreeMap treeMap = new TreeMap();
                    treeMap.put("startTime", Long.valueOf(aVar.getStartTime()));
                    treeMap.put("name", str);
                    treeMap.put("from", aVar.getRefer());
                    treeMap.put("duration", Long.valueOf(round));
                    hoVar.f14183d = treeMap;
                    hoVar.f14180a = dVar;
                    C2858ev.m15778a().post(hoVar);
                }
                if (C2664ab.f13485B) {
                    C2812dr.setLastActivity(str);
                }
                C2664ab.f13486C = str;
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.gz$a */
    /* loaded from: classes2.dex */
    public final class C2930a {
        private String pageName;
        private String refer;
        private long startTime;

        private C2930a(long j, String str, String str2) {
            this.startTime = 0L;
            this.refer = "";
            this.pageName = "";
            this.startTime = j;
            this.refer = str;
            this.pageName = str2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public long getStartTime() {
            return this.startTime;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getRefer() {
            return this.refer;
        }

        private String getPageName() {
            return this.pageName;
        }

        public String toString() {
            return "pageName: " + this.pageName + "\nfrom: " + this.refer + "\nstartTime: " + this.startTime;
        }
    }
}
