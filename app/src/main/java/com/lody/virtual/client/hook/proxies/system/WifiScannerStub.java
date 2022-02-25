package com.lody.virtual.client.hook.proxies.system;

import android.net.wifi.IWifiScanner;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Messenger;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import java.util.ArrayList;
import p110z1.ServiceManager;
import p110z1.WifiScanner;

/* loaded from: classes.dex */
public class WifiScannerStub extends BinderInvocationProxy {
    private static final String SERVICE_NAME = "wifiscanner";

    public WifiScannerStub() {
        super(new EmptyWifiScannerImpl(), SERVICE_NAME);
    }

    @Override // com.lody.virtual.client.hook.base.BinderInvocationProxy, com.lody.virtual.client.hook.base.MethodInvocationProxy, com.lody.virtual.client.interfaces.IInjector
    public void inject() throws Throwable {
        if (ServiceManager.checkService.call(SERVICE_NAME) == null) {
            super.inject();
        }
    }

    /* loaded from: classes.dex */
    static class EmptyWifiScannerImpl extends IWifiScanner.Stub {
        private final Handler mHandler = new Handler(Looper.getMainLooper());

        EmptyWifiScannerImpl() {
        }

        @Override // android.net.wifi.IWifiScanner
        public Messenger getMessenger() {
            return new Messenger(this.mHandler);
        }

        @Override // android.net.wifi.IWifiScanner
        public Bundle getAvailableChannels(int i) {
            Bundle bundle = new Bundle();
            bundle.putIntegerArrayList(WifiScanner.GET_AVAILABLE_CHANNELS_EXTRA.get(), new ArrayList<>(0));
            return bundle;
        }
    }
}
