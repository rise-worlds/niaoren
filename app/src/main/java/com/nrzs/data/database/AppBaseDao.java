package com.nrzs.data.database;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

/* renamed from: com.nrzs.data.database.a */
/* loaded from: classes2.dex */
public interface AppBaseDao<T> {
    @Insert(onConflict = 1)
    /* renamed from: a */
    void mo12572a(T t);

    @Delete
    /* renamed from: b */
    void mo12570b(T t);

    @Update
    /* renamed from: c */
    void mo12568c(T t);
}
