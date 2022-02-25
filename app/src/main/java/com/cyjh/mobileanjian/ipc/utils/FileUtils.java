package com.cyjh.mobileanjian.ipc.utils;

import android.content.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public final class FileUtils {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v18 */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r7v8 */
    public static boolean copyRawFile(Context context, int i, String str) {
        Throwable th;
        FileNotFoundException e;
        IOException e2;
        InputStream inputStream = null;
        boolean z = false;
        try {
            try {
                try {
                    File file = new File(str);
                    if (!file.exists()) {
                        file.createNewFile();
                    } else if (file.isDirectory()) {
                        file = new File(str + File.separator + context.getResources().getResourceName(i));
                    }
                    byte[] bArr = new byte[4096];
                    InputStream openRawResource = context.getResources().openRawResource(i);
                    try {
                        i = new FileOutputStream(file);
                        while (true) {
                            try {
                                int read = openRawResource.read(bArr);
                                if (read <= 0) {
                                    break;
                                }
                                i.write(bArr, 0, read);
                            } catch (FileNotFoundException e3) {
                                e = e3;
                                inputStream = openRawResource;
                                i = i;
                                e.printStackTrace();
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                if (i != 0) {
                                    i.close();
                                }
                                return z;
                            } catch (IOException e4) {
                                e2 = e4;
                                inputStream = openRawResource;
                                i = i;
                                e2.printStackTrace();
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                if (i != 0) {
                                    i.close();
                                }
                                return z;
                            } catch (Throwable th2) {
                                th = th2;
                                inputStream = openRawResource;
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e5) {
                                        e5.printStackTrace();
                                        throw th;
                                    }
                                }
                                if (i != 0) {
                                    i.close();
                                }
                                throw th;
                            }
                        }
                        z = true;
                        if (openRawResource != null) {
                            openRawResource.close();
                        }
                        i.close();
                    } catch (FileNotFoundException e6) {
                        e = e6;
                        inputStream = openRawResource;
                        i = 0;
                    } catch (IOException e7) {
                        e2 = e7;
                        inputStream = openRawResource;
                        i = 0;
                    } catch (Throwable th3) {
                        th = th3;
                        inputStream = openRawResource;
                        i = 0;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            } catch (FileNotFoundException e8) {
                e = e8;
                i = 0;
            } catch (IOException e9) {
                e2 = e9;
                i = 0;
            } catch (Throwable th5) {
                th = th5;
                i = 0;
            }
        } catch (IOException e10) {
            e10.printStackTrace();
        }
        return z;
    }

    public static void copyAssetsFile(Context context, String str, String str2) {
        try {
            InputStream open = context.getAssets().open(str);
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            byte[] bArr = new byte[4096];
            while (true) {
                int read = open.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    open.close();
                    fileOutputStream.close();
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private static String m20676a(String str) {
        if (str == null || str.trim().equals("")) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(str);
        while (stringBuffer.charAt(0) == File.separatorChar) {
            stringBuffer.deleteCharAt(0);
            if (stringBuffer.length() == 0) {
                return null;
            }
        }
        while (stringBuffer.charAt(stringBuffer.length() - 1) == File.separator.charAt(0)) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            if (stringBuffer.length() == 0) {
                return null;
            }
        }
        return stringBuffer.toString();
    }

    public static String makeAbsolutePath(String str, String... strArr) {
        if (str == null) {
            return null;
        }
        for (String str2 : strArr) {
            if (str2 == null) {
                return null;
            }
        }
        String str3 = File.separator;
        StringBuffer stringBuffer = new StringBuffer();
        if (!str.startsWith(str3)) {
            stringBuffer.append(str3);
        }
        stringBuffer.append(str);
        if (!str.endsWith(str3)) {
            stringBuffer.append(str3);
        }
        for (String str4 : strArr) {
            String a = m20676a(str4);
            if (a != null) {
                stringBuffer.append(a);
                stringBuffer.append(str3);
            }
        }
        if (stringBuffer.lastIndexOf(str3) == stringBuffer.length() - 1) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        return stringBuffer.toString();
    }

    public static void writeStringToFile(File file, String str) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getBaseFilePath(String str) {
        int lastIndexOf;
        return (str == null || str.length() <= 0 || (lastIndexOf = str.lastIndexOf(46)) < 0 || lastIndexOf >= str.length()) ? str : str.substring(0, lastIndexOf);
    }

    public static void recursionDeleteFiles(String str) {
        File[] listFiles;
        File file = new File(str);
        if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    recursionDeleteFiles(file2.getAbsolutePath());
                }
                file2.delete();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00f0 A[Catch: IOException -> 0x00ec, TRY_LEAVE, TryCatch #5 {IOException -> 0x00ec, blocks: (B:66:0x00e8, B:70:0x00f0), top: B:81:0x00e8 }] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00e8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.nio.channels.FileChannel] */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.nio.channels.FileChannel] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final int moveFiles(java.lang.String r16, java.lang.String r17, boolean r18) {
        /*
            Method dump skipped, instructions count: 253
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cyjh.mobileanjian.ipc.utils.FileUtils.moveFiles(java.lang.String, java.lang.String, boolean):int");
    }

    public static String file2Text(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        File file = new File(str);
        try {
            byte[] bArr = new byte[1024];
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                stringBuffer.append(new String(bArr, 0, read, str2));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return stringBuffer.toString();
    }
}
