package com.lbd.xj.manager;

import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import com.lbd.xj.device.MobileInfoManagerUtil;
import com.lbd.xj.device.location.LocationManagerUtil;
import com.lbd.xj.device.sersor.SensorManagerUtil;
import com.lbd.xj.device.wifi.WifiManagerUtil;

/* renamed from: com.lbd.xj.manager.FullJniUtil */
/* loaded from: classes.dex */
public class FullJniUtil {

    /* renamed from: a */
    static Vibrator f9467a;

    /* renamed from: b */
    private static Handler f9468b;

    public static native void CameraOnStop(String str);

    public static native void CameraPicture(String str, byte[] bArr);

    public static native void CameraPreview(String str, byte[] bArr);

    /* renamed from: a */
    static int m19756a(String str) {
        return 1;
    }

    /* renamed from: a */
    static int m19755a(String str, float f, float f2, float f3, float f4, int i, String str2) {
        return 1;
    }

    /* renamed from: a */
    static int m19754a(String str, int i, int i2, int i3) {
        return 0;
    }

    /* renamed from: a */
    static int m19753a(String str, String str2) {
        return 1;
    }

    /* renamed from: a */
    static int m19752a(String str, String str2, int i, int i2, int i3, int i4, int i5) {
        return 1;
    }

    /* renamed from: b */
    static int m19748b(String str) {
        return 1;
    }

    /* renamed from: b */
    static int m19747b(String str, int i, int i2, int i3) {
        return 1;
    }

    /* renamed from: b */
    public static String m19751b() {
        return "";
    }

    /* renamed from: c */
    static int m19746c() {
        return -1;
    }

    /* renamed from: c */
    static int m19743c(String str) {
        return 1;
    }

    /* renamed from: d */
    public static void m19742d() {
    }

    public static native int getbootstats(String str);

    public native void GpsChanged(int i, int i2, double d, double d2, double d3, double d4, double d5, double d6, long j);

    public native void GpsnmeaChanged(long j, String str);

    public native void MyWIFIChanged(String str);

    public native void SensorChanged(int i, float f, float f2, float f3);

    public native void dlnaSendConfig(byte[] bArr, int i);

    public native void killRom();

    public native int signaluid(int i, int i2);

    public native int start_pipe(String str);

    public native int test_linker();

    /* renamed from: com.lbd.xj.manager.FullJniUtil$a */
    /* loaded from: classes.dex */
    private static class C1498a {

        /* renamed from: a */
        public static final FullJniUtil f9469a = new FullJniUtil();

        private C1498a() {
        }
    }

    /* renamed from: a */
    public static FullJniUtil m19759a() {
        return C1498a.f9469a;
    }

    static {
        System.loadLibrary("rfbox");
        System.loadLibrary("native-lib");
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    /* renamed from: a */
    static void m19757a(int i, int i2) {
        WifiManagerUtil.getInstance().SetWifiStart(i, i2);
    }

    /* renamed from: b */
    static void m19749b(int i, int i2) {
        LocationManagerUtil.getInstance().SetGpsStart(i, i2);
    }

    /* renamed from: c */
    static void m19744c(int i, int i2) {
        LocationManagerUtil.getInstance().SetGpsStop(i, i2);
    }

    /* renamed from: d */
    static void m19741d(int i, int i2) {
        LocationManagerUtil.getInstance().SetGpsnmeaStart(i, i2);
    }

    /* renamed from: e */
    static void m19739e(int i, int i2) {
        LocationManagerUtil.getInstance().SetGpsnmeaStop(i, i2);
    }

    /* renamed from: a */
    public static void m19758a(int i) {
        SensorManagerUtil.getInstance().EnableSensors(i);
    }

    /* renamed from: b */
    public static void m19750b(int i) {
        SensorManagerUtil.getInstance().DisableSensors(i);
    }

    /* renamed from: f */
    public static void m19737f(int i, int i2) {
        SensorManagerUtil.getInstance().SetDelay(i, i2);
    }

    /* renamed from: c */
    public static boolean m19745c(int i) {
        return SensorManagerUtil.getInstance().CheckSensorsSupport(i);
    }

    /* renamed from: g */
    static String m19736g(int i, int i2) {
        return MobileInfoManagerUtil.getInstance().getSingle(1);
    }

    /* renamed from: h */
    static String m19735h(int i, int i2) {
        return MobileInfoManagerUtil.getInstance().getSingle(2);
    }

    /* renamed from: i */
    static String m19734i(int i, int i2) {
        return MobileInfoManagerUtil.getInstance().getSingle(3);
    }

    /* renamed from: j */
    static String m19733j(int i, int i2) {
        return MobileInfoManagerUtil.getInstance().getSingle(4);
    }

    /* renamed from: k */
    static String m19732k(int i, int i2) {
        return MobileInfoManagerUtil.getInstance().getSingle(5);
    }

    /* renamed from: l */
    static String m19731l(int i, int i2) {
        return MobileInfoManagerUtil.getInstance().getSingle(6);
    }

    /* renamed from: m */
    static String m19730m(int i, int i2) {
        return MobileInfoManagerUtil.getInstance().getSingle(7);
    }

    /* renamed from: n */
    static String m19729n(int i, int i2) {
        return MobileInfoManagerUtil.getInstance().getSingle(8);
    }

    /* renamed from: o */
    static String m19728o(int i, int i2) {
        return MobileInfoManagerUtil.getInstance().getSingle(9);
    }

    /* renamed from: p */
    static String m19727p(int i, int i2) {
        return MobileInfoManagerUtil.getInstance().getSingle(10);
    }

    /* renamed from: q */
    static String m19726q(int i, int i2) {
        return MobileInfoManagerUtil.getInstance().getSingle(11);
    }

    /* renamed from: r */
    static String m19725r(int i, int i2) {
        return MobileInfoManagerUtil.getInstance().getSingle(12);
    }

    /* renamed from: s */
    static String m19724s(int i, int i2) {
        return MobileInfoManagerUtil.getInstance().Ref_GetPhoneNumber(i, i2);
    }

    /* renamed from: t */
    static String m19723t(int i, int i2) {
        return MobileInfoManagerUtil.getInstance().Ref_GetNetworkOperatorNumber(i, i2);
    }

    /* renamed from: u */
    static String m19722u(int i, int i2) {
        return MobileInfoManagerUtil.getInstance().Ref_GetIccid(i, i2);
    }

    /* renamed from: v */
    static String m19721v(int i, int i2) {
        return MobileInfoManagerUtil.getInstance().Ref_Getimsi(i, i2);
    }

    /* renamed from: w */
    static String m19720w(int i, int i2) {
        return MobileInfoManagerUtil.getInstance().Ref_Getimei(i, i2);
    }

    /* renamed from: x */
    static String m19719x(int i, int i2) {
        return MobileInfoManagerUtil.getInstance().Ref_GetNetworkType(i, i2);
    }

    /* renamed from: y */
    static String m19718y(int i, int i2) {
        return MobileInfoManagerUtil.getInstance().Ref_GetCellInfo(i, i2);
    }

    /* renamed from: z */
    static String m19717z(int i, int i2) {
        return MobileInfoManagerUtil.getInstance().Ref_GetLac(i, i2);
    }

    /* renamed from: A */
    static String m19760A(int i, int i2) {
        return MobileInfoManagerUtil.getInstance().Ref_GetCid(i, i2);
    }

    /* renamed from: d */
    static String m19740d(String str) {
        return MobileInfoManagerUtil.getInstance().Ref_MyCallPhone(str);
    }

    /* renamed from: e */
    static String m19738e(String str) {
        if (str == null) {
            return "null";
        }
        String[] split = str.split(":");
        if (split[1] == null || f9468b == null) {
            Log.e("yuelogVibrator", "Ref_MyVibrator parts[1]==null && mHandleVibrator==null");
            return "null";
        }
        Message message = new Message();
        message.obj = split[1];
        Handler handler = f9468b;
        if (handler != null) {
            handler.sendMessage(message);
            return "null";
        }
        Log.e("yuelogVibrator", "Ref_MyVibrator is null");
        return "null";
    }
}
