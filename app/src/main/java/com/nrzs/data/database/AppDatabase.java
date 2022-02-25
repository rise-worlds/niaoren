package com.nrzs.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import com.nrzs.data.DataApp;
import com.nrzs.data.game.bean.TopicInfo;
import com.nrzs.data.other.bean.AdResultInfoItem;
import com.nrzs.data.redbag.bean.MoneyInfo;
import p110z1.BannerInfoDao;
import p110z1.MoneyDao;
import p110z1.TopicInfoDao;

@Database(entities = {TopicInfo.class, AdResultInfoItem.class, MoneyInfo.class}, exportSchema = false, version = 7)
/* loaded from: classes2.dex */
public abstract class AppDatabase extends RoomDatabase {

    /* renamed from: a */
    private static final Object f10623a = new Object();

    /* renamed from: b */
    private static AppDatabase f10624b;

    /* renamed from: a */
    public abstract TopicInfoDao mo18932a();

    /* renamed from: b */
    public abstract BannerInfoDao mo18929b();

    /* renamed from: c */
    public abstract MoneyDao mo18926c();

    /* renamed from: d */
    public void m18934d() {
    }

    /* renamed from: e */
    public static AppDatabase m18933e() {
        AppDatabase appDatabase;
        synchronized (f10623a) {
            if (f10624b == null) {
                f10624b = (AppDatabase) Room.databaseBuilder(DataApp.m18939d().m18947a(), AppDatabase.class, "nrzs_db").fallbackToDestructiveMigration().build();
            }
            appDatabase = f10624b;
        }
        return appDatabase;
    }
}
