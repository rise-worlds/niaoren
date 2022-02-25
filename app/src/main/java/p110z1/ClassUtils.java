package p110z1;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: z1.t */
/* loaded from: classes3.dex */
public class ClassUtils {

    /* renamed from: a */
    private static final String f23379a = ".classes";

    /* renamed from: b */
    private static final String f23380b = ".zip";

    /* renamed from: c */
    private static final String f23381c = "code_cache" + File.separator + "secondary-dexes";

    /* renamed from: d */
    private static final String f23382d = "multidex.version";

    /* renamed from: e */
    private static final String f23383e = "dex.number";

    /* renamed from: f */
    private static final int f23384f = 2;

    /* renamed from: g */
    private static final int f23385g = 1;

    /* renamed from: b */
    private static SharedPreferences m666b(Context context) {
        return context.getSharedPreferences(f23382d, Build.VERSION.SDK_INT < 11 ? 0 : 4);
    }

    /* renamed from: a */
    public static Set<String> m669a(Context context, final String str) throws PackageManager.NameNotFoundException, IOException, InterruptedException {
        final HashSet hashSet = new HashSet();
        List<String> a = m670a(context);
        final CountDownLatch countDownLatch = new CountDownLatch(a.size());
        for (final String str2 : a) {
            DefaultPoolExecutor.m1225a().execute(new Runnable() { // from class: z1.t.1
                /* JADX WARN: Code restructure failed: missing block: B:17:0x0059, code lost:
                    if (r0 == null) goto L_0x005e;
                 */
                @Override // java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public void run() {
                    /*
                        r4 = this;
                        r0 = 0
                        java.lang.String r1 = r1     // Catch: Throwable -> 0x0051
                        java.lang.String r2 = ".zip"
                        boolean r1 = r1.endsWith(r2)     // Catch: Throwable -> 0x0051
                        if (r1 == 0) goto L_0x0026
                        java.lang.String r1 = r1     // Catch: Throwable -> 0x0051
                        java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: Throwable -> 0x0051
                        r2.<init>()     // Catch: Throwable -> 0x0051
                        java.lang.String r3 = r1     // Catch: Throwable -> 0x0051
                        r2.append(r3)     // Catch: Throwable -> 0x0051
                        java.lang.String r3 = ".tmp"
                        r2.append(r3)     // Catch: Throwable -> 0x0051
                        java.lang.String r2 = r2.toString()     // Catch: Throwable -> 0x0051
                        r3 = 0
                        dalvik.system.DexFile r0 = dalvik.system.DexFile.loadDex(r1, r2, r3)     // Catch: Throwable -> 0x0051
                        goto L_0x002e
                    L_0x0026:
                        dalvik.system.DexFile r1 = new dalvik.system.DexFile     // Catch: Throwable -> 0x0051
                        java.lang.String r2 = r1     // Catch: Throwable -> 0x0051
                        r1.<init>(r2)     // Catch: Throwable -> 0x0051
                        r0 = r1
                    L_0x002e:
                        java.util.Enumeration r1 = r0.entries()     // Catch: Throwable -> 0x0051
                    L_0x0032:
                        boolean r2 = r1.hasMoreElements()     // Catch: Throwable -> 0x0051
                        if (r2 == 0) goto L_0x004c
                        java.lang.Object r2 = r1.nextElement()     // Catch: Throwable -> 0x0051
                        java.lang.String r2 = (java.lang.String) r2     // Catch: Throwable -> 0x0051
                        java.lang.String r3 = r2     // Catch: Throwable -> 0x0051
                        boolean r3 = r2.startsWith(r3)     // Catch: Throwable -> 0x0051
                        if (r3 == 0) goto L_0x0032
                        java.util.Set r3 = r3     // Catch: Throwable -> 0x0051
                        r3.add(r2)     // Catch: Throwable -> 0x0051
                        goto L_0x0032
                    L_0x004c:
                        if (r0 == 0) goto L_0x005e
                        goto L_0x005b
                    L_0x004f:
                        r1 = move-exception
                        goto L_0x0064
                    L_0x0051:
                        r1 = move-exception
                        java.lang.String r2 = "ARouter"
                        java.lang.String r3 = "Scan map file in dex files made error."
                        android.util.Log.e(r2, r3, r1)     // Catch: all -> 0x004f
                        if (r0 == 0) goto L_0x005e
                    L_0x005b:
                        r0.close()     // Catch: Throwable -> 0x005e
                    L_0x005e:
                        java.util.concurrent.CountDownLatch r0 = r4
                        r0.countDown()
                        return
                    L_0x0064:
                        if (r0 == 0) goto L_0x0069
                        r0.close()     // Catch: Throwable -> 0x0069
                    L_0x0069:
                        java.util.concurrent.CountDownLatch r0 = r4
                        r0.countDown()
                        throw r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: p110z1.ClassUtils.RunnableC55661.run():void");
                }
            });
        }
        countDownLatch.await();
        Log.d("ARouter::", "Filter " + hashSet.size() + " classes by packageName <" + str + SimpleComparison.f23610d);
        return hashSet;
    }

    /* renamed from: a */
    public static List<String> m670a(Context context) throws PackageManager.NameNotFoundException, IOException {
        ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
        File file = new File(applicationInfo.sourceDir);
        ArrayList arrayList = new ArrayList();
        arrayList.add(applicationInfo.sourceDir);
        String str = file.getName() + f23379a;
        if (!m671a()) {
            int i = m666b(context).getInt(f23383e, 1);
            File file2 = new File(applicationInfo.dataDir, f23381c);
            for (int i2 = 2; i2 <= i; i2++) {
                File file3 = new File(file2, str + i2 + f23380b);
                if (file3.isFile()) {
                    arrayList.add(file3.getAbsolutePath());
                } else {
                    throw new IOException("Missing extracted secondary dex file '" + file3.getPath() + "'");
                }
            }
        }
        if (ARouter.m1703c()) {
            arrayList.addAll(m668a(applicationInfo));
        }
        return arrayList;
    }

    /* renamed from: a */
    private static List<String> m668a(ApplicationInfo applicationInfo) {
        File[] listFiles;
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT < 21 || applicationInfo.splitSourceDirs == null) {
            try {
                File file = new File((String) Class.forName("com.android.tools.fd.runtime.Paths").getMethod("getDexFileDirectory", String.class).invoke(null, applicationInfo.packageName));
                if (file.exists() && file.isDirectory()) {
                    for (File file2 : file.listFiles()) {
                        if (file2 != null && file2.exists() && file2.isFile() && file2.getName().endsWith(".dex")) {
                            arrayList.add(file2.getAbsolutePath());
                        }
                    }
                    Log.d("ARouter::", "Found InstantRun support");
                }
            } catch (Exception e) {
                Log.e("ARouter::", "InstantRun support error, " + e.getMessage());
            }
        } else {
            arrayList.addAll(Arrays.asList(applicationInfo.splitSourceDirs));
            Log.d("ARouter::", "Found InstantRun support");
        }
        return arrayList;
    }

    /* renamed from: a */
    private static boolean m671a() {
        boolean z = false;
        String str = null;
        try {
            if (m667b()) {
                str = "'YunOS'";
                if (Integer.valueOf(System.getProperty("ro.build.version.sdk")).intValue() >= 21) {
                    z = true;
                }
            } else {
                str = "'Android'";
                String property = System.getProperty("java.vm.version");
                if (property != null) {
                    Matcher matcher = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?").matcher(property);
                    if (matcher.matches()) {
                        int parseInt = Integer.parseInt(matcher.group(1));
                        int parseInt2 = Integer.parseInt(matcher.group(2));
                        if (parseInt > 2 || (parseInt == 2 && parseInt2 >= 1)) {
                            z = true;
                        }
                    }
                }
            }
        } catch (NumberFormatException | Exception unused) {
        }
        StringBuilder sb = new StringBuilder();
        sb.append("VM with name ");
        sb.append(str);
        sb.append(z ? " has multidex support" : " does not have multidex support");
        Log.i("ARouter::", sb.toString());
        return z;
    }

    /* renamed from: b */
    private static boolean m667b() {
        try {
            String property = System.getProperty("ro.yunos.version");
            String property2 = System.getProperty("java.vm.name");
            if (property2 == null || !property2.toLowerCase().contains("lemur")) {
                if (property == null) {
                    return false;
                }
                if (property.trim().length() <= 0) {
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
