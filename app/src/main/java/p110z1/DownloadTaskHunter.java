package p110z1;

import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.message.MessageSnapshotTaker;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import p110z1.BaseDownloadTask;
import p110z1.IDownloadSpeed;
import p110z1.ITaskHunter;

/* renamed from: z1.afe */
/* loaded from: classes3.dex */
public class DownloadTaskHunter implements BaseDownloadTask.AbstractC3406d, ITaskHunter, ITaskHunter.AbstractC3436a, ITaskHunter.AbstractC3437b {

    /* renamed from: a */
    private IFileDownloadMessenger f15531a;

    /* renamed from: b */
    private final Object f15532b;

    /* renamed from: c */
    private final AbstractC3409a f15533c;

    /* renamed from: f */
    private final IDownloadSpeed.AbstractC3434b f15536f;

    /* renamed from: g */
    private final IDownloadSpeed.AbstractC3433a f15537g;

    /* renamed from: h */
    private long f15538h;

    /* renamed from: i */
    private long f15539i;

    /* renamed from: j */
    private int f15540j;

    /* renamed from: k */
    private boolean f15541k;

    /* renamed from: l */
    private boolean f15542l;

    /* renamed from: m */
    private String f15543m;

    /* renamed from: d */
    private volatile byte f15534d = 0;

    /* renamed from: e */
    private Throwable f15535e = null;

    /* renamed from: n */
    private boolean f15544n = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DownloadTaskHunter.java */
    /* renamed from: z1.afe$a */
    /* loaded from: classes3.dex */
    public interface AbstractC3409a {
        /* renamed from: ab */
        FileDownloadHeader mo13731ab();

        /* renamed from: ac */
        BaseDownloadTask.AbstractC3404b mo13730ac();

        /* renamed from: ad */
        ArrayList<BaseDownloadTask.AbstractC3403a> mo13729ad();

        /* renamed from: d */
        void mo13728d(String str);
    }

    @Override // p110z1.ITaskHunter.AbstractC3436a
    /* renamed from: a */
    public boolean mo13532a(MessageSnapshot messageSnapshot) {
        if (!FileDownloadStatus.m19080a(mo13543g(), messageSnapshot.mo19170b())) {
            if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13211c(this, "can't update mStatus change by keep ahead, %d, but the current mStatus is %d, %d", Byte.valueOf(this.f15534d), Byte.valueOf(mo13543g()), Integer.valueOf(m13732t()));
            }
            return false;
        }
        m13735e(messageSnapshot);
        return true;
    }

    @Override // p110z1.ITaskHunter.AbstractC3436a
    /* renamed from: b */
    public boolean mo13530b(MessageSnapshot messageSnapshot) {
        byte g = mo13543g();
        byte b = messageSnapshot.mo19170b();
        if (-2 == g && FileDownloadStatus.m19078b(b)) {
            if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13211c(this, "High concurrent cause, callback pending, but has already be paused %d", Integer.valueOf(m13732t()));
            }
            return true;
        } else if (!FileDownloadStatus.m19077b(g, b)) {
            if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13211c(this, "can't update mStatus change by keep flow, %d, but the current mStatus is %d, %d", Byte.valueOf(this.f15534d), Byte.valueOf(mo13543g()), Integer.valueOf(m13732t()));
            }
            return false;
        } else {
            m13735e(messageSnapshot);
            return true;
        }
    }

    @Override // p110z1.ITaskHunter.AbstractC3436a
    /* renamed from: c */
    public boolean mo13529c(MessageSnapshot messageSnapshot) {
        if (!FileDownloadStatus.m19079a(this.f15533c.mo13730ac().mo13801P())) {
            return false;
        }
        m13735e(messageSnapshot);
        return true;
    }

    @Override // p110z1.ITaskHunter.AbstractC3436a
    /* renamed from: d */
    public boolean mo13527d(MessageSnapshot messageSnapshot) {
        if (!this.f15533c.mo13730ac().mo13801P().mo13748q() || messageSnapshot.mo19170b() != -4 || mo13543g() != 2) {
            return false;
        }
        m13735e(messageSnapshot);
        return true;
    }

    @Override // p110z1.ITaskHunter.AbstractC3436a
    /* renamed from: d */
    public IFileDownloadMessenger mo13528d() {
        return this.f15531a;
    }

    @Override // p110z1.ITaskHunter.AbstractC3436a
    /* renamed from: a */
    public MessageSnapshot mo13531a(Throwable th) {
        this.f15534d = (byte) -1;
        this.f15535e = th;
        return MessageSnapshotTaker.m19150a(m13732t(), mo13541i(), th);
    }

    /* renamed from: e */
    private void m13735e(MessageSnapshot messageSnapshot) {
        BaseDownloadTask P = this.f15533c.mo13730ac().mo13801P();
        byte b = messageSnapshot.mo19170b();
        this.f15534d = b;
        this.f15541k = messageSnapshot.mo19159m();
        switch (b) {
            case -4:
                this.f15536f.mo13583a();
                int a = FileDownloadList.m13710a().m13709a(P.mo13754k());
                if (a + ((a > 1 || !P.mo13748q()) ? 0 : FileDownloadList.m13710a().m13709a(FileDownloadUtils.m13173b(P.mo13752m(), P.mo13746s()))) <= 1) {
                    byte d = FileDownloadServiceProxy.m13653a().mo13551d(P.mo13754k());
                    FileDownloadLog.m13210d(this, "warn, but no mListener to receive, switch to pending %d %d", Integer.valueOf(P.mo13754k()), Integer.valueOf(d));
                    if (FileDownloadStatus.m19078b(d)) {
                        this.f15534d = (byte) 1;
                        this.f15539i = messageSnapshot.mo19163i();
                        this.f15538h = messageSnapshot.mo19164h();
                        this.f15536f.mo13582a(this.f15538h);
                        this.f15531a.mo13578a(((MessageSnapshot.AbstractC1690a) messageSnapshot).mo19171a());
                        return;
                    }
                }
                FileDownloadList.m13710a().m13705a(this.f15533c.mo13730ac(), messageSnapshot);
                return;
            case -3:
                this.f15544n = messageSnapshot.mo19160l();
                this.f15538h = messageSnapshot.mo19163i();
                this.f15539i = messageSnapshot.mo19163i();
                FileDownloadList.m13710a().m13705a(this.f15533c.mo13730ac(), messageSnapshot);
                return;
            case -2:
            case 0:
            case 4:
            default:
                return;
            case -1:
                this.f15535e = messageSnapshot.mo19168d();
                this.f15538h = messageSnapshot.mo19164h();
                FileDownloadList.m13710a().m13705a(this.f15533c.mo13730ac(), messageSnapshot);
                return;
            case 1:
                this.f15538h = messageSnapshot.mo19164h();
                this.f15539i = messageSnapshot.mo19163i();
                this.f15531a.mo13578a(messageSnapshot);
                return;
            case 2:
                this.f15539i = messageSnapshot.mo19163i();
                this.f15542l = messageSnapshot.mo19166f();
                this.f15543m = messageSnapshot.mo19165g();
                String n = messageSnapshot.mo19158n();
                if (n != null) {
                    if (P.mo13747r() != null) {
                        FileDownloadLog.m13210d(this, "already has mFilename[%s], but assign mFilename[%s] again", P.mo13747r(), n);
                    }
                    this.f15533c.mo13728d(n);
                }
                this.f15536f.mo13582a(this.f15538h);
                this.f15531a.mo13573c(messageSnapshot);
                return;
            case 3:
                this.f15538h = messageSnapshot.mo19164h();
                this.f15536f.mo13580c(messageSnapshot.mo19164h());
                this.f15531a.mo13571d(messageSnapshot);
                return;
            case 5:
                this.f15538h = messageSnapshot.mo19164h();
                this.f15535e = messageSnapshot.mo19168d();
                this.f15540j = messageSnapshot.mo19167e();
                this.f15536f.mo13583a();
                this.f15531a.mo13568f(messageSnapshot);
                return;
            case 6:
                this.f15531a.mo13575b(messageSnapshot);
                return;
        }
    }

    @Override // p110z1.BaseDownloadTask.AbstractC3406d
    /* renamed from: a */
    public void mo13737a() {
        if (FileDownloadMonitor.m13676c()) {
            FileDownloadMonitor.m13677b().m13673b(this.f15533c.mo13730ac().mo13801P());
        }
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13209e(this, "filedownloader:lifecycle:start %s by %d ", toString(), Byte.valueOf(mo13543g()));
        }
    }

    @Override // p110z1.BaseDownloadTask.AbstractC3406d
    /* renamed from: o_ */
    public void mo13734o_() {
        if (FileDownloadMonitor.m13676c() && mo13543g() == 6) {
            FileDownloadMonitor.m13677b().m13672c(this.f15533c.mo13730ac().mo13801P());
        }
    }

    @Override // p110z1.BaseDownloadTask.AbstractC3406d
    /* renamed from: c */
    public void mo13736c() {
        BaseDownloadTask P = this.f15533c.mo13730ac().mo13801P();
        if (FileDownloadMonitor.m13676c()) {
            FileDownloadMonitor.m13677b().m13671d(P);
        }
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13209e(this, "filedownloader:lifecycle:over %s by %d ", toString(), Byte.valueOf(mo13543g()));
        }
        this.f15536f.mo13581b(this.f15538h);
        if (this.f15533c.mo13729ad() != null) {
            ArrayList arrayList = (ArrayList) this.f15533c.mo13729ad().clone();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((BaseDownloadTask.AbstractC3403a) arrayList.get(i)).mo13196a(P);
            }
        }
        FileDownloader.m13627a().m13587n().mo13519b(this.f15533c.mo13730ac());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DownloadTaskHunter(AbstractC3409a aVar, Object obj) {
        this.f15532b = obj;
        this.f15533c = aVar;
        DownloadSpeedMonitor afcVar = new DownloadSpeedMonitor();
        this.f15536f = afcVar;
        this.f15537g = afcVar;
        this.f15531a = new FileDownloadMessenger(aVar.mo13730ac(), this);
    }

    @Override // p110z1.ITaskHunter
    /* renamed from: e */
    public void mo13545e() {
        boolean z;
        synchronized (this.f15532b) {
            if (this.f15534d != 0) {
                FileDownloadLog.m13210d(this, "High concurrent cause, this task %d will not input to launch pool, because of the status isn't idle : %d", Integer.valueOf(m13732t()), Byte.valueOf(this.f15534d));
                return;
            }
            this.f15534d = (byte) 10;
            BaseDownloadTask.AbstractC3404b ac = this.f15533c.mo13730ac();
            BaseDownloadTask P = ac.mo13801P();
            if (FileDownloadMonitor.m13676c()) {
                FileDownloadMonitor.m13677b().m13674a(P);
            }
            if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13209e(this, "call start Url[%s], Path[%s] Listener[%s], Tag[%s]", P.mo13752m(), P.mo13749p(), P.mo13745t(), P.mo13810G());
            }
            try {
                m13733s();
                z = true;
            } catch (Throwable th) {
                FileDownloadList.m13710a().m13701b(ac);
                FileDownloadList.m13710a().m13705a(ac, mo13531a(th));
                z = false;
            }
            if (z) {
                FileDownloadTaskLauncher.m13640a().m13638a(this);
            }
            if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13209e(this, "the task[%d] has been into the launch pool.", Integer.valueOf(m13732t()));
            }
        }
    }

    @Override // p110z1.ITaskHunter
    /* renamed from: f */
    public boolean mo13544f() {
        if (FileDownloadStatus.m19081a(mo13543g())) {
            if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13211c(this, "High concurrent cause, Already is over, can't pause again, %d %d", Byte.valueOf(mo13543g()), Integer.valueOf(this.f15533c.mo13730ac().mo13801P().mo13754k()));
            }
            return false;
        }
        this.f15534d = (byte) -2;
        BaseDownloadTask.AbstractC3404b ac = this.f15533c.mo13730ac();
        BaseDownloadTask P = ac.mo13801P();
        FileDownloadTaskLauncher.m13640a().m13636b(this);
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13209e(this, "the task[%d] has been expired from the launch pool.", Integer.valueOf(m13732t()));
        }
        if (FileDownloader.m13627a().m13591j()) {
            FileDownloadServiceProxy.m13653a().mo13563a(P.mo13754k());
        } else if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "request pause the task[%d] to the download service, but the download service isn't connected yet.", Integer.valueOf(P.mo13754k()));
        }
        FileDownloadList.m13710a().m13701b(ac);
        FileDownloadList.m13710a().m13705a(ac, MessageSnapshotTaker.m19147a(P));
        FileDownloader.m13627a().m13587n().mo13519b(ac);
        return true;
    }

    @Override // p110z1.ITaskHunter
    /* renamed from: g */
    public byte mo13543g() {
        return this.f15534d;
    }

    @Override // p110z1.ITaskHunter
    /* renamed from: h */
    public void mo13542h() {
        this.f15535e = null;
        this.f15543m = null;
        this.f15542l = false;
        this.f15540j = 0;
        this.f15544n = false;
        this.f15541k = false;
        this.f15538h = 0L;
        this.f15539i = 0L;
        this.f15536f.mo13583a();
        if (FileDownloadStatus.m19081a(this.f15534d)) {
            this.f15531a.mo13570e();
            this.f15531a = new FileDownloadMessenger(this.f15533c.mo13730ac(), this);
        } else {
            this.f15531a.mo13577a(this.f15533c.mo13730ac(), this);
        }
        this.f15534d = (byte) 0;
    }

    @Override // p110z1.IDownloadSpeed.AbstractC3433a
    /* renamed from: a */
    public void mo13585a(int i) {
        this.f15537g.mo13585a(i);
    }

    @Override // p110z1.IDownloadSpeed.AbstractC3433a
    /* renamed from: b */
    public int mo13584b() {
        return this.f15537g.mo13584b();
    }

    @Override // p110z1.ITaskHunter
    /* renamed from: i */
    public long mo13541i() {
        return this.f15538h;
    }

    @Override // p110z1.ITaskHunter
    /* renamed from: j */
    public long mo13540j() {
        return this.f15539i;
    }

    @Override // p110z1.ITaskHunter
    /* renamed from: k */
    public Throwable mo13539k() {
        return this.f15535e;
    }

    @Override // p110z1.ITaskHunter
    /* renamed from: l */
    public int mo13538l() {
        return this.f15540j;
    }

    @Override // p110z1.ITaskHunter
    /* renamed from: m */
    public boolean mo13537m() {
        return this.f15544n;
    }

    @Override // p110z1.ITaskHunter
    /* renamed from: n */
    public boolean mo13536n() {
        return this.f15542l;
    }

    @Override // p110z1.ITaskHunter
    /* renamed from: o */
    public String mo13535o() {
        return this.f15543m;
    }

    @Override // p110z1.ITaskHunter
    /* renamed from: p */
    public boolean mo13534p() {
        return this.f15541k;
    }

    @Override // p110z1.ITaskHunter
    /* renamed from: q */
    public void mo13533q() {
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "free the task %d, when the status is %d", Integer.valueOf(m13732t()), Byte.valueOf(this.f15534d));
        }
        this.f15534d = (byte) 0;
    }

    /* renamed from: s */
    private void m13733s() throws IOException {
        File file;
        BaseDownloadTask P = this.f15533c.mo13730ac().mo13801P();
        if (P.mo13749p() == null) {
            P.mo13786a(FileDownloadUtils.m13174b(P.mo13752m()));
            if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13211c(this, "save Path is null to %s", P.mo13749p());
            }
        }
        if (P.mo13748q()) {
            file = new File(P.mo13749p());
        } else {
            String k = FileDownloadUtils.m13153k(P.mo13749p());
            if (k != null) {
                file = new File(k);
            } else {
                throw new InvalidParameterException(FileDownloadUtils.m13182a("the provided mPath[%s] is invalid, can't find its directory", P.mo13749p()));
            }
        }
        if (!file.exists() && !file.mkdirs() && !file.exists()) {
            throw new IOException(FileDownloadUtils.m13182a("Create parent directory failed, please make sure you have permission to create file or directory on the path: %s", file.getAbsolutePath()));
        }
    }

    /* renamed from: t */
    private int m13732t() {
        return this.f15533c.mo13730ac().mo13801P().mo13754k();
    }

    @Override // p110z1.ITaskHunter.AbstractC3437b
    /* renamed from: r */
    public void mo13525r() {
        if (this.f15534d != 10) {
            FileDownloadLog.m13210d(this, "High concurrent cause, this task %d will not start, because the of status isn't toLaunchPool: %d", Integer.valueOf(m13732t()), Byte.valueOf(this.f15534d));
            return;
        }
        BaseDownloadTask.AbstractC3404b ac = this.f15533c.mo13730ac();
        BaseDownloadTask P = ac.mo13801P();
        ILostServiceConnectedHandler n = FileDownloader.m13627a().m13587n();
        try {
            if (!n.mo13518c(ac)) {
                synchronized (this.f15532b) {
                    if (this.f15534d != 10) {
                        FileDownloadLog.m13210d(this, "High concurrent cause, this task %d will not start, the status can't assign to toFileDownloadService, because the status isn't toLaunchPool: %d", Integer.valueOf(m13732t()), Byte.valueOf(this.f15534d));
                        return;
                    }
                    this.f15534d = FileDownloadStatus.f10400b;
                    FileDownloadList.m13710a().m13701b(ac);
                    if (!FileDownloadHelper.m13226a(P.mo13754k(), P.mo13746s(), P.mo13814C(), true)) {
                        boolean a = FileDownloadServiceProxy.m13653a().mo13558a(P.mo13752m(), P.mo13749p(), P.mo13748q(), P.mo13751n(), P.mo13750o(), P.mo13806K(), P.mo13814C(), this.f15533c.mo13731ab(), P.mo13802O());
                        if (this.f15534d == -2) {
                            FileDownloadLog.m13210d(this, "High concurrent cause, this task %d will be paused,because of the status is paused, so the pause action must be applied", Integer.valueOf(m13732t()));
                            if (a) {
                                FileDownloadServiceProxy.m13653a().mo13563a(m13732t());
                            }
                        } else if (a) {
                            n.mo13519b(ac);
                        } else if (!n.mo13518c(ac)) {
                            MessageSnapshot a2 = mo13531a(new RuntimeException("Occur Unknown Error, when request to start maybe some problem in binder, maybe the process was killed in unexpected."));
                            if (FileDownloadList.m13710a().m13706a(ac)) {
                                n.mo13519b(ac);
                                FileDownloadList.m13710a().m13701b(ac);
                            }
                            FileDownloadList.m13710a().m13705a(ac, a2);
                        }
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
            FileDownloadList.m13710a().m13705a(ac, mo13531a(th));
        }
    }

    @Override // p110z1.ITaskHunter.AbstractC3437b
    /* renamed from: a */
    public boolean mo13526a(FileDownloadListener aflVar) {
        return this.f15533c.mo13730ac().mo13801P().mo13745t() == aflVar;
    }
}
