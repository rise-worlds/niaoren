package com.lody.virtual.remote;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class VParceledListSlice<T extends Parcelable> implements Parcelable {
    private static final int MAX_FIRST_IPC_SIZE = 131072;
    private static final int MAX_IPC_SIZE = 262144;
    private final List<T> mList;
    public static final Parcelable.ClassLoaderCreator<VParceledListSlice> CREATOR = new Parcelable.ClassLoaderCreator<VParceledListSlice>() { // from class: com.lody.virtual.remote.VParceledListSlice.1
        @Override // android.os.Parcelable.Creator
        public VParceledListSlice createFromParcel(Parcel parcel) {
            return new VParceledListSlice(parcel, null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.ClassLoaderCreator
        public VParceledListSlice createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new VParceledListSlice(parcel, classLoader);
        }

        @Override // android.os.Parcelable.Creator
        public VParceledListSlice[] newArray(int i) {
            return new VParceledListSlice[i];
        }
    };
    private static String TAG = "ParceledListSlice";
    private static boolean DEBUG = false;

    public VParceledListSlice(List<T> list) {
        this.mList = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private VParceledListSlice(Parcel parcel, ClassLoader classLoader) {
        classLoader = classLoader == null ? VParceledListSlice.class.getClassLoader() : classLoader;
        int readInt = parcel.readInt();
        this.mList = new ArrayList(readInt);
        if (DEBUG) {
            String str = TAG;
            Log.d(str, "Retrieving " + readInt + " items");
        }
        if (readInt > 0) {
            Class<?> cls = null;
            int i = 0;
            while (i < readInt && parcel.readInt() != 0) {
                Parcelable readParcelable = parcel.readParcelable(classLoader);
                if (cls == null) {
                    cls = readParcelable.getClass();
                } else if (readParcelable != null) {
                    verifySameType(cls, readParcelable.getClass());
                }
                this.mList.add(readParcelable);
                if (DEBUG) {
                    String str2 = TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Read inline #");
                    sb.append(i);
                    sb.append(": ");
                    List<T> list = this.mList;
                    sb.append(list.get(list.size() - 1));
                    Log.d(str2, sb.toString());
                }
                i++;
            }
            if (i < readInt) {
                IBinder readStrongBinder = parcel.readStrongBinder();
                while (i < readInt) {
                    if (DEBUG) {
                        String str3 = TAG;
                        Log.d(str3, "Reading more @" + i + " of " + readInt + ": retriever=" + readStrongBinder);
                    }
                    Parcel obtain = Parcel.obtain();
                    Parcel obtain2 = Parcel.obtain();
                    obtain.writeInt(i);
                    try {
                        readStrongBinder.transact(1, obtain, obtain2, 0);
                        while (i < readInt && obtain2.readInt() != 0) {
                            Parcelable readParcelable2 = obtain2.readParcelable(classLoader);
                            if (readParcelable2 != null) {
                                verifySameType(cls, readParcelable2.getClass());
                            }
                            this.mList.add(readParcelable2);
                            if (DEBUG) {
                                String str4 = TAG;
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("Read extra #");
                                sb2.append(i);
                                sb2.append(": ");
                                List<T> list2 = this.mList;
                                sb2.append(list2.get(list2.size() - 1));
                                Log.d(str4, sb2.toString());
                            }
                            i++;
                        }
                        obtain2.recycle();
                        obtain.recycle();
                    } catch (RemoteException e) {
                        String str5 = TAG;
                        Log.w(str5, "Failure retrieving array; only received " + i + " of " + readInt, e);
                        return;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void verifySameType(Class<?> cls, Class<?> cls2) {
        if (!cls2.equals(cls)) {
            throw new IllegalArgumentException("Can't unparcel type " + cls2.getName() + " in list of type " + cls.getName());
        }
    }

    public List<T> getList() {
        return this.mList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        int i = 0;
        for (int i2 = 0; i2 < this.mList.size(); i2++) {
            i |= this.mList.get(i2).describeContents();
        }
        return i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, final int i) {
        final int size = this.mList.size();
        parcel.writeInt(size);
        if (DEBUG) {
            String str = TAG;
            Log.d(str, "Writing " + size + " items");
        }
        if (size > 0) {
            final Class<?> cls = this.mList.get(0).getClass();
            int i2 = 0;
            while (i2 < size && parcel.dataSize() < 131072) {
                parcel.writeInt(1);
                T t = this.mList.get(i2);
                if (t == null) {
                    parcel.writeString(null);
                } else {
                    verifySameType(cls, t.getClass());
                    parcel.writeParcelable(t, i);
                }
                if (DEBUG) {
                    String str2 = TAG;
                    Log.d(str2, "Wrote inline #" + i2 + ": " + this.mList.get(i2));
                }
                i2++;
            }
            if (i2 < size) {
                parcel.writeInt(0);
                Binder binder = new Binder() { // from class: com.lody.virtual.remote.VParceledListSlice.2
                    @Override // android.os.Binder
                    protected boolean onTransact(int i3, Parcel parcel2, Parcel parcel3, int i4) throws RemoteException {
                        if (i3 != 1) {
                            return super.onTransact(i3, parcel2, parcel3, i4);
                        }
                        int readInt = parcel2.readInt();
                        if (VParceledListSlice.DEBUG) {
                            String str3 = VParceledListSlice.TAG;
                            Log.d(str3, "Writing more @" + readInt + " of " + size);
                        }
                        while (readInt < size && parcel3.dataSize() < 262144) {
                            parcel3.writeInt(1);
                            Parcelable parcelable = (Parcelable) VParceledListSlice.this.mList.get(readInt);
                            VParceledListSlice.verifySameType(cls, parcelable.getClass());
                            parcel3.writeParcelable(parcelable, i);
                            if (VParceledListSlice.DEBUG) {
                                String str4 = VParceledListSlice.TAG;
                                Log.d(str4, "Wrote extra #" + readInt + ": " + VParceledListSlice.this.mList.get(readInt));
                            }
                            readInt++;
                        }
                        if (readInt < size) {
                            if (VParceledListSlice.DEBUG) {
                                String str5 = VParceledListSlice.TAG;
                                Log.d(str5, "Breaking @" + readInt + " of " + size);
                            }
                            parcel3.writeInt(0);
                        }
                        return true;
                    }
                };
                if (DEBUG) {
                    String str3 = TAG;
                    Log.d(str3, "Breaking @" + i2 + " of " + size + ": retriever=" + binder);
                }
                parcel.writeStrongBinder(binder);
            }
        }
    }
}
