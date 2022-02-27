package com.cyjh.ddy.base.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.SensorManager;
import android.media.MediaDrm;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.opengl.GLES20;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.Utils;
import com.stripe.android.view.ShippingInfoWidget;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import org.apache.http.cookie.ClientCookie;
import org.slf4j.Marker;
import p110z1.ann;

/* renamed from: com.cyjh.ddy.base.utils.h */
/* loaded from: classes.dex */
public class InformationUtils {

    /* renamed from: a */
    public static final String f7127a = "InformationUtils";

    /* renamed from: b */
    private static final String f7128b = "app.setupwizard.disable,dalvik.vm.appimageformat,dalvik.vm.checkjni,dalvik.vm.dex2oat-Xms,dalvik.vm.dex2oat-Xmx,dalvik.vm.dex2oat-filter,dalvik.vm.execution-mode,dalvik.vm.heapgrowthlimit,dalvik.vm.heapmaxfree,dalvik.vm.heapminfree,dalvik.vm.heapsize,dalvik.vm.heapstartsize,dalvik.vm.heaptargetutilization,dalvik.vm.image-dex2oat-Xms,dalvik.vm.image-dex2oat-Xmx,dalvik.vm.image-dex2oat-filter,dalvik.vm.isa.x86.features,dalvik.vm.isa.x86.variant,dalvik.vm.lockprof.threshold,dalvik.vm.stack-trace-file,dalvik.vm.usejit,dalvik.vm.usejitprofiles,debug.atrace.tags.enableflags,debug.egl.hw,debug.force_rtl,debug.sf.nobootanimation,dev.bootcomplete,gsm.current.phone-type,gsm.network.type,gsm.operator.alpha,gsm.operator.iso-country,gsm.operator.isroaming,gsm.operator.numeric,gsm.sim.bstserial,gsm.sim.operator.alpha,gsm.sim.operator.iso-country,gsm.sim.operator.numeric,gsm.sim.state,gsm.version.ril-impl,hal.sensors,init.svc.adbd,init.svc.appstatsd,init.svc.audioserver,init.svc.bindmount,init.svc.bootanim,init.svc.bstfolderd,init.svc.bstsvcmgrtest,init.svc.cameraserver,init.svc.console,init.svc.debuggerd,init.svc.drm,init.svc.enable_arm_bin,init.svc.gatekeeperd,init.svc.healthd,init.svc.imeservice,init.svc.installd,init.svc.keystore,init.svc.lmkd,init.svc.logd,init.svc.logd-reinit,init.svc.media,init.svc.mediacodec,init.svc.mediadrm,init.svc.mediaextractor,init.svc.netd,init.svc.postupgrade,init.svc.servicemanager,init.svc.surfaceflinger,init.svc.ueventd,init.svc.vold,init.svc.zygote,keyguard.no_require_sim,logd.logpersistd.enable,net.bt.name,net.change,net.dns1,net.dns2,net.hostname,net.qtaguid_enabled,net.tcp.default_init_rwnd,persist.iwangding.uuid,persist.rtc_local_time,persist.sys.dalvik.vm.lib.2,persist.sys.gps.lpp,persist.sys.locale,persist.sys.nativebridge,persist.sys.pcode,persist.sys.profiler_ms,persist.sys.strictmode.disable,persist.sys.strictmode.visual,persist.sys.timezone,persist.sys.usb.config,persist.sys.webview.vmsize,pm.dexopt.ab-ota,pm.dexopt.bg-dexopt,pm.dexopt.boot,pm.dexopt.core-app,pm.dexopt.first-boot,pm.dexopt.forced-dexopt,pm.dexopt.install,pm.dexopt.nsys-library,pm.dexopt.shared-apk,ro.alarm.volume.adjustable,ro.allow.mock.location,ro.arch,ro.baseband,ro.board.platform,ro.boot.serialno,ro.bootimage.build.date,ro.bootimage.build.date.utc,ro.bootimage.build.fingerprint,ro.bootloader,ro.bootmode,ro.build.characteristics,ro.build.date,ro.build.date.utc,ro.build.description,ro.build.display.id,ro.build.fingerprint,ro.build.flavor,ro.build.host,ro.build.id,ro.build.product,ro.build.tags,ro.build.type,ro.build.user,ro.build.version.all_codenames,ro.build.version.base_os,ro.build.version.codename,ro.build.version.incremental,ro.build.version.preview_sdk,ro.build.version.release,ro.build.version.sdk,ro.build.version.security_patch,ro.carrier,ro.com.android.dataroaming,ro.com.android.dateformat,ro.com.google.clientidbase,ro.com.google.gmsversion,ro.com.google.locationfeatures,ro.com.google.networklocation,ro.config.alarm_alert,ro.config.notification_sound,ro.config.sync,ro.crypto.state,ro.dalvik.vm.isa.arm,ro.dalvik.vm.native.bridge,ro.debuggable,ro.device_owner,ro.enable.native.bridge.exec,ro.hardware,ro.kernel.android.checkjni,ro.opengles.version,ro.product.board,ro.product.brand,ro.product.cpu.abi,ro.product.cpu.abilist,ro.product.cpu.abilist32,ro.product.cpu.abilist64,ro.product.device,ro.product.locale,ro.product.manufacturer,ro.product.model,ro.product.name,ro.radio.use-ppp,ro.revision,ro.runtime.firstboot,ro.secure,ro.serialno,ro.setupwizard.suppress_d2d,ro.sf.lcd_density,ro.simulated.phone,ro.wifi.channels,ro.zygote,selinux.reload_policy,service.bootanim.exit,status.battery.level,status.battery.level_raw,status.battery.level_scale,status.battery.state,sys.boot_completed,sys.media.vdec.drop,sys.sysctl.extra_free_kbytes,sys.sysctl.tcp_def_init_rwnd,sys.usb.config,sys.usb.configfs,vold.has_adoptable,vold.post_fs_data_done";

    /* renamed from: c */
    private static final char[] f7129c = "0123456789ABCDEF".toCharArray();

    /* renamed from: a */
    public static String m21821a() {
        int i;
        EGLConfig eGLConfig;
        EGLContext eglCreateContext;
        EGLSurface eglCreatePbufferSurface;
        EGL10 egl10 = (EGL10) EGLContext.getEGL();
        EGLDisplay eglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        if (eglGetDisplay == EGL10.EGL_NO_DISPLAY || !egl10.eglInitialize(eglGetDisplay, new int[2])) {
            return null;
        }
        int[] iArr = new int[1];
        int[] iArr2 = {12352, 4, 12344};
        if (!egl10.eglChooseConfig(eglGetDisplay, iArr2, null, 0, iArr) || (i = iArr[0]) == 0) {
            return null;
        }
        EGLConfig[] eGLConfigArr = new EGLConfig[i];
        if (!egl10.eglChooseConfig(eglGetDisplay, iArr2, eGLConfigArr, i, iArr) || (eglCreateContext = egl10.eglCreateContext(eglGetDisplay, (eGLConfig = eGLConfigArr[0]), EGL10.EGL_NO_CONTEXT, new int[]{12440, 2, 12344})) == EGL10.EGL_NO_CONTEXT || (eglCreatePbufferSurface = egl10.eglCreatePbufferSurface(eglGetDisplay, eGLConfig, new int[]{12375, 1, 12374, 1, 12344})) == EGL10.EGL_NO_SURFACE || !egl10.eglMakeCurrent(eglGetDisplay, eglCreatePbufferSurface, eglCreatePbufferSurface, eglCreateContext) || egl10.eglGetError() != 12288) {
            return null;
        }
        String glGetString = GLES20.glGetString(7937);
        if (!glGetString.contains("Bluestacks")) {
            glGetString.contains("Translator");
        }
        String str = "\nVENDOR " + GLES20.glGetString(7936) + ":\nRENDERER " + glGetString + ":\nVERSION " + GLES20.glGetString(7938) + ":\nGL_SHADING_LANGUAGE_VERSION " + GLES20.glGetString(35724) + ":\nEXTENSIONS " + GLES20.glGetString(7939);
        if (GLES20.glGetError() != 0) {
            return null;
        }
        egl10.eglMakeCurrent(eglGetDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
        egl10.eglDestroySurface(eglGetDisplay, eglCreatePbufferSurface);
        egl10.eglDestroyContext(eglGetDisplay, eglCreateContext);
        return glGetString;
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x006a: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:20:0x006a */
    /* JADX WARN: Removed duplicated region for block: B:28:0x006d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m21819a(java.lang.String r6) {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch: all -> 0x0040, IOException -> 0x0042
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: all -> 0x0040, IOException -> 0x0042
            r2.<init>()     // Catch: all -> 0x0040, IOException -> 0x0042
            java.lang.String r3 = "getprop "
            r2.append(r3)     // Catch: all -> 0x0040, IOException -> 0x0042
            r2.append(r6)     // Catch: all -> 0x0040, IOException -> 0x0042
            java.lang.String r2 = r2.toString()     // Catch: all -> 0x0040, IOException -> 0x0042
            java.lang.Process r1 = r1.exec(r2)     // Catch: all -> 0x0040, IOException -> 0x0042
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: all -> 0x0040, IOException -> 0x0042
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: all -> 0x0040, IOException -> 0x0042
            java.io.InputStream r1 = r1.getInputStream()     // Catch: all -> 0x0040, IOException -> 0x0042
            r3.<init>(r1)     // Catch: all -> 0x0040, IOException -> 0x0042
            r1 = 1024(0x400, float:1.435E-42)
            r2.<init>(r3, r1)     // Catch: all -> 0x0040, IOException -> 0x0042
            java.lang.String r1 = r2.readLine()     // Catch: IOException -> 0x003e, all -> 0x0069
            r2.close()     // Catch: IOException -> 0x003e, all -> 0x0069
            r2.close()     // Catch: IOException -> 0x0035
            goto L_0x003d
        L_0x0035:
            r6 = move-exception
            java.lang.String r0 = "InformationUtils"
            java.lang.String r2 = "Exception while closing InputStream"
            android.util.Log.e(r0, r2, r6)
        L_0x003d:
            return r1
        L_0x003e:
            r1 = move-exception
            goto L_0x0044
        L_0x0040:
            r6 = move-exception
            goto L_0x006b
        L_0x0042:
            r1 = move-exception
            r2 = r0
        L_0x0044:
            java.lang.String r3 = "InformationUtils"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: all -> 0x0069
            r4.<init>()     // Catch: all -> 0x0069
            java.lang.String r5 = "Unable to read sysprop "
            r4.append(r5)     // Catch: all -> 0x0069
            r4.append(r6)     // Catch: all -> 0x0069
            java.lang.String r6 = r4.toString()     // Catch: all -> 0x0069
            android.util.Log.e(r3, r6, r1)     // Catch: all -> 0x0069
            if (r2 == 0) goto L_0x0068
            r2.close()     // Catch: IOException -> 0x0060
            goto L_0x0068
        L_0x0060:
            r6 = move-exception
            java.lang.String r1 = "InformationUtils"
            java.lang.String r2 = "Exception while closing InputStream"
            android.util.Log.e(r1, r2, r6)
        L_0x0068:
            return r0
        L_0x0069:
            r6 = move-exception
            r0 = r2
        L_0x006b:
            if (r0 == 0) goto L_0x0079
            r0.close()     // Catch: IOException -> 0x0071
            goto L_0x0079
        L_0x0071:
            r0 = move-exception
            java.lang.String r1 = "InformationUtils"
            java.lang.String r2 = "Exception while closing InputStream"
            android.util.Log.e(r1, r2, r0)
        L_0x0079:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cyjh.ddy.base.utils.InformationUtils.m21819a(java.lang.String):java.lang.String");
    }

    /* renamed from: b */
    public static Map<String, String> m21817b() {
        HashMap hashMap = new HashMap();
        String[] split = f7128b.split(",");
        for (int i = 0; i < split.length; i++) {
            CLog.m21882i(f7127a, split[i] + "|||" + m21819a(split[i]));
            if (!StringUtils.m23232a((CharSequence) m21819a(split[i]))) {
                hashMap.put(split[i], m21819a(split[i]));
            }
        }
        return hashMap;
    }

    @SuppressLint({"MissingPermission"})
    /* renamed from: a */
    public static String m21820a(Context context) {
        String str = "";
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f);
            if (Build.VERSION.SDK_INT < 21) {
                str = telephonyManager.getSimSerialNumber();
            } else {
                str = (String) telephonyManager.getClass().getMethod("getSimSerialNumber", new Class[0]).invoke(telephonyManager, new Object[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /* renamed from: c */
    public static String m21815c() {
        String a = m21819a("ro.product.brand");
        if (TextUtils.isEmpty(a)) {
            return "";
        }
        char c = 65535;
        switch (a.hashCode()) {
            case -1675632421:
                if (a.equals("Xiaomi")) {
                    c = 2;
                    break;
                }
                break;
            case -759499589:
                if (a.equals("xiaomi")) {
                    c = 3;
                    break;
                }
                break;
            case 2432928:
                if (a.equals(ann.f16790d)) {
                    c = 4;
                    break;
                }
                break;
            case 3620012:
                if (a.equals("vivo")) {
                    c = 5;
                    break;
                }
                break;
            case 68924490:
                if (a.equals("HONOR")) {
                    c = 1;
                    break;
                }
                break;
            case 2141820391:
                if (a.equals("HUAWEI")) {
                    c = 0;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 4:
            case 5:
            default:
                return "";
            case 2:
            case 3:
                return m21819a("ro.miui.ui.version.name");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: d */
    public static String m21814d() {
        char c;
        String a = m21819a("ro.product.brand");
        switch (a.hashCode()) {
            case -1675632421:
                if (a.equals("Xiaomi")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -759499589:
                if (a.equals("xiaomi")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 2432928:
                if (a.equals(ann.f16790d)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 3620012:
                if (a.equals("vivo")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 68924490:
                if (a.equals("HONOR")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 2141820391:
                if (a.equals("HUAWEI")) {
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
            case 1:
                return m21819a("ro.build.version.emui");
            case 2:
            case 3:
                return m21819a("ro.miui.ui.version.code");
            case 4:
                return m21819a("ro.build.version.opporom");
            case 5:
                return m21819a("ro.vivo.os.version");
            default:
                return "";
        }
    }

    /* renamed from: b */
    public static String m21816b(Context context) {
        return ((SensorManager) context.getSystemService("sensor")).getSensorList(-1).toString();
    }

    @RequiresApi(api = 18)
    /* renamed from: e */
    public static String m21813e() {
        try {
            MediaDrm mediaDrm = new MediaDrm(new UUID(-1301668207276963122L, -6645017420763422227L));
            byte[] propertyByteArray = mediaDrm.getPropertyByteArray("deviceUniqueId");
            byte[] propertyByteArray2 = mediaDrm.getPropertyByteArray("provisioningUniqueId");
            HashMap hashMap = new HashMap();
            hashMap.put("deviceUniqueId", m21818a(propertyByteArray));
            hashMap.put("provisioningUniqueId", m21818a(propertyByteArray2));
            hashMap.put("vendor", mediaDrm.getPropertyString("vendor"));
            hashMap.put(ClientCookie.VERSION_ATTR, mediaDrm.getPropertyString(ClientCookie.VERSION_ATTR));
            hashMap.put("description", mediaDrm.getPropertyString("description"));
            hashMap.put("algorithms", mediaDrm.getPropertyString("algorithms"));
            CLog.m21882i(f7127a, "getDrm" + C1123f.m21845a(hashMap));
            return C1123f.m21845a(hashMap);
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: a */
    public static String m21818a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            sb.append(f7129c[(b >> 4) & 15]);
            sb.append(f7129c[b & 15]);
        }
        return sb.toString();
    }

    /* renamed from: f */
    public static String m21812f() {
        return ScreenUtils.m23302b() + Marker.ANY_MARKER + ScreenUtils.m23306a();
    }

    @SuppressLint({"MissingPermission"})
    /* renamed from: g */
    public static String m21811g() {
        if (ActivityCompat.checkSelfPermission(Utils.m24103a(), "android.permission.ACCESS_WIFI_STATE") != 0) {
            return "";
        }
        if (ActivityCompat.checkSelfPermission(Utils.m24103a(), "android.permission.INTERNET") != 0) {
            return "";
        }
        String str = "";
        WifiManager wifiManager = (WifiManager) Utils.m24103a().getApplicationContext().getSystemService("wifi");
        if (wifiManager != null) {
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            str = connectionInfo != null ? connectionInfo.getSSID() : "";
        }
        return str.replace("\"", "");
    }

    @SuppressLint({"MissingPermission"})
    /* renamed from: h */
    public static String m21810h() {
        if (ActivityCompat.checkSelfPermission(Utils.m24103a(), "android.permission.ACCESS_WIFI_STATE") != 0) {
            return "";
        }
        if (ActivityCompat.checkSelfPermission(Utils.m24103a(), "android.permission.INTERNET") != 0) {
            return "";
        }
        return DeviceUtils.m22413a((String[]) null);
    }

    @SuppressLint({"MissingPermission"})
    /* renamed from: i */
    public static String m21809i() {
        try {
            if (ActivityCompat.checkSelfPermission(Utils.m24103a(), "android.permission.READ_PHONE_STATE") != 0) {
                return "";
            }
        } catch (Throwable unused) {
        }
        return PhoneUtils.m23536f();
    }

    @SuppressLint({"MissingPermission"})
    /* renamed from: j */
    public static String m21808j() {
        return DeviceImeiUtil.m21867a();
    }

    @SuppressLint({"MissingPermission"})
    /* renamed from: k */
    public static String m21807k() {
        try {
            if (ActivityCompat.checkSelfPermission(Utils.m24103a(), "android.permission.READ_PHONE_STATE") != 0) {
                return "";
            }
        } catch (Throwable unused) {
        }
        return ((TelephonyManager) Utils.m24103a().getApplicationContext().getSystemService(ShippingInfoWidget.f12563f)).getLine1Number();
    }

    @SuppressLint({"MissingPermission"})
    /* renamed from: l */
    public static String m21806l() {
        String a = m21819a("ro.serialno");
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        try {
            if (ActivityCompat.checkSelfPermission(Utils.m24103a(), "android.permission.READ_PHONE_STATE") != 0) {
                return "";
            }
        } catch (Throwable unused) {
        }
        return PhoneUtils.m23539c();
    }
}
