package com.lody.virtual.helper.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.p105io.IOUtils;
import p110z1.SimpleComparison;

/* loaded from: classes.dex */
public class PropertiesUtils {
    private static final char[] hexDigit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static boolean load(Properties properties, File file) {
        Throwable th;
        if (properties == null || file == null || !file.exists()) {
            return false;
        }
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                properties.load(fileInputStream2);
                FileUtils.closeQuietly(fileInputStream2);
                return true;
            } catch (Exception unused) {
                fileInputStream = fileInputStream2;
                FileUtils.closeQuietly(fileInputStream);
                return true;
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = fileInputStream2;
                FileUtils.closeQuietly(fileInputStream);
                throw th;
            }
        } catch (Exception unused2) {
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public static boolean save(Map map, File file, String str) {
        Throwable th;
        FileOutputStream fileOutputStream;
        if (map == null || file == null) {
            return false;
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            if (file.exists()) {
                file.delete();
            } else {
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file);
        } catch (Exception unused) {
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            store(map, fileOutputStream, str);
            FileUtils.closeQuietly(fileOutputStream);
            return true;
        } catch (Exception unused2) {
            fileOutputStream2 = fileOutputStream;
            FileUtils.closeQuietly(fileOutputStream2);
            return false;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream2 = fileOutputStream;
            FileUtils.closeQuietly(fileOutputStream2);
            throw th;
        }
    }

    private static void store(Map map, OutputStream outputStream, String str) throws IOException {
        store0(map, new BufferedWriter(new OutputStreamWriter(outputStream, "8859_1")), str, true);
    }

    private static void store0(Map map, BufferedWriter bufferedWriter, String str, boolean z) throws IOException {
        bufferedWriter.newLine();
        if (str != null) {
            writeComments(bufferedWriter, str);
        }
        synchronized (map) {
            for (Object obj : map.keySet()) {
                String valueOf = String.valueOf(obj);
                String valueOf2 = String.valueOf(map.get(obj));
                String saveConvert = saveConvert(valueOf, true, z);
                String saveConvert2 = saveConvert(valueOf2, false, z);
                bufferedWriter.write(saveConvert + SimpleComparison.f23609c + saveConvert2);
                bufferedWriter.newLine();
            }
        }
        bufferedWriter.flush();
    }

    private static char toHex(int i) {
        return hexDigit[i & 15];
    }

    private static String saveConvert(String str, boolean z, boolean z2) {
        int length = str.length();
        int i = length * 2;
        if (i < 0) {
            i = Integer.MAX_VALUE;
        }
        StringBuffer stringBuffer = new StringBuffer(i);
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt <= '=' || charAt >= 127) {
                switch (charAt) {
                    case '\t':
                        stringBuffer.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                        stringBuffer.append('t');
                        continue;
                    case '\n':
                        stringBuffer.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                        stringBuffer.append('n');
                        continue;
                    case '\f':
                        stringBuffer.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                        stringBuffer.append('f');
                        continue;
                    case '\r':
                        stringBuffer.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                        stringBuffer.append('r');
                        continue;
                    case ' ':
                        if (i2 == 0 || z) {
                            stringBuffer.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                        }
                        stringBuffer.append(' ');
                        continue;
                    case '!':
                    case '#':
                    case ':':
                    case '=':
                        stringBuffer.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                        stringBuffer.append(charAt);
                        continue;
                    default:
                        if ((charAt < ' ' || charAt > '~') && z2) {
                            stringBuffer.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                            stringBuffer.append('u');
                            stringBuffer.append(toHex((charAt >> '\f') & 15));
                            stringBuffer.append(toHex((charAt >> '\b') & 15));
                            stringBuffer.append(toHex((charAt >> 4) & 15));
                            stringBuffer.append(toHex(charAt & 15));
                            break;
                        } else {
                            stringBuffer.append(charAt);
                            continue;
                        }
                        break;
                }
            } else if (charAt == '\\') {
                stringBuffer.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                stringBuffer.append(IOUtils.DIR_SEPARATOR_WINDOWS);
            } else {
                stringBuffer.append(charAt);
            }
        }
        return stringBuffer.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x008d, code lost:
        if (r10.charAt(r4) != '!') goto L_0x008f;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void writeComments(java.io.BufferedWriter r9, java.lang.String r10) throws java.io.IOException {
        /*
            java.lang.String r0 = "#"
            r9.write(r0)
            int r0 = r10.length()
            r1 = 6
            char[] r1 = new char[r1]
            r2 = 0
            r3 = 92
            r1[r2] = r3
            r3 = 1
            r4 = 117(0x75, float:1.64E-43)
            r1[r3] = r4
            r4 = 0
        L_0x0017:
            if (r2 >= r0) goto L_0x0099
            char r5 = r10.charAt(r2)
            r6 = 13
            r7 = 10
            r8 = 255(0xff, float:3.57E-43)
            if (r5 > r8) goto L_0x0029
            if (r5 == r7) goto L_0x0029
            if (r5 != r6) goto L_0x0096
        L_0x0029:
            if (r4 == r2) goto L_0x0032
            java.lang.String r4 = r10.substring(r4, r2)
            r9.write(r4)
        L_0x0032:
            if (r5 <= r8) goto L_0x0067
            r4 = 2
            int r6 = r5 >> 12
            r6 = r6 & 15
            char r6 = toHex(r6)
            r1[r4] = r6
            r4 = 3
            int r6 = r5 >> 8
            r6 = r6 & 15
            char r6 = toHex(r6)
            r1[r4] = r6
            int r4 = r5 >> 4
            r4 = r4 & 15
            char r4 = toHex(r4)
            r6 = 4
            r1[r6] = r4
            r4 = 5
            r5 = r5 & 15
            char r5 = toHex(r5)
            r1[r4] = r5
            java.lang.String r4 = new java.lang.String
            r4.<init>(r1)
            r9.write(r4)
            goto L_0x0094
        L_0x0067:
            r9.newLine()
            if (r5 != r6) goto L_0x0079
            int r4 = r0 + (-1)
            if (r2 == r4) goto L_0x0079
            int r4 = r2 + 1
            char r5 = r10.charAt(r4)
            if (r5 != r7) goto L_0x0079
            r2 = r4
        L_0x0079:
            int r4 = r0 + (-1)
            if (r2 == r4) goto L_0x008f
            int r4 = r2 + 1
            char r5 = r10.charAt(r4)
            r6 = 35
            if (r5 == r6) goto L_0x0094
            char r4 = r10.charAt(r4)
            r5 = 33
            if (r4 == r5) goto L_0x0094
        L_0x008f:
            java.lang.String r4 = "#"
            r9.write(r4)
        L_0x0094:
            int r4 = r2 + 1
        L_0x0096:
            int r2 = r2 + r3
            goto L_0x0017
        L_0x0099:
            if (r4 == r2) goto L_0x00a2
            java.lang.String r10 = r10.substring(r4, r2)
            r9.write(r10)
        L_0x00a2:
            r9.newLine()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lody.virtual.helper.utils.PropertiesUtils.writeComments(java.io.BufferedWriter, java.lang.String):void");
    }
}
