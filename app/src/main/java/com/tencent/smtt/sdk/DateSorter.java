package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.export.external.interfaces.IX5DateSorter;

/* loaded from: classes2.dex */
public class DateSorter {
    public static int DAY_COUNT = 5;

    /* renamed from: a */
    private android.webkit.DateSorter f12760a;

    /* renamed from: b */
    private IX5DateSorter f12761b;

    static {
        m17074a();
    }

    public DateSorter(Context context) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            this.f12760a = new android.webkit.DateSorter(context);
        } else {
            this.f12761b = a.m16616c().m16575h(context);
        }
    }

    public int getIndex(long j) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return this.f12760a.getIndex(j);
        }
        return this.f12761b.getIndex(j);
    }

    public String getLabel(int i) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return this.f12760a.getLabel(i);
        }
        return this.f12761b.getLabel(i);
    }

    public long getBoundary(int i) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            return this.f12760a.getBoundary(i);
        }
        return this.f12761b.getBoundary(i);
    }

    /* renamed from: a */
    private static boolean m17074a() {
        X5CoreEngine a = X5CoreEngine.m16621a();
        return a != null && a.m16618b();
    }
}
