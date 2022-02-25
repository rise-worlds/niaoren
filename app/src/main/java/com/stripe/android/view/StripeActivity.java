package com.stripe.android.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p003v4.content.LocalBroadcastManager;
import android.support.p006v7.app.AlertDialog;
import android.support.p006v7.app.AppCompatActivity;
import android.support.p006v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewStub;
import android.widget.ProgressBar;
import com.stripe.android.C2364R;
import com.stripe.android.CustomerSession;
import p110z1.StripeException;

/* renamed from: com.stripe.android.view.m */
/* loaded from: classes2.dex */
abstract class StripeActivity extends AppCompatActivity {

    /* renamed from: j */
    BroadcastReceiver f12642j;
    @Nullable

    /* renamed from: k */
    AbstractC2485a f12643k;

    /* renamed from: l */
    boolean f12644l;

    /* renamed from: m */
    Toolbar f12645m;

    /* renamed from: n */
    ProgressBar f12646n;

    /* renamed from: o */
    ViewStub f12647o;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: StripeActivity.java */
    /* renamed from: com.stripe.android.view.m$a */
    /* loaded from: classes2.dex */
    public interface AbstractC2485a {
        /* renamed from: a */
        void m17217a(String str);
    }

    /* renamed from: b */
    protected abstract void mo17218b();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.p006v7.app.AppCompatActivity, android.support.p003v4.app.FragmentActivity, android.support.p003v4.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C2364R.layout.activity_stripe);
        this.f12646n = (ProgressBar) findViewById(C2364R.C2366id.progress_bar_as);
        this.f12645m = (Toolbar) findViewById(C2364R.C2366id.toolbar_as);
        this.f12647o = (ViewStub) findViewById(C2364R.C2366id.widget_viewstub_as);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setSupportActionBar(this.f12645m);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mo17219a(false);
        this.f12642j = new BroadcastReceiver() { // from class: com.stripe.android.view.m.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                StripeActivity.this.m17220a(((StripeException) intent.getSerializableExtra(CustomerSession.f11798b)).getLocalizedMessage());
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.p003v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.f12642j);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.p003v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(this.f12642j, new IntentFilter(CustomerSession.f11797a));
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C2364R.C2367menu.add_source_menu, menu);
        menu.findItem(C2364R.C2366id.action_save).setEnabled(!this.f12644l);
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == C2364R.C2366id.action_save) {
            mo17218b();
            return true;
        }
        boolean onOptionsItemSelected = super.onOptionsItemSelected(menuItem);
        if (!onOptionsItemSelected) {
            onBackPressed();
        }
        return onOptionsItemSelected;
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(C2364R.C2366id.action_save).setIcon(C2486n.m17212a(this, getTheme(), C2364R.attr.titleTextColor, C2364R.C2365drawable.ic_checkmark));
        return super.onPrepareOptionsMenu(menu);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo17219a(boolean z) {
        this.f12644l = z;
        if (z) {
            this.f12646n.setVisibility(0);
        } else {
            this.f12646n.setVisibility(8);
        }
        supportInvalidateOptionsMenu();
    }

    /* renamed from: a */
    void m17221a(@Nullable AbstractC2485a aVar) {
        this.f12643k = aVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m17220a(@NonNull String str) {
        AbstractC2485a aVar = this.f12643k;
        if (aVar != null) {
            aVar.m17217a(str);
        }
        new AlertDialog.Builder(this).setMessage(str).setCancelable(true).setPositiveButton(17039370, new DialogInterface.OnClickListener() { // from class: com.stripe.android.view.m.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create().show();
    }
}
