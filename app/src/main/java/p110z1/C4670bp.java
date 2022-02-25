package p110z1;

import android.content.Context;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import p110z1.C4268bf;

/* renamed from: z1.bp */
/* loaded from: classes3.dex */
public class C4670bp extends AbstractC4442bk {
    @Override // p110z1.AbstractC4442bk
    /* renamed from: a */
    protected String mo9547a(C4745bt btVar, String str, JSONObject jSONObject) {
        return str;
    }

    @Override // p110z1.AbstractC4442bk
    /* renamed from: a */
    protected JSONObject mo9540a() {
        return null;
    }

    @Override // p110z1.AbstractC4442bk
    /* renamed from: a */
    protected Map<String, String> mo9546a(boolean z, String str) {
        return new HashMap();
    }

    @Override // p110z1.AbstractC4442bk
    /* renamed from: a */
    public C4330bh mo9548a(C4745bt btVar, Context context, String str) throws Throwable {
        C4921cd.m5616b(C3876ar.f17447x, "mdap post");
        byte[] a = C4044ba.m9795a(str.getBytes(Charset.forName("UTF-8")));
        HashMap hashMap = new HashMap();
        hashMap.put("utdId", C4759bu.m9273a().m9268e());
        hashMap.put("logHeader", "RAW");
        hashMap.put("bizCode", "alipaysdk");
        hashMap.put("productId", "alipaysdk_android");
        hashMap.put("Content-Encoding", "Gzip");
        hashMap.put("productVersion", "15.7.3");
        C4268bf.C4270b a2 = C4268bf.m9708a(context, new C4268bf.C4269a(C3876ar.f17427d, hashMap, a));
        C4921cd.m5616b(C3876ar.f17447x, "mdap got " + a2);
        if (a2 != null) {
            boolean a3 = m9632a(a2);
            try {
                byte[] bArr = a2.f18561c;
                if (a3) {
                    bArr = C4044ba.m9794b(bArr);
                }
                return new C4330bh("", new String(bArr, Charset.forName("UTF-8")));
            } catch (Exception e) {
                C4921cd.m5618a(e);
                return null;
            }
        } else {
            throw new RuntimeException("Response is null");
        }
    }
}
