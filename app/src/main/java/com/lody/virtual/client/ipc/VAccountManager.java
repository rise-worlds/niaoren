package com.lody.virtual.client.ipc;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorDescription;
import android.accounts.IAccountManagerResponse;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import com.lody.virtual.client.env.VirtualRuntime;
import com.lody.virtual.client.stub.AmsTask;
import com.lody.virtual.helper.utils.IInterfaceUtils;
import com.lody.virtual.os.VUserHandle;
import com.lody.virtual.server.interfaces.IAccountManager;
import java.util.Map;

/* loaded from: classes.dex */
public class VAccountManager {
    private static VAccountManager sMgr = new VAccountManager();
    private IAccountManager mService;

    public static VAccountManager get() {
        return sMgr;
    }

    public IAccountManager getService() {
        IAccountManager iAccountManager = this.mService;
        if (iAccountManager == null || !IInterfaceUtils.isAlive(iAccountManager)) {
            synchronized (VAccountManager.class) {
                this.mService = (IAccountManager) LocalProxyUtils.genProxy(IAccountManager.class, getStubInterface());
            }
        }
        return this.mService;
    }

    private Object getStubInterface() {
        return IAccountManager.Stub.asInterface(ServiceManagerNative.getService("account"));
    }

    public AuthenticatorDescription[] getAuthenticatorTypes() {
        try {
            return getService().getAuthenticatorTypes(VUserHandle.myUserId());
        } catch (RemoteException e) {
            return (AuthenticatorDescription[]) VirtualRuntime.crash(e);
        }
    }

    public void removeAccount(IAccountManagerResponse iAccountManagerResponse, Account account, boolean z) {
        try {
            getService().removeAccount(VUserHandle.myUserId(), iAccountManagerResponse, account, z);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getAuthToken(IAccountManagerResponse iAccountManagerResponse, Account account, String str, boolean z, boolean z2, Bundle bundle) {
        try {
            getService().getAuthToken(VUserHandle.myUserId(), iAccountManagerResponse, account, str, z, z2, bundle);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean addAccountExplicitly(Account account, String str, Bundle bundle) {
        try {
            return getService().addAccountExplicitly(VUserHandle.myUserId(), account, str, bundle);
        } catch (RemoteException e) {
            return ((Boolean) VirtualRuntime.crash(e)).booleanValue();
        }
    }

    public Account[] getAccounts(int i, String str) {
        try {
            return getService().getAccounts(i, str);
        } catch (RemoteException e) {
            return (Account[]) VirtualRuntime.crash(e);
        }
    }

    public Account[] getAccounts(String str) {
        try {
            return getService().getAccounts(VUserHandle.myUserId(), str);
        } catch (RemoteException e) {
            return (Account[]) VirtualRuntime.crash(e);
        }
    }

    public String peekAuthToken(Account account, String str) {
        try {
            return getService().peekAuthToken(VUserHandle.myUserId(), account, str);
        } catch (RemoteException e) {
            return (String) VirtualRuntime.crash(e);
        }
    }

    public String getPreviousName(Account account) {
        try {
            return getService().getPreviousName(VUserHandle.myUserId(), account);
        } catch (RemoteException e) {
            return (String) VirtualRuntime.crash(e);
        }
    }

    public void hasFeatures(IAccountManagerResponse iAccountManagerResponse, Account account, String[] strArr) {
        try {
            getService().hasFeatures(VUserHandle.myUserId(), iAccountManagerResponse, account, strArr);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean accountAuthenticated(Account account) {
        try {
            return getService().accountAuthenticated(VUserHandle.myUserId(), account);
        } catch (RemoteException e) {
            return ((Boolean) VirtualRuntime.crash(e)).booleanValue();
        }
    }

    public void clearPassword(Account account) {
        try {
            getService().clearPassword(VUserHandle.myUserId(), account);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void renameAccount(IAccountManagerResponse iAccountManagerResponse, Account account, String str) {
        try {
            getService().renameAccount(VUserHandle.myUserId(), iAccountManagerResponse, account, str);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setPassword(Account account, String str) {
        try {
            getService().setPassword(VUserHandle.myUserId(), account, str);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void addAccount(int i, IAccountManagerResponse iAccountManagerResponse, String str, String str2, String[] strArr, boolean z, Bundle bundle) {
        try {
            getService().addAccount(i, iAccountManagerResponse, str, str2, strArr, z, bundle);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void addAccount(IAccountManagerResponse iAccountManagerResponse, String str, String str2, String[] strArr, boolean z, Bundle bundle) {
        try {
            getService().addAccount(VUserHandle.myUserId(), iAccountManagerResponse, str, str2, strArr, z, bundle);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void updateCredentials(IAccountManagerResponse iAccountManagerResponse, Account account, String str, boolean z, Bundle bundle) {
        try {
            getService().updateCredentials(VUserHandle.myUserId(), iAccountManagerResponse, account, str, z, bundle);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean removeAccountExplicitly(Account account) {
        try {
            return getService().removeAccountExplicitly(VUserHandle.myUserId(), account);
        } catch (RemoteException e) {
            return ((Boolean) VirtualRuntime.crash(e)).booleanValue();
        }
    }

    public void setUserData(Account account, String str, String str2) {
        try {
            getService().setUserData(VUserHandle.myUserId(), account, str, str2);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void editProperties(IAccountManagerResponse iAccountManagerResponse, String str, boolean z) {
        try {
            getService().editProperties(VUserHandle.myUserId(), iAccountManagerResponse, str, z);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getAuthTokenLabel(IAccountManagerResponse iAccountManagerResponse, String str, String str2) {
        try {
            getService().getAuthTokenLabel(VUserHandle.myUserId(), iAccountManagerResponse, str, str2);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void confirmCredentials(IAccountManagerResponse iAccountManagerResponse, Account account, Bundle bundle, boolean z) {
        try {
            getService().confirmCredentials(VUserHandle.myUserId(), iAccountManagerResponse, account, bundle, z);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void invalidateAuthToken(String str, String str2) {
        try {
            getService().invalidateAuthToken(VUserHandle.myUserId(), str, str2);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getAccountsByFeatures(IAccountManagerResponse iAccountManagerResponse, String str, String[] strArr) {
        try {
            getService().getAccountsByFeatures(VUserHandle.myUserId(), iAccountManagerResponse, str, strArr);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setAuthToken(Account account, String str, String str2) {
        try {
            getService().setAuthToken(VUserHandle.myUserId(), account, str, str2);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public Object getPassword(Account account) {
        try {
            return getService().getPassword(VUserHandle.myUserId(), account);
        } catch (RemoteException e) {
            return VirtualRuntime.crash(e);
        }
    }

    public String getUserData(Account account, String str) {
        try {
            return getService().getUserData(VUserHandle.myUserId(), account, str);
        } catch (RemoteException e) {
            return (String) VirtualRuntime.crash(e);
        }
    }

    public AccountManagerFuture<Bundle> addAccount(final int i, final String str, final String str2, final String[] strArr, Bundle bundle, final Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        if (str != null) {
            final Bundle bundle2 = new Bundle();
            if (bundle != null) {
                bundle2.putAll(bundle);
            }
            bundle2.putString("androidPackageName", "android");
            return new AmsTask(activity, handler, accountManagerCallback) { // from class: com.lody.virtual.client.ipc.VAccountManager.1
                @Override // com.lody.virtual.client.stub.AmsTask
                public void doWork() throws RemoteException {
                    VAccountManager.this.addAccount(i, this.mResponse, str, str2, strArr, activity != null, bundle2);
                }
            }.start();
        }
        throw new IllegalArgumentException("accountType is null");
    }

    public boolean setAccountVisibility(Account account, String str, int i) {
        try {
            return getService().setAccountVisibility(VUserHandle.myUserId(), account, str, i);
        } catch (RemoteException e) {
            return ((Boolean) VirtualRuntime.crash(e)).booleanValue();
        }
    }

    public int getAccountVisibility(Account account, String str) {
        try {
            return getService().getAccountVisibility(VUserHandle.myUserId(), account, str);
        } catch (RemoteException e) {
            return ((Integer) VirtualRuntime.crash(e)).intValue();
        }
    }

    public void startAddAccountSession(IAccountManagerResponse iAccountManagerResponse, String str, String str2, String[] strArr, boolean z, Bundle bundle) {
        try {
            getService().startAddAccountSession(iAccountManagerResponse, str, str2, strArr, z, bundle);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public void startUpdateCredentialsSession(IAccountManagerResponse iAccountManagerResponse, Account account, String str, boolean z, Bundle bundle) {
        try {
            getService().startUpdateCredentialsSession(iAccountManagerResponse, account, str, z, bundle);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public void registerAccountListener(String[] strArr, String str) {
        try {
            getService().registerAccountListener(strArr, str);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public void unregisterAccountListener(String[] strArr, String str) {
        try {
            getService().unregisterAccountListener(strArr, str);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public Map getPackagesAndVisibilityForAccount(Account account) {
        try {
            return getService().getPackagesAndVisibilityForAccount(VUserHandle.myUserId(), account);
        } catch (RemoteException e) {
            return (Map) VirtualRuntime.crash(e);
        }
    }

    public Map getAccountsAndVisibilityForPackage(String str, String str2) {
        try {
            return getService().getAccountsAndVisibilityForPackage(VUserHandle.myUserId(), str, str2);
        } catch (RemoteException e) {
            return (Map) VirtualRuntime.crash(e);
        }
    }

    public void finishSessionAsUser(IAccountManagerResponse iAccountManagerResponse, Bundle bundle, boolean z, Bundle bundle2, int i) {
        try {
            getService().finishSessionAsUser(iAccountManagerResponse, bundle, z, bundle2, i);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public void isCredentialsUpdateSuggested(IAccountManagerResponse iAccountManagerResponse, Account account, String str) {
        try {
            getService().isCredentialsUpdateSuggested(iAccountManagerResponse, account, str);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public boolean addAccountExplicitlyWithVisibility(Account account, String str, Bundle bundle, Map map) {
        try {
            return getService().addAccountExplicitlyWithVisibility(VUserHandle.myUserId(), account, str, bundle, map);
        } catch (RemoteException e) {
            return ((Boolean) VirtualRuntime.crash(e)).booleanValue();
        }
    }
}
