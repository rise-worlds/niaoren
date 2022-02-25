package p110z1;

/* renamed from: z1.afl */
/* loaded from: classes3.dex */
public abstract class FileDownloadListener {
    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo13268a(BaseDownloadTask afaVar) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo13267a(BaseDownloadTask afaVar, int i, int i2);

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo13693a(BaseDownloadTask afaVar, String str, boolean z, int i, int i2) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo13266a(BaseDownloadTask afaVar, Throwable th);

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo13265a(BaseDownloadTask afaVar, Throwable th, int i, int i2) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public boolean m13694a() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public void mo13262b(BaseDownloadTask afaVar) throws Throwable {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public abstract void mo13261b(BaseDownloadTask afaVar, int i, int i2);

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public abstract void mo13260c(BaseDownloadTask afaVar);

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public abstract void mo13259c(BaseDownloadTask afaVar, int i, int i2);

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public abstract void mo13258d(BaseDownloadTask afaVar);

    public FileDownloadListener() {
    }

    public FileDownloadListener(int i) {
        FileDownloadLog.m13210d(this, "not handle priority any more", new Object[0]);
    }
}
