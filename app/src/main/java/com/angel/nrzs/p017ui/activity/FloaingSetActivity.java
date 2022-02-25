package com.angel.nrzs.p017ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.angel.nrzs.C0692R;
import com.angel.nrzs.p017ui.base.AppBaseActivity;
import com.blankj.utilcode.util.SPUtils;
import p110z1.FloatWindowPermissionChecker;
import p110z1.ShareVal;

/* renamed from: com.angel.nrzs.ui.activity.FloaingSetActivity */
/* loaded from: classes.dex */
public class FloaingSetActivity extends AppBaseActivity implements View.OnClickListener {

    /* renamed from: a */
    public static final String f5376a = "KEY_FLOING_TYPE";

    /* renamed from: b */
    private TextView f5377b;

    /* renamed from: c */
    private TextView f5378c;

    /* renamed from: d */
    private TextView f5379d;

    /* renamed from: e */
    private RelativeLayout f5380e;

    /* renamed from: f */
    private RelativeLayout f5381f;

    /* renamed from: g */
    private CheckBox f5382g;

    /* renamed from: h */
    private int f5383h;

    /* renamed from: i */
    private boolean f5384i;

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    protected int mo18307a() {
        return C0692R.layout.nrzs_flogingset_activity;
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18303b() {
    }

    /* renamed from: a */
    public static void m25034a(Context context, int i) {
        Intent intent = new Intent(context, FloaingSetActivity.class);
        intent.putExtra(f5376a, i);
        context.startActivity(intent);
    }

    @Override // com.angel.nrzs.p017ui.base.AppBaseActivity, com.nrzs.libcommon.BaseActivity, android.support.p006v7.app.AppCompatActivity, android.support.p003v4.app.FragmentActivity, android.support.p003v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    public void mo18305a(Bundle bundle) {
        this.f5382g = (CheckBox) findViewById(C0692R.C0694id.nrzs_app_floaingset_notips);
        this.f5377b = (TextView) findViewById(C0692R.C0694id.nrzs_app_floaingset_goto_seting);
        this.f5378c = (TextView) findViewById(C0692R.C0694id.nrzs_app_floaingset_know_string);
        this.f5379d = (TextView) findViewById(C0692R.C0694id.nrzs_app_floaingset_know);
        this.f5380e = (RelativeLayout) findViewById(C0692R.C0694id.nrzs_app_floaingset_lay_seting);
        this.f5381f = (RelativeLayout) findViewById(C0692R.C0694id.nrzs_app_floaingset_lay_nofindset);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18302b(Bundle bundle) {
        this.f5383h = getIntent().getIntExtra(f5376a, -1);
        this.f5382g.setChecked(SPUtils.m23341a().m23318b(ShareVal.f16593c, false));
        this.f5384i = SPUtils.m23341a().m23318b(ShareVal.f16594d, false);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: d */
    public void mo18300d() {
        this.f5377b.setOnClickListener(this);
        this.f5378c.setOnClickListener(this);
        this.f5379d.setOnClickListener(this);
        this.f5382g.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.angel.nrzs.ui.activity.FloaingSetActivity.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SPUtils.m23341a().m23328a(ShareVal.f16593c, z);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case C0692R.C0694id.nrzs_app_floaingset_goto_seting /* 2131231181 */:
                int i = this.f5383h;
                if (i == 1) {
                    this.f5380e.setVisibility(8);
                    this.f5381f.setVisibility(0);
                    return;
                } else if (i == 2) {
                    this.f5380e.setVisibility(8);
                    this.f5381f.setVisibility(0);
                    return;
                } else if (i == 4) {
                    FloatWindowPermissionChecker.m12292b(this);
                    return;
                } else {
                    this.f5380e.setVisibility(8);
                    this.f5381f.setVisibility(0);
                    return;
                }
            case C0692R.C0694id.nrzs_app_floaingset_know /* 2131231182 */:
                finish();
                return;
            case C0692R.C0694id.nrzs_app_floaingset_know_string /* 2131231183 */:
                if (!this.f5384i) {
                    SPUtils.m23341a().m23328a(ShareVal.f16594d, true);
                }
                finish();
                return;
            default:
                return;
        }
    }

    @Override // android.support.p006v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return false;
        }
        return super.onKeyDown(i, keyEvent);
    }
}
