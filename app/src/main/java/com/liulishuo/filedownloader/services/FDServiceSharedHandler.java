package com.liulishuo.filedownloader.services;

import android.app.Notification;
import android.content.Intent;
import android.os.IBinder;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import java.lang.ref.WeakReference;
import p110z1.FileDownloadServiceProxy;
import p110z1.IFileDownloadIPCCallback;
import p110z1.IFileDownloadIPCService;

/* renamed from: com.liulishuo.filedownloader.services.e */
/* loaded from: classes.dex */
public class FDServiceSharedHandler extends IFileDownloadIPCService.AbstractBinderC3473a implements IFileDownloadServiceHandler {

    /* renamed from: p */
    private final FileDownloadManager f10429p;

    /* renamed from: q */
    private final WeakReference<FileDownloadService> f10430q;

    /* compiled from: FDServiceSharedHandler.java */
    /* renamed from: com.liulishuo.filedownloader.services.e$a */
    /* loaded from: classes.dex */
    public interface AbstractC1700a {
        /* renamed from: a */
        void mo13650a();

        /* renamed from: a */
        void mo13649a(FDServiceSharedHandler eVar);
    }

    @Override // com.liulishuo.filedownloader.services.IFileDownloadServiceHandler
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // p110z1.IFileDownloadIPCService
    public void registerCallback(IFileDownloadIPCCallback ahkVar) {
    }

    @Override // p110z1.IFileDownloadIPCService
    public void unregisterCallback(IFileDownloadIPCCallback ahkVar) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FDServiceSharedHandler(WeakReference<FileDownloadService> weakReference, FileDownloadManager gVar) {
        this.f10430q = weakReference;
        this.f10429p = gVar;
    }

    @Override // p110z1.IFileDownloadIPCService
    public boolean checkDownloading(String str, String str2) {
        return this.f10429p.m19046a(str, str2);
    }

    @Override // p110z1.IFileDownloadIPCService
    public void start(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3) {
        this.f10429p.m19045a(str, str2, z, i, i2, i3, z2, fileDownloadHeader, z3);
    }

    @Override // p110z1.IFileDownloadIPCService
    public boolean pause(int i) {
        return this.f10429p.m19043b(i);
    }

    @Override // p110z1.IFileDownloadIPCService
    public void pauseAllTasks() {
        this.f10429p.m19048a();
    }

    @Override // p110z1.IFileDownloadIPCService
    public boolean setMaxNetworkThreadCount(int i) {
        return this.f10429p.m19038f(i);
    }

    @Override // p110z1.IFileDownloadIPCService
    public long getSofar(int i) {
        return this.f10429p.m19041c(i);
    }

    @Override // p110z1.IFileDownloadIPCService
    public long getTotal(int i) {
        return this.f10429p.m19040d(i);
    }

    @Override // p110z1.IFileDownloadIPCService
    public byte getStatus(int i) {
        return this.f10429p.m19039e(i);
    }

    @Override // p110z1.IFileDownloadIPCService
    public boolean isIdle() {
        return this.f10429p.m19044b();
    }

    @Override // p110z1.IFileDownloadIPCService
    public void startForeground(int i, Notification notification) {
        WeakReference<FileDownloadService> weakReference = this.f10430q;
        if (weakReference != null && weakReference.get() != null) {
            this.f10430q.get().startForeground(i, notification);
        }
    }

    @Override // p110z1.IFileDownloadIPCService
    public void stopForeground(boolean z) {
        WeakReference<FileDownloadService> weakReference = this.f10430q;
        if (weakReference != null && weakReference.get() != null) {
            this.f10430q.get().stopForeground(z);
        }
    }

    @Override // p110z1.IFileDownloadIPCService
    public boolean clearTaskData(int i) {
        return this.f10429p.m19037g(i);
    }

    @Override // p110z1.IFileDownloadIPCService
    public void clearAllTaskData() {
        this.f10429p.m19042c();
    }

    @Override // com.liulishuo.filedownloader.services.IFileDownloadServiceHandler
    public void onStartCommand(Intent intent, int i, int i2) {
        FileDownloadServiceProxy.m13652b().mo13649a(this);
    }

    @Override // com.liulishuo.filedownloader.services.IFileDownloadServiceHandler
    public void onDestroy() {
        FileDownloadServiceProxy.m13652b().mo13650a();
    }
}
