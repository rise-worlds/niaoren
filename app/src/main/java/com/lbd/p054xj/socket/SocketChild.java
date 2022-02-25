package com.lbd.p054xj.socket;

import android.net.LocalSocket;
import android.support.annotation.Nullable;
import com.common.utils.log.LogUtils;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.lbd.p054xj.socket.C1545f;
import com.lbd.p054xj.socket.model.SocketData;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/* renamed from: com.lbd.xj.socket.b */
/* loaded from: classes.dex */
public class SocketChild {

    /* renamed from: a */
    BufferedReader f9537a;

    /* renamed from: b */
    BufferedWriter f9538b;

    /* renamed from: c */
    InputStream f9539c;

    /* renamed from: d */
    boolean f9540d;

    /* renamed from: e */
    boolean f9541e;

    /* renamed from: f */
    OutputStream f9542f;

    /* renamed from: g */
    LocalSocket f9543g;

    /* renamed from: h */
    C1545f.AbstractRunnableC1552d f9544h;

    /* renamed from: i */
    private int f9545i;

    /* renamed from: j */
    private SocketLongCallBack f9546j;

    /* renamed from: k */
    private boolean f9547k;

    public SocketChild(LocalSocket localSocket) {
        this.f9540d = true;
        this.f9541e = false;
        this.f9547k = true;
        this.f9544h = new C1545f.AbstractC1549a() { // from class: com.lbd.xj.socket.b.1
            @Override // com.lbd.p054xj.socket.C1545f.AbstractRunnableC1552d
            @Nullable
            public Object doInBackground() {
                while (SocketChild.this.f9540d) {
                    try {
                        if (SocketChild.this.m19657b()) {
                            SocketChild.this.m19661a(SocketChild.this.f9539c);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
                return null;
            }
        };
        try {
            this.f9543g = localSocket;
            this.f9539c = localSocket.getInputStream();
            this.f9542f = localSocket.getOutputStream();
            this.f9537a = new BufferedReader(new InputStreamReader(this.f9539c));
            this.f9538b = new BufferedWriter(new OutputStreamWriter(this.f9542f));
            C1545f.m19577d(this.f9544h);
            m19658a(true);
        } catch (IOException e) {
            m19663a();
            e.printStackTrace();
        }
    }

    public SocketChild(LocalSocket localSocket, SocketLongCallBack cVar, int i) {
        this.f9540d = true;
        this.f9541e = false;
        this.f9547k = true;
        this.f9544h = new C1545f.AbstractRunnableC1552d() { // from class: com.lbd.xj.socket.b.2
            @Override // com.lbd.p054xj.socket.C1545f.AbstractRunnableC1552d
            public void onCancel() {
            }

            @Override // com.lbd.p054xj.socket.C1545f.AbstractRunnableC1552d
            public void onSuccess(@Nullable Object obj) {
            }

            @Override // com.lbd.p054xj.socket.C1545f.AbstractRunnableC1552d
            @Nullable
            public Object doInBackground() throws Throwable {
                while (SocketChild.this.f9540d) {
                    try {
                        if (SocketChild.this.m19657b()) {
                            SocketChild.this.m19661a(SocketChild.this.f9539c);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
                return null;
            }

            @Override // com.lbd.p054xj.socket.C1545f.AbstractRunnableC1552d
            public void onFail(Throwable th) {
                SocketChild.this.m19663a();
            }
        };
        try {
            this.f9545i = i;
            if (cVar == null) {
                this.f9546j = cVar;
            }
            this.f9543g = localSocket;
            this.f9539c = localSocket.getInputStream();
            this.f9542f = localSocket.getOutputStream();
            this.f9537a = new BufferedReader(new InputStreamReader(this.f9539c));
            this.f9538b = new BufferedWriter(new OutputStreamWriter(this.f9542f));
            m19658a(true);
            C1545f.m19577d(this.f9544h);
        } catch (IOException e) {
            this.f9541e = true;
            m19663a();
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m19663a() {
        try {
            LogUtils.m22036e("close", ExpandableTextView.f6958c + this.f9541e);
            this.f9539c.close();
            this.f9542f.close();
            this.f9543g.close();
            m19658a(false);
            this.f9540d = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public boolean m19657b() {
        return this.f9547k;
    }

    /* renamed from: a */
    public void m19661a(InputStream inputStream) throws IOException {
        LogUtils.m22038d("InputStream", "readData ");
        int i = 0;
        while (i == 0) {
            try {
                Thread.sleep(200L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i = inputStream.available();
        }
        byte[] bArr = new byte[i];
        inputStream.read(bArr);
        SocketLongCallBack cVar = this.f9546j;
        if (cVar != null) {
            cVar.mo19652a(new String(bArr), this.f9545i);
        }
    }

    /* renamed from: b */
    public void m19656b(InputStream inputStream) {
        try {
            if (this.f9537a != null) {
                String readLine = this.f9537a.readLine();
                LogUtils.m22036e("readLine", this.f9537a + "");
                LogUtils.m22036e("readLine", readLine);
                if (this.f9546j != null) {
                    LogUtils.m22036e("readLine", readLine);
                    this.f9546j.mo19652a(readLine, this.f9545i);
                    return;
                }
                return;
            }
            this.f9537a = new BufferedReader(new InputStreamReader(inputStream));
            LogUtils.m22036e("readLine", this.f9537a + "");
            String readLine2 = this.f9537a.readLine();
            LogUtils.m22036e("readLine", readLine2);
            if (this.f9546j != null) {
                LogUtils.m22036e("readLine", readLine2);
                this.f9546j.mo19652a(readLine2, this.f9545i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: c */
    private void m19654c(final String str) {
        C1545f.m19586c(new C1545f.AbstractC1549a() { // from class: com.lbd.xj.socket.b.3
            @Override // com.lbd.p054xj.socket.C1545f.AbstractRunnableC1552d
            @Nullable
            public Object doInBackground() {
                try {
                    if (SocketChild.this.f9538b != null) {
                        SocketChild.this.f9538b.write(str);
                        SocketChild.this.f9538b.flush();
                    }
                } catch (IOException e) {
                    try {
                        e.printStackTrace();
                        SocketChild.this.m19663a();
                        return null;
                    } catch (Exception unused) {
                        return null;
                    }
                } catch (Exception unused2) {
                }
                SocketChild.this.f9541e = true;
                return null;
            }
        });
    }

    /* renamed from: a */
    public void m19660a(String str) {
        String str2;
        String str3 = str2.length() + "";
        String str4 = str + str3 + (str.length() + "");
        LogUtils.m22036e("sendData", str4);
        m19654c(str4);
    }

    /* renamed from: b */
    public void m19655b(String str) {
        m19654c(new SocketData(str, "").toString());
    }

    /* renamed from: a */
    public void m19659a(String str, String str2) {
        m19654c(new SocketData(str2, str).toString());
    }

    /* renamed from: a */
    public void m19658a(boolean z) {
        this.f9547k = z;
    }

    /* renamed from: a */
    public void m19662a(SocketLongCallBack cVar) {
        this.f9546j = cVar;
    }
}
