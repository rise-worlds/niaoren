package p110z1;

import android.util.Log;
import java.io.PrintStream;
import java.util.logging.Level;

/* renamed from: z1.czj */
/* loaded from: classes3.dex */
public interface Logger {
    /* renamed from: a */
    void mo3399a(Level level, String str);

    /* renamed from: a */
    void mo3398a(Level level, String str, Throwable th);

    /* compiled from: Logger.java */
    /* renamed from: z1.czj$a */
    /* loaded from: classes3.dex */
    public static class C5216a implements Logger {

        /* renamed from: a */
        static final boolean f21161a;

        /* renamed from: b */
        private final String f21162b;

        static {
            boolean z = false;
            try {
                if (Class.forName("android.util.Log") != null) {
                    z = true;
                }
            } catch (ClassNotFoundException unused) {
            }
            f21161a = z;
        }

        /* renamed from: a */
        public static boolean m3401a() {
            return f21161a;
        }

        public C5216a(String str) {
            this.f21162b = str;
        }

        @Override // p110z1.Logger
        /* renamed from: a */
        public void mo3399a(Level level, String str) {
            if (level != Level.OFF) {
                Log.println(m3400a(level), this.f21162b, str);
            }
        }

        @Override // p110z1.Logger
        /* renamed from: a */
        public void mo3398a(Level level, String str, Throwable th) {
            if (level != Level.OFF) {
                int a = m3400a(level);
                String str2 = this.f21162b;
                Log.println(a, str2, str + "\n" + Log.getStackTraceString(th));
            }
        }

        /* renamed from: a */
        protected int m3400a(Level level) {
            int intValue = level.intValue();
            if (intValue < 800) {
                return intValue < 500 ? 2 : 3;
            }
            if (intValue < 900) {
                return 4;
            }
            return intValue < 1000 ? 5 : 6;
        }
    }

    /* compiled from: Logger.java */
    /* renamed from: z1.czj$b */
    /* loaded from: classes3.dex */
    public static class C5217b implements Logger {

        /* renamed from: a */
        protected final java.util.logging.Logger f21163a;

        public C5217b(String str) {
            this.f21163a = java.util.logging.Logger.getLogger(str);
        }

        @Override // p110z1.Logger
        /* renamed from: a */
        public void mo3399a(Level level, String str) {
            this.f21163a.log(level, str);
        }

        @Override // p110z1.Logger
        /* renamed from: a */
        public void mo3398a(Level level, String str, Throwable th) {
            this.f21163a.log(level, str, th);
        }
    }

    /* compiled from: Logger.java */
    /* renamed from: z1.czj$c */
    /* loaded from: classes3.dex */
    public static class C5218c implements Logger {
        @Override // p110z1.Logger
        /* renamed from: a */
        public void mo3399a(Level level, String str) {
            PrintStream printStream = System.out;
            printStream.println("[" + level + "] " + str);
        }

        @Override // p110z1.Logger
        /* renamed from: a */
        public void mo3398a(Level level, String str, Throwable th) {
            PrintStream printStream = System.out;
            printStream.println("[" + level + "] " + str);
            th.printStackTrace(System.out);
        }
    }
}
