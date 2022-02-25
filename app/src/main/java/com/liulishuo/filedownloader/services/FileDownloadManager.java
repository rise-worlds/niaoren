package com.liulishuo.filedownloader.services;

import android.text.TextUtils;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import java.util.List;
import p110z1.CustomComponentHolder;
import p110z1.DownloadLaunchRunnable;
import p110z1.FileDownloadDatabase;
import p110z1.FileDownloadHelper;
import p110z1.FileDownloadLog;
import p110z1.FileDownloadUtils;
import p110z1.IThreadPoolMonitor;

/* renamed from: com.liulishuo.filedownloader.services.g */
/* loaded from: classes.dex */
class FileDownloadManager implements IThreadPoolMonitor {

    /* renamed from: a */
    private final FileDownloadDatabase f10433a;

    /* renamed from: b */
    private final FileDownloadThreadPool f10434b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FileDownloadManager() {
        CustomComponentHolder a = CustomComponentHolder.m13415a();
        this.f10433a = a.m13408c();
        this.f10434b = new FileDownloadThreadPool(a.m13407d());
    }

    /* renamed from: a */
    public synchronized void m19045a(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3) {
        List<ConnectionModel> list;
        FileDownloadModel fileDownloadModel;
        boolean z4;
        String str3;
        String str4;
        boolean z5 = true;
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "request start the task with url(%s) path(%s) isDirectory(%B)", str, str2, Boolean.valueOf(z));
        }
        int a = FileDownloadUtils.m13184a(str, str2, z);
        FileDownloadModel b = this.f10433a.mo13453b(a);
        if (z || b != null) {
            fileDownloadModel = b;
            list = null;
        } else {
            int a2 = FileDownloadUtils.m13184a(str, FileDownloadUtils.m13153k(str2), true);
            FileDownloadModel b2 = this.f10433a.mo13453b(a2);
            if (b2 == null || !str2.equals(b2.m19122d())) {
                list = null;
                fileDownloadModel = b2;
            } else {
                if (FileDownloadLog.f15845a) {
                    FileDownloadLog.m13211c(this, "task[%d] find model by dirCaseId[%d]", Integer.valueOf(a), Integer.valueOf(a2));
                }
                list = this.f10433a.mo13448c(a2);
                fileDownloadModel = b2;
            }
        }
        if (FileDownloadHelper.m13227a(a, fileDownloadModel, (IThreadPoolMonitor) this, true)) {
            if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13211c(this, "has already started download %d", Integer.valueOf(a));
            }
            return;
        }
        if (fileDownloadModel != null) {
            z4 = z2;
            str3 = fileDownloadModel.m19122d();
        } else {
            z4 = z2;
            str3 = FileDownloadUtils.m13183a(str2, z, (String) null);
        }
        if (FileDownloadHelper.m13226a(a, str3, z4, true)) {
            if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13211c(this, "has already completed downloading %d", Integer.valueOf(a));
            }
            return;
        }
        long j = 0;
        if (fileDownloadModel != null) {
            j = fileDownloadModel.m19118g();
        }
        if (fileDownloadModel != null) {
            str4 = fileDownloadModel.m19120e();
        } else {
            str4 = FileDownloadUtils.m13160e(str3);
        }
        if (FileDownloadHelper.m13228a(a, j, str4, str3, this)) {
            if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13211c(this, "there is an another task with the same target-file-path %d %s", Integer.valueOf(a), str3);
            }
            if (fileDownloadModel != null) {
                this.f10433a.mo13445e(a);
                this.f10433a.mo13446d(a);
            }
            return;
        }
        if (fileDownloadModel == null || !(fileDownloadModel.m19119f() == -2 || fileDownloadModel.m19119f() == -1 || fileDownloadModel.m19119f() == 1 || fileDownloadModel.m19119f() == 6 || fileDownloadModel.m19119f() == 2)) {
            if (fileDownloadModel == null) {
                fileDownloadModel = new FileDownloadModel();
            }
            fileDownloadModel.m19131a(str);
            fileDownloadModel.m19130a(str2, z);
            fileDownloadModel.m19133a(a);
            fileDownloadModel.m19132a(0L);
            fileDownloadModel.m19124c(0L);
            fileDownloadModel.m19134a((byte) 1);
            fileDownloadModel.m19128b(1);
        } else if (fileDownloadModel.m19135a() != a) {
            this.f10433a.mo13445e(fileDownloadModel.m19135a());
            this.f10433a.mo13446d(fileDownloadModel.m19135a());
            fileDownloadModel.m19133a(a);
            fileDownloadModel.m19130a(str2, z);
            if (list != null) {
                for (ConnectionModel aVar : list) {
                    aVar.m19092a(a);
                    this.f10433a.mo13456a(aVar);
                }
            }
        } else if (!TextUtils.equals(str, fileDownloadModel.m19129b())) {
            fileDownloadModel.m19131a(str);
        } else {
            z5 = false;
        }
        if (z5) {
            this.f10433a.mo13450b(fileDownloadModel);
        }
        this.f10434b.m19033a(new DownloadLaunchRunnable.C3459a().m13379a(fileDownloadModel).m13380a(fileDownloadHeader).m13376a(this).m13377a(Integer.valueOf(i2)).m13374b(Integer.valueOf(i)).m13378a(Boolean.valueOf(z2)).m13375b(Boolean.valueOf(z3)).m13373c(Integer.valueOf(i3)).m13381a());
    }

    /* renamed from: a */
    public boolean m19046a(String str, String str2) {
        return m19047a(FileDownloadUtils.m13173b(str, str2));
    }

    /* renamed from: a */
    public boolean m19047a(int i) {
        return mo13524a(this.f10433a.mo13453b(i));
    }

    /* renamed from: b */
    public boolean m19043b(int i) {
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "request pause the task %d", Integer.valueOf(i));
        }
        FileDownloadModel b = this.f10433a.mo13453b(i);
        if (b == null) {
            return false;
        }
        b.m19134a((byte) -2);
        this.f10434b.m19031b(i);
        return true;
    }

    /* renamed from: a */
    public void m19048a() {
        List<Integer> b = this.f10434b.m19032b();
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "pause all tasks %d", Integer.valueOf(b.size()));
        }
        for (Integer num : b) {
            m19043b(num.intValue());
        }
    }

    /* renamed from: c */
    public long m19041c(int i) {
        FileDownloadModel b = this.f10433a.mo13453b(i);
        if (b == null) {
            return 0L;
        }
        int n = b.m19111n();
        if (n <= 1) {
            return b.m19118g();
        }
        List<ConnectionModel> c = this.f10433a.mo13448c(i);
        if (c == null || c.size() != n) {
            return 0L;
        }
        return ConnectionModel.m19090a(c);
    }

    /* renamed from: d */
    public long m19040d(int i) {
        FileDownloadModel b = this.f10433a.mo13453b(i);
        if (b == null) {
            return 0L;
        }
        return b.m19117h();
    }

    /* renamed from: e */
    public byte m19039e(int i) {
        FileDownloadModel b = this.f10433a.mo13453b(i);
        if (b == null) {
            return (byte) 0;
        }
        return b.m19119f();
    }

    /* renamed from: b */
    public boolean m19044b() {
        return this.f10434b.m19036a() <= 0;
    }

    /* renamed from: f */
    public synchronized boolean m19038f(int i) {
        return this.f10434b.m19035a(i);
    }

    @Override // p110z1.IThreadPoolMonitor
    /* renamed from: a */
    public boolean mo13524a(FileDownloadModel fileDownloadModel) {
        if (fileDownloadModel == null) {
            return false;
        }
        boolean c = this.f10434b.m19029c(fileDownloadModel.m19135a());
        if (FileDownloadStatus.m19081a(fileDownloadModel.m19119f())) {
            return c;
        }
        if (c) {
            return true;
        }
        FileDownloadLog.m13214a(this, "%d status is[%s](not finish) & but not in the pool", Integer.valueOf(fileDownloadModel.m19135a()), Byte.valueOf(fileDownloadModel.m19119f()));
        return false;
    }

    @Override // p110z1.IThreadPoolMonitor
    /* renamed from: a */
    public int mo13523a(String str, int i) {
        return this.f10434b.m19034a(str, i);
    }

    /* renamed from: g */
    public boolean m19037g(int i) {
        if (i == 0) {
            FileDownloadLog.m13210d(this, "The task[%d] id is invalid, can't clear it.", Integer.valueOf(i));
            return false;
        } else if (m19047a(i)) {
            FileDownloadLog.m13210d(this, "The task[%d] is downloading, can't clear it.", Integer.valueOf(i));
            return false;
        } else {
            this.f10433a.mo13445e(i);
            this.f10433a.mo13446d(i);
            return true;
        }
    }

    /* renamed from: c */
    public void m19042c() {
        this.f10433a.mo13469a();
    }
}
