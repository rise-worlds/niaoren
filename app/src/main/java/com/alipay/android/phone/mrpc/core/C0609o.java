package com.alipay.android.phone.mrpc.core;

import com.liulishuo.filedownloader.model.ConnectionModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;

/* renamed from: com.alipay.android.phone.mrpc.core.o */
/* loaded from: classes.dex */
public final class C0609o extends AbstractC0614t {

    /* renamed from: b */
    private String f171b;

    /* renamed from: c */
    private byte[] f172c;

    /* renamed from: g */
    private boolean f176g;

    /* renamed from: e */
    private ArrayList<Header> f174e = new ArrayList<>();

    /* renamed from: f */
    private Map<String, String> f175f = new HashMap();

    /* renamed from: d */
    private String f173d = "application/x-www-form-urlencoded";

    public C0609o(String str) {
        this.f171b = str;
    }

    /* renamed from: a */
    public final String m25483a() {
        return this.f171b;
    }

    /* renamed from: a */
    public final void m25482a(String str) {
        this.f173d = str;
    }

    /* renamed from: a */
    public final void m25481a(String str, String str2) {
        if (this.f175f == null) {
            this.f175f = new HashMap();
        }
        this.f175f.put(str, str2);
    }

    /* renamed from: a */
    public final void m25480a(Header header) {
        this.f174e.add(header);
    }

    /* renamed from: a */
    public final void m25479a(boolean z) {
        this.f176g = z;
    }

    /* renamed from: a */
    public final void m25478a(byte[] bArr) {
        this.f172c = bArr;
    }

    /* renamed from: b */
    public final String m25476b(String str) {
        Map<String, String> map = this.f175f;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    /* renamed from: b */
    public final byte[] m25477b() {
        return this.f172c;
    }

    /* renamed from: c */
    public final String m25475c() {
        return this.f173d;
    }

    /* renamed from: d */
    public final ArrayList<Header> m25474d() {
        return this.f174e;
    }

    /* renamed from: e */
    public final boolean m25473e() {
        return this.f176g;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C0609o oVar = (C0609o) obj;
        byte[] bArr = this.f172c;
        if (bArr == null) {
            if (oVar.f172c != null) {
                return false;
            }
        } else if (!bArr.equals(oVar.f172c)) {
            return false;
        }
        String str = this.f171b;
        if (str == null) {
            if (oVar.f171b != null) {
                return false;
            }
        } else if (!str.equals(oVar.f171b)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        Map<String, String> map = this.f175f;
        int hashCode = ((map == null || !map.containsKey(ConnectionModel.f10389a)) ? 1 : this.f175f.get(ConnectionModel.f10389a).hashCode() + 31) * 31;
        String str = this.f171b;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public final String toString() {
        return String.format("Url : %s,HttpHeader: %s", this.f171b, this.f174e);
    }
}
