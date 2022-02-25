package com.goldcoast.sdk.domain;

import android.os.ConditionVariable;
import android.text.TextUtils;
import com.p018b.p019a.Callback;
import com.p018b.p019a.Response;
import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: EntryPoint.java */
/* renamed from: com.goldcoast.sdk.domain.e */
/* loaded from: classes.dex */
public final class C1398e implements Callback {

    /* renamed from: a */
    final /* synthetic */ ConditionVariable f9063a;

    /* renamed from: b */
    final /* synthetic */ boolean[] f9064b;

    /* renamed from: c */
    final /* synthetic */ EntryPoint f9065c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C1398e(EntryPoint entryPoint, ConditionVariable conditionVariable, boolean[] zArr) {
        this.f9065c = entryPoint;
        this.f9063a = conditionVariable;
        this.f9064b = zArr;
    }

    @Override // com.p018b.p019a.Callback
    /* renamed from: a */
    public final void mo20275a(Response asVar) {
        int i;
        String d = asVar.m24449e().m24428d();
        if (!TextUtils.isEmpty(d)) {
            try {
                EntryPoint.m20306a(this.f9065c, d);
            } catch (Exception e) {
                int i2 = 3;
                String format = String.format("Exception:\n %s\n", e.getMessage());
                i = this.f9065c.f9049r;
                if (i == 4) {
                    format = String.format("Exception:\n %s\n$$$ failed", e.getMessage());
                    i2 = 1;
                }
                this.f9065c.m20289b(format, i2);
            }
        }
        this.f9063a.open();
    }

    @Override // com.p018b.p019a.Callback
    /* renamed from: a */
    public final void mo20274a(IOException iOException) {
        int i;
        new HashMap().put("stack", this.f9065c.getStackString(iOException));
        this.f9064b[0] = true;
        i = this.f9065c.f9049r;
        if (i == 4) {
            EntryPoint entryPoint = this.f9065c;
            entryPoint.m20289b(this.f9065c.getStackString(iOException) + "\n$$$ failed\n", 1);
        } else {
            EntryPoint entryPoint2 = this.f9065c;
            entryPoint2.m20289b(entryPoint2.getStackString(iOException), 3);
        }
        this.f9063a.open();
    }
}
