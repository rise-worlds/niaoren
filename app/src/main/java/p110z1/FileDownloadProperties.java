package p110z1;

/* renamed from: z1.ahv */
/* loaded from: classes3.dex */
public class FileDownloadProperties {

    /* renamed from: i */
    private static final String f15847i = "http.lenient";

    /* renamed from: j */
    private static final String f15848j = "process.non-separate";

    /* renamed from: k */
    private static final String f15849k = "download.min-progress-step";

    /* renamed from: l */
    private static final String f15850l = "download.min-progress-time";

    /* renamed from: m */
    private static final String f15851m = "download.max-network-thread-count";

    /* renamed from: n */
    private static final String f15852n = "file.non-pre-allocation";

    /* renamed from: o */
    private static final String f15853o = "broadcast.completed";

    /* renamed from: p */
    private static final String f15854p = "download.trial-connection-head-method";

    /* renamed from: q */
    private static final String f15855q = "true";

    /* renamed from: r */
    private static final String f15856r = "false";

    /* renamed from: a */
    public final int f15857a;

    /* renamed from: b */
    public final long f15858b;

    /* renamed from: c */
    public final boolean f15859c;

    /* renamed from: d */
    public final boolean f15860d;

    /* renamed from: e */
    public final int f15861e;

    /* renamed from: f */
    public final boolean f15862f;

    /* renamed from: g */
    public final boolean f15863g;

    /* renamed from: h */
    public final boolean f15864h;

    /* compiled from: FileDownloadProperties.java */
    /* renamed from: z1.ahv$a */
    /* loaded from: classes3.dex */
    public static class C3483a {

        /* renamed from: a */
        private static final FileDownloadProperties f15865a = new FileDownloadProperties();
    }

    /* renamed from: a */
    public static FileDownloadProperties m13208a() {
        return C3483a.f15865a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x02ae A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x00ab A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:149:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0098 A[Catch: all -> 0x02aa, TryCatch #3 {all -> 0x02aa, blocks: (B:7:0x0026, B:8:0x002f, B:9:0x0035, B:10:0x003b, B:11:0x0041, B:12:0x0047, B:13:0x004d, B:14:0x0053, B:46:0x0094, B:48:0x0098, B:50:0x009c, B:51:0x00a6), top: B:129:0x0016 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00a6 A[Catch: all -> 0x02aa, TRY_LEAVE, TryCatch #3 {all -> 0x02aa, blocks: (B:7:0x0026, B:8:0x002f, B:9:0x0035, B:10:0x003b, B:11:0x0041, B:12:0x0047, B:13:0x004d, B:14:0x0053, B:46:0x0094, B:48:0x0098, B:50:0x009c, B:51:0x00a6), top: B:129:0x0016 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01a8  */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v35 */
    /* JADX WARN: Type inference failed for: r4v36 */
    /* JADX WARN: Type inference failed for: r4v37 */
    /* JADX WARN: Type inference failed for: r7v26 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private FileDownloadProperties() {
        /*
            Method dump skipped, instructions count: 704
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.FileDownloadProperties.<init>():void");
    }

    /* renamed from: a */
    public static int m13207a(int i) {
        if (i > 12) {
            FileDownloadLog.m13210d(FileDownloadProperties.class, "require the count of network thread  is %d, what is more than the max valid count(%d), so adjust to %d auto", Integer.valueOf(i), 12, 12);
            return 12;
        } else if (i >= 1) {
            return i;
        } else {
            FileDownloadLog.m13210d(FileDownloadProperties.class, "require the count of network thread  is %d, what is less than the min valid count(%d), so adjust to %d auto", Integer.valueOf(i), 1, 1);
            return 1;
        }
    }
}
