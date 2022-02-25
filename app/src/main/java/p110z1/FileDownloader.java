package p110z1;

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import com.liulishuo.filedownloader.model.FileDownloadTaskAtom;
import com.liulishuo.filedownloader.services.DownloadMgrInitialParams;
import java.io.File;
import java.util.List;
import p110z1.BaseDownloadTask;

/* renamed from: z1.afv */
/* loaded from: classes3.dex */
public class FileDownloader {

    /* renamed from: b */
    private static final Object f15609b = new Object();

    /* renamed from: d */
    private static final Object f15610d = new Object();

    /* renamed from: a */
    private Runnable f15611a;

    /* renamed from: c */
    private IQueuesHandler f15612c;

    /* renamed from: e */
    private ILostServiceConnectedHandler f15613e;

    /* renamed from: a */
    public static void m13621a(Context context) {
        FileDownloadHelper.m13225a(context.getApplicationContext());
    }

    /* renamed from: a */
    public static DownloadMgrInitialParams.C1699a m13622a(Application application) {
        FileDownloadHelper.m13225a(application.getApplicationContext());
        DownloadMgrInitialParams.C1699a aVar = new DownloadMgrInitialParams.C1699a();
        CustomComponentHolder.m13415a().m13413a(aVar);
        return aVar;
    }

    /* renamed from: b */
    public static void m13605b(Context context) {
        if (context != null) {
            m13621a(context);
            return;
        }
        throw new IllegalArgumentException("the provided context must not be null!");
    }

    /* renamed from: a */
    public static void m13620a(Context context, DownloadMgrInitialParams.C1699a aVar) {
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(FileDownloader.class, "init Downloader with params: %s %s", context, aVar);
        }
        if (context != null) {
            FileDownloadHelper.m13225a(context.getApplicationContext());
            CustomComponentHolder.m13415a().m13413a(aVar);
            return;
        }
        throw new IllegalArgumentException("the provided context must not be null!");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FileDownloader.java */
    /* renamed from: z1.afv$a */
    /* loaded from: classes3.dex */
    public static final class C3432a {

        /* renamed from: a */
        private static final FileDownloader f15615a = new FileDownloader();

        private C3432a() {
        }
    }

    /* renamed from: a */
    public static FileDownloader m13627a() {
        return C3432a.f15615a;
    }

    /* renamed from: a */
    public static void m13626a(int i) {
        FileDownloadMessageStation.f15571e = i;
    }

    /* renamed from: b */
    public static void m13607b(int i) {
        if (i > 0) {
            FileDownloadMessageStation.f15572f = i;
            return;
        }
        throw new IllegalArgumentException("sub package size must more than 0");
    }

    /* renamed from: b */
    public static void m13608b() {
        m13626a(10);
    }

    /* renamed from: c */
    public static void m13603c() {
        m13626a(-1);
    }

    /* renamed from: d */
    public static boolean m13601d() {
        return FileDownloadMessageStation.m13688b();
    }

    /* renamed from: a */
    public BaseDownloadTask m13618a(String str) {
        return new DownloadTask(str);
    }

    /* renamed from: a */
    public boolean m13610a(FileDownloadListener aflVar, boolean z) {
        if (aflVar == null) {
            FileDownloadLog.m13210d(this, "Tasks with the listener can't start, because the listener provided is null: [null, %B]", Boolean.valueOf(z));
            return false;
        } else if (z) {
            return m13588m().mo13507b(aflVar);
        } else {
            return m13588m().mo13511a(aflVar);
        }
    }

    /* renamed from: a */
    public void m13611a(FileDownloadListener aflVar) {
        FileDownloadTaskLauncher.m13640a().m13639a(aflVar);
        for (BaseDownloadTask.AbstractC3404b bVar : FileDownloadList.m13710a().m13704a(aflVar)) {
            bVar.mo13801P().mo13756i();
        }
    }

    /* renamed from: e */
    public void m13599e() {
        FileDownloadTaskLauncher.m13640a().m13637b();
        for (BaseDownloadTask.AbstractC3404b bVar : FileDownloadList.m13710a().m13697d()) {
            bVar.mo13801P().mo13756i();
        }
        if (FileDownloadServiceProxy.m13653a().mo13550e()) {
            FileDownloadServiceProxy.m13653a().mo13554c();
            return;
        }
        if (this.f15611a == null) {
            this.f15611a = new Runnable() { // from class: z1.afv.1
                @Override // java.lang.Runnable
                public void run() {
                    FileDownloadServiceProxy.m13653a().mo13554c();
                }
            };
        }
        FileDownloadServiceProxy.m13653a().mo13560a(FileDownloadHelper.m13229a(), this.f15611a);
    }

    /* renamed from: c */
    public int m13602c(int i) {
        List<BaseDownloadTask.AbstractC3404b> d = FileDownloadList.m13710a().m13696d(i);
        if (d == null || d.isEmpty()) {
            FileDownloadLog.m13210d(this, "request pause but not exist %d", Integer.valueOf(i));
            return 0;
        }
        for (BaseDownloadTask.AbstractC3404b bVar : d) {
            bVar.mo13801P().mo13756i();
        }
        return d.size();
    }

    /* renamed from: a */
    public boolean m13624a(int i, String str) {
        m13602c(i);
        if (!FileDownloadServiceProxy.m13653a().mo13547f(i)) {
            return false;
        }
        File file = new File(FileDownloadUtils.m13160e(str));
        if (file.exists()) {
            file.delete();
        }
        File file2 = new File(str);
        if (!file2.exists()) {
            return true;
        }
        file2.delete();
        return true;
    }

    /* renamed from: f */
    public void m13597f() {
        m13599e();
        FileDownloadServiceProxy.m13653a().mo13548f();
    }

    /* renamed from: d */
    public long m13600d(int i) {
        BaseDownloadTask.AbstractC3404b b = FileDownloadList.m13710a().m13702b(i);
        if (b == null) {
            return FileDownloadServiceProxy.m13653a().mo13556b(i);
        }
        return b.mo13801P().mo13742w();
    }

    /* renamed from: e */
    public long m13598e(int i) {
        BaseDownloadTask.AbstractC3404b b = FileDownloadList.m13710a().m13702b(i);
        if (b == null) {
            return FileDownloadServiceProxy.m13653a().mo13553c(i);
        }
        return b.mo13801P().mo13739z();
    }

    /* renamed from: f */
    public byte m13596f(int i) {
        return m13606b(i, null);
    }

    /* renamed from: a */
    public byte m13617a(String str, String str2) {
        return m13606b(FileDownloadUtils.m13173b(str, str2), str2);
    }

    /* renamed from: b */
    public byte m13606b(int i, String str) {
        byte b;
        BaseDownloadTask.AbstractC3404b b2 = FileDownloadList.m13710a().m13702b(i);
        if (b2 == null) {
            b = FileDownloadServiceProxy.m13653a().mo13551d(i);
        } else {
            b = b2.mo13801P().mo13815B();
        }
        if (str == null || b != 0 || !FileDownloadUtils.m13169c(FileDownloadHelper.m13229a()) || !new File(str).exists()) {
            return b;
        }
        return (byte) -3;
    }

    /* renamed from: a */
    public int m13614a(String str, FileDownloadListener aflVar) {
        return m13615a(str, FileDownloadUtils.m13174b(str), aflVar);
    }

    /* renamed from: a */
    public int m13615a(String str, String str2, FileDownloadListener aflVar) {
        return m13623a(FileDownloadUtils.m13173b(str, str2), aflVar);
    }

    /* renamed from: a */
    public int m13623a(int i, FileDownloadListener aflVar) {
        BaseDownloadTask.AbstractC3404b b = FileDownloadList.m13710a().m13702b(i);
        if (b == null) {
            return 0;
        }
        b.mo13801P().mo13781a(aflVar);
        return b.mo13801P().mo13754k();
    }

    /* renamed from: g */
    public void m13595g() {
        if (!m13591j()) {
            FileDownloadServiceProxy.m13653a().mo13561a(FileDownloadHelper.m13229a());
        }
    }

    /* renamed from: a */
    public void m13619a(Runnable runnable) {
        if (m13591j()) {
            runnable.run();
        } else {
            FileDownloadServiceProxy.m13653a().mo13560a(FileDownloadHelper.m13229a(), runnable);
        }
    }

    /* renamed from: h */
    public void m13593h() {
        if (m13591j()) {
            FileDownloadServiceProxy.m13653a().mo13555b(FileDownloadHelper.m13229a());
        }
    }

    /* renamed from: i */
    public boolean m13592i() {
        if (!m13591j() || !FileDownloadList.m13710a().m13703b() || !FileDownloadServiceProxy.m13653a().mo13552d()) {
            return false;
        }
        m13593h();
        return true;
    }

    /* renamed from: j */
    public boolean m13591j() {
        return FileDownloadServiceProxy.m13653a().mo13550e();
    }

    /* renamed from: a */
    public void m13612a(FileDownloadConnectListener affVar) {
        FileDownloadEventPool.m13726a().mo13304a(DownloadServiceConnectChangedEvent.f15799a, affVar);
    }

    /* renamed from: b */
    public void m13604b(FileDownloadConnectListener affVar) {
        FileDownloadEventPool.m13726a().mo13302b(DownloadServiceConnectChangedEvent.f15799a, affVar);
    }

    /* renamed from: a */
    public void m13625a(int i, Notification notification) {
        FileDownloadServiceProxy.m13653a().mo13562a(i, notification);
    }

    /* renamed from: a */
    public void m13609a(boolean z) {
        FileDownloadServiceProxy.m13653a().mo13557a(z);
    }

    /* renamed from: a */
    public boolean m13616a(String str, String str2, long j) {
        FileDownloadLog.m13210d(this, "If you invoked this method, please remove it directly feel free, it doesn't need any longer", new Object[0]);
        return true;
    }

    /* renamed from: a */
    public boolean m13613a(List<FileDownloadTaskAtom> list) {
        FileDownloadLog.m13210d(this, "If you invoked this method, please remove it directly feel free, it doesn't need any longer", new Object[0]);
        return true;
    }

    /* renamed from: g */
    public boolean m13594g(int i) {
        if (FileDownloadList.m13710a().m13703b()) {
            return FileDownloadServiceProxy.m13653a().mo13549e(i);
        }
        FileDownloadLog.m13210d(this, "Can't change the max network thread count, because there are actively executing tasks in FileDownloader, please try again after all actively executing tasks are completed or invoking FileDownloader#pauseAll directly.", new Object[0]);
        return false;
    }

    /* renamed from: k */
    public FileDownloadLine m13590k() {
        return new FileDownloadLine();
    }

    /* renamed from: l */
    public FileDownloadLineAsync m13589l() {
        return new FileDownloadLineAsync();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: m */
    public IQueuesHandler m13588m() {
        if (this.f15612c == null) {
            synchronized (f15609b) {
                if (this.f15612c == null) {
                    this.f15612c = new QueuesHandler();
                }
            }
        }
        return this.f15612c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: n */
    public ILostServiceConnectedHandler m13587n() {
        if (this.f15613e == null) {
            synchronized (f15610d) {
                if (this.f15613e == null) {
                    this.f15613e = new LostServiceConnectedHandler();
                    m13612a((FileDownloadConnectListener) this.f15613e);
                }
            }
        }
        return this.f15613e;
    }
}
