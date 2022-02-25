package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import java.nio.channels.FileLock;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.tencent.smtt.sdk.t */
/* loaded from: classes2.dex */
public class X5CoreEngine {

    /* renamed from: a */
    private static X5CoreEngine f13249a;

    /* renamed from: e */
    private static FileLock f13250e;

    /* renamed from: b */
    private X5CoreWizard f13251b;

    /* renamed from: c */
    private boolean f13252c;

    /* renamed from: d */
    private boolean f13253d;

    private X5CoreEngine() {
    }

    /* renamed from: a */
    public static X5CoreEngine m16621a() {
        if (f13249a == null) {
            synchronized (X5CoreEngine.class) {
                if (f13249a == null) {
                    f13249a = new X5CoreEngine();
                }
            }
        }
        return f13249a;
    }

    /* renamed from: b */
    public boolean m16618b() {
        if (QbSdk.f12789a) {
            return false;
        }
        return this.f13252c;
    }

    /* renamed from: a */
    public X5CoreWizard m16619a(boolean z) {
        if (z) {
            return this.f13251b;
        }
        return m16616c();
    }

    /* renamed from: c */
    public X5CoreWizard m16616c() {
        if (QbSdk.f12789a) {
            return null;
        }
        return this.f13251b;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x009a A[Catch: all -> 0x01cb, TRY_LEAVE, TryCatch #3 {, blocks: (B:3:0x0001, B:6:0x0023, B:8:0x0027, B:9:0x0032, B:11:0x003e, B:13:0x0045, B:14:0x0060, B:16:0x0063, B:18:0x0067, B:19:0x0076, B:21:0x007e, B:23:0x009a, B:36:0x010d, B:38:0x0119, B:39:0x013e, B:40:0x0162, B:42:0x0168, B:43:0x0188, B:44:0x01a8, B:46:0x01c4, B:47:0x01c7, B:27:0x00ab, B:29:0x00b1, B:30:0x00bd, B:32:0x00c1, B:33:0x00f0, B:35:0x00f4), top: B:54:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01a8 A[Catch: all -> 0x01cb, TryCatch #3 {, blocks: (B:3:0x0001, B:6:0x0023, B:8:0x0027, B:9:0x0032, B:11:0x003e, B:13:0x0045, B:14:0x0060, B:16:0x0063, B:18:0x0067, B:19:0x0076, B:21:0x007e, B:23:0x009a, B:36:0x010d, B:38:0x0119, B:39:0x013e, B:40:0x0162, B:42:0x0168, B:43:0x0188, B:44:0x01a8, B:46:0x01c4, B:47:0x01c7, B:27:0x00ab, B:29:0x00b1, B:30:0x00bd, B:32:0x00c1, B:33:0x00f0, B:35:0x00f4), top: B:54:0x0001 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void m16620a(android.content.Context r11) {
        /*
            Method dump skipped, instructions count: 462
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.X5CoreEngine.m16620a(android.content.Context):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public boolean m16615d() {
        return this.f13253d;
    }

    /* renamed from: b */
    public FileLock m16617b(Context context) {
        TbsLog.m16531i("X5CoreEngine", "tryTbsCoreLoadFileLock ##");
        FileLock fileLock = f13250e;
        if (fileLock != null) {
            return fileLock;
        }
        synchronized (X5CoreEngine.class) {
            if (f13250e == null) {
                f13250e = FileUtil.m16436e(context);
                if (f13250e == null) {
                    TbsLog.m16531i("X5CoreEngine", "init -- sTbsCoreLoadFileLock failed!");
                } else {
                    TbsLog.m16531i("X5CoreEngine", "init -- sTbsCoreLoadFileLock succeeded: " + f13250e);
                }
            }
        }
        return f13250e;
    }
}
