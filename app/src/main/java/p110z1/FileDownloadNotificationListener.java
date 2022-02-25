package p110z1;

import p110z1.BaseDownloadTask;

/* renamed from: z1.aho */
/* loaded from: classes3.dex */
public abstract class FileDownloadNotificationListener extends FileDownloadListener {

    /* renamed from: a */
    private final FileDownloadNotificationHelper f15833a;

    /* renamed from: a */
    protected boolean m13264a(BaseDownloadTask afaVar, BaseNotificationItem ahmVar) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // p110z1.FileDownloadListener
    /* renamed from: b */
    public void mo13262b(BaseDownloadTask afaVar) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // p110z1.FileDownloadListener
    /* renamed from: d */
    public void mo13258d(BaseDownloadTask afaVar) {
    }

    /* renamed from: h */
    protected abstract BaseNotificationItem m13253h(BaseDownloadTask afaVar);

    /* renamed from: i */
    protected boolean m13252i(BaseDownloadTask afaVar) {
        return false;
    }

    public FileDownloadNotificationListener(FileDownloadNotificationHelper ahnVar) {
        if (ahnVar != null) {
            this.f15833a = ahnVar;
            return;
        }
        throw new IllegalArgumentException("helper must not be null!");
    }

    /* renamed from: b */
    public FileDownloadNotificationHelper m13263b() {
        return this.f15833a;
    }

    /* renamed from: a */
    public void m13269a(int i) {
        BaseDownloadTask.AbstractC3404b b;
        if (i != 0 && (b = FileDownloadList.m13710a().m13702b(i)) != null) {
            m13256e(b.mo13801P());
        }
    }

    /* renamed from: e */
    public void m13256e(BaseDownloadTask afaVar) {
        BaseNotificationItem h;
        if (!m13252i(afaVar) && (h = m13253h(afaVar)) != null) {
            this.f15833a.m13273a((FileDownloadNotificationHelper) h);
        }
    }

    /* renamed from: f */
    public void m13255f(BaseDownloadTask afaVar) {
        if (!m13252i(afaVar)) {
            this.f15833a.m13275a(afaVar.mo13754k(), afaVar.mo13815B());
            BaseNotificationItem c = this.f15833a.m13271c(afaVar.mo13754k());
            if (!m13264a(afaVar, c) && c != null) {
                c.m13297a();
            }
        }
    }

    /* renamed from: g */
    public void m13254g(BaseDownloadTask afaVar) {
        if (!m13252i(afaVar)) {
            this.f15833a.m13275a(afaVar.mo13754k(), afaVar.mo13815B());
        }
    }

    /* renamed from: d */
    public void m13257d(BaseDownloadTask afaVar, int i, int i2) {
        if (!m13252i(afaVar)) {
            this.f15833a.m13274a(afaVar.mo13754k(), afaVar.mo13743v(), afaVar.mo13740y());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // p110z1.FileDownloadListener
    /* renamed from: a */
    public void mo13267a(BaseDownloadTask afaVar, int i, int i2) {
        m13256e(afaVar);
        m13254g(afaVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // p110z1.FileDownloadListener
    /* renamed from: a */
    public void mo13268a(BaseDownloadTask afaVar) {
        super.mo13268a(afaVar);
        m13254g(afaVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // p110z1.FileDownloadListener
    /* renamed from: b */
    public void mo13261b(BaseDownloadTask afaVar, int i, int i2) {
        m13257d(afaVar, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // p110z1.FileDownloadListener
    /* renamed from: a */
    public void mo13265a(BaseDownloadTask afaVar, Throwable th, int i, int i2) {
        super.mo13265a(afaVar, th, i, i2);
        m13254g(afaVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // p110z1.FileDownloadListener
    /* renamed from: c */
    public void mo13260c(BaseDownloadTask afaVar) {
        m13255f(afaVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // p110z1.FileDownloadListener
    /* renamed from: c */
    public void mo13259c(BaseDownloadTask afaVar, int i, int i2) {
        m13255f(afaVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // p110z1.FileDownloadListener
    /* renamed from: a */
    public void mo13266a(BaseDownloadTask afaVar, Throwable th) {
        m13255f(afaVar);
    }
}
