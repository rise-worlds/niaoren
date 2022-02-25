package com.kaopu.download.util;

/* loaded from: classes.dex */
public class DownloadHttpUtil {
    public static int BUFFER_SIZE = 2048;
    public static final int HTTP_REQUEST_CANCLE = 2;
    public static final int HTTP_REQUEST_NONE = 3;
    public static final int HTTP_REQUEST_PAUSE = 1;
    public static final int MAX_REQUEST_RETRY_COUNTS = 3;
    public static final int RETRY_SLEEP_TIME = 2000;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v2 */
    public static String utf8URLencode(String str) {
        if (str == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt < 0 || charAt > 255) {
                byte[] bArr = new byte[0];
                try {
                    bArr = Character.toString(charAt).getBytes("UTF-8");
                } catch (Exception unused) {
                }
                for (byte b : bArr) {
                    if (b < 0) {
                        b += 256;
                    }
                    stringBuffer.append("%" + Integer.toHexString(b == true ? 1 : 0).toUpperCase());
                }
            } else {
                stringBuffer.append(charAt);
            }
        }
        return stringBuffer.toString();
    }
}
