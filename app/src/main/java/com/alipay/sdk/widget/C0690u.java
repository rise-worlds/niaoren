package com.alipay.sdk.widget;

import java.util.Iterator;
import java.util.Stack;

/* renamed from: com.alipay.sdk.widget.u */
/* loaded from: classes.dex */
public class C0690u {

    /* renamed from: a */
    private Stack<C0682p> f415a = new Stack<>();

    /* renamed from: a */
    public C0682p m25217a() {
        return this.f415a.pop();
    }

    /* renamed from: a */
    public void m25216a(C0682p pVar) {
        this.f415a.push(pVar);
    }

    /* renamed from: b */
    public boolean m25215b() {
        return this.f415a.isEmpty();
    }

    /* renamed from: c */
    public void m25214c() {
        if (!m25215b()) {
            Iterator<C0682p> it = this.f415a.iterator();
            while (it.hasNext()) {
                it.next().m25240a();
            }
            this.f415a.clear();
        }
    }
}
