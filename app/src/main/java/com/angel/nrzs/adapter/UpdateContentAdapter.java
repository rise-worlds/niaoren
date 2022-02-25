package com.angel.nrzs.adapter;

import android.support.p006v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.angel.nrzs.C0692R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class UpdateContentAdapter extends RecyclerView.Adapter<UpdateContentViewHolder> {

    /* renamed from: a */
    private String[] f5213a;

    /* renamed from: b */
    private List<String> f5214b = new ArrayList();

    public UpdateContentAdapter(String str) {
        String[] strArr;
        if (str.contains("--") || str.contains("\n")) {
            this.f5213a = str.split("\n|--");
            for (String str2 : this.f5213a) {
                if (!TextUtils.isEmpty(str2)) {
                    this.f5214b.add(str2);
                }
            }
            return;
        }
        this.f5214b.add(str);
    }

    /* renamed from: a */
    public UpdateContentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new UpdateContentViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C0692R.layout.nrzs_item_update_content, viewGroup, false));
    }

    /* renamed from: a */
    public void onBindViewHolder(UpdateContentViewHolder updateContentViewHolder, int i) {
        if (this.f5214b.size() == 1) {
            updateContentViewHolder.f5215a.setText(this.f5214b.get(i));
        } else {
            updateContentViewHolder.f5215a.setText(m25153a(this.f5214b.get(i)));
        }
    }

    @Override // android.support.p006v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f5214b.size();
    }

    /* renamed from: a */
    private String m25153a(String str) {
        int lastIndexOf = str.lastIndexOf("；");
        if (lastIndexOf == -1) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lastIndexOf; i++) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    /* loaded from: classes.dex */
    public class UpdateContentViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a */
        TextView f5215a;

        public UpdateContentViewHolder(View view) {
            super(view);
            this.f5215a = (TextView) view.findViewById(C0692R.C0694id.text_item_update_content);
        }
    }
}
