package p110z1;

import android.annotation.SuppressLint;
import android.content.Context;
import com.liulishuo.filedownloader.message.MessageSnapshotFlow;
import com.liulishuo.filedownloader.message.MessageSnapshotTaker;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.io.File;
import java.io.IOException;

/* renamed from: z1.aht */
/* loaded from: classes3.dex */
public class FileDownloadHelper {
    @SuppressLint({"StaticFieldLeak"})

    /* renamed from: a */
    private static Context f15844a;

    /* compiled from: FileDownloadHelper.java */
    /* renamed from: z1.aht$a */
    /* loaded from: classes3.dex */
    public interface AbstractC3477a {
        /* renamed from: a */
        int mo13224a(int i, String str, String str2, long j);
    }

    /* compiled from: FileDownloadHelper.java */
    /* renamed from: z1.aht$b */
    /* loaded from: classes3.dex */
    public interface AbstractC3478b {
        /* renamed from: a */
        FileDownloadConnection mo13223a(String str) throws IOException;
    }

    /* compiled from: FileDownloadHelper.java */
    /* renamed from: z1.aht$c */
    /* loaded from: classes3.dex */
    public interface AbstractC3479c {
        /* renamed from: a */
        FileDownloadDatabase mo13222a();
    }

    /* compiled from: FileDownloadHelper.java */
    /* renamed from: z1.aht$d */
    /* loaded from: classes3.dex */
    public interface AbstractC3480d {
        /* renamed from: a */
        int mo13221a(int i, String str, String str2, boolean z);

        /* renamed from: a */
        int mo13220a(String str, String str2, boolean z);
    }

    /* compiled from: FileDownloadHelper.java */
    /* renamed from: z1.aht$e */
    /* loaded from: classes3.dex */
    public interface AbstractC3481e {
        /* renamed from: a */
        FileDownloadOutputStream mo13218a(File file) throws IOException;

        /* renamed from: a */
        boolean mo13219a();
    }

    /* renamed from: a */
    public static void m13225a(Context context) {
        f15844a = context;
    }

    /* renamed from: a */
    public static Context m13229a() {
        return f15844a;
    }

    /* renamed from: a */
    public static boolean m13226a(int i, String str, boolean z, boolean z2) {
        if (!z && str != null) {
            File file = new File(str);
            if (file.exists()) {
                MessageSnapshotFlow.m19157a().m19156a(MessageSnapshotTaker.m19149a(i, file, z2));
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public static boolean m13227a(int i, FileDownloadModel fileDownloadModel, IThreadPoolMonitor agcVar, boolean z) {
        if (!agcVar.mo13524a(fileDownloadModel)) {
            return false;
        }
        MessageSnapshotFlow.m19157a().m19156a(MessageSnapshotTaker.m19151a(i, fileDownloadModel.m19118g(), fileDownloadModel.m19117h(), z));
        return true;
    }

    /* renamed from: a */
    public static boolean m13228a(int i, long j, String str, String str2, IThreadPoolMonitor agcVar) {
        int a;
        if (str2 == null || str == null || (a = agcVar.mo13523a(str, i)) == 0) {
            return false;
        }
        MessageSnapshotFlow.m19157a().m19156a(MessageSnapshotTaker.m19150a(i, j, new PathConflictException(a, str, str2)));
        return true;
    }
}
