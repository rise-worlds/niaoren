package org.apache.commons.mail.p107b;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

/* renamed from: org.apache.commons.mail.b.b */
/* loaded from: classes2.dex */
public final class MimeMessageUtils {
    private MimeMessageUtils() {
    }

    /* renamed from: a */
    private static MimeMessage m14825a(Session session, byte[] bArr) throws MessagingException, IOException {
        Throwable th;
        ByteArrayInputStream byteArrayInputStream;
        ByteArrayInputStream byteArrayInputStream2 = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr);
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            MimeMessage mimeMessage = new MimeMessage(session, byteArrayInputStream);
            byteArrayInputStream.close();
            return mimeMessage;
        } catch (Throwable th3) {
            th = th3;
            byteArrayInputStream2 = byteArrayInputStream;
            if (byteArrayInputStream2 != null) {
                byteArrayInputStream2.close();
            }
            throw th;
        }
    }

    /* renamed from: a */
    private static MimeMessage m14828a(Session session, File file) throws MessagingException, IOException {
        Throwable th;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            MimeMessage a = m14827a(session, fileInputStream);
            fileInputStream.close();
            return a;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            throw th;
        }
    }

    /* renamed from: a */
    private static MimeMessage m14827a(Session session, InputStream inputStream) throws MessagingException {
        return new MimeMessage(session, inputStream);
    }

    /* renamed from: a */
    private static MimeMessage m14826a(Session session, String str) throws MessagingException, IOException {
        Throwable th;
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(str.getBytes());
            try {
                MimeMessage a = m14827a(session, byteArrayInputStream2);
                byteArrayInputStream2.close();
                return a;
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

    /* renamed from: a */
    private static void m14824a(MimeMessage mimeMessage, File file) throws MessagingException, IOException {
        Throwable th;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
                throw new IOException("Failed to create the following parent directories: " + file.getParentFile());
            }
            fileOutputStream = new FileOutputStream(file);
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            mimeMessage.writeTo(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            throw th;
        }
    }
}
