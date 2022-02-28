package com.angel.nrzs.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.angel.nrzs.C0692R;
import com.nrzs.data.ddy.bean.respond.GroupInfo;
import java.util.List;
import p110z1.ChooseCallback;

/* loaded from: classes.dex */
public class MovePopGrouptAdapter extends RecyclerView.Adapter<GroupViewHolder> {

    /* renamed from: a */
    private List<GroupInfo> f5181a;

    /* renamed from: b */
    private ChooseCallback f5182b;

    /* renamed from: a */
    public void m25167a(List<GroupInfo> list, ChooseCallback eiVar) {
        this.f5181a = list;
        this.f5182b = eiVar;
    }

    /* renamed from: a */
    public GroupViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new GroupViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C0692R.layout.nrzs_item_group, viewGroup, false));
    }

    /* renamed from: a */
    public void onBindViewHolder(GroupViewHolder groupViewHolder, final int i) {
        groupViewHolder.f5185a.setText(this.f5181a.get(i).GroupName);
        groupViewHolder.f5185a.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.adapter.MovePopGrouptAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MovePopGrouptAdapter.this.f5182b.mo3129a((GroupInfo) MovePopGrouptAdapter.this.f5181a.get(i));
            }
        });
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f5181a.size();
    }

    /* loaded from: classes.dex */
    public class GroupViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a */
        TextView f5185a;

        public GroupViewHolder(View view) {
            super(view);
            this.f5185a = (TextView) view.findViewById(C0692R.C0694id.f2634g6);
        }
    }
}
