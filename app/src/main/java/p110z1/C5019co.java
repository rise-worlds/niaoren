package p110z1;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.webkit.WebView;
import com.alipay.sdk.app.C0648a;
import com.alipay.sdk.app.C0662k;
import com.alipay.sdk.app.C0663l;
import com.alipay.sdk.app.EnumC0664m;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.tendcloud.tenddata.C2663aa;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.mail.EmailConstants;
import org.json.JSONObject;
import org.slf4j.Marker;
import p110z1.C3894au;

/* renamed from: z1.co */
/* loaded from: classes3.dex */
public class C5019co {

    /* renamed from: a */
    static final String f20922a = "com.eg.android.AlipayGphone";

    /* renamed from: b */
    static final int f20923b = 125;

    /* renamed from: c */
    private static final String f20924c = "com.alipay.android.app";

    /* renamed from: d */
    private static final String f20925d = "com.eg.android.AlipayGphoneRC";

    /* renamed from: e */
    private static final int f20926e = 99;

    /* renamed from: f */
    private static final String[] f20927f = {"10.1.5.1013151", "10.1.5.1013148"};

    /* renamed from: g */
    public static String m4471g(Context context) {
        return "-1;-1";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m4504a() {
        if (C0648a.m25301b()) {
            return f20925d;
        }
        try {
            return C0662k.f328a.get(0).f17546a;
        } catch (Throwable unused) {
            return f20922a;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m4498a(String str) {
        return (!C0648a.m25301b() || !TextUtils.equals(str, f20925d)) ? "com.eg.android.AlipayGphone.IAlixPay" : "com.eg.android.AlipayGphoneRC.IAlixPay";
    }

    /* renamed from: b */
    public static Map<String, String> m4484b(String str) {
        String[] split;
        HashMap hashMap = new HashMap();
        for (String str2 : str.split(C4745bt.f20071b)) {
            int indexOf = str2.indexOf(SimpleComparison.f23609c, 1);
            if (-1 != indexOf) {
                hashMap.put(str2.substring(0, indexOf), URLDecoder.decode(str2.substring(indexOf + 1)));
            }
        }
        return hashMap;
    }

    /* renamed from: a */
    public static Map<String, String> m4490a(C4745bt btVar, String str) {
        String[] split;
        HashMap hashMap = new HashMap(4);
        int indexOf = str.indexOf(63);
        if (indexOf != -1 && indexOf < str.length() - 1) {
            for (String str2 : str.substring(indexOf + 1).split(C4745bt.f20071b)) {
                int indexOf2 = str2.indexOf(61, 1);
                if (indexOf2 != -1 && indexOf2 < str2.length() - 1) {
                    hashMap.put(str2.substring(0, indexOf2), m4482b(btVar, str2.substring(indexOf2 + 1)));
                }
            }
        }
        return hashMap;
    }

    /* renamed from: c */
    public static JSONObject m4479c(String str) {
        try {
            return new JSONObject(str);
        } catch (Throwable unused) {
            return new JSONObject();
        }
    }

    /* renamed from: b */
    public static String m4482b(C4745bt btVar, String str) {
        try {
            return URLDecoder.decode(str, EmailConstants.UTF_8);
        } catch (UnsupportedEncodingException e) {
            C3754ao.m12155a(btVar, C3857aq.f17251b, C3857aq.f17270u, e);
            return "";
        }
    }

    /* renamed from: a */
    public static String m4497a(String str, String str2, String str3) {
        try {
            int indexOf = str3.indexOf(str) + str.length();
            if (indexOf <= str.length()) {
                return "";
            }
            int i = 0;
            if (!TextUtils.isEmpty(str2)) {
                i = str3.indexOf(str2, indexOf);
            }
            if (i < 1) {
                return str3.substring(indexOf);
            }
            return str3.substring(indexOf, i);
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: a */
    public static String m4489a(C4745bt btVar, byte[] bArr) {
        BigInteger modulus;
        try {
            PublicKey publicKey = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr))).getPublicKey();
            if (!(publicKey instanceof RSAPublicKey) || (modulus = ((RSAPublicKey) publicKey).getModulus()) == null) {
                return null;
            }
            return modulus.toString(16);
        } catch (Exception e) {
            C3754ao.m12155a(btVar, C3857aq.f17253d, C3857aq.f17264o, e);
            return null;
        }
    }

    /* renamed from: a */
    public static C5020a m4493a(C4745bt btVar, Context context, List<C3894au.C3895a> list) {
        C5020a a;
        if (list == null) {
            return null;
        }
        for (C3894au.C3895a aVar : list) {
            if (aVar != null && (a = m4494a(btVar, context, aVar.f17546a, aVar.f17547b, aVar.f17548c)) != null && !a.m4468a(btVar) && !a.m4469a()) {
                return a;
            }
        }
        return null;
    }

    /* renamed from: a */
    private static C5020a m4494a(C4745bt btVar, Context context, String str, int i, String str2) {
        PackageInfo packageInfo;
        if (C0648a.m25301b() && f20922a.equals(str)) {
            str = f20925d;
        }
        try {
            packageInfo = m4485b(context, str);
        } catch (Throwable th) {
            C3754ao.m12156a(btVar, C3857aq.f17253d, C3857aq.f17261l, th.getMessage());
            packageInfo = null;
        }
        if (!m4492a(btVar, packageInfo)) {
            return null;
        }
        return m4499a(packageInfo, i, str2);
    }

    /* renamed from: a */
    private static boolean m4492a(C4745bt btVar, PackageInfo packageInfo) {
        String str = "";
        boolean z = false;
        if (packageInfo == null) {
            str = str + "info == null";
        } else if (packageInfo.signatures == null) {
            str = str + "info.signatures == null";
        } else if (packageInfo.signatures.length <= 0) {
            str = str + "info.signatures.length <= 0";
        } else {
            z = true;
        }
        if (!z) {
            C3754ao.m12156a(btVar, C3857aq.f17253d, C3857aq.f17262m, str);
        }
        return z;
    }

    /* renamed from: b */
    private static PackageInfo m4485b(Context context, String str) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getPackageInfo(str, 192);
    }

    /* renamed from: a */
    private static C5020a m4499a(PackageInfo packageInfo, int i, String str) {
        if (packageInfo == null) {
            return null;
        }
        return new C5020a(packageInfo, i, str);
    }

    /* renamed from: z1.co$a */
    /* loaded from: classes3.dex */
    public static final class C5020a {

        /* renamed from: a */
        public final PackageInfo f20928a;

        /* renamed from: b */
        public final int f20929b;

        /* renamed from: c */
        public final String f20930c;

        public C5020a(PackageInfo packageInfo, int i, String str) {
            this.f20928a = packageInfo;
            this.f20929b = i;
            this.f20930c = str;
        }

        /* renamed from: a */
        public boolean m4468a(C4745bt btVar) {
            Signature[] signatureArr = this.f20928a.signatures;
            if (signatureArr == null || signatureArr.length == 0) {
                return false;
            }
            for (Signature signature : signatureArr) {
                String a = C5019co.m4489a(btVar, signature.toByteArray());
                if (a != null && !TextUtils.equals(a, this.f20930c)) {
                    C3754ao.m12156a(btVar, C3857aq.f17251b, C3857aq.f17272w, String.format("Got %s, expected %s", a, this.f20930c));
                    return true;
                }
            }
            return false;
        }

        /* renamed from: a */
        public boolean m4469a() {
            return this.f20928a.versionCode < this.f20929b;
        }
    }

    /* renamed from: a */
    public static boolean m4502a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(f20924c, 128) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m4483b(C4745bt btVar, Context context, List<C3894au.C3895a> list) {
        try {
            for (C3894au.C3895a aVar : list) {
                if (aVar != null) {
                    String str = aVar.f17546a;
                    if (C0648a.m25301b() && f20922a.equals(str)) {
                        str = f20925d;
                    }
                    try {
                        if (context.getPackageManager().getPackageInfo(str, 128) != null) {
                            return true;
                        }
                    } catch (PackageManager.NameNotFoundException unused) {
                        continue;
                    }
                }
            }
            return false;
        } catch (Throwable th) {
            C3754ao.m12155a(btVar, C3857aq.f17251b, C3857aq.f17228T, th);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m4500a(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        try {
            String str = packageInfo.versionName;
            if (!TextUtils.equals(str, f20927f[0])) {
                if (!TextUtils.equals(str, f20927f[1])) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m4486b(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(m4504a(), 128);
            if (packageInfo == null) {
                return false;
            }
            return packageInfo.versionCode < 99;
        } catch (Throwable th) {
            C4921cd.m5618a(th);
            return false;
        }
    }

    /* renamed from: c */
    public static String m4480c(Context context) {
        String b = m4487b();
        String c = m4481c();
        String d = m4477d(context);
        String e = m4475e(context);
        return " (" + b + C4963cj.f20745b + c + C4963cj.f20745b + d + C4963cj.f20745b + C4963cj.f20745b + e + ")(sdk android)";
    }

    /* renamed from: b */
    public static String m4487b() {
        return "Android " + Build.VERSION.RELEASE;
    }

    /* renamed from: c */
    public static String m4481c() {
        String d = m4478d();
        int indexOf = d.indexOf("-");
        if (indexOf != -1) {
            d = d.substring(0, indexOf);
        }
        int indexOf2 = d.indexOf("\n");
        if (indexOf2 != -1) {
            d = d.substring(0, indexOf2);
        }
        return "Linux " + d;
    }

    /* renamed from: d */
    private static String m4478d() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/version"), 256);
            String readLine = bufferedReader.readLine();
            bufferedReader.close();
            Matcher matcher = Pattern.compile("\\w+\\s+\\w+\\s+([^\\s]+)\\s+\\(([^\\s@]+(?:@[^\\s.]+)?)[^)]*\\)\\s+\\((?:[^(]*\\([^)]*\\))?[^)]*\\)\\s+([^\\s]+)\\s+(?:PREEMPT\\s+)?(.+)").matcher(readLine);
            if (!matcher.matches() || matcher.groupCount() < 4) {
                return "Unavailable";
            }
            return matcher.group(1) + "\n" + matcher.group(2) + ExpandableTextView.f6958c + matcher.group(3) + "\n" + matcher.group(4);
        } catch (IOException unused) {
            return "Unavailable";
        }
    }

    /* renamed from: d */
    public static String m4477d(Context context) {
        return context.getResources().getConfiguration().locale.toString();
    }

    /* renamed from: e */
    public static String m4475e(Context context) {
        DisplayMetrics h = m4470h(context);
        return h.widthPixels + Marker.ANY_MARKER + h.heightPixels;
    }

    /* renamed from: h */
    private static DisplayMetrics m4470h(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    /* renamed from: f */
    public static String m4473f(Context context) {
        String a = C4998cn.m4582a(context);
        return a.substring(0, a.indexOf(C2663aa.f13457a));
    }

    /* renamed from: a */
    public static String m4503a(int i) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            switch (random.nextInt(3)) {
                case 0:
                    sb.append(String.valueOf((char) Math.round((Math.random() * 25.0d) + 65.0d)));
                    break;
                case 1:
                    sb.append(String.valueOf((char) Math.round((Math.random() * 25.0d) + 97.0d)));
                    break;
                case 2:
                    sb.append(String.valueOf(new Random().nextInt(10)));
                    break;
            }
        }
        return sb.toString();
    }

    /* renamed from: d */
    public static boolean m4476d(String str) {
        return Pattern.compile("^http(s)?://([a-z0-9_\\-]+\\.)*(alipaydev|alipay|taobao)\\.(com|net)(:\\d+)?(/.*)?$").matcher(str).matches();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m4501a(Context context, String str) {
        String str2 = "";
        try {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getApplicationContext().getSystemService(ServiceManagerNative.ACTIVITY)).getRunningAppProcesses()) {
                if (runningAppProcessInfo.processName.equals(str)) {
                    str2 = str2 + "#M";
                } else {
                    if (runningAppProcessInfo.processName.startsWith(str + ":")) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(str2);
                        sb.append("#");
                        sb.append(runningAppProcessInfo.processName.replace(str + ":", ""));
                        str2 = sb.toString();
                    }
                }
            }
        } catch (Throwable unused) {
            str2 = "";
        }
        if (str2.length() > 0) {
            str2 = str2.substring(1);
        }
        return str2.length() == 0 ? "N" : str2;
    }

    /* renamed from: a */
    public static boolean m4491a(C4745bt btVar, WebView webView, String str, Activity activity) {
        C5020a a;
        int parseInt;
        String str2;
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        if (activity == null) {
            return false;
        }
        if (str.toLowerCase().startsWith(C3876ar.f17435l.toLowerCase()) || str.toLowerCase().startsWith(C3876ar.f17436m.toLowerCase())) {
            try {
                a = m4493a(btVar, activity, C0662k.f328a);
            } catch (Throwable unused) {
            }
            if (a != null && !a.m4469a() && !a.m4468a(btVar)) {
                if (str.startsWith("intent://platformapi/startapp")) {
                    str = str.replaceFirst("intent://platformapi/startapp\\?", C3876ar.f17435l);
                }
                activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            }
            return true;
        } else if (TextUtils.equals(str, C3876ar.f17438o) || TextUtils.equals(str, C3876ar.f17439p)) {
            C0663l.m25285a(C0663l.m25282c());
            activity.finish();
            return true;
        } else if (!str.startsWith(C3876ar.f17437n)) {
            return false;
        } else {
            try {
                String substring = str.substring(str.indexOf(C3876ar.f17437n) + 24);
                parseInt = Integer.parseInt(substring.substring(substring.lastIndexOf(C3876ar.f17440q) + 10));
            } catch (Exception unused2) {
                C0663l.m25285a(C0663l.m25280e());
            }
            if (!(parseInt == EnumC0664m.SUCCEEDED.m25279a() || parseInt == EnumC0664m.PAY_WAITTING.m25279a())) {
                EnumC0664m b = EnumC0664m.m25275b(EnumC0664m.FAILED.m25279a());
                C0663l.m25285a(C0663l.m25286a(b.m25279a(), b.m25276b(), ""));
                activity.runOnUiThread(new RunnableC5067cp(activity));
                return true;
            }
            if (C3876ar.f17444u) {
                StringBuilder sb = new StringBuilder();
                String decode = URLDecoder.decode(str);
                String decode2 = URLDecoder.decode(decode);
                String str3 = decode2.substring(decode2.indexOf(C3876ar.f17437n) + 24, decode2.lastIndexOf(C3876ar.f17440q)).split(C3876ar.f17442s)[0];
                int indexOf = decode.indexOf(C3876ar.f17442s) + 12;
                sb.append(str3);
                sb.append(C3876ar.f17442s);
                sb.append(decode.substring(indexOf, decode.indexOf(C4745bt.f20071b, indexOf)));
                sb.append(decode.substring(decode.indexOf(C4745bt.f20071b, indexOf)));
                str2 = sb.toString();
            } else {
                String decode3 = URLDecoder.decode(str);
                str2 = decode3.substring(decode3.indexOf(C3876ar.f17437n) + 24, decode3.lastIndexOf(C3876ar.f17440q));
            }
            EnumC0664m b2 = EnumC0664m.m25275b(parseInt);
            C0663l.m25285a(C0663l.m25286a(b2.m25279a(), b2.m25276b(), str2));
            activity.runOnUiThread(new RunnableC5067cp(activity));
            return true;
        }
    }

    /* renamed from: a */
    public static String m4496a(C4745bt btVar, Context context) {
        return m4495a(btVar, context, context.getPackageName());
    }

    /* renamed from: a */
    private static String m4495a(C4745bt btVar, Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 128).versionName;
        } catch (Throwable th) {
            C3754ao.m12155a(btVar, C3857aq.f17251b, C3857aq.f17261l, th);
            return "";
        }
    }

    /* renamed from: e */
    public static String m4474e(String str) {
        try {
            Uri parse = Uri.parse(str);
            return String.format("%s%s", parse.getAuthority(), parse.getPath());
        } catch (Throwable th) {
            C4921cd.m5618a(th);
            return "-";
        }
    }

    /* renamed from: f */
    public static String m4472f(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            return m4488a(instance.digest());
        } catch (NoSuchAlgorithmException unused) {
            return "";
        }
    }

    /* renamed from: a */
    private static String m4488a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            sb.append(Character.forDigit((b & 240) >> 4, 16));
            sb.append(Character.forDigit(b & 15, 16));
        }
        return sb.toString();
    }
}
