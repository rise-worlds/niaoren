package com.tendcloud.tenddata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.fi */
/* loaded from: classes2.dex */
public final class C2873fi {

    /* renamed from: a */
    private static int f13970a = 2;

    /* renamed from: b */
    private static int f13971b = 6;

    /* renamed from: c */
    private static int f13972c = 6;

    /* renamed from: d */
    private static int f13973d = -40;

    /* renamed from: e */
    private static int f13974e = 4;

    /* renamed from: f */
    private C2875b f13975f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.fi$d */
    /* loaded from: classes2.dex */
    public class C2877d {
        C2876c fp1;
        C2876c fp2;
        double score;

        C2877d(C2876c cVar, C2876c cVar2, double d) {
            this.fp1 = cVar;
            this.fp2 = cVar2;
            this.score = d;
        }
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.fi$e */
    /* loaded from: classes2.dex */
    class C2878e {
        Object key;
        Object value;

        C2878e(Object obj, Object obj2) {
            this.key = obj;
            this.value = obj2;
        }
    }

    C2873fi() {
        this(new C2875b());
    }

    C2873fi(C2875b bVar) {
        this.f13975f = bVar;
    }

    /* renamed from: a */
    double m15727a(C2876c cVar, C2876c cVar2) {
        Map bssmap = cVar.getBssmap(false);
        Map bssmap2 = cVar2.getBssmap(false);
        HashSet<C2874a> hashSet = new HashSet();
        int i = 0;
        int i2 = 0;
        double d = 0.0d;
        double d2 = 0.0d;
        for (Map.Entry entry : bssmap.entrySet()) {
            C2874a aVar = (C2874a) entry.getValue();
            C2874a aVar2 = (C2874a) bssmap2.get(entry.getKey());
            i2 += aVar.getRssi();
            if (aVar2 == null) {
                hashSet.add(aVar);
            } else {
                i++;
                double b = m15723b(aVar.getRssi(), aVar2.getRssi());
                d += b;
                d2 += b * m15728a(aVar.getRssi(), aVar2.getRssi());
            }
        }
        if (i == 0) {
            return 0.0d;
        }
        double d3 = 0.0d;
        for (Map.Entry entry2 : bssmap2.entrySet()) {
            i2 += ((C2874a) entry2.getValue()).getRssi();
            if (!bssmap.containsKey(entry2.getKey())) {
                hashSet.add(entry2.getValue());
            }
        }
        int max = Math.max(this.f13975f.getRssiThreshold(), (int) ((i2 / ((cVar.getBsslist().size() + cVar2.getBsslist().size()) - 0)) + 1.2d));
        for (C2874a aVar3 : hashSet) {
            if (aVar3.getRssi() > max) {
                d3 += 1.0d;
            }
        }
        return (d2 / d) * (1.0d - Math.pow(d3 / ((i * 2) + d3), f13974e));
    }

    /* renamed from: a */
    double m15726a(C2876c cVar, List list) {
        Iterator it = list.iterator();
        double d = 0.0d;
        while (it.hasNext()) {
            d = Math.max(m15727a((C2876c) it.next(), cVar), d);
        }
        return d;
    }

    /* renamed from: a */
    double m15725a(List list, List list2) {
        double d = 0.0d;
        if (list.isEmpty() || list2.isEmpty()) {
            return 0.0d;
        }
        LinkedList<C2877d> linkedList = new LinkedList();
        m15721b(list, list2, linkedList);
        int i = 0;
        for (C2877d dVar : linkedList) {
            if (!(dVar.fp1 == null || dVar.fp2 == null)) {
                d += dVar.score;
                i++;
            }
        }
        return d / i;
    }

    /* renamed from: b */
    C2876c m15722b(C2876c cVar, C2876c cVar2) {
        Map bssmap = cVar.getBssmap(false);
        Map bssmap2 = cVar2.getBssmap(false);
        TreeMap treeMap = new TreeMap();
        C2876c cVar3 = new C2876c();
        cVar3.setPoiId(cVar2.getPoiId());
        cVar3.setTimestamp(cVar2.getTimestamp());
        LinkedList linkedList = new LinkedList();
        cVar3.setBsslist(linkedList);
        for (Map.Entry entry : bssmap.entrySet()) {
            C2874a aVar = (C2874a) entry.getValue();
            C2874a aVar2 = (C2874a) bssmap2.get(entry.getKey());
            if (aVar2 == null) {
                double d = -aVar.getRssi();
                while (treeMap.containsKey(Double.valueOf(d))) {
                    d += 1.0E-4d;
                }
                treeMap.put(Double.valueOf(d), aVar);
            } else {
                linkedList.add(new C2874a(aVar2.getSsid(), aVar2.getBssid(), (byte) ((aVar2.getRssi() + aVar.getRssi()) / 2), aVar2.getBand(), aVar2.getChannel()));
            }
        }
        for (Map.Entry entry2 : bssmap2.entrySet()) {
            if (!bssmap.containsKey(entry2.getKey())) {
                double d2 = -((C2874a) entry2.getValue()).getRssi();
                while (treeMap.containsKey(Double.valueOf(d2))) {
                    d2 += 1.0E-4d;
                }
                treeMap.put(Double.valueOf(d2), entry2.getValue());
            }
        }
        for (Map.Entry entry3 : treeMap.entrySet()) {
            byte b = (byte) (-((Double) entry3.getKey()).doubleValue());
            if (linkedList.size() >= this.f13975f.getMaxBssEntries() || b < this.f13975f.getRssiThreshold()) {
                break;
            }
            linkedList.add(entry3.getValue());
        }
        return cVar3;
    }

    /* renamed from: a */
    double m15724a(List list, List list2, List list3) {
        double d = 0.0d;
        if (list.isEmpty() || list2.isEmpty()) {
            list3.addAll(list);
            list3.addAll(list2);
            return 0.0d;
        }
        LinkedList<C2877d> linkedList = new LinkedList();
        m15721b(list, list2, linkedList);
        int i = 0;
        for (C2877d dVar : linkedList) {
            if (dVar.fp1 != null && dVar.fp2 != null) {
                d += dVar.score;
                i++;
                list3.add(m15722b(dVar.fp1, dVar.fp2));
            } else if (list3.size() < this.f13975f.getMinFingerprints()) {
                list3.add((dVar.fp1 == null ? dVar.fp2 : dVar.fp1).cloneFingerprint());
            }
        }
        return d / i;
    }

    /* renamed from: a */
    double m15728a(int i, int i2) {
        double d = 0.0d;
        if (i >= 0 || i2 >= 0) {
            return 0.0d;
        }
        double d2 = (i2 + i) / 2;
        double abs = Math.abs(i - d2);
        int i3 = f13970a;
        if (abs > i3) {
            d = abs - i3;
        }
        return Math.pow((d + d2) / d2, f13971b);
    }

    /* renamed from: b */
    void m15721b(List list, List list2, List list3) {
        ArrayList arrayList = new ArrayList();
        HashSet<C2876c> hashSet = new HashSet();
        HashSet<C2876c> hashSet2 = new HashSet();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            C2876c cVar = (C2876c) it.next();
            Iterator it2 = list2.iterator();
            while (it2.hasNext()) {
                C2876c cVar2 = (C2876c) it2.next();
                hashSet2.add(cVar2);
                arrayList.add(new C2877d(cVar, cVar2, m15727a(cVar, cVar2)));
            }
            hashSet.add(cVar);
        }
        Collections.sort(arrayList, new C2879fj(this));
        list3.clear();
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            C2877d dVar = (C2877d) it3.next();
            if (hashSet.contains(dVar.fp1) && hashSet2.contains(dVar.fp2)) {
                hashSet.remove(dVar.fp1);
                hashSet2.remove(dVar.fp2);
                list3.add(dVar);
            }
        }
        for (C2876c cVar3 : hashSet) {
            list3.add(new C2877d(cVar3, null, 0.0d));
        }
        for (C2876c cVar4 : hashSet2) {
            list3.add(new C2877d(null, cVar4, 0.0d));
        }
    }

    /* renamed from: b */
    double m15723b(int i, int i2) {
        int i3;
        if (i >= 0 || i2 >= 0) {
            return 0.0d;
        }
        double max = Math.max(i, i2);
        if (max >= f13973d) {
            return 1.0d;
        }
        return Math.pow((max + 128.0d) / (i3 + 128), f13972c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.fi$a */
    /* loaded from: classes2.dex */
    public class C2874a {
        private byte band;
        private String bssid;
        private byte channel;
        private byte rssi;
        private String ssid;

        C2874a() {
            this.ssid = "";
            this.bssid = "00:00:00:00:00:00";
            this.rssi = (byte) -127;
            this.band = (byte) 1;
            this.channel = (byte) 1;
        }

        C2874a(String str, String str2, byte b, byte b2, byte b3) {
            this.ssid = str;
            this.bssid = str2;
            this.rssi = b;
            this.band = b2;
            this.channel = b3;
        }

        String getSsid() {
            return this.ssid;
        }

        void setSsid(String str) {
            this.ssid = str;
        }

        String getBssid() {
            return this.bssid;
        }

        void setBssid(String str) {
            this.bssid = str;
        }

        byte getRssi() {
            return this.rssi;
        }

        void setRssi(byte b) {
            this.rssi = b;
        }

        byte getBand() {
            return this.band;
        }

        void setBand(byte b) {
            this.band = b;
        }

        byte getChannel() {
            return this.channel;
        }

        void setChannel(byte b) {
            this.channel = b;
        }

        C2874a cloneBssEntry() {
            return new C2874a(this.ssid, this.bssid, this.rssi, this.band, this.channel);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.fi$b */
    /* loaded from: classes2.dex */
    public static class C2875b {
        static final int DEFAULT_MAX_BSS_ENTRIES = 50;
        static final int DEFAULT_MAX_FINGERPRINTS = 10;
        static final int DEFAULT_MIN_FINGERPRINTS = 3;
        static final int DEFAULT_RSSI_THRESHOLD = -85;
        private int maxFingerprints = 10;
        private int minFingerprints = 3;
        private int maxBssEntries = 50;
        private int rssiThreshold = -85;

        C2875b() {
        }

        int getMaxFingerprints() {
            return this.maxFingerprints;
        }

        void setMaxFingerprints(int i) {
            this.maxFingerprints = i;
        }

        int getMinFingerprints() {
            return this.minFingerprints;
        }

        void setMinFingerprints(int i) {
            this.minFingerprints = i;
        }

        int getMaxBssEntries() {
            return this.maxBssEntries;
        }

        void setMaxBssEntries(int i) {
            this.maxBssEntries = i;
        }

        int getRssiThreshold() {
            return this.rssiThreshold;
        }

        void setRssiThreshold(int i) {
            this.rssiThreshold = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.fi$c */
    /* loaded from: classes2.dex */
    public class C2876c {
        private List bsslist;
        private Map bssmap;
        private long poiId;
        private int timestamp;

        C2876c() {
        }

        int getTimestamp() {
            return this.timestamp;
        }

        void setTimestamp(int i) {
            this.timestamp = i;
        }

        long getPoiId() {
            return this.poiId;
        }

        void setPoiId(long j) {
            this.poiId = j;
        }

        List getBsslist() {
            return this.bsslist;
        }

        void setBsslist(List list) {
            this.bsslist = list;
        }

        Map getBssmap(boolean z) {
            if (this.bssmap == null || z) {
                this.bssmap = new HashMap();
                for (C2874a aVar : this.bsslist) {
                    this.bssmap.put(aVar.getBssid(), aVar);
                }
            }
            return this.bssmap;
        }

        C2876c cloneFingerprint() {
            C2876c cVar = new C2876c();
            cVar.setTimestamp(this.timestamp);
            cVar.setPoiId(this.poiId);
            LinkedList linkedList = new LinkedList();
            for (C2874a aVar : this.bsslist) {
                linkedList.add(aVar.cloneBssEntry());
            }
            cVar.setBsslist(linkedList);
            return cVar;
        }
    }
}
