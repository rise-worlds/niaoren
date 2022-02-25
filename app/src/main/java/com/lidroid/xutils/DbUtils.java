package com.lidroid.xutils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.p058db.sqlite.CursorUtils;
import com.lidroid.xutils.p058db.sqlite.DbModelSelector;
import com.lidroid.xutils.p058db.sqlite.Selector;
import com.lidroid.xutils.p058db.sqlite.SqlInfo;
import com.lidroid.xutils.p058db.sqlite.SqlInfoBuilder;
import com.lidroid.xutils.p058db.sqlite.WhereBuilder;
import com.lidroid.xutils.p058db.table.C1665Id;
import com.lidroid.xutils.p058db.table.DbModel;
import com.lidroid.xutils.p058db.table.Table;
import com.lidroid.xutils.p058db.table.TableUtils;
import com.lidroid.xutils.util.IOUtils;
import com.lidroid.xutils.util.LogUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import p110z1.SimpleComparison;

/* loaded from: classes.dex */
public class DbUtils {
    private static HashMap<String, DbUtils> daoMap = new HashMap<>();
    private DaoConfig daoConfig;
    private SQLiteDatabase database;
    private boolean debug = false;
    private boolean allowTransaction = false;
    private Lock writeLock = new ReentrantLock();
    private volatile boolean writeLocked = false;
    private final FindTempCache findTempCache = new FindTempCache(this, null);

    /* loaded from: classes.dex */
    public interface DbUpgradeListener {
        void onUpgrade(DbUtils dbUtils, int i, int i2);
    }

    private DbUtils(DaoConfig daoConfig) {
        if (daoConfig != null) {
            this.database = createDatabase(daoConfig);
            this.daoConfig = daoConfig;
            return;
        }
        throw new IllegalArgumentException("daoConfig may not be null");
    }

    private static synchronized DbUtils getInstance(DaoConfig daoConfig) {
        DbUtils dbUtils;
        synchronized (DbUtils.class) {
            dbUtils = daoMap.get(daoConfig.getDbName());
            if (dbUtils == null) {
                dbUtils = new DbUtils(daoConfig);
                daoMap.put(daoConfig.getDbName(), dbUtils);
            } else {
                dbUtils.daoConfig = daoConfig;
            }
            SQLiteDatabase sQLiteDatabase = dbUtils.database;
            int version = sQLiteDatabase.getVersion();
            int dbVersion = daoConfig.getDbVersion();
            if (version != dbVersion) {
                if (version != 0) {
                    DbUpgradeListener dbUpgradeListener = daoConfig.getDbUpgradeListener();
                    if (dbUpgradeListener != null) {
                        dbUpgradeListener.onUpgrade(dbUtils, version, dbVersion);
                    } else {
                        try {
                            dbUtils.dropDb();
                        } catch (DbException e) {
                            LogUtils.m19219e(e.getMessage(), e);
                        }
                    }
                }
                sQLiteDatabase.setVersion(dbVersion);
            }
        }
        return dbUtils;
    }

    public static DbUtils create(Context context) {
        return getInstance(new DaoConfig(context));
    }

    public static DbUtils create(Context context, String str) {
        DaoConfig daoConfig = new DaoConfig(context);
        daoConfig.setDbName(str);
        return getInstance(daoConfig);
    }

    public static DbUtils create(Context context, String str, String str2) {
        DaoConfig daoConfig = new DaoConfig(context);
        daoConfig.setDbDir(str);
        daoConfig.setDbName(str2);
        return getInstance(daoConfig);
    }

    public static DbUtils create(Context context, String str, int i, DbUpgradeListener dbUpgradeListener) {
        DaoConfig daoConfig = new DaoConfig(context);
        daoConfig.setDbName(str);
        daoConfig.setDbVersion(i);
        daoConfig.setDbUpgradeListener(dbUpgradeListener);
        return getInstance(daoConfig);
    }

    public static DbUtils create(Context context, String str, String str2, int i, DbUpgradeListener dbUpgradeListener) {
        DaoConfig daoConfig = new DaoConfig(context);
        daoConfig.setDbDir(str);
        daoConfig.setDbName(str2);
        daoConfig.setDbVersion(i);
        daoConfig.setDbUpgradeListener(dbUpgradeListener);
        return getInstance(daoConfig);
    }

    public static DbUtils create(DaoConfig daoConfig) {
        return getInstance(daoConfig);
    }

    public DbUtils configDebug(boolean z) {
        this.debug = z;
        return this;
    }

    public DbUtils configAllowTransaction(boolean z) {
        this.allowTransaction = z;
        return this;
    }

    public SQLiteDatabase getDatabase() {
        return this.database;
    }

    public DaoConfig getDaoConfig() {
        return this.daoConfig;
    }

    public void saveOrUpdate(Object obj) throws DbException {
        try {
            beginTransaction();
            createTableIfNotExist(obj.getClass());
            saveOrUpdateWithoutTransaction(obj);
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void saveOrUpdateAll(List<?> list) throws DbException {
        if (list != null && list.size() != 0) {
            try {
                beginTransaction();
                createTableIfNotExist(list.get(0).getClass());
                Iterator<?> it = list.iterator();
                while (it.hasNext()) {
                    saveOrUpdateWithoutTransaction(it.next());
                }
                setTransactionSuccessful();
            } finally {
                endTransaction();
            }
        }
    }

    public void replace(Object obj) throws DbException {
        try {
            beginTransaction();
            createTableIfNotExist(obj.getClass());
            execNonQuery(SqlInfoBuilder.buildReplaceSqlInfo(this, obj));
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void replaceAll(List<?> list) throws DbException {
        if (list != null && list.size() != 0) {
            try {
                beginTransaction();
                createTableIfNotExist(list.get(0).getClass());
                Iterator<?> it = list.iterator();
                while (it.hasNext()) {
                    execNonQuery(SqlInfoBuilder.buildReplaceSqlInfo(this, it.next()));
                }
                setTransactionSuccessful();
            } finally {
                endTransaction();
            }
        }
    }

    public void save(Object obj) throws DbException {
        try {
            beginTransaction();
            createTableIfNotExist(obj.getClass());
            execNonQuery(SqlInfoBuilder.buildInsertSqlInfo(this, obj));
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void saveAll(List<?> list) throws DbException {
        if (list != null && list.size() != 0) {
            try {
                beginTransaction();
                createTableIfNotExist(list.get(0).getClass());
                Iterator<?> it = list.iterator();
                while (it.hasNext()) {
                    execNonQuery(SqlInfoBuilder.buildInsertSqlInfo(this, it.next()));
                }
                setTransactionSuccessful();
            } finally {
                endTransaction();
            }
        }
    }

    public boolean saveBindingId(Object obj) throws DbException {
        try {
            beginTransaction();
            createTableIfNotExist(obj.getClass());
            boolean saveBindingIdWithoutTransaction = saveBindingIdWithoutTransaction(obj);
            setTransactionSuccessful();
            return saveBindingIdWithoutTransaction;
        } finally {
            endTransaction();
        }
    }

    public void saveBindingIdAll(List<?> list) throws DbException {
        if (list != null && list.size() != 0) {
            try {
                beginTransaction();
                createTableIfNotExist(list.get(0).getClass());
                Iterator<?> it = list.iterator();
                while (it.hasNext()) {
                    if (!saveBindingIdWithoutTransaction(it.next())) {
                        throw new DbException("saveBindingId error, transaction will not commit!");
                    }
                }
                setTransactionSuccessful();
            } finally {
                endTransaction();
            }
        }
    }

    public void deleteById(Class<?> cls, Object obj) throws DbException {
        if (tableIsExist(cls)) {
            try {
                beginTransaction();
                execNonQuery(SqlInfoBuilder.buildDeleteSqlInfo(this, cls, obj));
                setTransactionSuccessful();
            } finally {
                endTransaction();
            }
        }
    }

    public void delete(Object obj) throws DbException {
        if (tableIsExist(obj.getClass())) {
            try {
                beginTransaction();
                execNonQuery(SqlInfoBuilder.buildDeleteSqlInfo(this, obj));
                setTransactionSuccessful();
            } finally {
                endTransaction();
            }
        }
    }

    public void delete(Class<?> cls, WhereBuilder whereBuilder) throws DbException {
        if (tableIsExist(cls)) {
            try {
                beginTransaction();
                execNonQuery(SqlInfoBuilder.buildDeleteSqlInfo(this, cls, whereBuilder));
                setTransactionSuccessful();
            } finally {
                endTransaction();
            }
        }
    }

    public void deleteAll(List<?> list) throws DbException {
        if (list != null && list.size() != 0 && tableIsExist(list.get(0).getClass())) {
            try {
                beginTransaction();
                Iterator<?> it = list.iterator();
                while (it.hasNext()) {
                    execNonQuery(SqlInfoBuilder.buildDeleteSqlInfo(this, it.next()));
                }
                setTransactionSuccessful();
            } finally {
                endTransaction();
            }
        }
    }

    public void deleteAll(Class<?> cls) throws DbException {
        delete(cls, null);
    }

    public void update(Object obj, String... strArr) throws DbException {
        if (tableIsExist(obj.getClass())) {
            try {
                beginTransaction();
                execNonQuery(SqlInfoBuilder.buildUpdateSqlInfo(this, obj, strArr));
                setTransactionSuccessful();
            } finally {
                endTransaction();
            }
        }
    }

    public void update(Object obj, WhereBuilder whereBuilder, String... strArr) throws DbException {
        if (tableIsExist(obj.getClass())) {
            try {
                beginTransaction();
                execNonQuery(SqlInfoBuilder.buildUpdateSqlInfo(this, obj, whereBuilder, strArr));
                setTransactionSuccessful();
            } finally {
                endTransaction();
            }
        }
    }

    public void updateAll(List<?> list, String... strArr) throws DbException {
        if (list != null && list.size() != 0 && tableIsExist(list.get(0).getClass())) {
            try {
                beginTransaction();
                Iterator<?> it = list.iterator();
                while (it.hasNext()) {
                    execNonQuery(SqlInfoBuilder.buildUpdateSqlInfo(this, it.next(), strArr));
                }
                setTransactionSuccessful();
            } finally {
                endTransaction();
            }
        }
    }

    public void updateAll(List<?> list, WhereBuilder whereBuilder, String... strArr) throws DbException {
        if (list != null && list.size() != 0 && tableIsExist(list.get(0).getClass())) {
            try {
                beginTransaction();
                Iterator<?> it = list.iterator();
                while (it.hasNext()) {
                    execNonQuery(SqlInfoBuilder.buildUpdateSqlInfo(this, it.next(), whereBuilder, strArr));
                }
                setTransactionSuccessful();
            } finally {
                endTransaction();
            }
        }
    }

    public <T> T findById(Class<T> cls, Object obj) throws DbException {
        DbException dbException;
        if (!tableIsExist(cls)) {
            return null;
        }
        String selector = Selector.from(cls).where(Table.get(this, cls).f10235id.getColumnName(), SimpleComparison.f23609c, obj).limit(1).toString();
        long seq = CursorUtils.FindCacheSequence.getSeq();
        this.findTempCache.setSeq(seq);
        T t = (T) this.findTempCache.get(selector);
        if (t != null) {
            return t;
        }
        Cursor execQuery = execQuery(selector);
        if (execQuery != null) {
            try {
                if (execQuery.moveToNext()) {
                    T t2 = (T) CursorUtils.getEntity(this, execQuery, cls, seq);
                    this.findTempCache.put(selector, t2);
                    return t2;
                }
            } finally {
                try {
                } finally {
                }
            }
        }
        return null;
    }

    public <T> T findFirst(Selector selector) throws DbException {
        DbException dbException;
        if (!tableIsExist(selector.getEntityType())) {
            return null;
        }
        String selector2 = selector.limit(1).toString();
        long seq = CursorUtils.FindCacheSequence.getSeq();
        this.findTempCache.setSeq(seq);
        T t = (T) this.findTempCache.get(selector2);
        if (t != null) {
            return t;
        }
        Cursor execQuery = execQuery(selector2);
        if (execQuery != null) {
            try {
                if (execQuery.moveToNext()) {
                    T t2 = (T) CursorUtils.getEntity(this, execQuery, selector.getEntityType(), seq);
                    this.findTempCache.put(selector2, t2);
                    return t2;
                }
            } finally {
                try {
                } finally {
                }
            }
        }
        return null;
    }

    public <T> T findFirst(Class<T> cls) throws DbException {
        return (T) findFirst(Selector.from(cls));
    }

    public <T> List<T> findAll(Selector selector) throws DbException {
        DbException dbException;
        if (!tableIsExist(selector.getEntityType())) {
            return null;
        }
        String selector2 = selector.toString();
        long seq = CursorUtils.FindCacheSequence.getSeq();
        this.findTempCache.setSeq(seq);
        Object obj = this.findTempCache.get(selector2);
        if (obj != null) {
            return (List) obj;
        }
        ArrayList arrayList = new ArrayList();
        Cursor execQuery = execQuery(selector2);
        if (execQuery != null) {
            while (execQuery.moveToNext()) {
                try {
                    arrayList.add(CursorUtils.getEntity(this, execQuery, selector.getEntityType(), seq));
                } finally {
                    try {
                    } finally {
                    }
                }
            }
            this.findTempCache.put(selector2, arrayList);
        }
        return arrayList;
    }

    public <T> List<T> findAll(Class<T> cls) throws DbException {
        return findAll(Selector.from(cls));
    }

    public DbModel findDbModelFirst(SqlInfo sqlInfo) throws DbException {
        Cursor execQuery = execQuery(sqlInfo);
        if (execQuery == null) {
            return null;
        }
        try {
            if (execQuery.moveToNext()) {
                return CursorUtils.getDbModel(execQuery);
            }
            return null;
        } catch (Throwable th) {
            try {
                throw new DbException(th);
            } finally {
                IOUtils.closeQuietly(execQuery);
            }
        }
    }

    public DbModel findDbModelFirst(DbModelSelector dbModelSelector) throws DbException {
        Cursor execQuery;
        DbException dbException;
        if (tableIsExist(dbModelSelector.getEntityType()) && (execQuery = execQuery(dbModelSelector.limit(1).toString())) != null) {
            try {
                if (execQuery.moveToNext()) {
                    return CursorUtils.getDbModel(execQuery);
                }
            } finally {
                try {
                } finally {
                }
            }
        }
        return null;
    }

    public List<DbModel> findDbModelAll(SqlInfo sqlInfo) throws DbException {
        DbException dbException;
        ArrayList arrayList = new ArrayList();
        Cursor execQuery = execQuery(sqlInfo);
        if (execQuery != null) {
            while (execQuery.moveToNext()) {
                try {
                    arrayList.add(CursorUtils.getDbModel(execQuery));
                } finally {
                    try {
                    } finally {
                    }
                }
            }
        }
        return arrayList;
    }

    public List<DbModel> findDbModelAll(DbModelSelector dbModelSelector) throws DbException {
        DbException dbException;
        if (!tableIsExist(dbModelSelector.getEntityType())) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Cursor execQuery = execQuery(dbModelSelector.toString());
        if (execQuery != null) {
            while (execQuery.moveToNext()) {
                try {
                    arrayList.add(CursorUtils.getDbModel(execQuery));
                } finally {
                    try {
                    } finally {
                    }
                }
            }
        }
        return arrayList;
    }

    public long count(Selector selector) throws DbException {
        Class<?> entityType = selector.getEntityType();
        if (!tableIsExist(entityType)) {
            return 0L;
        }
        Table table = Table.get(this, entityType);
        return findDbModelFirst(selector.select("count(" + table.f10235id.getColumnName() + ") as count")).getLong("count");
    }

    public long count(Class<?> cls) throws DbException {
        return count(Selector.from(cls));
    }

    /* loaded from: classes.dex */
    public static class DaoConfig {
        private Context context;
        private String dbDir;
        private DbUpgradeListener dbUpgradeListener;
        private String dbName = "xUtils.db";
        private int dbVersion = 1;

        public DaoConfig(Context context) {
            this.context = context.getApplicationContext();
        }

        public Context getContext() {
            return this.context;
        }

        public String getDbName() {
            return this.dbName;
        }

        public void setDbName(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.dbName = str;
            }
        }

        public int getDbVersion() {
            return this.dbVersion;
        }

        public void setDbVersion(int i) {
            this.dbVersion = i;
        }

        public DbUpgradeListener getDbUpgradeListener() {
            return this.dbUpgradeListener;
        }

        public void setDbUpgradeListener(DbUpgradeListener dbUpgradeListener) {
            this.dbUpgradeListener = dbUpgradeListener;
        }

        public String getDbDir() {
            return this.dbDir;
        }

        public void setDbDir(String str) {
            this.dbDir = str;
        }
    }

    private SQLiteDatabase createDatabase(DaoConfig daoConfig) {
        String dbDir = daoConfig.getDbDir();
        if (TextUtils.isEmpty(dbDir)) {
            return daoConfig.getContext().openOrCreateDatabase(daoConfig.getDbName(), 0, null);
        }
        File file = new File(dbDir);
        if (file.exists() || file.mkdirs()) {
            return SQLiteDatabase.openOrCreateDatabase(new File(dbDir, daoConfig.getDbName()), (SQLiteDatabase.CursorFactory) null);
        }
        return null;
    }

    private void saveOrUpdateWithoutTransaction(Object obj) throws DbException {
        C1665Id id = Table.get(this, obj.getClass()).f10235id;
        if (!id.isAutoIncrement()) {
            execNonQuery(SqlInfoBuilder.buildReplaceSqlInfo(this, obj));
        } else if (id.getColumnValue(obj) != null) {
            execNonQuery(SqlInfoBuilder.buildUpdateSqlInfo(this, obj, new String[0]));
        } else {
            saveBindingIdWithoutTransaction(obj);
        }
    }

    private boolean saveBindingIdWithoutTransaction(Object obj) throws DbException {
        Table table = Table.get(this, obj.getClass());
        C1665Id id = table.f10235id;
        if (id.isAutoIncrement()) {
            execNonQuery(SqlInfoBuilder.buildInsertSqlInfo(this, obj));
            long lastAutoIncrementId = getLastAutoIncrementId(table.tableName);
            if (lastAutoIncrementId == -1) {
                return false;
            }
            id.setAutoIncrementId(obj, lastAutoIncrementId);
            return true;
        }
        execNonQuery(SqlInfoBuilder.buildInsertSqlInfo(this, obj));
        return true;
    }

    private long getLastAutoIncrementId(String str) throws DbException {
        DbException dbException;
        Cursor execQuery = execQuery("SELECT seq FROM sqlite_sequence WHERE name='" + str + "'");
        long j = -1;
        if (execQuery != null) {
            try {
                if (execQuery.moveToNext()) {
                    j = execQuery.getLong(0);
                }
            } finally {
                try {
                } finally {
                }
            }
        }
        return j;
    }

    public void createTableIfNotExist(Class<?> cls) throws DbException {
        if (!tableIsExist(cls)) {
            execNonQuery(SqlInfoBuilder.buildCreateTableSqlInfo(this, cls));
            String execAfterTableCreated = TableUtils.getExecAfterTableCreated(cls);
            if (!TextUtils.isEmpty(execAfterTableCreated)) {
                execNonQuery(execAfterTableCreated);
            }
        }
    }

    public boolean tableIsExist(Class<?> cls) throws DbException {
        DbException dbException;
        Table table = Table.get(this, cls);
        if (table.isCheckedDatabase()) {
            return true;
        }
        Cursor execQuery = execQuery("SELECT COUNT(*) AS c FROM sqlite_master WHERE type='table' AND name='" + table.tableName + "'");
        if (execQuery != null) {
            try {
                if (execQuery.moveToNext() && execQuery.getInt(0) > 0) {
                    table.setCheckedDatabase(true);
                    return true;
                }
            } finally {
                try {
                } finally {
                }
            }
        }
        return false;
    }

    public void dropDb() throws DbException {
        Cursor execQuery = execQuery("SELECT name FROM sqlite_master WHERE type='table' AND name<>'sqlite_sequence'");
        if (execQuery != null) {
            while (execQuery.moveToNext()) {
                try {
                    String string = execQuery.getString(0);
                    execNonQuery("DROP TABLE " + string);
                    Table.remove(this, string);
                } catch (Throwable th) {
                    try {
                        throw new DbException(th);
                    } finally {
                        IOUtils.closeQuietly(execQuery);
                    }
                }
            }
        }
    }

    public void dropTable(Class<?> cls) throws DbException {
        if (tableIsExist(cls)) {
            String tableName = TableUtils.getTableName(cls);
            execNonQuery("DROP TABLE " + tableName);
            Table.remove(this, cls);
        }
    }

    public void close() {
        String dbName = this.daoConfig.getDbName();
        if (daoMap.containsKey(dbName)) {
            daoMap.remove(dbName);
            this.database.close();
        }
    }

    private void debugSql(String str) {
        if (this.debug) {
            LogUtils.m19222d(str);
        }
    }

    private void beginTransaction() {
        if (this.allowTransaction) {
            this.database.beginTransaction();
            return;
        }
        this.writeLock.lock();
        this.writeLocked = true;
    }

    private void setTransactionSuccessful() {
        if (this.allowTransaction) {
            this.database.setTransactionSuccessful();
        }
    }

    private void endTransaction() {
        if (this.allowTransaction) {
            this.database.endTransaction();
        }
        if (this.writeLocked) {
            this.writeLock.unlock();
            this.writeLocked = false;
        }
    }

    public void execNonQuery(SqlInfo sqlInfo) throws DbException {
        debugSql(sqlInfo.getSql());
        try {
            if (sqlInfo.getBindArgs() != null) {
                this.database.execSQL(sqlInfo.getSql(), sqlInfo.getBindArgsAsArray());
            } else {
                this.database.execSQL(sqlInfo.getSql());
            }
        } catch (Throwable th) {
            throw new DbException(th);
        }
    }

    public void execNonQuery(String str) throws DbException {
        debugSql(str);
        try {
            this.database.execSQL(str);
        } catch (Throwable th) {
            throw new DbException(th);
        }
    }

    public Cursor execQuery(SqlInfo sqlInfo) throws DbException {
        debugSql(sqlInfo.getSql());
        try {
            return this.database.rawQuery(sqlInfo.getSql(), sqlInfo.getBindArgsAsStrArray());
        } catch (Throwable th) {
            throw new DbException(th);
        }
    }

    public Cursor execQuery(String str) throws DbException {
        debugSql(str);
        try {
            return this.database.rawQuery(str, null);
        } catch (Throwable th) {
            throw new DbException(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class FindTempCache {
        private final ConcurrentHashMap<String, Object> cache;
        private long seq;

        private FindTempCache() {
            this.cache = new ConcurrentHashMap<>();
            this.seq = 0L;
        }

        /* synthetic */ FindTempCache(DbUtils dbUtils, FindTempCache findTempCache) {
            this();
        }

        public void put(String str, Object obj) {
            if (str != null && obj != null) {
                this.cache.put(str, obj);
            }
        }

        public Object get(String str) {
            return this.cache.get(str);
        }

        public void setSeq(long j) {
            if (this.seq != j) {
                this.cache.clear();
                this.seq = j;
            }
        }
    }
}
