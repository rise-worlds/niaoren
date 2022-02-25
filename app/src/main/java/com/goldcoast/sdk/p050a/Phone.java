package com.goldcoast.sdk.p050a;

import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import p110z1.acf;

/* renamed from: com.goldcoast.sdk.a.e */
/* loaded from: classes.dex */
public final class Phone extends Entity<Phone> {

    /* renamed from: a */
    private String f8957a;

    /* renamed from: b */
    private String f8958b;

    /* renamed from: c */
    private String f8959c;

    /* renamed from: d */
    private String f8960d;

    /* renamed from: e */
    private String f8961e;

    /* renamed from: f */
    private String f8962f;

    /* renamed from: g */
    private String f8963g;

    /* renamed from: h */
    private String f8964h;

    /* renamed from: i */
    private String f8965i;

    /* renamed from: j */
    private String f8966j;

    /* renamed from: k */
    private String f8967k;

    /* renamed from: l */
    private String f8968l;

    /* renamed from: m */
    private String f8969m;

    /* renamed from: n */
    private String f8970n;

    /* renamed from: o */
    private String f8971o;

    /* renamed from: p */
    private String f8972p;

    /* renamed from: q */
    private String f8973q;

    /* renamed from: r */
    private String f8974r;

    /* renamed from: s */
    private String f8975s;

    /* renamed from: t */
    private String f8976t;

    /* renamed from: u */
    private String f8977u;

    /* renamed from: v */
    private String f8978v;

    /* renamed from: w */
    private String f8979w;

    /* renamed from: x */
    private String f8980x;

    /* renamed from: y */
    private String f8981y;

    public Phone() {
    }

    public Phone(Map<String, String> map) {
        Iterator<String> it = map.keySet().iterator();
        if (it != null && it.hasNext()) {
            while (it.hasNext()) {
                String next = it.next();
                if (next.equals("ro.product.brand")) {
                    this.f8957a = map.get(next);
                } else if (next.equals("ro.product.name")) {
                    this.f8958b = map.get(next);
                } else if (next.equals("ro.product.model")) {
                    this.f8959c = map.get(next);
                } else if (next.equals("ro.build.fingerprint")) {
                    this.f8960d = map.get(next);
                } else if (next.equals("ro.build.version.sdk")) {
                    this.f8961e = map.get(next);
                } else if (next.equals("ro.build.version.release")) {
                    this.f8962f = map.get(next);
                } else if (next.equals("ro.build.date")) {
                    this.f8963g = map.get(next);
                } else if (next.equals("ro.build.date.utc")) {
                    this.f8964h = map.get(next);
                } else if (next.equals("ro.boot.cpuid")) {
                    this.f8965i = map.get(next);
                } else if (next.equals("ro.btconfig.vendor")) {
                    this.f8966j = map.get(next);
                } else if (next.equals("persist.sys.timezone")) {
                    this.f8967k = map.get(next);
                } else if (next.equals("persist.sys.country")) {
                    this.f8968l = map.get(next);
                } else if (next.equals("persist.sys.language")) {
                    this.f8969m = map.get(next);
                } else if (next.equals("persist.sys.dalvik.vm.lib")) {
                    this.f8970n = map.get(next);
                } else if (next.equals("ro.build.description")) {
                    this.f8971o = map.get(next);
                } else if (next.equals("ro.runtime.firstboot")) {
                    this.f8972p = map.get(next);
                } else if (next.equals("ro.serialno")) {
                    this.f8973q = map.get(next);
                } else if (next.equals(acf.f15167F)) {
                    this.f8974r = map.get(next);
                } else if (next.equals(acf.f15168G)) {
                    this.f8975s = map.get(next);
                } else if (next.equals("ro.product.locale.language")) {
                    this.f8976t = map.get(next);
                } else if (next.equals("ro.product.locale.region")) {
                    this.f8977u = map.get(next);
                } else if (next.equals("ro.product.cpu.abi")) {
                    this.f8978v = map.get(next);
                } else if (next.equals(acf.f15169H)) {
                    this.f8979w = map.get(next);
                } else if (next.equals("ro.build.selinux")) {
                    this.f8980x = map.get(next);
                } else if (next.equals("ro.build.selinux.enforce")) {
                    this.f8981y = map.get(next);
                }
            }
        }
    }

    /* renamed from: a */
    public final JSONObject m20369a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ro.product.brand", this.f8957a);
            jSONObject.put("ro.product.name", this.f8958b);
            jSONObject.put("ro.product.model", this.f8959c);
            jSONObject.put("ro.build.fingerprint", this.f8960d);
            jSONObject.put("ro.build.version.sdk", this.f8961e);
            jSONObject.put("ro.build.version.release", this.f8962f);
            jSONObject.put("ro.build.date", this.f8963g);
            jSONObject.put("ro.build.date.utc", this.f8964h);
            jSONObject.put("ro.boot.cpuid", this.f8965i);
            jSONObject.put("ro.btconfig.vendor", this.f8966j);
            jSONObject.put("persist.sys.timezone", this.f8967k);
            jSONObject.put("persist.sys.country", this.f8968l);
            jSONObject.put("persist.sys.language", this.f8969m);
            jSONObject.put("persist.sys.dalvik.vm.lib", this.f8970n);
            jSONObject.put("ro.build.description", this.f8971o);
            jSONObject.put("ro.runtime.firstboot", this.f8972p);
            jSONObject.put("ro.serialno", this.f8973q);
            jSONObject.put(acf.f15167F, this.f8974r);
            jSONObject.put(acf.f15168G, this.f8975s);
            jSONObject.put("ro.product.locale.language", this.f8976t);
            jSONObject.put("ro.product.locale.region", this.f8977u);
            jSONObject.put("ro.product.cpu.abi", this.f8978v);
            jSONObject.put(acf.f15169H, this.f8979w);
            jSONObject.put("ro.build.selinux", this.f8980x);
            jSONObject.put("ro.build.selinux.enforce", this.f8981y);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    /* renamed from: b */
    public final String m20368b() {
        return this.f8964h;
    }
}
