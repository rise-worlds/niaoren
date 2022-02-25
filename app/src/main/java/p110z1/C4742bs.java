package p110z1;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* renamed from: z1.bs */
/* loaded from: classes3.dex */
public class C4742bs {

    /* renamed from: a */
    private EnumC4725br f20055a;

    /* renamed from: b */
    private String f20056b;

    /* renamed from: c */
    private String[] f20057c;

    public C4742bs(String str) {
        this.f20056b = str;
    }

    public C4742bs(String str, EnumC4725br brVar) {
        this.f20056b = str;
        this.f20055a = brVar;
    }

    /* renamed from: a */
    public static void m9460a(C4742bs bsVar) {
        String[] c = bsVar.m9457c();
        if (c.length == 3 && TextUtils.equals("tid", c[0])) {
            C4814by a = C4814by.m8377a(C4759bu.m9273a().m9271b());
            if (!TextUtils.isEmpty(c[1]) && !TextUtils.isEmpty(c[2])) {
                a.m8376a(c[1], c[2]);
            }
        }
    }

    /* renamed from: a */
    public static List<C4742bs> m9461a(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null) {
            return arrayList;
        }
        String[] b = m9458b(jSONObject.optString("name", ""));
        for (int i = 0; i < b.length; i++) {
            EnumC4725br a = EnumC4725br.m9495a(b[i]);
            if (a != EnumC4725br.None) {
                C4742bs bsVar = new C4742bs(b[i], a);
                bsVar.f20057c = m9462a(b[i]);
                arrayList.add(bsVar);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private static String[] m9462a(String str) {
        ArrayList arrayList = new ArrayList();
        int indexOf = str.indexOf(40);
        int lastIndexOf = str.lastIndexOf(41);
        if (indexOf == -1 || lastIndexOf == -1 || lastIndexOf <= indexOf) {
            return null;
        }
        for (String str2 : str.substring(indexOf + 1, lastIndexOf).split("' *, *'", -1)) {
            arrayList.add(str2.trim().replaceAll("'", "").replaceAll("\"", ""));
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    /* renamed from: b */
    private static String[] m9458b(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.split(C4963cj.f20745b);
        }
        return null;
    }

    /* renamed from: a */
    public String m9463a() {
        return this.f20056b;
    }

    /* renamed from: b */
    public EnumC4725br m9459b() {
        return this.f20055a;
    }

    /* renamed from: c */
    public String[] m9457c() {
        return this.f20057c;
    }
}
