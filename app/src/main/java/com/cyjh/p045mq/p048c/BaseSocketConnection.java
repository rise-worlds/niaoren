package com.cyjh.p045mq.p048c;

import android.net.LocalSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

/* renamed from: com.cyjh.mq.c.a */
/* loaded from: classes.dex */
public class BaseSocketConnection {

    /* renamed from: a */
    private static final int f8798a = 4096;

    /* renamed from: b */
    private Socket f8799b;

    /* renamed from: c */
    private LocalSocket f8800c;

    /* renamed from: d */
    private InputStream f8801d;

    /* renamed from: e */
    private OutputStream f8802e;

    public BaseSocketConnection(Socket socket) {
        this.f8799b = null;
        this.f8800c = null;
        this.f8801d = null;
        this.f8802e = null;
        this.f8799b = socket;
        try {
            this.f8801d = this.f8799b.getInputStream();
            this.f8802e = this.f8799b.getOutputStream();
        } catch (IOException e) {
            new StringBuilder("BaseSocketConnection(Socket socket) e").append(e.toString());
            e.printStackTrace();
        }
    }

    public BaseSocketConnection(LocalSocket localSocket) {
        this.f8799b = null;
        this.f8800c = null;
        this.f8801d = null;
        this.f8802e = null;
        this.f8800c = localSocket;
        try {
            this.f8801d = this.f8800c.getInputStream();
            this.f8802e = this.f8800c.getOutputStream();
        } catch (IOException e) {
            new StringBuilder("BaseSocketConnection(LocalSocket localSocket) e").append(e.toString());
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    private void m20544b(byte[] bArr) {
        m20546a(ByteBuffer.wrap(bArr));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final void m20546a(ByteBuffer byteBuffer) {
        try {
            byte[] bArr = new byte[4096];
            while (byteBuffer.hasRemaining()) {
                if (byteBuffer.remaining() >= 4096) {
                    byteBuffer.get(bArr);
                } else {
                    bArr = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bArr);
                }
                this.f8802e.write(bArr);
            }
            this.f8802e.flush();
        } catch (IOException e) {
            e.printStackTrace();
            new StringBuilder("sendData e").append(e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final int m20545a(byte[] bArr) {
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            try {
                i2 = this.f8801d.read(bArr, i, length - i);
                if (i2 < 0) {
                    return -1;
                }
                i += i2;
            } catch (IOException e) {
                e.printStackTrace();
                new StringBuilder("receiveData e").append(e.toString());
                return -1;
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo20543a() {
        try {
            if (this.f8799b != null) {
                this.f8799b.close();
            }
            if (this.f8800c != null) {
                this.f8800c.close();
            }
            if (this.f8801d != null) {
                this.f8801d.close();
            }
            if (this.f8802e != null) {
                this.f8802e.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
