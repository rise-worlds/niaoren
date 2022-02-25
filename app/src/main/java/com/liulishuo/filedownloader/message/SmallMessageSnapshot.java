package com.liulishuo.filedownloader.message;

import android.os.Parcel;
import com.liulishuo.filedownloader.message.MessageSnapshot;

/* loaded from: classes.dex */
public abstract class SmallMessageSnapshot extends MessageSnapshot {
    SmallMessageSnapshot(int i) {
        super(i);
        this.f10335a = false;
    }

    SmallMessageSnapshot(Parcel parcel) {
        super(parcel);
    }

    @Override // com.liulishuo.filedownloader.message.MessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
    /* renamed from: i */
    public long mo19163i() {
        return mo19161k();
    }

    @Override // com.liulishuo.filedownloader.message.MessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
    /* renamed from: h */
    public long mo19164h() {
        return mo19162j();
    }

    /* loaded from: classes.dex */
    public static class PendingMessageSnapshot extends SmallMessageSnapshot {

        /* renamed from: b */
        private final int f10345b;

        /* renamed from: c */
        private final int f10346c;

        /* renamed from: b */
        public byte mo19170b() {
            return (byte) 1;
        }

        PendingMessageSnapshot(PendingMessageSnapshot pendingMessageSnapshot) {
            this(pendingMessageSnapshot.mo19169c(), pendingMessageSnapshot.mo19162j(), pendingMessageSnapshot.mo19161k());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public PendingMessageSnapshot(int i, int i2, int i3) {
            super(i);
            this.f10345b = i2;
            this.f10346c = i3;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public PendingMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.f10345b = parcel.readInt();
            this.f10346c = parcel.readInt();
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            SmallMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeInt(this.f10345b);
            parcel.writeInt(this.f10346c);
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: j */
        public int mo19162j() {
            return this.f10345b;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: k */
        public int mo19161k() {
            return this.f10346c;
        }
    }

    /* loaded from: classes.dex */
    public static class ConnectedMessageSnapshot extends SmallMessageSnapshot {

        /* renamed from: b */
        private final boolean f10339b;

        /* renamed from: c */
        private final int f10340c;

        /* renamed from: d */
        private final String f10341d;

        /* renamed from: e */
        private final String f10342e;

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
        public ConnectedMessageSnapshot(int i, boolean z, int i2, String str, String str2) {
            super(i);
            this.f10339b = z;
            this.f10340c = i2;
            this.f10341d = str;
            this.f10342e = str2;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            SmallMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeByte(this.f10339b ? (byte) 1 : (byte) 0);
            parcel.writeInt(this.f10340c);
            parcel.writeString(this.f10341d);
            parcel.writeString(this.f10342e);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public ConnectedMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.f10339b = parcel.readByte() != 0;
            this.f10340c = parcel.readInt();
            this.f10341d = parcel.readString();
            this.f10342e = parcel.readString();
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: n */
        public String mo19158n() {
            return this.f10342e;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: f */
        public boolean mo19166f() {
            return this.f10339b;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: k */
        public int mo19161k() {
            return this.f10340c;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: g */
        public String mo19165g() {
            return this.f10341d;
        }
    }

    /* loaded from: classes.dex */
    public static class ProgressMessageSnapshot extends SmallMessageSnapshot {

        /* renamed from: b */
        private final int f10347b;

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
        public ProgressMessageSnapshot(int i, int i2) {
            super(i);
            this.f10347b = i2;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: j */
        public int mo19162j() {
            return this.f10347b;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            SmallMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeInt(this.f10347b);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public ProgressMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.f10347b = parcel.readInt();
        }
    }

    /* loaded from: classes.dex */
    public static class CompletedFlowDirectlySnapshot extends CompletedSnapshot implements IFlowDirectly {
        /* JADX INFO: Access modifiers changed from: package-private */
        public CompletedFlowDirectlySnapshot(int i, boolean z, int i2) {
            super(i, z, i2);
        }

        CompletedFlowDirectlySnapshot(Parcel parcel) {
            super(parcel);
        }
    }

    /* loaded from: classes.dex */
    public static class CompletedSnapshot extends SmallMessageSnapshot {

        /* renamed from: b */
        private final boolean f10337b;

        /* renamed from: c */
        private final int f10338c;

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
        public CompletedSnapshot(int i, boolean z, int i2) {
            super(i);
            this.f10337b = z;
            this.f10338c = i2;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            SmallMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeByte(this.f10337b ? (byte) 1 : (byte) 0);
            parcel.writeInt(this.f10338c);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public CompletedSnapshot(Parcel parcel) {
            super(parcel);
            this.f10337b = parcel.readByte() != 0;
            this.f10338c = parcel.readInt();
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: k */
        public int mo19161k() {
            return this.f10338c;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: l */
        public boolean mo19160l() {
            return this.f10337b;
        }
    }

    /* loaded from: classes.dex */
    public static class ErrorMessageSnapshot extends SmallMessageSnapshot {

        /* renamed from: b */
        private final int f10343b;

        /* renamed from: c */
        private final Throwable f10344c;

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
        public ErrorMessageSnapshot(int i, int i2, Throwable th) {
            super(i);
            this.f10343b = i2;
            this.f10344c = th;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: j */
        public int mo19162j() {
            return this.f10343b;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: d */
        public Throwable mo19168d() {
            return this.f10344c;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            SmallMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeInt(this.f10343b);
            parcel.writeSerializable(this.f10344c);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public ErrorMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.f10343b = parcel.readInt();
            this.f10344c = (Throwable) parcel.readSerializable();
        }
    }

    /* loaded from: classes.dex */
    public static class RetryMessageSnapshot extends ErrorMessageSnapshot {

        /* renamed from: b */
        private final int f10348b;

        @Override // com.liulishuo.filedownloader.message.SmallMessageSnapshot.ErrorMessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: b */
        public byte mo19170b() {
            return (byte) 5;
        }

        @Override // com.liulishuo.filedownloader.message.SmallMessageSnapshot.ErrorMessageSnapshot, com.liulishuo.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public RetryMessageSnapshot(int i, int i2, Throwable th, int i3) {
            super(i, i2, th);
            this.f10348b = i3;
        }

        @Override // com.liulishuo.filedownloader.message.MessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: e */
        public int mo19167e() {
            return this.f10348b;
        }

        @Override // com.liulishuo.filedownloader.message.SmallMessageSnapshot.ErrorMessageSnapshot, com.liulishuo.filedownloader.message.MessageSnapshot, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f10348b);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public RetryMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.f10348b = parcel.readInt();
        }
    }

    /* loaded from: classes.dex */
    public static class WarnFlowDirectlySnapshot extends WarnMessageSnapshot implements IFlowDirectly {
        /* JADX INFO: Access modifiers changed from: package-private */
        public WarnFlowDirectlySnapshot(int i, int i2, int i3) {
            super(i, i2, i3);
        }

        WarnFlowDirectlySnapshot(Parcel parcel) {
            super(parcel);
        }
    }

    /* loaded from: classes.dex */
    public static class WarnMessageSnapshot extends PendingMessageSnapshot implements MessageSnapshot.AbstractC1690a {
        @Override // com.liulishuo.filedownloader.message.SmallMessageSnapshot.PendingMessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: b */
        public byte mo19170b() {
            return (byte) -4;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public WarnMessageSnapshot(int i, int i2, int i3) {
            super(i, i2, i3);
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
        @Override // com.liulishuo.filedownloader.message.SmallMessageSnapshot.PendingMessageSnapshot, com.liulishuo.filedownloader.message.IMessageSnapshot
        /* renamed from: b */
        public byte mo19170b() {
            return (byte) -2;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public PausedSnapshot(int i, int i2, int i3) {
            super(i, i2, i3);
        }
    }
}
