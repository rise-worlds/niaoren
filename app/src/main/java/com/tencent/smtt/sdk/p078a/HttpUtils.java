package com.tencent.smtt.sdk.p078a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.text.TextUtils;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsConfig;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsDownloadUpload;
import com.tencent.smtt.sdk.TbsDownloader;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.sdk.TbsLogReport;
import com.tencent.smtt.sdk.TbsPVConfig;
import com.tencent.smtt.sdk.TbsShareManager;
import com.tencent.smtt.utils.AppUtil;
import com.tencent.smtt.utils.Post3DESEncryption;
import com.tencent.smtt.utils.SysCoreQUA2Utils;
import com.tencent.smtt.utils.TbsCommonConfig;
import com.tencent.smtt.utils.TbsLog;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import org.apache.commons.mail.EmailConstants;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;
import p110z1.C3894au;
import p110z1.C4745bt;
import p110z1.Consts;
import p110z1.SimpleComparison;
import p110z1.ThirdAppInfoNew;

/* renamed from: com.tencent.smtt.sdk.a.b */
/* loaded from: classes2.dex */
public class HttpUtils {

    /* renamed from: a */
    public static byte[] f13083a;

    static {
        try {
            f13083a = "65dRa93L".getBytes(EmailConstants.UTF_8);
        } catch (UnsupportedEncodingException unused) {
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.tencent.smtt.sdk.a.b$1] */
    /* renamed from: a */
    public static void m16888a(final ThirdAppInfoNew aVar, final Context context) {
        new Thread("HttpUtils") { // from class: com.tencent.smtt.sdk.a.b.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                String str;
                byte[] bArr;
                aVar.f15041k = AppUtil.m16512a();
                if (Build.VERSION.SDK_INT >= 8) {
                    JSONObject jSONObject = null;
                    if (HttpUtils.f13083a == null) {
                        try {
                            HttpUtils.f13083a = "65dRa93L".getBytes(EmailConstants.UTF_8);
                        } catch (UnsupportedEncodingException unused) {
                            HttpUtils.f13083a = null;
                            TbsLog.m16533e("sdkreport", "Post failed -- get POST_DATA_KEY failed!");
                        }
                    }
                    if (HttpUtils.f13083a == null) {
                        TbsLog.m16533e("sdkreport", "Post failed -- POST_DATA_KEY is null!");
                        return;
                    }
                    String string = TbsDownloadConfig.getInstance(context).mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_DESkEY_TOKEN, "");
                    String str2 = "";
                    String str3 = "";
                    if (!TextUtils.isEmpty(string)) {
                        str2 = string.substring(0, string.indexOf(C4745bt.f20071b));
                        str3 = string.substring(string.indexOf(C4745bt.f20071b) + 1, string.length());
                    }
                    boolean z = TextUtils.isEmpty(str2) || str2.length() != 96 || TextUtils.isEmpty(str3) || str3.length() != 24;
                    try {
                        TbsCommonConfig a = TbsCommonConfig.m16389a();
                        if (z) {
                            str = a.m16387b() + Post3DESEncryption.m16423a().m16420b();
                        } else {
                            str = a.m16383f() + str2;
                        }
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);
                        httpURLConnection.setUseCaches(false);
                        httpURLConnection.setConnectTimeout(C3894au.f17525h);
                        if (Build.VERSION.SDK_INT > 13) {
                            httpURLConnection.setRequestProperty(HTTP.CONN_DIRECTIVE, "close");
                        }
                        try {
                            jSONObject = HttpUtils.m16884c(aVar, context);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (jSONObject == null) {
                            TbsLog.m16533e("sdkreport", "post -- jsonData is null!");
                            return;
                        }
                        try {
                            byte[] bytes = jSONObject.toString().getBytes(EmailConstants.UTF_8);
                            if (z) {
                                bArr = Post3DESEncryption.m16423a().m16422a(bytes);
                            } else {
                                bArr = Post3DESEncryption.m16421a(bytes, str3);
                            }
                            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(bArr.length));
                            try {
                                OutputStream outputStream = httpURLConnection.getOutputStream();
                                outputStream.write(bArr);
                                outputStream.flush();
                                if (httpURLConnection.getResponseCode() == 200) {
                                    TbsLog.m16531i("sdkreport", "Post successful!");
                                    TbsLog.m16531i("sdkreport", "SIGNATURE is " + jSONObject.getString("SIGNATURE"));
                                    HttpUtils.m16887b(context, HttpUtils.m16886b(httpURLConnection, str3, z));
                                    new TbsDownloadUpload(context).clearUploadCode();
                                    return;
                                }
                                TbsLog.m16533e("sdkreport", "Post failed -- not 200 code is " + httpURLConnection.getResponseCode());
                                TbsLogReport.TbsLogInfo tbsLogInfo = TbsLogReport.getInstance(context).tbsLogInfo();
                                tbsLogInfo.setErrorCode(TbsListener.ErrorCode.PV_UPLOAD_ERROR);
                                tbsLogInfo.setFailDetail("" + httpURLConnection.getResponseCode());
                                TbsLogReport.getInstance(context).eventReport(TbsLogReport.EventType.TYPE_DOWNLOAD, tbsLogInfo);
                            } catch (Throwable th) {
                                TbsLog.m16533e("sdkreport", "Post failed -- exceptions:" + th.getMessage());
                                TbsLogReport.TbsLogInfo tbsLogInfo2 = TbsLogReport.getInstance(context).tbsLogInfo();
                                tbsLogInfo2.setErrorCode(TbsListener.ErrorCode.PV_UPLOAD_ERROR);
                                tbsLogInfo2.setFailDetail(th);
                                TbsLogReport.getInstance(context).eventReport(TbsLogReport.EventType.TYPE_DOWNLOAD, tbsLogInfo2);
                            }
                        } catch (Throwable unused2) {
                        }
                    } catch (IOException e2) {
                        TbsLog.m16533e("sdkreport", "Post failed -- IOException:" + e2);
                    } catch (AssertionError e3) {
                        TbsLog.m16533e("sdkreport", "Post failed -- AssertionError:" + e3);
                    } catch (NoClassDefFoundError e4) {
                        TbsLog.m16533e("sdkreport", "Post failed -- NoClassDefFoundError:" + e4);
                    }
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(15:40|2|(12:9|(1:11)(1:12)|14|(1:16)(1:17)|18|(4:20|(1:22)(1:23)|24|(1:26))|27|43|28|(2:30|(1:32)(2:33|(1:35)))|42|36)|13|14|(0)(0)|18|(0)|27|43|28|(0)|42|36|(1:(0))) */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00f3 A[Catch: Exception -> 0x018d, TryCatch #0 {Exception -> 0x018d, blocks: (B:2:0x0000, B:4:0x0071, B:6:0x007b, B:9:0x0086, B:11:0x008a, B:12:0x0092, B:13:0x009a, B:14:0x00e0, B:16:0x00f3, B:17:0x00fd, B:18:0x010f, B:20:0x0113, B:22:0x0127, B:23:0x0135, B:26:0x013d, B:27:0x0144), top: B:40:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00fd A[Catch: Exception -> 0x018d, TryCatch #0 {Exception -> 0x018d, blocks: (B:2:0x0000, B:4:0x0071, B:6:0x007b, B:9:0x0086, B:11:0x008a, B:12:0x0092, B:13:0x009a, B:14:0x00e0, B:16:0x00f3, B:17:0x00fd, B:18:0x010f, B:20:0x0113, B:22:0x0127, B:23:0x0135, B:26:0x013d, B:27:0x0144), top: B:40:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0113 A[Catch: Exception -> 0x018d, TryCatch #0 {Exception -> 0x018d, blocks: (B:2:0x0000, B:4:0x0071, B:6:0x007b, B:9:0x0086, B:11:0x008a, B:12:0x0092, B:13:0x009a, B:14:0x00e0, B:16:0x00f3, B:17:0x00fd, B:18:0x010f, B:20:0x0113, B:22:0x0127, B:23:0x0135, B:26:0x013d, B:27:0x0144), top: B:40:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0151 A[Catch: Exception -> 0x018c, TryCatch #1 {Exception -> 0x018c, blocks: (B:28:0x014b, B:30:0x0151, B:32:0x015b, B:33:0x0173, B:35:0x017d), top: B:43:0x014b }] */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.json.JSONObject m16884c(p110z1.ThirdAppInfoNew r8, android.content.Context r9) {
        /*
            Method dump skipped, instructions count: 406
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.p078a.HttpUtils.m16884c(z1.a, android.content.Context):org.json.JSONObject");
    }

    /* renamed from: a */
    public static void m16890a(Context context, String str, String str2, String str3, int i, boolean z, long j, boolean z2) {
        if (QbSdk.getSettings() == null || !QbSdk.getSettings().containsKey(QbSdk.KEY_SET_SENDREQUEST_AND_UPLOAD) || !QbSdk.getSettings().get(QbSdk.KEY_SET_SENDREQUEST_AND_UPLOAD).equals("false")) {
            String str4 = "";
            try {
                ApplicationInfo applicationInfo = context.getApplicationInfo();
                if (TbsConfig.APP_QQ.equals(applicationInfo.packageName)) {
                    str4 = context.getPackageManager().getPackageInfo(applicationInfo.packageName, 0).versionName;
                    if (!TextUtils.isEmpty(QbSdk.getQQBuildNumber())) {
                        str4 = str4 + Consts.f23430h + QbSdk.getQQBuildNumber();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                ThirdAppInfoNew aVar = new ThirdAppInfoNew();
                aVar.f15031a = context.getApplicationContext().getApplicationInfo().packageName;
                TbsCommonConfig.m16388a(context);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08"));
                aVar.f15032b = simpleDateFormat.format(Calendar.getInstance().getTime());
                aVar.f15040j = AppUtil.m16501b(context);
                String a = AppUtil.m16509a(context, TbsDownloader.TBS_METADATA);
                if (!TextUtils.isEmpty(a)) {
                    aVar.f15039i = a;
                }
                aVar.f15035e = str;
                if (z) {
                    aVar.f15033c = str2;
                    aVar.f15044n = z2;
                } else {
                    aVar.f15033c = SysCoreQUA2Utils.m16401a(context);
                }
                aVar.f15034d = str3;
                String e2 = AppUtil.m16498e(context);
                String c = AppUtil.m16500c(context);
                String d = AppUtil.m16499d(context);
                String f = AppUtil.m16497f(context);
                if (c != null && !"".equals(c)) {
                    aVar.f15036f = c;
                }
                if (d != null && !"".equals(d)) {
                    aVar.f15037g = d;
                }
                if (!TextUtils.isEmpty(f)) {
                    aVar.f15047q = f;
                }
                if (e2 != null && !"".equals(e2)) {
                    aVar.f15038h = e2;
                }
                aVar.f15042l = i;
                int i2 = 1;
                if (!TbsShareManager.isThirdPartyApp(context)) {
                    if (!z) {
                        i2 = 0;
                    }
                    aVar.f15043m = i2;
                    if (z && z2) {
                        aVar.f15043m = 3;
                    }
                } else if (z) {
                    if (TbsShareManager.getCoreFormOwn()) {
                        aVar.f15043m = 2;
                    } else {
                        aVar.f15043m = 1;
                    }
                    if (z2) {
                        aVar.f15043m = 3;
                    }
                } else {
                    aVar.f15043m = 0;
                }
                aVar.f15045o = str4;
                aVar.f15046p = m16893a(context);
                if (!z) {
                    aVar.f15048r = j;
                    aVar.f15049s = QbSdk.getTbsVersion(context);
                }
                m16888a(aVar, context.getApplicationContext());
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else {
            TbsLog.m16531i("sdkreport", "[HttpUtils.doReport] -- SET_SENDREQUEST_AND_UPLOAD is false");
        }
    }

    /* renamed from: a */
    private static String m16893a(Context context) {
        try {
            byte[] byteArray = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray();
            if (byteArray != null) {
                MessageDigest instance = MessageDigest.getInstance("SHA-1");
                instance.update(byteArray);
                byte[] digest = instance.digest();
                if (digest != null) {
                    StringBuilder sb = new StringBuilder("");
                    if (digest != null && digest.length > 0) {
                        for (int i = 0; i < digest.length; i++) {
                            String upperCase = Integer.toHexString(digest[i] & 255).toUpperCase();
                            if (i > 0) {
                                sb.append(":");
                            }
                            if (upperCase.length() < 2) {
                                sb.append(0);
                            }
                            sb.append(upperCase);
                        }
                        return sb.toString();
                    }
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.lang.StringBuilder] */
    /* JADX WARN: Unknown variable types count: 1 */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m16886b(java.net.HttpURLConnection r5, java.lang.String r6, boolean r7) {
        /*
            Method dump skipped, instructions count: 211
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.p078a.HttpUtils.m16886b(java.net.HttpURLConnection, java.lang.String, boolean):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m16887b(Context context, String str) {
        try {
            TbsPVConfig.releaseInstance();
            TbsPVConfig.getInstance(context).clear();
            if (!TextUtils.isEmpty(str)) {
                for (String str2 : str.split("\\|")) {
                    try {
                        String[] split = str2.split(SimpleComparison.f23609c);
                        if (split.length == 2) {
                            m16891a(context, split[0], split[1]);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                TbsPVConfig.getInstance(context).commit();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* renamed from: a */
    private static void m16891a(Context context, String str, String str2) {
        if (!"reset".equals(str) || !"true".equals(str2)) {
            TbsPVConfig.getInstance(context).putData(str, str2);
        } else {
            QbSdk.reset(context);
        }
    }
}
