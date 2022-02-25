package com.liulishuo.filedownloader.message;

import android.os.Parcel;
import com.liulishuo.filedownloader.message.MessageSnapshot;

/* loaded from: classes.dex */
public abstract class LargeMessageSnapshot extends MessageSnapshot {
    LargeMessageSnapshot(int i) {
        super(i);
        this.f10335a = true;
    }

    LargeMessageSnapshot(Parcel parcel) {
        super(parcel);
    }

    @Override // com.liulishuo.filedownloader.message.MessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
    /* renamed from: j */
    public int mo19162j() {
        if (mo19164h() > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) mo19164h();
    }

    @Override // com.liulishuo.filedownloader.message.MessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
    /* renamed from: k */
    public int mo19161k() {
        if (mo19163i() > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) mo19163i();
    }

    /* loaded from: classes.dex */
    public static class PendingMessageSnapshot extends LargeMessageSnapshot {

        /* renamed from: b */
        private final long f10331b;

        /* renamed from: c */
        private final long f10332c;

        /* renamed from: b */
        public byte mo19170b() {
            return (byte) 1;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        PendingMessageSnapshot(PendingMessageSnapshot pendingMessageSnapshot) {
            this(pendingMessageSnapshot.mo19169c(), pendingMessageSnapshot.mo19164h(), pendingMessageSnapshot.mo19163i());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public PendingMessageSnapshot(int i, long j, long j2) {
            super(i);
            this.f10331b = j;
            this.f10332c = j2;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: h */
        public long mo19164h() {
            return this.f10331b;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: i */
        public long mo19163i() {
            return this.f10332c;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            LargeMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeLong(this.f10331b);
            parcel.writeLong(this.f10332c);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public PendingMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.f10331b = parcel.readLong();
            this.f10332c = parcel.readLong();
        }
    }

    /* loaded from: classes.dex */
    public static class ConnectedMessageSnapshot extends LargeMessageSnapshot {

        /* renamed from: b */
        private final boolean f10325b;

        /* renamed from: c */
        private final long f10326c;

        /* renamed from: d */
        private final String f10327d;

        /* renamed from: e */
        private final String f10328e;

        @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: b */
        public byte mo19170b() {
            return (byte) 2;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public ConnectedMessageSnapshot(int i, boolean z, long j, String str, String str2) {
            super(i);
            this.f10325b = z;
            this.f10326c = j;
            this.f10327d = str;
            this.f10328e = str2;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            LargeMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeByte(this.f10325b ? (byte) 1 : (byte) 0);
            parcel.writeLong(this.f10326c);
            parcel.writeString(this.f10327d);
            parcel.writeString(this.f10328e);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public ConnectedMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.f10325b = parcel.readByte() != 0;
            this.f10326c = parcel.readLong();
            this.f10327d = parcel.readString();
            this.f10328e = parcel.readString();
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: n */
        public String mo19158n() {
            return this.f10328e;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: f */
        public boolean mo19166f() {
            return this.f10325b;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: i */
        public long mo19163i() {
            return this.f10326c;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: g */
        public String mo19165g() {
            return this.f10327d;
        }
    }

    /* loaded from: classes.dex */
    public static class ProgressMessageSnapshot extends LargeMessageSnapshot {

        /* renamed from: b */
        private final long f10333b;

        @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: b */
        public byte mo19170b() {
            return (byte) 3;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public ProgressMessageSnapshot(int i, long j) {
            super(i);
            this.f10333b = j;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: h */
        public long mo19164h() {
            return this.f10333b;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            LargeMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeLong(this.f10333b);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public ProgressMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.f10333b = parcel.readLong();
        }
    }

    /* loaded from: classes.dex */
    public static class CompletedFlowDirectlySnapshot extends CompletedSnapshot implements IFlowDirectly {
        /* JADX INFO: Access modifiers changed from: package-private */
        public CompletedFlowDirectlySnapshot(int i, boolean z, long j) {
            super(i, z, j);
        }

        CompletedFlowDirectlySnapshot(Parcel parcel) {
            super(parcel);
        }
    }

    /* loaded from: classes.dex */
    public static class CompletedSnapshot extends LargeMessageSnapshot {

        /* renamed from: b */
        private final boolean f10323b;

        /* renamed from: c */
        private final long f10324c;

        @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: b */
        public byte mo19170b() {
            return (byte) -3;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public CompletedSnapshot(int i, boolean z, long j) {
            super(i);
            this.f10323b = z;
            this.f10324c = j;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            LargeMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeByte(this.f10323b ? (byte) 1 : (byte) 0);
            parcel.writeLong(this.f10324c);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public CompletedSnapshot(Parcel parcel) {
            super(parcel);
            this.f10323b = parcel.readByte() != 0;
            this.f10324c = parcel.readLong();
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: i */
        public long mo19163i() {
            return this.f10324c;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: l */
        public boolean mo19160l() {
            return this.f10323b;
        }
    }

    /* loaded from: classes.dex */
    public static class ErrorMessageSnapshot extends LargeMessageSnapshot {

        /* renamed from: b */
        private final long f10329b;

        /* renamed from: c */
        private final Throwable f10330c;

        @Override // com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: b */
        public byte mo19170b() {
            return (byte) -1;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public ErrorMessageSnapshot(int i, long j, Throwable th) {
            super(i);
            this.f10329b = j;
            this.f10330c = th;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: h */
        public long mo19164h() {
            return this.f10329b;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: d */
        public Throwable mo19168d() {
            return this.f10330c;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            LargeMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeLong(this.f10329b);
            parcel.writeSerializable(this.f10330c);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public ErrorMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.f10329b = parcel.readLong();
            this.f10330c = (Throwable) parcel.readSerializable();
        }
    }

    /* loaded from: classes.dex */
    public static class RetryMessageSnapshot extends ErrorMessageSnapshot {

        /* renamed from: b */
        private final int f10334b;

        @Override // com.liulishuo.filedownloader.message.LargeMessageSnapshot.ErrorMessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: b */
        public byte mo19170b() {
            return (byte) 5;
        }

        @Override // com.liulishuo.filedownloader.message.LargeMessageSnapshot.ErrorMessageSnapshot, com.liulishuo.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public RetryMessageSnapshot(int i, long j, Throwable th, int i2) {
            super(i, j, th);
            this.f10334b = i2;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: e */
        public int mo19167e() {
            return this.f10334b;
        }

        @Override // com.liulishuo.filedownloader.message.LargeMessageSnapshot.ErrorMessageSnapshot, com.liulishuo.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f10334b);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public RetryMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.f10334b = parcel.readInt();
        }
    }

    /* loaded from: classes.dex */
    public static class WarnFlowDirectlySnapshot extends WarnMessageSnapshot implements IFlowDirectly {
        /* JADX INFO: Access modifiers changed from: package-private */
        public WarnFlowDirectlySnapshot(int i, long j, long j2) {
            super(i, j, j2);
        }

        WarnFlowDirectlySnapshot(Parcel parcel) {
            super(parcel);
        }
    }

    /* loaded from: classes.dex */
    public static class WarnMessageSnapshot extends PendingMessageSnapshot implements MessageSnapshot.AbstractC1690a {
        @Override // com.liulishuo.filedownloader.message.LargeMessageSnapshot.PendingMessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: b */
        public byte mo19170b() {
            return (byte) -4;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public WarnMessageSnapshot(int i, long j, long j2) {
            super(i, j, j2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public WarnMessageSnapshot(Parcel parcel) {
            super(parcel);
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot.AbstractC1690a
        /* renamed from: a */
        public MessageSnapshot mo19171a() {
            return new PendingMessageSnapshot(this);
        }
    }

    /* loaded from: classes.dex */
    public static class PausedSnapshot extends PendingMessageSnapshot {
        @Override // com.liulishuo.filedownloader.message.LargeMessageSnapshot.PendingMessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: b */
        public byte mo19170b() {
            return (byte) -2;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public PausedSnapshot(int i, long j, long j2) {
            super(i, j, j2);
        }
    }
}
