package com.liulishuo.filedownloader.message;

import com.liulishuo.filedownloader.message.MessageSnapshotFlow;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import p110z1.FileDownloadExecutors;

/* renamed from: com.liulishuo.filedownloader.message.e */
/* loaded from: classes.dex */
public class MessageSnapshotThreadPool {

    /* renamed from: a */
    private final List<C1694a> f10352a = new ArrayList();

    /* renamed from: b */
    private final MessageSnapshotFlow.AbstractC1693b f10353b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageSnapshotThreadPool(int i, MessageSnapshotFlow.AbstractC1693b bVar) {
        this.f10353b = bVar;
        for (int i2 = 0; i2 < i; i2++) {
            this.f10352a.add(new C1694a(i2));
        }
    }

    /* renamed from: a */
    public void m19146a(MessageSnapshot messageSnapshot) {
        C1694a aVar = null;
        try {
            synchronized (this.f10352a) {
                int c = messageSnapshot.mo19169c();
                Iterator<C1694a> it = this.f10352a.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    C1694a next = it.next();
                    if (next.f10355b.contains(Integer.valueOf(c))) {
                        aVar = next;
                        break;
                    }
                }
                if (aVar == null) {
                    int i = 0;
                    Iterator<C1694a> it2 = this.f10352a.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        C1694a next2 = it2.next();
                        if (next2.f10355b.size() <= 0) {
                            aVar = next2;
                            break;
                        } else if (i == 0 || next2.f10355b.size() < i) {
                            i = next2.f10355b.size();
                            aVar = next2;
                        }
                    }
                }
                aVar.m19144a(c);
            }
        } finally {
            aVar.m19143a(messageSnapshot);
        }
    }

    /* compiled from: MessageSnapshotThreadPool.java */
    /* renamed from: com.liulishuo.filedownloader.message.e$a */
    /* loaded from: classes.dex */
    public class C1694a {

        /* renamed from: b */
        private final List<Integer> f10355b = new ArrayList();

        /* renamed from: c */
        private final Executor f10356c;

        public C1694a(int i) {
            this.f10356c = FileDownloadExecutors.m13232a(1, "Flow-" + i);
        }

        /* renamed from: a */
        public void m19144a(int i) {
            this.f10355b.add(Integer.valueOf(i));
        }

        /* renamed from: a */
        public void m19143a(final MessageSnapshot messageSnapshot) {
            this.f10356c.execute(new Runnable() { // from class: com.liulishuo.filedownloader.message.e.a.1
                @Override // java.lang.Runnable
                public void run() {
                    MessageSnapshotThreadPool.this.f10353b.receive(messageSnapshot);
                    C1694a.this.f10355b.remove(Integer.valueOf(messageSnapshot.mo19169c()));
                }
            });
        }
    }
}
