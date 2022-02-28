package com.nrzs.user.p071ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.gyf.barlibrary.ImmersionBar;
import com.nrzs.base.router.RouterConstants;
import com.nrzs.data.game.bean.RdataBean;
import com.nrzs.data.game.bean.TopicInfo;
import com.nrzs.moudleui.pinnedheader.PinnedHeaderRecyclerView;
import com.nrzs.user.C2222R;
import com.nrzs.user.model.AllGameActivityModel;
import com.nrzs.user.p071ui.adapter.GameAllAdapter;
import com.nrzs.user.p071ui.base.UserBaseActivity;
import com.nrzs.user.p071ui.view.PYSideBar;
import java.util.ArrayList;
import java.util.List;
import p110z1.ChooseEvent;
import p110z1.EventBus;
import p110z1.IChooseCallback;
import p110z1.Subscribe;
import p110z1.ThreadMode;

@Route(path = RouterConstants.ModuleUser.CHOOSE_GAME)
/* renamed from: com.nrzs.user.ui.activity.ChooseGameActivity */
/* loaded from: classes2.dex */
public class ChooseGameActivity extends UserBaseActivity {

    /* renamed from: a */
    private GameAllAdapter f11355a;

    /* renamed from: b */
    private ImageView f11356b;

    /* renamed from: c */
    private PYSideBar f11357c;

    /* renamed from: d */
    private AllGameActivityModel f11358d;

    /* renamed from: f */
    private PinnedHeaderRecyclerView f11360f;

    /* renamed from: g */
    private RelativeLayout f11361g;

    /* renamed from: h */
    private int f11362h;

    /* renamed from: e */
    private List<TopicInfo> f11359e = new ArrayList();

    /* renamed from: i */
    private Observer<List<TopicInfo>> f11363i = new Observer<List<TopicInfo>>() { // from class: com.nrzs.user.ui.activity.ChooseGameActivity.1
        /* renamed from: a */
        public void onChanged(@Nullable List<TopicInfo> list) {
            ChooseGameActivity.this.f11359e.clear();
            if (list != null) {
                ChooseGameActivity.this.f11359e.addAll(list);
                ChooseGameActivity.this.f11355a.notifyDataSetChanged();
            }
        }
    };

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    protected int mo18307a() {
        return C2222R.layout.nrzs_game_activity_all_question;
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18303b() {
        ImmersionBar.m20080a(this).m20087a(C2222R.color.colorPrimary).m19974h(true).m19994f();
        this.f11362h = getIntent().getIntExtra("type", 0);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void mo18305a(Bundle bundle) {
        EventBus.m3448a().m3446a(this);
        this.f11360f = (PinnedHeaderRecyclerView) findViewById(C2222R.C2224id.nrzs_game_recycleview);
        this.f11361g = (RelativeLayout) findViewById(C2222R.C2224id.nrzs_game_rl_top);
        this.f11356b = (ImageView) findViewById(C2222R.C2224id.nrzs_game_backimg);
        this.f11357c = (PYSideBar) findViewById(C2222R.C2224id.nrzs_game_pysidebar_question);
        if (this.f11362h == 0) {
            this.f11361g.setVisibility(0);
        } else {
            this.f11361g.setVisibility(4);
        }
    }

    @Override // com.nrzs.user.p071ui.base.UserBaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    @Subscribe(m3389a = ThreadMode.MAIN)
    protected void onDestroy() {
        super.onDestroy();
        EventBus.m3448a().m3430c(this);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18302b(Bundle bundle) {
        if (this.f11358d == null) {
            this.f11358d = (AllGameActivityModel) ViewModelProviders.m25704of(this).get(AllGameActivityModel.class);
        }
        this.f11358d.m18391a().observe(this, this.f11363i);
        this.f11355a = new GameAllAdapter(this.f11359e, this.f11362h, new IChooseCallback() { // from class: com.nrzs.user.ui.activity.ChooseGameActivity.2
            @Override // p110z1.IChooseCallback
            /* renamed from: a */
            public void mo12562a(int i) {
            }

            @Override // p110z1.IChooseCallback
            /* renamed from: a */
            public void mo12561a(RdataBean rdataBean) {
            }

            @Override // p110z1.IChooseCallback
            /* renamed from: a */
            public void mo12560a(TopicInfo topicInfo) {
                EventBus.m3448a().m3427d(new ChooseEvent.C3858a(topicInfo));
                ChooseGameActivity.this.finish();
            }
        });
        this.f11360f.setAdapter(this.f11355a);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.nrzs.user.ui.activity.ChooseGameActivity.3
            @Override // android.support.v7.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i) {
                if (((TopicInfo) ChooseGameActivity.this.f11359e.get(i)).isFirst) {
                    return gridLayoutManager.getSpanCount();
                }
                return 1;
            }
        });
        this.f11360f.setLayoutManager(gridLayoutManager);
        this.f11358d.m18389b();
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: d */
    public void mo18300d() {
        this.f11356b.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.activity.ChooseGameActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChooseGameActivity.this.finish();
            }
        });
        this.f11357c.setOnTouchingLetterChangedListener(new PYSideBar.AbstractC2333a() { // from class: com.nrzs.user.ui.activity.ChooseGameActivity.5
            @Override // com.nrzs.user.p071ui.view.PYSideBar.AbstractC2333a
            /* renamed from: a */
            public void mo18187a(String str) {
                int c = ChooseGameActivity.this.f11355a.m18294c(str.charAt(0));
                if (c != -1) {
                    ChooseGameActivity.this.f11360f.smoothScrollToPosition(c);
                }
            }
        });
    }
}
