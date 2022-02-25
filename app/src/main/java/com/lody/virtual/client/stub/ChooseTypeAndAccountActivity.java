package com.lody.virtual.client.stub;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorDescription;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.lody.virtual.C1713R;
import com.lody.virtual.client.ipc.VAccountManager;
import com.lody.virtual.helper.utils.VLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public class ChooseTypeAndAccountActivity extends Activity implements AccountManagerCallback<Bundle> {
    private static final boolean DEBUG = false;
    public static final String EXTRA_ADD_ACCOUNT_AUTH_TOKEN_TYPE_STRING = "authTokenType";
    public static final String EXTRA_ADD_ACCOUNT_OPTIONS_BUNDLE = "addAccountOptions";
    public static final String EXTRA_ADD_ACCOUNT_REQUIRED_FEATURES_STRING_ARRAY = "addAccountRequiredFeatures";
    public static final String EXTRA_ALLOWABLE_ACCOUNTS_ARRAYLIST = "allowableAccounts";
    public static final String EXTRA_ALLOWABLE_ACCOUNT_TYPES_STRING_ARRAY = "allowableAccountTypes";
    @Deprecated
    public static final String EXTRA_ALWAYS_PROMPT_FOR_ACCOUNT = "alwaysPromptForAccount";
    public static final String EXTRA_DESCRIPTION_TEXT_OVERRIDE = "descriptionTextOverride";
    public static final String EXTRA_SELECTED_ACCOUNT = "selectedAccount";
    private static final String KEY_INSTANCE_STATE_ACCOUNT_LIST = "accountList";
    private static final String KEY_INSTANCE_STATE_EXISTING_ACCOUNTS = "existingAccounts";
    private static final String KEY_INSTANCE_STATE_PENDING_REQUEST = "pendingRequest";
    private static final String KEY_INSTANCE_STATE_SELECTED_ACCOUNT_NAME = "selectedAccountName";
    private static final String KEY_INSTANCE_STATE_SELECTED_ADD_ACCOUNT = "selectedAddAccount";
    public static final String KEY_USER_ID = "userId";
    public static final int REQUEST_ADD_ACCOUNT = 2;
    public static final int REQUEST_CHOOSE_TYPE = 1;
    public static final int REQUEST_NULL = 0;
    private static final int SELECTED_ITEM_NONE = -1;
    private static final String TAG = "AccountChooser";
    private ArrayList<Account> mAccounts;
    private int mCallingUserId;
    private String mDescriptionOverride;
    private boolean mDontShowPicker;
    private Button mOkButton;
    private int mSelectedItemIndex;
    private Set<Account> mSetOfAllowableAccounts;
    private Set<String> mSetOfRelevantAccountTypes;
    private String mSelectedAccountName = null;
    private boolean mSelectedAddNewAccount = false;
    private int mPendingRequest = 0;
    private Parcelable[] mExistingAccounts = null;

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        Intent intent = getIntent();
        boolean z = false;
        if (bundle != null) {
            this.mPendingRequest = bundle.getInt(KEY_INSTANCE_STATE_PENDING_REQUEST);
            this.mExistingAccounts = bundle.getParcelableArray(KEY_INSTANCE_STATE_EXISTING_ACCOUNTS);
            this.mSelectedAccountName = bundle.getString(KEY_INSTANCE_STATE_SELECTED_ACCOUNT_NAME);
            this.mSelectedAddNewAccount = bundle.getBoolean(KEY_INSTANCE_STATE_SELECTED_ADD_ACCOUNT, false);
            this.mAccounts = bundle.getParcelableArrayList(KEY_INSTANCE_STATE_ACCOUNT_LIST);
            this.mCallingUserId = bundle.getInt(KEY_USER_ID);
        } else {
            this.mPendingRequest = 0;
            this.mExistingAccounts = null;
            this.mCallingUserId = intent.getIntExtra(KEY_USER_ID, -1);
            Account account = (Account) intent.getParcelableExtra(EXTRA_SELECTED_ACCOUNT);
            if (account != null) {
                this.mSelectedAccountName = account.name;
            }
        }
        VLog.m18988v(TAG, "selected account name is " + this.mSelectedAccountName);
        this.mSetOfAllowableAccounts = getAllowableAccountSet(intent);
        this.mSetOfRelevantAccountTypes = getReleventAccountTypes(intent);
        this.mDescriptionOverride = intent.getStringExtra(EXTRA_DESCRIPTION_TEXT_OVERRIDE);
        this.mAccounts = getAcceptableAccountChoices(VAccountManager.get());
        if (this.mDontShowPicker) {
            super.onCreate(bundle);
            return;
        }
        if (this.mPendingRequest == 0 && this.mAccounts.isEmpty()) {
            setNonLabelThemeAndCallSuperCreate(bundle);
            if (this.mSetOfRelevantAccountTypes.size() == 1) {
                runAddAccountForAuthenticator(this.mSetOfRelevantAccountTypes.iterator().next());
            } else {
                startChooseAccountTypeActivity();
            }
        }
        String[] listOfDisplayableOptions = getListOfDisplayableOptions(this.mAccounts);
        this.mSelectedItemIndex = getItemIndexToSelect(this.mAccounts, this.mSelectedAccountName, this.mSelectedAddNewAccount);
        super.onCreate(bundle);
        setContentView(C1713R.layout.choose_type_and_account);
        overrideDescriptionIfSupplied(this.mDescriptionOverride);
        populateUIAccountList(listOfDisplayableOptions);
        this.mOkButton = (Button) findViewById(16908314);
        Button button = this.mOkButton;
        if (this.mSelectedItemIndex != -1) {
            z = true;
        }
        button.setEnabled(z);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "ChooseTypeAndAccountActivity.onDestroy()");
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(KEY_INSTANCE_STATE_PENDING_REQUEST, this.mPendingRequest);
        if (this.mPendingRequest == 2) {
            bundle.putParcelableArray(KEY_INSTANCE_STATE_EXISTING_ACCOUNTS, this.mExistingAccounts);
        }
        int i = this.mSelectedItemIndex;
        if (i != -1) {
            if (i == this.mAccounts.size()) {
                bundle.putBoolean(KEY_INSTANCE_STATE_SELECTED_ADD_ACCOUNT, true);
            } else {
                bundle.putBoolean(KEY_INSTANCE_STATE_SELECTED_ADD_ACCOUNT, false);
                bundle.putString(KEY_INSTANCE_STATE_SELECTED_ACCOUNT_NAME, this.mAccounts.get(this.mSelectedItemIndex).name);
            }
        }
        bundle.putParcelableArrayList(KEY_INSTANCE_STATE_ACCOUNT_LIST, this.mAccounts);
    }

    public void onCancelButtonClicked(View view) {
        onBackPressed();
    }

    public void onOkButtonClicked(View view) {
        if (this.mSelectedItemIndex == this.mAccounts.size()) {
            startChooseAccountTypeActivity();
            return;
        }
        int i = this.mSelectedItemIndex;
        if (i != -1) {
            onAccountSelected(this.mAccounts.get(i));
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        String str;
        String str2;
        String stringExtra;
        if (Log.isLoggable(TAG, 2)) {
            if (!(intent == null || intent.getExtras() == null)) {
                intent.getExtras().keySet();
            }
            Log.v(TAG, "ChooseTypeAndAccountActivity.onActivityResult(reqCode=" + i + ", resCode=" + i2 + ", extras=" + (intent != null ? intent.getExtras() : null) + ")");
        }
        this.mPendingRequest = 0;
        if (i2 != 0) {
            if (i2 == -1) {
                if (i == 1) {
                    if (intent == null || (stringExtra = intent.getStringExtra("accountType")) == null) {
                        Log.d(TAG, "ChooseTypeAndAccountActivity.onActivityResult: unable to find account type, pretending the request was canceled");
                    } else {
                        runAddAccountForAuthenticator(stringExtra);
                        return;
                    }
                } else if (i == 2) {
                    if (intent != null) {
                        str2 = intent.getStringExtra("authAccount");
                        str = intent.getStringExtra("accountType");
                    } else {
                        str2 = null;
                        str = null;
                    }
                    if (str2 == null || str == null) {
                        Account[] accounts = VAccountManager.get().getAccounts(this.mCallingUserId, null);
                        HashSet hashSet = new HashSet();
                        for (Parcelable parcelable : this.mExistingAccounts) {
                            hashSet.add((Account) parcelable);
                        }
                        int length = accounts.length;
                        int i3 = 0;
                        while (true) {
                            if (i3 >= length) {
                                break;
                            }
                            Account account = accounts[i3];
                            if (!hashSet.contains(account)) {
                                str2 = account.name;
                                str = account.type;
                                break;
                            }
                            i3++;
                        }
                    }
                    if (!(str2 == null && str == null)) {
                        setResultAndFinish(str2, str);
                        return;
                    }
                }
                Log.d(TAG, "ChooseTypeAndAccountActivity.onActivityResult: unable to find added account, pretending the request was canceled");
            }
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "ChooseTypeAndAccountActivity.onActivityResult: canceled");
            }
            setResult(0);
            finish();
        } else if (this.mAccounts.isEmpty()) {
            setResult(0);
            finish();
        }
    }

    protected void runAddAccountForAuthenticator(String str) {
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "runAddAccountForAuthenticator: " + str);
        }
        Bundle bundleExtra = getIntent().getBundleExtra(EXTRA_ADD_ACCOUNT_OPTIONS_BUNDLE);
        String[] stringArrayExtra = getIntent().getStringArrayExtra(EXTRA_ADD_ACCOUNT_REQUIRED_FEATURES_STRING_ARRAY);
        VAccountManager.get().addAccount(this.mCallingUserId, str, getIntent().getStringExtra(EXTRA_ADD_ACCOUNT_AUTH_TOKEN_TYPE_STRING), stringArrayExtra, bundleExtra, null, this, null);
    }

    @Override // android.accounts.AccountManagerCallback
    public void run(AccountManagerFuture<Bundle> accountManagerFuture) {
        try {
            Intent intent = (Intent) accountManagerFuture.getResult().getParcelable("intent");
            if (intent != null) {
                this.mPendingRequest = 2;
                this.mExistingAccounts = VAccountManager.get().getAccounts(this.mCallingUserId, null);
                intent.setFlags(intent.getFlags() & (-268435457));
                startActivityForResult(intent, 2);
                return;
            }
        } catch (AuthenticatorException | IOException unused) {
        } catch (OperationCanceledException unused2) {
            setResult(0);
            finish();
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("errorMessage", "error communicating with server");
        setResult(-1, new Intent().putExtras(bundle));
        finish();
    }

    private void setNonLabelThemeAndCallSuperCreate(Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 21) {
            setTheme(16974396);
        } else {
            setTheme(16973941);
        }
        super.onCreate(bundle);
    }

    private void onAccountSelected(Account account) {
        Log.d(TAG, "selected account " + account);
        setResultAndFinish(account.name, account.type);
    }

    private void setResultAndFinish(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("authAccount", str);
        bundle.putString("accountType", str2);
        setResult(-1, new Intent().putExtras(bundle));
        VLog.m18988v(TAG, "ChooseTypeAndAccountActivity.setResultAndFinish: selected account " + str + ", " + str2);
        finish();
    }

    private void startChooseAccountTypeActivity() {
        Intent intent = new Intent(this, ChooseAccountTypeActivity.class);
        intent.setFlags(524288);
        intent.putExtra(EXTRA_ALLOWABLE_ACCOUNT_TYPES_STRING_ARRAY, getIntent().getStringArrayExtra(EXTRA_ALLOWABLE_ACCOUNT_TYPES_STRING_ARRAY));
        intent.putExtra(EXTRA_ADD_ACCOUNT_OPTIONS_BUNDLE, getIntent().getBundleExtra(EXTRA_ADD_ACCOUNT_OPTIONS_BUNDLE));
        intent.putExtra(EXTRA_ADD_ACCOUNT_REQUIRED_FEATURES_STRING_ARRAY, getIntent().getStringArrayExtra(EXTRA_ADD_ACCOUNT_REQUIRED_FEATURES_STRING_ARRAY));
        intent.putExtra(EXTRA_ADD_ACCOUNT_AUTH_TOKEN_TYPE_STRING, getIntent().getStringExtra(EXTRA_ADD_ACCOUNT_AUTH_TOKEN_TYPE_STRING));
        startActivityForResult(intent, 1);
        this.mPendingRequest = 1;
    }

    private int getItemIndexToSelect(ArrayList<Account> arrayList, String str, boolean z) {
        if (z) {
            return arrayList.size();
        }
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).name.equals(str)) {
                return i;
            }
        }
        return -1;
    }

    private String[] getListOfDisplayableOptions(ArrayList<Account> arrayList) {
        String[] strArr = new String[arrayList.size() + 1];
        for (int i = 0; i < arrayList.size(); i++) {
            strArr[i] = arrayList.get(i).name;
        }
        strArr[arrayList.size()] = getResources().getString(C1713R.string.add_account_button_label);
        return strArr;
    }

    private ArrayList<Account> getAcceptableAccountChoices(VAccountManager vAccountManager) {
        Set<String> set;
        Account[] accounts = vAccountManager.getAccounts(this.mCallingUserId, null);
        ArrayList<Account> arrayList = new ArrayList<>(accounts.length);
        for (Account account : accounts) {
            Set<Account> set2 = this.mSetOfAllowableAccounts;
            if ((set2 == null || set2.contains(account)) && ((set = this.mSetOfRelevantAccountTypes) == null || set.contains(account.type))) {
                arrayList.add(account);
            }
        }
        return arrayList;
    }

    private Set<String> getReleventAccountTypes(Intent intent) {
        String[] stringArrayExtra = intent.getStringArrayExtra(EXTRA_ALLOWABLE_ACCOUNT_TYPES_STRING_ARRAY);
        AuthenticatorDescription[] authenticatorTypes = VAccountManager.get().getAuthenticatorTypes();
        HashSet hashSet = new HashSet(authenticatorTypes.length);
        for (AuthenticatorDescription authenticatorDescription : authenticatorTypes) {
            hashSet.add(authenticatorDescription.type);
        }
        if (stringArrayExtra == null) {
            return hashSet;
        }
        HashSet hashSet2 = new HashSet();
        Collections.addAll(hashSet2, stringArrayExtra);
        hashSet2.retainAll(hashSet);
        return hashSet2;
    }

    private Set<Account> getAllowableAccountSet(Intent intent) {
        ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra(EXTRA_ALLOWABLE_ACCOUNTS_ARRAYLIST);
        if (parcelableArrayListExtra == null) {
            return null;
        }
        HashSet hashSet = new HashSet(parcelableArrayListExtra.size());
        Iterator it = parcelableArrayListExtra.iterator();
        while (it.hasNext()) {
            hashSet.add((Account) ((Parcelable) it.next()));
        }
        return hashSet;
    }

    private void overrideDescriptionIfSupplied(String str) {
        TextView textView = (TextView) findViewById(C1713R.C1715id.description);
        if (!TextUtils.isEmpty(str)) {
            textView.setText(str);
        } else {
            textView.setVisibility(8);
        }
    }

    private void populateUIAccountList(String[] strArr) {
        ListView listView = (ListView) findViewById(16908298);
        listView.setAdapter((ListAdapter) new ArrayAdapter(this, 17367055, strArr));
        listView.setChoiceMode(1);
        listView.setItemsCanFocus(false);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.lody.virtual.client.stub.ChooseTypeAndAccountActivity.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ChooseTypeAndAccountActivity.this.mSelectedItemIndex = i;
                ChooseTypeAndAccountActivity.this.mOkButton.setEnabled(true);
            }
        });
        int i = this.mSelectedItemIndex;
        if (i != -1) {
            listView.setItemChecked(i, true);
        }
    }
}
