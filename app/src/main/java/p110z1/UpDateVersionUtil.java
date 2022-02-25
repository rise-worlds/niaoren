package p110z1;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.cyjh.p045mq.p049d.C1363e;
import com.tencent.smtt.sdk.TbsConfig;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

/* renamed from: z1.aqv */
/* loaded from: classes3.dex */
public class UpDateVersionUtil {
    /* renamed from: a */
    public static boolean m11433a(Context context) {
        return false;
    }

    /* renamed from: a */
    public static int m11432a(Context context, String str) {
        if (context == null || str == null || str.trim().equals("")) {
            return -1;
        }
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo.versionCode;
    }

    /* renamed from: b */
    public static String m11428b(Context context, String str) {
        PackageInfo packageInfo = null;
        if (context == null || str == null || str.trim().equals("")) {
            return null;
        }
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo.versionName;
    }

    /* renamed from: c */
    public static String m11427c(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getPackageInfo(str, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
            if (installedPackages == null || installedPackages.size() <= 0) {
                return "";
            }
            for (PackageInfo packageInfo : installedPackages) {
                if (TbsConfig.APP_WX.equals(packageInfo.packageName)) {
                    return packageInfo.versionName;
                }
            }
            return "";
        }
    }

    /* renamed from: b */
    public static String m11429b(Context context) {
        String c = m11427c(context, TbsConfig.APP_WX);
        if (c.length() >= 6) {
            c = c.substring(0, 6);
        }
        return (c == null || !c.endsWith(Consts.f23430h) || c.length() < 5) ? c : c.substring(0, 5);
    }

    /* renamed from: a */
    public static void m11430a(final String str) {
        new Thread(new Runnable() { // from class: z1.aqv.1
            @Override // java.lang.Runnable
            public void run() {
                DataOutputStream dataOutputStream;
                Process process;
                Throwable th;
                BufferedReader bufferedReader;
                String str2 = "chmod 777 " + str;
                BufferedReader bufferedReader2 = null;
                try {
                    try {
                        process = Runtime.getRuntime().exec(C1363e.f8870a);
                        try {
                            dataOutputStream = new DataOutputStream(process.getOutputStream());
                            try {
                                dataOutputStream.writeBytes(str2 + "\n");
                                dataOutputStream.writeBytes("echo return\n");
                                dataOutputStream.writeBytes(C1363e.f8872c);
                                bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                            } catch (Exception unused) {
                            } catch (Throwable th2) {
                                th = th2;
                            }
                            try {
                                StringBuilder sb = new StringBuilder();
                                while (true) {
                                    String readLine = bufferedReader.readLine();
                                    if (readLine == null) {
                                        break;
                                    }
                                    sb.append(readLine + "\n");
                                    "return".equalsIgnoreCase(readLine);
                                }
                                process.waitFor();
                                dataOutputStream.flush();
                                dataOutputStream.close();
                                bufferedReader.close();
                            } catch (Exception unused2) {
                                bufferedReader2 = bufferedReader;
                                if (dataOutputStream != null) {
                                    dataOutputStream.flush();
                                    dataOutputStream.close();
                                }
                                if (bufferedReader2 != null) {
                                    bufferedReader2.close();
                                }
                                process.destroy();
                            } catch (Throwable th3) {
                                th = th3;
                                bufferedReader2 = bufferedReader;
                                if (dataOutputStream != null) {
                                    try {
                                        dataOutputStream.flush();
                                        dataOutputStream.close();
                                    } catch (Exception unused3) {
                                        throw th;
                                    }
                                }
                                if (bufferedReader2 != null) {
                                    bufferedReader2.close();
                                }
                                process.destroy();
                                throw th;
                            }
                        } catch (Exception unused4) {
                            dataOutputStream = null;
                        } catch (Throwable th4) {
                            th = th4;
                            dataOutputStream = null;
                        }
                    } catch (Exception unused5) {
                        return;
                    }
                } catch (Exception unused6) {
                    process = null;
                    dataOutputStream = null;
                } catch (Throwable th5) {
                    th = th5;
                    process = null;
                    dataOutputStream = null;
                }
                process.destroy();
            }
        });
    }

    /* renamed from: a */
    public static boolean m11431a(File file, Context context) {
        DataOutputStream dataOutputStream;
        Process process;
        Throwable th;
        BufferedReader bufferedReader;
        StringBuilder sb;
        String readLine;
        String str = "pm install -r " + file + "\n";
        BufferedReader bufferedReader2 = null;
        try {
            process = Runtime.getRuntime().exec(C1363e.f8870a);
            try {
                dataOutputStream = new DataOutputStream(process.getOutputStream());
                try {
                    dataOutputStream.writeBytes(str + "\n");
                    dataOutputStream.writeBytes(C1363e.f8872c);
                    bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                } catch (Exception unused) {
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    sb = new StringBuilder();
                } catch (Exception unused2) {
                    bufferedReader2 = bufferedReader;
                    if (dataOutputStream != null) {
                        try {
                            dataOutputStream.flush();
                            dataOutputStream.close();
                        } catch (Exception unused3) {
                            return false;
                        }
                    }
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    process.destroy();
                    return false;
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader2 = bufferedReader;
                    if (dataOutputStream != null) {
                        try {
                            dataOutputStream.flush();
                            dataOutputStream.close();
                        } catch (Exception unused4) {
                            return false;
                        }
                    }
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    process.destroy();
                    throw th;
                }
            } catch (Exception unused5) {
                dataOutputStream = null;
            } catch (Throwable th4) {
                th = th4;
                dataOutputStream = null;
            }
        } catch (Exception unused6) {
            process = null;
            dataOutputStream = null;
        } catch (Throwable th5) {
            th = th5;
            process = null;
            dataOutputStream = null;
        }
        do {
            readLine = bufferedReader.readLine();
            if (readLine != null) {
                sb.append(readLine + "\n");
            } else {
                process.waitFor();
                try {
                    dataOutputStream.flush();
                    dataOutputStream.close();
                    bufferedReader.close();
                    process.destroy();
                    return false;
                } catch (Exception unused7) {
                    return false;
                }
            }
        } while (!"Success".equalsIgnoreCase(readLine));
        try {
            dataOutputStream.flush();
            dataOutputStream.close();
            bufferedReader.close();
            process.destroy();
            return true;
        } catch (Exception unused8) {
            return false;
        }
    }
}
