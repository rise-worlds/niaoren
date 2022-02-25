package p110z1;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.ix */
/* loaded from: classes3.dex */
public final class ASCIIEncoder implements AbstractC5379jd {
    @Override // p110z1.AbstractC5379jd
    /* renamed from: a */
    public final int mo2275a() {
        return 0;
    }

    /* renamed from: a */
    private static char m2364a(char c, char c2) {
        if (C5380jg.m2308a(c) && C5380jg.m2308a(c2)) {
            return (char) (((c - '0') * 10) + (c2 - '0') + 130);
        }
        throw new IllegalArgumentException("not digits: " + c + c2);
    }

    @Override // p110z1.AbstractC5379jd
    /* renamed from: a */
    public final void mo2273a(EncoderContext jeVar) {
        int i;
        String str = jeVar.f22078a;
        int i2 = jeVar.f22083f;
        int length = str.length();
        if (i2 < length) {
            char charAt = str.charAt(i2);
            i = 0;
            while (C5380jg.m2308a(charAt) && i2 < length) {
                i++;
                i2++;
                if (i2 < length) {
                    charAt = str.charAt(i2);
                }
            }
        } else {
            i = 0;
        }
        if (i >= 2) {
            char charAt2 = jeVar.f22078a.charAt(jeVar.f22083f);
            char charAt3 = jeVar.f22078a.charAt(jeVar.f22083f + 1);
            if (!C5380jg.m2308a(charAt2) || !C5380jg.m2308a(charAt3)) {
                throw new IllegalArgumentException("not digits: " + charAt2 + charAt3);
            }
            jeVar.m2331a((char) (((charAt2 - '0') * 10) + (charAt3 - '0') + 130));
            jeVar.f22083f += 2;
            return;
        }
        char a = jeVar.m2332a();
        int a2 = C5380jg.m2305a(jeVar.f22078a, jeVar.f22083f, 0);
        if (a2 != 0) {
            switch (a2) {
                case 1:
                    jeVar.m2331a((char) 230);
                    jeVar.f22084g = 1;
                    return;
                case 2:
                    jeVar.m2331a((char) 239);
                    jeVar.f22084g = 2;
                    return;
                case 3:
                    jeVar.m2331a((char) 238);
                    jeVar.f22084g = 3;
                    return;
                case 4:
                    jeVar.m2331a((char) 240);
                    jeVar.f22084g = 4;
                    return;
                case 5:
                    jeVar.m2331a((char) 231);
                    jeVar.f22084g = 5;
                    return;
                default:
                    throw new IllegalStateException("Illegal mode: ".concat(String.valueOf(a2)));
            }
        } else if (C5380jg.m2300b(a)) {
            jeVar.m2331a((char) 235);
            jeVar.m2331a((char) ((a - 128) + 1));
            jeVar.f22083f++;
        } else {
            jeVar.m2331a((char) (a + 1));
            jeVar.f22083f++;
        }
    }
}
