package p110z1;

import java.util.Arrays;
import p110z1.Log;

/* compiled from: Logger.java */
/* renamed from: z1.ui */
/* loaded from: classes3.dex */
public class C5570ui {

    /* renamed from: a */
    private static final String f23456a = "{}";

    /* renamed from: b */
    private static final int f23457b = 2;

    /* renamed from: c */
    private static final Object f23458c = new Object();

    /* renamed from: d */
    private final Log f23459d;

    public C5570ui(Log uhVar) {
        this.f23459d = uhVar;
    }

    /* renamed from: a */
    public boolean m608a(Log.EnumC5569a aVar) {
        return this.f23459d.mo623a(aVar);
    }

    /* renamed from: a */
    public void m620a(String str) {
        Log.EnumC5569a aVar = Log.EnumC5569a.TRACE;
        Object obj = f23458c;
        m598a(aVar, null, str, obj, obj, obj, null);
    }

    /* renamed from: a */
    public void m619a(String str, Object obj) {
        Log.EnumC5569a aVar = Log.EnumC5569a.TRACE;
        Object obj2 = f23458c;
        m598a(aVar, null, str, obj, obj2, obj2, null);
    }

    /* renamed from: a */
    public void m618a(String str, Object obj, Object obj2) {
        m598a(Log.EnumC5569a.TRACE, null, str, obj, obj2, f23458c, null);
    }

    /* renamed from: a */
    public void m617a(String str, Object obj, Object obj2, Object obj3) {
        m598a(Log.EnumC5569a.TRACE, null, str, obj, obj2, obj3, null);
    }

    /* renamed from: a */
    public void m615a(String str, Object[] objArr) {
        Log.EnumC5569a aVar = Log.EnumC5569a.TRACE;
        Object obj = f23458c;
        m598a(aVar, null, str, obj, obj, obj, objArr);
    }

    /* renamed from: a */
    public void m613a(Throwable th, String str) {
        Log.EnumC5569a aVar = Log.EnumC5569a.TRACE;
        Object obj = f23458c;
        m598a(aVar, th, str, obj, obj, obj, null);
    }

    /* renamed from: a */
    public void m612a(Throwable th, String str, Object obj) {
        Log.EnumC5569a aVar = Log.EnumC5569a.TRACE;
        Object obj2 = f23458c;
        m598a(aVar, th, str, obj, obj2, obj2, null);
    }

    /* renamed from: a */
    public void m611a(Throwable th, String str, Object obj, Object obj2) {
        m598a(Log.EnumC5569a.TRACE, th, str, obj, obj2, f23458c, null);
    }

    /* renamed from: a */
    public void m610a(Throwable th, String str, Object obj, Object obj2, Object obj3) {
        m598a(Log.EnumC5569a.TRACE, th, str, obj, obj2, obj3, null);
    }

    /* renamed from: a */
    public void m609a(Throwable th, String str, Object[] objArr) {
        Log.EnumC5569a aVar = Log.EnumC5569a.TRACE;
        Object obj = f23458c;
        m598a(aVar, th, str, obj, obj, obj, objArr);
    }

    /* renamed from: b */
    public void m596b(String str) {
        Log.EnumC5569a aVar = Log.EnumC5569a.DEBUG;
        Object obj = f23458c;
        m598a(aVar, null, str, obj, obj, obj, null);
    }

    /* renamed from: b */
    public void m595b(String str, Object obj) {
        Log.EnumC5569a aVar = Log.EnumC5569a.DEBUG;
        Object obj2 = f23458c;
        m598a(aVar, null, str, obj, obj2, obj2, null);
    }

    /* renamed from: b */
    public void m594b(String str, Object obj, Object obj2) {
        m598a(Log.EnumC5569a.DEBUG, null, str, obj, obj2, f23458c, null);
    }

    /* renamed from: b */
    public void m593b(String str, Object obj, Object obj2, Object obj3) {
        m598a(Log.EnumC5569a.DEBUG, null, str, obj, obj2, obj3, null);
    }

    /* renamed from: b */
    public void m592b(String str, Object[] objArr) {
        Log.EnumC5569a aVar = Log.EnumC5569a.DEBUG;
        Object obj = f23458c;
        m598a(aVar, null, str, obj, obj, obj, objArr);
    }

    /* renamed from: b */
    public void m591b(Throwable th, String str) {
        Log.EnumC5569a aVar = Log.EnumC5569a.DEBUG;
        Object obj = f23458c;
        m598a(aVar, th, str, obj, obj, obj, null);
    }

    /* renamed from: b */
    public void m590b(Throwable th, String str, Object obj) {
        Log.EnumC5569a aVar = Log.EnumC5569a.DEBUG;
        Object obj2 = f23458c;
        m598a(aVar, th, str, obj, obj2, obj2, null);
    }

    /* renamed from: b */
    public void m589b(Throwable th, String str, Object obj, Object obj2) {
        m598a(Log.EnumC5569a.DEBUG, th, str, obj, obj2, f23458c, null);
    }

    /* renamed from: b */
    public void m588b(Throwable th, String str, Object obj, Object obj2, Object obj3) {
        m598a(Log.EnumC5569a.DEBUG, th, str, obj, obj2, obj3, null);
    }

    /* renamed from: b */
    public void m587b(Throwable th, String str, Object[] objArr) {
        Log.EnumC5569a aVar = Log.EnumC5569a.DEBUG;
        Object obj = f23458c;
        m598a(aVar, th, str, obj, obj, obj, objArr);
    }

    /* renamed from: c */
    public void m586c(String str) {
        Log.EnumC5569a aVar = Log.EnumC5569a.INFO;
        Object obj = f23458c;
        m598a(aVar, null, str, obj, obj, obj, null);
    }

    /* renamed from: c */
    public void m585c(String str, Object obj) {
        Log.EnumC5569a aVar = Log.EnumC5569a.INFO;
        Object obj2 = f23458c;
        m598a(aVar, null, str, obj, obj2, obj2, null);
    }

    /* renamed from: c */
    public void m584c(String str, Object obj, Object obj2) {
        m598a(Log.EnumC5569a.INFO, null, str, obj, obj2, f23458c, null);
    }

    /* renamed from: c */
    public void m583c(String str, Object obj, Object obj2, Object obj3) {
        m598a(Log.EnumC5569a.INFO, null, str, obj, obj2, obj3, null);
    }

    /* renamed from: c */
    public void m582c(String str, Object[] objArr) {
        Log.EnumC5569a aVar = Log.EnumC5569a.INFO;
        Object obj = f23458c;
        m598a(aVar, null, str, obj, obj, obj, objArr);
    }

    /* renamed from: c */
    public void m581c(Throwable th, String str) {
        Log.EnumC5569a aVar = Log.EnumC5569a.INFO;
        Object obj = f23458c;
        m598a(aVar, th, str, obj, obj, obj, null);
    }

    /* renamed from: c */
    public void m580c(Throwable th, String str, Object obj) {
        Log.EnumC5569a aVar = Log.EnumC5569a.INFO;
        Object obj2 = f23458c;
        m598a(aVar, th, str, obj, obj2, obj2, null);
    }

    /* renamed from: c */
    public void m579c(Throwable th, String str, Object obj, Object obj2) {
        m598a(Log.EnumC5569a.INFO, th, str, obj, obj2, f23458c, null);
    }

    /* renamed from: c */
    public void m578c(Throwable th, String str, Object obj, Object obj2, Object obj3) {
        m598a(Log.EnumC5569a.INFO, th, str, obj, obj2, obj3, null);
    }

    /* renamed from: c */
    public void m577c(Throwable th, String str, Object[] objArr) {
        Log.EnumC5569a aVar = Log.EnumC5569a.INFO;
        Object obj = f23458c;
        m598a(aVar, th, str, obj, obj, obj, objArr);
    }

    /* renamed from: d */
    public void m576d(String str) {
        Log.EnumC5569a aVar = Log.EnumC5569a.WARNING;
        Object obj = f23458c;
        m598a(aVar, null, str, obj, obj, obj, null);
    }

    /* renamed from: d */
    public void m575d(String str, Object obj) {
        Log.EnumC5569a aVar = Log.EnumC5569a.WARNING;
        Object obj2 = f23458c;
        m598a(aVar, null, str, obj, obj2, obj2, null);
    }

    /* renamed from: d */
    public void m574d(String str, Object obj, Object obj2) {
        m598a(Log.EnumC5569a.WARNING, null, str, obj, obj2, f23458c, null);
    }

    /* renamed from: d */
    public void m573d(String str, Object obj, Object obj2, Object obj3) {
        m598a(Log.EnumC5569a.WARNING, null, str, obj, obj2, obj3, null);
    }

    /* renamed from: d */
    public void m572d(String str, Object[] objArr) {
        Log.EnumC5569a aVar = Log.EnumC5569a.WARNING;
        Object obj = f23458c;
        m598a(aVar, null, str, obj, obj, obj, objArr);
    }

    /* renamed from: d */
    public void m571d(Throwable th, String str) {
        Log.EnumC5569a aVar = Log.EnumC5569a.WARNING;
        Object obj = f23458c;
        m598a(aVar, th, str, obj, obj, obj, null);
    }

    /* renamed from: d */
    public void m570d(Throwable th, String str, Object obj) {
        Log.EnumC5569a aVar = Log.EnumC5569a.WARNING;
        Object obj2 = f23458c;
        m598a(aVar, th, str, obj, obj2, obj2, null);
    }

    /* renamed from: d */
    public void m569d(Throwable th, String str, Object obj, Object obj2) {
        m598a(Log.EnumC5569a.WARNING, th, str, obj, obj2, f23458c, null);
    }

    /* renamed from: d */
    public void m568d(Throwable th, String str, Object obj, Object obj2, Object obj3) {
        m598a(Log.EnumC5569a.WARNING, th, str, obj, obj2, obj3, null);
    }

    /* renamed from: d */
    public void m567d(Throwable th, String str, Object[] objArr) {
        Log.EnumC5569a aVar = Log.EnumC5569a.WARNING;
        Object obj = f23458c;
        m598a(aVar, th, str, obj, obj, obj, objArr);
    }

    /* renamed from: e */
    public void m566e(String str) {
        Log.EnumC5569a aVar = Log.EnumC5569a.ERROR;
        Object obj = f23458c;
        m598a(aVar, null, str, obj, obj, obj, null);
    }

    /* renamed from: e */
    public void m565e(String str, Object obj) {
        Log.EnumC5569a aVar = Log.EnumC5569a.ERROR;
        Object obj2 = f23458c;
        m598a(aVar, null, str, obj, obj2, obj2, null);
    }

    /* renamed from: e */
    public void m564e(String str, Object obj, Object obj2) {
        m598a(Log.EnumC5569a.ERROR, null, str, obj, obj2, f23458c, null);
    }

    /* renamed from: e */
    public void m563e(String str, Object obj, Object obj2, Object obj3) {
        m598a(Log.EnumC5569a.ERROR, null, str, obj, obj2, obj3, null);
    }

    /* renamed from: e */
    public void m562e(String str, Object[] objArr) {
        Log.EnumC5569a aVar = Log.EnumC5569a.ERROR;
        Object obj = f23458c;
        m598a(aVar, null, str, obj, obj, obj, objArr);
    }

    /* renamed from: e */
    public void m561e(Throwable th, String str) {
        Log.EnumC5569a aVar = Log.EnumC5569a.ERROR;
        Object obj = f23458c;
        m598a(aVar, th, str, obj, obj, obj, null);
    }

    /* renamed from: e */
    public void m560e(Throwable th, String str, Object obj) {
        Log.EnumC5569a aVar = Log.EnumC5569a.ERROR;
        Object obj2 = f23458c;
        m598a(aVar, th, str, obj, obj2, obj2, null);
    }

    /* renamed from: e */
    public void m559e(Throwable th, String str, Object obj, Object obj2) {
        m598a(Log.EnumC5569a.ERROR, th, str, obj, obj2, f23458c, null);
    }

    /* renamed from: e */
    public void m558e(Throwable th, String str, Object obj, Object obj2, Object obj3) {
        m598a(Log.EnumC5569a.ERROR, th, str, obj, obj2, obj3, null);
    }

    /* renamed from: e */
    public void m557e(Throwable th, String str, Object[] objArr) {
        Log.EnumC5569a aVar = Log.EnumC5569a.ERROR;
        Object obj = f23458c;
        m598a(aVar, th, str, obj, obj, obj, objArr);
    }

    /* renamed from: f */
    public void m556f(String str) {
        Log.EnumC5569a aVar = Log.EnumC5569a.FATAL;
        Object obj = f23458c;
        m598a(aVar, null, str, obj, obj, obj, null);
    }

    /* renamed from: f */
    public void m555f(String str, Object obj) {
        Log.EnumC5569a aVar = Log.EnumC5569a.FATAL;
        Object obj2 = f23458c;
        m598a(aVar, null, str, obj, obj2, obj2, null);
    }

    /* renamed from: f */
    public void m554f(String str, Object obj, Object obj2) {
        m598a(Log.EnumC5569a.FATAL, null, str, obj, obj2, f23458c, null);
    }

    /* renamed from: f */
    public void m553f(String str, Object obj, Object obj2, Object obj3) {
        m598a(Log.EnumC5569a.FATAL, null, str, obj, obj2, obj3, null);
    }

    /* renamed from: f */
    public void m552f(String str, Object[] objArr) {
        Log.EnumC5569a aVar = Log.EnumC5569a.FATAL;
        Object obj = f23458c;
        m598a(aVar, null, str, obj, obj, obj, objArr);
    }

    /* renamed from: f */
    public void m551f(Throwable th, String str) {
        Log.EnumC5569a aVar = Log.EnumC5569a.FATAL;
        Object obj = f23458c;
        m598a(aVar, th, str, obj, obj, obj, null);
    }

    /* renamed from: f */
    public void m550f(Throwable th, String str, Object obj) {
        Log.EnumC5569a aVar = Log.EnumC5569a.FATAL;
        Object obj2 = f23458c;
        m598a(aVar, th, str, obj, obj2, obj2, null);
    }

    /* renamed from: f */
    public void m549f(Throwable th, String str, Object obj, Object obj2) {
        m598a(Log.EnumC5569a.FATAL, th, str, obj, obj2, f23458c, null);
    }

    /* renamed from: f */
    public void m548f(Throwable th, String str, Object obj, Object obj2, Object obj3) {
        m598a(Log.EnumC5569a.FATAL, th, str, obj, obj2, obj3, null);
    }

    /* renamed from: f */
    public void m547f(Throwable th, String str, Object[] objArr) {
        Log.EnumC5569a aVar = Log.EnumC5569a.FATAL;
        Object obj = f23458c;
        m598a(aVar, th, str, obj, obj, obj, objArr);
    }

    /* renamed from: a */
    public void m607a(Log.EnumC5569a aVar, String str) {
        Object obj = f23458c;
        m598a(aVar, null, str, obj, obj, obj, null);
    }

    /* renamed from: a */
    public void m606a(Log.EnumC5569a aVar, String str, Object obj) {
        Object obj2 = f23458c;
        m598a(aVar, null, str, obj, obj2, obj2, null);
    }

    /* renamed from: a */
    public void m605a(Log.EnumC5569a aVar, String str, Object obj, Object obj2) {
        m598a(aVar, null, str, obj, obj2, f23458c, null);
    }

    /* renamed from: a */
    public void m604a(Log.EnumC5569a aVar, String str, Object obj, Object obj2, Object obj3) {
        m598a(aVar, null, str, obj, obj2, obj3, null);
    }

    /* renamed from: a */
    public void m603a(Log.EnumC5569a aVar, String str, Object[] objArr) {
        Object obj = f23458c;
        m598a(aVar, null, str, obj, obj, obj, objArr);
    }

    /* renamed from: a */
    public void m602a(Log.EnumC5569a aVar, Throwable th, String str) {
        Object obj = f23458c;
        m598a(aVar, th, str, obj, obj, obj, null);
    }

    /* renamed from: a */
    public void m601a(Log.EnumC5569a aVar, Throwable th, String str, Object obj) {
        Object obj2 = f23458c;
        m598a(aVar, th, str, obj, obj2, obj2, null);
    }

    /* renamed from: a */
    public void m600a(Log.EnumC5569a aVar, Throwable th, String str, Object obj, Object obj2) {
        m598a(aVar, th, str, obj, obj2, f23458c, null);
    }

    /* renamed from: a */
    public void m599a(Log.EnumC5569a aVar, Throwable th, String str, Object obj, Object obj2, Object obj3) {
        m598a(aVar, th, str, obj, obj2, obj3, null);
    }

    /* renamed from: a */
    public void m597a(Log.EnumC5569a aVar, Throwable th, String str, Object[] objArr) {
        Object obj = f23458c;
        m598a(aVar, th, str, obj, obj, obj, objArr);
    }

    /* renamed from: a */
    private void m598a(Log.EnumC5569a aVar, Throwable th, String str, Object obj, Object obj2, Object obj3, Object[] objArr) {
        if (this.f23459d.mo623a(aVar)) {
            String a = m616a(str, obj, obj2, obj3, objArr);
            if (th == null) {
                this.f23459d.mo622a(aVar, a);
            } else {
                this.f23459d.mo621a(aVar, a, th);
            }
        }
    }

    /* renamed from: a */
    private String m616a(String str, Object obj, Object obj2, Object obj3, Object[] objArr) {
        StringBuilder sb = new StringBuilder(128);
        int i = 0;
        int i2 = 0;
        while (true) {
            int indexOf = str.indexOf(f23456a, i);
            if (indexOf == -1) {
                sb.append(str.substring(i));
                return sb.toString();
            }
            sb.append(str.substring(i, indexOf));
            i = f23457b + indexOf;
            if (objArr == null) {
                if (i2 == 0) {
                    m614a(sb, obj);
                } else if (i2 == 1) {
                    m614a(sb, obj2);
                } else if (i2 == 2) {
                    m614a(sb, obj3);
                }
            } else if (i2 < objArr.length) {
                m614a(sb, objArr[i2]);
            }
            i2++;
        }
    }

    /* renamed from: a */
    private void m614a(StringBuilder sb, Object obj) {
        if (obj != f23458c) {
            if (obj == null) {
                sb.append("null");
            } else if (obj.getClass().isArray()) {
                sb.append(Arrays.toString((Object[]) obj));
            } else {
                sb.append(obj);
            }
        }
    }
}
