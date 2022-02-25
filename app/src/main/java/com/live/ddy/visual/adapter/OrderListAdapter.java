package com.live.ddy.visual.adapter;

import android.support.p006v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.live.ddy.C1701R;
import com.nrzs.data.ddy.bean.respond.OrderDaileInfo;
import java.util.List;
import p110z1.IDdyClickInterface;

/* loaded from: classes.dex */
public class OrderListAdapter extends RecyclerView.Adapter<GroupViewHolder> {

    /* renamed from: a */
    private List<OrderDaileInfo> f10463a;

    /* renamed from: b */
    private IDdyClickInterface f10464b;

    public OrderListAdapter(IDdyClickInterface aieVar) {
        this.f10464b = aieVar;
    }

    /* renamed from: a */
    public void m19013a(List<OrderDaileInfo> list) {
        this.f10463a = list;
    }

    /* renamed from: a */
    public GroupViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new GroupViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C1701R.layout.ddy_dialog_orserlist_item, viewGroup, false));
    }

    /* renamed from: a */
    public void onBindViewHolder(GroupViewHolder groupViewHolder, int i) {
        final OrderDaileInfo orderDaileInfo = this.f10463a.get(i);
        groupViewHolder.f10467a.setText(orderDaileInfo.DeviceName);
        TextView textView = groupViewHolder.f10468b;
        textView.setText("编号 ：" + orderDaileInfo.DDYOrderId);
        if (orderDaileInfo.TypeCode == 11 || orderDaileInfo.TypeCode == 4) {
            groupViewHolder.f10469c.setImageDrawable(groupViewHolder.itemView.getContext().getResources().getDrawable(C1701R.C1702drawable.bird_ic_cloud_phone_vip_high));
        } else if (orderDaileInfo.TypeCode == 12) {
            groupViewHolder.f10469c.setImageDrawable(groupViewHolder.itemView.getContext().getResources().getDrawable(C1701R.C1702drawable.bird_ic_cloud_phone_vip_top));
        } else if (orderDaileInfo.TypeCode == 7) {
            groupViewHolder.f10469c.setImageDrawable(groupViewHolder.itemView.getContext().getResources().getDrawable(C1701R.C1702drawable.bird_ic_cloud_phone_vip_super));
        } else if (orderDaileInfo.TypeCode == 3 || orderDaileInfo.TypeCode == 9) {
            groupViewHolder.f10469c.setImageDrawable(groupViewHolder.itemView.getContext().getResources().getDrawable(C1701R.C1702drawable.bird_ic_cloud_phone_vip_normal));
        } else {
            groupViewHolder.f10469c.setImageDrawable(groupViewHolder.itemView.getContext().getResources().getDrawable(C1701R.C1702drawable.bird_ic_cloud_phone_vip_super));
        }
        groupViewHolder.f10470d.setOnClickListener(new View.OnClickListener() { // from class: com.live.ddy.visual.adapter.OrderListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OrderListAdapter.this.f10464b.mo13085a(Long.parseLong(orderDaileInfo.DDYOrderId), orderDaileInfo.f10629Id);
            }
        });
    }

    @Override // android.support.p006v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f10463a.size();
    }

    /* loaded from: classes.dex */
    public class GroupViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a */
        TextView f10467a;

        /* renamed from: b */
        TextView f10468b;

        /* renamed from: c */
        ImageView f10469c;

        /* renamed from: d */
        LinearLayout f10470d;

        public GroupViewHolder(View view) {
            super(view);
            this.f10467a = (TextView) view.findViewById(C1701R.C1703id.group_name);
            this.f10468b = (TextView) view.findViewById(C1701R.C1703id.order_num);
            this.f10470d = (LinearLayout) view.findViewById(C1701R.C1703id.all_lay);
            this.f10469c = (ImageView) view.findViewById(C1701R.C1703id.level_img);
        }
    }
}
