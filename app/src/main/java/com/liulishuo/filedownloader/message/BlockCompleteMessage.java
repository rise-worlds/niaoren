package com.liulishuo.filedownloader.message;

import p110z1.FileDownloadUtils;

/* loaded from: classes.dex */
public interface BlockCompleteMessage {
    /* renamed from: a */
    MessageSnapshot mo19174a();

    /* loaded from: classes.dex */
    public static class BlockCompleteMessageImpl extends MessageSnapshot implements BlockCompleteMessage {

        /* renamed from: b */
        private final MessageSnapshot f10322b;

        @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: b */
        public byte mo19170b() {
            return (byte) 4;
        }

        public BlockCompleteMessageImpl(MessageSnapshot messageSnapshot) {
            super(messageSnapshot.mo19169c());
            if (messageSnapshot.mo19170b() == -3) {
                this.f10322b = messageSnapshot;
                return;
            }
            throw new IllegalArgumentException(FileDownloadUtils.m13182a("can't create the block complete message for id[%d], status[%d]", Integer.valueOf(messageSnapshot.mo19169c()), Byte.valueOf(messageSnapshot.mo19170b())));
        }

        @Override // com.liulishuo.filedownloader.message.BlockCompleteMessage
        /* renamed from: a */
        public MessageSnapshot mo19174a() {
            return this.f10322b;
        }
    }
}
