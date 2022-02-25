package p110z1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.text.TextUtils;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONObject;
import p110z1.C3754ao;

/* renamed from: z1.aq */
/* loaded from: classes3.dex */
public class C3857aq {

    /* renamed from: A */
    public static final String f17209A = "ClientBindException";

    /* renamed from: B */
    public static final String f17210B = "SaveTradeTokenError";

    /* renamed from: C */
    public static final String f17211C = "ClientBindServiceFailed";

    /* renamed from: D */
    public static final String f17212D = "BindWaitTimeoutEx";

    /* renamed from: E */
    public static final String f17213E = "CheckClientExistEx";

    /* renamed from: F */
    public static final String f17214F = "CheckClientSignEx";

    /* renamed from: G */
    public static final String f17215G = "GetInstalledAppEx";

    /* renamed from: H */
    public static final String f17216H = "ParserTidClientKeyEx";

    /* renamed from: I */
    public static final String f17217I = "PgApiInvoke";

    /* renamed from: J */
    public static final String f17218J = "PgBindStarting";

    /* renamed from: K */
    public static final String f17219K = "PgBinded";

    /* renamed from: L */
    public static final String f17220L = "PgBindEnd";

    /* renamed from: M */
    public static final String f17221M = "PgBindPay";

    /* renamed from: N */
    public static final String f17222N = "PgReturn";

    /* renamed from: O */
    public static final String f17223O = "PgWltVer";

    /* renamed from: P */
    public static final String f17224P = "PgOpenStarting";

    /* renamed from: Q */
    public static final String f17225Q = "ErrActNull";

    /* renamed from: R */
    public static final String f17226R = "GetInstalledAppEx";

    /* renamed from: S */
    public static final String f17227S = "StartLaunchAppTransEx";

    /* renamed from: T */
    public static final String f17228T = "CheckLaunchAppExistEx";

    /* renamed from: U */
    public static final String f17229U = "LogCurrentAppLaunchSwitch";

    /* renamed from: V */
    public static final String f17230V = "LogCurrentQueryTime";

    /* renamed from: W */
    public static final String f17231W = "LogCalledPackage";

    /* renamed from: X */
    public static final String f17232X = "LogBindCalledH5";

    /* renamed from: Y */
    public static final String f17233Y = "LogCalledH5";

    /* renamed from: Z */
    public static final String f17234Z = "LogHkLoginByIntent";

    /* renamed from: a */
    public static final String f17235a = "net";

    /* renamed from: aa */
    public static final String f17236aa = "SchemePayWrongHashEx";

    /* renamed from: ab */
    public static final String f17237ab = "LogAppLaunchSwitchEnabled";

    /* renamed from: ac */
    public static final String f17238ac = "H5CbUrlEmpty";

    /* renamed from: ad */
    public static final String f17239ad = "H5CbEx";

    /* renamed from: ae */
    public static final String f17240ae = "BuildSchemePayUriError";

    /* renamed from: af */
    public static final String f17241af = "StartActivityEx";

    /* renamed from: ag */
    public static final String f17242ag = "JSONEx";

    /* renamed from: ah */
    public static final String f17243ah = "ParseBundleSerializableError";

    /* renamed from: ai */
    public static final String f17244ai = "ParseSchemeQueryError";

    /* renamed from: aj */
    public static final String f17245aj = "tid_context_null";

    /* renamed from: ak */
    public static final String f17246ak = "partner";

    /* renamed from: al */
    public static final String f17247al = "out_trade_no";

    /* renamed from: am */
    public static final String f17248am = "trade_no";

    /* renamed from: an */
    public static final String f17249an = "biz_content";

    /* renamed from: ao */
    public static final String f17250ao = "app_id";

    /* renamed from: b */
    public static final String f17251b = "biz";

    /* renamed from: c */
    public static final String f17252c = "cp";

    /* renamed from: d */
    public static final String f17253d = "auth";

    /* renamed from: e */
    public static final String f17254e = "third";

    /* renamed from: f */
    public static final String f17255f = "tid";

    /* renamed from: g */
    public static final String f17256g = "FormatResultEx";

    /* renamed from: h */
    public static final String f17257h = "GetApdidEx";

    /* renamed from: i */
    public static final String f17258i = "GetApdidNull";

    /* renamed from: j */
    public static final String f17259j = "GetApdidTimeout";

    /* renamed from: k */
    public static final String f17260k = "GetUtdidEx";

    /* renamed from: l */
    public static final String f17261l = "GetPackageInfoEx";

    /* renamed from: m */
    public static final String f17262m = "NotIncludeSignatures";

    /* renamed from: n */
    public static final String f17263n = "GetInstalledPackagesEx";

    /* renamed from: o */
    public static final String f17264o = "GetPublicKeyFromSignEx";

    /* renamed from: p */
    public static final String f17265p = "H5PayNetworkError";

    /* renamed from: q */
    public static final String f17266q = "H5AuthNetworkError";

    /* renamed from: r */
    public static final String f17267r = "SSLError";

    /* renamed from: s */
    public static final String f17268s = "SSLProceed";

    /* renamed from: t */
    public static final String f17269t = "SSLDenied";

    /* renamed from: u */
    public static final String f17270u = "H5PayDataAnalysisError";

    /* renamed from: v */
    public static final String f17271v = "H5AuthDataAnalysisError";

    /* renamed from: w */
    public static final String f17272w = "PublicKeyUnmatch";

    /* renamed from: x */
    public static final String f17273x = "ClientBindFailed";

    /* renamed from: y */
    public static final String f17274y = "TriDesEncryptError";

    /* renamed from: z */
    public static final String f17275z = "TriDesDecryptError";

    /* renamed from: ap */
    private String f17276ap;

    /* renamed from: aq */
    private String f17277aq;

    /* renamed from: ar */
    private String f17278ar;

    /* renamed from: as */
    private String f17279as;

    /* renamed from: at */
    private String f17280at;

    /* renamed from: au */
    private String f17281au;

    /* renamed from: av */
    private String f17282av;

    /* renamed from: aw */
    private String f17283aw = "";

    /* renamed from: ax */
    private String f17284ax = "";

    /* renamed from: ay */
    private String f17285ay;

    public C3857aq(Context context, boolean z) {
        context = context != null ? context.getApplicationContext() : context;
        this.f17276ap = m11636c();
        this.f17278ar = m11648a(context);
        this.f17279as = m11649a(z ? 0L : C3754ao.C3758c.m12139a(context));
        this.f17280at = m11633d();
        this.f17281au = m11639b(context);
        this.f17282av = "-";
        this.f17285ay = "-";
    }

    /* renamed from: a */
    private boolean m11650a() {
        return TextUtils.isEmpty(this.f17284ax);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m11643a(String str, String str2, Throwable th) {
        m11634c(str, str2, m11641a(th));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m11642a(String str, String str2, Throwable th, String str3) {
        String a = m11641a(th);
        m11634c(str, str2, str3 + ": " + a);
    }

    /* renamed from: c */
    private synchronized void m11634c(String str, String str2, String str3) {
        C4921cd.m5613d(C3876ar.f17447x, String.format("err %s %s %s", str, str2, str3));
        String str4 = "";
        if (!TextUtils.isEmpty(this.f17284ax)) {
            str4 = str4 + "^";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str4);
        Object[] objArr = new Object[4];
        objArr[0] = str;
        objArr[1] = str2;
        objArr[2] = TextUtils.isEmpty(str3) ? "-" : m11638b(str3);
        objArr[3] = m11638b(m11640b());
        sb.append(String.format("%s,%s,%s,%s", objArr));
        this.f17284ax += sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m11644a(String str, String str2, String str3) {
        m11634c(str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m11637b(String str, String str2, String str3) {
        m11631d("", str, str2 + "|" + str3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m11645a(String str, String str2) {
        m11631d("", str, str2);
    }

    /* renamed from: d */
    private synchronized void m11631d(String str, String str2, String str3) {
        C4921cd.m5616b(C3876ar.f17447x, String.format("event %s %s %s", str, str2, str3));
        String str4 = "";
        if (!TextUtils.isEmpty(this.f17283aw)) {
            str4 = str4 + "^";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str4);
        Object[] objArr = new Object[4];
        objArr[0] = TextUtils.isEmpty(str) ? "-" : m11638b(str);
        objArr[1] = m11638b(str2);
        objArr[2] = m11638b(str3);
        objArr[3] = m11638b(m11640b());
        sb.append(String.format("%s,%s,%s,-,-,-,-,-,-,-,-,-,-,%s", objArr));
        this.f17283aw += sb.toString();
    }

    /* renamed from: b */
    private static String m11640b() {
        return new SimpleDateFormat("HH:mm:ss:SSS", Locale.getDefault()).format(new Date());
    }

    /* renamed from: b */
    private static String m11638b(String str) {
        return TextUtils.isEmpty(str) ? "" : str.replace("[", "【").replace("]", "】").replace("(", "（").replace(")", "）").replace(",", "，").replace("^", "~").replace("#", "＃");
    }

    /* renamed from: c */
    private static String m11635c(String str) {
        return TextUtils.isEmpty(str) ? "-" : str;
    }

    /* renamed from: a */
    private static String m11641a(Throwable th) {
        if (th == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append(th.getClass().getName());
            stringBuffer.append(":");
            stringBuffer.append(th.getMessage());
            stringBuffer.append(" 》 ");
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null) {
                int i = 0;
                for (StackTraceElement stackTraceElement : stackTrace) {
                    stringBuffer.append(stackTraceElement.toString());
                    stringBuffer.append(" 》 ");
                    i++;
                    if (i > 5) {
                        break;
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public String m11646a(String str) {
        this.f17277aq = m11632d(str);
        return String.format("[(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s)]", this.f17276ap, this.f17277aq, this.f17278ar, this.f17279as, this.f17280at, this.f17281au, this.f17282av, m11635c(this.f17283aw), m11635c(this.f17284ax), this.f17285ay);
    }

    @SuppressLint({"SimpleDateFormat"})
    /* renamed from: c */
    private static String m11636c() {
        return String.format("123456789,%s", new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date()));
    }

    /* renamed from: d */
    private static String m11632d(String str) {
        String str2;
        String str3;
        if (str == null) {
            str = "";
        }
        String[] split = str.split(C4745bt.f20071b);
        String str4 = null;
        if (split != null) {
            str3 = null;
            str2 = null;
            String str5 = null;
            for (String str6 : split) {
                String[] split2 = str6.split(SimpleComparison.f23609c);
                if (split2 != null && split2.length == 2) {
                    if (split2[0].equalsIgnoreCase(f17246ak)) {
                        str3 = split2[1].replace("\"", "");
                    } else if (split2[0].equalsIgnoreCase(f17247al)) {
                        str2 = split2[1].replace("\"", "");
                    } else if (split2[0].equalsIgnoreCase(f17248am)) {
                        str5 = split2[1].replace("\"", "");
                    } else if (split2[0].equalsIgnoreCase(f17249an)) {
                        try {
                            JSONObject jSONObject = new JSONObject(C5019co.m4482b(C4745bt.m9418a(), split2[1]));
                            if (TextUtils.isEmpty(str2)) {
                                str2 = jSONObject.getString(f17247al);
                            }
                        } catch (Throwable unused) {
                        }
                    } else if (split2[0].equalsIgnoreCase(f17250ao) && TextUtils.isEmpty(str3)) {
                        str3 = split2[1];
                    }
                }
            }
            str4 = str5;
        } else {
            str3 = null;
            str2 = null;
        }
        return String.format("%s,%s,-,%s,-,-,-", m11638b(str4), m11638b(str2), m11638b(str3));
    }

    /* renamed from: a */
    private static String m11648a(Context context) {
        String str = "-";
        String str2 = "-";
        if (context != null) {
            try {
                Context applicationContext = context.getApplicationContext();
                str = applicationContext.getPackageName();
                PackageInfo packageInfo = applicationContext.getPackageManager().getPackageInfo(str, 64);
                str2 = packageInfo.versionName + "|" + m11647a(packageInfo);
            } catch (Throwable unused) {
            }
        }
        return String.format("%s,%s,-,-,-", m11638b(str), m11638b(str2));
    }

    /* renamed from: a */
    private static String m11647a(PackageInfo packageInfo) {
        if (packageInfo == null || packageInfo.signatures == null || packageInfo.signatures.length == 0) {
            return ResultTypeConstant.f7213z;
        }
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(packageInfo.signatures.length);
            for (Signature signature : packageInfo.signatures) {
                String str = "?";
                try {
                    String a = C5019co.m4489a((C4745bt) null, signature.toByteArray());
                    str = TextUtils.isEmpty(a) ? "?" : C5019co.m4472f(a).substring(0, 8);
                } catch (Throwable unused) {
                }
                sb.append("-");
                sb.append(str);
            }
            return sb.toString();
        } catch (Throwable unused2) {
            return "?";
        }
    }

    /* renamed from: a */
    private static String m11649a(long j) {
        String b = m11638b("15.7.3");
        String b2 = m11638b("h.a.3.7.3");
        return String.format("android,3,%s,%s,com.alipay.mcpay,5.0,-,%s,-", b, b2, "~" + j);
    }

    /* renamed from: d */
    private static String m11633d() {
        return String.format("%s,%s,-,-,-", m11638b(C4814by.m8377a(C4759bu.m9273a().m9271b()).m8378a()), m11638b(C4759bu.m9273a().m9268e()));
    }

    /* renamed from: b */
    private static String m11639b(Context context) {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,-", m11638b(C4873ca.m6511d(context)), "android", m11638b(Build.VERSION.RELEASE), m11638b(Build.MODEL), "-", m11638b(C4873ca.m6517a(context).m6518a()), m11638b(C4873ca.m6515b(context).m5536b()), "gw", m11638b(C4873ca.m6517a(context).m6516b()));
    }
}
