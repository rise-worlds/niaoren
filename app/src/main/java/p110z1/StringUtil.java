package p110z1;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import org.apache.commons.p105io.FileUtils;
import org.w3c.dom.Node;

@SuppressLint({"DefaultLocale"})
/* renamed from: z1.apg */
/* loaded from: classes3.dex */
public class StringUtil {

    /* renamed from: b */
    private static final String f17116b = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";

    /* renamed from: c */
    private static final DecimalFormat f17117c = new DecimalFormat("#00.0");

    /* renamed from: a */
    static DecimalFormat f17115a = new DecimalFormat("#.0");

    /* renamed from: c */
    public static String m11794c(String str) {
        return str == null ? "" : str;
    }

    /* renamed from: h */
    public static String m11788h(String str) {
        return str == null ? "" : str;
    }

    /* renamed from: a */
    public static String m11821a(Context context, int i) {
        return context.getResources().getString(i);
    }

    /* renamed from: a */
    public static boolean m11812a(CharSequence charSequence) {
        int length;
        if (charSequence == null || (length = charSequence.length()) == 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (m11828a(charSequence.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    public static boolean m11800b(CharSequence charSequence) {
        int length;
        if (charSequence == null || (length = charSequence.length()) == 0) {
            return true;
        }
        for (int i = 0; i < length; i++) {
            if (!m11828a(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m11828a(char c) {
        return c == 12288 || Character.isWhitespace(c);
    }

    /* renamed from: c */
    public static boolean m11795c(CharSequence charSequence) {
        return charSequence == null || charSequence.length() <= 0;
    }

    /* renamed from: a */
    public static boolean m11804a(String... strArr) {
        for (String str : strArr) {
            if (m11795c((CharSequence) str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public static String m11817a(Cursor cursor, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex != -1) {
            return cursor.getString(columnIndex);
        }
        return null;
    }

    /* renamed from: a */
    public static boolean m11808a(String str, String str2) {
        if (str == str2) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return str.equals(str2);
    }

    /* renamed from: a */
    public static String m11811a(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf >= 0) {
            return str.substring(lastIndexOf + 1);
        }
        return null;
    }

    /* renamed from: b */
    public static String m11799b(String str) {
        if (str == null) {
            return null;
        }
        return str.replace(".png", ".a").replace(".jpg", ".b");
    }

    /* renamed from: a */
    public static int m11810a(String str, int i) {
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return i;
        }
    }

    /* renamed from: a */
    public static long m11809a(String str, Long l) {
        try {
            return Long.parseLong(str);
        } catch (Exception unused) {
            return l.longValue();
        }
    }

    /* renamed from: a */
    public static String m11823a(long j, int i) {
        float f = 1.0f;
        switch (i) {
            case 1:
                f = 10.0f;
                break;
            case 2:
                f = 100.0f;
                break;
            case 3:
                f = 1000.0f;
                break;
            case 4:
                f = 10000.0f;
                break;
        }
        float f2 = (float) j;
        try {
            if (f2 >= 1024.0f) {
                float f3 = f2 / 1024.0f;
                if (f3 >= 1024.0f) {
                    float f4 = f3 / 1024.0f;
                    if (f4 >= 1024.0f) {
                        float f5 = f4 / 1024.0f;
                        if (i == 0) {
                            return ((int) f5) + "GB";
                        }
                        return (Math.round(f5 * f) / f) + "GB";
                    } else if (i == 0) {
                        return ((int) f4) + "MB";
                    } else {
                        return (Math.round(f4 * f) / f) + "MB";
                    }
                } else if (i == 0) {
                    return ((int) f3) + "KB";
                } else {
                    return (Math.round(f3 * f) / f) + "KB";
                }
            } else if (i == 0) {
                return ((int) f2) + "B";
            } else {
                return (Math.round(f2 * f) / f) + "B";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "-1B";
        }
    }

    /* renamed from: a */
    public static String m11827a(double d, int i) {
        float f = 1.0f;
        switch (i) {
            case 1:
                f = 10.0f;
                break;
            case 2:
                f = 100.0f;
                break;
            case 3:
                f = 1000.0f;
                break;
            case 4:
                f = 10000.0f;
                break;
        }
        if (d >= 1024.0d) {
            double d2 = d / 1024.0d;
            if (d2 >= 1024.0d) {
                double d3 = d2 / 1024.0d;
                if (d3 >= 1024.0d) {
                    double d4 = d3 / 1024.0d;
                    if (i == 0) {
                        return ((int) d4) + "GB";
                    }
                    return (((float) Math.round(d4 * f)) / f) + "GB";
                } else if (i == 0) {
                    return ((int) d3) + "MB";
                } else {
                    return (((float) Math.round(d3 * f)) / f) + "MB";
                }
            } else if (i == 0) {
                return ((int) d2) + "KB";
            } else {
                return (((float) Math.round(d2 * f)) / f) + "KB";
            }
        } else if (i == 0) {
            return ((int) d) + "B";
        } else {
            return (((float) Math.round(d * f)) / f) + "B";
        }
    }

    /* renamed from: a */
    public static String m11805a(Node node) {
        Node firstChild = node.getFirstChild();
        if (firstChild == null || !"#text".equals(firstChild.getNodeName())) {
            return null;
        }
        String nodeValue = firstChild.getNodeValue();
        return nodeValue != null ? nodeValue.trim() : nodeValue;
    }

    /* renamed from: d */
    public static boolean m11792d(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    /* renamed from: e */
    public static boolean m11791e(String str) {
        return Pattern.compile(RegexUtil.f17293A).matcher(str).matches();
    }

    /* renamed from: f */
    public static boolean m11790f(String str) {
        return Pattern.compile("\\w+@(\\w+\\.)+[a-z]{2,3}").matcher(str).matches();
    }

    /* renamed from: g */
    public static boolean m11789g(String str) {
        return Pattern.compile(RegexUtil.f17298F).matcher(str).matches();
    }

    /* renamed from: a */
    public static String m11807a(String str, String str2, String str3) {
        String lowerCase = str.toLowerCase();
        String lowerCase2 = str2.toLowerCase();
        int hashCode = str3.toLowerCase(Locale.getDefault()).hashCode() - 25;
        try {
            char[] cArr = new char[lowerCase.length()];
            for (int i = 0; i < cArr.length; i++) {
                cArr[i] = (char) (lowerCase.toCharArray()[i] << 2);
            }
            for (int i2 = 0; i2 < cArr.length; i2++) {
                if (i2 % cArr[i2] == 0) {
                    hashCode += cArr[i2];
                } else {
                    hashCode -= cArr[i2];
                }
            }
            int i3 = (hashCode << 2) >> 3;
            char[] cArr2 = new char[lowerCase2.length()];
            for (int i4 = 0; i4 < cArr2.length - 2; i4++) {
                cArr2[i4] = (char) ((lowerCase2.toCharArray()[i4] + cArr[i4 % cArr.length]) << 3);
                if (((cArr2[i4] * 2) - (cArr[i4] * 2)) % 2 == 0) {
                    i3 += cArr2[i4];
                } else {
                    i3 -= cArr2[i4];
                }
            }
            return "i" + Math.abs(i3);
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: a */
    public static List<String> m11806a(HashMap<String, String> hashMap, String str) {
        ArrayList arrayList = new ArrayList();
        for (String str2 : hashMap.keySet()) {
            if (m11808a(hashMap.get(str2), str)) {
                arrayList.add(str2);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static String m11818a(ResolveInfo resolveInfo) {
        return (resolveInfo.activityInfo.packageName + "|" + resolveInfo.activityInfo.name).toLowerCase();
    }

    /* renamed from: a */
    public static String m11822a(ComponentName componentName) {
        return (componentName.getPackageName() + "|" + componentName.getClassName()).toLowerCase();
    }

    /* renamed from: b */
    public static String m11797b(String str, String str2) {
        return (str.trim() + "|" + str2.trim()).toLowerCase();
    }

    /* renamed from: a */
    public static String m11819a(ActivityInfo activityInfo) {
        return (activityInfo.packageName + "|" + activityInfo.name).toLowerCase();
    }

    /* renamed from: a */
    public static boolean m11820a(Context context, String str, int i) {
        for (String str2 : context.getResources().getStringArray(i)) {
            if (str.toLowerCase().endsWith(str2)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: d */
    public static String m11793d(CharSequence charSequence) {
        return m11795c(charSequence) ? "" : charSequence.toString().replace("'", "''").replace("?", "");
    }

    /* renamed from: a */
    public static int m11825a(int i) {
        return Integer.parseInt(String.valueOf(System.currentTimeMillis() % i));
    }

    /* renamed from: i */
    public static String m11787i(String str) {
        return str.substring(str.indexOf("{") + 1, str.lastIndexOf("/"));
    }

    /* renamed from: a */
    public static float m11815a(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return (float) Math.ceil(fontMetrics.descent - fontMetrics.ascent);
    }

    /* renamed from: a */
    public static void m11814a(Paint paint, Rect rect) {
        paint.getTextBounds("桌", 0, 1, rect);
    }

    /* renamed from: a */
    public static void m11816a(Canvas canvas, Paint paint, String str, int i, int i2, int i3) {
        int fontMetricsInt = paint.getFontMetricsInt(null);
        int length = str.length();
        int breakText = paint.breakText(str, true, i, null);
        int i4 = ((length - 1) / breakText) + 1;
        int i5 = 0;
        int i6 = 0;
        while (i5 < i4) {
            int i7 = i6 + breakText;
            if (i7 >= length) {
                i7 = length;
            }
            canvas.drawText(str.substring(i6, i7), i3, ((5 + fontMetricsInt) * i5) + i2, paint);
            i5++;
            i6 = i7;
        }
    }

    /* renamed from: b */
    public static String m11802b(int i) {
        try {
            String hexString = Integer.toHexString(Color.alpha(i));
            if (hexString.length() < 2) {
                hexString = '0' + hexString;
            }
            String hexString2 = Integer.toHexString(Color.red(i));
            if (hexString2.length() < 2) {
                hexString2 = '0' + hexString2;
            }
            String hexString3 = Integer.toHexString(Color.green(i));
            if (hexString3.length() < 2) {
                hexString3 = '0' + hexString3;
            }
            String hexString4 = Integer.toHexString(Color.blue(i));
            if (hexString4.length() < 2) {
                hexString4 = '0' + hexString4;
            }
            return '#' + hexString + hexString2 + hexString3 + hexString4;
        } catch (Exception unused) {
            return "#FFFFFF";
        }
    }

    /* renamed from: j */
    public static String m11786j(String str) {
        return TextUtils.isEmpty(str) ? "" : str.replaceAll("\\$|\\^|\\*|\\(|\\)|\\-|\\+|\\{|\\}|\\||\\.|\\?|\\[|\\]|\\&|\\\\", "");
    }

    /* renamed from: b */
    public static String m11798b(String str, int i) {
        return (str == null || str.length() <= i) ? str : str.substring(0, i);
    }

    /* renamed from: k */
    public static boolean m11785k(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    /* renamed from: a */
    public static String m11813a(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                try {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append(readLine + "/n");
                    } catch (Throwable th) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        throw th;
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                    inputStream.close();
                }
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        inputStream.close();
        return sb.toString();
    }

    /* renamed from: l */
    public static boolean m11784l(String str) {
        return Pattern.compile(f17116b).matcher(str).matches();
    }

    /* renamed from: m */
    public static int m11783m(String str) throws Exception {
        return new String(str.getBytes("GBK"), "iso-8859-1").length();
    }

    /* renamed from: a */
    public static String m11826a(float f) {
        return f17117c.format(f).toString() + "%";
    }

    /* renamed from: a */
    public static String m11824a(long j) {
        double d;
        String str = "";
        if (j / 1024 < 1) {
            d = j;
            str = "B";
        } else if (j / 1048576 < 1) {
            d = j / 1024.0d;
            str = "KB";
        } else if (j / FileUtils.ONE_GB < 1) {
            d = j / 1048576.0d;
            str = "MB";
        } else if (j / 0 < 1) {
            d = j / 1.073741824E9d;
            str = "GB";
        } else {
            d = 0.0d;
        }
        if (d == 0.0d) {
            return "0B";
        }
        return f17115a.format(d) + str;
    }

    /* renamed from: b */
    public static String m11801b(long j) {
        long j2 = j / 1024;
        if (j2 < 1) {
            return j + "B";
        }
        long j3 = j / 1048576;
        if (j3 < 1) {
            return j2 + "KB";
        }
        long j4 = j / FileUtils.ONE_GB;
        if (j4 < 1) {
            return j3 + "MB";
        } else if (j / 0 >= 1) {
            return "0B";
        } else {
            return j4 + "GB";
        }
    }

    /* renamed from: c */
    public static String m11796c(long j) {
        float f = (float) j;
        if (f < 1024.0f) {
            BigDecimal scale = new BigDecimal(f).setScale(2, 4);
            return scale + "Bytes";
        }
        float f2 = f / 1024.0f;
        if (f2 < 1024.0f) {
            BigDecimal scale2 = new BigDecimal(f2).setScale(2, 4);
            return scale2 + "KB";
        }
        float f3 = f2 / 1024.0f;
        if (f3 < 1024.0f) {
            BigDecimal scale3 = new BigDecimal(f3).setScale(2, 4);
            return scale3 + "MB";
        }
        float f4 = f3 / 1024.0f;
        if (f4 < 1024.0f) {
            BigDecimal scale4 = new BigDecimal(f4).setScale(2, 4);
            return scale4 + "GB";
        }
        BigDecimal scale5 = new BigDecimal(f4 / 1024.0f).setScale(2, 4);
        return scale5 + "TB";
    }

    /* renamed from: b */
    public static String m11803b(float f) {
        BigDecimal bigDecimal = new BigDecimal(f * 100.0f);
        return bigDecimal.setScale(2, 4) + "%";
    }
}
