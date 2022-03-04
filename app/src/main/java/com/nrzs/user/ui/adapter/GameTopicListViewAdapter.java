package com.nrzs.user.ui.adapter;

import android.support.annotation.NonNull;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nrzs.data.game.bean.RdataBean;
import com.nrzs.moudleui.adapter.BaseListAdapter;
import com.nrzs.moudleui.adapter.BaseViewHolder;
import com.nrzs.user.C2222R;
import java.util.List;
import p110z1.GlideImageUtils;
import p110z1.IChooseCallback;

/* renamed from: com.nrzs.user.ui.adapter.GameTopicListViewAdapter */
/* loaded from: classes2.dex */
public class GameTopicListViewAdapter extends BaseListAdapter<RdataBean, BaseViewHolder> {

    /* renamed from: a */
    private long f11502a = -1;

    /* renamed from: b */
    private IChooseCallback f11503b;

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: a */
    public void mo18184a(View view, int i) {
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: b */
    public void mo18180b(View view, int i) {
    }

    public GameTopicListViewAdapter(List<RdataBean> list, IChooseCallback alpVar) {
        super(list);
        this.f11503b = alpVar;
    }

    /* renamed from: a */
    public void m18293a(long j) {
        this.f11502a = j;
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: b */
    public BaseViewHolder mo18181b(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, int i2) {
        return new BaseViewHolder(layoutInflater, viewGroup, i);
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: a */
    public void mo18183a(@NonNull BaseViewHolder baseViewHolder, int i) {
        final RdataBean rdataBean = (RdataBean) this.f11255c.get(i);
        GlideImageUtils.m11880a((ImageView) baseViewHolder.itemView.findViewById(C2222R.C2224id.iv_scrpit_icon), m18480d(), C2222R.C2223drawable.bird_bg_common_img, rdataBean.getScriptIco());
        ((TextView) baseViewHolder.itemView.findViewById(C2222R.C2224id.text_script_release_date)).setText(rdataBean.getReleaseDateStr());
        ((TextView) baseViewHolder.itemView.findViewById(C2222R.C2224id.text_script_title)).setText(rdataBean.getScriptName());
        ((TextView) baseViewHolder.itemView.findViewById(C2222R.C2224id.text_script_score)).setText(Html.fromHtml("<font color=\"#FF9100\">" + rdataBean.getAuthorRewardSGBTotalNumStr() + "</font>次打赏"));
        ((TextView) baseViewHolder.itemView.findViewById(C2222R.C2224id.tv_author_name)).setText(TextUtils.isEmpty(rdataBean.getNickName()) ? "辅助大神" : rdataBean.getNickName());
        TextView textView = (TextView) baseViewHolder.itemView.findViewById(C2222R.C2224id.text_script_gold);
        if (rdataBean.isToolVip()) {
            textView.setText(rdataBean.getGold() + "金币/天");
            textView.setVisibility(0);
        } else {
            textView.setVisibility(4);
        }
        ((RelativeLayout) baseViewHolder.itemView.findViewById(C2222R.C2224id.rl_topic_item)).setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.adapter.GameTopicListViewAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameTopicListViewAdapter.this.f11503b.mo12561a(rdataBean);
            }
        });
        ((TextView) baseViewHolder.itemView.findViewById(C2222R.C2224id.btn_run)).setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.adapter.GameTopicListViewAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameTopicListViewAdapter.this.f11503b.mo12561a(rdataBean);
            }
        });
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: a */
    protected int mo18186a(int i) {
        return C2222R.layout.item_script_list_view;
    }
}
