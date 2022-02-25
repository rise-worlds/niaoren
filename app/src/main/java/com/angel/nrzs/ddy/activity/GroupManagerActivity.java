package com.angel.nrzs.ddy.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.p006v7.widget.LinearLayoutManager;
import android.support.p006v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.angel.nrzs.C0692R;
import com.angel.nrzs.adapter.GrouptManagerAdapter;
import com.angel.nrzs.ddy.model.DdyHomeFragmentModel;
import com.angel.nrzs.app.base.AppBaseActivity;
import com.blankj.utilcode.util.LogUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.nrzs.data.ddy.bean.respond.GroupInfo;
import java.util.List;
import p110z1.AbstractC5307eh;
import p110z1.CreateGroupDialog;
import p110z1.GroupManager;

/* loaded from: classes.dex */
public class GroupManagerActivity extends AppBaseActivity {

    /* renamed from: a */
    private ImageView f5223a;

    /* renamed from: b */
    private ImageView f5224b;

    /* renamed from: c */
    private TextView f5225c;

    /* renamed from: d */
    private RecyclerView f5226d;

    /* renamed from: e */
    private DdyHomeFragmentModel f5227e;

    /* renamed from: f */
    private GrouptManagerAdapter f5228f;

    /* renamed from: g */
    private Observer<List<GroupInfo>> f5229g = new Observer<List<GroupInfo>>() { // from class: com.angel.nrzs.ddy.activity.GroupManagerActivity.1
        /* renamed from: a */
        public void onChanged(@Nullable List<GroupInfo> list) {
            LogUtils.m23734c("HomeFragmentChange", "onChange");
            if (list != null) {
                GroupManager.m12887a().m12885a(list);
                GroupManagerActivity.this.m25147c();
            }
        }
    };

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    protected int mo18307a() {
        return C0692R.layout.nrzs_activity_groupmanage;
    }

    /* renamed from: a */
    public static void m25149a(Context context) {
        context.startActivity(new Intent(context, GroupManagerActivity.class));
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18303b() {
        ImmersionBar.m20080a(this).m20087a(C0692R.color.f1303bb).m19974h(true).m19994f();
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    public void mo18305a(Bundle bundle) {
        this.f5223a = (ImageView) findViewById(C0692R.C0694id.f2462ap);
        this.f5225c = (TextView) findViewById(C0692R.C0694id.move_btn);
        this.f5224b = (ImageView) findViewById(C0692R.C0694id.create_group_btn);
        this.f5226d = (RecyclerView) findViewById(C0692R.C0694id.recycler_group_list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m25146e() {
        if (this.f5227e == null) {
            this.f5227e = (DdyHomeFragmentModel) ViewModelProviders.m25704of(this).get(DdyHomeFragmentModel.class);
        }
        this.f5227e.m25089d().observe(this, this.f5229g);
        this.f5227e.m25091b();
    }

    /* renamed from: c */
    public void m25147c() {
        this.f5226d.setLayoutManager(new LinearLayoutManager(this));
        this.f5228f = new GrouptManagerAdapter(new AbstractC5307eh() { // from class: com.angel.nrzs.ddy.activity.GroupManagerActivity.2
            @Override // p110z1.AbstractC5307eh
            /* renamed from: b */
            public void mo3130b() {
            }

            @Override // p110z1.AbstractC5307eh
            /* renamed from: a */
            public void mo3131a() {
                GroupManagerActivity.this.m25146e();
            }
        });
        this.f5228f.m25187a(GroupManager.m12887a().m12881c());
        this.f5226d.setAdapter(this.f5228f);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18302b(Bundle bundle) {
        m25147c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.angel.nrzs.app.base.AppBaseActivity, android.support.p006v7.app.AppCompatActivity, android.support.p003v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ImmersionBar.m20080a(this).m19985g();
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: d */
    public void mo18300d() {
        this.f5223a.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ddy.activity.GroupManagerActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GroupManagerActivity.this.finish();
            }
        });
        this.f5225c.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ddy.activity.GroupManagerActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MoveDdyActivity.m25143a(GroupManagerActivity.this);
            }
        });
        this.f5224b.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ddy.activity.GroupManagerActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new CreateGroupDialog(GroupManagerActivity.this, new AbstractC5307eh() { // from class: com.angel.nrzs.ddy.activity.GroupManagerActivity.5.1
                    @Override // p110z1.AbstractC5307eh
                    /* renamed from: b */
                    public void mo3130b() {
                    }

                    @Override // p110z1.AbstractC5307eh
                    /* renamed from: a */
                    public void mo3131a() {
                        GroupManagerActivity.this.m25146e();
                    }
                }).show();
            }
        });
    }
}
