package com.cyjh.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import p110z1.ConnectionSource;
import p110z1.TableUtils;

/* renamed from: com.cyjh.db.OrmLiteOpenHelper */
/* loaded from: classes.dex */
public class OrmLiteOpenHelper extends OrmLiteSqliteOpenHelper {
    public static List<Class> DAO_CLASS_LIST = new ArrayList();
    public static final String DATABASE_NAME = "cyjh.sqlite";
    public static final int DATABASE_VERSION = 10;

    public OrmLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, 10);
    }

    @Override // com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase, ConnectionSource wmVar) {
        for (Class cls : DAO_CLASS_LIST) {
            try {
                TableUtils.m158a(wmVar, cls);
            } catch (SQLException unused) {
            }
        }
    }

    @Override // com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, ConnectionSource wmVar, int i, int i2) {
        try {
            for (Class cls : DAO_CLASS_LIST) {
                TableUtils.m157a((ConnectionSource) this.connectionSource, cls, true);
            }
            onCreate(sQLiteDatabase, this.connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
