package com.liulishuo.filedownloader.message;

import com.liulishuo.filedownloader.message.BlockCompleteMessage;
import com.liulishuo.filedownloader.message.LargeMessageSnapshot;
import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.message.SmallMessageSnapshot;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.io.File;
import p110z1.BaseDownloadTask;
import p110z1.DownloadStatusCallback;
import p110z1.FileDownloadLog;
import p110z1.FileDownloadUtils;

/* renamed from: com.liulishuo.filedownloader.message.d */
/* loaded from: classes.dex */
public class MessageSnapshotTaker {
    /* renamed from: a */
    public static MessageSnapshot m19153a(byte b, FileDownloadModel fileDownloadModel) {
        return m19152a(b, fileDownloadModel, (DownloadStatusCallback.C3464a) null);
    }

    /* renamed from: a */
    public static MessageSnapshot m19149a(int i, File file, boolean z) {
        long length = file.length();
        if (length > 2147483647L) {
            if (z) {
                return new LargeMessageSnapshot.CompletedFlowDirectlySnapshot(i, true, length);
            }
            return new LargeMessageSnapshot.CompletedSnapshot(i, true, length);
        } else if (z) {
            return new SmallMessageSnapshot.CompletedFlowDirectlySnapshot(i, true, (int) length);
        } else {
            return new SmallMessageSnapshot.CompletedSnapshot(i, true, (int) length);
        }
    }

    /* renamed from: a */
    public static MessageSnapshot m19151a(int i, long j, long j2, boolean z) {
        if (j2 > 2147483647L) {
            if (z) {
                return new LargeMessageSnapshot.WarnFlowDirectlySnapshot(i, j, j2);
            }
            return new LargeMessageSnapshot.WarnMessageSnapshot(i, j, j2);
        } else if (z) {
            return new SmallMessageSnapshot.WarnFlowDirectlySnapshot(i, (int) j, (int) j2);
        } else {
            return new SmallMessageSnapshot.WarnMessageSnapshot(i, (int) j, (int) j2);
        }
    }

    /* renamed from: a */
    public static MessageSnapshot m19150a(int i, long j, Throwable th) {
        if (j > 2147483647L) {
            return new LargeMessageSnapshot.ErrorMessageSnapshot(i, j, th);
        }
        return new SmallMessageSnapshot.ErrorMessageSnapshot(i, (int) j, th);
    }

    /* renamed from: a */
    public static MessageSnapshot m19147a(BaseDownloadTask afaVar) {
        if (afaVar.mo13803N()) {
            return new LargeMessageSnapshot.PausedSnapshot(afaVar.mo13754k(), afaVar.mo13742w(), afaVar.mo13739z());
        }
        return new SmallMessageSnapshot.PausedSnapshot(afaVar.mo13754k(), afaVar.mo13743v(), afaVar.mo13740y());
    }

    /* renamed from: a */
    public static MessageSnapshot m19148a(MessageSnapshot messageSnapshot) {
        if (messageSnapshot.mo19170b() == -3) {
            return new BlockCompleteMessage.BlockCompleteMessageImpl(messageSnapshot);
        }
        throw new IllegalStateException(FileDownloadUtils.m13182a("take block completed snapshot, must has already be completed. %d %d", Integer.valueOf(messageSnapshot.mo19169c()), Byte.valueOf(messageSnapshot.mo19170b())));
    }

    /* renamed from: a */
    public static MessageSnapshot m19152a(byte b, FileDownloadModel fileDownloadModel, DownloadStatusCallback.C3464a aVar) {
        IllegalStateException illegalStateException;
        int a = fileDownloadModel.m19135a();
        if (b != -4) {
            switch (b) {
                case -3:
                    if (fileDownloadModel.m19108q()) {
                        return new LargeMessageSnapshot.CompletedSnapshot(a, false, fileDownloadModel.m19117h());
                    }
                    return new SmallMessageSnapshot.CompletedSnapshot(a, false, (int) fileDownloadModel.m19117h());
                case -2:
                case 0:
                case 4:
                default:
                    String a2 = FileDownloadUtils.m13182a("it can't takes a snapshot for the task(%s) when its status is %d,", fileDownloadModel, Byte.valueOf(b));
                    FileDownloadLog.m13210d(MessageSnapshotTaker.class, "it can't takes a snapshot for the task(%s) when its status is %d,", fileDownloadModel, Byte.valueOf(b));
                    if (aVar.m13330b() != null) {
                        illegalStateException = new IllegalStateException(a2, aVar.m13330b());
                    } else {
                        illegalStateException = new IllegalStateException(a2);
                    }
                    if (fileDownloadModel.m19108q()) {
                        return new LargeMessageSnapshot.ErrorMessageSnapshot(a, fileDownloadModel.m19118g(), illegalStateException);
                    }
                    return new SmallMessageSnapshot.ErrorMessageSnapshot(a, (int) fileDownloadModel.m19118g(), illegalStateException);
                case -1:
                    if (fileDownloadModel.m19108q()) {
                        return new LargeMessageSnapshot.ErrorMessageSnapshot(a, fileDownloadModel.m19118g(), aVar.m13330b());
                    }
                    return new SmallMessageSnapshot.ErrorMessageSnapshot(a, (int) fileDownloadModel.m19118g(), aVar.m13330b());
                case 1:
                    if (fileDownloadModel.m19108q()) {
                        return new LargeMessageSnapshot.PendingMessageSnapshot(a, fileDownloadModel.m19118g(), fileDownloadModel.m19117h());
                    }
                    return new SmallMessageSnapshot.PendingMessageSnapshot(a, (int) fileDownloadModel.m19118g(), (int) fileDownloadModel.m19117h());
                case 2:
                    String m = fileDownloadModel.m19113l() ? fileDownloadModel.m19112m() : null;
                    if (fileDownloadModel.m19108q()) {
                        return new LargeMessageSnapshot.ConnectedMessageSnapshot(a, aVar.m13334a(), fileDownloadModel.m19117h(), fileDownloadModel.m19115j(), m);
                    }
                    return new SmallMessageSnapshot.ConnectedMessageSnapshot(a, aVar.m13334a(), (int) fileDownloadModel.m19117h(), fileDownloadModel.m19115j(), m);
                case 3:
                    if (fileDownloadModel.m19108q()) {
                        return new LargeMessageSnapshot.ProgressMessageSnapshot(a, fileDownloadModel.m19118g());
                    }
                    return new SmallMessageSnapshot.ProgressMessageSnapshot(a, (int) fileDownloadModel.m19118g());
                case 5:
                    if (fileDownloadModel.m19108q()) {
                        return new LargeMessageSnapshot.RetryMessageSnapshot(a, fileDownloadModel.m19118g(), aVar.m13330b(), aVar.m13329c());
                    }
                    return new SmallMessageSnapshot.RetryMessageSnapshot(a, (int) fileDownloadModel.m19118g(), aVar.m13330b(), aVar.m13329c());
                case 6:
                    return new MessageSnapshot.StartedMessageSnapshot(a);
            }
        } else {
            throw new IllegalStateException(FileDownloadUtils.m13182a("please use #catchWarn instead %d", Integer.valueOf(a)));
        }
    }
}
