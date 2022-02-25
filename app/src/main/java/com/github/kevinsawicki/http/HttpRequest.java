package com.github.kevinsawicki.http;

import com.lidroid.xutils.http.client.multipart.MIME;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.security.AccessController;
import java.security.GeneralSecurityException;
import java.security.PrivilegedAction;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.commons.p105io.IOUtils;
import org.apache.http.protocol.HTTP;
import org.apache.tools.ant.util.ProxySetup;
import org.apache.tools.tar.TarConstants;
import org.slf4j.Marker;
import p110z1.C4745bt;
import p110z1.SimpleComparison;
import p110z1.Typography;

/* loaded from: classes.dex */
public class HttpRequest {
    private static final String BOUNDARY = "00content0boundary00";
    public static final String CHARSET_UTF8 = "UTF-8";
    public static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";
    public static final String CONTENT_TYPE_JSON = "application/json";
    private static final String CONTENT_TYPE_MULTIPART = "multipart/form-data; boundary=00content0boundary00";
    private static final String CRLF = "\r\n";
    public static final String ENCODING_GZIP = "gzip";
    public static final String HEADER_ACCEPT = "Accept";
    public static final String HEADER_ACCEPT_CHARSET = "Accept-Charset";
    public static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String HEADER_CACHE_CONTROL = "Cache-Control";
    public static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
    public static final String HEADER_CONTENT_LENGTH = "Content-Length";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_DATE = "Date";
    public static final String HEADER_ETAG = "ETag";
    public static final String HEADER_EXPIRES = "Expires";
    public static final String HEADER_IF_NONE_MATCH = "If-None-Match";
    public static final String HEADER_LAST_MODIFIED = "Last-Modified";
    public static final String HEADER_LOCATION = "Location";
    public static final String HEADER_PROXY_AUTHORIZATION = "Proxy-Authorization";
    public static final String HEADER_REFERER = "Referer";
    public static final String HEADER_SERVER = "Server";
    public static final String HEADER_USER_AGENT = "User-Agent";
    public static final String METHOD_DELETE = "DELETE";
    public static final String METHOD_GET = "GET";
    public static final String METHOD_HEAD = "HEAD";
    public static final String METHOD_OPTIONS = "OPTIONS";
    public static final String METHOD_POST = "POST";
    public static final String METHOD_PUT = "PUT";
    public static final String METHOD_TRACE = "TRACE";
    public static final String PARAM_CHARSET = "charset";
    private static SSLSocketFactory TRUSTED_FACTORY;
    private static HostnameVerifier TRUSTED_VERIFIER;
    private boolean form;
    private String httpProxyHost;
    private int httpProxyPort;
    private boolean multipart;
    private RequestOutputStream output;
    private final String requestMethod;
    private final URL url;
    private static final String[] EMPTY_STRINGS = new String[0];
    private static ConnectionFactory CONNECTION_FACTORY = ConnectionFactory.DEFAULT;
    private HttpURLConnection connection = null;
    private boolean ignoreCloseExceptions = true;
    private boolean uncompress = false;
    private int bufferSize = 8192;
    private long totalSize = -1;
    private long totalWritten = 0;
    private UploadProgress progress = UploadProgress.DEFAULT;

    /* loaded from: classes.dex */
    public interface ConnectionFactory {
        public static final ConnectionFactory DEFAULT = new ConnectionFactory() { // from class: com.github.kevinsawicki.http.HttpRequest.ConnectionFactory.1
            @Override // com.github.kevinsawicki.http.HttpRequest.ConnectionFactory
            public final HttpURLConnection create(URL url) throws IOException {
                return (HttpURLConnection) url.openConnection();
            }

            @Override // com.github.kevinsawicki.http.HttpRequest.ConnectionFactory
            public final HttpURLConnection create(URL url, Proxy proxy) throws IOException {
                return (HttpURLConnection) url.openConnection(proxy);
            }
        };

        HttpURLConnection create(URL url) throws IOException;

        HttpURLConnection create(URL url, Proxy proxy) throws IOException;
    }

    /* loaded from: classes.dex */
    public interface UploadProgress {
        public static final UploadProgress DEFAULT = new UploadProgress() { // from class: com.github.kevinsawicki.http.HttpRequest.UploadProgress.1
            @Override // com.github.kevinsawicki.http.HttpRequest.UploadProgress
            public final void onUpload(long j, long j2) {
            }
        };

        void onUpload(long j, long j2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getValidCharset(String str) {
        return (str == null || str.length() <= 0) ? "UTF-8" : str;
    }

    private static SSLSocketFactory getTrustedFactory() throws HttpRequestException {
        if (TRUSTED_FACTORY == null) {
            TrustManager[] trustManagerArr = {new X509TrustManager() { // from class: com.github.kevinsawicki.http.HttpRequest.1
                @Override // javax.net.ssl.X509TrustManager
                public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
                }

                @Override // javax.net.ssl.X509TrustManager
                public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
                }

                @Override // javax.net.ssl.X509TrustManager
                public final X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }};
            try {
                SSLContext instance = SSLContext.getInstance("TLS");
                instance.init(null, trustManagerArr, new SecureRandom());
                TRUSTED_FACTORY = instance.getSocketFactory();
            } catch (GeneralSecurityException e) {
                IOException iOException = new IOException("Security exception configuring SSL context");
                iOException.initCause(e);
                throw new HttpRequestException(iOException);
            }
        }
        return TRUSTED_FACTORY;
    }

    private static HostnameVerifier getTrustedVerifier() {
        if (TRUSTED_VERIFIER == null) {
            TRUSTED_VERIFIER = new HostnameVerifier() { // from class: com.github.kevinsawicki.http.HttpRequest.2
                @Override // javax.net.ssl.HostnameVerifier
                public final boolean verify(String str, SSLSession sSLSession) {
                    return true;
                }
            };
        }
        return TRUSTED_VERIFIER;
    }

    private static StringBuilder addPathSeparator(String str, StringBuilder sb) {
        if (str.indexOf(58) + 2 == str.lastIndexOf(47)) {
            sb.append(IOUtils.DIR_SEPARATOR_UNIX);
        }
        return sb;
    }

    private static StringBuilder addParamPrefix(String str, StringBuilder sb) {
        int indexOf = str.indexOf(63);
        int length = sb.length() - 1;
        if (indexOf == -1) {
            sb.append('?');
        } else if (indexOf < length && str.charAt(length) != '&') {
            sb.append(Typography.f21051c);
        }
        return sb;
    }

    private static StringBuilder addParam(Object obj, Object obj2, StringBuilder sb) {
        if (obj2 != null && obj2.getClass().isArray()) {
            obj2 = arrayToList(obj2);
        }
        if (obj2 instanceof Iterable) {
            Iterator it = ((Iterable) obj2).iterator();
            while (it.hasNext()) {
                sb.append(obj);
                sb.append("[]=");
                Object next = it.next();
                if (next != null) {
                    sb.append(next);
                }
                if (it.hasNext()) {
                    sb.append(C4745bt.f20071b);
                }
            }
        } else {
            sb.append(obj);
            sb.append(SimpleComparison.f23609c);
            if (obj2 != null) {
                sb.append(obj2);
            }
        }
        return sb;
    }

    public static void setConnectionFactory(ConnectionFactory connectionFactory) {
        if (connectionFactory == null) {
            CONNECTION_FACTORY = ConnectionFactory.DEFAULT;
        } else {
            CONNECTION_FACTORY = connectionFactory;
        }
    }

    /* loaded from: classes.dex */
    public static class Base64 {
        private static final byte EQUALS_SIGN = 61;
        private static final String PREFERRED_ENCODING = "US-ASCII";
        private static final byte[] _STANDARD_ALPHABET = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, TarConstants.LF_GNUTYPE_LONGLINK, TarConstants.LF_GNUTYPE_LONGNAME, 77, 78, 79, 80, 81, 82, TarConstants.LF_GNUTYPE_SPARSE, 84, 85, 86, 87, TarConstants.LF_PAX_EXTENDED_HEADER_UC, 89, 90, 97, 98, 99, 100, 101, 102, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 121, 122, TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, 56, 57, 43, 47};

        private Base64() {
        }

        private static byte[] encode3to4(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
            byte[] bArr3 = _STANDARD_ALPHABET;
            int i4 = 0;
            int i5 = (i2 > 0 ? (bArr[i] << 24) >>> 8 : 0) | (i2 > 1 ? (bArr[i + 1] << 24) >>> 16 : 0);
            if (i2 > 2) {
                i4 = (bArr[i + 2] << 24) >>> 24;
            }
            int i6 = i5 | i4;
            switch (i2) {
                case 1:
                    bArr2[i3] = bArr3[i6 >>> 18];
                    bArr2[i3 + 1] = bArr3[(i6 >>> 12) & 63];
                    bArr2[i3 + 2] = EQUALS_SIGN;
                    bArr2[i3 + 3] = EQUALS_SIGN;
                    return bArr2;
                case 2:
                    bArr2[i3] = bArr3[i6 >>> 18];
                    bArr2[i3 + 1] = bArr3[(i6 >>> 12) & 63];
                    bArr2[i3 + 2] = bArr3[(i6 >>> 6) & 63];
                    bArr2[i3 + 3] = EQUALS_SIGN;
                    return bArr2;
                case 3:
                    bArr2[i3] = bArr3[i6 >>> 18];
                    bArr2[i3 + 1] = bArr3[(i6 >>> 12) & 63];
                    bArr2[i3 + 2] = bArr3[(i6 >>> 6) & 63];
                    bArr2[i3 + 3] = bArr3[i6 & 63];
                    return bArr2;
                default:
                    return bArr2;
            }
        }

        public static String encode(String str) {
            byte[] bArr;
            try {
                bArr = str.getBytes("US-ASCII");
            } catch (UnsupportedEncodingException unused) {
                bArr = str.getBytes();
            }
            return encodeBytes(bArr);
        }

        public static String encodeBytes(byte[] bArr) {
            return encodeBytes(bArr, 0, bArr.length);
        }

        public static String encodeBytes(byte[] bArr, int i, int i2) {
            byte[] encodeBytesToBytes = encodeBytesToBytes(bArr, i, i2);
            try {
                return new String(encodeBytesToBytes, "US-ASCII");
            } catch (UnsupportedEncodingException unused) {
                return new String(encodeBytesToBytes);
            }
        }

        public static byte[] encodeBytesToBytes(byte[] bArr, int i, int i2) {
            if (bArr == null) {
                throw new NullPointerException("Cannot serialize a null array.");
            } else if (i < 0) {
                throw new IllegalArgumentException("Cannot have negative offset: " + i);
            } else if (i2 < 0) {
                throw new IllegalArgumentException("Cannot have length offset: " + i2);
            } else if (i + i2 <= bArr.length) {
                int i3 = 4;
                int i4 = (i2 / 3) * 4;
                if (i2 % 3 <= 0) {
                    i3 = 0;
                }
                byte[] bArr2 = new byte[i4 + i3];
                int i5 = i2 - 2;
                int i6 = 0;
                int i7 = 0;
                while (i6 < i5) {
                    encode3to4(bArr, i6 + i, 3, bArr2, i7);
                    i6 += 3;
                    i7 += 4;
                }
                if (i6 < i2) {
                    encode3to4(bArr, i + i6, i2 - i6, bArr2, i7);
                    i7 += 4;
                }
                if (i7 > bArr2.length - 1) {
                    return bArr2;
                }
                byte[] bArr3 = new byte[i7];
                System.arraycopy(bArr2, 0, bArr3, 0, i7);
                return bArr3;
            } else {
                throw new IllegalArgumentException(String.format("Cannot have offset of %d and length of %d with array of length %d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(bArr.length)));
            }
        }
    }

    /* loaded from: classes.dex */
    public static class HttpRequestException extends RuntimeException {
        private static final long serialVersionUID = -1170466989781746231L;

        public HttpRequestException(IOException iOException) {
            super(iOException);
        }

        @Override // java.lang.Throwable
        public IOException getCause() {
            return (IOException) super.getCause();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static abstract class Operation<V> implements Callable<V> {
        protected abstract void done() throws IOException;

        protected abstract V run() throws HttpRequestException, IOException;

        protected Operation() {
        }

        @Override // java.util.concurrent.Callable
        public V call() throws HttpRequestException {
            Throwable th;
            boolean z = true;
            try {
                try {
                    V run = run();
                    try {
                        done();
                        return run;
                    } catch (IOException e) {
                        throw new HttpRequestException(e);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        done();
                    } catch (IOException e2) {
                        if (!z) {
                            throw new HttpRequestException(e2);
                        }
                    }
                    throw th;
                }
            } catch (HttpRequestException e3) {
                throw e3;
            } catch (IOException e4) {
                throw new HttpRequestException(e4);
            } catch (Throwable th3) {
                th = th3;
                z = false;
                done();
                throw th;
            }
        }
    }

    /* loaded from: classes.dex */
    protected static abstract class CloseOperation<V> extends Operation<V> {
        private final Closeable closeable;
        private final boolean ignoreCloseExceptions;

        protected CloseOperation(Closeable closeable, boolean z) {
            this.closeable = closeable;
            this.ignoreCloseExceptions = z;
        }

        @Override // com.github.kevinsawicki.http.HttpRequest.Operation
        protected void done() throws IOException {
            Closeable closeable = this.closeable;
            if (closeable instanceof Flushable) {
                ((Flushable) closeable).flush();
            }
            if (this.ignoreCloseExceptions) {
                try {
                    this.closeable.close();
                } catch (IOException unused) {
                }
            } else {
                this.closeable.close();
            }
        }
    }

    /* loaded from: classes.dex */
    protected static abstract class FlushOperation<V> extends Operation<V> {
        private final Flushable flushable;

        protected FlushOperation(Flushable flushable) {
            this.flushable = flushable;
        }

        @Override // com.github.kevinsawicki.http.HttpRequest.Operation
        protected void done() throws IOException {
            this.flushable.flush();
        }
    }

    /* loaded from: classes.dex */
    public static class RequestOutputStream extends BufferedOutputStream {
        private final CharsetEncoder encoder;

        public RequestOutputStream(OutputStream outputStream, String str, int i) {
            super(outputStream, i);
            this.encoder = Charset.forName(HttpRequest.getValidCharset(str)).newEncoder();
        }

        public RequestOutputStream write(String str) throws IOException {
            ByteBuffer encode = this.encoder.encode(CharBuffer.wrap(str));
            super.write(encode.array(), 0, encode.limit());
            return this;
        }
    }

    private static List<Object> arrayToList(Object obj) {
        if (obj instanceof Object[]) {
            return Arrays.asList((Object[]) obj);
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            int length = iArr.length;
            while (i < length) {
                arrayList.add(Integer.valueOf(iArr[i]));
                i++;
            }
        } else if (obj instanceof boolean[]) {
            boolean[] zArr = (boolean[]) obj;
            int length2 = zArr.length;
            while (i < length2) {
                arrayList.add(Boolean.valueOf(zArr[i]));
                i++;
            }
        } else if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            int length3 = jArr.length;
            while (i < length3) {
                arrayList.add(Long.valueOf(jArr[i]));
                i++;
            }
        } else if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            int length4 = fArr.length;
            while (i < length4) {
                arrayList.add(Float.valueOf(fArr[i]));
                i++;
            }
        } else if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            int length5 = dArr.length;
            while (i < length5) {
                arrayList.add(Double.valueOf(dArr[i]));
                i++;
            }
        } else if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            int length6 = sArr.length;
            while (i < length6) {
                arrayList.add(Short.valueOf(sArr[i]));
                i++;
            }
        } else if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            int length7 = bArr.length;
            while (i < length7) {
                arrayList.add(Byte.valueOf(bArr[i]));
                i++;
            }
        } else if (obj instanceof char[]) {
            char[] cArr = (char[]) obj;
            int length8 = cArr.length;
            while (i < length8) {
                arrayList.add(Character.valueOf(cArr[i]));
                i++;
            }
        }
        return arrayList;
    }

    public static String encode(CharSequence charSequence) throws HttpRequestException {
        int port;
        String str;
        int i;
        try {
            URL url = new URL(charSequence.toString());
            String host = url.getHost();
            if (url.getPort() != -1) {
                str = host + ':' + Integer.toString(port);
            } else {
                str = host;
            }
            try {
                String aSCIIString = new URI(url.getProtocol(), str, url.getPath(), url.getQuery(), null).toASCIIString();
                int indexOf = aSCIIString.indexOf(63);
                if (indexOf <= 0 || (i = indexOf + 1) >= aSCIIString.length()) {
                    return aSCIIString;
                }
                return aSCIIString.substring(0, i) + aSCIIString.substring(i).replace(Marker.ANY_NON_NULL_MARKER, "%2B");
            } catch (URISyntaxException e) {
                IOException iOException = new IOException("Parsing URI failed");
                iOException.initCause(e);
                throw new HttpRequestException(iOException);
            }
        } catch (IOException e2) {
            throw new HttpRequestException(e2);
        }
    }

    public static String append(CharSequence charSequence, Map<?, ?> map) {
        String charSequence2 = charSequence.toString();
        if (map == null || map.isEmpty()) {
            return charSequence2;
        }
        StringBuilder sb = new StringBuilder(charSequence2);
        addPathSeparator(charSequence2, sb);
        addParamPrefix(charSequence2, sb);
        Iterator<Map.Entry<?, ?>> it = map.entrySet().iterator();
        Map.Entry<?, ?> next = it.next();
        addParam(next.getKey().toString(), next.getValue(), sb);
        while (it.hasNext()) {
            sb.append(Typography.f21051c);
            Map.Entry<?, ?> next2 = it.next();
            addParam(next2.getKey().toString(), next2.getValue(), sb);
        }
        return sb.toString();
    }

    public static String append(CharSequence charSequence, Object... objArr) {
        String charSequence2 = charSequence.toString();
        if (objArr == null || objArr.length == 0) {
            return charSequence2;
        }
        if (objArr.length % 2 == 0) {
            StringBuilder sb = new StringBuilder(charSequence2);
            addPathSeparator(charSequence2, sb);
            addParamPrefix(charSequence2, sb);
            addParam(objArr[0], objArr[1], sb);
            for (int i = 2; i < objArr.length; i += 2) {
                sb.append(Typography.f21051c);
                addParam(objArr[i], objArr[i + 1], sb);
            }
            return sb.toString();
        }
        throw new IllegalArgumentException("Must specify an even number of parameter names/values");
    }

    public static HttpRequest get(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, "GET");
    }

    public static HttpRequest get(URL url) throws HttpRequestException {
        return new HttpRequest(url, "GET");
    }

    public static HttpRequest get(CharSequence charSequence, Map<?, ?> map, boolean z) {
        String append = append(charSequence, map);
        if (z) {
            append = encode(append);
        }
        return get(append);
    }

    public static HttpRequest get(CharSequence charSequence, boolean z, Object... objArr) {
        String append = append(charSequence, objArr);
        if (z) {
            append = encode(append);
        }
        return get(append);
    }

    public static HttpRequest post(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, "POST");
    }

    public static HttpRequest post(URL url) throws HttpRequestException {
        return new HttpRequest(url, "POST");
    }

    public static HttpRequest post(CharSequence charSequence, Map<?, ?> map, boolean z) {
        String append = append(charSequence, map);
        if (z) {
            append = encode(append);
        }
        return post(append);
    }

    public static HttpRequest post(CharSequence charSequence, boolean z, Object... objArr) {
        String append = append(charSequence, objArr);
        if (z) {
            append = encode(append);
        }
        return post(append);
    }

    public static HttpRequest put(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, "PUT");
    }

    public static HttpRequest put(URL url) throws HttpRequestException {
        return new HttpRequest(url, "PUT");
    }

    public static HttpRequest put(CharSequence charSequence, Map<?, ?> map, boolean z) {
        String append = append(charSequence, map);
        if (z) {
            append = encode(append);
        }
        return put(append);
    }

    public static HttpRequest put(CharSequence charSequence, boolean z, Object... objArr) {
        String append = append(charSequence, objArr);
        if (z) {
            append = encode(append);
        }
        return put(append);
    }

    public static HttpRequest delete(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, "DELETE");
    }

    public static HttpRequest delete(URL url) throws HttpRequestException {
        return new HttpRequest(url, "DELETE");
    }

    public static HttpRequest delete(CharSequence charSequence, Map<?, ?> map, boolean z) {
        String append = append(charSequence, map);
        if (z) {
            append = encode(append);
        }
        return delete(append);
    }

    public static HttpRequest delete(CharSequence charSequence, boolean z, Object... objArr) {
        String append = append(charSequence, objArr);
        if (z) {
            append = encode(append);
        }
        return delete(append);
    }

    public static HttpRequest head(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, "HEAD");
    }

    public static HttpRequest head(URL url) throws HttpRequestException {
        return new HttpRequest(url, "HEAD");
    }

    public static HttpRequest head(CharSequence charSequence, Map<?, ?> map, boolean z) {
        String append = append(charSequence, map);
        if (z) {
            append = encode(append);
        }
        return head(append);
    }

    public static HttpRequest head(CharSequence charSequence, boolean z, Object... objArr) {
        String append = append(charSequence, objArr);
        if (z) {
            append = encode(append);
        }
        return head(append);
    }

    public static HttpRequest options(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, "OPTIONS");
    }

    public static HttpRequest options(URL url) throws HttpRequestException {
        return new HttpRequest(url, "OPTIONS");
    }

    public static HttpRequest trace(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, "TRACE");
    }

    public static HttpRequest trace(URL url) throws HttpRequestException {
        return new HttpRequest(url, "TRACE");
    }

    public static void keepAlive(boolean z) {
        setProperty("http.keepAlive", Boolean.toString(z));
    }

    public static void maxConnections(int i) {
        setProperty("http.maxConnections", Integer.toString(i));
    }

    public static void proxyHost(String str) {
        setProperty(ProxySetup.HTTP_PROXY_HOST, str);
        setProperty(ProxySetup.HTTPS_PROXY_HOST, str);
    }

    public static void proxyPort(int i) {
        String num = Integer.toString(i);
        setProperty(ProxySetup.HTTP_PROXY_PORT, num);
        setProperty(ProxySetup.HTTPS_PROXY_PORT, num);
    }

    public static void nonProxyHosts(String... strArr) {
        if (strArr == null || strArr.length <= 0) {
            setProperty(ProxySetup.HTTP_NON_PROXY_HOSTS, null);
            return;
        }
        StringBuilder sb = new StringBuilder();
        int length = strArr.length - 1;
        for (int i = 0; i < length; i++) {
            sb.append(strArr[i]);
            sb.append('|');
        }
        sb.append(strArr[length]);
        setProperty(ProxySetup.HTTP_NON_PROXY_HOSTS, sb.toString());
    }

    private static String setProperty(final String str, final String str2) {
        PrivilegedAction<String> privilegedAction;
        if (str2 != null) {
            privilegedAction = new PrivilegedAction<String>() { // from class: com.github.kevinsawicki.http.HttpRequest.3
                @Override // java.security.PrivilegedAction
                public final String run() {
                    return System.setProperty(str, str2);
                }
            };
        } else {
            privilegedAction = new PrivilegedAction<String>() { // from class: com.github.kevinsawicki.http.HttpRequest.4
                @Override // java.security.PrivilegedAction
                public final String run() {
                    return System.clearProperty(str);
                }
            };
        }
        return (String) AccessController.doPrivileged(privilegedAction);
    }

    public HttpRequest(CharSequence charSequence, String str) throws HttpRequestException {
        try {
            this.url = new URL(charSequence.toString());
            this.requestMethod = str;
        } catch (MalformedURLException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest(URL url, String str) throws HttpRequestException {
        this.url = url;
        this.requestMethod = str;
    }

    private Proxy createProxy() {
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.httpProxyHost, this.httpProxyPort));
    }

    private HttpURLConnection createConnection() {
        HttpURLConnection httpURLConnection;
        try {
            if (this.httpProxyHost != null) {
                httpURLConnection = CONNECTION_FACTORY.create(this.url, createProxy());
            } else {
                httpURLConnection = CONNECTION_FACTORY.create(this.url);
            }
            httpURLConnection.setRequestMethod(this.requestMethod);
            return httpURLConnection;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public String toString() {
        return method() + ' ' + url();
    }

    public HttpURLConnection getConnection() {
        if (this.connection == null) {
            this.connection = createConnection();
        }
        return this.connection;
    }

    public HttpRequest ignoreCloseExceptions(boolean z) {
        this.ignoreCloseExceptions = z;
        return this;
    }

    public boolean ignoreCloseExceptions() {
        return this.ignoreCloseExceptions;
    }

    public int code() throws HttpRequestException {
        try {
            closeOutput();
            return getConnection().getResponseCode();
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest code(AtomicInteger atomicInteger) throws HttpRequestException {
        atomicInteger.set(code());
        return this;
    }

    /* renamed from: ok */
    public boolean m20392ok() throws HttpRequestException {
        return 200 == code();
    }

    public boolean created() throws HttpRequestException {
        return 201 == code();
    }

    public boolean noContent() throws HttpRequestException {
        return 204 == code();
    }

    public boolean serverError() throws HttpRequestException {
        return 500 == code();
    }

    public boolean badRequest() throws HttpRequestException {
        return 400 == code();
    }

    public boolean notFound() throws HttpRequestException {
        return 404 == code();
    }

    public boolean notModified() throws HttpRequestException {
        return 304 == code();
    }

    public String message() throws HttpRequestException {
        try {
            closeOutput();
            return getConnection().getResponseMessage();
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest disconnect() {
        getConnection().disconnect();
        return this;
    }

    public HttpRequest chunk(int i) {
        getConnection().setChunkedStreamingMode(i);
        return this;
    }

    public HttpRequest bufferSize(int i) {
        if (i > 0) {
            this.bufferSize = i;
            return this;
        }
        throw new IllegalArgumentException("Size must be greater than zero");
    }

    public int bufferSize() {
        return this.bufferSize;
    }

    public HttpRequest uncompress(boolean z) {
        this.uncompress = z;
        return this;
    }

    protected ByteArrayOutputStream byteStream() {
        int contentLength = contentLength();
        if (contentLength > 0) {
            return new ByteArrayOutputStream(contentLength);
        }
        return new ByteArrayOutputStream();
    }

    public String body(String str) throws HttpRequestException {
        ByteArrayOutputStream byteStream = byteStream();
        try {
            copy(buffer(), byteStream);
            return byteStream.toString(getValidCharset(str));
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public String body() throws HttpRequestException {
        return body(charset());
    }

    public HttpRequest body(AtomicReference<String> atomicReference) throws HttpRequestException {
        atomicReference.set(body());
        return this;
    }

    public HttpRequest body(AtomicReference<String> atomicReference, String str) throws HttpRequestException {
        atomicReference.set(body(str));
        return this;
    }

    public boolean isBodyEmpty() throws HttpRequestException {
        return contentLength() == 0;
    }

    public byte[] bytes() throws HttpRequestException {
        ByteArrayOutputStream byteStream = byteStream();
        try {
            copy(buffer(), byteStream);
            return byteStream.toByteArray();
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public BufferedInputStream buffer() throws HttpRequestException {
        return new BufferedInputStream(stream(), this.bufferSize);
    }

    public InputStream stream() throws HttpRequestException {
        InputStream inputStream;
        if (code() < 400) {
            try {
                inputStream = getConnection().getInputStream();
            } catch (IOException e) {
                throw new HttpRequestException(e);
            }
        } else {
            inputStream = getConnection().getErrorStream();
            if (inputStream == null) {
                try {
                    inputStream = getConnection().getInputStream();
                } catch (IOException e2) {
                    if (contentLength() <= 0) {
                        inputStream = new ByteArrayInputStream(new byte[0]);
                    } else {
                        throw new HttpRequestException(e2);
                    }
                }
            }
        }
        if (!this.uncompress || !ENCODING_GZIP.equals(contentEncoding())) {
            return inputStream;
        }
        try {
            return new GZIPInputStream(inputStream);
        } catch (IOException e3) {
            throw new HttpRequestException(e3);
        }
    }

    public InputStreamReader reader(String str) throws HttpRequestException {
        try {
            return new InputStreamReader(stream(), getValidCharset(str));
        } catch (UnsupportedEncodingException e) {
            throw new HttpRequestException(e);
        }
    }

    public InputStreamReader reader() throws HttpRequestException {
        return reader(charset());
    }

    public BufferedReader bufferedReader(String str) throws HttpRequestException {
        return new BufferedReader(reader(str), this.bufferSize);
    }

    public BufferedReader bufferedReader() throws HttpRequestException {
        return bufferedReader(charset());
    }

    public HttpRequest receive(File file) throws HttpRequestException {
        try {
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), this.bufferSize);
            return new CloseOperation<HttpRequest>(bufferedOutputStream, this.ignoreCloseExceptions) { // from class: com.github.kevinsawicki.http.HttpRequest.5
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.github.kevinsawicki.http.HttpRequest.Operation
                public HttpRequest run() throws HttpRequestException, IOException {
                    return HttpRequest.this.receive(bufferedOutputStream);
                }
            }.call();
        } catch (FileNotFoundException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest receive(OutputStream outputStream) throws HttpRequestException {
        try {
            return copy(buffer(), outputStream);
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest receive(PrintStream printStream) throws HttpRequestException {
        return receive((OutputStream) printStream);
    }

    public HttpRequest receive(final Appendable appendable) throws HttpRequestException {
        final BufferedReader bufferedReader = bufferedReader();
        return new CloseOperation<HttpRequest>(bufferedReader, this.ignoreCloseExceptions) { // from class: com.github.kevinsawicki.http.HttpRequest.6
            @Override // com.github.kevinsawicki.http.HttpRequest.Operation
            public HttpRequest run() throws IOException {
                CharBuffer allocate = CharBuffer.allocate(HttpRequest.this.bufferSize);
                while (true) {
                    int read = bufferedReader.read(allocate);
                    if (read == -1) {
                        return HttpRequest.this;
                    }
                    allocate.rewind();
                    appendable.append(allocate, 0, read);
                    allocate.rewind();
                }
            }
        }.call();
    }

    public HttpRequest receive(final Writer writer) throws HttpRequestException {
        final BufferedReader bufferedReader = bufferedReader();
        return new CloseOperation<HttpRequest>(bufferedReader, this.ignoreCloseExceptions) { // from class: com.github.kevinsawicki.http.HttpRequest.7
            @Override // com.github.kevinsawicki.http.HttpRequest.Operation
            public HttpRequest run() throws IOException {
                return HttpRequest.this.copy(bufferedReader, writer);
            }
        }.call();
    }

    public HttpRequest readTimeout(int i) {
        getConnection().setReadTimeout(i);
        return this;
    }

    public HttpRequest connectTimeout(int i) {
        getConnection().setConnectTimeout(i);
        return this;
    }

    public HttpRequest header(String str, String str2) {
        getConnection().setRequestProperty(str, str2);
        return this;
    }

    public HttpRequest header(String str, Number number) {
        return header(str, number != null ? number.toString() : null);
    }

    public HttpRequest headers(Map<String, String> map) {
        if (!map.isEmpty()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                header(entry);
            }
        }
        return this;
    }

    public HttpRequest header(Map.Entry<String, String> entry) {
        return header(entry.getKey(), entry.getValue());
    }

    public String header(String str) throws HttpRequestException {
        closeOutputQuietly();
        return getConnection().getHeaderField(str);
    }

    public Map<String, List<String>> headers() throws HttpRequestException {
        closeOutputQuietly();
        return getConnection().getHeaderFields();
    }

    public long dateHeader(String str) throws HttpRequestException {
        return dateHeader(str, -1L);
    }

    public long dateHeader(String str, long j) throws HttpRequestException {
        closeOutputQuietly();
        return getConnection().getHeaderFieldDate(str, j);
    }

    public int intHeader(String str) throws HttpRequestException {
        return intHeader(str, -1);
    }

    public int intHeader(String str, int i) throws HttpRequestException {
        closeOutputQuietly();
        return getConnection().getHeaderFieldInt(str, i);
    }

    public String[] headers(String str) {
        Map<String, List<String>> headers = headers();
        if (headers == null || headers.isEmpty()) {
            return EMPTY_STRINGS;
        }
        List<String> list = headers.get(str);
        if (list == null || list.isEmpty()) {
            return EMPTY_STRINGS;
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    public String parameter(String str, String str2) {
        return getParam(header(str), str2);
    }

    public Map<String, String> parameters(String str) {
        return getParams(header(str));
    }

    protected Map<String, String> getParams(String str) {
        String trim;
        int length;
        if (str == null || str.length() == 0) {
            return Collections.emptyMap();
        }
        int length2 = str.length();
        int indexOf = str.indexOf(59) + 1;
        if (indexOf == 0 || indexOf == length2) {
            return Collections.emptyMap();
        }
        int indexOf2 = str.indexOf(59, indexOf);
        if (indexOf2 == -1) {
            indexOf2 = length2;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        while (indexOf < indexOf2) {
            int indexOf3 = str.indexOf(61, indexOf);
            if (indexOf3 != -1 && indexOf3 < indexOf2) {
                String trim2 = str.substring(indexOf, indexOf3).trim();
                if (trim2.length() > 0 && (length = (trim = str.substring(indexOf3 + 1, indexOf2).trim()).length()) != 0) {
                    if (length > 2 && '\"' == trim.charAt(0)) {
                        int i = length - 1;
                        if ('\"' == trim.charAt(i)) {
                            linkedHashMap.put(trim2, trim.substring(1, i));
                        }
                    }
                    linkedHashMap.put(trim2, trim);
                }
            }
            indexOf = indexOf2 + 1;
            indexOf2 = str.indexOf(59, indexOf);
            if (indexOf2 == -1) {
                indexOf2 = length2;
            }
        }
        return linkedHashMap;
    }

    protected String getParam(String str, String str2) {
        String trim;
        int length;
        if (str == null || str.length() == 0) {
            return null;
        }
        int length2 = str.length();
        int indexOf = str.indexOf(59) + 1;
        if (indexOf == 0 || indexOf == length2) {
            return null;
        }
        int indexOf2 = str.indexOf(59, indexOf);
        if (indexOf2 == -1) {
            indexOf2 = length2;
        }
        while (indexOf < indexOf2) {
            int indexOf3 = str.indexOf(61, indexOf);
            if (indexOf3 == -1 || indexOf3 >= indexOf2 || !str2.equals(str.substring(indexOf, indexOf3).trim()) || (length = (trim = str.substring(indexOf3 + 1, indexOf2).trim()).length()) == 0) {
                indexOf = indexOf2 + 1;
                indexOf2 = str.indexOf(59, indexOf);
                if (indexOf2 == -1) {
                    indexOf2 = length2;
                }
            } else {
                if (length > 2 && '\"' == trim.charAt(0)) {
                    int i = length - 1;
                    if ('\"' == trim.charAt(i)) {
                        return trim.substring(1, i);
                    }
                }
                return trim;
            }
        }
        return null;
    }

    public String charset() {
        return parameter("Content-Type", PARAM_CHARSET);
    }

    public HttpRequest userAgent(String str) {
        return header("User-Agent", str);
    }

    public HttpRequest referer(String str) {
        return header(HEADER_REFERER, str);
    }

    public HttpRequest useCaches(boolean z) {
        getConnection().setUseCaches(z);
        return this;
    }

    public HttpRequest acceptEncoding(String str) {
        return header(HEADER_ACCEPT_ENCODING, str);
    }

    public HttpRequest acceptGzipEncoding() {
        return acceptEncoding(ENCODING_GZIP);
    }

    public HttpRequest acceptCharset(String str) {
        return header(HEADER_ACCEPT_CHARSET, str);
    }

    public String contentEncoding() {
        return header("Content-Encoding");
    }

    public String server() {
        return header("Server");
    }

    public long date() {
        return dateHeader("Date");
    }

    public String cacheControl() {
        return header(HEADER_CACHE_CONTROL);
    }

    public String eTag() {
        return header(HEADER_ETAG);
    }

    public long expires() {
        return dateHeader(HEADER_EXPIRES);
    }

    public long lastModified() {
        return dateHeader(HEADER_LAST_MODIFIED);
    }

    public String location() {
        return header(HEADER_LOCATION);
    }

    public HttpRequest authorization(String str) {
        return header("Authorization", str);
    }

    public HttpRequest proxyAuthorization(String str) {
        return header("Proxy-Authorization", str);
    }

    public HttpRequest basic(String str, String str2) {
        return authorization("Basic " + Base64.encode(str + ':' + str2));
    }

    public HttpRequest proxyBasic(String str, String str2) {
        return proxyAuthorization("Basic " + Base64.encode(str + ':' + str2));
    }

    public HttpRequest ifModifiedSince(long j) {
        getConnection().setIfModifiedSince(j);
        return this;
    }

    public HttpRequest ifNoneMatch(String str) {
        return header(HEADER_IF_NONE_MATCH, str);
    }

    public HttpRequest contentType(String str) {
        return contentType(str, null);
    }

    public HttpRequest contentType(String str, String str2) {
        if (str2 == null || str2.length() <= 0) {
            return header("Content-Type", str);
        }
        return header("Content-Type", str + HTTP.CHARSET_PARAM + str2);
    }

    public String contentType() {
        return header("Content-Type");
    }

    public int contentLength() {
        return intHeader("Content-Length");
    }

    public HttpRequest contentLength(String str) {
        return contentLength(Integer.parseInt(str));
    }

    public HttpRequest contentLength(int i) {
        getConnection().setFixedLengthStreamingMode(i);
        return this;
    }

    public HttpRequest accept(String str) {
        return header(HEADER_ACCEPT, str);
    }

    public HttpRequest acceptJson() {
        return accept("application/json");
    }

    protected HttpRequest copy(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        return new CloseOperation<HttpRequest>(inputStream, this.ignoreCloseExceptions) { // from class: com.github.kevinsawicki.http.HttpRequest.8
            @Override // com.github.kevinsawicki.http.HttpRequest.Operation
            public HttpRequest run() throws IOException {
                byte[] bArr = new byte[HttpRequest.this.bufferSize];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        return HttpRequest.this;
                    }
                    outputStream.write(bArr, 0, read);
                    HttpRequest.this.totalWritten += read;
                    HttpRequest.this.progress.onUpload(HttpRequest.this.totalWritten, HttpRequest.this.totalSize);
                }
            }
        }.call();
    }

    protected HttpRequest copy(final Reader reader, final Writer writer) throws IOException {
        return new CloseOperation<HttpRequest>(reader, this.ignoreCloseExceptions) { // from class: com.github.kevinsawicki.http.HttpRequest.9
            @Override // com.github.kevinsawicki.http.HttpRequest.Operation
            public HttpRequest run() throws IOException {
                char[] cArr = new char[HttpRequest.this.bufferSize];
                while (true) {
                    int read = reader.read(cArr);
                    if (read == -1) {
                        return HttpRequest.this;
                    }
                    writer.write(cArr, 0, read);
                    HttpRequest.this.totalWritten += read;
                    HttpRequest.this.progress.onUpload(HttpRequest.this.totalWritten, -1L);
                }
            }
        }.call();
    }

    public HttpRequest progress(UploadProgress uploadProgress) {
        if (uploadProgress == null) {
            this.progress = UploadProgress.DEFAULT;
        } else {
            this.progress = uploadProgress;
        }
        return this;
    }

    private HttpRequest incrementTotalSize(long j) {
        if (this.totalSize == -1) {
            this.totalSize = 0L;
        }
        this.totalSize += j;
        return this;
    }

    protected HttpRequest closeOutput() throws IOException {
        progress(null);
        RequestOutputStream requestOutputStream = this.output;
        if (requestOutputStream == null) {
            return this;
        }
        if (this.multipart) {
            requestOutputStream.write("\r\n--00content0boundary00--\r\n");
        }
        if (this.ignoreCloseExceptions) {
            try {
                this.output.close();
            } catch (IOException unused) {
            }
        } else {
            this.output.close();
        }
        this.output = null;
        return this;
    }

    protected HttpRequest closeOutputQuietly() throws HttpRequestException {
        try {
            return closeOutput();
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    protected HttpRequest openOutput() throws IOException {
        if (this.output != null) {
            return this;
        }
        getConnection().setDoOutput(true);
        this.output = new RequestOutputStream(getConnection().getOutputStream(), getParam(getConnection().getRequestProperty("Content-Type"), PARAM_CHARSET), this.bufferSize);
        return this;
    }

    protected HttpRequest startPart() throws IOException {
        if (!this.multipart) {
            this.multipart = true;
            contentType(CONTENT_TYPE_MULTIPART).openOutput();
            this.output.write("--00content0boundary00\r\n");
        } else {
            this.output.write("\r\n--00content0boundary00\r\n");
        }
        return this;
    }

    protected HttpRequest writePartHeader(String str, String str2) throws IOException {
        return writePartHeader(str, str2, null);
    }

    protected HttpRequest writePartHeader(String str, String str2, String str3) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("form-data; name=\"");
        sb.append(str);
        if (str2 != null) {
            sb.append("\"; filename=\"");
            sb.append(str2);
        }
        sb.append(Typography.f21049a);
        partHeader(MIME.CONTENT_DISPOSITION, sb.toString());
        if (str3 != null) {
            partHeader("Content-Type", str3);
        }
        return send("\r\n");
    }

    public HttpRequest part(String str, String str2) {
        return part(str, (String) null, str2);
    }

    public HttpRequest part(String str, String str2, String str3) throws HttpRequestException {
        return part(str, str2, (String) null, str3);
    }

    public HttpRequest part(String str, String str2, String str3, String str4) throws HttpRequestException {
        try {
            startPart();
            writePartHeader(str, str2, str3);
            this.output.write(str4);
            return this;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest part(String str, Number number) throws HttpRequestException {
        return part(str, (String) null, number);
    }

    public HttpRequest part(String str, String str2, Number number) throws HttpRequestException {
        return part(str, str2, number != null ? number.toString() : null);
    }

    public HttpRequest part(String str, File file) throws HttpRequestException {
        return part(str, (String) null, file);
    }

    public HttpRequest part(String str, String str2, File file) throws HttpRequestException {
        return part(str, str2, (String) null, file);
    }

    public HttpRequest part(String str, String str2, String str3, File file) throws HttpRequestException {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            incrementTotalSize(file.length());
            return part(str, str2, str3, bufferedInputStream);
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest part(String str, InputStream inputStream) throws HttpRequestException {
        return part(str, (String) null, (String) null, inputStream);
    }

    public HttpRequest part(String str, String str2, String str3, InputStream inputStream) throws HttpRequestException {
        try {
            startPart();
            writePartHeader(str, str2, str3);
            copy(inputStream, this.output);
            return this;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest partHeader(String str, String str2) throws HttpRequestException {
        return send(str).send(": ").send(str2).send("\r\n");
    }

    public HttpRequest send(File file) throws HttpRequestException {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            incrementTotalSize(file.length());
            return send(bufferedInputStream);
        } catch (FileNotFoundException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest send(byte[] bArr) throws HttpRequestException {
        if (bArr != null) {
            incrementTotalSize(bArr.length);
        }
        return send(new ByteArrayInputStream(bArr));
    }

    public HttpRequest send(InputStream inputStream) throws HttpRequestException {
        try {
            openOutput();
            copy(inputStream, this.output);
            return this;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest send(final Reader reader) throws HttpRequestException {
        try {
            openOutput();
            RequestOutputStream requestOutputStream = this.output;
            final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(requestOutputStream, requestOutputStream.encoder.charset());
            return new FlushOperation<HttpRequest>(outputStreamWriter) { // from class: com.github.kevinsawicki.http.HttpRequest.10
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.github.kevinsawicki.http.HttpRequest.Operation
                public HttpRequest run() throws IOException {
                    return HttpRequest.this.copy(reader, outputStreamWriter);
                }
            }.call();
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest send(CharSequence charSequence) throws HttpRequestException {
        try {
            openOutput();
            this.output.write(charSequence.toString());
            return this;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public OutputStreamWriter writer() throws HttpRequestException {
        try {
            openOutput();
            return new OutputStreamWriter(this.output, this.output.encoder.charset());
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest form(Map<?, ?> map) throws HttpRequestException {
        return form(map, "UTF-8");
    }

    public HttpRequest form(Map.Entry<?, ?> entry) throws HttpRequestException {
        return form(entry, "UTF-8");
    }

    public HttpRequest form(Map.Entry<?, ?> entry, String str) throws HttpRequestException {
        return form(entry.getKey(), entry.getValue(), str);
    }

    public HttpRequest form(Object obj, Object obj2) throws HttpRequestException {
        return form(obj, obj2, "UTF-8");
    }

    public HttpRequest form(Object obj, Object obj2, String str) throws HttpRequestException {
        boolean z = !this.form;
        if (z) {
            contentType("application/x-www-form-urlencoded", str);
            this.form = true;
        }
        String validCharset = getValidCharset(str);
        try {
            openOutput();
            if (!z) {
                this.output.write(38);
            }
            this.output.write(URLEncoder.encode(obj.toString(), validCharset));
            this.output.write(61);
            if (obj2 != null) {
                this.output.write(URLEncoder.encode(obj2.toString(), validCharset));
            }
            return this;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest form(Map<?, ?> map, String str) throws HttpRequestException {
        if (!map.isEmpty()) {
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                form(entry, str);
            }
        }
        return this;
    }

    public HttpRequest trustAllCerts() throws HttpRequestException {
        HttpURLConnection connection = getConnection();
        if (connection instanceof HttpsURLConnection) {
            ((HttpsURLConnection) connection).setSSLSocketFactory(getTrustedFactory());
        }
        return this;
    }

    public HttpRequest trustAllHosts() {
        HttpURLConnection connection = getConnection();
        if (connection instanceof HttpsURLConnection) {
            ((HttpsURLConnection) connection).setHostnameVerifier(getTrustedVerifier());
        }
        return this;
    }

    public URL url() {
        return getConnection().getURL();
    }

    public String method() {
        return getConnection().getRequestMethod();
    }

    public HttpRequest useProxy(String str, int i) {
        if (this.connection == null) {
            this.httpProxyHost = str;
            this.httpProxyPort = i;
            return this;
        }
        throw new IllegalStateException("The connection has already been created. This method must be called before reading or writing to the request.");
    }

    public HttpRequest followRedirects(boolean z) {
        getConnection().setInstanceFollowRedirects(z);
        return this;
    }
}
