package p110z1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.WindowInsets;
import com.blankj.utilcode.util.LogUtils;
import com.lbd.xj.app.XJApp;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.stripe.android.PaymentResultListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* compiled from: Utils.java */
/* renamed from: z1.aes */
/* loaded from: classes3.dex */
public class aes {

    /* renamed from: a */
    public static final int f15467a = 8;

    /* renamed from: b */
    public static final int f15468b = 32;

    /* renamed from: c */
    public static final int f15469c = 0;

    /* renamed from: d */
    public static final int f15470d = 1;

    /* renamed from: e */
    public static final int f15471e = 2;

    /* renamed from: f */
    public static final int f15472f = 3;

    /* renamed from: g */
    public static final int f15473g = 4;

    /* renamed from: h */
    public static final int f15474h = 5;

    /* renamed from: i */
    public static final int f15475i = 6;

    /* renamed from: j */
    public static final int f15476j = 7;

    /* renamed from: k */
    public static final int f15477k = 8;

    /* renamed from: l */
    private static String f15478l = "SHENG Utils";

    /* compiled from: Utils.java */
    /* renamed from: z1.aes$a */
    /* loaded from: classes3.dex */
    public static class C3399a {
        @SuppressLint({"PrivateApi"})
        /* renamed from: a */
        public static String m13840a(String str) {
            try {
                Class<?> loadClass = XJApp.getInstance().getApplicationContext().getClassLoader().loadClass("android.os.SystemProperties");
                return loadClass.getMethod("get", String.class).invoke(loadClass.getInterfaces(), str).toString();
            } catch (ClassNotFoundException e) {
                LogUtils.m23720e(PaymentResultListener.f11903c, "get error() ", e);
                return "";
            } catch (IllegalAccessException e2) {
                LogUtils.m23720e(PaymentResultListener.f11903c, "get error() ", e2);
                return "";
            } catch (IllegalArgumentException e3) {
                LogUtils.m23720e(PaymentResultListener.f11903c, "get error() ", e3);
                return "";
            } catch (NoSuchMethodException e4) {
                LogUtils.m23720e(PaymentResultListener.f11903c, "get error() ", e4);
                return "";
            } catch (InvocationTargetException e5) {
                LogUtils.m23720e(PaymentResultListener.f11903c, "get error() ", e5);
                return "";
            }
        }
    }

    /* renamed from: a */
    public static void m13868a(InputStream inputStream, OutputStream outputStream) {
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr, 0, 1024);
                if (read == -1) {
                    inputStream.close();
                    outputStream.close();
                    return;
                }
                outputStream.write(bArr, 0, read);
            }
        } catch (Exception unused) {
        }
    }

    /* renamed from: a */
    public static int[] m13873a(Activity activity) {
        int[] iArr = {0, 0};
        try {
            Class<?> loadClass = activity.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
            return (int[]) loadClass.getMethod("getNotchSize", new Class[0]).invoke(loadClass, new Object[0]);
        } catch (ClassNotFoundException unused) {
            com.common.utils.log.LogUtils.m22036e(f15478l, "getNotchSize ClassNotFoundException");
            return iArr;
        } catch (NoSuchMethodException unused2) {
            com.common.utils.log.LogUtils.m22036e(f15478l, "getNotchSize NoSuchMethodException");
            return iArr;
        } catch (Exception unused3) {
            com.common.utils.log.LogUtils.m22036e(f15478l, "getNotchSize Exception");
            return iArr;
        } catch (Throwable unused4) {
            return iArr;
        }
    }

    /* renamed from: a */
    public static String m13869a(File file) {
        if (!file.isFile()) {
            return null;
        }
        byte[] bArr = new byte[1024];
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int read = fileInputStream.read(bArr, 0, 1024);
                if (read != -1) {
                    instance.update(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    return new BigInteger(1, instance.digest()).toString(16);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static int m13871a(Context context) {
        Resources resources = context.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(resources.getIdentifier("navigation_bar_height", "dimen", "android"));
        com.common.utils.log.LogUtils.m22032v("dbw", "Navi height:" + dimensionPixelSize);
        return dimensionPixelSize;
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x00d5, code lost:
        if (r0.equals("vivo") != false) goto L_0x010c;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int[] m13872a(android.app.Activity r10, int[] r11) {
        /*
            Method dump skipped, instructions count: 618
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.aes.m13872a(android.app.Activity, int[]):int[]");
    }

    /* renamed from: b */
    public static int m13860b(Activity activity) {
        char c;
        DisplayCutout displayCutout;
        if (Build.VERSION.SDK_INT >= 28) {
            WindowInsets rootWindowInsets = activity.getWindow().getDecorView().getRootWindowInsets();
            if (!(rootWindowInsets == null || (displayCutout = rootWindowInsets.getDisplayCutout()) == null)) {
                List<Rect> boundingRects = displayCutout.getBoundingRects();
                if (boundingRects.size() > 0) {
                    Rect rect = boundingRects.get(0);
                    return rect.bottom - rect.top;
                }
            }
            return 0;
        }
        String lowerCase = Build.BRAND.toLowerCase();
        com.common.utils.log.LogUtils.m22036e("brand.toLowerCase()", lowerCase);
        switch (lowerCase.hashCode()) {
            case -1320380160:
                if (lowerCase.equals("oneplus")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case -1206476313:
                if (lowerCase.equals("huawei")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -759499589:
                if (lowerCase.equals("xiaomi")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 50733:
                if (lowerCase.equals("360")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 3418016:
                if (lowerCase.equals("oppo")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 3620012:
                if (lowerCase.equals("vivo")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 99462250:
                if (lowerCase.equals("honor")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 103777484:
                if (lowerCase.equals("meizu")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1864941562:
                if (lowerCase.equals("samsung")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                if (m13841k(activity)) {
                    m13853c(activity);
                    String str = f15478l;
                    com.common.utils.log.LogUtils.m22038d(str, "samsung is " + Build.MODEL.toLowerCase());
                    break;
                } else {
                    return 0;
                }
            case 1:
            case 2:
                if (m13846f(activity)) {
                    m13873a(activity);
                    break;
                } else {
                    return 0;
                }
            case 3:
                if (m13867a("ro.miui.notch", activity) != 1) {
                    return 0;
                }
                int identifier = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
                com.common.utils.log.LogUtils.m22036e("brand.resourceId()", Integer.valueOf(identifier));
                com.common.utils.log.LogUtils.m22036e("brand.getDimensionPixelSize()", Integer.valueOf(activity.getResources().getDimensionPixelSize(identifier)));
                if (identifier <= 0) {
                    return 0;
                }
                break;
            case 4:
                if (m13844h(activity)) {
                    m13849d(activity);
                    String str2 = f15478l;
                    com.common.utils.log.LogUtils.m22038d(str2, "vivo is " + Build.MODEL.toLowerCase());
                    break;
                } else {
                    return 0;
                }
            case 5:
                m13845g(activity);
                break;
        }
        return 0;
    }

    /* renamed from: b */
    public static PackageInfo m13859b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    private static int m13858b(Context context, String str) {
        int identifier = context.getResources().getIdentifier(str, "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    /* renamed from: a */
    public static String m13874a() {
        Random random = new Random();
        int[] iArr = new int[3];
        int i = 0;
        while (i < 3) {
            i++;
            iArr[i] = random.nextInt(255);
        }
        return String.format("12:34:56:%02x:%02x:%02x", Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1]), Integer.valueOf(iArr[2]));
    }

    /* renamed from: b */
    public static String m13861b() {
        Random random = new Random();
        int[] iArr = new int[3];
        for (int i = 0; i < 3; i++) {
            iArr[i] = random.nextInt(65535);
        }
        return String.format("0%04x%04x%04x", Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1]), Integer.valueOf(iArr[2]));
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x004d, code lost:
        r0 = "/storage/" + r1[r2];
     */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m13854c() {
        /*
            java.lang.String r0 = ""
            java.io.File r1 = new java.io.File     // Catch: Exception -> 0x0061
            java.lang.String r2 = "storage"
            r1.<init>(r2)     // Catch: Exception -> 0x0061
            boolean r2 = r1.exists()     // Catch: Exception -> 0x0061
            if (r2 == 0) goto L_0x0065
            java.lang.String[] r1 = r1.list()     // Catch: Exception -> 0x0061
            if (r1 == 0) goto L_0x0065
            r2 = 0
        L_0x0016:
            int r3 = r1.length     // Catch: Exception -> 0x0061
            if (r2 >= r3) goto L_0x0065
            r3 = r1[r2]     // Catch: Exception -> 0x0061
            java.lang.String r4 = "-"
            int r3 = r3.indexOf(r4)     // Catch: Exception -> 0x0061
            if (r3 > 0) goto L_0x004d
            java.lang.String r3 = "emulated"
            r4 = r1[r2]     // Catch: Exception -> 0x0061
            boolean r3 = r3.equals(r4)     // Catch: Exception -> 0x0061
            if (r3 != 0) goto L_0x004a
            java.lang.String r3 = "self"
            r4 = r1[r2]     // Catch: Exception -> 0x0061
            boolean r3 = r3.equals(r4)     // Catch: Exception -> 0x0061
            if (r3 != 0) goto L_0x004a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: Exception -> 0x0061
            r3.<init>()     // Catch: Exception -> 0x0061
            java.lang.String r4 = "/storage/"
            r3.append(r4)     // Catch: Exception -> 0x0061
            r4 = r1[r2]     // Catch: Exception -> 0x0061
            r3.append(r4)     // Catch: Exception -> 0x0061
            java.lang.String r0 = r3.toString()     // Catch: Exception -> 0x0061
        L_0x004a:
            int r2 = r2 + 1
            goto L_0x0016
        L_0x004d:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: Exception -> 0x0061
            r3.<init>()     // Catch: Exception -> 0x0061
            java.lang.String r4 = "/storage/"
            r3.append(r4)     // Catch: Exception -> 0x0061
            r1 = r1[r2]     // Catch: Exception -> 0x0061
            r3.append(r1)     // Catch: Exception -> 0x0061
            java.lang.String r0 = r3.toString()     // Catch: Exception -> 0x0061
            goto L_0x0065
        L_0x0061:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0065:
            java.lang.String r1 = "SD卡路径"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "path is === "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            com.common.utils.log.LogUtils.m22036e(r1, r2)
            java.lang.String r1 = ""
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0085
            java.lang.String r0 = "无扩展卡"
        L_0x0085:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.aes.m13854c():java.lang.String");
    }

    /* renamed from: c */
    public static int m13853c(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int dimensionPixelSize = identifier > 0 ? context.getResources().getDimensionPixelSize(identifier) : 0;
        String str = f15478l;
        com.common.utils.log.LogUtils.m22038d(str, "statusBarHeight : " + dimensionPixelSize);
        return dimensionPixelSize;
    }

    /* renamed from: d */
    public static int m13849d(Context context) {
        return m13858b(context, "status_bar_height");
    }

    /* renamed from: e */
    public static String m13847e(Context context) {
        PackageInfo b = m13859b(context);
        return b != null ? b.versionName : "";
    }

    /* renamed from: a */
    public static int m13862a(int[] iArr, float[] fArr, int[] iArr2) {
        iArr2[0] = iArr[0];
        iArr2[1] = iArr[1];
        float f = iArr[0] / iArr[2];
        float f2 = iArr[1] / iArr[3];
        LogUtils.m23742b(f15478l, "getWH:nwidth/ntwidth(" + iArr[0] + "/" + iArr[2] + ")=" + f);
        LogUtils.m23742b(f15478l, "getWH:nhigh/nthigh(" + iArr[1] + "/" + iArr[3] + ")=" + f2);
        LogUtils.m23742b(f15478l, "x=" + f + "nthigh" + iArr[3] + "nhigh" + iArr[1]);
        float f3 = ((float) iArr[3]) * f;
        if (f3 > iArr[1]) {
            LogUtils.m23742b(f15478l, "放大后宽大于高");
            iArr2[0] = (int) Math.ceil(iArr[2] * f2);
            iArr2[1] = (int) Math.ceil(iArr[3] * f2);
            LogUtils.m23742b(f15478l, "222 out[1]=" + iArr2[1] + "xxx=" + f2);
        } else {
            LogUtils.m23742b(f15478l, "放大后宽小于等于高");
            iArr2[1] = (int) Math.ceil(f3);
            String str = f15478l;
            com.common.utils.log.LogUtils.m22038d(str, "222 out[1]=" + iArr2[1] + "xxx=" + f);
        }
        fArr[0] = iArr2[0] / iArr[2];
        fArr[1] = iArr2[1] / iArr[3];
        LogUtils.m23742b(f15478l, "222 scale[0]=" + fArr[0] + "scale[1]=" + fArr[1]);
        return 0;
    }

    /* renamed from: a */
    public static int m13867a(String str, Context context) {
        try {
            Class<?> loadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            return ((Integer) loadClass.getMethod("getInt", String.class, Integer.TYPE).invoke(loadClass, new String(str), new Integer(0))).intValue();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return 0;
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            return 0;
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            return 0;
        }
    }

    /* renamed from: f */
    public static boolean m13846f(Context context) {
        try {
            Class<?> loadClass = context.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
            return ((Boolean) loadClass.getMethod("hasNotchInScreen", new Class[0]).invoke(loadClass, new Object[0])).booleanValue();
        } catch (ClassNotFoundException unused) {
            com.common.utils.log.LogUtils.m22036e(f15478l, "hasNotchAtHuawei ClassNotFoundException");
            return false;
        } catch (NoSuchMethodException unused2) {
            com.common.utils.log.LogUtils.m22036e(f15478l, "hasNotchAtHuawei NoSuchMethodException");
            return false;
        } catch (Exception unused3) {
            com.common.utils.log.LogUtils.m22036e(f15478l, "hasNotchAtHuawei Exception");
            return false;
        }
    }

    /* renamed from: g */
    public static boolean m13845g(Context context) {
        return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    /* renamed from: h */
    public static boolean m13844h(Context context) {
        boolean z = false;
        try {
            Class<?> loadClass = context.getClassLoader().loadClass("android.util.FtFeature");
            z = ((Boolean) loadClass.getMethod("isFeatureSupport", Integer.TYPE).invoke(loadClass, 32)).booleanValue();
            String str = f15478l;
            com.common.utils.log.LogUtils.m22038d(str, "ret1 hasNotchAtVivo" + z);
            return z;
        } catch (ClassNotFoundException unused) {
            com.common.utils.log.LogUtils.m22036e(f15478l, "hasNotchAtVivo ClassNotFoundException");
            return z;
        } catch (NoSuchMethodException unused2) {
            com.common.utils.log.LogUtils.m22036e(f15478l, "hasNotchAtVivo NoSuchMethodException");
            return z;
        } catch (Exception unused3) {
            com.common.utils.log.LogUtils.m22036e(f15478l, "hasNotchAtVivo Exception");
            return z;
        }
    }

    /* renamed from: i */
    public static boolean m13843i(Context context) {
        return m13867a("ro.miui.notch", context) == 1 || m13846f(context) || m13845g(context) || m13844h(context);
    }

    /* renamed from: j */
    public static void m13842j(Context context) {
        double doubleValue = CheckPhoneInfoTools.m14199b(context).doubleValue();
        boolean a = CheckPhoneInfoTools.m14202a();
        com.common.utils.log.LogUtils.m22038d("isCheckPhone", "nMemSize" + doubleValue + "ck" + a);
        if (doubleValue < 1.5d) {
            com.common.utils.log.LogUtils.m22035i("手机内存太小，无法使用虚拟大师");
        } else if (a) {
            com.common.utils.log.LogUtils.m22035i("虚拟大师不支持在红手指上运行");
        }
    }

    /* renamed from: e */
    private static boolean m13848e() {
        try {
            return ((Boolean) Class.forName("flyme.config.FlymeFeature").getDeclaredField("IS_FRINGE_DEVICE").get(null)).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: k */
    public static boolean m13841k(Context context) {
        try {
            Resources resources = context.getResources();
            int identifier = resources.getIdentifier("config_mainBuiltInDisplayCutout", "string", "android");
            String string = identifier > 0 ? resources.getString(identifier) : null;
            if (string != null) {
                if (!TextUtils.isEmpty(string)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            Log.w("isSansung", "Can not update hasDisplayCutout. " + e);
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m13870a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        ArrayList arrayList = (ArrayList) ((ActivityManager) context.getSystemService(ServiceManagerNative.ACTIVITY)).getRunningServices(30);
        for (int i = 0; i < arrayList.size(); i++) {
            if (((ActivityManager.RunningServiceInfo) arrayList.get(i)).service.getClassName().toString().equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: d */
    public static boolean m13850d() {
        return Build.MANUFACTURER.toLowerCase().equals("xiaomi");
    }

    /* renamed from: a */
    public static int m13866a(String str, String str2) {
        String readLine;
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            do {
                readLine = bufferedReader.readLine();
                if (readLine == null) {
                    fileInputStream.close();
                    return 1;
                }
            } while (!readLine.contains(str2));
            fileInputStream.close();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    /* renamed from: b */
    public static boolean m13857b(String str, String str2) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            FileInputStream fileInputStream = new FileInputStream(str2);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read <= 0) {
                    fileOutputStream.flush();
                    fileInputStream.close();
                    fileOutputStream.close();
                    return true;
                }
                fileOutputStream.write(bArr, 0, read);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    public static String m13863a(String str, String str2, FileSizeCallback acwVar) {
        try {
            File file = new File(str + "-temp");
            com.common.utils.log.LogUtils.m22039d(file.getAbsolutePath());
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            FileInputStream fileInputStream = new FileInputStream(str2);
            byte[] bArr = new byte[1024];
            int available = fileInputStream.available();
            int i = 0;
            int i2 = 2048;
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                i += read;
                fileOutputStream.write(bArr, 0, read);
                i2--;
                if (i2 == 0) {
                    acwVar.mo14340a(i / (available / 100));
                    i2 = 2048;
                }
            }
            if (i == available) {
                Thread.sleep(500L);
                file.renameTo(new File(str));
                acwVar.mo14339a(str);
            }
            fileOutputStream.flush();
            fileInputStream.close();
            fileOutputStream.close();
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            acwVar.mo14341a();
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00ad A[Catch: Exception -> 0x00a9, TRY_LEAVE, TryCatch #4 {Exception -> 0x00a9, blocks: (B:45:0x00a5, B:49:0x00ad), top: B:54:0x00a5 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00a5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m13855b(java.lang.String r10, java.lang.String r11, p110z1.FileSizeCallback r12) {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch: all -> 0x0086, Exception -> 0x0089
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: all -> 0x0086, Exception -> 0x0089
            r2.<init>()     // Catch: all -> 0x0086, Exception -> 0x0089
            r2.append(r10)     // Catch: all -> 0x0086, Exception -> 0x0089
            java.lang.String r3 = "-temp"
            r2.append(r3)     // Catch: all -> 0x0086, Exception -> 0x0089
            java.lang.String r2 = r2.toString()     // Catch: all -> 0x0086, Exception -> 0x0089
            r1.<init>(r2)     // Catch: all -> 0x0086, Exception -> 0x0089
            java.io.RandomAccessFile r2 = new java.io.RandomAccessFile     // Catch: all -> 0x0086, Exception -> 0x0089
            java.lang.String r3 = "rwd"
            r2.<init>(r1, r3)     // Catch: all -> 0x0086, Exception -> 0x0089
            boolean r3 = r1.exists()     // Catch: all -> 0x0080, Exception -> 0x0082
            if (r3 == 0) goto L_0x002c
            long r3 = r1.length()     // Catch: all -> 0x0080, Exception -> 0x0082
            r2.seek(r3)     // Catch: all -> 0x0080, Exception -> 0x0082
            goto L_0x002e
        L_0x002c:
            r3 = 0
        L_0x002e:
            java.lang.String r5 = r1.getAbsolutePath()     // Catch: all -> 0x0080, Exception -> 0x0082
            com.common.utils.log.LogUtils.m22039d(r5)     // Catch: all -> 0x0080, Exception -> 0x0082
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: all -> 0x0080, Exception -> 0x0082
            r5.<init>(r11)     // Catch: all -> 0x0080, Exception -> 0x0082
            r11 = 2048(0x800, float:2.87E-42)
            byte[] r0 = new byte[r11]     // Catch: all -> 0x007c, Exception -> 0x007e
            int r6 = r5.available()     // Catch: all -> 0x007c, Exception -> 0x007e
            r7 = 2048(0x800, float:2.87E-42)
        L_0x0044:
            int r8 = r5.read(r0)     // Catch: all -> 0x007c, Exception -> 0x007e
            if (r8 > 0) goto L_0x0066
            long r6 = (long) r6     // Catch: all -> 0x007c, Exception -> 0x007e
            int r11 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r11 != 0) goto L_0x005f
            r3 = 500(0x1f4, double:2.47E-321)
            java.lang.Thread.sleep(r3)     // Catch: all -> 0x007c, Exception -> 0x007e
            java.io.File r11 = new java.io.File     // Catch: all -> 0x007c, Exception -> 0x007e
            r11.<init>(r10)     // Catch: all -> 0x007c, Exception -> 0x007e
            r1.renameTo(r11)     // Catch: all -> 0x007c, Exception -> 0x007e
            r12.mo14339a(r10)     // Catch: all -> 0x007c, Exception -> 0x007e
        L_0x005f:
            r2.close()     // Catch: Exception -> 0x0094
            r5.close()     // Catch: Exception -> 0x0094
            goto L_0x009f
        L_0x0066:
            r9 = 0
            r2.write(r0, r9, r8)     // Catch: all -> 0x007c, Exception -> 0x007e
            long r8 = (long) r8     // Catch: all -> 0x007c, Exception -> 0x007e
            long r3 = r3 + r8
            int r7 = r7 + (-1)
            if (r7 != 0) goto L_0x0044
            int r7 = r6 / 100
            long r7 = (long) r7     // Catch: all -> 0x007c, Exception -> 0x007e
            long r7 = r3 / r7
            int r7 = (int) r7     // Catch: all -> 0x007c, Exception -> 0x007e
            r12.mo14340a(r7)     // Catch: all -> 0x007c, Exception -> 0x007e
            r7 = 2048(0x800, float:2.87E-42)
            goto L_0x0044
        L_0x007c:
            r10 = move-exception
            goto L_0x00a2
        L_0x007e:
            r11 = move-exception
            goto L_0x0084
        L_0x0080:
            r10 = move-exception
            goto L_0x00a3
        L_0x0082:
            r11 = move-exception
            r5 = r0
        L_0x0084:
            r0 = r2
            goto L_0x008b
        L_0x0086:
            r10 = move-exception
            r2 = r0
            goto L_0x00a3
        L_0x0089:
            r11 = move-exception
            r5 = r0
        L_0x008b:
            r11.printStackTrace()     // Catch: all -> 0x00a0
            if (r0 == 0) goto L_0x0096
            r0.close()     // Catch: Exception -> 0x0094
            goto L_0x0096
        L_0x0094:
            r11 = move-exception
            goto L_0x009c
        L_0x0096:
            if (r5 == 0) goto L_0x009f
            r5.close()     // Catch: Exception -> 0x0094
            goto L_0x009f
        L_0x009c:
            r11.printStackTrace()
        L_0x009f:
            return r10
        L_0x00a0:
            r10 = move-exception
            r2 = r0
        L_0x00a2:
            r0 = r5
        L_0x00a3:
            if (r2 == 0) goto L_0x00ab
            r2.close()     // Catch: Exception -> 0x00a9
            goto L_0x00ab
        L_0x00a9:
            r11 = move-exception
            goto L_0x00b1
        L_0x00ab:
            if (r0 == 0) goto L_0x00b4
            r0.close()     // Catch: Exception -> 0x00a9
            goto L_0x00b4
        L_0x00b1:
            r11.printStackTrace()
        L_0x00b4:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.aes.m13855b(java.lang.String, java.lang.String, z1.acw):java.lang.String");
    }

    /* renamed from: a */
    public static boolean m13865a(String str, String str2, ApplicationInfo applicationInfo) {
        File file = new File(applicationInfo.dataDir, "osimg");
        StringBuilder sb = new StringBuilder();
        sb.append(file);
        sb.append("/r/ot01/sdcard/xjapp/");
        aef.m14124e(sb.toString());
        sb.append(str);
        sb.append(".apk");
        return m13857b(sb.toString(), str2);
    }

    /* renamed from: b */
    public static boolean m13856b(String str, String str2, ApplicationInfo applicationInfo) {
        File file = new File(applicationInfo.dataDir, "osimg");
        return m13857b(file + "/r/ot01/sdcard/Download/" + str + ".apk", str2);
    }

    /* renamed from: a */
    public static String m13864a(String str, String str2, ApplicationInfo applicationInfo, FileSizeCallback acwVar) {
        File file = new File(applicationInfo.dataDir, "osimg");
        StringBuilder sb = new StringBuilder();
        sb.append(file);
        sb.append("/r/ot01/storage/sdcard/虚拟空间文件中转站/");
        sb.append(str);
        if (sb.indexOf(".apk") == -1) {
            sb.append(".apk");
        }
        return m13863a(sb.toString(), str2, acwVar);
    }

    /* renamed from: c */
    public static boolean m13851c(String str, String str2, ApplicationInfo applicationInfo) {
        File file = new File(applicationInfo.dataDir, "osimg");
        return m13857b(file + "/r/ot01/虚拟空间文件中转站/" + str + ".apk", str2);
    }

    /* renamed from: c */
    public static void m13852c(String str, String str2) {
        new File(str).renameTo(new File(str2));
    }
}
