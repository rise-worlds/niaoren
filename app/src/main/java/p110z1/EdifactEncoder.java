package p110z1;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.jc */
/* loaded from: classes3.dex */
public final class EdifactEncoder implements AbstractC5379jd {
    @Override // p110z1.AbstractC5379jd
    /* renamed from: a */
    public final int mo2275a() {
        return 4;
    }

    @Override // p110z1.AbstractC5379jd
    /* renamed from: a */
    public final void mo2273a(EncoderContext jeVar) {
        boolean z;
        StringBuilder sb = new StringBuilder();
        while (true) {
            z = true;
            if (!jeVar.m2326b()) {
                break;
            }
            char a = jeVar.m2332a();
            if (a >= ' ' && a <= '?') {
                sb.append(a);
            } else if (a < '@' || a > '^') {
                C5380jg.m2299c(a);
            } else {
                sb.append((char) (a - '@'));
            }
            jeVar.f22083f++;
            if (sb.length() >= 4) {
                jeVar.m2329a(m2334a(sb));
                sb.delete(0, 4);
                if (C5380jg.m2305a(jeVar.f22078a, jeVar.f22083f, 4) != 4) {
                    jeVar.f22084g = 0;
                    break;
                }
            }
        }
        sb.append((char) 31);
        try {
            int length = sb.length();
            if (length != 0) {
                if (length == 1) {
                    jeVar.m2323d();
                    int length2 = jeVar.f22085h.f22114b - jeVar.f22082e.length();
                    int c = jeVar.m2324c();
                    if (c > length2) {
                        jeVar.m2330a(jeVar.f22082e.length() + 1);
                        length2 = jeVar.f22085h.f22114b - jeVar.f22082e.length();
                    }
                    if (c <= length2 && length2 <= 2) {
                        return;
                    }
                }
                if (length <= 4) {
                    int i = length - 1;
                    String a2 = m2334a(sb);
                    if (!(!jeVar.m2326b()) || i > 2) {
                        z = false;
                    }
                    if (i <= 2) {
                        jeVar.m2330a(jeVar.f22082e.length() + i);
                        if (jeVar.f22085h.f22114b - jeVar.f22082e.length() >= 3) {
                            jeVar.m2330a(jeVar.f22082e.length() + a2.length());
                            z = false;
                        }
                    }
                    if (z) {
                        jeVar.f22085h = null;
                        jeVar.f22083f -= i;
                    } else {
                        jeVar.m2329a(a2);
                    }
                    return;
                }
                throw new IllegalStateException("Count must not exceed 4");
            }
        } finally {
            jeVar.f22084g = 0;
        }
    }

    /* renamed from: a */
    private static void m2333a(EncoderContext jeVar, CharSequence charSequence) {
        try {
            int length = charSequence.length();
            if (length != 0) {
                boolean z = true;
                if (length == 1) {
                    jeVar.m2323d();
                    int length2 = jeVar.f22085h.f22114b - jeVar.f22082e.length();
                    int c = jeVar.m2324c();
                    if (c > length2) {
                        jeVar.m2330a(jeVar.f22082e.length() + 1);
                        length2 = jeVar.f22085h.f22114b - jeVar.f22082e.length();
                    }
                    if (c <= length2 && length2 <= 2) {
                        return;
                    }
                }
                if (length <= 4) {
                    int i = length - 1;
                    String a = m2334a(charSequence);
                    if (!(!jeVar.m2326b()) || i > 2) {
                        z = false;
                    }
                    if (i <= 2) {
                        jeVar.m2330a(jeVar.f22082e.length() + i);
                        if (jeVar.f22085h.f22114b - jeVar.f22082e.length() >= 3) {
                            jeVar.m2330a(jeVar.f22082e.length() + a.length());
                            z = false;
                        }
                    }
                    if (z) {
                        jeVar.f22085h = null;
                        jeVar.f22083f -= i;
                    } else {
                        jeVar.m2329a(a);
                    }
                    return;
                }
                throw new IllegalStateException("Count must not exceed 4");
            }
        } finally {
            jeVar.f22084g = 0;
        }
    }

    /* renamed from: a */
    private static void m2335a(char c, StringBuilder sb) {
        if (c >= ' ' && c <= '?') {
            sb.append(c);
        } else if (c < '@' || c > '^') {
            C5380jg.m2299c(c);
        } else {
            sb.append((char) (c - '@'));
        }
    }

    /* renamed from: a */
    private static String m2334a(CharSequence charSequence) {
        char c = 0;
        int length = charSequence.length() - 0;
        if (length != 0) {
            char charAt = charSequence.charAt(0);
            char charAt2 = length >= 2 ? charSequence.charAt(1) : (char) 0;
            char charAt3 = length >= 3 ? charSequence.charAt(2) : (char) 0;
            if (length >= 4) {
                c = charSequence.charAt(3);
            }
            int i = (charAt << 18) + (charAt2 << '\f') + (charAt3 << 6) + c;
            char c2 = (char) ((i >> 8) & 255);
            char c3 = (char) (i & 255);
            StringBuilder sb = new StringBuilder(3);
            sb.append((char) ((i >> 16) & 255));
            if (length >= 2) {
                sb.append(c2);
            }
            if (length >= 3) {
                sb.append(c3);
            }
            return sb.toString();
        }
        throw new IllegalStateException("StringBuilder must not be empty");
    }
}
