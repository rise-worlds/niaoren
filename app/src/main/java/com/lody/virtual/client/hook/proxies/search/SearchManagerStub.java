package com.lody.virtual.client.hook.proxies.search;

import android.annotation.TargetApi;
import android.content.ComponentName;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.MethodProxy;
import com.lody.virtual.client.hook.base.StaticMethodProxy;
import java.lang.reflect.Method;
import p110z1.ISearchManager;

@TargetApi(17)
/* loaded from: classes.dex */
public class SearchManagerStub extends BinderInvocationProxy {
    public SearchManagerStub() {
        super(ISearchManager.C5118a.asInterface, "search");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new StaticMethodProxy("launchLegacyAssist"));
        addMethodProxy(new GetSearchableInfo());
    }

    /* loaded from: classes.dex */
    private static class GetSearchableInfo extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getSearchableInfo";
        }

        private GetSearchableInfo() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            ComponentName componentName = (ComponentName) objArr[0];
            if (componentName == null || VirtualCore.getPM().getActivityInfo(componentName, 0) == null) {
                return method.invoke(obj, objArr);
            }
            return null;
        }
    }
}
