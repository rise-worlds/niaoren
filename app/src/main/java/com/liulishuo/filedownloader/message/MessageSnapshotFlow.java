package com.liulishuo.filedownloader.message;

/* renamed from: com.liulishuo.filedownloader.message.c */
/* loaded from: classes.dex */
public class MessageSnapshotFlow {

    /* renamed from: a */
    private volatile MessageSnapshotThreadPool f10349a;

    /* renamed from: b */
    private volatile AbstractC1693b f10350b;

    /* compiled from: MessageSnapshotFlow.java */
    /* renamed from: com.liulishuo.filedownloader.message.c$b */
    /* loaded from: classes.dex */
    public interface AbstractC1693b {
        void receive(MessageSnapshot messageSnapshot);
    }

    /* compiled from: MessageSnapshotFlow.java */
    /* renamed from: com.liulishuo.filedownloader.message.c$a */
    /* loaded from: classes.dex */
    public static final class C1692a {

        /* renamed from: a */
        private static final MessageSnapshotFlow f10351a = new MessageSnapshotFlow();
    }

    /* renamed from: a */
    public static MessageSnapshotFlow m19157a() {
        return C1692a.f10351a;
    }

    /* renamed from: a */
    public void m19155a(AbstractC1693b bVar) {
        this.f10350b = bVar;
        if (bVar == null) {
            this.f10349a = null;
        } else {
            this.f10349a = new MessageSnapshotThreadPool(5, bVar);
        }
    }

    /* renamed from: a */
    public void m19156a(MessageSnapshot messageSnapshot) {
        if (messageSnapshot instanceof IFlowDirectly) {
            if (this.f10350b != null) {
                this.f10350b.receive(messageSnapshot);
            }
        } else if (this.f10349a != null) {
            this.f10349a.m19146a(messageSnapshot);
        }
    }
}
