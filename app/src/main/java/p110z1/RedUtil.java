package p110z1;

import com.redwas.bean.RedConfigResutlInfoItem;

/* renamed from: z1.aqs */
/* loaded from: classes3.dex */
public class RedUtil {

    /* renamed from: a */
    public static RedUtil f17397a;

    /* renamed from: b */
    public String f17398b = "com.tencent.mm:id/";

    /* renamed from: c */
    public String f17399c = "[微信红包]";

    /* renamed from: d */
    public String f17400d = this.f17398b + "e7t";

    /* renamed from: e */
    public String f17401e = this.f17398b + "av2";

    /* renamed from: f */
    public String f17402f = this.f17398b + "tt";

    /* renamed from: g */
    public String f17403g = this.f17398b + "f67";

    /* renamed from: h */
    public String f17404h = this.f17398b + "ahs";

    /* renamed from: i */
    public String f17405i = this.f17398b + "an3";

    /* renamed from: j */
    public String f17406j = this.f17398b + "deu";

    /* renamed from: k */
    public String f17407k = this.f17398b + "das";

    /* renamed from: l */
    public String f17408l = this.f17398b + "deo";

    /* renamed from: m */
    public String f17409m = this.f17398b + "f4f";

    /* renamed from: a */
    public static RedUtil m11452a() {
        if (f17397a == null) {
            f17397a = new RedUtil();
        }
        return f17397a;
    }

    /* renamed from: a */
    public void m11451a(RedConfigResutlInfoItem redConfigResutlInfoItem) {
        this.f17399c = redConfigResutlInfoItem.getNotificationKey();
        this.f17400d = this.f17398b + redConfigResutlInfoItem.getFindId();
        this.f17401e = this.f17398b + redConfigResutlInfoItem.getLastRedEnvelopeKey();
        this.f17402f = this.f17398b + redConfigResutlInfoItem.getRedEnvelopeOpenKey();
        this.f17403g = this.f17398b + redConfigResutlInfoItem.getRedEnvelopeOpenBackKey();
        this.f17404h = this.f17398b + redConfigResutlInfoItem.getRedEnvelopeDetailBackKey();
        this.f17405i = this.f17398b + redConfigResutlInfoItem.getRedEnvelopeBackKey();
        this.f17406j = this.f17398b + redConfigResutlInfoItem.getRedEnvelopeDetailTimeKey();
        this.f17407k = this.f17398b + redConfigResutlInfoItem.getRedEnvelopeDetailNameKey();
        this.f17408l = this.f17398b + redConfigResutlInfoItem.getRedEnvelopeDetailMoneyKey();
        this.f17409m = this.f17398b + redConfigResutlInfoItem.getRedEnvelopeOpensKey();
    }
}
