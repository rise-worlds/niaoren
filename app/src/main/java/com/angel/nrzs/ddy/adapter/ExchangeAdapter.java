package com.angel.nrzs.ddy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.angel.nrzs.C0692R;
import com.nrzs.data.ddy.bean.respond.OrderDaileInfo;
import java.util.List;

/* loaded from: classes.dex */
public class ExchangeAdapter extends RecyclerView.Adapter<GroupViewHolder> {

    /* renamed from: a */
    private List<OrderDaileInfo> f5259a;

    /* renamed from: b */
    private AbstractC0740a f5260b;

    /* renamed from: com.angel.nrzs.ddy.adapter.ExchangeAdapter$a */
    /* loaded from: classes.dex */
    public interface AbstractC0740a {
        /* renamed from: a */
        void mo3143a(OrderDaileInfo orderDaileInfo);
    }

    public ExchangeAdapter(AbstractC0740a aVar) {
        this.f5260b = aVar;
    }

    /* renamed from: a */
    public void m25126a(List<OrderDaileInfo> list) {
        this.f5259a = list;
    }

    /* renamed from: a */
    public GroupViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new GroupViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C0692R.layout.exchange_item_view, viewGroup, false));
    }

    /* renamed from: a */
    public void onBindViewHolder(GroupViewHolder groupViewHolder, final int i) {
        final OrderDaileInfo orderDaileInfo = this.f5259a.get(i);
        if (orderDaileInfo.isTop) {
            groupViewHolder.f5271e.setVisibility(0);
            if (orderDaileInfo.isCheack) {
                groupViewHolder.f5271e.setBackground(groupViewHolder.f5267a.getResources().getDrawable(C0692R.C0693drawable.f2019hz));
            } else {
                groupViewHolder.f5271e.setBackground(groupViewHolder.f5267a.getResources().getDrawable(C0692R.C0693drawable.f2018hy));
            }
        } else {
            if (orderDaileInfo.isCheack) {
                groupViewHolder.f5272f.setBackground(groupViewHolder.f5267a.getResources().getDrawable(C0692R.C0693drawable.f2019hz));
            } else {
                groupViewHolder.f5272f.setBackground(groupViewHolder.f5267a.getResources().getDrawable(C0692R.C0693drawable.f2018hy));
            }
            groupViewHolder.f5271e.setVisibility(8);
            groupViewHolder.f5268b.setText(orderDaileInfo.DeviceName);
            TextView textView = groupViewHolder.f5269c;
            textView.setText("编号 : " + orderDaileInfo.DDYOrderId);
            TextView textView2 = groupViewHolder.f5270d;
            textView2.setText("剩余 ：" + orderDaileInfo.RemainTime);
        }
        groupViewHolder.f5271e.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ddy.adapter.ExchangeAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ExchangeAdapter.this.f5260b.mo3143a(orderDaileInfo);
                for (int i2 = 0; i2 < ExchangeAdapter.this.f5259a.size(); i2++) {
                    if (i2 == i) {
                        ((OrderDaileInfo) ExchangeAdapter.this.f5259a.get(i2)).isCheack = true;
                    } else {
                        ((OrderDaileInfo) ExchangeAdapter.this.f5259a.get(i2)).isCheack = false;
                    }
                }
                ExchangeAdapter.this.notifyDataSetChanged();
            }
        });
        groupViewHolder.f5272f.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ddy.adapter.ExchangeAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ExchangeAdapter.this.f5260b.mo3143a(orderDaileInfo);
                for (int i2 = 0; i2 < ExchangeAdapter.this.f5259a.size(); i2++) {
                    if (i2 == i) {
                        ((OrderDaileInfo) ExchangeAdapter.this.f5259a.get(i2)).isCheack = true;
                    } else {
                        ((OrderDaileInfo) ExchangeAdapter.this.f5259a.get(i2)).isCheack = false;
                    }
                }
                ExchangeAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Override // android.support.p006v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f5259a.size();
    }

    /* loaded from: classes.dex */
    public class GroupViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a */
        Context f5267a;

        /* renamed from: b */
        TextView f5268b;

        /* renamed from: c */
        TextView f5269c;

        /* renamed from: d */
        TextView f5270d;

        /* renamed from: e */
        LinearLayout f5271e;

        /* renamed from: f */
        LinearLayout f5272f;

        public GroupViewHolder(View view) {
            super(view);
            this.f5267a = view.getContext();
            this.f5268b = (TextView) view.findViewById(C0692R.C0694id.order_name);
            this.f5269c = (TextView) view.findViewById(C0692R.C0694id.f2918r2);
            this.f5270d = (TextView) view.findViewById(C0692R.C0694id.order_time);
            this.f5271e = (LinearLayout) view.findViewById(C0692R.C0694id.f3119xa);
            this.f5272f = (LinearLayout) view.findViewById(C0692R.C0694id.nomal_lay);
        }
    }
}
