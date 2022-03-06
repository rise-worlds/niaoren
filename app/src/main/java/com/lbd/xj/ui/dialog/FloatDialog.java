package com.lbd.xj.ui.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.lbd.xj.C1467R;
import com.lbd.xj.ui.activity.AppBaseActivity;
import com.lbd.xj.service.XJFloatService;
import p110z1.EventBus;
import p110z1.FloatStatusEvent;
import p110z1.FloatingPermissionCompat;
import p110z1.aej;

/* renamed from: com.lbd.xj.ui.dialog.FloatDialog */
/* loaded from: classes.dex */
public class FloatDialog extends AppBaseActivity implements View.OnClickListener {

    /* renamed from: a */
    private TextView f9680a;

    /* renamed from: b */
    private TextView f9681b;

    @Override // com.lbd.xj.base.ui.BaseActivity
    /* renamed from: b */
    public void mo19492b() {
    }

    @Override // com.lbd.xj.base.ui.BaseActivity
    /* renamed from: c */
    public void mo19490c() {
    }

    @Override // com.lbd.xj.base.ui.BaseActivity
    /* renamed from: c */
    public void mo19489c(Bundle bundle) {
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
    }

    @Override // com.lbd.xj.base.ui.BaseActivity
    /* renamed from: a */
    protected int mo19493a() {
        return C1467R.layout.dialog_float;
    }

    @Override // com.lbd.xj.base.ui.BaseActivity
    /* renamed from: b */
    public void mo19491b(Bundle bundle) {
        this.f9680a = (TextView) findViewById(C1467R.C1469id.tv_float_close);
        this.f9680a.setOnClickListener(this);
        this.f9681b = (TextView) findViewById(C1467R.C1469id.tv_float_open);
        this.f9681b.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == C1467R.C1469id.tv_float_close) {
            EventBus.m3448a().m3427d(new FloatStatusEvent(false));
            finish();
        } else if (id == C1467R.C1469id.tv_float_open) {
            FloatingPermissionCompat.m14338a().m14334b(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lbd.xj.ui.activity.AppBaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (FloatingPermissionCompat.m14338a().m14337a(this)) {
            EventBus.m3448a().m3427d(new FloatStatusEvent(true));
            aej.m13957a(this, new Intent(this, XJFloatService.class));
            finish();
        }
    }
}
