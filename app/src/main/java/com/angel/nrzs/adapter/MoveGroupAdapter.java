package com.angel.nrzs.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.angel.nrzs.App;
import com.angel.nrzs.C0692R;
import com.nrzs.data.ddy.bean.respond.OrderDaileInfo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class MoveGroupAdapter extends RecyclerView.Adapter<GroupViewHolder> {

    /* renamed from: a */
    private List<OrderDaileInfo> f5164a;

    /* renamed from: b */
    private List<OrderDaileInfo> f5165b = new ArrayList();

    /* renamed from: a */
    public void m25178a(List<OrderDaileInfo> list) {
        this.f5164a = list;
    }

    /* renamed from: a */
    public void m25177a(boolean z) {
        for (int i = 0; i < this.f5164a.size(); i++) {
            this.f5164a.get(i).isCheack = z;
        }
        this.f5165b.clear();
        if (z) {
            this.f5165b.addAll(this.f5164a);
        }
        notifyDataSetChanged();
    }

    /* renamed from: a */
    public List<OrderDaileInfo> m25182a() {
        return this.f5165b;
    }

    /* renamed from: a */
    public GroupViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new GroupViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C0692R.layout.nrzs_item_move_group, viewGroup, false));
    }

    /* renamed from: a */
    public void onBindViewHolder(final GroupViewHolder groupViewHolder, final int i) {
        OrderDaileInfo orderDaileInfo = this.f5164a.get(i);
        groupViewHolder.f5169a.setText(orderDaileInfo.DeviceName);
        TextView textView = groupViewHolder.f5170b;
        textView.setText("编号 ：" + orderDaileInfo.DDYOrderId);
        groupViewHolder.f5171c.setText(orderDaileInfo.RemainTime);
        if (orderDaileInfo.TypeCode == 11 || orderDaileInfo.TypeCode == 4) {
            groupViewHolder.f5173e.setImageDrawable(App.m25213a().getResources().getDrawable(C0692R.C0693drawable.f2175ka));
        } else if (orderDaileInfo.TypeCode == 12) {
            groupViewHolder.f5173e.setImageDrawable(App.m25213a().getResources().getDrawable(C0692R.C0693drawable.f2178kd));
        } else if (orderDaileInfo.TypeCode == 7) {
            groupViewHolder.f5173e.setImageDrawable(App.m25213a().getResources().getDrawable(C0692R.C0693drawable.f2177kc));
        } else if (orderDaileInfo.TypeCode == 3 || orderDaileInfo.TypeCode == 9) {
            groupViewHolder.f5173e.setImageDrawable(App.m25213a().getResources().getDrawable(C0692R.C0693drawable.f2176kb));
        } else {
            groupViewHolder.f5173e.setImageDrawable(App.m25213a().getResources().getDrawable(C0692R.C0693drawable.f2177kc));
        }
        groupViewHolder.f5172d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.angel.nrzs.adapter.MoveGroupAdapter.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ((OrderDaileInfo) MoveGroupAdapter.this.f5164a.get(i)).isCheack = z;
                groupViewHolder.f5172d.setChecked(((OrderDaileInfo) MoveGroupAdapter.this.f5164a.get(i)).isCheack);
                if (z) {
                    MoveGroupAdapter.this.f5165b.add(MoveGroupAdapter.this.f5164a.get(i));
                } else {
                    MoveGroupAdapter.this.f5165b.remove(MoveGroupAdapter.this.f5164a.get(i));
                }
            }
        });
        groupViewHolder.f5172d.setChecked(this.f5164a.get(i).isCheack);
    }

    @Override // android.support.p006v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f5164a.size();
    }

    /* loaded from: classes.dex */
    public class GroupViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a */
        TextView f5169a;

        /* renamed from: b */
        TextView f5170b;

        /* renamed from: c */
        TextView f5171c;

        /* renamed from: d */
        CheckBox f5172d;

        /* renamed from: e */
        ImageView f5173e;

        public GroupViewHolder(View view) {
            super(view);
            this.f5169a = (TextView) view.findViewById(C0692R.C0694id.f2634g6);
            this.f5170b = (TextView) view.findViewById(C0692R.C0694id.f2918r2);
            this.f5171c = (TextView) view.findViewById(C0692R.C0694id.f3096wn);
            this.f5172d = (CheckBox) view.findViewById(C0692R.C0694id.f2541d4);
            this.f5173e = (ImageView) view.findViewById(C0692R.C0694id.f2724iy);
        }
    }
}
