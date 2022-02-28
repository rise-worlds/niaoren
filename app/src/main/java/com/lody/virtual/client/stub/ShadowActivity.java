package com.lody.virtual.client.stub;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.PersistableBundle;
import android.text.TextUtils;
import com.lody.virtual.client.VClient;
import com.lody.virtual.client.core.InvocationStubManager;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.env.VirtualRuntime;
import com.lody.virtual.client.hook.delegate.AppInstrumentation;
import com.lody.virtual.client.hook.proxies.p059am.HCallbackStub;
import com.lody.virtual.client.ipc.VActivityManager;
import com.lody.virtual.os.VUserHandle;
import com.lody.virtual.remote.StubActivityRecord;
import p110z1.ActivityThread;
import p110z1.BaseBundle;
import p110z1.BundleICS;

/* loaded from: classes.dex */
public abstract class ShadowActivity extends Activity {
    private Object activityThread = VirtualCore.mainThread();
    private Instrumentation mInstrumentation = ActivityThread.mInstrumentation.get(this.activityThread);
    private ShadowInstrumentation mShadowInstrumentation;

    /* renamed from: com.lody.virtual.client.stub.ShadowActivity$P0 */
    /* loaded from: classes.dex */
    public static class ActivityC1809P0 extends ShadowActivity {
    }

    /* renamed from: com.lody.virtual.client.stub.ShadowActivity$P1 */
    /* loaded from: classes.dex */
    public static class ActivityC1810P1 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P10 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P11 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P12 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P13 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P14 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P15 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P16 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P17 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P18 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P19 extends ShadowActivity {
    }

    /* renamed from: com.lody.virtual.client.stub.ShadowActivity$P2 */
    /* loaded from: classes.dex */
    public static class ActivityC1811P2 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P20 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P21 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P22 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P23 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P24 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P25 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P26 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P27 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P28 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P29 extends ShadowActivity {
    }

    /* renamed from: com.lody.virtual.client.stub.ShadowActivity$P3 */
    /* loaded from: classes.dex */
    public static class ActivityC1812P3 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P30 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P31 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P32 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P33 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P34 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P35 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P36 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P37 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P38 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P39 extends ShadowActivity {
    }

    /* renamed from: com.lody.virtual.client.stub.ShadowActivity$P4 */
    /* loaded from: classes.dex */
    public static class ActivityC1813P4 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P40 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P41 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P42 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P43 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P44 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P45 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P46 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P47 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P48 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P49 extends ShadowActivity {
    }

    /* renamed from: com.lody.virtual.client.stub.ShadowActivity$P5 */
    /* loaded from: classes.dex */
    public static class ActivityC1814P5 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P50 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P51 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P52 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P53 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P54 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P55 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P56 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P57 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P58 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P59 extends ShadowActivity {
    }

    /* renamed from: com.lody.virtual.client.stub.ShadowActivity$P6 */
    /* loaded from: classes.dex */
    public static class ActivityC1815P6 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P60 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P61 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P62 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P63 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P64 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P65 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P66 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P67 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P68 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P69 extends ShadowActivity {
    }

    /* renamed from: com.lody.virtual.client.stub.ShadowActivity$P7 */
    /* loaded from: classes.dex */
    public static class ActivityC1816P7 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P70 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P71 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P72 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P73 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P74 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P75 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P76 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P77 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P78 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P79 extends ShadowActivity {
    }

    /* renamed from: com.lody.virtual.client.stub.ShadowActivity$P8 */
    /* loaded from: classes.dex */
    public static class ActivityC1817P8 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P80 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P81 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P82 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P83 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P84 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P85 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P86 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P87 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P88 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P89 extends ShadowActivity {
    }

    /* renamed from: com.lody.virtual.client.stub.ShadowActivity$P9 */
    /* loaded from: classes.dex */
    public static class ActivityC1818P9 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P90 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P91 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P92 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P93 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P94 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P95 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P96 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P97 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P98 extends ShadowActivity {
    }

    /* loaded from: classes.dex */
    public static class P99 extends ShadowActivity {
    }

    public ShadowActivity() {
        Instrumentation instrumentation = this.mInstrumentation;
        if (instrumentation instanceof AppInstrumentation) {
            if (this.mShadowInstrumentation == null) {
                this.mShadowInstrumentation = new ShadowInstrumentation(instrumentation);
            }
            ActivityThread.mInstrumentation.set(this.activityThread, this.mShadowInstrumentation);
            return;
        }
        ActivityThread.mInstrumentation.set(this.activityThread, AppInstrumentation.getDefault());
    }

    private void restoreInstrumentation() {
        ActivityThread.mInstrumentation.set(this.activityThread, this.mInstrumentation);
    }

    /* loaded from: classes.dex */
    private static class ShadowInstrumentation extends Instrumentation {
        private Instrumentation mBase;

        public ShadowInstrumentation(Instrumentation instrumentation) {
            this.mBase = instrumentation;
        }

        @Override // android.app.Instrumentation
        public Activity newActivity(Class<?> cls, Context context, IBinder iBinder, Application application, Intent intent, ActivityInfo activityInfo, CharSequence charSequence, Activity activity, String str, Object obj) throws InstantiationException, IllegalAccessException {
            return this.mBase.newActivity(cls, context, iBinder, application, intent, activityInfo, charSequence, activity, str, obj);
        }

        @Override // android.app.Instrumentation
        public Activity newActivity(ClassLoader classLoader, String str, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
            return this.mBase.newActivity(classLoader, str, intent);
        }

        @Override // android.app.Instrumentation
        public void callActivityOnCreate(Activity activity, Bundle bundle) {
            if (bundle != null) {
                ShadowActivity.clearParcelledData(bundle);
            }
            this.mBase.callActivityOnCreate(activity, bundle);
        }

        @Override // android.app.Instrumentation
        @TargetApi(21)
        public void callActivityOnCreate(Activity activity, Bundle bundle, PersistableBundle persistableBundle) {
            if (bundle != null) {
                ShadowActivity.clearParcelledData(bundle);
            }
            this.mBase.callActivityOnCreate(activity, bundle, persistableBundle);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(null);
        if (bundle != null) {
            clearParcelledData(bundle);
        }
        restoreInstrumentation();
        finish();
        StubActivityRecord stubActivityRecord = new StubActivityRecord(getIntent());
        if (stubActivityRecord.intent == null) {
            return;
        }
        if (!TextUtils.equals(stubActivityRecord.info.processName, VirtualRuntime.getProcessName()) || stubActivityRecord.userId != VUserHandle.myUserId()) {
            VActivityManager.get().startActivity(stubActivityRecord.intent, stubActivityRecord.userId);
            return;
        }
        InvocationStubManager.getInstance().checkEnv(HCallbackStub.class);
        Intent intent = stubActivityRecord.intent;
        intent.setExtrasClassLoader(VClient.get().getCurrentApplication().getClassLoader());
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void clearParcelledData(Bundle bundle) {
        Parcel parcel;
        Parcel obtain = Parcel.obtain();
        obtain.writeInt(0);
        obtain.setDataPosition(0);
        if (BaseBundle.TYPE != null) {
            parcel = BaseBundle.mParcelledData.get(bundle);
            BaseBundle.mParcelledData.set(bundle, obtain);
        } else if (BundleICS.TYPE != null) {
            parcel = BundleICS.mParcelledData.get(bundle);
            BundleICS.mParcelledData.set(bundle, obtain);
        } else {
            parcel = null;
        }
        if (parcel != null) {
            parcel.recycle();
        }
    }
}
