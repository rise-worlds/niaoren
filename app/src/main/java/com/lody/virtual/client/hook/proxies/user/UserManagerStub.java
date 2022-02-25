package com.lody.virtual.client.hook.proxies.user;

import android.annotation.TargetApi;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy;
import com.lody.virtual.client.hook.base.ReplaceLastUserIdMethodProxy;
import com.lody.virtual.client.hook.base.ResultStaticMethodProxy;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import java.util.Collections;
import p110z1.IUserManager;
import p110z1.UserInfo;

@TargetApi(17)
/* loaded from: classes.dex */
public class UserManagerStub extends BinderInvocationProxy {
    public UserManagerStub() {
        super(IUserManager.C5174a.asInterface, ServiceManagerNative.USER);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new ReplaceCallingPkgMethodProxy("setApplicationRestrictions"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getApplicationRestrictions"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getApplicationRestrictionsForUser"));
        addMethodProxy(new ReplaceLastUserIdMethodProxy("isUserUnlockingOrUnlocked"));
        addMethodProxy(new ReplaceLastUserIdMethodProxy("isManagedProfile"));
        addMethodProxy(new ResultStaticMethodProxy("getProfileParent", null));
        addMethodProxy(new ResultStaticMethodProxy("getUserIcon", null));
        addMethodProxy(new ResultStaticMethodProxy("getUserInfo", UserInfo.ctor.newInstance(0, "Admin", Integer.valueOf(UserInfo.FLAG_PRIMARY.get()))));
        addMethodProxy(new ResultStaticMethodProxy("getDefaultGuestRestrictions", null));
        addMethodProxy(new ResultStaticMethodProxy("setDefaultGuestRestrictions", null));
        addMethodProxy(new ResultStaticMethodProxy("removeRestrictions", null));
        addMethodProxy(new ResultStaticMethodProxy("getUsers", Collections.singletonList(UserInfo.ctor.newInstance(0, "Admin", Integer.valueOf(UserInfo.FLAG_PRIMARY.get())))));
        addMethodProxy(new ResultStaticMethodProxy("createUser", null));
        addMethodProxy(new ResultStaticMethodProxy("createProfileForUser", null));
        addMethodProxy(new ResultStaticMethodProxy("getProfiles", Collections.EMPTY_LIST));
    }
}
