package com.nrzs.moudleui.adapter;

import android.support.annotation.NonNull;
import android.support.p006v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes2.dex */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    public BaseViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        this(layoutInflater.inflate(i, viewGroup, false));
    }

    public BaseViewHolder(@NonNull View view) {
        super(view);
    }
}
