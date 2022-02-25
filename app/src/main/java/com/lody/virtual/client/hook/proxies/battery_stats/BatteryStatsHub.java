package com.lody.virtual.client.hook.proxies.battery_stats;

import android.annotation.TargetApi;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceLastUidMethodProxy;
import java.lang.reflect.Method;
import p110z1.IBatteryStats;
import p110z1.SystemHealthManager;

@TargetApi(24)
/* loaded from: classes.dex */
public class BatteryStatsHub extends BinderInvocationProxy {
    private static final String SERVICE_NAME = "batterystats";

    public BatteryStatsHub() {
        super(IBatteryStats.C5195a.asInterface, SERVICE_NAME);
    }

    @Override // com.lody.virtual.client.hook.base.BinderInvocationProxy, com.lody.virtual.client.hook.base.MethodInvocationProxy, com.lody.virtual.client.interfaces.IInjector
    public void inject() throws Throwable {
        super.inject();
        if (SystemHealthManager.mBatteryStats != null) {
            SystemHealthManager.mBatteryStats.set((android.os.health.SystemHealthManager) VirtualCore.get().getContext().getSystemService("systemhealth"), getInvocationStub().getProxyInterface());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new ReplaceLastUidMethodProxy("takeUidSnapshot") { // from class: com.lody.virtual.client.hook.proxies.battery_stats.BatteryStatsHub.1
            @Override // com.lody.virtual.client.hook.base.MethodProxy
            public Object call(Object obj, Method method, Object... objArr) {
                try {
                    return super.call(obj, method, objArr);
                } catch (Throwable unused) {
                    return null;
                }
            }
        });
    }
}
