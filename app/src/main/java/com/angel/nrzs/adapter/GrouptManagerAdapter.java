package com.angel.nrzs.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.angel.nrzs.C0692R;
import com.nrzs.data.ddy.bean.respond.GroupInfo;
import java.util.List;
import p110z1.AbstractC5307eh;
import p110z1.DeleteGroupDialog;
import p110z1.RnameGroupDialog;

/* loaded from: classes.dex */
public class GrouptManagerAdapter extends RecyclerView.Adapter<GroupViewHolder> {

    /* renamed from: a */
    private List<GroupInfo> f5137a;

    /* renamed from: b */
    private AbstractC5307eh f5138b;

    public GrouptManagerAdapter(AbstractC5307eh ehVar) {
        this.f5138b = ehVar;
    }

    /* renamed from: a */
    public void m25187a(List<GroupInfo> list) {
        this.f5137a = list;
    }

    /* renamed from: a */
    public GroupViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new GroupViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C0692R.layout.nrzs_item_group_manager, viewGroup, false));
    }

    /* renamed from: a */
    public void onBindViewHolder(GroupViewHolder groupViewHolder, final int i) {
        groupViewHolder.f5143a.setText(this.f5137a.get(i).GroupName);
        groupViewHolder.f5144b.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.adapter.GrouptManagerAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new RnameGroupDialog(view.getContext(), ((GroupInfo) GrouptManagerAdapter.this.f5137a.get(i)).GroupId, GrouptManagerAdapter.this.f5138b).show();
            }
        });
        groupViewHolder.f5145c.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.adapter.GrouptManagerAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new DeleteGroupDialog(view.getContext(), ((GroupInfo) GrouptManagerAdapter.this.f5137a.get(i)).GroupId, GrouptManagerAdapter.this.f5138b).show();
            }
        });
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f5137a.size();
    }

    /* loaded from: classes.dex */
    public class GroupViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a */
        TextView f5143a;

        /* renamed from: b */
        ImageView f5144b;

        /* renamed from: c */
        ImageView f5145c;

        public GroupViewHolder(View view) {
            super(view);
            this.f5143a = (TextView) view.findViewById(C0692R.C0694id.f2634g6);
            this.f5144b = (ImageView) view.findViewById(C0692R.C0694id.edit_grout);
            this.f5145c = (ImageView) view.findViewById(C0692R.C0694id.delete_btn);
        }
    }
}
