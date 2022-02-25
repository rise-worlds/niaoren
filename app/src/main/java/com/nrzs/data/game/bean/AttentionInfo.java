package com.nrzs.data.game.bean;

import android.arch.persistence.room.ColumnInfo;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class AttentionInfo implements Serializable {
    @ColumnInfo
    private int Attention;
    @ColumnInfo
    public boolean fromSearchActivity = false;

    public int getAttention() {
        return this.Attention;
    }

    public void setAttention(int i) {
        this.Attention = i;
    }
}
