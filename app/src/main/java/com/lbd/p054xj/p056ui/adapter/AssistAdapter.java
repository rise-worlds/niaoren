package com.lbd.p054xj.p056ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.Utils;
import com.nrzs.data.p066ft.bean.AssistInfo;
import com.nrzs.moudleui.adapter.BaseListAdapter;
import com.nrzs.moudleui.adapter.BaseViewHolder;
import com.nrzs.ft.C1990R;
import com.nrzs.ft.FloatApp;
import java.util.List;
import p110z1.CdKeyDialog;
import p110z1.EventCollectManager;
import p110z1.EventConstants;
import p110z1.FloatDataManager;
import p110z1.FwManager;
import p110z1.GlideImageUtils;
import p110z1.IntentToAssistService;
import p110z1.ShareVal;
import p110z1.apf;

/* renamed from: com.lbd.xj.ui.adapter.AssistAdapter */
/* loaded from: classes.dex */
public class AssistAdapter extends BaseListAdapter<AssistInfo, C1593a> {
    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: a */
    public void mo18184a(View view, int i) {
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: b */
    public void mo18180b(View view, int i) {
    }

    public AssistAdapter() {
    }

    public AssistAdapter(List<AssistInfo> list) {
        super(list);
    }

    /* renamed from: a */
    public C1593a mo18181b(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, int i2) {
        return new C1593a(layoutInflater, viewGroup, mo18186a(i2));
    }

    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void mo18183a(@NonNull C1593a aVar, final int i) {
        AssistInfo assistInfo = (AssistInfo) this.f11255c.get(i);
        if (assistInfo != null) {
            aVar.f9670b.setText(assistInfo.ScriptName);
            aVar.f9673e.setText(assistInfo.ScriptDesc);
            aVar.f9674f.setText(assistInfo.UpdateTime);
            TextView textView = aVar.f9677i;
            textView.setText(Html.fromHtml("<font color=\"#FF9100\">" + assistInfo.AuthorRewardSGBTotalNumStr + "</font>次打赏"));
            aVar.f9671c.setText(TextUtils.isEmpty(assistInfo.ScriptNickName) ? "辅助大神" : assistInfo.ScriptNickName);
            if (assistInfo.IsVip == 1) {
                TextView textView2 = aVar.f9678j;
                textView2.setText(assistInfo.Gold + "金币/天");
                aVar.f9678j.setVisibility(0);
                aVar.f9672d.setVisibility(8);
                aVar.f9672d.setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.adapter.AssistAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        EventCollectManager.m12642a().m12640a(AssistAdapter.this.m18480d(), "悬浮窗脚本列表专区购买", "悬浮窗脚本列表专区购买", EventConstants.f16455w);
                        if (apf.m11836b((Context) Utils.m24103a(), ShareVal.f16591a, ShareVal.f16614x, false)) {
                            new CdKeyDialog(AssistAdapter.this.m18480d()).show();
                        } else {
                            IntentToAssistService.m12810a(view.getContext(), FloatDataManager.m12352j().m12354h());
                        }
                    }
                });
            } else {
                aVar.f9678j.setVisibility(4);
                aVar.f9672d.setVisibility(8);
            }
            GlideImageUtils.m11880a(aVar.f9676h, m18480d(), C1990R.C1991drawable.bird_bg_common_img, assistInfo.ScriptIco);
            aVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.adapter.AssistAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    FloatDataManager.m12352j().m12367a((AssistInfo) AssistAdapter.this.f11255c.get(i));
                    FwManager.getInstance().initAssistInfoView((AssistInfo) AssistAdapter.this.f11255c.get(i));
                }
            });
        }
    }

    /* renamed from: a */
    public void onViewAttachedToWindow(@NonNull C1593a aVar) {
        super.onViewAttachedToWindow(aVar);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) aVar.f9669a.getLayoutParams();
        if (ScreenUtils.m23290h()) {
            layoutParams.width = -1;
            layoutParams.height = FloatApp.m18899b().f10711a;
        } else {
            layoutParams.width = FloatApp.m18899b().f10711a;
            layoutParams.height = -1;
        }
        aVar.f9669a.setLayoutParams(layoutParams);
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: a */
    protected int mo18186a(int i) {
        return C1990R.layout.nrzs_ft_item_assist;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.lbd.xj.ui.adapter.AssistAdapter$a */
    /* loaded from: classes.dex */
    public static class C1593a extends BaseViewHolder {

        /* renamed from: c */
        TextView f9671c = (TextView) this.itemView.findViewById(C1990R.C1992id.tv_author_name);

        /* renamed from: a */
        LinearLayout f9669a = (LinearLayout) this.itemView.findViewById(C1990R.C1992id.nrzs_ft_ll_root);

        /* renamed from: b */
        TextView f9670b = (TextView) this.itemView.findViewById(C1990R.C1992id.nrzs_ft_tv_title);

        /* renamed from: d */
        TextView f9672d = (TextView) this.itemView.findViewById(C1990R.C1992id.nrzs_ft_tv_buy);

        /* renamed from: e */
        TextView f9673e = (TextView) this.itemView.findViewById(C1990R.C1992id.nrzs_ft_tv_content);

        /* renamed from: f */
        TextView f9674f = (TextView) this.itemView.findViewById(C1990R.C1992id.nrzs_ft_tv_time);

        /* renamed from: g */
        TextView f9675g = (TextView) this.itemView.findViewById(C1990R.C1992id.nrzs_ft_tv_run);

        /* renamed from: h */
        ImageView f9676h = (ImageView) this.itemView.findViewById(C1990R.C1992id.nrzs_ft_iv_icon);

        /* renamed from: i */
        TextView f9677i = (TextView) this.itemView.findViewById(C1990R.C1992id.nrzs_ft_tv_count);

        /* renamed from: j */
        TextView f9678j = (TextView) this.itemView.findViewById(C1990R.C1992id.tv_ft_gold);

        public C1593a(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
            super(layoutInflater, viewGroup, i);
        }
    }
}
