package com.nrzs.p067ft.adapter;

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
import android.widget.Toast;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.Utils;
import com.nrzs.data.p066ft.bean.AssistInfo;
import com.nrzs.moudleui.adapter.BaseListAdapter;
import com.nrzs.moudleui.adapter.BaseViewHolder;
import com.nrzs.p067ft.C1990R;
import com.nrzs.p067ft.FloatApp;
import com.nrzs.p067ft.p068ui.view.AssistListView;
import java.util.List;
import p110z1.CdKeyDialog;
import p110z1.EventCollectManager;
import p110z1.EventConstants;
import p110z1.FloatDataManager;
import p110z1.FloatViewManager;
import p110z1.GlideImageUtils;
import p110z1.IntentToAssistService;
import p110z1.ShareVal;
import p110z1.ane;
import p110z1.ang;
import p110z1.apf;

/* renamed from: com.nrzs.ft.adapter.AssistAdapter */
/* loaded from: classes2.dex */
public class AssistAdapter extends BaseListAdapter<AssistInfo, C1998a> {

    /* renamed from: a */
    private Context f10673a;

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: a */
    public void mo18184a(View view, int i) {
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: b */
    public void mo18180b(View view, int i) {
    }

    public AssistAdapter(Context context) {
        this.f10673a = context;
    }

    public AssistAdapter(List<AssistInfo> list) {
        super(list);
    }

    /* renamed from: a */
    public C1998a mo18181b(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, int i2) {
        return new C1998a(layoutInflater, viewGroup, mo18186a(i2));
    }

    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void mo18183a(@NonNull C1998a aVar, final int i) {
        AssistInfo assistInfo = (AssistInfo) this.f11255c.get(i);
        if (assistInfo != null) {
            aVar.f10680b.setText(assistInfo.ScriptName);
            aVar.f10683e.setText(assistInfo.ScriptDesc);
            aVar.f10684f.setText(assistInfo.UpdateTime);
            TextView textView = aVar.f10687i;
            textView.setText(Html.fromHtml("<font color=\"#FF9100\">" + assistInfo.AuthorRewardSGBTotalNumStr + "</font>次打赏"));
            aVar.f10681c.setText(TextUtils.isEmpty(assistInfo.ScriptNickName) ? "辅助大神" : assistInfo.ScriptNickName);
            if (assistInfo.IsVip == 1) {
                TextView textView2 = aVar.f10688j;
                textView2.setText(assistInfo.Gold + "金币/天");
                aVar.f10688j.setVisibility(0);
                aVar.f10682d.setVisibility(8);
                aVar.f10682d.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.ft.adapter.AssistAdapter.1
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
                aVar.f10688j.setVisibility(4);
                aVar.f10682d.setVisibility(8);
            }
            GlideImageUtils.m11880a(aVar.f10686h, m18480d(), C1990R.C1991drawable.bird_bg_common_img, assistInfo.ScriptIco);
            aVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.ft.adapter.AssistAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(final View view) {
                    if (!ane.m12491a().m12489b() || ((AssistInfo) AssistAdapter.this.f11255c.get(i)).UseOcrText != 1) {
                        FloatDataManager.m12352j().m12367a((AssistInfo) AssistAdapter.this.f11255c.get(i));
                        FloatViewManager.m12346a(view.getContext().getApplicationContext()).m12343a(view.getContext(), (AssistInfo) AssistAdapter.this.f11255c.get(i));
                        FloatViewManager.m12346a(view.getContext().getApplicationContext()).m12339a(AssistListView.class.getName());
                        return;
                    }
                    new ang(AssistAdapter.this.f10673a, new ang.AbstractC3695a() { // from class: com.nrzs.ft.adapter.AssistAdapter.2.1
                        @Override // p110z1.ang.AbstractC3695a
                        /* renamed from: a */
                        public void mo12434a() {
                            FloatDataManager.m12352j().m12367a((AssistInfo) AssistAdapter.this.f11255c.get(i));
                            FloatViewManager.m12346a(view.getContext().getApplicationContext()).m12343a(view.getContext(), (AssistInfo) AssistAdapter.this.f11255c.get(i));
                            FloatViewManager.m12346a(view.getContext().getApplicationContext()).m12339a(AssistListView.class.getName());
                        }

                        @Override // p110z1.ang.AbstractC3695a
                        /* renamed from: b */
                        public void mo12433b() {
                            Toast.makeText(view.getContext(), "下载文字识别资源失败，请重试", 1).show();
                        }
                    }).show();
                }
            });
        }
    }

    /* renamed from: a */
    public void onViewAttachedToWindow(@NonNull C1998a aVar) {
        super.onViewAttachedToWindow(aVar);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) aVar.f10679a.getLayoutParams();
        if (ScreenUtils.m23290h()) {
            layoutParams.width = -1;
            layoutParams.height = FloatApp.m18899b().f10711a;
        } else {
            layoutParams.width = FloatApp.m18899b().f10711a;
            layoutParams.height = -1;
        }
        aVar.f10679a.setLayoutParams(layoutParams);
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: a */
    protected int mo18186a(int i) {
        return C1990R.layout.nrzs_ft_item_assist;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.nrzs.ft.adapter.AssistAdapter$a */
    /* loaded from: classes2.dex */
    public static class C1998a extends BaseViewHolder {

        /* renamed from: c */
        TextView f10681c = (TextView) this.itemView.findViewById(C1990R.C1992id.tv_author_name);

        /* renamed from: a */
        LinearLayout f10679a = (LinearLayout) this.itemView.findViewById(C1990R.C1992id.nrzs_ft_ll_root);

        /* renamed from: b */
        TextView f10680b = (TextView) this.itemView.findViewById(C1990R.C1992id.nrzs_ft_tv_title);

        /* renamed from: d */
        TextView f10682d = (TextView) this.itemView.findViewById(C1990R.C1992id.nrzs_ft_tv_buy);

        /* renamed from: e */
        TextView f10683e = (TextView) this.itemView.findViewById(C1990R.C1992id.nrzs_ft_tv_content);

        /* renamed from: f */
        TextView f10684f = (TextView) this.itemView.findViewById(C1990R.C1992id.nrzs_ft_tv_time);

        /* renamed from: g */
        TextView f10685g = (TextView) this.itemView.findViewById(C1990R.C1992id.nrzs_ft_tv_run);

        /* renamed from: h */
        ImageView f10686h = (ImageView) this.itemView.findViewById(C1990R.C1992id.nrzs_ft_iv_icon);

        /* renamed from: i */
        TextView f10687i = (TextView) this.itemView.findViewById(C1990R.C1992id.nrzs_ft_tv_count);

        /* renamed from: j */
        TextView f10688j = (TextView) this.itemView.findViewById(C1990R.C1992id.tv_ft_gold);

        public C1998a(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
            super(layoutInflater, viewGroup, i);
        }
    }
}
