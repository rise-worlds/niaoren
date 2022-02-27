package com.nrzs.p067ft.adapter.manager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/* renamed from: com.nrzs.ft.adapter.manager.FtHorLayoutManager */
/* loaded from: classes2.dex */
public class FtHorLayoutManager extends LinearLayoutManager {
    public FtHorLayoutManager(Context context) {
        super(context);
        setOrientation(0);
    }

    public FtHorLayoutManager(Context context, int i, boolean z) {
        super(context, i, z);
    }

    public FtHorLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // android.support.p006v7.widget.LinearLayoutManager, android.support.p006v7.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -1);
    }
}
