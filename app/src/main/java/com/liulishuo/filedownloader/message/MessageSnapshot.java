package com.liulishuo.filedownloader.message;

import android.os.Parcel;
import android.os.Parcelable;
import com.liulishuo.filedownloader.message.LargeMessageSnapshot;
import com.liulishuo.filedownloader.message.SmallMessageSnapshot;
import p110z1.FileDownloadUtils;

/* loaded from: classes.dex */
public abstract class MessageSnapshot implements Parcelable, IMessageSnapshot {
    public static final Parcelable.Creator<MessageSnapshot> CREATOR = new Parcelable.Creator<MessageSnapshot>() { // from class: com.liulishuo.filedownloader.message.MessageSnapshot.1
        /* renamed from: a */
        public MessageSnapshot createFromParcel(Parcel parcel) {
            MessageSnapshot messageSnapshot;
            boolean z = true;
            if (parcel.readByte() != 1) {
                z = false;
            }
            byte readByte = parcel.readByte();
            switch (readByte) {
                case -4:
                    if (!z) {
                        messageSnapshot = new SmallMessageSnapshot.WarnMessageSnapshot(parcel);
                        break;
                    } else {
                        messageSnapshot = new LargeMessageSnapshot.WarnMessageSnapshot(parcel);
                        break;
                    }
                case -3:
                    if (!z) {
                        messageSnapshot = new SmallMessageSnapshot.CompletedSnapshot(parcel);
                        break;
                    } else {
                        messageSnapshot = new LargeMessageSnapshot.CompletedSnapshot(parcel);
                        break;
                    }
                case -2:
                case 0:
                case 4:
                default:
                    messageSnapshot = null;
                    break;
                case -1:
                    if (!z) {
                        messageSnapshot = new SmallMessageSnapshot.ErrorMessageSnapshot(parcel);
                        break;
                    } else {
                        messageSnapshot = new LargeMessageSnapshot.ErrorMessageSnapshot(parcel);
                        break;
                    }
                case 1:
                    if (!z) {
                        messageSnapshot = new SmallMessageSnapshot.PendingMessageSnapshot(parcel);
                        break;
                    } else {
                        messageSnapshot = new LargeMessageSnapshot.PendingMessageSnapshot(parcel);
                        break;
                    }
                case 2:
                    if (!z) {
                        messageSnapshot = new SmallMessageSnapshot.ConnectedMessageSnapshot(parcel);
                        break;
                    } else {
                        messageSnapshot = new LargeMessageSnapshot.ConnectedMessageSnapshot(parcel);
                        break;
                    }
                case 3:
                    if (!z) {
                        messageSnapshot = new SmallMessageSnapshot.ProgressMessageSnapshot(parcel);
                        break;
                    } else {
                        messageSnapshot = new LargeMessageSnapshot.ProgressMessageSnapshot(parcel);
                        break;
                    }
                case 5:
                    if (!z) {
                        messageSnapshot = new SmallMessageSnapshot.RetryMessageSnapshot(parcel);
                        break;
                    } else {
                        messageSnapshot = new LargeMessageSnapshot.RetryMessageSnapshot(parcel);
                        break;
                    }
                case 6:
                    messageSnapshot = new StartedMessageSnapshot(parcel);
                    break;
            }
            if (messageSnapshot != null) {
                messageSnapshot.f10335a = z;
                return messageSnapshot;
            }
            throw new IllegalStateException("Can't restore the snapshot because unknown status: " + ((int) readByte));
        }

        /* renamed from: a */
        public MessageSnapshot[] newArray(int i) {
            return new MessageSnapshot[i];
        }
    };

    /* renamed from: a */
    protected boolean f10335a;

    /* renamed from: b */
    private final int f10336b;

    /* renamed from: com.liulishuo.filedownloader.message.MessageSnapshot$a */
    /* loaded from: classes.dex */
    public interface AbstractC1690a {
        /* renamed from: a */
        MessageSnapshot mo19171a();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageSnapshot(int i) {
        this.f10336b = i;
    }

    @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
    /* renamed from: c */
    public int mo19169c() {
        return this.f10336b;
    }

    /* renamed from: d */
    public Throwable mo19168d() {
        throw new C1691b("getThrowable", this);
    }

    /* renamed from: e */
    public int mo19167e() {
        throw new C1691b("getRetryingTimes", this);
    }

    /* renamed from: f */
    public boolean mo19166f() {
        throw new C1691b("isResuming", this);
    }

    /* renamed from: g */
    public String mo19165g() {
        throw new C1691b("getEtag", this);
    }

    /* renamed from: h */
    public long mo19164h() {
        throw new C1691b("getLargeSofarBytes", this);
    }

    /* renamed from: i */
    public long mo19163i() {
        throw new C1691b("getLargeTotalBytes", this);
    }

    /* renamed from: j */
    public int mo19162j() {
        throw new C1691b("getSmallSofarBytes", this);
    }

    /* renamed from: k */
    public int mo19161k() {
        throw new C1691b("getSmallTotalBytes", this);
    }

    /* renamed from: l */
    public boolean mo19160l() {
        throw new C1691b("isReusedDownloadedFile", this);
    }

    /* renamed from: n */
    public String mo19158n() {
        throw new C1691b("getFileName", this);
    }

    @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
    /* renamed from: m */
    public boolean mo19159m() {
        return this.f10335a;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.f10335a ? (byte) 1 : (byte) 0);
        parcel.writeByte(mo19170b());
        parcel.writeInt(this.f10336b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageSnapshot(Parcel parcel) {
        this.f10336b = parcel.readInt();
    }

    /* renamed from: com.liulishuo.filedownloader.message.MessageSnapshot$b */
    /* loaded from: classes.dex */
    public static class C1691b extends IllegalStateException {
        C1691b(String str, MessageSnapshot messageSnapshot) {
            super(FileDownloadUtils.m13182a("There isn't a field for '%s' in this message %d %d %s", str, Integer.valueOf(messageSnapshot.mo19169c()), Byte.valueOf(messageSnapshot.mo19170b()), messageSnapshot.getClass().getName()));
        }
    }

    /* loaded from: classes.dex */
    public static class StartedMessageSnapshot extends MessageSnapshot {
        @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: b */
        public byte mo19170b() {
            return (byte) 6;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public StartedMessageSnapshot(int i) {
            super(i);
        }

        StartedMessageSnapshot(Parcel parcel) {
            super(parcel);
        }
    }
}
