package p110z1;

import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.os.IBinder;
import mirror.MethodParams;
import mirror.RefBoolean;
import mirror.RefClass;
import mirror.RefConstructor;
import mirror.RefInt;
import mirror.RefMethod;
import mirror.RefObject;

/* renamed from: z1.ctg */
/* loaded from: classes3.dex */
public class BroadcastReceiver {
    public static Class<?> TYPE = RefClass.load(BroadcastReceiver.class, android.content.BroadcastReceiver.class);
    public static RefMethod<BroadcastReceiver.PendingResult> getPendingResult;
    @MethodParams({BroadcastReceiver.PendingResult.class})
    public static RefMethod<Void> setPendingResult;

    /* compiled from: BroadcastReceiver.java */
    /* renamed from: z1.ctg$a */
    /* loaded from: classes3.dex */
    public static class C5132a {
        public static Class<?> TYPE = RefClass.load(C5132a.class, BroadcastReceiver.PendingResult.class);
        @MethodParams({int.class, String.class, Bundle.class, int.class, boolean.class, boolean.class, IBinder.class})
        public static RefConstructor<BroadcastReceiver.PendingResult> ctor;
        public static RefBoolean mAbortBroadcast;
        public static RefBoolean mFinished;
        public static RefBoolean mInitialStickyHint;
        public static RefBoolean mOrderedHint;
        public static RefInt mResultCode;
        public static RefObject<String> mResultData;
        public static RefObject<Bundle> mResultExtras;
        public static RefObject<IBinder> mToken;
        public static RefInt mType;
    }

    /* compiled from: BroadcastReceiver.java */
    /* renamed from: z1.ctg$b */
    /* loaded from: classes3.dex */
    public static class C5133b {
        public static Class<?> TYPE = RefClass.load(C5133b.class, BroadcastReceiver.PendingResult.class);
        @MethodParams({int.class, String.class, Bundle.class, int.class, boolean.class, boolean.class, IBinder.class, int.class})
        public static RefConstructor<BroadcastReceiver.PendingResult> ctor;
        public static RefBoolean mAbortBroadcast;
        public static RefBoolean mFinished;
        public static RefBoolean mInitialStickyHint;
        public static RefBoolean mOrderedHint;
        public static RefInt mResultCode;
        public static RefObject<String> mResultData;
        public static RefObject<Bundle> mResultExtras;
        public static RefInt mSendingUser;
        public static RefObject<IBinder> mToken;
        public static RefInt mType;
    }

    /* compiled from: BroadcastReceiver.java */
    /* renamed from: z1.ctg$c */
    /* loaded from: classes3.dex */
    public static class C5134c {
        public static Class<?> TYPE = RefClass.load(C5134c.class, BroadcastReceiver.PendingResult.class);
        @MethodParams({int.class, String.class, Bundle.class, int.class, boolean.class, boolean.class, IBinder.class, int.class, int.class})
        public static RefConstructor<BroadcastReceiver.PendingResult> ctor;
        public static RefBoolean mAbortBroadcast;
        public static RefBoolean mFinished;
        public static RefInt mFlags;
        public static RefBoolean mInitialStickyHint;
        public static RefBoolean mOrderedHint;
        public static RefInt mResultCode;
        public static RefObject<String> mResultData;
        public static RefObject<Bundle> mResultExtras;
        public static RefInt mSendingUser;
        public static RefObject<IBinder> mToken;
        public static RefInt mType;
    }
}
