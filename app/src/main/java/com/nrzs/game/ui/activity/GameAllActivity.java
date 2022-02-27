package com.nrzs.game.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.gyf.barlibrary.ImmersionBar;
import com.nrzs.base.router.RouterConstants;
import com.nrzs.data.game.bean.TopicInfo;
import com.nrzs.game.C2029R;
import com.nrzs.game.adapter.GameAllAdapter;
import com.nrzs.game.model.AllGameActivityModel;
import com.nrzs.game.ui.base.GameBaseActivity;
import com.nrzs.game.ui.view.PYSideBar;
import com.nrzs.moudleui.pinnedheader.PinnedHeaderRecyclerView;
import java.util.ArrayList;
import java.util.List;
import p110z1.ILoadData;
import p110z1.LocalLoadHelper;
import p110z1.apn;

@Route(path = RouterConstants.ModuleGame.ALL)
/* renamed from: com.nrzs.game.ui.activity.GameAllActivity */
/* loaded from: classes2.dex */
public class GameAllActivity extends GameBaseActivity {

    /* renamed from: a */
    private apn f10933a;

    /* renamed from: b */
    private GameAllAdapter f10934b;

    /* renamed from: c */
    private ImageView f10935c;

    /* renamed from: d */
    private ImageView f10936d;

    /* renamed from: e */
    private PYSideBar f10937e;

    /* renamed from: f */
    private AllGameActivityModel f10938f;

    /* renamed from: h */
    private PinnedHeaderRecyclerView f10940h;

    /* renamed from: i */
    private RelativeLayout f10941i;

    /* renamed from: j */
    private int f10942j;

    /* renamed from: g */
    private List<TopicInfo> f10939g = new ArrayList();

    /* renamed from: k */
    private Observer<List<TopicInfo>> f10943k = new Observer<List<TopicInfo>>() { // from class: com.nrzs.game.ui.activity.GameAllActivity.1
        /* renamed from: a */
        public void onChanged(@Nullable List<TopicInfo> list) {
            GameAllActivity.this.f10939g.clear();
            if (list != null) {
                GameAllActivity.this.f10939g.addAll(list);
                GameAllActivity.this.f10934b.notifyDataSetChanged();
            }
        }
    };

    /* renamed from: a */
    public static void m18710a(Context context) {
        context.startActivity(new Intent(context, GameAllActivity.class));
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    protected int mo18307a() {
        return C2029R.layout.nrzs_game_activity_all;
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18303b() {
        ImmersionBar.m20080a(this).m20087a(C2029R.color.colorPrimary).m19974h(true).m19994f();
        this.f10942j = getIntent().getIntExtra("type", 0);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    public void mo18305a(Bundle bundle) {
        this.f10940h = (PinnedHeaderRecyclerView) findViewById(C2029R.C2031id.nrzs_game_recycleview);
        this.f10941i = (RelativeLayout) findViewById(C2029R.C2031id.nrzs_game_rl_top);
        this.f10935c = (ImageView) findViewById(C2029R.C2031id.nrzs_game_backimg);
        this.f10936d = (ImageView) findViewById(C2029R.C2031id.nrzs_game_seachimg);
        this.f10937e = (PYSideBar) findViewById(C2029R.C2031id.nrzs_game_pysidebar);
        if (this.f10942j == 0) {
            this.f10941i.setVisibility(0);
        } else {
            this.f10941i.setVisibility(4);
        }
        this.f10933a = new apn(new LocalLoadHelper(getApplicationContext(), this.f10940h, null, null, null, new View.OnClickListener() { // from class: com.nrzs.game.ui.activity.GameAllActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameAllActivity.this.f10933a.mo11686a();
            }
        }), new ILoadData() { // from class: com.nrzs.game.ui.activity.GameAllActivity.3
            @Override // p110z1.ILoadData
            /* renamed from: a */
            public void mo11683a(int i) {
            }
        });
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18302b(Bundle bundle) {
        if (this.f10938f == null) {
            this.f10938f = (AllGameActivityModel) ViewModelProviders.m25704of(this).get(AllGameActivityModel.class);
        }
        this.f10938f.m18816a().observe(this, this.f10943k);
        this.f10934b = new GameAllAdapter(this.f10939g, this.f10942j);
        this.f10940h.setAdapter(this.f10934b);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.nrzs.game.ui.activity.GameAllActivity.4
            @Override // android.support.p006v7.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i) {
                if (((TopicInfo) GameAllActivity.this.f10939g.get(i)).isFirst) {
                    return gridLayoutManager.getSpanCount();
                }
                return 1;
            }
        });
        this.f10940h.setLayoutManager(gridLayoutManager);
        this.f10938f.m18814b();
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: d */
    public void mo18300d() {
        this.f10935c.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.game.ui.activity.GameAllActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameAllActivity.this.finish();
            }
        });
        this.f10936d.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.game.ui.activity.GameAllActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameSearchActivity.m18673a(GameAllActivity.this);
            }
        });
        this.f10937e.setOnTouchingLetterChangedListener(new PYSideBar.AbstractC2158a() { // from class: com.nrzs.game.ui.activity.GameAllActivity.7
            @Override // com.nrzs.game.ui.view.PYSideBar.AbstractC2158a
            /* renamed from: a */
            public void mo18590a(String str) {
                int c = GameAllActivity.this.f10934b.m18829c(str.charAt(0));
                if (c != -1) {
                    GameAllActivity.this.f10940h.smoothScrollToPosition(c);
                }
            }
        });
    }
}
