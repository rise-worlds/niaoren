package com.cyjh.mobileanjian.ipc;

import com.alipay.sdk.widget.C0675j;
import com.cyjh.mobileanjian.ipc.interfaces.OnRootApplyCallback;
import com.cyjh.p045mq.p049d.C1363e;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* renamed from: com.cyjh.mobileanjian.ipc.d */
/* loaded from: classes.dex */
public final class RootShell {

    /* renamed from: a */
    public static RootShell f8232a = null;

    /* renamed from: h */
    private static final String f8233h = "echo \"rootOK\"\n";

    /* renamed from: b */
    OnRootApplyCallback f8234b = null;

    /* renamed from: c */
    public C1266a f8235c = null;

    /* renamed from: d */
    public boolean f8236d = false;

    /* renamed from: e */
    public DataOutputStream f8237e = null;

    /* renamed from: f */
    InputStream f8238f = null;

    /* renamed from: g */
    Process f8239g = null;

    /* renamed from: b */
    private boolean m21021b() {
        return this.f8236d;
    }

    /* renamed from: a */
    public final void m21024a(OnRootApplyCallback onRootApplyCallback) {
        if (this.f8236d) {
            onRootApplyCallback.onObtained();
            return;
        }
        this.f8234b = onRootApplyCallback;
        this.f8235c = new C1266a(this, (byte) 0);
        this.f8235c.start();
    }

    /* renamed from: a */
    public static synchronized RootShell m21029a() {
        RootShell dVar;
        synchronized (RootShell.class) {
            if (f8232a == null) {
                f8232a = new RootShell();
            }
            dVar = f8232a;
        }
        return dVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: RootShell.java */
    /* renamed from: com.cyjh.mobileanjian.ipc.d$a */
    /* loaded from: classes.dex */
    public class C1266a extends Thread {
        private C1266a() {
        }

        /* synthetic */ C1266a(RootShell dVar, byte b) {
            this();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            try {
                RootShell.this.f8239g = Runtime.getRuntime().exec(C1363e.f8870a);
                RootShell.this.f8237e = new DataOutputStream(RootShell.this.f8239g.getOutputStream());
                RootShell.this.f8238f = RootShell.this.f8239g.getInputStream();
                RootShell.this.f8237e.writeBytes(RootShell.f8233h);
                RootShell.this.f8237e.flush();
                RootShell dVar = RootShell.this;
                new C12641("", RootShell.this.f8239g);
                C12652 r1 = new C12652("", false);
                r1.start();
                try {
                    r1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new StringBuilder("go straight here, mRoot = ").append(dVar.f8236d);
                if (!dVar.f8236d) {
                    dVar.f8239g.destroy();
                    if (dVar.f8234b != null) {
                        dVar.f8234b.onRefused();
                    }
                    try {
                        dVar.f8237e.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (IOException e3) {
                if (RootShell.this.f8239g != null) {
                    RootShell.this.f8239g.destroy();
                }
                if (RootShell.this.f8234b != null) {
                    RootShell.this.f8234b.onRefused();
                }
                e3.printStackTrace();
            }
        }
    }

    /* compiled from: RootShell.java */
    /* renamed from: com.cyjh.mobileanjian.ipc.d$1 */
    /* loaded from: classes.dex */
    final class C12641 extends Thread {

        /* renamed from: a */
        final /* synthetic */ Process f8240a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C12641(String str, Process process) {
            super(str);
            this.f8240a = process;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.f8240a.getErrorStream()));
            do {
                try {
                    try {
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                } catch (IOException unused) {
                    bufferedReader.close();
                    return;
                } catch (Throwable th) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    throw th;
                }
            } while (bufferedReader.readLine() != null);
            try {
                bufferedReader.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    private void m21023a(Process process, boolean z) {
        C12641 r0 = new C12641("", process);
        C12652 r3 = new C12652("", z);
        if (z) {
            r0.start();
        }
        r3.start();
        if (z) {
            try {
                r0.join();
            } catch (InterruptedException unused) {
            }
            try {
                r3.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try {
                r3.join();
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
        new StringBuilder("go straight here, mRoot = ").append(this.f8236d);
        if (!this.f8236d) {
            this.f8239g.destroy();
            OnRootApplyCallback onRootApplyCallback = this.f8234b;
            if (onRootApplyCallback != null) {
                onRootApplyCallback.onRefused();
            }
            try {
                this.f8237e.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
    }

    /* compiled from: RootShell.java */
    /* renamed from: com.cyjh.mobileanjian.ipc.d$2 */
    /* loaded from: classes.dex */
    final class C12652 extends Thread {

        /* renamed from: a */
        final /* synthetic */ boolean f8242a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C12652(String str, boolean z) {
            super(str);
            this.f8242a = z;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(RootShell.this.f8238f));
            while (true) {
                try {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            if (readLine.equals("rootOK")) {
                                RootShell.this.f8236d = true;
                                if (RootShell.this.f8234b != null) {
                                    RootShell.this.f8234b.onObtained();
                                }
                                if (this.f8242a) {
                                }
                            }
                        }
                        try {
                            bufferedReader.close();
                            return;
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        try {
                            bufferedReader.close();
                            return;
                        } catch (IOException e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                } catch (Throwable th) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                    throw th;
                }
            }
        }
    }

    /* renamed from: c */
    private void m21018c() {
        if (this.f8236d) {
            m21022a(C0675j.f377o);
            if (this.f8235c.isAlive()) {
                this.f8235c.interrupt();
            }
            try {
                this.f8237e.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        f8232a = null;
    }

    /* renamed from: a */
    public final boolean m21022a(String str) {
        if (!this.f8236d) {
            return false;
        }
        try {
            this.f8237e.write(str.getBytes());
            this.f8237e.write("\n".getBytes());
            this.f8237e.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
    }

    /* renamed from: d */
    private void m21016d() {
        this.f8235c = new C1266a(this, (byte) 0);
        this.f8235c.start();
    }

    /* renamed from: b */
    private static /* synthetic */ void m21019b(RootShell dVar, Process process) {
        new C12641("", process);
        C12652 r3 = new C12652("", false);
        r3.start();
        try {
            r3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new StringBuilder("go straight here, mRoot = ").append(dVar.f8236d);
        if (!dVar.f8236d) {
            dVar.f8239g.destroy();
            OnRootApplyCallback onRootApplyCallback = dVar.f8234b;
            if (onRootApplyCallback != null) {
                onRootApplyCallback.onRefused();
            }
            try {
                dVar.f8237e.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }
}
