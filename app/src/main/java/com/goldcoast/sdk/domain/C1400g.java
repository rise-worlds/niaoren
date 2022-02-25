package com.goldcoast.sdk.domain;

import android.os.ConditionVariable;
import android.text.TextUtils;
import com.p018b.p019a.Callback;
import com.p018b.p019a.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/* compiled from: EntryPoint.java */
/* renamed from: com.goldcoast.sdk.domain.g */
/* loaded from: classes.dex */
final class C1400g implements Callback {

    /* renamed from: a */
    final /* synthetic */ List f9067a;

    /* renamed from: b */
    final /* synthetic */ ConditionVariable f9068b;

    /* renamed from: c */
    final /* synthetic */ EntryPoint f9069c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C1400g(EntryPoint entryPoint, List list, ConditionVariable conditionVariable) {
        this.f9069c = entryPoint;
        this.f9067a = list;
        this.f9068b = conditionVariable;
    }

    @Override // com.p018b.p019a.Callback
    /* renamed from: a */
    public final void mo20275a(Response asVar) {
        int i;
        String d = asVar.m24449e().m24428d();
        if (!TextUtils.isEmpty(d)) {
            try {
                EntryPoint.m20304a(this.f9069c, d, this.f9067a);
            } catch (Exception e) {
                int i2 = 3;
                String format = String.format("Exception:\n %s\n", e.getMessage());
                i = this.f9069c.f9049r;
                if (i == 4) {
                    format = String.format("Exception:\n %s\n$$$ failed", e.getMessage());
                    i2 = 1;
                }
                this.f9069c.m20289b(format, i2);
            }
        }
        this.f9068b.open();
    }

    @Override // com.p018b.p019a.Callback
    /* renamed from: a */
    public final void mo20274a(IOException iOException) {
        int i;
        new HashMap().put("stack", this.f9069c.getStackString(iOException));
        i = this.f9069c.f9049r;
        if (i == 4) {
            EntryPoint entryPoint = this.f9069c;
            entryPoint.m20289b(this.f9069c.getStackString(iOException) + "\n$$$ failed\n", 1);
        } else {
            EntryPoint entryPoint2 = this.f9069c;
            entryPoint2.m20289b(entryPoint2.getStackString(iOException), 3);
        }
        this.f9068b.open();
    }
}
