package com.lody.virtual.client.stub;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;
import com.lody.virtual.helper.compat.BundleCompat;
import com.lody.virtual.server.IRequestPermissionsResult;

@TargetApi(23)
/* loaded from: classes.dex */
public class RequestPermissionsActivity extends Activity {
    private static final int REQUEST_PERMISSION_CODE = 996;
    private IRequestPermissionsResult mCallBack;

    public static void request(Context context, boolean z, String[] strArr, IRequestPermissionsResult iRequestPermissionsResult) {
        Intent intent = new Intent();
        if (z) {
            intent.setClassName(StubManifest.PACKAGE_NAME_64BIT, RequestPermissionsActivity.class.getName());
        } else {
            intent.setClassName(StubManifest.PACKAGE_NAME, RequestPermissionsActivity.class.getName());
        }
        intent.setFlags(268435456);
        intent.putExtra("permissions", strArr);
        BundleCompat.putBinder(intent, "callback", iRequestPermissionsResult.asBinder());
        context.startActivity(intent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        String[] stringArrayExtra = intent.getStringArrayExtra("permissions");
        IBinder binder = BundleCompat.getBinder(intent, "callback");
        if (binder == null || stringArrayExtra == null) {
            finish();
            return;
        }
        this.mCallBack = IRequestPermissionsResult.Stub.asInterface(binder);
        requestPermissions(stringArrayExtra, 996);
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        IRequestPermissionsResult iRequestPermissionsResult = this.mCallBack;
        if (iRequestPermissionsResult != null) {
            try {
                if (!iRequestPermissionsResult.onResult(i, strArr, iArr)) {
                    runOnUiThread(new Runnable() { // from class: com.lody.virtual.client.stub.RequestPermissionsActivity.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Toast.makeText(RequestPermissionsActivity.this, "Request permission failed.", 0).show();
                        }
                    });
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        finish();
    }
}
