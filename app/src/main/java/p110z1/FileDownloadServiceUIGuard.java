package p110z1;

import android.app.Notification;
import android.os.IBinder;
import android.os.RemoteException;
import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.message.MessageSnapshotFlow;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.services.BaseFileServiceUIGuard;
import com.liulishuo.filedownloader.services.FileDownloadService;
import p110z1.IFileDownloadIPCCallback;
import p110z1.IFileDownloadIPCService;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.aft */
/* loaded from: classes3.dex */
public class FileDownloadServiceUIGuard extends BaseFileServiceUIGuard<BinderC3427a, IFileDownloadIPCService> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public FileDownloadServiceUIGuard() {
        super(FileDownloadService.SeparateProcessService.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public BinderC3427a mo13644b() {
        return new BinderC3427a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public IFileDownloadIPCService mo13643b(IBinder iBinder) {
        return IFileDownloadIPCService.AbstractBinderC3473a.asInterface(iBinder);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void mo13642b(IFileDownloadIPCService ahlVar, BinderC3427a aVar) throws RemoteException {
        ahlVar.registerCallback(aVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b  reason: avoid collision after fix types in other method */
    public void mo13646a(IFileDownloadIPCService ahlVar, BinderC3427a aVar) throws RemoteException {
        ahlVar.unregisterCallback(aVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* compiled from: FileDownloadServiceUIGuard.java */
    /* renamed from: z1.aft$a */
    /* loaded from: classes3.dex */
    public static class BinderC3427a extends IFileDownloadIPCCallback.AbstractBinderC3471a {
        protected BinderC3427a() {
        }

        @Override // p110z1.IFileDownloadIPCCallback
        public void callback(MessageSnapshot messageSnapshot) throws RemoteException {
            MessageSnapshotFlow.m19157a().m19156a(messageSnapshot);
        }
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: a */
    public boolean mo13558a(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3) {
        if (!mo13550e()) {
            return DownloadServiceNotConnectedHelper.m13242a(str, str2, z);
        }
        try {
            m19071h().start(str, str2, z, i, i2, i3, z2, fileDownloadHeader, z3);
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: a */
    public boolean mo13563a(int i) {
        if (!mo13550e()) {
            return DownloadServiceNotConnectedHelper.m13245a(i);
        }
        try {
            return m19071h().pause(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: a */
    public boolean mo13559a(String str, String str2) {
        if (!mo13550e()) {
            return DownloadServiceNotConnectedHelper.m13243a(str, str2);
        }
        try {
            return m19071h().checkDownloading(str, str2);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: b */
    public long mo13556b(int i) {
        if (!mo13550e()) {
            return DownloadServiceNotConnectedHelper.m13238b(i);
        }
        try {
            return m19071h().getSofar(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: c */
    public long mo13553c(int i) {
        if (!mo13550e()) {
            return DownloadServiceNotConnectedHelper.m13236c(i);
        }
        try {
            return m19071h().getTotal(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: d */
    public byte mo13551d(int i) {
        if (!mo13550e()) {
            return DownloadServiceNotConnectedHelper.m13235d(i);
        }
        try {
            return m19071h().getStatus(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return (byte) 0;
        }
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: c */
    public void mo13554c() {
        if (!mo13550e()) {
            DownloadServiceNotConnectedHelper.m13246a();
            return;
        }
        try {
            m19071h().pauseAllTasks();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: d */
    public boolean mo13552d() {
        if (!mo13550e()) {
            return DownloadServiceNotConnectedHelper.m13239b();
        }
        try {
            m19071h().isIdle();
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return true;
        }
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: a */
    public void mo13562a(int i, Notification notification) {
        if (!mo13550e()) {
            DownloadServiceNotConnectedHelper.m13244a(i, notification);
            return;
        }
        try {
            m19071h().startForeground(i, notification);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: a */
    public void mo13557a(boolean z) {
        if (!mo13550e()) {
            DownloadServiceNotConnectedHelper.m13240a(z);
            return;
        }
        try {
            m19071h().stopForeground(z);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: e */
    public boolean mo13549e(int i) {
        if (!mo13550e()) {
            return DownloadServiceNotConnectedHelper.m13234e(i);
        }
        try {
            return m19071h().setMaxNetworkThreadCount(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: f */
    public boolean mo13547f(int i) {
        if (!mo13550e()) {
            return DownloadServiceNotConnectedHelper.m13233f(i);
        }
        try {
            return m19071h().clearTaskData(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: f */
    public void mo13548f() {
        if (!mo13550e()) {
            DownloadServiceNotConnectedHelper.m13237c();
            return;
        }
        try {
            m19071h().clearAllTaskData();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
