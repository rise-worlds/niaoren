package com.tencent.smtt.sdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.tbs.video.interfaces.IUserStateHolder;

/* loaded from: classes2.dex */
public class VideoActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.requestWindowFeature(1);
        super.getWindow().setFormat(-3);
        Intent intent = super.getIntent();
        Bundle bundleExtra = intent != null ? intent.getBundleExtra("extraData") : null;
        if (bundleExtra != null) {
            bundleExtra.putInt("callMode", 1);
            TbsVideoPlayer.m16644a(super.getApplicationContext()).m16643a((String) null, bundleExtra, (IUserStateHolder) null);
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        TbsVideoPlayer.m16644a(this).m16645a(this, 2);
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        TbsVideoPlayer.m16644a(this).m16645a(this, 1);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        TbsVideoPlayer.m16644a(this).m16645a(this, 3);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        TbsVideoPlayer.m16644a(this).m16645a(this, 4);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        TbsVideoPlayer.m16644a(this).m16646a(i, i2, intent);
    }
}
