package com.cyjh.mobileanjian.ipc.utils;

import java.util.HashMap;

/* renamed from: com.cyjh.mobileanjian.ipc.utils.h */
/* loaded from: classes.dex */
public class MyHashMap {

    /* renamed from: c */
    private static final long f8686c = 2749416527681438711L;

    /* renamed from: d */
    private HashMap<String, Class> f8689d = new HashMap<>();

    /* renamed from: a */
    HashMap<String, Integer> f8687a = new HashMap<>();

    /* renamed from: b */
    HashMap<Class, Integer> f8688b = new HashMap<>();

    /* renamed from: a */
    public final void m20648a(String str, Class cls, Integer num) {
        this.f8689d.put(str, cls);
        this.f8687a.put(str, num);
        this.f8688b.put(cls, num);
    }

    /* renamed from: a */
    public final Class m20649a(String str) {
        return this.f8689d.get(str);
    }

    /* renamed from: b */
    private int m20647b(String str) {
        return this.f8687a.get(str).intValue();
    }

    /* renamed from: a */
    private int m20650a(Class cls) {
        return this.f8688b.get(cls).intValue();
    }
}
