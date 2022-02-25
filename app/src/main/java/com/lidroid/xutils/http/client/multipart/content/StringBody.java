package com.lidroid.xutils.http.client.multipart.content;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* loaded from: classes.dex */
public class StringBody extends AbstractContentBody {
    private final Charset charset;
    private final byte[] content;

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentBody
    public String getFilename() {
        return null;
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public String getTransferEncoding() {
        return "8bit";
    }

    public static StringBody create(String str, String str2, Charset charset) throws IllegalArgumentException {
        try {
            return new StringBody(str, str2, charset);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Charset " + charset + " is not supported", e);
        }
    }

    public static StringBody create(String str, Charset charset) throws IllegalArgumentException {
        return create(str, null, charset);
    }

    public static StringBody create(String str) throws IllegalArgumentException {
        return create(str, null, null);
    }

    public StringBody(String str, String str2, Charset charset) throws UnsupportedEncodingException {
        super(str2);
        if (str != null) {
            charset = charset == null ? Charset.forName("UTF-8") : charset;
            this.content = str.getBytes(charset.name());
            this.charset = charset;
            return;
        }
        throw new IllegalArgumentException("Text may not be null");
    }

    public StringBody(String str, Charset charset) throws UnsupportedEncodingException {
        this(str, "text/plain", charset);
    }

    public StringBody(String str) throws UnsupportedEncodingException {
        this(str, "text/plain", null);
    }

    public Reader getReader() {
        return new InputStreamReader(new ByteArrayInputStream(this.content), this.charset);
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentBody
    public void writeTo(OutputStream outputStream) throws IOException {
        if (outputStream != null) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.content);
            byte[] bArr = new byte[4096];
            do {
                int read = byteArrayInputStream.read(bArr);
                if (read == -1) {
                    outputStream.flush();
                    return;
                }
                outputStream.write(bArr, 0, read);
                this.callBackInfo.pos += read;
            } while (this.callBackInfo.doCallBack(false));
            throw new InterruptedIOException("cancel");
        }
        throw new IllegalArgumentException("Output stream may not be null");
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public String getCharset() {
        return this.charset.name();
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public long getContentLength() {
        return this.content.length;
    }
}
