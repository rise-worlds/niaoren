package com.tendcloud.tenddata;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.media.AudioManager;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.cardemulation.CardEmulation;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.stripe.android.view.ShippingInfoWidget;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Marker;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.dv */
/* loaded from: classes2.dex */
public class C2821dv {

    /* renamed from: a */
    public static final String f13825a = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq";

    /* renamed from: b */
    public static final String f13826b = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq";

    /* renamed from: c */
    public static final String f13827c = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq";

    /* renamed from: d */
    private static final int f13828d = 3600000;

    /* renamed from: e */
    private static final Pattern f13829e = Pattern.compile("([0-9]+)");

    /* renamed from: f */
    private static final Pattern f13830f = Pattern.compile("\\s*([0-9]+)");

    /* renamed from: g */
    private static final FileFilter f13831g = new C2823dw();

    /* renamed from: h */
    private static BroadcastReceiver f13832h = new C2824dx();

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.dv$a */
    /* loaded from: classes2.dex */
    public static class C2822a {
        static final int HCE_ENABLED = 3;
        static final int NFC_ENABLED = 2;
        static final int NOT_ENALBED = 1;
        static final int UNKNOWN = 0;
    }

    /* renamed from: p */
    public static String[] m15909p() {
        return null;
    }

    /* renamed from: a */
    public static String m15946a() {
        return C2664ab.f13516j + Build.VERSION.RELEASE;
    }

    /* renamed from: b */
    public static String m15940b() {
        try {
            return Build.ID;
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: c */
    public static String m15936c() {
        return Build.MANUFACTURER.trim();
    }

    /* renamed from: d */
    public static String m15933d() {
        return Build.BRAND.trim();
    }

    /* renamed from: e */
    public static String m15931e() {
        return Build.MODEL.trim();
    }

    /* renamed from: f */
    public static int m15929f() {
        return TimeZone.getDefault().getRawOffset() / 3600000;
    }

    /* renamed from: g */
    public static String m15927g() {
        try {
            String trim = Build.MODEL.trim();
            String a = m15941a(Build.MANUFACTURER.trim(), trim);
            if (TextUtils.isEmpty(a)) {
                a = m15941a(Build.BRAND.trim(), trim);
            }
            if (a == null) {
                a = "";
            }
            return a + ":" + trim;
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: h */
    public static String m15925h() {
        try {
            if (C2855es.m15807a(14)) {
                return Build.getRadioVersion();
            }
            return "unknown";
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    /* renamed from: a */
    public static JSONObject m15945a(Context context) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("nfcStatus", m15939b(context));
            jSONObject.put("appsRegistedHCE", m15902s(context));
            jSONObject.put("ssMode", m15900t(context));
            return jSONObject;
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return null;
        }
    }

    /* renamed from: b */
    public static int m15939b(Context context) {
        NfcAdapter defaultAdapter;
        if (context == null) {
            return 0;
        }
        try {
            if (!C2855es.m15807a(10) || (defaultAdapter = ((NfcManager) context.getSystemService("nfc")).getDefaultAdapter()) == null) {
                return 0;
            }
            if (!defaultAdapter.isEnabled()) {
                return 1;
            }
            if (C2855es.m15807a(19)) {
                if (context.getPackageManager().hasSystemFeature("android.hardware.nfc.hce")) {
                    return 3;
                }
            }
            return 2;
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return 0;
        }
    }

    /* renamed from: s */
    private static JSONArray m15902s(Context context) {
        ServiceInfo[] serviceInfoArr;
        if (!C2855es.m15807a(19)) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray();
            List<PackageInfo> a = m15944a(context, 4);
            if (a != null) {
                for (PackageInfo packageInfo : a) {
                    if (packageInfo != null && (serviceInfoArr = packageInfo.services) != null) {
                        int length = serviceInfoArr.length;
                        int i = 0;
                        while (true) {
                            if (i < length) {
                                ServiceInfo serviceInfo = serviceInfoArr[i];
                                try {
                                    Bundle bundle = context.getPackageManager().getServiceInfo(new ComponentName(serviceInfo.packageName, serviceInfo.name), 128).metaData;
                                    if (bundle != null && bundle.containsKey("android.nfc.cardemulation.host_apdu_service")) {
                                        jSONArray.put(packageInfo.packageName);
                                        break;
                                    }
                                } catch (Throwable unused) {
                                }
                                i++;
                            }
                        }
                    }
                }
            }
            return jSONArray;
        } catch (Throwable unused2) {
            return null;
        }
    }

    /* renamed from: a */
    private static List m15944a(Context context, int i) {
        Throwable th;
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getInstalledPackages(i);
        } catch (Throwable unused) {
            ArrayList arrayList = new ArrayList();
            BufferedReader bufferedReader = null;
            try {
                try {
                    Process exec = Runtime.getRuntime().exec("pm list packages");
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(exec.getInputStream()));
                    while (true) {
                        try {
                            String readLine = bufferedReader2.readLine();
                            if (readLine == null) {
                                break;
                            }
                            arrayList.add(packageManager.getPackageInfo(readLine.substring(readLine.indexOf(58) + 1), i));
                        } catch (Throwable th2) {
                            th = th2;
                            bufferedReader = bufferedReader2;
                            try {
                                C2933hb.postSDKError(th);
                                if (bufferedReader != null) {
                                    bufferedReader.close();
                                }
                                return arrayList;
                            } catch (Throwable th3) {
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (Throwable unused2) {
                                    }
                                }
                                throw th3;
                            }
                        }
                    }
                    exec.waitFor();
                    bufferedReader2.close();
                } catch (Throwable th4) {
                    th = th4;
                }
            } catch (Throwable unused3) {
            }
            return arrayList;
        }
    }

    /* renamed from: t */
    private static int m15900t(Context context) {
        NfcAdapter defaultAdapter;
        try {
            if (!C2855es.m15807a(19) || (defaultAdapter = ((NfcManager) context.getSystemService("nfc")).getDefaultAdapter()) == null) {
                return -1;
            }
            return CardEmulation.getInstance(defaultAdapter).getSelectionModeForCategory("payment");
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return -1;
        }
    }

    /* renamed from: a */
    private static String m15941a(String str, String str2) {
        try {
            String lowerCase = str.toLowerCase();
            if (!lowerCase.startsWith("unknown") && !lowerCase.startsWith("alps") && !lowerCase.startsWith("android") && !lowerCase.startsWith("sprd") && !lowerCase.startsWith("spreadtrum") && !lowerCase.startsWith("rockchip") && !lowerCase.startsWith("wondermedia") && !lowerCase.startsWith("mtk") && !lowerCase.startsWith("mt65") && !lowerCase.startsWith("nvidia") && !lowerCase.startsWith("brcm") && !lowerCase.startsWith("marvell")) {
                if (!str2.toLowerCase().contains(lowerCase)) {
                    return str;
                }
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: i */
    public static int m15923i() {
        return Build.VERSION.SDK_INT;
    }

    /* renamed from: j */
    public static String m15921j() {
        return Build.VERSION.RELEASE;
    }

    /* renamed from: k */
    public static String m15919k() {
        return Locale.getDefault().getLanguage();
    }

    /* renamed from: l */
    public static String m15917l() {
        return Locale.getDefault().getCountry();
    }

    /* renamed from: a */
    public static JSONObject m15943a(Context context, JSONObject jSONObject) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            if (displayMetrics != null) {
                int i = displayMetrics.widthPixels;
                int i2 = displayMetrics.heightPixels;
                jSONObject.put("pixel", Math.min(i, i2) + Marker.ANY_MARKER + Math.max(i, i2) + Marker.ANY_MARKER + displayMetrics.densityDpi);
                jSONObject.put("densityDpi", displayMetrics.densityDpi);
            }
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    /* renamed from: b */
    public static JSONObject m15938b(Context context, JSONObject jSONObject) {
        try {
            jSONObject.put("brightness", m15904r(context));
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    /* renamed from: c */
    public static String m15935c(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            if (displayMetrics == null) {
                return "";
            }
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            return Math.min(i, i2) + Marker.ANY_MARKER + Math.max(i, i2) + Marker.ANY_MARKER + displayMetrics.densityDpi;
        } catch (Throwable unused) {
            return "";
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x003b, code lost:
        throw new java.lang.RuntimeException("List size more than 104857600 limit");
     */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0060 A[Catch: Throwable -> 0x0099, TryCatch #3 {Throwable -> 0x0099, blocks: (B:6:0x0013, B:15:0x003c, B:17:0x004c, B:19:0x0053, B:21:0x0060, B:23:0x0067, B:25:0x0070, B:27:0x0080, B:28:0x008a, B:29:0x008d, B:30:0x0090), top: B:41:0x0013 }] */
    /* renamed from: m */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String[] m15915m() {
        /*
            r0 = 4
            java.lang.String[] r1 = new java.lang.String[r0]
            r2 = 0
            r3 = 0
        L_0x0005:
            if (r3 >= r0) goto L_0x000e
            java.lang.String r4 = ""
            r1[r3] = r4
            int r3 = r3 + 1
            goto L_0x0005
        L_0x000e:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.io.FileReader r3 = new java.io.FileReader     // Catch: Throwable -> 0x0099
            java.lang.String r4 = "/proc/cpuinfo"
            r3.<init>(r4)     // Catch: Throwable -> 0x0099
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch: Throwable -> 0x0099
            r5 = 1024(0x400, float:1.435E-42)
            r4.<init>(r3, r5)     // Catch: Throwable -> 0x0099
        L_0x0021:
            r5 = 1
            java.lang.String r6 = r4.readLine()     // Catch: Throwable -> 0x004c
            if (r6 == 0) goto L_0x003c
            r0.add(r6)     // Catch: Throwable -> 0x004c
            int r6 = r0.size()     // Catch: Throwable -> 0x004c
            r7 = 104857600(0x6400000, float:3.6111186E-35)
            if (r6 > r7) goto L_0x0034
            goto L_0x0021
        L_0x0034:
            java.lang.RuntimeException r6 = new java.lang.RuntimeException     // Catch: Throwable -> 0x004c
            java.lang.String r7 = "List size more than 104857600 limit"
            r6.<init>(r7)     // Catch: Throwable -> 0x004c
            throw r6     // Catch: Throwable -> 0x004c
        L_0x003c:
            r4.close()     // Catch: IOException -> 0x0042, Throwable -> 0x0099
            r3.close()     // Catch: IOException -> 0x0042, Throwable -> 0x0099
        L_0x0042:
            r3 = 1
            goto L_0x0053
        L_0x0044:
            r0 = move-exception
            r4.close()     // Catch: IOException -> 0x004b, Throwable -> 0x0099
            r3.close()     // Catch: IOException -> 0x004b, Throwable -> 0x0099
        L_0x004b:
            throw r0     // Catch: Throwable -> 0x0099
        L_0x004c:
            r4.close()     // Catch: IOException -> 0x0052, Throwable -> 0x0099
            r3.close()     // Catch: IOException -> 0x0052, Throwable -> 0x0099
        L_0x0052:
            r3 = 0
        L_0x0053:
            java.lang.String r4 = "Processor\\s*:\\s*(.*)"
            java.lang.String r6 = "CPU\\s*variant\\s*:\\s*0x(.*)"
            java.lang.String r7 = "Hardware\\s*:\\s*(.*)"
            java.lang.String[] r4 = new java.lang.String[]{r4, r6, r7}     // Catch: Throwable -> 0x0099
            r6 = 3
            if (r3 == 0) goto L_0x0090
            int r3 = r0.size()     // Catch: Throwable -> 0x0099
            r7 = 0
        L_0x0065:
            if (r7 >= r6) goto L_0x0090
            r8 = r4[r7]     // Catch: Throwable -> 0x0099
            java.util.regex.Pattern r8 = java.util.regex.Pattern.compile(r8)     // Catch: Throwable -> 0x0099
            r9 = 0
        L_0x006e:
            if (r9 >= r3) goto L_0x008d
            java.lang.Object r10 = r0.get(r9)     // Catch: Throwable -> 0x0099
            java.lang.String r10 = (java.lang.String) r10     // Catch: Throwable -> 0x0099
            java.util.regex.Matcher r10 = r8.matcher(r10)     // Catch: Throwable -> 0x0099
            boolean r11 = r10.find()     // Catch: Throwable -> 0x0099
            if (r11 == 0) goto L_0x008a
            java.util.regex.MatchResult r10 = r10.toMatchResult()     // Catch: Throwable -> 0x0099
            java.lang.String r10 = r10.group(r5)     // Catch: Throwable -> 0x0099
            r1[r7] = r10     // Catch: Throwable -> 0x0099
        L_0x008a:
            int r9 = r9 + 1
            goto L_0x006e
        L_0x008d:
            int r7 = r7 + 1
            goto L_0x0065
        L_0x0090:
            java.lang.String r0 = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"
            java.lang.String r0 = m15934c(r0)     // Catch: Throwable -> 0x0099
            r1[r6] = r0     // Catch: Throwable -> 0x0099
            goto L_0x009d
        L_0x0099:
            r0 = move-exception
            com.tendcloud.tenddata.C2933hb.postSDKError(r0)
        L_0x009d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tendcloud.tenddata.C2821dv.m15915m():java.lang.String[]");
    }

    /* renamed from: n */
    public static JSONObject m15913n() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("name", m15915m()[2]);
            jSONObject.put("coreNum", m15911o());
            jSONObject.put("maxFreq", m15942a(f13825a));
            jSONObject.put("minFreq", m15942a(f13826b));
            jSONObject.put("curFreq", m15942a(f13827c));
            return jSONObject;
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return null;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:15:0x0036
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: a */
    public static int m15942a(java.lang.String r6) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            r1 = -1
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            r0 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch: Throwable -> 0x0052
            r2.<init>(r6)     // Catch: Throwable -> 0x0052
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch: Throwable -> 0x003f
            r6.<init>(r2)     // Catch: Throwable -> 0x003f
            java.lang.String r0 = r6.readLine()     // Catch: Throwable -> 0x0038
            int r3 = r0.length()     // Catch: Throwable -> 0x0038
            r4 = 104857600(0x6400000, float:3.6111186E-35)
            if (r3 > r4) goto L_0x002e
            java.lang.String r0 = r0.trim()     // Catch: Throwable -> 0x0038
            int r1 = java.lang.Integer.parseInt(r0)     // Catch: Throwable -> 0x0038
            r2.close()     // Catch: Throwable -> 0x002a
        L_0x002a:
            r6.close()     // Catch: Throwable -> 0x005e
            goto L_0x005e
        L_0x002e:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch: Throwable -> 0x0038
            java.lang.String r3 = "Input stream more than 100 MB size limit"
            r0.<init>(r3)     // Catch: Throwable -> 0x0038
            throw r0     // Catch: Throwable -> 0x0038
        L_0x0036:
            r0 = move-exception
            goto L_0x0045
        L_0x0038:
            goto L_0x0054
        L_0x003a:
            r6 = move-exception
            r5 = r0
            r0 = r6
            r6 = r5
            goto L_0x0045
        L_0x003f:
            r6 = r0
            goto L_0x0054
        L_0x0041:
            r6 = move-exception
            r2 = r0
            r0 = r6
            r6 = r2
        L_0x0045:
            if (r2 == 0) goto L_0x004c
            r2.close()     // Catch: Throwable -> 0x004b
            goto L_0x004c
        L_0x004b:
        L_0x004c:
            if (r6 == 0) goto L_0x0051
            r6.close()     // Catch: Throwable -> 0x0051
        L_0x0051:
            throw r0
        L_0x0052:
            r6 = r0
            r2 = r6
        L_0x0054:
            if (r2 == 0) goto L_0x005b
            r2.close()     // Catch: Throwable -> 0x005a
            goto L_0x005b
        L_0x005a:
        L_0x005b:
            if (r6 == 0) goto L_0x005e
            goto L_0x002a
        L_0x005e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tendcloud.tenddata.C2821dv.m15942a(java.lang.String):int");
    }

    /* renamed from: o */
    public static int m15911o() {
        try {
            File[] listFiles = new File("/sys/devices/system/cpu/").listFiles(f13831g);
            if (listFiles != null) {
                return listFiles.length;
            }
            return 1;
        } catch (Throwable unused) {
            return 1;
        }
    }

    /* renamed from: q */
    public static int[] m15907q() {
        int[] iArr = {0, 0};
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                int blockSize = statFs.getBlockSize();
                int blockCount = statFs.getBlockCount();
                int availableBlocks = statFs.getAvailableBlocks();
                iArr[0] = (blockCount * (blockSize / 512)) / 2;
                iArr[1] = (availableBlocks * (blockSize / 512)) / 2;
            }
        } catch (Throwable unused) {
        }
        return iArr;
    }

    /* renamed from: r */
    public static int[] m15905r() {
        int[] iArr = {0, 0};
        int[] iArr2 = new int[4];
        for (int i = 0; i < 4; i++) {
            iArr2[i] = 0;
        }
        try {
            FileReader fileReader = new FileReader("/proc/meminfo");
            BufferedReader bufferedReader = new BufferedReader(fileReader, 1024);
            for (int i2 = 0; i2 < 4; i2++) {
                try {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine.length() <= 104857600) {
                            iArr2[i2] = m15937b(readLine);
                        } else {
                            throw new RuntimeException("Input stream more than 100 MB size limit");
                        }
                    } catch (IOException unused) {
                        bufferedReader.close();
                    } catch (Throwable th) {
                        try {
                            bufferedReader.close();
                            fileReader.close();
                        } catch (IOException unused2) {
                        }
                        throw th;
                    }
                } catch (IOException unused3) {
                }
            }
            iArr[0] = iArr2[0];
            iArr[1] = iArr2[1] + iArr2[2] + iArr2[3];
            bufferedReader.close();
            fileReader.close();
        } catch (Throwable th2) {
            C2933hb.postSDKError(th2);
        }
        return iArr;
    }

    /* renamed from: s */
    public static int[] m15903s() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
            StatFs statFs2 = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
            return new int[]{(statFs.getBlockCount() * (statFs.getBlockSize() / 512)) / 2, (statFs.getAvailableBlocks() * (statFs.getBlockSize() / 512)) / 2, (statFs2.getBlockCount() * (statFs2.getBlockSize() / 512)) / 2, (statFs2.getAvailableBlocks() * (statFs2.getBlockSize() / 512)) / 2};
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return null;
        }
    }

    /* renamed from: d */
    public static int m15932d(Context context) {
        if (context == null) {
            try {
                if (C2664ab.f13513g == null) {
                    return 0;
                }
                context = C2664ab.f13513g;
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
                return 0;
            }
        }
        Intent registerReceiver = context.registerReceiver(f13832h, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        int i = registerReceiver != null ? registerReceiver.getExtras().getInt("level", 0) : 0;
        context.unregisterReceiver(f13832h);
        return i;
    }

    /* renamed from: e */
    public static int m15930e(Context context) {
        if (context == null) {
            try {
                if (C2664ab.f13513g == null) {
                    return 0;
                }
                context = C2664ab.f13513g;
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
                return 0;
            }
        }
        Intent registerReceiver = context.registerReceiver(f13832h, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        int intExtra = registerReceiver != null ? registerReceiver.getIntExtra("status", 0) : 0;
        context.unregisterReceiver(f13832h);
        return intExtra;
    }

    /* renamed from: b */
    private static int m15937b(String str) {
        try {
            String str2 = "";
            Matcher matcher = f13829e.matcher(str);
            if (matcher.find()) {
                str2 = matcher.toMatchResult().group(0);
            }
            return Integer.parseInt(str2);
        } catch (Exception e) {
            C2933hb.postSDKError(e);
            return 0;
        }
    }

    /* renamed from: t */
    public static int m15901t() {
        try {
            Matcher matcher = f13830f.matcher(m15934c("/sys/class/power_supply/battery/full_bat"));
            if (matcher.find()) {
                return Integer.parseInt(matcher.toMatchResult().group(0));
            }
            return 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    /* renamed from: c */
    private static String m15934c(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            FileReader fileReader = new FileReader(str);
            char[] cArr = new char[1024];
            BufferedReader bufferedReader = new BufferedReader(fileReader, 1024);
            while (true) {
                int read = bufferedReader.read(cArr, 0, 1024);
                if (-1 == read) {
                    break;
                }
                stringBuffer.append(new String(cArr, 0, read));
            }
            bufferedReader.close();
            fileReader.close();
        } catch (Throwable unused) {
        }
        return stringBuffer.toString();
    }

    /* renamed from: f */
    public static JSONObject m15928f(Context context) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("mobile", m15912n(context));
            jSONObject.put("wifi", m15922i(context));
            jSONObject.put("gps", m15924h(context));
            jSONObject.put("telephone", m15914m(context));
            jSONObject.put("nfc", m15918k(context));
            jSONObject.put("bluetooth", m15920j(context));
            jSONObject.put("otg", m15926g(context));
            jSONObject.put("insertEarphones", m15916l(context));
            return jSONObject;
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return null;
        }
    }

    /* renamed from: g */
    public static boolean m15926g(Context context) {
        if (context == null) {
            if (C2664ab.f13513g == null) {
                return false;
            }
            context = C2664ab.f13513g;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                return packageManager.hasSystemFeature("android.hardware.usb.host");
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: h */
    public static boolean m15924h(Context context) {
        if (context == null) {
            if (C2664ab.f13513g == null) {
                return false;
            }
            context = C2664ab.f13513g;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                return packageManager.hasSystemFeature("android.hardware.location.gps");
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: i */
    public static boolean m15922i(Context context) {
        if (context == null) {
            if (C2664ab.f13513g == null) {
                return false;
            }
            context = C2664ab.f13513g;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                return packageManager.hasSystemFeature("android.hardware.wifi");
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: j */
    public static boolean m15920j(Context context) {
        if (context == null) {
            if (C2664ab.f13513g == null) {
                return false;
            }
            context = C2664ab.f13513g;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                return packageManager.hasSystemFeature("android.hardware.bluetooth");
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: k */
    public static boolean m15918k(Context context) {
        if (context == null) {
            if (C2664ab.f13513g == null) {
                return false;
            }
            context = C2664ab.f13513g;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                return packageManager.hasSystemFeature("android.hardware.nfc");
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: l */
    public static boolean m15916l(Context context) {
        if (context == null) {
            if (C2664ab.f13513g == null) {
                return false;
            }
            context = C2664ab.f13513g;
        }
        try {
            AudioManager audioManager = (AudioManager) context.getSystemService("audio");
            if (audioManager != null) {
                return audioManager.isWiredHeadsetOn();
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: m */
    public static boolean m15914m(Context context) {
        if (context == null) {
            if (C2664ab.f13513g == null) {
                return false;
            }
            context = C2664ab.f13513g;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f);
            if (telephonyManager != null) {
                return telephonyManager.getPhoneType() != 0;
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: n */
    public static boolean m15912n(Context context) {
        if (context == null) {
            if (C2664ab.f13513g == null) {
                return false;
            }
            context = C2664ab.f13513g;
        }
        try {
            return context.getPackageManager().hasSystemFeature("android.hardware.telephony");
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: o */
    public static int m15910o(Context context) {
        if (context == null) {
            try {
                if (C2664ab.f13513g == null) {
                    return -1;
                }
                context = C2664ab.f13513g;
            } catch (Throwable unused) {
            }
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (displayMetrics != null) {
            return displayMetrics.widthPixels;
        }
        return -1;
    }

    /* renamed from: p */
    public static int m15908p(Context context) {
        if (context == null) {
            try {
                if (C2664ab.f13513g == null) {
                    return -1;
                }
                context = C2664ab.f13513g;
            } catch (Throwable unused) {
            }
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (displayMetrics != null) {
            return displayMetrics.heightPixels;
        }
        return -1;
    }

    /* renamed from: q */
    public static int m15906q(Context context) {
        if (context == null) {
            try {
                if (C2664ab.f13513g == null) {
                    return -1;
                }
                context = C2664ab.f13513g;
            } catch (Throwable unused) {
            }
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (displayMetrics != null) {
            return displayMetrics.densityDpi;
        }
        return -1;
    }

    /* renamed from: r */
    public static int m15904r(Context context) {
        if (context == null) {
            if (C2664ab.f13513g == null) {
                return -1;
            }
            context = C2664ab.f13513g;
        }
        try {
            return Settings.System.getInt(context.getContentResolver(), "screen_brightness");
        } catch (Throwable unused) {
            return -1;
        }
    }
}
