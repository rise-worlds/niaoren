package p110z1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.net.Uri;
import com.alipay.android.app.IAlixPay;
import com.alipay.android.app.IRemoteServiceCallback;
import p110z1.C5019co;

/* renamed from: z1.cf */
/* loaded from: classes3.dex */
public class C4943cf {

    /* renamed from: a */
    public static final String f20633a = "failed";

    /* renamed from: b */
    public static final String f20634b = "scheme_failed";

    /* renamed from: c */
    private Activity f20635c;

    /* renamed from: d */
    private IAlixPay f20636d;

    /* renamed from: f */
    private boolean f20638f;

    /* renamed from: g */
    private AbstractC4944a f20639g;

    /* renamed from: h */
    private final C4745bt f20640h;

    /* renamed from: e */
    private final Object f20637e = IAlixPay.class;

    /* renamed from: i */
    private ServiceConnection f20641i = new ServiceConnectionC4955cg(this);

    /* renamed from: j */
    private String f20642j = null;

    /* renamed from: k */
    private IRemoteServiceCallback f20643k = new BinderC4961ci(this);

    /* renamed from: z1.cf$a */
    /* loaded from: classes3.dex */
    public interface AbstractC4944a {
        /* renamed from: a */
        void mo5476a();

        /* renamed from: b */
        void mo5475b();
    }

    public C4943cf(Activity activity, C4745bt btVar, AbstractC4944a aVar) {
        this.f20635c = activity;
        this.f20640h = btVar;
        this.f20639g = aVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0059 A[Catch: Throwable -> 0x0062, TryCatch #0 {Throwable -> 0x0062, blocks: (B:3:0x0003, B:6:0x0015, B:7:0x0017, B:9:0x0021, B:11:0x0029, B:14:0x0030, B:17:0x003b, B:19:0x003f, B:22:0x004c, B:23:0x0051, B:24:0x0055, B:26:0x0059, B:27:0x005b), top: B:33:0x0003 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String m5487a(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.String r0 = ""
            r1 = 0
            z1.au r2 = p110z1.C3894au.m9967i()     // Catch: Throwable -> 0x0062
            java.util.List r2 = r2.m9968h()     // Catch: Throwable -> 0x0062
            z1.au r3 = p110z1.C3894au.m9967i()     // Catch: Throwable -> 0x0062
            boolean r3 = r3.f17539s     // Catch: Throwable -> 0x0062
            if (r3 == 0) goto L_0x0015
            if (r2 != 0) goto L_0x0017
        L_0x0015:
            java.util.List<z1.au$a> r2 = com.alipay.sdk.app.C0662k.f328a     // Catch: Throwable -> 0x0062
        L_0x0017:
            z1.bt r3 = r6.f20640h     // Catch: Throwable -> 0x0062
            android.app.Activity r4 = r6.f20635c     // Catch: Throwable -> 0x0062
            z1.co$a r2 = p110z1.C5019co.m4493a(r3, r4, r2)     // Catch: Throwable -> 0x0062
            if (r2 == 0) goto L_0x005f
            z1.bt r3 = r6.f20640h     // Catch: Throwable -> 0x0062
            boolean r3 = r2.m4468a(r3)     // Catch: Throwable -> 0x0062
            if (r3 != 0) goto L_0x005f
            boolean r3 = r2.m4469a()     // Catch: Throwable -> 0x0062
            if (r3 == 0) goto L_0x0030
            goto L_0x005f
        L_0x0030:
            android.content.pm.PackageInfo r3 = r2.f20928a     // Catch: Throwable -> 0x0062
            boolean r3 = p110z1.C5019co.m4500a(r3)     // Catch: Throwable -> 0x0062
            if (r3 == 0) goto L_0x003b
            java.lang.String r7 = "failed"
            return r7
        L_0x003b:
            android.content.pm.PackageInfo r3 = r2.f20928a     // Catch: Throwable -> 0x0062
            if (r3 == 0) goto L_0x0051
            java.lang.String r3 = "com.eg.android.AlipayGphone"
            android.content.pm.PackageInfo r4 = r2.f20928a     // Catch: Throwable -> 0x0062
            java.lang.String r4 = r4.packageName     // Catch: Throwable -> 0x0062
            boolean r3 = r3.equals(r4)     // Catch: Throwable -> 0x0062
            if (r3 == 0) goto L_0x004c
            goto L_0x0051
        L_0x004c:
            android.content.pm.PackageInfo r3 = r2.f20928a     // Catch: Throwable -> 0x0062
            java.lang.String r0 = r3.packageName     // Catch: Throwable -> 0x0062
            goto L_0x0055
        L_0x0051:
            java.lang.String r0 = p110z1.C5019co.m4504a()     // Catch: Throwable -> 0x0062
        L_0x0055:
            android.content.pm.PackageInfo r3 = r2.f20928a     // Catch: Throwable -> 0x0062
            if (r3 == 0) goto L_0x005b
            android.content.pm.PackageInfo r1 = r2.f20928a     // Catch: Throwable -> 0x0062
        L_0x005b:
            r6.m5480a(r2)     // Catch: Throwable -> 0x0062
            goto L_0x006c
        L_0x005f:
            java.lang.String r7 = "failed"
            return r7
        L_0x0062:
            r2 = move-exception
            z1.bt r3 = r6.f20640h
            java.lang.String r4 = "biz"
            java.lang.String r5 = "CheckClientSignEx"
            p110z1.C3754ao.m12155a(r3, r4, r5, r2)
        L_0x006c:
            java.lang.String r7 = r6.m5485a(r7, r0, r1)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.C4943cf.m5487a(java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    private void m5480a(C5019co.C5020a aVar) throws InterruptedException {
        PackageInfo packageInfo;
        if (aVar != null && (packageInfo = aVar.f20928a) != null) {
            String str = packageInfo.packageName;
            Intent intent = new Intent();
            intent.setClassName(str, "com.alipay.android.app.TransProcessPayActivity");
            try {
                this.f20635c.startActivity(intent);
            } catch (Throwable th) {
                C3754ao.m12155a(this.f20640h, C3857aq.f17251b, C3857aq.f17227S, th);
            }
            Thread.sleep(200L);
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:59:0x0286
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: a */
    private java.lang.String m5485a(java.lang.String r17, java.lang.String r18, android.content.pm.PackageInfo r19) {
        /*
            Method dump skipped, instructions count: 745
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.C4943cf.m5485a(java.lang.String, java.lang.String, android.content.pm.PackageInfo):java.lang.String");
    }

    /* renamed from: a */
    private static boolean m5486a(String str, Context context, C4745bt btVar) {
        try {
            Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
            intent.setClassName(str, "com.alipay.android.msp.ui.views.MspContainerActivity");
            if (intent.resolveActivityInfo(context.getPackageManager(), 0) != null) {
                return true;
            }
            C3754ao.m12157a(btVar, C3857aq.f17251b, "BSPDetectFail");
            return false;
        } catch (Throwable th) {
            C3754ao.m12155a(btVar, C3857aq.f17251b, "BSPDetectFail", th);
            return false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:53:0x01dc, code lost:
        if (r13 != null) goto L_0x01de;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x01de, code lost:
        r13.setRequestedOrientation(0);
        r12.f20638f = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x023f, code lost:
        if (r13 != null) goto L_0x01de;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String m5484a(java.lang.String r13, java.lang.String r14, p110z1.C4745bt r15) {
        /*
            Method dump skipped, instructions count: 686
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.C4943cf.m5484a(java.lang.String, java.lang.String, z1.bt):java.lang.String");
    }

    /* renamed from: a */
    public void m5488a() {
        this.f20635c = null;
    }
}
