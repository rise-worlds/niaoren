package com.lody.virtual.client.hook.proxies.appwidget;

import android.annotation.TargetApi;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ResultStaticMethodProxy;
import p110z1.IAppWidgetService;

@TargetApi(21)
/* loaded from: classes.dex */
public class AppWidgetManagerStub extends BinderInvocationProxy {
    public AppWidgetManagerStub() {
        super(IAppWidgetService.C5197a.asInterface, "appwidget");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new ResultStaticMethodProxy("startListening", new int[0]));
        addMethodProxy(new ResultStaticMethodProxy("stopListening", 0));
        addMethodProxy(new ResultStaticMethodProxy("allocateAppWidgetId", 0));
        addMethodProxy(new ResultStaticMethodProxy("deleteAppWidgetId", 0));
        addMethodProxy(new ResultStaticMethodProxy("deleteHost", 0));
        addMethodProxy(new ResultStaticMethodProxy("deleteAllHosts", 0));
        addMethodProxy(new ResultStaticMethodProxy("getAppWidgetViews", null));
        addMethodProxy(new ResultStaticMethodProxy("getAppWidgetIdsForHost", null));
        addMethodProxy(new ResultStaticMethodProxy("createAppWidgetConfigIntentSender", null));
        addMethodProxy(new ResultStaticMethodProxy("updateAppWidgetIds", 0));
        addMethodProxy(new ResultStaticMethodProxy("updateAppWidgetOptions", 0));
        addMethodProxy(new ResultStaticMethodProxy("getAppWidgetOptions", null));
        addMethodProxy(new ResultStaticMethodProxy("partiallyUpdateAppWidgetIds", 0));
        addMethodProxy(new ResultStaticMethodProxy("updateAppWidgetProvider", 0));
        addMethodProxy(new ResultStaticMethodProxy("notifyAppWidgetViewDataChanged", 0));
        addMethodProxy(new ResultStaticMethodProxy("getInstalledProvidersForProfile", null));
        addMethodProxy(new ResultStaticMethodProxy("getAppWidgetInfo", null));
        addMethodProxy(new ResultStaticMethodProxy("hasBindAppWidgetPermission", false));
        addMethodProxy(new ResultStaticMethodProxy("setBindAppWidgetPermission", 0));
        addMethodProxy(new ResultStaticMethodProxy("bindAppWidgetId", false));
        addMethodProxy(new ResultStaticMethodProxy("bindRemoteViewsService", 0));
        addMethodProxy(new ResultStaticMethodProxy("unbindRemoteViewsService", 0));
        addMethodProxy(new ResultStaticMethodProxy("getAppWidgetIds", new int[0]));
        addMethodProxy(new ResultStaticMethodProxy("isBoundWidgetPackage", false));
    }
}
