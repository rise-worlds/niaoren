package com.lody.virtual.client.stub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.lody.virtual.C1713R;
import com.lody.virtual.client.env.Constants;
import com.lody.virtual.helper.compat.BundleCompat;
import com.lody.virtual.helper.utils.VLog;
import com.lody.virtual.os.VUserHandle;

/* loaded from: classes.dex */
public class ChooserActivity extends ResolverActivity {
    public static final String ACTION = Intent.createChooser(new Intent(), "").getAction();
    public static final String EXTRA_DATA = "android.intent.extra.virtual.data";
    public static final String EXTRA_INTENT = "android.intent.extra.virtual.intent";
    public static final String EXTRA_REQUEST_CODE = "android.intent.extra.virtual.request_code";
    public static final String EXTRA_RESULTTO = "_va|ibinder|resultTo";
    public static final String EXTRA_WHO = "android.intent.extra.virtual.who";

    public static boolean check(Intent intent) {
        try {
            if (!TextUtils.equals(ACTION, intent.getAction())) {
                if (!TextUtils.equals("android.intent.action.CHOOSER", intent.getAction())) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.lody.virtual.client.stub.ResolverActivity, android.app.Activity
    @SuppressLint({"MissingSuperCall"})
    protected void onCreate(Bundle bundle) {
        Bundle extras = getIntent().getExtras();
        Intent intent = getIntent();
        int i = extras.getInt(Constants.EXTRA_USER_HANDLE, VUserHandle.getCallingUserId());
        this.mOptions = (Bundle) extras.getParcelable(EXTRA_DATA);
        this.mResultWho = extras.getString(EXTRA_WHO);
        this.mRequestCode = extras.getInt(EXTRA_REQUEST_CODE, 0);
        this.mResultTo = BundleCompat.getBinder(extras, EXTRA_RESULTTO);
        Parcelable parcelableExtra = intent.getParcelableExtra("android.intent.extra.INTENT");
        if (!(parcelableExtra instanceof Intent)) {
            VLog.m18986w("ChooseActivity", "Target is not an intent: %s", parcelableExtra);
            finish();
            return;
        }
        Intent intent2 = (Intent) parcelableExtra;
        CharSequence charSequenceExtra = intent.getCharSequenceExtra("android.intent.extra.TITLE");
        CharSequence string = charSequenceExtra == null ? getString(C1713R.string.choose) : charSequenceExtra;
        Parcelable[] parcelableArrayExtra = intent.getParcelableArrayExtra("android.intent.extra.INITIAL_INTENTS");
        Intent[] intentArr = null;
        if (parcelableArrayExtra != null) {
            Intent[] intentArr2 = new Intent[parcelableArrayExtra.length];
            for (int i2 = 0; i2 < parcelableArrayExtra.length; i2++) {
                if (!(parcelableArrayExtra[i2] instanceof Intent)) {
                    VLog.m18986w("ChooseActivity", "Initial intent #" + i2 + " not an Intent: %s", parcelableArrayExtra[i2]);
                    finish();
                    return;
                }
                intentArr2[i2] = (Intent) parcelableArrayExtra[i2];
            }
            intentArr = intentArr2;
        }
        super.onCreate(bundle, intent2, string, intentArr, null, false, i);
    }
}
