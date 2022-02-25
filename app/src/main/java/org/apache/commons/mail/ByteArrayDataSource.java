package org.apache.commons.mail;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import javax.activation.DataSource;

@Deprecated
/* loaded from: classes2.dex */
public class ByteArrayDataSource implements DataSource {
    public static final int BUFFER_SIZE = 512;
    private ByteArrayOutputStream baos;
    private String name = "";
    private final String type;

    public ByteArrayDataSource(byte[] bArr, String str) throws IOException {
        Throwable th;
        this.type = str;
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArr);
            try {
                byteArrayDataSource(byteArrayInputStream2);
                byteArrayInputStream2.close();
            } catch (Throwable th2) {
                th = th2;
                byteArrayInputStream = byteArrayInputStream2;
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public ByteArrayDataSource(InputStream inputStream, String str) throws IOException {
        this.type = str;
        byteArrayDataSource(inputStream);
    }

    public ByteArrayDataSource(String str, String str2) throws IOException {
        this.type = str2;
        try {
            try {
                this.baos = new ByteArrayOutputStream();
                this.baos.write(str.getBytes("iso-8859-1"));
                this.baos.flush();
                this.baos.close();
            } catch (UnsupportedEncodingException unused) {
                throw new IOException("The Character Encoding is not supported.");
            }
        } finally {
            ByteArrayOutputStream byteArrayOutputStream = this.baos;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
        }
    }

    private void byteArrayDataSource(InputStream inputStream) throws IOException {
        BufferedInputStream bufferedInputStream;
        Throwable th;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            byte[] bArr = new byte[512];
            bufferedInputStream = new BufferedInputStream(inputStream);
            try {
                this.baos = new ByteArrayOutputStream();
                BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(this.baos);
                while (true) {
                    try {
                        int read = bufferedInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        bufferedOutputStream2.write(bArr, 0, read);
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedOutputStream = bufferedOutputStream2;
                        if (bufferedInputStream != null) {
                            bufferedInputStream.close();
                        }
                        ByteArrayOutputStream byteArrayOutputStream = this.baos;
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                        if (bufferedOutputStream != null) {
                            bufferedOutputStream.close();
                        }
                        throw th;
                    }
                }
                bufferedOutputStream2.flush();
                bufferedOutputStream2.close();
                bufferedInputStream.close();
                ByteArrayOutputStream byteArrayOutputStream2 = this.baos;
                if (byteArrayOutputStream2 != null) {
                    byteArrayOutputStream2.close();
                }
                bufferedOutputStream2.close();
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedInputStream = null;
        }
    }

    @Override // javax.activation.DataSource
    public String getContentType() {
        String str = this.type;
        return str == null ? "application/octet-stream" : str;
    }

    @Override // javax.activation.DataSource
    public InputStream getInputStream() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = this.baos;
        if (byteArrayOutputStream != null) {
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        }
        throw new IOException("no data");
    }

    public void setName(String str) {
        this.name = str;
    }

    @Override // javax.activation.DataSource
    public String getName() {
        return this.name;
    }

    @Override // javax.activation.DataSource
    public OutputStream getOutputStream() {
        this.baos = new ByteArrayOutputStream();
        return this.baos;
    }
}
