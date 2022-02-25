package p110z1;

import android.text.TextUtils;
import android.util.SparseArray;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import java.io.File;
import java.util.ArrayList;
import p110z1.BaseDownloadTask;
import p110z1.DownloadTaskHunter;
import p110z1.ITaskHunter;

/* renamed from: z1.afd */
/* loaded from: classes3.dex */
public class DownloadTask implements BaseDownloadTask, BaseDownloadTask.AbstractC3404b, DownloadTaskHunter.AbstractC3409a {

    /* renamed from: b */
    public static final int f15506b = 10;

    /* renamed from: d */
    private final ITaskHunter f15508d;

    /* renamed from: e */
    private final ITaskHunter.AbstractC3436a f15509e;

    /* renamed from: f */
    private int f15510f;

    /* renamed from: g */
    private ArrayList<BaseDownloadTask.AbstractC3403a> f15511g;

    /* renamed from: h */
    private final String f15512h;

    /* renamed from: i */
    private String f15513i;

    /* renamed from: j */
    private String f15514j;

    /* renamed from: k */
    private boolean f15515k;

    /* renamed from: l */
    private FileDownloadHeader f15516l;

    /* renamed from: m */
    private FileDownloadListener f15517m;

    /* renamed from: n */
    private SparseArray<Object> f15518n;

    /* renamed from: o */
    private Object f15519o;

    /* renamed from: p */
    private int f15520p = 0;

    /* renamed from: q */
    private boolean f15521q = false;

    /* renamed from: r */
    private boolean f15522r = false;

    /* renamed from: s */
    private int f15523s = 100;

    /* renamed from: t */
    private int f15524t = 10;

    /* renamed from: u */
    private boolean f15525u = false;

    /* renamed from: c */
    volatile int f15507c = 0;

    /* renamed from: v */
    private boolean f15526v = false;

    /* renamed from: x */
    private final Object f15528x = new Object();

    /* renamed from: y */
    private volatile boolean f15529y = false;

    /* renamed from: w */
    private final Object f15527w = new Object();

    @Override // p110z1.BaseDownloadTask.AbstractC3404b
    /* renamed from: P */
    public BaseDownloadTask mo13801P() {
        return this;
    }

    @Override // p110z1.DownloadTaskHunter.AbstractC3409a
    /* renamed from: ac */
    public BaseDownloadTask.AbstractC3404b mo13730ac() {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DownloadTask(String str) {
        this.f15512h = str;
        DownloadTaskHunter afeVar = new DownloadTaskHunter(this, this.f15527w);
        this.f15508d = afeVar;
        this.f15509e = afeVar;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: a */
    public BaseDownloadTask mo13789a(int i) {
        this.f15508d.mo13585a(i);
        return this;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: a */
    public BaseDownloadTask mo13786a(String str) {
        return mo13784a(str, false);
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: a */
    public BaseDownloadTask mo13784a(String str, boolean z) {
        this.f15513i = str;
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "setPath %s", str);
        }
        this.f15515k = z;
        if (z) {
            this.f15514j = null;
        } else {
            this.f15514j = new File(str).getName();
        }
        return this;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: a */
    public BaseDownloadTask mo13781a(FileDownloadListener aflVar) {
        this.f15517m = aflVar;
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "setListener %s", aflVar);
        }
        return this;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: b */
    public BaseDownloadTask mo13775b(int i) {
        this.f15523s = i;
        return this;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: c */
    public BaseDownloadTask mo13769c(int i) {
        this.f15524t = i;
        return this;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: a */
    public BaseDownloadTask mo13790a() {
        return mo13775b(-1);
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: a */
    public BaseDownloadTask mo13787a(Object obj) {
        this.f15519o = obj;
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "setTag %s", obj);
        }
        return this;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: a */
    public BaseDownloadTask mo13788a(int i, Object obj) {
        if (this.f15518n == null) {
            this.f15518n = new SparseArray<>(2);
        }
        this.f15518n.put(i, obj);
        return this;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: a */
    public BaseDownloadTask mo13780a(boolean z) {
        this.f15525u = z;
        return this;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: a */
    public BaseDownloadTask mo13783a(BaseDownloadTask.AbstractC3403a aVar) {
        mo13773b(aVar);
        return this;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: b */
    public BaseDownloadTask mo13773b(BaseDownloadTask.AbstractC3403a aVar) {
        if (this.f15511g == null) {
            this.f15511g = new ArrayList<>();
        }
        if (!this.f15511g.contains(aVar)) {
            this.f15511g.add(aVar);
        }
        return this;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: c */
    public boolean mo13767c(BaseDownloadTask.AbstractC3403a aVar) {
        ArrayList<BaseDownloadTask.AbstractC3403a> arrayList = this.f15511g;
        return arrayList != null && arrayList.remove(aVar);
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: d */
    public BaseDownloadTask mo13764d(int i) {
        this.f15520p = i;
        return this;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: a */
    public BaseDownloadTask mo13785a(String str, String str2) {
        m13777af();
        this.f15516l.m19139a(str, str2);
        return this;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: b */
    public BaseDownloadTask mo13774b(String str) {
        m13777af();
        this.f15516l.m19140a(str);
        return this;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: c */
    public BaseDownloadTask mo13768c(String str) {
        if (this.f15516l == null) {
            synchronized (this.f15528x) {
                if (this.f15516l == null) {
                    return this;
                }
            }
        }
        this.f15516l.m19138b(str);
        return this;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: b */
    public BaseDownloadTask mo13771b(boolean z) {
        this.f15521q = z;
        return this;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: c */
    public BaseDownloadTask mo13766c(boolean z) {
        this.f15522r = z;
        return this;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: b */
    public int mo13776b() {
        return mo13770c().mo13738a();
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: c */
    public BaseDownloadTask.AbstractC3405c mo13770c() {
        return new C3408a();
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: d */
    public boolean mo13765d() {
        if (mo13761f()) {
            FileDownloadLog.m13210d(this, "This task[%d] is running, if you want start the same task, please create a new one by FileDownloader#create", Integer.valueOf(mo13754k()));
            return false;
        }
        this.f15507c = 0;
        this.f15526v = false;
        this.f15529y = false;
        this.f15508d.mo13542h();
        return true;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: e */
    public boolean mo13763e() {
        return this.f15508d.mo13543g() != 0;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: f */
    public boolean mo13761f() {
        if (FileDownloader.m13627a().m13587n().mo13521a(this)) {
            return true;
        }
        return FileDownloadStatus.m19078b(mo13815B());
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: g */
    public boolean mo13759g() {
        return this.f15507c != 0;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: h */
    public int mo13757h() {
        if (!this.f15526v) {
            return m13778ae();
        }
        throw new IllegalStateException("If you start the task manually, it means this task doesn't belong to a queue, so you must not invoke BaseDownloadTask#ready() or InQueueTask#enqueue() before you start() this method. For detail: If this task doesn't belong to a queue, what is just an isolated task, you just need to invoke BaseDownloadTask#start() to start this task, that's all. In other words, If this task doesn't belong to a queue, you must not invoke BaseDownloadTask#ready() method or InQueueTask#enqueue() method before invoke BaseDownloadTask#start(), If you do that and if there is the same listener object to start a queue in another thread, this task may be assembled by the queue, in that case, when you invoke BaseDownloadTask#start() manually to start this task or this task is started by the queue, there is an exception buried in there, because this task object is started two times without declare BaseDownloadTask#reuse() : 1. you invoke BaseDownloadTask#start() manually;  2. the queue start this task automatically.");
    }

    /* renamed from: ae */
    private int m13778ae() {
        if (!mo13763e()) {
            if (!mo13759g()) {
                mo13797T();
            }
            this.f15508d.mo13545e();
            return mo13754k();
        } else if (mo13761f()) {
            throw new IllegalStateException(FileDownloadUtils.m13182a("This task is running %d, if you want to start the same task, please create a new one by FileDownloader.create", Integer.valueOf(mo13754k())));
        } else {
            throw new IllegalStateException("This task is dirty to restart, If you want to reuse this task, please invoke #reuse method manually and retry to restart again." + this.f15508d.toString());
        }
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: i */
    public boolean mo13756i() {
        boolean f;
        synchronized (this.f15527w) {
            f = this.f15508d.mo13544f();
        }
        return f;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: j */
    public boolean mo13755j() {
        return mo13756i();
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: k */
    public int mo13754k() {
        int i = this.f15510f;
        if (i != 0) {
            return i;
        }
        if (TextUtils.isEmpty(this.f15513i) || TextUtils.isEmpty(this.f15512h)) {
            return 0;
        }
        int a = FileDownloadUtils.m13184a(this.f15512h, this.f15513i, this.f15515k);
        this.f15510f = a;
        return a;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: l */
    public int mo13753l() {
        return mo13754k();
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: m */
    public String mo13752m() {
        return this.f15512h;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: n */
    public int mo13751n() {
        return this.f15523s;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: o */
    public int mo13750o() {
        return this.f15524t;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: p */
    public String mo13749p() {
        return this.f15513i;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: q */
    public boolean mo13748q() {
        return this.f15515k;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: r */
    public String mo13747r() {
        return this.f15514j;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: s */
    public String mo13746s() {
        return FileDownloadUtils.m13183a(mo13749p(), mo13748q(), mo13747r());
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: t */
    public FileDownloadListener mo13745t() {
        return this.f15517m;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: u */
    public int mo13744u() {
        return mo13743v();
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: v */
    public int mo13743v() {
        if (this.f15508d.mo13541i() > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) this.f15508d.mo13541i();
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: w */
    public long mo13742w() {
        return this.f15508d.mo13541i();
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: x */
    public int mo13741x() {
        return mo13740y();
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: y */
    public int mo13740y() {
        if (this.f15508d.mo13540j() > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) this.f15508d.mo13540j();
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: z */
    public long mo13739z() {
        return this.f15508d.mo13540j();
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: A */
    public int mo13816A() {
        return this.f15508d.mo13584b();
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: B */
    public byte mo13815B() {
        return this.f15508d.mo13543g();
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: C */
    public boolean mo13814C() {
        return this.f15525u;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: D */
    public Throwable mo13813D() {
        return mo13812E();
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: E */
    public Throwable mo13812E() {
        return this.f15508d.mo13539k();
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: F */
    public boolean mo13811F() {
        return this.f15508d.mo13537m();
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: G */
    public Object mo13810G() {
        return this.f15519o;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: e */
    public Object mo13762e(int i) {
        SparseArray<Object> sparseArray = this.f15518n;
        if (sparseArray == null) {
            return null;
        }
        return sparseArray.get(i);
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: H */
    public boolean mo13809H() {
        return mo13808I();
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: I */
    public boolean mo13808I() {
        return this.f15508d.mo13536n();
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: J */
    public String mo13807J() {
        return this.f15508d.mo13535o();
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: K */
    public int mo13806K() {
        return this.f15520p;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: L */
    public int mo13805L() {
        return this.f15508d.mo13538l();
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: M */
    public boolean mo13804M() {
        return this.f15521q;
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: N */
    public boolean mo13803N() {
        return this.f15508d.mo13534p();
    }

    @Override // p110z1.BaseDownloadTask
    /* renamed from: O */
    public boolean mo13802O() {
        return this.f15522r;
    }

    /* renamed from: af */
    private void m13777af() {
        if (this.f15516l == null) {
            synchronized (this.f15528x) {
                if (this.f15516l == null) {
                    this.f15516l = new FileDownloadHeader();
                }
            }
        }
    }

    @Override // p110z1.DownloadTaskHunter.AbstractC3409a
    /* renamed from: ab */
    public FileDownloadHeader mo13731ab() {
        return this.f15516l;
    }

    @Override // p110z1.BaseDownloadTask.AbstractC3404b
    /* renamed from: V */
    public void mo13795V() {
        this.f15529y = true;
    }

    @Override // p110z1.BaseDownloadTask.AbstractC3404b
    /* renamed from: W */
    public void mo13794W() {
        this.f15508d.mo13533q();
        if (FileDownloadList.m13710a().m13706a(this)) {
            this.f15529y = false;
        }
    }

    @Override // p110z1.BaseDownloadTask.AbstractC3404b
    /* renamed from: X */
    public void mo13793X() {
        m13778ae();
    }

    @Override // p110z1.BaseDownloadTask.AbstractC3404b
    /* renamed from: Y */
    public void mo13792Y() {
        m13778ae();
    }

    @Override // p110z1.BaseDownloadTask.AbstractC3404b
    /* renamed from: Z */
    public Object mo13791Z() {
        return this.f15527w;
    }

    @Override // p110z1.BaseDownloadTask.AbstractC3404b
    /* renamed from: aa */
    public boolean mo13779aa() {
        ArrayList<BaseDownloadTask.AbstractC3403a> arrayList = this.f15511g;
        return arrayList != null && arrayList.size() > 0;
    }

    @Override // p110z1.BaseDownloadTask.AbstractC3404b
    /* renamed from: U */
    public boolean mo13796U() {
        return this.f15529y;
    }

    @Override // p110z1.DownloadTaskHunter.AbstractC3409a
    /* renamed from: d */
    public void mo13728d(String str) {
        this.f15514j = str;
    }

    @Override // p110z1.DownloadTaskHunter.AbstractC3409a
    /* renamed from: ad */
    public ArrayList<BaseDownloadTask.AbstractC3403a> mo13729ad() {
        return this.f15511g;
    }

    @Override // p110z1.BaseDownloadTask.AbstractC3404b
    /* renamed from: Q */
    public ITaskHunter.AbstractC3436a mo13800Q() {
        return this.f15509e;
    }

    @Override // p110z1.BaseDownloadTask.AbstractC3404b
    /* renamed from: f */
    public boolean mo13760f(int i) {
        return mo13754k() == i;
    }

    @Override // p110z1.BaseDownloadTask.AbstractC3404b
    /* renamed from: b */
    public boolean mo13772b(FileDownloadListener aflVar) {
        return mo13745t() == aflVar;
    }

    @Override // p110z1.BaseDownloadTask.AbstractC3404b
    /* renamed from: R */
    public boolean mo13799R() {
        return FileDownloadStatus.m19081a(mo13815B());
    }

    @Override // p110z1.BaseDownloadTask.AbstractC3404b
    /* renamed from: S */
    public int mo13798S() {
        return this.f15507c;
    }

    @Override // p110z1.BaseDownloadTask.AbstractC3404b
    /* renamed from: g */
    public void mo13758g(int i) {
        this.f15507c = i;
    }

    @Override // p110z1.BaseDownloadTask.AbstractC3404b
    /* renamed from: T */
    public void mo13797T() {
        int i;
        if (mo13745t() != null) {
            i = mo13745t().hashCode();
        } else {
            i = hashCode();
        }
        this.f15507c = i;
    }

    public String toString() {
        return FileDownloadUtils.m13182a("%d@%s", Integer.valueOf(mo13754k()), super.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DownloadTask.java */
    /* renamed from: z1.afd$a */
    /* loaded from: classes3.dex */
    public static final class C3408a implements BaseDownloadTask.AbstractC3405c {

        /* renamed from: a */
        private final DownloadTask f15530a;

        private C3408a(DownloadTask afdVar) {
            this.f15530a = afdVar;
            this.f15530a.f15526v = true;
        }

        @Override // p110z1.BaseDownloadTask.AbstractC3405c
        /* renamed from: a */
        public int mo13738a() {
            int k = this.f15530a.mo13754k();
            if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13211c(this, "add the task[%d] to the queue", Integer.valueOf(k));
            }
            FileDownloadList.m13710a().m13698c(this.f15530a);
            return k;
        }
    }
}
