package com.nrzs.game.p069ui.activity;

import android.arch.lifecycle.Observer;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p003v4.widget.SwipeRefreshLayout;
import android.support.p006v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alipay.sdk.widget.C0675j;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.nrzs.base.router.RouterConstants;
import com.nrzs.base.router.provider.CarkeyProvider;
import com.nrzs.base.router.provider.ProviderFactory;
import com.nrzs.data.game.bean.PagesBean;
import com.nrzs.data.game.bean.RdataBean;
import com.nrzs.data.game.bean.TopicInfo;
import com.nrzs.data.game.bean.response.TopicDetailResponseInfo;
import com.nrzs.data.other.bean.AdResultInfoItem;
import com.nrzs.game.C2029R;
import com.nrzs.game.adapter.GameTopicListViewAdapter;
import com.nrzs.game.model.GameTopicModel;
import com.nrzs.game.p069ui.base.GameBaseActivity;
import com.nrzs.game.p069ui.view.GameTopicHeadView;
import com.nrzs.game.p069ui.view.XLinearLayoutManager;
import java.util.ArrayList;
import java.util.List;
import p110z1.ActionVal;
import p110z1.EventCollectManager;
import p110z1.EventConstants;
import p110z1.GlobalConstants;
import p110z1.HttpVal;
import p110z1.ILoadData;
import p110z1.IntentToAssistService;
import p110z1.LocalLoadHelper;
import p110z1.LoginManager;
import p110z1.PreSetListManager;
import p110z1.TopicInfoManager;
import p110z1.VAManager;
import p110z1.apn;

@Route(path = RouterConstants.ModuleGame.TOPIC)
/* renamed from: com.nrzs.game.ui.activity.GameTopicActivity */
/* loaded from: classes2.dex */
public class GameTopicActivity extends GameBaseActivity {

    /* renamed from: a */
    public static final String f11047a = "topic_id_key";

    /* renamed from: b */
    private TextView f11048b;

    /* renamed from: c */
    private ImageView f11049c;

    /* renamed from: d */
    private TextView f11050d;

    /* renamed from: e */
    private SwipeRefreshLayout f11051e;

    /* renamed from: f */
    private RecyclerView f11052f;

    /* renamed from: g */
    private LinearLayout f11053g;

    /* renamed from: h */
    private TextView f11054h;

    /* renamed from: i */
    private GameTopicListViewAdapter f11055i;

    /* renamed from: j */
    private List<RdataBean> f11056j;

    /* renamed from: k */
    private GameTopicHeadView f11057k;

    /* renamed from: l */
    private XLinearLayoutManager f11058l;

    /* renamed from: m */
    private PagesBean f11059m;

    /* renamed from: n */
    private PagesBean f11060n;

    /* renamed from: o */
    private apn f11061o;

    /* renamed from: p */
    private long f11062p;

    /* renamed from: q */
    private Observer<TopicDetailResponseInfo> f11063q = new Observer<TopicDetailResponseInfo>() { // from class: com.nrzs.game.ui.activity.GameTopicActivity.1
        /* renamed from: a */
        public void onChanged(@Nullable TopicDetailResponseInfo topicDetailResponseInfo) {
            GameTopicActivity.this.f11051e.setRefreshing(false);
            if (topicDetailResponseInfo == null) {
                GameTopicActivity.this.f11061o.mo11681l_();
                return;
            }
            GameTopicActivity.this.f11061o.mo11679n_();
            GameTopicActivity.this.f11057k.setdata(topicDetailResponseInfo);
            GameTopicActivity.this.f11059m = topicDetailResponseInfo.getPages();
            if (topicDetailResponseInfo.getRdata() != null && topicDetailResponseInfo.getRdata().size() > 0) {
                GameTopicActivity.this.f11056j.addAll(topicDetailResponseInfo.getRdata());
            }
            GameTopicActivity.this.f11055i.notifyDataSetChanged();
        }
    };

    /* renamed from: a */
    public static void m18649a(Context context, int i) {
        Intent intent = new Intent(context, GameTopicActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("topic_id_key", i);
        context.startActivity(intent);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    protected int mo18307a() {
        return C2029R.layout.nrzs_game_topic;
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18303b() {
        ImmersionBar.m20080a(this).m20087a(C2029R.color.colorPrimary).m19974h(true).m19994f();
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    public void mo18305a(Bundle bundle) {
        this.f11062p = getIntent().getIntExtra("topic_id_key", -1);
        this.f11052f = (RecyclerView) findViewById(C2029R.C2031id.rcv_game_topic);
        this.f11051e = (SwipeRefreshLayout) findViewById(C2029R.C2031id.srl_game_topic);
        this.f11053g = (LinearLayout) findViewById(C2029R.C2031id.ll_buy_vip);
        this.f11054h = (TextView) findViewById(C2029R.C2031id.text_buy_vip_content);
        this.f11048b = (TextView) findViewById(C2029R.C2031id.text_title);
        this.f11049c = (ImageView) findViewById(C2029R.C2031id.btn_back);
        this.f11050d = (TextView) findViewById(C2029R.C2031id.btn_repair_game);
        this.f11061o = new apn(new LocalLoadHelper(getApplicationContext(), this.f11051e, new View.OnClickListener() { // from class: com.nrzs.game.ui.activity.GameTopicActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameTopicActivity.this.m18650a(1);
            }
        }), new ILoadData() { // from class: com.nrzs.game.ui.activity.GameTopicActivity.3
            @Override // p110z1.ILoadData
            /* renamed from: a */
            public void mo11683a(int i) {
            }
        });
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18302b(Bundle bundle) {
        this.f11059m = new PagesBean();
        this.f11060n = new PagesBean();
        m18650a(1);
        this.f11058l = new XLinearLayoutManager(this, 1, false);
        this.f11052f.setLayoutManager(this.f11058l);
        this.f11056j = new ArrayList();
        this.f11057k = new GameTopicHeadView(this);
        this.f11055i = new GameTopicListViewAdapter(this.f11056j);
        this.f11055i.m18827a(this.f11062p);
        this.f11055i.m18491a((View) this.f11057k, 1, false);
        this.f11052f.setAdapter(this.f11055i);
        String stringExtra = getIntent().getStringExtra(C0675j.f373k);
        this.f11048b.setText(TextUtils.isEmpty(stringExtra) ? "" : stringExtra);
        this.f11055i.m18823a(stringExtra);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: d */
    public void mo18300d() {
        this.f11050d.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.game.ui.activity.GameTopicActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TopicInfo a = TopicInfoManager.m12726c().m12736a(GameTopicActivity.this.f11062p);
                if (a == null) {
                    ToastUtils.m23030a("游戏未添加");
                } else {
                    VAManager.m12070i().m12091a(GameTopicActivity.this, a.localAppPackage);
                }
            }
        });
        this.f11052f.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.nrzs.game.ui.activity.GameTopicActivity.5
            @Override // android.support.p006v7.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
            }

            @Override // android.support.p006v7.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (GameTopicActivity.this.f11058l.findLastVisibleItemPosition() + 1 == GameTopicActivity.this.f11058l.getItemCount() && GameTopicActivity.this.f11059m.getIsLastPage() != 1 && GameTopicActivity.this.f11060n.getCurrentPage() < GameTopicActivity.this.f11059m.getCurrentPage()) {
                    GameTopicActivity gameTopicActivity = GameTopicActivity.this;
                    gameTopicActivity.m18650a(gameTopicActivity.f11059m.getCurrentPage() + 1);
                    GameTopicActivity gameTopicActivity2 = GameTopicActivity.this;
                    gameTopicActivity2.f11060n = gameTopicActivity2.f11059m;
                }
            }
        });
        this.f11051e.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.nrzs.game.ui.activity.GameTopicActivity.6
            @Override // android.support.p003v4.widget.SwipeRefreshLayout.OnRefreshListener
            public void onRefresh() {
                GameTopicActivity.this.f11053g.setVisibility(8);
                GameTopicActivity.this.f11051e.setRefreshing(true);
                GameTopicActivity.this.f11056j.clear();
                GameTopicActivity.this.m18650a(1);
            }
        });
        this.f11049c.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.game.ui.activity.GameTopicActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameTopicActivity.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.p003v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m18650a(int i) {
        this.f11061o.mo11682k_();
        GameTopicModel gameTopicModel = new GameTopicModel(getApplication());
        gameTopicModel.m18807a().observe(this, this.f11063q);
        gameTopicModel.m18806a((int) this.f11062p, i, 10);
    }

    /* renamed from: a */
    private void m18648a(final TopicDetailResponseInfo topicDetailResponseInfo) {
        this.f11053g.setVisibility(0);
        this.f11053g.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.game.ui.activity.-$$Lambda$GameTopicActivity$9QJTGpWexLGkWleVwYtiI_2t_a0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GameTopicActivity.this.m18647a(topicDetailResponseInfo, view);
            }
        });
        if (topicDetailResponseInfo.getPlatformVIPInfo() == null) {
            this.f11054h.setText(Html.fromHtml("畅享全平台辅助，约<font color='#FFF700'>0</font>元/月"));
            return;
        }
        this.f11054h.setText(Html.fromHtml("畅享全平台辅助，约<font color='#FFF700'>" + topicDetailResponseInfo.getPlatformVIPInfo().getPrice() + "</font>元/月"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m18647a(TopicDetailResponseInfo topicDetailResponseInfo, View view) {
        try {
            if (!LoginManager.m12620d().m12606r()) {
                IntentToAssistService.m12813a(this);
            } else if (topicDetailResponseInfo.getPlatformVIPInfo() != null) {
                EventCollectManager.m12642a().m12640a(this, "游戏详情全平台", "游戏详情全平台", EventConstants.f16450r);
                CarkeyProvider createCarkey = ProviderFactory.createCarkey();
                if (!PreSetListManager.m12116a().m12110f() || createCarkey == null) {
                    Intent intent = new Intent(ActionVal.f16466c);
                    intent.addFlags(268435456);
                    AdResultInfoItem adResultInfoItem = new AdResultInfoItem();
                    adResultInfoItem.Title = "购买续费";
                    adResultInfoItem.ExecArgs = HttpVal.f16516c;
                    intent.putExtra(GlobalConstants.f16482e, 0);
                    intent.putExtra(GlobalConstants.f16481d, adResultInfoItem);
                    startActivity(intent);
                    return;
                }
                createCarkey.showdialog(this);
            }
        } catch (ActivityNotFoundException unused) {
            Log.d("FirstActivity.this", "找不到该活动");
        }
    }
}
