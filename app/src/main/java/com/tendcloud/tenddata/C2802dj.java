package com.tendcloud.tenddata;

import android.annotation.SuppressLint;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.zip.GZIPOutputStream;
import org.apache.tools.tar.TarConstants;

/* compiled from: td */
@SuppressLint({"Assert"})
/* renamed from: com.tendcloud.tenddata.dj */
/* loaded from: classes2.dex */
public class C2802dj {

    /* renamed from: a */
    public static final int f13736a = 0;

    /* renamed from: b */
    public static final int f13737b = 1;

    /* renamed from: c */
    public static final int f13738c = 0;

    /* renamed from: d */
    public static final int f13739d = 2;

    /* renamed from: e */
    public static final int f13740e = 4;

    /* renamed from: f */
    public static final int f13741f = 8;

    /* renamed from: g */
    public static final int f13742g = 16;

    /* renamed from: h */
    public static final int f13743h = 32;

    /* renamed from: j */
    private static final int f13745j = 76;

    /* renamed from: l */
    private static final byte f13747l = 10;

    /* renamed from: m */
    private static final String f13748m = "US-ASCII";

    /* renamed from: o */
    private static final byte f13750o = -1;

    /* renamed from: i */
    static final /* synthetic */ boolean f13744i = !C2802dj.class.desiredAssertionStatus();

    /* renamed from: p */
    private static final byte[] f13751p = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, TarConstants.LF_GNUTYPE_LONGLINK, TarConstants.LF_GNUTYPE_LONGNAME, 77, 78, 79, 80, 81, 82, TarConstants.LF_GNUTYPE_SPARSE, 84, 85, 86, 87, TarConstants.LF_PAX_EXTENDED_HEADER_UC, 89, 90, 97, 98, 99, 100, 101, 102, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 121, 122, TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, 56, 57, 43, 47};

    /* renamed from: n */
    private static final byte f13749n = -5;

    /* renamed from: k */
    private static final byte f13746k = 61;

    /* renamed from: q */
    private static final byte[] f13752q = {-9, -9, -9, -9, -9, -9, -9, -9, -9, f13749n, f13749n, -9, -9, f13749n, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, f13749n, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, 56, 57, 58, 59, 60, f13746k, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, FileDownloadStatus.f10400b, 12, C2771ci.f13672f, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    /* renamed from: r */
    private static final byte[] f13753r = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, TarConstants.LF_GNUTYPE_LONGLINK, TarConstants.LF_GNUTYPE_LONGNAME, 77, 78, 79, 80, 81, 82, TarConstants.LF_GNUTYPE_SPARSE, 84, 85, 86, 87, TarConstants.LF_PAX_EXTENDED_HEADER_UC, 89, 90, 97, 98, 99, 100, 101, 102, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 121, 122, TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, 56, 57, 45, 95};

    /* renamed from: s */
    private static final byte[] f13754s = {-9, -9, -9, -9, -9, -9, -9, -9, -9, f13749n, f13749n, -9, -9, f13749n, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, f13749n, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, 56, 57, 58, 59, 60, f13746k, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, FileDownloadStatus.f10400b, 12, C2771ci.f13672f, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, 63, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    /* renamed from: t */
    private static final byte[] f13755t = {45, TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, TarConstants.LF_GNUTYPE_LONGLINK, TarConstants.LF_GNUTYPE_LONGNAME, 77, 78, 79, 80, 81, 82, TarConstants.LF_GNUTYPE_SPARSE, 84, 85, 86, 87, TarConstants.LF_PAX_EXTENDED_HEADER_UC, 89, 90, 95, 97, 98, 99, 100, 101, 102, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 121, 122};

    /* renamed from: u */
    private static final byte[] f13756u = {-9, -9, -9, -9, -9, -9, -9, -9, -9, f13749n, f13749n, -9, -9, f13749n, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, f13749n, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -9, -9, -9, -1, -9, -9, -9, FileDownloadStatus.f10400b, 12, C2771ci.f13672f, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, -9, -9, -9, -9, 37, -9, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, 56, 57, 58, 59, 60, f13746k, 62, 63, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    /* renamed from: b */
    private static final byte[] m16076b(int i) {
        if ((i & 16) == 16) {
            return f13753r;
        }
        if ((i & 32) == 32) {
            return f13755t;
        }
        return f13751p;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public static final byte[] m16068c(int i) {
        if ((i & 16) == 16) {
            return f13754s;
        }
        if ((i & 32) == 32) {
            return f13756u;
        }
        return f13752q;
    }

    private C2802dj() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static byte[] m16069b(byte[] bArr, byte[] bArr2, int i, int i2) {
        m16071b(bArr2, 0, i, bArr, 0, i2);
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static byte[] m16071b(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        byte[] b = m16076b(i4);
        int i5 = 0;
        int i6 = (i2 > 0 ? (bArr[i] << 24) >>> 8 : 0) | (i2 > 1 ? (bArr[i + 1] << 24) >>> 16 : 0);
        if (i2 > 2) {
            i5 = (bArr[i + 2] << 24) >>> 24;
        }
        int i7 = i6 | i5;
        switch (i2) {
            case 1:
                bArr2[i3] = b[i7 >>> 18];
                bArr2[i3 + 1] = b[(i7 >>> 12) & 63];
                bArr2[i3 + 2] = f13746k;
                bArr2[i3 + 3] = f13746k;
                return bArr2;
            case 2:
                bArr2[i3] = b[i7 >>> 18];
                bArr2[i3 + 1] = b[(i7 >>> 12) & 63];
                bArr2[i3 + 2] = b[(i7 >>> 6) & 63];
                bArr2[i3 + 3] = f13746k;
                return bArr2;
            case 3:
                bArr2[i3] = b[i7 >>> 18];
                bArr2[i3 + 1] = b[(i7 >>> 12) & 63];
                bArr2[i3 + 2] = b[(i7 >>> 6) & 63];
                bArr2[i3 + 3] = b[i7 & 63];
                return bArr2;
            default:
                return bArr2;
        }
    }

    /* renamed from: a */
    public static void m16086a(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        byte[] bArr = new byte[3];
        byte[] bArr2 = new byte[4];
        while (byteBuffer.hasRemaining()) {
            int min = Math.min(3, byteBuffer.remaining());
            byteBuffer.get(bArr, 0, min);
            m16069b(bArr2, bArr, min, 0);
            byteBuffer2.put(bArr2);
        }
    }

    /* renamed from: a */
    public static void m16085a(ByteBuffer byteBuffer, CharBuffer charBuffer) {
        byte[] bArr = new byte[3];
        byte[] bArr2 = new byte[4];
        while (byteBuffer.hasRemaining()) {
            int min = Math.min(3, byteBuffer.remaining());
            byteBuffer.get(bArr, 0, min);
            m16069b(bArr2, bArr, min, 0);
            for (int i = 0; i < 4; i++) {
                charBuffer.put((char) (bArr2[i] & 255));
            }
        }
    }

    /* renamed from: a */
    public static String m16092a(Serializable serializable) {
        return m16091a(serializable, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public static String m16091a(Serializable serializable, int i) {
        GZIPOutputStream gZIPOutputStream;
        IOException e;
        C2804b bVar;
        if (serializable != null) {
            ByteArrayOutputStream byteArrayOutputStream = null;
            r0 = null;
            r0 = null;
            r0 = 0;
            r0 = null;
            r0 = null;
            ObjectOutputStream objectOutputStream = null;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    bVar = new C2804b(byteArrayOutputStream, i | 1);
                    try {
                        if ((i & 2) != 0) {
                            gZIPOutputStream = new GZIPOutputStream(bVar);
                            try {
                                objectOutputStream = new ObjectOutputStream(gZIPOutputStream);
                                gZIPOutputStream = gZIPOutputStream;
                            } catch (IOException e2) {
                                e = e2;
                                byteArrayOutputStream = byteArrayOutputStream;
                                objectOutputStream = objectOutputStream;
                                gZIPOutputStream = gZIPOutputStream;
                                try {
                                    throw e;
                                } catch (Throwable th) {
                                    th = th;
                                    try {
                                        objectOutputStream.close();
                                    } catch (Exception unused) {
                                    }
                                    try {
                                        gZIPOutputStream.close();
                                    } catch (Exception unused2) {
                                    }
                                    try {
                                        bVar.close();
                                    } catch (Exception unused3) {
                                    }
                                    try {
                                        byteArrayOutputStream.close();
                                    } catch (Exception unused4) {
                                    }
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                objectOutputStream.close();
                                gZIPOutputStream.close();
                                bVar.close();
                                byteArrayOutputStream.close();
                                throw th;
                            }
                        } else {
                            objectOutputStream = new ObjectOutputStream(bVar);
                            gZIPOutputStream = null;
                        }
                        objectOutputStream.writeObject(serializable);
                        try {
                            objectOutputStream.close();
                        } catch (Exception unused5) {
                        }
                        try {
                            gZIPOutputStream.close();
                        } catch (Exception unused6) {
                        }
                        try {
                            bVar.close();
                        } catch (Exception unused7) {
                        }
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception unused8) {
                        }
                        try {
                            return new String(byteArrayOutputStream.toByteArray(), "US-ASCII");
                        } catch (UnsupportedEncodingException unused9) {
                            return new String(byteArrayOutputStream.toByteArray());
                        }
                    } catch (IOException e3) {
                        e = e3;
                        byteArrayOutputStream = byteArrayOutputStream;
                        objectOutputStream = objectOutputStream;
                        gZIPOutputStream = objectOutputStream;
                    } catch (Throwable th3) {
                        th = th3;
                        gZIPOutputStream = objectOutputStream;
                    }
                } catch (IOException e4) {
                    e = e4;
                    gZIPOutputStream = null;
                    bVar = null;
                    byteArrayOutputStream = byteArrayOutputStream;
                    objectOutputStream = null;
                } catch (Throwable th4) {
                    th = th4;
                    gZIPOutputStream = null;
                    bVar = null;
                }
            } catch (IOException e5) {
                e = e5;
                gZIPOutputStream = null;
                objectOutputStream = null;
                bVar = null;
            } catch (Throwable th5) {
                th = th5;
                gZIPOutputStream = null;
                byteArrayOutputStream = null;
                bVar = null;
            }
        } else {
            throw new NullPointerException("Cannot serialize a null object.");
        }
    }

    /* renamed from: a */
    public static String m16084a(byte[] bArr) {
        String str;
        try {
            str = m16081a(bArr, 0, bArr.length, 0);
        } catch (IOException e) {
            if (f13744i) {
                str = null;
            } else {
                throw new AssertionError(e.getMessage());
            }
        }
        if (f13744i || str != null) {
            return str;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public static String m16083a(byte[] bArr, int i) {
        return m16081a(bArr, 0, bArr.length, i);
    }

    /* renamed from: a */
    public static String m16082a(byte[] bArr, int i, int i2) {
        String str;
        try {
            str = m16081a(bArr, i, i2, 0);
        } catch (IOException e) {
            if (f13744i) {
                str = null;
            } else {
                throw new AssertionError(e.getMessage());
            }
        }
        if (f13744i || str != null) {
            return str;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public static String m16081a(byte[] bArr, int i, int i2, int i3) {
        byte[] b = m16072b(bArr, i, i2, i3);
        try {
            return new String(b, "US-ASCII");
        } catch (UnsupportedEncodingException unused) {
            return new String(b);
        }
    }

    /* renamed from: b */
    public static byte[] m16073b(byte[] bArr) {
        try {
            return m16072b(bArr, 0, bArr.length, 0);
        } catch (IOException e) {
            if (f13744i) {
                return null;
            }
            throw new AssertionError("IOExceptions only come from GZipping, which is turned off: " + e.getMessage());
        }
    }

    /* renamed from: b */
    public static byte[] m16072b(byte[] bArr, int i, int i2, int i3) {
        int i4;
        C2804b bVar;
        IOException e;
        if (bArr == null) {
            throw new NullPointerException("Cannot serialize a null array.");
        } else if (i < 0) {
            throw new IllegalArgumentException("Cannot have negative offset: " + i);
        } else if (i2 < 0) {
            throw new IllegalArgumentException("Cannot have length offset: " + i2);
        } else if (i + i2 > bArr.length) {
            throw new IllegalArgumentException(String.format("Cannot have offset of %d and length of %d with array of length %d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(bArr.length)));
        } else if ((i3 & 2) != 0) {
            ByteArrayOutputStream byteArrayOutputStream = null;
            r1 = null;
            r1 = null;
            GZIPOutputStream gZIPOutputStream = null;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    bVar = new C2804b(byteArrayOutputStream, i3 | 1);
                    try {
                        gZIPOutputStream = new GZIPOutputStream(bVar);
                    } catch (IOException e2) {
                        e = e2;
                        gZIPOutputStream = null;
                    } catch (Throwable th) {
                        th = th;
                        try {
                            gZIPOutputStream.close();
                        } catch (Exception unused) {
                        }
                        try {
                            bVar.close();
                        } catch (Exception unused2) {
                        }
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception unused3) {
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                    e = e3;
                    bVar = null;
                    gZIPOutputStream = null;
                } catch (Throwable th2) {
                    th = th2;
                    bVar = null;
                }
            } catch (IOException e4) {
                e = e4;
                bVar = null;
                gZIPOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                byteArrayOutputStream = null;
                bVar = null;
            }
            try {
                gZIPOutputStream.write(bArr, i, i2);
                gZIPOutputStream.close();
                try {
                    gZIPOutputStream.close();
                } catch (Exception unused4) {
                }
                try {
                    bVar.close();
                } catch (Exception unused5) {
                }
                try {
                    byteArrayOutputStream.close();
                } catch (Exception unused6) {
                }
                return byteArrayOutputStream.toByteArray();
            } catch (IOException e5) {
                e = e5;
                byteArrayOutputStream = byteArrayOutputStream;
                try {
                    throw e;
                } catch (Throwable th4) {
                    th = th4;
                    gZIPOutputStream.close();
                    bVar.close();
                    byteArrayOutputStream.close();
                    throw th;
                }
            } catch (Throwable th5) {
                th = th5;
                gZIPOutputStream.close();
                bVar.close();
                byteArrayOutputStream.close();
                throw th;
            }
        } else {
            boolean z = (i3 & 8) != 0;
            int i5 = ((i2 / 3) * 4) + (i2 % 3 > 0 ? 4 : 0);
            if (z) {
                i5 += i5 / 76;
            }
            byte[] bArr2 = new byte[i5];
            int i6 = i2 - 2;
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            while (i7 < i6) {
                m16071b(bArr, i7 + i, 3, bArr2, i8, i3);
                int i10 = i9 + 4;
                if (!z || i10 < 76) {
                    i9 = i10;
                } else {
                    bArr2[i8 + 4] = 10;
                    i8++;
                    i9 = 0;
                }
                i7 += 3;
                i8 += 4;
            }
            if (i7 < i2) {
                m16071b(bArr, i7 + i, i2 - i7, bArr2, i8, i3);
                i4 = i8 + 4;
            } else {
                i4 = i8;
            }
            if (i4 > bArr2.length - 1) {
                return bArr2;
            }
            byte[] bArr3 = new byte[i4];
            System.arraycopy(bArr2, 0, bArr3, 0, i4);
            return bArr3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static int m16070b(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        int i4;
        int i5;
        if (bArr == null) {
            throw new NullPointerException("Source array was null.");
        } else if (bArr2 == null) {
            throw new NullPointerException("Destination array was null.");
        } else if (i < 0 || (i4 = i + 3) >= bArr.length) {
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and still process four bytes.", Integer.valueOf(bArr.length), Integer.valueOf(i)));
        } else if (i2 < 0 || (i5 = i2 + 2) >= bArr2.length) {
            throw new IllegalArgumentException(String.format("Destination array with length %d cannot have offset of %d and still store three bytes.", Integer.valueOf(bArr2.length), Integer.valueOf(i2)));
        } else {
            byte[] c = m16068c(i3);
            int i6 = i + 2;
            if (bArr[i6] == 61) {
                bArr2[i2] = (byte) ((((c[bArr[i + 1]] & 255) << 12) | ((c[bArr[i]] & 255) << 18)) >>> 16);
                return 1;
            } else if (bArr[i4] == 61) {
                int i7 = ((c[bArr[i6]] & 255) << 6) | ((c[bArr[i + 1]] & 255) << 12) | ((c[bArr[i]] & 255) << 18);
                bArr2[i2] = (byte) (i7 >>> 16);
                bArr2[i2 + 1] = (byte) (i7 >>> 8);
                return 2;
            } else {
                int i8 = (c[bArr[i4]] & 255) | ((c[bArr[i + 1]] & 255) << 12) | ((c[bArr[i]] & 255) << 18) | ((c[bArr[i6]] & 255) << 6);
                bArr2[i2] = (byte) (i8 >> 16);
                bArr2[i2 + 1] = (byte) (i8 >> 8);
                bArr2[i5] = (byte) i8;
                return 3;
            }
        }
    }

    /* renamed from: c */
    public static byte[] m16065c(byte[] bArr) {
        return m16064c(bArr, 0, bArr.length, 0);
    }

    /* renamed from: c */
    public static byte[] m16064c(byte[] bArr, int i, int i2, int i3) {
        int i4;
        if (bArr == null) {
            throw new NullPointerException("Cannot decode null source array.");
        } else if (i < 0 || (i4 = i + i2) > bArr.length) {
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and process %d bytes.", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
        } else if (i2 == 0) {
            return new byte[0];
        } else {
            if (i2 >= 4) {
                byte[] c = m16068c(i3);
                byte[] bArr2 = new byte[(i2 * 3) / 4];
                byte[] bArr3 = new byte[4];
                int i5 = 0;
                int i6 = 0;
                while (i < i4) {
                    byte b = c[bArr[i] & 255];
                    if (b >= -5) {
                        if (b >= -1) {
                            int i7 = i5 + 1;
                            bArr3[i5] = bArr[i];
                            if (i7 > 3) {
                                i6 += m16070b(bArr3, 0, bArr2, i6, i3);
                                if (bArr[i] == 61) {
                                    break;
                                }
                                i5 = 0;
                            } else {
                                i5 = i7;
                            }
                        }
                        i++;
                    } else {
                        throw new IOException(String.format("Bad Base64 input character decimal %d in array position %d", Integer.valueOf(bArr[i] & 255), Integer.valueOf(i)));
                    }
                }
                byte[] bArr4 = new byte[i6];
                System.arraycopy(bArr2, 0, bArr4, 0, i6);
                return bArr4;
            }
            throw new IllegalArgumentException("Base64-encoded string must have at least four characters, but length specified was " + i2);
        }
    }

    /* renamed from: a */
    public static byte[] m16090a(String str) {
        return m16089a(str, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [int] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11, types: [java.io.ByteArrayInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.io.ByteArrayInputStream] */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.io.ByteArrayInputStream] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Unknown variable types count: 1 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:52:0x0059 -> B:64:0x0059). Please submit an issue!!! */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] m16089a(java.lang.String r5, int r6) {
        /*
            if (r5 == 0) goto L_0x008d
            java.lang.String r0 = "US-ASCII"
            byte[] r5 = r5.getBytes(r0)     // Catch: UnsupportedEncodingException -> 0x0009
            goto L_0x000d
        L_0x0009:
            byte[] r5 = r5.getBytes()
        L_0x000d:
            int r0 = r5.length
            r1 = 0
            byte[] r5 = m16064c(r5, r1, r0, r6)
            r0 = 4
            r6 = r6 & r0
            r2 = 1
            if (r6 == 0) goto L_0x001a
            r6 = 1
            goto L_0x001b
        L_0x001a:
            r6 = 0
        L_0x001b:
            if (r5 == 0) goto L_0x008c
            int r3 = r5.length
            if (r3 < r0) goto L_0x008c
            if (r6 != 0) goto L_0x008c
            byte r6 = r5[r1]
            r6 = r6 & 255(0xff, float:3.57E-43)
            byte r0 = r5[r2]
            int r0 = r0 << 8
            r2 = 65280(0xff00, float:9.1477E-41)
            r0 = r0 & r2
            r6 = r6 | r0
            r0 = 35615(0x8b1f, float:4.9907E-41)
            if (r0 != r6) goto L_0x008c
            r6 = 2048(0x800, float:2.87E-42)
            byte[] r6 = new byte[r6]
            r0 = 0
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch: all -> 0x0071, IOException -> 0x0075
            r2.<init>()     // Catch: all -> 0x0071, IOException -> 0x0075
            java.io.ByteArrayInputStream r3 = new java.io.ByteArrayInputStream     // Catch: all -> 0x0069, IOException -> 0x006c
            r3.<init>(r5)     // Catch: all -> 0x0069, IOException -> 0x006c
            java.util.zip.GZIPInputStream r4 = new java.util.zip.GZIPInputStream     // Catch: all -> 0x0064, IOException -> 0x0066
            r4.<init>(r3)     // Catch: all -> 0x0064, IOException -> 0x0066
        L_0x0048:
            int r0 = r4.read(r6)     // Catch: all -> 0x0060, IOException -> 0x0062
            if (r0 < 0) goto L_0x0052
            r2.write(r6, r1, r0)     // Catch: all -> 0x0060, IOException -> 0x0062
            goto L_0x0048
        L_0x0052:
            byte[] r5 = r2.toByteArray()     // Catch: all -> 0x0060, IOException -> 0x0062
            r2.close()     // Catch: Exception -> 0x0059
        L_0x0059:
            r4.close()     // Catch: Exception -> 0x005c
        L_0x005c:
            r3.close()     // Catch: Exception -> 0x008c
            goto L_0x008c
        L_0x0060:
            r5 = move-exception
            goto L_0x0081
        L_0x0062:
            r6 = move-exception
            goto L_0x006f
        L_0x0064:
            r5 = move-exception
            goto L_0x0082
        L_0x0066:
            r6 = move-exception
            r4 = r0
            goto L_0x006f
        L_0x0069:
            r5 = move-exception
            r3 = r0
            goto L_0x0082
        L_0x006c:
            r6 = move-exception
            r3 = r0
            r4 = r3
        L_0x006f:
            r0 = r2
            goto L_0x0078
        L_0x0071:
            r5 = move-exception
            r2 = r0
            r3 = r2
            goto L_0x0082
        L_0x0075:
            r6 = move-exception
            r3 = r0
            r4 = r3
        L_0x0078:
            r6.printStackTrace()     // Catch: all -> 0x007f
            r0.close()     // Catch: Exception -> 0x0059
            goto L_0x0059
        L_0x007f:
            r5 = move-exception
            r2 = r0
        L_0x0081:
            r0 = r4
        L_0x0082:
            r2.close()     // Catch: Exception -> 0x0085
        L_0x0085:
            r0.close()     // Catch: Exception -> 0x0088
        L_0x0088:
            r3.close()     // Catch: Exception -> 0x008b
        L_0x008b:
            throw r5
        L_0x008c:
            return r5
        L_0x008d:
            java.lang.NullPointerException r5 = new java.lang.NullPointerException
            java.lang.String r6 = "Input string was null."
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tendcloud.tenddata.C2802dj.m16089a(java.lang.String, int):byte[]");
    }

    /* renamed from: b */
    public static Object m16075b(String str) {
        return m16088a(str, 0, (ClassLoader) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v14, types: [java.io.ObjectInputStream] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.io.ObjectInputStream] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* renamed from: a */
    public static Object m16088a(String str, int i, ClassLoader classLoader) {
        Throwable th;
        ByteArrayInputStream byteArrayInputStream;
        IOException e;
        ClassNotFoundException e2;
        byte[] a = m16089a(str, i);
        C2805dk dkVar = 0;
        dkVar = 0;
        dkVar = null;
        dkVar = null;
        try {
            try {
                byteArrayInputStream = new ByteArrayInputStream(a);
            } catch (Throwable th2) {
                th = th2;
                byteArrayInputStream = null;
                dkVar = classLoader;
            }
            try {
                if (classLoader == null) {
                    dkVar = new ObjectInputStream(byteArrayInputStream);
                } else {
                    dkVar = new C2805dk(byteArrayInputStream, classLoader);
                }
                Object readObject = dkVar.readObject();
                try {
                    byteArrayInputStream.close();
                } catch (Exception unused) {
                }
                try {
                    dkVar.close();
                } catch (Exception unused2) {
                }
                return readObject;
            } catch (IOException e3) {
                e = e3;
                throw e;
            } catch (ClassNotFoundException e4) {
                e2 = e4;
                throw e2;
            } catch (Throwable th3) {
                th = th3;
                try {
                    byteArrayInputStream.close();
                } catch (Exception unused3) {
                }
                try {
                    dkVar.close();
                } catch (Exception unused4) {
                }
                throw th;
            }
        } catch (IOException e5) {
            e = e5;
        } catch (ClassNotFoundException e6) {
            e2 = e6;
        } catch (Throwable th4) {
            th = th4;
            byteArrayInputStream = null;
        }
    }

    /* renamed from: a */
    public static void m16078a(byte[] bArr, String str) {
        Throwable th;
        C2804b bVar;
        if (bArr != null) {
            C2804b bVar2 = null;
            try {
                try {
                    bVar = new C2804b(new FileOutputStream(str), 1);
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    bVar.write(bArr);
                    try {
                        bVar.close();
                    } catch (Exception unused) {
                    }
                } catch (IOException e) {
                } catch (Throwable th3) {
                    th = th3;
                    bVar2 = bVar;
                    try {
                        bVar2.close();
                    } catch (Exception unused2) {
                    }
                    throw th;
                }
            } catch (IOException e2) {
                throw e2;
            }
        } else {
            throw new NullPointerException("Data to encode was null.");
        }
    }

    /* renamed from: a */
    public static void m16087a(String str, String str2) {
        Throwable th;
        C2804b bVar;
        C2804b bVar2 = null;
        try {
            try {
                bVar = new C2804b(new FileOutputStream(str2), 0);
            } catch (IOException e) {
                throw e;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            bVar.write(str.getBytes("US-ASCII"));
            try {
                bVar.close();
            } catch (Exception unused) {
            }
        } catch (IOException e2) {
        } catch (Throwable th3) {
            th = th3;
            bVar2 = bVar;
            try {
                bVar2.close();
            } catch (Exception unused2) {
            }
            throw th;
        }
    }

    /* renamed from: c */
    public static byte[] m16067c(String str) {
        Throwable th;
        C2803a aVar = null;
        try {
            try {
                File file = new File(str);
                if (file.length() <= 2147483647L) {
                    byte[] bArr = new byte[(int) file.length()];
                    C2803a aVar2 = new C2803a(new BufferedInputStream(new FileInputStream(file)), 0);
                    int i = 0;
                    while (true) {
                        try {
                            int read = aVar2.read(bArr, i, 4096);
                            if (read < 0) {
                                break;
                            }
                            i += read;
                        } catch (IOException e) {
                            throw e;
                        } catch (Throwable th2) {
                            th = th2;
                            aVar = aVar2;
                            try {
                                aVar.close();
                            } catch (Exception unused) {
                            }
                            throw th;
                        }
                    }
                    byte[] bArr2 = new byte[i];
                    System.arraycopy(bArr, 0, bArr2, 0, i);
                    try {
                        aVar2.close();
                    } catch (Exception unused2) {
                    }
                    return bArr2;
                }
                throw new IOException("File is too big for this convenience method (" + file.length() + " bytes).");
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (IOException e2) {
            throw e2;
        }
    }

    /* renamed from: d */
    public static String m16063d(String str) {
        Throwable th;
        C2803a aVar = null;
        try {
            try {
                File file = new File(str);
                byte[] bArr = new byte[Math.max((int) ((file.length() * 1.4d) + 1.0d), 40)];
                C2803a aVar2 = new C2803a(new BufferedInputStream(new FileInputStream(file)), 1);
                int i = 0;
                while (true) {
                    try {
                        int read = aVar2.read(bArr, i, 4096);
                        if (read < 0) {
                            break;
                        }
                        i += read;
                    } catch (IOException e) {
                        throw e;
                    } catch (Throwable th2) {
                        th = th2;
                        aVar = aVar2;
                        try {
                            aVar.close();
                        } catch (Exception unused) {
                        }
                        throw th;
                    }
                }
                String str2 = new String(bArr, 0, i, "US-ASCII");
                try {
                    aVar2.close();
                } catch (Exception unused2) {
                }
                return str2;
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (IOException e2) {
            throw e2;
        }
    }

    /* renamed from: b */
    public static void m16074b(String str, String str2) {
        Throwable th;
        BufferedOutputStream bufferedOutputStream;
        String d = m16063d(str);
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str2));
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                bufferedOutputStream.write(d.getBytes("US-ASCII"));
                try {
                    bufferedOutputStream.close();
                } catch (Exception unused) {
                }
            } catch (IOException e) {
            } catch (Throwable th3) {
                th = th3;
                bufferedOutputStream2 = bufferedOutputStream;
                try {
                    bufferedOutputStream2.close();
                } catch (Exception unused2) {
                }
                throw th;
            }
        } catch (IOException e2) {
            throw e2;
        }
    }

    /* renamed from: c */
    public static void m16066c(String str, String str2) {
        Throwable th;
        BufferedOutputStream bufferedOutputStream;
        byte[] c = m16067c(str);
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str2));
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                bufferedOutputStream.write(c);
                try {
                    bufferedOutputStream.close();
                } catch (Exception unused) {
                }
            } catch (IOException e) {
            } catch (Throwable th3) {
                th = th3;
                bufferedOutputStream2 = bufferedOutputStream;
                try {
                    bufferedOutputStream2.close();
                } catch (Exception unused2) {
                }
                throw th;
            }
        } catch (IOException e2) {
            throw e2;
        }
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.dj$a */
    /* loaded from: classes2.dex */
    public static class C2803a extends FilterInputStream {
        private boolean breakLines;
        private byte[] buffer;
        private int bufferLength;
        private byte[] decodabet;
        private boolean encode;
        private int lineLength;
        private int numSigBytes;
        private int options;
        private int position;

        public C2803a(InputStream inputStream) {
            this(inputStream, 0);
        }

        public C2803a(InputStream inputStream, int i) {
            super(inputStream);
            this.options = i;
            boolean z = true;
            this.breakLines = (i & 8) > 0;
            this.encode = (i & 1) <= 0 ? false : z;
            this.bufferLength = this.encode ? 4 : 3;
            this.buffer = new byte[this.bufferLength];
            this.position = -1;
            this.lineLength = 0;
            this.decodabet = C2802dj.m16068c(i);
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read() {
            int read;
            if (this.position < 0) {
                if (this.encode) {
                    byte[] bArr = new byte[3];
                    int i = 0;
                    for (int i2 = 0; i2 < 3; i2++) {
                        int read2 = this.in.read();
                        if (read2 < 0) {
                            break;
                        }
                        bArr[i2] = (byte) read2;
                        i++;
                    }
                    if (i <= 0) {
                        return -1;
                    }
                    C2802dj.m16071b(bArr, 0, i, this.buffer, 0, this.options);
                    this.position = 0;
                    this.numSigBytes = 4;
                } else {
                    byte[] bArr2 = new byte[4];
                    int i3 = 0;
                    while (i3 < 4) {
                        do {
                            read = this.in.read();
                            if (read < 0) {
                                break;
                            }
                        } while (this.decodabet[read & 127] <= -5);
                        if (read < 0) {
                            break;
                        }
                        bArr2[i3] = (byte) read;
                        i3++;
                    }
                    if (i3 == 4) {
                        this.numSigBytes = C2802dj.m16070b(bArr2, 0, this.buffer, 0, this.options);
                        this.position = 0;
                    } else if (i3 == 0) {
                        return -1;
                    } else {
                        throw new IOException("Improperly padded Base64 input.");
                    }
                }
            }
            int i4 = this.position;
            if (i4 < 0) {
                throw new IOException("Error in Base64 code reading stream.");
            } else if (i4 >= this.numSigBytes) {
                return -1;
            } else {
                if (!this.encode || !this.breakLines || this.lineLength < 76) {
                    this.lineLength++;
                    byte[] bArr3 = this.buffer;
                    int i5 = this.position;
                    this.position = i5 + 1;
                    byte b = bArr3[i5];
                    if (this.position >= this.bufferLength) {
                        this.position = -1;
                    }
                    return b & 255;
                }
                this.lineLength = 0;
                return 10;
            }
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read(byte[] bArr, int i, int i2) {
            int i3 = 0;
            while (true) {
                if (i3 >= i2) {
                    break;
                }
                int read = read();
                if (read >= 0) {
                    bArr[i + i3] = (byte) read;
                    i3++;
                } else if (i3 == 0) {
                    return -1;
                }
            }
            return i3;
        }
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.dj$b */
    /* loaded from: classes2.dex */
    public static class C2804b extends FilterOutputStream {

        /* renamed from: b4 */
        private byte[] f13757b4;
        private boolean breakLines;
        private byte[] buffer;
        private int bufferLength;
        private byte[] decodabet;
        private boolean encode;
        private int lineLength;
        private int options;
        private int position;
        private boolean suspendEncoding;

        public C2804b(OutputStream outputStream) {
            this(outputStream, 1);
        }

        public C2804b(OutputStream outputStream, int i) {
            super(outputStream);
            boolean z = true;
            this.breakLines = (i & 8) != 0;
            this.encode = (i & 1) == 0 ? false : z;
            this.bufferLength = this.encode ? 3 : 4;
            this.buffer = new byte[this.bufferLength];
            this.position = 0;
            this.lineLength = 0;
            this.suspendEncoding = false;
            this.f13757b4 = new byte[4];
            this.options = i;
            this.decodabet = C2802dj.m16068c(i);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(int i) {
            if (this.suspendEncoding) {
                this.out.write(i);
            } else if (this.encode) {
                byte[] bArr = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr[i2] = (byte) i;
                if (this.position >= this.bufferLength) {
                    this.out.write(C2802dj.m16069b(this.f13757b4, this.buffer, this.bufferLength, this.options));
                    this.lineLength += 4;
                    if (this.breakLines && this.lineLength >= 76) {
                        this.out.write(10);
                        this.lineLength = 0;
                    }
                    this.position = 0;
                }
            } else {
                byte[] bArr2 = this.decodabet;
                int i3 = i & 127;
                if (bArr2[i3] > -5) {
                    byte[] bArr3 = this.buffer;
                    int i4 = this.position;
                    this.position = i4 + 1;
                    bArr3[i4] = (byte) i;
                    if (this.position >= this.bufferLength) {
                        this.out.write(this.f13757b4, 0, C2802dj.m16070b(bArr3, 0, this.f13757b4, 0, this.options));
                        this.position = 0;
                    }
                } else if (bArr2[i3] != -5) {
                    throw new IOException("Invalid character in Base64 data.");
                }
            }
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) {
            if (this.suspendEncoding) {
                this.out.write(bArr, i, i2);
                return;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                write(bArr[i + i3]);
            }
        }

        public void flushBase64() {
            if (this.position <= 0) {
                return;
            }
            if (this.encode) {
                this.out.write(C2802dj.m16069b(this.f13757b4, this.buffer, this.position, this.options));
                this.position = 0;
                return;
            }
            throw new IOException("Base64 input not properly padded.");
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            flushBase64();
            super.close();
            this.buffer = null;
            this.out = null;
        }

        public void suspendEncoding() {
            flushBase64();
            this.suspendEncoding = true;
        }

        public void resumeEncoding() {
            this.suspendEncoding = false;
        }
    }
}
