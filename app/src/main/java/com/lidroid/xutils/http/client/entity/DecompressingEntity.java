package com.lidroid.xutils.http.client.entity;

import com.lidroid.xutils.http.callback.RequestCallBackHandler;
import com.lidroid.xutils.util.IOUtils;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.entity.HttpEntityWrapper;

/* loaded from: classes.dex */
abstract class DecompressingEntity extends HttpEntityWrapper implements UploadEntity {
    private InputStream content;
    private long uncompressedLength;
    private long uploadedSize = 0;
    private RequestCallBackHandler callBackHandler = null;

    abstract InputStream decorate(InputStream inputStream) throws IOException;

    @Override // org.apache.http.entity.HttpEntityWrapper, org.apache.http.HttpEntity
    public long getContentLength() {
        return -1L;
    }

    public DecompressingEntity(HttpEntity httpEntity) {
        super(httpEntity);
        this.uncompressedLength = httpEntity.getContentLength();
    }

    private InputStream getDecompressingStream() throws IOException {
        try {
            return decorate(this.wrappedEntity.getContent());
        } catch (IOException e) {
            IOUtils.closeQuietly((Closeable) null);
            throw e;
        }
    }

    @Override // org.apache.http.entity.HttpEntityWrapper, org.apache.http.HttpEntity
    public InputStream getContent() throws IOException {
        if (!this.wrappedEntity.isStreaming()) {
            return getDecompressingStream();
        }
        if (this.content == null) {
            this.content = getDecompressingStream();
        }
        return this.content;
    }

    @Override // org.apache.http.entity.HttpEntityWrapper, org.apache.http.HttpEntity
    public void writeTo(OutputStream outputStream) throws IOException {
        if (outputStream != null) {
            Closeable closeable = null;
            try {
                closeable = getContent();
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = closeable.read(bArr);
                    if (read == -1) {
                        outputStream.flush();
                        if (this.callBackHandler != null) {
                            this.callBackHandler.updateProgress(this.uncompressedLength, this.uploadedSize, true);
                        }
                        return;
                    }
                    outputStream.write(bArr, 0, read);
                    this.uploadedSize += read;
                    if (this.callBackHandler != null && !this.callBackHandler.updateProgress(this.uncompressedLength, this.uploadedSize, false)) {
                        throw new InterruptedIOException("cancel");
                    }
                }
            } finally {
                IOUtils.closeQuietly(closeable);
            }
        } else {
            throw new IllegalArgumentException("Output stream may not be null");
        }
    }

    @Override // com.lidroid.xutils.http.client.entity.UploadEntity
    public void setCallBackHandler(RequestCallBackHandler requestCallBackHandler) {
        this.callBackHandler = requestCallBackHandler;
    }
}
