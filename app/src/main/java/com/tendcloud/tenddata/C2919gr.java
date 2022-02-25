package com.tendcloud.tenddata;

import android.support.p003v4.app.NotificationCompat;
import android.util.Log;
import com.stripe.android.PaymentResultListener;
import com.tendcloud.tenddata.C3034zz;
import java.lang.Thread;
import java.util.HashMap;
import java.util.TreeMap;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.gr */
/* loaded from: classes2.dex */
public class C2919gr {

    /* renamed from: a */
    private static volatile C2919gr f14118a;

    /* renamed from: a */
    public static C2919gr m15562a() {
        if (f14118a == null) {
            synchronized (C2919gr.class) {
                if (f14118a == null) {
                    f14118a = new C2919gr();
                }
            }
        }
        return f14118a;
    }

    private C2919gr() {
        m15557b();
    }

    static {
        try {
            C2858ev.m15778a().register(m15562a());
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    void m15558a(HashMap hashMap) {
        try {
            AbstractC2790d dVar = (AbstractC2790d) hashMap.get(NotificationCompat.CATEGORY_SERVICE);
            if (hashMap.containsKey("throwable") && hashMap.containsKey("occurTime")) {
                m15559a((Throwable) hashMap.get("throwable"), String.valueOf(hashMap.get("occurTime")), dVar, false);
            }
        } catch (Throwable unused) {
        }
    }

    public final void onTDEBEventError(C3034zz.C3035a aVar) {
        if (aVar != null) {
            try {
                if (aVar.paraMap != null && Integer.parseInt(String.valueOf(aVar.paraMap.get("apiType"))) == 3) {
                    m15558a(aVar.paraMap);
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: b */
    private static void m15557b() {
        try {
            Thread.setDefaultUncaughtExceptionHandler(new C2920a());
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.gr$a */
    /* loaded from: classes2.dex */
    public static class C2920a implements Thread.UncaughtExceptionHandler {
        private Thread.UncaughtExceptionHandler appDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();

        C2920a() {
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            if (C2664ab.f13511e) {
                C2919gr.m15559a(th, String.valueOf(System.currentTimeMillis()), AbstractC2790d.APP, true);
                Log.w(C2664ab.f13525s, "UncaughtException in Thread " + thread.getName(), th);
            }
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.appDefaultHandler;
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.uncaughtException(thread, th);
            }
        }
    }

    /* renamed from: a */
    static void m15559a(Throwable th, String str, AbstractC2790d dVar, boolean z) {
        if (C2664ab.f13513g != null) {
            Throwable th2 = th;
            while (th2.getCause() != null) {
                try {
                    th2 = th2.getCause();
                } catch (Throwable unused) {
                    return;
                }
            }
            StackTraceElement[] stackTrace = th2.getStackTrace();
            StringBuilder sb = new StringBuilder();
            sb.append(th2.getClass().getName());
            sb.append(":");
            String packageName = C2664ab.f13513g.getPackageName();
            int i = 0;
            for (int i2 = 0; i < 3 && i2 < stackTrace.length; i2++) {
                String className = stackTrace[i2].getClassName();
                if ((!className.startsWith("java.") || packageName.startsWith("java.")) && ((!className.startsWith("javax.") || packageName.startsWith("javax.")) && ((!className.startsWith("android.") || packageName.startsWith("android.")) && (!className.startsWith("com.android.") || packageName.startsWith("com.android."))))) {
                    sb.append(stackTrace[i2].toString());
                    sb.append(":");
                    i++;
                }
            }
            long currentTimeMillis = str.trim().isEmpty() ? System.currentTimeMillis() : Long.valueOf(str).longValue();
            C2947ho hoVar = new C2947ho();
            hoVar.f14181b = "apm";
            hoVar.f14182c = PaymentResultListener.f11903c;
            TreeMap treeMap = new TreeMap();
            treeMap.put("msg", m15560a(th));
            treeMap.put("type", th2.getClass().getName());
            hoVar.f14183d = treeMap;
            hoVar.f14180a = AbstractC2790d.APP;
            C2858ev.m15778a().post(hoVar);
            if (z && C2664ab.f13487D) {
                C2812dr.m16020c(currentTimeMillis, dVar);
            }
        }
    }

    /* renamed from: a */
    private static final String m15560a(Throwable th) {
        StringBuilder sb = new StringBuilder();
        sb.append(th.toString());
        sb.append("\r\n");
        try {
            StackTraceElement[] stackTrace = th.getStackTrace();
            int i = 50;
            if (stackTrace.length <= 50) {
                i = stackTrace.length;
            }
            for (int i2 = 0; i2 < i; i2++) {
                sb.append("\tat ");
                sb.append(stackTrace[i2]);
                sb.append("\r\n");
            }
            Throwable cause = th.getCause();
            if (cause != null) {
                m15561a(sb, stackTrace, cause, 1);
            }
        } catch (Throwable unused) {
        }
        return sb.toString();
    }

    /* renamed from: a */
    private static final void m15561a(StringBuilder sb, StackTraceElement[] stackTraceElementArr, Throwable th, int i) {
        try {
            StackTraceElement[] stackTrace = th.getStackTrace();
            int length = stackTrace.length - 1;
            for (int length2 = stackTraceElementArr.length - 1; length >= 0 && length2 >= 0 && stackTrace[length].equals(stackTraceElementArr[length2]); length2--) {
                length--;
            }
            int i2 = 50;
            if (length <= 50) {
                i2 = length;
            }
            sb.append("Caused by : ");
            sb.append(th);
            sb.append("\r\n");
            for (int i3 = 0; i3 <= i2; i3++) {
                sb.append("\t");
                sb.append(stackTrace[i3]);
                sb.append("\r\n");
            }
            if (i < 5 && th.getCause() != null) {
                m15561a(sb, stackTrace, th, i + 1);
            }
        } catch (Throwable unused) {
        }
    }
}
