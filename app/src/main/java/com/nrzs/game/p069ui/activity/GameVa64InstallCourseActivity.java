package com.nrzs.game.p069ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gyf.barlibrary.ImmersionBar;
import com.nrzs.game.C2029R;
import com.nrzs.game.p069ui.base.GameBaseActivity;
import com.nrzs.game.p069ui.view.NRZSGameWebView;
import p110z1.NRZSFileConfig;
import p110z1.PXKJCoreUtils;
import p110z1.PreSetListManager;

/* renamed from: com.nrzs.game.ui.activity.GameVa64InstallCourseActivity */
/* loaded from: classes2.dex */
public class GameVa64InstallCourseActivity extends GameBaseActivity {

    /* renamed from: a */
    private TextView f11096a;

    /* renamed from: b */
    private ImageView f11097b;

    /* renamed from: c */
    private LinearLayout f11098c;

    /* renamed from: d */
    private NRZSGameWebView f11099d;

    /* renamed from: a */
    public static void m18606a(Context context) {
        context.startActivity(new Intent(context, GameVa64InstallCourseActivity.class));
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    protected int mo18307a() {
        return C2029R.layout.nrzs_game_activity_va_64_install;
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18303b() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        getWindow().setAttributes(attributes);
        ImmersionBar.m20080a(this).m19951p(C2029R.C2031id.nrzs_game_tv_title).m19994f();
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    public void mo18305a(Bundle bundle) {
        this.f11096a = (TextView) findViewById(C2029R.C2031id.nrzs_game_tv_install_64);
        this.f11097b = (ImageView) findViewById(C2029R.C2031id.nrzs_game_iv_install_64_close);
        this.f11098c = (LinearLayout) findViewById(C2029R.C2031id.nrzs_game_iv_ll_webview);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18302b(Bundle bundle) {
        this.f11099d = new NRZSGameWebView(this);
        this.f11098c.addView(this.f11099d, -1, -1);
        this.f11099d.m18982a(PreSetListManager.m12116a().m12104l(), "", null);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: d */
    public void mo18300d() {
        this.f11096a.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.game.ui.activity.GameVa64InstallCourseActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameVa64InstallCourseActivity.this.m18604c();
            }
        });
        this.f11097b.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.game.ui.activity.GameVa64InstallCourseActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameVa64InstallCourseActivity.this.onBackPressed();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m18604c() {
        int a = PXKJCoreUtils.m12927a(getApplicationContext(), NRZSFileConfig.f16563u);
        if (a == -1) {
            Log.d("LBS_PXKJ", "暂时无法安装");
        } else if (a == 0) {
            Log.d("LBS_PXKJ", "应用已安装");
        } else {
            finish();
            Log.d("LBS_PXKJ", "正在安装");
        }
    }
}
