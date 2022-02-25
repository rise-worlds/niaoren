package com.angel.nrzs.adapter;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.angel.nrzs.C0692R;
import com.angel.nrzs.p017ui.activity.NRZSWebviewActivity;
import com.blankj.utilcode.util.LogUtils;
import com.nrzs.data.game.bean.TopicInfo;
import com.nrzs.data.other.bean.AdResultInfoItem;
import com.nrzs.data.other.bean.PurchassedAssistinfo;
import com.nrzs.moudleui.adapter.BaseListAdapter;
import com.nrzs.moudleui.adapter.BaseViewHolder;
import java.util.List;
import p110z1.CKU;
import p110z1.DoneCallback;
import p110z1.EventCollectManager;
import p110z1.EventConstants;
import p110z1.FailCallback;
import p110z1.GlideImageUtils;
import p110z1.HttpVal;
import p110z1.TopicInfoManager;

/* loaded from: classes.dex */
public class PuchsasedAssistAdapter extends BaseListAdapter<PurchassedAssistinfo, C0718a> {
    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: a */
    protected int mo18186a(int i) {
        return C0692R.layout.nrzs_item_puirchased_assist;
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: a */
    public void mo18184a(View view, int i) {
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: b */
    public void mo18180b(View view, int i) {
    }

    public PuchsasedAssistAdapter(List<PurchassedAssistinfo> list) {
        super(list);
    }

    /* renamed from: a */
    public C0718a mo18181b(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, int i2) {
        return new C0718a(layoutInflater, viewGroup, i);
    }

    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void mo18183a(@NonNull C0718a aVar, int i) {
        final PurchassedAssistinfo purchassedAssistinfo = (PurchassedAssistinfo) this.f11255c.get(i);
        aVar.f5195b.setText(purchassedAssistinfo.ProductName);
        TextView textView = aVar.f5196c;
        textView.setText("到期时间:" + purchassedAssistinfo.EndTime);
        if (purchassedAssistinfo.ProductType == 3) {
            GlideImageUtils.m11880a(aVar.f5194a, m18480d(), (int) C0692R.C0693drawable.f2301no, "");
            aVar.f5199f.setVisibility(0);
            aVar.f5198e.setVisibility(8);
            aVar.f5199f.setText(purchassedAssistinfo.ProductDesc);
            if (purchassedAssistinfo.EndTime.equals("未购买")) {
                aVar.f5197d.setText("购买");
            } else {
                aVar.f5197d.setText("续费");
            }
        } else {
            GlideImageUtils.m11880a(aVar.f5194a, m18480d(), (int) C0692R.mipmap.f3513a, purchassedAssistinfo.ProductIcon);
            aVar.f5199f.setVisibility(8);
            aVar.f5198e.setVisibility(0);
        }
        aVar.f5197d.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.adapter.PuchsasedAssistAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (purchassedAssistinfo.ProductType == 3) {
                    EventCollectManager.m12642a().m12640a(view.getContext(), "已购辅助全平台", "已购辅助全平台", EventConstants.f16448p);
                    AdResultInfoItem adResultInfoItem = new AdResultInfoItem();
                    adResultInfoItem.Title = "购买续费";
                    adResultInfoItem.ExecArgs = HttpVal.f16516c + "?topicId=0";
                    NRZSWebviewActivity.m25004a(view.getContext(), 0, 1, adResultInfoItem);
                    return;
                }
                EventCollectManager.m12642a().m12640a(view.getContext(), "已购辅助专区", String.valueOf(purchassedAssistinfo.TopicId), EventConstants.f16449q);
                AdResultInfoItem adResultInfoItem2 = new AdResultInfoItem();
                adResultInfoItem2.Title = "购买续费";
                adResultInfoItem2.ExecArgs = HttpVal.f16516c + "?topicId=" + purchassedAssistinfo.TopicId;
                NRZSWebviewActivity.m25004a(view.getContext(), 0, 1, adResultInfoItem2);
            }
        });
        aVar.f5198e.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.adapter.PuchsasedAssistAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                TopicInfoManager.m12726c().m12735a(purchassedAssistinfo.TopicId, new FailCallback<Throwable>() { // from class: com.angel.nrzs.adapter.PuchsasedAssistAdapter.2.1
                    /* renamed from: a */
                    public void onFail(Throwable th) {
                        LogUtils.m23734c("TopicList", "result:" + th.getMessage());
                    }
                }, new DoneCallback<TopicInfo>() { // from class: com.angel.nrzs.adapter.PuchsasedAssistAdapter.2.2
                    /* renamed from: a */
                    public void onDone(TopicInfo topicInfo) {
                        new CKU().m13031a(view.getContext(), PuchsasedAssistAdapter.this.m18480d().getClass(), topicInfo, !TextUtils.isEmpty(topicInfo.localAppPackage));
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.angel.nrzs.adapter.PuchsasedAssistAdapter$a */
    /* loaded from: classes.dex */
    public static class C0718a extends BaseViewHolder {

        /* renamed from: a */
        ImageView f5194a = (ImageView) this.itemView.findViewById(C0692R.C0694id.nrzs_app_buy_item_img);

        /* renamed from: b */
        TextView f5195b = (TextView) this.itemView.findViewById(C0692R.C0694id.nrzs_app_sctript_buy_assist_item_name);

        /* renamed from: c */
        TextView f5196c = (TextView) this.itemView.findViewById(C0692R.C0694id.nrzs_app__buy_assist_ietm_time);

        /* renamed from: d */
        TextView f5197d = (TextView) this.itemView.findViewById(C0692R.C0694id.nrzs_app__buy_assist_ietm_xf);

        /* renamed from: e */
        TextView f5198e = (TextView) this.itemView.findViewById(C0692R.C0694id.nrzs_app__buy_assist_ietm_run);

        /* renamed from: f */
        TextView f5199f = (TextView) this.itemView.findViewById(C0692R.C0694id.nrzs_app__vipvontent);

        C0718a(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
            super(layoutInflater, viewGroup, i);
        }
    }
}
