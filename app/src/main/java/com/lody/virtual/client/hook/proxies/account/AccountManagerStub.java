package com.lody.virtual.client.hook.proxies.account;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.IAccountManagerResponse;
import android.annotation.TargetApi;
import android.os.Bundle;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.MethodProxy;
import com.lody.virtual.client.ipc.VAccountManager;
import com.lody.virtual.helper.compat.BuildCompat;
import com.lody.virtual.helper.utils.Reflect;
import java.lang.reflect.Method;
import java.util.Map;
import p110z1.IAccountManager;

/* loaded from: classes.dex */
public class AccountManagerStub extends BinderInvocationProxy {
    private static VAccountManager Mgr = VAccountManager.get();

    public AccountManagerStub() {
        super(IAccountManager.C5104a.asInterface, "account");
    }

    @Override // com.lody.virtual.client.hook.base.BinderInvocationProxy, com.lody.virtual.client.hook.base.MethodInvocationProxy, com.lody.virtual.client.interfaces.IInjector
    public void inject() throws Throwable {
        super.inject();
        try {
            Reflect.m18998on((AccountManager) getContext().getSystemService("account")).set("mService", getInvocationStub().getProxyInterface());
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new getPassword());
        addMethodProxy(new getUserData());
        addMethodProxy(new getAuthenticatorTypes());
        addMethodProxy(new getAccounts());
        addMethodProxy(new getAccountsForPackage());
        addMethodProxy(new getAccountsByTypeForPackage());
        addMethodProxy(new getAccountsAsUser());
        addMethodProxy(new hasFeatures());
        addMethodProxy(new getAccountsByFeatures());
        addMethodProxy(new addAccountExplicitly());
        addMethodProxy(new removeAccount());
        addMethodProxy(new removeAccountAsUser());
        addMethodProxy(new removeAccountExplicitly());
        addMethodProxy(new copyAccountToUser());
        addMethodProxy(new invalidateAuthToken());
        addMethodProxy(new peekAuthToken());
        addMethodProxy(new setAuthToken());
        addMethodProxy(new setPassword());
        addMethodProxy(new clearPassword());
        addMethodProxy(new setUserData());
        addMethodProxy(new updateAppPermission());
        addMethodProxy(new getAuthToken());
        addMethodProxy(new addAccount());
        addMethodProxy(new addAccountAsUser());
        addMethodProxy(new updateCredentials());
        addMethodProxy(new editProperties());
        addMethodProxy(new confirmCredentialsAsUser());
        addMethodProxy(new accountAuthenticated());
        addMethodProxy(new getAuthTokenLabel());
        addMethodProxy(new addSharedAccountAsUser());
        addMethodProxy(new getSharedAccountsAsUser());
        addMethodProxy(new removeSharedAccountAsUser());
        addMethodProxy(new renameAccount());
        addMethodProxy(new getPreviousName());
        addMethodProxy(new renameSharedAccountAsUser());
        if (BuildCompat.isOreo()) {
            addMethodProxy(new finishSessionAsUser());
            addMethodProxy(new getAccountVisibility());
            addMethodProxy(new addAccountExplicitlyWithVisibility());
            addMethodProxy(new getAccountsAndVisibilityForPackage());
            addMethodProxy(new getPackagesAndVisibilityForAccount());
            addMethodProxy(new setAccountVisibility());
            addMethodProxy(new startAddAccountSession());
            addMethodProxy(new startUpdateCredentialsSession());
            addMethodProxy(new registerAccountListener());
            addMethodProxy(new unregisterAccountListener());
        }
    }

    /* loaded from: classes.dex */
    private static class getPassword extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getPassword";
        }

        private getPassword() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return AccountManagerStub.Mgr.getPassword((Account) objArr[0]);
        }
    }

    /* loaded from: classes.dex */
    private static class getUserData extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getUserData";
        }

        private getUserData() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return AccountManagerStub.Mgr.getUserData((Account) objArr[0], (String) objArr[1]);
        }
    }

    /* loaded from: classes.dex */
    private static class getAuthenticatorTypes extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getAuthenticatorTypes";
        }

        private getAuthenticatorTypes() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return AccountManagerStub.Mgr.getAuthenticatorTypes();
        }
    }

    /* loaded from: classes.dex */
    private static class getAccounts extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getAccounts";
        }

        private getAccounts() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return AccountManagerStub.Mgr.getAccounts((String) objArr[0]);
        }
    }

    /* loaded from: classes.dex */
    private static class getAccountsForPackage extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getAccountsForPackage";
        }

        private getAccountsForPackage() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            String str = (String) objArr[0];
            return AccountManagerStub.Mgr.getAccounts(null);
        }
    }

    /* loaded from: classes.dex */
    private static class getAccountsByTypeForPackage extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getAccountsByTypeForPackage";
        }

        private getAccountsByTypeForPackage() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            String str = (String) objArr[1];
            return AccountManagerStub.Mgr.getAccounts((String) objArr[0]);
        }
    }

    /* loaded from: classes.dex */
    private static class getAccountByTypeAndFeatures extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getAccountByTypeAndFeatures";
        }

        private getAccountByTypeAndFeatures() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            String str = (String) objArr[1];
            return AccountManagerStub.Mgr.getAccounts((String) objArr[0]);
        }
    }

    /* loaded from: classes.dex */
    private static class getAccountsAsUser extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getAccountsAsUser";
        }

        private getAccountsAsUser() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return AccountManagerStub.Mgr.getAccounts((String) objArr[0]);
        }
    }

    /* loaded from: classes.dex */
    private static class hasFeatures extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "hasFeatures";
        }

        private hasFeatures() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            AccountManagerStub.Mgr.hasFeatures((IAccountManagerResponse) objArr[0], (Account) objArr[1], (String[]) objArr[2]);
            return 0;
        }
    }

    /* loaded from: classes.dex */
    private static class getAccountsByFeatures extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getAccountsByFeatures";
        }

        private getAccountsByFeatures() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            AccountManagerStub.Mgr.getAccountsByFeatures((IAccountManagerResponse) objArr[0], (String) objArr[1], (String[]) objArr[2]);
            return 0;
        }
    }

    /* loaded from: classes.dex */
    private static class addAccountExplicitly extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "addAccountExplicitly";
        }

        private addAccountExplicitly() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return Boolean.valueOf(AccountManagerStub.Mgr.addAccountExplicitly((Account) objArr[0], (String) objArr[1], (Bundle) objArr[2]));
        }
    }

    /* loaded from: classes.dex */
    private static class removeAccount extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "removeAccount";
        }

        private removeAccount() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            boolean booleanValue = ((Boolean) objArr[2]).booleanValue();
            AccountManagerStub.Mgr.removeAccount((IAccountManagerResponse) objArr[0], (Account) objArr[1], booleanValue);
            return 0;
        }
    }

    /* loaded from: classes.dex */
    private static class removeAccountAsUser extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "removeAccountAsUser";
        }

        private removeAccountAsUser() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            boolean booleanValue = ((Boolean) objArr[2]).booleanValue();
            AccountManagerStub.Mgr.removeAccount((IAccountManagerResponse) objArr[0], (Account) objArr[1], booleanValue);
            return 0;
        }
    }

    /* loaded from: classes.dex */
    private static class removeAccountExplicitly extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "removeAccountExplicitly";
        }

        private removeAccountExplicitly() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return Boolean.valueOf(AccountManagerStub.Mgr.removeAccountExplicitly((Account) objArr[0]));
        }
    }

    /* loaded from: classes.dex */
    private static class copyAccountToUser extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "copyAccountToUser";
        }

        private copyAccountToUser() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            IAccountManagerResponse iAccountManagerResponse = (IAccountManagerResponse) objArr[0];
            Account account = (Account) objArr[1];
            ((Integer) objArr[2]).intValue();
            ((Integer) objArr[3]).intValue();
            method.invoke(obj, objArr);
            return 0;
        }
    }

    /* loaded from: classes.dex */
    private static class invalidateAuthToken extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "invalidateAuthToken";
        }

        private invalidateAuthToken() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            AccountManagerStub.Mgr.invalidateAuthToken((String) objArr[0], (String) objArr[1]);
            return 0;
        }
    }

    /* loaded from: classes.dex */
    private static class peekAuthToken extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "peekAuthToken";
        }

        private peekAuthToken() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return AccountManagerStub.Mgr.peekAuthToken((Account) objArr[0], (String) objArr[1]);
        }
    }

    /* loaded from: classes.dex */
    private static class setAuthToken extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "setAuthToken";
        }

        private setAuthToken() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            AccountManagerStub.Mgr.setAuthToken((Account) objArr[0], (String) objArr[1], (String) objArr[2]);
            return 0;
        }
    }

    /* loaded from: classes.dex */
    private static class setPassword extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "setPassword";
        }

        private setPassword() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            AccountManagerStub.Mgr.setPassword((Account) objArr[0], (String) objArr[1]);
            return 0;
        }
    }

    /* loaded from: classes.dex */
    private static class clearPassword extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "clearPassword";
        }

        private clearPassword() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            AccountManagerStub.Mgr.clearPassword((Account) objArr[0]);
            return 0;
        }
    }

    /* loaded from: classes.dex */
    private static class setUserData extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "setUserData";
        }

        private setUserData() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            AccountManagerStub.Mgr.setUserData((Account) objArr[0], (String) objArr[1], (String) objArr[2]);
            return 0;
        }
    }

    /* loaded from: classes.dex */
    private static class updateAppPermission extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "updateAppPermission";
        }

        private updateAppPermission() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            Account account = (Account) objArr[0];
            String str = (String) objArr[1];
            ((Integer) objArr[2]).intValue();
            ((Boolean) objArr[3]).booleanValue();
            method.invoke(obj, objArr);
            return 0;
        }
    }

    /* loaded from: classes.dex */
    private static class getAuthToken extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getAuthToken";
        }

        private getAuthToken() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            AccountManagerStub.Mgr.getAuthToken((IAccountManagerResponse) objArr[0], (Account) objArr[1], (String) objArr[2], ((Boolean) objArr[3]).booleanValue(), ((Boolean) objArr[4]).booleanValue(), (Bundle) objArr[5]);
            return 0;
        }
    }

    /* loaded from: classes.dex */
    private static class addAccount extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "addAccount";
        }

        private addAccount() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            AccountManagerStub.Mgr.addAccount((IAccountManagerResponse) objArr[0], (String) objArr[1], (String) objArr[2], (String[]) objArr[3], ((Boolean) objArr[4]).booleanValue(), (Bundle) objArr[5]);
            return 0;
        }
    }

    /* loaded from: classes.dex */
    private static class addAccountAsUser extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "addAccountAsUser";
        }

        private addAccountAsUser() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            AccountManagerStub.Mgr.addAccount((IAccountManagerResponse) objArr[0], (String) objArr[1], (String) objArr[2], (String[]) objArr[3], ((Boolean) objArr[4]).booleanValue(), (Bundle) objArr[5]);
            return 0;
        }
    }

    /* loaded from: classes.dex */
    private static class updateCredentials extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "updateCredentials";
        }

        private updateCredentials() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            AccountManagerStub.Mgr.updateCredentials((IAccountManagerResponse) objArr[0], (Account) objArr[1], (String) objArr[2], ((Boolean) objArr[3]).booleanValue(), (Bundle) objArr[4]);
            return 0;
        }
    }

    /* loaded from: classes.dex */
    private static class editProperties extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "editProperties";
        }

        private editProperties() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            boolean booleanValue = ((Boolean) objArr[2]).booleanValue();
            AccountManagerStub.Mgr.editProperties((IAccountManagerResponse) objArr[0], (String) objArr[1], booleanValue);
            return 0;
        }
    }

    /* loaded from: classes.dex */
    private static class confirmCredentialsAsUser extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "confirmCredentialsAsUser";
        }

        private confirmCredentialsAsUser() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            boolean booleanValue = ((Boolean) objArr[3]).booleanValue();
            AccountManagerStub.Mgr.confirmCredentials((IAccountManagerResponse) objArr[0], (Account) objArr[1], (Bundle) objArr[2], booleanValue);
            return 0;
        }
    }

    /* loaded from: classes.dex */
    private static class accountAuthenticated extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "accountAuthenticated";
        }

        private accountAuthenticated() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return Boolean.valueOf(AccountManagerStub.Mgr.accountAuthenticated((Account) objArr[0]));
        }
    }

    /* loaded from: classes.dex */
    private static class getAuthTokenLabel extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getAuthTokenLabel";
        }

        private getAuthTokenLabel() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            AccountManagerStub.Mgr.getAuthTokenLabel((IAccountManagerResponse) objArr[0], (String) objArr[1], (String) objArr[2]);
            return 0;
        }
    }

    /* loaded from: classes.dex */
    private static class addSharedAccountAsUser extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "addSharedAccountAsUser";
        }

        private addSharedAccountAsUser() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            Account account = (Account) objArr[0];
            ((Integer) objArr[1]).intValue();
            return method.invoke(obj, objArr);
        }
    }

    /* loaded from: classes.dex */
    private static class getSharedAccountsAsUser extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getSharedAccountsAsUser";
        }

        private getSharedAccountsAsUser() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            ((Integer) objArr[0]).intValue();
            return method.invoke(obj, objArr);
        }
    }

    /* loaded from: classes.dex */
    private static class removeSharedAccountAsUser extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "removeSharedAccountAsUser";
        }

        private removeSharedAccountAsUser() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            Account account = (Account) objArr[0];
            ((Integer) objArr[1]).intValue();
            return method.invoke(obj, objArr);
        }
    }

    /* loaded from: classes.dex */
    private static class renameAccount extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "renameAccount";
        }

        private renameAccount() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            AccountManagerStub.Mgr.renameAccount((IAccountManagerResponse) objArr[0], (Account) objArr[1], (String) objArr[2]);
            return 0;
        }
    }

    /* loaded from: classes.dex */
    private static class getPreviousName extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getPreviousName";
        }

        private getPreviousName() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return AccountManagerStub.Mgr.getPreviousName((Account) objArr[0]);
        }
    }

    /* loaded from: classes.dex */
    private static class renameSharedAccountAsUser extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "renameSharedAccountAsUser";
        }

        private renameSharedAccountAsUser() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            Account account = (Account) objArr[0];
            String str = (String) objArr[1];
            ((Integer) objArr[2]).intValue();
            return method.invoke(obj, objArr);
        }
    }

    @TargetApi(26)
    /* loaded from: classes.dex */
    private static class isCredentialsUpdateSuggested extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "isCredentialsUpdateSuggested";
        }

        private isCredentialsUpdateSuggested() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            AccountManagerStub.Mgr.isCredentialsUpdateSuggested((IAccountManagerResponse) objArr[0], (Account) objArr[1], (String) objArr[2]);
            return 0;
        }
    }

    @TargetApi(26)
    /* loaded from: classes.dex */
    private static class finishSessionAsUser extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "finishSessionAsUser";
        }

        private finishSessionAsUser() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            AccountManagerStub.Mgr.finishSessionAsUser((IAccountManagerResponse) objArr[0], (Bundle) objArr[1], ((Boolean) objArr[2]).booleanValue(), (Bundle) objArr[3], ((Integer) objArr[4]).intValue());
            return 0;
        }
    }

    @TargetApi(26)
    /* loaded from: classes.dex */
    private static class getAccountVisibility extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getAccountVisibility";
        }

        private getAccountVisibility() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return Integer.valueOf(AccountManagerStub.Mgr.getAccountVisibility((Account) objArr[0], (String) objArr[1]));
        }
    }

    @TargetApi(26)
    /* loaded from: classes.dex */
    private static class addAccountExplicitlyWithVisibility extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "addAccountExplicitlyWithVisibility";
        }

        private addAccountExplicitlyWithVisibility() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return Boolean.valueOf(AccountManagerStub.Mgr.addAccountExplicitlyWithVisibility((Account) objArr[0], (String) objArr[1], (Bundle) objArr[2], (Map) objArr[3]));
        }
    }

    @TargetApi(26)
    /* loaded from: classes.dex */
    private static class getAccountsAndVisibilityForPackage extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getAccountsAndVisibilityForPackage";
        }

        private getAccountsAndVisibilityForPackage() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return AccountManagerStub.Mgr.getAccountsAndVisibilityForPackage((String) objArr[0], (String) objArr[1]);
        }
    }

    @TargetApi(26)
    /* loaded from: classes.dex */
    private static class getPackagesAndVisibilityForAccount extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getPackagesAndVisibilityForAccount";
        }

        private getPackagesAndVisibilityForAccount() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return AccountManagerStub.Mgr.getPackagesAndVisibilityForAccount((Account) objArr[0]);
        }
    }

    @TargetApi(26)
    /* loaded from: classes.dex */
    private static class setAccountVisibility extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "setAccountVisibility";
        }

        private setAccountVisibility() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return Boolean.valueOf(AccountManagerStub.Mgr.setAccountVisibility((Account) objArr[0], (String) objArr[1], ((Integer) objArr[2]).intValue()));
        }
    }

    @TargetApi(26)
    /* loaded from: classes.dex */
    private static class startAddAccountSession extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "startAddAccountSession";
        }

        private startAddAccountSession() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            AccountManagerStub.Mgr.startAddAccountSession((IAccountManagerResponse) objArr[0], (String) objArr[1], (String) objArr[2], (String[]) objArr[3], ((Boolean) objArr[4]).booleanValue(), (Bundle) objArr[5]);
            return 0;
        }
    }

    @TargetApi(26)
    /* loaded from: classes.dex */
    private static class startUpdateCredentialsSession extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "startUpdateCredentialsSession";
        }

        private startUpdateCredentialsSession() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            AccountManagerStub.Mgr.startUpdateCredentialsSession((IAccountManagerResponse) objArr[0], (Account) objArr[1], (String) objArr[2], ((Boolean) objArr[3]).booleanValue(), (Bundle) objArr[4]);
            return 0;
        }
    }

    @TargetApi(26)
    /* loaded from: classes.dex */
    private static class registerAccountListener extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "registerAccountListener";
        }

        private registerAccountListener() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            AccountManagerStub.Mgr.registerAccountListener((String[]) objArr[0], (String) objArr[1]);
            return 0;
        }
    }

    @TargetApi(26)
    /* loaded from: classes.dex */
    private static class unregisterAccountListener extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "unregisterAccountListener";
        }

        private unregisterAccountListener() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            AccountManagerStub.Mgr.unregisterAccountListener((String[]) objArr[0], (String) objArr[1]);
            return 0;
        }
    }
}
