package com.lody.virtual.client.fixer;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import com.lody.virtual.client.core.InvocationStubManager;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.proxies.graphics.GraphicsStatsStub;
import p110z1.ContentResolverJBMR2;
import p110z1.ContextImpl;
import p110z1.ContextImplKitkat;

/* loaded from: classes.dex */
public class ContextFixer {
    public static void fixContext(Context context) {
        try {
            context.getPackageName();
            InvocationStubManager.getInstance().checkEnv(GraphicsStatsStub.class);
            int i = 0;
            while (context instanceof ContextWrapper) {
                context = ((ContextWrapper) context).getBaseContext();
                i++;
                if (i >= 10) {
                    return;
                }
            }
            ContextImpl.mPackageManager.set(context, null);
            try {
                context.getPackageManager();
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (VirtualCore.get().isVAppProcess()) {
                String hostPkg = VirtualCore.get().getHostPkg();
                ContextImpl.mBasePackageName.set(context, hostPkg);
                if (Build.VERSION.SDK_INT >= 19) {
                    ContextImplKitkat.mOpPackageName.set(context, hostPkg);
                }
                if (Build.VERSION.SDK_INT >= 18) {
                    ContentResolverJBMR2.mPackageName.set(context.getContentResolver(), hostPkg);
                }
            }
        } catch (Throwable unused) {
        }
    }
}
