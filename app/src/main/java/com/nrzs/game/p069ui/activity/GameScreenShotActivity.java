package com.nrzs.game.p069ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import com.blankj.utilcode.util.LogUtils;
import p110z1.VAManager;

/* renamed from: com.nrzs.game.ui.activity.GameScreenShotActivity */
/* loaded from: classes2.dex */
public class GameScreenShotActivity extends Activity {
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtils.m23734c("CKU", "免root 模式 - onCreate");
        requestWindowFeature(1);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().setDimAmount(0.0f);
        m18698a();
    }

    /* renamed from: a */
    public void m18698a() {
        startActivityForResult(new Intent(this, RealScreenShotActivity.class), 2);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 2) {
            VAManager.m12070i().m12074e();
        }
        finish();
    }
}
