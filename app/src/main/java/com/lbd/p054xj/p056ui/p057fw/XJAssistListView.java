package com.lbd.p054xj.p056ui.p057fw;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.support.p006v7.widget.LinearLayoutManager;
import android.support.p006v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.Utils;
import com.lbd.p054xj.p056ui.adapter.AssistAdapter;
import com.nrzs.moudleui.adapter.BaseListAdapter;
import com.nrzs.p067ft.C1990R;
import p110z1.AssistListContract;
import p110z1.AssistListPresenter;
import p110z1.CdKeyDialog;
import p110z1.ChannelDataManager;
import p110z1.EventCollectManager;
import p110z1.EventConstants;
import p110z1.FloatDataManager;
import p110z1.FloatXnkjManager;
import p110z1.FwManager;
import p110z1.ILoadData;
import p110z1.IntentToAssistService;
import p110z1.LocalLoadHelper;
import p110z1.ShareVal;
import p110z1.apf;
import p110z1.apn;

/* renamed from: com.lbd.xj.ui.fw.XJAssistListView */
/* loaded from: classes.dex */
public class XJAssistListView extends FtBaseView implements AssistListContract.AbstractC3682b {

    /* renamed from: c */
    private AssistListContract.AbstractC3681a f9828c;

    /* renamed from: d */
    private AssistAdapter f9829d;

    /* renamed from: e */
    private RecyclerView f9830e;

    /* renamed from: f */
    private TextView f9831f;

    /* renamed from: g */
    private ImageView f9832g;

    /* renamed from: h */
    private ImageView f9833h;

    /* renamed from: i */
    private LinearLayoutManager f9834i;

    /* renamed from: j */
    private apn f9835j;

    /* renamed from: k */
    private TextView f9836k;

    public XJAssistListView(Context context, WindowManager windowManager) {
        super(context, windowManager);
        m19382h();
        m19383g();
        LogUtils.m23734c("onConfigurationChanged", "AssistListView:" + ScreenUtils.m23292g());
    }

    @Override // com.lbd.p054xj.p056ui.p057fw.FtBaseView
    public int getLayoutId() {
        return C1990R.layout.nrzs_ft_layout_assist_list;
    }

    @Override // com.lbd.p054xj.p056ui.p057fw.FtBaseView
    /* renamed from: a */
    public void mo19389a() {
        this.f9836k = (TextView) findViewById(C1990R.C1992id.nrzs_ft_tv_title);
        this.f9832g = (ImageView) findViewById(C1990R.C1992id.nrzs_ft_iv_close);
        this.f9833h = (ImageView) findViewById(C1990R.C1992id.nrzs_ft_iv_back);
        this.f9830e = (RecyclerView) findViewById(C1990R.C1992id.nrzs_ft_rv_assist);
        this.f9831f = (TextView) findViewById(C1990R.C1992id.nrzs_ft_tv_buy);
        this.f9831f.postDelayed(new Runnable() { // from class: com.lbd.xj.ui.fw.XJAssistListView.1
            @Override // java.lang.Runnable
            public void run() {
                FloatXnkjManager.INSTANCE.userEnginInit();
            }
        }, 500L);
    }

    @Override // com.lbd.p054xj.p056ui.p057fw.FtBaseView
    /* renamed from: b */
    public void mo19387b() {
        WindowManager.LayoutParams params = getParams();
        params.x = 0;
        params.y = 0;
        params.width = ScreenUtils.m23306a();
        params.height = ScreenUtils.m23302b();
        int systemUiVisibility = getSystemUiVisibility();
        if (Build.VERSION.SDK_INT >= 19) {
            setSystemUiVisibility(systemUiVisibility | 2 | 512 | 4 | 1024 | 2048);
        } else {
            setSystemUiVisibility(systemUiVisibility | 2 | 512 | 4 | 1024);
        }
        try {
            this.f9803b.addView(this, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.lbd.p054xj.p056ui.p057fw.FtBaseView
    /* renamed from: c */
    public void mo19385c() {
        if (getParent() != null) {
            this.f9803b.removeView(this);
        }
    }

    @Override // com.lbd.p054xj.p056ui.p057fw.FtBaseView
    /* renamed from: d */
    public void mo19384d() {
        this.f9833h.callOnClick();
    }

    /* renamed from: g */
    private void m19383g() {
        this.f9833h.setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.fw.XJAssistListView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FwManager.getInstance().removeXJAssistListView();
                FwManager.getInstance().initXJFloatView(true);
            }
        });
        this.f9832g.setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.fw.XJAssistListView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FwManager.getInstance().removeXJAssistListView();
                FwManager.getInstance().initXJFloatView(true);
            }
        });
        this.f9831f.setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.fw.XJAssistListView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtils.m23720e("悬浮", "进来");
                if (ChannelDataManager.m12655a().m12650d()) {
                    new CdKeyDialog(XJAssistListView.this.getContext()).show();
                    return;
                }
                EventCollectManager.m12642a().m12640a(XJAssistListView.this.getContext(), "悬浮窗脚本列表全平台购买", "悬浮窗脚本列表全平台购买", EventConstants.f16454v);
                if (apf.m11836b((Context) Utils.m24103a(), ShareVal.f16591a, ShareVal.f16614x, false)) {
                    new CdKeyDialog(XJAssistListView.this.getContext()).show();
                } else {
                    IntentToAssistService.m12809a(view.getContext(), 0L, 2);
                }
                FwManager.getInstance().removeXJAssistListView();
            }
        });
    }

    /* renamed from: h */
    private void m19382h() {
        EventCollectManager.m12642a().m12640a(getContext(), "悬浮窗脚本列表", "悬浮窗脚本列表", EventConstants.f16443k);
        this.f9836k.setText(FloatDataManager.m12352j().m12356f().TopicName);
        this.f9835j = new apn(new LocalLoadHelper(getContext(), this.f9830e, null, null, null, new View.OnClickListener() { // from class: com.lbd.xj.ui.fw.XJAssistListView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XJAssistListView.this.f9835j.mo11686a();
            }
        }), new ILoadData() { // from class: com.lbd.xj.ui.fw.XJAssistListView.6
            @Override // p110z1.ILoadData
            /* renamed from: a */
            public void mo11683a(int i) {
                XJAssistListView.this.f9828c.mo12240c();
            }
        });
        new AssistListPresenter(this);
        this.f9828c.mo12243a();
        this.f9835j.mo11686a();
        this.f9834i = new LinearLayoutManager(getContext());
        if (ScreenUtils.m23290h()) {
            this.f9834i.setOrientation(1);
        } else if (ScreenUtils.m23292g()) {
            this.f9834i.setOrientation(0);
        }
        this.f9830e.setLayoutManager(this.f9834i);
        this.f9829d = new AssistAdapter();
        this.f9830e.setAdapter(this.f9829d);
    }

    public void setPresenter(AssistListContract.AbstractC3681a aVar) {
        this.f9828c = aVar;
    }

    @Override // p110z1.AssistListContract.AbstractC3682b
    public BaseListAdapter getAdapter() {
        return this.f9829d;
    }

    @Override // p110z1.AssistListContract.AbstractC3682b
    /* renamed from: e */
    public void mo12493e() {
        this.f9835j.mo11679n_();
    }

    @Override // p110z1.AssistListContract.AbstractC3682b
    /* renamed from: f */
    public void mo12492f() {
        this.f9835j.mo11681l_();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LogUtils.m23734c("onConfigurationChanged", "onConfigurationChanged:" + configuration.orientation);
        if (configuration.orientation == 1) {
            this.f9834i.setOrientation(1);
        } else if (configuration.orientation == 2) {
            this.f9834i.setOrientation(0);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        LogUtils.m23734c("onConfigurationChanged", "onConfigurationChanged:" + ScreenUtils.m23292g());
        if (ScreenUtils.m23290h()) {
            this.f9834i.setOrientation(1);
        } else {
            this.f9834i.setOrientation(0);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
