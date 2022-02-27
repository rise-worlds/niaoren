package com.angel.nrzs.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.angel.nrzs.C0692R;
import com.nrzs.data.ddy.bean.respond.GroupInfo;
import java.util.List;
import p110z1.EventBus;
import p110z1.GroupFlushEvent;

/* loaded from: classes.dex */
public class GrouptAdapter extends RecyclerView.Adapter<GroupViewHolder> {

    /* renamed from: a */
    private List<GroupInfo> f5131a;

    /* renamed from: b */
    private AbstractC0704a f5132b;

    /* renamed from: com.angel.nrzs.adapter.GrouptAdapter$a */
    /* loaded from: classes.dex */
    public interface AbstractC0704a {
        /* renamed from: a */
        void mo3177a();
    }

    public GrouptAdapter(AbstractC0704a aVar) {
        this.f5132b = aVar;
    }

    /* renamed from: a */
    public void m25192a(List<GroupInfo> list) {
        this.f5131a = list;
    }

    /* renamed from: a */
    public GroupViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new GroupViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C0692R.layout.nrzs_item_group, viewGroup, false));
    }

    /* renamed from: a */
    public void onBindViewHolder(GroupViewHolder groupViewHolder, final int i) {
        groupViewHolder.f5135a.setText(this.f5131a.get(i).GroupName);
        groupViewHolder.f5135a.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.adapter.GrouptAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EventBus.m3448a().m3427d(new GroupFlushEvent.C3559c(((GroupInfo) GrouptAdapter.this.f5131a.get(i)).GroupName, i + 1, ((GroupInfo) GrouptAdapter.this.f5131a.get(i)).GroupId));
                GrouptAdapter.this.f5132b.mo3177a();
            }
        });
    }

    @Override // android.support.p006v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f5131a.size();
    }

    /* loaded from: classes.dex */
    public class GroupViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a */
        TextView f5135a;

        public GroupViewHolder(View view) {
            super(view);
            this.f5135a = (TextView) view.findViewById(C0692R.C0694id.f2634g6);
        }
    }
}
