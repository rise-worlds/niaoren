package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.github.kevinsawicki.http.HttpRequest;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsLogReport;
import com.tencent.smtt.utils.ApkUtil;
import com.tencent.smtt.utils.Apn;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.TbsUtils;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.http.protocol.HTTP;
import p110z1.C3894au;
import p110z1.Consts;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.tencent.smtt.sdk.i */
/* loaded from: classes2.dex */
public class TbsApkDownloader {

    /* renamed from: d */
    private static int f13167d = 5;

    /* renamed from: e */
    private static int f13168e = 1;

    /* renamed from: f */
    private static final String[] f13169f = {"tbs_downloading_com.tencent.mtt", "tbs_downloading_com.tencent.mm", "tbs_downloading_com.tencent.mobileqq", "tbs_downloading_com.tencent.tbs", "tbs_downloading_com.qzone"};

    /* renamed from: C */
    private boolean f13172C;

    /* renamed from: a */
    String f13173a;

    /* renamed from: g */
    private Context f13176g;

    /* renamed from: h */
    private String f13177h;

    /* renamed from: i */
    private String f13178i;

    /* renamed from: j */
    private String f13179j;

    /* renamed from: k */
    private File f13180k;

    /* renamed from: l */
    private long f13181l;

    /* renamed from: o */
    private boolean f13184o;

    /* renamed from: p */
    private int f13185p;

    /* renamed from: q */
    private int f13186q;

    /* renamed from: r */
    private boolean f13187r;

    /* renamed from: s */
    private boolean f13188s;

    /* renamed from: t */
    private HttpURLConnection f13189t;

    /* renamed from: u */
    private String f13190u;

    /* renamed from: v */
    private TbsLogReport.TbsLogInfo f13191v;

    /* renamed from: w */
    private String f13192w;

    /* renamed from: x */
    private int f13193x;

    /* renamed from: y */
    private boolean f13194y;

    /* renamed from: z */
    private Handler f13195z;

    /* renamed from: m */
    private int f13182m = 30000;

    /* renamed from: n */
    private int f13183n = C3894au.f17525h;

    /* renamed from: B */
    private int f13171B = f13167d;

    /* renamed from: b */
    String[] f13174b = null;

    /* renamed from: c */
    int f13175c = 0;

    /* renamed from: A */
    private Set<String> f13170A = new HashSet();

    public TbsApkDownloader(Context context) throws NullPointerException {
        this.f13176g = context.getApplicationContext();
        this.f13191v = TbsLogReport.getInstance(this.f13176g).tbsLogInfo();
        this.f13190u = "tbs_downloading_" + this.f13176g.getPackageName();
        TbsInstaller.m16742a();
        this.f13180k = TbsInstaller.m16676s(this.f13176g);
        if (this.f13180k != null) {
            m16777e();
            this.f13192w = null;
            this.f13193x = -1;
            return;
        }
        throw new NullPointerException("TbsCorePrivateDir is null!");
    }

    /* renamed from: e */
    private void m16777e() {
        this.f13185p = 0;
        this.f13186q = 0;
        this.f13181l = -1L;
        this.f13179j = null;
        this.f13184o = false;
        this.f13187r = false;
        this.f13188s = false;
        this.f13194y = false;
    }

    /* renamed from: a */
    private void m16793a(String str) throws Exception {
        URL url = new URL(str);
        HttpURLConnection httpURLConnection = this.f13189t;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Throwable th) {
                TbsLog.m16533e(TbsDownloader.LOGTAG, "[initHttpRequest] mHttpRequest.disconnect() Throwable:" + th.toString());
            }
        }
        this.f13189t = (HttpURLConnection) url.openConnection();
        this.f13189t.setRequestProperty("User-Agent", TbsDownloader.m17019b(this.f13176g));
        this.f13189t.setRequestProperty(HttpRequest.HEADER_ACCEPT_ENCODING, HTTP.IDENTITY_CODING);
        this.f13189t.setRequestMethod("GET");
        this.f13189t.setInstanceFollowRedirects(false);
        this.f13189t.setConnectTimeout(this.f13183n);
        this.f13189t.setReadTimeout(this.f13182m);
    }

    /* renamed from: f */
    private void m16776f() {
        TbsLog.m16531i(TbsDownloader.LOGTAG, "[TbsApkDownloader.closeHttpRequest]");
        HttpURLConnection httpURLConnection = this.f13189t;
        if (httpURLConnection != null) {
            if (!this.f13187r) {
                this.f13191v.setResolveIp(m16791a(httpURLConnection.getURL()));
            }
            try {
                this.f13189t.disconnect();
            } catch (Throwable th) {
                TbsLog.m16533e(TbsDownloader.LOGTAG, "[closeHttpRequest] mHttpRequest.disconnect() Throwable:" + th.toString());
            }
            this.f13189t = null;
        }
        int i = this.f13191v.f12929a;
        if (this.f13187r || !this.f13194y) {
            TbsDownloader.f12902a = false;
            return;
        }
        this.f13191v.setEventTime(System.currentTimeMillis());
        String apnInfo = Apn.getApnInfo(this.f13176g);
        if (apnInfo == null) {
            apnInfo = "";
        }
        int apnType = Apn.getApnType(this.f13176g);
        this.f13191v.setApn(apnInfo);
        this.f13191v.setNetworkType(apnType);
        if (apnType != this.f13193x || !apnInfo.equals(this.f13192w)) {
            this.f13191v.setNetworkChange(0);
        }
        if ((this.f13191v.f12929a == 0 || this.f13191v.f12929a == 107) && this.f13191v.getDownFinalFlag() == 0) {
            if (!Apn.isNetworkAvailable(this.f13176g)) {
                m16802a(101, (String) null, true);
            } else if (!m16771k()) {
                m16802a(101, (String) null, true);
            }
        }
        if (TbsDownloader.m17031a(this.f13176g)) {
            TbsLogReport.getInstance(this.f13176g).eventReport(TbsLogReport.EventType.TYPE_DOWNLOAD_DECOUPLE, this.f13191v);
        } else {
            TbsLogReport.getInstance(this.f13176g).eventReport(TbsLogReport.EventType.TYPE_DOWNLOAD, this.f13191v);
        }
        this.f13191v.resetArgs();
        if (i != 100) {
            QbSdk.f12801m.onDownloadFinish(i);
        }
    }

    /* renamed from: b */
    private boolean m16787b(int i) {
        try {
            File file = new File(this.f13180k, "x5.tbs");
            File a = m16798a(this.f13176g);
            if (a == null) {
                return false;
            }
            File file2 = new File(a, TbsDownloader.getOverSea(this.f13176g) ? "x5.oversea.tbs.org" : "x5.tbs.org");
            file.delete();
            FileUtil.m16443b(file2, file);
            if (ApkUtil.m16518a(this.f13176g, file, 0L, i)) {
                return true;
            }
            TbsLog.m16531i(TbsDownloader.LOGTAG, "[TbsApkDownloader.copyTbsApkFromBackupToInstall] verifyTbsApk error!!");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            TbsLog.m16533e(TbsDownloader.LOGTAG, "[TbsApkDownloader.copyTbsApkFromBackupToInstall] Exception is " + e.getMessage());
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00f1  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean m16789a(boolean r8, boolean r9) {
        /*
            Method dump skipped, instructions count: 325
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsApkDownloader.m16789a(boolean, boolean):boolean");
    }

    /* renamed from: a */
    public boolean m16805a() {
        TbsLog.m16531i("TbsApkDownloader", "verifyAndInstallDecoupleCoreFromBackup #1");
        try {
            File file = new File(FileUtil.m16465a(this.f13176g, 4), "x5.tbs.decouple");
            if (file.exists()) {
                TbsLog.m16531i("TbsApkDownloader", "verifyAndInstallDecoupleCoreFromBackup #2");
            } else {
                File b = TbsDownloader.m17020b(TbsDownloadConfig.getInstance(this.f13176g).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, -1));
                if (b != null && b.exists()) {
                    FileUtil.m16443b(b, file);
                }
            }
            if (!ApkUtil.m16518a(this.f13176g, file, 0L, TbsDownloadConfig.getInstance(this.f13176g).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, -1))) {
                return false;
            }
            TbsLog.m16531i("TbsApkDownloader", "verifyAndInstallDecoupleCoreFromBackup #3");
            return TbsInstaller.m16742a().m16700e(this.f13176g);
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:(2:503|235)|(3:513|237|(8:239|251|495|252|511|253|(2:254|(1:505)(4:262|509|263|(1:497)(8:288|(2:290|(1:507)(4:304|493|305|(2:550|307)))(1:313)|314|499|315|316|(5:318|(1:320)|(3:324|(5:551|332|(1:334)|335|336)|345)|348|553)(2:349|552)|350)))|(8:483|338|542|(1:340)|408|227|548|547)(2:521|343)))|(1:250)(1:249)|251|495|252|511|253|(3:254|(0)(0)|350)|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(11:503|235|(3:513|237|(8:239|251|495|252|511|253|(2:254|(1:505)(4:262|509|263|(1:497)(8:288|(2:290|(1:507)(4:304|493|305|(2:550|307)))(1:313)|314|499|315|316|(5:318|(1:320)|(3:324|(5:551|332|(1:334)|335|336)|345)|348|553)(2:349|552)|350)))|(8:483|338|542|(1:340)|408|227|548|547)(2:521|343)))|(1:250)(1:249)|251|495|252|511|253|(3:254|(0)(0)|350)|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x04aa, code lost:
        if (r35 == false) goto L_0x020e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x04b2, code lost:
        if (r35 == false) goto L_0x04b4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:206:0x0552, code lost:
        if (r35 != false) goto L_0x062d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:228:0x05e9, code lost:
        m16802a(113, "tbsApkFileSize=" + r9 + "  but contentLength=" + r34.f13181l, true);
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r34.f13176g).setDownloadInterruptCode(-310);
     */
    /* JADX WARN: Code restructure failed: missing block: B:230:0x0629, code lost:
        if (r35 == false) goto L_0x020e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:256:0x06a8, code lost:
        com.tencent.smtt.utils.TbsLog.m16530i(com.tencent.smtt.sdk.TbsDownloader.LOGTAG, "STEP 1/2 begin downloading...Canceled!", true);
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r34.f13176g).setDownloadInterruptCode(-309);
     */
    /* JADX WARN: Code restructure failed: missing block: B:257:0x06bb, code lost:
        r26 = r12;
        r4 = false;
        r12 = r9;
        r9 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:266:0x06e0, code lost:
        if (r34.f13174b == null) goto L_0x0708;
     */
    /* JADX WARN: Code restructure failed: missing block: B:268:0x06e7, code lost:
        if (m16780c(true, r3) != false) goto L_0x0706;
     */
    /* JADX WARN: Code restructure failed: missing block: B:269:0x06e9, code lost:
        if (r35 != false) goto L_0x06f9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:271:0x06ef, code lost:
        if (m16790a(false) == false) goto L_0x06f9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:272:0x06f1, code lost:
        r12 = r9;
        r9 = r22;
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:273:0x06f9, code lost:
        r34.f13188s = true;
        r12 = r9;
        r9 = r22;
        r4 = false;
        r26 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:274:0x0706, code lost:
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:275:0x0708, code lost:
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:276:0x0709, code lost:
        r34.f13188s = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:277:0x070d, code lost:
        if (r34.f13174b == null) goto L_0x0711;
     */
    /* JADX WARN: Code restructure failed: missing block: B:278:0x070f, code lost:
        r12 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:279:0x0711, code lost:
        r12 = r26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:280:0x0713, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r34.f13176g).setDownloadInterruptCode(-311);
     */
    /* JADX WARN: Code restructure failed: missing block: B:281:0x071e, code lost:
        r4 = false;
        r12 = r9;
        r9 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:292:0x074b, code lost:
        com.tencent.smtt.utils.TbsLog.m16530i(com.tencent.smtt.sdk.TbsDownloader.LOGTAG, "STEP 1/2 begin downloading...failed because you exceeded max flow!", true);
        r4 = new java.lang.StringBuilder();
        r4.append("downloadFlow=");
        r4.append(r7);
        r4.append(" downloadMaxflow=");
     */
    /* JADX WARN: Code restructure failed: missing block: B:293:0x0765, code lost:
        r9 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:294:0x0767, code lost:
        r4.append(r9);
        m16802a(112, r4.toString(), true);
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r34.f13176g).setDownloadInterruptCode(-307);
     */
    /* JADX WARN: Code restructure failed: missing block: B:295:0x077f, code lost:
        r22 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:296:0x0782, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:297:0x0784, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:308:0x07e0, code lost:
        r4 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:359:0x0915, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:360:0x0916, code lost:
        r12 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:361:0x091f, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:362:0x0920, code lost:
        r12 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:364:0x092a, code lost:
        r5 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:365:0x092b, code lost:
        r12 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:366:0x0934, code lost:
        r5 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:367:0x0935, code lost:
        r26 = r12;
        r12 = r9;
        r9 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:370:0x0948, code lost:
        r11 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:373:0x0955, code lost:
        r11 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:375:0x0960, code lost:
        m16795a(r11);
        m16795a(r6);
        m16795a(r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:376:0x096b, code lost:
        if (r34.f13188s != false) goto L_0x0978;
     */
    /* JADX WARN: Code restructure failed: missing block: B:377:0x096d, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r34.f13176g).setDownloadInterruptCode(-319);
     */
    /* JADX WARN: Code restructure failed: missing block: B:378:0x0978, code lost:
        if (r35 != false) goto L_0x0b02;
     */
    /* JADX WARN: Code restructure failed: missing block: B:379:0x097a, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r34.f13176g).f12891a.put(com.tencent.smtt.sdk.TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOAD_FLOW, java.lang.Long.valueOf(r7));
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r34.f13176g).commit();
     */
    /* JADX WARN: Code restructure failed: missing block: B:394:0x0a06, code lost:
        if (r35 == false) goto L_0x097a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:402:0x0a36, code lost:
        if (r35 != false) goto L_0x0a72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:406:0x0a56, code lost:
        if (r35 == false) goto L_0x0a58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:407:0x0a58, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r34.f13176g).f12891a.put(com.tencent.smtt.sdk.TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOAD_FLOW, java.lang.Long.valueOf(r7));
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r34.f13176g).commit();
     */
    /* JADX WARN: Code restructure failed: missing block: B:433:0x0afe, code lost:
        if (r35 == false) goto L_0x020e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x020c, code lost:
        if (r35 == false) goto L_0x020e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x020e, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r34.f13176g).f12891a.put(com.tencent.smtt.sdk.TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOAD_FLOW, java.lang.Long.valueOf(r7));
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r34.f13176g).commit();
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x024a, code lost:
        if (r35 == false) goto L_0x020e;
     */
    /* JADX WARN: Removed duplicated region for block: B:262:0x06d3  */
    /* JADX WARN: Removed duplicated region for block: B:386:0x09b5 A[Catch: all -> 0x0a77, TryCatch #8 {all -> 0x0a77, blocks: (B:384:0x09ae, B:386:0x09b5, B:390:0x09bd, B:392:0x09c5, B:397:0x0a0f, B:399:0x0a18, B:400:0x0a23, B:404:0x0a3c), top: B:479:0x09ae }] */
    /* JADX WARN: Removed duplicated region for block: B:390:0x09bd A[Catch: all -> 0x0a77, TryCatch #8 {all -> 0x0a77, blocks: (B:384:0x09ae, B:386:0x09b5, B:390:0x09bd, B:392:0x09c5, B:397:0x0a0f, B:399:0x0a18, B:400:0x0a23, B:404:0x0a3c), top: B:479:0x09ae }] */
    /* JADX WARN: Removed duplicated region for block: B:396:0x0a0d  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x0a18 A[Catch: all -> 0x0a77, TryCatch #8 {all -> 0x0a77, blocks: (B:384:0x09ae, B:386:0x09b5, B:390:0x09bd, B:392:0x09c5, B:397:0x0a0f, B:399:0x0a18, B:400:0x0a23, B:404:0x0a3c), top: B:479:0x09ae }] */
    /* JADX WARN: Removed duplicated region for block: B:400:0x0a23 A[Catch: all -> 0x0a77, TRY_LEAVE, TryCatch #8 {all -> 0x0a77, blocks: (B:384:0x09ae, B:386:0x09b5, B:390:0x09bd, B:392:0x09c5, B:397:0x0a0f, B:399:0x0a18, B:400:0x0a23, B:404:0x0a3c), top: B:479:0x09ae }] */
    /* JADX WARN: Removed duplicated region for block: B:424:0x0a9d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:470:0x0bac  */
    /* JADX WARN: Removed duplicated region for block: B:483:0x0897 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:505:0x06a8 A[EDGE_INSN: B:505:0x06a8->B:256:0x06a8 ?: BREAK  , EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:521:0x08c4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:543:0x0af3 A[EDGE_INSN: B:543:0x0af3->B:432:0x0af3 ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:545:0x0a39 A[ADDED_TO_REGION, SYNTHETIC] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m16784b(boolean r35, boolean r36) {
        /*
            Method dump skipped, instructions count: 3069
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsApkDownloader.m16784b(boolean, boolean):void");
    }

    /* renamed from: a */
    public boolean m16790a(boolean z) {
        String[] strArr;
        int i;
        if ((z && !m16769m() && (!QbSdk.getDownloadWithoutWifi() || !Apn.isNetworkAvailable(this.f13176g))) || (strArr = this.f13174b) == null || (i = this.f13175c) < 0 || i >= strArr.length) {
            return false;
        }
        this.f13175c = i + 1;
        this.f13179j = strArr[i];
        this.f13185p = 0;
        this.f13186q = 0;
        this.f13181l = -1L;
        this.f13184o = false;
        this.f13187r = false;
        this.f13188s = false;
        this.f13194y = false;
        return true;
    }

    /* renamed from: a */
    private long m16799a(long j, long j2) {
        long currentTimeMillis = System.currentTimeMillis();
        this.f13191v.setDownConsumeTime(currentTimeMillis - j);
        this.f13191v.setDownloadSize(j2);
        return currentTimeMillis;
    }

    /* renamed from: a */
    private void m16802a(int i, String str, boolean z) {
        if (z || this.f13185p > this.f13171B) {
            this.f13191v.setErrorCode(i);
            this.f13191v.setFailDetail(str);
        }
    }

    /* renamed from: a */
    private String m16792a(Throwable th) {
        String stackTraceString = Log.getStackTraceString(th);
        return stackTraceString.length() > 1024 ? stackTraceString.substring(0, 1024) : stackTraceString;
    }

    /* renamed from: c */
    private void m16781c(boolean z) {
        TbsUtils.m16364a(this.f13176g);
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(this.f13176g);
        instance.f12891a.put(TbsDownloadConfig.TbsConfigKey.KEY_FULL_PACKAGE, false);
        instance.f12891a.put(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, false);
        instance.f12891a.put(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_CODE_REASON, -123);
        instance.commit();
        QbSdk.f12801m.onDownloadFinish(z ? 100 : 120);
        int i = instance.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_RESPONSECODE, 0);
        boolean a = TbsDownloader.m17031a(this.f13176g);
        if (i == 5) {
            Bundle a2 = m16801a(i, a);
            if (a2 != null) {
                TbsInstaller.m16742a().m16717b(this.f13176g, a2);
            }
        } else if (i == 3 || i > 10000) {
            File a3 = m16798a(this.f13176g);
            if (a3 != null) {
                TbsInstaller.m16742a().m16717b(this.f13176g, m16803a(i, a3, a));
                return;
            }
            m16783c();
            instance.f12891a.put(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, true);
            instance.commit();
        } else {
            TbsInstaller.m16742a().m16730a(this.f13176g, new File(this.f13180k, "x5.tbs").getAbsolutePath(), instance.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0));
            m16794a(new File(this.f13180k, "x5.tbs"), this.f13176g);
        }
    }

    /* renamed from: a */
    public Bundle m16801a(int i, boolean z) {
        int i2;
        File file;
        File file2;
        if (z) {
            file = TbsInstaller.m16742a().m16679p(this.f13176g);
            i2 = TbsInstaller.m16742a().m16690h(this.f13176g);
        } else {
            file = TbsInstaller.m16742a().m16678q(this.f13176g);
            i2 = TbsInstaller.m16742a().m16688i(this.f13176g);
        }
        File file3 = new File(this.f13180k, "x5.tbs");
        String absolutePath = file3.exists() ? file3.getAbsolutePath() : null;
        if (absolutePath == null) {
            return null;
        }
        int i3 = TbsDownloadConfig.getInstance(this.f13176g).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
        if (z) {
            file2 = TbsInstaller.m16742a().m16695f(this.f13176g, 6);
        } else {
            file2 = TbsInstaller.m16742a().m16695f(this.f13176g, 5);
        }
        Bundle bundle = new Bundle();
        bundle.putInt("operation", i);
        bundle.putInt("old_core_ver", i2);
        bundle.putInt("new_core_ver", i3);
        bundle.putString("old_apk_location", file.getAbsolutePath());
        bundle.putString("new_apk_location", file2.getAbsolutePath());
        bundle.putString("diff_file_location", absolutePath);
        String a = FileUtil.m16465a(this.f13176g, 7);
        File file4 = new File(a);
        if (!file4.exists()) {
            file4.mkdirs();
        }
        bundle.putString("backup_apk", new File(a, i3 + ".tbs").getAbsolutePath());
        return bundle;
    }

    /* renamed from: a */
    public Bundle m16803a(int i, File file, boolean z) {
        File file2;
        if (z) {
            file2 = new File(file, "x5.tbs.decouple");
        } else {
            file2 = new File(file, TbsDownloader.getOverSea(this.f13176g) ? "x5.oversea.tbs.org" : "x5.tbs.org");
        }
        int a = ApkUtil.m16519a(this.f13176g, file2);
        File file3 = new File(this.f13180k, "x5.tbs");
        String absolutePath = file3.exists() ? file3.getAbsolutePath() : null;
        int i2 = TbsDownloadConfig.getInstance(this.f13176g).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
        Bundle bundle = new Bundle();
        bundle.putInt("operation", i);
        bundle.putInt("old_core_ver", a);
        bundle.putInt("new_core_ver", i2);
        bundle.putString("old_apk_location", file2.getAbsolutePath());
        bundle.putString("new_apk_location", absolutePath);
        bundle.putString("diff_file_location", absolutePath);
        return bundle;
    }

    /* renamed from: a */
    private void m16795a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    /* renamed from: a */
    private void m16800a(long j) {
        this.f13185p++;
        if (j <= 0) {
            try {
                j = m16770l();
            } catch (Exception unused) {
                return;
            }
        }
        Thread.sleep(j);
    }

    /* renamed from: g */
    private boolean m16775g() {
        File file = new File(FileUtil.m16465a(this.f13176g, 4), TbsDownloader.getOverSea(this.f13176g) ? "x5.oversea.tbs.org" : "x5.tbs.org");
        int i = TbsDownloadConfig.getInstance(this.f13176g).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_USE_BACKUP_VERSION, 0);
        if (i == 0) {
            i = TbsDownloadConfig.getInstance(this.f13176g).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
        }
        return ApkUtil.m16518a(this.f13176g, file, 0L, i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0086, code lost:
        if (r7 != r5) goto L_0x0088;
     */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean m16780c(boolean r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 523
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsApkDownloader.m16780c(boolean, boolean):boolean");
    }

    /* renamed from: d */
    private boolean m16778d(boolean z) {
        File file;
        TbsLog.m16531i(TbsDownloader.LOGTAG, "[TbsApkDownloader.deleteFile] isApk=" + z);
        if (z) {
            file = new File(this.f13180k, "x5.tbs");
        } else {
            file = new File(this.f13180k, "x5.tbs.temp");
        }
        if (file.exists()) {
            return file.delete();
        }
        return true;
    }

    /* renamed from: h */
    private void m16774h() {
        try {
            File file = new File(FileUtil.m16465a(this.f13176g, 4), TbsDownloader.getOverSea(this.f13176g) ? "x5.oversea.tbs.org" : "x5.tbs.org");
            if (file.exists()) {
                file.delete();
                File[] listFiles = file.getParentFile().listFiles();
                Pattern compile = Pattern.compile(ApkUtil.m16515a(TbsDownloader.m17031a(this.f13176g)) + "(.*)");
                for (File file2 : listFiles) {
                    if (compile.matcher(file2.getName()).find() && file2.isFile() && file2.exists()) {
                        file2.delete();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: i */
    private boolean m16773i() {
        return new File(this.f13180k, "x5.tbs.temp").exists();
    }

    /* renamed from: j */
    private long m16772j() {
        File file = new File(this.f13180k, "x5.tbs.temp");
        if (file.exists()) {
            return file.length();
        }
        return 0L;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:17:0x004e
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: k */
    private boolean m16771k() {
        /*
            r9 = this;
            java.lang.Runtime r0 = java.lang.Runtime.getRuntime()
            java.lang.String r1 = "www.qq.com"
            r2 = 0
            r3 = 0
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: Throwable -> 0x006b
            r4.<init>()     // Catch: Throwable -> 0x006b
            java.lang.String r5 = "ping "
            r4.append(r5)     // Catch: Throwable -> 0x006b
            r4.append(r1)     // Catch: Throwable -> 0x006b
            java.lang.String r1 = r4.toString()     // Catch: Throwable -> 0x006b
            java.lang.Process r0 = r0.exec(r1)     // Catch: Throwable -> 0x006b
            java.io.InputStream r0 = r0.getInputStream()     // Catch: Throwable -> 0x006b
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch: Throwable -> 0x0061
            r1.<init>(r0)     // Catch: Throwable -> 0x0061
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch: Throwable -> 0x0058
            r4.<init>(r1)     // Catch: Throwable -> 0x0058
            r3 = 0
        L_0x002c:
            java.lang.String r5 = r4.readLine()     // Catch: Throwable -> 0x0050
            r6 = 1
            if (r5 == 0) goto L_0x004a
            java.lang.String r7 = "TTL"
            boolean r7 = r5.contains(r7)     // Catch: Throwable -> 0x0050
            if (r7 != 0) goto L_0x0049
            java.lang.String r7 = "ttl"
            boolean r5 = r5.contains(r7)     // Catch: Throwable -> 0x0050
            if (r5 == 0) goto L_0x0044
            goto L_0x0049
        L_0x0044:
            int r3 = r3 + r6
            r5 = 5
            if (r3 < r5) goto L_0x002c
            goto L_0x004a
        L_0x0049:
            r2 = 1
        L_0x004a:
            r9.m16795a(r0)
            goto L_0x0074
        L_0x004e:
            r2 = move-exception
            goto L_0x007d
        L_0x0050:
            r3 = move-exception
            r8 = r3
            r3 = r0
            r0 = r8
            goto L_0x006e
        L_0x0055:
            r2 = move-exception
            r4 = r3
            goto L_0x007d
        L_0x0058:
            r4 = move-exception
            r8 = r3
            r3 = r0
            r0 = r4
            r4 = r8
            goto L_0x006e
        L_0x005e:
            r2 = move-exception
            r4 = r3
            goto L_0x007e
        L_0x0061:
            r1 = move-exception
            r4 = r3
            r3 = r0
            r0 = r1
            r1 = r4
            goto L_0x006e
        L_0x0067:
            r2 = move-exception
            r0 = r3
            r4 = r0
            goto L_0x007e
        L_0x006b:
            r0 = move-exception
            r1 = r3
            r4 = r1
        L_0x006e:
            r0.printStackTrace()     // Catch: all -> 0x007b
            r9.m16795a(r3)
        L_0x0074:
            r9.m16795a(r1)
            r9.m16795a(r4)
            return r2
        L_0x007b:
            r2 = move-exception
            r0 = r3
        L_0x007d:
            r3 = r1
        L_0x007e:
            r9.m16795a(r0)
            r9.m16795a(r3)
            r9.m16795a(r4)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsApkDownloader.m16771k():boolean");
    }

    /* renamed from: a */
    private String m16791a(URL url) {
        try {
            return InetAddress.getByName(url.getHost()).getHostAddress();
        } catch (Error e) {
            e.printStackTrace();
            return "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    /* renamed from: l */
    private long m16770l() {
        int i = this.f13185p;
        switch (i) {
            case 1:
            case 2:
                return i * 20000;
            case 3:
            case 4:
                return 100000L;
            default:
                return 200000L;
        }
    }

    @TargetApi(8)
    /* renamed from: a */
    static File m16798a(Context context) {
        File file;
        try {
            if (Build.VERSION.SDK_INT >= 8) {
                file = new File(FileUtil.m16465a(context, 4));
            } else {
                file = null;
            }
            if (file != null && !file.exists() && !file.isDirectory()) {
                file.mkdirs();
            }
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            TbsLog.m16533e(TbsDownloader.LOGTAG, "[TbsApkDownloader.backupApkPath] Exception is " + e.getMessage());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(8)
    /* renamed from: b */
    public static File m16786b(Context context) {
        try {
            if (Build.VERSION.SDK_INT < 8) {
                return null;
            }
            File a = m16797a(context, 4);
            if (a == null) {
                a = m16797a(context, 3);
            }
            if (a == null) {
                a = m16797a(context, 2);
            }
            return a == null ? m16797a(context, 1) : a;
        } catch (Exception e) {
            e.printStackTrace();
            TbsLog.m16533e(TbsDownloader.LOGTAG, "[TbsApkDownloader.backupApkPath] Exception is " + e.getMessage());
            return null;
        }
    }

    /* renamed from: a */
    private static File m16797a(Context context, int i) {
        File file = new File(FileUtil.m16465a(context, i));
        if (!file.exists() || !file.isDirectory()) {
            return null;
        }
        if (new File(file, TbsDownloader.getOverSea(context) ? "x5.oversea.tbs.org" : "x5.tbs.org").exists()) {
            return file;
        }
        return null;
    }

    /* renamed from: b */
    public int m16785b(boolean z) {
        File a = m16798a(this.f13176g);
        if (z) {
            if (a == null) {
                return 0;
            }
            return ApkUtil.m16519a(this.f13176g, new File(a, "x5.tbs.decouple"));
        } else if (a == null) {
            return 0;
        } else {
            Context context = this.f13176g;
            return ApkUtil.m16519a(context, new File(a, TbsDownloader.getOverSea(context) ? "x5.oversea.tbs.org" : "x5.tbs.org"));
        }
    }

    /* renamed from: b */
    public void m16788b() {
        this.f13187r = true;
        if (TbsShareManager.isThirdPartyApp(this.f13176g)) {
            TbsLogReport.TbsLogInfo tbsLogInfo = TbsLogReport.getInstance(this.f13176g).tbsLogInfo();
            tbsLogInfo.setErrorCode(-309);
            tbsLogInfo.setFailDetail(new Exception());
            if (TbsDownloader.m17031a(this.f13176g)) {
                TbsLogReport.getInstance(this.f13176g).eventReport(TbsLogReport.EventType.TYPE_DOWNLOAD_DECOUPLE, tbsLogInfo);
            } else {
                TbsLogReport.getInstance(this.f13176g).eventReport(TbsLogReport.EventType.TYPE_DOWNLOAD, tbsLogInfo);
            }
        }
    }

    /* renamed from: c */
    public void m16783c() {
        m16788b();
        m16778d(false);
        m16778d(true);
    }

    /* renamed from: a */
    public void m16804a(int i) {
        if (TbsInstaller.m16742a().m16675t(this.f13176g)) {
            TbsInstaller.m16742a().m16721b();
            try {
                File file = new File(this.f13180k, "x5.tbs");
                int a = ApkUtil.m16519a(this.f13176g, file);
                if (-1 == a || (i > 0 && i == a)) {
                    FileUtil.m16444b(file);
                }
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: a */
    public static void m16794a(File file, Context context) {
        File file2;
        boolean contains;
        synchronized (ApkUtil.class) {
            if (file != null) {
                if (file.exists()) {
                    try {
                        File a = m16798a(context);
                        if (a != null) {
                            if (TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V_TYPE, 0) == 1) {
                                file2 = new File(a, "x5.tbs.decouple");
                            } else {
                                file2 = new File(a, TbsDownloader.getOverSea(context) ? "x5.oversea.tbs.org" : "x5.tbs.org");
                            }
                            file2.delete();
                            FileUtil.m16443b(file, file2);
                            boolean contains2 = file2.getName().contains("tbs.org");
                            if (file2.getName().contains("x5.tbs.decouple") || contains2) {
                                File[] listFiles = a.listFiles();
                                Pattern compile = Pattern.compile(ApkUtil.m16515a(contains) + "(.*)");
                                for (File file3 : listFiles) {
                                    if (compile.matcher(file3.getName()).find() && file3.isFile() && file3.exists()) {
                                        file3.delete();
                                    }
                                }
                                File file4 = new File(a, ApkUtil.m16515a(contains) + Consts.f23430h + TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0));
                                if (file4.exists()) {
                                    TbsLog.m16533e(TbsDownloader.LOGTAG, "[TbsApkDownloader.backupTbsApk]delete bacup config file error ");
                                    return;
                                }
                                file4.createNewFile();
                            }
                            if (TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V_TYPE, 0) != 1 && TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, 0) == ApkUtil.m16519a(context, file)) {
                                int i = TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_RESPONSECODE, 0);
                                if (i == 5 || i == 3) {
                                    TbsLog.m16531i("TbsApkDownloader", "response code=" + i + "return backup decouple apk");
                                }
                                File file5 = new File(a, "x5.tbs.decouple");
                                if (ApkUtil.m16519a(context, file) != ApkUtil.m16519a(context, file5)) {
                                    file5.delete();
                                    FileUtil.m16443b(file, file5);
                                }
                            }
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        }
    }

    /* renamed from: c */
    public static void m16782c(Context context) {
        try {
            TbsInstaller.m16742a();
            File s = TbsInstaller.m16676s(context);
            new File(s, "x5.tbs").delete();
            new File(s, "x5.tbs.temp").delete();
            File a = m16798a(context);
            if (a != null) {
                new File(a, "x5.tbs.org").delete();
                new File(a, "x5.oversea.tbs.org").delete();
                File[] listFiles = a.listFiles();
                Pattern compile = Pattern.compile(ApkUtil.m16515a(true) + "(.*)");
                for (File file : listFiles) {
                    if (compile.matcher(file.getName()).find() && file.isFile() && file.exists()) {
                        file.delete();
                    }
                }
                Pattern compile2 = Pattern.compile(ApkUtil.m16515a(false) + "(.*)");
                for (File file2 : listFiles) {
                    if (compile2.matcher(file2.getName()).find() && file2.isFile() && file2.exists()) {
                        file2.delete();
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: m */
    public boolean m16769m() {
        String str;
        Throwable th;
        HttpURLConnection httpURLConnection;
        boolean z = false;
        boolean z2 = Apn.getApnType(this.f13176g) == 3;
        TbsLog.m16531i(TbsDownloader.LOGTAG, "[TbsApkDwonloader.detectWifiNetworkAvailable] isWifi=" + z2);
        HttpURLConnection httpURLConnection2 = null;
        if (z2) {
            str = Apn.getWifiSSID(this.f13176g);
            TbsLog.m16531i(TbsDownloader.LOGTAG, "[TbsApkDwonloader.detectWifiNetworkAvailable] localBSSID=" + str);
            try {
                try {
                    httpURLConnection = (HttpURLConnection) new URL("http://pms.mb.qq.com/rsp204").openConnection();
                    try {
                        httpURLConnection.setInstanceFollowRedirects(false);
                        httpURLConnection.setConnectTimeout(10000);
                        httpURLConnection.setReadTimeout(10000);
                        httpURLConnection.setUseCaches(false);
                        httpURLConnection.getInputStream();
                        int responseCode = httpURLConnection.getResponseCode();
                        TbsLog.m16531i(TbsDownloader.LOGTAG, "[TbsApkDwonloader.detectWifiNetworkAvailable] responseCode=" + responseCode);
                        if (responseCode == 204) {
                            z = true;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        httpURLConnection2 = httpURLConnection;
                        try {
                            th.printStackTrace();
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                            if (!z) {
                                this.f13170A.add(str);
                                m16768n();
                                this.f13195z.sendMessageDelayed(this.f13195z.obtainMessage(150, str), 120000L);
                            }
                            if (z) {
                                this.f13170A.remove(str);
                            }
                            return z;
                        } catch (Throwable th3) {
                            if (httpURLConnection2 != null) {
                                try {
                                    httpURLConnection2.disconnect();
                                } catch (Exception unused) {
                                }
                            }
                            throw th3;
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            } catch (Exception unused2) {
            }
        } else {
            str = null;
        }
        if (!z && !TextUtils.isEmpty(str) && !this.f13170A.contains(str)) {
            this.f13170A.add(str);
            m16768n();
            this.f13195z.sendMessageDelayed(this.f13195z.obtainMessage(150, str), 120000L);
        }
        if (z && this.f13170A.contains(str)) {
            this.f13170A.remove(str);
        }
        return z;
    }

    /* renamed from: n */
    private void m16768n() {
        if (this.f13195z == null) {
            this.f13195z = new Handler(TbsHandlerThread.m16748a().getLooper()) { // from class: com.tencent.smtt.sdk.i.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    if (message.what == 150) {
                        TbsApkDownloader.this.m16769m();
                    }
                }
            };
        }
    }

    /* renamed from: d */
    public boolean m16779d() {
        TbsLog.m16531i(TbsDownloader.LOGTAG, "[TbsApkDownloader.isDownloadForeground] mIsDownloadForeground=" + this.f13172C);
        return this.f13172C;
    }
}
