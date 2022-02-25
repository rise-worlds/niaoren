package com.tendcloud.tenddata;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import java.util.ArrayList;
import org.apache.tools.ant.taskdefs.WaitFor;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.hj */
/* loaded from: classes2.dex */
final class C2941hj extends BroadcastReceiver {

    /* renamed from: b */
    ArrayList f14153b;

    /* renamed from: c */
    JSONArray f14154c;

    /* renamed from: d */
    C2880fk f14155d;

    /* renamed from: e */
    C2880fk f14156e;

    /* renamed from: i */
    private WifiManager f14160i;

    /* renamed from: a */
    C2881fl f14152a = new C2881fl();

    /* renamed from: f */
    long f14157f = 0;

    /* renamed from: g */
    long f14158g = 0;

    /* renamed from: h */
    private long f14159h = WaitFor.DEFAULT_MAX_WAIT_MILLIS;

    public C2941hj(WifiManager wifiManager) {
        this.f14160i = wifiManager;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        C2924gv.f14122a.post(new RunnableC2942hk(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m15511a() {
        try {
            C2947ho hoVar = new C2947ho();
            hoVar.f14181b = "env";
            hoVar.f14182c = "wifiUpdate";
            hoVar.f14180a = AbstractC2790d.ENV;
            C2858ev.m15778a().post(hoVar);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public C2880fk m15508b() {
        try {
            this.f14155d = m15509a(this.f14154c);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
        return this.f14155d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public C2880fk m15506c() {
        try {
            this.f14153b = (ArrayList) this.f14160i.getScanResults();
            if (this.f14153b != null) {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < this.f14153b.size(); i++) {
                    if (((ScanResult) this.f14153b.get(i)).level >= -75) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("SSID", ((ScanResult) this.f14153b.get(i)).SSID);
                        jSONObject.put("BSSID", ((ScanResult) this.f14153b.get(i)).BSSID);
                        jSONObject.put("level", ((ScanResult) this.f14153b.get(i)).level);
                        jSONArray.put(jSONObject);
                    }
                }
                this.f14154c = jSONArray;
                this.f14156e = m15509a(jSONArray);
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
        return this.f14156e;
    }

    /* renamed from: a */
    private C2880fk m15509a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                arrayList.add(new C2871fg(jSONObject.getString("SSID"), jSONObject.getString("BSSID"), (byte) jSONObject.getInt("level"), (byte) 0, (byte) 0));
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
        C2880fk fkVar = new C2880fk();
        fkVar.setBsslist(arrayList);
        return fkVar;
    }
}
