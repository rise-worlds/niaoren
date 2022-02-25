package com.angel.nrzs.p017ui.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.p003v4.app.Fragment;
import android.support.p003v4.app.FragmentManager;
import android.support.p003v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.angel.nrzs.App;
import com.angel.nrzs.C0692R;
import com.angel.nrzs.adapter.MainFragmentAdapter;
import com.angel.nrzs.ddy.frament.DdyHomeFragment;
import com.angel.nrzs.p017ui.base.AppBaseActivity;
import com.angel.nrzs.p017ui.fragment.ChannelFragment;
import com.angel.nrzs.p017ui.fragment.HomeFragment;
import com.angel.nrzs.p017ui.fragment.PersonFragment;
import com.angel.nrzs.p017ui.view.CustomViewPager;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.nrzs.base.router.RouterConstants;
import com.nrzs.base.router.RouterUtils;
import com.nrzs.data.DataApp;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.other.bean.request.UplaodAppRequestInfo;
import com.nrzs.data.user.bean.UserInfo;
import com.nrzs.game.model.TentcentDownModel;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import com.nrzs.p067ft.INRZSAidlInterface;
import com.nrzs.p067ft.IOnNewLoginInfoListener;
import com.nrzs.p067ft.NRZSAidlService;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.tools.ant.taskdefs.WaitFor;
import p110z1.Autologinmanager;
import p110z1.CommonEvent;
import p110z1.CommonUtil;
import p110z1.Disposable;
import p110z1.EventBus;
import p110z1.EventCollectManager;
import p110z1.EventConstants;
import p110z1.FloatWindowManager;
import p110z1.FloatXnkjManager;
import p110z1.FragmentEvent;
import p110z1.FtCheckFloatingDialog;
import p110z1.Gson;
import p110z1.HttpVal;
import p110z1.LoginEvent;
import p110z1.LoginManager;
import p110z1.Observer;
import p110z1.OrcDownModel;
import p110z1.PopShowManager;
import p110z1.PreSetListManager;
import p110z1.ShareVal;
import p110z1.SharedPreferencesUtil;
import p110z1.Subscribe;
import p110z1.ThreadMode;
import p110z1.TypeToken;
import p110z1.VersionManager;
import p110z1.XnkjUtils;
import p110z1.acf;
import p110z1.aoy;
import p110z1.apa;
import p110z1.apf;
import p110z1.apn;
import p110z1.aqg;

@Route(path = RouterConstants.MAIN)
/* renamed from: com.angel.nrzs.ui.activity.MainActivity */
/* loaded from: classes.dex */
public class MainActivity extends AppBaseActivity implements ViewPager.OnPageChangeListener {

    /* renamed from: b */
    private static final long f5386b = 3000;

    /* renamed from: c */
    private apn f5388c;

    /* renamed from: e */
    private FragmentManager f5390e;

    /* renamed from: f */
    private MainFragmentAdapter f5391f;

    /* renamed from: g */
    private boolean f5392g;

    /* renamed from: h */
    private CustomViewPager f5393h;

    /* renamed from: i */
    private TextView f5394i;

    /* renamed from: j */
    private TextView f5395j;

    /* renamed from: k */
    private TextView f5396k;

    /* renamed from: l */
    private TextView f5397l;

    /* renamed from: m */
    private ArrayList<TextView> f5398m;

    /* renamed from: n */
    private ArrayList<Fragment> f5399n;

    /* renamed from: o */
    private INRZSAidlInterface f5400o;

    /* renamed from: a */
    BroadcastReceiver f5387a = new BroadcastReceiver() { // from class: com.angel.nrzs.ui.activity.MainActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getDataString().split(":")[1] != null) {
                if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
                    EventBus.m3448a().m3427d(new CommonEvent.C3553a());
                }
                if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
                    EventBus.m3448a().m3427d(new CommonEvent.C3553a());
                }
            }
        }
    };

    /* renamed from: d */
    private long f5389d = 0;

    /* renamed from: p */
    private boolean f5401p = true;

    /* renamed from: q */
    private boolean f5402q = true;

    /* renamed from: r */
    private final IOnNewLoginInfoListener f5403r = new IOnNewLoginInfoListener.AbstractBinderC2010a() { // from class: com.angel.nrzs.ui.activity.MainActivity.7
        @Override // com.nrzs.p067ft.IOnNewLoginInfoListener
        public void onNewLoginInfo(String str) throws RemoteException {
            UserInfo userInfo;
            if (str != null && !str.isEmpty() && (userInfo = (UserInfo) new Gson().m1589a(str, (Class<Object>) UserInfo.class)) != null) {
                LoginManager.m12620d().m12628a(userInfo);
            }
        }

        @Override // com.nrzs.p067ft.IOnNewLoginInfoListener
        public void onCdKeyUpdateInfo(String str) throws RemoteException {
            UserInfo userInfo;
            if (str != null && !str.isEmpty() && (userInfo = (UserInfo) new Gson().m1589a(str, (Class<Object>) UserInfo.class)) != null) {
                if (userInfo.AscriptionAuthorId > 0 || DataApp.m18939d().f10604g > 0) {
                    LoginManager.m12620d().m12629a(userInfo.AscriptionAuthorId);
                    EventBus.m3448a().m3427d(new FragmentEvent.C3555a(1));
                }
            }
        }
    };

    /* renamed from: s */
    private ThreadCallback f5404s = new ThreadCallback<BaseResponse<Objects>, String>() { // from class: com.angel.nrzs.ui.activity.MainActivity.8
        /* renamed from: a */
        public BaseResponse<Objects> onResponse(String str) {
            BaseResponse<Objects> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<Objects>>() { // from class: com.angel.nrzs.ui.activity.MainActivity.8.1
            });
            if (baseResponse != null) {
                return baseResponse;
            }
            return null;
        }
    };

    /* renamed from: t */
    private UICallback f5405t = new UICallback<BaseResponse<Objects>>() { // from class: com.angel.nrzs.ui.activity.MainActivity.9
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            Log.d("uploadapp", "失败");
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<Objects> baseResponse) {
            if (baseResponse == null) {
                return;
            }
            if (baseResponse.code == 1) {
                Log.d("uploadapp", "成功");
            } else {
                Log.d("uploadapp", "失败");
            }
        }
    };

    /* renamed from: u */
    private final ServiceConnection f5406u = new ServiceConnection() { // from class: com.angel.nrzs.ui.activity.MainActivity.10
        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MainActivity.this.f5400o = INRZSAidlInterface.AbstractBinderC2008a.asInterface(iBinder);
            if (MainActivity.this.f5400o != null) {
                try {
                    MainActivity.this.f5400o.registListener(MainActivity.this.f5403r);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    protected int mo18307a() {
        return C0692R.layout.nrzs_activity_main;
    }

    @Override // android.support.p003v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // android.support.p003v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    /* renamed from: a */
    public static void m25033a(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.angel.nrzs.p017ui.base.AppBaseActivity, com.nrzs.libcommon.BaseActivity, android.support.p006v7.app.AppCompatActivity, android.support.p003v4.app.FragmentActivity, android.support.p003v4.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        SharedPreferencesUtil.m11446a(getApplicationContext());
        EventBus.m3448a().m3446a(this);
        LoginManager.m12620d().m12624a(true);
        Autologinmanager.m11654a(App.m25213a()).m11655a();
        VersionManager.m3095a(this).m3086a(true, true);
        m25014g();
        m25021c();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addDataScheme(ServiceManagerNative.PACKAGE);
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        registerReceiver(this.f5387a, intentFilter);
        TentcentDownModel.m18803a().m18770m();
    }

    /* renamed from: e */
    private void m25018e() {
        if (!this.f5392g) {
            this.f5392g = true;
            if (!TextUtils.isEmpty(LoginManager.m12620d().m12621c())) {
                try {
                    String str = getPackageManager().getApplicationInfo(LoginManager.m12620d().m12621c(), 0).sourceDir;
                    if (!TextUtils.isEmpty(str)) {
                        m25027a(str, 2);
                    } else {
                        m25027a(str, 3);
                        Log.e("UploadLocalAppPackage", "空");
                    }
                    Log.e("UploadLocalAppPackage", str);
                } catch (PackageManager.NameNotFoundException e) {
                    m25027a("", 3);
                    e.printStackTrace();
                }
            }
        }
    }

    /* renamed from: c */
    public void m25021c() {
        try {
            new Thread(new Runnable() { // from class: com.angel.nrzs.ui.activity.MainActivity.11
                @Override // java.lang.Runnable
                public void run() {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
                    String b = apf.m11837b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16598h, "");
                    Log.e("datalal", "进入run");
                    if (TextUtils.isEmpty(b)) {
                        apf.m11842a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16598h, simpleDateFormat.format(new Date(System.currentTimeMillis())));
                        Log.e("datalal", "没有保存过");
                        EventCollectManager.m12642a().m12641a(MainActivity.this);
                        return;
                    }
                    try {
                        Date parse = simpleDateFormat.parse(b);
                        String format = simpleDateFormat.format(new Date(System.currentTimeMillis()));
                        long a = MainActivity.this.m25025a(parse, simpleDateFormat.parse(format));
                        Log.e("datalal", "保存过了");
                        if (a > 7) {
                            Log.e("datalal", "时间差7天");
                            apf.m11842a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16598h, format);
                            EventCollectManager.m12642a().m12641a(MainActivity.this);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m25027a(final String str, final int i) {
        Log.e("datalal", "调用");
        new Thread(new Runnable() { // from class: com.angel.nrzs.ui.activity.MainActivity.12
            @Override // java.lang.Runnable
            public void run() {
                Log.e("datalal", "走run");
                if (LoginManager.m12620d().m12606r()) {
                    Log.e("datalal", "开始上传");
                    try {
                        UplaodAppRequestInfo uplaodAppRequestInfo = new UplaodAppRequestInfo();
                        uplaodAppRequestInfo.UserId = LoginManager.m12620d().m12614j();
                        uplaodAppRequestInfo.UploadStatue = i;
                        if (str.equals("")) {
                            uplaodAppRequestInfo.LocalAppFileMD5 = "";
                        } else {
                            uplaodAppRequestInfo.LocalAppFileMD5 = aoy.m11887a(new File(str));
                        }
                        MainActivity.this.m25026a(HttpVal.f16509Y, str, uplaodAppRequestInfo);
                    } catch (Exception e) {
                        Log.e("datalal", "出错了" + e.toString());
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m25026a(String str, String str2, UplaodAppRequestInfo uplaodAppRequestInfo) throws Exception {
        File file;
        String str3;
        MultipartBody multipartBody;
        new HashMap();
        List<String> sigin = uplaodAppRequestInfo.getSigin(str, uplaodAppRequestInfo.getNoencodeMapProperty());
        if (sigin != null && sigin.size() >= 2) {
            String str4 = sigin.get(0);
            String str5 = sigin.get(1);
            if (str2.equals("")) {
                file = null;
                str3 = "没有找到安装包";
            } else {
                File file2 = new File(str2);
                str3 = file2.getName();
                file = file2;
            }
            Log.e("datalal", "进来了");
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (file == null) {
                MultipartBody.Builder addFormDataPart = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("UserId", String.valueOf(LoginManager.m12620d().m12614j()));
                MultipartBody.Builder addFormDataPart2 = addFormDataPart.addFormDataPart("UploadStatue", uplaodAppRequestInfo.UploadStatue + "").addFormDataPart("LocalAppFileMD5", uplaodAppRequestInfo.LocalAppFileMD5).addFormDataPart("a", uplaodAppRequestInfo.f10610a).addFormDataPart("b", uplaodAppRequestInfo.f10614b).addFormDataPart("ab", uplaodAppRequestInfo.f10612ab).addFormDataPart("bc", uplaodAppRequestInfo.f10615bc);
                MultipartBody.Builder addFormDataPart3 = addFormDataPart2.addFormDataPart("d", uplaodAppRequestInfo.f10616d + "");
                MultipartBody.Builder addFormDataPart4 = addFormDataPart3.addFormDataPart("de", uplaodAppRequestInfo.f10617de + "").addFormDataPart(ServiceManagerNative.f10497VS, uplaodAppRequestInfo.f10621vs);
                MultipartBody.Builder addFormDataPart5 = addFormDataPart4.addFormDataPart("vc", uplaodAppRequestInfo.f10620vc + "");
                MultipartBody.Builder addFormDataPart6 = addFormDataPart5.addFormDataPart("pg", uplaodAppRequestInfo.f10618pg + "").addFormDataPart("pv", uplaodAppRequestInfo.f10619pv).addFormDataPart("ad", uplaodAppRequestInfo.f10613ad);
                MultipartBody.Builder addFormDataPart7 = addFormDataPart6.addFormDataPart("aa", uplaodAppRequestInfo.f10611aa + "");
                multipartBody = addFormDataPart7.addFormDataPart("isVa", uplaodAppRequestInfo.isVa + "").addFormDataPart("R", str4).addFormDataPart("Sign", str5).build();
            } else {
                MultipartBody.Builder addFormDataPart8 = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("UserId", String.valueOf(LoginManager.m12620d().m12614j()));
                MultipartBody.Builder addFormDataPart9 = addFormDataPart8.addFormDataPart("UploadStatue", uplaodAppRequestInfo.UploadStatue + "").addFormDataPart("LocalAppFileMD5", uplaodAppRequestInfo.LocalAppFileMD5).addFormDataPart("a", uplaodAppRequestInfo.f10610a).addFormDataPart("b", uplaodAppRequestInfo.f10614b).addFormDataPart("ab", uplaodAppRequestInfo.f10612ab).addFormDataPart("bc", uplaodAppRequestInfo.f10615bc);
                MultipartBody.Builder addFormDataPart10 = addFormDataPart9.addFormDataPart("d", uplaodAppRequestInfo.f10616d + "");
                MultipartBody.Builder addFormDataPart11 = addFormDataPart10.addFormDataPart("de", uplaodAppRequestInfo.f10617de + "").addFormDataPart(ServiceManagerNative.f10497VS, uplaodAppRequestInfo.f10621vs);
                MultipartBody.Builder addFormDataPart12 = addFormDataPart11.addFormDataPart("vc", uplaodAppRequestInfo.f10620vc + "");
                MultipartBody.Builder addFormDataPart13 = addFormDataPart12.addFormDataPart("pg", uplaodAppRequestInfo.f10618pg + "").addFormDataPart("pv", uplaodAppRequestInfo.f10619pv).addFormDataPart("ad", uplaodAppRequestInfo.f10613ad);
                MultipartBody.Builder addFormDataPart14 = addFormDataPart13.addFormDataPart("aa", uplaodAppRequestInfo.f10611aa + "");
                multipartBody = addFormDataPart14.addFormDataPart("isVa", uplaodAppRequestInfo.isVa + "").addFormDataPart("R", str4).addFormDataPart("Sign", str5).addFormDataPart("file", str3, RequestBody.create(MediaType.parse("multipart/form-data"), file)).build();
            }
            Request.Builder builder2 = new Request.Builder();
            Request build = builder2.header("Authorization", "Client-ID " + UUID.randomUUID()).url(str).post(multipartBody).build();
            OkHttpClient build2 = builder.connectTimeout(acf.f15175N, TimeUnit.SECONDS).writeTimeout(acf.f15175N, TimeUnit.SECONDS).readTimeout(acf.f15175N, TimeUnit.SECONDS).build();
            Log.e("上传", "开始enqueue");
            build2.newCall(build).enqueue(new Callback() { // from class: com.angel.nrzs.ui.activity.MainActivity.13
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException iOException) {
                    Log.e("上传", "失败");
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) throws IOException {
                    Log.e("上传", "成功");
                }
            });
        }
    }

    /* renamed from: a */
    public long m25025a(Date date, Date date2) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date2);
        return (instance2.getTimeInMillis() - instance.getTimeInMillis()) / WaitFor.ONE_DAY;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.angel.nrzs.p017ui.base.AppBaseActivity, android.support.p003v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        MainFragmentAdapter mainFragmentAdapter = this.f5391f;
        if (mainFragmentAdapter != null) {
            mainFragmentAdapter.notifyDataSetChanged();
        }
        FloatXnkjManager.INSTANCE.createSocket("lbsxxxx");
        PopShowManager.m3117a(this).m3114b();
        Log.e("PopShowManager", "onresume");
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
    }

    /* renamed from: f */
    private void m25016f() {
        if (!CommonUtil.m11900a((Activity) this)) {
            apf.m11843a(this, "common_shared_file", ShareVal.f16607q, 0);
        } else if (Build.VERSION.SDK_INT >= 28) {
            final View decorView = getWindow().getDecorView();
            decorView.post(new Runnable() { // from class: com.angel.nrzs.ui.activity.MainActivity.14
                @Override // java.lang.Runnable
                public void run() {
                    DisplayCutout displayCutout = decorView.getRootWindowInsets().getDisplayCutout();
                    if (displayCutout != null) {
                        apf.m11843a(MainActivity.this.getBaseContext(), "common_shared_file", ShareVal.f16607q, displayCutout.getSafeInsetTop());
                    }
                }
            });
        } else {
            apf.m11843a(this, "common_shared_file", ShareVal.f16607q, CommonUtil.m11896b((Activity) this));
        }
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18303b() {
        ImmersionBar.m20080a(this).m19994f();
        m25016f();
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    public void mo18305a(Bundle bundle) {
        this.f5394i = (TextView) findViewById(C0692R.C0694id.nrzs_app_tv_home);
        this.f5395j = (TextView) findViewById(C0692R.C0694id.nrzs_app_tv_ysj);
        this.f5396k = (TextView) findViewById(C0692R.C0694id.nrzs_app_tv_person);
        this.f5397l = (TextView) findViewById(C0692R.C0694id.nrzs_app_tv_xnkj);
        this.f5393h = (CustomViewPager) findViewById(C0692R.C0694id.nrzs_app_viewpager);
        this.f5393h.setOffscreenPageLimit(4);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18302b(Bundle bundle) {
        String[] split;
        String i = PreSetListManager.m12116a().m12107i();
        if (!TextUtils.isEmpty(i)) {
            for (String str : i.split(",")) {
                if (DataApp.m18939d().m18944b().equals(str)) {
                    this.f5401p = false;
                }
            }
        }
        String k = PreSetListManager.m12116a().m12105k();
        if (!TextUtils.isEmpty(k)) {
            for (String str2 : k.split(",")) {
                if (str2.equals("all") || DataApp.m18939d().m18944b().equals(str2)) {
                    this.f5402q = false;
                }
            }
        }
        this.f5399n = new ArrayList<>();
        this.f5398m = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        PersonFragment personFragment = new PersonFragment();
        DdyHomeFragment ddyHomeFragment = new DdyHomeFragment();
        this.f5399n.add(homeFragment);
        if (this.f5401p) {
            this.f5399n.add(ddyHomeFragment);
        } else {
            this.f5395j.setVisibility(8);
        }
        if (!XnkjUtils.m12528a() && this.f5402q) {
            this.f5399n.add(RouterUtils.getXnkjFragment());
        }
        this.f5399n.add(personFragment);
        this.f5398m.add(this.f5394i);
        if (this.f5401p) {
            this.f5398m.add(this.f5395j);
        }
        if (XnkjUtils.m12528a() || !this.f5402q) {
            this.f5397l.setVisibility(8);
        } else {
            this.f5398m.add(this.f5397l);
            this.f5397l.setVisibility(0);
        }
        this.f5398m.add(this.f5396k);
        this.f5390e = getSupportFragmentManager();
        this.f5391f = new MainFragmentAdapter(this.f5390e, this.f5399n);
        this.f5393h.setAdapter(this.f5391f);
        this.f5393h.setOffscreenPageLimit(4);
        this.f5394i.setSelected(true);
        if (!SPUtils.m23341a().m23318b(ShareVal.f16593c, false)) {
            FloatWindowManager.m12304a(this, new Observer<Integer>() { // from class: com.angel.nrzs.ui.activity.MainActivity.2
                @Override // p110z1.Observer
                public void onComplete() {
                }

                @Override // p110z1.Observer
                public void onError(Throwable th) {
                }

                @Override // p110z1.Observer
                public void onSubscribe(Disposable atrVar) {
                }

                /* renamed from: a */
                public void onNext(Integer num) {
                    int intValue = num.intValue();
                    if (intValue == 2) {
                        if (!SPUtils.m23341a().m23318b(ShareVal.f16593c, false)) {
                            new FtCheckFloatingDialog(MainActivity.this, 2).show();
                        }
                    } else if (intValue == 4) {
                        new FtCheckFloatingDialog(MainActivity.this, 4).show();
                    } else if (intValue == -1) {
                        new FtCheckFloatingDialog(MainActivity.this, -1).show();
                    }
                }
            });
        }
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: d */
    public void mo18300d() {
        this.f5393h.addOnPageChangeListener(this);
        this.f5395j.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ui.activity.MainActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.f5393h.setCurrentItem(1);
                MainActivity mainActivity = MainActivity.this;
                mainActivity.m25032a(mainActivity.f5395j);
            }
        });
        this.f5394i.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ui.activity.MainActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.f5393h.setCurrentItem(0);
                MainActivity mainActivity = MainActivity.this;
                mainActivity.m25032a(mainActivity.f5394i);
            }
        });
        this.f5396k.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ui.activity.MainActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.f5393h.setCurrentItem(MainActivity.this.f5398m.size() - 1);
                MainActivity mainActivity = MainActivity.this;
                mainActivity.m25032a(mainActivity.f5396k);
            }
        });
        this.f5397l.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ui.activity.MainActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity.this.f5401p) {
                    MainActivity.this.f5393h.setCurrentItem(2);
                } else {
                    MainActivity.this.f5393h.setCurrentItem(1);
                }
                MainActivity mainActivity = MainActivity.this;
                mainActivity.m25032a(mainActivity.f5397l);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m25032a(TextView textView) {
        this.f5394i.setSelected(false);
        this.f5395j.setSelected(false);
        this.f5396k.setSelected(false);
        this.f5397l.setSelected(false);
        textView.setSelected(true);
    }

    @Override // android.support.p003v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        TextView textView = this.f5398m.get(i);
        TextView textView2 = this.f5394i;
        if (textView == textView2) {
            m25032a(textView2);
            EventCollectManager.m12642a().m12640a(this, "导航首页", "导航首页", EventConstants.f16458z);
            ImmersionBar.m20080a(this).m19974h(false).m20018c(false).m19994f();
            return;
        }
        TextView textView3 = this.f5397l;
        if (textView == textView3) {
            m25032a(textView3);
            EventCollectManager.m12642a().m12640a(this, "后台挂机", "后台挂机", EventConstants.f16400D);
            ImmersionBar.m20080a(this).m19974h(false).m20018c(false).m19994f();
            return;
        }
        TextView textView4 = this.f5396k;
        if (textView == textView4) {
            m25032a(textView4);
            EventCollectManager.m12642a().m12640a(this, "导航我的", "导航我的", EventConstants.f16397A);
            ImmersionBar.m20080a(this).m19974h(false).m20018c(false).m19994f();
            return;
        }
        TextView textView5 = this.f5395j;
        if (textView == textView5) {
            m25032a(textView5);
            EventCollectManager.m12642a().m12640a(this, "云手机", "云手机", EventConstants.f16425ab);
            ImmersionBar.m20080a(this).m19974h(false).m20018c(false).m19994f();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.angel.nrzs.p017ui.base.AppBaseActivity, android.support.p006v7.app.AppCompatActivity, android.support.p003v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        SharedPreferencesUtil.m11436a("STATE_ISPAUSE", true);
        LogUtils.m23734c("onDestroy", "onChange");
        unregisterReceiver(this.f5387a);
        TentcentDownModel.m18803a().m18788b();
        OrcDownModel.m12861a(this).m12863a();
        ServiceConnection serviceConnection = this.f5406u;
        if (serviceConnection != null) {
            unbindService(serviceConnection);
        }
        EventBus.m3448a().m3430c(this);
    }

    /* renamed from: g */
    private void m25014g() {
        Intent intent = new Intent(this, NRZSAidlService.class);
        startService(intent);
        bindService(intent, this.f5406u, 1);
    }

    @Override // android.support.p003v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        m25012h();
    }

    /* renamed from: h */
    private void m25012h() {
        if (this.f5389d == 0) {
            this.f5389d = System.currentTimeMillis();
            aqg.m11586a(this, getResources().getString(C0692R.string.common_message_exit_app));
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f5389d > f5386b) {
            this.f5389d = currentTimeMillis;
            aqg.m11586a(this, getResources().getString(C0692R.string.common_message_exit_app));
            return;
        }
        SharedPreferencesUtil.m11436a("STATE_ISPAUSE", true);
        finish();
        System.exit(0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.p003v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int intExtra = intent.getIntExtra("position", -1);
        if (intExtra != -1) {
            this.f5393h.setCurrentItem(intExtra);
        }
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m25024a(FragmentEvent.C3555a aVar) {
        if (aVar.f16127c == 1) {
            this.f5399n.clear();
            this.f5399n.add(new ChannelFragment());
            if (this.f5401p) {
                this.f5399n.add(new DdyHomeFragment());
            }
            if (!XnkjUtils.m12528a() && this.f5402q) {
                this.f5399n.add(RouterUtils.getXnkjFragment());
            }
            this.f5399n.add(new PersonFragment());
            this.f5391f.m25183a(this.f5399n);
        } else if (aVar.f16127c == 2) {
            this.f5399n.clear();
            this.f5399n.add(new HomeFragment());
            if (this.f5401p) {
                this.f5399n.add(new DdyHomeFragment());
            }
            if (!XnkjUtils.m12528a() && this.f5402q) {
                this.f5399n.add(RouterUtils.getXnkjFragment());
            }
            this.f5399n.add(new PersonFragment());
            this.f5391f.m25183a(this.f5399n);
        }
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m25023a(LoginEvent.C3560a aVar) {
        if (aVar.f16136f == 1) {
            PopShowManager.m3117a(this).m3119a();
            PopShowManager.m3117a(this).m3114b();
            m25018e();
        }
    }
}
