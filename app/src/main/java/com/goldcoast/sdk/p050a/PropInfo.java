package com.goldcoast.sdk.p050a;

import android.os.Build;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import p110z1.SimpleComparison;

/* renamed from: com.goldcoast.sdk.a.f */
/* loaded from: classes.dex */
public final class PropInfo extends Entity<PropInfo> {

    /* renamed from: a */
    private String f8982a;

    /* renamed from: b */
    private String f8983b;

    /* renamed from: c */
    private String f8984c;

    /* renamed from: d */
    private String f8985d;

    /* renamed from: e */
    private String f8986e;

    public PropInfo() {
    }

    public PropInfo(String[] strArr) {
        if (strArr != null) {
            Map a = m20365a(strArr);
            Iterator it = a.keySet().iterator();
            if (it != null && it.hasNext()) {
                while (it.hasNext()) {
                    String str = (String) it.next();
                    if (str.equals(".vendor")) {
                        this.f8982a = (String) a.get(str);
                    } else if (str.equals(".product")) {
                        this.f8983b = (String) a.get(str);
                    } else if (str.equals(".uname_r")) {
                        this.f8984c = (String) a.get(str);
                    } else if (str.equals(".uname_v")) {
                        this.f8985d = (String) a.get(str);
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public static String[] m20367a() {
        String[] strArr = new String[4];
        strArr[0] = ".vendor=" + Build.BRAND;
        strArr[1] = ".product=" + Build.MODEL;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/version"), 8192);
            String readLine = bufferedReader.readLine();
            String[] split = readLine.split("\\s+");
            strArr[2] = ".uname_v=" + readLine.substring(readLine.lastIndexOf("#"), readLine.length());
            strArr[3] = ".uname_r=" + split[2];
            bufferedReader.close();
        } catch (IOException unused) {
        }
        return strArr;
    }

    /* renamed from: a */
    public final void m20366a(String str) {
        this.f8986e = str;
    }

    /* renamed from: b */
    public final JSONObject m20364b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(".vendor", this.f8982a);
            jSONObject.put(".product", this.f8983b);
            jSONObject.put(".uname_r", this.f8984c);
            jSONObject.put(".uname_v", this.f8985d);
            jSONObject.put(".uname_v_utc", this.f8986e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    /* renamed from: a */
    private static Map m20365a(String[] strArr) {
        HashMap hashMap = new HashMap();
        for (String str : strArr) {
            try {
                if (str.contains(SimpleComparison.f23609c)) {
                    String[] split = str.split(SimpleComparison.f23609c);
                    hashMap.put(split[0].trim(), split[1].trim());
                }
            } catch (Exception unused) {
            }
        }
        return hashMap;
    }
}
