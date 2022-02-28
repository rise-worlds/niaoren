package com.nrzs.game.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.lody.virtual.client.ipc.VActivityManager;
import com.lody.virtual.helper.compat.PermissionCompat;
import com.lody.virtual.oem.OemPermissionHelper;
import com.nrzs.core.models.AppData;
import com.nrzs.core.models.AppInfoLite;
import com.nrzs.core.models.GameInfo;
import com.nrzs.core.models.PackageAppData;
import com.nrzs.data.DataApp;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.game.bean.GameScreenInfo;
import com.nrzs.data.game.bean.TopicInfo;
import com.nrzs.game.C2029R;
import com.nrzs.game.model.TentcentDownModel;
import com.nrzs.game.ui.base.GameBaseActivity;
import com.nrzs.http.UICallback;
import com.nrzs.va.VirtualCoreProxy;
import java.util.List;
import p110z1.DoneCallback;
import p110z1.FailCallback;
import p110z1.GameApp;
import p110z1.GameDetectPathRepository;
import p110z1.HxCommonDialog;
import p110z1.IntentToAssistService;
import p110z1.NRZSFileConfig;
import p110z1.PackageAppDataStorage;
import p110z1.QQInstallDialog;
import p110z1.ShareVal;
import p110z1.TencentSupport;
import p110z1.VAGameScreenInfoRepository;
import p110z1.VAManager;
import p110z1.VAppStatueCallback;
import p110z1.VUiKit;
import p110z1.Wx32TipsDialog;
import p110z1.ajc;
import p110z1.aoy;
import p110z1.apf;

/* renamed from: com.nrzs.game.ui.activity.GameVALoadingActivity */
/* loaded from: classes2.dex */
public class GameVALoadingActivity extends GameBaseActivity {

    /* renamed from: e */
    private static final int f11071e = 995;

    /* renamed from: a */
    public GameInfo f11072a;

    /* renamed from: b */
    public TopicInfo f11073b;

    /* renamed from: c */
    public int f11074c;

    /* renamed from: d */
    public PackageAppData f11075d;

    /* renamed from: f */
    private int f11076f;

    /* renamed from: g */
    private Handler f11077g = new Handler();

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    public void mo18305a(Bundle bundle) {
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18303b() {
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: d */
    public void mo18300d() {
    }

    /* renamed from: a */
    public static void m18632a(Context context, TopicInfo topicInfo, int i) {
        Intent intent = new Intent(context, GameVALoadingActivity.class);
        intent.putExtra("TopicInfo", topicInfo);
        intent.putExtra("where", i);
        context.startActivity(intent);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    protected int mo18307a() {
        return C2029R.layout.nrzs_game_dialog_va_loading;
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18302b(Bundle bundle) {
        this.f11073b = (TopicInfo) getIntent().getParcelableExtra("TopicInfo");
        this.f11074c = getIntent().getIntExtra("where", 1);
        this.f11072a = VAManager.m12079b(this.f11073b);
        PackageAppDataStorage.m12941a().m12939a(this.f11072a.f10544h, new ajc<PackageAppData>() { // from class: com.nrzs.game.ui.activity.GameVALoadingActivity.1
            /* renamed from: a */
            public void callback(PackageAppData dVar) {
                GameVALoadingActivity.this.f11075d = PackageAppDataStorage.m12941a().m12937c(GameVALoadingActivity.this.f11072a.f10544h);
                GameVALoadingActivity.this.m18621e();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m18621e() {
        if (TentcentDownModel.m18803a().m18773j()) {
            m18619f();
        } else if (!TentcentDownModel.m18803a().m18777f()) {
            m18619f();
        } else {
            Wx32TipsDialog aouVar = new Wx32TipsDialog(this, new Wx32TipsDialog.AbstractC3822a() { // from class: com.nrzs.game.ui.activity.GameVALoadingActivity.9
                @Override // p110z1.Wx32TipsDialog.AbstractC3822a
                /* renamed from: a */
                public void mo11917a() {
                    GameVALoadingActivity.this.m18619f();
                    LogUtils.m23734c("check_32_64", "安装成功后 走QQ");
                }

                @Override // p110z1.Wx32TipsDialog.AbstractC3822a
                /* renamed from: b */
                public void mo11916b() {
                    GameVALoadingActivity.this.m18619f();
                }
            });
            aouVar.show();
            aouVar.m11937a();
            LogUtils.m23734c("check_32_64", "updateQQState");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public void m18619f() {
        if (TentcentDownModel.m18803a().m18772k()) {
            m18615h();
        } else if (!TentcentDownModel.m18803a().m18778e()) {
            m18615h();
        } else if (!isFinishing()) {
            QQInstallDialog aotVar = new QQInstallDialog(this, new QQInstallDialog.AbstractC3815a() { // from class: com.nrzs.game.ui.activity.GameVALoadingActivity.10
                @Override // p110z1.QQInstallDialog.AbstractC3815a
                /* renamed from: a */
                public void mo11939a() {
                    GameVALoadingActivity.this.m18615h();
                }

                @Override // p110z1.QQInstallDialog.AbstractC3815a
                /* renamed from: b */
                public void mo11938b() {
                    GameVALoadingActivity.this.m18615h();
                }
            });
            aotVar.show();
            aotVar.m11954a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public void m18617g() {
        HxCommonDialog.m12993a(this).m12976e(C2029R.style.NRZS_Game_my_dialog).m12986a(C2029R.layout.nrzs_game_va_64_permission).m12985a(C2029R.C2031id.nrzs_game_iv_64_close, new HxCommonDialog.AbstractC3515b() { // from class: com.nrzs.game.ui.activity.GameVALoadingActivity.13
            @Override // p110z1.HxCommonDialog.AbstractC3515b
            /* renamed from: a */
            public void mo12988a(View view, HxCommonDialog aiuVar) {
                aiuVar.dismiss();
                GameVALoadingActivity.this.m18615h();
            }
        }).m12985a(C2029R.C2031id.nrzs_game_tv_64_cancle, new HxCommonDialog.AbstractC3515b() { // from class: com.nrzs.game.ui.activity.GameVALoadingActivity.12
            @Override // p110z1.HxCommonDialog.AbstractC3515b
            /* renamed from: a */
            public void mo12988a(View view, HxCommonDialog aiuVar) {
                aiuVar.dismiss();
                GameVALoadingActivity.this.m18615h();
            }
        }).m12985a(C2029R.C2031id.nrzs_game_tv_64_go_permission, new HxCommonDialog.AbstractC3515b() { // from class: com.nrzs.game.ui.activity.GameVALoadingActivity.11
            @Override // p110z1.HxCommonDialog.AbstractC3515b
            /* renamed from: a */
            public void mo12988a(View view, HxCommonDialog aiuVar) {
                Intent permissionActivityIntent = OemPermissionHelper.getPermissionActivityIntent(GameVALoadingActivity.this);
                if (permissionActivityIntent != null) {
                    GameVALoadingActivity.this.startActivity(permissionActivityIntent);
                } else {
                    Log.d("LBS_PXKJ", "权限获取失败，请到我的页面查看教程");
                }
                aiuVar.dismiss();
                GameVALoadingActivity.this.finish();
            }
        }).m12980a(false).m12987a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public void m18615h() {
        new GameDetectPathRepository().m12712a(new UICallback<BaseResponse<List<String>>>() { // from class: com.nrzs.game.ui.activity.GameVALoadingActivity.14
            @Override // com.nrzs.http.UICallback
            /* renamed from: a */
            public void mo3021a(Throwable th) {
                GameVALoadingActivity.this.m18627a(ResultTypeConstant.f7213z);
            }

            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo3022a(BaseResponse<List<String>> baseResponse) {
                if (baseResponse != null) {
                    try {
                        if (baseResponse.code == 1 && baseResponse.data != null) {
                            List<String> list = baseResponse.data;
                            if (!list.isEmpty()) {
                                GameVALoadingActivity.this.m18627a(list.get(0));
                            } else {
                                GameVALoadingActivity.this.m18627a(ResultTypeConstant.f7213z);
                            }
                        }
                    } catch (Exception unused) {
                        GameVALoadingActivity.this.m18627a(ResultTypeConstant.f7213z);
                        return;
                    }
                }
                GameVALoadingActivity.this.m18627a(ResultTypeConstant.f7213z);
            }
        }, this.f11072a.f10544h, this.f11072a.f10546j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m18627a(String str) {
        aoy.m11884a(NRZSFileConfig.f16547e + "1.txt", str, false);
        new VAGameScreenInfoRepository().m12694a(new UICallback<BaseResponse<GameScreenInfo>>() { // from class: com.nrzs.game.ui.activity.GameVALoadingActivity.15
            @Override // com.nrzs.http.UICallback
            /* renamed from: a */
            public void mo3021a(Throwable th) {
                GameVALoadingActivity.this.m18614i();
            }

            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo3022a(BaseResponse<GameScreenInfo> baseResponse) {
                if (baseResponse == null || baseResponse.code != 1 || baseResponse.data == null) {
                    GameVALoadingActivity.this.m18614i();
                } else {
                    GameVALoadingActivity.this.m18614i();
                }
            }
        }, this.f11072a.f10544h);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i */
    public void m18614i() {
        VUiKit.m11713a().mo3333a(new Runnable() { // from class: com.nrzs.game.ui.activity.GameVALoadingActivity.3
            @Override // java.lang.Runnable
            public void run() {
                aoy.m11888a(GameVALoadingActivity.this, "1.dll", NRZSFileConfig.f16547e, "1.dll");
            }
        }).mo3285a(new FailCallback<Throwable>() { // from class: com.nrzs.game.ui.activity.GameVALoadingActivity.2
            /* renamed from: a */
            public void onFail(Throwable th) {
                GameVALoadingActivity.this.m18613j();
            }
        }).mo3282b(new DoneCallback<Void>() { // from class: com.nrzs.game.ui.activity.GameVALoadingActivity.16
            /* renamed from: a */
            public void onDone(Void r1) {
                GameVALoadingActivity.this.m18613j();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public void m18613j() {
        VAManager.m12070i().m12084a(new VAppStatueCallback() { // from class: com.nrzs.game.ui.activity.GameVALoadingActivity.4
            @Override // p110z1.VAppStatueCallback
            /* renamed from: a */
            public void mo12964a(int i) {
            }

            @Override // p110z1.VAppStatueCallback
            /* renamed from: a */
            public void mo12963a(int i, String[] strArr, AppData aVar, int i2) {
                GameVALoadingActivity.this.f11076f = i;
                if (!TencentSupport.f16013a.contains(aVar.mo18958e())) {
                    IntentToAssistService.m12803a(GameApp.getInstance().m13006b(), VAManager.m12070i().m12081b(), VAManager.m12070i().m12094a(), VAManager.m12070i().m12075d(), VAManager.m12070i().m12077c(), 7);
                }
                IntentToAssistService.m12801a(GameVALoadingActivity.this.getApplicationContext(), GameVALoadingActivity.this.f11072a.f10544h, i);
                if (i2 == 1) {
                    VActivityManager.get().launchApp(i, GameVALoadingActivity.this.f11072a.f10544h);
                    GameVALoadingActivity.this.f11077g.postDelayed(new Runnable() { // from class: com.nrzs.game.ui.activity.GameVALoadingActivity.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (!GameVALoadingActivity.this.isFinishing()) {
                                GameVALoadingActivity.this.finish();
                            }
                        }
                    }, 500L);
                } else if (i2 == 2 && Build.VERSION.SDK_INT >= 23) {
                    GameVALoadingActivity.this.m18626a(strArr);
                }
            }

            @Override // p110z1.VAppStatueCallback
            /* renamed from: b */
            public void mo12962b(int i) {
                if (i == 0) {
                    ToastUtils.m23030a("安装失败：" + i);
                } else if (i == 1) {
                    ToastUtils.m23030a("安装失败：" + i);
                } else if (i == 3) {
                    ToastUtils.m23030a("安装失败：" + i);
                } else if (i == -1) {
                    if (VirtualCoreProxy.isEngineInstalled()) {
                        GameVALoadingActivity.this.m18617g();
                        return;
                    } else {
                        GameVALoadingActivity.this.m18624c();
                        return;
                    }
                }
                GameVALoadingActivity.this.finish();
            }
        }, getApplicationContext(), this.f11072a, (AppInfoLite) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(api = 23)
    /* renamed from: a */
    public void m18626a(String[] strArr) {
        requestPermissions(strArr, 995);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (PermissionCompat.isRequestGranted(iArr)) {
            if (!VActivityManager.get().launchApp(this.f11076f, this.f11072a.f10544h)) {
                ToastUtils.m23030a("启动64位应用失败，请在手机设置中开启【鸟人助手64位插件】自启动权限后重试");
            }
            finish();
        } else if (VActivityManager.get().launchApp(this.f11076f, this.f11072a.f10544h)) {
            this.f11077g.postDelayed(new Runnable() { // from class: com.nrzs.game.ui.activity.GameVALoadingActivity.5
                @Override // java.lang.Runnable
                public void run() {
                    if (!GameVALoadingActivity.this.isFinishing()) {
                        GameVALoadingActivity.this.finish();
                    }
                }
            }, 500L);
        } else {
            runOnUiThread($$Lambda$GameVALoadingActivity$nouO6InYl6iQj6sAWxqvcuBprM.INSTANCE);
            finish();
        }
    }

    /* renamed from: c */
    public void m18624c() {
        HxCommonDialog.m12993a(this).m12976e(C2029R.style.NRZS_Game_my_dialog).m12986a(C2029R.layout.nrzs_game_va_64_install).m12985a(C2029R.C2031id.nrzs_game_iv_install_64_close, new HxCommonDialog.AbstractC3515b() { // from class: com.nrzs.game.ui.activity.GameVALoadingActivity.8
            @Override // p110z1.HxCommonDialog.AbstractC3515b
            /* renamed from: a */
            public void mo12988a(View view, HxCommonDialog aiuVar) {
                aiuVar.dismiss();
                GameVALoadingActivity.this.m18615h();
            }
        }).m12985a(C2029R.C2031id.nrzs_game_tv_install_64, new HxCommonDialog.AbstractC3515b() { // from class: com.nrzs.game.ui.activity.GameVALoadingActivity.7
            @Override // p110z1.HxCommonDialog.AbstractC3515b
            /* renamed from: a */
            public void mo12988a(View view, HxCommonDialog aiuVar) {
                GameVa64InstallCourseActivity.m18606a(view.getContext());
                aiuVar.dismiss();
                GameVALoadingActivity.this.finish();
            }
        }).m12982a(new HxCommonDialog.AbstractC3514a() { // from class: com.nrzs.game.ui.activity.GameVALoadingActivity.6
            @Override // p110z1.HxCommonDialog.AbstractC3514a
            /* renamed from: a */
            public void mo12989a(View view) {
                ((CheckBox) view.findViewById(C2029R.C2031id.nrzs_game_cb_install_64)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.nrzs.game.ui.activity.GameVALoadingActivity.6.1
                    @Override // android.widget.CompoundButton.OnCheckedChangeListener
                    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        apf.m11841a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16571B, z);
                    }
                });
            }
        }).m12980a(false).m12987a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.game.ui.base.GameBaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f11077g.removeCallbacksAndMessages(null);
    }
}
