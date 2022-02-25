package com.cyjh.mobileanjian.ipc;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.PowerManager;
import android.os.Vibrator;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodInfo;
import com.ime.input.InputMethodManager;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.stripe.android.view.ShippingInfoWidget;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.tools.ant.taskdefs.optional.clearcase.ClearCase;

/* renamed from: com.cyjh.mobileanjian.ipc.a */
/* loaded from: classes.dex */
public final class AppAgent {

    /* renamed from: c */
    public static final String f8191c = "NO NETWORK";

    /* renamed from: d */
    public static final String f8192d = "WiFi";

    /* renamed from: e */
    public static final String f8193e = "2G";

    /* renamed from: f */
    public static final String f8194f = "3G";

    /* renamed from: g */
    public static final String f8195g = "4G";

    /* renamed from: k */
    private static MediaPlayer f8196k = new MediaPlayer();

    /* renamed from: a */
    public Context f8197a;

    /* renamed from: b */
    public InputMethodManager f8198b = InputMethodManager.m19885a();

    /* renamed from: h */
    private PowerManager.WakeLock f8199h;

    /* renamed from: i */
    private PowerManager.WakeLock f8200i;

    /* renamed from: j */
    private KeyguardManager.KeyguardLock f8201j;

    public AppAgent(Context context) {
        this.f8197a = null;
        this.f8197a = context;
        this.f8199h = ((PowerManager) this.f8197a.getSystemService("power")).newWakeLock(6, "fzcyjh");
        this.f8199h.setReferenceCounted(false);
        this.f8201j = ((KeyguardManager) this.f8197a.getSystemService("keyguard")).newKeyguardLock(ClearCase.COMMAND_UNLOCK);
        this.f8200i = ((PowerManager) this.f8197a.getSystemService("power")).newWakeLock(268435462, "lock_unlock_screen");
    }

    /* renamed from: l */
    private void m21061l() {
        this.f8199h = ((PowerManager) this.f8197a.getSystemService("power")).newWakeLock(6, "fzcyjh");
        this.f8199h.setReferenceCounted(false);
    }

    /* renamed from: m */
    private boolean m21060m() {
        try {
            return ((Vibrator) this.f8197a.getSystemService("vibrator")).hasVibrator();
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: a */
    public final void m21084a(int i) {
        if (m21060m()) {
            m21085a();
            ((Vibrator) this.f8197a.getSystemService("vibrator")).vibrate(i);
        }
    }

    /* renamed from: a */
    public final void m21085a() {
        ((Vibrator) this.f8197a.getSystemService("vibrator")).cancel();
    }

    /* renamed from: a */
    public final boolean m21083a(String str) {
        if (str == null || str.trim().equals("")) {
            return false;
        }
        try {
            Intent launchIntentForPackage = this.f8197a.getPackageManager().getLaunchIntentForPackage(str);
            if (launchIntentForPackage == null) {
                return false;
            }
            launchIntentForPackage.setFlags(270532608);
            launchIntentForPackage.setPackage(null);
            this.f8197a.startActivity(launchIntentForPackage);
            StringBuilder sb = new StringBuilder("runApp(");
            sb.append(str);
            sb.append(") OK.");
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: f */
    private static void m21067f(int i) {
        if (i > 0) {
            RootShell a = RootShell.m21029a();
            a.m21022a("kill " + i);
        }
    }

    /* renamed from: b */
    public final boolean m21079b(String str) {
        boolean z = false;
        if (str == null || str.trim().equals("")) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) this.f8197a.getSystemService(ServiceManagerNative.ACTIVITY)).getRunningAppProcesses()) {
            if (runningAppProcessInfo.processName.equals(str)) {
                int i = runningAppProcessInfo.pid;
                if (i > 0) {
                    RootShell a = RootShell.m21029a();
                    a.m21022a("kill " + i);
                }
                z = true;
            }
        }
        return z;
    }

    /* renamed from: n */
    private List<String> m21059n() {
        ArrayList arrayList = new ArrayList();
        for (InputMethodInfo inputMethodInfo : ((android.view.inputmethod.InputMethodManager) this.f8197a.getSystemService("input_method")).getInputMethodList()) {
            arrayList.add(inputMethodInfo.getId());
        }
        return arrayList;
    }

    /* renamed from: b */
    public final String m21081b() {
        String string = Settings.Secure.getString(this.f8197a.getContentResolver(), "default_input_method");
        if (!string.contains("com.cyjh")) {
            return string;
        }
        ArrayList<String> arrayList = new ArrayList();
        for (InputMethodInfo inputMethodInfo : ((android.view.inputmethod.InputMethodManager) this.f8197a.getSystemService("input_method")).getInputMethodList()) {
            arrayList.add(inputMethodInfo.getId());
        }
        for (String str : arrayList) {
            if (!str.contains("com.cyjh")) {
                return str;
            }
        }
        return string;
    }

    /* renamed from: e */
    private void m21069e(String str) {
        InputConnection currentInputConnection;
        InputMethodManager aVar = this.f8198b;
        if (aVar.f9378a != null && (currentInputConnection = aVar.f9378a.getCurrentInputConnection()) != null) {
            currentInputConnection.commitText(str, str.length());
        }
    }

    /* renamed from: c */
    public final String m21077c() {
        WifiInfo connectionInfo = ((WifiManager) this.f8197a.getSystemService("wifi")).getConnectionInfo();
        return connectionInfo == null ? "" : connectionInfo.getMacAddress();
    }

    @SuppressLint({"MissingPermission"})
    /* renamed from: d */
    public final String m21074d() {
        String str = null;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) this.f8197a.getSystemService(ShippingInfoWidget.f12563f);
            if (Build.VERSION.SDK_INT >= 29) {
                str = Settings.System.getString(this.f8197a.getContentResolver(), "android_id");
            } else {
                str = telephonyManager.getDeviceId();
            }
            if (str == null || str.equals("")) {
                str = m21077c();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (str == null || str.equals("")) ? m21077c() : str;
    }

    /* renamed from: b */
    public final void m21080b(int i) {
        if (i > 0) {
            this.f8199h.acquire();
            return;
        }
        PowerManager.WakeLock wakeLock = this.f8199h;
        if (wakeLock != null) {
            wakeLock.release();
        }
    }

    /* renamed from: e */
    public final void m21071e() {
        String packageName = this.f8197a.getPackageName();
        ActivityManager activityManager = (ActivityManager) this.f8197a.getSystemService(ServiceManagerNative.ACTIVITY);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (int i = 0; i < runningAppProcesses.size(); i++) {
                ActivityManager.RunningAppProcessInfo runningAppProcessInfo = runningAppProcesses.get(i);
                if (!runningAppProcessInfo.processName.contains(packageName) && runningAppProcessInfo.importance > 200) {
                    for (String str : runningAppProcessInfo.pkgList) {
                        activityManager.killBackgroundProcesses(str);
                    }
                }
            }
        }
    }

    /* renamed from: c */
    public static void m21075c(String str) {
        if (f8196k == null) {
            f8196k = new MediaPlayer();
        }
        m21068f();
        try {
            f8196k.reset();
            f8196k.setDataSource(str);
            f8196k.prepare();
            f8196k.start();
        } catch (IOException e) {
            e.printStackTrace();
            f8196k.release();
            f8196k = null;
        }
    }

    /* renamed from: f */
    public static void m21068f() {
        MediaPlayer mediaPlayer = f8196k;
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            f8196k.stop();
        }
    }

    /* renamed from: g */
    public final void m21066g() {
        ((DevicePolicyManager) this.f8197a.getSystemService("device_policy")).lockNow();
        this.f8201j.reenableKeyguard();
    }

    /* renamed from: h */
    public final void m21065h() {
        this.f8201j.disableKeyguard();
        this.f8200i.acquire();
        this.f8200i.release();
    }

    /* renamed from: c */
    public final void m21076c(int i) {
        Settings.System.putInt(this.f8197a.getContentResolver(), "screen_off_timeout", i * 1000);
    }

    /* renamed from: d */
    public final void m21073d(int i) {
        Settings.System.putInt(this.f8197a.getContentResolver(), "screen_brightness", (int) ((i * 255) / 100.0d));
    }

    /* renamed from: e */
    public final void m21070e(int i) {
        AudioManager audioManager = (AudioManager) this.f8197a.getSystemService("audio");
        audioManager.setStreamVolume(4, (int) ((audioManager.getStreamMaxVolume(4) * i) / 100.0d), 8);
        audioManager.setStreamVolume(3, (int) ((audioManager.getStreamMaxVolume(3) * i) / 100.0d), 8);
        audioManager.setStreamVolume(5, (int) ((audioManager.getStreamMaxVolume(5) * i) / 100.0d), 8);
        audioManager.setStreamVolume(2, (int) ((audioManager.getStreamMaxVolume(2) * i) / 100.0d), 8);
        audioManager.setStreamVolume(1, (int) ((audioManager.getStreamMaxVolume(1) * i) / 100.0d), 8);
        audioManager.setStreamVolume(0, (int) ((audioManager.getStreamMaxVolume(0) * i) / 100.0d), 8);
        audioManager.setStreamVolume(8, (int) ((i * audioManager.getStreamMaxVolume(8)) / 100.0d), 8);
    }

    /* renamed from: a */
    private void m21082a(boolean z) {
        Settings.System.putInt(this.f8197a.getContentResolver(), "airplane_mode_on", z ? 1 : 0);
        Intent intent = new Intent("android.intent.action.AIRPLANE_MODE");
        intent.putExtra(ShippingInfoWidget.f12562e, z);
        this.f8197a.sendBroadcast(intent);
    }

    /* renamed from: b */
    private void m21078b(boolean z) {
        Settings.System.putInt(this.f8197a.getContentResolver(), "accelerometer_rotation", !z ? 1 : 0);
    }

    /* renamed from: i */
    public final String m21064i() {
        NetworkInfo activeNetworkInfo;
        NetworkInfo.State state;
        ConnectivityManager connectivityManager = (ConnectivityManager) this.f8197a.getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isAvailable()) {
            return f8191c;
        }
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
        if (networkInfo != null && (state = networkInfo.getState()) != null && (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING)) {
            return f8192d;
        }
        NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
        if (networkInfo2 == null) {
            return f8191c;
        }
        NetworkInfo.State state2 = networkInfo2.getState();
        String subtypeName = networkInfo2.getSubtypeName();
        if (state2 == null) {
            return f8191c;
        }
        if (state2 != NetworkInfo.State.CONNECTED && state2 != NetworkInfo.State.CONNECTING) {
            return f8191c;
        }
        switch (activeNetworkInfo.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return f8193e;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return f8194f;
            case 13:
                return f8195g;
            default:
                return (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) ? f8194f : f8193e;
        }
    }

    /* renamed from: d */
    public final String m21072d(String str) {
        try {
            return this.f8197a.getPackageManager().getApplicationInfo(str, 0).sourceDir;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: j */
    public final String m21063j() {
        try {
            return this.f8197a.getFilesDir().getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: k */
    public final void m21062k() {
        Intent intent = new Intent("android.net.vpn.SETTINGS");
        intent.setFlags(268435456);
        this.f8197a.startActivity(intent);
    }
}
