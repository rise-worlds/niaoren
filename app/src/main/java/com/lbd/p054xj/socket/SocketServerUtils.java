package com.lbd.p054xj.socket;

import android.net.LocalServerSocket;
import android.net.LocalSocket;
import android.support.annotation.Nullable;
import com.common.utils.log.LogUtils;
import com.lbd.p054xj.socket.C1545f;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/* renamed from: com.lbd.xj.socket.e */
/* loaded from: classes.dex */
public class SocketServerUtils {

    /* renamed from: a */
    InputStream f9564a;

    /* renamed from: c */
    LocalServerSocket f9566c;

    /* renamed from: d */
    LocalSocket f9567d;

    /* renamed from: e */
    OutputStream f9568e;

    /* renamed from: f */
    String f9569f;

    /* renamed from: i */
    private SocketCallBack f9572i;

    /* renamed from: g */
    C1545f.AbstractRunnableC1552d f9570g = new C1545f.AbstractRunnableC1552d() { // from class: com.lbd.xj.socket.e.1
        @Override // com.lbd.p054xj.socket.C1545f.AbstractRunnableC1552d
        public void onCancel() {
        }

        @Override // com.lbd.p054xj.socket.C1545f.AbstractRunnableC1552d
        @Nullable
        public Object doInBackground() throws Throwable {
            LogUtils.m22036e("socket", "doInBackground");
            SocketServerUtils eVar = SocketServerUtils.this;
            eVar.f9566c = new LocalServerSocket(eVar.f9569f);
            while (true) {
                SocketServerUtils eVar2 = SocketServerUtils.this;
                eVar2.f9567d = eVar2.f9566c.accept();
                C1545f.m19586c(SocketServerUtils.this.f9571h);
            }
        }

        @Override // com.lbd.p054xj.socket.C1545f.AbstractRunnableC1552d
        public void onFail(Throwable th) {
            SocketServerUtils.m19627a(SocketServerUtils.this).mo19666a();
            C1545f.m19586c(SocketServerUtils.this.f9570g);
        }

        @Override // com.lbd.p054xj.socket.C1545f.AbstractRunnableC1552d
        public void onSuccess(@Nullable Object obj) {
            SocketServerUtils.m19627a(SocketServerUtils.this).mo19666a();
        }
    };

    /* renamed from: h */
    C1545f.AbstractRunnableC1552d f9571h = new C1545f.AbstractRunnableC1552d() { // from class: com.lbd.xj.socket.e.2
        @Override // com.lbd.p054xj.socket.C1545f.AbstractRunnableC1552d
        public void onCancel() {
        }

        @Override // com.lbd.p054xj.socket.C1545f.AbstractRunnableC1552d
        public void onFail(Throwable th) {
        }

        @Override // com.lbd.p054xj.socket.C1545f.AbstractRunnableC1552d
        public void onSuccess(@Nullable Object obj) {
        }

        @Override // com.lbd.p054xj.socket.C1545f.AbstractRunnableC1552d
        @Nullable
        public Object doInBackground() throws Throwable {
            try {
                SocketServerUtils.this.f9564a = SocketServerUtils.this.f9567d.getInputStream();
                SocketServerUtils.this.f9568e = SocketServerUtils.this.f9567d.getOutputStream();
                SocketServerUtils.this.m19626a(SocketServerUtils.this.f9564a);
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    };

    /* renamed from: b */
    int f9565b = 0;

    public SocketServerUtils(String str) {
        m19623c(str);
    }

    /* renamed from: a */
    public void m19625a(String str) {
        int i = this.f9565b;
        if (i == 0) {
            m19624b(str);
        } else if (i == 1) {
            m19624b("BigIdentifier" + str.length() + "");
            this.f9565b = 0;
        }
    }

    /* renamed from: a */
    static SocketCallBack m19627a(SocketServerUtils eVar) {
        return eVar.f9572i;
    }

    /* renamed from: c */
    private void m19623c(String str) {
        LogUtils.m22036e("socket", "开启服务");
        this.f9569f = str;
        C1545f.m19586c(this.f9570g);
    }

    /* renamed from: a */
    public void m19626a(InputStream inputStream) throws IOException {
        int i = 0;
        while (i == 0) {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i = inputStream.available();
        }
        byte[] bArr = new byte[i];
        inputStream.read(bArr);
        String str = new String(bArr);
        if (str.indexOf("Big") != -1) {
            String replace = str.replace("Big", "");
            this.f9565b = 1;
            this.f9572i.mo19665a(replace);
            return;
        }
        this.f9572i.mo19665a(str);
    }

    /* renamed from: b */
    public void m19624b(final String str) {
        new Thread(new Runnable() { // from class: com.lbd.xj.socket.e.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(SocketServerUtils.this.f9568e));
                    bufferedWriter.write(str);
                    bufferedWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /* renamed from: a */
    public void m19628a(SocketCallBack aVar) {
        this.f9572i = aVar;
    }
}
