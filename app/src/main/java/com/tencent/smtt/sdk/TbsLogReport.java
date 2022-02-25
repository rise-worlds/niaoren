package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.utils.AppUtil;
import com.tencent.smtt.utils.HttpUtil;
import com.tencent.smtt.utils.SysCoreQUA2Utils;
import com.tencent.smtt.utils.TbsCommonConfig;
import com.tencent.smtt.utils.TbsLog;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.mail.EmailConstants;
import org.json.JSONArray;
import p110z1.C4963cj;

/* loaded from: classes2.dex */
public class TbsLogReport {

    /* renamed from: a */
    private static TbsLogReport f12920a;

    /* renamed from: b */
    private Handler f12921b;

    /* renamed from: c */
    private Context f12922c;

    /* renamed from: d */
    private boolean f12923d = false;

    /* loaded from: classes2.dex */
    public enum EventType {
        TYPE_DOWNLOAD(0),
        TYPE_INSTALL(1),
        TYPE_LOAD(2),
        TYPE_DOWNLOAD_DECOUPLE(3),
        TYPE_INSTALL_DECOUPLE(4),
        TYPE_COOKIE_DB_SWITCH(5),
        TYPE_SDK_REPORT_INFO(6);
        

        /* renamed from: a */
        int f12928a;

        EventType(int i) {
            this.f12928a = i;
        }
    }

    private TbsLogReport(Context context) {
        this.f12921b = null;
        this.f12922c = context.getApplicationContext();
        HandlerThread handlerThread = new HandlerThread("TbsLogReportThread");
        handlerThread.start();
        this.f12921b = new Handler(handlerThread.getLooper()) { // from class: com.tencent.smtt.sdk.TbsLogReport.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 600) {
                    if (message.obj instanceof TbsLogInfo) {
                        try {
                            int i = message.arg1;
                            TbsLogReport.this.m17004a(i, (TbsLogInfo) message.obj);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else if (message.what == 601) {
                    TbsLogReport.this.m16998b();
                }
            }
        };
    }

    public static TbsLogReport getInstance(Context context) {
        if (f12920a == null) {
            synchronized (TbsLogReport.class) {
                if (f12920a == null) {
                    f12920a = new TbsLogReport(context);
                }
            }
        }
        return f12920a;
    }

    /* loaded from: classes2.dex */
    public static class TbsLogInfo implements Cloneable {

        /* renamed from: a */
        int f12929a;

        /* renamed from: b */
        private long f12930b;

        /* renamed from: c */
        private String f12931c;

        /* renamed from: d */
        private String f12932d;

        /* renamed from: e */
        private int f12933e;

        /* renamed from: f */
        private int f12934f;

        /* renamed from: g */
        private int f12935g;

        /* renamed from: h */
        private int f12936h;

        /* renamed from: i */
        private String f12937i;

        /* renamed from: j */
        private int f12938j;

        /* renamed from: k */
        private int f12939k;

        /* renamed from: l */
        private long f12940l;

        /* renamed from: m */
        private long f12941m;

        /* renamed from: n */
        private int f12942n;

        /* renamed from: o */
        private String f12943o;

        /* renamed from: p */
        private String f12944p;

        /* renamed from: q */
        private long f12945q;

        private TbsLogInfo() {
            resetArgs();
        }

        protected Object clone() {
            try {
                return super.clone();
            } catch (CloneNotSupportedException unused) {
                return this;
            }
        }

        public void resetArgs() {
            this.f12930b = 0L;
            this.f12931c = null;
            this.f12932d = null;
            this.f12933e = 0;
            this.f12934f = 0;
            this.f12935g = 0;
            this.f12936h = 2;
            this.f12937i = "unknown";
            this.f12938j = 0;
            this.f12939k = 2;
            this.f12940l = 0L;
            this.f12941m = 0L;
            this.f12942n = 1;
            this.f12929a = 0;
            this.f12943o = null;
            this.f12944p = null;
            this.f12945q = 0L;
        }

        public void setEventTime(long j) {
            this.f12930b = j;
        }

        public void setDownloadUrl(String str) {
            if (this.f12931c == null) {
                this.f12931c = str;
                return;
            }
            this.f12931c += C4963cj.f20745b + str;
        }

        public void setResolveIp(String str) {
            this.f12932d = str;
        }

        public void setHttpCode(int i) {
            this.f12933e = i;
        }

        public void setPatchUpdateFlag(int i) {
            this.f12934f = i;
        }

        public void setDownloadCancel(int i) {
            this.f12935g = i;
        }

        public void setUnpkgFlag(int i) {
            this.f12936h = i;
        }

        public void setApn(String str) {
            this.f12937i = str;
        }

        public void setNetworkType(int i) {
            this.f12938j = i;
        }

        public void setDownFinalFlag(int i) {
            this.f12939k = i;
        }

        public int getDownFinalFlag() {
            return this.f12939k;
        }

        public void setPkgSize(long j) {
            this.f12940l = j;
        }

        public void setDownConsumeTime(long j) {
            this.f12941m += j;
        }

        public void setNetworkChange(int i) {
            this.f12942n = i;
        }

        public void setErrorCode(int i) {
            if (!(i == 100 || i == 110 || i == 120 || i == 111 || i >= 400)) {
                TbsLog.m16530i(TbsDownloader.LOGTAG, "error occured, errorCode:" + i, true);
            }
            if (i == 111) {
                TbsLog.m16530i(TbsDownloader.LOGTAG, "you are not in wifi, downloading stoped", true);
            }
            this.f12929a = i;
        }

        public void setCheckErrorDetail(String str) {
            setErrorCode(108);
            this.f12943o = str;
        }

        public void setFailDetail(String str) {
            if (str != null) {
                if (str.length() > 1024) {
                    str = str.substring(0, 1024);
                }
                this.f12944p = str;
            }
        }

        public void setFailDetail(Throwable th) {
            if (th == null) {
                this.f12944p = "";
                return;
            }
            String stackTraceString = Log.getStackTraceString(th);
            if (stackTraceString.length() > 1024) {
                stackTraceString = stackTraceString.substring(0, 1024);
            }
            this.f12944p = stackTraceString;
        }

        public void setDownloadSize(long j) {
            this.f12945q += j;
        }
    }

    public TbsLogInfo tbsLogInfo() {
        return new TbsLogInfo();
    }

    public void setInstallErrorCode(int i, String str) {
        setInstallErrorCode(i, str, EventType.TYPE_INSTALL);
    }

    public void setInstallErrorCode(int i, String str, EventType eventType) {
        if (!(i == 200 || i == 220 || i == 221)) {
            TbsLog.m16530i(TbsDownloader.LOGTAG, "error occured in installation, errorCode:" + i, true);
        }
        TbsLogInfo tbsLogInfo = tbsLogInfo();
        tbsLogInfo.setFailDetail(str);
        m17003a(i, tbsLogInfo, eventType);
    }

    /* renamed from: a */
    private void m17003a(int i, TbsLogInfo tbsLogInfo, EventType eventType) {
        tbsLogInfo.setErrorCode(i);
        tbsLogInfo.setEventTime(System.currentTimeMillis());
        QbSdk.f12801m.onInstallFinish(i);
        eventReport(eventType, tbsLogInfo);
    }

    public void setInstallErrorCode(int i, Throwable th) {
        TbsLogInfo tbsLogInfo = tbsLogInfo();
        tbsLogInfo.setFailDetail(th);
        m17003a(i, tbsLogInfo, EventType.TYPE_INSTALL);
    }

    public void setLoadErrorCode(int i, String str) {
        TbsLogInfo tbsLogInfo = tbsLogInfo();
        tbsLogInfo.setErrorCode(i);
        tbsLogInfo.setEventTime(System.currentTimeMillis());
        tbsLogInfo.setFailDetail(str);
        eventReport(EventType.TYPE_LOAD, tbsLogInfo);
    }

    public void setLoadErrorCode(int i, Throwable th) {
        String str = "NULL";
        if (th != null) {
            str = "msg: " + th.getMessage() + "; err: " + th + "; cause: " + Log.getStackTraceString(th.getCause());
            if (str.length() > 1024) {
                str = str.substring(0, 1024);
            }
        }
        setLoadErrorCode(i, str);
    }

    public void dailyReport() {
        this.f12921b.sendEmptyMessage(UiMessage.CommandToUi.Command_Type.LOAD_PROFILE_VALUE);
    }

    public void eventReport(EventType eventType, TbsLogInfo tbsLogInfo) {
        try {
            Message obtainMessage = this.f12921b.obtainMessage();
            obtainMessage.what = 600;
            obtainMessage.arg1 = eventType.f12928a;
            obtainMessage.obj = (TbsLogInfo) tbsLogInfo.clone();
            this.f12921b.sendMessage(obtainMessage);
        } catch (Throwable th) {
            TbsLog.m16527w("upload", "[TbsLogReport.eventReport] error, message=" + th.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m17004a(int i, TbsLogInfo tbsLogInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append(m17005a(i));
        sb.append(m16999a(AppUtil.m16500c(this.f12922c)));
        sb.append(m16999a(SysCoreQUA2Utils.m16401a(this.f12922c)));
        sb.append(m17005a(TbsInstaller.m16742a().m16688i(this.f12922c)));
        String str = Build.MODEL;
        try {
            str = new String(str.getBytes("UTF-8"), "ISO8859-1");
        } catch (Exception unused) {
        }
        sb.append(m16999a(str));
        String packageName = this.f12922c.getPackageName();
        sb.append(m16999a(packageName));
        if (TbsConfig.APP_WX.equals(packageName)) {
            sb.append(m16999a(AppUtil.m16509a(this.f12922c, TbsDownloader.TBS_METADATA)));
        } else {
            sb.append(m17005a(AppUtil.m16501b(this.f12922c)));
        }
        sb.append(m16999a(m17002a(tbsLogInfo.f12930b)));
        sb.append(m16999a(tbsLogInfo.f12931c));
        sb.append(m16999a(tbsLogInfo.f12932d));
        sb.append(m17005a(tbsLogInfo.f12933e));
        sb.append(m17005a(tbsLogInfo.f12934f));
        sb.append(m17005a(tbsLogInfo.f12935g));
        sb.append(m17005a(tbsLogInfo.f12936h));
        sb.append(m16999a(tbsLogInfo.f12937i));
        sb.append(m17005a(tbsLogInfo.f12938j));
        sb.append(m17005a(tbsLogInfo.f12939k));
        sb.append(m16997b(tbsLogInfo.f12945q));
        sb.append(m16997b(tbsLogInfo.f12940l));
        sb.append(m16997b(tbsLogInfo.f12941m));
        sb.append(m17005a(tbsLogInfo.f12942n));
        sb.append(m17005a(tbsLogInfo.f12929a));
        sb.append(m16999a(tbsLogInfo.f12943o));
        sb.append(m16999a(tbsLogInfo.f12944p));
        sb.append(m17005a(TbsDownloadConfig.getInstance(this.f12922c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0)));
        sb.append(m16999a(AppUtil.m16497f(this.f12922c)));
        sb.append(m16999a("4.3.0.1148_43697"));
        sb.append(false);
        SharedPreferences d = m16994d();
        JSONArray a = m17006a();
        JSONArray jSONArray = new JSONArray();
        if (jSONArray.length() >= 5) {
            for (int i2 = 4; i2 >= 1; i2--) {
                try {
                    jSONArray.put(a.get(jSONArray.length() - i2));
                } catch (Exception unused2) {
                    TbsLog.m16533e("upload", "JSONArray transform error!");
                }
            }
            a = jSONArray;
        }
        a.put(sb.toString());
        SharedPreferences.Editor edit = d.edit();
        edit.putString("tbs_download_upload", a.toString());
        edit.commit();
        if (this.f12923d || i != EventType.TYPE_LOAD.f12928a) {
            m16998b();
        }
    }

    /* renamed from: a */
    private JSONArray m17006a() {
        String string = m16994d().getString("tbs_download_upload", null);
        if (string == null) {
            return new JSONArray();
        }
        try {
            JSONArray jSONArray = new JSONArray(string);
            if (jSONArray.length() > 5) {
                JSONArray jSONArray2 = new JSONArray();
                int length = jSONArray.length() - 1;
                if (length > jSONArray.length() - 5) {
                    jSONArray2.put(jSONArray.get(length));
                    return jSONArray2;
                }
            }
            return jSONArray;
        } catch (Exception unused) {
            return new JSONArray();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x012c, code lost:
        if (r0 != null) goto L_0x0105;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:59:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0129 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0155 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0150 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0124 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v9 */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void reportTbsLog() {
        /*
            Method dump skipped, instructions count: 350
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsLogReport.reportTbsLog():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m16998b() {
        if (QbSdk.f12802n == null || !QbSdk.f12802n.containsKey(QbSdk.KEY_SET_SENDREQUEST_AND_UPLOAD) || !QbSdk.f12802n.get(QbSdk.KEY_SET_SENDREQUEST_AND_UPLOAD).equals("false")) {
            TbsLog.m16531i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat]");
            JSONArray a = m17006a();
            if (a == null || a.length() == 0) {
                TbsLog.m16531i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat] no data");
                return;
            }
            TbsLog.m16531i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat] jsonArray:" + a);
            try {
                String a2 = HttpUtil.m16428a(TbsCommonConfig.m16388a(this.f12922c).m16386c(), a.toString().getBytes(EmailConstants.UTF_8), new HttpUtil.AbstractC2658a() { // from class: com.tencent.smtt.sdk.TbsLogReport.3
                    @Override // com.tencent.smtt.utils.HttpUtil.AbstractC2658a
                    /* renamed from: a */
                    public void mo16424a(int i) {
                        TbsLog.m16531i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat] onHttpResponseCode:" + i);
                        if (i < 300) {
                            TbsLogReport.this.m16995c();
                        }
                    }
                }, true);
                TbsLog.m16531i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat] response:" + a2 + " testcase: -1");
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else {
            TbsLog.m16531i("upload", "[TbsLogReport.sendLogReportRequest] -- SET_SENDREQUEST_AND_UPLOAD is false");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m16995c() {
        SharedPreferences.Editor edit = m16994d().edit();
        edit.remove("tbs_download_upload");
        edit.commit();
    }

    /* renamed from: a */
    private String m17002a(long j) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(j));
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: d */
    private SharedPreferences m16994d() {
        return this.f12922c.getSharedPreferences("tbs_download_stat", 4);
    }

    /* renamed from: a */
    private String m16999a(String str) {
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            str = "";
        }
        sb.append(str);
        sb.append("|");
        return sb.toString();
    }

    /* renamed from: a */
    private String m17005a(int i) {
        return i + "|";
    }

    /* renamed from: b */
    private String m16997b(long j) {
        return j + "|";
    }

    public void clear() {
        try {
            SharedPreferences.Editor edit = m16994d().edit();
            edit.clear();
            edit.commit();
        } catch (Exception unused) {
        }
    }

    /* renamed from: com.tencent.smtt.sdk.TbsLogReport$a */
    /* loaded from: classes2.dex */
    private static class C2589a {

        /* renamed from: a */
        private final String f12946a;

        /* renamed from: b */
        private final String f12947b;

        public C2589a(String str, String str2) {
            this.f12946a = str;
            this.f12947b = str2;
        }

        /* JADX WARN: Can't wrap try/catch for region: R(8:85|3|(7:112|4|5|83|6|110|7)|(10:114|8|101|9|(2:10|(1:12)(1:117))|13|93|14|89|17)|39|91|40|43) */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x008d, code lost:
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x008e, code lost:
            r0.printStackTrace();
         */
        /* JADX WARN: Removed duplicated region for block: B:106:0x0079 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:87:0x006f A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:71:0x00d4 -> B:84:0x00d7). Please submit an issue!!! */
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void m16977a() {
            /*
                Method dump skipped, instructions count: 238
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsLogReport.C2589a.m16977a():void");
        }

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x003d -> B:29:0x0040). Please submit an issue!!! */
        /* renamed from: a */
        private static void m16976a(File file) throws IOException {
            Throwable th;
            Exception e;
            RandomAccessFile randomAccessFile = null;
            try {
                try {
                    try {
                        randomAccessFile = new RandomAccessFile(file, "rw");
                    } catch (Exception e2) {
                        e = e2;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            try {
                int parseInt = Integer.parseInt("00001000", 2);
                randomAccessFile.seek(7L);
                int read = randomAccessFile.read();
                if ((read & parseInt) > 0) {
                    randomAccessFile.seek(7L);
                    randomAccessFile.write((~parseInt) & 255 & read);
                }
                randomAccessFile.close();
            } catch (Exception e4) {
                e = e4;
                randomAccessFile = randomAccessFile;
                e.printStackTrace();
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (Throwable th3) {
                th = th3;
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                throw th;
            }
        }
    }

    public void setShouldUploadEventReport(boolean z) {
        this.f12923d = z;
    }

    public boolean getShouldUploadEventReport() {
        return this.f12923d;
    }
}
