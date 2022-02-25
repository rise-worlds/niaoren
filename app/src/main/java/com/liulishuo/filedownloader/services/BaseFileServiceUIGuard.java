package com.liulishuo.filedownloader.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import p110z1.DownloadServiceConnectChangedEvent;
import p110z1.FileDownloadEventPool;
import p110z1.FileDownloadLog;
import p110z1.FileDownloadUtils;
import p110z1.IFileDownloadServiceProxy;

/* renamed from: com.liulishuo.filedownloader.services.a */
/* loaded from: classes.dex */
public abstract class BaseFileServiceUIGuard<CALLBACK extends Binder, INTERFACE extends IInterface> implements ServiceConnection, IFileDownloadServiceProxy {

    /* renamed from: b */
    private volatile INTERFACE f10414b;

    /* renamed from: c */
    private final Class<?> f10415c;

    /* renamed from: d */
    private final HashMap<String, Object> f10416d = new HashMap<>();

    /* renamed from: e */
    private final List<Context> f10417e = new ArrayList();

    /* renamed from: f */
    private final ArrayList<Runnable> f10418f = new ArrayList<>();

    /* renamed from: a */
    private final CALLBACK f10413a = mo13644b();

    /* renamed from: a */
    protected abstract void mo13646a(INTERFACE r1, CALLBACK callback) throws RemoteException;

    /* renamed from: b */
    protected abstract CALLBACK mo13644b();

    /* renamed from: b */
    protected abstract INTERFACE mo13643b(IBinder iBinder);

    /* renamed from: b */
    protected abstract void mo13642b(INTERFACE r1, CALLBACK callback) throws RemoteException;

    /* renamed from: g */
    protected CALLBACK m19072g() {
        return this.f10413a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: h */
    public INTERFACE m19071h() {
        return this.f10414b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseFileServiceUIGuard(Class<?> cls) {
        this.f10415c = cls;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f10414b = mo13643b(iBinder);
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "onServiceConnected %s %s", componentName, this.f10414b);
        }
        try {
            mo13642b(this.f10414b, this.f10413a);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        this.f10418f.clear();
        for (Runnable runnable : (List) this.f10418f.clone()) {
            runnable.run();
        }
        FileDownloadEventPool.m13726a().mo13301b(new DownloadServiceConnectChangedEvent(DownloadServiceConnectChangedEvent.EnumC3470a.connected, this.f10415c));
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "onServiceDisconnected %s %s", componentName, this.f10414b);
        }
        m19074b(true);
    }

    /* renamed from: b */
    private void m19074b(boolean z) {
        if (!z && this.f10414b != null) {
            try {
                mo13646a((BaseFileServiceUIGuard<CALLBACK, INTERFACE>) this.f10414b, (INTERFACE) this.f10413a);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "release connect resources %s", this.f10414b);
        }
        this.f10414b = null;
        FileDownloadEventPool.m13726a().mo13301b(new DownloadServiceConnectChangedEvent(z ? DownloadServiceConnectChangedEvent.EnumC3470a.lost : DownloadServiceConnectChangedEvent.EnumC3470a.disconnected, this.f10415c));
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: a */
    public void mo13561a(Context context) {
        mo13560a(context, (Runnable) null);
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: a */
    public void mo13560a(Context context, Runnable runnable) {
        if (!FileDownloadUtils.m13187a(context)) {
            if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13211c(this, "bindStartByContext %s", context.getClass().getSimpleName());
            }
            Intent intent = new Intent(context, this.f10415c);
            if (runnable != null && !this.f10418f.contains(runnable)) {
                this.f10418f.add(runnable);
            }
            if (!this.f10417e.contains(context)) {
                this.f10417e.add(context);
            }
            context.bindService(intent, this, 1);
            context.startService(intent);
            return;
        }
        throw new IllegalStateException("Fatal-Exception: You can't bind the FileDownloadService in :filedownloader process.\n It's the invalid operation and is likely to cause unexpected problems.\n Maybe you want to use non-separate process mode for FileDownloader, More detail about non-separate mode, please move to wiki manually: https://github.com/lingochamp/FileDownloader/wiki/filedownloader.properties");
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: b */
    public void mo13555b(Context context) {
        if (this.f10417e.contains(context)) {
            if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13211c(this, "unbindByContext %s", context);
            }
            this.f10417e.remove(context);
            if (this.f10417e.isEmpty()) {
                m19074b(false);
            }
            Intent intent = new Intent(context, this.f10415c);
            context.unbindService(this);
            context.stopService(intent);
        }
    }

    /* renamed from: c */
    public void m19073c(Context context) {
        context.startService(new Intent(context, this.f10415c));
    }

    /* renamed from: a */
    protected Object m19075a(String str) {
        return this.f10416d.remove(str);
    }

    /* renamed from: a */
    protected String m19076a(Object obj) {
        if (obj == null) {
            return null;
        }
        String obj2 = obj.toString();
        this.f10416d.put(obj2, obj);
        return obj2;
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: e */
    public boolean mo13550e() {
        return m19071h() != null;
    }
}
