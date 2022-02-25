package p110z1;

/* renamed from: z1.ahb */
/* loaded from: classes3.dex */
public abstract class IDownloadEvent {

    /* renamed from: b */
    public Runnable f15802b = null;

    /* renamed from: c */
    protected final String f15803c;

    public IDownloadEvent(String str) {
        this.f15803c = str;
    }

    public IDownloadEvent(String str, boolean z) {
        this.f15803c = str;
        if (z) {
            FileDownloadLog.m13210d(this, "do not handle ORDER any more, %s", str);
        }
    }

    /* renamed from: b */
    public final String m13305b() {
        return this.f15803c;
    }
}
