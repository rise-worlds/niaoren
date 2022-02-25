package com.angel.nrzs.p017ui.activity;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import com.angel.nrzs.C0692R;
import com.angel.nrzs.adapter.PuchsasedAssistAdapter;
import com.angel.nrzs.model.PurchassedAssistModel;
import com.angel.nrzs.p017ui.base.AppBaseActivity;
import com.gyf.barlibrary.ImmersionBar;
import com.nrzs.data.other.bean.AdResultInfoItem;
import com.nrzs.data.other.bean.PurchassedAssistinfo;
import java.util.ArrayList;
import java.util.List;
import p110z1.BannerManager;
import p110z1.BannersOnClick;
import p110z1.DoneCallback;
import p110z1.EventCollectManager;
import p110z1.EventConstants;
import p110z1.GlideImageUtils;
import p110z1.HttpVal;

/* renamed from: com.angel.nrzs.ui.activity.PurchasedAssistActivyt */
/* loaded from: classes.dex */
public class PurchasedAssistActivyt extends AppBaseActivity {

    /* renamed from: a */
    private AdResultInfoItem f5447a;

    /* renamed from: b */
    private ImageView f5448b;

    /* renamed from: c */
    private RecyclerView f5449c;

    /* renamed from: d */
    private LinearLayout f5450d;

    /* renamed from: e */
    private TextView f5451e;

    /* renamed from: f */
    private ImageView f5452f;

    /* renamed from: g */
    private PuchsasedAssistAdapter f5453g;

    /* renamed from: h */
    private PurchassedAssistModel f5454h;

    /* renamed from: i */
    private List<PurchassedAssistinfo> f5455i;

    /* renamed from: j */
    private Observer<List<PurchassedAssistinfo>> f5456j = new Observer<List<PurchassedAssistinfo>>() { // from class: com.angel.nrzs.ui.activity.PurchasedAssistActivyt.1
        /* renamed from: a */
        public void onChanged(@Nullable List<PurchassedAssistinfo> list) {
            if (list == null || list.size() <= 0) {
                PurchasedAssistActivyt.this.f5450d.setVisibility(0);
                PurchasedAssistActivyt.this.f5449c.setVisibility(8);
                return;
            }
            PurchasedAssistActivyt.this.f5450d.setVisibility(8);
            PurchasedAssistActivyt.this.f5449c.setVisibility(0);
            PurchasedAssistActivyt.this.f5455i.clear();
            PurchasedAssistActivyt.this.f5455i.addAll(list);
            PurchasedAssistActivyt.this.f5453g.notifyDataSetChanged();
        }
    };

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    protected int mo18307a() {
        return C0692R.layout.purchased_assist_activity;
    }

    /* renamed from: a */
    public static void m24992a(Context context) {
        context.startActivity(new Intent(context, PurchasedAssistActivyt.class));
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18303b() {
        ImmersionBar.m20080a(this).m20087a(C0692R.color.f1303bb).m19974h(true).m19994f();
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    public void mo18305a(Bundle bundle) {
        this.f5448b = (ImageView) findViewById(C0692R.C0694id.nrzs_app__buy_assist_imageback);
        this.f5449c = (RecyclerView) findViewById(C0692R.C0694id.nrzs_app_sctript_buy_assist_recycle);
        this.f5450d = (LinearLayout) findViewById(C0692R.C0694id.nrzs_app_nodata);
        this.f5452f = (ImageView) findViewById(C0692R.C0694id.nrzs_app_adimg);
        this.f5451e = (TextView) findViewById(C0692R.C0694id.nrzs_app__buy_btn);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18302b(Bundle bundle) {
        BannerManager.m12679a().m12661e(new DoneCallback<AdResultInfoItem>() { // from class: com.angel.nrzs.ui.activity.PurchasedAssistActivyt.2
            /* renamed from: a */
            public void onDone(AdResultInfoItem adResultInfoItem) {
                PurchasedAssistActivyt.this.f5447a = adResultInfoItem;
                if (PurchasedAssistActivyt.this.f5447a != null) {
                    PurchasedAssistActivyt.this.f5452f.setVisibility(0);
                    GlideImageUtils.m11880a(PurchasedAssistActivyt.this.f5452f, PurchasedAssistActivyt.this.getApplicationContext(), (int) C0692R.mipmap.f3513a, PurchasedAssistActivyt.this.f5447a.ImgUrl);
                    return;
                }
                PurchasedAssistActivyt.this.f5452f.setVisibility(8);
            }
        });
        this.f5455i = new ArrayList();
        if (this.f5454h == null) {
            this.f5454h = (PurchassedAssistModel) ViewModelProviders.m25704of(this).get(PurchassedAssistModel.class);
        }
        this.f5454h.m25043b().observe(this, this.f5456j);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(1);
        this.f5449c.setLayoutManager(linearLayoutManager);
        this.f5453g = new PuchsasedAssistAdapter(this.f5455i);
        this.f5449c.setAdapter(this.f5453g);
        this.f5454h.m25044a();
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: d */
    public void mo18300d() {
        this.f5448b.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ui.activity.PurchasedAssistActivyt.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PurchasedAssistActivyt.this.finish();
            }
        });
        this.f5452f.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ui.activity.PurchasedAssistActivyt.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PurchasedAssistActivyt.this.f5447a != null) {
                    BannersOnClick dvVar = new BannersOnClick();
                    PurchasedAssistActivyt purchasedAssistActivyt = PurchasedAssistActivyt.this;
                    dvVar.m3199a(purchasedAssistActivyt, purchasedAssistActivyt.f5447a, 99);
                }
            }
        });
        this.f5451e.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ui.activity.PurchasedAssistActivyt.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EventCollectManager.m12642a().m12640a(PurchasedAssistActivyt.this, "已购辅助全平台", "已购辅助全平台", EventConstants.f16448p);
                AdResultInfoItem adResultInfoItem = new AdResultInfoItem();
                adResultInfoItem.Title = "购买续费";
                adResultInfoItem.ExecArgs = HttpVal.f16516c + "?topicId=0";
                NRZSWebviewActivity.m25004a(PurchasedAssistActivyt.this, 0, 1, adResultInfoItem);
            }
        });
    }
}
