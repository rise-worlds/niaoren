package com.tencent.smtt.utils;

import android.content.Context;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/* renamed from: com.tencent.smtt.utils.o */
/* loaded from: classes2.dex */
public class TbsConfigFile {

    /* renamed from: e */
    private static TbsConfigFile f13404e;

    /* renamed from: b */
    private Context f13406b;

    /* renamed from: c */
    private File f13407c = null;

    /* renamed from: a */
    public boolean f13405a = false;

    /* renamed from: d */
    private boolean f13408d = false;

    /* renamed from: f */
    private File f13409f = null;

    /* renamed from: a */
    public static synchronized TbsConfigFile m16379a(Context context) {
        TbsConfigFile oVar;
        synchronized (TbsConfigFile.class) {
            if (f13404e == null) {
                f13404e = new TbsConfigFile(context);
            }
            oVar = f13404e;
        }
        return oVar;
    }

    private TbsConfigFile(Context context) {
        this.f13406b = null;
        this.f13406b = context.getApplicationContext();
        m16377b();
    }

    /* renamed from: a */
    public static synchronized TbsConfigFile m16380a() {
        TbsConfigFile oVar;
        synchronized (TbsConfigFile.class) {
            oVar = f13404e;
        }
        return oVar;
    }

    /* renamed from: b */
    public synchronized void m16377b() {
        Throwable th;
        BufferedInputStream bufferedInputStream = null;
        try {
            if (this.f13409f == null) {
                this.f13409f = m16375d();
            }
        } catch (Throwable th2) {
            th = th2;
        }
        if (this.f13409f != null) {
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(this.f13409f));
            try {
                Properties properties = new Properties();
                properties.load(bufferedInputStream2);
                String property = properties.getProperty("setting_forceUseSystemWebview", "");
                if (!"".equals(property)) {
                    this.f13405a = Boolean.parseBoolean(property);
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedInputStream = bufferedInputStream2;
                th.printStackTrace();
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (Exception e) {
                        e = e;
                        e.printStackTrace();
                    }
                }
            }
            try {
                bufferedInputStream2.close();
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
            }
        }
    }

    /* renamed from: d */
    private File m16375d() {
        try {
            if (this.f13407c == null) {
                this.f13407c = new File(this.f13406b.getDir("tbs", 0), "core_private");
                if (this.f13407c == null || !this.f13407c.isDirectory()) {
                    return null;
                }
            }
            File file = new File(this.f13407c, "debug.conf");
            if (!file.exists()) {
                file.createNewFile();
            }
            return file;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public void m16378a(boolean z) {
        this.f13408d = z;
        m16376c();
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:19:0x0059
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: c */
    public void m16376c() {
        /*
            r7 = this;
            r0 = 0
            java.io.File r1 = r7.m16375d()     // Catch: Throwable -> 0x006b
            if (r1 != 0) goto L_0x0018
            r0.close()     // Catch: Exception -> 0x000b
            goto L_0x000f
        L_0x000b:
            r1 = move-exception
            r1.printStackTrace()
        L_0x000f:
            r0.close()     // Catch: Exception -> 0x0013
            goto L_0x0017
        L_0x0013:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0017:
            return
        L_0x0018:
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: Throwable -> 0x006b
            r2.<init>(r1)     // Catch: Throwable -> 0x006b
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch: Throwable -> 0x006b
            r3.<init>(r2)     // Catch: Throwable -> 0x006b
            java.util.Properties r2 = new java.util.Properties     // Catch: Throwable -> 0x0064
            r2.<init>()     // Catch: Throwable -> 0x0064
            r2.load(r3)     // Catch: Throwable -> 0x0064
            java.lang.String r4 = "setting_forceUseSystemWebview"
            boolean r5 = r7.f13405a     // Catch: Throwable -> 0x0064
            java.lang.String r5 = java.lang.Boolean.toString(r5)     // Catch: Throwable -> 0x0064
            r2.setProperty(r4, r5)     // Catch: Throwable -> 0x0064
            java.lang.String r4 = "result_systemWebviewForceUsed"
            boolean r5 = r7.f13408d     // Catch: Throwable -> 0x0064
            java.lang.String r5 = java.lang.Boolean.toString(r5)     // Catch: Throwable -> 0x0064
            r2.setProperty(r4, r5)     // Catch: Throwable -> 0x0064
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: Throwable -> 0x0064
            r4.<init>(r1)     // Catch: Throwable -> 0x0064
            java.io.BufferedOutputStream r1 = new java.io.BufferedOutputStream     // Catch: Throwable -> 0x0064
            r1.<init>(r4)     // Catch: Throwable -> 0x0064
            r2.store(r1, r0)     // Catch: Throwable -> 0x005e
            r3.close()     // Catch: Exception -> 0x0051
            goto L_0x0055
        L_0x0051:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0055:
            r1.close()     // Catch: Exception -> 0x007c
            goto L_0x0080
        L_0x0059:
            r0 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x0084
        L_0x005e:
            r0 = move-exception
            r2 = r1
            r1 = r0
            goto L_0x0066
        L_0x0062:
            r1 = move-exception
            goto L_0x0084
        L_0x0064:
            r1 = move-exception
            r2 = r0
        L_0x0066:
            r0 = r3
            goto L_0x006d
        L_0x0068:
            r1 = move-exception
            r3 = r0
            goto L_0x0084
        L_0x006b:
            r1 = move-exception
            r2 = r0
        L_0x006d:
            r1.printStackTrace()     // Catch: all -> 0x0081
            r0.close()     // Catch: Exception -> 0x0074
            goto L_0x0078
        L_0x0074:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0078:
            r2.close()     // Catch: Exception -> 0x007c
            goto L_0x0080
        L_0x007c:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0080:
            return
        L_0x0081:
            r1 = move-exception
            r3 = r0
            r0 = r2
        L_0x0084:
            r3.close()     // Catch: Exception -> 0x0088
            goto L_0x008c
        L_0x0088:
            r2 = move-exception
            r2.printStackTrace()
        L_0x008c:
            r0.close()     // Catch: Exception -> 0x0090
            goto L_0x0094
        L_0x0090:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0094:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.TbsConfigFile.m16376c():void");
    }
}
