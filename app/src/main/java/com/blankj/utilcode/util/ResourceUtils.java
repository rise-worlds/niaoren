package com.blankj.utilcode.util;

import android.support.annotation.RawRes;
import com.liulishuo.filedownloader.model.ConnectionModel;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/* renamed from: com.blankj.utilcode.util.ao */
/* loaded from: classes.dex */
public final class ResourceUtils {

    /* renamed from: a */
    private static final int f6704a = 8192;

    private ResourceUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static int m23465a(String str) {
        return Utils.m24103a().getResources().getIdentifier(str, ConnectionModel.f10389a, Utils.m24103a().getPackageName());
    }

    /* renamed from: b */
    public static int m23459b(String str) {
        return Utils.m24103a().getResources().getIdentifier(str, "string", Utils.m24103a().getPackageName());
    }

    /* renamed from: c */
    public static int m23456c(String str) {
        return Utils.m24103a().getResources().getIdentifier(str, "color", Utils.m24103a().getPackageName());
    }

    /* renamed from: d */
    public static int m23454d(String str) {
        return Utils.m24103a().getResources().getIdentifier(str, "dimen", Utils.m24103a().getPackageName());
    }

    /* renamed from: e */
    public static int m23453e(String str) {
        return Utils.m24103a().getResources().getIdentifier(str, "drawable", Utils.m24103a().getPackageName());
    }

    /* renamed from: f */
    public static int m23452f(String str) {
        return Utils.m24103a().getResources().getIdentifier(str, "mipmap", Utils.m24103a().getPackageName());
    }

    /* renamed from: g */
    public static int m23451g(String str) {
        return Utils.m24103a().getResources().getIdentifier(str, "layout", Utils.m24103a().getPackageName());
    }

    /* renamed from: h */
    public static int m23450h(String str) {
        return Utils.m24103a().getResources().getIdentifier(str, "style", Utils.m24103a().getPackageName());
    }

    /* renamed from: i */
    public static int m23449i(String str) {
        return Utils.m24103a().getResources().getIdentifier(str, "anim", Utils.m24103a().getPackageName());
    }

    /* renamed from: j */
    public static int m23448j(String str) {
        return Utils.m24103a().getResources().getIdentifier(str, "menu", Utils.m24103a().getPackageName());
    }

    /* renamed from: a */
    public static boolean m23463a(String str, String str2) {
        try {
            String[] list = Utils.m24103a().getAssets().list(str);
            if (list.length <= 0) {
                return m23464a(str2, Utils.m24103a().getAssets().open(str), false);
            }
            boolean z = true;
            for (String str3 : list) {
                z &= m23463a(str + "/" + str3, str2 + "/" + str3);
            }
            return z;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: k */
    public static String m23447k(String str) {
        return m23458b(str, (String) null);
    }

    /* renamed from: b */
    public static String m23458b(String str, String str2) {
        try {
            byte[] a = m23467a(Utils.m24103a().getAssets().open(str));
            if (a == null) {
                return null;
            }
            if (m23444n(str2)) {
                return new String(a);
            }
            try {
                return new String(a, str2);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return "";
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* renamed from: l */
    public static List<String> m23446l(String str) {
        return m23455c(str, (String) null);
    }

    /* renamed from: c */
    public static List<String> m23455c(String str, String str2) {
        try {
            return m23466a(Utils.m24103a().getResources().getAssets().open(str), str2);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m23470a(@RawRes int i, String str) {
        return m23464a(str, Utils.m24103a().getResources().openRawResource(i), false);
    }

    /* renamed from: a */
    public static String m23471a(@RawRes int i) {
        return m23461b(i, (String) null);
    }

    /* renamed from: b */
    public static String m23461b(@RawRes int i, String str) {
        byte[] a = m23467a(Utils.m24103a().getResources().openRawResource(i));
        if (a == null) {
            return null;
        }
        if (m23444n(str)) {
            return new String(a);
        }
        try {
            return new String(a, str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: b */
    public static List<String> m23462b(@RawRes int i) {
        return m23457c(i, (String) null);
    }

    /* renamed from: c */
    public static List<String> m23457c(@RawRes int i, String str) {
        return m23466a(Utils.m24103a().getResources().openRawResource(i), str);
    }

    /* renamed from: a */
    private static boolean m23464a(String str, InputStream inputStream, boolean z) {
        return m23468a(m23445m(str), inputStream, z);
    }

    /* renamed from: a */
    private static boolean m23468a(File file, InputStream inputStream, boolean z) {
        Throwable th;
        IOException e;
        BufferedOutputStream bufferedOutputStream;
        if (!m23469a(file) || inputStream == null) {
            return false;
        }
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file, z));
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e2) {
            e = e2;
        }
        try {
            byte[] bArr = new byte[8192];
            while (true) {
                int read = inputStream.read(bArr, 0, 8192);
                if (read == -1) {
                    break;
                }
                bufferedOutputStream.write(bArr, 0, read);
            }
            try {
                inputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            try {
                bufferedOutputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            return true;
        } catch (IOException e5) {
            e = e5;
            bufferedOutputStream2 = bufferedOutputStream;
            e.printStackTrace();
            try {
                inputStream.close();
            } catch (IOException e6) {
                e6.printStackTrace();
            }
            if (bufferedOutputStream2 != null) {
                try {
                    bufferedOutputStream2.close();
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
            }
            return false;
        } catch (Throwable th3) {
            th = th3;
            bufferedOutputStream2 = bufferedOutputStream;
            try {
                inputStream.close();
            } catch (IOException e8) {
                e8.printStackTrace();
            }
            if (bufferedOutputStream2 != null) {
                try {
                    bufferedOutputStream2.close();
                } catch (IOException e9) {
                    e9.printStackTrace();
                }
            }
            throw th;
        }
    }

    /* renamed from: m */
    private static File m23445m(String str) {
        if (m23444n(str)) {
            return null;
        }
        return new File(str);
    }

    /* renamed from: a */
    private static boolean m23469a(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!m23460b(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: b */
    private static boolean m23460b(File file) {
        return file != null && (!file.exists() ? file.mkdirs() : file.isDirectory());
    }

    /* renamed from: n */
    private static boolean m23444n(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    private static byte[] m23467a(InputStream inputStream) {
        Throwable th;
        IOException e;
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            if (inputStream == null) {
                return null;
            }
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int read = inputStream.read(bArr, 0, 8192);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    return byteArray;
                } catch (IOException e4) {
                    e = e4;
                    e.printStackTrace();
                    try {
                        inputStream.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                    }
                    return null;
                }
            } catch (IOException e7) {
                e = e7;
                byteArrayOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                try {
                    inputStream.close();
                } catch (IOException e8) {
                    e8.printStackTrace();
                }
                if (0 != 0) {
                    try {
                        byteArrayOutputStream2.close();
                    } catch (IOException e9) {
                        e9.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x004f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.util.List<java.lang.String> m23466a(java.io.InputStream r4, java.lang.String r5) {
        /*
            r0 = 0
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: all -> 0x0039, IOException -> 0x003b
            r1.<init>()     // Catch: all -> 0x0039, IOException -> 0x003b
            boolean r2 = m23444n(r5)     // Catch: all -> 0x0039, IOException -> 0x003b
            if (r2 == 0) goto L_0x0017
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch: all -> 0x0039, IOException -> 0x003b
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: all -> 0x0039, IOException -> 0x003b
            r2.<init>(r4)     // Catch: all -> 0x0039, IOException -> 0x003b
            r5.<init>(r2)     // Catch: all -> 0x0039, IOException -> 0x003b
            goto L_0x0022
        L_0x0017:
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: all -> 0x0039, IOException -> 0x003b
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: all -> 0x0039, IOException -> 0x003b
            r3.<init>(r4, r5)     // Catch: all -> 0x0039, IOException -> 0x003b
            r2.<init>(r3)     // Catch: all -> 0x0039, IOException -> 0x003b
            r5 = r2
        L_0x0022:
            java.lang.String r4 = r5.readLine()     // Catch: IOException -> 0x0037, all -> 0x004b
            if (r4 == 0) goto L_0x002c
            r1.add(r4)     // Catch: IOException -> 0x0037, all -> 0x004b
            goto L_0x0022
        L_0x002c:
            if (r5 == 0) goto L_0x0036
            r5.close()     // Catch: IOException -> 0x0032
            goto L_0x0036
        L_0x0032:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0036:
            return r1
        L_0x0037:
            r4 = move-exception
            goto L_0x003d
        L_0x0039:
            r4 = move-exception
            goto L_0x004d
        L_0x003b:
            r4 = move-exception
            r5 = r0
        L_0x003d:
            r4.printStackTrace()     // Catch: all -> 0x004b
            if (r5 == 0) goto L_0x004a
            r5.close()     // Catch: IOException -> 0x0046
            goto L_0x004a
        L_0x0046:
            r4 = move-exception
            r4.printStackTrace()
        L_0x004a:
            return r0
        L_0x004b:
            r4 = move-exception
            r0 = r5
        L_0x004d:
            if (r0 == 0) goto L_0x0057
            r0.close()     // Catch: IOException -> 0x0053
            goto L_0x0057
        L_0x0053:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0057:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.ResourceUtils.m23466a(java.io.InputStream, java.lang.String):java.util.List");
    }
}
