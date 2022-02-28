package com.angel.nrzs.ddy.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.angel.nrzs.C0692R;
import com.angel.nrzs.adapter.MoveGroupAdapter;
import com.angel.nrzs.ddy.model.DdyHomeFragmentModel;
import com.angel.nrzs.app.base.AppBaseActivity;
import com.blankj.utilcode.util.LogUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.nrzs.data.ddy.bean.respond.GroupInfo;
import com.nrzs.data.ddy.bean.respond.OrderDaileInfo;
import java.util.ArrayList;
import java.util.List;
import p110z1.ChooseCallback;
import p110z1.DoMoveGroupDialog;
import p110z1.EventBus;
import p110z1.GroupFlushEvent;
import p110z1.GroupManager;
import p110z1.MoveGroupDialog;
import p110z1.aqg;

/* loaded from: classes.dex */
public class MoveDdyActivity extends AppBaseActivity {

    /* renamed from: a */
    private ImageView f5236a;

    /* renamed from: b */
    private ImageView f5237b;

    /* renamed from: c */
    private TextView f5238c;

    /* renamed from: d */
    private RecyclerView f5239d;

    /* renamed from: e */
    private CheckBox f5240e;

    /* renamed from: i */
    private MoveGroupAdapter f5244i;

    /* renamed from: j */
    private GroupInfo f5245j;

    /* renamed from: k */
    private MoveGroupDialog f5246k;

    /* renamed from: l */
    private DoMoveGroupDialog f5247l;

    /* renamed from: m */
    private DdyHomeFragmentModel f5248m;

    /* renamed from: f */
    private List<GroupInfo> f5241f = new ArrayList();

    /* renamed from: g */
    private List<OrderDaileInfo> f5242g = new ArrayList();

    /* renamed from: h */
    private List<OrderDaileInfo> f5243h = new ArrayList();

    /* renamed from: n */
    private Observer<List<Long>> f5249n = new Observer<List<Long>>() { // from class: com.angel.nrzs.ddy.activity.MoveDdyActivity.1
        /* renamed from: a */
        public void onChanged(@Nullable List<Long> list) {
            LogUtils.m23734c("HomeFragmentChange", "onChange");
            if (list != null) {
                MoveDdyActivity.this.m25135c();
            } else {
                aqg.m11586a(MoveDdyActivity.this, "移动失败");
            }
        }
    };

    /* renamed from: o */
    private Observer<List<OrderDaileInfo>> f5250o = new Observer<List<OrderDaileInfo>>() { // from class: com.angel.nrzs.ddy.activity.MoveDdyActivity.2
        /* renamed from: a */
        public void onChanged(@Nullable List<OrderDaileInfo> list) {
            MoveDdyActivity.this.f5242g = list;
            GroupManager.m12887a().m12882b(list);
            MoveDdyActivity moveDdyActivity = MoveDdyActivity.this;
            moveDdyActivity.m25144a(moveDdyActivity.f5245j.GroupId, MoveDdyActivity.this.f5245j.GroupName);
            aqg.m11586a(MoveDdyActivity.this, "移动成功");
            EventBus.m3448a().m3427d(new GroupFlushEvent.C3557a());
        }
    };

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    protected int mo18307a() {
        return C0692R.layout.nrzs_move_ddye;
    }

    /* renamed from: a */
    public static void m25143a(Context context) {
        context.startActivity(new Intent(context, MoveDdyActivity.class));
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18303b() {
        ImmersionBar.m20080a(this).m20087a(C0692R.color.f1303bb).m19974h(true).m19994f();
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    public void mo18305a(Bundle bundle) {
        this.f5236a = (ImageView) findViewById(C0692R.C0694id.f2462ap);
        this.f5237b = (ImageView) findViewById(C0692R.C0694id.move_btn);
        this.f5238c = (TextView) findViewById(C0692R.C0694id.grup_tv);
        this.f5239d = (RecyclerView) findViewById(C0692R.C0694id.recycler_group_list);
        this.f5240e = (CheckBox) findViewById(C0692R.C0694id.all_cheack);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18302b(Bundle bundle) {
        this.f5241f = GroupManager.m12887a().m12881c();
        this.f5242g = GroupManager.m12887a().m12880d();
        this.f5245j = this.f5241f.get(0);
        m25144a(this.f5245j.GroupId, this.f5245j.GroupName);
    }

    /* renamed from: a */
    public void m25144a(long j, String str) {
        this.f5243h.clear();
        for (int i = 0; i < this.f5242g.size(); i++) {
            OrderDaileInfo orderDaileInfo = this.f5242g.get(i);
            if (orderDaileInfo.GroupId == j) {
                this.f5243h.add(orderDaileInfo);
            }
        }
        this.f5238c.setText(str);
        this.f5239d.setLayoutManager(new LinearLayoutManager(this));
        this.f5244i = new MoveGroupAdapter();
        this.f5244i.m25178a(this.f5243h);
        this.f5239d.setAdapter(this.f5244i);
    }

    /* renamed from: a */
    public void m25137a(Long l) {
        String str = "";
        List<OrderDaileInfo> a = this.f5244i.m25182a();
        if (a == null || a.size() <= 0) {
            aqg.m11586a(this, "请选择设备后在操作");
            return;
        }
        for (int i = 0; i < a.size(); i++) {
            str = str.equals("") ? a.get(i).f10629Id + "" : str + "," + a.get(i).f10629Id;
        }
        if (this.f5248m == null) {
            this.f5248m = (DdyHomeFragmentModel) ViewModelProviders.m25704of(this).get(DdyHomeFragmentModel.class);
        }
        this.f5248m.m25088e().observe(this, this.f5249n);
        this.f5248m.m25092a(str, l);
    }

    /* renamed from: c */
    public void m25135c() {
        if (this.f5248m == null) {
            this.f5248m = (DdyHomeFragmentModel) ViewModelProviders.m25704of(this).get(DdyHomeFragmentModel.class);
        }
        this.f5248m.m25090c().observe(this, this.f5250o);
        this.f5248m.m25093a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.angel.nrzs.app.base.AppBaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f5244i.m25177a(false);
        ImmersionBar.m20080a(this).m19985g();
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: d */
    public void mo18300d() {
        this.f5236a.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ddy.activity.MoveDdyActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MoveDdyActivity.this.finish();
            }
        });
        this.f5237b.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ddy.activity.MoveDdyActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MoveDdyActivity.this.f5247l == null) {
                    MoveDdyActivity moveDdyActivity = MoveDdyActivity.this;
                    moveDdyActivity.f5247l = new DoMoveGroupDialog(moveDdyActivity, moveDdyActivity.f5241f, new ChooseCallback() { // from class: com.angel.nrzs.ddy.activity.MoveDdyActivity.4.1
                        @Override // p110z1.ChooseCallback
                        /* renamed from: a */
                        public void mo3129a(GroupInfo groupInfo) {
                            if (MoveDdyActivity.this.f5247l != null) {
                                MoveDdyActivity.this.m25137a(Long.valueOf(groupInfo.GroupId));
                                MoveDdyActivity.this.f5247l.dismiss();
                            }
                        }
                    });
                }
                MoveDdyActivity.this.f5247l.show();
            }
        });
        this.f5238c.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ddy.activity.MoveDdyActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MoveDdyActivity.this.f5246k == null) {
                    MoveDdyActivity moveDdyActivity = MoveDdyActivity.this;
                    moveDdyActivity.f5246k = new MoveGroupDialog(moveDdyActivity, moveDdyActivity.f5241f, new ChooseCallback() { // from class: com.angel.nrzs.ddy.activity.MoveDdyActivity.5.1
                        @Override // p110z1.ChooseCallback
                        /* renamed from: a */
                        public void mo3129a(GroupInfo groupInfo) {
                            MoveDdyActivity.this.f5245j = groupInfo;
                            MoveDdyActivity.this.m25144a(groupInfo.GroupId, groupInfo.GroupName);
                            if (MoveDdyActivity.this.f5246k != null) {
                                MoveDdyActivity.this.f5246k.dismiss();
                            }
                        }
                    });
                }
                MoveDdyActivity.this.f5246k.show();
            }
        });
        this.f5240e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.angel.nrzs.ddy.activity.MoveDdyActivity.6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MoveDdyActivity.this.f5244i.m25177a(z);
            }
        });
    }
}
