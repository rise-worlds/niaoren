package p110z1;

/* renamed from: z1.agz */
/* loaded from: classes3.dex */
public class DownloadEventSampleListener extends IDownloadListener {

    /* renamed from: a */
    private final AbstractC3468a f15798a;

    /* compiled from: DownloadEventSampleListener.java */
    /* renamed from: z1.agz$a */
    /* loaded from: classes3.dex */
    public interface AbstractC3468a {
        /* renamed from: a */
        boolean m13308a(IDownloadEvent ahbVar);
    }

    public DownloadEventSampleListener(AbstractC3468a aVar) {
        this.f15798a = aVar;
    }

    @Override // p110z1.IDownloadListener
    /* renamed from: a */
    public boolean mo13300a(IDownloadEvent ahbVar) {
        AbstractC3468a aVar = this.f15798a;
        return aVar != null && aVar.m13308a(ahbVar);
    }
}
