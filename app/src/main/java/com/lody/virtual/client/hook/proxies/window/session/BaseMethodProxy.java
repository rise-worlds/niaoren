package com.lody.virtual.client.hook.proxies.window.session;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.WindowManager;
import com.cyjh.ddy.media.media.ActionCode;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.base.StaticMethodProxy;
import com.lody.virtual.helper.utils.ArrayUtils;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
class BaseMethodProxy extends StaticMethodProxy {
    private boolean mDrawOverlays = false;

    public BaseMethodProxy(String str) {
        super(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isDrawOverlays() {
        return this.mDrawOverlays;
    }

    @Override // com.lody.virtual.client.hook.base.MethodProxy
    @SuppressLint({"SwitchIntDef"})
    public boolean beforeCall(Object obj, Method method, Object... objArr) {
        WindowManager.LayoutParams layoutParams;
        this.mDrawOverlays = false;
        int indexOfFirst = ArrayUtils.indexOfFirst(objArr, WindowManager.LayoutParams.class);
        if (!(indexOfFirst == -1 || (layoutParams = (WindowManager.LayoutParams) objArr[indexOfFirst]) == null)) {
            layoutParams.packageName = getHostPkg();
            switch (layoutParams.type) {
                case ActionCode.CtrlConnectRefuse_2002 /* 2002 */:
                case 2003:
                case 2006:
                case 2007:
                case 2010:
                case 2038:
                    this.mDrawOverlays = true;
                    break;
            }
            if (Build.VERSION.SDK_INT >= 26 && VirtualCore.get().getTargetSdkVersion() >= 26 && this.mDrawOverlays) {
                layoutParams.type = 2038;
            }
        }
        return true;
    }
}
