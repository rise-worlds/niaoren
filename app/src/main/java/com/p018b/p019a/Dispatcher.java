package com.p018b.p019a;

import com.p018b.p019a.p020a.Util;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: com.b.a.u */
/* loaded from: classes.dex */
public final class Dispatcher {

    /* renamed from: c */
    private Runnable f6392c;

    /* renamed from: d */
    private ExecutorService f6393d;

    /* renamed from: a */
    private int f6390a = 64;

    /* renamed from: b */
    private int f6391b = 5;

    /* renamed from: e */
    private final Deque<C0902an> f6394e = new ArrayDeque();

    /* renamed from: f */
    private final Deque<C0902an> f6395f = new ArrayDeque();

    /* renamed from: g */
    private final Deque<RealCall> f6396g = new ArrayDeque();

    /* renamed from: a */
    private synchronized ExecutorService m24376a() {
        if (this.f6393d == null) {
            this.f6393d = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), Util.m24756a("OkHttp Dispatcher", false));
        }
        return this.f6393d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final synchronized void m24374a(C0902an anVar) {
        if (this.f6395f.size() >= this.f6390a || m24368c(anVar) >= this.f6391b) {
            this.f6394e.add(anVar);
            return;
        }
        this.f6395f.add(anVar);
        m24376a().execute(anVar);
    }

    /* renamed from: b */
    private void m24372b() {
        if (this.f6395f.size() < this.f6390a && !this.f6394e.isEmpty()) {
            Iterator<C0902an> it = this.f6394e.iterator();
            while (it.hasNext()) {
                C0902an next = it.next();
                if (m24368c(next) < this.f6391b) {
                    it.remove();
                    this.f6395f.add(next);
                    m24376a().execute(next);
                }
                if (this.f6395f.size() >= this.f6390a) {
                    return;
                }
            }
        }
    }

    /* renamed from: c */
    private int m24368c(C0902an anVar) {
        int i = 0;
        for (C0902an anVar2 : this.f6395f) {
            if (anVar2.m24473a().equals(anVar.m24473a())) {
                i++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final synchronized void m24375a(RealCall amVar) {
        this.f6396g.add(amVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final void m24370b(C0902an anVar) {
        m24373a(this.f6395f, anVar, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final void m24371b(RealCall amVar) {
        m24373a(this.f6396g, amVar, false);
    }

    /* renamed from: a */
    private <T> void m24373a(Deque<T> deque, T t, boolean z) {
        int c;
        Runnable runnable;
        synchronized (this) {
            if (deque.remove(t)) {
                if (z) {
                    m24372b();
                }
                c = m24369c();
                runnable = this.f6392c;
            } else {
                throw new AssertionError("Call wasn't in-flight!");
            }
        }
        if (c == 0 && runnable != null) {
            runnable.run();
        }
    }

    /* renamed from: c */
    private synchronized int m24369c() {
        return this.f6395f.size() + this.f6396g.size();
    }
}
