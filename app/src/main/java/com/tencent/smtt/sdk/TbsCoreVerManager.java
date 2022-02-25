package com.tencent.smtt.sdk;

import android.content.Context;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.tencent.smtt.sdk.j */
/* loaded from: classes2.dex */
public class TbsCoreVerManager {

    /* renamed from: a */
    private static TbsCoreVerManager f13197a;

    /* renamed from: b */
    private static Context f13198b;

    private TbsCoreVerManager() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static TbsCoreVerManager m16764a(Context context) {
        if (f13197a == null) {
            synchronized (TbsCoreVerManager.class) {
                if (f13197a == null) {
                    f13197a = new TbsCoreVerManager();
                }
            }
        }
        f13198b = context.getApplicationContext();
        return f13197a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m16765a(int i, int i2) {
        m16762a("copy_core_ver", i);
        m16762a("copy_status", i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m16758b(int i, int i2) {
        m16762a("tpatch_ver", i);
        m16762a("tpatch_status", i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public File m16767a() {
        TbsInstaller.m16742a();
        File file = new File(TbsInstaller.m16676s(f13198b), "tbscoreinstall.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return file;
    }

    /* renamed from: e */
    private Properties m16749e() {
        Throwable th;
        Properties properties;
        Exception e;
        BufferedInputStream bufferedInputStream;
        BufferedInputStream bufferedInputStream2 = null;
        try {
            try {
                File a = m16767a();
                properties = new Properties();
                if (a != null) {
                    try {
                        bufferedInputStream = new BufferedInputStream(new FileInputStream(a));
                    } catch (Exception e2) {
                        e = e2;
                    }
                    try {
                        properties.load(bufferedInputStream);
                        bufferedInputStream2 = bufferedInputStream;
                    } catch (Exception e3) {
                        e = e3;
                        bufferedInputStream2 = bufferedInputStream;
                        e.printStackTrace();
                        if (bufferedInputStream2 != null) {
                            try {
                                bufferedInputStream2.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        return properties;
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedInputStream2 = bufferedInputStream;
                        if (bufferedInputStream2 != null) {
                            try {
                                bufferedInputStream2.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
                if (bufferedInputStream2 != null) {
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                return properties;
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Exception e7) {
            e = e7;
            properties = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public int m16760b() {
        return m16753c("install_core_ver");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public int m16756c() {
        return m16757b("install_status");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m16766a(int i) {
        m16762a("dexopt_retry_num", i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m16759b(int i) {
        m16762a("unzip_retry_num", i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m16763a(String str) {
        m16761a("install_apk_path", str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public void m16754c(int i, int i2) {
        m16762a("install_core_ver", i);
        m16762a("install_status", i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public void m16755c(int i) {
        m16762a("incrupdate_status", i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public int m16752d() {
        return m16757b("incrupdate_status");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public void m16751d(int i) {
        m16762a("unlzma_status", i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public int m16757b(String str) {
        Properties e = m16749e();
        if (e == null || e.getProperty(str) == null) {
            return -1;
        }
        return Integer.parseInt(e.getProperty(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m16761a(String str, String str2) {
        Throwable th;
        FileOutputStream fileOutputStream;
        Exception e;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                try {
                    Properties e2 = m16749e();
                    if (e2 != null) {
                        e2.setProperty(str, str2);
                        File a = m16767a();
                        if (a != null) {
                            fileOutputStream = new FileOutputStream(a);
                            try {
                                e2.store(fileOutputStream, "update " + str + " and status!");
                                fileOutputStream2 = fileOutputStream;
                            } catch (Exception e3) {
                                e = e3;
                                fileOutputStream2 = fileOutputStream;
                                e.printStackTrace();
                                if (fileOutputStream2 != null) {
                                    fileOutputStream2.close();
                                }
                                return;
                            } catch (Throwable th2) {
                                th = th2;
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (IOException e4) {
                                        e4.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = null;
                }
            } catch (Exception e5) {
                e = e5;
            }
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
        } catch (IOException e6) {
            e6.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m16762a(String str, int i) {
        m16761a(str, String.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public int m16753c(String str) {
        Properties e = m16749e();
        if (e == null || e.getProperty(str) == null) {
            return 0;
        }
        return Integer.parseInt(e.getProperty(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public String m16750d(String str) {
        Properties e = m16749e();
        if (e == null || e.getProperty(str) == null) {
            return null;
        }
        return e.getProperty(str);
    }
}
