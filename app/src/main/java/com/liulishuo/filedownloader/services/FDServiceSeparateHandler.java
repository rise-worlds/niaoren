package com.liulishuo.filedownloader.services;

import android.app.Notification;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.message.MessageSnapshotFlow;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import java.lang.ref.WeakReference;
import p110z1.FileDownloadLog;
import p110z1.IFileDownloadIPCCallback;
import p110z1.IFileDownloadIPCService;

/* renamed from: com.liulishuo.filedownloader.services.d */
/* loaded from: classes.dex */
public class FDServiceSeparateHandler extends IFileDownloadIPCService.AbstractBinderC3473a implements MessageSnapshotFlow.AbstractC1693b, IFileDownloadServiceHandler {

    /* renamed from: p */
    private final RemoteCallbackList<IFileDownloadIPCCallback> f10426p = new RemoteCallbackList<>();

    /* renamed from: q */
    private final FileDownloadManager f10427q;

    /* renamed from: r */
    private final WeakReference<FileDownloadService> f10428r;

    @Override // com.liulishuo.filedownloader.services.IFileDownloadServiceHandler
    public IBinder onBind(Intent intent) {
        return this;
    }

    @Override // com.liulishuo.filedownloader.services.IFileDownloadServiceHandler
    public void onStartCommand(Intent intent, int i, int i2) {
    }

    /* renamed from: a */
    private synchronized int m19051a(MessageSnapshot messageSnapshot) {
        int beginBroadcast;
        RemoteCallbackList<IFileDownloadIPCCallback> remoteCallbackList;
        beginBroadcast = this.f10426p.beginBroadcast();
        for (int i = 0; i < beginBroadcast; i++) {
            try {
                this.f10426p.getBroadcastItem(i).callback(messageSnapshot);
            } catch (RemoteException e) {
                FileDownloadLog.m13213a(this, e, "callback error", new Object[0]);
                remoteCallbackList = this.f10426p;
            }
        }
        remoteCallbackList = this.f10426p;
        remoteCallbackList.finishBroadcast();
        return beginBroadcast;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FDServiceSeparateHandler(WeakReference<FileDownloadService> weakReference, FileDownloadManager gVar) {
        this.f10428r = weakReference;
        this.f10427q = gVar;
        MessageSnapshotFlow.m19157a().m19155a(this);
    }

    @Override // p110z1.IFileDownloadIPCService
    public void registerCallback(IFileDownloadIPCCallback ahkVar) throws RemoteException {
        this.f10426p.register(ahkVar);
    }

    @Override // p110z1.IFileDownloadIPCService
    public void unregisterCallback(IFileDownloadIPCCallback ahkVar) throws RemoteException {
        this.f10426p.unregister(ahkVar);
    }

    @Override // p110z1.IFileDownloadIPCService
    public boolean checkDownloading(String str, String str2) throws RemoteException {
        return this.f10427q.m19046a(str, str2);
    }

    @Override // p110z1.IFileDownloadIPCService
    public void start(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3) throws RemoteException {
        this.f10427q.m19045a(str, str2, z, i, i2, i3, z2, fileDownloadHeader, z3);
    }

    @Override // p110z1.IFileDownloadIPCService
    public boolean pause(int i) throws RemoteException {
        return this.f10427q.m19043b(i);
    }

    @Override // p110z1.IFileDownloadIPCService
    public void pauseAllTasks() throws RemoteException {
        this.f10427q.m19048a();
    }

    @Override // p110z1.IFileDownloadIPCService
    public boolean setMaxNetworkThreadCount(int i) throws RemoteException {
        return this.f10427q.m19038f(i);
    }

    @Override // p110z1.IFileDownloadIPCService
    public long getSofar(int i) throws RemoteException {
        return this.f10427q.m19041c(i);
    }

    @Override // p110z1.IFileDownloadIPCService
    public long getTotal(int i) throws RemoteException {
        return this.f10427q.m19040d(i);
    }

    @Override // p110z1.IFileDownloadIPCService
    public byte getStatus(int i) throws RemoteException {
        return this.f10427q.m19039e(i);
    }

    @Override // p110z1.IFileDownloadIPCService
    public boolean isIdle() throws RemoteException {
        return this.f10427q.m19044b();
    }

    @Override // p110z1.IFileDownloadIPCService
    public void startForeground(int i, Notification notification) throws RemoteException {
        WeakReference<FileDownloadService> weakReference = this.f10428r;
        if (weakReference != null && weakReference.get() != null) {
            this.f10428r.get().startForeground(i, notification);
        }
    }

    @Override // p110z1.IFileDownloadIPCService
    public void stopForeground(boolean z) throws RemoteException {
        WeakReference<FileDownloadService> weakReference = this.f10428r;
        if (weakReference != null && weakReference.get() != null) {
            this.f10428r.get().stopForeground(z);
        }
    }

    @Override // p110z1.IFileDownloadIPCService
    public boolean clearTaskData(int i) throws RemoteException {
        return this.f10427q.m19037g(i);
    }

    @Override // p110z1.IFileDownloadIPCService
    public void clearAllTaskData() throws RemoteException {
        this.f10427q.m19042c();
    }

    @Override // com.liulishuo.filedownloader.services.IFileDownloadServiceHandler
    public void onDestroy() {
        MessageSnapshotFlow.m19157a().m19155a((MessageSnapshotFlow.AbstractC1693b) null);
    }

    @Override // com.liulishuo.filedownloader.message.MessageSnapshotFlow.AbstractC1693b
    public void receive(MessageSnapshot messageSnapshot) {
        m19051a(messageSnapshot);
    }
}
