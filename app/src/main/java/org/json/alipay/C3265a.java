package org.json.alipay;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

/* renamed from: org.json.alipay.a */
/* loaded from: classes2.dex */
public class C3265a {

    /* renamed from: a */
    private ArrayList f14853a;

    public C3265a() {
        this.f14853a = new ArrayList();
    }

    public C3265a(Object obj) {
        this();
        if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                this.f14853a.add(Array.get(obj, i));
            }
            return;
        }
        throw new JSONException("JSONArray initial value should be a string or collection or array.");
    }

    public C3265a(String str) {
        this(new C3268c(str));
    }

    public C3265a(Collection collection) {
        this.f14853a = collection == null ? new ArrayList() : new ArrayList(collection);
    }

    public C3265a(C3268c cVar) {
        this();
        char c;
        Object obj;
        ArrayList arrayList;
        char c2 = cVar.m14748c();
        if (c2 == '[') {
            c = ']';
        } else if (c2 == '(') {
            c = ')';
        } else {
            throw cVar.m14750a("A JSONArray text must start with '['");
        }
        if (cVar.m14748c() != ']') {
            do {
                cVar.m14752a();
                if (cVar.m14748c() == ',') {
                    cVar.m14752a();
                    arrayList = this.f14853a;
                    obj = null;
                } else {
                    cVar.m14752a();
                    arrayList = this.f14853a;
                    obj = cVar.m14747d();
                }
                arrayList.add(obj);
                char c3 = cVar.m14748c();
                if (c3 != ')') {
                    if (c3 != ',' && c3 != ';') {
                        if (c3 != ']') {
                            throw cVar.m14750a("Expected a ',' or ']'");
                        }
                    }
                }
                if (c != c3) {
                    throw cVar.m14750a("Expected a '" + new Character(c) + "'");
                }
                return;
            } while (cVar.m14748c() != ']');
        }
    }

    /* renamed from: a */
    private String m14759a(String str) {
        int size = this.f14853a.size();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                stringBuffer.append(str);
            }
            stringBuffer.append(C3266b.m14757a(this.f14853a.get(i)));
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public final int m14761a() {
        return this.f14853a.size();
    }

    /* renamed from: a */
    public final Object m14760a(int i) {
        Object obj = (i < 0 || i >= this.f14853a.size()) ? null : this.f14853a.get(i);
        if (obj != null) {
            return obj;
        }
        throw new JSONException("JSONArray[" + i + "] not found.");
    }

    public String toString() {
        try {
            return "[" + m14759a(",") + ']';
        } catch (Exception unused) {
            return null;
        }
    }
}
