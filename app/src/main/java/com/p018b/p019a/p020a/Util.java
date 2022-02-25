package com.p018b.p019a.p020a;

import android.support.p003v4.media.session.PlaybackStateCompat;
import com.p018b.p019a.HttpUrl;
import com.p018b.p019a.RequestBody;
import com.p018b.p019a.ResponseBody;
import com.p018b.p029b.Buffer;
import com.p018b.p029b.BufferedSource;
import com.p018b.p029b.ByteString;
import com.p018b.p029b.Source;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.reflect.Array;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import p110z1.cjm;

/* renamed from: com.b.a.a.c */
/* loaded from: classes.dex */
public final class Util {

    /* renamed from: a */
    public static final byte[] f5777a = new byte[0];

    /* renamed from: b */
    public static final String[] f5778b = new String[0];

    /* renamed from: c */
    public static final ResponseBody f5779c = ResponseBody.m24429a(f5777a);

    /* renamed from: d */
    public static final RequestBody f5780d = RequestBody.m24455a(f5777a);

    /* renamed from: g */
    private static final ByteString f5783g = ByteString.m24313b("efbbbf");

    /* renamed from: h */
    private static final ByteString f5784h = ByteString.m24313b("feff");

    /* renamed from: i */
    private static final ByteString f5785i = ByteString.m24313b("fffe");

    /* renamed from: j */
    private static final ByteString f5786j = ByteString.m24313b("0000ffff");

    /* renamed from: k */
    private static final ByteString f5787k = ByteString.m24313b("ffff0000");

    /* renamed from: e */
    public static final Charset f5781e = Charset.forName("UTF-8");

    /* renamed from: l */
    private static final Charset f5788l = Charset.forName("UTF-16BE");

    /* renamed from: m */
    private static final Charset f5789m = Charset.forName("UTF-16LE");

    /* renamed from: n */
    private static final Charset f5790n = Charset.forName("UTF-32BE");

    /* renamed from: o */
    private static final Charset f5791o = Charset.forName("UTF-32LE");

    /* renamed from: f */
    public static final TimeZone f5782f = TimeZone.getTimeZone("GMT");

    /* renamed from: p */
    private static final Pattern f5792p = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");

    /* renamed from: a */
    public static void m24769a(long j, long j2) {
        if ((j2 | 0) < 0 || 0 > j || j - 0 < j2) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /* renamed from: a */
    public static boolean m24761a(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    /* renamed from: a */
    public static void m24764a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: a */
    public static void m24754a(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (AssertionError e) {
                if (!m24763a(e)) {
                    throw e;
                }
            } catch (RuntimeException e2) {
                throw e2;
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: a */
    public static boolean m24765a(Source xVar, TimeUnit timeUnit) {
        try {
            return m24766a(xVar, 100, timeUnit);
        } catch (IOException unused) {
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m24766a(Source xVar, int i, TimeUnit timeUnit) {
        long nanoTime = System.nanoTime();
        long d = xVar.mo24250a().mo24247c_() ? xVar.mo24250a().mo24246d() - nanoTime : Long.MAX_VALUE;
        xVar.mo24250a().mo24243a(Math.min(d, timeUnit.toNanos(i)) + nanoTime);
        try {
            Buffer fVar = new Buffer();
            while (xVar.mo24249a(fVar, PlaybackStateCompat.ACTION_PLAY_FROM_URI) != -1) {
                fVar.m24317o();
            }
            if (d == cjm.f20759b) {
                xVar.mo24250a().mo24244e_();
                return true;
            }
            xVar.mo24250a().mo24243a(nanoTime + d);
            return true;
        } catch (InterruptedIOException unused) {
            if (d == cjm.f20759b) {
                xVar.mo24250a().mo24244e_();
                return false;
            }
            xVar.mo24250a().mo24243a(nanoTime + d);
            return false;
        } catch (Throwable th) {
            if (d == cjm.f20759b) {
                xVar.mo24250a().mo24244e_();
            } else {
                xVar.mo24250a().mo24243a(nanoTime + d);
            }
            throw th;
        }
    }

    /* renamed from: a */
    public static <T> List<T> m24753a(List<T> list) {
        return Collections.unmodifiableList(new ArrayList(list));
    }

    /* renamed from: a */
    public static <T> List<T> m24752a(T... tArr) {
        return Collections.unmodifiableList(Arrays.asList((Object[]) tArr.clone()));
    }

    /* renamed from: a */
    public static ThreadFactory m24756a(String str, boolean z) {
        return new ThreadFactoryC0859d(str, z);
    }

    /* renamed from: a */
    public static String m24768a(HttpUrl acVar, boolean z) {
        String str;
        if (acVar.m24529f().contains(":")) {
            str = "[" + acVar.m24529f() + "]";
        } else {
            str = acVar.m24529f();
        }
        if (!z && acVar.m24528g() == HttpUrl.m24546a(acVar.m24538b())) {
            return str;
        }
        return str + ":" + acVar.m24528g();
    }

    /* renamed from: a */
    public static boolean m24763a(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    /* renamed from: a */
    public static <T> int m24751a(T[] tArr, T t) {
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            if (m24761a(tArr[i], t)) {
                return i;
            }
        }
        return -1;
    }

    /* renamed from: a */
    public static String[] m24750a(String[] strArr, String str) {
        String[] strArr2 = new String[strArr.length + 1];
        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
        strArr2[strArr2.length - 1] = str;
        return strArr2;
    }

    /* renamed from: a */
    public static int m24759a(String str, int i, int i2) {
        while (i < i2) {
            switch (str.charAt(i)) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    i++;
                default:
                    return i;
            }
        }
        return i2;
    }

    /* renamed from: b */
    public static int m24748b(String str, int i, int i2) {
        for (int i3 = i2 - 1; i3 >= i; i3--) {
            switch (str.charAt(i3)) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                default:
                    return i3 + 1;
            }
        }
        return i;
    }

    /* renamed from: c */
    public static String m24746c(String str, int i, int i2) {
        int a = m24759a(str, i, i2);
        return str.substring(a, m24748b(str, a, i2));
    }

    /* renamed from: a */
    public static int m24757a(String str, int i, int i2, String str2) {
        while (i < i2) {
            if (str2.indexOf(str.charAt(i)) != -1) {
                return i;
            }
            i++;
        }
        return i2;
    }

    /* renamed from: a */
    public static int m24758a(String str, int i, int i2, char c) {
        while (i < i2) {
            if (str.charAt(i) == c) {
                return i;
            }
            i++;
        }
        return i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0038, code lost:
        return null;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m24760a(java.lang.String r6) {
        /*
            r0 = 0
            java.lang.String r6 = java.net.IDN.toASCII(r6)     // Catch: IllegalArgumentException -> 0x003a
            java.util.Locale r1 = java.util.Locale.US     // Catch: IllegalArgumentException -> 0x003a
            java.lang.String r6 = r6.toLowerCase(r1)     // Catch: IllegalArgumentException -> 0x003a
            boolean r1 = r6.isEmpty()     // Catch: IllegalArgumentException -> 0x003a
            if (r1 == 0) goto L_0x0012
            return r0
        L_0x0012:
            r1 = 0
            r2 = 0
        L_0x0014:
            int r3 = r6.length()     // Catch: IllegalArgumentException -> 0x003a
            r4 = 1
            if (r2 >= r3) goto L_0x0035
            char r3 = r6.charAt(r2)     // Catch: IllegalArgumentException -> 0x003a
            r5 = 31
            if (r3 <= r5) goto L_0x0036
            r5 = 127(0x7f, float:1.78E-43)
            if (r3 < r5) goto L_0x0028
            goto L_0x0036
        L_0x0028:
            java.lang.String r5 = " #%/:?@[\\]"
            int r3 = r5.indexOf(r3)     // Catch: IllegalArgumentException -> 0x003a
            r5 = -1
            if (r3 == r5) goto L_0x0032
            goto L_0x0036
        L_0x0032:
            int r2 = r2 + 1
            goto L_0x0014
        L_0x0035:
            r4 = 0
        L_0x0036:
            if (r4 == 0) goto L_0x0039
            return r0
        L_0x0039:
            return r6
        L_0x003a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p018b.p019a.p020a.Util.m24760a(java.lang.String):java.lang.String");
    }

    /* renamed from: b */
    public static int m24749b(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt <= 31 || charAt >= 127) {
                return i;
            }
        }
        return -1;
    }

    /* renamed from: c */
    public static boolean m24747c(String str) {
        return f5792p.matcher(str).matches();
    }

    /* renamed from: a */
    public static String m24755a(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }

    /* renamed from: a */
    public static Charset m24767a(BufferedSource hVar, Charset charset) {
        if (hVar.mo24285b(f5783g)) {
            hVar.mo24278f(f5783g.mo24255g());
            return f5781e;
        } else if (hVar.mo24285b(f5784h)) {
            hVar.mo24278f(f5784h.mo24255g());
            return f5788l;
        } else if (hVar.mo24285b(f5785i)) {
            hVar.mo24278f(f5785i.mo24255g());
            return f5789m;
        } else if (hVar.mo24285b(f5786j)) {
            hVar.mo24278f(f5786j.mo24255g());
            return f5790n;
        } else if (!hVar.mo24285b(f5787k)) {
            return charset;
        } else {
            hVar.mo24278f(f5787k.mo24255g());
            return f5791o;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public static <T> T[] m24762a(Class<T> cls, T[] tArr, T[] tArr2) {
        ArrayList arrayList = new ArrayList();
        for (Object[] objArr : tArr) {
            int length = tArr2.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    T t = tArr2[i];
                    if (objArr.equals(t)) {
                        arrayList.add(t);
                        break;
                    }
                    i++;
                }
            }
        }
        return (T[]) arrayList.toArray((Object[]) Array.newInstance((Class<?>) cls, arrayList.size()));
    }
}
