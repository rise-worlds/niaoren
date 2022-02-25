package com.lody.virtual.server.accounts;

import android.accounts.Account;
import android.accounts.AuthenticatorDescription;
import android.accounts.IAccountAuthenticator;
import android.accounts.IAccountAuthenticatorResponse;
import android.accounts.IAccountManagerResponse;
import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.helper.compat.AccountManagerCompat;
import com.lody.virtual.helper.utils.Singleton;
import com.lody.virtual.helper.utils.VLog;
import com.lody.virtual.p061os.VBinder;
import com.lody.virtual.p061os.VEnvironment;
import com.lody.virtual.p061os.VUserHandle;
import com.lody.virtual.server.interfaces.IAccountManager;
import com.lody.virtual.server.p062am.VActivityManagerService;
import com.lody.virtual.server.p063pm.VPackageManagerService;
import com.tendcloud.tenddata.C2986is;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import p110z1.R_Hide;

/* loaded from: classes.dex */
public class VAccountManagerService extends IAccountManager.Stub {
    private static final long CHECK_IN_TIME = 43200000;
    private static final Singleton<VAccountManagerService> sInstance = new Singleton<VAccountManagerService>() { // from class: com.lody.virtual.server.accounts.VAccountManagerService.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.lody.virtual.helper.utils.Singleton
        public VAccountManagerService create() {
            return new VAccountManagerService();
        }
    };
    private static final String TAG = VAccountManagerService.class.getSimpleName();
    private final SparseArray<List<VAccount>> accountsByUserId = new SparseArray<>();
    private final SparseArray<List<VAccountVisibility>> accountsVisibilitiesByUserId = new SparseArray<>();
    private final LinkedList<AuthTokenRecord> authTokenRecords = new LinkedList<>();
    private final LinkedHashMap<String, Session> mSessions = new LinkedHashMap<>();
    private final AuthenticatorCache cache = new AuthenticatorCache();
    private Context mContext = VirtualCore.get().getContext();
    private long lastAccountChangeTime = 0;

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public void finishSessionAsUser(IAccountManagerResponse iAccountManagerResponse, Bundle bundle, boolean z, Bundle bundle2, int i) throws RemoteException {
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public void isCredentialsUpdateSuggested(IAccountManagerResponse iAccountManagerResponse, Account account, String str) throws RemoteException {
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public void registerAccountListener(String[] strArr, String str) throws RemoteException {
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public void startAddAccountSession(IAccountManagerResponse iAccountManagerResponse, String str, String str2, String[] strArr, boolean z, Bundle bundle) throws RemoteException {
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public void startUpdateCredentialsSession(IAccountManagerResponse iAccountManagerResponse, Account account, String str, boolean z, Bundle bundle) throws RemoteException {
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public void unregisterAccountListener(String[] strArr, String str) throws RemoteException {
    }

    public static VAccountManagerService get() {
        return sInstance.get();
    }

    public static void systemReady() {
        get().readAllAccounts();
        get().readAllAccountVisibilities();
    }

    private static AuthenticatorDescription parseAuthenticatorDescription(Resources resources, String str, AttributeSet attributeSet) {
        TypedArray obtainAttributes = resources.obtainAttributes(attributeSet, R_Hide.C5192d.AccountAuthenticator.get());
        try {
            String string = obtainAttributes.getString(R_Hide.C5192d.AccountAuthenticator_accountType.get());
            int resourceId = obtainAttributes.getResourceId(R_Hide.C5192d.AccountAuthenticator_label.get(), 0);
            int resourceId2 = obtainAttributes.getResourceId(R_Hide.C5192d.AccountAuthenticator_icon.get(), 0);
            int resourceId3 = obtainAttributes.getResourceId(R_Hide.C5192d.AccountAuthenticator_smallIcon.get(), 0);
            int resourceId4 = obtainAttributes.getResourceId(R_Hide.C5192d.AccountAuthenticator_accountPreferences.get(), 0);
            boolean z = obtainAttributes.getBoolean(R_Hide.C5192d.AccountAuthenticator_customTokens.get(), false);
            if (!TextUtils.isEmpty(string)) {
                return new AuthenticatorDescription(string, str, resourceId, resourceId2, resourceId3, resourceId4, z);
            }
            return null;
        } finally {
            obtainAttributes.recycle();
        }
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public AuthenticatorDescription[] getAuthenticatorTypes(int i) {
        AuthenticatorDescription[] authenticatorDescriptionArr;
        synchronized (this.cache) {
            authenticatorDescriptionArr = new AuthenticatorDescription[this.cache.authenticators.size()];
            int i2 = 0;
            for (AuthenticatorInfo authenticatorInfo : this.cache.authenticators.values()) {
                authenticatorDescriptionArr[i2] = authenticatorInfo.desc;
                i2++;
            }
        }
        return authenticatorDescriptionArr;
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public void getAccountsByFeatures(int i, IAccountManagerResponse iAccountManagerResponse, String str, String[] strArr) {
        if (iAccountManagerResponse == null) {
            throw new IllegalArgumentException("response is null");
        } else if (str != null) {
            AuthenticatorInfo authenticatorInfo = getAuthenticatorInfo(str);
            if (authenticatorInfo == null) {
                Bundle bundle = new Bundle();
                bundle.putParcelableArray(C2986is.f14279a, new Account[0]);
                try {
                    iAccountManagerResponse.onResult(bundle);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else if (strArr == null || strArr.length == 0) {
                Bundle bundle2 = new Bundle();
                bundle2.putParcelableArray(C2986is.f14279a, getAccounts(i, str));
                try {
                    iAccountManagerResponse.onResult(bundle2);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            } else {
                new GetAccountsByTypeAndFeatureSession(iAccountManagerResponse, i, authenticatorInfo, strArr).bind();
            }
        } else {
            throw new IllegalArgumentException("accountType is null");
        }
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public final String getPreviousName(int i, Account account) {
        String str;
        if (account != null) {
            synchronized (this.accountsByUserId) {
                str = null;
                VAccount account2 = getAccount(i, account);
                if (account2 != null) {
                    str = account2.previousName;
                }
            }
            return str;
        }
        throw new IllegalArgumentException("account is null");
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public Account[] getAccounts(int i, String str) {
        List<Account> accountList = getAccountList(i, str);
        return (Account[]) accountList.toArray(new Account[accountList.size()]);
    }

    private List<Account> getAccountList(int i, String str) {
        ArrayList arrayList;
        synchronized (this.accountsByUserId) {
            arrayList = new ArrayList();
            List<VAccount> list = this.accountsByUserId.get(i);
            if (list != null) {
                for (VAccount vAccount : list) {
                    if (str == null || vAccount.type.equals(str)) {
                        arrayList.add(new Account(vAccount.name, vAccount.type));
                    }
                }
            }
        }
        return arrayList;
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public final void getAuthToken(final int i, IAccountManagerResponse iAccountManagerResponse, final Account account, final String str, final boolean z, boolean z2, final Bundle bundle) {
        String customAuthToken;
        VAccount account2;
        if (iAccountManagerResponse != null) {
            try {
                if (account == null) {
                    VLog.m18986w(TAG, "getAuthToken called with null account", new Object[0]);
                    iAccountManagerResponse.onError(7, "account is null");
                } else if (str == null) {
                    VLog.m18986w(TAG, "getAuthToken called with null authTokenType", new Object[0]);
                    iAccountManagerResponse.onError(7, "authTokenType is null");
                } else {
                    AuthenticatorInfo authenticatorInfo = getAuthenticatorInfo(account.type);
                    if (authenticatorInfo == null) {
                        try {
                            iAccountManagerResponse.onError(7, "account.type does not exist");
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    } else {
                        final String string = bundle.getString("androidPackageName");
                        final boolean z3 = authenticatorInfo.desc.customTokens;
                        bundle.putInt("callerUid", VBinder.getCallingUid());
                        bundle.putInt("callerPid", VBinder.getCallingPid());
                        if (z) {
                            bundle.putBoolean(AccountManagerCompat.KEY_NOTIFY_ON_FAILURE, true);
                        }
                        if (!z3) {
                            synchronized (this.accountsByUserId) {
                                account2 = getAccount(i, account);
                            }
                            String str2 = account2 != null ? account2.authTokens.get(str) : null;
                            if (str2 != null) {
                                Bundle bundle2 = new Bundle();
                                bundle2.putString("authtoken", str2);
                                bundle2.putString("authAccount", account.name);
                                bundle2.putString("accountType", account.type);
                                onResult(iAccountManagerResponse, bundle2);
                                return;
                            }
                        }
                        if (!z3 || (customAuthToken = getCustomAuthToken(i, account, str, string)) == null) {
                            new Session(iAccountManagerResponse, i, authenticatorInfo, z2, false, account.name) { // from class: com.lody.virtual.server.accounts.VAccountManagerService.2
                                @Override // com.lody.virtual.server.accounts.VAccountManagerService.Session
                                protected String toDebugString(long j) {
                                    return super.toDebugString(j) + ", getAuthToken, " + account + ", authTokenType " + str + ", loginOptions " + bundle + ", notifyOnAuthFailure " + z;
                                }

                                @Override // com.lody.virtual.server.accounts.VAccountManagerService.Session
                                public void run() throws RemoteException {
                                    this.mAuthenticator.getAuthToken(this, account, str, bundle);
                                }

                                @Override // com.lody.virtual.server.accounts.VAccountManagerService.Session, android.accounts.IAccountAuthenticatorResponse
                                public void onResult(Bundle bundle3) throws RemoteException {
                                    if (bundle3 != null) {
                                        String string2 = bundle3.getString("authtoken");
                                        if (string2 != null) {
                                            String string3 = bundle3.getString("authAccount");
                                            String string4 = bundle3.getString("accountType");
                                            if (TextUtils.isEmpty(string4) || TextUtils.isEmpty(string3)) {
                                                onError(5, "the type and name should not be empty");
                                                return;
                                            }
                                            if (!z3) {
                                                synchronized (VAccountManagerService.this.accountsByUserId) {
                                                    if (VAccountManagerService.this.getAccount(i, string3, string4) == null) {
                                                        List list = (List) VAccountManagerService.this.accountsByUserId.get(i);
                                                        if (list == null) {
                                                            list = new ArrayList();
                                                            VAccountManagerService.this.accountsByUserId.put(i, list);
                                                        }
                                                        list.add(new VAccount(i, new Account(string3, string4)));
                                                        VAccountManagerService.this.saveAllAccounts();
                                                    }
                                                }
                                            }
                                            long j = bundle3.getLong(AccountManagerCompat.KEY_CUSTOM_TOKEN_EXPIRY, 0L);
                                            if (z3 && j > System.currentTimeMillis()) {
                                                AuthTokenRecord authTokenRecord = new AuthTokenRecord(i, account, str, string, string2, j);
                                                synchronized (VAccountManagerService.this.authTokenRecords) {
                                                    VAccountManagerService.this.authTokenRecords.remove(authTokenRecord);
                                                    VAccountManagerService.this.authTokenRecords.add(authTokenRecord);
                                                }
                                            }
                                        }
                                        if (((Intent) bundle3.getParcelable("intent")) != null && z) {
                                            boolean z4 = z3;
                                        }
                                    }
                                    super.onResult(bundle3);
                                }
                            }.bind();
                            return;
                        }
                        Bundle bundle3 = new Bundle();
                        bundle3.putString("authtoken", customAuthToken);
                        bundle3.putString("authAccount", account.name);
                        bundle3.putString("accountType", account.type);
                        onResult(iAccountManagerResponse, bundle3);
                    }
                }
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("response is null");
        }
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public void setPassword(int i, Account account, String str) {
        if (account != null) {
            setPasswordInternal(i, account, str);
            return;
        }
        throw new IllegalArgumentException("account is null");
    }

    private void setPasswordInternal(int i, Account account, String str) {
        synchronized (this.accountsByUserId) {
            VAccount account2 = getAccount(i, account);
            if (account2 != null) {
                account2.password = str;
                account2.authTokens.clear();
                saveAllAccounts();
                synchronized (this.authTokenRecords) {
                    Iterator<AuthTokenRecord> it = this.authTokenRecords.iterator();
                    while (it.hasNext()) {
                        AuthTokenRecord next = it.next();
                        if (next.userId == i && next.account.equals(account)) {
                            it.remove();
                        }
                    }
                }
                sendAccountsChangedBroadcast(i);
            }
        }
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public void setAuthToken(int i, Account account, String str, String str2) {
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (str != null) {
            synchronized (this.accountsByUserId) {
                VAccount account2 = getAccount(i, account);
                if (account2 != null) {
                    account2.authTokens.put(str, str2);
                    saveAllAccounts();
                }
            }
        } else {
            throw new IllegalArgumentException("authTokenType is null");
        }
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public void setUserData(int i, Account account, String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("key is null");
        } else if (account != null) {
            VAccount account2 = getAccount(i, account);
            if (account2 != null) {
                synchronized (this.accountsByUserId) {
                    account2.userDatas.put(str, str2);
                    saveAllAccounts();
                }
            }
        } else {
            throw new IllegalArgumentException("account is null");
        }
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public void hasFeatures(int i, IAccountManagerResponse iAccountManagerResponse, final Account account, final String[] strArr) {
        if (iAccountManagerResponse == null) {
            throw new IllegalArgumentException("response is null");
        } else if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (strArr != null) {
            AuthenticatorInfo authenticatorInfo = getAuthenticatorInfo(account.type);
            if (authenticatorInfo == null) {
                try {
                    iAccountManagerResponse.onError(7, "account.type does not exist");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else {
                new Session(iAccountManagerResponse, i, authenticatorInfo, false, true, account.name) { // from class: com.lody.virtual.server.accounts.VAccountManagerService.3
                    @Override // com.lody.virtual.server.accounts.VAccountManagerService.Session
                    public void run() throws RemoteException {
                        try {
                            this.mAuthenticator.hasFeatures(this, account, strArr);
                        } catch (RemoteException unused) {
                            onError(1, "remote exception");
                        }
                    }

                    @Override // com.lody.virtual.server.accounts.VAccountManagerService.Session, android.accounts.IAccountAuthenticatorResponse
                    public void onResult(Bundle bundle) throws RemoteException {
                        IAccountManagerResponse responseAndClose = getResponseAndClose();
                        if (responseAndClose != null) {
                            try {
                                if (bundle == null) {
                                    responseAndClose.onError(5, "null bundle");
                                    return;
                                }
                                String str = VAccountManagerService.TAG;
                                Log.v(str, getClass().getSimpleName() + " calling onResult() on response " + responseAndClose);
                                Bundle bundle2 = new Bundle();
                                bundle2.putBoolean("booleanResult", bundle.getBoolean("booleanResult", false));
                                responseAndClose.onResult(bundle2);
                            } catch (RemoteException e2) {
                                Log.v(VAccountManagerService.TAG, "failure while notifying response", e2);
                            }
                        }
                    }
                }.bind();
            }
        } else {
            throw new IllegalArgumentException("features is null");
        }
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public void updateCredentials(int i, IAccountManagerResponse iAccountManagerResponse, final Account account, final String str, boolean z, final Bundle bundle) {
        if (iAccountManagerResponse == null) {
            throw new IllegalArgumentException("response is null");
        } else if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (str != null) {
            AuthenticatorInfo authenticatorInfo = getAuthenticatorInfo(account.type);
            if (authenticatorInfo == null) {
                try {
                    iAccountManagerResponse.onError(7, "account.type does not exist");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else {
                new Session(iAccountManagerResponse, i, authenticatorInfo, z, false, account.name) { // from class: com.lody.virtual.server.accounts.VAccountManagerService.4
                    @Override // com.lody.virtual.server.accounts.VAccountManagerService.Session
                    public void run() throws RemoteException {
                        this.mAuthenticator.updateCredentials(this, account, str, bundle);
                    }

                    @Override // com.lody.virtual.server.accounts.VAccountManagerService.Session
                    protected String toDebugString(long j) {
                        Bundle bundle2 = bundle;
                        if (bundle2 != null) {
                            bundle2.keySet();
                        }
                        return super.toDebugString(j) + ", updateCredentials, " + account + ", authTokenType " + str + ", loginOptions " + bundle;
                    }
                }.bind();
            }
        } else {
            throw new IllegalArgumentException("authTokenType is null");
        }
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public String getPassword(int i, Account account) {
        if (account != null) {
            synchronized (this.accountsByUserId) {
                VAccount account2 = getAccount(i, account);
                if (account2 == null) {
                    return null;
                }
                return account2.password;
            }
        }
        throw new IllegalArgumentException("account is null");
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public String getUserData(int i, Account account, String str) {
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (str != null) {
            synchronized (this.accountsByUserId) {
                VAccount account2 = getAccount(i, account);
                if (account2 == null) {
                    return null;
                }
                return account2.userDatas.get(str);
            }
        } else {
            throw new IllegalArgumentException("key is null");
        }
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public void editProperties(int i, IAccountManagerResponse iAccountManagerResponse, final String str, boolean z) {
        if (iAccountManagerResponse == null) {
            throw new IllegalArgumentException("response is null");
        } else if (str != null) {
            AuthenticatorInfo authenticatorInfo = getAuthenticatorInfo(str);
            if (authenticatorInfo == null) {
                try {
                    iAccountManagerResponse.onError(7, "account.type does not exist");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else {
                new Session(iAccountManagerResponse, i, authenticatorInfo, z, true, null) { // from class: com.lody.virtual.server.accounts.VAccountManagerService.5
                    @Override // com.lody.virtual.server.accounts.VAccountManagerService.Session
                    public void run() throws RemoteException {
                        this.mAuthenticator.editProperties(this, this.mAuthenticatorInfo.desc.type);
                    }

                    @Override // com.lody.virtual.server.accounts.VAccountManagerService.Session
                    protected String toDebugString(long j) {
                        return super.toDebugString(j) + ", editProperties, accountType " + str;
                    }
                }.bind();
            }
        } else {
            throw new IllegalArgumentException("accountType is null");
        }
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public void getAuthTokenLabel(int i, IAccountManagerResponse iAccountManagerResponse, String str, final String str2) {
        if (str == null) {
            throw new IllegalArgumentException("accountType is null");
        } else if (str2 != null) {
            AuthenticatorInfo authenticatorInfo = getAuthenticatorInfo(str);
            if (authenticatorInfo == null) {
                try {
                    iAccountManagerResponse.onError(7, "account.type does not exist");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else {
                new Session(iAccountManagerResponse, i, authenticatorInfo, false, false, null) { // from class: com.lody.virtual.server.accounts.VAccountManagerService.6
                    @Override // com.lody.virtual.server.accounts.VAccountManagerService.Session
                    public void run() throws RemoteException {
                        this.mAuthenticator.getAuthTokenLabel(this, str2);
                    }

                    @Override // com.lody.virtual.server.accounts.VAccountManagerService.Session, android.accounts.IAccountAuthenticatorResponse
                    public void onResult(Bundle bundle) throws RemoteException {
                        if (bundle != null) {
                            String string = bundle.getString("authTokenLabelKey");
                            Bundle bundle2 = new Bundle();
                            bundle2.putString("authTokenLabelKey", string);
                            super.onResult(bundle2);
                            return;
                        }
                        super.onResult(null);
                    }
                }.bind();
            }
        } else {
            throw new IllegalArgumentException("authTokenType is null");
        }
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public void confirmCredentials(int i, IAccountManagerResponse iAccountManagerResponse, final Account account, final Bundle bundle, boolean z) {
        if (iAccountManagerResponse == null) {
            throw new IllegalArgumentException("response is null");
        } else if (account != null) {
            AuthenticatorInfo authenticatorInfo = getAuthenticatorInfo(account.type);
            if (authenticatorInfo == null) {
                try {
                    iAccountManagerResponse.onError(7, "account.type does not exist");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else {
                new Session(iAccountManagerResponse, i, authenticatorInfo, z, true, account.name, true, true) { // from class: com.lody.virtual.server.accounts.VAccountManagerService.7
                    @Override // com.lody.virtual.server.accounts.VAccountManagerService.Session
                    public void run() throws RemoteException {
                        this.mAuthenticator.confirmCredentials(this, account, bundle);
                    }
                }.bind();
            }
        } else {
            throw new IllegalArgumentException("account is null");
        }
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public void addAccount(int i, IAccountManagerResponse iAccountManagerResponse, final String str, final String str2, final String[] strArr, boolean z, final Bundle bundle) {
        if (iAccountManagerResponse == null) {
            throw new IllegalArgumentException("response is null");
        } else if (str != null) {
            AuthenticatorInfo authenticatorInfo = getAuthenticatorInfo(str);
            if (authenticatorInfo == null) {
                try {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("authtoken", str2);
                    bundle2.putString("accountType", str);
                    bundle2.putBoolean("booleanResult", false);
                    iAccountManagerResponse.onResult(bundle2);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else {
                new Session(iAccountManagerResponse, i, authenticatorInfo, z, true, null, false, true) { // from class: com.lody.virtual.server.accounts.VAccountManagerService.8
                    @Override // com.lody.virtual.server.accounts.VAccountManagerService.Session
                    public void run() throws RemoteException {
                        this.mAuthenticator.addAccount(this, this.mAuthenticatorInfo.desc.type, str2, strArr, bundle);
                    }

                    @Override // com.lody.virtual.server.accounts.VAccountManagerService.Session
                    protected String toDebugString(long j) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(super.toDebugString(j));
                        sb.append(", addAccount, accountType ");
                        sb.append(str);
                        sb.append(", requiredFeatures ");
                        String[] strArr2 = strArr;
                        sb.append(strArr2 != null ? TextUtils.join(",", strArr2) : null);
                        return sb.toString();
                    }
                }.bind();
            }
        } else {
            throw new IllegalArgumentException("accountType is null");
        }
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public boolean addAccountExplicitly(int i, Account account, String str, Bundle bundle) {
        if (account != null) {
            return insertAccountIntoDatabase(i, account, str, bundle);
        }
        throw new IllegalArgumentException("account is null");
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public boolean removeAccountExplicitly(int i, Account account) {
        return account != null && removeAccountInternal(i, account);
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public void renameAccount(int i, IAccountManagerResponse iAccountManagerResponse, Account account, String str) {
        if (account != null) {
            Account renameAccountInternal = renameAccountInternal(i, account, str);
            Bundle bundle = new Bundle();
            bundle.putString("authAccount", renameAccountInternal.name);
            bundle.putString("accountType", renameAccountInternal.type);
            try {
                iAccountManagerResponse.onResult(bundle);
            } catch (RemoteException e) {
                Log.w(TAG, e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("account is null");
        }
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public void removeAccount(final int i, IAccountManagerResponse iAccountManagerResponse, final Account account, boolean z) {
        if (iAccountManagerResponse == null) {
            throw new IllegalArgumentException("response is null");
        } else if (account != null) {
            AuthenticatorInfo authenticatorInfo = getAuthenticatorInfo(account.type);
            if (authenticatorInfo == null) {
                try {
                    iAccountManagerResponse.onError(7, "account.type does not exist");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else {
                new Session(iAccountManagerResponse, i, authenticatorInfo, z, true, account.name) { // from class: com.lody.virtual.server.accounts.VAccountManagerService.9
                    @Override // com.lody.virtual.server.accounts.VAccountManagerService.Session
                    protected String toDebugString(long j) {
                        return super.toDebugString(j) + ", removeAccount, account " + account;
                    }

                    @Override // com.lody.virtual.server.accounts.VAccountManagerService.Session
                    public void run() throws RemoteException {
                        this.mAuthenticator.getAccountRemovalAllowed(this, account);
                    }

                    @Override // com.lody.virtual.server.accounts.VAccountManagerService.Session, android.accounts.IAccountAuthenticatorResponse
                    public void onResult(Bundle bundle) throws RemoteException {
                        if (bundle != null && bundle.containsKey("booleanResult") && !bundle.containsKey("intent")) {
                            boolean z2 = bundle.getBoolean("booleanResult");
                            if (z2) {
                                VAccountManagerService.this.removeAccountInternal(i, account);
                            }
                            IAccountManagerResponse responseAndClose = getResponseAndClose();
                            if (responseAndClose != null) {
                                String str = VAccountManagerService.TAG;
                                Log.v(str, getClass().getSimpleName() + " calling onResult() on response " + responseAndClose);
                                Bundle bundle2 = new Bundle();
                                bundle2.putBoolean("booleanResult", z2);
                                try {
                                    responseAndClose.onResult(bundle2);
                                } catch (RemoteException e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }
                        super.onResult(bundle);
                    }
                }.bind();
            }
        } else {
            throw new IllegalArgumentException("account is null");
        }
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public void clearPassword(int i, Account account) {
        if (account != null) {
            setPasswordInternal(i, account, null);
            return;
        }
        throw new IllegalArgumentException("account is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean removeAccountInternal(int i, Account account) {
        List<VAccount> list = this.accountsByUserId.get(i);
        if (list == null) {
            return false;
        }
        Iterator<VAccount> it = list.iterator();
        while (it.hasNext()) {
            VAccount next = it.next();
            if (i == next.userId && TextUtils.equals(next.name, account.name) && TextUtils.equals(account.type, next.type)) {
                it.remove();
                saveAllAccounts();
                sendAccountsChangedBroadcast(i);
                return true;
            }
        }
        return false;
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public boolean accountAuthenticated(int i, Account account) {
        if (account != null) {
            synchronized (this.accountsByUserId) {
                VAccount account2 = getAccount(i, account);
                if (account2 == null) {
                    return false;
                }
                account2.lastAuthenticatedTime = System.currentTimeMillis();
                saveAllAccounts();
                return true;
            }
        }
        throw new IllegalArgumentException("account is null");
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public void invalidateAuthToken(int i, String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("accountType is null");
        } else if (str2 != null) {
            synchronized (this.accountsByUserId) {
                List<VAccount> list = this.accountsByUserId.get(i);
                if (list != null) {
                    boolean z = false;
                    for (VAccount vAccount : list) {
                        if (vAccount.type.equals(str)) {
                            vAccount.authTokens.values().remove(str2);
                            z = true;
                        }
                    }
                    if (z) {
                        saveAllAccounts();
                    }
                }
                synchronized (this.authTokenRecords) {
                    Iterator<AuthTokenRecord> it = this.authTokenRecords.iterator();
                    while (it.hasNext()) {
                        AuthTokenRecord next = it.next();
                        if (next.userId == i && next.authTokenType.equals(str) && next.authToken.equals(str2)) {
                            it.remove();
                        }
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("authToken is null");
        }
    }

    private Account renameAccountInternal(int i, Account account, String str) {
        synchronized (this.accountsByUserId) {
            VAccount account2 = getAccount(i, account);
            if (account2 == null) {
                return account;
            }
            account2.previousName = account2.name;
            account2.name = str;
            saveAllAccounts();
            Account account3 = new Account(account2.name, account2.type);
            synchronized (this.authTokenRecords) {
                Iterator<AuthTokenRecord> it = this.authTokenRecords.iterator();
                while (it.hasNext()) {
                    AuthTokenRecord next = it.next();
                    if (next.userId == i && next.account.equals(account)) {
                        next.account = account3;
                    }
                }
            }
            sendAccountsChangedBroadcast(i);
            return account3;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    public String peekAuthToken(int i, Account account, String str) {
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (str != null) {
            synchronized (this.accountsByUserId) {
                VAccount account2 = getAccount(i, account);
                if (account2 == null) {
                    return null;
                }
                return account2.authTokens.get(str);
            }
        } else {
            throw new IllegalArgumentException("authTokenType is null");
        }
    }

    private String getCustomAuthToken(int i, Account account, String str, String str2) {
        String str3;
        AuthTokenRecord authTokenRecord = new AuthTokenRecord(i, account, str, str2);
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this.authTokenRecords) {
            Iterator<AuthTokenRecord> it = this.authTokenRecords.iterator();
            str3 = null;
            while (it.hasNext()) {
                AuthTokenRecord next = it.next();
                if (next.expiryEpochMillis > 0 && next.expiryEpochMillis < currentTimeMillis) {
                    it.remove();
                } else if (authTokenRecord.equals(next)) {
                    str3 = authTokenRecord.authToken;
                }
            }
        }
        return str3;
    }

    private void onResult(IAccountManagerResponse iAccountManagerResponse, Bundle bundle) {
        try {
            iAccountManagerResponse.onResult(bundle);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private AuthenticatorInfo getAuthenticatorInfo(String str) {
        AuthenticatorInfo authenticatorInfo;
        synchronized (this.cache) {
            authenticatorInfo = str == null ? null : this.cache.authenticators.get(str);
        }
        return authenticatorInfo;
    }

    private VAccount getAccount(int i, Account account) {
        return getAccount(i, account.name, account.type);
    }

    private boolean insertAccountIntoDatabase(int i, Account account, String str, Bundle bundle) {
        if (account == null) {
            return false;
        }
        synchronized (this.accountsByUserId) {
            if (getAccount(i, account.name, account.type) != null) {
                return false;
            }
            VAccount vAccount = new VAccount(i, account);
            vAccount.password = str;
            if (bundle != null) {
                for (String str2 : bundle.keySet()) {
                    Object obj = bundle.get(str2);
                    if (obj instanceof String) {
                        vAccount.userDatas.put(str2, (String) obj);
                    }
                }
            }
            List<VAccount> list = this.accountsByUserId.get(i);
            if (list == null) {
                list = new ArrayList<>();
                this.accountsByUserId.put(i, list);
            }
            list.add(vAccount);
            saveAllAccounts();
            sendAccountsChangedBroadcast(vAccount.userId);
            return true;
        }
    }

    private void sendAccountsChangedBroadcast(int i) {
        VActivityManagerService.get().sendBroadcastAsUser(new Intent("android.accounts.LOGIN_ACCOUNTS_CHANGED"), new VUserHandle(i));
        broadcastCheckInNowIfNeed(i);
    }

    private void broadcastCheckInNowIfNeed(int i) {
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(currentTimeMillis - this.lastAccountChangeTime) > CHECK_IN_TIME) {
            this.lastAccountChangeTime = currentTimeMillis;
            saveAllAccounts();
            VActivityManagerService.get().sendBroadcastAsUser(new Intent("android.server.checkin.CHECKIN_NOW"), new VUserHandle(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveAllAccounts() {
        File accountConfigFile = VEnvironment.getAccountConfigFile();
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInt(1);
            ArrayList<VAccount> arrayList = new ArrayList();
            for (int i = 0; i < this.accountsByUserId.size(); i++) {
                List<VAccount> valueAt = this.accountsByUserId.valueAt(i);
                if (valueAt != null) {
                    arrayList.addAll(valueAt);
                }
            }
            obtain.writeInt(arrayList.size());
            for (VAccount vAccount : arrayList) {
                vAccount.writeToParcel(obtain, 0);
            }
            obtain.writeLong(this.lastAccountChangeTime);
            FileOutputStream fileOutputStream = new FileOutputStream(accountConfigFile);
            fileOutputStream.write(obtain.marshall());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        obtain.recycle();
    }

    private void readAllAccounts() {
        byte[] bArr;
        int read;
        File accountConfigFile = VEnvironment.getAccountConfigFile();
        refreshAuthenticatorCache(null);
        if (accountConfigFile.exists()) {
            this.accountsByUserId.clear();
            Parcel obtain = Parcel.obtain();
            try {
                try {
                    FileInputStream fileInputStream = new FileInputStream(accountConfigFile);
                    bArr = new byte[(int) accountConfigFile.length()];
                    read = fileInputStream.read(bArr);
                    fileInputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (read == bArr.length) {
                    obtain.unmarshall(bArr, 0, bArr.length);
                    obtain.setDataPosition(0);
                    obtain.readInt();
                    int readInt = obtain.readInt();
                    boolean z = false;
                    while (true) {
                        readInt--;
                        if (readInt <= 0) {
                            break;
                        }
                        VAccount vAccount = new VAccount(obtain);
                        VLog.m18993d(TAG, "Reading account : " + vAccount.type, new Object[0]);
                        if (this.cache.authenticators.get(vAccount.type) != null) {
                            List<VAccount> list = this.accountsByUserId.get(vAccount.userId);
                            if (list == null) {
                                list = new ArrayList<>();
                                this.accountsByUserId.put(vAccount.userId, list);
                            }
                            list.add(vAccount);
                        } else {
                            z = true;
                        }
                    }
                    this.lastAccountChangeTime = obtain.readLong();
                    if (z) {
                        saveAllAccounts();
                    }
                    return;
                }
                throw new IOException(String.format(Locale.ENGLISH, "Expect length %d, but got %d.", Integer.valueOf(bArr.length), Integer.valueOf(read)));
            } finally {
                obtain.recycle();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public VAccount getAccount(int i, String str, String str2) {
        List<VAccount> list = this.accountsByUserId.get(i);
        if (list == null) {
            return null;
        }
        for (VAccount vAccount : list) {
            if (TextUtils.equals(vAccount.name, str) && TextUtils.equals(vAccount.type, str2)) {
                return vAccount;
            }
        }
        return null;
    }

    public void refreshAuthenticatorCache(String str) {
        this.cache.authenticators.clear();
        Intent intent = new Intent("android.accounts.AccountAuthenticator");
        if (str != null) {
            intent.setPackage(str);
        }
        generateServicesMap(VPackageManagerService.get().queryIntentServices(intent, null, 128, 0), this.cache.authenticators, new RegisteredServicesParser());
    }

    private void generateServicesMap(List<ResolveInfo> list, Map<String, AuthenticatorInfo> map, RegisteredServicesParser registeredServicesParser) {
        AuthenticatorDescription parseAuthenticatorDescription;
        for (ResolveInfo resolveInfo : list) {
            XmlResourceParser parser = registeredServicesParser.getParser(this.mContext, resolveInfo.serviceInfo, "android.accounts.AccountAuthenticator");
            if (parser != null) {
                try {
                    AttributeSet asAttributeSet = Xml.asAttributeSet(parser);
                    while (true) {
                        int next = parser.next();
                        if (next == 1 || next == 2) {
                            break;
                        }
                    }
                    if ("account-authenticator".equals(parser.getName()) && (parseAuthenticatorDescription = parseAuthenticatorDescription(registeredServicesParser.getResources(this.mContext, resolveInfo.serviceInfo.applicationInfo), resolveInfo.serviceInfo.packageName, asAttributeSet)) != null) {
                        map.put(parseAuthenticatorDescription.type, new AuthenticatorInfo(parseAuthenticatorDescription, resolveInfo.serviceInfo));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @TargetApi(26)
    private boolean removeAccountVisibility(int i, Account account) {
        List<VAccountVisibility> list = this.accountsVisibilitiesByUserId.get(i);
        if (list == null) {
            return false;
        }
        Iterator<VAccountVisibility> it = list.iterator();
        while (it.hasNext()) {
            VAccountVisibility next = it.next();
            if (i == next.userId && TextUtils.equals(next.name, account.name) && TextUtils.equals(account.type, next.type)) {
                it.remove();
                saveAllAccountVisibilities();
                sendAccountsChangedBroadcast(i);
                return true;
            }
        }
        return false;
    }

    @TargetApi(26)
    private boolean renameAccountVisibility(int i, Account account, String str) {
        synchronized (this.accountsVisibilitiesByUserId) {
            VAccountVisibility accountVisibility = getAccountVisibility(i, account);
            if (accountVisibility == null) {
                return false;
            }
            accountVisibility.name = str;
            saveAllAccountVisibilities();
            sendAccountsChangedBroadcast(i);
            return true;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    @TargetApi(26)
    public Map<String, Integer> getPackagesAndVisibilityForAccount(int i, Account account) {
        VAccountVisibility accountVisibility = getAccountVisibility(i, account);
        if (accountVisibility != null) {
            return accountVisibility.visibility;
        }
        return null;
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    @TargetApi(26)
    public boolean addAccountExplicitlyWithVisibility(int i, Account account, String str, Bundle bundle, Map map) {
        if (account != null) {
            boolean insertAccountIntoDatabase = insertAccountIntoDatabase(i, account, str, bundle);
            insertAccountVisibilityIntoDatabase(i, account, map);
            return insertAccountIntoDatabase;
        }
        throw new IllegalArgumentException("account is null");
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    @TargetApi(26)
    public boolean setAccountVisibility(int i, Account account, String str, int i2) {
        VAccountVisibility accountVisibility = getAccountVisibility(i, account);
        if (accountVisibility == null) {
            return false;
        }
        accountVisibility.visibility.put(str, Integer.valueOf(i2));
        saveAllAccountVisibilities();
        sendAccountsChangedBroadcast(i);
        return true;
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    @TargetApi(26)
    public int getAccountVisibility(int i, Account account, String str) {
        VAccountVisibility accountVisibility = getAccountVisibility(i, account);
        if (accountVisibility == null || !accountVisibility.visibility.containsKey(str)) {
            return 0;
        }
        return accountVisibility.visibility.get(str).intValue();
    }

    @Override // com.lody.virtual.server.interfaces.IAccountManager
    @TargetApi(26)
    public Map<Account, Integer> getAccountsAndVisibilityForPackage(int i, String str, String str2) {
        HashMap hashMap = new HashMap();
        for (Account account : getAccountList(i, str2)) {
            VAccountVisibility accountVisibility = getAccountVisibility(i, account);
            if (accountVisibility != null && accountVisibility.visibility.containsKey(str)) {
                hashMap.put(account, accountVisibility.visibility.get(str));
            }
        }
        return hashMap;
    }

    @TargetApi(26)
    private boolean insertAccountVisibilityIntoDatabase(int i, Account account, Map<String, Integer> map) {
        if (account == null) {
            return false;
        }
        synchronized (this.accountsVisibilitiesByUserId) {
            VAccountVisibility vAccountVisibility = new VAccountVisibility(i, account, map);
            List<VAccountVisibility> list = this.accountsVisibilitiesByUserId.get(i);
            if (list == null) {
                list = new ArrayList<>();
                this.accountsVisibilitiesByUserId.put(i, list);
            }
            list.add(vAccountVisibility);
            saveAllAccountVisibilities();
            sendAccountsChangedBroadcast(vAccountVisibility.userId);
        }
        return true;
    }

    @TargetApi(26)
    private void saveAllAccountVisibilities() {
        File accountVisibilityConfigFile = VEnvironment.getAccountVisibilityConfigFile();
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInt(1);
            obtain.writeInt(this.accountsVisibilitiesByUserId.size());
            for (int i = 0; i < this.accountsVisibilitiesByUserId.size(); i++) {
                obtain.writeInt(i);
                List<VAccountVisibility> valueAt = this.accountsVisibilitiesByUserId.valueAt(i);
                if (valueAt == null) {
                    obtain.writeInt(0);
                } else {
                    obtain.writeInt(valueAt.size());
                    for (VAccountVisibility vAccountVisibility : valueAt) {
                        vAccountVisibility.writeToParcel(obtain, 0);
                    }
                }
            }
            obtain.writeLong(this.lastAccountChangeTime);
            FileOutputStream fileOutputStream = new FileOutputStream(accountVisibilityConfigFile);
            fileOutputStream.write(obtain.marshall());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        obtain.recycle();
    }

    @TargetApi(26)
    private void readAllAccountVisibilities() {
        File accountVisibilityConfigFile = VEnvironment.getAccountVisibilityConfigFile();
        Parcel obtain = Parcel.obtain();
        if (accountVisibilityConfigFile.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(accountVisibilityConfigFile);
                byte[] bArr = new byte[(int) accountVisibilityConfigFile.length()];
                int read = fileInputStream.read(bArr);
                fileInputStream.close();
                if (read == bArr.length) {
                    obtain.unmarshall(bArr, 0, bArr.length);
                    obtain.setDataPosition(0);
                    obtain.readInt();
                    int readInt = obtain.readInt();
                    for (int i = 0; i < readInt; i++) {
                        int readInt2 = obtain.readInt();
                        int readInt3 = obtain.readInt();
                        ArrayList arrayList = new ArrayList();
                        this.accountsVisibilitiesByUserId.put(readInt2, arrayList);
                        for (int i2 = 0; i2 < readInt3; i2++) {
                            arrayList.add(new VAccountVisibility(obtain));
                        }
                    }
                    this.lastAccountChangeTime = obtain.readLong();
                } else {
                    throw new IOException(String.format("Expect length %d, but got %d.", Integer.valueOf(bArr.length), Integer.valueOf(read)));
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        obtain.recycle();
    }

    @TargetApi(26)
    private VAccountVisibility getAccountVisibility(int i, String str, String str2) {
        List<VAccountVisibility> list = this.accountsVisibilitiesByUserId.get(i);
        if (list == null) {
            return null;
        }
        for (VAccountVisibility vAccountVisibility : list) {
            if (TextUtils.equals(vAccountVisibility.name, str) && TextUtils.equals(vAccountVisibility.type, str2)) {
                return vAccountVisibility;
            }
        }
        return null;
    }

    @TargetApi(26)
    private VAccountVisibility getAccountVisibility(int i, Account account) {
        return getAccountVisibility(i, account.name, account.type);
    }

    public AccountAndUser[] getAllAccounts() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.accountsByUserId.size(); i++) {
            for (VAccount vAccount : this.accountsByUserId.valueAt(i)) {
                arrayList.add(new AccountAndUser(new Account(vAccount.name, vAccount.type), vAccount.userId));
            }
        }
        return (AccountAndUser[]) arrayList.toArray(new AccountAndUser[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class AuthTokenRecord {
        public Account account;
        public String authToken;
        private String authTokenType;
        public long expiryEpochMillis;
        private String packageName;
        public int userId;

        AuthTokenRecord(int i, Account account, String str, String str2, String str3, long j) {
            this.userId = i;
            this.account = account;
            this.authTokenType = str;
            this.packageName = str2;
            this.authToken = str3;
            this.expiryEpochMillis = j;
        }

        AuthTokenRecord(int i, Account account, String str, String str2) {
            this.userId = i;
            this.account = account;
            this.authTokenType = str;
            this.packageName = str2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            AuthTokenRecord authTokenRecord = (AuthTokenRecord) obj;
            return this.userId == authTokenRecord.userId && this.account.equals(authTokenRecord.account) && this.authTokenType.equals(authTokenRecord.authTokenType) && this.packageName.equals(authTokenRecord.packageName);
        }

        public int hashCode() {
            return (((((this.userId * 31) + this.account.hashCode()) * 31) + this.authTokenType.hashCode()) * 31) + this.packageName.hashCode();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class AuthenticatorInfo {
        final AuthenticatorDescription desc;
        final ServiceInfo serviceInfo;

        AuthenticatorInfo(AuthenticatorDescription authenticatorDescription, ServiceInfo serviceInfo) {
            this.desc = authenticatorDescription;
            this.serviceInfo = serviceInfo;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class AuthenticatorCache {
        final Map<String, AuthenticatorInfo> authenticators;

        private AuthenticatorCache() {
            this.authenticators = new HashMap();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public abstract class Session extends IAccountAuthenticatorResponse.Stub implements ServiceConnection, IBinder.DeathRecipient {
        private String mAccountName;
        private boolean mAuthDetailsRequired;
        IAccountAuthenticator mAuthenticator;
        final AuthenticatorInfo mAuthenticatorInfo;
        private long mCreationTime;
        private boolean mExpectActivityLaunch;
        private int mNumErrors;
        private int mNumRequestContinued;
        public int mNumResults;
        private IAccountManagerResponse mResponse;
        private final boolean mStripAuthTokenFromResult;
        private boolean mUpdateLastAuthenticatedTime;
        final int mUserId;

        public abstract void run() throws RemoteException;

        Session(IAccountManagerResponse iAccountManagerResponse, int i, AuthenticatorInfo authenticatorInfo, boolean z, boolean z2, String str, boolean z3, boolean z4) {
            if (authenticatorInfo != null) {
                this.mStripAuthTokenFromResult = z2;
                this.mResponse = iAccountManagerResponse;
                this.mUserId = i;
                this.mAuthenticatorInfo = authenticatorInfo;
                this.mExpectActivityLaunch = z;
                this.mCreationTime = SystemClock.elapsedRealtime();
                this.mAccountName = str;
                this.mAuthDetailsRequired = z3;
                this.mUpdateLastAuthenticatedTime = z4;
                synchronized (VAccountManagerService.this.mSessions) {
                    VAccountManagerService.this.mSessions.put(toString(), this);
                }
                if (iAccountManagerResponse != null) {
                    try {
                        iAccountManagerResponse.asBinder().linkToDeath(this, 0);
                    } catch (RemoteException unused) {
                        this.mResponse = null;
                        binderDied();
                    }
                }
            } else {
                throw new IllegalArgumentException("accountType is null");
            }
        }

        Session(VAccountManagerService vAccountManagerService, IAccountManagerResponse iAccountManagerResponse, int i, AuthenticatorInfo authenticatorInfo, boolean z, boolean z2, String str) {
            this(iAccountManagerResponse, i, authenticatorInfo, z, z2, str, false, false);
        }

        IAccountManagerResponse getResponseAndClose() {
            IAccountManagerResponse iAccountManagerResponse = this.mResponse;
            if (iAccountManagerResponse == null) {
                return null;
            }
            close();
            return iAccountManagerResponse;
        }

        private void close() {
            synchronized (VAccountManagerService.this.mSessions) {
                if (VAccountManagerService.this.mSessions.remove(toString()) != null) {
                    IAccountManagerResponse iAccountManagerResponse = this.mResponse;
                    if (iAccountManagerResponse != null) {
                        iAccountManagerResponse.asBinder().unlinkToDeath(this, 0);
                        this.mResponse = null;
                    }
                    unbind();
                }
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            this.mAuthenticator = IAccountAuthenticator.Stub.asInterface(iBinder);
            try {
                run();
            } catch (RemoteException unused) {
                onError(1, "remote exception");
            }
        }

        @Override // android.accounts.IAccountAuthenticatorResponse
        public void onRequestContinued() {
            this.mNumRequestContinued++;
        }

        @Override // android.accounts.IAccountAuthenticatorResponse
        public void onError(int i, String str) {
            this.mNumErrors++;
            IAccountManagerResponse responseAndClose = getResponseAndClose();
            if (responseAndClose != null) {
                Log.v(VAccountManagerService.TAG, getClass().getSimpleName() + " calling onError() on response " + responseAndClose);
                try {
                    responseAndClose.onError(i, str);
                } catch (RemoteException e) {
                    Log.v(VAccountManagerService.TAG, "Session.onError: caught RemoteException while responding", e);
                }
            } else {
                Log.v(VAccountManagerService.TAG, "Session.onError: already closed");
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            this.mAuthenticator = null;
            IAccountManagerResponse responseAndClose = getResponseAndClose();
            if (responseAndClose != null) {
                try {
                    responseAndClose.onError(1, "disconnected");
                } catch (RemoteException e) {
                    Log.v(VAccountManagerService.TAG, "Session.onServiceDisconnected: caught RemoteException while responding", e);
                }
            }
        }

        public void onResult(Bundle bundle) throws RemoteException {
            IAccountManagerResponse iAccountManagerResponse;
            boolean z = true;
            this.mNumResults++;
            if (bundle != null) {
                boolean z2 = bundle.getBoolean("booleanResult", false);
                boolean z3 = bundle.containsKey("authAccount") && bundle.containsKey("accountType");
                if (!this.mUpdateLastAuthenticatedTime || (!z2 && !z3)) {
                    z = false;
                }
                if (z || this.mAuthDetailsRequired) {
                    synchronized (VAccountManagerService.this.accountsByUserId) {
                        VAccount account = VAccountManagerService.this.getAccount(this.mUserId, this.mAccountName, this.mAuthenticatorInfo.desc.type);
                        if (z && account != null) {
                            account.lastAuthenticatedTime = System.currentTimeMillis();
                            VAccountManagerService.this.saveAllAccounts();
                        }
                        if (this.mAuthDetailsRequired) {
                            long j = -1;
                            if (account != null) {
                                j = account.lastAuthenticatedTime;
                            }
                            bundle.putLong(AccountManagerCompat.KEY_LAST_AUTHENTICATED_TIME, j);
                        }
                    }
                }
            }
            if (bundle != null) {
                TextUtils.isEmpty(bundle.getString("authtoken"));
            }
            Intent intent = null;
            if (bundle != null) {
                intent = (Intent) bundle.getParcelable("intent");
            }
            if (!this.mExpectActivityLaunch || bundle == null || !bundle.containsKey("intent")) {
                iAccountManagerResponse = getResponseAndClose();
            } else {
                iAccountManagerResponse = this.mResponse;
            }
            if (iAccountManagerResponse != null) {
                try {
                    if (bundle == null) {
                        Log.v(VAccountManagerService.TAG, getClass().getSimpleName() + " calling onError() on response " + iAccountManagerResponse);
                        iAccountManagerResponse.onError(5, "null bundle returned");
                        return;
                    }
                    if (this.mStripAuthTokenFromResult) {
                        bundle.remove("authtoken");
                    }
                    Log.v(VAccountManagerService.TAG, getClass().getSimpleName() + " calling onResult() on response " + iAccountManagerResponse);
                    if (bundle.getInt("errorCode", -1) <= 0 || intent != null) {
                        iAccountManagerResponse.onResult(bundle);
                    } else {
                        iAccountManagerResponse.onError(bundle.getInt("errorCode"), bundle.getString("errorMessage"));
                    }
                } catch (RemoteException e) {
                    Log.v(VAccountManagerService.TAG, "failure while notifying response", e);
                }
            }
        }

        void bind() {
            String str = VAccountManagerService.TAG;
            Log.v(str, "initiating bind to authenticator type " + this.mAuthenticatorInfo.desc.type);
            Intent intent = new Intent();
            intent.setAction("android.accounts.AccountAuthenticator");
            intent.setClassName(this.mAuthenticatorInfo.serviceInfo.packageName, this.mAuthenticatorInfo.serviceInfo.name);
            intent.putExtra("_VA_|_user_id_", this.mUserId);
            if (!VAccountManagerService.this.mContext.bindService(intent, this, 1)) {
                String str2 = VAccountManagerService.TAG;
                Log.d(str2, "bind attempt failed for " + toDebugString());
                onError(1, "bind failure");
            }
        }

        protected String toDebugString() {
            return toDebugString(SystemClock.elapsedRealtime());
        }

        protected String toDebugString(long j) {
            StringBuilder sb = new StringBuilder();
            sb.append("Session: expectLaunch ");
            sb.append(this.mExpectActivityLaunch);
            sb.append(", connected ");
            sb.append(this.mAuthenticator != null);
            sb.append(", stats (");
            sb.append(this.mNumResults);
            sb.append("/");
            sb.append(this.mNumRequestContinued);
            sb.append("/");
            sb.append(this.mNumErrors);
            sb.append("), lifetime ");
            sb.append((j - this.mCreationTime) / 1000.0d);
            return sb.toString();
        }

        private void unbind() {
            if (this.mAuthenticator != null) {
                this.mAuthenticator = null;
                VAccountManagerService.this.mContext.unbindService(this);
            }
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            this.mResponse = null;
            close();
        }
    }

    /* loaded from: classes.dex */
    private class GetAccountsByTypeAndFeatureSession extends Session {
        private volatile Account[] mAccountsOfType = null;
        private volatile ArrayList<Account> mAccountsWithFeatures = null;
        private volatile int mCurrentAccount = 0;
        private final String[] mFeatures;

        public GetAccountsByTypeAndFeatureSession(IAccountManagerResponse iAccountManagerResponse, int i, AuthenticatorInfo authenticatorInfo, String[] strArr) {
            super(VAccountManagerService.this, iAccountManagerResponse, i, authenticatorInfo, false, true, null);
            this.mFeatures = strArr;
        }

        @Override // com.lody.virtual.server.accounts.VAccountManagerService.Session
        public void run() throws RemoteException {
            this.mAccountsOfType = VAccountManagerService.this.getAccounts(this.mUserId, this.mAuthenticatorInfo.desc.type);
            this.mAccountsWithFeatures = new ArrayList<>(this.mAccountsOfType.length);
            this.mCurrentAccount = 0;
            checkAccount();
        }

        public void checkAccount() {
            if (this.mCurrentAccount >= this.mAccountsOfType.length) {
                sendResult();
                return;
            }
            IAccountAuthenticator iAccountAuthenticator = this.mAuthenticator;
            if (iAccountAuthenticator == null) {
                String str = VAccountManagerService.TAG;
                Log.v(str, "checkAccount: aborting session since we are no longer connected to the authenticator, " + toDebugString());
                return;
            }
            try {
                iAccountAuthenticator.hasFeatures(this, this.mAccountsOfType[this.mCurrentAccount], this.mFeatures);
            } catch (RemoteException unused) {
                onError(1, "remote exception");
            }
        }

        @Override // com.lody.virtual.server.accounts.VAccountManagerService.Session, android.accounts.IAccountAuthenticatorResponse
        public void onResult(Bundle bundle) {
            this.mNumResults++;
            if (bundle == null) {
                onError(5, "null bundle");
                return;
            }
            if (bundle.getBoolean("booleanResult", false)) {
                this.mAccountsWithFeatures.add(this.mAccountsOfType[this.mCurrentAccount]);
            }
            this.mCurrentAccount++;
            checkAccount();
        }

        public void sendResult() {
            IAccountManagerResponse responseAndClose = getResponseAndClose();
            if (responseAndClose != null) {
                try {
                    Account[] accountArr = new Account[this.mAccountsWithFeatures.size()];
                    for (int i = 0; i < accountArr.length; i++) {
                        accountArr[i] = this.mAccountsWithFeatures.get(i);
                    }
                    if (Log.isLoggable(VAccountManagerService.TAG, 2)) {
                        String str = VAccountManagerService.TAG;
                        Log.v(str, getClass().getSimpleName() + " calling onResult() on response " + responseAndClose);
                    }
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArray(C2986is.f14279a, accountArr);
                    responseAndClose.onResult(bundle);
                } catch (RemoteException e) {
                    Log.v(VAccountManagerService.TAG, "failure while notifying response", e);
                }
            }
        }

        @Override // com.lody.virtual.server.accounts.VAccountManagerService.Session
        protected String toDebugString(long j) {
            StringBuilder sb = new StringBuilder();
            sb.append(super.toDebugString(j));
            sb.append(", getAccountsByTypeAndFeatures, ");
            String[] strArr = this.mFeatures;
            sb.append(strArr != null ? TextUtils.join(",", strArr) : null);
            return sb.toString();
        }
    }
}
