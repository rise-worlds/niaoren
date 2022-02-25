package com.tendcloud.tenddata;

import android.net.Proxy;
import com.cyjh.mobileanjian.ipc.AppAgent;
import com.liulishuo.filedownloader.model.ConnectionModel;
import java.util.ArrayList;
import java.util.UUID;
import org.apache.tools.ant.taskdefs.optional.vss.MSVSSConstants;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.iy */
/* loaded from: classes2.dex */
public class C2992iy extends AbstractC2984iq {

    /* renamed from: a */
    private C2880fk f14288a;

    /* renamed from: c */
    private String f14289c;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public C2992iy(EnumC2995ja jaVar) {
        this.f14289c = UUID.randomUUID().toString();
        try {
            switch (C2993iz.f14290x4bb14de0[jaVar.ordinal()]) {
                case 1:
                    m15410a("type", EnumC2995ja.WIFI.m15388a());
                    m15410a("available", Boolean.valueOf(C2836ec.m15867e(C2664ab.f13513g)));
                    if (C2836ec.m15862j(C2664ab.f13513g)) {
                        m15410a("connected", (Object) true);
                        JSONArray a = C2913gm.m15591a().m15583a(AppAgent.f8192d, C2998jc.m15383a().f14301a, C2998jc.m15383a().f14302b);
                        if (a != null) {
                            m15410a(MSVSSConstants.TIME_CURRENT, C2836ec.m15897A(C2664ab.f13513g));
                            if (a.length() > 0) {
                                C2998jc.m15383a().m15379a(AppAgent.f8192d, a);
                            }
                        } else {
                            m15409a(MSVSSConstants.TIME_CURRENT, this.f14268b);
                        }
                        JSONArray B = C2836ec.m15896B(C2664ab.f13513g);
                        C2880fk a2 = m15389a(B);
                        if (this.f14288a == null) {
                            m15410a("scannable", B);
                            this.f14288a = a2;
                        } else if (new C2881fl().m15714a(this.f14288a, a2) > 0.8d) {
                            m15410a("scannable", (Object) null);
                        } else {
                            m15410a("scannable", B);
                            this.f14288a = a2;
                            this.f14289c = UUID.randomUUID().toString();
                        }
                        JSONArray a3 = C2913gm.m15591a().m15583a(AppAgent.f8192d, C2998jc.m15383a().f14301a, C2998jc.m15383a().f14302b);
                        if (a3 != null) {
                            m15410a("configured", C2836ec.m15846z(C2664ab.f13513g));
                            if (a3.length() > 0) {
                                C2998jc.m15383a().m15379a(AppAgent.f8192d, a3);
                            }
                        } else {
                            m15409a("configured", this.f14268b);
                        }
                        JSONArray a4 = C2913gm.m15591a().m15583a("IP", C2998jc.m15383a().f14301a, C2998jc.m15383a().f14302b);
                        if (a4 != null) {
                            m15410a("ip", C2836ec.m15873b(C2664ab.f13513g));
                            if (a4.length() > 0) {
                                C2998jc.m15383a().m15379a("IP", a4);
                            }
                        } else {
                            m15409a("ip", this.f14268b);
                        }
                    } else {
                        m15410a("connected", (Object) false);
                    }
                    if (C2836ec.m15887a()) {
                        m15410a("proxy", Proxy.getDefaultHost() + ":" + Proxy.getDefaultPort());
                    }
                    m15410a("scannableFingerId", this.f14289c);
                    return;
                case 2:
                    m15410a("type", EnumC2995ja.CELLULAR.m15388a());
                    m15410a("available", Boolean.valueOf(C2836ec.m15866f(C2664ab.f13513g)));
                    m15410a("connected", Boolean.valueOf(C2836ec.m15861k(C2664ab.f13513g)));
                    if (C2836ec.m15869c(C2664ab.f13513g)) {
                        m15410a(MSVSSConstants.TIME_CURRENT, C2836ec.m15883a(C2664ab.f13513g, false));
                    }
                    if (C2836ec.m15887a()) {
                        m15410a("proxy", Proxy.getDefaultHost() + ":" + Proxy.getDefaultPort());
                    }
                    m15410a("scannable", C2836ec.m15848x(C2664ab.f13513g));
                    return;
                case 3:
                    m15410a("type", EnumC2995ja.BLUETOOTH.m15388a());
                    return;
                default:
                    return;
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    private static C2880fk m15389a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                arrayList.add(new C2871fg(jSONObject.getString("name"), jSONObject.getString(ConnectionModel.f10389a), (byte) jSONObject.getInt("level"), (byte) 0, (byte) 0));
            } catch (Throwable th) {
                C2811dq.eForInternal(th);
            }
        }
        C2880fk fkVar = new C2880fk();
        fkVar.setBsslist(arrayList);
        return fkVar;
    }
}
