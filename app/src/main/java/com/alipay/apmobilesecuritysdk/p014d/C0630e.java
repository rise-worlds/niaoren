package com.alipay.apmobilesecuritysdk.p014d;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import p110z1.C4745bt;
import p110z1.C5116cs;
import p110z1.SimpleComparison;

/* renamed from: com.alipay.apmobilesecuritysdk.d.e */
/* loaded from: classes.dex */
public final class C0630e {

    /* renamed from: a */
    private static Map<String, String> f220a;

    /* renamed from: b */
    private static final String[] f221b = {"AD1", "AD2", "AD3", "AD8", "AD9", "AD10", "AD11", "AD12", "AD14", "AD15", "AD16", "AD18", "AD20", "AD21", "AD23", "AD24", "AD26", "AD27", "AD28", "AD29", "AD30", "AD31", "AD34", "AA1", "AA2", "AA3", "AA4", "AC4", "AC10", "AE1", "AE2", "AE3", "AE4", "AE5", "AE6", "AE7", "AE8", "AE9", "AE10", "AE11", "AE12", "AE13", "AE14", "AE15"};

    /* renamed from: a */
    private static String m25417a(Map<String, String> map) {
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList);
        int i = 0;
        while (i < arrayList.size()) {
            String str = (String) arrayList.get(i);
            String str2 = map.get(str);
            if (str2 == null) {
                str2 = "";
            }
            StringBuilder sb = new StringBuilder();
            sb.append(i == 0 ? "" : C4745bt.f20071b);
            sb.append(str);
            sb.append(SimpleComparison.f23609c);
            sb.append(str2);
            stringBuffer.append(sb.toString());
            i++;
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public static synchronized Map<String, String> m25418a(Context context, Map<String, String> map) {
        Map<String, String> map2;
        synchronized (C0630e.class) {
            if (f220a == null) {
                m25415c(context, map);
            }
            f220a.putAll(C0629d.m25421a());
            map2 = f220a;
        }
        return map2;
    }

    /* renamed from: a */
    public static synchronized void m25419a() {
        synchronized (C0630e.class) {
            f220a = null;
        }
    }

    /* renamed from: b */
    public static synchronized String m25416b(Context context, Map<String, String> map) {
        String[] strArr;
        String a;
        synchronized (C0630e.class) {
            m25418a(context, map);
            TreeMap treeMap = new TreeMap();
            for (String str : f221b) {
                if (f220a.containsKey(str)) {
                    treeMap.put(str, f220a.get(str));
                }
            }
            a = C5116cs.m3522a(m25417a(treeMap));
        }
        return a;
    }

    /* renamed from: c */
    private static synchronized void m25415c(Context context, Map<String, String> map) {
        synchronized (C0630e.class) {
            TreeMap treeMap = new TreeMap();
            f220a = treeMap;
            treeMap.putAll(C0627b.m25423a(context, map));
            f220a.putAll(C0629d.m25420a(context));
            f220a.putAll(C0628c.m25422a(context));
            f220a.putAll(C0626a.m25424a(context, map));
        }
    }
}
