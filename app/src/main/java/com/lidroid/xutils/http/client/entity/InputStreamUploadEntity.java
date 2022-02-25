package com.lidroid.xutils.http.client.entity;

import android.support.p003v4.media.session.PlaybackStateCompat;
import com.lidroid.xutils.http.callback.RequestCallBackHandler;
import com.lidroid.xutils.util.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import org.apache.http.entity.AbstractHttpEntity;

/* loaded from: classes.dex */
public class InputStreamUploadEntity extends AbstractHttpEntity implements UploadEntity {
    private static final int BUFFER_SIZE = 2048;
    private final InputStream content;
    private final long length;
    private long uploadedSize = 0;
    private RequestCallBackHandler callBackHandler = null;

    @Override // org.apache.http.HttpEntity
    public boolean isRepeatable() {
        return false;
    }

    @Override // org.apache.http.HttpEntity
    public boolean isStreaming() {
        return true;
    }

    public InputStreamUploadEntity(InputStream inputStream, long j) {
        if (inputStream != null) {
            this.content = inputStream;
            this.length = j;
            return;
        }
        throw new IllegalArgumentException("Source input stream may not be null");
    }

    @Override // org.apache.http.HttpEntity
    public long getContentLength() {
        return this.length;
    }

    @Override // org.apache.http.HttpEntity
    public InputStream getContent() throws IOException {
        return this.content;
    }

    @Override // org.apache.http.HttpEntity
    public void writeTo(OutputStream outputStream) throws IOException {
        int read;
        if (outputStream != null) {
            InputStream inputStream = this.content;
            try {
                byte[] bArr = new byte[2048];
                if (this.length < 0) {
                    while (true) {
                        int read2 = inputStream.read(bArr);
                        if (read2 == -1) {
                            break;
                        }
                        outputStream.write(bArr, 0, read2);
                        this.uploadedSize += read2;
                        if (this.callBackHandler != null && !this.callBackHandler.updateProgress(this.uploadedSize + 1, this.uploadedSize, false)) {
                            throw new InterruptedIOException("cancel");
                        }
                    }
                } else {
                    long j = this.length;
                    while (j > 0 && (read = inputStream.read(bArr, 0, (int) Math.min((long) PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH, j))) != -1) {
                        outputStream.write(bArr, 0, read);
                        long j2 = read;
                        j -= j2;
                        this.uploadedSize += j2;
                        if (this.callBackHandler != null && !this.callBackHandler.updateProgress(this.length, this.uploadedSize, false)) {
                            throw new InterruptedIOException("cancel");
                        }
                    }
                }
                outputStream.flush();
                if (this.callBackHandler != null) {
                    this.callBackHandler.updateProgress(this.length, this.uploadedSize, true);
                }
            } finally {
                IOUtils.closeQuietly(inputStream);
            }
        } else {
            throw new IllegalArgumentException("Output stream may not be null");
        }
    }

    @Override // org.apache.http.entity.AbstractHttpEntity, org.apache.http.HttpEntity
    public void consumeContent() throws IOException {
        this.content.close();
    }

    @Override // com.lidroid.xutils.http.client.entity.UploadEntity
    public void setCallBackHandler(RequestCallBackHandler requestCallBackHandler) {
        this.callBackHandler = requestCallBackHandler;
    }
}
