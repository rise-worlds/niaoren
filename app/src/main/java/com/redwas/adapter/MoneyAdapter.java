package com.redwas.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.nrzs.data.redbag.bean.MoneyInfo;
import com.nrzs.moudleui.adapter.BaseListAdapter;
import com.nrzs.moudleui.adapter.BaseViewHolder;
import com.redwas.redwars.C2342R;
import java.util.List;

/* loaded from: classes2.dex */
public class MoneyAdapter extends BaseListAdapter<MoneyInfo, C2338a> {
    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: a */
    public void mo18184a(View view, int i) {
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: b */
    public void mo18180b(View view, int i) {
    }

    public MoneyAdapter(List<MoneyInfo> list) {
        super(list);
    }

    /* renamed from: a */
    public C2338a mo18181b(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, int i2) {
        return new C2338a(layoutInflater, viewGroup, i);
    }

    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void mo18183a(@NonNull C2338a aVar, int i) {
        MoneyInfo moneyInfo = (MoneyInfo) this.f11255c.get(i);
        aVar.f11652a.setText(moneyInfo.f10652c);
        aVar.f11653b.setText(moneyInfo.f10653d);
        aVar.f11654c.setText(moneyInfo.f10654e);
        aVar.f11655d.setText(moneyInfo.f10655f);
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: a */
    protected int mo18186a(int i) {
        return C2342R.layout.intm_money_histrnoy_list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.redwas.adapter.MoneyAdapter$a */
    /* loaded from: classes2.dex */
    public static class C2338a extends BaseViewHolder {

        /* renamed from: a */
        TextView f11652a = (TextView) this.itemView.findViewById(C2342R.C2344id.record_day);

        /* renamed from: b */
        TextView f11653b = (TextView) this.itemView.findViewById(C2342R.C2344id.record_time);

        /* renamed from: c */
        TextView f11654c = (TextView) this.itemView.findViewById(C2342R.C2344id.record_user_name);

        /* renamed from: d */
        TextView f11655d = (TextView) this.itemView.findViewById(C2342R.C2344id.record_money);

        C2338a(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
            super(layoutInflater, viewGroup, i);
        }
    }
}
