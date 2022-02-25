package com.tendcloud.tenddata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.fl */
/* loaded from: classes2.dex */
public class C2881fl {

    /* renamed from: a */
    private static int f13980a = 2;

    /* renamed from: b */
    private static int f13981b = 6;

    /* renamed from: c */
    private static int f13982c = 6;

    /* renamed from: d */
    private static int f13983d = -40;

    /* renamed from: e */
    private static int f13984e = 4;

    /* renamed from: f */
    private C2872fh f13985f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.fl$a */
    /* loaded from: classes2.dex */
    public class C2882a {
        public C2880fk fp1;
        public C2880fk fp2;
        public double score;

        public C2882a(C2880fk fkVar, C2880fk fkVar2, double d) {
            this.fp1 = fkVar;
            this.fp2 = fkVar2;
            this.score = d;
        }
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.fl$b */
    /* loaded from: classes2.dex */
    class C2883b {
        public Object key;
        public Object value;

        public C2883b(Object obj, Object obj2) {
            this.key = obj;
            this.value = obj2;
        }
    }

    public C2881fl() {
        this(new C2872fh());
    }

    public C2881fl(C2872fh fhVar) {
        this.f13985f = fhVar;
    }

    /* renamed from: a */
    public double m15714a(C2880fk fkVar, C2880fk fkVar2) {
        Map a = fkVar.m15719a(false);
        Map a2 = fkVar2.m15719a(false);
        HashSet<C2871fg> hashSet = new HashSet();
        int i = 0;
        int i2 = 0;
        double d = 0.0d;
        double d2 = 0.0d;
        for (Map.Entry entry : a.entrySet()) {
            C2871fg fgVar = (C2871fg) entry.getValue();
            C2871fg fgVar2 = (C2871fg) a2.get(entry.getKey());
            i2 += fgVar.m15736c();
            if (fgVar2 == null) {
                hashSet.add(fgVar);
            } else {
                i++;
                double b = m15710b(fgVar.m15736c(), fgVar2.m15736c());
                d += b;
                d2 += b * m15715a(fgVar.m15736c(), fgVar2.m15736c());
            }
        }
        if (i == 0) {
            return 0.0d;
        }
        double d3 = 0.0d;
        for (Map.Entry entry2 : a2.entrySet()) {
            i2 += ((C2871fg) entry2.getValue()).m15736c();
            if (!a.containsKey(entry2.getKey())) {
                hashSet.add(entry2.getValue());
            }
        }
        int max = Math.max(this.f13985f.m15729d(), (int) ((i2 / ((fkVar.m15717c().size() + fkVar2.m15717c().size()) - 0)) + 1.2d));
        for (C2871fg fgVar3 : hashSet) {
            if (fgVar3.m15736c() > max) {
                d3 += 1.0d;
            }
        }
        return (d2 / d) * (1.0d - Math.pow(d3 / ((i * 2) + d3), f13984e));
    }

    /* renamed from: a */
    public double m15713a(C2880fk fkVar, List list) {
        Iterator it = list.iterator();
        double d = 0.0d;
        while (it.hasNext()) {
            d = Math.max(m15714a((C2880fk) it.next(), fkVar), d);
        }
        return d;
    }

    /* renamed from: a */
    public double m15712a(List list, List list2) {
        double d = 0.0d;
        if (list.isEmpty() || list2.isEmpty()) {
            return 0.0d;
        }
        LinkedList<C2882a> linkedList = new LinkedList();
        m15708b(list, list2, linkedList);
        int i = 0;
        for (C2882a aVar : linkedList) {
            if (!(aVar.fp1 == null || aVar.fp2 == null)) {
                d += aVar.score;
                i++;
            }
        }
        return d / i;
    }

    /* renamed from: b */
    public C2880fk m15709b(C2880fk fkVar, C2880fk fkVar2) {
        Map a = fkVar.m15719a(false);
        Map a2 = fkVar2.m15719a(false);
        TreeMap treeMap = new TreeMap();
        C2880fk fkVar3 = new C2880fk();
        fkVar3.setPoiId(fkVar2.m15718b());
        fkVar3.setTimestamp(fkVar2.m15720a());
        LinkedList linkedList = new LinkedList();
        fkVar3.setBsslist(linkedList);
        for (Map.Entry entry : a.entrySet()) {
            C2871fg fgVar = (C2871fg) entry.getValue();
            C2871fg fgVar2 = (C2871fg) a2.get(entry.getKey());
            if (fgVar2 == null) {
                double d = -fgVar.m15736c();
                while (treeMap.containsKey(Double.valueOf(d))) {
                    d += 1.0E-4d;
                }
                treeMap.put(Double.valueOf(d), fgVar);
            } else {
                linkedList.add(new C2871fg(fgVar2.m15738a(), fgVar2.m15737b(), (byte) ((fgVar2.m15736c() + fgVar.m15736c()) / 2), fgVar2.m15735d(), fgVar2.m15734e()));
            }
        }
        for (Map.Entry entry2 : a2.entrySet()) {
            if (!a.containsKey(entry2.getKey())) {
                double d2 = -((C2871fg) entry2.getValue()).m15736c();
                while (treeMap.containsKey(Double.valueOf(d2))) {
                    d2 += 1.0E-4d;
                }
                treeMap.put(Double.valueOf(d2), entry2.getValue());
            }
        }
        for (Map.Entry entry3 : treeMap.entrySet()) {
            byte b = (byte) (-((Double) entry3.getKey()).doubleValue());
            if (linkedList.size() >= this.f13985f.m15730c() || b < this.f13985f.m15729d()) {
                break;
            }
            linkedList.add(entry3.getValue());
        }
        return fkVar3;
    }

    /* renamed from: a */
    public double m15711a(List list, List list2, List list3) {
        double d = 0.0d;
        if (list.isEmpty() || list2.isEmpty()) {
            list3.addAll(list);
            list3.addAll(list2);
            return 0.0d;
        }
        LinkedList<C2882a> linkedList = new LinkedList();
        m15708b(list, list2, linkedList);
        int i = 0;
        for (C2882a aVar : linkedList) {
            if (aVar.fp1 != null && aVar.fp2 != null) {
                d += aVar.score;
                i++;
                list3.add(m15709b(aVar.fp1, aVar.fp2));
            } else if (list3.size() < this.f13985f.m15731b()) {
                list3.add((aVar.fp1 == null ? aVar.fp2 : aVar.fp1).m15716d());
            }
        }
        return d / i;
    }

    /* renamed from: a */
    public double m15715a(int i, int i2) {
        double d = 0.0d;
        if (i >= 0 || i2 >= 0) {
            return 0.0d;
        }
        double d2 = (i2 + i) / 2;
        double abs = Math.abs(i - d2);
        int i3 = f13980a;
        if (abs > i3) {
            d = abs - i3;
        }
        return Math.pow((d + d2) / d2, f13981b);
    }

    /* renamed from: b */
    public void m15708b(List list, List list2, List list3) {
        ArrayList arrayList = new ArrayList();
        HashSet<C2880fk> hashSet = new HashSet();
        HashSet<C2880fk> hashSet2 = new HashSet();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            C2880fk fkVar = (C2880fk) it.next();
            Iterator it2 = list2.iterator();
            while (it2.hasNext()) {
                C2880fk fkVar2 = (C2880fk) it2.next();
                hashSet2.add(fkVar2);
                arrayList.add(new C2882a(fkVar, fkVar2, m15714a(fkVar, fkVar2)));
            }
            hashSet.add(fkVar);
        }
        Collections.sort(arrayList, new C2884fm(this));
        list3.clear();
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            C2882a aVar = (C2882a) it3.next();
            if (hashSet.contains(aVar.fp1) && hashSet2.contains(aVar.fp2)) {
                hashSet.remove(aVar.fp1);
                hashSet2.remove(aVar.fp2);
                list3.add(aVar);
            }
        }
        for (C2880fk fkVar3 : hashSet) {
            list3.add(new C2882a(fkVar3, null, 0.0d));
        }
        for (C2880fk fkVar4 : hashSet2) {
            list3.add(new C2882a(null, fkVar4, 0.0d));
        }
    }

    /* renamed from: b */
    public double m15710b(int i, int i2) {
        int i3;
        if (i >= 0 || i2 >= 0) {
            return 0.0d;
        }
        double max = Math.max(i, i2);
        if (max >= f13983d) {
            return 1.0d;
        }
        return Math.pow((max + 128.0d) / (i3 + 128), f13982c);
    }
}
