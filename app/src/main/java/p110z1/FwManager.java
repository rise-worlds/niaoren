package p110z1;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.WindowManager;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.cyjh.ddy.media.media.ActionCode;
import com.lbd.xj.C1467R;
import com.lbd.xj.app.XJApp;
import com.lbd.xj.manager.XJDataManager;
import com.lbd.xj.ui.p057fw.XJAssistInfoView;
import com.lbd.xj.ui.p057fw.XJAssistListView;
import com.lbd.xj.ui.p057fw.XJFloatView;
import com.lbd.xj.ui.p057fw.XJInnerFloatView;
import com.lbd.xj.ui.p057fw.XJLiveView;
import com.lbd.xj.ui.p057fw.XJOutFloatView;
import com.lbd.xj.service.XJFloatService;
import com.lbd.xj.socket.SocketManagerServer;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.nrzs.base.router.RouterUtils;
import com.nrzs.data.p066ft.bean.AssistInfo;

/* renamed from: z1.adu */
/* loaded from: classes3.dex */
public enum FwManager {
    INSTAANCE;
    
    private static final String TAG = "FwManager";
    private XJAssistInfoView assistInfoView;
    private XJAssistListView assistListView;
    private Context context;
    private XJFtNeedGoldDialog ftNeedGoldDialog;
    private XJFtUserKickDialog ftUserKickDialog;
    private int getHeiht;
    private int getWiidth;
    public int height_vertical;
    public boolean isFront;
    private WindowManager.LayoutParams mParams;
    public int mParamsX;
    public int mParamsY;
    public int mPheight;
    public int mPwidth;
    private float originScale;
    private int realHeight;
    private int realWidth;
    public float screen_w_h;
    public int surfaceMagin;
    private WindowManager wManager;
    public int width_change;
    public int width_vertical;
    private XJFloatView xjFloatView;
    private XJInnerFloatView xjInnerFloatView;
    private XJOutFloatView xjOutFloatView;
    private Handler handler = new Handler();
    public boolean isCkyx = false;
    public boolean isNav = true;
    private boolean isShowAssisList = false;
    public boolean isRunScript = false;
    public boolean isFristOuter = false;

    public void onPause() {
    }

    public void onResume() {
    }

    FwManager() {
    }

    public static FwManager getInstance() {
        return INSTAANCE;
    }

    public void initData() {
        this.originScale = 0.5f;
        this.height_vertical = (int) (getHeiht() * this.originScale);
        this.screen_w_h = (getWiidth() * 1.0f) / getHeiht();
        this.width_vertical = (int) (this.height_vertical * this.screen_w_h);
        this.width_change = aeo.m13916b(XJApp.getInstance().getApplicationContext()) - 35;
        this.getHeiht = 0;
        this.getWiidth = 0;
        this.mPwidth = 0;
        this.mPheight = 0;
        this.surfaceMagin = 35;
        this.realHeight = aeo.m13920a(XJApp.getInstance().getApplicationContext());
        this.realWidth = aeo.m13916b(XJApp.getInstance().getApplicationContext());
    }

    public void initOnCreate(Context context) {
        this.context = context.getApplicationContext();
        this.wManager = (WindowManager) context.getApplicationContext().getSystemService("window");
        this.mParams = new WindowManager.LayoutParams();
        this.mParams.type = Build.VERSION.SDK_INT > 25 ? 2038 : ActionCode.CtrlConnectRefuse_2002;
        WindowManager.LayoutParams layoutParams = this.mParams;
        layoutParams.format = -3;
        layoutParams.flags |= 8;
        this.mParams.width = SizeUtils.m23262a(70.0f);
        this.mParams.height = SizeUtils.m23262a(70.0f);
        WindowManager.LayoutParams layoutParams2 = this.mParams;
        layoutParams2.gravity = 51;
        layoutParams2.systemUiVisibility = 2;
        layoutParams2.alpha = 1.0f;
        layoutParams2.x = 0;
        layoutParams2.y = 0;
        this.xjFloatView = new XJFloatView(context.getApplicationContext(), this.wManager, this.mParams);
        this.xjInnerFloatView = new XJInnerFloatView(context.getApplicationContext(), this.wManager, this.mParams);
        this.xjOutFloatView = new XJOutFloatView(context.getApplicationContext(), this.wManager, this.mParams);
    }

    public void initXJFloatView(boolean z) {
        String h = GeneralUtil.m13996h(XJApp.getInstance().getApplicationContext());
        LogUtils.m23734c("initXJFloatView", Boolean.valueOf(z), h);
        if (!TextUtils.isEmpty(h) && (h.equals("com.nrzs.game.ui.activity.GameAllActivity") || "com.angel.nrzs.ui.activity.NRZSWebviewActivity".equals(h) || "com.angel.nrzs.ui.activity.MainActivity".equals(h) || "com.angel.nrzs.ui.activity.RewardActivity".equals(h) || "com.nrzs.user.ui.activity.LoginActivity".equals(h))) {
            getInstance().removeXJFloatView();
        } else if (!z || !this.isShowAssisList) {
            int b = SharepreferenceUtil.m13883b(ValShare.f16644c, 0);
            removeXJInnerFloatView();
            removeXJOuterFloatView();
            removeXJAssistListView();
            removeXJAssisInfoView();
            this.xjFloatView.setBackgroundResource(C1467R.C1468drawable.bird_logo_pop);
            if (this.xjFloatView.getParent() != null) {
                this.xjFloatView.setVisibility(b);
                return;
            }
            this.xjFloatView.m19381a();
            this.xjFloatView.setVisibility(b);
            if (!z && this.isCkyx) {
                initXJOuterFloatView();
                this.xjOutFloatView.m19336c();
                this.isCkyx = false;
            }
        } else {
            this.isShowAssisList = false;
            initXJAssistListView();
        }
    }

    public void showXJAssistListView() {
        this.isShowAssisList = true;
    }

    public void initXJAssistListView() {
        this.assistListView = new XJAssistListView(this.context, this.wManager);
        this.assistListView.mo19387b();
        removeXJFloatView();
    }

    public void removeXJAssistListView() {
        XJAssistListView xJAssistListView = this.assistListView;
        if (xJAssistListView != null) {
            xJAssistListView.mo19385c();
        }
    }

    public void setRunScript(boolean z) {
        this.isRunScript = z;
        initXJFloatView(true);
    }

    public void initAssistInfoView(AssistInfo assistInfo) {
        removeXJAssistListView();
        this.assistInfoView = new XJAssistInfoView(this.context, assistInfo, this.wManager);
        this.assistInfoView.mo19387b();
    }

    public void removeXJAssisInfoView() {
        XJAssistInfoView xJAssistInfoView = this.assistInfoView;
        if (xJAssistInfoView != null) {
            xJAssistInfoView.mo19385c();
        }
    }

    public void setVisible(int i) {
        XJFloatView xJFloatView = this.xjFloatView;
        if (xJFloatView != null && xJFloatView.getParent() != null) {
            this.xjFloatView.setVisibility(i);
        }
    }

    public void initXJInnerFloatView() {
        removeXJFloatView();
        this.xjInnerFloatView.m19371b();
    }

    public void initXJOuterFloatView() {
        int i;
        removeXJFloatView();
        try {
            this.screen_w_h = (getWiidth() * 1.0f) / getHeiht();
            this.width_vertical = (int) (this.height_vertical * this.screen_w_h);
            this.height_vertical = (int) (getHeiht() * this.originScale);
            LogUtils.m23720e("showWindow", "showWindow:  height_vertical " + this.height_vertical + "width_verticalt  " + this.width_vertical + "screen_w_h" + this.screen_w_h);
            this.mParams.width = this.width_vertical;
            this.mParams.height = this.height_vertical;
            PreferencesUtil.m13937a().m13931a("isRotation", (Object) true);
            LogUtils.m23720e("showWindow", "showWindow:  mParams.width  " + this.mParams.width + "mParams.height  " + this.mParams.height);
        } catch (Exception e) {
            LogUtils.m23720e(e.getMessage());
            WindowManager.LayoutParams layoutParams = this.mParams;
            layoutParams.width = this.width_vertical;
            layoutParams.height = this.height_vertical;
        }
        int i2 = this.mPheight;
        if (!(i2 == 0 || (i = this.mPwidth) == 0)) {
            WindowManager.LayoutParams layoutParams2 = this.mParams;
            layoutParams2.width = i;
            layoutParams2.height = i2;
        }
        this.mParams.x = aeo.m13910e() - this.mParams.width;
        this.mParams.y = aeo.m13912d() - this.mParams.height;
        LogUtils.m23734c(TAG, "this.mParams.width:" + this.mParams.width + ",this.mParams.height:" + this.mParams.height);
        this.xjOutFloatView.setTuing(false);
        this.xjOutFloatView.m19342a(this.mParams);
    }

    public void removeXJFloatView() {
        this.xjFloatView.m19378b();
    }

    public void removeXJInnerFloatView() {
        XJInnerFloatView xJInnerFloatView = this.xjInnerFloatView;
        if (xJInnerFloatView != null) {
            xJInnerFloatView.m19369c();
        }
    }

    public void removeXJOuterFloatView() {
        this.xjOutFloatView.m19339b();
    }

    public int getwidth_change() {
        return this.width_change;
    }

    public float getscreen_w_h() {
        return this.screen_w_h;
    }

    public int dip2px(Context context, float f) {
        return (int) TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    private int getHeiht() {
        int i;
        if (XJLiveView.f9862a != 0) {
            return XJLiveView.f9862a;
        }
        aeo.m13920a(XJApp.getInstance().getApplicationContext());
        try {
            i = SizeUtils.m23262a(24.0f) + ((Integer) PreferencesUtil.m13937a().m13927b("surfaceH", Integer.valueOf(aeo.m13925a()))).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            i = 0;
        }
        this.getHeiht = i;
        return this.getHeiht;
    }

    public int getRealWidth() {
        this.realWidth = aeo.m13916b(XJApp.getInstance().getApplicationContext());
        return this.realWidth;
    }

    private int getWiidth() {
        int i;
        if (XJLiveView.f9863b != 0) {
            return XJLiveView.f9863b;
        }
        try {
            i = ((Integer) PreferencesUtil.m13937a().m13927b("surfaceW", Integer.valueOf(aeo.m13908f()))).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            i = 0;
        }
        this.getWiidth = i;
        return this.getWiidth;
    }

    public void startActivity(Context context, Class cls) {
        if (isExsitMianActivity(context, cls) && XJDataManager.INSTANCE.isFullActivityIsStart()) {
            Intent intent = new Intent();
            intent.setFlags(268435456);
            intent.setClass(context, cls);
            PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 0);
            try {
                LogUtils.m23720e("startActivity", "PendingIntent");
                if (Build.MANUFACTURER.equals("Xiaomi")) {
                    LogUtils.m23720e("startActivity", "startActivity: 1");
                    SystemHelper.m13835a(context);
                    return;
                }
                LogUtils.m23720e("startActivity", "startActivity: 2");
                XJLiveView.m19351c();
                activity.send();
            } catch (Exception e) {
                e.printStackTrace();
                XJLiveView.m19351c();
                context.startActivity(intent);
                LogUtils.m23720e("startActivity", "startActivity");
            }
        }
    }

    private boolean isExsitMianActivity(Context context, Class cls) {
        ComponentName resolveActivity = new Intent(context, cls).resolveActivity(context.getPackageManager());
        if (resolveActivity == null) {
            return false;
        }
        for (ActivityManager.RunningTaskInfo runningTaskInfo : ((ActivityManager) context.getSystemService(ServiceManagerNative.ACTIVITY)).getRunningTasks(10)) {
            if (runningTaskInfo.topActivity.equals(resolveActivity)) {
                return true;
            }
        }
        return false;
    }

    public void socketMenuXJ() {
        this.handler.post(new Runnable() { // from class: z1.adu.1
            @Override // java.lang.Runnable
            public void run() {
                FwManager.this.sendService(3);
            }
        });
    }

    public void sendInnerBack() {
        this.handler.post(new Runnable() { // from class: z1.adu.2
            @Override // java.lang.Runnable
            public void run() {
                FwManager.this.sendService(8);
            }
        });
    }

    public void sendInnerHome() {
        this.handler.post(new Runnable() { // from class: z1.adu.3
            @Override // java.lang.Runnable
            public void run() {
                FwManager.this.sendService(9);
            }
        });
    }

    public void sendInnerTask() {
        this.handler.post(new Runnable() { // from class: z1.adu.4
            @Override // java.lang.Runnable
            public void run() {
                FwManager.this.sendService(3);
            }
        });
    }

    public void shutDownXJ() {
        this.handler.post(new Runnable() { // from class: z1.adu.5
            @Override // java.lang.Runnable
            public void run() {
                FwManager.this.sendService(5);
            }
        });
    }

    public void socketNotifyXJ() {
        this.handler.post(new Runnable() { // from class: z1.adu.6
            @Override // java.lang.Runnable
            public void run() {
                FwManager.this.sendService(4);
            }
        });
    }

    public void socketSetScreenXJ(int i, int i2, int i3) {
        Intent intent = new Intent(XJApp.getInstance().getApplicationContext(), XJFloatService.class);
        intent.putExtra("key", 6);
        intent.putExtra("rw", i);
        intent.putExtra("rh", i2);
        intent.putExtra("dpi", i3);
        aej.m13957a(XJApp.getInstance().getApplicationContext(), intent);
    }

    public void socketSetFloatXJ(int i) {
        Intent intent = new Intent(XJApp.getInstance().getApplicationContext(), XJFloatService.class);
        intent.putExtra("key", 7);
        intent.putExtra(ValShare.f16644c, i);
        aej.m13957a(XJApp.getInstance().getApplicationContext(), intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendService(int i) {
        Intent intent = new Intent(XJApp.getInstance().getApplicationContext(), XJFloatService.class);
        intent.putExtra("key", i);
        aej.m13957a(XJApp.getInstance().getApplicationContext(), intent);
    }

    public void sendActionToService(int i) {
        Intent intent = new Intent(XJApp.getInstance().getApplicationContext(), XJFloatService.class);
        intent.putExtra("key", 2);
        intent.putExtra("Action", i);
        aej.m13957a(XJApp.getInstance().getApplicationContext(), intent);
    }

    public void toMain() {
        RouterUtils.toMain(0);
    }

    public void stopScript() {
        FloatXnkjManager.INSTANCE.stopEngin(new FloatXnkjManager.AbstractC3864a() { // from class: z1.adu.7
            @Override // p110z1.FloatXnkjManager.AbstractC3864a
            /* renamed from: a */
            public void mo11569a(int i, String str) {
            }

            @Override // p110z1.FloatXnkjManager.AbstractC3864a
            /* renamed from: a */
            public void mo11570a() {
                FwManager aduVar = FwManager.this;
                aduVar.isRunScript = false;
                aduVar.initXJFloatView(true);
            }
        });
    }

    public void sendOpenImport() {
        SocketManagerServer.m19692b().m19695a("1", SocketConstants.f15221T);
    }

    public void sendResoultion() {
        SocketManagerServer.m19692b().m19695a("", SocketConstants.f15220S);
    }

    public void sendInnerVoice() {
        this.handler.post(new Runnable() { // from class: z1.adu.8
            @Override // java.lang.Runnable
            public void run() {
                FwManager.this.sendService(10);
            }
        });
    }

    public void showUserKickDialog(Context context) {
        showXJUserKickDialog(context, true);
    }

    public void showXJUserKickDialog(Context context, boolean z) {
        LogUtils.m23734c("newEngin", "onEventMainThread - showUserKickDialog");
        if (this.ftUserKickDialog == null) {
            this.ftUserKickDialog = new XJFtUserKickDialog(context);
        }
        if (!this.ftUserKickDialog.isShowing()) {
            this.ftUserKickDialog.show();
        }
        this.ftUserKickDialog.m14275a(z);
    }

    public void dismissXJFtUserKickDialog() {
        XJFtUserKickDialog adsVar = this.ftUserKickDialog;
        if (adsVar != null) {
            adsVar.dismiss();
            this.ftUserKickDialog = null;
        }
    }

    public void showFtNeedGoldDialog(Context context) {
        if (this.ftNeedGoldDialog == null) {
            this.ftNeedGoldDialog = new XJFtNeedGoldDialog(context);
        }
        if (!this.ftNeedGoldDialog.isShowing()) {
            this.ftNeedGoldDialog.show();
        }
    }

    public void dismissFtNeedGoldDialog() {
        XJFtNeedGoldDialog adrVar = this.ftNeedGoldDialog;
        if (adrVar != null) {
            adrVar.dismiss();
            this.ftNeedGoldDialog = null;
        }
    }
}
