package com.lody.virtual.client.hook.proxies.isms;

import android.os.Build;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy;
import com.lody.virtual.client.hook.base.ReplaceSpecPkgMethodProxy;
import p110z1.ISms;

/* loaded from: classes.dex */
public class ISmsStub extends BinderInvocationProxy {
    public ISmsStub() {
        super(ISms.C5206a.asInterface, "isms");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        if (Build.VERSION.SDK_INT >= 23) {
            addMethodProxy(new ReplaceSpecPkgMethodProxy("getAllMessagesFromIccEfForSubscriber", 1));
            addMethodProxy(new ReplaceSpecPkgMethodProxy("updateMessageOnIccEfForSubscriber", 1));
            addMethodProxy(new ReplaceSpecPkgMethodProxy("copyMessageToIccEfForSubscriber", 1));
            addMethodProxy(new ReplaceSpecPkgMethodProxy("sendDataForSubscriber", 1));
            addMethodProxy(new ReplaceSpecPkgMethodProxy("sendDataForSubscriberWithSelfPermissions", 1));
            addMethodProxy(new ReplaceSpecPkgMethodProxy("sendTextForSubscriber", 1));
            addMethodProxy(new ReplaceSpecPkgMethodProxy("sendTextForSubscriberWithSelfPermissions", 1));
            addMethodProxy(new ReplaceSpecPkgMethodProxy("sendMultipartTextForSubscriber", 1));
            addMethodProxy(new ReplaceSpecPkgMethodProxy("sendStoredText", 1));
            addMethodProxy(new ReplaceSpecPkgMethodProxy("sendStoredMultipartText", 1));
        } else if (Build.VERSION.SDK_INT >= 21) {
            addMethodProxy(new ReplaceCallingPkgMethodProxy("getAllMessagesFromIccEf"));
            addMethodProxy(new ReplaceSpecPkgMethodProxy("getAllMessagesFromIccEfForSubscriber", 1));
            addMethodProxy(new ReplaceCallingPkgMethodProxy("updateMessageOnIccEf"));
            addMethodProxy(new ReplaceSpecPkgMethodProxy("updateMessageOnIccEfForSubscriber", 1));
            addMethodProxy(new ReplaceCallingPkgMethodProxy("copyMessageToIccEf"));
            addMethodProxy(new ReplaceSpecPkgMethodProxy("copyMessageToIccEfForSubscriber", 1));
            addMethodProxy(new ReplaceCallingPkgMethodProxy("sendData"));
            addMethodProxy(new ReplaceSpecPkgMethodProxy("sendDataForSubscriber", 1));
            addMethodProxy(new ReplaceCallingPkgMethodProxy("sendText"));
            addMethodProxy(new ReplaceSpecPkgMethodProxy("sendTextForSubscriber", 1));
            addMethodProxy(new ReplaceCallingPkgMethodProxy("sendMultipartText"));
            addMethodProxy(new ReplaceSpecPkgMethodProxy("sendMultipartTextForSubscriber", 1));
            addMethodProxy(new ReplaceSpecPkgMethodProxy("sendStoredText", 1));
            addMethodProxy(new ReplaceSpecPkgMethodProxy("sendStoredMultipartText", 1));
        } else if (Build.VERSION.SDK_INT >= 18) {
            addMethodProxy(new ReplaceCallingPkgMethodProxy("getAllMessagesFromIccEf"));
            addMethodProxy(new ReplaceCallingPkgMethodProxy("updateMessageOnIccEf"));
            addMethodProxy(new ReplaceCallingPkgMethodProxy("copyMessageToIccEf"));
            addMethodProxy(new ReplaceCallingPkgMethodProxy("sendData"));
            addMethodProxy(new ReplaceCallingPkgMethodProxy("sendText"));
            addMethodProxy(new ReplaceCallingPkgMethodProxy("sendMultipartText"));
        }
    }
}
