package com.lody.virtual.server.interfaces;

import android.app.job.JobInfo;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.lody.virtual.remote.VJobWorkItem;
import java.util.List;

/* loaded from: classes.dex */
public interface IJobService extends IInterface {
    void cancel(int i, int i2) throws RemoteException;

    void cancelAll(int i) throws RemoteException;

    int enqueue(int i, JobInfo jobInfo, VJobWorkItem vJobWorkItem) throws RemoteException;

    List<JobInfo> getAllPendingJobs(int i) throws RemoteException;

    JobInfo getPendingJob(int i, int i2) throws RemoteException;

    int schedule(int i, JobInfo jobInfo) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IJobService {
        private static final String DESCRIPTOR = "com.lody.virtual.server.interfaces.IJobService";
        static final int TRANSACTION_cancel = 2;
        static final int TRANSACTION_cancelAll = 3;
        static final int TRANSACTION_enqueue = 6;
        static final int TRANSACTION_getAllPendingJobs = 4;
        static final int TRANSACTION_getPendingJob = 5;
        static final int TRANSACTION_schedule = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IJobService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IJobService)) {
                return new Proxy(iBinder);
            }
            return (IJobService) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                JobInfo jobInfo = null;
                VJobWorkItem vJobWorkItem = null;
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        int readInt = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            jobInfo = (JobInfo) JobInfo.CREATOR.createFromParcel(parcel);
                        }
                        int schedule = schedule(readInt, jobInfo);
                        parcel2.writeNoException();
                        parcel2.writeInt(schedule);
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        cancel(parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        cancelAll(parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        List<JobInfo> allPendingJobs = getAllPendingJobs(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeTypedList(allPendingJobs);
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        JobInfo pendingJob = getPendingJob(parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        if (pendingJob != null) {
                            parcel2.writeInt(1);
                            pendingJob.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        int readInt2 = parcel.readInt();
                        JobInfo jobInfo2 = parcel.readInt() != 0 ? (JobInfo) JobInfo.CREATOR.createFromParcel(parcel) : null;
                        if (parcel.readInt() != 0) {
                            vJobWorkItem = VJobWorkItem.CREATOR.createFromParcel(parcel);
                        }
                        int enqueue = enqueue(readInt2, jobInfo2, vJobWorkItem);
                        parcel2.writeNoException();
                        parcel2.writeInt(enqueue);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* loaded from: classes.dex */
        private static class Proxy implements IJobService {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.lody.virtual.server.interfaces.IJobService
            public int schedule(int i, JobInfo jobInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (jobInfo != null) {
                        obtain.writeInt(1);
                        jobInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IJobService
            public void cancel(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IJobService
            public void cancelAll(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IJobService
            public List<JobInfo> getAllPendingJobs(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(JobInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IJobService
            public JobInfo getPendingJob(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (JobInfo) JobInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IJobService
            public int enqueue(int i, JobInfo jobInfo, VJobWorkItem vJobWorkItem) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (jobInfo != null) {
                        obtain.writeInt(1);
                        jobInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (vJobWorkItem != null) {
                        obtain.writeInt(1);
                        vJobWorkItem.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
