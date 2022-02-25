package p110z1;

/* renamed from: z1.aha */
/* loaded from: classes3.dex */
public class DownloadServiceConnectChangedEvent extends IDownloadEvent {

    /* renamed from: a */
    public static final String f15799a = "event.service.connect.changed";

    /* renamed from: d */
    private final EnumC3470a f15800d;

    /* renamed from: e */
    private final Class<?> f15801e;

    /* compiled from: DownloadServiceConnectChangedEvent.java */
    /* renamed from: z1.aha$a */
    /* loaded from: classes3.dex */
    public enum EnumC3470a {
        connected,
        disconnected,
        lost
    }

    public DownloadServiceConnectChangedEvent(EnumC3470a aVar, Class<?> cls) {
        super(f15799a);
        this.f15800d = aVar;
        this.f15801e = cls;
    }

    /* renamed from: a */
    public EnumC3470a m13307a() {
        return this.f15800d;
    }

    /* renamed from: a */
    public boolean m13306a(Class<?> cls) {
        Class<?> cls2 = this.f15801e;
        return cls2 != null && cls2.getName().equals(cls.getName());
    }
}
