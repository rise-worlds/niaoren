package p110z1;

import android.app.Notification;
import android.content.Context;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.services.FDServiceSharedHandler;

/* renamed from: z1.afr */
/* loaded from: classes3.dex */
public class FileDownloadServiceProxy implements IFileDownloadServiceProxy {

    /* renamed from: a */
    private final IFileDownloadServiceProxy f15598a;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FileDownloadServiceProxy.java */
    /* renamed from: z1.afr$a */
    /* loaded from: classes3.dex */
    public static final class C3426a {

        /* renamed from: a */
        private static final FileDownloadServiceProxy f15599a = new FileDownloadServiceProxy();

        private C3426a() {
        }
    }

    /* renamed from: a */
    public static FileDownloadServiceProxy m13653a() {
        return C3426a.f15599a;
    }

    /* renamed from: b */
    public static FDServiceSharedHandler.AbstractC1700a m13652b() {
        if (m13653a().f15598a instanceof FileDownloadServiceSharedTransmit) {
            return (FDServiceSharedHandler.AbstractC1700a) m13653a().f15598a;
        }
        return null;
    }

    private FileDownloadServiceProxy() {
        this.f15598a = FileDownloadProperties.m13208a().f15860d ? new FileDownloadServiceSharedTransmit() : new FileDownloadServiceUIGuard();
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: a */
    public boolean mo13558a(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3) {
        return this.f15598a.mo13558a(str, str2, z, i, i2, i3, z2, fileDownloadHeader, z3);
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: a */
    public boolean mo13563a(int i) {
        return this.f15598a.mo13563a(i);
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: a */
    public boolean mo13559a(String str, String str2) {
        return this.f15598a.mo13559a(str, str2);
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: b */
    public long mo13556b(int i) {
        return this.f15598a.mo13556b(i);
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: c */
    public long mo13553c(int i) {
        return this.f15598a.mo13553c(i);
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: d */
    public byte mo13551d(int i) {
        return this.f15598a.mo13551d(i);
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: c */
    public void mo13554c() {
        this.f15598a.mo13554c();
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: d */
    public boolean mo13552d() {
        return this.f15598a.mo13552d();
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: e */
    public boolean mo13550e() {
        return this.f15598a.mo13550e();
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: a */
    public void mo13561a(Context context) {
        this.f15598a.mo13561a(context);
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: a */
    public void mo13560a(Context context, Runnable runnable) {
        this.f15598a.mo13560a(context, runnable);
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: b */
    public void mo13555b(Context context) {
        this.f15598a.mo13555b(context);
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: a */
    public void mo13562a(int i, Notification notification) {
        this.f15598a.mo13562a(i, notification);
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: a */
    public void mo13557a(boolean z) {
        this.f15598a.mo13557a(z);
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: e */
    public boolean mo13549e(int i) {
        return this.f15598a.mo13549e(i);
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: f */
    public boolean mo13547f(int i) {
        return this.f15598a.mo13547f(i);
    }

    @Override // p110z1.IFileDownloadServiceProxy
    /* renamed from: f */
    public void mo13548f() {
        this.f15598a.mo13548f();
    }
}
