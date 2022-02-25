package com.goldcoast.sdk.p050a;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.goldcoast.sdk.a.a */
/* loaded from: classes.dex */
public final class CPUInfo extends Entity<CPUInfo> {

    /* renamed from: a */
    private String f8930a = ResultTypeConstant.f7213z;

    /* renamed from: b */
    private String f8931b;

    /* renamed from: c */
    private String f8932c;

    /* renamed from: d */
    private String f8933d;

    /* renamed from: e */
    private String f8934e;

    /* renamed from: f */
    private String f8935f;

    /* renamed from: g */
    private String f8936g;

    /* renamed from: h */
    private String f8937h;

    /* renamed from: i */
    private String f8938i;

    /* renamed from: j */
    private String f8939j;

    /* renamed from: k */
    private String f8940k;

    public CPUInfo() {
    }

    public CPUInfo(Map map) {
        m20390a(map);
    }

    /* renamed from: a */
    private CPUInfo m20390a(Map<String, String> map) {
        Iterator<String> it = map.keySet().iterator();
        if (it == null || !it.hasNext()) {
            return null;
        }
        while (it.hasNext()) {
            String next = it.next();
            if (next.replace(ExpandableTextView.f6958c, "").toLowerCase().contains("processorcnt")) {
                this.f8930a = map.get(next);
            } else if (next.replace(ExpandableTextView.f6958c, "").toLowerCase().contains("modelname")) {
                this.f8931b = map.get(next);
            } else if (next.replace(ExpandableTextView.f6958c, "").toLowerCase().contains("features")) {
                this.f8932c = map.get(next);
            } else if (next.replace(ExpandableTextView.f6958c, "").toLowerCase().contains("cpuimplementer")) {
                this.f8933d = map.get(next);
            } else if (next.replace(ExpandableTextView.f6958c, "").toLowerCase().contains("cpuarchitecture")) {
                this.f8934e = map.get(next);
            } else if (next.replace(ExpandableTextView.f6958c, "").toLowerCase().contains("cpuvariant")) {
                this.f8935f = map.get(next);
            } else if (next.replace(ExpandableTextView.f6958c, "").toLowerCase().contains("cpupart")) {
                this.f8936g = map.get(next);
            } else if (next.replace(ExpandableTextView.f6958c, "").toLowerCase().contains("cpurevision")) {
                this.f8937h = map.get(next);
            } else if (next.replace(ExpandableTextView.f6958c, "").toLowerCase().contains("hardware")) {
                this.f8938i = map.get(next);
            } else if (next.replace(ExpandableTextView.f6958c, "").toLowerCase().contains("revision")) {
                this.f8939j = map.get(next);
            } else if (next.replace(ExpandableTextView.f6958c, "").toLowerCase().contains("serial")) {
                this.f8940k = map.get(next);
            }
        }
        try {
            this.f8930a = String.valueOf(Integer.valueOf(this.f8930a).intValue() + 1);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return this;
    }

    /* renamed from: a */
    public final JSONObject m20391a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("processorcnt", this.f8930a == null ? "" : this.f8930a);
            jSONObject.put("modelname", this.f8931b == null ? "" : this.f8931b);
            jSONObject.put("features", this.f8932c == null ? "" : this.f8932c);
            jSONObject.put("cpuimplementer", this.f8933d == null ? "" : this.f8933d);
            jSONObject.put("cpuarchitecture", this.f8934e == null ? "" : this.f8934e);
            jSONObject.put("cpuvariant", this.f8935f == null ? "" : this.f8935f);
            jSONObject.put("cpupart", this.f8936g == null ? "" : this.f8936g);
            jSONObject.put("cpurevision", this.f8937h == null ? "" : this.f8937h);
            jSONObject.put("hardware", this.f8938i == null ? "" : this.f8938i);
            jSONObject.put("revision", this.f8939j == null ? "" : this.f8939j);
            jSONObject.put("serial", this.f8940k == null ? "" : this.f8940k);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
