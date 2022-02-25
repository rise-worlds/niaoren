package com.lody.virtual.client.hook.proxies.service;

import android.os.IInterface;
import com.lody.virtual.client.core.ServiceLocalManager;
import com.lody.virtual.client.hook.base.BinderInvocationStub;
import com.lody.virtual.client.hook.base.MethodInvocationProxy;
import com.lody.virtual.client.hook.base.MethodInvocationStub;
import com.lody.virtual.client.hook.base.StaticMethodProxy;
import com.lody.virtual.helper.utils.VLog;
import java.lang.reflect.Method;
import p110z1.ServiceManager;

/* loaded from: classes.dex */
public class ServiceManagerStub extends MethodInvocationProxy<MethodInvocationStub<IInterface>> {
    public ServiceManagerStub() {
        super(new MethodInvocationStub(ServiceManager.getIServiceManager.call(new Object[0])));
    }

    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy, com.lody.virtual.client.interfaces.IInjector
    public void inject() {
        ServiceManager.sServiceManager.set(getInvocationStub().getProxyInterface());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new StaticMethodProxy("getService") { // from class: com.lody.virtual.client.hook.proxies.service.ServiceManagerStub.1
            @Override // com.lody.virtual.client.hook.base.MethodProxy
            public Object call(Object obj, Method method, Object... objArr) throws Throwable {
                String str = (String) objArr[0];
                BinderInvocationStub service = ServiceLocalManager.getService(str);
                if (service != null) {
                    VLog.m18993d("kk", "ServiceLocalManager.getService:%s->%s", str, service);
                    return service;
                }
                VLog.m18993d("kk", "ServiceLocalManager.getService:%s no find", str);
                return super.call(obj, method, objArr);
            }
        });
        addMethodProxy(new StaticMethodProxy("checkService") { // from class: com.lody.virtual.client.hook.proxies.service.ServiceManagerStub.2
            @Override // com.lody.virtual.client.hook.base.MethodProxy
            public Object call(Object obj, Method method, Object... objArr) throws Throwable {
                String str = (String) objArr[0];
                BinderInvocationStub service = ServiceLocalManager.getService(str);
                if (service != null) {
                    VLog.m18993d("kk", "ServiceLocalManager.checkService:%s->%s", str, service);
                    return service;
                }
                VLog.m18993d("kk", "ServiceLocalManager.checkService:%s no find", str);
                return super.call(obj, method, objArr);
            }
        });
    }

    @Override // com.lody.virtual.client.interfaces.IInjector
    public boolean isEnvBad() {
        return ServiceManager.sServiceManager.get() != getInvocationStub().getProxyInterface();
    }
}
