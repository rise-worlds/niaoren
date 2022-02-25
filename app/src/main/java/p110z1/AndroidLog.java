package p110z1;

import p110z1.Log;

/* renamed from: z1.rn */
/* loaded from: classes3.dex */
public class AndroidLog implements Log {

    /* renamed from: a */
    private static final String f23150a = "ORMLite";

    /* renamed from: b */
    private static final int f23151b = 200;

    /* renamed from: c */
    private static final int f23152c = 23;

    /* renamed from: d */
    private String f23153d;

    /* renamed from: e */
    private volatile int f23154e = 0;

    /* renamed from: f */
    private final boolean[] f23155f;

    public AndroidLog(String str) {
        this.f23153d = LoggerFactory.m543b(str);
        int length = this.f23153d.length();
        if (length > 23) {
            this.f23153d = this.f23153d.substring(length - 23, length);
        }
        int i = 0;
        for (Log.EnumC5569a aVar : Log.EnumC5569a.values()) {
            int b = m1138b(aVar);
            if (b > i) {
                i = b;
            }
        }
        this.f23155f = new boolean[i + 1];
        m1140a();
    }

    @Override // p110z1.Log
    /* renamed from: a */
    public boolean mo623a(Log.EnumC5569a aVar) {
        int i = this.f23154e + 1;
        this.f23154e = i;
        if (i >= 200) {
            m1140a();
            this.f23154e = 0;
        }
        int b = m1138b(aVar);
        boolean[] zArr = this.f23155f;
        if (b < zArr.length) {
            return zArr[b];
        }
        return m1139a(b);
    }

    @Override // p110z1.Log
    /* renamed from: a */
    public void mo622a(Log.EnumC5569a aVar, String str) {
        switch (aVar) {
            case TRACE:
                android.util.Log.v(this.f23153d, str);
                return;
            case DEBUG:
                android.util.Log.d(this.f23153d, str);
                return;
            case INFO:
                android.util.Log.i(this.f23153d, str);
                return;
            case WARNING:
                android.util.Log.w(this.f23153d, str);
                return;
            case ERROR:
                android.util.Log.e(this.f23153d, str);
                return;
            case FATAL:
                android.util.Log.e(this.f23153d, str);
                return;
            default:
                android.util.Log.i(this.f23153d, str);
                return;
        }
    }

    @Override // p110z1.Log
    /* renamed from: a */
    public void mo621a(Log.EnumC5569a aVar, String str, Throwable th) {
        switch (aVar) {
            case TRACE:
                android.util.Log.v(this.f23153d, str, th);
                return;
            case DEBUG:
                android.util.Log.d(this.f23153d, str, th);
                return;
            case INFO:
                android.util.Log.i(this.f23153d, str, th);
                return;
            case WARNING:
                android.util.Log.w(this.f23153d, str, th);
                return;
            case ERROR:
                android.util.Log.e(this.f23153d, str, th);
                return;
            case FATAL:
                android.util.Log.e(this.f23153d, str, th);
                return;
            default:
                android.util.Log.i(this.f23153d, str, th);
                return;
        }
    }

    /* renamed from: a */
    private void m1140a() {
        for (Log.EnumC5569a aVar : Log.EnumC5569a.values()) {
            int b = m1138b(aVar);
            boolean[] zArr = this.f23155f;
            if (b < zArr.length) {
                zArr[b] = m1139a(b);
            }
        }
    }

    /* renamed from: a */
    private boolean m1139a(int i) {
        return android.util.Log.isLoggable(this.f23153d, i) || android.util.Log.isLoggable(f23150a, i);
    }

    /* renamed from: b */
    private int m1138b(Log.EnumC5569a aVar) {
        switch (aVar) {
            case TRACE:
                return 2;
            case DEBUG:
                return 3;
            case INFO:
                return 4;
            case WARNING:
                return 5;
            case ERROR:
                return 6;
            case FATAL:
                return 6;
            default:
                return 4;
        }
    }
}
