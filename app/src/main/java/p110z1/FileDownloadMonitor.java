package p110z1;

/* renamed from: z1.afo */
/* loaded from: classes3.dex */
public class FileDownloadMonitor {

    /* renamed from: a */
    private static AbstractC3424a f15585a;

    /* compiled from: FileDownloadMonitor.java */
    /* renamed from: z1.afo$a */
    /* loaded from: classes3.dex */
    public interface AbstractC3424a {
        /* renamed from: a */
        void m13675a(int i, boolean z, FileDownloadListener aflVar);

        /* renamed from: a */
        void m13674a(BaseDownloadTask afaVar);

        /* renamed from: b */
        void m13673b(BaseDownloadTask afaVar);

        /* renamed from: c */
        void m13672c(BaseDownloadTask afaVar);

        /* renamed from: d */
        void m13671d(BaseDownloadTask afaVar);
    }

    /* renamed from: a */
    public static void m13678a(AbstractC3424a aVar) {
        f15585a = aVar;
    }

    /* renamed from: a */
    public static void m13679a() {
        f15585a = null;
    }

    /* renamed from: b */
    public static AbstractC3424a m13677b() {
        return f15585a;
    }

    /* renamed from: c */
    public static boolean m13676c() {
        return m13677b() != null;
    }
}
