package com.goldcoast.sdk.domain;

import android.text.TextUtils;
import com.goldcoast.sdk.p052c.SPUtil;
import org.json.JSONArray;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: EntryPoint.java */
/* renamed from: com.goldcoast.sdk.domain.i */
/* loaded from: classes.dex */
public final class RunnableC1402i implements Runnable {

    /* renamed from: a */
    final /* synthetic */ EntryPoint f9075a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC1402i(EntryPoint entryPoint) {
        this.f9075a = entryPoint;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SPUtil.m20317a();
        String a = SPUtil.m20315a("refer");
        if (!TextUtils.isEmpty(a)) {
            try {
                JSONArray jSONArray = new JSONArray(a);
                if (jSONArray.length() > 0) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        this.f9075a.m20297a(jSONArray.get(i).toString(), i, 2);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
