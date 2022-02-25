package org.apache.commons.mail;

import com.tencent.smtt.sdk.TbsListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.BitSet;
import java.util.Random;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/* loaded from: classes2.dex */
final class EmailUtils {
    private static final char ESCAPE_CHAR = '%';
    private static final int RADIX = 16;
    private static final Random RANDOM = new Random();
    private static final BitSet SAFE_URL = new BitSet(256);
    private static final String US_ASCII = "US-ASCII";

    static {
        for (int i = 97; i <= 122; i++) {
            SAFE_URL.set(i);
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            SAFE_URL.set(i2);
        }
        for (int i3 = 48; i3 <= 57; i3++) {
            SAFE_URL.set(i3);
        }
        SAFE_URL.set(45);
        SAFE_URL.set(95);
        SAFE_URL.set(46);
        SAFE_URL.set(42);
        SAFE_URL.set(43);
        SAFE_URL.set(36);
        SAFE_URL.set(33);
        SAFE_URL.set(39);
        SAFE_URL.set(40);
        SAFE_URL.set(41);
        SAFE_URL.set(44);
        SAFE_URL.set(64);
    }

    private EmailUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isNotEmpty(String str) {
        return str != null && str.length() > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void notNull(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String randomAlphabetic(int i) {
        return random(i, 0, 0, true, false, null, RANDOM);
    }

    private static String random(int i, int i2, int i3, boolean z, boolean z2, char[] cArr, Random random) {
        char c;
        if (i == 0) {
            return "";
        }
        if (i >= 0) {
            if (i2 == 0 && i3 == 0) {
                i3 = TbsListener.ErrorCode.DOWNLOAD_RETRYTIMES302_EXCEED;
                i2 = 32;
                if (!z && !z2) {
                    i2 = 0;
                    i3 = Integer.MAX_VALUE;
                }
            }
            StringBuffer stringBuffer = new StringBuffer();
            int i4 = i3 - i2;
            while (true) {
                int i5 = i - 1;
                if (i == 0) {
                    return stringBuffer.toString();
                }
                if (cArr == null) {
                    c = (char) (random.nextInt(i4) + i2);
                } else {
                    c = cArr[random.nextInt(i4) + i2];
                }
                if ((!z || !z2 || !Character.isLetterOrDigit(c)) && ((!z || !Character.isLetter(c)) && ((!z2 || !Character.isDigit(c)) && (z || z2)))) {
                    i = i5 + 1;
                } else {
                    stringBuffer.append(c);
                    i = i5;
                }
            }
        } else {
            throw new IllegalArgumentException("Requested random string length " + i + " is less than 0.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String encodeUrl(String str) throws UnsupportedEncodingException {
        byte[] bytes;
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : str.getBytes("US-ASCII")) {
            int i = b;
            if (b < 0) {
                i = b + 256;
            }
            if (SAFE_URL.get(i == 1 ? 1 : 0)) {
                sb.append((char) i);
            } else {
                sb.append(ESCAPE_CHAR);
                char upperCase = Character.toUpperCase(Character.forDigit((i >> 4) & 15, 16));
                char upperCase2 = Character.toUpperCase(Character.forDigit((i == 1 ? 1 : 0) & 15, 16));
                sb.append(upperCase);
                sb.append(upperCase2);
            }
        }
        return sb.toString();
    }

    static void writeMimeMessage(File file, MimeMessage mimeMessage) throws IOException, MessagingException {
        Throwable th;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
                throw new IOException("Failed to create the following parent directories: " + file.getParentFile());
            }
            fileOutputStream = new FileOutputStream(file);
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            mimeMessage.writeTo(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            throw th;
        }
    }
}
