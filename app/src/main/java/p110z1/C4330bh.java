package p110z1;

import android.text.TextUtils;
import org.json.JSONObject;

/* renamed from: z1.bh */
/* loaded from: classes3.dex */
public final class C4330bh {

    /* renamed from: a */
    private final String f18707a;

    /* renamed from: b */
    private final String f18708b;

    public C4330bh(String str, String str2) {
        this.f18707a = str;
        this.f18708b = str2;
    }

    /* renamed from: a */
    public String m9691a() {
        return this.f18707a;
    }

    /* renamed from: b */
    public String m9690b() {
        return this.f18708b;
    }

    /* renamed from: c */
    public JSONObject m9689c() {
        if (TextUtils.isEmpty(this.f18708b)) {
            return null;
        }
        try {
            return new JSONObject(this.f18708b);
        } catch (Exception e) {
            C4921cd.m5618a(e);
            return null;
        }
    }

    public String toString() {
        return String.format("<Letter envelop=%s body=%s>", this.f18707a, this.f18708b);
    }
}
