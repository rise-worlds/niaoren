package com.lody.virtual.client.hook.proxies.clipboard;

import android.os.Build;
import android.os.IInterface;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceLastPkgMethodProxy;
import p110z1.ClipboardManager;
import p110z1.ClipboardManagerOreo;

/* loaded from: classes.dex */
public class ClipBoardStub extends BinderInvocationProxy {
    public ClipBoardStub() {
        super(getInterface(), "clipboard");
    }

    private static IInterface getInterface() {
        if (ClipboardManager.getService != null) {
            return ClipboardManager.getService.call(new Object[0]);
        }
        if (ClipboardManagerOreo.mService != null) {
            return ClipboardManagerOreo.mService.get((android.content.ClipboardManager) VirtualCore.get().getContext().getSystemService("clipboard"));
        } else if (ClipboardManagerOreo.sService != null) {
            return ClipboardManagerOreo.sService.get();
        } else {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new ReplaceLastPkgMethodProxy("getPrimaryClip"));
        if (Build.VERSION.SDK_INT > 17) {
            addMethodProxy(new ReplaceLastPkgMethodProxy("setPrimaryClip"));
            addMethodProxy(new ReplaceLastPkgMethodProxy("getPrimaryClipDescription"));
            addMethodProxy(new ReplaceLastPkgMethodProxy("hasPrimaryClip"));
            addMethodProxy(new ReplaceLastPkgMethodProxy("addPrimaryClipChangedListener"));
            addMethodProxy(new ReplaceLastPkgMethodProxy("removePrimaryClipChangedListener"));
            addMethodProxy(new ReplaceLastPkgMethodProxy("hasClipboardText"));
        }
    }

    @Override // com.lody.virtual.client.hook.base.BinderInvocationProxy, com.lody.virtual.client.hook.base.MethodInvocationProxy, com.lody.virtual.client.interfaces.IInjector
    public void inject() throws Throwable {
        super.inject();
        if (ClipboardManagerOreo.mService != null) {
            ClipboardManagerOreo.mService.set((android.content.ClipboardManager) VirtualCore.get().getContext().getSystemService("clipboard"), getInvocationStub().getProxyInterface());
        } else if (ClipboardManagerOreo.sService != null) {
            ClipboardManagerOreo.sService.set(getInvocationStub().getProxyInterface());
        }
    }
}
