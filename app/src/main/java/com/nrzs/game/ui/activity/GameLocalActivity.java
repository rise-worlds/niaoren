package com.nrzs.game.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.gyf.barlibrary.ImmersionBar;
import com.nrzs.core.models.GameInfo;
import com.nrzs.data.game.bean.TopicInfo;
import com.nrzs.game.C2029R;
import com.nrzs.game.adapter.GameLocalAdapter;
import com.nrzs.game.model.GameLocalModel;
import com.nrzs.game.ui.base.GameBaseActivity;
import com.nrzs.moudleui.pinnedheader.PinnedHeaderRecyclerView;
import java.util.ArrayList;
import java.util.List;
import p110z1.ILoadData;
import p110z1.LocalLoadHelper;
import p110z1.apn;

/* renamed from: com.nrzs.game.ui.activity.GameLocalActivity */
/* loaded from: classes2.dex */
public class GameLocalActivity extends GameBaseActivity {

    /* renamed from: a */
    private TopicInfo f10952a;

    /* renamed from: b */
    private PinnedHeaderRecyclerView f10953b;

    /* renamed from: c */
    private GameLocalAdapter f10954c;

    /* renamed from: d */
    private ImageView f10955d;

    /* renamed from: e */
    private apn f10956e;

    /* renamed from: f */
    private GameLocalModel f10957f;

    /* renamed from: g */
    private List<GameInfo> f10958g = new ArrayList();

    /* renamed from: h */
    private Observer<List<GameInfo>> f10959h = new Observer<List<GameInfo>>() { // from class: com.nrzs.game.ui.activity.GameLocalActivity.1
        /* renamed from: a */
        public void onChanged(@Nullable List<GameInfo> list) {
            GameLocalActivity.this.f10958g.clear();
            if (list == null || list.isEmpty()) {
                GameLocalActivity.this.f10956e.mo11680m_();
                return;
            }
            GameLocalActivity.this.f10956e.mo11679n_();
            GameLocalActivity.this.f10958g.addAll(list);
            GameLocalActivity.this.f10954c.notifyDataSetChanged();
        }
    };

    /* renamed from: a */
    public static void m18704a(Context context, TopicInfo topicInfo) {
        Log.e("选择游戏", topicInfo.TopicID + "进入了");
        Intent intent = new Intent(context, GameLocalActivity.class);
        intent.putExtra("topicInfo", topicInfo);
        intent.putExtra("isbackgrout", topicInfo.SportBackGround);
        context.startActivity(intent);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    protected int mo18307a() {
        return C2029R.layout.nrzs_game_activity_local;
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18303b() {
        ImmersionBar.m20080a(this).m20087a(C2029R.color.colorPrimary).m19974h(true).m19994f();
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    public void mo18305a(Bundle bundle) {
        this.f10953b = (PinnedHeaderRecyclerView) findViewById(C2029R.C2031id.nrzs_game_recycleview);
        this.f10955d = (ImageView) findViewById(C2029R.C2031id.nrzs_game_backimg);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18302b(Bundle bundle) {
        this.f10952a = (TopicInfo) getIntent().getParcelableExtra("topicInfo");
        this.f10952a.SportBackGround = getIntent().getIntExtra("isbackgrout", 0);
        if (this.f10957f == null) {
            this.f10957f = (GameLocalModel) ViewModelProviders.m25704of(this).get(GameLocalModel.class);
        }
        this.f10957f.m18812a().observe(this, this.f10959h);
        this.f10954c = new GameLocalAdapter(this.f10958g, this.f10952a);
        this.f10953b.setAdapter(this.f10954c);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.nrzs.game.ui.activity.GameLocalActivity.2
            @Override // android.support.p006v7.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i) {
                if (((GameInfo) GameLocalActivity.this.f10958g.get(i)).f10549m) {
                    return gridLayoutManager.getSpanCount();
                }
                return 1;
            }
        });
        this.f10953b.setLayoutManager(gridLayoutManager);
        this.f10956e = new apn(new LocalLoadHelper(this, this.f10953b, null, null, null, new View.OnClickListener() { // from class: com.nrzs.game.ui.activity.GameLocalActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameLocalActivity.this.f10956e.mo11686a();
            }
        }), new ILoadData() { // from class: com.nrzs.game.ui.activity.GameLocalActivity.4
            @Override // p110z1.ILoadData
            /* renamed from: a */
            public void mo11683a(int i) {
                GameLocalActivity.this.f10957f.m18809b();
            }
        });
        this.f10956e.mo11686a();
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: d */
    public void mo18300d() {
        this.f10955d.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.game.ui.activity.GameLocalActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameLocalActivity.this.finish();
            }
        });
    }
}
