package com.lidroid.xutils.http;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.util.IOUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import org.apache.http.HttpResponse;

/* loaded from: classes.dex */
public class ResponseStream extends InputStream {
    private String _directResult;
    private HttpResponse baseResponse;
    private InputStream baseStream;
    private String charset;
    private long expiry;
    private String requestMethod;
    private String requestUrl;

    public ResponseStream(HttpResponse httpResponse, String str, long j) throws IOException {
        this(httpResponse, "UTF-8", str, j);
    }

    public ResponseStream(HttpResponse httpResponse, String str, String str2, long j) throws IOException {
        if (httpResponse != null) {
            this.baseResponse = httpResponse;
            this.baseStream = httpResponse.getEntity().getContent();
            this.charset = str;
            this.requestUrl = str2;
            this.expiry = j;
            return;
        }
        throw new IllegalArgumentException("baseResponse may not be null");
    }

    public ResponseStream(String str) throws IOException {
        if (str != null) {
            this._directResult = str;
            return;
        }
        throw new IllegalArgumentException("result may not be null");
    }

    public String getRequestUrl() {
        return this.requestUrl;
    }

    public String getRequestMethod() {
        return this.requestMethod;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRequestMethod(String str) {
        this.requestMethod = str;
    }

    public InputStream getBaseStream() {
        return this.baseStream;
    }

    public HttpResponse getBaseResponse() {
        return this.baseResponse;
    }

    public int getStatusCode() {
        if (this._directResult != null) {
            return 200;
        }
        return this.baseResponse.getStatusLine().getStatusCode();
    }

    public Locale getLocale() {
        return this._directResult != null ? Locale.getDefault() : this.baseResponse.getLocale();
    }

    public String getReasonPhrase() {
        return this._directResult != null ? "" : this.baseResponse.getStatusLine().getReasonPhrase();
    }

    public String readString() throws IOException {
        String str = this._directResult;
        if (str != null) {
            return str;
        }
        InputStream inputStream = this.baseStream;
        if (inputStream == null) {
            return null;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, this.charset));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            this._directResult = sb.toString();
            if (this.requestUrl != null && HttpUtils.sHttpCache.isEnabled(this.requestMethod)) {
                HttpUtils.sHttpCache.put(this.requestUrl, this._directResult, this.expiry);
            }
            return this._directResult;
        } finally {
            IOUtils.closeQuietly(this.baseStream);
        }
    }

    public void readFile(String str) throws IOException {
        Throwable th;
        if (this._directResult == null && this.baseStream != null) {
            BufferedOutputStream bufferedOutputStream = null;
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str));
                try {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(this.baseStream);
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = bufferedInputStream.read(bArr);
                        if (read == -1) {
                            bufferedOutputStream.flush();
                            IOUtils.closeQuietly(bufferedOutputStream);
                            IOUtils.closeQuietly(this.baseStream);
                            return;
                        }
                        bufferedOutputStream.write(bArr, 0, read);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    IOUtils.closeQuietly(bufferedOutputStream);
                    IOUtils.closeQuietly(this.baseStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        InputStream inputStream = this.baseStream;
        if (inputStream == null) {
            return -1;
        }
        return inputStream.read();
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        InputStream inputStream = this.baseStream;
        if (inputStream == null) {
            return 0;
        }
        return inputStream.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        InputStream inputStream = this.baseStream;
        if (inputStream != null) {
            inputStream.close();
        }
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        InputStream inputStream = this.baseStream;
        if (inputStream != null) {
            inputStream.mark(i);
        }
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        InputStream inputStream = this.baseStream;
        if (inputStream == null) {
            return false;
        }
        return inputStream.markSupported();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        InputStream inputStream = this.baseStream;
        if (inputStream == null) {
            return -1;
        }
        return inputStream.read(bArr);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        InputStream inputStream = this.baseStream;
        if (inputStream == null) {
            return -1;
        }
        return inputStream.read(bArr, i, i2);
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        if (this.baseStream != null) {
            this.baseStream.reset();
        }
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        InputStream inputStream = this.baseStream;
        if (inputStream == null) {
            return 0L;
        }
        return inputStream.skip(j);
    }

    public long getContentLength() {
        if (this.baseStream == null) {
            return 0L;
        }
        return this.baseResponse.getEntity().getContentLength();
    }
}
