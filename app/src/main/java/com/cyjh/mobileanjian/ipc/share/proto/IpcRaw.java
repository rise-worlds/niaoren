package com.cyjh.mobileanjian.ipc.share.proto;

import com.cyjh.mobileanjian.ipc.share.proto.Ipc;

/* renamed from: com.cyjh.mobileanjian.ipc.share.proto.b */
/* loaded from: classes.dex */
public final class IpcRaw extends ProtoBufDataWraper {

    /* renamed from: a */
    private Ipc.IpcMessage f8415a;

    public IpcRaw(Ipc.IpcMessage ipcMessage) {
        this.f8415a = null;
        this.f8415a = ipcMessage;
    }

    @Override // com.cyjh.mobileanjian.ipc.share.proto.ProtoBufDataWraper
    /* renamed from: a */
    public final byte[] mo20932a() {
        Ipc.IpcMessage ipcMessage = this.f8415a;
        if (ipcMessage == null) {
            return null;
        }
        return ipcMessage.toByteArray();
    }

    /* renamed from: a */
    public static final Ipc.IpcMessage m20933a(int i) {
        return Ipc.IpcMessage.newBuilder().setCmd(i).build();
    }
}
