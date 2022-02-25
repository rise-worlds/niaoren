package com.lody.virtual.helper.utils;

import android.text.TextUtils;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes.dex */
public class MD5Utils {
    protected static char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    protected static MessageDigest MESSAGE_DIGEST_5;

    static {
        MESSAGE_DIGEST_5 = null;
        try {
            MESSAGE_DIGEST_5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String getFileMD5String(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read > 0) {
                MESSAGE_DIGEST_5.update(bArr, 0, read);
            } else {
                fileInputStream.close();
                return bufferToHex(MESSAGE_DIGEST_5.digest());
            }
        }
    }

    public static String hashBase64(byte[] bArr) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = byteArrayInputStream.read(bArr2);
                if (read > 0) {
                    instance.update(bArr2, 0, read);
                } else {
                    try {
                        break;
                    } catch (IOException unused) {
                    }
                }
            }
            byteArrayInputStream.close();
            return Base64.encodeToString(instance.digest(), 0);
        } catch (Exception unused2) {
            try {
                byteArrayInputStream.close();
            } catch (IOException unused3) {
            }
            return null;
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (IOException unused4) {
            }
            throw th;
        }
    }

    public static String getFileMD5String(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read > 0) {
                MESSAGE_DIGEST_5.update(bArr, 0, read);
            } else {
                inputStream.close();
                return bufferToHex(MESSAGE_DIGEST_5.digest());
            }
        }
    }

    private static String bufferToHex(byte[] bArr) {
        return bufferToHex(bArr, 0, bArr.length);
    }

    private static String bufferToHex(byte[] bArr, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer(i2 * 2);
        int i3 = i2 + i;
        while (i < i3) {
            appendHexPair(bArr[i], stringBuffer);
            i++;
        }
        return stringBuffer.toString();
    }

    private static void appendHexPair(byte b, StringBuffer stringBuffer) {
        char[] cArr = HEX_DIGITS;
        char c = cArr[(b & 240) >> 4];
        char c2 = cArr[b & 15];
        stringBuffer.append(c);
        stringBuffer.append(c2);
    }

    public static boolean compareFiles(File file, File file2) throws IOException {
        if (file.getAbsolutePath().equals(file2.getAbsolutePath())) {
            return true;
        }
        return TextUtils.equals(getFileMD5String(file), getFileMD5String(file2));
    }
}
