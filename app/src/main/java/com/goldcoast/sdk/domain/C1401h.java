package com.goldcoast.sdk.domain;

import android.os.ConditionVariable;
import android.text.TextUtils;
import com.goldcoast.sdk.p052c.AESUtil;
import com.goldcoast.sdk.p052c.LogUtil;
import com.p018b.p019a.Callback;
import com.p018b.p019a.Response;
import java.io.IOException;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: EntryPoint.java */
/* renamed from: com.goldcoast.sdk.domain.h */
/* loaded from: classes.dex */
public final class C1401h implements Callback {

    /* renamed from: a */
    final /* synthetic */ int f9070a;

    /* renamed from: b */
    final /* synthetic */ String f9071b;

    /* renamed from: c */
    final /* synthetic */ ConditionVariable f9072c;

    /* renamed from: d */
    final /* synthetic */ int f9073d;

    /* renamed from: e */
    final /* synthetic */ EntryPoint f9074e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C1401h(EntryPoint entryPoint, int i, String str, ConditionVariable conditionVariable, int i2) {
        this.f9074e = entryPoint;
        this.f9070a = i;
        this.f9071b = str;
        this.f9072c = conditionVariable;
        this.f9073d = i2;
    }

    @Override // com.p018b.p019a.Callback
    /* renamed from: a */
    public final void mo20275a(Response asVar) {
        String d = asVar.m24449e().m24428d();
        HashMap hashMap = new HashMap();
        LogUtil.m20321a();
        LogUtil.m20318b("######\n" + String.format("<feedBackP.Enc>:%s\n", d));
        if (!TextUtils.isEmpty(d) || this.f9070a != 1) {
            String str = null;
            try {
                str = AESUtil.m20344b(new JSONObject(d).optString("body", ""));
            } catch (JSONException e) {
                hashMap.put("message", this.f9074e.getStackString(e));
            }
            if (!TextUtils.isEmpty(str) || asVar.m24452b() == 200 || this.f9070a != 1) {
                try {
                    if (new JSONObject(str).optString("code", "").equals("200")) {
                        if (this.f9070a == 2) {
                            EntryPoint.m20311a(this.f9073d);
                        }
                    } else if (this.f9070a == 1) {
                        EntryPoint.m20299a(this.f9071b);
                        hashMap.put("message", "code not equals 200");
                    }
                } catch (Exception e2) {
                    hashMap.put("stack", this.f9074e.getStackString(e2));
                }
                this.f9072c.open();
                return;
            }
            EntryPoint.m20299a(this.f9071b);
            this.f9072c.open();
            return;
        }
        hashMap.put("message", "response is null");
        EntryPoint.m20299a(this.f9071b);
        this.f9072c.open();
    }

    @Override // com.p018b.p019a.Callback
    /* renamed from: a */
    public final void mo20274a(IOException iOException) {
        new HashMap();
        if (this.f9070a == 1) {
            EntryPoint.m20299a(this.f9071b);
        }
        EntryPoint entryPoint = this.f9074e;
        entryPoint.m20289b(entryPoint.getStackString(iOException), 3);
        this.f9072c.open();
    }
}
