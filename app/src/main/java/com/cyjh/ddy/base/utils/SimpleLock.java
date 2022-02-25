package com.cyjh.ddy.base.utils;

/* renamed from: com.cyjh.ddy.base.utils.r */
/* loaded from: classes.dex */
public class SimpleLock {

    /* renamed from: a */
    private int f7136a;

    public SimpleLock() {
        this.f7136a = 1;
    }

    public SimpleLock(int i) throws Exception {
        this.f7136a = 1;
        if (i >= 1) {
            this.f7136a = i;
            return;
        }
        throw new Exception("SimpleLock init err.");
    }

    /* renamed from: a */
    public void m21744a() {
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException unused) {
            }
        }
    }

    /* renamed from: b */
    public void m21743b() {
        this.f7136a--;
        if (this.f7136a <= 0) {
            synchronized (this) {
                notify();
            }
        }
    }
}
