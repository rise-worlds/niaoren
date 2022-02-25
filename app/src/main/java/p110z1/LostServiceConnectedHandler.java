package p110z1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p110z1.BaseDownloadTask;
import p110z1.DownloadServiceConnectChangedEvent;

/* renamed from: z1.agd */
/* loaded from: classes3.dex */
public class LostServiceConnectedHandler extends FileDownloadConnectListener implements ILostServiceConnectedHandler {

    /* renamed from: a */
    private final ArrayList<BaseDownloadTask.AbstractC3404b> f15616a = new ArrayList<>();

    @Override // p110z1.FileDownloadConnectListener
    /* renamed from: a */
    public void mo13522a() {
        IQueuesHandler m = FileDownloader.m13627a().m13588m();
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "The downloader service is connected.", new Object[0]);
        }
        synchronized (this.f15616a) {
            this.f15616a.clear();
            ArrayList arrayList = new ArrayList(m.mo13509b());
            for (BaseDownloadTask.AbstractC3404b bVar : (List) this.f15616a.clone()) {
                int S = bVar.mo13798S();
                if (m.mo13515a(S)) {
                    bVar.mo13801P().mo13770c().mo13738a();
                    if (!arrayList.contains(Integer.valueOf(S))) {
                        arrayList.add(Integer.valueOf(S));
                    }
                } else {
                    bVar.mo13792Y();
                }
            }
            m.mo13512a(arrayList);
        }
    }

    @Override // p110z1.FileDownloadConnectListener
    /* renamed from: b */
    public void mo13520b() {
        if (m13727c() == DownloadServiceConnectChangedEvent.EnumC3470a.lost) {
            IQueuesHandler m = FileDownloader.m13627a().m13588m();
            if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13211c(this, "lost the connection to the file download service, and current active task size is %d", Integer.valueOf(FileDownloadList.m13710a().m13700c()));
            }
            if (FileDownloadList.m13710a().m13700c() > 0) {
                synchronized (this.f15616a) {
                    FileDownloadList.m13710a().m13707a(this.f15616a);
                    Iterator<BaseDownloadTask.AbstractC3404b> it = this.f15616a.iterator();
                    while (it.hasNext()) {
                        it.next().mo13794W();
                    }
                    m.mo13516a();
                }
                try {
                    FileDownloader.m13627a().m13595g();
                } catch (IllegalStateException unused) {
                    FileDownloadLog.m13210d(this, "restart service failed, you may need to restart downloading manually when the app comes back to foreground", new Object[0]);
                }
            }
        } else if (FileDownloadList.m13710a().m13700c() > 0) {
            FileDownloadLog.m13210d(this, "file download service has be unbound but the size of active tasks are not empty %d ", Integer.valueOf(FileDownloadList.m13710a().m13700c()));
        }
    }

    @Override // p110z1.ILostServiceConnectedHandler
    /* renamed from: a */
    public boolean mo13521a(BaseDownloadTask.AbstractC3404b bVar) {
        return !this.f15616a.isEmpty() && this.f15616a.contains(bVar);
    }

    @Override // p110z1.ILostServiceConnectedHandler
    /* renamed from: b */
    public void mo13519b(BaseDownloadTask.AbstractC3404b bVar) {
        if (!this.f15616a.isEmpty()) {
            synchronized (this.f15616a) {
                this.f15616a.remove(bVar);
            }
        }
    }

    @Override // p110z1.ILostServiceConnectedHandler
    /* renamed from: c */
    public boolean mo13518c(BaseDownloadTask.AbstractC3404b bVar) {
        if (!FileDownloader.m13627a().m13591j()) {
            synchronized (this.f15616a) {
                if (!FileDownloader.m13627a().m13591j()) {
                    if (FileDownloadLog.f15845a) {
                        FileDownloadLog.m13211c(this, "Waiting for connecting with the downloader service... %d", Integer.valueOf(bVar.mo13801P().mo13754k()));
                    }
                    FileDownloadServiceProxy.m13653a().mo13561a(FileDownloadHelper.m13229a());
                    if (!this.f15616a.contains(bVar)) {
                        bVar.mo13794W();
                        this.f15616a.add(bVar);
                    }
                    return true;
                }
            }
        }
        mo13519b(bVar);
        return false;
    }
}
