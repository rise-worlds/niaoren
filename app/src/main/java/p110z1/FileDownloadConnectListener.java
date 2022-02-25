package p110z1;

import p110z1.DownloadServiceConnectChangedEvent;

/* renamed from: z1.aff */
/* loaded from: classes3.dex */
public abstract class FileDownloadConnectListener extends IDownloadListener {

    /* renamed from: a */
    private DownloadServiceConnectChangedEvent.EnumC3470a f15545a;

    /* renamed from: a */
    public abstract void mo13522a();

    /* renamed from: b */
    public abstract void mo13520b();

    @Override // p110z1.IDownloadListener
    /* renamed from: a */
    public boolean mo13300a(IDownloadEvent ahbVar) {
        if (!(ahbVar instanceof DownloadServiceConnectChangedEvent)) {
            return false;
        }
        this.f15545a = ((DownloadServiceConnectChangedEvent) ahbVar).m13307a();
        if (this.f15545a == DownloadServiceConnectChangedEvent.EnumC3470a.connected) {
            mo13522a();
            return false;
        }
        mo13520b();
        return false;
    }

    /* renamed from: c */
    public DownloadServiceConnectChangedEvent.EnumC3470a m13727c() {
        return this.f15545a;
    }
}
