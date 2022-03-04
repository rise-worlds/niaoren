package com.nrzs.user.ui.activity;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.gyf.barlibrary.ImmersionBar;
import com.nrzs.base.router.RouterConstants;
import com.nrzs.data.game.bean.PagesBean;
import com.nrzs.data.game.bean.RdataBean;
import com.nrzs.data.game.bean.TopicInfo;
import com.nrzs.data.game.bean.response.TopicDetailResponseInfo;
import com.nrzs.user.C2222R;
import com.nrzs.user.model.GameTopicModel;
import com.nrzs.user.ui.adapter.GameTopicListViewAdapter;
import com.nrzs.user.ui.base.UserBaseActivity;
import java.util.ArrayList;
import java.util.List;
import p110z1.ChooseEvent;
import p110z1.EventBus;
import p110z1.IChooseCallback;
import p110z1.ILoadData;
import p110z1.LocalLoadHelper;
import p110z1.apn;

@Route(path = RouterConstants.ModuleUser.CHOOSE_SCTIPT)
/* renamed from: com.nrzs.user.ui.activity.ChooseScriptActivity */
/* loaded from: classes2.dex */
public class ChooseScriptActivity extends UserBaseActivity {

    /* renamed from: a */
    public static final String f11370a = "topic_id_key";

    /* renamed from: b */
    private TextView f11371b;

    /* renamed from: c */
    private ImageView f11372c;

    /* renamed from: d */
    private TextView f11373d;

    /* renamed from: e */
    private SwipeRefreshLayout f11374e;

    /* renamed from: f */
    private RecyclerView f11375f;

    /* renamed from: g */
    private LinearLayout f11376g;

    /* renamed from: h */
    private GameTopicListViewAdapter f11377h;

    /* renamed from: i */
    private List<RdataBean> f11378i;

    /* renamed from: j */
    private LinearLayoutManager f11379j;

    /* renamed from: k */
    private PagesBean f11380k;

    /* renamed from: l */
    private PagesBean f11381l;

    /* renamed from: m */
    private apn f11382m;

    /* renamed from: n */
    private long f11383n;

    /* renamed from: o */
    private Observer<TopicDetailResponseInfo> f11384o = new Observer<TopicDetailResponseInfo>() { // from class: com.nrzs.user.ui.activity.ChooseScriptActivity.1
        /* renamed from: a */
        public void onChanged(@Nullable TopicDetailResponseInfo topicDetailResponseInfo) {
            ChooseScriptActivity.this.f11374e.setRefreshing(false);
            if (topicDetailResponseInfo == null) {
                ChooseScriptActivity.this.f11382m.mo11681l_();
                return;
            }
            ChooseScriptActivity.this.f11382m.mo11679n_();
            ChooseScriptActivity.this.f11380k = topicDetailResponseInfo.getPages();
            if (topicDetailResponseInfo.getRdata() != null && topicDetailResponseInfo.getRdata().size() > 0) {
                ChooseScriptActivity.this.f11378i.addAll(topicDetailResponseInfo.getRdata());
            }
            ChooseScriptActivity.this.f11377h.notifyDataSetChanged();
        }
    };

    /* renamed from: a */
    public static void m18372a(Context context, int i) {
        Intent intent = new Intent(context, ChooseScriptActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("topic_id_key", i);
        context.startActivity(intent);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    protected int mo18307a() {
        return C2222R.layout.activity_choose_script_list;
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18303b() {
        ImmersionBar.m20080a(this).m20087a(C2222R.color.colorPrimary).m19974h(true).m19994f();
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    public void mo18305a(Bundle bundle) {
        this.f11375f = (RecyclerView) findViewById(C2222R.C2224id.rcv_game_topic);
        this.f11374e = (SwipeRefreshLayout) findViewById(C2222R.C2224id.srl_game_topic);
        this.f11376g = (LinearLayout) findViewById(C2222R.C2224id.ll_buy_vip);
        this.f11371b = (TextView) findViewById(C2222R.C2224id.text_title);
        this.f11372c = (ImageView) findViewById(C2222R.C2224id.btn_back);
        this.f11373d = (TextView) findViewById(C2222R.C2224id.btn_repair_game);
        this.f11382m = new apn(new LocalLoadHelper(getApplicationContext(), this.f11374e, new View.OnClickListener() { // from class: com.nrzs.user.ui.activity.ChooseScriptActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChooseScriptActivity.this.m18373a(1);
            }
        }), new ILoadData() { // from class: com.nrzs.user.ui.activity.ChooseScriptActivity.3
            @Override // p110z1.ILoadData
            /* renamed from: a */
            public void mo11683a(int i) {
            }
        });
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18302b(Bundle bundle) {
        this.f11380k = new PagesBean();
        this.f11381l = new PagesBean();
        m18373a(1);
        this.f11379j = new LinearLayoutManager(getBaseContext());
        this.f11375f.setLayoutManager(this.f11379j);
        this.f11378i = new ArrayList();
        this.f11377h = new GameTopicListViewAdapter(this.f11378i, new IChooseCallback() { // from class: com.nrzs.user.ui.activity.ChooseScriptActivity.4
            @Override // p110z1.IChooseCallback
            /* renamed from: a */
            public void mo12562a(int i) {
            }

            @Override // p110z1.IChooseCallback
            /* renamed from: a */
            public void mo12560a(TopicInfo topicInfo) {
            }

            @Override // p110z1.IChooseCallback
            /* renamed from: a */
            public void mo12561a(RdataBean rdataBean) {
                EventBus.m3448a().m3427d(new ChooseEvent.C3859b(rdataBean));
                ChooseScriptActivity.this.finish();
            }
        });
        this.f11383n = getIntent().getIntExtra("topic_id_key", -1);
        this.f11377h.m18293a(this.f11383n);
        this.f11375f.setAdapter(this.f11377h);
        this.f11371b.setText("选择脚本");
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: d */
    public void mo18300d() {
        this.f11373d.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.activity.ChooseScriptActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        this.f11375f.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.nrzs.user.ui.activity.ChooseScriptActivity.6
            @Override // android.support.v7.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
            }

            @Override // android.support.v7.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (ChooseScriptActivity.this.f11379j.findLastVisibleItemPosition() + 1 == ChooseScriptActivity.this.f11379j.getItemCount() && ChooseScriptActivity.this.f11380k.getIsLastPage() != 1 && ChooseScriptActivity.this.f11381l.getCurrentPage() < ChooseScriptActivity.this.f11380k.getCurrentPage()) {
                    ChooseScriptActivity chooseScriptActivity = ChooseScriptActivity.this;
                    chooseScriptActivity.m18373a(chooseScriptActivity.f11380k.getCurrentPage() + 1);
                    ChooseScriptActivity chooseScriptActivity2 = ChooseScriptActivity.this;
                    chooseScriptActivity2.f11381l = chooseScriptActivity2.f11380k;
                }
            }
        });
        this.f11374e.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.nrzs.user.ui.activity.ChooseScriptActivity.7
            @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
            public void onRefresh() {
                ChooseScriptActivity.this.f11376g.setVisibility(8);
                ChooseScriptActivity.this.f11374e.setRefreshing(true);
                ChooseScriptActivity.this.f11378i.clear();
                ChooseScriptActivity.this.m18373a(1);
            }
        });
        this.f11372c.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.activity.ChooseScriptActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChooseScriptActivity.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m18373a(int i) {
        this.f11382m.mo11682k_();
        GameTopicModel gameTopicModel = new GameTopicModel(getApplication());
        gameTopicModel.m18387a().observe(this, this.f11384o);
        gameTopicModel.m18386a(getIntent().getIntExtra("topic_id_key", 0), i, 10);
    }
}
