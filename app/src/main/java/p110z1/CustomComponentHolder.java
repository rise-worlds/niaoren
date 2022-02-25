package p110z1;

import com.liulishuo.filedownloader.services.DownloadMgrInitialParams;
import java.io.File;
import java.io.IOException;
import p110z1.FileDownloadHelper;

/* renamed from: z1.ags */
/* loaded from: classes3.dex */
public class CustomComponentHolder {

    /* renamed from: a */
    private DownloadMgrInitialParams f15688a;

    /* renamed from: b */
    private FileDownloadHelper.AbstractC3477a f15689b;

    /* renamed from: c */
    private FileDownloadHelper.AbstractC3478b f15690c;

    /* renamed from: d */
    private FileDownloadHelper.AbstractC3481e f15691d;

    /* renamed from: e */
    private FileDownloadDatabase f15692e;

    /* renamed from: f */
    private FileDownloadHelper.AbstractC3480d f15693f;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: CustomComponentHolder.java */
    /* renamed from: z1.ags$a */
    /* loaded from: classes3.dex */
    public static final class C3457a {

        /* renamed from: a */
        private static final CustomComponentHolder f15694a = new CustomComponentHolder();

        private C3457a() {
        }
    }

    /* renamed from: a */
    public static CustomComponentHolder m13415a() {
        return C3457a.f15694a;
    }

    /* renamed from: a */
    public void m13413a(DownloadMgrInitialParams.C1699a aVar) {
        synchronized (this) {
            this.f15688a = new DownloadMgrInitialParams(aVar);
            this.f15690c = null;
            this.f15691d = null;
            this.f15692e = null;
            this.f15693f = null;
        }
    }

    /* renamed from: a */
    public FileDownloadConnection m13411a(String str) throws IOException {
        return m13404g().mo13223a(str);
    }

    /* renamed from: a */
    public FileDownloadOutputStream m13412a(File file) throws IOException {
        return m13403h().mo13218a(file);
    }

    /* renamed from: b */
    public FileDownloadHelper.AbstractC3480d m13409b() {
        FileDownloadHelper.AbstractC3480d dVar = this.f15693f;
        if (dVar != null) {
            return dVar;
        }
        synchronized (this) {
            if (this.f15693f == null) {
                this.f15693f = m13402i().m19065f();
            }
        }
        return this.f15693f;
    }

    /* renamed from: c */
    public FileDownloadDatabase m13408c() {
        FileDownloadDatabase aglVar = this.f15692e;
        if (aglVar != null) {
            return aglVar;
        }
        synchronized (this) {
            if (this.f15692e == null) {
                this.f15692e = m13402i().m19069b();
                m13410a(this.f15692e.mo13454b());
            }
        }
        return this.f15692e;
    }

    /* renamed from: d */
    public int m13407d() {
        return m13402i().m19070a();
    }

    /* renamed from: e */
    public boolean m13406e() {
        return m13403h().mo13219a();
    }

    /* renamed from: a */
    public int m13414a(int i, String str, String str2, long j) {
        return m13405f().mo13224a(i, str, str2, j);
    }

    /* renamed from: f */
    private FileDownloadHelper.AbstractC3477a m13405f() {
        FileDownloadHelper.AbstractC3477a aVar = this.f15689b;
        if (aVar != null) {
            return aVar;
        }
        synchronized (this) {
            if (this.f15689b == null) {
                this.f15689b = m13402i().m19066e();
            }
        }
        return this.f15689b;
    }

    /* renamed from: g */
    private FileDownloadHelper.AbstractC3478b m13404g() {
        FileDownloadHelper.AbstractC3478b bVar = this.f15690c;
        if (bVar != null) {
            return bVar;
        }
        synchronized (this) {
            if (this.f15690c == null) {
                this.f15690c = m13402i().m19067d();
            }
        }
        return this.f15690c;
    }

    /* renamed from: h */
    private FileDownloadHelper.AbstractC3481e m13403h() {
        FileDownloadHelper.AbstractC3481e eVar = this.f15691d;
        if (eVar != null) {
            return eVar;
        }
        synchronized (this) {
            if (this.f15691d == null) {
                this.f15691d = m13402i().m19068c();
            }
        }
        return this.f15691d;
    }

    /* renamed from: i */
    private DownloadMgrInitialParams m13402i() {
        DownloadMgrInitialParams cVar = this.f15688a;
        if (cVar != null) {
            return cVar;
        }
        synchronized (this) {
            if (this.f15688a == null) {
                this.f15688a = new DownloadMgrInitialParams();
            }
        }
        return this.f15688a;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:35:0x00bb
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: a */
    private static void m13410a(p110z1.FileDownloadDatabase.AbstractC3443a r25) {
        /*
            Method dump skipped, instructions count: 458
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.CustomComponentHolder.m13410a(z1.agl$a):void");
    }
}
