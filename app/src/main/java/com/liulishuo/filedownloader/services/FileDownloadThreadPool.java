package com.liulishuo.filedownloader.services;

import android.util.SparseArray;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import p110z1.DownloadLaunchRunnable;
import p110z1.FileDownloadExecutors;
import p110z1.FileDownloadLog;
import p110z1.FileDownloadProperties;

/* renamed from: com.liulishuo.filedownloader.services.h */
/* loaded from: classes.dex */
class FileDownloadThreadPool {

    /* renamed from: b */
    private ThreadPoolExecutor f10436b;

    /* renamed from: d */
    private int f10438d;

    /* renamed from: a */
    private SparseArray<DownloadLaunchRunnable> f10435a = new SparseArray<>();

    /* renamed from: c */
    private final String f10437c = "Network";

    /* renamed from: e */
    private int f10439e = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FileDownloadThreadPool(int i) {
        this.f10436b = FileDownloadExecutors.m13232a(i, "Network");
        this.f10438d = i;
    }

    /* renamed from: a */
    public synchronized boolean m19035a(int i) {
        if (m19036a() > 0) {
            FileDownloadLog.m13210d(this, "Can't change the max network thread count, because the  network thread pool isn't in IDLE, please try again after all running tasks are completed or invoking FileDownloader#pauseAll directly.", new Object[0]);
            return false;
        }
        int a = FileDownloadProperties.m13207a(i);
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "change the max network thread count, from %d to %d", Integer.valueOf(this.f10438d), Integer.valueOf(a));
        }
        List<Runnable> shutdownNow = this.f10436b.shutdownNow();
        this.f10436b = FileDownloadExecutors.m13232a(a, "Network");
        if (shutdownNow.size() > 0) {
            FileDownloadLog.m13210d(this, "recreate the network thread pool and discard %d tasks", Integer.valueOf(shutdownNow.size()));
        }
        this.f10438d = a;
        return true;
    }

    /* renamed from: a */
    public void m19033a(DownloadLaunchRunnable agtVar) {
        agtVar.m13392b();
        synchronized (this) {
            this.f10435a.put(agtVar.m13389d(), agtVar);
        }
        this.f10436b.execute(agtVar);
        int i = this.f10439e;
        if (i >= 600) {
            m19030c();
            this.f10439e = 0;
            return;
        }
        this.f10439e = i + 1;
    }

    /* renamed from: b */
    public void m19031b(int i) {
        m19030c();
        synchronized (this) {
            DownloadLaunchRunnable agtVar = this.f10435a.get(i);
            if (agtVar != null) {
                agtVar.m13400a();
                boolean remove = this.f10436b.remove(agtVar);
                if (FileDownloadLog.f15845a) {
                    FileDownloadLog.m13211c(this, "successful cancel %d %B", Integer.valueOf(i), Boolean.valueOf(remove));
                }
            }
            this.f10435a.remove(i);
        }
    }

    /* renamed from: c */
    private synchronized void m19030c() {
        SparseArray<DownloadLaunchRunnable> sparseArray = new SparseArray<>();
        int size = this.f10435a.size();
        for (int i = 0; i < size; i++) {
            int keyAt = this.f10435a.keyAt(i);
            DownloadLaunchRunnable agtVar = this.f10435a.get(keyAt);
            if (agtVar.m13388e()) {
                sparseArray.put(keyAt, agtVar);
            }
        }
        this.f10435a = sparseArray;
    }

    /* renamed from: c */
    public boolean m19029c(int i) {
        DownloadLaunchRunnable agtVar = this.f10435a.get(i);
        return agtVar != null && agtVar.m13388e();
    }

    /* renamed from: a */
    public int m19034a(String str, int i) {
        if (str == null) {
            return 0;
        }
        int size = this.f10435a.size();
        for (int i2 = 0; i2 < size; i2++) {
            DownloadLaunchRunnable valueAt = this.f10435a.valueAt(i2);
            if (valueAt != null && valueAt.m13388e() && valueAt.m13389d() != i && str.equals(valueAt.m13387f())) {
                return valueAt.m13389d();
            }
        }
        return 0;
    }

    /* renamed from: a */
    public synchronized int m19036a() {
        m19030c();
        return this.f10435a.size();
    }

    /* renamed from: b */
    public synchronized List<Integer> m19032b() {
        ArrayList arrayList;
        m19030c();
        arrayList = new ArrayList();
        for (int i = 0; i < this.f10435a.size(); i++) {
            arrayList.add(Integer.valueOf(this.f10435a.get(this.f10435a.keyAt(i)).m13389d()));
        }
        return arrayList;
    }
}
