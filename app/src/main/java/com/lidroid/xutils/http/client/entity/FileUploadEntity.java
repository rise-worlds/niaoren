package com.lidroid.xutils.http.client.entity;

import com.lidroid.xutils.http.callback.RequestCallBackHandler;
import com.lidroid.xutils.util.IOUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import org.apache.http.entity.FileEntity;

/* loaded from: classes.dex */
public class FileUploadEntity extends FileEntity implements UploadEntity {
    private long fileSize;
    private long uploadedSize = 0;
    private RequestCallBackHandler callBackHandler = null;

    public FileUploadEntity(File file, String str) {
        super(file, str);
        this.fileSize = file.length();
    }

    @Override // org.apache.http.entity.FileEntity, org.apache.http.HttpEntity
    public void writeTo(OutputStream outputStream) throws IOException {
        Throwable th;
        if (outputStream != null) {
            BufferedInputStream bufferedInputStream = null;
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(this.file));
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = bufferedInputStream.read(bArr);
                        if (read == -1) {
                            outputStream.flush();
                            if (this.callBackHandler != null) {
                                this.callBackHandler.updateProgress(this.fileSize, this.uploadedSize, true);
                            }
                            IOUtils.closeQuietly(bufferedInputStream);
                            return;
                        }
                        outputStream.write(bArr, 0, read);
                        this.uploadedSize += read;
                        if (this.callBackHandler != null && !this.callBackHandler.updateProgress(this.fileSize, this.uploadedSize, false)) {
                            throw new InterruptedIOException("cancel");
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    IOUtils.closeQuietly(bufferedInputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
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
