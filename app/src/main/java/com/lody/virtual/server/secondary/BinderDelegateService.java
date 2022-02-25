package com.lody.virtual.server.secondary;

import android.content.ComponentName;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import com.lody.virtual.server.IBinderDelegateService;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class BinderDelegateService extends IBinderDelegateService.Stub {
    private static final Map<String, ProxyBinderFactory> mFactories = new HashMap();
    private ComponentName name;
    private IBinder service;

    /* loaded from: classes.dex */
    private interface ProxyBinderFactory {
        IBinder create(Binder binder);
    }

    static {
        mFactories.put("android.accounts.IAccountAuthenticator", new ProxyBinderFactory() { // from class: com.lody.virtual.server.secondary.BinderDelegateService.1
            @Override // com.lody.virtual.server.secondary.BinderDelegateService.ProxyBinderFactory
            public IBinder create(Binder binder) {
                return new FakeIdentityBinder(binder);
            }
        });
    }

    public BinderDelegateService(ComponentName componentName, IBinder iBinder) {
        this.name = componentName;
        if (iBinder instanceof Binder) {
            Binder binder = (Binder) iBinder;
            ProxyBinderFactory proxyBinderFactory = mFactories.get(binder.getInterfaceDescriptor());
            if (proxyBinderFactory != null) {
                iBinder = proxyBinderFactory.create(binder);
            }
        }
        this.service = iBinder;
    }

    @Override // com.lody.virtual.server.IBinderDelegateService
    public ComponentName getComponent() {
        return this.name;
    }

    @Override // com.lody.virtual.server.IBinderDelegateService
    public IBinder getService() throws RemoteException {
        return this.service;
    }
}
