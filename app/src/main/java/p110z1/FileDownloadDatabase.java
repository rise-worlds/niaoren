package p110z1;

import com.liulishuo.filedownloader.model.ConnectionModel;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.util.List;

/* renamed from: z1.agl */
/* loaded from: classes3.dex */
public interface FileDownloadDatabase {

    /* compiled from: FileDownloadDatabase.java */
    /* renamed from: z1.agl$a */
    /* loaded from: classes3.dex */
    public interface AbstractC3443a extends Iterable<FileDownloadModel> {
        /* renamed from: a */
        void mo13443a();

        /* renamed from: a */
        void mo13442a(int i, FileDownloadModel fileDownloadModel);

        /* renamed from: a */
        void mo13441a(FileDownloadModel fileDownloadModel);

        /* renamed from: b */
        void mo13440b(FileDownloadModel fileDownloadModel);
    }

    /* renamed from: a */
    void mo13469a();

    /* renamed from: a */
    void mo13468a(int i);

    /* renamed from: a */
    void mo13467a(int i, int i2);

    /* renamed from: a */
    void mo13466a(int i, int i2, long j);

    /* renamed from: a */
    void mo13465a(int i, long j);

    /* renamed from: a */
    void mo13464a(int i, long j, String str, String str2);

    /* renamed from: a */
    void mo13462a(int i, String str, long j, long j2, int i2);

    /* renamed from: a */
    void mo13461a(int i, Throwable th);

    /* renamed from: a */
    void mo13460a(int i, Throwable th, long j);

    /* renamed from: a */
    void mo13457a(FileDownloadModel fileDownloadModel);

    /* renamed from: a */
    void mo13456a(ConnectionModel aVar);

    /* renamed from: b */
    FileDownloadModel mo13453b(int i);

    /* renamed from: b */
    AbstractC3443a mo13454b();

    /* renamed from: b */
    void mo13452b(int i, long j);

    /* renamed from: b */
    void mo13450b(FileDownloadModel fileDownloadModel);

    /* renamed from: c */
    List<ConnectionModel> mo13448c(int i);

    /* renamed from: c */
    void mo13447c(int i, long j);

    /* renamed from: d */
    void mo13446d(int i);

    /* renamed from: e */
    boolean mo13445e(int i);

    /* renamed from: f */
    void mo13444f(int i);
}
