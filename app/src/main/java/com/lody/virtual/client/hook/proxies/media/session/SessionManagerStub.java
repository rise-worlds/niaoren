package com.lody.virtual.client.hook.proxies.media.session;

import android.annotation.TargetApi;
import android.os.IInterface;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.MethodProxy;
import com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy;
import com.lody.virtual.client.hook.utils.MethodParameterUtils;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import p110z1.ISessionManager;

@TargetApi(21)
/* loaded from: classes.dex */
public class SessionManagerStub extends BinderInvocationProxy {
    public SessionManagerStub() {
        super(ISessionManager.C5167a.asInterface, "media_session");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new ReplaceCallingPkgMethodProxy("createSession") { // from class: com.lody.virtual.client.hook.proxies.media.session.SessionManagerStub.1
            @Override // com.lody.virtual.client.hook.base.MethodProxy
            public Object call(Object obj, Method method, Object... objArr) throws Throwable {
                final IInterface iInterface = (IInterface) super.call(obj, method, objArr);
                return SessionManagerStub.CreateProxy(iInterface, new InvocationHandler() { // from class: com.lody.virtual.client.hook.proxies.media.session.SessionManagerStub.1.1
                    @Override // java.lang.reflect.InvocationHandler
                    public Object invoke(Object obj2, Method method2, Object[] objArr2) throws Throwable {
                        if (!"getController".equals(method2.getName())) {
                            return method2.invoke(iInterface, objArr2);
                        }
                        final IInterface iInterface2 = (IInterface) method2.invoke(iInterface, objArr2);
                        return SessionManagerStub.CreateProxy(iInterface2, new InvocationHandler() { // from class: com.lody.virtual.client.hook.proxies.media.session.SessionManagerStub.1.1.1
                            @Override // java.lang.reflect.InvocationHandler
                            public Object invoke(Object obj3, Method method3, Object[] objArr3) throws Throwable {
                                if ("setVolumeTo".equals(method3.getName())) {
                                    MethodParameterUtils.replaceFirstAppPkg(objArr3);
                                    return method3.invoke(iInterface2, objArr3);
                                } else if ("adjustVolume".equals(method3.getName())) {
                                    MethodParameterUtils.replaceFirstAppPkg(objArr3);
                                    return method3.invoke(iInterface2, objArr3);
                                } else {
                                    if ("createSession".equals(method3.getName()) || "getSessions".equals(method3.getName()) || "getSession2Tokens".equals(method3.getName()) || "addSessionsListener".equals(method3.getName()) || "addSession2TokensListener".equals(method3.getName())) {
                                        MethodProxy.replaceLastUserId(objArr3);
                                    }
                                    return method3.invoke(iInterface2, objArr3);
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object CreateProxy(IInterface iInterface, InvocationHandler invocationHandler) {
        return Proxy.newProxyInstance(iInterface.getClass().getClassLoader(), iInterface.getClass().getInterfaces(), invocationHandler);
    }
}
