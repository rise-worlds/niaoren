package com.lody.virtual.client.hook.proxies.bluetooth;

import android.os.Build;
import android.os.IInterface;
import android.text.TextUtils;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceLastPkgMethodProxy;
import com.lody.virtual.client.hook.base.ResultBinderMethodProxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import p110z1.IBluetooth;

/* loaded from: classes.dex */
public class BluetoothStub extends BinderInvocationProxy {
    private static final String SERVER_NAME;

    static {
        SERVER_NAME = Build.VERSION.SDK_INT >= 17 ? "bluetooth_manager" : "bluetooth";
    }

    public BluetoothStub() {
        super(IBluetooth.C5130a.asInterface, SERVER_NAME);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new GetAddress());
        if (Build.VERSION.SDK_INT >= 17) {
            addMethodProxy(new ResultBinderMethodProxy("registerAdapter") { // from class: com.lody.virtual.client.hook.proxies.bluetooth.BluetoothStub.1
                @Override // com.lody.virtual.client.hook.base.ResultBinderMethodProxy
                public InvocationHandler createProxy(final IInterface iInterface) {
                    return new InvocationHandler() { // from class: com.lody.virtual.client.hook.proxies.bluetooth.BluetoothStub.1.1
                        @Override // java.lang.reflect.InvocationHandler
                        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                            if ("getAddress".equals(method.getName()) && C17561.getDeviceConfig().enable) {
                                String str = C17561.getDeviceConfig().bluetoothMac;
                                if (!TextUtils.isEmpty(str)) {
                                    return str;
                                }
                            }
                            return method.invoke(iInterface, objArr);
                        }
                    };
                }
            });
        }
    }

    /* loaded from: classes.dex */
    private static class GetAddress extends ReplaceLastPkgMethodProxy {
        public GetAddress() {
            super("getAddress");
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (getDeviceConfig().enable) {
                String str = getDeviceConfig().bluetoothMac;
                if (!TextUtils.isEmpty(str)) {
                    return str;
                }
            }
            return super.call(obj, method, objArr);
        }
    }
}
