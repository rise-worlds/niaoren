package com.cyjh.p045mq.p048c;

import android.content.Context;
import android.net.LocalServerSocket;
import android.net.LocalSocket;
import android.os.HandlerThread;
import com.cyjh.mobileanjian.ipc.interfaces.AppQuitListener;
import com.cyjh.mobileanjian.ipc.interfaces.EngineStateObserver;
import com.cyjh.mobileanjian.ipc.share.proto.IpcRaw;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.cyjh.mq.c.c */
/* loaded from: classes.dex */
public final class IpcServer implements AppQuitListener {

    /* renamed from: b */
    private static final int f8830b = 10092;

    /* renamed from: f */
    private Context f8835f;

    /* renamed from: c */
    private ServerSocket f8832c = null;

    /* renamed from: d */
    private LocalServerSocket f8833d = null;

    /* renamed from: e */
    private IpcConnection f8834e = null;

    /* renamed from: g */
    private boolean f8836g = false;

    /* renamed from: a */
    public List<EngineStateObserver> f8831a = new ArrayList();

    public IpcServer(Context context) {
        this.f8835f = null;
        this.f8835f = context;
    }

    /* renamed from: a */
    private void m20497a(EngineStateObserver engineStateObserver) {
        this.f8831a.add(engineStateObserver);
    }

    /* renamed from: b */
    private void m20493b(EngineStateObserver engineStateObserver) {
        this.f8831a.remove(engineStateObserver);
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [com.cyjh.mq.c.c$1] */
    /* renamed from: a */
    public final void m20494a(String str, int i) {
        while (this.f8832c == null) {
            try {
                this.f8832c = new ServerSocket(i);
            } catch (IOException e) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                e.printStackTrace();
            }
        }
        try {
            new HandlerThread("server socket thread") { // from class: com.cyjh.mq.c.c.1
                @Override // android.os.HandlerThread
                protected final void onLooperPrepared() {
                    super.onLooperPrepared();
                    while (!IpcServer.this.f8836g) {
                        try {
                            Socket accept = IpcServer.this.f8832c.accept();
                            if (IpcServer.this.f8834e == null || !IpcServer.this.f8834e.f8806b) {
                                IpcServer.this.f8834e = new IpcConnection(IpcServer.this.f8835f, accept);
                                IpcServer.this.f8834e.f8811g = IpcServer.this.f8831a;
                                IpcServer.this.f8834e.m20525b();
                            } else {
                                accept.close();
                            }
                        } catch (IOException e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                    IpcServer.this.f8832c.close();
                }
            }.start();
            this.f8833d = new LocalServerSocket(str);
            while (!this.f8836g) {
                LocalSocket accept = this.f8833d.accept();
                if (this.f8834e == null || !this.f8834e.f8806b) {
                    this.f8834e = new IpcConnection(this.f8835f, accept);
                    this.f8834e.f8811g = this.f8831a;
                    this.f8834e.m20525b();
                } else {
                    this.f8834e.mo20543a();
                }
            }
            this.f8833d.close();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    @Override // com.cyjh.mobileanjian.ipc.interfaces.AppQuitListener
    public final void onAppQuit() {
        if (!this.f8836g) {
            this.f8836g = true;
            try {
                if (this.f8834e != null) {
                    this.f8834e.m20531a(IpcRaw.m20933a(65535));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
