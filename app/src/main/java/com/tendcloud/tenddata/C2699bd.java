package com.tendcloud.tenddata;

import com.liulishuo.filedownloader.model.ConnectionModel;
import com.tendcloud.tenddata.AbstractC2732bp;
import com.tendcloud.tenddata.C2723bn;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import p110z1.C4985cm;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.bd */
/* loaded from: classes2.dex */
class C2699bd {

    /* renamed from: b */
    private static final Class[] f13563b = new Class[0];

    /* renamed from: c */
    private static final List f13564c = Collections.emptyList();

    /* renamed from: a */
    private final AbstractC2713bj f13565a;

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bd$a */
    /* loaded from: classes2.dex */
    static class C2700a {
        final List imageUrls;
        final AbstractC2732bp visitor;

        private C2700a(AbstractC2732bp bpVar, List list) {
            this.visitor = bpVar;
            this.imageUrls = list;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2699bd(AbstractC2713bj bjVar, AbstractC2732bp.AbstractC2741g gVar) {
        this.f13565a = bjVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public AbstractC2732bp m16269a(JSONObject jSONObject, AbstractC2732bp.AbstractC2740f fVar) {
        try {
            String string = jSONObject.getString("type");
            String string2 = jSONObject.getString(ConnectionModel.f10389a);
            List a = m16271a(jSONObject.getJSONArray("path"), this.f13565a);
            a.size();
            if ("click".equals(string)) {
                return new AbstractC2732bp.C2733a(a, 1, string2, fVar);
            }
            if ("selected".equals(string)) {
                return new AbstractC2732bp.C2733a(a, 4, string2, fVar);
            }
            if ("text_changed".equals(string)) {
                return new AbstractC2732bp.C2735b(a, string2, fVar);
            }
            if ("detected".equals(string)) {
                return new AbstractC2732bp.C2743i(a, string2, fVar);
            }
            return null;
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public C2727bo m16270a(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = jSONObject.getJSONObject("config").getJSONArray("classes");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                Class<?> cls = Class.forName(jSONObject2.getString("name"));
                JSONArray jSONArray2 = jSONObject2.getJSONArray("properties");
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    arrayList.add(m16272a(cls, jSONArray2.getJSONObject(i2)));
                }
            }
            return new C2727bo(arrayList, this.f13565a);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return null;
        }
    }

    /* renamed from: a */
    List m16271a(JSONArray jSONArray, AbstractC2713bj bjVar) {
        int i;
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i2);
            String a = m16268a(jSONObject, "prefix");
            String a2 = m16268a(jSONObject, "class");
            int optInt = jSONObject.optInt("index", -1);
            String a3 = m16268a(jSONObject, "description");
            int optInt2 = jSONObject.optInt(ConnectionModel.f10389a, -1);
            String a4 = m16268a(jSONObject, "id_name");
            String a5 = m16268a(jSONObject, "tag");
            if ("shortest".equals(a)) {
                i = 1;
            } else if (a != null) {
                return f13564c;
            } else {
                i = 0;
            }
            Integer a6 = m16273a(optInt2, a4, bjVar);
            if (a6 == null) {
                return f13564c;
            }
            arrayList.add(new C2723bn.C2726c(i, a2, optInt, a6.intValue(), a3, a5));
        }
        return arrayList;
    }

    /* renamed from: a */
    private Integer m16273a(int i, String str, AbstractC2713bj bjVar) {
        int i2;
        if (str == null) {
            i2 = -1;
        } else if (!bjVar.knownIdName(str)) {
            return null;
        } else {
            i2 = bjVar.idFromName(str);
        }
        if (-1 != i2 && -1 != i && i2 != i) {
            return null;
        }
        if (-1 != i2) {
            return Integer.valueOf(i2);
        }
        return Integer.valueOf(i);
    }

    /* renamed from: a */
    private C2701b m16272a(Class cls, JSONObject jSONObject) {
        C2712bi biVar;
        String str;
        try {
            String string = jSONObject.getString("name");
            if (jSONObject.has("get")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("get");
                biVar = new C2712bi(cls, jSONObject2.getString("selector"), f13563b, Class.forName(jSONObject2.getJSONObject(C4985cm.f20833c).getString("type")));
            } else {
                biVar = null;
            }
            if (jSONObject.has("set")) {
                str = jSONObject.getJSONObject("set").getString("selector");
            } else {
                str = null;
            }
            return new C2701b(string, cls, biVar, str);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bd$b */
    /* loaded from: classes2.dex */
    public class C2701b {
        final C2712bi accessor;
        private final String mMutatorName;
        public final String name;
        final Class targetClass;

        C2701b(String str, Class cls, C2712bi biVar, String str2) {
            this.name = str;
            this.targetClass = cls;
            this.accessor = biVar;
            this.mMutatorName = str2;
        }

        public C2712bi makeMutator(Object[] objArr) {
            String str = this.mMutatorName;
            if (str == null) {
                return null;
            }
            return new C2712bi(this.targetClass, str, objArr, Void.TYPE);
        }

        public String toString() {
            return "[PropertyDescription " + this.name + "," + this.targetClass + ", " + this.accessor + "/" + this.mMutatorName + "]";
        }
    }

    /* renamed from: a */
    private static String m16268a(JSONObject jSONObject, String str) {
        if (!jSONObject.has(str) || jSONObject.isNull(str)) {
            return null;
        }
        return jSONObject.getString(str);
    }
}
