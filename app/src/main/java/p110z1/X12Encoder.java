package p110z1;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.jk */
/* loaded from: classes3.dex */
public final class X12Encoder extends C40Encoder {
    @Override // p110z1.C40Encoder, p110z1.AbstractC5379jd
    /* renamed from: a */
    public final int mo2275a() {
        return 3;
    }

    @Override // p110z1.C40Encoder, p110z1.AbstractC5379jd
    /* renamed from: a */
    public final void mo2273a(EncoderContext jeVar) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (!jeVar.m2326b()) {
                break;
            }
            char a = jeVar.m2332a();
            jeVar.f22083f++;
            mo2274a(a, sb);
            if (sb.length() % 3 == 0) {
                m2361a(jeVar, sb);
                if (C5380jg.m2305a(jeVar.f22078a, jeVar.f22083f, 3) != 3) {
                    jeVar.f22084g = 0;
                    break;
                }
            }
        }
        mo2272b(jeVar, sb);
    }

    @Override // p110z1.C40Encoder
    /* renamed from: a */
    final int mo2274a(char c, StringBuilder sb) {
        if (c == '\r') {
            sb.append((char) 0);
        } else if (c == ' ') {
            sb.append((char) 3);
        } else if (c == '*') {
            sb.append((char) 1);
        } else if (c == '>') {
            sb.append((char) 2);
        } else if (c >= '0' && c <= '9') {
            sb.append((char) ((c - '0') + 4));
        } else if (c < 'A' || c > 'Z') {
            C5380jg.m2299c(c);
        } else {
            sb.append((char) ((c - 'A') + 14));
        }
        return 1;
    }

    @Override // p110z1.C40Encoder
    /* renamed from: b */
    final void mo2272b(EncoderContext jeVar, StringBuilder sb) {
        jeVar.m2323d();
        int length = jeVar.f22085h.f22114b - jeVar.f22082e.length();
        jeVar.f22083f -= sb.length();
        if (jeVar.m2324c() > 1 || length > 1 || jeVar.m2324c() != length) {
            jeVar.m2331a((char) 254);
        }
        if (jeVar.f22084g < 0) {
            jeVar.f22084g = 0;
        }
    }
}
