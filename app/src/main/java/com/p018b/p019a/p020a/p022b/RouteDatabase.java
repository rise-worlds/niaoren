package com.p018b.p019a.p020a.p022b;

import com.p018b.p019a.Route;
import java.util.LinkedHashSet;
import java.util.Set;

/* renamed from: com.b.a.a.b.d */
/* loaded from: classes.dex */
public final class RouteDatabase {

    /* renamed from: a */
    private final Set<Route> f5753a = new LinkedHashSet();

    /* renamed from: a */
    public final synchronized void m24796a(Route awVar) {
        this.f5753a.add(awVar);
    }

    /* renamed from: b */
    public final synchronized void m24795b(Route awVar) {
        this.f5753a.remove(awVar);
    }

    /* renamed from: c */
    public final synchronized boolean m24794c(Route awVar) {
        return this.f5753a.contains(awVar);
    }
}
