package com.lbd.p054xj.p056ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.FrameLayout;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.common.utils.SharedPreferencesUtil;
import com.gyf.barlibrary.ImmersionBar;
import com.lbd.p054xj.C1467R;
import com.lbd.p054xj.manager.launch.BoxLaunchManager;
import com.lbd.p054xj.manager.launch.LaunchFileSocket;
import com.lbd.p054xj.p056ui.dialog.FloatDialog;
import com.lbd.p054xj.p056ui.fragment.LoadingFragment;
import com.lbd.p054xj.p056ui.fragment.SplashFragment;
import com.lbd.p054xj.service.XJFloatService;
import com.lbd.p054xj.socket.C1545f;
import com.nrzs.base.router.RouterConstants;
import com.nrzs.data.xnkj.bean.XJBannerInfo;
import com.nrzs.data.xnkj.bean.response.XJBaseAppReponse;
import java.util.List;
import p110z1.EventBus;
import p110z1.EventBusMessage;
import p110z1.FloatStatusEvent;
import p110z1.FloatingPermissionCompat;
import p110z1.GetAppItemTask;
import p110z1.SharepreferenceUtil;
import p110z1.SkipLauncherEvent;
import p110z1.SocketConstants;
import p110z1.Subscribe;
import p110z1.ThreadMode;
import p110z1.TypeToken;
import p110z1.UnZipEvent;
import p110z1.ValShare;
import p110z1.acf;
import p110z1.apa;

@Route(path = RouterConstants.ModuleXNKJ.START)
/* renamed from: com.lbd.xj.ui.activity.XJActivity */
/* loaded from: classes.dex */
public class XJActivity extends AppBaseActivity {

    /* renamed from: a */
    private static final String f9599a = "XJActivity";

    /* renamed from: b */
    private BoxLaunchManager f9600b;

    /* renamed from: c */
    private FrameLayout f9601c;

    /* renamed from: d */
    private boolean f9602d = false;

    /* renamed from: e */
    private AlertDialog f9603e;

    /* renamed from: j */
    private void m19542j() {
    }

    /* renamed from: k */
    private void m19541k() {
    }

    @Override // com.lbd.p054xj.base.p055ui.BaseActivity
    /* renamed from: c */
    public void mo19490c() {
    }

    @Override // com.lbd.p054xj.base.p055ui.BaseActivity
    /* renamed from: a */
    protected int mo19493a() {
        return C1467R.layout.xj_activity;
    }

    @Override // com.lbd.p054xj.base.p055ui.BaseActivity
    /* renamed from: b */
    public void mo19492b() {
        ImmersionBar.m20080a(this).m19994f();
    }

    @Override // com.lbd.p054xj.base.p055ui.BaseActivity
    /* renamed from: b */
    public void mo19491b(Bundle bundle) {
        this.f9601c = (FrameLayout) findViewById(C1467R.C1469id.xj_fl);
    }

    @Override // com.lbd.p054xj.base.p055ui.BaseActivity
    /* renamed from: c */
    public void mo19489c(Bundle bundle) {
        EventBus.m3448a().m3446a(this);
        m19543i();
    }

    /* renamed from: d */
    public void m19548d() {
        LogUtils.m23734c(f9599a, "XJActivity - initData");
        m19545g();
        m19546f();
        this.f9600b.setDisplayParam(this, null);
        C1545f.m19586c(new GetAppItemTask());
        m19542j();
        m19541k();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m19552a(EventBusMessage acmVar) {
        char c;
        String a = acmVar.m14349a();
        com.common.utils.log.LogUtils.m22039d("getMessageType:" + a);
        switch (a.hashCode()) {
            case -1756469166:
                if (a.equals(acf.f15174M)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1590613600:
                if (a.equals(acf.f15164C)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1091561267:
                if (a.equals(acf.f15162A)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -672458724:
                if (a.equals(acf.f15171J)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -372091400:
                if (a.equals(SocketConstants.f15232ad)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -81048531:
                if (a.equals(acf.f15201z)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 257022647:
                if (a.equals(acf.f15200y)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 343759874:
                if (a.equals(acf.f15173L)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 375107168:
                if (a.equals(acf.f15163B)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 1477769704:
                if (a.equals(acf.f15172K)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1492574188:
                if (a.equals(acf.f15165D)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 1643494382:
                if (a.equals(acf.f15185j)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                this.f9600b.zipjavaCompress(BoxLaunchManager.rootFile);
                return;
            case 1:
            case 2:
            case 3:
                this.f9600b.setDisplayParam(this, null);
                return;
            case 4:
            case 5:
                SharedPreferencesUtil.getInstance().putBooleanValue(acf.f15186k, false);
                return;
            case 6:
            case '\b':
            default:
                return;
            case 7:
                com.common.utils.log.LogUtils.m22035i("更新异常 2");
                return;
            case '\t':
                com.common.utils.log.LogUtils.m22035i("更新异常 1");
                return;
            case '\n':
                this.f9600b.doInitVirtualMachine((String) acmVar.m14348b());
                return;
            case 11:
                this.f9600b.startVirtualMachine();
                return;
        }
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m19550a(SkipLauncherEvent acsVar) {
        if (!this.f9602d) {
            getSupportFragmentManager().beginTransaction().replace(C1467R.C1469id.xj_fl, m19547e()).commit();
        } else {
            this.f9600b.gotoFullActivity(this);
        }
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m19551a(FloatStatusEvent acoVar) {
        if (!acoVar.m14347a()) {
            m19548d();
            m19544h();
        } else if (FloatingPermissionCompat.m14338a().m14337a(this)) {
            m19548d();
            m19544h();
        }
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m19549a(UnZipEvent acuVar) {
        Fragment findFragmentById;
        if (acuVar.m14342a() == 100 && (findFragmentById = getSupportFragmentManager().findFragmentById(C1467R.C1469id.xj_fl)) != null && (findFragmentById instanceof LoadingFragment)) {
            this.f9600b.gotoFullActivity(this);
        }
    }

    /* renamed from: e */
    private LoadingFragment m19547e() {
        BoxLaunchManager boxLaunchManager = this.f9600b;
        return LoadingFragment.m19426a(boxLaunchManager != null ? boxLaunchManager.getProgress() : 0);
    }

    /* renamed from: f */
    private void m19546f() {
        if (!FloatingPermissionCompat.m14338a().m14337a(this)) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            startForegroundService(new Intent(this, XJFloatService.class));
        } else {
            startForegroundService(new Intent(this, XJFloatService.class));
        }
    }

    /* renamed from: g */
    private void m19545g() {
        if (this.f9600b == null) {
            this.f9600b = new BoxLaunchManager();
            this.f9600b.setCallBack(new BoxLaunchManager.BoxLaunchCallback() { // from class: com.lbd.xj.ui.activity.XJActivity.1
                @Override // com.lbd.p054xj.manager.launch.BoxLaunchManager.BoxLaunchCallback
                public void patchUnZip() {
                }

                @Override // com.lbd.p054xj.manager.launch.BoxLaunchManager.BoxLaunchCallback
                public void unZipSucceed() {
                }

                @Override // com.lbd.p054xj.manager.launch.BoxLaunchManager.BoxLaunchCallback
                public void upZipFail() {
                }

                @Override // com.lbd.p054xj.manager.launch.BoxLaunchManager.BoxLaunchCallback
                public void unZipProgress(int i) {
                    LogUtils.m23734c("SHENG", "unZipProgress => " + i);
                    EventBus.m3448a().m3427d(new UnZipEvent(i));
                }

                @Override // com.lbd.p054xj.manager.launch.BoxLaunchManager.BoxLaunchCallback
                public void launchReady() {
                    XJActivity.this.f9602d = true;
                }
            });
            if (BoxLaunchManager.none == 0) {
                new LaunchFileSocket(this);
            }
        }
    }

    /* renamed from: h */
    private void m19544h() {
        if (BoxLaunchManager.none == 0) {
            String b = SharepreferenceUtil.m13882b(ValShare.f16642a, "");
            if (!TextUtils.isEmpty(b)) {
                XJBaseAppReponse xJBaseAppReponse = (XJBaseAppReponse) apa.m11877a(b, new TypeToken<XJBaseAppReponse<List<XJBannerInfo>>>() { // from class: com.lbd.xj.ui.activity.XJActivity.2
                });
                if (xJBaseAppReponse == null || xJBaseAppReponse.Data == 0 || ((List) xJBaseAppReponse.Data).size() <= 0) {
                    m19554a(m19547e());
                } else {
                    m19554a(SplashFragment.m19415f());
                }
            } else {
                m19554a(m19547e());
            }
        } else {
            BoxLaunchManager boxLaunchManager = this.f9600b;
            if (boxLaunchManager != null) {
                boxLaunchManager.gotoFullActivity(this);
            }
        }
    }

    /* renamed from: a */
    private void m19554a(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(C1467R.C1469id.xj_fl, fragment).commit();
    }

    /* renamed from: i */
    private void m19543i() {
        if (!FloatingPermissionCompat.m14338a().m14337a(this)) {
            startActivity(new Intent(this, FloatDialog.class));
            return;
        }
        m19548d();
        m19544h();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lbd.p054xj.p056ui.activity.AppBaseActivity, android.support.p006v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        EventBus.m3448a().m3430c(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lbd.p054xj.p056ui.activity.AppBaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }
}
