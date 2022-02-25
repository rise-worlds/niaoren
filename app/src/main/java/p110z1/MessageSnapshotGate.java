package p110z1;

import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.message.MessageSnapshotFlow;
import java.util.List;
import p110z1.BaseDownloadTask;

/* renamed from: z1.age */
/* loaded from: classes3.dex */
public class MessageSnapshotGate implements MessageSnapshotFlow.AbstractC1693b {
    /* renamed from: a */
    private boolean m13517a(List<BaseDownloadTask.AbstractC3404b> list, MessageSnapshot messageSnapshot) {
        boolean a;
        if (list.size() > 1 && messageSnapshot.mo19170b() == -3) {
            for (BaseDownloadTask.AbstractC3404b bVar : list) {
                synchronized (bVar.mo13791Z()) {
                    if (bVar.mo13800Q().mo13529c(messageSnapshot)) {
                        FileDownloadLog.m13211c(this, "updateMoreLikelyCompleted", new Object[0]);
                        return true;
                    }
                }
            }
        }
        for (BaseDownloadTask.AbstractC3404b bVar2 : list) {
            synchronized (bVar2.mo13791Z()) {
                if (bVar2.mo13800Q().mo13530b(messageSnapshot)) {
                    FileDownloadLog.m13211c(this, "updateKeepFlow", new Object[0]);
                    return true;
                }
            }
        }
        if (-4 == messageSnapshot.mo19170b()) {
            for (BaseDownloadTask.AbstractC3404b bVar3 : list) {
                synchronized (bVar3.mo13791Z()) {
                    if (bVar3.mo13800Q().mo13527d(messageSnapshot)) {
                        FileDownloadLog.m13211c(this, "updateSampleFilePathTaskRunning", new Object[0]);
                        return true;
                    }
                }
            }
        }
        if (list.size() != 1) {
            return false;
        }
        BaseDownloadTask.AbstractC3404b bVar4 = list.get(0);
        synchronized (bVar4.mo13791Z()) {
            FileDownloadLog.m13211c(this, "updateKeepAhead", new Object[0]);
            a = bVar4.mo13800Q().mo13532a(messageSnapshot);
        }
        return a;
    }

    @Override // com.liulishuo.filedownloader.message.MessageSnapshotFlow.AbstractC1693b
    public void receive(MessageSnapshot messageSnapshot) {
        synchronized (Integer.toString(messageSnapshot.mo19169c()).intern()) {
            List<BaseDownloadTask.AbstractC3404b> c = FileDownloadList.m13710a().m13699c(messageSnapshot.mo19169c());
            if (c.size() > 0) {
                BaseDownloadTask P = c.get(0).mo13801P();
                if (FileDownloadLog.f15845a) {
                    FileDownloadLog.m13211c(this, "~~~callback %s old[%s] new[%s] %d", Integer.valueOf(messageSnapshot.mo19169c()), Byte.valueOf(P.mo13815B()), Byte.valueOf(messageSnapshot.mo19170b()), Integer.valueOf(c.size()));
                }
                if (!m13517a(c, messageSnapshot)) {
                    StringBuilder sb = new StringBuilder("The event isn't consumed, id:" + messageSnapshot.mo19169c() + " status:" + ((int) messageSnapshot.mo19170b()) + " task-count:" + c.size());
                    for (BaseDownloadTask.AbstractC3404b bVar : c) {
                        sb.append(" | ");
                        sb.append((int) bVar.mo13801P().mo13815B());
                    }
                    FileDownloadLog.m13212b(this, sb.toString(), new Object[0]);
                }
            } else {
                FileDownloadLog.m13212b(this, "Receive the event %d, but there isn't any running task in the upper layer", Byte.valueOf(messageSnapshot.mo19170b()));
            }
        }
    }
}
