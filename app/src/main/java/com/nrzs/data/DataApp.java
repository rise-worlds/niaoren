package com.nrzs.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.LogUtils;
import com.nrzs.base.router.RouterConstants;
import com.nrzs.data.router.ChannelProvider;
import com.stripe.android.view.ShippingInfoWidget;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Method;
import p110z1.ARouter;
import p110z1.CommonUtil;
import p110z1.EventConstants;
import p110z1.NRZSFileConfig;
import p110z1.ShareVal;
import p110z1.apf;

/* renamed from: com.nrzs.data.b */
/* loaded from: classes2.dex */
public class DataApp {

    /* renamed from: i */
    static final /* synthetic */ boolean f10597i = !DataApp.class.desiredAssertionStatus();

    /* renamed from: a */
    public String f10598a;

    /* renamed from: b */
    public String f10599b;

    /* renamed from: c */
    public long f10600c;

    /* renamed from: d */
    public String f10601d;

    /* renamed from: e */
    public String f10602e;

    /* renamed from: f */
    public String f10603f;

    /* renamed from: g */
    public int f10604g;

    /* renamed from: h */
    public long f10605h;

    /* renamed from: j */
    private Context f10606j;

    /* renamed from: k */
    private String f10607k;

    /* renamed from: l */
    private String f10608l;

    /* renamed from: a */
    public void m18946a(Context context) {
        this.f10606j = context;
        String packageName = context.getPackageName();
        this.f10603f = packageName;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            this.f10600c = packageInfo == null ? -1L : packageInfo.versionCode;
            this.f10599b = packageInfo == null ? "default" : packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        m18944b();
        this.f10602e = "49C46856B70328720EA293A8B343A50DD1290017";
        this.f10598a = "87a058253a6c4d37b010a865e0dc33b0";
        m18945a(this.f10607k);
    }

    /* renamed from: a */
    public Context m18947a() {
        return this.f10606j;
    }

    private DataApp() {
    }

    /* renamed from: b */
    public String m18944b() {
        if (TextUtils.isEmpty(this.f10607k)) {
            this.f10607k = ((ChannelProvider) ARouter.m1714a().m1707a(RouterConstants.Provider.PROVIDER_GETCHANNEL).navigation()).mo3077a();
            if (TextUtils.isEmpty(this.f10607k)) {
                this.f10607k = EventConstants.f16434b;
            }
        }
        LogUtils.m23734c("getChannel", "channel:" + this.f10607k);
        String str = this.f10607k;
        this.f10601d = str;
        return str;
    }

    /* renamed from: a */
    public void m18945a(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                String[] split = str.split("_");
                if (split.length >= 2) {
                    this.f10604g = m18942b(split[0]);
                    this.f10605h = Long.parseLong(split[1]);
                } else {
                    this.f10604g = 0;
                    this.f10605h = 0L;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintStream printStream = System.out;
        printStream.println("initAppChannel appChannelType:" + this.f10604g + "  channelId:" + this.f10605h);
    }

    /* renamed from: b */
    public void m18943b(Context context) {
        ApplicationInfo applicationInfo;
        try {
            String string = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString("APP_CHANNEL");
            if (TextUtils.isEmpty(string)) {
                string = applicationInfo.metaData.getInt("APP_CHANNEL") + "";
            }
            if (!TextUtils.isEmpty(string)) {
                String[] split = string.split("_");
                if (split.length >= 2) {
                    this.f10604g = m18942b(split[0]);
                    this.f10605h = Long.parseLong(split[1]);
                }
            } else {
                String b = FileIOUtils.m22278b(new File(NRZSFileConfig.f16546d, "test"));
                if (!TextUtils.isEmpty(b)) {
                    String[] split2 = b.split("_");
                    if (split2.length >= 2) {
                        this.f10604g = m18942b(split2[0]);
                        this.f10605h = Long.parseLong(split2[1]);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("initAppChannel appChannelType:" + this.f10604g + "  channelId:" + this.f10605h);
    }

    /* renamed from: b */
    private int m18942b(String str) {
        if ("Z".equals(str)) {
            return 1;
        }
        return "D".equals(str) ? 2 : 0;
    }

    /* renamed from: c */
    public String m18941c() {
        return m18940c(this.f10606j);
    }

    @SuppressLint({"MissingPermission"})
    /* renamed from: c */
    public String m18940c(Context context) {
        if (CommonUtil.m11892c(context)) {
            LogUtils.m23734c("getIMEI", "getIMEI1:1111" + this.f10608l);
            if (!TextUtils.isEmpty(this.f10608l)) {
                LogUtils.m23734c("getIMEI", "getIMEI2:" + this.f10608l);
                return this.f10608l;
            }
            if (TextUtils.isEmpty(this.f10608l)) {
                this.f10608l = m18937e(context);
                if (TextUtils.isEmpty(this.f10608l)) {
                    this.f10608l = m18938d(context);
                    if (TextUtils.isEmpty(this.f10608l)) {
                        this.f10608l = m18936f(context);
                    }
                }
            }
            LogUtils.m23734c("getIMEI", "getIMEI3:" + this.f10608l);
            return this.f10608l;
        }
        LogUtils.m23734c("getIMEI", "getIMEI1:2222" + this.f10608l);
        if (!TextUtils.isEmpty(this.f10608l)) {
            LogUtils.m23734c("getIMEI", "getIMEI2:" + this.f10608l);
            return this.f10608l;
        }
        this.f10608l = apf.m11837b(m18939d().m18947a(), ShareVal.f16608r, ShareVal.f16576G, "");
        if (TextUtils.isEmpty(this.f10608l)) {
            this.f10608l = m18937e(context);
            if (TextUtils.isEmpty(this.f10608l)) {
                this.f10608l = m18938d(context);
                if (TextUtils.isEmpty(this.f10608l)) {
                    this.f10608l = m18936f(context);
                }
            }
            apf.m11842a(m18939d().m18947a(), ShareVal.f16608r, ShareVal.f16576G, this.f10608l);
        }
        LogUtils.m23734c("getIMEI", "getIMEI3:" + this.f10608l);
        return this.f10608l;
    }

    /* renamed from: e */
    private static String m18937e(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f);
        if (Build.VERSION.SDK_INT >= 26) {
            return context.checkSelfPermission("android.permission.READ_PHONE_STATE") != 0 ? "" : telephonyManager.getImei();
        }
        try {
            Method declaredMethod = telephonyManager.getClass().getDeclaredMethod("getImei", new Class[0]);
            declaredMethod.setAccessible(true);
            String str = (String) declaredMethod.invoke(telephonyManager, new Object[0]);
            return !TextUtils.isEmpty(str) ? str : "";
        } catch (Exception e) {
            Log.e("PhoneUtils", "getIMEI: ", e);
            return "";
        }
    }

    /* renamed from: f */
    private static String m18936f(Context context) {
        String f = DeviceUtils.m22408f();
        LogUtils.m23734c("getIMEI", "getIMEI10:" + f);
        if (!"02:00:00:00:00:00".equals(f)) {
            return f;
        }
        LogUtils.m23734c("getIMEI", "getIMEI11:" + f);
        return "";
    }

    /* renamed from: d */
    public static String m18938d(Context context) {
        String str;
        if (Build.VERSION.SDK_INT >= 29) {
            str = Settings.Secure.getString(context.getContentResolver(), "android_id");
        } else {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f);
            if (Build.VERSION.SDK_INT >= 23 && context.checkSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
                return "";
            }
            if (!f10597i && telephonyManager == null) {
                throw new AssertionError();
            } else if (telephonyManager.getDeviceId() == null) {
                str = Settings.Secure.getString(context.getContentResolver(), "android_id");
            } else if (Build.VERSION.SDK_INT >= 26) {
                str = telephonyManager.getImei();
            } else {
                str = telephonyManager.getDeviceId();
            }
        }
        Log.d("deviceId", str);
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DataApp.java */
    /* renamed from: com.nrzs.data.b$a */
    /* loaded from: classes2.dex */
    public static class C1965a {

        /* renamed from: a */
        private static final DataApp f10609a = new DataApp();

        private C1965a() {
        }
    }

    /* renamed from: d */
    public static DataApp m18939d() {
        return C1965a.f10609a;
    }
}
