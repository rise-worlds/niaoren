package com.lidroid.xutils.util;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.github.kevinsawicki.http.HttpRequest;
import com.lidroid.xutils.http.client.multipart.MIME;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.cert.X509Certificate;
import java.util.Locale;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpRequestBase;

/* loaded from: classes.dex */
public class OtherUtils {
    private static final int STRING_BUFFER_LENGTH = 100;
    private static SSLSocketFactory sslSocketFactory;

    private OtherUtils() {
    }

    public static String getUserAgent(Context context) {
        String str = null;
        if (context != null) {
            try {
                str = context.getString(((Integer) Class.forName("com.android.internal.R$string").getDeclaredField("web_user_agent").get(null)).intValue());
            } catch (Throwable unused) {
            }
        }
        if (TextUtils.isEmpty(str)) {
            str = "Mozilla/5.0 (Linux; U; Android %s) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 %sSafari/533.1";
        }
        Locale locale = Locale.getDefault();
        StringBuffer stringBuffer = new StringBuffer();
        String str2 = Build.VERSION.RELEASE;
        if (str2.length() > 0) {
            stringBuffer.append(str2);
        } else {
            stringBuffer.append("1.0");
        }
        stringBuffer.append("; ");
        String language = locale.getLanguage();
        if (language != null) {
            stringBuffer.append(language.toLowerCase());
            String country = locale.getCountry();
            if (country != null) {
                stringBuffer.append("-");
                stringBuffer.append(country.toLowerCase());
            }
        } else {
            stringBuffer.append("en");
        }
        if ("REL".equals(Build.VERSION.CODENAME)) {
            String str3 = Build.MODEL;
            if (str3.length() > 0) {
                stringBuffer.append("; ");
                stringBuffer.append(str3);
            }
        }
        String str4 = Build.ID;
        if (str4.length() > 0) {
            stringBuffer.append(" Build/");
            stringBuffer.append(str4);
        }
        return String.format(str, stringBuffer, "Mobile ");
    }

    public static String getDiskCacheDir(Context context, String str) {
        File cacheDir;
        File externalCacheDir;
        String path = (!"mounted".equals(Environment.getExternalStorageState()) || (externalCacheDir = context.getExternalCacheDir()) == null) ? null : externalCacheDir.getPath();
        if (path == null && (cacheDir = context.getCacheDir()) != null && cacheDir.exists()) {
            path = cacheDir.getPath();
        }
        return String.valueOf(path) + File.separator + str;
    }

    public static long getAvailableSpace(File file) {
        try {
            StatFs statFs = new StatFs(file.getPath());
            return statFs.getBlockSize() * statFs.getAvailableBlocks();
        } catch (Throwable th) {
            LogUtils.m19219e(th.getMessage(), th);
            return -1L;
        }
    }

    public static boolean isSupportRange(HttpResponse httpResponse) {
        String value;
        if (httpResponse == null) {
            return false;
        }
        Header firstHeader = httpResponse.getFirstHeader("Accept-Ranges");
        if (firstHeader != null) {
            return "bytes".equals(firstHeader.getValue());
        }
        Header firstHeader2 = httpResponse.getFirstHeader("Content-Range");
        return (firstHeader2 == null || (value = firstHeader2.getValue()) == null || !value.startsWith("bytes")) ? false : true;
    }

    public static String getFileNameFromHttpResponse(HttpResponse httpResponse) {
        Header firstHeader;
        if (httpResponse == null || (firstHeader = httpResponse.getFirstHeader(MIME.CONTENT_DISPOSITION)) == null) {
            return null;
        }
        for (HeaderElement headerElement : firstHeader.getElements()) {
            NameValuePair parameterByName = headerElement.getParameterByName("filename");
            if (parameterByName != null) {
                String value = parameterByName.getValue();
                return CharsetUtils.toCharset(value, "UTF-8", value.length());
            }
        }
        return null;
    }

    public static Charset getCharsetFromHttpRequest(HttpRequestBase httpRequestBase) {
        String str;
        if (httpRequestBase == null) {
            return null;
        }
        Header firstHeader = httpRequestBase.getFirstHeader("Content-Type");
        boolean z = false;
        if (firstHeader != null) {
            for (HeaderElement headerElement : firstHeader.getElements()) {
                NameValuePair parameterByName = headerElement.getParameterByName(HttpRequest.PARAM_CHARSET);
                if (parameterByName != null) {
                    str = parameterByName.getValue();
                    break;
                }
            }
        }
        str = null;
        if (!TextUtils.isEmpty(str)) {
            try {
                z = Charset.isSupported(str);
            } catch (Throwable unused) {
            }
        }
        if (z) {
            return Charset.forName(str);
        }
        return null;
    }

    public static long sizeOfString(String str, String str2) throws UnsupportedEncodingException {
        long j = 0;
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        int length = str.length();
        if (length < 100) {
            return str.getBytes(str2).length;
        }
        int i = 0;
        while (i < length) {
            int i2 = i + 100;
            j += getSubString(str, i, i2 < length ? i2 : length).getBytes(str2).length;
            i = i2;
        }
        return j;
    }

    public static String getSubString(String str, int i, int i2) {
        return new String(str.substring(i, i2));
    }

    public static StackTraceElement getCurrentStackTraceElement() {
        return Thread.currentThread().getStackTrace()[3];
    }

    public static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];
    }

    public static void trustAllHttpsURLConnection() {
        if (sslSocketFactory == null) {
            TrustManager[] trustManagerArr = {new X509TrustManager() { // from class: com.lidroid.xutils.util.OtherUtils.1
                @Override // javax.net.ssl.X509TrustManager
                public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
                }

                @Override // javax.net.ssl.X509TrustManager
                public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
                }

                @Override // javax.net.ssl.X509TrustManager
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }};
            try {
                SSLContext instance = SSLContext.getInstance("TLS");
                instance.init(null, trustManagerArr, null);
                sslSocketFactory = instance.getSocketFactory();
            } catch (Throwable th) {
                LogUtils.m19219e(th.getMessage(), th);
            }
        }
        SSLSocketFactory sSLSocketFactory = sslSocketFactory;
        if (sSLSocketFactory != null) {
            HttpsURLConnection.setDefaultSSLSocketFactory(sSLSocketFactory);
            HttpsURLConnection.setDefaultHostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        }
    }
}
