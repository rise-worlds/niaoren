package p110z1;

import android.os.Process;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;
import p110z1.ConnectTask;
import p110z1.ConnectionProfile;
import p110z1.DownloadRunnable;

/* renamed from: z1.agt */
/* loaded from: classes3.dex */
public class DownloadLaunchRunnable implements Runnable, ProcessCallback {

    /* renamed from: k */
    private static final int f15695k = 416;

    /* renamed from: l */
    private static final int f15696l = -1;

    /* renamed from: r */
    private static final ThreadPoolExecutor f15697r = FileDownloadExecutors.m13230a("ConnectionBlock");

    /* renamed from: A */
    private long f15698A;

    /* renamed from: B */
    private long f15699B;

    /* renamed from: C */
    private long f15700C;

    /* renamed from: D */
    private long f15701D;

    /* renamed from: a */
    int f15702a;

    /* renamed from: b */
    private final DownloadStatusCallback f15703b;

    /* renamed from: c */
    private final int f15704c;

    /* renamed from: d */
    private final FileDownloadModel f15705d;

    /* renamed from: e */
    private final FileDownloadHeader f15706e;

    /* renamed from: f */
    private final boolean f15707f;

    /* renamed from: g */
    private final boolean f15708g;

    /* renamed from: h */
    private final FileDownloadDatabase f15709h;

    /* renamed from: i */
    private final IThreadPoolMonitor f15710i;

    /* renamed from: j */
    private boolean f15711j;

    /* renamed from: m */
    private boolean f15712m;

    /* renamed from: n */
    private final boolean f15713n;

    /* renamed from: o */
    private final ArrayList<DownloadRunnable> f15714o;

    /* renamed from: p */
    private DownloadRunnable f15715p;

    /* renamed from: q */
    private boolean f15716q;

    /* renamed from: s */
    private boolean f15717s;

    /* renamed from: t */
    private boolean f15718t;

    /* renamed from: u */
    private boolean f15719u;

    /* renamed from: v */
    private final AtomicBoolean f15720v;

    /* renamed from: w */
    private volatile boolean f15721w;

    /* renamed from: x */
    private volatile boolean f15722x;

    /* renamed from: y */
    private volatile Exception f15723y;

    /* renamed from: z */
    private String f15724z;

    /* renamed from: i */
    private int m13384i() {
        return 5;
    }

    private DownloadLaunchRunnable(FileDownloadModel fileDownloadModel, FileDownloadHeader fileDownloadHeader, IThreadPoolMonitor agcVar, int i, int i2, boolean z, boolean z2, int i3) {
        this.f15704c = 5;
        this.f15712m = false;
        this.f15714o = new ArrayList<>(5);
        this.f15698A = 0L;
        this.f15699B = 0L;
        this.f15700C = 0L;
        this.f15701D = 0L;
        this.f15720v = new AtomicBoolean(true);
        this.f15721w = false;
        this.f15711j = false;
        this.f15705d = fileDownloadModel;
        this.f15706e = fileDownloadHeader;
        this.f15707f = z;
        this.f15708g = z2;
        this.f15709h = CustomComponentHolder.m13415a().m13408c();
        this.f15713n = CustomComponentHolder.m13415a().m13406e();
        this.f15710i = agcVar;
        this.f15702a = i3;
        this.f15703b = new DownloadStatusCallback(fileDownloadModel, i3, i, i2);
    }

    private DownloadLaunchRunnable(DownloadStatusCallback agvVar, FileDownloadModel fileDownloadModel, FileDownloadHeader fileDownloadHeader, IThreadPoolMonitor agcVar, int i, int i2, boolean z, boolean z2, int i3) {
        this.f15704c = 5;
        this.f15712m = false;
        this.f15714o = new ArrayList<>(5);
        this.f15698A = 0L;
        this.f15699B = 0L;
        this.f15700C = 0L;
        this.f15701D = 0L;
        this.f15720v = new AtomicBoolean(true);
        this.f15721w = false;
        this.f15711j = false;
        this.f15705d = fileDownloadModel;
        this.f15706e = fileDownloadHeader;
        this.f15707f = z;
        this.f15708g = z2;
        this.f15709h = CustomComponentHolder.m13415a().m13408c();
        this.f15713n = CustomComponentHolder.m13415a().m13406e();
        this.f15710i = agcVar;
        this.f15702a = i3;
        this.f15703b = agvVar;
    }

    /* renamed from: a */
    static DownloadLaunchRunnable m13393a(DownloadStatusCallback agvVar, FileDownloadModel fileDownloadModel, FileDownloadHeader fileDownloadHeader, IThreadPoolMonitor agcVar, int i, int i2, boolean z, boolean z2, int i3) {
        return new DownloadLaunchRunnable(agvVar, fileDownloadModel, fileDownloadHeader, agcVar, i, i2, z, z2, i3);
    }

    /* renamed from: a */
    public void m13400a() {
        this.f15721w = true;
        DownloadRunnable aguVar = this.f15715p;
        if (aguVar != null) {
            aguVar.m13372a();
        }
        Iterator it = ((ArrayList) this.f15714o.clone()).iterator();
        while (it.hasNext()) {
            DownloadRunnable aguVar2 = (DownloadRunnable) it.next();
            if (aguVar2 != null) {
                aguVar2.m13372a();
            }
        }
    }

    /* renamed from: b */
    public void m13392b() {
        m13396a(this.f15709h.mo13448c(this.f15705d.m19135a()));
        this.f15703b.m13345c();
    }

    @Override // java.lang.Runnable
    public void run() {
        List<ConnectionModel> c;
        try {
            Process.setThreadPriority(10);
            if (this.f15705d.m19119f() != 1) {
                if (this.f15705d.m19119f() != -2) {
                    mo13312b(new RuntimeException(FileDownloadUtils.m13182a("Task[%d] can't start the download runnable, because its status is %d not %d", Integer.valueOf(this.f15705d.m19135a()), Byte.valueOf(this.f15705d.m19119f()), (byte) 1)));
                } else if (FileDownloadLog.f15845a) {
                    FileDownloadLog.m13211c(this, "High concurrent cause, start runnable but already paused %d", Integer.valueOf(this.f15705d.m19135a()));
                }
                this.f15703b.m13349b();
                if (this.f15721w) {
                    this.f15703b.m13341f();
                } else if (this.f15722x) {
                    this.f15703b.m13352a(this.f15723y);
                } else {
                    try {
                        this.f15703b.m13340g();
                    } catch (IOException e) {
                        this.f15703b.m13352a(e);
                    }
                }
                this.f15720v.set(false);
                return;
            }
            if (!this.f15721w) {
                this.f15703b.m13343d();
            }
            while (!this.f15721w) {
                try {
                    m13383j();
                    m13386g();
                    m13382k();
                    c = this.f15709h.mo13448c(this.f15705d.m19135a());
                    m13396a(c);
                } catch (IOException | IllegalAccessException | IllegalArgumentException | InterruptedException | FileDownloadGiveUpRetryException | FileDownloadSecurityException e2) {
                    if (mo13314a(e2)) {
                        mo13310c(e2);
                    } else {
                        mo13312b(e2);
                    }
                } catch (C3460b unused) {
                    this.f15703b.m13349b();
                    if (this.f15721w) {
                        this.f15703b.m13341f();
                    } else if (this.f15722x) {
                        this.f15703b.m13352a(this.f15723y);
                    } else {
                        try {
                            this.f15703b.m13340g();
                        } catch (IOException e3) {
                            this.f15703b.m13352a(e3);
                        }
                    }
                    this.f15720v.set(false);
                    return;
                } catch (C3461c unused2) {
                    this.f15705d.m19134a((byte) 5);
                }
                if (this.f15721w) {
                    this.f15705d.m19134a((byte) -2);
                    this.f15703b.m13349b();
                    if (this.f15721w) {
                        this.f15703b.m13341f();
                    } else if (this.f15722x) {
                        this.f15703b.m13352a(this.f15723y);
                    } else {
                        try {
                            this.f15703b.m13340g();
                        } catch (IOException e4) {
                            this.f15703b.m13352a(e4);
                        }
                    }
                    this.f15720v.set(false);
                    return;
                }
                long h = this.f15705d.m19117h();
                m13397a(h, this.f15705d.m19120e());
                int b = m13391b(h);
                if (b <= 0) {
                    throw new IllegalAccessException(FileDownloadUtils.m13182a("invalid connection count %d, the connection count must be larger than 0", Integer.valueOf(b)));
                } else if (h == 0) {
                    this.f15703b.m13349b();
                    if (this.f15721w) {
                        this.f15703b.m13341f();
                    } else if (this.f15722x) {
                        this.f15703b.m13352a(this.f15723y);
                    } else {
                        try {
                            this.f15703b.m13340g();
                        } catch (IOException e5) {
                            this.f15703b.m13352a(e5);
                        }
                    }
                    this.f15720v.set(false);
                    return;
                } else if (this.f15721w) {
                    this.f15705d.m19134a((byte) -2);
                    this.f15703b.m13349b();
                    if (this.f15721w) {
                        this.f15703b.m13341f();
                    } else if (this.f15722x) {
                        this.f15703b.m13352a(this.f15723y);
                    } else {
                        try {
                            this.f15703b.m13340g();
                        } catch (IOException e6) {
                            this.f15703b.m13352a(e6);
                        }
                    }
                    this.f15720v.set(false);
                    return;
                } else {
                    this.f15716q = b == 1;
                    if (this.f15716q) {
                        m13390c(h);
                    } else {
                        this.f15703b.m13342e();
                        if (this.f15717s) {
                            m13399a(b, c);
                        } else {
                            m13398a(h, b);
                        }
                    }
                    this.f15703b.m13349b();
                    if (this.f15721w) {
                        this.f15703b.m13341f();
                    } else if (this.f15722x) {
                        this.f15703b.m13352a(this.f15723y);
                    } else {
                        try {
                            this.f15703b.m13340g();
                        } catch (IOException e7) {
                            this.f15703b.m13352a(e7);
                        }
                    }
                    this.f15720v.set(false);
                    return;
                }
            }
            if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13211c(this, "High concurrent cause, start runnable but already paused %d", Integer.valueOf(this.f15705d.m19135a()));
            }
            this.f15703b.m13349b();
            if (this.f15721w) {
                this.f15703b.m13341f();
            } else if (this.f15722x) {
                this.f15703b.m13352a(this.f15723y);
            } else {
                try {
                    this.f15703b.m13340g();
                } catch (IOException e8) {
                    this.f15703b.m13352a(e8);
                }
            }
            this.f15720v.set(false);
        } catch (Throwable th) {
            this.f15703b.m13349b();
            if (this.f15721w) {
                this.f15703b.m13341f();
            } else if (this.f15722x) {
                this.f15703b.m13352a(this.f15723y);
            } else {
                try {
                    this.f15703b.m13340g();
                } catch (IOException e9) {
                    this.f15703b.m13352a(e9);
                }
            }
            this.f15720v.set(false);
            throw th;
        }
    }

    /* renamed from: b */
    private int m13391b(long j) {
        if (!m13385h()) {
            return 1;
        }
        if (this.f15717s) {
            return this.f15705d.m19111n();
        }
        return CustomComponentHolder.m13415a().m13414a(this.f15705d.m19135a(), this.f15705d.m19129b(), this.f15705d.m19125c(), j);
    }

    /* renamed from: g */
    private void m13386g() throws IOException, C3461c, IllegalAccessException, FileDownloadSecurityException {
        ConnectionProfile agrVar;
        FileDownloadConnection agiVar = null;
        try {
            if (this.f15712m) {
                agrVar = ConnectionProfile.C3456a.m13416b();
            } else {
                agrVar = ConnectionProfile.C3456a.m13420a();
            }
            ConnectTask a = new ConnectTask.C3453a().m13426a(this.f15705d.m19135a()).m13424a(this.f15705d.m19129b()).m13422b(this.f15705d.m19115j()).m13425a(this.f15706e).m13423a(agrVar).m13427a();
            agiVar = a.m13437a();
            m13394a(a.m13429d(), a, agiVar);
        } finally {
            if (agiVar != null) {
                agiVar.mo13490f();
            }
        }
    }

    /* renamed from: h */
    private boolean m13385h() {
        return (!this.f15717s || this.f15705d.m19111n() > 1) && this.f15718t && this.f15713n && !this.f15719u;
    }

    /* renamed from: a */
    void m13396a(List<ConnectionModel> list) {
        long j;
        int n = this.f15705d.m19111n();
        String e = this.f15705d.m19120e();
        String d = this.f15705d.m19122d();
        boolean z = false;
        boolean z2 = n > 1;
        if (this.f15712m) {
            j = 0;
        } else if (z2 && !this.f15713n) {
            j = 0;
        } else if (!FileDownloadUtils.m13193a(this.f15705d.m19135a(), this.f15705d)) {
            j = 0;
        } else if (!this.f15713n) {
            j = new File(e).length();
        } else if (z2) {
            j = n != list.size() ? 0L : ConnectionModel.m19090a(list);
        } else {
            j = this.f15705d.m19118g();
        }
        this.f15705d.m19132a(j);
        if (j > 0) {
            z = true;
        }
        this.f15717s = z;
        if (!this.f15717s) {
            this.f15709h.mo13446d(this.f15705d.m19135a());
            FileDownloadUtils.m13167c(d, e);
        }
    }

    /* renamed from: a */
    private void m13394a(Map<String, List<String>> map, ConnectTask agqVar, FileDownloadConnection agiVar) throws IOException, C3461c, IllegalArgumentException, FileDownloadSecurityException {
        boolean z;
        int a = this.f15705d.m19135a();
        int e = agiVar.mo13491e();
        this.f15718t = FileDownloadUtils.m13176b(e, agiVar);
        boolean z2 = e == 200 || e == 201 || e == 0;
        String j = this.f15705d.m19115j();
        String a2 = FileDownloadUtils.m13190a(a, agiVar);
        if (e == 412) {
            z = true;
        } else if (j != 0 && !j.equals(a2) && (z2 || this.f15718t)) {
            z = true;
        } else if (e != 201 || !agqVar.m13433b()) {
            if (e == 416) {
                if (this.f15705d.m19118g() > 0) {
                    z = true;
                } else if (!this.f15712m) {
                    this.f15712m = true;
                    z = true;
                }
            }
            z = false;
        } else {
            z = true;
        }
        if (z) {
            if (this.f15717s) {
                FileDownloadLog.m13210d(this, "there is precondition failed on this request[%d] with old etag[%s]、new etag[%s]、response code is %d", Integer.valueOf(a), j, a2, Integer.valueOf(e));
            }
            this.f15709h.mo13446d(this.f15705d.m19135a());
            FileDownloadUtils.m13167c(this.f15705d.m19122d(), this.f15705d.m19120e());
            this.f15717s = false;
            if (j != null && j.equals(a2)) {
                FileDownloadLog.m13210d(this, "the old etag[%s] is the same to the new etag[%s], but the response status code is %d not Partial(206), so wo have to start this task from very beginning for task[%d]!", j, a2, Integer.valueOf(e), Integer.valueOf(a));
                a2 = null;
            }
            this.f15705d.m19132a(0L);
            this.f15705d.m19124c(0L);
            this.f15705d.m19126b(a2);
            this.f15705d.m19110o();
            this.f15709h.mo13462a(a, this.f15705d.m19115j(), this.f15705d.m19118g(), this.f15705d.m19117h(), this.f15705d.m19111n());
            throw new C3461c();
        }
        this.f15724z = agqVar.m13431c();
        if (this.f15718t || z2) {
            long a3 = FileDownloadUtils.m13181a(agiVar);
            String a4 = this.f15705d.m19113l() ? FileDownloadUtils.m13180a(agiVar, this.f15705d.m19129b()) : null;
            this.f15719u = a3 == -1;
            this.f15703b.m13350a(this.f15717s && this.f15718t, a3, a2, a4);
            return;
        }
        throw new FileDownloadHttpException(e, map, agiVar.mo13493c());
    }

    /* renamed from: c */
    private void m13390c(long j) throws IOException, IllegalAccessException {
        ConnectionProfile agrVar;
        if (!this.f15718t) {
            this.f15705d.m19132a(0L);
            agrVar = ConnectionProfile.C3456a.m13419a(j);
        } else {
            agrVar = ConnectionProfile.C3456a.m13418a(this.f15705d.m19118g(), this.f15705d.m19118g(), j - this.f15705d.m19118g());
        }
        this.f15715p = new DownloadRunnable.C3463a().m13368a(this.f15705d.m19135a()).m13366a((Integer) (-1)).m13362a(this).m13365a(this.f15705d.m19129b()).m13360b(this.f15705d.m19115j()).m13367a(this.f15706e).m13361a(this.f15708g).m13363a(agrVar).m13359c(this.f15705d.m19120e()).m13369a();
        this.f15705d.m19128b(1);
        this.f15709h.mo13467a(this.f15705d.m19135a(), 1);
        if (this.f15721w) {
            this.f15705d.m19134a((byte) -2);
            this.f15715p.m13372a();
            return;
        }
        this.f15715p.run();
    }

    /* renamed from: a */
    private void m13399a(int i, List<ConnectionModel> list) throws InterruptedException {
        if (i <= 1 || list.size() != i) {
            throw new IllegalArgumentException();
        }
        m13395a(list, this.f15705d.m19117h());
    }

    /* renamed from: a */
    private void m13398a(long j, int i) throws InterruptedException {
        long j2 = j / i;
        int a = this.f15705d.m19135a();
        ArrayList arrayList = new ArrayList();
        long j3 = 0;
        int i2 = 0;
        while (i2 < i) {
            long j4 = i2 == i + (-1) ? -1L : (j3 + j2) - 1;
            ConnectionModel aVar = new ConnectionModel();
            aVar.m19092a(a);
            aVar.m19088b(i2);
            aVar.m19091a(j3);
            aVar.m19087b(j3);
            aVar.m19085c(j4);
            arrayList.add(aVar);
            this.f15709h.mo13456a(aVar);
            j3 += j2;
            i2++;
        }
        this.f15705d.m19128b(i);
        this.f15709h.mo13467a(a, i);
        m13395a(arrayList, j);
    }

    /* renamed from: a */
    private void m13395a(List<ConnectionModel> list, long j) throws InterruptedException {
        long j2;
        int a = this.f15705d.m19135a();
        String str = this.f15705d.m19115j();
        String str2 = this.f15724z;
        if (str2 == null) {
            str2 = this.f15705d.m19129b();
        }
        String e = this.f15705d.m19120e();
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "fetch data with multiple connection(count: [%d]) for task[%d] totalLength[%d]", Integer.valueOf(list.size()), Integer.valueOf(a), Long.valueOf(j));
        }
        boolean z = this.f15717s;
        long j3 = 0;
        long j4 = 0;
        for (ConnectionModel aVar : list) {
            if (aVar.m19083e() == -1) {
                j2 = j - aVar.m19084d();
            } else {
                j2 = (aVar.m19083e() - aVar.m19084d()) + 1;
            }
            j4 += aVar.m19084d() - aVar.m19086c();
            if (j2 != j3) {
                DownloadRunnable.C3463a aVar2 = new DownloadRunnable.C3463a();
                ConnectionProfile a2 = ConnectionProfile.C3456a.m13417a(aVar.m19086c(), aVar.m19084d(), aVar.m19083e(), j2);
                DownloadRunnable.C3463a a3 = aVar2.m13368a(a).m13366a(Integer.valueOf(aVar.m19089b())).m13362a(this).m13365a(str2);
                if (!z) {
                    str = null;
                }
                DownloadRunnable a4 = a3.m13360b(str).m13367a(this.f15706e).m13361a(this.f15708g).m13363a(a2).m13359c(e).m13369a();
                if (FileDownloadLog.f15845a) {
                    FileDownloadLog.m13211c(this, "enable multiple connection: %s", aVar);
                }
                if (a4 != null) {
                    this.f15714o.add(a4);
                } else {
                    throw new IllegalArgumentException("the download runnable must not be null!");
                }
            } else if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13211c(this, "pass connection[%d-%d], because it has been completed", Integer.valueOf(aVar.m19093a()), Integer.valueOf(aVar.m19089b()));
            }
            j3 = 0;
        }
        if (j4 != this.f15705d.m19118g()) {
            FileDownloadLog.m13210d(this, "correct the sofar[%d] from connection table[%d]", Long.valueOf(this.f15705d.m19118g()), Long.valueOf(j4));
            this.f15705d.m19132a(j4);
        }
        ArrayList arrayList = new ArrayList(this.f15714o.size());
        Iterator<DownloadRunnable> it = this.f15714o.iterator();
        while (it.hasNext()) {
            DownloadRunnable next = it.next();
            if (this.f15721w) {
                next.m13372a();
            } else {
                arrayList.add(Executors.callable(next));
            }
        }
        if (this.f15721w) {
            this.f15705d.m19134a((byte) -2);
            return;
        }
        List<Future> invokeAll = f15697r.invokeAll(arrayList);
        if (FileDownloadLog.f15845a) {
            for (Future future : invokeAll) {
                FileDownloadLog.m13211c(this, "finish sub-task for [%d] %B %B", Integer.valueOf(a), Boolean.valueOf(future.isDone()), Boolean.valueOf(future.isCancelled()));
            }
        }
    }

    /* renamed from: a */
    private void m13397a(long j, String str) throws IOException, IllegalAccessException {
        FileDownloadOutputStream ahpVar = null;
        if (j != -1) {
            try {
                ahpVar = FileDownloadUtils.m13148p(this.f15705d.m19120e());
                long length = new File(str).length();
                long j2 = j - length;
                long h = FileDownloadUtils.m13156h(str);
                if (h < j2) {
                    throw new FileDownloadOutOfSpaceException(h, j2, length);
                } else if (!FileDownloadProperties.m13208a().f15862f) {
                    ahpVar.mo13247b(j);
                }
            } finally {
                if (0 != 0) {
                    ahpVar.mo13248b();
                }
            }
        }
    }

    @Override // p110z1.ProcessCallback
    /* renamed from: a */
    public void mo13315a(long j) {
        if (!this.f15721w) {
            this.f15703b.m13356a(j);
        }
    }

    @Override // p110z1.ProcessCallback
    /* renamed from: a */
    public void mo13313a(DownloadRunnable aguVar, long j, long j2) {
        if (!this.f15721w) {
            int i = aguVar.f15733a;
            if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13211c(this, "the connection has been completed(%d): [%d, %d)  %d", Integer.valueOf(i), Long.valueOf(j), Long.valueOf(j2), Long.valueOf(this.f15705d.m19117h()));
            }
            if (!this.f15716q) {
                synchronized (this.f15714o) {
                    this.f15714o.remove(aguVar);
                }
            } else if (j != 0 && j2 != this.f15705d.m19117h()) {
                FileDownloadLog.m13214a(this, "the single task not completed corrected(%d, %d != %d) for task(%d)", Long.valueOf(j), Long.valueOf(j2), Long.valueOf(this.f15705d.m19117h()), Integer.valueOf(this.f15705d.m19135a()));
            }
        } else if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "the task[%d] has already been paused, so pass the completed callback", Integer.valueOf(this.f15705d.m19135a()));
        }
    }

    @Override // p110z1.ProcessCallback
    /* renamed from: a */
    public boolean mo13314a(Exception exc) {
        if (exc instanceof FileDownloadHttpException) {
            int code = ((FileDownloadHttpException) exc).getCode();
            if (this.f15716q && code == 416 && !this.f15711j) {
                FileDownloadUtils.m13167c(this.f15705d.m19122d(), this.f15705d.m19120e());
                this.f15711j = true;
                return true;
            }
        }
        return this.f15702a > 0 && !(exc instanceof FileDownloadGiveUpRetryException);
    }

    @Override // p110z1.ProcessCallback
    /* renamed from: b */
    public void mo13312b(Exception exc) {
        this.f15722x = true;
        this.f15723y = exc;
        if (!this.f15721w) {
            Iterator it = ((ArrayList) this.f15714o.clone()).iterator();
            while (it.hasNext()) {
                DownloadRunnable aguVar = (DownloadRunnable) it.next();
                if (aguVar != null) {
                    aguVar.m13371b();
                }
            }
        } else if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "the task[%d] has already been paused, so pass the error callback", Integer.valueOf(this.f15705d.m19135a()));
        }
    }

    @Override // p110z1.ProcessCallback
    /* renamed from: c */
    public void mo13310c(Exception exc) {
        if (!this.f15721w) {
            int i = this.f15702a;
            this.f15702a = i - 1;
            if (i < 0) {
                FileDownloadLog.m13214a(this, "valid retry times is less than 0(%d) for download task(%d)", Integer.valueOf(this.f15702a), Integer.valueOf(this.f15705d.m19135a()));
            }
            this.f15703b.m13351a(exc, this.f15702a);
        } else if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "the task[%d] has already been paused, so pass the retry callback", Integer.valueOf(this.f15705d.m19135a()));
        }
    }

    @Override // p110z1.ProcessCallback
    /* renamed from: c */
    public void mo13311c() {
        this.f15709h.mo13465a(this.f15705d.m19135a(), this.f15705d.m19118g());
    }

    /* renamed from: j */
    private void m13383j() throws FileDownloadGiveUpRetryException {
        if (this.f15708g && !FileDownloadUtils.m13151m("android.permission.ACCESS_NETWORK_STATE")) {
            throw new FileDownloadGiveUpRetryException(FileDownloadUtils.m13182a("Task[%d] can't start the download runnable, because this task require wifi, but user application nor current process has %s, so we can't check whether the network type connection.", Integer.valueOf(this.f15705d.m19135a()), "android.permission.ACCESS_NETWORK_STATE"));
        } else if (this.f15708g && FileDownloadUtils.m13161e()) {
            throw new FileDownloadNetworkPolicyException();
        }
    }

    /* renamed from: k */
    private void m13382k() throws C3461c, C3460b {
        int a = this.f15705d.m19135a();
        if (this.f15705d.m19113l()) {
            String d = this.f15705d.m19122d();
            int b = FileDownloadUtils.m13173b(this.f15705d.m19129b(), d);
            if (!FileDownloadHelper.m13226a(a, d, this.f15707f, false)) {
                FileDownloadModel b2 = this.f15709h.mo13453b(b);
                if (b2 != null) {
                    if (!FileDownloadHelper.m13227a(a, b2, this.f15710i, false)) {
                        List<ConnectionModel> c = this.f15709h.mo13448c(b);
                        this.f15709h.mo13445e(b);
                        this.f15709h.mo13446d(b);
                        FileDownloadUtils.m13146r(this.f15705d.m19122d());
                        if (FileDownloadUtils.m13193a(b, b2)) {
                            this.f15705d.m19132a(b2.m19118g());
                            this.f15705d.m19124c(b2.m19117h());
                            this.f15705d.m19126b(b2.m19115j());
                            this.f15705d.m19128b(b2.m19111n());
                            this.f15709h.mo13450b(this.f15705d);
                            if (c != null) {
                                for (ConnectionModel aVar : c) {
                                    aVar.m19092a(a);
                                    this.f15709h.mo13456a(aVar);
                                }
                            }
                            throw new C3461c();
                        }
                    } else {
                        this.f15709h.mo13445e(a);
                        this.f15709h.mo13446d(a);
                        throw new C3460b();
                    }
                }
                if (FileDownloadHelper.m13228a(a, this.f15705d.m19118g(), this.f15705d.m19120e(), d, this.f15710i)) {
                    this.f15709h.mo13445e(a);
                    this.f15709h.mo13446d(a);
                    throw new C3460b();
                }
                return;
            }
            this.f15709h.mo13445e(a);
            this.f15709h.mo13446d(a);
            throw new C3460b();
        }
    }

    /* renamed from: d */
    public int m13389d() {
        return this.f15705d.m19135a();
    }

    /* renamed from: e */
    public boolean m13388e() {
        return this.f15720v.get() || this.f15703b.m13358a();
    }

    /* renamed from: f */
    public String m13387f() {
        return this.f15705d.m19120e();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DownloadLaunchRunnable.java */
    /* renamed from: z1.agt$c */
    /* loaded from: classes3.dex */
    public class C3461c extends Throwable {
        C3461c() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DownloadLaunchRunnable.java */
    /* renamed from: z1.agt$b */
    /* loaded from: classes3.dex */
    public class C3460b extends Throwable {
        C3460b() {
        }
    }

    /* compiled from: DownloadLaunchRunnable.java */
    /* renamed from: z1.agt$a */
    /* loaded from: classes3.dex */
    public static class C3459a {

        /* renamed from: a */
        private FileDownloadModel f15725a;

        /* renamed from: b */
        private FileDownloadHeader f15726b;

        /* renamed from: c */
        private IThreadPoolMonitor f15727c;

        /* renamed from: d */
        private Integer f15728d;

        /* renamed from: e */
        private Integer f15729e;

        /* renamed from: f */
        private Boolean f15730f;

        /* renamed from: g */
        private Boolean f15731g;

        /* renamed from: h */
        private Integer f15732h;

        /* renamed from: a */
        public C3459a m13379a(FileDownloadModel fileDownloadModel) {
            this.f15725a = fileDownloadModel;
            return this;
        }

        /* renamed from: a */
        public C3459a m13380a(FileDownloadHeader fileDownloadHeader) {
            this.f15726b = fileDownloadHeader;
            return this;
        }

        /* renamed from: a */
        public C3459a m13376a(IThreadPoolMonitor agcVar) {
            this.f15727c = agcVar;
            return this;
        }

        /* renamed from: a */
        public C3459a m13377a(Integer num) {
            this.f15728d = num;
            return this;
        }

        /* renamed from: b */
        public C3459a m13374b(Integer num) {
            this.f15729e = num;
            return this;
        }

        /* renamed from: a */
        public C3459a m13378a(Boolean bool) {
            this.f15730f = bool;
            return this;
        }

        /* renamed from: b */
        public C3459a m13375b(Boolean bool) {
            this.f15731g = bool;
            return this;
        }

        /* renamed from: c */
        public C3459a m13373c(Integer num) {
            this.f15732h = num;
            return this;
        }

        /* renamed from: a */
        public DownloadLaunchRunnable m13381a() {
            IThreadPoolMonitor agcVar;
            Integer num;
            FileDownloadModel fileDownloadModel = this.f15725a;
            if (fileDownloadModel != null && (agcVar = this.f15727c) != null && (num = this.f15728d) != null && this.f15729e != null && this.f15730f != null && this.f15731g != null && this.f15732h != null) {
                return new DownloadLaunchRunnable(fileDownloadModel, this.f15726b, agcVar, num.intValue(), this.f15729e.intValue(), this.f15730f.booleanValue(), this.f15731g.booleanValue(), this.f15732h.intValue());
            }
            throw new IllegalArgumentException();
        }
    }
}
