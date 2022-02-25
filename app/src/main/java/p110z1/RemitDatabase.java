package p110z1;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import p110z1.FileDownloadDatabase;
import p110z1.FileDownloadHelper;

/* renamed from: z1.agn */
/* loaded from: classes3.dex */
public class RemitDatabase implements FileDownloadDatabase {

    /* renamed from: h */
    private static final int f15646h = 0;

    /* renamed from: c */
    private Handler f15649c;

    /* renamed from: g */
    private volatile Thread f15653g;

    /* renamed from: e */
    private final List<Integer> f15651e = new ArrayList();

    /* renamed from: f */
    private AtomicInteger f15652f = new AtomicInteger();

    /* renamed from: a */
    private final NoDatabaseImpl f15647a = new NoDatabaseImpl();

    /* renamed from: b */
    private final SqliteDatabaseImpl f15648b = new SqliteDatabaseImpl();

    /* renamed from: d */
    private final long f15650d = FileDownloadProperties.m13208a().f15858b;

    public RemitDatabase() {
        HandlerThread handlerThread = new HandlerThread(FileDownloadUtils.m13152l("RemitHandoverToDB"));
        handlerThread.start();
        this.f15649c = new Handler(handlerThread.getLooper(), new Handler.Callback() { // from class: z1.agn.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                int i = message.what;
                if (i == 0) {
                    if (RemitDatabase.this.f15653g != null) {
                        LockSupport.unpark(RemitDatabase.this.f15653g);
                        RemitDatabase.this.f15653g = null;
                    }
                    return false;
                }
                try {
                    RemitDatabase.this.f15652f.set(i);
                    RemitDatabase.this.m13472g(i);
                    RemitDatabase.this.f15651e.add(Integer.valueOf(i));
                    return false;
                } finally {
                    RemitDatabase.this.f15652f.set(0);
                    if (RemitDatabase.this.f15653g != null) {
                        LockSupport.unpark(RemitDatabase.this.f15653g);
                        RemitDatabase.this.f15653g = null;
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public void m13472g(int i) {
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "sync cache to db %d", Integer.valueOf(i));
        }
        this.f15648b.mo13450b(this.f15647a.mo13453b(i));
        List<ConnectionModel> c = this.f15647a.mo13448c(i);
        this.f15648b.mo13446d(i);
        for (ConnectionModel aVar : c) {
            this.f15648b.mo13456a(aVar);
        }
    }

    /* renamed from: h */
    private boolean m13471h(int i) {
        return !this.f15651e.contains(Integer.valueOf(i));
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13468a(int i) {
        this.f15649c.sendEmptyMessageDelayed(i, this.f15650d);
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: b */
    public FileDownloadModel mo13453b(int i) {
        return this.f15647a.mo13453b(i);
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: c */
    public List<ConnectionModel> mo13448c(int i) {
        return this.f15647a.mo13448c(i);
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: d */
    public void mo13446d(int i) {
        this.f15647a.mo13446d(i);
        if (!m13471h(i)) {
            this.f15648b.mo13446d(i);
        }
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13456a(ConnectionModel aVar) {
        this.f15647a.mo13456a(aVar);
        if (!m13471h(aVar.m19093a())) {
            this.f15648b.mo13456a(aVar);
        }
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13466a(int i, int i2, long j) {
        this.f15647a.mo13466a(i, i2, j);
        if (!m13471h(i)) {
            this.f15648b.mo13466a(i, i2, j);
        }
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13465a(int i, long j) {
        this.f15647a.mo13465a(i, j);
        if (!m13471h(i)) {
            this.f15648b.mo13465a(i, j);
        }
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13467a(int i, int i2) {
        this.f15647a.mo13467a(i, i2);
        if (!m13471h(i)) {
            this.f15648b.mo13467a(i, i2);
        }
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13457a(FileDownloadModel fileDownloadModel) {
        this.f15647a.mo13457a(fileDownloadModel);
        if (!m13471h(fileDownloadModel.m19135a())) {
            this.f15648b.mo13457a(fileDownloadModel);
        }
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: b */
    public void mo13450b(FileDownloadModel fileDownloadModel) {
        this.f15647a.mo13450b(fileDownloadModel);
        if (!m13471h(fileDownloadModel.m19135a())) {
            this.f15648b.mo13450b(fileDownloadModel);
        }
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: e */
    public boolean mo13445e(int i) {
        this.f15648b.mo13445e(i);
        return this.f15647a.mo13445e(i);
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13469a() {
        this.f15647a.mo13469a();
        this.f15648b.mo13469a();
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13462a(int i, String str, long j, long j2, int i2) {
        this.f15647a.mo13462a(i, str, j, j2, i2);
        if (!m13471h(i)) {
            this.f15648b.mo13462a(i, str, j, j2, i2);
        }
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13464a(int i, long j, String str, String str2) {
        this.f15647a.mo13464a(i, j, str, str2);
        if (!m13471h(i)) {
            this.f15648b.mo13464a(i, j, str, str2);
        }
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: f */
    public void mo13444f(int i) {
        this.f15647a.mo13444f(i);
        if (!m13471h(i)) {
            this.f15648b.mo13444f(i);
        }
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13461a(int i, Throwable th) {
        this.f15647a.mo13461a(i, th);
        if (!m13471h(i)) {
            this.f15648b.mo13461a(i, th);
        }
    }

    /* renamed from: i */
    private void m13470i(int i) {
        this.f15649c.removeMessages(i);
        if (this.f15652f.get() == i) {
            this.f15653g = Thread.currentThread();
            this.f15649c.sendEmptyMessage(0);
            LockSupport.park();
            return;
        }
        m13472g(i);
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: a */
    public void mo13460a(int i, Throwable th, long j) {
        this.f15647a.mo13460a(i, th, j);
        if (m13471h(i)) {
            m13470i(i);
        }
        this.f15648b.mo13460a(i, th, j);
        this.f15651e.remove(Integer.valueOf(i));
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: b */
    public void mo13452b(int i, long j) {
        this.f15647a.mo13452b(i, j);
        if (m13471h(i)) {
            this.f15649c.removeMessages(i);
            if (this.f15652f.get() == i) {
                this.f15653g = Thread.currentThread();
                this.f15649c.sendEmptyMessage(0);
                LockSupport.park();
                this.f15648b.mo13452b(i, j);
            }
        } else {
            this.f15648b.mo13452b(i, j);
        }
        this.f15651e.remove(Integer.valueOf(i));
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: c */
    public void mo13447c(int i, long j) {
        this.f15647a.mo13447c(i, j);
        if (m13471h(i)) {
            m13470i(i);
        }
        this.f15648b.mo13447c(i, j);
        this.f15651e.remove(Integer.valueOf(i));
    }

    @Override // p110z1.FileDownloadDatabase
    /* renamed from: b */
    public FileDownloadDatabase.AbstractC3443a mo13454b() {
        return this.f15648b.m13458a(this.f15647a.f15642a, this.f15647a.f15643b);
    }

    /* compiled from: RemitDatabase.java */
    /* renamed from: z1.agn$a */
    /* loaded from: classes3.dex */
    public static class C3448a implements FileDownloadHelper.AbstractC3479c {
        @Override // p110z1.FileDownloadHelper.AbstractC3479c
        /* renamed from: a */
        public FileDownloadDatabase mo13222a() {
            return new RemitDatabase();
        }
    }
}
