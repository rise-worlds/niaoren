package com.liulishuo.filedownloader.services;

import p110z1.DefaultConnectionCountAdapter;
import p110z1.FileDownloadDatabase;
import p110z1.FileDownloadHelper;
import p110z1.FileDownloadLog;
import p110z1.FileDownloadProperties;
import p110z1.FileDownloadRandomAccessFile;
import p110z1.FileDownloadUrlConnection;
import p110z1.FileDownloadUtils;
import p110z1.RemitDatabase;

/* renamed from: com.liulishuo.filedownloader.services.c */
/* loaded from: classes.dex */
public class DownloadMgrInitialParams {

    /* renamed from: a */
    private final C1699a f10419a;

    public DownloadMgrInitialParams() {
        this.f10419a = null;
    }

    public DownloadMgrInitialParams(C1699a aVar) {
        this.f10419a = aVar;
    }

    /* renamed from: a */
    public int m19070a() {
        C1699a aVar = this.f10419a;
        if (aVar == null) {
            return m19063h();
        }
        Integer num = aVar.f10421b;
        if (num == null) {
            return m19063h();
        }
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "initial FileDownloader manager with the customize maxNetworkThreadCount: %d", num);
        }
        return FileDownloadProperties.m13207a(num.intValue());
    }

    /* renamed from: b */
    public FileDownloadDatabase m19069b() {
        C1699a aVar = this.f10419a;
        if (aVar == null || aVar.f10420a == null) {
            return m19062i();
        }
        FileDownloadDatabase a = this.f10419a.f10420a.mo13222a();
        if (a == null) {
            return m19062i();
        }
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "initial FileDownloader manager with the customize database: %s", a);
        }
        return a;
    }

    /* renamed from: c */
    public FileDownloadHelper.AbstractC3481e m19068c() {
        C1699a aVar = this.f10419a;
        if (aVar == null) {
            return m19061j();
        }
        FileDownloadHelper.AbstractC3481e eVar = aVar.f10422c;
        if (eVar == null) {
            return m19061j();
        }
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "initial FileDownloader manager with the customize output stream: %s", eVar);
        }
        return eVar;
    }

    /* renamed from: d */
    public FileDownloadHelper.AbstractC3478b m19067d() {
        C1699a aVar = this.f10419a;
        if (aVar == null) {
            return m19060k();
        }
        FileDownloadHelper.AbstractC3478b bVar = aVar.f10423d;
        if (bVar == null) {
            return m19060k();
        }
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "initial FileDownloader manager with the customize connection creator: %s", bVar);
        }
        return bVar;
    }

    /* renamed from: e */
    public FileDownloadHelper.AbstractC3477a m19066e() {
        C1699a aVar = this.f10419a;
        if (aVar == null) {
            return m19059l();
        }
        FileDownloadHelper.AbstractC3477a aVar2 = aVar.f10424e;
        if (aVar2 == null) {
            return m19059l();
        }
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "initial FileDownloader manager with the customize connection count adapter: %s", aVar2);
        }
        return aVar2;
    }

    /* renamed from: f */
    public FileDownloadHelper.AbstractC3480d m19065f() {
        C1699a aVar = this.f10419a;
        if (aVar == null) {
            return m19064g();
        }
        FileDownloadHelper.AbstractC3480d dVar = aVar.f10425f;
        if (dVar == null) {
            return m19064g();
        }
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "initial FileDownloader manager with the customize id generator: %s", dVar);
        }
        return dVar;
    }

    /* renamed from: g */
    private FileDownloadHelper.AbstractC3480d m19064g() {
        return new DefaultIdGenerator();
    }

    /* renamed from: h */
    private int m19063h() {
        return FileDownloadProperties.m13208a().f15861e;
    }

    /* renamed from: i */
    private FileDownloadDatabase m19062i() {
        return new RemitDatabase();
    }

    /* renamed from: j */
    private FileDownloadHelper.AbstractC3481e m19061j() {
        return new FileDownloadRandomAccessFile.C3475a();
    }

    /* renamed from: k */
    private FileDownloadHelper.AbstractC3478b m19060k() {
        return new FileDownloadUrlConnection.C3442b();
    }

    /* renamed from: l */
    private FileDownloadHelper.AbstractC3477a m19059l() {
        return new DefaultConnectionCountAdapter();
    }

    /* compiled from: DownloadMgrInitialParams.java */
    /* renamed from: com.liulishuo.filedownloader.services.c$a */
    /* loaded from: classes.dex */
    public static class C1699a {

        /* renamed from: a */
        FileDownloadHelper.AbstractC3479c f10420a;

        /* renamed from: b */
        Integer f10421b;

        /* renamed from: c */
        FileDownloadHelper.AbstractC3481e f10422c;

        /* renamed from: d */
        FileDownloadHelper.AbstractC3478b f10423d;

        /* renamed from: e */
        FileDownloadHelper.AbstractC3477a f10424e;

        /* renamed from: f */
        FileDownloadHelper.AbstractC3480d f10425f;

        /* renamed from: a */
        public void m19058a() {
        }

        /* renamed from: a */
        public C1699a m19053a(FileDownloadHelper.AbstractC3480d dVar) {
            this.f10425f = dVar;
            return this;
        }

        /* renamed from: a */
        public C1699a m19056a(FileDownloadHelper.AbstractC3477a aVar) {
            this.f10424e = aVar;
            return this;
        }

        /* renamed from: a */
        public C1699a m19054a(FileDownloadHelper.AbstractC3479c cVar) {
            this.f10420a = cVar;
            return this;
        }

        /* renamed from: a */
        public C1699a m19057a(int i) {
            if (i > 0) {
                this.f10421b = Integer.valueOf(i);
            }
            return this;
        }

        /* renamed from: a */
        public C1699a m19052a(FileDownloadHelper.AbstractC3481e eVar) {
            this.f10422c = eVar;
            FileDownloadHelper.AbstractC3481e eVar2 = this.f10422c;
            if (eVar2 == null || eVar2.mo13219a() || FileDownloadProperties.m13208a().f15862f) {
                return this;
            }
            throw new IllegalArgumentException("Since the provided FileDownloadOutputStream does not support the seek function, if FileDownloader pre-allocates file size at the beginning of the download, it will can not be resumed from the breakpoint. If you need to ensure that the resumption is available, please add and set the value of 'file.non-pre-allocation' field to 'true' in the 'filedownloader.properties' file which is in your application assets folder manually for resolving this problem.");
        }

        /* renamed from: a */
        public C1699a m19055a(FileDownloadHelper.AbstractC3478b bVar) {
            this.f10423d = bVar;
            return this;
        }

        public String toString() {
            return FileDownloadUtils.m13182a("component: database[%s], maxNetworkCount[%s], outputStream[%s], connection[%s], connectionCountAdapter[%s]", this.f10420a, this.f10421b, this.f10422c, this.f10423d, this.f10424e);
        }
    }
}
