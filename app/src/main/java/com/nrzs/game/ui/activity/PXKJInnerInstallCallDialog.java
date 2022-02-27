package com.nrzs.game.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.nrzs.core.models.AppInfoLite;
import com.nrzs.game.C2029R;
import com.nrzs.p072va.AppInstallOptions;
import com.nrzs.p072va.VirtualCoreProxy;
import p110z1.DoneCallback;
import p110z1.VUiKit;

/* renamed from: com.nrzs.game.ui.activity.PXKJInnerInstallCallDialog */
/* loaded from: classes2.dex */
public class PXKJInnerInstallCallDialog extends Activity {
    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static /* synthetic */ void m18599b(Void r0) {
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C2029R.layout.nrzs_game_dialog_va_loading);
        Intent intent = getIntent();
        intent.getExtras().getString("url");
        m18602a(new AppInfoLite("i am sdk", intent.getExtras().getString("url"), false, null));
    }

    /* renamed from: a */
    public void m18603a() {
        Intent intent = new Intent();
        intent.putExtra("name", "aaa");
        intent.putExtra("password", "bbb");
        setResult(30, intent);
        finish();
    }

    /* renamed from: a */
    public void m18602a(final AppInfoLite appInfoLite) {
        VUiKit.m11713a().mo3333a(new Runnable() { // from class: com.nrzs.game.ui.activity.-$$Lambda$PXKJInnerInstallCallDialog$ipSbqaELYsCsfrMOi7NeB3DY7mU
            @Override // java.lang.Runnable
            public final void run() {
                PXKJInnerInstallCallDialog.m18600b(AppInfoLite.this);
            }
        }).mo3294a($$Lambda$PXKJInnerInstallCallDialog$WlFYojk2XKgfX_4wC6hC9wu_rP8.INSTANCE).mo3282b(new DoneCallback() { // from class: com.nrzs.game.ui.activity.-$$Lambda$PXKJInnerInstallCallDialog$flVVNAWZrUFyI1LHScm7o5P4BHE
            @Override // p110z1.DoneCallback
            public final void onDone(Object obj) {
                PXKJInnerInstallCallDialog.this.m18601a((Void) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static /* synthetic */ void m18600b(AppInfoLite appInfoLite) {
        boolean z = VirtualCoreProxy.installPackageSync(appInfoLite.f10534e, AppInstallOptions.makeOptions(true, false)).isSuccess;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m18601a(Void r1) {
        m18603a();
    }
}
