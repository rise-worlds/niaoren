package com.lody.virtual.client.stub;

import android.accounts.AuthenticatorDescription;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.lody.virtual.C1713R;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.ipc.VAccountManager;
import com.lody.virtual.helper.utils.VLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* loaded from: classes.dex */
public class ChooseAccountTypeActivity extends Activity {
    private static final boolean DEBUG = false;
    private static final String TAG = "AccountChooser";
    private ArrayList<AuthInfo> mAuthenticatorInfosToDisplay;
    private HashMap<String, AuthInfo> mTypeToAuthenticatorInfo = new HashMap<>();

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        HashSet hashSet;
        super.onCreate(bundle);
        String[] stringArrayExtra = getIntent().getStringArrayExtra(ChooseTypeAndAccountActivity.EXTRA_ALLOWABLE_ACCOUNT_TYPES_STRING_ARRAY);
        if (stringArrayExtra != null) {
            hashSet = new HashSet(stringArrayExtra.length);
            Collections.addAll(hashSet, stringArrayExtra);
        } else {
            hashSet = null;
        }
        buildTypeToAuthDescriptionMap();
        this.mAuthenticatorInfosToDisplay = new ArrayList<>(this.mTypeToAuthenticatorInfo.size());
        for (Map.Entry<String, AuthInfo> entry : this.mTypeToAuthenticatorInfo.entrySet()) {
            String key = entry.getKey();
            AuthInfo value = entry.getValue();
            if (hashSet == null || hashSet.contains(key)) {
                this.mAuthenticatorInfosToDisplay.add(value);
            }
        }
        if (this.mAuthenticatorInfosToDisplay.isEmpty()) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("errorMessage", "no allowable account types");
            setResult(-1, new Intent().putExtras(bundle2));
            finish();
        } else if (this.mAuthenticatorInfosToDisplay.size() == 1) {
            setResultAndFinish(this.mAuthenticatorInfosToDisplay.get(0).desc.type);
        } else {
            setContentView(C1713R.layout.choose_account_type);
            ListView listView = (ListView) findViewById(16908298);
            listView.setAdapter((ListAdapter) new AccountArrayAdapter(this, 17367043, this.mAuthenticatorInfosToDisplay));
            listView.setChoiceMode(0);
            listView.setTextFilterEnabled(false);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.lody.virtual.client.stub.ChooseAccountTypeActivity.1
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    ChooseAccountTypeActivity chooseAccountTypeActivity = ChooseAccountTypeActivity.this;
                    chooseAccountTypeActivity.setResultAndFinish(((AuthInfo) chooseAccountTypeActivity.mAuthenticatorInfosToDisplay.get(i)).desc.type);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResultAndFinish(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("accountType", str);
        setResult(-1, new Intent().putExtras(bundle));
        finish();
    }

    private void buildTypeToAuthDescriptionMap() {
        AuthenticatorDescription[] authenticatorTypes;
        Drawable drawable;
        for (AuthenticatorDescription authenticatorDescription : VAccountManager.get().getAuthenticatorTypes()) {
            String str = null;
            try {
                Resources resources = VirtualCore.get().getResources(authenticatorDescription.packageName);
                drawable = resources.getDrawable(authenticatorDescription.iconId);
                try {
                    CharSequence text = resources.getText(authenticatorDescription.labelId);
                    text.toString();
                    str = text.toString();
                } catch (Resources.NotFoundException unused) {
                    VLog.m18986w(TAG, "No icon resource for account type " + authenticatorDescription.type, new Object[0]);
                    this.mTypeToAuthenticatorInfo.put(authenticatorDescription.type, new AuthInfo(authenticatorDescription, str, drawable));
                }
            } catch (Resources.NotFoundException unused2) {
                drawable = null;
            }
            this.mTypeToAuthenticatorInfo.put(authenticatorDescription.type, new AuthInfo(authenticatorDescription, str, drawable));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class AuthInfo {
        final AuthenticatorDescription desc;
        final Drawable drawable;
        final String name;

        AuthInfo(AuthenticatorDescription authenticatorDescription, String str, Drawable drawable) {
            this.desc = authenticatorDescription;
            this.name = str;
            this.drawable = drawable;
        }
    }

    /* loaded from: classes.dex */
    private static class ViewHolder {
        ImageView icon;
        TextView text;

        private ViewHolder() {
        }
    }

    /* loaded from: classes.dex */
    private static class AccountArrayAdapter extends ArrayAdapter<AuthInfo> {
        private ArrayList<AuthInfo> mInfos;
        private LayoutInflater mLayoutInflater;

        AccountArrayAdapter(Context context, int i, ArrayList<AuthInfo> arrayList) {
            super(context, i, arrayList);
            this.mInfos = arrayList;
            this.mLayoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = this.mLayoutInflater.inflate(C1713R.layout.choose_account_row, (ViewGroup) null);
                viewHolder = new ViewHolder();
                viewHolder.text = (TextView) view.findViewById(C1713R.C1715id.account_row_text);
                viewHolder.icon = (ImageView) view.findViewById(C1713R.C1715id.account_row_icon);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.text.setText(this.mInfos.get(i).name);
            viewHolder.icon.setImageDrawable(this.mInfos.get(i).drawable);
            return view;
        }
    }
}
