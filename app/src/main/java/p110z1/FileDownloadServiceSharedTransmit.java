package p110z1;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.services.FDServiceSharedHandler;
import com.liulishuo.filedownloader.services.FileDownloadService;
import java.util.ArrayList;
import java.util.List;
import p110z1.DownloadServiceConnectChangedEvent;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.afs */
/* loaded from: classes3.dex */
public class FileDownloadServiceSharedTransmit implements FDServiceSharedHandler.AbstractC1700a, IFileDownloadServiceProxy {

    /* renamed from: a */
    private static final Class<?> f15600a = FileDownloadService.SharedMainProcessService.class;

    /* renamed from: b */
    private final ArrayList<Runnable> f15601b = new ArrayList<>();

    /* renamed from: c */
    private FDServiceSharedHandler f15602c;

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: a */
    public boolean mo13558a(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3) {
        if (!mo13550e()) {
            return DownloadServiceNotConnectedHelper.m13242a(str, str2, z);
        }
        this.f15602c.start(str, str2, z, i, i2, i3, z2, fileDownloadHeader, z3);
        return true;
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: a */
    public boolean mo13563a(int i) {
        if (!mo13550e()) {
            return DownloadServiceNotConnectedHelper.m13245a(i);
        }
        return this.f15602c.pause(i);
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: a */
    public boolean mo13559a(String str, String str2) {
        if (!mo13550e()) {
            return DownloadServiceNotConnectedHelper.m13243a(str, str2);
        }
        return this.f15602c.checkDownloading(str, str2);
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: b */
    public long mo13556b(int i) {
        if (!mo13550e()) {
            return DownloadServiceNotConnectedHelper.m13238b(i);
        }
        return this.f15602c.getSofar(i);
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: c */
    public long mo13553c(int i) {
        if (!mo13550e()) {
            return DownloadServiceNotConnectedHelper.m13236c(i);
        }
        return this.f15602c.getTotal(i);
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: d */
    public byte mo13551d(int i) {
        if (!mo13550e()) {
            return DownloadServiceNotConnectedHelper.m13235d(i);
        }
        return this.f15602c.getStatus(i);
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: c */
    public void mo13554c() {
        if (!mo13550e()) {
            DownloadServiceNotConnectedHelper.m13246a();
        } else {
            this.f15602c.pauseAllTasks();
        }
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: d */
    public boolean mo13552d() {
        if (!mo13550e()) {
            return DownloadServiceNotConnectedHelper.m13239b();
        }
        return this.f15602c.isIdle();
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: e */
    public boolean mo13550e() {
        return this.f15602c != null;
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: a */
    public void mo13561a(Context context) {
        mo13560a(context, (Runnable) null);
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: a */
    public void mo13560a(Context context, Runnable runnable) {
        if (runnable != null && !this.f15601b.contains(runnable)) {
            this.f15601b.add(runnable);
        }
        context.startService(new Intent(context, f15600a));
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: b */
    public void mo13555b(Context context) {
        context.stopService(new Intent(context, f15600a));
        this.f15602c = null;
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: a */
    public void mo13562a(int i, Notification notification) {
        if (!mo13550e()) {
            DownloadServiceNotConnectedHelper.m13244a(i, notification);
        } else {
            this.f15602c.startForeground(i, notification);
        }
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: a */
    public void mo13557a(boolean z) {
        if (!mo13550e()) {
            DownloadServiceNotConnectedHelper.m13240a(z);
        } else {
            this.f15602c.stopForeground(z);
        }
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: e */
    public boolean mo13549e(int i) {
        if (!mo13550e()) {
            return DownloadServiceNotConnectedHelper.m13234e(i);
        }
        return this.f15602c.setMaxNetworkThreadCount(i);
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: f */
    public boolean mo13547f(int i) {
        if (!mo13550e()) {
            return DownloadServiceNotConnectedHelper.m13233f(i);
        }
        return this.f15602c.clearTaskData(i);
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: f */
    public void mo13548f() {
        if (!mo13550e()) {
            DownloadServiceNotConnectedHelper.m13237c();
        } else {
            this.f15602c.clearAllTaskData();
        }
    }

    @Override // com.liulishuo.filedownloader.services.FDServiceSharedHandler.AbstractC1700a
    /* renamed from: a */
    public void mo13649a(FDServiceSharedHandler eVar) {
        this.f15602c = eVar;
        this.f15601b.clear();
        for (Runnable runnable : (List) this.f15601b.clone()) {
            runnable.run();
        }
        FileDownloadEventPool.m13726a().mo13301b(new DownloadServiceConnectChangedEvent(DownloadServiceConnectChangedEvent.EnumC3470a.connected, f15600a));
    }

    @Override // com.liulishuo.filedownloader.services.FDServiceSharedHandler.AbstractC1700a
    /* renamed from: a */
    public void mo13650a() {
        this.f15602c = null;
        FileDownloadEventPool.m13726a().mo13301b(new DownloadServiceConnectChangedEvent(DownloadServiceConnectChangedEvent.EnumC3470a.disconnected, f15600a));
    }
}
