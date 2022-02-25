package com.j256.ormlite.android.apptools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import p110z1.AndroidConnectionSource;
import p110z1.AndroidDatabaseConnection;
import p110z1.C5570ui;
import p110z1.ConnectionSource;
import p110z1.Dao;
import p110z1.DaoManager;
import p110z1.DatabaseConnection;
import p110z1.DatabaseTableConfigLoader;
import p110z1.LoggerFactory;
import p110z1.RuntimeExceptionDao;

/* renamed from: com.j256.ormlite.android.apptools.b */
/* loaded from: classes.dex */
public abstract class OrmLiteSqliteOpenHelper extends SQLiteOpenHelper {
    protected static C5570ui logger = LoggerFactory.m545a(OrmLiteSqliteOpenHelper.class);
    protected AndroidConnectionSource connectionSource;
    private volatile boolean isOpen;

    public abstract void onCreate(SQLiteDatabase sQLiteDatabase, ConnectionSource wmVar);

    public abstract void onUpgrade(SQLiteDatabase sQLiteDatabase, ConnectionSource wmVar, int i, int i2);

    public OrmLiteSqliteOpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
        this.connectionSource = new AndroidConnectionSource(this);
        this.isOpen = true;
        logger.m618a("{}: constructed connectionSource {}", this, this.connectionSource);
    }

    public OrmLiteSqliteOpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i, int i2) {
        this(context, str, cursorFactory, i, openFileId(context, i2));
    }

    public OrmLiteSqliteOpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i, File file) {
        this(context, str, cursorFactory, i, openFile(file));
    }

    public OrmLiteSqliteOpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i, InputStream inputStream) {
        super(context, str, cursorFactory, i);
        this.connectionSource = new AndroidConnectionSource(this);
        this.isOpen = true;
        try {
            if (inputStream != null) {
                try {
                    DaoManager.m1032a(DatabaseTableConfigLoader.m182a(new BufferedReader(new InputStreamReader(inputStream), 4096)));
                    try {
                        inputStream.close();
                    } catch (IOException unused) {
                    }
                } catch (SQLException e) {
                    throw new IllegalStateException("Could not load object config file", e);
                }
            }
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException unused2) {
            }
            throw th;
        }
    }

    public ConnectionSource getConnectionSource() {
        if (!this.isOpen) {
            logger.m571d(new IllegalStateException(), "Getting connectionSource was called after closed");
        }
        return this.connectionSource;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        ConnectionSource connectionSource = getConnectionSource();
        DatabaseConnection g = connectionSource.mo247g();
        boolean z = true;
        if (g == null) {
            g = new AndroidDatabaseConnection(sQLiteDatabase, true);
            try {
                connectionSource.mo253b(g);
            } catch (SQLException e) {
                throw new IllegalStateException("Could not save special connection", e);
            }
        } else {
            z = false;
        }
        try {
            onCreate(sQLiteDatabase, connectionSource);
        } finally {
            if (z) {
                connectionSource.mo251c(g);
            }
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        ConnectionSource connectionSource = getConnectionSource();
        DatabaseConnection g = connectionSource.mo247g();
        boolean z = true;
        if (g == null) {
            g = new AndroidDatabaseConnection(sQLiteDatabase, true);
            try {
                connectionSource.mo253b(g);
            } catch (SQLException e) {
                throw new IllegalStateException("Could not save special connection", e);
            }
        } else {
            z = false;
        }
        try {
            onUpgrade(sQLiteDatabase, connectionSource, i, i2);
        } finally {
            if (z) {
                connectionSource.mo251c(g);
            }
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper, java.lang.AutoCloseable
    public void close() {
        super.close();
        this.connectionSource.mo252c();
        this.isOpen = false;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public <D extends Dao<T, ?>, T> D getDao(Class<T> cls) throws SQLException {
        return (D) DaoManager.m1027a(getConnectionSource(), cls);
    }

    public <D extends RuntimeExceptionDao<T, ?>, T> D getRuntimeExceptionDao(Class<T> cls) {
        try {
            return (D) new RuntimeExceptionDao(getDao(cls));
        } catch (SQLException e) {
            throw new RuntimeException("Could not create RuntimeExcepitionDao for class " + cls, e);
        }
    }

    public String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(super.hashCode());
    }

    private static InputStream openFileId(Context context, int i) {
        InputStream openRawResource = context.getResources().openRawResource(i);
        if (openRawResource != null) {
            return openRawResource;
        }
        throw new IllegalStateException("Could not find object config file with id " + i);
    }

    private static InputStream openFile(File file) {
        if (file == null) {
            return null;
        }
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Could not open config file " + file, e);
        }
    }
}
