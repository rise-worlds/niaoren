package android.arch.persistence.room;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.arch.core.executor.ArchTaskExecutor;
import android.arch.persistence.p000db.SimpleSQLiteQuery;
import android.arch.persistence.p000db.SupportSQLiteDatabase;
import android.arch.persistence.p000db.SupportSQLiteOpenHelper;
import android.arch.persistence.p000db.SupportSQLiteQuery;
import android.arch.persistence.p000db.SupportSQLiteStatement;
import android.arch.persistence.p000db.framework.FrameworkSQLiteOpenHelperFactory;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.WorkerThread;
import android.support.p003v4.app.ActivityManagerCompat;
import android.support.p003v4.util.SparseArrayCompat;
import android.util.Log;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes.dex */
public abstract class RoomDatabase {
    private static final String DB_IMPL_SUFFIX = "_Impl";
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final int MAX_BIND_PARAMETER_CNT = 999;
    private boolean mAllowMainThreadQueries;
    @Nullable
    protected List<Callback> mCallbacks;
    protected volatile SupportSQLiteDatabase mDatabase;
    private SupportSQLiteOpenHelper mOpenHelper;
    boolean mWriteAheadLoggingEnabled;
    private final ReentrantLock mCloseLock = new ReentrantLock();
    private final InvalidationTracker mInvalidationTracker = createInvalidationTracker();

    /* loaded from: classes.dex */
    public static abstract class Callback {
        public void onCreate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
        }

        public void onOpen(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
        }
    }

    @WorkerThread
    public abstract void clearAllTables();

    @NonNull
    protected abstract InvalidationTracker createInvalidationTracker();

    @NonNull
    protected abstract SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration);

    /* JADX INFO: Access modifiers changed from: package-private */
    public Lock getCloseLock() {
        return this.mCloseLock;
    }

    @CallSuper
    public void init(@NonNull DatabaseConfiguration databaseConfiguration) {
        this.mOpenHelper = createOpenHelper(databaseConfiguration);
        boolean z = false;
        if (Build.VERSION.SDK_INT >= 16) {
            if (databaseConfiguration.journalMode == JournalMode.WRITE_AHEAD_LOGGING) {
                z = true;
            }
            this.mOpenHelper.setWriteAheadLoggingEnabled(z);
        }
        this.mCallbacks = databaseConfiguration.callbacks;
        this.mAllowMainThreadQueries = databaseConfiguration.allowMainThreadQueries;
        this.mWriteAheadLoggingEnabled = z;
    }

    @NonNull
    public SupportSQLiteOpenHelper getOpenHelper() {
        return this.mOpenHelper;
    }

    public boolean isOpen() {
        SupportSQLiteDatabase supportSQLiteDatabase = this.mDatabase;
        return supportSQLiteDatabase != null && supportSQLiteDatabase.isOpen();
    }

    public void close() {
        if (isOpen()) {
            try {
                this.mCloseLock.lock();
                this.mOpenHelper.close();
            } finally {
                this.mCloseLock.unlock();
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void assertNotMainThread() {
        if (!this.mAllowMainThreadQueries && ArchTaskExecutor.getInstance().isMainThread()) {
            throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.");
        }
    }

    public Cursor query(String str, @Nullable Object[] objArr) {
        return this.mOpenHelper.getWritableDatabase().query(new SimpleSQLiteQuery(str, objArr));
    }

    public Cursor query(SupportSQLiteQuery supportSQLiteQuery) {
        assertNotMainThread();
        return this.mOpenHelper.getWritableDatabase().query(supportSQLiteQuery);
    }

    public SupportSQLiteStatement compileStatement(@NonNull String str) {
        assertNotMainThread();
        return this.mOpenHelper.getWritableDatabase().compileStatement(str);
    }

    public void beginTransaction() {
        assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = this.mOpenHelper.getWritableDatabase();
        this.mInvalidationTracker.syncTriggers(writableDatabase);
        writableDatabase.beginTransaction();
    }

    public void endTransaction() {
        this.mOpenHelper.getWritableDatabase().endTransaction();
        if (!inTransaction()) {
            this.mInvalidationTracker.refreshVersionsAsync();
        }
    }

    public void setTransactionSuccessful() {
        this.mOpenHelper.getWritableDatabase().setTransactionSuccessful();
    }

    public void runInTransaction(@NonNull Runnable runnable) {
        beginTransaction();
        try {
            runnable.run();
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public <V> V runInTransaction(@NonNull Callable<V> callable) {
        beginTransaction();
        try {
            try {
                V call = callable.call();
                setTransactionSuccessful();
                return call;
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                throw new RuntimeException("Exception in transaction", e2);
            }
        } finally {
            endTransaction();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void internalInitInvalidationTracker(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
        this.mInvalidationTracker.internalInit(supportSQLiteDatabase);
    }

    @NonNull
    public InvalidationTracker getInvalidationTracker() {
        return this.mInvalidationTracker;
    }

    public boolean inTransaction() {
        return this.mOpenHelper.getWritableDatabase().inTransaction();
    }

    /* loaded from: classes.dex */
    public enum JournalMode {
        AUTOMATIC,
        TRUNCATE,
        WRITE_AHEAD_LOGGING;

        @SuppressLint({"NewApi"})
        JournalMode resolve(Context context) {
            ActivityManager activityManager;
            if (this != AUTOMATIC) {
                return this;
            }
            if (Build.VERSION.SDK_INT < 16 || (activityManager = (ActivityManager) context.getSystemService(ServiceManagerNative.ACTIVITY)) == null || ActivityManagerCompat.isLowRamDevice(activityManager)) {
                return TRUNCATE;
            }
            return WRITE_AHEAD_LOGGING;
        }
    }

    /* loaded from: classes.dex */
    public static class Builder<T extends RoomDatabase> {
        private boolean mAllowMainThreadQueries;
        private ArrayList<Callback> mCallbacks;
        private final Context mContext;
        private final Class<T> mDatabaseClass;
        private SupportSQLiteOpenHelper.Factory mFactory;
        private Set<Integer> mMigrationStartAndEndVersions;
        private Set<Integer> mMigrationsNotRequiredFrom;
        private final String mName;
        private JournalMode mJournalMode = JournalMode.AUTOMATIC;
        private boolean mRequireMigration = true;
        private final MigrationContainer mMigrationContainer = new MigrationContainer();

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder(@NonNull Context context, @NonNull Class<T> cls, @Nullable String str) {
            this.mContext = context;
            this.mDatabaseClass = cls;
            this.mName = str;
        }

        @NonNull
        public Builder<T> openHelperFactory(@Nullable SupportSQLiteOpenHelper.Factory factory) {
            this.mFactory = factory;
            return this;
        }

        @NonNull
        public Builder<T> addMigrations(@NonNull Migration... migrationArr) {
            if (this.mMigrationStartAndEndVersions == null) {
                this.mMigrationStartAndEndVersions = new HashSet();
            }
            for (Migration migration : migrationArr) {
                this.mMigrationStartAndEndVersions.add(Integer.valueOf(migration.startVersion));
                this.mMigrationStartAndEndVersions.add(Integer.valueOf(migration.endVersion));
            }
            this.mMigrationContainer.addMigrations(migrationArr);
            return this;
        }

        @NonNull
        public Builder<T> allowMainThreadQueries() {
            this.mAllowMainThreadQueries = true;
            return this;
        }

        @NonNull
        public Builder<T> setJournalMode(@NonNull JournalMode journalMode) {
            this.mJournalMode = journalMode;
            return this;
        }

        @NonNull
        public Builder<T> fallbackToDestructiveMigration() {
            this.mRequireMigration = false;
            return this;
        }

        @NonNull
        public Builder<T> fallbackToDestructiveMigrationFrom(int... iArr) {
            if (this.mMigrationsNotRequiredFrom == null) {
                this.mMigrationsNotRequiredFrom = new HashSet(iArr.length);
            }
            for (int i : iArr) {
                this.mMigrationsNotRequiredFrom.add(Integer.valueOf(i));
            }
            return this;
        }

        @NonNull
        public Builder<T> addCallback(@NonNull Callback callback) {
            if (this.mCallbacks == null) {
                this.mCallbacks = new ArrayList<>();
            }
            this.mCallbacks.add(callback);
            return this;
        }

        @NonNull
        public T build() {
            if (this.mContext == null) {
                throw new IllegalArgumentException("Cannot provide null context for the database.");
            } else if (this.mDatabaseClass != null) {
                Set<Integer> set = this.mMigrationStartAndEndVersions;
                if (!(set == null || this.mMigrationsNotRequiredFrom == null)) {
                    for (Integer num : set) {
                        if (this.mMigrationsNotRequiredFrom.contains(num)) {
                            throw new IllegalArgumentException("Inconsistency detected. A Migration was supplied to addMigration(Migration... migrations) that has a start or end version equal to a start version supplied to fallbackToDestructiveMigrationFrom(int... startVersions). Start version: " + num);
                        }
                    }
                }
                if (this.mFactory == null) {
                    this.mFactory = new FrameworkSQLiteOpenHelperFactory();
                }
                Context context = this.mContext;
                DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration(context, this.mName, this.mFactory, this.mMigrationContainer, this.mCallbacks, this.mAllowMainThreadQueries, this.mJournalMode.resolve(context), this.mRequireMigration, this.mMigrationsNotRequiredFrom);
                T t = (T) ((RoomDatabase) Room.getGeneratedImplementation(this.mDatabaseClass, RoomDatabase.DB_IMPL_SUFFIX));
                t.init(databaseConfiguration);
                return t;
            } else {
                throw new IllegalArgumentException("Must provide an abstract class that extends RoomDatabase");
            }
        }
    }

    /* loaded from: classes.dex */
    public static class MigrationContainer {
        private SparseArrayCompat<SparseArrayCompat<Migration>> mMigrations = new SparseArrayCompat<>();

        public void addMigrations(@NonNull Migration... migrationArr) {
            for (Migration migration : migrationArr) {
                addMigration(migration);
            }
        }

        private void addMigration(Migration migration) {
            int i = migration.startVersion;
            int i2 = migration.endVersion;
            SparseArrayCompat<Migration> sparseArrayCompat = this.mMigrations.get(i);
            if (sparseArrayCompat == null) {
                sparseArrayCompat = new SparseArrayCompat<>();
                this.mMigrations.put(i, sparseArrayCompat);
            }
            Migration migration2 = sparseArrayCompat.get(i2);
            if (migration2 != null) {
                Log.w("ROOM", "Overriding migration " + migration2 + " with " + migration);
            }
            sparseArrayCompat.append(i2, migration);
        }

        @Nullable
        public List<Migration> findMigrationPath(int i, int i2) {
            if (i == i2) {
                return Collections.emptyList();
            }
            return findUpMigrationPath(new ArrayList(), i2 > i, i, i2);
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x001a  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0019 A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private java.util.List<android.arch.persistence.room.migration.Migration> findUpMigrationPath(java.util.List<android.arch.persistence.room.migration.Migration> r11, boolean r12, int r13, int r14) {
            /*
                r10 = this;
                r0 = -1
                r1 = 1
                if (r12 == 0) goto L_0x0006
                r2 = -1
                goto L_0x0007
            L_0x0006:
                r2 = 1
            L_0x0007:
                if (r12 == 0) goto L_0x000c
                if (r13 >= r14) goto L_0x004f
                goto L_0x000e
            L_0x000c:
                if (r13 <= r14) goto L_0x004f
            L_0x000e:
                android.support.v4.util.SparseArrayCompat<android.support.v4.util.SparseArrayCompat<android.arch.persistence.room.migration.Migration>> r3 = r10.mMigrations
                java.lang.Object r3 = r3.get(r13)
                android.support.v4.util.SparseArrayCompat r3 = (android.support.p003v4.util.SparseArrayCompat) r3
                r4 = 0
                if (r3 != 0) goto L_0x001a
                return r4
            L_0x001a:
                int r5 = r3.size()
                r6 = 0
                if (r12 == 0) goto L_0x0025
                int r5 = r5 + (-1)
                r7 = -1
                goto L_0x0027
            L_0x0025:
                r7 = r5
                r5 = 0
            L_0x0027:
                if (r5 == r7) goto L_0x004c
                int r8 = r3.keyAt(r5)
                if (r12 == 0) goto L_0x0037
                if (r8 > r14) goto L_0x0035
                if (r8 <= r13) goto L_0x0035
                r9 = 1
                goto L_0x003e
            L_0x0035:
                r9 = 0
                goto L_0x003e
            L_0x0037:
                if (r8 < r14) goto L_0x003d
                if (r8 >= r13) goto L_0x003d
                r9 = 1
                goto L_0x003e
            L_0x003d:
                r9 = 0
            L_0x003e:
                if (r9 == 0) goto L_0x004a
                java.lang.Object r13 = r3.valueAt(r5)
                r11.add(r13)
                r13 = r8
                r6 = 1
                goto L_0x004c
            L_0x004a:
                int r5 = r5 + r2
                goto L_0x0027
            L_0x004c:
                if (r6 != 0) goto L_0x0007
                return r4
            L_0x004f:
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: android.arch.persistence.room.RoomDatabase.MigrationContainer.findUpMigrationPath(java.util.List, boolean, int, int):java.util.List");
        }
    }
}
