package p110z1;

/* renamed from: z1.hj */
/* loaded from: classes3.dex */
public abstract class ParsedResult {

    /* renamed from: a */
    private final int f21877a;

    /* renamed from: a */
    public abstract String mo2565a();

    /* JADX INFO: Access modifiers changed from: protected */
    public ParsedResult(int i) {
        this.f21877a = i;
    }

    /* renamed from: b */
    private int m2595b() {
        return this.f21877a;
    }

    public final String toString() {
        return mo2565a();
    }

    /* renamed from: a */
    public static void m2597a(String str, StringBuilder sb) {
        if (str != null && !str.isEmpty()) {
            if (sb.length() > 0) {
                sb.append('\n');
            }
            sb.append(str);
        }
    }

    /* renamed from: a */
    public static void m2596a(String[] strArr, StringBuilder sb) {
        if (strArr != null) {
            for (String str : strArr) {
                m2597a(str, sb);
            }
        }
    }
}
