package p110z1;

import java.io.File;
import java.io.IOException;

/* renamed from: z1.aek */
/* loaded from: classes3.dex */
public class LogCarshUtil {

    /* renamed from: a */
    static LogCarshUtil f15452a;

    /* renamed from: d */
    Runtime f15455d;

    /* renamed from: c */
    Process f15454c = null;

    /* renamed from: b */
    String f15453b = "/sdcard/log.txt";

    /* renamed from: a */
    public File m13954a() {
        if (aef.m14121f(this.f15453b)) {
            return aef.m14154a(this.f15453b);
        }
        return aef.m14154a(this.f15453b);
    }

    /* renamed from: b */
    public void m13953b() {
        new Thread(new Runnable() { // from class: z1.aek.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Runtime.getRuntime().exec("logcat -c ").waitFor();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
        }).start();
    }

    /* renamed from: c */
    public void m13952c() {
        Process process = this.f15454c;
        if (process != null) {
            process.destroy();
        }
    }

    /* renamed from: d */
    public static synchronized LogCarshUtil m13951d() {
        LogCarshUtil aekVar;
        synchronized (LogCarshUtil.class) {
            LogCarshUtil aekVar2 = f15452a;
            if (f15452a == null) {
                LogCarshUtil aekVar3 = f15452a;
                f15452a = new LogCarshUtil();
            }
            LogCarshUtil aekVar4 = f15452a;
            aekVar = f15452a;
        }
        return aekVar;
    }

    /* renamed from: e */
    public void m13950e() {
        new Thread(new Runnable() { // from class: z1.aek.2
            @Override // java.lang.Runnable
            public void run() {
                aef.m14115h(LogCarshUtil.this.f15453b);
                LogCarshUtil.this.f15455d = Runtime.getRuntime();
                LogCarshUtil aekVar = LogCarshUtil.this;
                aekVar.f15454c = null;
                try {
                    Runtime runtime = aekVar.f15455d;
                    aekVar.f15454c = runtime.exec("logcat -f " + LogCarshUtil.this.f15453b);
                    LogCarshUtil.this.f15454c.waitFor();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
        }).start();
    }
}
