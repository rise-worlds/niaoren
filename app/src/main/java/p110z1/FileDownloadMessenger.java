package p110z1;

import com.liulishuo.filedownloader.message.BlockCompleteMessage;
import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import p110z1.BaseDownloadTask;
import p110z1.ITaskHunter;

/* renamed from: z1.afn */
/* loaded from: classes3.dex */
class FileDownloadMessenger implements IFileDownloadMessenger {

    /* renamed from: a */
    private BaseDownloadTask.AbstractC3404b f15581a;

    /* renamed from: b */
    private BaseDownloadTask.AbstractC3406d f15582b;

    /* renamed from: c */
    private Queue<MessageSnapshot> f15583c;

    /* renamed from: d */
    private boolean f15584d = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FileDownloadMessenger(BaseDownloadTask.AbstractC3404b bVar, BaseDownloadTask.AbstractC3406d dVar) {
        m13681b(bVar, dVar);
    }

    /* renamed from: b */
    private void m13681b(BaseDownloadTask.AbstractC3404b bVar, BaseDownloadTask.AbstractC3406d dVar) {
        this.f15581a = bVar;
        this.f15582b = dVar;
        this.f15583c = new LinkedBlockingQueue();
    }

    @Override // p110z1.IFileDownloadMessenger
    /* renamed from: a */
    public boolean mo13579a() {
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "notify begin %s", this.f15581a);
        }
        if (this.f15581a == null) {
            FileDownloadLog.m13210d(this, "can't begin the task, the holder fo the messenger is nil, %d", Integer.valueOf(this.f15583c.size()));
            return false;
        }
        this.f15582b.mo13737a();
        return true;
    }

    @Override // p110z1.IFileDownloadMessenger
    /* renamed from: a */
    public void mo13578a(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "notify pending %s", this.f15581a);
        }
        this.f15582b.mo13734o_();
        m13680k(messageSnapshot);
    }

    @Override // p110z1.IFileDownloadMessenger
    /* renamed from: b */
    public void mo13575b(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "notify started %s", this.f15581a);
        }
        this.f15582b.mo13734o_();
        m13680k(messageSnapshot);
    }

    @Override // p110z1.IFileDownloadMessenger
    /* renamed from: c */
    public void mo13573c(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "notify connected %s", this.f15581a);
        }
        this.f15582b.mo13734o_();
        m13680k(messageSnapshot);
    }

    @Override // p110z1.IFileDownloadMessenger
    /* renamed from: d */
    public void mo13571d(MessageSnapshot messageSnapshot) {
        BaseDownloadTask P = this.f15581a.mo13801P();
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "notify progress %s %d %d", P, Long.valueOf(P.mo13742w()), Long.valueOf(P.mo13739z()));
        }
        if (P.mo13751n() > 0) {
            this.f15582b.mo13734o_();
            m13680k(messageSnapshot);
        } else if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "notify progress but client not request notify %s", this.f15581a);
        }
    }

    @Override // p110z1.IFileDownloadMessenger
    /* renamed from: e */
    public void mo13569e(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "notify block completed %s %s", this.f15581a, Thread.currentThread().getName());
        }
        this.f15582b.mo13734o_();
        m13680k(messageSnapshot);
    }

    @Override // p110z1.IFileDownloadMessenger
    /* renamed from: f */
    public void mo13568f(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.f15845a) {
            BaseDownloadTask P = this.f15581a.mo13801P();
            FileDownloadLog.m13211c(this, "notify retry %s %d %d %s", this.f15581a, Integer.valueOf(P.mo13806K()), Integer.valueOf(P.mo13805L()), P.mo13812E());
        }
        this.f15582b.mo13734o_();
        m13680k(messageSnapshot);
    }

    @Override // p110z1.IFileDownloadMessenger
    /* renamed from: g */
    public void mo13567g(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "notify warn %s", this.f15581a);
        }
        this.f15582b.mo13736c();
        m13680k(messageSnapshot);
    }

    @Override // p110z1.IFileDownloadMessenger
    /* renamed from: h */
    public void mo13566h(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.f15845a) {
            BaseDownloadTask.AbstractC3404b bVar = this.f15581a;
            FileDownloadLog.m13211c(this, "notify error %s %s", bVar, bVar.mo13801P().mo13812E());
        }
        this.f15582b.mo13736c();
        m13680k(messageSnapshot);
    }

    @Override // p110z1.IFileDownloadMessenger
    /* renamed from: i */
    public void mo13565i(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "notify paused %s", this.f15581a);
        }
        this.f15582b.mo13736c();
        m13680k(messageSnapshot);
    }

    @Override // p110z1.IFileDownloadMessenger
    /* renamed from: j */
    public void mo13564j(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "notify completed %s", this.f15581a);
        }
        this.f15582b.mo13736c();
        m13680k(messageSnapshot);
    }

    /* renamed from: k */
    private void m13680k(MessageSnapshot messageSnapshot) {
        BaseDownloadTask.AbstractC3404b bVar = this.f15581a;
        if (bVar == null) {
            if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13211c(this, "occur this case, it would be the host task of this messenger has been over(paused/warn/completed/error) on the other thread before receiving the snapshot(id[%d], status[%d])", Integer.valueOf(messageSnapshot.mo19169c()), Byte.valueOf(messageSnapshot.mo19170b()));
            }
        } else if (this.f15584d || bVar.mo13801P().mo13745t() == null) {
            if ((FileDownloadMonitor.m13676c() || this.f15581a.mo13779aa()) && messageSnapshot.mo19170b() == 4) {
                this.f15582b.mo13736c();
            }
            m13682a(messageSnapshot.mo19170b());
        } else {
            this.f15583c.offer(messageSnapshot);
            FileDownloadMessageStation.m13692a().m13690a(this);
        }
    }

    /* renamed from: a */
    private void m13682a(int i) {
        if (FileDownloadStatus.m19081a(i)) {
            if (!this.f15583c.isEmpty()) {
                MessageSnapshot peek = this.f15583c.peek();
                FileDownloadLog.m13210d(this, "the messenger[%s](with id[%d]) has already accomplished all his job, but there still are some messages in parcel queue[%d] queue-top-status[%d]", this, Integer.valueOf(peek.mo19169c()), Integer.valueOf(this.f15583c.size()), Byte.valueOf(peek.mo19170b()));
            }
            this.f15581a = null;
        }
    }

    @Override // p110z1.IFileDownloadMessenger
    /* renamed from: b */
    public void mo13576b() {
        if (!this.f15584d) {
            MessageSnapshot poll = this.f15583c.poll();
            byte b = poll.mo19170b();
            BaseDownloadTask.AbstractC3404b bVar = this.f15581a;
            if (bVar != null) {
                BaseDownloadTask P = bVar.mo13801P();
                FileDownloadListener t = P.mo13745t();
                ITaskHunter.AbstractC3436a Q = bVar.mo13800Q();
                m13682a(b);
                if (t != null && !t.m13694a()) {
                    if (b == 4) {
                        try {
                            t.mo13262b(P);
                            mo13564j(((BlockCompleteMessage) poll).mo19174a());
                        } catch (Throwable th) {
                            mo13566h(Q.mo13531a(th));
                        }
                    } else {
                        FileDownloadLargeFileListener afhVar = null;
                        if (t instanceof FileDownloadLargeFileListener) {
                            afhVar = (FileDownloadLargeFileListener) t;
                        }
                        switch (b) {
                            case -4:
                                t.mo13258d(P);
                                return;
                            case -3:
                                t.mo13260c(P);
                                return;
                            case -2:
                                if (afhVar != null) {
                                    afhVar.m13720c(P, poll.mo19164h(), poll.mo19163i());
                                    return;
                                } else {
                                    t.mo13259c(P, poll.mo19162j(), poll.mo19161k());
                                    return;
                                }
                            case -1:
                                t.mo13266a(P, poll.mo19168d());
                                return;
                            case 0:
                            case 4:
                            default:
                                return;
                            case 1:
                                if (afhVar != null) {
                                    afhVar.m13724a(P, poll.mo19164h(), poll.mo19163i());
                                    return;
                                } else {
                                    t.mo13267a(P, poll.mo19162j(), poll.mo19161k());
                                    return;
                                }
                            case 2:
                                if (afhVar != null) {
                                    afhVar.m13723a(P, poll.mo19165g(), poll.mo19166f(), P.mo13742w(), poll.mo19163i());
                                    return;
                                } else {
                                    t.mo13693a(P, poll.mo19165g(), poll.mo19166f(), P.mo13743v(), poll.mo19161k());
                                    return;
                                }
                            case 3:
                                if (afhVar != null) {
                                    afhVar.m13721b(P, poll.mo19164h(), P.mo13739z());
                                    return;
                                } else {
                                    t.mo13261b(P, poll.mo19162j(), P.mo13740y());
                                    return;
                                }
                            case 5:
                                if (afhVar != null) {
                                    afhVar.m13722a(P, poll.mo19168d(), poll.mo19167e(), poll.mo19164h());
                                    return;
                                } else {
                                    t.mo13265a(P, poll.mo19168d(), poll.mo19167e(), poll.mo19162j());
                                    return;
                                }
                            case 6:
                                t.mo13268a(P);
                                return;
                        }
                    }
                }
            } else {
                throw new IllegalArgumentException(FileDownloadUtils.m13182a("can't handover the message, no master to receive this message(status[%d]) size[%d]", Integer.valueOf(b), Integer.valueOf(this.f15583c.size())));
            }
        }
    }

    @Override // p110z1.IFileDownloadMessenger
    /* renamed from: c */
    public boolean mo13574c() {
        return this.f15581a.mo13801P().mo13804M();
    }

    @Override // p110z1.IFileDownloadMessenger
    /* renamed from: a */
    public void mo13577a(BaseDownloadTask.AbstractC3404b bVar, BaseDownloadTask.AbstractC3406d dVar) {
        if (this.f15581a == null) {
            m13681b(bVar, dVar);
            return;
        }
        throw new IllegalStateException(FileDownloadUtils.m13182a("the messenger is working, can't re-appointment for %s", bVar));
    }

    @Override // p110z1.IFileDownloadMessenger
    /* renamed from: d */
    public boolean mo13572d() {
        return this.f15583c.peek().mo19170b() == 4;
    }

    @Override // p110z1.IFileDownloadMessenger
    /* renamed from: e */
    public void mo13570e() {
        this.f15584d = true;
    }

    public String toString() {
        Object[] objArr = new Object[2];
        BaseDownloadTask.AbstractC3404b bVar = this.f15581a;
        objArr[0] = Integer.valueOf(bVar == null ? -1 : bVar.mo13801P().mo13754k());
        objArr[1] = super.toString();
        return FileDownloadUtils.m13182a("%d:%s", objArr);
    }
}
