package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import org.apache.commons.p105io.FileUtils;
import p110z1.TimeConstants;

/* renamed from: com.blankj.utilcode.util.q */
/* loaded from: classes.dex */
public final class ConvertUtils {

    /* renamed from: a */
    private static final char[] f6897a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* renamed from: a */
    public static long m22469a(long j, int i) {
        if (j < 0) {
            return -1L;
        }
        return j * i;
    }

    /* renamed from: b */
    public static double m22454b(long j, int i) {
        if (j < 0) {
            return -1.0d;
        }
        return j / i;
    }

    /* renamed from: c */
    public static long m22447c(long j, int i) {
        return j * i;
    }

    private ConvertUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static String m22457a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            for (int i = 7; i >= 0; i--) {
                sb.append(((b >> i) & 1) == 0 ? '0' : '1');
            }
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static byte[] m22459a(String str) {
        int length = str.length() % 8;
        int length2 = str.length() / 8;
        if (length != 0) {
            while (length < 8) {
                str = ResultTypeConstant.f7213z + str;
                length++;
            }
            length2++;
        }
        byte[] bArr = new byte[length2];
        for (int i = 0; i < length2; i++) {
            for (int i2 = 0; i2 < 8; i2++) {
                bArr[i] = (byte) (bArr[i] << 1);
                bArr[i] = (byte) (bArr[i] | (str.charAt((i * 8) + i2) - '0'));
            }
        }
        return bArr;
    }

    /* renamed from: b */
    public static char[] m22449b(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) <= 0) {
            return null;
        }
        char[] cArr = new char[length];
        for (int i = 0; i < length; i++) {
            cArr[i] = (char) (bArr[i] & 255);
        }
        return cArr;
    }

    /* renamed from: a */
    public static byte[] m22456a(char[] cArr) {
        if (cArr == null || cArr.length <= 0) {
            return null;
        }
        int length = cArr.length;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) cArr[i];
        }
        return bArr;
    }

    /* renamed from: c */
    public static String m22445c(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) <= 0) {
            return "";
        }
        char[] cArr = new char[length << 1];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            char[] cArr2 = f6897a;
            cArr[i] = cArr2[(bArr[i2] >> 4) & 15];
            i = i3 + 1;
            cArr[i3] = cArr2[bArr[i2] & 15];
        }
        return new String(cArr);
    }

    /* renamed from: b */
    public static byte[] m22451b(String str) {
        if (m22446c(str)) {
            return null;
        }
        int length = str.length();
        if (length % 2 != 0) {
            str = ResultTypeConstant.f7213z + str;
            length++;
        }
        char[] charArray = str.toUpperCase().toCharArray();
        byte[] bArr = new byte[length >> 1];
        for (int i = 0; i < length; i += 2) {
            bArr[i >> 1] = (byte) ((m22472a(charArray[i]) << 4) | m22472a(charArray[i + 1]));
        }
        return bArr;
    }

    /* renamed from: a */
    private static int m22472a(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'F') {
            return (c - 'A') + 10;
        }
        throw new IllegalArgumentException();
    }

    @SuppressLint({"DefaultLocale"})
    /* renamed from: a */
    public static String m22470a(long j) {
        return j < 0 ? "shouldn't be less than zero!" : j < 1024 ? String.format("%.3fB", Double.valueOf(j)) : j < 1048576 ? String.format("%.3fKB", Double.valueOf(j / 1024.0d)) : j < FileUtils.ONE_GB ? String.format("%.3fMB", Double.valueOf(j / 1048576.0d)) : String.format("%.3fGB", Double.valueOf(j / 1.073741824E9d));
    }

    /* renamed from: d */
    public static long m22443d(long j, int i) {
        return j / i;
    }

    @SuppressLint({"DefaultLocale"})
    /* renamed from: e */
    public static String m22441e(long j, int i) {
        if (j <= 0 || i <= 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String[] strArr = {"天", "小时", "分钟", "秒", "毫秒"};
        int[] iArr = {TimeConstants.f21692e, TimeConstants.f21691d, 60000, 1000, 1};
        int min = Math.min(i, 5);
        for (int i2 = 0; i2 < min; i2++) {
            if (j >= iArr[i2]) {
                long j2 = j / iArr[i2];
                j -= iArr[i2] * j2;
                sb.append(j2);
                sb.append(strArr[i2]);
            }
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static ByteArrayOutputStream m22463a(InputStream inputStream) {
        try {
            if (inputStream == null) {
                return null;
            }
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr, 0, 1024);
                    if (read != -1) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    } else {
                        try {
                            break;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                inputStream.close();
                return byteArrayOutputStream;
            } catch (IOException e2) {
                e2.printStackTrace();
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                return null;
            }
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            throw th;
        }
    }

    /* renamed from: a */
    public ByteArrayInputStream m22461a(OutputStream outputStream) {
        if (outputStream == null) {
            return null;
        }
        return new ByteArrayInputStream(((ByteArrayOutputStream) outputStream).toByteArray());
    }

    /* renamed from: b */
    public static byte[] m22453b(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        return m22463a(inputStream).toByteArray();
    }

    /* renamed from: d */
    public static InputStream m22442d(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        return new ByteArrayInputStream(bArr);
    }

    /* renamed from: b */
    public static byte[] m22452b(OutputStream outputStream) {
        if (outputStream == null) {
            return null;
        }
        return ((ByteArrayOutputStream) outputStream).toByteArray();
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0030 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.io.OutputStream m22440e(byte[] r2) {
        /*
            r0 = 0
            if (r2 == 0) goto L_0x0039
            int r1 = r2.length
            if (r1 > 0) goto L_0x0007
            goto L_0x0039
        L_0x0007:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: all -> 0x001a, IOException -> 0x001c
            r1.<init>()     // Catch: all -> 0x001a, IOException -> 0x001c
            r1.write(r2)     // Catch: IOException -> 0x0018, all -> 0x002c
            r1.close()     // Catch: IOException -> 0x0013
            goto L_0x0017
        L_0x0013:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0017:
            return r1
        L_0x0018:
            r2 = move-exception
            goto L_0x001e
        L_0x001a:
            r2 = move-exception
            goto L_0x002e
        L_0x001c:
            r2 = move-exception
            r1 = r0
        L_0x001e:
            r2.printStackTrace()     // Catch: all -> 0x002c
            if (r1 == 0) goto L_0x002b
            r1.close()     // Catch: IOException -> 0x0027
            goto L_0x002b
        L_0x0027:
            r2 = move-exception
            r2.printStackTrace()
        L_0x002b:
            return r0
        L_0x002c:
            r2 = move-exception
            r0 = r1
        L_0x002e:
            if (r0 == 0) goto L_0x0038
            r0.close()     // Catch: IOException -> 0x0034
            goto L_0x0038
        L_0x0034:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0038:
            throw r2
        L_0x0039:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.ConvertUtils.m22440e(byte[]):java.io.OutputStream");
    }

    /* renamed from: a */
    public static String m22462a(InputStream inputStream, String str) {
        if (inputStream == null || m22446c(str)) {
            return "";
        }
        try {
            ByteArrayOutputStream a = m22463a(inputStream);
            return a == null ? "" : a.toString(str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: a */
    public static InputStream m22458a(String str, String str2) {
        if (str == null || m22446c(str2)) {
            return null;
        }
        try {
            return new ByteArrayInputStream(str.getBytes(str2));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static String m22460a(OutputStream outputStream, String str) {
        if (outputStream == null || m22446c(str)) {
            return "";
        }
        try {
            return new String(m22452b(outputStream), str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: b */
    public static OutputStream m22450b(String str, String str2) {
        if (str == null || m22446c(str2)) {
            return null;
        }
        try {
            return m22440e(str.getBytes(str2));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static byte[] m22467a(Bitmap bitmap, Bitmap.CompressFormat compressFormat) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: f */
    public static Bitmap m22439f(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public static Bitmap m22466a(Drawable drawable) {
        Bitmap bitmap;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /* renamed from: a */
    public static Drawable m22468a(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return new BitmapDrawable(Utils.m24103a().getResources(), bitmap);
    }

    /* renamed from: a */
    public static byte[] m22465a(Drawable drawable, Bitmap.CompressFormat compressFormat) {
        if (drawable == null) {
            return null;
        }
        return m22467a(m22466a(drawable), compressFormat);
    }

    /* renamed from: g */
    public static Drawable m22438g(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return m22468a(m22439f(bArr));
    }

    /* renamed from: a */
    public static Bitmap m22464a(View view) {
        if (view == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Drawable background = view.getBackground();
        if (background != null) {
            background.draw(canvas);
        } else {
            canvas.drawColor(-1);
        }
        view.draw(canvas);
        return createBitmap;
    }

    /* renamed from: a */
    public static int m22471a(float f) {
        return (int) ((f * Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    /* renamed from: b */
    public static int m22455b(float f) {
        return (int) ((f / Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    /* renamed from: c */
    public static int m22448c(float f) {
        return (int) ((f * Resources.getSystem().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    /* renamed from: d */
    public static int m22444d(float f) {
        return (int) ((f / Resources.getSystem().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    /* renamed from: c */
    private static boolean m22446c(String str) {
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
}
