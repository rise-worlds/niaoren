package p110z1;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/* renamed from: z1.df */
/* loaded from: classes3.dex */
public final class C5260df {

    /* renamed from: a */
    private String f21294a;

    /* renamed from: b */
    private String f21295b;

    /* renamed from: c */
    private String f21296c;

    /* renamed from: d */
    private String f21297d;

    /* renamed from: e */
    private String f21298e;

    /* renamed from: f */
    private String f21299f;

    /* renamed from: g */
    private String f21300g;

    public C5260df(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.f21294a = str;
        this.f21295b = str2;
        this.f21296c = str3;
        this.f21297d = str4;
        this.f21298e = str5;
        this.f21299f = str6;
        this.f21300g = str7;
    }

    public final String toString() {
        String str;
        StringBuilder sb;
        String str2;
        StringBuilder sb2;
        String str3;
        StringBuilder sb3;
        StringBuffer stringBuffer = new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime()));
        stringBuffer.append("," + this.f21294a);
        stringBuffer.append("," + this.f21295b);
        stringBuffer.append("," + this.f21296c);
        stringBuffer.append("," + this.f21297d);
        if (C5097cq.m3699a(this.f21298e) || this.f21298e.length() < 20) {
            sb = new StringBuilder(",");
            str = this.f21298e;
        } else {
            sb = new StringBuilder(",");
            str = this.f21298e.substring(0, 20);
        }
        sb.append(str);
        stringBuffer.append(sb.toString());
        if (C5097cq.m3699a(this.f21299f) || this.f21299f.length() < 20) {
            sb2 = new StringBuilder(",");
            str2 = this.f21299f;
        } else {
            sb2 = new StringBuilder(",");
            str2 = this.f21299f.substring(0, 20);
        }
        sb2.append(str2);
        stringBuffer.append(sb2.toString());
        if (C5097cq.m3699a(this.f21300g) || this.f21300g.length() < 20) {
            sb3 = new StringBuilder(",");
            str3 = this.f21300g;
        } else {
            sb3 = new StringBuilder(",");
            str3 = this.f21300g.substring(0, 20);
        }
        sb3.append(str3);
        stringBuffer.append(sb3.toString());
        return stringBuffer.toString();
    }
}
