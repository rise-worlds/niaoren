package com.angel.nrzs.app.activity;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.angel.nrzs.App;
import com.angel.nrzs.C0692R;
import com.angel.nrzs.model.SplashActivityModel;
import com.angel.nrzs.app.base.AppBaseActivity;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.gyf.barlibrary.ImmersionBar;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.database.AppDatabase;
import com.nrzs.data.other.bean.AdResultInfoItem;
import com.nrzs.data.other.bean.WelcomeBannerInfo;
import com.nrzs.data.other.bean.response.GetIpResponseInfo;
import com.nrzs.data.other.bean.response.GetWelcomeV6ResponseInfo;
import com.nrzs.game.model.TentcentDownModel;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import p110z1.BannersOnClick;
import p110z1.BitmapUtil;
import p110z1.C5277dw;
import p110z1.C5351fg;
import p110z1.EventCollectManager;
import p110z1.EventConstants;
import p110z1.GameApp;
import p110z1.GlideImageUtils;
import p110z1.Gson;
import p110z1.HttpVal;
import p110z1.LocalHttp;
import p110z1.NRZSLocalConfig;
import p110z1.PXKJCoreUtils;
import p110z1.PermissionUtil;
import p110z1.ScriptModeManager;
import p110z1.ShareVal;
import p110z1.TopicInfoManager;
import p110z1.TypeToken;
import p110z1.VAGameScreenInfoRepository;
import p110z1.apa;
import p110z1.apb;
import p110z1.apf;
import p110z1.aqg;

/* renamed from: com.angel.nrzs.ui.activity.SplashActivity */
/* loaded from: classes.dex */
public class SplashActivity extends AppBaseActivity {

    /* renamed from: j */
    private static final int f5490j = 1;

    /* renamed from: k */
    private static final int f5491k = 5000;

    /* renamed from: l */
    private static final int f5492l = 1;

    /* renamed from: m */
    private static final int f5493m = 2;

    /* renamed from: a */
    private SplashActivityModel f5494a;

    /* renamed from: b */
    private ImageView f5495b;

    /* renamed from: c */
    private TextView f5496c;

    /* renamed from: e */
    private GetIpResponseInfo f5498e;

    /* renamed from: n */
    private HandlerC0814a f5503n;

    /* renamed from: d */
    private boolean f5497d = false;

    /* renamed from: f */
    private ThreadCallback<BaseResponse<GetWelcomeV6ResponseInfo>, String> f5499f = new ThreadCallback<BaseResponse<GetWelcomeV6ResponseInfo>, String>() { // from class: com.angel.nrzs.ui.activity.SplashActivity.1
        /* renamed from: a */
        public BaseResponse<GetWelcomeV6ResponseInfo> onResponse(String str) {
            boolean z;
            BaseResponse<GetWelcomeV6ResponseInfo> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<GetWelcomeV6ResponseInfo>>() { // from class: com.angel.nrzs.ui.activity.SplashActivity.1.1
            });
            if (baseResponse == null || !(baseResponse.data instanceof GetWelcomeV6ResponseInfo)) {
                return null;
            }
            try {
                List<WelcomeBannerInfo> list = baseResponse.data.ImgList;
                if (list != null) {
                    ArrayList arrayList = new ArrayList();
                    String[] list2 = new File(C5351fg.f21627b).list();
                    for (int i = 0; i < list.size(); i++) {
                        WelcomeBannerInfo welcomeBannerInfo = list.get(i);
                        String str2 = C5351fg.f21627b + apb.m11874a(welcomeBannerInfo.ImgPath);
                        arrayList.add(str2);
                        if (!new File(str2).exists()) {
                            C5351fg.m2880b(str2);
                            BitmapUtil.m2966a(Glide.with(App.getInstance()).asBitmap().load(welcomeBannerInfo.ImgPath).into(Integer.MIN_VALUE, Integer.MIN_VALUE).get(), str2);
                        }
                    }
                    for (int i2 = 0; i2 < list2.length; i2++) {
                        Iterator it = arrayList.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                if (((String) it.next()).contains(list2[i2])) {
                                    z = false;
                                    break;
                                }
                            } else {
                                z = true;
                                break;
                            }
                        }
                        if (z) {
                            C5351fg.m2878c(C5351fg.f21627b + list2[i2]);
                        }
                    }
                    SPUtils.m23341a().m23332a(ShareVal.f16602l, new Gson().m1575b(list));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return baseResponse;
        }
    };

    /* renamed from: g */
    private UICallback<BaseResponse<GetWelcomeV6ResponseInfo>> f5500g = new UICallback<BaseResponse<GetWelcomeV6ResponseInfo>>() { // from class: com.angel.nrzs.ui.activity.SplashActivity.2
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            aqg.m11586a(SplashActivity.this, "获取欢迎页失败");
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<GetWelcomeV6ResponseInfo> baseResponse) {
            if (baseResponse != null && baseResponse.code == 0) {
                aqg.m11586a(SplashActivity.this, baseResponse.msg);
            }
        }
    };

    /* renamed from: h */
    private UICallback<BaseResponse<GetIpResponseInfo>> f5501h = new UICallback<BaseResponse<GetIpResponseInfo>>() { // from class: com.angel.nrzs.ui.activity.SplashActivity.3
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            SplashActivity.this.f5497d = false;
            SplashActivity.this.m24961a(LocalHttp.f16541b);
            SplashActivity.this.m24953h();
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<GetIpResponseInfo> baseResponse) {
            if (baseResponse != null) {
                SplashActivity.this.f5497d = true;
                SplashActivity.this.f5498e = baseResponse.data;
                if (!TextUtils.isEmpty(baseResponse.data.rdata.IPpool)) {
                    String[] split = baseResponse.data.rdata.IPpool.split("\\|");
                    if (split.length >= 1) {
                        SplashActivity.this.m24961a(split);
                    } else {
                        SplashActivity.this.m24961a(LocalHttp.f16541b);
                    }
                } else {
                    SplashActivity.this.m24961a(LocalHttp.f16541b);
                }
            } else {
                SplashActivity.this.m24961a(LocalHttp.f16541b);
            }
            SplashActivity.this.m24953h();
        }
    };

    /* renamed from: i */
    private ThreadCallback<BaseResponse<GetIpResponseInfo>, String> f5502i = new ThreadCallback<BaseResponse<GetIpResponseInfo>, String>() { // from class: com.angel.nrzs.ui.activity.SplashActivity.4
        /* renamed from: a */
        public BaseResponse<GetIpResponseInfo> onResponse(String str) {
            BaseResponse<GetIpResponseInfo> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<GetIpResponseInfo>>() { // from class: com.angel.nrzs.ui.activity.SplashActivity.4.1
            });
            if (baseResponse == null || !(baseResponse.data instanceof GetIpResponseInfo)) {
                return null;
            }
            return baseResponse;
        }
    };

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    protected int mo18307a() {
        return C0692R.layout.nrzs_activity_splash;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.angel.nrzs.ui.activity.SplashActivity$a */
    /* loaded from: classes.dex */
    public static class HandlerC0814a extends Handler {

        /* renamed from: a */
        WeakReference<Activity> f5516a;

        public HandlerC0814a(Activity activity) {
            this.f5516a = new WeakReference<>(activity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Activity activity = this.f5516a.get();
            if (activity != null && message.what == 1) {
                MainActivity.m25033a(activity);
                activity.finish();
            }
        }
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18303b() {
        LogUtils.m23734c("HomeFragmentChange", "splashactivity - initBeforeView");
        if (!isTaskRoot()) {
            finish();
        } else if (ImmersionBar.m19958m()) {
            ImmersionBar.m20080a(this).m19951p(C0692R.C0694id.nrzs_app_toolbar).m20018c(true).m19994f();
        } else {
            ImmersionBar.m20080a(this).m19951p(C0692R.C0694id.nrzs_app_toolbar).m19994f();
        }
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    public void mo18305a(Bundle bundle) {
        this.f5495b = (ImageView) findViewById(C0692R.C0694id.nrzs_app_iv_banner);
        this.f5496c = (TextView) findViewById(C0692R.C0694id.nrzs_app_tv_skip);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18302b(Bundle bundle) {
        m24956e();
        m24959c();
    }

    /* renamed from: c */
    public void m24959c() {
        new Thread(new Runnable() { // from class: com.angel.nrzs.ui.activity.SplashActivity.5
            @Override // java.lang.Runnable
            public void run() {
                long b = AppDatabase.m18933e().mo18932a().mo12748b();
                TopicInfoManager.m12726c().m12725c(AppDatabase.m18933e().mo18932a().mo12752a());
                TopicInfoManager.m12726c().m12728b(b);
                Log.e("最大id", b + "");
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m24961a(String[] strArr) {
        Random random = new Random();
        int nextInt = random.nextInt(strArr.length);
        Log.e("随机数", "" + nextInt + "长度" + strArr.length + random.nextInt(strArr.length) + strArr[nextInt]);
        LocalHttp.f16542c = strArr[nextInt];
        Log.e("Httpval", "前");
        HttpVal.m12519a();
        HttpVal.m12518b();
        Log.e("Httpval", "后");
    }

    /* renamed from: f */
    private void m24955f() {
        Gson oxVar = new Gson();
        String b = SPUtils.m23341a().m23320b(ShareVal.f16602l, "");
        List list = !b.equals("") ? (List) oxVar.m1588a(b, new TypeToken<List<WelcomeBannerInfo>>() { // from class: com.angel.nrzs.ui.activity.SplashActivity.6
        }.getType()) : null;
        if (list == null || list.size() <= 0) {
            this.f5496c.setVisibility(8);
            return;
        }
        this.f5496c.setVisibility(0);
        int c = SPUtils.m23341a().m23315c("GetWelcomeV6", 0) % list.size();
        WelcomeBannerInfo welcomeBannerInfo = (WelcomeBannerInfo) list.get(c);
        SPUtils.m23341a().m23322b("GetWelcomeV6", c + 1);
        File file = new File(C5351fg.f21627b + apb.m11874a(welcomeBannerInfo.ImgPath));
        if (file.exists()) {
            new Gson();
            oxVar.m1575b(welcomeBannerInfo);
            m24962a(file.getPath(), welcomeBannerInfo);
        }
    }

    /* renamed from: a */
    public void m24962a(String str, final WelcomeBannerInfo welcomeBannerInfo) {
        ImageView imageView = this.f5495b;
        if (imageView != null) {
            GlideImageUtils.m11880a(imageView, getApplicationContext(), (int) C0692R.mipmap.f3513a, str);
            this.f5495b.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ui.activity.SplashActivity.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AdResultInfoItem adResultInfoItem = new AdResultInfoItem();
                    adResultInfoItem.Title = welcomeBannerInfo.Title;
                    adResultInfoItem.ExecArgs = welcomeBannerInfo.H5Url;
                    if (welcomeBannerInfo.AdObject == 1) {
                        adResultInfoItem.ExecCommand = C5277dw.f21346b;
                    } else if (welcomeBannerInfo.AdObject == 2) {
                        adResultInfoItem.ExecCommand = C5277dw.f21345a;
                    }
                    if (!TextUtils.isEmpty(welcomeBannerInfo.H5Url)) {
                        SplashActivity.this.f5503n.removeMessages(1);
                        new BannersOnClick().m3199a(SplashActivity.this, adResultInfoItem, 1);
                        SplashActivity.this.finish();
                    }
                }
            });
        }
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: d */
    public void mo18300d() {
        this.f5496c.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ui.activity.SplashActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SplashActivity.this.f5497d) {
                    if (SplashActivity.this.f5498e == null) {
                        SplashActivity.this.m24961a(LocalHttp.f16541b);
                    } else if (!TextUtils.isEmpty(SplashActivity.this.f5498e.rdata.IPpool)) {
                        String[] split = SplashActivity.this.f5498e.rdata.IPpool.split("\\|");
                        if (split.length >= 1) {
                            SplashActivity.this.m24961a(split);
                        } else {
                            SplashActivity.this.m24961a(LocalHttp.f16541b);
                        }
                    } else {
                        SplashActivity.this.m24961a(LocalHttp.f16541b);
                    }
                    SplashActivity.this.m24953h();
                } else {
                    SplashActivity.this.m24961a(LocalHttp.f16541b);
                    SplashActivity.this.m24953h();
                }
                SplashActivity.this.f5503n.removeMessages(1);
                MainActivity.m25033a(SplashActivity.this);
                SplashActivity.this.finish();
            }
        });
    }

    /* renamed from: g */
    private void m24954g() {
        ScriptModeManager.m3097f().m3099d();
        if (this.f5494a == null) {
            this.f5494a = (SplashActivityModel) ViewModelProviders.m25704of(this).get(SplashActivityModel.class);
        }
        this.f5494a.m25037b(this.f5501h, this.f5502i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public void m24953h() {
        m24955f();
        if (!NRZSLocalConfig.m12512d()) {
            try {
                GameApp.getInstance().m13003c();
                new VAGameScreenInfoRepository().m12695a();
                PXKJCoreUtils.m12928a(App.getInstance());
                TentcentDownModel.m18803a().m18779d();
            } catch (Exception unused) {
                Log.e("permmisionGranted", "permmisionGranted--isOnlyRoot-error");
            }
        }
        this.f5494a.m25039a(this.f5500g, this.f5499f);
        this.f5494a.m25036c();
        this.f5494a.m25040a();
        this.f5494a.m25038b();
        this.f5503n = new HandlerC0814a(this);
        this.f5503n.sendEmptyMessageDelayed(1, 5000L);
        if (ImmersionBar.m20025c(this)) {
            apf.m11841a((Context) this, "common_shared_file", ShareVal.f16570A, true);
        }
    }

    /* renamed from: i */
    private void m24952i() {
        finish();
    }

    /* renamed from: e */
    public void m24956e() {
        switch (PermissionUtil.m11847a(this, "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.READ_PHONE_STATE", "android.permission.ACCESS_FINE_LOCATION")) {
            case WAIT:
                Log.e("permmisionGranted", "permmisionGranted--等待授权");
                return;
            case GRANTED:
                m24954g();
                return;
            default:
                return;
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        switch (PermissionUtil.m11847a(this, "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.READ_PHONE_STATE", "android.permission.ACCESS_FINE_LOCATION")) {
            case GRANTED:
                m24954g();
                Log.e("permmisionGranted", "permmisionGranted--第一次允许权限之后走这边");
                return;
            case DENIED:
                m24952i();
                Log.e("permmisionGranted", "permmisionGranted--拒绝授权之后走这边");
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.angel.nrzs.app.base.AppBaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        HandlerC0814a aVar = this.f5503n;
        if (aVar != null) {
            aVar.removeCallbacksAndMessages(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.angel.nrzs.app.base.AppBaseActivity, com.nrzs.libcommon.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        EventCollectManager.m12642a().m12640a(App.getInstance(), "欢迎页面", "欢迎页面", EventConstants.f16434b);
    }
}
