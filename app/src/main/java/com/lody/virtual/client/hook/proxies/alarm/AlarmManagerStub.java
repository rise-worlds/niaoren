package com.lody.virtual.client.hook.proxies.alarm;

import android.app.AlarmManager;
import android.os.Build;
import android.os.WorkSource;
import android.support.v4.app.NotificationCompat;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.MethodProxy;
import com.lody.virtual.helper.utils.ArrayUtils;
import java.lang.reflect.Method;
import p110z1.IAlarmManager;

/* loaded from: classes.dex */
public class AlarmManagerStub extends BinderInvocationProxy {
    public AlarmManagerStub() {
        super(IAlarmManager.C5115a.asInterface, NotificationCompat.CATEGORY_ALARM);
    }

    @Override // com.lody.virtual.client.hook.base.BinderInvocationProxy, com.lody.virtual.client.hook.base.MethodInvocationProxy, com.lody.virtual.client.interfaces.IInjector
    public void inject() throws Throwable {
        super.inject();
        AlarmManager alarmManager = (AlarmManager) VirtualCore.get().getContext().getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (p110z1.AlarmManager.mService != null) {
            try {
                p110z1.AlarmManager.mService.set(alarmManager, getInvocationStub().getProxyInterface());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new Set());
        addMethodProxy(new SetTime());
        addMethodProxy(new SetTimeZone());
    }

    /* loaded from: classes.dex */
    private static class SetTimeZone extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return null;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "setTimeZone";
        }

        private SetTimeZone() {
        }
    }

    /* loaded from: classes.dex */
    private static class SetTime extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "setTime";
        }

        private SetTime() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return Build.VERSION.SDK_INT >= 21 ? false : null;
        }
    }

    /* loaded from: classes.dex */
    private static class Set extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "set";
        }

        private Set() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public boolean beforeCall(Object obj, Method method, Object... objArr) {
            if (Build.VERSION.SDK_INT >= 24 && (objArr[0] instanceof String)) {
                objArr[0] = getHostPkg();
            }
            int indexOfFirst = ArrayUtils.indexOfFirst(objArr, WorkSource.class);
            if (indexOfFirst < 0) {
                return true;
            }
            objArr[indexOfFirst] = null;
            return true;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            try {
                return super.call(obj, method, objArr);
            } catch (Throwable th) {
                th.printStackTrace();
                return 0;
            }
        }
    }

    /* loaded from: classes.dex */
    private static class GetNextAlarmClock extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getNextAlarmClock";
        }

        private GetNextAlarmClock() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            replaceLastUserId(objArr);
            return super.call(obj, method, objArr);
        }
    }
}
