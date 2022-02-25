package com.blankj.utilcode.util;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

/* renamed from: com.blankj.utilcode.util.v */
/* loaded from: classes.dex */
public final class FileIOUtils {

    /* renamed from: a */
    private static int f6910a = 524288;

    private FileIOUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static boolean m22285a(String str, InputStream inputStream) {
        return m22295a(m22259f(str), inputStream, false);
    }

    /* renamed from: a */
    public static boolean m22284a(String str, InputStream inputStream, boolean z) {
        return m22295a(m22259f(str), inputStream, z);
    }

    /* renamed from: a */
    public static boolean m22296a(File file, InputStream inputStream) {
        return m22295a(file, inputStream, false);
    }

    /* renamed from: a */
    public static boolean m22295a(File file, InputStream inputStream, boolean z) {
        Throwable th;
        IOException e;
        BufferedOutputStream bufferedOutputStream;
        if (!m22260f(file) || inputStream == null) {
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
            byte[] bArr = new byte[f6910a];
            while (true) {
                int read = inputStream.read(bArr);
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

    /* renamed from: a */
    public static boolean m22281a(String str, byte[] bArr) {
        return m22291a(m22259f(str), bArr, false);
    }

    /* renamed from: a */
    public static boolean m22280a(String str, byte[] bArr, boolean z) {
        return m22291a(m22259f(str), bArr, z);
    }

    /* renamed from: a */
    public static boolean m22292a(File file, byte[] bArr) {
        return m22291a(file, bArr, false);
    }

    /* renamed from: a */
    public static boolean m22291a(File file, byte[] bArr, boolean z) {
        Throwable th;
        IOException e;
        BufferedOutputStream bufferedOutputStream;
        if (bArr == null || !m22260f(file)) {
            return false;
        }
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file, z));
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            bufferedOutputStream.write(bArr);
            try {
                bufferedOutputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            return true;
        } catch (IOException e4) {
            e = e4;
            bufferedOutputStream2 = bufferedOutputStream;
            e.printStackTrace();
            if (bufferedOutputStream2 != null) {
                try {
                    bufferedOutputStream2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            return false;
        } catch (Throwable th3) {
            th = th3;
            bufferedOutputStream2 = bufferedOutputStream;
            if (bufferedOutputStream2 != null) {
                try {
                    bufferedOutputStream2.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            throw th;
        }
    }

    /* renamed from: b */
    public static boolean m22272b(String str, byte[] bArr, boolean z) {
        return m22290a(m22259f(str), bArr, false, z);
    }

    /* renamed from: a */
    public static boolean m22279a(String str, byte[] bArr, boolean z, boolean z2) {
        return m22290a(m22259f(str), bArr, z, z2);
    }

    /* renamed from: b */
    public static boolean m22276b(File file, byte[] bArr, boolean z) {
        return m22290a(file, bArr, false, z);
    }

    /* renamed from: a */
    public static boolean m22290a(File file, byte[] bArr, boolean z, boolean z2) {
        if (bArr == null) {
            return false;
        }
        FileChannel fileChannel = null;
        try {
            try {
                fileChannel = new FileOutputStream(file, z).getChannel();
                fileChannel.position(fileChannel.size());
                fileChannel.write(ByteBuffer.wrap(bArr));
                if (z2) {
                    fileChannel.force(true);
                }
                if (fileChannel != null) {
                    try {
                        fileChannel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return true;
            } catch (IOException e2) {
                e2.printStackTrace();
                if (fileChannel != null) {
                    try {
                        fileChannel.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                return false;
            }
        } catch (Throwable th) {
            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
    }

    /* renamed from: c */
    public static boolean m22265c(String str, byte[] bArr, boolean z) {
        return m22271b(str, bArr, false, z);
    }

    /* renamed from: b */
    public static boolean m22271b(String str, byte[] bArr, boolean z, boolean z2) {
        return m22275b(m22259f(str), bArr, z, z2);
    }

    /* renamed from: c */
    public static boolean m22268c(File file, byte[] bArr, boolean z) {
        return m22275b(file, bArr, false, z);
    }

    /* renamed from: b */
    public static boolean m22275b(File file, byte[] bArr, boolean z, boolean z2) {
        Throwable th;
        FileChannel fileChannel;
        IOException e;
        if (bArr == null || !m22260f(file)) {
            return false;
        }
        FileChannel fileChannel2 = null;
        try {
            try {
                fileChannel = new FileOutputStream(file, z).getChannel();
            } catch (Throwable th2) {
                th = th2;
                fileChannel = null;
            }
        } catch (IOException e2) {
            e = e2;
        }
        try {
            MappedByteBuffer map = fileChannel.map(FileChannel.MapMode.READ_WRITE, fileChannel.size(), bArr.length);
            map.put(bArr);
            if (z2) {
                map.force();
            }
            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            return true;
        } catch (IOException e4) {
            e = e4;
            fileChannel2 = fileChannel;
            e.printStackTrace();
            if (fileChannel2 != null) {
                try {
                    fileChannel2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            return false;
        } catch (Throwable th3) {
            th = th3;
            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            throw th;
        }
    }

    /* renamed from: a */
    public static boolean m22283a(String str, String str2) {
        return m22293a(m22259f(str), str2, false);
    }

    /* renamed from: a */
    public static boolean m22282a(String str, String str2, boolean z) {
        return m22293a(m22259f(str), str2, z);
    }

    /* renamed from: a */
    public static boolean m22294a(File file, String str) {
        return m22293a(file, str, false);
    }

    /* renamed from: a */
    public static boolean m22293a(File file, String str, boolean z) {
        Throwable th;
        IOException e;
        BufferedWriter bufferedWriter;
        if (file == null || str == null || !m22260f(file)) {
            return false;
        }
        BufferedWriter bufferedWriter2 = null;
        try {
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(file, z));
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e2) {
            e = e2;
        }
        try {
            bufferedWriter.write(str);
            try {
                bufferedWriter.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            return true;
        } catch (IOException e4) {
            e = e4;
            bufferedWriter2 = bufferedWriter;
            e.printStackTrace();
            if (bufferedWriter2 != null) {
                try {
                    bufferedWriter2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            return false;
        } catch (Throwable th3) {
            th = th3;
            bufferedWriter2 = bufferedWriter;
            if (bufferedWriter2 != null) {
                try {
                    bufferedWriter2.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            throw th;
        }
    }

    /* renamed from: a */
    public static List<String> m22288a(String str) {
        return m22277b(m22259f(str), (String) null);
    }

    /* renamed from: b */
    public static List<String> m22273b(String str, String str2) {
        return m22277b(m22259f(str), str2);
    }

    /* renamed from: a */
    public static List<String> m22299a(File file) {
        return m22297a(file, 0, Integer.MAX_VALUE, (String) null);
    }

    /* renamed from: b */
    public static List<String> m22277b(File file, String str) {
        return m22297a(file, 0, Integer.MAX_VALUE, str);
    }

    /* renamed from: a */
    public static List<String> m22287a(String str, int i, int i2) {
        return m22297a(m22259f(str), i, i2, (String) null);
    }

    /* renamed from: a */
    public static List<String> m22286a(String str, int i, int i2, String str2) {
        return m22297a(m22259f(str), i, i2, str2);
    }

    /* renamed from: a */
    public static List<String> m22298a(File file, int i, int i2) {
        return m22297a(file, i, i2, (String) null);
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x006d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<java.lang.String> m22297a(java.io.File r6, int r7, int r8, java.lang.String r9) {
        /*
            boolean r0 = m22256h(r6)
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            if (r7 <= r8) goto L_0x000b
            return r1
        L_0x000b:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch: all -> 0x0057, IOException -> 0x0059
            r0.<init>()     // Catch: all -> 0x0057, IOException -> 0x0059
            boolean r2 = m22255h(r9)     // Catch: all -> 0x0057, IOException -> 0x0059
            r3 = 1
            if (r2 == 0) goto L_0x0027
            java.io.BufferedReader r9 = new java.io.BufferedReader     // Catch: all -> 0x0057, IOException -> 0x0059
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: all -> 0x0057, IOException -> 0x0059
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: all -> 0x0057, IOException -> 0x0059
            r4.<init>(r6)     // Catch: all -> 0x0057, IOException -> 0x0059
            r2.<init>(r4)     // Catch: all -> 0x0057, IOException -> 0x0059
            r9.<init>(r2)     // Catch: all -> 0x0057, IOException -> 0x0059
            goto L_0x0037
        L_0x0027:
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: all -> 0x0057, IOException -> 0x0059
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: all -> 0x0057, IOException -> 0x0059
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: all -> 0x0057, IOException -> 0x0059
            r5.<init>(r6)     // Catch: all -> 0x0057, IOException -> 0x0059
            r4.<init>(r5, r9)     // Catch: all -> 0x0057, IOException -> 0x0059
            r2.<init>(r4)     // Catch: all -> 0x0057, IOException -> 0x0059
            r9 = r2
        L_0x0037:
            java.lang.String r6 = r9.readLine()     // Catch: IOException -> 0x0055, all -> 0x0069
            if (r6 == 0) goto L_0x004a
            if (r3 <= r8) goto L_0x0040
            goto L_0x004a
        L_0x0040:
            if (r7 > r3) goto L_0x0047
            if (r3 > r8) goto L_0x0047
            r0.add(r6)     // Catch: IOException -> 0x0055, all -> 0x0069
        L_0x0047:
            int r3 = r3 + 1
            goto L_0x0037
        L_0x004a:
            if (r9 == 0) goto L_0x0054
            r9.close()     // Catch: IOException -> 0x0050
            goto L_0x0054
        L_0x0050:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0054:
            return r0
        L_0x0055:
            r6 = move-exception
            goto L_0x005b
        L_0x0057:
            r6 = move-exception
            goto L_0x006b
        L_0x0059:
            r6 = move-exception
            r9 = r1
        L_0x005b:
            r6.printStackTrace()     // Catch: all -> 0x0069
            if (r9 == 0) goto L_0x0068
            r9.close()     // Catch: IOException -> 0x0064
            goto L_0x0068
        L_0x0064:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0068:
            return r1
        L_0x0069:
            r6 = move-exception
            r1 = r9
        L_0x006b:
            if (r1 == 0) goto L_0x0075
            r1.close()     // Catch: IOException -> 0x0071
            goto L_0x0075
        L_0x0071:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0075:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.FileIOUtils.m22297a(java.io.File, int, int, java.lang.String):java.util.List");
    }

    /* renamed from: b */
    public static String m22274b(String str) {
        return m22269c(m22259f(str), (String) null);
    }

    /* renamed from: c */
    public static String m22266c(String str, String str2) {
        return m22269c(m22259f(str), str2);
    }

    /* renamed from: b */
    public static String m22278b(File file) {
        return m22269c(file, (String) null);
    }

    /* renamed from: c */
    public static String m22269c(File file, String str) {
        byte[] c = m22270c(file);
        if (c == null) {
            return null;
        }
        if (m22255h(str)) {
            return new String(c);
        }
        try {
            return new String(c, str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: c */
    public static byte[] m22267c(String str) {
        return m22270c(m22259f(str));
    }

    /* renamed from: c */
    public static byte[] m22270c(File file) {
        if (!m22256h(file)) {
            return null;
        }
        try {
            return m22289a(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: d */
    public static byte[] m22263d(String str) {
        return m22264d(m22259f(str));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* renamed from: d */
    public static byte[] m22264d(File file) {
        Throwable th;
        FileChannel fileChannel;
        IOException e;
        try {
            if (!m22256h(file)) {
                return null;
            }
            try {
                fileChannel = new RandomAccessFile(file, "r").getChannel();
                try {
                    ByteBuffer allocate = ByteBuffer.allocate((int) fileChannel.size());
                    do {
                    } while (fileChannel.read(allocate) > 0);
                    byte[] array = allocate.array();
                    if (fileChannel != null) {
                        try {
                            fileChannel.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    return array;
                } catch (IOException e3) {
                    e = e3;
                    e.printStackTrace();
                    if (fileChannel != null) {
                        try {
                            fileChannel.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    return null;
                }
            } catch (IOException e5) {
                e = e5;
                fileChannel = null;
            } catch (Throwable th2) {
                th = th2;
                file = 0;
                if (file != 0) {
                    try {
                        file.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* renamed from: e */
    public static byte[] m22261e(String str) {
        return m22262e(m22259f(str));
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x004f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] m22262e(java.io.File r9) {
        /*
            boolean r0 = m22256h(r9)
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            java.io.RandomAccessFile r0 = new java.io.RandomAccessFile     // Catch: all -> 0x0039, IOException -> 0x003c
            java.lang.String r2 = "r"
            r0.<init>(r9, r2)     // Catch: all -> 0x0039, IOException -> 0x003c
            java.nio.channels.FileChannel r9 = r0.getChannel()     // Catch: all -> 0x0039, IOException -> 0x003c
            long r2 = r9.size()     // Catch: IOException -> 0x0037, all -> 0x004c
            int r0 = (int) r2     // Catch: IOException -> 0x0037, all -> 0x004c
            java.nio.channels.FileChannel$MapMode r4 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch: IOException -> 0x0037, all -> 0x004c
            r5 = 0
            long r7 = (long) r0     // Catch: IOException -> 0x0037, all -> 0x004c
            r3 = r9
            java.nio.MappedByteBuffer r2 = r3.map(r4, r5, r7)     // Catch: IOException -> 0x0037, all -> 0x004c
            java.nio.MappedByteBuffer r2 = r2.load()     // Catch: IOException -> 0x0037, all -> 0x004c
            byte[] r3 = new byte[r0]     // Catch: IOException -> 0x0037, all -> 0x004c
            r4 = 0
            r2.get(r3, r4, r0)     // Catch: IOException -> 0x0037, all -> 0x004c
            if (r9 == 0) goto L_0x0036
            r9.close()     // Catch: IOException -> 0x0032
            goto L_0x0036
        L_0x0032:
            r9 = move-exception
            r9.printStackTrace()
        L_0x0036:
            return r3
        L_0x0037:
            r0 = move-exception
            goto L_0x003e
        L_0x0039:
            r0 = move-exception
            r9 = r1
            goto L_0x004d
        L_0x003c:
            r0 = move-exception
            r9 = r1
        L_0x003e:
            r0.printStackTrace()     // Catch: all -> 0x004c
            if (r9 == 0) goto L_0x004b
            r9.close()     // Catch: IOException -> 0x0047
            goto L_0x004b
        L_0x0047:
            r9 = move-exception
            r9.printStackTrace()
        L_0x004b:
            return r1
        L_0x004c:
            r0 = move-exception
        L_0x004d:
            if (r9 == 0) goto L_0x0057
            r9.close()     // Catch: IOException -> 0x0053
            goto L_0x0057
        L_0x0053:
            r9 = move-exception
            r9.printStackTrace()
        L_0x0057:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.FileIOUtils.m22262e(java.io.File):byte[]");
    }

    /* renamed from: a */
    public static void m22300a(int i) {
        f6910a = i;
    }

    /* renamed from: f */
    private static File m22259f(String str) {
        if (m22255h(str)) {
            return null;
        }
        return new File(str);
    }

    /* renamed from: g */
    private static boolean m22257g(String str) {
        return m22260f(m22259f(str));
    }

    /* renamed from: f */
    private static boolean m22260f(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!m22258g(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: g */
    private static boolean m22258g(File file) {
        return file != null && (!file.exists() ? file.mkdirs() : file.isDirectory());
    }

    /* renamed from: h */
    private static boolean m22256h(File file) {
        return file != null && file.exists();
    }

    /* renamed from: h */
    private static boolean m22255h(String str) {
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

    /* JADX WARN: Removed duplicated region for block: B:51:0x005a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static byte[] m22289a(java.io.InputStream r7) {
        /*
            r0 = 0
            if (r7 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: all -> 0x0032, IOException -> 0x0037
            r1.<init>()     // Catch: all -> 0x0032, IOException -> 0x0037
            int r2 = com.blankj.utilcode.util.FileIOUtils.f6910a     // Catch: IOException -> 0x0030, all -> 0x004f
            byte[] r2 = new byte[r2]     // Catch: IOException -> 0x0030, all -> 0x004f
        L_0x000d:
            int r3 = com.blankj.utilcode.util.FileIOUtils.f6910a     // Catch: IOException -> 0x0030, all -> 0x004f
            r4 = 0
            int r3 = r7.read(r2, r4, r3)     // Catch: IOException -> 0x0030, all -> 0x004f
            r5 = -1
            if (r3 == r5) goto L_0x001b
            r1.write(r2, r4, r3)     // Catch: IOException -> 0x0030, all -> 0x004f
            goto L_0x000d
        L_0x001b:
            byte[] r0 = r1.toByteArray()     // Catch: IOException -> 0x0030, all -> 0x004f
            r7.close()     // Catch: IOException -> 0x0023
            goto L_0x0027
        L_0x0023:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0027:
            r1.close()     // Catch: IOException -> 0x002b
            goto L_0x002f
        L_0x002b:
            r7 = move-exception
            r7.printStackTrace()
        L_0x002f:
            return r0
        L_0x0030:
            r2 = move-exception
            goto L_0x0039
        L_0x0032:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x0050
        L_0x0037:
            r2 = move-exception
            r1 = r0
        L_0x0039:
            r2.printStackTrace()     // Catch: all -> 0x004f
            r7.close()     // Catch: IOException -> 0x0040
            goto L_0x0044
        L_0x0040:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0044:
            if (r1 == 0) goto L_0x004e
            r1.close()     // Catch: IOException -> 0x004a
            goto L_0x004e
        L_0x004a:
            r7 = move-exception
            r7.printStackTrace()
        L_0x004e:
            return r0
        L_0x004f:
            r0 = move-exception
        L_0x0050:
            r7.close()     // Catch: IOException -> 0x0054
            goto L_0x0058
        L_0x0054:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0058:
            if (r1 == 0) goto L_0x0062
            r1.close()     // Catch: IOException -> 0x005e
            goto L_0x0062
        L_0x005e:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0062:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.FileIOUtils.m22289a(java.io.InputStream):byte[]");
    }
}
