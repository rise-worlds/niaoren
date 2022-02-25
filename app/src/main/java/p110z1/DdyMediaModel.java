package p110z1;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.blankj.utilcode.util.ScreenUtils;
import com.cyjh.ddy.media.bean.DdyUserInfo;
import com.cyjh.ddy.media.media.listener.IHwySDKListener;
import com.cyjh.ddysdk.device.base.constants.DdyDeviceErrorConstants;
import com.cyjh.ddysdk.device.media.DdyDeviceMediaContract;
import com.cyjh.ddysdk.device.media.DdyDeviceMediaHelper;
import com.cyjh.ddysdk.order.base.bean.NoticeSyncInfo;
import com.live.ddy.C1701R;
import com.live.ddy.visual.view.DdyMediaFloatView;
import com.live.ddy.visual.view.DdyMediaLayout;
import com.live.ddy.visual.view.DdyMediaNavigationLayout;
import com.nrzs.data.ddy.bean.request.GetDeviteToken;
import com.nrzs.data.ddy.bean.respond.DeviceToken;
import com.nrzs.http.UICallback;

/* renamed from: z1.aig */
/* loaded from: classes3.dex */
public class DdyMediaModel implements IDdyClickInterface {

    /* renamed from: a */
    private static final String f15925a = "DdyMediaModel";

    /* renamed from: b */
    private DdyDeviceMediaHelper f15926b;

    /* renamed from: c */
    private boolean f15927c;

    /* renamed from: d */
    private int f15928d;

    /* renamed from: e */
    private int f15929e;

    /* renamed from: f */
    private long f15930f;

    /* renamed from: g */
    private long f15931g;

    /* renamed from: h */
    private String f15932h;

    /* renamed from: i */
    private String f15933i;

    /* renamed from: j */
    private String f15934j;

    /* renamed from: k */
    private DdyMediaFloatView f15935k;

    /* renamed from: l */
    private DdyMediaDeviceListDialog f15936l;

    /* renamed from: m */
    private DdyMediaNavigationLayout f15937m;

    /* renamed from: o */
    private IDdyMediaActivity f15939o;

    /* renamed from: p */
    private DdyRepository f15940p;

    /* renamed from: n */
    private int f15938n = 0;

    /* renamed from: q */
    private UICallback<DeviceToken> f15941q = new UICallback<DeviceToken>() { // from class: z1.aig.1
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            if (DdyMediaModel.this.f15939o != null) {
                DdyMediaModel.this.f15939o.mo13131g();
            }
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(DeviceToken deviceToken) {
            if (deviceToken != null) {
                if (DdyMediaModel.this.f15939o != null) {
                    DdyMediaModel.this.f15939o.mo13135a(deviceToken.AccessToken);
                }
            } else if (DdyMediaModel.this.f15939o != null) {
                DdyMediaModel.this.f15939o.mo13131g();
            }
        }
    };

    /* renamed from: r */
    private DdyDeviceMediaContract.Callback f15942r = new DdyDeviceMediaContract.Callback() { // from class: z1.aig.2
        @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.Callback
        public void success(Object obj) {
            if (DdyMediaModel.this.f15929e == 0) {
                Log.d(DdyMediaModel.f15925a, "1 切换清晰度成功 " + DdyMediaModel.this.f15928d + "," + Thread.currentThread().getName());
                if (DdyMediaModel.this.f15928d == 0) {
                    DdyMediaModel.this.f15928d = 1;
                } else {
                    DdyMediaModel.this.f15928d = 0;
                }
                if (DdyMediaModel.this.f15936l != null) {
                    DdyMediaModel.this.f15936l.m13120c(DdyMediaModel.this.f15928d);
                }
                Log.d(DdyMediaModel.f15925a, "2 切换清晰度成功 " + DdyMediaModel.this.f15928d + "," + Thread.currentThread().getName());
                return;
            }
            if (DdyMediaModel.this.f15939o != null) {
                DdyMediaModel.this.f15939o.mo13128j();
            }
            DdyMediaModel.this.f15929e = 0;
        }

        @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.Callback
        public void failuer(DdyDeviceErrorConstants ddyDeviceErrorConstants, String str) {
            if (DdyMediaModel.this.f15929e == 0) {
                DdyMediaModel.this.f15936l.m13120c(3);
            } else if (DdyMediaModel.this.f15939o != null) {
                DdyMediaModel.this.f15939o.mo13131g();
            }
        }
    };

    /* renamed from: a */
    public void m13077a(IDdyMediaActivity aiaVar, long j, String str, String str2, String str3) {
        this.f15939o = aiaVar;
        if (this.f15926b == null) {
            this.f15926b = new DdyDeviceMediaHelper(this.f15939o.mo13134c());
        }
        this.f15932h = str;
        this.f15934j = str2;
        this.f15933i = str3;
        this.f15930f = j;
    }

    /* renamed from: a */
    public void m13079a(LinearLayout linearLayout) {
        this.f15927c = this.f15926b.init(m13084a(this.f15930f, this.f15932h), this.f15934j, "", new IHwySDKListener() { // from class: z1.aig.3
            @Override // com.cyjh.ddy.media.media.listener.IHwySDKListener
            public void actionCodeCallback(int i, String str) {
                Log.d(DdyMediaModel.f15925a, "actionCodeCallback:" + i + ",value:" + str + "," + Thread.currentThread().getName());
                if (i == 8004 && DdyMediaModel.this.f15939o != null) {
                    DdyMediaModel.this.f15939o.mo13133e();
                }
            }

            @Override // com.cyjh.ddy.media.media.listener.IHwySDKListener
            public void upFps(String str) {
                Log.d(DdyMediaModel.f15925a, "upFps()".concat(" fps:" + str));
            }

            @Override // com.cyjh.ddy.media.media.listener.IHwySDKListener
            public void upPing(String str) {
                Log.d(DdyMediaModel.f15925a, "upPing()".concat(" pingRtt:" + str));
            }

            @Override // com.cyjh.ddy.media.media.listener.IHwySDKListener
            public void autoRotateScreen(int i) {
                Log.d(DdyMediaModel.f15925a, "autoRotateScreen");
                if (DdyMediaModel.this.f15936l != null) {
                    DdyMediaModel.this.f15936l.m13122b(i);
                }
            }

            @Override // com.cyjh.ddy.media.media.listener.IHwySDKListener
            public void upClipboard(String str) {
                Log.d(DdyMediaModel.f15925a, "upClipboard");
            }

            @Override // com.cyjh.ddy.media.media.listener.IHwySDKListener
            public void upNoticeSyncInfo(NoticeSyncInfo noticeSyncInfo) {
                Log.d(DdyMediaModel.f15925a, "upNoticeSyncInfo");
            }

            @Override // com.cyjh.ddy.media.media.listener.IHwySDKListener
            public void upTraffic(long j, long j2) {
                Log.d(DdyMediaModel.f15925a, "upTraffic");
            }

            @Override // com.cyjh.ddy.media.media.listener.IHwySDKListener
            public void upFrameTime(long j) {
                Log.d(DdyMediaModel.f15925a, "upFrameTime");
            }
        }, linearLayout, false);
    }

    /* renamed from: a */
    public void m13078a(String str) {
        if (this.f15927c) {
            this.f15926b.setPullStreamRate(str, "");
            this.f15926b.setReConnect(true);
            this.f15926b.playMedia(Long.valueOf(this.f15930f).longValue(), this.f15932h, "", new DdyDeviceMediaContract.Callback() { // from class: z1.aig.4
                @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.Callback
                public void success(Object obj) {
                    Log.d(DdyMediaModel.f15925a, "loadMedia - success");
                }

                @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.Callback
                public void failuer(DdyDeviceErrorConstants ddyDeviceErrorConstants, String str2) {
                    Log.d(DdyMediaModel.f15925a, "loadMedia - failuer :" + ddyDeviceErrorConstants.name() + ",msg:" + str2);
                }
            });
            return;
        }
        Log.d(f15925a, "init sdk error");
    }

    /* renamed from: a */
    public void m13082a(Context context, RelativeLayout relativeLayout) {
        DdyMediaFloatView ddyMediaFloatView = this.f15935k;
        if (ddyMediaFloatView == null) {
            DdyMediaFloatView ddyMediaFloatView2 = new DdyMediaFloatView(context);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(9);
            layoutParams.addRule(10);
            layoutParams.topMargin = (ScreenUtils.m23302b() / 2) - 102;
            ddyMediaFloatView2.setLayoutParams(layoutParams);
            relativeLayout.addView(ddyMediaFloatView2);
            this.f15935k = ddyMediaFloatView2;
            this.f15935k.setiDdyClickInterface(this);
            this.f15935k.setVisibility(0);
            return;
        }
        ddyMediaFloatView.setVisibility(0);
    }

    /* renamed from: a */
    public void m13087a() {
        if (this.f15936l == null) {
            this.f15936l = new DdyMediaDeviceListDialog(this.f15939o.mo13134c());
            this.f15936l.m13124a(this);
            this.f15936l.setOnShowListener(new DialogInterface.OnShowListener() { // from class: z1.aig.5
                @Override // android.content.DialogInterface.OnShowListener
                public void onShow(DialogInterface dialogInterface) {
                    Log.d(DdyMediaModel.f15925a, "DdyMediaDeviceListDialog onShow," + Thread.currentThread().getName());
                    if (DdyMediaModel.this.f15935k != null) {
                        DdyMediaModel.this.f15935k.setVisibility(8);
                    }
                }
            });
            this.f15936l.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: z1.aig.6
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    Log.d(DdyMediaModel.f15925a, "DdyMediaDeviceListDialog onDismiss," + Thread.currentThread().getName());
                    if (DdyMediaModel.this.f15935k != null) {
                        DdyMediaModel.this.f15935k.setVisibility(0);
                    }
                }
            });
        }
        if (!this.f15936l.isShowing()) {
            this.f15936l.show();
        }
    }

    /* renamed from: a */
    public void m13083a(Context context, ViewGroup viewGroup, int i) {
        this.f15938n = i;
        DdyMediaNavigationLayout ddyMediaNavigationLayout = this.f15937m;
        if (ddyMediaNavigationLayout == null) {
            this.f15937m = new DdyMediaNavigationLayout(context, viewGroup);
            this.f15937m.setiDdyClickInterface(this);
            viewGroup.addView(this.f15937m);
            this.f15937m.setVisibility(i);
        } else {
            ddyMediaNavigationLayout.setVisibility(i);
        }
        m13080a(viewGroup);
    }

    @Override // p110z1.IDdyClickInterface
    /* renamed from: a */
    public void mo13081a(View view, int i, int i2, String str) {
        IDdyMediaActivity aiaVar;
        if (i2 == 0) {
            m13087a();
        } else if (i2 == 1) {
            if (i == 0) {
                IDdyMediaActivity aiaVar2 = this.f15939o;
                if (aiaVar2 != null) {
                    aiaVar2.mo13132f();
                }
            } else if (i == 1) {
                if (this.f15939o != null) {
                    this.f15939o.mo13137a(this.f15937m.getVisibility() == 0 ? 8 : 0);
                }
            } else if (i == 2 && (aiaVar = this.f15939o) != null) {
                aiaVar.mo13131g();
            }
        } else if (i2 != 2) {
        } else {
            if (i == 0) {
                this.f15926b.doKeyEvent(this.f15930f, 4);
            } else if (i == 1) {
                this.f15926b.doKeyEvent(this.f15930f, 3);
            } else if (i == 2) {
                this.f15926b.doKeyEvent(this.f15930f, 187);
            }
        }
    }

    @Override // p110z1.IDdyClickInterface
    /* renamed from: a */
    public void mo13085a(long j, long j2) {
        IDdyMediaActivity aiaVar = this.f15939o;
        if (aiaVar != null) {
            aiaVar.mo13136a(j, j2);
        }
    }

    /* renamed from: b */
    public void m13074b() {
        DdyDeviceMediaHelper ddyDeviceMediaHelper = this.f15926b;
        if (ddyDeviceMediaHelper != null) {
            int i = this.f15928d;
            if (i == 0) {
                ddyDeviceMediaHelper.setPullStreamRate("2000", null);
            } else if (i == 1) {
                ddyDeviceMediaHelper.setPullStreamRate(EventConstants.f16434b, null);
            }
            this.f15926b.changeMedia(this.f15930f, this.f15932h, this.f15933i, this.f15942r);
        }
    }

    /* renamed from: b */
    public void m13073b(long j, long j2) {
        this.f15931g = j;
        this.f15929e = 1;
        DdyMediaFloatView ddyMediaFloatView = this.f15935k;
        if (ddyMediaFloatView != null) {
            ddyMediaFloatView.setVisibility(8);
        }
        DdyMediaNavigationLayout ddyMediaNavigationLayout = this.f15937m;
        if (ddyMediaNavigationLayout != null) {
            ddyMediaNavigationLayout.setVisibility(8);
        }
        DdyMediaDeviceListDialog aibVar = this.f15936l;
        if (aibVar != null) {
            aibVar.m13126a(1);
            this.f15936l = null;
        }
        m13086a(j2);
    }

    /* renamed from: a */
    private void m13086a(long j) {
        if (this.f15940p == null) {
            this.f15940p = new DdyRepository();
        }
        GetDeviteToken getDeviteToken = new GetDeviteToken();
        getDeviteToken.UserId = LoginManager.m12620d().m12614j();
        getDeviteToken.OrderId = j;
        this.f15940p.m12875a(getDeviteToken, this.f15941q);
    }

    /* renamed from: b */
    public void m13072b(String str) {
        this.f15934j = str;
        this.f15930f = this.f15931g;
        this.f15926b.setDeviceToken(this.f15934j);
        this.f15926b.changeMedia(this.f15930f, this.f15932h, this.f15933i, this.f15942r);
    }

    /* renamed from: c */
    public void m13069c() {
        DdyMediaFloatView ddyMediaFloatView = this.f15935k;
        if (ddyMediaFloatView != null) {
            ddyMediaFloatView.setVisibility(0);
        }
        DdyMediaNavigationLayout ddyMediaNavigationLayout = this.f15937m;
        if (ddyMediaNavigationLayout != null) {
            ddyMediaNavigationLayout.setVisibility(this.f15938n);
        }
    }

    /* renamed from: d */
    public void m13067d() {
        DdyMediaDeviceListDialog aibVar = this.f15936l;
        if (aibVar != null) {
            aibVar.m13126a(1);
        }
        DdyDeviceMediaHelper ddyDeviceMediaHelper = this.f15926b;
        if (ddyDeviceMediaHelper != null) {
            ddyDeviceMediaHelper.uninit();
        }
    }

    /* renamed from: a */
    public DdyUserInfo m13084a(long j, String str) {
        DdyUserInfo ddyUserInfo = new DdyUserInfo();
        ddyUserInfo.OrderId = j;
        ddyUserInfo.UCID = str;
        return ddyUserInfo;
    }

    /* renamed from: a */
    public void m13080a(ViewGroup viewGroup) {
        DdyMediaLayout ddyMediaLayout = (DdyMediaLayout) viewGroup.findViewById(C1701R.C1703id.nrzs_ddy_id_ll_media);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ddyMediaLayout.getLayoutParams();
        if (this.f15938n == 8) {
            layoutParams.rightMargin = 0;
            layoutParams.bottomMargin = 0;
        } else {
            Log.i("DDYLBSD", "setMediaViewSize");
            int a = DdyScreenUtil.m13144a(viewGroup.getContext(), 44.0f);
            if (DdyScreenUtil.m13142c(viewGroup.getContext())) {
                layoutParams.rightMargin = a;
                Log.i("DDYLBSD", "layoutParams.rightMargin:" + layoutParams.rightMargin);
                layoutParams.bottomMargin = 0;
            } else {
                layoutParams.rightMargin = 0;
                layoutParams.bottomMargin = a;
                Log.i("DDYLBSD", "layoutParams.bottomMargin:" + layoutParams.bottomMargin);
            }
        }
        Log.i("DDYLBSD", "end layoutParams.rightMargin:" + layoutParams.rightMargin);
        ddyMediaLayout.setLayoutParams(layoutParams);
    }
}
