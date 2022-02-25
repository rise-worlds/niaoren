package com.alipay.android.phone.mrpc.core.p010a;

import com.alipay.android.phone.mrpc.core.RpcException;
import java.lang.reflect.Type;
import org.json.JSONObject;
import p110z1.C3336ad;
import p110z1.C4985cm;

/* renamed from: com.alipay.android.phone.mrpc.core.a.d */
/* loaded from: classes.dex */
public final class C0586d extends AbstractC0583a {
    public C0586d(Type type, byte[] bArr) {
        super(type, bArr);
    }

    @Override // com.alipay.android.phone.mrpc.core.p010a.AbstractC0585c
    /* renamed from: a */
    public final Object mo25520a() {
        try {
            String str = new String(this.f128b);
            StringBuilder sb = new StringBuilder("threadid = ");
            sb.append(Thread.currentThread().getId());
            sb.append("; rpc response:  ");
            sb.append(str);
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt(C4985cm.f20831a);
            if (i == 1000) {
                return this.f127a == String.class ? jSONObject.optString(C4985cm.f20833c) : C3336ad.m14324a(jSONObject.optString(C4985cm.f20833c), this.f127a);
            }
            throw new RpcException(Integer.valueOf(i), jSONObject.optString("tips"));
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder("response  =");
            sb2.append(new String(this.f128b));
            sb2.append(":");
            sb2.append(e);
            throw new RpcException((Integer) 10, sb2.toString() == null ? "" : e.getMessage());
        }
    }
}
