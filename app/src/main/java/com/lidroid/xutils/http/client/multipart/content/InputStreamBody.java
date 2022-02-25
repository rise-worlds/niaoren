package com.lidroid.xutils.http.client.multipart.content;

import com.lidroid.xutils.util.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;

/* loaded from: classes.dex */
public class InputStreamBody extends AbstractContentBody {
    private final String filename;

    /* renamed from: in */
    private final InputStream f10237in;
    private long length;

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public String getCharset() {
        return null;
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public String getTransferEncoding() {
        return "binary";
    }

    public InputStreamBody(InputStream inputStream, long j, String str, String str2) {
        super(str2);
        if (inputStream != null) {
            this.f10237in = inputStream;
            this.filename = str;
            this.length = j;
            return;
        }
        throw new IllegalArgumentException("Input stream may not be null");
    }

    public InputStreamBody(InputStream inputStream, long j, String str) {
        this(inputStream, j, str, "application/octet-stream");
    }

    public InputStreamBody(InputStream inputStream, long j) {
        this(inputStream, j, "no_name", "application/octet-stream");
    }

    public InputStream getInputStream() {
        return this.f10237in;
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentBody
    public void writeTo(OutputStream outputStream) throws IOException {
        if (outputStream != null) {
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = this.f10237in.read(bArr);
                    if (read == -1) {
                        outputStream.flush();
                        return;
                    }
                    outputStream.write(bArr, 0, read);
                    this.callBackInfo.pos += read;
                    if (!this.callBackInfo.doCallBack(false)) {
                        throw new InterruptedIOException("cancel");
                    }
                }
            } finally {
                IOUtils.closeQuietly(this.f10237in);
            }
        } else {
            throw new IllegalArgumentException("Output stream may not be null");
        }
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public long getContentLength() {
        return this.length;
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentBody
    public String getFilename() {
        return this.filename;
    }
}
