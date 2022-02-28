package com.lody.virtual.server.content;

import android.accounts.Account;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ISyncStatusObserver;
import android.content.PeriodicSync;
import android.content.SyncStatusInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.Process;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.lody.virtual.helper.utils.AtomicFile;
import com.lody.virtual.helper.utils.FastXmlSerializer;
import com.lody.virtual.os.VEnvironment;
import com.lody.virtual.server.accounts.AccountAndUser;
import com.lody.virtual.server.content.SyncManager;
import com.tendcloud.tenddata.C2986is;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import org.apache.commons.mail.EmailConstants;
import org.apache.tools.ant.taskdefs.WaitFor;
import org.apache.tools.ant.types.selectors.SizeSelector;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

/* loaded from: classes.dex */
public class SyncStorageEngine extends Handler {
    private static final int ACCOUNTS_VERSION = 2;
    private static final boolean DEBUG = false;
    private static final double DEFAULT_FLEX_PERCENT_SYNC = 0.04d;
    private static final long DEFAULT_MIN_FLEX_ALLOWED_SECS = 5;
    private static final long DEFAULT_POLL_FREQUENCY_SECONDS = 86400;
    public static final int EVENT_START = 0;
    public static final int EVENT_STOP = 1;
    public static final int MAX_HISTORY = 100;
    public static final String MESG_CANCELED = "canceled";
    public static final String MESG_SUCCESS = "success";
    private static final int MSG_WRITE_STATISTICS = 2;
    private static final int MSG_WRITE_STATUS = 1;
    public static final long NOT_IN_BACKOFF_MODE = -1;
    private static final int PENDING_FINISH_TO_WRITE = 4;
    public static final int PENDING_OPERATION_VERSION = 3;
    public static final int SOURCE_LOCAL = 1;
    public static final int SOURCE_PERIODIC = 4;
    public static final int SOURCE_POLL = 2;
    public static final int SOURCE_SERVER = 0;
    public static final int SOURCE_USER = 3;
    public static final int STATISTICS_FILE_END = 0;
    public static final int STATISTICS_FILE_ITEM = 101;
    public static final int STATISTICS_FILE_ITEM_OLD = 100;
    public static final int STATUS_FILE_END = 0;
    public static final int STATUS_FILE_ITEM = 100;
    private static final boolean SYNC_ENABLED_DEFAULT = false;
    private static final String TAG = "SyncManager";
    private static final String TAG_FILE = "SyncManagerFile";
    private static final long WRITE_STATISTICS_DELAY = 1800000;
    private static final long WRITE_STATUS_DELAY = 600000;
    private static final String XML_ATTR_AUTHORITYID = "authority_id";
    private static final String XML_ATTR_ENABLED = "enabled";
    private static final String XML_ATTR_EXPEDITED = "expedited";
    private static final String XML_ATTR_LISTEN_FOR_TICKLES = "listen-for-tickles";
    private static final String XML_ATTR_NEXT_AUTHORITY_ID = "nextAuthorityId";
    private static final String XML_ATTR_REASON = "reason";
    private static final String XML_ATTR_SOURCE = "source";
    private static final String XML_ATTR_SYNC_RANDOM_OFFSET = "offsetInSeconds";
    private static final String XML_ATTR_USER = "user";
    private static final String XML_ATTR_VERSION = "version";
    private static final String XML_TAG_LISTEN_FOR_TICKLES = "listenForTickles";
    private final AtomicFile mAccountInfoFile;
    private final Context mContext;
    private final AtomicFile mPendingFile;
    private final AtomicFile mStatisticsFile;
    private final AtomicFile mStatusFile;
    private int mSyncRandomOffset;
    private OnSyncRequestListener mSyncRequestListener;
    private int mYear;
    private int mYearInDays;
    public static final String[] EVENTS = {"START", "STOP"};
    public static final String[] SOURCES = {"SERVER", "LOCAL", "POLL", "USER", "PERIODIC"};
    private static HashMap<String, String> sAuthorityRenames = new HashMap<>();
    private static volatile SyncStorageEngine sSyncStorageEngine = null;
    private final SparseArray<AuthorityInfo> mAuthorities = new SparseArray<>();
    private final HashMap<AccountAndUser, AccountInfo> mAccounts = new HashMap<>();
    private final ArrayList<PendingOperation> mPendingOperations = new ArrayList<>();
    private final SparseArray<ArrayList<VSyncInfo>> mCurrentSyncs = new SparseArray<>();
    private final SparseArray<SyncStatusInfo> mSyncStatus = new SparseArray<>();
    private final ArrayList<SyncHistoryItem> mSyncHistory = new ArrayList<>();
    private final RemoteCallbackList<ISyncStatusObserver> mChangeListeners = new RemoteCallbackList<>();
    private final HashMap<ComponentName, SparseArray<AuthorityInfo>> mServices = new HashMap<>();
    private int mNextAuthorityId = 0;
    private final DayStats[] mDayStats = new DayStats[28];
    private int mNumPendingFinished = 0;
    private int mNextHistoryId = 0;
    private SparseArray<Boolean> mMasterSyncAutomatically = new SparseArray<>();
    private final Calendar mCal = Calendar.getInstance(TimeZone.getTimeZone("GMT+0"));
    private boolean mDefaultMasterSyncAutomatically = false;

    /* loaded from: classes.dex */
    public interface OnSyncRequestListener {
        void onSyncRequest(Account account, int i, int i2, String str, Bundle bundle);
    }

    /* loaded from: classes.dex */
    public static class SyncHistoryItem {
        int authorityId;
        long downstreamActivity;
        long elapsedTime;
        int event;
        long eventTime;
        Bundle extras;
        int historyId;
        boolean initialization;
        String mesg;
        int reason;
        int source;
        long upstreamActivity;
    }

    public static long calculateDefaultFlexTime(long j) {
        if (j < 5) {
            return 0L;
        }
        if (j < 86400) {
            return (long) (j * DEFAULT_FLEX_PERCENT_SYNC);
        }
        return 3456L;
    }

    static {
        sAuthorityRenames.put("contacts", "com.android.contacts");
        sAuthorityRenames.put("calendar", "com.android.calendar");
    }

    /* loaded from: classes.dex */
    public static class PendingOperation {
        final Account account;
        final String authority;
        int authorityId;
        final boolean expedited;
        final Bundle extras;
        byte[] flatExtras;
        final int reason;
        final ComponentName serviceName;
        final int syncSource;
        final int userId;

        /* JADX INFO: Access modifiers changed from: package-private */
        public PendingOperation(Account account, int i, int i2, int i3, String str, Bundle bundle, boolean z) {
            this.account = account;
            this.userId = i;
            this.syncSource = i3;
            this.reason = i2;
            this.authority = str;
            this.extras = bundle != null ? new Bundle(bundle) : bundle;
            this.expedited = z;
            this.authorityId = -1;
            this.serviceName = null;
        }

        PendingOperation(PendingOperation pendingOperation) {
            this.account = pendingOperation.account;
            this.userId = pendingOperation.userId;
            this.reason = pendingOperation.reason;
            this.syncSource = pendingOperation.syncSource;
            this.authority = pendingOperation.authority;
            this.extras = pendingOperation.extras;
            this.authorityId = pendingOperation.authorityId;
            this.expedited = pendingOperation.expedited;
            this.serviceName = pendingOperation.serviceName;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class AccountInfo {
        final AccountAndUser accountAndUser;
        final HashMap<String, AuthorityInfo> authorities = new HashMap<>();

        AccountInfo(AccountAndUser accountAndUser) {
            this.accountAndUser = accountAndUser;
        }
    }

    /* loaded from: classes.dex */
    public static class AuthorityInfo {
        final Account account;
        final String authority;
        long backoffDelay;
        long backoffTime;
        long delayUntil;
        boolean enabled;
        final int ident;
        final ArrayList<PeriodicSync> periodicSyncs;
        final ComponentName service;
        int syncable;
        final int userId;

        AuthorityInfo(AuthorityInfo authorityInfo) {
            this.account = authorityInfo.account;
            this.userId = authorityInfo.userId;
            this.authority = authorityInfo.authority;
            this.service = authorityInfo.service;
            this.ident = authorityInfo.ident;
            this.enabled = authorityInfo.enabled;
            this.syncable = authorityInfo.syncable;
            this.backoffTime = authorityInfo.backoffTime;
            this.backoffDelay = authorityInfo.backoffDelay;
            this.delayUntil = authorityInfo.delayUntil;
            this.periodicSyncs = new ArrayList<>();
            Iterator<PeriodicSync> it = authorityInfo.periodicSyncs.iterator();
            while (it.hasNext()) {
                this.periodicSyncs.add(p110z1.PeriodicSync.clone(it.next()));
            }
        }

        AuthorityInfo(Account account, int i, String str, int i2) {
            this.account = account;
            this.userId = i;
            this.authority = str;
            this.service = null;
            this.ident = i2;
            this.enabled = false;
            this.syncable = -1;
            this.backoffTime = -1L;
            this.backoffDelay = -1L;
            this.periodicSyncs = new ArrayList<>();
            PeriodicSync periodicSync = new PeriodicSync(account, str, new Bundle(), 86400L);
            p110z1.PeriodicSync.flexTime.set(periodicSync, SyncStorageEngine.calculateDefaultFlexTime(86400L));
            this.periodicSyncs.add(periodicSync);
        }

        AuthorityInfo(ComponentName componentName, int i, int i2) {
            this.account = null;
            this.userId = i;
            this.authority = null;
            this.service = componentName;
            this.ident = i2;
            this.enabled = true;
            this.syncable = -1;
            this.backoffTime = -1L;
            this.backoffDelay = -1L;
            this.periodicSyncs = new ArrayList<>();
            PeriodicSync periodicSync = new PeriodicSync(this.account, this.authority, new Bundle(), 86400L);
            p110z1.PeriodicSync.flexTime.set(periodicSync, SyncStorageEngine.calculateDefaultFlexTime(86400L));
            this.periodicSyncs.add(periodicSync);
        }
    }

    /* loaded from: classes.dex */
    public static class DayStats {
        public final int day;
        public int failureCount;
        public long failureTime;
        public int successCount;
        public long successTime;

        public DayStats(int i) {
            this.day = i;
        }
    }

    private SyncStorageEngine(Context context, File file) {
        this.mContext = context;
        sSyncStorageEngine = this;
        maybeDeleteLegacyPendingInfoLocked(file);
        this.mAccountInfoFile = new AtomicFile(new File(file, "accounts.xml"));
        this.mStatusFile = new AtomicFile(new File(file, "status.bin"));
        this.mPendingFile = new AtomicFile(new File(file, "pending.xml"));
        this.mStatisticsFile = new AtomicFile(new File(file, "stats.bin"));
        readAccountInfoLocked();
        readStatusLocked();
        readPendingOperationsLocked();
        readStatisticsLocked();
        readAndDeleteLegacyAccountInfoLocked();
        writeAccountInfoLocked();
        writeStatusLocked();
        writePendingOperationsLocked();
        writeStatisticsLocked();
    }

    public static void init(Context context) {
        if (sSyncStorageEngine == null) {
            sSyncStorageEngine = new SyncStorageEngine(context, VEnvironment.getSyncDirectory());
        }
    }

    public static SyncStorageEngine getSingleton() {
        if (sSyncStorageEngine != null) {
            return sSyncStorageEngine;
        }
        throw new IllegalStateException("not initialized");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setOnSyncRequestListener(OnSyncRequestListener onSyncRequestListener) {
        if (this.mSyncRequestListener == null) {
            this.mSyncRequestListener = onSyncRequestListener;
        }
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (message.what == 1) {
            synchronized (this.mAuthorities) {
                writeStatusLocked();
            }
        } else if (message.what == 2) {
            synchronized (this.mAuthorities) {
                writeStatisticsLocked();
            }
        }
    }

    public int getSyncRandomOffset() {
        return this.mSyncRandomOffset;
    }

    public void addStatusChangeListener(int i, ISyncStatusObserver iSyncStatusObserver) {
        synchronized (this.mAuthorities) {
            this.mChangeListeners.register(iSyncStatusObserver, Integer.valueOf(i));
        }
    }

    public void removeStatusChangeListener(ISyncStatusObserver iSyncStatusObserver) {
        synchronized (this.mAuthorities) {
            this.mChangeListeners.unregister(iSyncStatusObserver);
        }
    }

    private void reportChange(int i) {
        ArrayList arrayList;
        synchronized (this.mAuthorities) {
            int beginBroadcast = this.mChangeListeners.beginBroadcast();
            arrayList = null;
            while (beginBroadcast > 0) {
                beginBroadcast--;
                if ((((Integer) this.mChangeListeners.getBroadcastCookie(beginBroadcast)).intValue() & i) != 0) {
                    if (arrayList == null) {
                        arrayList = new ArrayList(beginBroadcast);
                    }
                    arrayList.add(this.mChangeListeners.getBroadcastItem(beginBroadcast));
                }
            }
            this.mChangeListeners.finishBroadcast();
        }
        if (arrayList != null) {
            int size = arrayList.size();
            while (size > 0) {
                size--;
                try {
                    ((ISyncStatusObserver) arrayList.get(size)).onStatusChanged(i);
                } catch (RemoteException unused) {
                }
            }
        }
    }

    public boolean getSyncAutomatically(Account account, int i, String str) {
        synchronized (this.mAuthorities) {
            boolean z = true;
            if (account != null) {
                AuthorityInfo authorityLocked = getAuthorityLocked(account, i, str, "getSyncAutomatically");
                if (authorityLocked == null || !authorityLocked.enabled) {
                    z = false;
                }
                return z;
            }
            int size = this.mAuthorities.size();
            while (size > 0) {
                size--;
                AuthorityInfo valueAt = this.mAuthorities.valueAt(size);
                if (valueAt.authority.equals(str) && valueAt.userId == i && valueAt.enabled) {
                    return true;
                }
            }
            return false;
        }
    }

    public void setSyncAutomatically(Account account, int i, String str, boolean z) {
        synchronized (this.mAuthorities) {
            AuthorityInfo orCreateAuthorityLocked = getOrCreateAuthorityLocked(account, i, str, -1, false);
            if (orCreateAuthorityLocked.enabled != z) {
                orCreateAuthorityLocked.enabled = z;
                writeAccountInfoLocked();
                if (z) {
                    requestSync(account, i, -6, str, new Bundle());
                }
                reportChange(1);
            }
        }
    }

    public int getIsSyncable(Account account, int i, String str) {
        synchronized (this.mAuthorities) {
            if (account != null) {
                AuthorityInfo authorityLocked = getAuthorityLocked(account, i, str, "getIsSyncable");
                if (authorityLocked == null) {
                    return -1;
                }
                return authorityLocked.syncable;
            }
            int size = this.mAuthorities.size();
            while (size > 0) {
                size--;
                AuthorityInfo valueAt = this.mAuthorities.valueAt(size);
                if (valueAt.authority.equals(str)) {
                    return valueAt.syncable;
                }
            }
            return -1;
        }
    }

    public void setIsSyncable(Account account, int i, String str, int i2) {
        if (i2 > 1) {
            i2 = 1;
        } else if (i2 < -1) {
            i2 = -1;
        }
        synchronized (this.mAuthorities) {
            AuthorityInfo orCreateAuthorityLocked = getOrCreateAuthorityLocked(account, i, str, -1, false);
            if (orCreateAuthorityLocked.syncable != i2) {
                orCreateAuthorityLocked.syncable = i2;
                writeAccountInfoLocked();
                if (i2 > 0) {
                    requestSync(account, i, -5, str, new Bundle());
                }
                reportChange(1);
            }
        }
    }

    public Pair<Long, Long> getBackoff(Account account, int i, String str) {
        synchronized (this.mAuthorities) {
            AuthorityInfo authorityLocked = getAuthorityLocked(account, i, str, "getBackoff");
            if (authorityLocked != null && authorityLocked.backoffTime >= 0) {
                return Pair.create(Long.valueOf(authorityLocked.backoffTime), Long.valueOf(authorityLocked.backoffDelay));
            }
            return null;
        }
    }

    public void setBackoff(Account account, int i, String str, long j, long j2) {
        boolean z;
        synchronized (this.mAuthorities) {
            if (account == null || str == null) {
                z = false;
                for (AccountInfo accountInfo : this.mAccounts.values()) {
                    if (account == null || account.equals(accountInfo.accountAndUser.account) || i == accountInfo.accountAndUser.userId) {
                        for (AuthorityInfo authorityInfo : accountInfo.authorities.values()) {
                            if (str == null || str.equals(authorityInfo.authority)) {
                                if (authorityInfo.backoffTime != j || authorityInfo.backoffDelay != j2) {
                                    authorityInfo.backoffTime = j;
                                    authorityInfo.backoffDelay = j2;
                                    z = true;
                                }
                            }
                        }
                    }
                }
            } else {
                AuthorityInfo orCreateAuthorityLocked = getOrCreateAuthorityLocked(account, i, str, -1, true);
                if (orCreateAuthorityLocked.backoffTime != j || orCreateAuthorityLocked.backoffDelay != j2) {
                    orCreateAuthorityLocked.backoffTime = j;
                    orCreateAuthorityLocked.backoffDelay = j2;
                    z = true;
                } else {
                    return;
                }
            }
            if (z) {
                reportChange(1);
            }
        }
    }

    public void clearAllBackoffsLocked(SyncQueue syncQueue) {
        boolean z;
        synchronized (this.mAuthorities) {
            z = false;
            for (AccountInfo accountInfo : this.mAccounts.values()) {
                for (AuthorityInfo authorityInfo : accountInfo.authorities.values()) {
                    if (authorityInfo.backoffTime != -1 || authorityInfo.backoffDelay != -1) {
                        authorityInfo.backoffTime = -1L;
                        authorityInfo.backoffDelay = -1L;
                        syncQueue.onBackoffChanged(accountInfo.accountAndUser.account, accountInfo.accountAndUser.userId, authorityInfo.authority, 0L);
                        z = true;
                    }
                }
            }
        }
        if (z) {
            reportChange(1);
        }
    }

    public void setDelayUntilTime(Account account, int i, String str, long j) {
        synchronized (this.mAuthorities) {
            AuthorityInfo orCreateAuthorityLocked = getOrCreateAuthorityLocked(account, i, str, -1, true);
            if (orCreateAuthorityLocked.delayUntil != j) {
                orCreateAuthorityLocked.delayUntil = j;
                reportChange(1);
            }
        }
    }

    public long getDelayUntilTime(Account account, int i, String str) {
        synchronized (this.mAuthorities) {
            AuthorityInfo authorityLocked = getAuthorityLocked(account, i, str, "getDelayUntil");
            if (authorityLocked == null) {
                return 0L;
            }
            return authorityLocked.delayUntil;
        }
    }

    private void updateOrRemovePeriodicSync(PeriodicSync periodicSync, int i, boolean z) {
        synchronized (this.mAuthorities) {
            if (periodicSync.period <= 0 && z) {
                Log.e(TAG, "period < 0, should never happen in updateOrRemovePeriodicSync: add-" + z);
            }
            if (periodicSync.extras == null) {
                Log.e(TAG, "null extras, should never happen in updateOrRemovePeriodicSync: add-" + z);
            }
            AuthorityInfo orCreateAuthorityLocked = getOrCreateAuthorityLocked(periodicSync.account, i, periodicSync.authority, -1, false);
            boolean z2 = false;
            if (z) {
                int size = orCreateAuthorityLocked.periodicSyncs.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    }
                    PeriodicSync periodicSync2 = orCreateAuthorityLocked.periodicSyncs.get(i2);
                    if (!p110z1.PeriodicSync.syncExtrasEquals(periodicSync.extras, periodicSync2.extras)) {
                        i2++;
                    } else if (periodicSync.period == periodicSync2.period && p110z1.PeriodicSync.flexTime.get(periodicSync) == p110z1.PeriodicSync.flexTime.get(periodicSync2)) {
                        writeAccountInfoLocked();
                        writeStatusLocked();
                        return;
                    } else {
                        orCreateAuthorityLocked.periodicSyncs.set(i2, p110z1.PeriodicSync.clone(periodicSync));
                        z2 = true;
                    }
                }
                if (!z2) {
                    orCreateAuthorityLocked.periodicSyncs.add(p110z1.PeriodicSync.clone(periodicSync));
                    getOrCreateSyncStatusLocked(orCreateAuthorityLocked.ident).setPeriodicSyncTime(orCreateAuthorityLocked.periodicSyncs.size() - 1, 0L);
                }
            } else {
                SyncStatusInfo syncStatusInfo = this.mSyncStatus.get(orCreateAuthorityLocked.ident);
                Iterator<PeriodicSync> it = orCreateAuthorityLocked.periodicSyncs.iterator();
                int i3 = 0;
                while (it.hasNext()) {
                    if (p110z1.PeriodicSync.syncExtrasEquals(it.next().extras, periodicSync.extras)) {
                        it.remove();
                        if (syncStatusInfo != null) {
                            syncStatusInfo.removePeriodicSyncTime(i3);
                        } else {
                            Log.e(TAG, "Tried removing sync status on remove periodic sync butdid not find it.");
                        }
                        z2 = true;
                    } else {
                        i3++;
                    }
                }
                if (!z2) {
                    writeAccountInfoLocked();
                    writeStatusLocked();
                    return;
                }
            }
            writeAccountInfoLocked();
            writeStatusLocked();
            reportChange(1);
        }
    }

    public void addPeriodicSync(PeriodicSync periodicSync, int i) {
        updateOrRemovePeriodicSync(periodicSync, i, true);
    }

    public void removePeriodicSync(PeriodicSync periodicSync, int i) {
        updateOrRemovePeriodicSync(periodicSync, i, false);
    }

    public List<PeriodicSync> getPeriodicSyncs(Account account, int i, String str) {
        ArrayList arrayList = new ArrayList();
        synchronized (this.mAuthorities) {
            AuthorityInfo authorityLocked = getAuthorityLocked(account, i, str, "getPeriodicSyncs");
            if (authorityLocked != null) {
                Iterator<PeriodicSync> it = authorityLocked.periodicSyncs.iterator();
                while (it.hasNext()) {
                    arrayList.add(p110z1.PeriodicSync.clone(it.next()));
                }
            }
        }
        return arrayList;
    }

    public void setMasterSyncAutomatically(boolean z, int i) {
        synchronized (this.mAuthorities) {
            Boolean bool = this.mMasterSyncAutomatically.get(i);
            if (bool == null || bool.booleanValue() != z) {
                this.mMasterSyncAutomatically.put(i, Boolean.valueOf(z));
                writeAccountInfoLocked();
                if (z) {
                    requestSync(null, i, -7, null, new Bundle());
                }
                reportChange(1);
            }
        }
    }

    public boolean getMasterSyncAutomatically(int i) {
        boolean booleanValue;
        synchronized (this.mAuthorities) {
            Boolean bool = this.mMasterSyncAutomatically.get(i);
            booleanValue = bool == null ? this.mDefaultMasterSyncAutomatically : bool.booleanValue();
        }
        return booleanValue;
    }

    public void removeAuthority(Account account, int i, String str) {
        synchronized (this.mAuthorities) {
            removeAuthorityLocked(account, i, str, true);
        }
    }

    public AuthorityInfo getAuthority(int i) {
        AuthorityInfo authorityInfo;
        synchronized (this.mAuthorities) {
            authorityInfo = this.mAuthorities.get(i);
        }
        return authorityInfo;
    }

    public boolean isSyncActive(Account account, int i, String str) {
        synchronized (this.mAuthorities) {
            for (VSyncInfo vSyncInfo : getCurrentSyncs(i)) {
                AuthorityInfo authority = getAuthority(vSyncInfo.authorityId);
                if (authority != null && authority.account.equals(account) && authority.authority.equals(str) && authority.userId == i) {
                    return true;
                }
            }
            return false;
        }
    }

    public PendingOperation insertIntoPending(PendingOperation pendingOperation) {
        synchronized (this.mAuthorities) {
            AuthorityInfo orCreateAuthorityLocked = getOrCreateAuthorityLocked(pendingOperation.account, pendingOperation.userId, pendingOperation.authority, -1, true);
            if (orCreateAuthorityLocked == null) {
                return null;
            }
            PendingOperation pendingOperation2 = new PendingOperation(pendingOperation);
            pendingOperation2.authorityId = orCreateAuthorityLocked.ident;
            this.mPendingOperations.add(pendingOperation2);
            appendPendingOperationLocked(pendingOperation2);
            getOrCreateSyncStatusLocked(orCreateAuthorityLocked.ident).pending = true;
            reportChange(2);
            return pendingOperation2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0034 A[Catch: all -> 0x0077, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x000d, B:8:0x0015, B:11:0x001b, B:12:0x0021, B:13:0x0026, B:15:0x0034, B:17:0x003d, B:19:0x004f, B:21:0x0059, B:24:0x0061, B:27:0x0067, B:29:0x0071), top: B:35:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean deleteFromPending(com.lody.virtual.server.content.SyncStorageEngine.PendingOperation r10) {
        /*
            r9 = this;
            android.util.SparseArray<com.lody.virtual.server.content.SyncStorageEngine$AuthorityInfo> r0 = r9.mAuthorities
            monitor-enter(r0)
            java.util.ArrayList<com.lody.virtual.server.content.SyncStorageEngine$PendingOperation> r1 = r9.mPendingOperations     // Catch: all -> 0x0077
            boolean r1 = r1.remove(r10)     // Catch: all -> 0x0077
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x0070
            java.util.ArrayList<com.lody.virtual.server.content.SyncStorageEngine$PendingOperation> r1 = r9.mPendingOperations     // Catch: all -> 0x0077
            int r1 = r1.size()     // Catch: all -> 0x0077
            if (r1 == 0) goto L_0x0021
            int r1 = r9.mNumPendingFinished     // Catch: all -> 0x0077
            r4 = 4
            if (r1 < r4) goto L_0x001b
            goto L_0x0021
        L_0x001b:
            int r1 = r9.mNumPendingFinished     // Catch: all -> 0x0077
            int r1 = r1 + r2
            r9.mNumPendingFinished = r1     // Catch: all -> 0x0077
            goto L_0x0026
        L_0x0021:
            r9.writePendingOperationsLocked()     // Catch: all -> 0x0077
            r9.mNumPendingFinished = r3     // Catch: all -> 0x0077
        L_0x0026:
            android.accounts.Account r1 = r10.account     // Catch: all -> 0x0077
            int r4 = r10.userId     // Catch: all -> 0x0077
            java.lang.String r5 = r10.authority     // Catch: all -> 0x0077
            java.lang.String r6 = "deleteFromPending"
            com.lody.virtual.server.content.SyncStorageEngine$AuthorityInfo r1 = r9.getAuthorityLocked(r1, r4, r5, r6)     // Catch: all -> 0x0077
            if (r1 == 0) goto L_0x0071
            java.util.ArrayList<com.lody.virtual.server.content.SyncStorageEngine$PendingOperation> r4 = r9.mPendingOperations     // Catch: all -> 0x0077
            int r4 = r4.size()     // Catch: all -> 0x0077
            r5 = 0
        L_0x003b:
            if (r5 >= r4) goto L_0x0064
            java.util.ArrayList<com.lody.virtual.server.content.SyncStorageEngine$PendingOperation> r6 = r9.mPendingOperations     // Catch: all -> 0x0077
            java.lang.Object r6 = r6.get(r5)     // Catch: all -> 0x0077
            com.lody.virtual.server.content.SyncStorageEngine$PendingOperation r6 = (com.lody.virtual.server.content.SyncStorageEngine.PendingOperation) r6     // Catch: all -> 0x0077
            android.accounts.Account r7 = r6.account     // Catch: all -> 0x0077
            android.accounts.Account r8 = r10.account     // Catch: all -> 0x0077
            boolean r7 = r7.equals(r8)     // Catch: all -> 0x0077
            if (r7 == 0) goto L_0x0061
            java.lang.String r7 = r6.authority     // Catch: all -> 0x0077
            java.lang.String r8 = r10.authority     // Catch: all -> 0x0077
            boolean r7 = r7.equals(r8)     // Catch: all -> 0x0077
            if (r7 == 0) goto L_0x0061
            int r6 = r6.userId     // Catch: all -> 0x0077
            int r7 = r10.userId     // Catch: all -> 0x0077
            if (r6 != r7) goto L_0x0061
            r10 = 1
            goto L_0x0065
        L_0x0061:
            int r5 = r5 + 1
            goto L_0x003b
        L_0x0064:
            r10 = 0
        L_0x0065:
            if (r10 != 0) goto L_0x0071
            int r10 = r1.ident     // Catch: all -> 0x0077
            android.content.SyncStatusInfo r10 = r9.getOrCreateSyncStatusLocked(r10)     // Catch: all -> 0x0077
            r10.pending = r3     // Catch: all -> 0x0077
            goto L_0x0071
        L_0x0070:
            r2 = 0
        L_0x0071:
            monitor-exit(r0)     // Catch: all -> 0x0077
            r10 = 2
            r9.reportChange(r10)
            return r2
        L_0x0077:
            r10 = move-exception
            monitor-exit(r0)     // Catch: all -> 0x0077
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lody.virtual.server.content.SyncStorageEngine.deleteFromPending(com.lody.virtual.server.content.SyncStorageEngine$PendingOperation):boolean");
    }

    public ArrayList<PendingOperation> getPendingOperations() {
        ArrayList<PendingOperation> arrayList;
        synchronized (this.mAuthorities) {
            arrayList = new ArrayList<>(this.mPendingOperations);
        }
        return arrayList;
    }

    public int getPendingOperationCount() {
        int size;
        synchronized (this.mAuthorities) {
            size = this.mPendingOperations.size();
        }
        return size;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0052, code lost:
        if (r7 > 0) goto L_0x0054;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0054, code lost:
        if (r7 <= 0) goto L_0x009b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0056, code lost:
        r7 = r7 - 1;
        r8 = r1.keyAt(r7);
        r6.mAuthorities.remove(r8);
        r2 = r6.mSyncStatus.size();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0067, code lost:
        if (r2 <= 0) goto L_0x007f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0069, code lost:
        r2 = r2 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0071, code lost:
        if (r6.mSyncStatus.keyAt(r2) != r8) goto L_0x0067;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0073, code lost:
        r6.mSyncStatus.remove(r6.mSyncStatus.keyAt(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007f, code lost:
        r2 = r6.mSyncHistory.size();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0085, code lost:
        if (r2 <= 0) goto L_0x0054;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0087, code lost:
        r2 = r2 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0093, code lost:
        if (r6.mSyncHistory.get(r2).authorityId != r8) goto L_0x0085;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0095, code lost:
        r6.mSyncHistory.remove(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x009b, code lost:
        writeAccountInfoLocked();
        writeStatusLocked();
        writePendingOperationsLocked();
        writeStatisticsLocked();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void doDatabaseCleanup(android.accounts.Account[] r7, int r8) {
        /*
            r6 = this;
            android.util.SparseArray<com.lody.virtual.server.content.SyncStorageEngine$AuthorityInfo> r0 = r6.mAuthorities
            monitor-enter(r0)
            android.util.SparseArray r1 = new android.util.SparseArray     // Catch: all -> 0x00a9
            r1.<init>()     // Catch: all -> 0x00a9
            java.util.HashMap<com.lody.virtual.server.accounts.AccountAndUser, com.lody.virtual.server.content.SyncStorageEngine$AccountInfo> r2 = r6.mAccounts     // Catch: all -> 0x00a9
            java.util.Collection r2 = r2.values()     // Catch: all -> 0x00a9
            java.util.Iterator r2 = r2.iterator()     // Catch: all -> 0x00a9
        L_0x0012:
            boolean r3 = r2.hasNext()     // Catch: all -> 0x00a9
            if (r3 == 0) goto L_0x004e
            java.lang.Object r3 = r2.next()     // Catch: all -> 0x00a9
            com.lody.virtual.server.content.SyncStorageEngine$AccountInfo r3 = (com.lody.virtual.server.content.SyncStorageEngine.AccountInfo) r3     // Catch: all -> 0x00a9
            com.lody.virtual.server.accounts.AccountAndUser r4 = r3.accountAndUser     // Catch: all -> 0x00a9
            android.accounts.Account r4 = r4.account     // Catch: all -> 0x00a9
            boolean r4 = com.lody.virtual.helper.utils.ArrayUtils.contains(r7, r4)     // Catch: all -> 0x00a9
            if (r4 != 0) goto L_0x0012
            com.lody.virtual.server.accounts.AccountAndUser r4 = r3.accountAndUser     // Catch: all -> 0x00a9
            int r4 = r4.userId     // Catch: all -> 0x00a9
            if (r4 != r8) goto L_0x0012
            java.util.HashMap<java.lang.String, com.lody.virtual.server.content.SyncStorageEngine$AuthorityInfo> r3 = r3.authorities     // Catch: all -> 0x00a9
            java.util.Collection r3 = r3.values()     // Catch: all -> 0x00a9
            java.util.Iterator r3 = r3.iterator()     // Catch: all -> 0x00a9
        L_0x0038:
            boolean r4 = r3.hasNext()     // Catch: all -> 0x00a9
            if (r4 == 0) goto L_0x004a
            java.lang.Object r4 = r3.next()     // Catch: all -> 0x00a9
            com.lody.virtual.server.content.SyncStorageEngine$AuthorityInfo r4 = (com.lody.virtual.server.content.SyncStorageEngine.AuthorityInfo) r4     // Catch: all -> 0x00a9
            int r5 = r4.ident     // Catch: all -> 0x00a9
            r1.put(r5, r4)     // Catch: all -> 0x00a9
            goto L_0x0038
        L_0x004a:
            r2.remove()     // Catch: all -> 0x00a9
            goto L_0x0012
        L_0x004e:
            int r7 = r1.size()     // Catch: all -> 0x00a9
            if (r7 <= 0) goto L_0x00a7
        L_0x0054:
            if (r7 <= 0) goto L_0x009b
            int r7 = r7 + (-1)
            int r8 = r1.keyAt(r7)     // Catch: all -> 0x00a9
            android.util.SparseArray<com.lody.virtual.server.content.SyncStorageEngine$AuthorityInfo> r2 = r6.mAuthorities     // Catch: all -> 0x00a9
            r2.remove(r8)     // Catch: all -> 0x00a9
            android.util.SparseArray<android.content.SyncStatusInfo> r2 = r6.mSyncStatus     // Catch: all -> 0x00a9
            int r2 = r2.size()     // Catch: all -> 0x00a9
        L_0x0067:
            if (r2 <= 0) goto L_0x007f
            int r2 = r2 + (-1)
            android.util.SparseArray<android.content.SyncStatusInfo> r3 = r6.mSyncStatus     // Catch: all -> 0x00a9
            int r3 = r3.keyAt(r2)     // Catch: all -> 0x00a9
            if (r3 != r8) goto L_0x0067
            android.util.SparseArray<android.content.SyncStatusInfo> r3 = r6.mSyncStatus     // Catch: all -> 0x00a9
            android.util.SparseArray<android.content.SyncStatusInfo> r4 = r6.mSyncStatus     // Catch: all -> 0x00a9
            int r4 = r4.keyAt(r2)     // Catch: all -> 0x00a9
            r3.remove(r4)     // Catch: all -> 0x00a9
            goto L_0x0067
        L_0x007f:
            java.util.ArrayList<com.lody.virtual.server.content.SyncStorageEngine$SyncHistoryItem> r2 = r6.mSyncHistory     // Catch: all -> 0x00a9
            int r2 = r2.size()     // Catch: all -> 0x00a9
        L_0x0085:
            if (r2 <= 0) goto L_0x0054
            int r2 = r2 + (-1)
            java.util.ArrayList<com.lody.virtual.server.content.SyncStorageEngine$SyncHistoryItem> r3 = r6.mSyncHistory     // Catch: all -> 0x00a9
            java.lang.Object r3 = r3.get(r2)     // Catch: all -> 0x00a9
            com.lody.virtual.server.content.SyncStorageEngine$SyncHistoryItem r3 = (com.lody.virtual.server.content.SyncStorageEngine.SyncHistoryItem) r3     // Catch: all -> 0x00a9
            int r3 = r3.authorityId     // Catch: all -> 0x00a9
            if (r3 != r8) goto L_0x0085
            java.util.ArrayList<com.lody.virtual.server.content.SyncStorageEngine$SyncHistoryItem> r3 = r6.mSyncHistory     // Catch: all -> 0x00a9
            r3.remove(r2)     // Catch: all -> 0x00a9
            goto L_0x0085
        L_0x009b:
            r6.writeAccountInfoLocked()     // Catch: all -> 0x00a9
            r6.writeStatusLocked()     // Catch: all -> 0x00a9
            r6.writePendingOperationsLocked()     // Catch: all -> 0x00a9
            r6.writeStatisticsLocked()     // Catch: all -> 0x00a9
        L_0x00a7:
            monitor-exit(r0)     // Catch: all -> 0x00a9
            return
        L_0x00a9:
            r7 = move-exception
            monitor-exit(r0)     // Catch: all -> 0x00a9
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lody.virtual.server.content.SyncStorageEngine.doDatabaseCleanup(android.accounts.Account[], int):void");
    }

    public VSyncInfo addActiveSync(SyncManager.ActiveSyncContext activeSyncContext) {
        VSyncInfo vSyncInfo;
        synchronized (this.mAuthorities) {
            AuthorityInfo orCreateAuthorityLocked = getOrCreateAuthorityLocked(activeSyncContext.mSyncOperation.account, activeSyncContext.mSyncOperation.userId, activeSyncContext.mSyncOperation.authority, -1, true);
            vSyncInfo = new VSyncInfo(orCreateAuthorityLocked.ident, orCreateAuthorityLocked.account, orCreateAuthorityLocked.authority, activeSyncContext.mStartTime);
            getCurrentSyncs(orCreateAuthorityLocked.userId).add(vSyncInfo);
        }
        reportActiveChange();
        return vSyncInfo;
    }

    public void removeActiveSync(VSyncInfo vSyncInfo, int i) {
        synchronized (this.mAuthorities) {
            getCurrentSyncs(i).remove(vSyncInfo);
        }
        reportActiveChange();
    }

    public void reportActiveChange() {
        reportChange(4);
    }

    public long insertStartSyncEvent(Account account, int i, int i2, String str, long j, int i3, boolean z, Bundle bundle) {
        synchronized (this.mAuthorities) {
            AuthorityInfo authorityLocked = getAuthorityLocked(account, i, str, "insertStartSyncEvent");
            if (authorityLocked == null) {
                return -1L;
            }
            SyncHistoryItem syncHistoryItem = new SyncHistoryItem();
            syncHistoryItem.initialization = z;
            syncHistoryItem.authorityId = authorityLocked.ident;
            int i4 = this.mNextHistoryId;
            this.mNextHistoryId = i4 + 1;
            syncHistoryItem.historyId = i4;
            if (this.mNextHistoryId < 0) {
                this.mNextHistoryId = 0;
            }
            syncHistoryItem.eventTime = j;
            syncHistoryItem.source = i3;
            syncHistoryItem.reason = i2;
            syncHistoryItem.extras = bundle;
            syncHistoryItem.event = 0;
            this.mSyncHistory.add(0, syncHistoryItem);
            while (this.mSyncHistory.size() > 100) {
                this.mSyncHistory.remove(this.mSyncHistory.size() - 1);
            }
            long j2 = syncHistoryItem.historyId;
            reportChange(8);
            return j2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00cc A[Catch: all -> 0x0161, TryCatch #0 {, blocks: (B:4:0x000b, B:6:0x0013, B:11:0x0028, B:12:0x003e, B:14:0x0040, B:15:0x0061, B:17:0x0065, B:18:0x006b, B:19:0x0071, B:20:0x0077, B:21:0x007d, B:22:0x0082, B:24:0x008d, B:25:0x0097, B:27:0x009f, B:28:0x00b5, B:30:0x00ba, B:32:0x00cc, B:34:0x00d4, B:39:0x00e2, B:40:0x00fe, B:42:0x0107, B:46:0x0110, B:48:0x011e, B:49:0x0120, B:52:0x012f, B:53:0x0133, B:55:0x0139, B:57:0x0145, B:58:0x0149, B:60:0x0150, B:61:0x015a), top: B:67:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00fe A[Catch: all -> 0x0161, TryCatch #0 {, blocks: (B:4:0x000b, B:6:0x0013, B:11:0x0028, B:12:0x003e, B:14:0x0040, B:15:0x0061, B:17:0x0065, B:18:0x006b, B:19:0x0071, B:20:0x0077, B:21:0x007d, B:22:0x0082, B:24:0x008d, B:25:0x0097, B:27:0x009f, B:28:0x00b5, B:30:0x00ba, B:32:0x00cc, B:34:0x00d4, B:39:0x00e2, B:40:0x00fe, B:42:0x0107, B:46:0x0110, B:48:0x011e, B:49:0x0120, B:52:0x012f, B:53:0x0133, B:55:0x0139, B:57:0x0145, B:58:0x0149, B:60:0x0150, B:61:0x015a), top: B:67:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x012f A[Catch: all -> 0x0161, TryCatch #0 {, blocks: (B:4:0x000b, B:6:0x0013, B:11:0x0028, B:12:0x003e, B:14:0x0040, B:15:0x0061, B:17:0x0065, B:18:0x006b, B:19:0x0071, B:20:0x0077, B:21:0x007d, B:22:0x0082, B:24:0x008d, B:25:0x0097, B:27:0x009f, B:28:0x00b5, B:30:0x00ba, B:32:0x00cc, B:34:0x00d4, B:39:0x00e2, B:40:0x00fe, B:42:0x0107, B:46:0x0110, B:48:0x011e, B:49:0x0120, B:52:0x012f, B:53:0x0133, B:55:0x0139, B:57:0x0145, B:58:0x0149, B:60:0x0150, B:61:0x015a), top: B:67:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0133 A[Catch: all -> 0x0161, TryCatch #0 {, blocks: (B:4:0x000b, B:6:0x0013, B:11:0x0028, B:12:0x003e, B:14:0x0040, B:15:0x0061, B:17:0x0065, B:18:0x006b, B:19:0x0071, B:20:0x0077, B:21:0x007d, B:22:0x0082, B:24:0x008d, B:25:0x0097, B:27:0x009f, B:28:0x00b5, B:30:0x00ba, B:32:0x00cc, B:34:0x00d4, B:39:0x00e2, B:40:0x00fe, B:42:0x0107, B:46:0x0110, B:48:0x011e, B:49:0x0120, B:52:0x012f, B:53:0x0133, B:55:0x0139, B:57:0x0145, B:58:0x0149, B:60:0x0150, B:61:0x015a), top: B:67:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0145 A[Catch: all -> 0x0161, TryCatch #0 {, blocks: (B:4:0x000b, B:6:0x0013, B:11:0x0028, B:12:0x003e, B:14:0x0040, B:15:0x0061, B:17:0x0065, B:18:0x006b, B:19:0x0071, B:20:0x0077, B:21:0x007d, B:22:0x0082, B:24:0x008d, B:25:0x0097, B:27:0x009f, B:28:0x00b5, B:30:0x00ba, B:32:0x00cc, B:34:0x00d4, B:39:0x00e2, B:40:0x00fe, B:42:0x0107, B:46:0x0110, B:48:0x011e, B:49:0x0120, B:52:0x012f, B:53:0x0133, B:55:0x0139, B:57:0x0145, B:58:0x0149, B:60:0x0150, B:61:0x015a), top: B:67:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0149 A[Catch: all -> 0x0161, TryCatch #0 {, blocks: (B:4:0x000b, B:6:0x0013, B:11:0x0028, B:12:0x003e, B:14:0x0040, B:15:0x0061, B:17:0x0065, B:18:0x006b, B:19:0x0071, B:20:0x0077, B:21:0x007d, B:22:0x0082, B:24:0x008d, B:25:0x0097, B:27:0x009f, B:28:0x00b5, B:30:0x00ba, B:32:0x00cc, B:34:0x00d4, B:39:0x00e2, B:40:0x00fe, B:42:0x0107, B:46:0x0110, B:48:0x011e, B:49:0x0120, B:52:0x012f, B:53:0x0133, B:55:0x0139, B:57:0x0145, B:58:0x0149, B:60:0x0150, B:61:0x015a), top: B:67:0x000b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void stopSyncEvent(long r17, long r19, java.lang.String r21, long r22, long r24) {
        /*
            Method dump skipped, instructions count: 370
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lody.virtual.server.content.SyncStorageEngine.stopSyncEvent(long, long, java.lang.String, long, long):void");
    }

    private List<VSyncInfo> getCurrentSyncs(int i) {
        List<VSyncInfo> currentSyncsLocked;
        synchronized (this.mAuthorities) {
            currentSyncsLocked = getCurrentSyncsLocked(i);
        }
        return currentSyncsLocked;
    }

    public List<VSyncInfo> getCurrentSyncsCopy(int i) {
        ArrayList arrayList;
        synchronized (this.mAuthorities) {
            List<VSyncInfo> currentSyncsLocked = getCurrentSyncsLocked(i);
            arrayList = new ArrayList();
            for (VSyncInfo vSyncInfo : currentSyncsLocked) {
                arrayList.add(new VSyncInfo(vSyncInfo));
            }
        }
        return arrayList;
    }

    private List<VSyncInfo> getCurrentSyncsLocked(int i) {
        ArrayList<VSyncInfo> arrayList = this.mCurrentSyncs.get(i);
        if (arrayList != null) {
            return arrayList;
        }
        ArrayList<VSyncInfo> arrayList2 = new ArrayList<>();
        this.mCurrentSyncs.put(i, arrayList2);
        return arrayList2;
    }

    public ArrayList<SyncStatusInfo> getSyncStatus() {
        ArrayList<SyncStatusInfo> arrayList;
        synchronized (this.mAuthorities) {
            int size = this.mSyncStatus.size();
            arrayList = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                arrayList.add(this.mSyncStatus.valueAt(i));
            }
        }
        return arrayList;
    }

    public Pair<AuthorityInfo, SyncStatusInfo> getCopyOfAuthorityWithSyncStatus(Account account, int i, String str) {
        Pair<AuthorityInfo, SyncStatusInfo> createCopyPairOfAuthorityWithSyncStatusLocked;
        synchronized (this.mAuthorities) {
            createCopyPairOfAuthorityWithSyncStatusLocked = createCopyPairOfAuthorityWithSyncStatusLocked(getOrCreateAuthorityLocked(account, i, str, -1, true));
        }
        return createCopyPairOfAuthorityWithSyncStatusLocked;
    }

    public ArrayList<Pair<AuthorityInfo, SyncStatusInfo>> getCopyOfAllAuthoritiesWithSyncStatus() {
        ArrayList<Pair<AuthorityInfo, SyncStatusInfo>> arrayList;
        synchronized (this.mAuthorities) {
            arrayList = new ArrayList<>(this.mAuthorities.size());
            for (int i = 0; i < this.mAuthorities.size(); i++) {
                arrayList.add(createCopyPairOfAuthorityWithSyncStatusLocked(this.mAuthorities.valueAt(i)));
            }
        }
        return arrayList;
    }

    public SyncStatusInfo getStatusByAccountAndAuthority(Account account, int i, String str) {
        if (account == null || str == null) {
            return null;
        }
        synchronized (this.mAuthorities) {
            int size = this.mSyncStatus.size();
            for (int i2 = 0; i2 < size; i2++) {
                SyncStatusInfo valueAt = this.mSyncStatus.valueAt(i2);
                AuthorityInfo authorityInfo = this.mAuthorities.get(valueAt.authorityId);
                if (authorityInfo != null && authorityInfo.authority.equals(str) && authorityInfo.userId == i && account.equals(authorityInfo.account)) {
                    return valueAt;
                }
            }
            return null;
        }
    }

    public boolean isSyncPending(Account account, int i, String str) {
        synchronized (this.mAuthorities) {
            int size = this.mSyncStatus.size();
            for (int i2 = 0; i2 < size; i2++) {
                SyncStatusInfo valueAt = this.mSyncStatus.valueAt(i2);
                AuthorityInfo authorityInfo = this.mAuthorities.get(valueAt.authorityId);
                if (authorityInfo != null && i == authorityInfo.userId && ((account == null || authorityInfo.account.equals(account)) && authorityInfo.authority.equals(str) && valueAt.pending)) {
                    return true;
                }
            }
            return false;
        }
    }

    public ArrayList<SyncHistoryItem> getSyncHistory() {
        ArrayList<SyncHistoryItem> arrayList;
        synchronized (this.mAuthorities) {
            int size = this.mSyncHistory.size();
            arrayList = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                arrayList.add(this.mSyncHistory.get(i));
            }
        }
        return arrayList;
    }

    public DayStats[] getDayStatistics() {
        DayStats[] dayStatsArr;
        synchronized (this.mAuthorities) {
            dayStatsArr = new DayStats[this.mDayStats.length];
            System.arraycopy(this.mDayStats, 0, dayStatsArr, 0, dayStatsArr.length);
        }
        return dayStatsArr;
    }

    private Pair<AuthorityInfo, SyncStatusInfo> createCopyPairOfAuthorityWithSyncStatusLocked(AuthorityInfo authorityInfo) {
        return Pair.create(new AuthorityInfo(authorityInfo), new SyncStatusInfo(getOrCreateSyncStatusLocked(authorityInfo.ident)));
    }

    private int getCurrentDayLocked() {
        this.mCal.setTimeInMillis(System.currentTimeMillis());
        int i = this.mCal.get(6);
        if (this.mYear != this.mCal.get(1)) {
            this.mYear = this.mCal.get(1);
            this.mCal.clear();
            this.mCal.set(1, this.mYear);
            this.mYearInDays = (int) (this.mCal.getTimeInMillis() / WaitFor.ONE_DAY);
        }
        return i + this.mYearInDays;
    }

    private AuthorityInfo getAuthorityLocked(Account account, int i, String str, String str2) {
        AuthorityInfo authorityInfo;
        AccountInfo accountInfo = this.mAccounts.get(new AccountAndUser(account, i));
        if (accountInfo == null || (authorityInfo = accountInfo.authorities.get(str)) == null) {
            return null;
        }
        return authorityInfo;
    }

    private AuthorityInfo getAuthorityLocked(ComponentName componentName, int i, String str) {
        AuthorityInfo authorityInfo = this.mServices.get(componentName).get(i);
        if (authorityInfo == null) {
            return null;
        }
        return authorityInfo;
    }

    private AuthorityInfo getOrCreateAuthorityLocked(ComponentName componentName, int i, int i2, boolean z) {
        SparseArray<AuthorityInfo> sparseArray = this.mServices.get(componentName);
        if (sparseArray == null) {
            sparseArray = new SparseArray<>();
            this.mServices.put(componentName, sparseArray);
        }
        AuthorityInfo authorityInfo = sparseArray.get(i);
        if (authorityInfo == null) {
            if (i2 < 0) {
                i2 = this.mNextAuthorityId;
                this.mNextAuthorityId = i2 + 1;
                z = true;
            }
            authorityInfo = new AuthorityInfo(componentName, i, i2);
            sparseArray.put(i, authorityInfo);
            this.mAuthorities.put(i2, authorityInfo);
            if (z) {
                writeAccountInfoLocked();
            }
        }
        return authorityInfo;
    }

    private AuthorityInfo getOrCreateAuthorityLocked(Account account, int i, String str, int i2, boolean z) {
        AccountAndUser accountAndUser = new AccountAndUser(account, i);
        AccountInfo accountInfo = this.mAccounts.get(accountAndUser);
        if (accountInfo == null) {
            accountInfo = new AccountInfo(accountAndUser);
            this.mAccounts.put(accountAndUser, accountInfo);
        }
        AuthorityInfo authorityInfo = accountInfo.authorities.get(str);
        if (authorityInfo == null) {
            if (i2 < 0) {
                i2 = this.mNextAuthorityId;
                this.mNextAuthorityId = i2 + 1;
                z = true;
            }
            authorityInfo = new AuthorityInfo(account, i, str, i2);
            accountInfo.authorities.put(str, authorityInfo);
            this.mAuthorities.put(i2, authorityInfo);
            if (z) {
                writeAccountInfoLocked();
            }
        }
        return authorityInfo;
    }

    private void removeAuthorityLocked(Account account, int i, String str, boolean z) {
        AuthorityInfo remove;
        AccountInfo accountInfo = this.mAccounts.get(new AccountAndUser(account, i));
        if (accountInfo != null && (remove = accountInfo.authorities.remove(str)) != null) {
            this.mAuthorities.remove(remove.ident);
            if (z) {
                writeAccountInfoLocked();
            }
        }
    }

    public void setPeriodicSyncTime(int i, PeriodicSync periodicSync, long j) {
        AuthorityInfo authorityInfo;
        boolean z;
        synchronized (this.mAuthorities) {
            authorityInfo = this.mAuthorities.get(i);
            z = false;
            int i2 = 0;
            while (true) {
                if (i2 >= authorityInfo.periodicSyncs.size()) {
                    break;
                } else if (periodicSync.equals(authorityInfo.periodicSyncs.get(i2))) {
                    this.mSyncStatus.get(i).setPeriodicSyncTime(i2, j);
                    z = true;
                    break;
                } else {
                    i2++;
                }
            }
        }
        if (!z) {
            Log.w(TAG, "Ignoring setPeriodicSyncTime request for a sync that does not exist. Authority: " + authorityInfo.authority);
        }
    }

    private SyncStatusInfo getOrCreateSyncStatusLocked(int i) {
        SyncStatusInfo syncStatusInfo = this.mSyncStatus.get(i);
        if (syncStatusInfo != null) {
            return syncStatusInfo;
        }
        SyncStatusInfo syncStatusInfo2 = new SyncStatusInfo(i);
        this.mSyncStatus.put(i, syncStatusInfo2);
        return syncStatusInfo2;
    }

    public void writeAllState() {
        synchronized (this.mAuthorities) {
            if (this.mNumPendingFinished > 0) {
                writePendingOperationsLocked();
            }
            writeStatusLocked();
            writeStatisticsLocked();
        }
    }

    public void clearAndReadState() {
        synchronized (this.mAuthorities) {
            this.mAuthorities.clear();
            this.mAccounts.clear();
            this.mServices.clear();
            this.mPendingOperations.clear();
            this.mSyncStatus.clear();
            this.mSyncHistory.clear();
            readAccountInfoLocked();
            readStatusLocked();
            readPendingOperationsLocked();
            readStatisticsLocked();
            readAndDeleteLegacyAccountInfoLocked();
            writeAccountInfoLocked();
            writeStatusLocked();
            writePendingOperationsLocked();
            writeStatisticsLocked();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:119:0x00b8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void readAccountInfoLocked() {
        /*
            Method dump skipped, instructions count: 376
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lody.virtual.server.content.SyncStorageEngine.readAccountInfoLocked():void");
    }

    private void maybeDeleteLegacyPendingInfoLocked(File file) {
        File file2 = new File(file, "pending.bin");
        if (file2.exists()) {
            file2.delete();
        }
    }

    private boolean maybeMigrateSettingsForRenamedAuthorities() {
        ArrayList arrayList = new ArrayList();
        int size = this.mAuthorities.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            AuthorityInfo valueAt = this.mAuthorities.valueAt(i);
            String str = sAuthorityRenames.get(valueAt.authority);
            if (str != null) {
                arrayList.add(valueAt);
                if (valueAt.enabled && getAuthorityLocked(valueAt.account, valueAt.userId, str, "cleanup") == null) {
                    getOrCreateAuthorityLocked(valueAt.account, valueAt.userId, str, -1, false).enabled = true;
                    z = true;
                }
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            AuthorityInfo authorityInfo = (AuthorityInfo) it.next();
            removeAuthorityLocked(authorityInfo.account, authorityInfo.userId, authorityInfo.authority, false);
            z = true;
        }
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void parseListenForTickles(org.xmlpull.v1.XmlPullParser r6) {
        /*
            r5 = this;
            java.lang.String r0 = "user"
            r1 = 0
            java.lang.String r0 = r6.getAttributeValue(r1, r0)
            r2 = 0
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: NullPointerException -> 0x000d, NumberFormatException -> 0x0016
            goto L_0x001f
        L_0x000d:
            r0 = move-exception
            java.lang.String r3 = "SyncManager"
            java.lang.String r4 = "the user in listen-for-tickles is null"
            android.util.Log.e(r3, r4, r0)
            goto L_0x001e
        L_0x0016:
            r0 = move-exception
            java.lang.String r3 = "SyncManager"
            java.lang.String r4 = "error parsing the user for listen-for-tickles"
            android.util.Log.e(r3, r4, r0)
        L_0x001e:
            r0 = 0
        L_0x001f:
            java.lang.String r3 = "enabled"
            java.lang.String r6 = r6.getAttributeValue(r1, r3)
            if (r6 == 0) goto L_0x002d
            boolean r6 = java.lang.Boolean.parseBoolean(r6)
            if (r6 == 0) goto L_0x002e
        L_0x002d:
            r2 = 1
        L_0x002e:
            android.util.SparseArray<java.lang.Boolean> r6 = r5.mMasterSyncAutomatically
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r2)
            r6.put(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lody.virtual.server.content.SyncStorageEngine.parseListenForTickles(org.xmlpull.v1.XmlPullParser):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.lody.virtual.server.content.SyncStorageEngine.AuthorityInfo parseAuthority(org.xmlpull.v1.XmlPullParser r17, int r18) {
        /*
            Method dump skipped, instructions count: 301
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lody.virtual.server.content.SyncStorageEngine.parseAuthority(org.xmlpull.v1.XmlPullParser, int):com.lody.virtual.server.content.SyncStorageEngine$AuthorityInfo");
    }

    private PeriodicSync parsePeriodicSync(XmlPullParser xmlPullParser, AuthorityInfo authorityInfo) {
        long j;
        Bundle bundle = new Bundle();
        String attributeValue = xmlPullParser.getAttributeValue(null, "period");
        String attributeValue2 = xmlPullParser.getAttributeValue(null, "flex");
        try {
            long parseLong = Long.parseLong(attributeValue);
            try {
                j = Long.parseLong(attributeValue2);
            } catch (NullPointerException unused) {
                long calculateDefaultFlexTime = calculateDefaultFlexTime(parseLong);
                Log.d(TAG, "No flex time specified for this sync, using a default. period: " + parseLong + " flex: " + calculateDefaultFlexTime);
                j = calculateDefaultFlexTime;
            } catch (NumberFormatException unused2) {
                Log.e(TAG, "Error formatting value parsed for periodic sync flex: " + attributeValue2);
                j = calculateDefaultFlexTime(parseLong);
            }
            PeriodicSync periodicSync = new PeriodicSync(authorityInfo.account, authorityInfo.authority, bundle, parseLong);
            p110z1.PeriodicSync.flexTime.set(periodicSync, j);
            authorityInfo.periodicSyncs.add(periodicSync);
            return periodicSync;
        } catch (NullPointerException e) {
            Log.e(TAG, "the period of a periodic sync is null", e);
            return null;
        } catch (NumberFormatException e2) {
            Log.e(TAG, "error parsing the period of a periodic sync", e2);
            return null;
        }
    }

    private void parseExtra(XmlPullParser xmlPullParser, Bundle bundle) {
        String attributeValue = xmlPullParser.getAttributeValue(null, "name");
        String attributeValue2 = xmlPullParser.getAttributeValue(null, "type");
        String attributeValue3 = xmlPullParser.getAttributeValue(null, "value1");
        String attributeValue4 = xmlPullParser.getAttributeValue(null, "value2");
        try {
            if ("long".equals(attributeValue2)) {
                bundle.putLong(attributeValue, Long.parseLong(attributeValue3));
            } else if ("integer".equals(attributeValue2)) {
                bundle.putInt(attributeValue, Integer.parseInt(attributeValue3));
            } else if ("double".equals(attributeValue2)) {
                bundle.putDouble(attributeValue, Double.parseDouble(attributeValue3));
            } else if ("float".equals(attributeValue2)) {
                bundle.putFloat(attributeValue, Float.parseFloat(attributeValue3));
            } else if ("boolean".equals(attributeValue2)) {
                bundle.putBoolean(attributeValue, Boolean.parseBoolean(attributeValue3));
            } else if ("string".equals(attributeValue2)) {
                bundle.putString(attributeValue, attributeValue3);
            } else if ("account".equals(attributeValue2)) {
                bundle.putParcelable(attributeValue, new Account(attributeValue3, attributeValue4));
            }
        } catch (NullPointerException e) {
            Log.e(TAG, "error parsing bundle value", e);
        } catch (NumberFormatException e2) {
            Log.e(TAG, "error parsing bundle value", e2);
        }
    }

    private void writeAccountInfoLocked() {
        FileOutputStream fileOutputStream;
        IOException e;
        Log.v(TAG, "Writing new " + this.mAccountInfoFile.getBaseFile());
        try {
            fileOutputStream = this.mAccountInfoFile.startWrite();
        } catch (IOException e2) {
            e = e2;
            fileOutputStream = null;
        }
        try {
            XmlSerializer fastXmlSerializer = new FastXmlSerializer();
            fastXmlSerializer.setOutput(fileOutputStream, EmailConstants.UTF_8);
            fastXmlSerializer.startDocument(null, true);
            fastXmlSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            fastXmlSerializer.startTag(null, C2986is.f14279a);
            fastXmlSerializer.attribute(null, "version", Integer.toString(2));
            fastXmlSerializer.attribute(null, XML_ATTR_NEXT_AUTHORITY_ID, Integer.toString(this.mNextAuthorityId));
            fastXmlSerializer.attribute(null, XML_ATTR_SYNC_RANDOM_OFFSET, Integer.toString(this.mSyncRandomOffset));
            int size = this.mMasterSyncAutomatically.size();
            for (int i = 0; i < size; i++) {
                int keyAt = this.mMasterSyncAutomatically.keyAt(i);
                fastXmlSerializer.startTag(null, XML_TAG_LISTEN_FOR_TICKLES);
                fastXmlSerializer.attribute(null, "user", Integer.toString(keyAt));
                fastXmlSerializer.attribute(null, XML_ATTR_ENABLED, Boolean.toString(this.mMasterSyncAutomatically.valueAt(i).booleanValue()));
                fastXmlSerializer.endTag(null, XML_TAG_LISTEN_FOR_TICKLES);
            }
            int size2 = this.mAuthorities.size();
            for (int i2 = 0; i2 < size2; i2++) {
                AuthorityInfo valueAt = this.mAuthorities.valueAt(i2);
                fastXmlSerializer.startTag(null, "authority");
                fastXmlSerializer.attribute(null, ConnectionModel.f10389a, Integer.toString(valueAt.ident));
                fastXmlSerializer.attribute(null, "user", Integer.toString(valueAt.userId));
                fastXmlSerializer.attribute(null, XML_ATTR_ENABLED, Boolean.toString(valueAt.enabled));
                if (valueAt.service == null) {
                    fastXmlSerializer.attribute(null, "account", valueAt.account.name);
                    fastXmlSerializer.attribute(null, "type", valueAt.account.type);
                    fastXmlSerializer.attribute(null, "authority", valueAt.authority);
                } else {
                    fastXmlSerializer.attribute(null, ServiceManagerNative.PACKAGE, valueAt.service.getPackageName());
                    fastXmlSerializer.attribute(null, "class", valueAt.service.getClassName());
                }
                if (valueAt.syncable < 0) {
                    fastXmlSerializer.attribute(null, "syncable", "unknown");
                } else {
                    fastXmlSerializer.attribute(null, "syncable", Boolean.toString(valueAt.syncable != 0));
                }
                Iterator<PeriodicSync> it = valueAt.periodicSyncs.iterator();
                while (it.hasNext()) {
                    PeriodicSync next = it.next();
                    fastXmlSerializer.startTag(null, "periodicSync");
                    fastXmlSerializer.attribute(null, "period", Long.toString(next.period));
                    fastXmlSerializer.attribute(null, "flex", Long.toString(p110z1.PeriodicSync.flexTime.get(next)));
                    extrasToXml(fastXmlSerializer, next.extras);
                    fastXmlSerializer.endTag(null, "periodicSync");
                }
                fastXmlSerializer.endTag(null, "authority");
            }
            fastXmlSerializer.endTag(null, C2986is.f14279a);
            fastXmlSerializer.endDocument();
            this.mAccountInfoFile.finishWrite(fileOutputStream);
        } catch (IOException e3) {
            e = e3;
            Log.w(TAG, "Error writing accounts", e);
            if (fileOutputStream != null) {
                this.mAccountInfoFile.failWrite(fileOutputStream);
            }
        }
    }

    static int getIntColumn(Cursor cursor, String str) {
        return cursor.getInt(cursor.getColumnIndex(str));
    }

    static long getLongColumn(Cursor cursor, String str) {
        return cursor.getLong(cursor.getColumnIndex(str));
    }

    private void readAndDeleteLegacyAccountInfoLocked() {
        SQLiteDatabase sQLiteDatabase;
        boolean z;
        File databasePath = this.mContext.getDatabasePath("syncmanager.db");
        if (databasePath.exists()) {
            String path = databasePath.getPath();
            try {
                sQLiteDatabase = SQLiteDatabase.openDatabase(path, null, 1);
            } catch (SQLiteException unused) {
                sQLiteDatabase = null;
            }
            if (sQLiteDatabase != null) {
                boolean z2 = sQLiteDatabase.getVersion() >= 11;
                Log.v(TAG, "Reading legacy sync accounts db");
                SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
                sQLiteQueryBuilder.setTables("stats, status");
                HashMap hashMap = new HashMap();
                hashMap.put("_id", "status._id as _id");
                hashMap.put("account", "stats.account as account");
                if (z2) {
                    hashMap.put("account_type", "stats.account_type as account_type");
                }
                hashMap.put("authority", "stats.authority as authority");
                hashMap.put("totalElapsedTime", "totalElapsedTime");
                hashMap.put("numSyncs", "numSyncs");
                hashMap.put("numSourceLocal", "numSourceLocal");
                hashMap.put("numSourcePoll", "numSourcePoll");
                hashMap.put("numSourceServer", "numSourceServer");
                hashMap.put("numSourceUser", "numSourceUser");
                hashMap.put("lastSuccessSource", "lastSuccessSource");
                hashMap.put("lastSuccessTime", "lastSuccessTime");
                hashMap.put("lastFailureSource", "lastFailureSource");
                hashMap.put("lastFailureTime", "lastFailureTime");
                hashMap.put("lastFailureMesg", "lastFailureMesg");
                hashMap.put("pending", "pending");
                sQLiteQueryBuilder.setProjectionMap(hashMap);
                sQLiteQueryBuilder.appendWhere("stats._id = status.stats_id");
                Cursor query = sQLiteQueryBuilder.query(sQLiteDatabase, null, null, null, null, null, null);
                while (query.moveToNext()) {
                    String string = query.getString(query.getColumnIndex("account"));
                    String string2 = z2 ? query.getString(query.getColumnIndex("account_type")) : null;
                    if (string2 == null) {
                        string2 = "com.google";
                    }
                    AuthorityInfo orCreateAuthorityLocked = getOrCreateAuthorityLocked(new Account(string, string2), 0, query.getString(query.getColumnIndex("authority")), -1, false);
                    if (orCreateAuthorityLocked != null) {
                        int size = this.mSyncStatus.size();
                        SyncStatusInfo syncStatusInfo = null;
                        while (true) {
                            if (size <= 0) {
                                z = false;
                                break;
                            }
                            size--;
                            syncStatusInfo = this.mSyncStatus.valueAt(size);
                            if (syncStatusInfo.authorityId == orCreateAuthorityLocked.ident) {
                                z = true;
                                break;
                            }
                        }
                        if (!z) {
                            syncStatusInfo = new SyncStatusInfo(orCreateAuthorityLocked.ident);
                            this.mSyncStatus.put(orCreateAuthorityLocked.ident, syncStatusInfo);
                        }
                        syncStatusInfo.totalElapsedTime = getLongColumn(query, "totalElapsedTime");
                        syncStatusInfo.numSyncs = getIntColumn(query, "numSyncs");
                        syncStatusInfo.numSourceLocal = getIntColumn(query, "numSourceLocal");
                        syncStatusInfo.numSourcePoll = getIntColumn(query, "numSourcePoll");
                        syncStatusInfo.numSourceServer = getIntColumn(query, "numSourceServer");
                        syncStatusInfo.numSourceUser = getIntColumn(query, "numSourceUser");
                        syncStatusInfo.numSourcePeriodic = 0;
                        syncStatusInfo.lastSuccessSource = getIntColumn(query, "lastSuccessSource");
                        syncStatusInfo.lastSuccessTime = getLongColumn(query, "lastSuccessTime");
                        syncStatusInfo.lastFailureSource = getIntColumn(query, "lastFailureSource");
                        syncStatusInfo.lastFailureTime = getLongColumn(query, "lastFailureTime");
                        syncStatusInfo.lastFailureMesg = query.getString(query.getColumnIndex("lastFailureMesg"));
                        syncStatusInfo.pending = getIntColumn(query, "pending") != 0;
                    }
                }
                query.close();
                SQLiteQueryBuilder sQLiteQueryBuilder2 = new SQLiteQueryBuilder();
                sQLiteQueryBuilder2.setTables("settings");
                Cursor query2 = sQLiteQueryBuilder2.query(sQLiteDatabase, null, null, null, null, null, null);
                while (query2.moveToNext()) {
                    String string3 = query2.getString(query2.getColumnIndex("name"));
                    String string4 = query2.getString(query2.getColumnIndex(SizeSelector.SIZE_KEY));
                    if (string3 != null) {
                        if (string3.equals("listen_for_tickles")) {
                            setMasterSyncAutomatically(string4 == null || Boolean.parseBoolean(string4), 0);
                        } else if (string3.startsWith("sync_provider_")) {
                            String substring = string3.substring(14, string3.length());
                            int size2 = this.mAuthorities.size();
                            while (size2 > 0) {
                                size2--;
                                AuthorityInfo valueAt = this.mAuthorities.valueAt(size2);
                                if (valueAt.authority.equals(substring)) {
                                    valueAt.enabled = string4 == null || Boolean.parseBoolean(string4);
                                    valueAt.syncable = 1;
                                }
                            }
                        }
                    }
                }
                query2.close();
                sQLiteDatabase.close();
                new File(path).delete();
            }
        }
    }

    private void readStatusLocked() {
        Log.v(TAG, "Reading " + this.mStatusFile.getBaseFile());
        try {
            byte[] readFully = this.mStatusFile.readFully();
            Parcel obtain = Parcel.obtain();
            obtain.unmarshall(readFully, 0, readFully.length);
            obtain.setDataPosition(0);
            while (true) {
                int readInt = obtain.readInt();
                if (readInt != 0 && readInt == 100) {
                    SyncStatusInfo syncStatusInfo = new SyncStatusInfo(obtain);
                    if (this.mAuthorities.indexOfKey(syncStatusInfo.authorityId) >= 0) {
                        syncStatusInfo.pending = false;
                        Log.v(TAG, "Adding status for id " + syncStatusInfo.authorityId);
                        this.mSyncStatus.put(syncStatusInfo.authorityId, syncStatusInfo);
                    }
                } else {
                    return;
                }
            }
        } catch (IOException unused) {
        }
    }

    private void writeStatusLocked() {
        removeMessages(1);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = this.mStatusFile.startWrite();
            Parcel obtain = Parcel.obtain();
            int size = this.mSyncStatus.size();
            for (int i = 0; i < size; i++) {
                obtain.writeInt(100);
                this.mSyncStatus.valueAt(i).writeToParcel(obtain, 0);
            }
            obtain.writeInt(0);
            fileOutputStream.write(obtain.marshall());
            obtain.recycle();
            this.mStatusFile.finishWrite(fileOutputStream);
        } catch (IOException e) {
            Log.w(TAG, "Error writing status", e);
            if (fileOutputStream != null) {
                this.mStatusFile.failWrite(fileOutputStream);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x0181, code lost:
        if (r2 == null) goto L_0x0192;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0183, code lost:
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x018f, code lost:
        if (r2 == null) goto L_0x0192;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void readPendingOperationsLocked() {
        /*
            Method dump skipped, instructions count: 409
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lody.virtual.server.content.SyncStorageEngine.readPendingOperationsLocked():void");
    }

    private void writePendingOperationsLocked() {
        int size = this.mPendingOperations.size();
        try {
            if (size == 0) {
                Log.v(TAG_FILE, "Truncating " + this.mPendingFile.getBaseFile());
                this.mPendingFile.truncate();
                return;
            }
            Log.v(TAG_FILE, "Writing new " + this.mPendingFile.getBaseFile());
            FileOutputStream startWrite = this.mPendingFile.startWrite();
            XmlSerializer fastXmlSerializer = new FastXmlSerializer();
            fastXmlSerializer.setOutput(startWrite, EmailConstants.UTF_8);
            for (int i = 0; i < size; i++) {
                writePendingOperationLocked(this.mPendingOperations.get(i), fastXmlSerializer);
            }
            fastXmlSerializer.endDocument();
            this.mPendingFile.finishWrite(startWrite);
        } catch (IOException e) {
            Log.w(TAG, "Error writing pending operations", e);
            if (0 != 0) {
                this.mPendingFile.failWrite(null);
            }
        }
    }

    private void writePendingOperationLocked(PendingOperation pendingOperation, XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.startTag(null, "op");
        xmlSerializer.attribute(null, "version", Integer.toString(3));
        xmlSerializer.attribute(null, XML_ATTR_AUTHORITYID, Integer.toString(pendingOperation.authorityId));
        xmlSerializer.attribute(null, "source", Integer.toString(pendingOperation.syncSource));
        xmlSerializer.attribute(null, XML_ATTR_EXPEDITED, Boolean.toString(pendingOperation.expedited));
        xmlSerializer.attribute(null, XML_ATTR_REASON, Integer.toString(pendingOperation.reason));
        extrasToXml(xmlSerializer, pendingOperation.extras);
        xmlSerializer.endTag(null, "op");
    }

    private void appendPendingOperationLocked(PendingOperation pendingOperation) {
        Log.v(TAG, "Appending to " + this.mPendingFile.getBaseFile());
        try {
            try {
                FileOutputStream openAppend = this.mPendingFile.openAppend();
                try {
                    XmlSerializer fastXmlSerializer = new FastXmlSerializer();
                    fastXmlSerializer.setOutput(openAppend, EmailConstants.UTF_8);
                    writePendingOperationLocked(pendingOperation, fastXmlSerializer);
                    fastXmlSerializer.endDocument();
                    this.mPendingFile.finishWrite(openAppend);
                } catch (IOException e) {
                    Log.w(TAG, "Error writing appending operation", e);
                    this.mPendingFile.failWrite(openAppend);
                }
                try {
                    openAppend.close();
                } catch (IOException unused) {
                }
            } catch (Throwable th) {
                try {
                    TAG.close();
                } catch (IOException unused2) {
                }
                throw th;
            }
        } catch (IOException unused3) {
            Log.v(TAG, "Failed append; writing full file");
            writePendingOperationsLocked();
        }
    }

    private static byte[] flattenBundle(Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        try {
            bundle.writeToParcel(obtain, 0);
            return obtain.marshall();
        } finally {
            obtain.recycle();
        }
    }

    private static Bundle unflattenBundle(byte[] bArr) {
        Bundle bundle;
        Parcel obtain = Parcel.obtain();
        try {
            try {
                obtain.unmarshall(bArr, 0, bArr.length);
                obtain.setDataPosition(0);
                bundle = obtain.readBundle();
            } catch (RuntimeException unused) {
                bundle = new Bundle();
            }
            return bundle;
        } finally {
            obtain.recycle();
        }
    }

    private void extrasToXml(XmlSerializer xmlSerializer, Bundle bundle) throws IOException {
        for (String str : bundle.keySet()) {
            xmlSerializer.startTag(null, "extra");
            xmlSerializer.attribute(null, "name", str);
            Object obj = bundle.get(str);
            if (obj instanceof Long) {
                xmlSerializer.attribute(null, "type", "long");
                xmlSerializer.attribute(null, "value1", obj.toString());
            } else if (obj instanceof Integer) {
                xmlSerializer.attribute(null, "type", "integer");
                xmlSerializer.attribute(null, "value1", obj.toString());
            } else if (obj instanceof Boolean) {
                xmlSerializer.attribute(null, "type", "boolean");
                xmlSerializer.attribute(null, "value1", obj.toString());
            } else if (obj instanceof Float) {
                xmlSerializer.attribute(null, "type", "float");
                xmlSerializer.attribute(null, "value1", obj.toString());
            } else if (obj instanceof Double) {
                xmlSerializer.attribute(null, "type", "double");
                xmlSerializer.attribute(null, "value1", obj.toString());
            } else if (obj instanceof String) {
                xmlSerializer.attribute(null, "type", "string");
                xmlSerializer.attribute(null, "value1", obj.toString());
            } else if (obj instanceof Account) {
                xmlSerializer.attribute(null, "type", "account");
                Account account = (Account) obj;
                xmlSerializer.attribute(null, "value1", account.name);
                xmlSerializer.attribute(null, "value2", account.type);
            }
            xmlSerializer.endTag(null, "extra");
        }
    }

    private void requestSync(Account account, int i, int i2, String str, Bundle bundle) {
        OnSyncRequestListener onSyncRequestListener;
        if (Process.myUid() != 1000 || (onSyncRequestListener = this.mSyncRequestListener) == null) {
            ContentResolver.requestSync(account, str, bundle);
        } else {
            onSyncRequestListener.onSyncRequest(account, i, i2, str, bundle);
        }
    }

    private void readStatisticsLocked() {
        try {
            byte[] readFully = this.mStatisticsFile.readFully();
            Parcel obtain = Parcel.obtain();
            int i = 0;
            obtain.unmarshall(readFully, 0, readFully.length);
            obtain.setDataPosition(0);
            while (true) {
                int readInt = obtain.readInt();
                if (readInt != 0) {
                    if (!(readInt == 101 || readInt == 100)) {
                        Log.w(TAG, "Unknown stats token: " + readInt);
                        return;
                    }
                    int readInt2 = obtain.readInt();
                    if (readInt == 100) {
                        readInt2 = (readInt2 - 2009) + 14245;
                    }
                    DayStats dayStats = new DayStats(readInt2);
                    dayStats.successCount = obtain.readInt();
                    dayStats.successTime = obtain.readLong();
                    dayStats.failureCount = obtain.readInt();
                    dayStats.failureTime = obtain.readLong();
                    if (i < this.mDayStats.length) {
                        this.mDayStats[i] = dayStats;
                        i++;
                    }
                } else {
                    return;
                }
            }
        } catch (IOException unused) {
        }
    }

    private void writeStatisticsLocked() {
        Log.v(TAG, "Writing new " + this.mStatisticsFile.getBaseFile());
        removeMessages(2);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = this.mStatisticsFile.startWrite();
            Parcel obtain = Parcel.obtain();
            int length = this.mDayStats.length;
            for (int i = 0; i < length; i++) {
                DayStats dayStats = this.mDayStats[i];
                if (dayStats == null) {
                    break;
                }
                obtain.writeInt(101);
                obtain.writeInt(dayStats.day);
                obtain.writeInt(dayStats.successCount);
                obtain.writeLong(dayStats.successTime);
                obtain.writeInt(dayStats.failureCount);
                obtain.writeLong(dayStats.failureTime);
            }
            obtain.writeInt(0);
            fileOutputStream.write(obtain.marshall());
            obtain.recycle();
            this.mStatisticsFile.finishWrite(fileOutputStream);
        } catch (IOException e) {
            Log.w(TAG, "Error writing stats", e);
            if (fileOutputStream != null) {
                this.mStatisticsFile.failWrite(fileOutputStream);
            }
        }
    }

    public void dumpPendingOperations(StringBuilder sb) {
        sb.append("Pending Ops: ");
        sb.append(this.mPendingOperations.size());
        sb.append(" operation(s)\n");
        Iterator<PendingOperation> it = this.mPendingOperations.iterator();
        while (it.hasNext()) {
            PendingOperation next = it.next();
            sb.append("(" + next.account);
            sb.append(", u" + next.userId);
            sb.append(", " + next.authority);
            sb.append(", " + next.extras);
            sb.append(")\n");
        }
    }
}
