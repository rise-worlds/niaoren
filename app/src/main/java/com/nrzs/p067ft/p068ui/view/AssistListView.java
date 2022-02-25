package com.nrzs.p067ft.p068ui.view;

import android.content.Context;
import android.content.res.Configuration;
import android.support.p006v7.widget.LinearLayoutManager;
import android.support.p006v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.nrzs.moudleui.adapter.BaseListAdapter;
import com.nrzs.p067ft.C1990R;
import com.nrzs.p067ft.adapter.AssistAdapter;
import com.nrzs.p067ft.p068ui.base.FtBaseView;
import java.io.PrintStream;
import p110z1.AssistListContract;
import p110z1.AssistListPresenter;
import p110z1.CdKeyDialog;
import p110z1.ChannelDataManager;
import p110z1.EventCollectManager;
import p110z1.EventConstants;
import p110z1.FloatDataManager;
import p110z1.FloatViewManager;
import p110z1.ILoadData;
import p110z1.IntentToAssistService;
import p110z1.LocalLoadHelper;
import p110z1.apn;

/* renamed from: com.nrzs.ft.ui.view.AssistListView */
/* loaded from: classes2.dex */
public class AssistListView extends FtBaseView implements AssistListContract.AbstractC3682b {

    /* renamed from: a */
    private AssistListContract.AbstractC3681a f10763a;

    /* renamed from: b */
    private AssistAdapter f10764b;

    /* renamed from: c */
    private RecyclerView f10765c;

    /* renamed from: d */
    private TextView f10766d;

    /* renamed from: e */
    private ImageView f10767e;

    /* renamed from: f */
    private ImageView f10768f;

    /* renamed from: g */
    private LinearLayoutManager f10769g;

    /* renamed from: h */
    private apn f10770h;

    /* renamed from: i */
    private TextView f10771i;

    /* renamed from: j */
    private Context f10772j;

    public AssistListView(Context context) {
        super(context);
        this.f10772j = context;
        m18866h();
        m18867g();
        LogUtils.m23734c("onConfigurationChanged", "AssistListView:" + ScreenUtils.m23292g());
    }

    @Override // com.nrzs.p067ft.p068ui.base.FtBaseView
    public int getLayoutId() {
        return C1990R.layout.nrzs_ft_layout_assist_list;
    }

    @Override // com.nrzs.p067ft.p068ui.base.FtBaseView
    /* renamed from: a */
    public void mo18870a() {
        this.f10771i = (TextView) findViewById(C1990R.C1992id.nrzs_ft_tv_title);
        this.f10767e = (ImageView) findViewById(C1990R.C1992id.nrzs_ft_iv_close);
        this.f10768f = (ImageView) findViewById(C1990R.C1992id.nrzs_ft_iv_back);
        this.f10765c = (RecyclerView) findViewById(C1990R.C1992id.nrzs_ft_rv_assist);
        this.f10766d = (TextView) findViewById(C1990R.C1992id.nrzs_ft_tv_buy);
    }

    /* renamed from: g */
    private void m18867g() {
        this.f10768f.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.ft.ui.view.AssistListView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FloatViewManager.m12346a(AssistListView.this.getContext().getApplicationContext()).m12335b(AssistListView.class.getName());
                IntentToAssistService.m12804a(view.getContext(), FloatDataManager.m12352j().f16749e);
            }
        });
        this.f10767e.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.ft.ui.view.AssistListView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FloatViewManager.m12346a(AssistListView.this.getContext().getApplicationContext()).m12339a(AssistListView.class.getName());
            }
        });
        this.f10766d.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.ft.ui.view.AssistListView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Log.e("悬浮", "进来");
                PrintStream printStream = System.out;
                printStream.println("ChannelDataManager isChannelPayEnabled:" + ChannelDataManager.m12655a().m12650d());
                if (ChannelDataManager.m12655a().m12650d()) {
                    new CdKeyDialog(AssistListView.this.getContext()).show();
                    return;
                }
                EventCollectManager.m12642a().m12640a(AssistListView.this.getContext(), "悬浮窗脚本列表充值金币", "悬浮窗脚本列表充值金币", EventConstants.f16421Y);
                IntentToAssistService.m12800b(view.getContext(), 0L, 2);
            }
        });
    }

    /* renamed from: h */
    private void m18866h() {
        EventCollectManager.m12642a().m12640a(getContext(), "悬浮窗脚本列表", "悬浮窗脚本列表", EventConstants.f16443k);
        this.f10771i.setText(FloatDataManager.m12352j().m12356f() == null ? "脚本列表" : FloatDataManager.m12352j().m12356f().TopicName);
        this.f10770h = new apn(new LocalLoadHelper(getContext(), this.f10765c, null, null, null, new View.OnClickListener() { // from class: com.nrzs.ft.ui.view.AssistListView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AssistListView.this.f10770h.mo11686a();
            }
        }), new ILoadData() { // from class: com.nrzs.ft.ui.view.AssistListView.5
            @Override // p110z1.ILoadData
            /* renamed from: a */
            public void mo11683a(int i) {
                AssistListView.this.f10763a.mo12240c();
            }
        });
        new AssistListPresenter(this);
        this.f10763a.mo12243a();
        this.f10770h.mo11686a();
        this.f10769g = new LinearLayoutManager(getContext());
        if (ScreenUtils.m23290h()) {
            this.f10769g.setOrientation(1);
        } else if (ScreenUtils.m23292g()) {
            this.f10769g.setOrientation(0);
        }
        this.f10765c.setLayoutManager(this.f10769g);
        this.f10764b = new AssistAdapter(this.f10772j);
        this.f10765c.setAdapter(this.f10764b);
    }

    public void setPresenter(AssistListContract.AbstractC3681a aVar) {
        this.f10763a = aVar;
    }

    @Override // p110z1.AssistListContract.AbstractC3682b
    public BaseListAdapter getAdapter() {
        return this.f10764b;
    }

    @Override // p110z1.AssistListContract.AbstractC3682b
    /* renamed from: e */
    public void mo12493e() {
        this.f10770h.mo11679n_();
    }

    @Override // p110z1.AssistListContract.AbstractC3682b
    /* renamed from: f */
    public void mo12492f() {
        this.f10770h.mo11681l_();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LogUtils.m23734c("onConfigurationChanged", "onConfigurationChanged:" + configuration.orientation);
        if (configuration.orientation == 1) {
            this.f10769g.setOrientation(1);
        } else if (configuration.orientation == 2) {
            this.f10769g.setOrientation(0);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        LogUtils.m23734c("onConfigurationChanged", "onConfigurationChanged:" + ScreenUtils.m23292g());
        if (ScreenUtils.m23290h()) {
            this.f10769g.setOrientation(1);
        } else {
            this.f10769g.setOrientation(0);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
