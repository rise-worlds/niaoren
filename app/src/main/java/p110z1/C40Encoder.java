package p110z1;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.iz */
/* loaded from: classes3.dex */
public class C40Encoder implements AbstractC5379jd {
    @Override // p110z1.AbstractC5379jd
    /* renamed from: a */
    public int mo2275a() {
        return 1;
    }

    @Override // p110z1.AbstractC5379jd
    /* renamed from: a */
    public void mo2273a(EncoderContext jeVar) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (!jeVar.m2326b()) {
                break;
            }
            char a = jeVar.m2332a();
            jeVar.f22083f++;
            int a2 = mo2274a(a, sb);
            int length = jeVar.f22082e.length() + ((sb.length() / 3) << 1);
            jeVar.m2330a(length);
            int i = jeVar.f22085h.f22114b - length;
            if (jeVar.m2326b()) {
                if (sb.length() % 3 == 0 && C5380jg.m2305a(jeVar.f22078a, jeVar.f22083f, mo2275a()) != mo2275a()) {
                    jeVar.f22084g = 0;
                    break;
                }
            } else {
                StringBuilder sb2 = new StringBuilder();
                if (sb.length() % 3 == 2 && (i < 2 || i > 2)) {
                    a2 = m2360a(jeVar, sb, sb2, a2);
                }
                while (sb.length() % 3 == 1 && ((a2 <= 3 && i != 1) || a2 > 3)) {
                    a2 = m2360a(jeVar, sb, sb2, a2);
                }
            }
        }
        mo2272b(jeVar, sb);
    }

    /* renamed from: a */
    private int m2360a(EncoderContext jeVar, StringBuilder sb, StringBuilder sb2, int i) {
        int length = sb.length();
        sb.delete(length - i, length);
        jeVar.f22083f--;
        int a = mo2274a(jeVar.m2332a(), sb2);
        jeVar.f22085h = null;
        return a;
    }

    /* renamed from: b */
    void mo2272b(EncoderContext jeVar, StringBuilder sb) {
        int length = sb.length() % 3;
        int length2 = jeVar.f22082e.length() + ((sb.length() / 3) << 1);
        jeVar.m2330a(length2);
        int i = jeVar.f22085h.f22114b - length2;
        if (length == 2) {
            sb.append((char) 0);
            while (sb.length() >= 3) {
                m2361a(jeVar, sb);
            }
            if (jeVar.m2326b()) {
                jeVar.m2331a((char) 254);
            }
        } else if (i == 1 && length == 1) {
            while (sb.length() >= 3) {
                m2361a(jeVar, sb);
            }
            if (jeVar.m2326b()) {
                jeVar.m2331a((char) 254);
            }
            jeVar.f22083f--;
        } else if (length == 0) {
            while (sb.length() >= 3) {
                m2361a(jeVar, sb);
            }
            if (i > 0 || jeVar.m2326b()) {
                jeVar.m2331a((char) 254);
            }
        } else {
            throw new IllegalStateException("Unexpected case. Please report!");
        }
        jeVar.f22084g = 0;
    }

    /* renamed from: a */
    int mo2274a(char c, StringBuilder sb) {
        if (c == ' ') {
            sb.append((char) 3);
            return 1;
        } else if (c >= '0' && c <= '9') {
            sb.append((char) ((c - '0') + 4));
            return 1;
        } else if (c >= 'A' && c <= 'Z') {
            sb.append((char) ((c - 'A') + 14));
            return 1;
        } else if (c < ' ') {
            sb.append((char) 0);
            sb.append(c);
            return 2;
        } else if (c >= '!' && c <= '/') {
            sb.append((char) 1);
            sb.append((char) (c - '!'));
            return 2;
        } else if (c >= ':' && c <= '@') {
            sb.append((char) 1);
            sb.append((char) ((c - ':') + 15));
            return 2;
        } else if (c >= '[' && c <= '_') {
            sb.append((char) 1);
            sb.append((char) ((c - '[') + 22));
            return 2;
        } else if (c < '`' || c > 127) {
            sb.append("\u0001\u001e");
            return mo2274a((char) (c - 128), sb) + 2;
        } else {
            sb.append((char) 2);
            sb.append((char) (c - '`'));
            return 2;
        }
    }

    /* renamed from: a */
    private static String m2362a(CharSequence charSequence) {
        int charAt = (charSequence.charAt(0) * 1600) + (charSequence.charAt(1) * '(') + charSequence.charAt(2) + 1;
        return new String(new char[]{(char) (charAt / 256), (char) (charAt % 256)});
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m2361a(EncoderContext jeVar, StringBuilder sb) {
        int charAt = (sb.charAt(0) * 1600) + (sb.charAt(1) * '(') + sb.charAt(2) + 1;
        jeVar.m2329a(new String(new char[]{(char) (charAt / 256), (char) (charAt % 256)}));
        sb.delete(0, 3);
    }
}
