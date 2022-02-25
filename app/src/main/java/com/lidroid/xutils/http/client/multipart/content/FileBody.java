package com.lidroid.xutils.http.client.multipart.content;

import com.lidroid.xutils.util.IOUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;

/* loaded from: classes.dex */
public class FileBody extends AbstractContentBody {
    private final String charset;
    private final File file;
    private final String filename;

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public String getTransferEncoding() {
        return "binary";
    }

    public FileBody(File file, String str, String str2, String str3) {
        super(str2);
        if (file != null) {
            this.file = file;
            if (str != null) {
                this.filename = str;
            } else {
                this.filename = file.getName();
            }
            this.charset = str3;
            return;
        }
        throw new IllegalArgumentException("File may not be null");
    }

    public FileBody(File file, String str, String str2) {
        this(file, null, str, str2);
    }

    public FileBody(File file, String str) {
        this(file, null, str, null);
    }

    public FileBody(File file) {
        this(file, null, "application/octet-stream", null);
    }

    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentBody
    public void writeTo(OutputStream outputStream) throws IOException {
        Throwable th;
        if (outputStream != null) {
            BufferedInputStream bufferedInputStream = null;
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(this.file));
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read == -1) {
                        outputStream.flush();
                        IOUtils.closeQuietly(bufferedInputStream);
                        return;
                    }
                    outputStream.write(bArr, 0, read);
                    this.callBackInfo.pos += read;
                    if (!this.callBackInfo.doCallBack(false)) {
                        throw new InterruptedIOException("cancel");
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                IOUtils.closeQuietly(bufferedInputStream);
                throw th;
            }
        } else {
            throw new IllegalArgumentException("Output stream may not be null");
        }
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public String getCharset() {
        return this.charset;
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public long getContentLength() {
        return this.file.length();
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentBody
    public String getFilename() {
        return this.filename;
    }

    public File getFile() {
        return this.file;
    }
}
