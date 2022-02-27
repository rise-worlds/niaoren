package com.lody.virtual.server.content;

import android.accounts.Account;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.ISyncAdapter;
import android.content.ISyncContext;
import android.content.ISyncStatusObserver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.PeriodicSync;
import android.content.ServiceConnection;
import android.content.SyncAdapterType;
import android.content.SyncResult;
import android.content.SyncStatusInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.Log;
import android.util.Pair;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.lody.virtual.client.env.Constants;
import com.lody.virtual.helper.compat.ContentResolverCompat;
import com.lody.virtual.p061os.BackgroundThread;
import com.lody.virtual.p061os.VUserHandle;
import com.lody.virtual.p061os.VUserInfo;
import com.lody.virtual.p061os.VUserManager;
import com.lody.virtual.server.accounts.AccountAndUser;
import com.lody.virtual.server.accounts.VAccountManagerService;
import com.lody.virtual.server.p062am.VActivityManagerService;
import com.tendcloud.tenddata.C2664ab;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.apache.tools.ant.taskdefs.WaitFor;
import p110z1.cjm;

/* loaded from: classes.dex */
public class SyncManager {
    private static final String ACTION_SYNC_ALARM = "android.content.syncmanager.SYNC_ALARM";
    private static final long DEFAULT_MAX_SYNC_RETRY_TIME_IN_SECONDS = 3600;
    private static final int DELAY_RETRY_SYNC_IN_PROGRESS_IN_SECONDS = 10;
    private static final String HANDLE_SYNC_ALARM_WAKE_LOCK = "SyncManagerHandleSyncAlarm";
    private static final int INITIALIZATION_UNBIND_DELAY_MS = 5000;
    private static final AccountAndUser[] INITIAL_ACCOUNTS_ARRAY = new AccountAndUser[0];
    private static final long INITIAL_SYNC_RETRY_TIME_IN_MS = 30000;
    private static final long LOCAL_SYNC_DELAY = 30000;
    private static final int MAX_SIMULTANEOUS_INITIALIZATION_SYNCS = 5;
    private static final int MAX_SIMULTANEOUS_REGULAR_SYNCS = 2;
    private static final long MAX_TIME_PER_SYNC = 300000;
    private static final long SYNC_ALARM_TIMEOUT_MAX = 7200000;
    private static final long SYNC_ALARM_TIMEOUT_MIN = 30000;
    private static final String SYNC_LOOP_WAKE_LOCK = "SyncLoopWakeLock";
    private static final long SYNC_NOTIFICATION_DELAY = 30000;
    private static final String SYNC_WAKE_LOCK_PREFIX = "*sync*";
    private static final String TAG = "SyncManager";
    private ConnectivityManager mConnManagerDoNotUseDirectly;
    private Context mContext;
    private final PowerManager mPowerManager;
    protected SyncAdaptersCache mSyncAdapters;
    private final PendingIntent mSyncAlarmIntent;
    private final SyncQueue mSyncQueue;
    private volatile AccountAndUser[] mRunningAccounts = INITIAL_ACCOUNTS_ARRAY;
    private volatile boolean mDataConnectionIsConnected = false;
    private volatile boolean mStorageIsLow = false;
    private AlarmManager mAlarmService = null;
    protected final ArrayList<ActiveSyncContext> mActiveSyncContexts = new ArrayList<>();
    private BroadcastReceiver mStorageIntentReceiver = new BroadcastReceiver() { // from class: com.lody.virtual.server.content.SyncManager.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.intent.action.DEVICE_STORAGE_LOW".equals(action)) {
                Log.v(SyncManager.TAG, "Internal storage is low.");
                SyncManager.this.mStorageIsLow = true;
                SyncManager.this.cancelActiveSync(null, -1, null);
            } else if ("android.intent.action.DEVICE_STORAGE_OK".equals(action)) {
                Log.v(SyncManager.TAG, "Internal storage is ok.");
                SyncManager.this.mStorageIsLow = false;
                SyncManager.this.sendCheckAlarmsMessage();
            }
        }
    };
    private BroadcastReceiver mBootCompletedReceiver = new BroadcastReceiver() { // from class: com.lody.virtual.server.content.SyncManager.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            SyncManager.this.mSyncHandler.onBootCompleted();
        }
    };
    private BroadcastReceiver mBackgroundDataSettingChanged = new BroadcastReceiver() { // from class: com.lody.virtual.server.content.SyncManager.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (SyncManager.this.getConnectivityManager().getBackgroundDataSetting()) {
                SyncManager.this.scheduleSync(null, -1, -1, null, new Bundle(), 0L, 0L, false);
            }
        }
    };
    private BroadcastReceiver mAccountsUpdatedReceiver = new BroadcastReceiver() { // from class: com.lody.virtual.server.content.SyncManager.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            SyncManager.this.updateRunningAccounts();
            SyncManager.this.scheduleSync(null, -1, -2, null, null, 0L, 0L, false);
        }
    };
    private BroadcastReceiver mConnectivityIntentReceiver = new BroadcastReceiver() { // from class: com.lody.virtual.server.content.SyncManager.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean z = SyncManager.this.mDataConnectionIsConnected;
            SyncManager syncManager = SyncManager.this;
            syncManager.mDataConnectionIsConnected = syncManager.readDataConnectionState();
            if (SyncManager.this.mDataConnectionIsConnected) {
                if (!z) {
                    Log.v(SyncManager.TAG, "Reconnection detected: clearing all backoffs");
                    synchronized (SyncManager.this.mSyncQueue) {
                        SyncManager.this.mSyncStorageEngine.clearAllBackoffsLocked(SyncManager.this.mSyncQueue);
                    }
                }
                SyncManager.this.sendCheckAlarmsMessage();
            }
        }
    };
    private BroadcastReceiver mShutdownIntentReceiver = new BroadcastReceiver() { // from class: com.lody.virtual.server.content.SyncManager.6
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.w(SyncManager.TAG, "Writing sync state before shutdown...");
            SyncManager.this.getSyncStorageEngine().writeAllState();
        }
    };
    private BroadcastReceiver mUserIntentReceiver = new BroadcastReceiver() { // from class: com.lody.virtual.server.content.SyncManager.7
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            int intExtra = intent.getIntExtra(Constants.EXTRA_USER_HANDLE, VUserHandle.USER_NULL);
            if (intExtra != -10000) {
                if (Constants.ACTION_USER_REMOVED.equals(action)) {
                    SyncManager.this.onUserRemoved(intExtra);
                } else if (Constants.ACTION_USER_ADDED.equals(action)) {
                    SyncManager.this.onUserStarting(intExtra);
                } else if (Constants.ACTION_USER_REMOVED.equals(action)) {
                    SyncManager.this.onUserStopping(intExtra);
                }
            }
        }
    };
    private volatile boolean mBootCompleted = false;
    private SyncStorageEngine mSyncStorageEngine = SyncStorageEngine.getSingleton();
    private final SyncHandler mSyncHandler = new SyncHandler(BackgroundThread.get().getLooper());
    private final VUserManager mUserManager = VUserManager.get();
    private int mSyncRandomOffsetMillis = this.mSyncStorageEngine.getSyncRandomOffset() * 1000;

    private String getLastFailureMessage(int i) {
        switch (i) {
            case 1:
                return "sync already in progress";
            case 2:
                return "authentication error";
            case 3:
                return "I/O error";
            case 4:
                return "parse error";
            case 5:
                return "conflict error";
            case 6:
                return "too many deletions error";
            case 7:
                return "too many retries error";
            case 8:
                return "internal error";
            default:
                return "unknown";
        }
    }

    private List<VUserInfo> getAllUsers() {
        return this.mUserManager.getUsers();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean containsAccountAndUser(AccountAndUser[] accountAndUserArr, Account account, int i) {
        for (int i2 = 0; i2 < accountAndUserArr.length; i2++) {
            if (accountAndUserArr[i2].userId == i && accountAndUserArr[i2].account.equals(account)) {
                return true;
            }
        }
        return false;
    }

    public void updateRunningAccounts() {
        this.mRunningAccounts = VAccountManagerService.get().getAllAccounts();
        if (this.mBootCompleted) {
            doDatabaseCleanup();
        }
        Iterator<ActiveSyncContext> it = this.mActiveSyncContexts.iterator();
        while (it.hasNext()) {
            ActiveSyncContext next = it.next();
            if (!containsAccountAndUser(this.mRunningAccounts, next.mSyncOperation.account, next.mSyncOperation.userId)) {
                Log.d(TAG, "canceling sync since the account is no longer running");
                sendSyncFinishedOrCanceledMessage(next, null);
            }
        }
        sendCheckAlarmsMessage();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doDatabaseCleanup() {
        for (VUserInfo vUserInfo : this.mUserManager.getUsers(true)) {
            if (!vUserInfo.partial) {
                this.mSyncStorageEngine.doDatabaseCleanup(VAccountManagerService.get().getAccounts(vUserInfo.f10500id, null), vUserInfo.f10500id);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean readDataConnectionState() {
        NetworkInfo activeNetworkInfo = getConnectivityManager().getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ConnectivityManager getConnectivityManager() {
        ConnectivityManager connectivityManager;
        synchronized (this) {
            if (this.mConnManagerDoNotUseDirectly == null) {
                this.mConnManagerDoNotUseDirectly = (ConnectivityManager) this.mContext.getSystemService("connectivity");
            }
            connectivityManager = this.mConnManagerDoNotUseDirectly;
        }
        return connectivityManager;
    }

    public SyncManager(Context context) {
        this.mContext = context;
        SyncStorageEngine.init(context);
        this.mSyncStorageEngine.setOnSyncRequestListener(new SyncStorageEngine.OnSyncRequestListener() { // from class: com.lody.virtual.server.content.SyncManager.8
            @Override // com.lody.virtual.server.content.SyncStorageEngine.OnSyncRequestListener
            public void onSyncRequest(Account account, int i, int i2, String str, Bundle bundle) {
                SyncManager.this.scheduleSync(account, i, i2, str, bundle, 0L, 0L, false);
            }
        });
        this.mSyncAdapters = new SyncAdaptersCache(this.mContext);
        this.mSyncAdapters.refreshServiceCache(null);
        this.mSyncQueue = new SyncQueue(this.mSyncStorageEngine, this.mSyncAdapters);
        this.mSyncAlarmIntent = PendingIntent.getBroadcast(this.mContext, 0, new Intent(ACTION_SYNC_ALARM), 0);
        context.registerReceiver(this.mConnectivityIntentReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        context.registerReceiver(this.mBootCompletedReceiver, new IntentFilter("android.intent.action.BOOT_COMPLETED"));
        context.registerReceiver(this.mBackgroundDataSettingChanged, new IntentFilter("android.net.conn.BACKGROUND_DATA_SETTING_CHANGED"));
        IntentFilter intentFilter = new IntentFilter("android.intent.action.DEVICE_STORAGE_LOW");
        intentFilter.addAction("android.intent.action.DEVICE_STORAGE_OK");
        context.registerReceiver(this.mStorageIntentReceiver, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter("android.intent.action.ACTION_SHUTDOWN");
        intentFilter2.setPriority(100);
        context.registerReceiver(this.mShutdownIntentReceiver, intentFilter2);
        IntentFilter intentFilter3 = new IntentFilter();
        intentFilter3.addAction(Constants.ACTION_USER_REMOVED);
        intentFilter3.addAction(Constants.ACTION_USER_ADDED);
        intentFilter3.addAction(Constants.ACTION_USER_REMOVED);
        this.mContext.registerReceiver(this.mUserIntentReceiver, intentFilter3);
        context.registerReceiver(new SyncAlarmIntentReceiver(), new IntentFilter(ACTION_SYNC_ALARM));
        this.mPowerManager = (PowerManager) context.getSystemService("power");
        this.mSyncStorageEngine.addStatusChangeListener(1, new ISyncStatusObserver.Stub() { // from class: com.lody.virtual.server.content.SyncManager.9
            @Override // android.content.ISyncStatusObserver
            public void onStatusChanged(int i) {
                SyncManager.this.sendCheckAlarmsMessage();
            }
        });
    }

    private long jitterize(long j, long j2) {
        Random random = new Random(SystemClock.elapsedRealtime());
        long j3 = j2 - j;
        if (j3 <= 2147483647L) {
            return j + random.nextInt((int) j3);
        }
        throw new IllegalArgumentException("the difference between the maxValue and the minValue must be less than 2147483647");
    }

    public SyncStorageEngine getSyncStorageEngine() {
        return this.mSyncStorageEngine;
    }

    public int getIsSyncable(Account account, int i, String str) {
        int isSyncable = this.mSyncStorageEngine.getIsSyncable(account, i, str);
        VUserInfo userInfo = VUserManager.get().getUserInfo(i);
        if (userInfo == null || !userInfo.isRestricted() || this.mSyncAdapters.getServiceInfo(account, str) == null) {
            return isSyncable;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ensureAlarmService() {
        if (this.mAlarmService == null) {
            this.mAlarmService = (AlarmManager) this.mContext.getSystemService(NotificationCompat.CATEGORY_ALARM);
        }
    }

    public void scheduleSync(Account account, int i, int i2, String str, Bundle bundle, long j, long j2, boolean z) {
        AccountAndUser[] accountAndUserArr;
        Iterator it;
        String str2 = str;
        boolean z2 = !this.mBootCompleted || getConnectivityManager().getBackgroundDataSetting();
        Bundle bundle2 = bundle == null ? new Bundle() : bundle;
        Log.d(TAG, "one-time sync for: " + account + ExpandableTextView.f6958c + bundle2.toString() + ExpandableTextView.f6958c + str2);
        long j3 = Boolean.valueOf(bundle2.getBoolean("expedited", false)).booleanValue() ? -1L : j2;
        if (account == null || i == -1) {
            AccountAndUser[] accountAndUserArr2 = this.mRunningAccounts;
            if (accountAndUserArr2.length == 0) {
                Log.v(TAG, "scheduleSync: no accounts configured, dropping");
                return;
            }
            accountAndUserArr = accountAndUserArr2;
        } else {
            accountAndUserArr = new AccountAndUser[]{new AccountAndUser(account, i)};
        }
        boolean z3 = bundle2.getBoolean("upload", false);
        boolean z4 = bundle2.getBoolean("force", false);
        if (z4) {
            bundle2.putBoolean("ignore_backoff", true);
            bundle2.putBoolean("ignore_settings", true);
        }
        boolean z5 = bundle2.getBoolean("ignore_settings", false);
        int i3 = z3 ? 1 : z4 ? 3 : str2 == null ? 2 : 0;
        int length = accountAndUserArr.length;
        int i4 = 0;
        while (i4 < length) {
            AccountAndUser accountAndUser = accountAndUserArr[i4];
            HashSet hashSet = new HashSet();
            for (SyncAdaptersCache.SyncAdapterInfo syncAdapterInfo : this.mSyncAdapters.getAllServices()) {
                hashSet.add(syncAdapterInfo.type.authority);
            }
            if (str2 != null) {
                boolean contains = hashSet.contains(str2);
                hashSet.clear();
                if (contains) {
                    hashSet.add(str2);
                }
            }
            Iterator it2 = hashSet.iterator();
            while (it2.hasNext()) {
                String str3 = (String) it2.next();
                int isSyncable = getIsSyncable(accountAndUser.account, accountAndUser.userId, str3);
                if (isSyncable != 0) {
                    SyncAdaptersCache.SyncAdapterInfo serviceInfo = this.mSyncAdapters.getServiceInfo(accountAndUser.account, str3);
                    if (serviceInfo == null) {
                        accountAndUserArr = accountAndUserArr;
                    } else {
                        boolean allowParallelSyncs = serviceInfo.type.allowParallelSyncs();
                        boolean isAlwaysSyncable = serviceInfo.type.isAlwaysSyncable();
                        if (isSyncable >= 0 || !isAlwaysSyncable) {
                            it = it2;
                        } else {
                            it = it2;
                            this.mSyncStorageEngine.setIsSyncable(accountAndUser.account, accountAndUser.userId, str3, 1);
                            isSyncable = 1;
                        }
                        if (z && isSyncable >= 0) {
                            accountAndUserArr = accountAndUserArr;
                            it2 = it;
                        } else if (!serviceInfo.type.supportsUploading() && z3) {
                            accountAndUserArr = accountAndUserArr;
                            it2 = it;
                        } else if (!(isSyncable < 0 || z5 || (z2 && this.mSyncStorageEngine.getMasterSyncAutomatically(accountAndUser.userId) && this.mSyncStorageEngine.getSyncAutomatically(accountAndUser.account, accountAndUser.userId, str3)))) {
                            Log.d(TAG, "scheduleSync: sync of " + accountAndUser + ", " + str3 + " is not allowed, dropping request");
                            accountAndUserArr = accountAndUserArr;
                            it2 = it;
                        } else {
                            Pair<Long, Long> backoff = this.mSyncStorageEngine.getBackoff(accountAndUser.account, accountAndUser.userId, str3);
                            long delayUntilTime = this.mSyncStorageEngine.getDelayUntilTime(accountAndUser.account, accountAndUser.userId, str3);
                            long longValue = backoff != null ? ((Long) backoff.first).longValue() : 0L;
                            if (isSyncable < 0) {
                                Bundle bundle3 = new Bundle();
                                bundle3.putBoolean("initialize", true);
                                Log.v(TAG, "schedule initialisation Sync:, delay until " + delayUntilTime + ", run by 0, source " + i3 + ", account " + accountAndUser + ", authority " + str3 + ", extras " + bundle3);
                                scheduleSyncOperation(new SyncOperation(accountAndUser.account, accountAndUser.userId, i2, i3, str3, bundle3, 0L, 0L, longValue, delayUntilTime, allowParallelSyncs));
                            }
                            if (!z) {
                                Log.v(TAG, "scheduleSync: delay until " + delayUntilTime + " run by " + j3 + " flex " + j + ", source " + i3 + ", account " + accountAndUser + ", authority " + str3 + ", extras " + bundle2);
                                accountAndUser = accountAndUser;
                                i4 = i4;
                                length = length;
                                i3 = i3;
                                j3 = j3;
                                scheduleSyncOperation(new SyncOperation(accountAndUser.account, accountAndUser.userId, i2, i3, str3, bundle2, j3, j, longValue, delayUntilTime, allowParallelSyncs));
                            } else {
                                accountAndUser = accountAndUser;
                                i4 = i4;
                                length = length;
                                i3 = i3;
                                j3 = j3;
                            }
                            accountAndUserArr = accountAndUserArr;
                            z3 = z3;
                            it2 = it;
                        }
                    }
                }
            }
            i4++;
            z3 = z3;
            str2 = str;
        }
    }

    public void scheduleLocalSync(Account account, int i, int i2, String str) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("upload", true);
        long j = LOCAL_SYNC_DELAY;
        scheduleSync(account, i, i2, str, bundle, j, j * 2, false);
    }

    public SyncAdapterType[] getSyncAdapterTypes() {
        Collection<SyncAdaptersCache.SyncAdapterInfo> allServices = this.mSyncAdapters.getAllServices();
        SyncAdapterType[] syncAdapterTypeArr = new SyncAdapterType[allServices.size()];
        int i = 0;
        for (SyncAdaptersCache.SyncAdapterInfo syncAdapterInfo : allServices) {
            syncAdapterTypeArr[i] = syncAdapterInfo.type;
            i++;
        }
        return syncAdapterTypeArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSyncAlarmMessage() {
        Log.v(TAG, "sending MESSAGE_SYNC_ALARM");
        this.mSyncHandler.sendEmptyMessage(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendCheckAlarmsMessage() {
        Log.v(TAG, "sending MESSAGE_CHECK_ALARMS");
        this.mSyncHandler.removeMessages(3);
        this.mSyncHandler.sendEmptyMessage(3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSyncFinishedOrCanceledMessage(ActiveSyncContext activeSyncContext, SyncResult syncResult) {
        Log.v(TAG, "sending MESSAGE_SYNC_FINISHED");
        Message obtainMessage = this.mSyncHandler.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.obj = new SyncHandlerMessagePayload(activeSyncContext, syncResult);
        this.mSyncHandler.sendMessage(obtainMessage);
    }

    private void sendCancelSyncsMessage(Account account, int i, String str) {
        Log.v(TAG, "sending MESSAGE_CANCEL");
        Message obtainMessage = this.mSyncHandler.obtainMessage();
        obtainMessage.what = 6;
        obtainMessage.obj = Pair.create(account, str);
        obtainMessage.arg1 = i;
        this.mSyncHandler.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class SyncHandlerMessagePayload {
        public final ActiveSyncContext activeSyncContext;
        public final SyncResult syncResult;

        SyncHandlerMessagePayload(ActiveSyncContext activeSyncContext, SyncResult syncResult) {
            this.activeSyncContext = activeSyncContext;
            this.syncResult = syncResult;
        }
    }

    /* loaded from: classes.dex */
    class SyncAlarmIntentReceiver extends BroadcastReceiver {
        SyncAlarmIntentReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            SyncManager.this.sendSyncAlarmMessage();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearBackoffSetting(SyncOperation syncOperation) {
        this.mSyncStorageEngine.setBackoff(syncOperation.account, syncOperation.userId, syncOperation.authority, -1L, -1L);
        synchronized (this.mSyncQueue) {
            this.mSyncQueue.onBackoffChanged(syncOperation.account, syncOperation.userId, syncOperation.authority, 0L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void increaseBackoffSetting(SyncOperation syncOperation) {
        long j;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Pair<Long, Long> backoff = this.mSyncStorageEngine.getBackoff(syncOperation.account, syncOperation.userId, syncOperation.authority);
        if (backoff == null) {
            j = -1;
        } else if (elapsedRealtime < ((Long) backoff.first).longValue()) {
            Log.v(TAG, "Still in backoff, do not increase it. Remaining: " + ((((Long) backoff.first).longValue() - elapsedRealtime) / 1000) + " seconds.");
            return;
        } else {
            j = ((Long) backoff.second).longValue() * 2;
        }
        if (j <= 0) {
            j = jitterize(C2664ab.f13498O, 33000L);
        }
        long j2 = j > WaitFor.ONE_HOUR ? 3600000L : j;
        long j3 = elapsedRealtime + j2;
        this.mSyncStorageEngine.setBackoff(syncOperation.account, syncOperation.userId, syncOperation.authority, j3, j2);
        syncOperation.backoff = Long.valueOf(j3);
        syncOperation.updateEffectiveRunTime();
        synchronized (this.mSyncQueue) {
            this.mSyncQueue.onBackoffChanged(syncOperation.account, syncOperation.userId, syncOperation.authority, j3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDelayUntilTime(SyncOperation syncOperation, long j) {
        long j2 = j * 1000;
        long currentTimeMillis = System.currentTimeMillis();
        long elapsedRealtime = j2 > currentTimeMillis ? SystemClock.elapsedRealtime() + (j2 - currentTimeMillis) : 0L;
        this.mSyncStorageEngine.setDelayUntilTime(syncOperation.account, syncOperation.userId, syncOperation.authority, elapsedRealtime);
        synchronized (this.mSyncQueue) {
            this.mSyncQueue.onDelayUntilTimeChanged(syncOperation.account, syncOperation.authority, elapsedRealtime);
        }
    }

    public void cancelActiveSync(Account account, int i, String str) {
        sendCancelSyncsMessage(account, i, str);
    }

    public void scheduleSyncOperation(SyncOperation syncOperation) {
        boolean add;
        synchronized (this.mSyncQueue) {
            add = this.mSyncQueue.add(syncOperation);
        }
        if (add) {
            Log.v(TAG, "scheduleSyncOperation: enqueued " + syncOperation);
            sendCheckAlarmsMessage();
            return;
        }
        Log.v(TAG, "scheduleSyncOperation: dropping duplicate sync operation " + syncOperation);
    }

    public void clearScheduledSyncOperations(Account account, int i, String str) {
        synchronized (this.mSyncQueue) {
            this.mSyncQueue.remove(account, i, str);
        }
        this.mSyncStorageEngine.setBackoff(account, i, str, -1L, -1L);
    }

    void maybeRescheduleSync(SyncResult syncResult, SyncOperation syncOperation) {
        Log.d(TAG, "encountered error(s) during the sync: " + syncResult + ", " + syncOperation);
        SyncOperation syncOperation2 = new SyncOperation(syncOperation);
        if (syncOperation2.extras.getBoolean("ignore_backoff", false)) {
            syncOperation2.extras.remove("ignore_backoff");
        }
        if (syncOperation2.extras.getBoolean("do_not_retry", false)) {
            Log.d(TAG, "not retrying sync operation because SYNC_EXTRAS_DO_NOT_RETRY was specified " + syncOperation2);
        } else if (syncOperation2.extras.getBoolean("upload", false) && !syncResult.syncAlreadyInProgress) {
            syncOperation2.extras.remove("upload");
            Log.d(TAG, "retrying sync operation as a two-way sync because an upload-only sync encountered an error: " + syncOperation2);
            scheduleSyncOperation(syncOperation2);
        } else if (syncResult.tooManyRetries) {
            Log.d(TAG, "not retrying sync operation because it retried too many times: " + syncOperation2);
        } else if (syncResult.madeSomeProgress()) {
            Log.d(TAG, "retrying sync operation because even though it had an error it achieved some success");
            scheduleSyncOperation(syncOperation2);
        } else if (syncResult.syncAlreadyInProgress) {
            Log.d(TAG, "retrying sync operation that failed because there was already a sync in progress: " + syncOperation2);
            scheduleSyncOperation(new SyncOperation(syncOperation2.account, syncOperation2.userId, syncOperation2.reason, syncOperation2.syncSource, syncOperation2.authority, syncOperation2.extras, 10000L, syncOperation2.flexTime, syncOperation2.backoff.longValue(), syncOperation2.delayUntil, syncOperation2.allowParallelSyncs));
        } else if (syncResult.hasSoftError()) {
            Log.d(TAG, "retrying sync operation because it encountered a soft error: " + syncOperation2);
            scheduleSyncOperation(syncOperation2);
        } else {
            Log.d(TAG, "not retrying sync operation because the error is a hard error: " + syncOperation2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUserStarting(int i) {
        this.mSyncAdapters.refreshServiceCache(null);
        updateRunningAccounts();
        synchronized (this.mSyncQueue) {
            this.mSyncQueue.addPendingOperations(i);
        }
        for (Account account : VAccountManagerService.get().getAccounts(i, null)) {
            scheduleSync(account, i, -8, null, null, 0L, 0L, true);
        }
        sendCheckAlarmsMessage();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUserStopping(int i) {
        updateRunningAccounts();
        cancelActiveSync(null, i, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUserRemoved(int i) {
        updateRunningAccounts();
        this.mSyncStorageEngine.doDatabaseCleanup(new Account[0], i);
        synchronized (this.mSyncQueue) {
            this.mSyncQueue.removeUser(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ActiveSyncContext extends ISyncContext.Stub implements ServiceConnection, IBinder.DeathRecipient {
        boolean mBound;
        final long mHistoryRowId;
        VSyncInfo mSyncInfo;
        final SyncOperation mSyncOperation;
        boolean mIsLinkedToDeath = false;
        ISyncAdapter mSyncAdapter = null;
        final long mStartTime = SystemClock.elapsedRealtime();
        long mTimeoutStartTime = this.mStartTime;

        @Override // android.content.ISyncContext
        public void sendHeartbeat() {
        }

        public ActiveSyncContext(SyncOperation syncOperation, long j) {
            this.mSyncOperation = syncOperation;
            this.mHistoryRowId = j;
        }

        @Override // android.content.ISyncContext
        public void onFinished(SyncResult syncResult) {
            Log.v(SyncManager.TAG, "onFinished: " + this);
            SyncManager.this.sendSyncFinishedOrCanceledMessage(this, syncResult);
        }

        public void toString(StringBuilder sb) {
            sb.append("startTime ");
            sb.append(this.mStartTime);
            sb.append(", mTimeoutStartTime ");
            sb.append(this.mTimeoutStartTime);
            sb.append(", mHistoryRowId ");
            sb.append(this.mHistoryRowId);
            sb.append(", syncOperation ");
            sb.append(this.mSyncOperation);
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Message obtainMessage = SyncManager.this.mSyncHandler.obtainMessage();
            obtainMessage.what = 4;
            obtainMessage.obj = new ServiceConnectionData(this, ISyncAdapter.Stub.asInterface(iBinder));
            SyncManager.this.mSyncHandler.sendMessage(obtainMessage);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Message obtainMessage = SyncManager.this.mSyncHandler.obtainMessage();
            obtainMessage.what = 5;
            obtainMessage.obj = new ServiceConnectionData(this, null);
            SyncManager.this.mSyncHandler.sendMessage(obtainMessage);
        }

        boolean bindToSyncAdapter(SyncAdaptersCache.SyncAdapterInfo syncAdapterInfo, int i) {
            Log.d(SyncManager.TAG, "bindToSyncAdapter: " + syncAdapterInfo.serviceInfo + ", connection " + this);
            Intent intent = new Intent();
            intent.setAction("android.content.SyncAdapter");
            intent.setComponent(syncAdapterInfo.componentName);
            this.mBound = true;
            boolean bindServiceAsUser = VActivityManagerService.get().bindServiceAsUser(intent, this, 21, new VUserHandle(this.mSyncOperation.userId));
            if (!bindServiceAsUser) {
                this.mBound = false;
            }
            return bindServiceAsUser;
        }

        protected void close() {
            Log.d(SyncManager.TAG, "unBindFromSyncAdapter: connection " + this);
            if (this.mBound) {
                this.mBound = false;
                SyncManager.this.mContext.unbindService(this);
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            toString(sb);
            return sb.toString();
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            SyncManager.this.sendSyncFinishedOrCanceledMessage(this, null);
        }
    }

    static String formatTime(long j) {
        Time time = new Time();
        time.set(j);
        return time.format("%Y-%m-%d %H:%M:%S");
    }

    /* loaded from: classes.dex */
    private class SyncTimeTracker {
        boolean mLastWasSyncing;
        private long mTimeSpentSyncing;
        long mWhenSyncStarted;

        private SyncTimeTracker() {
            this.mLastWasSyncing = false;
            this.mWhenSyncStarted = 0L;
        }

        public synchronized void update() {
            boolean z = !SyncManager.this.mActiveSyncContexts.isEmpty();
            if (z != this.mLastWasSyncing) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (z) {
                    this.mWhenSyncStarted = elapsedRealtime;
                } else {
                    this.mTimeSpentSyncing += elapsedRealtime - this.mWhenSyncStarted;
                }
                this.mLastWasSyncing = z;
            }
        }

        public synchronized long timeSpentSyncing() {
            if (!this.mLastWasSyncing) {
                return this.mTimeSpentSyncing;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            return this.mTimeSpentSyncing + (elapsedRealtime - this.mWhenSyncStarted);
        }
    }

    /* loaded from: classes.dex */
    class ServiceConnectionData {
        public final ActiveSyncContext activeSyncContext;
        public final ISyncAdapter syncAdapter;

        ServiceConnectionData(ActiveSyncContext activeSyncContext, ISyncAdapter iSyncAdapter) {
            this.activeSyncContext = activeSyncContext;
            this.syncAdapter = iSyncAdapter;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class SyncHandler extends Handler {
        private static final int MESSAGE_CANCEL = 6;
        private static final int MESSAGE_CHECK_ALARMS = 3;
        private static final int MESSAGE_SERVICE_CONNECTED = 4;
        private static final int MESSAGE_SERVICE_DISCONNECTED = 5;
        private static final int MESSAGE_SYNC_ALARM = 2;
        private static final int MESSAGE_SYNC_FINISHED = 1;
        public final SyncTimeTracker mSyncTimeTracker;
        public final SyncNotificationInfo mSyncNotificationInfo = new SyncNotificationInfo();
        private Long mAlarmScheduleTime = null;
        private List<Message> mBootQueue = new ArrayList();

        private void sendSyncStateIntent() {
        }

        public void onBootCompleted() {
            Log.v(SyncManager.TAG, "Boot completed, clearing boot queue.");
            SyncManager.this.doDatabaseCleanup();
            synchronized (this) {
                for (Message message : this.mBootQueue) {
                    sendMessage(message);
                }
                this.mBootQueue = null;
                SyncManager.this.mBootCompleted = true;
            }
        }

        private boolean tryEnqueueMessageUntilReadyToRun(Message message) {
            synchronized (this) {
                if (SyncManager.this.mBootCompleted) {
                    return false;
                }
                this.mBootQueue.add(Message.obtain(message));
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class SyncNotificationInfo {
            public boolean isActive = false;
            public Long startTime = null;

            SyncNotificationInfo() {
            }

            public void toString(StringBuilder sb) {
                sb.append("isActive ");
                sb.append(this.isActive);
                sb.append(", startTime ");
                sb.append(this.startTime);
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                toString(sb);
                return sb.toString();
            }
        }

        public SyncHandler(Looper looper) {
            super(looper);
            this.mSyncTimeTracker = new SyncTimeTracker();
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Throwable th;
            long j;
            if (!tryEnqueueMessageUntilReadyToRun(message)) {
                long j2 = cjm.f20759b;
                try {
                    SyncManager.this.mDataConnectionIsConnected = SyncManager.this.readDataConnectionState();
                    j = scheduleReadyPeriodicSyncs();
                } catch (Throwable th2) {
                    th = th2;
                    j = Long.MAX_VALUE;
                }
                try {
                    switch (message.what) {
                        case 1:
                            Log.v(SyncManager.TAG, "handleSyncHandlerMessage: MESSAGE_SYNC_FINISHED");
                            SyncHandlerMessagePayload syncHandlerMessagePayload = (SyncHandlerMessagePayload) message.obj;
                            if (SyncManager.this.isSyncStillActive(syncHandlerMessagePayload.activeSyncContext)) {
                                runSyncFinishedOrCanceledLocked(syncHandlerMessagePayload.syncResult, syncHandlerMessagePayload.activeSyncContext);
                                j2 = maybeStartNextSyncLocked();
                                break;
                            } else {
                                Log.d(SyncManager.TAG, "handleSyncHandlerMessage: dropping since the sync is no longer active: " + syncHandlerMessagePayload.activeSyncContext);
                                break;
                            }
                        case 2:
                            Log.v(SyncManager.TAG, "handleSyncHandlerMessage: MESSAGE_SYNC_ALARM");
                            this.mAlarmScheduleTime = null;
                            j2 = maybeStartNextSyncLocked();
                            break;
                        case 3:
                            Log.v(SyncManager.TAG, "handleSyncHandlerMessage: MESSAGE_CHECK_ALARMS");
                            j2 = maybeStartNextSyncLocked();
                            break;
                        case 4:
                            ServiceConnectionData serviceConnectionData = (ServiceConnectionData) message.obj;
                            Log.d(SyncManager.TAG, "handleSyncHandlerMessage: MESSAGE_SERVICE_CONNECTED: " + serviceConnectionData.activeSyncContext);
                            if (SyncManager.this.isSyncStillActive(serviceConnectionData.activeSyncContext)) {
                                runBoundToSyncAdapter(serviceConnectionData.activeSyncContext, serviceConnectionData.syncAdapter);
                                break;
                            }
                            break;
                        case 5:
                            ActiveSyncContext activeSyncContext = ((ServiceConnectionData) message.obj).activeSyncContext;
                            Log.d(SyncManager.TAG, "handleSyncHandlerMessage: MESSAGE_SERVICE_DISCONNECTED: " + activeSyncContext);
                            if (SyncManager.this.isSyncStillActive(activeSyncContext)) {
                                if (activeSyncContext.mSyncAdapter != null) {
                                    try {
                                        activeSyncContext.mSyncAdapter.cancelSync(activeSyncContext);
                                    } catch (RemoteException unused) {
                                    }
                                }
                                SyncResult syncResult = new SyncResult();
                                syncResult.stats.numIoExceptions++;
                                runSyncFinishedOrCanceledLocked(syncResult, activeSyncContext);
                                j2 = maybeStartNextSyncLocked();
                                break;
                            }
                            break;
                        case 6:
                            Pair pair = (Pair) message.obj;
                            Log.d(SyncManager.TAG, "handleSyncHandlerMessage: MESSAGE_SERVICE_CANCEL: " + pair.first + ", " + ((String) pair.second));
                            cancelActiveSyncLocked((Account) pair.first, message.arg1, (String) pair.second);
                            j2 = maybeStartNextSyncLocked();
                            break;
                    }
                    manageSyncNotificationLocked();
                    manageSyncAlarmLocked(j, j2);
                    this.mSyncTimeTracker.update();
                } catch (Throwable th3) {
                    th = th3;
                    manageSyncNotificationLocked();
                    manageSyncAlarmLocked(j, cjm.f20759b);
                    this.mSyncTimeTracker.update();
                    throw th;
                }
            }
        }

        private long scheduleReadyPeriodicSyncs() {
            int i;
            boolean z;
            long j;
            SyncHandler syncHandler = this;
            Log.v(SyncManager.TAG, "scheduleReadyPeriodicSyncs");
            boolean backgroundDataSetting = SyncManager.this.getConnectivityManager().getBackgroundDataSetting();
            long j2 = cjm.f20759b;
            if (!backgroundDataSetting) {
                return cjm.f20759b;
            }
            AccountAndUser[] accountAndUserArr = SyncManager.this.mRunningAccounts;
            long currentTimeMillis = System.currentTimeMillis();
            long j3 = 0;
            long j4 = 0 < currentTimeMillis - ((long) SyncManager.this.mSyncRandomOffsetMillis) ? currentTimeMillis - SyncManager.this.mSyncRandomOffsetMillis : 0L;
            Iterator<Pair<SyncStorageEngine.AuthorityInfo, SyncStatusInfo>> it = SyncManager.this.mSyncStorageEngine.getCopyOfAllAuthoritiesWithSyncStatus().iterator();
            long j5 = Long.MAX_VALUE;
            while (it.hasNext()) {
                Pair<SyncStorageEngine.AuthorityInfo, SyncStatusInfo> next = it.next();
                SyncStorageEngine.AuthorityInfo authorityInfo = (SyncStorageEngine.AuthorityInfo) next.first;
                SyncStatusInfo syncStatusInfo = (SyncStatusInfo) next.second;
                if (TextUtils.isEmpty(authorityInfo.authority)) {
                    Log.e(SyncManager.TAG, "Got an empty provider string. Skipping: " + authorityInfo);
                    accountAndUserArr = accountAndUserArr;
                    currentTimeMillis = currentTimeMillis;
                    j4 = j4;
                    j3 = j3;
                    it = it;
                } else if (!SyncManager.this.containsAccountAndUser(accountAndUserArr, authorityInfo.account, authorityInfo.userId)) {
                    accountAndUserArr = accountAndUserArr;
                    currentTimeMillis = currentTimeMillis;
                    j4 = j4;
                    j3 = j3;
                    it = it;
                } else if (!SyncManager.this.mSyncStorageEngine.getMasterSyncAutomatically(authorityInfo.userId)) {
                    accountAndUserArr = accountAndUserArr;
                    currentTimeMillis = currentTimeMillis;
                    j4 = j4;
                    j3 = j3;
                    it = it;
                } else if (!SyncManager.this.mSyncStorageEngine.getSyncAutomatically(authorityInfo.account, authorityInfo.userId, authorityInfo.authority)) {
                    accountAndUserArr = accountAndUserArr;
                    currentTimeMillis = currentTimeMillis;
                    j4 = j4;
                    it = it;
                    j3 = 0;
                } else if (SyncManager.this.getIsSyncable(authorityInfo.account, authorityInfo.userId, authorityInfo.authority) == 0) {
                    accountAndUserArr = accountAndUserArr;
                    currentTimeMillis = currentTimeMillis;
                    j4 = j4;
                    it = it;
                    j3 = 0;
                } else {
                    int size = authorityInfo.periodicSyncs.size();
                    for (int i2 = 0; i2 < size; i2 = i + 1) {
                        PeriodicSync periodicSync = authorityInfo.periodicSyncs.get(i2);
                        Bundle bundle = periodicSync.extras;
                        long j6 = periodicSync.period * 1000;
                        long j7 = p110z1.PeriodicSync.flexTime.get(periodicSync) * 1000;
                        if (j6 <= 0) {
                            accountAndUserArr = accountAndUserArr;
                            j4 = j4;
                            size = size;
                            it = it;
                            j5 = j5;
                            syncStatusInfo = syncStatusInfo;
                            currentTimeMillis = currentTimeMillis;
                            i = i2;
                        } else {
                            size = size;
                            it = it;
                            long periodicSyncTime = syncStatusInfo.getPeriodicSyncTime(i2);
                            j5 = j5;
                            long j8 = j6 - (j4 % j6);
                            long j9 = currentTimeMillis - periodicSyncTime;
                            if (j8 > j7 || j9 <= j6 - j7) {
                                accountAndUserArr = accountAndUserArr;
                                syncStatusInfo = syncStatusInfo;
                                z = false;
                            } else {
                                accountAndUserArr = accountAndUserArr;
                                syncStatusInfo = syncStatusInfo;
                                z = true;
                            }
                            Log.v(SyncManager.TAG, "sync: " + i2 + " for " + authorityInfo.authority + ". period: " + j6 + " flex: " + j7 + " remaining: " + j8 + " time_since_last: " + j9 + " last poll absol: " + periodicSyncTime + " shifted now: " + j4 + " run_early: " + z);
                            if (z || j8 == j6 || periodicSyncTime > currentTimeMillis || j9 >= j6) {
                                Pair<Long, Long> backoff = SyncManager.this.mSyncStorageEngine.getBackoff(authorityInfo.account, authorityInfo.userId, authorityInfo.authority);
                                SyncAdaptersCache.SyncAdapterInfo serviceInfo = SyncManager.this.mSyncAdapters.getServiceInfo(authorityInfo.account, authorityInfo.authority);
                                if (serviceInfo == null) {
                                    j4 = j4;
                                    currentTimeMillis = currentTimeMillis;
                                    i = i2;
                                } else {
                                    j4 = j4;
                                    currentTimeMillis = currentTimeMillis;
                                    SyncManager.this.mSyncStorageEngine.setPeriodicSyncTime(authorityInfo.ident, authorityInfo.periodicSyncs.get(i2), currentTimeMillis);
                                    i = i2;
                                    j = j8;
                                    SyncManager.this.scheduleSyncOperation(new SyncOperation(authorityInfo.account, authorityInfo.userId, -4, 4, authorityInfo.authority, bundle, 0L, 0L, backoff != null ? ((Long) backoff.first).longValue() : 0L, SyncManager.this.mSyncStorageEngine.getDelayUntilTime(authorityInfo.account, authorityInfo.userId, authorityInfo.authority), serviceInfo.type.allowParallelSyncs()));
                                }
                            } else {
                                j4 = j4;
                                j = j8;
                                currentTimeMillis = currentTimeMillis;
                                i = i2;
                            }
                            j5 = z ? currentTimeMillis + j6 + j : currentTimeMillis + j;
                            if (j5 < j5) {
                            }
                        }
                    }
                    j3 = 0;
                    syncHandler = this;
                    j2 = cjm.f20759b;
                }
                syncHandler = this;
                j2 = cjm.f20759b;
            }
            long j10 = j3;
            if (j5 == j2) {
                return j2;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (j5 >= currentTimeMillis) {
                j10 = j5 - currentTimeMillis;
            }
            return elapsedRealtime + j10;
        }

        /* JADX WARN: Code restructure failed: missing block: B:48:0x0198, code lost:
            if (r2.mStartTime > r3.mStartTime) goto L_0x019f;
         */
        /* JADX WARN: Removed duplicated region for block: B:108:0x0342  */
        /* JADX WARN: Removed duplicated region for block: B:109:0x0350  */
        /* JADX WARN: Removed duplicated region for block: B:125:0x0359 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private long maybeStartNextSyncLocked() {
            /*
                Method dump skipped, instructions count: 894
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lody.virtual.server.content.SyncManager.SyncHandler.maybeStartNextSyncLocked():long");
        }

        private boolean dispatchSyncOperation(SyncOperation syncOperation) {
            Log.v(SyncManager.TAG, "dispatchSyncOperation: we are going to sync " + syncOperation);
            Log.v(SyncManager.TAG, "num active syncs: " + SyncManager.this.mActiveSyncContexts.size());
            Iterator<ActiveSyncContext> it = SyncManager.this.mActiveSyncContexts.iterator();
            while (it.hasNext()) {
                Log.v(SyncManager.TAG, it.next().toString());
            }
            SyncAdaptersCache.SyncAdapterInfo serviceInfo = SyncManager.this.mSyncAdapters.getServiceInfo(syncOperation.account, syncOperation.authority);
            if (serviceInfo == null) {
                Log.d(SyncManager.TAG, "can't find a sync adapter for " + syncOperation.authority + ", removing settings for it");
                SyncManager.this.mSyncStorageEngine.removeAuthority(syncOperation.account, syncOperation.userId, syncOperation.authority);
                return false;
            }
            ActiveSyncContext activeSyncContext = new ActiveSyncContext(syncOperation, insertStartSyncEvent(syncOperation));
            activeSyncContext.mSyncInfo = SyncManager.this.mSyncStorageEngine.addActiveSync(activeSyncContext);
            SyncManager.this.mActiveSyncContexts.add(activeSyncContext);
            Log.v(SyncManager.TAG, "dispatchSyncOperation: starting " + activeSyncContext);
            if (activeSyncContext.bindToSyncAdapter(serviceInfo, syncOperation.userId)) {
                return true;
            }
            Log.e(SyncManager.TAG, "Bind attempt failed to " + serviceInfo);
            closeActiveSyncContext(activeSyncContext);
            return false;
        }

        private void runBoundToSyncAdapter(ActiveSyncContext activeSyncContext, ISyncAdapter iSyncAdapter) {
            activeSyncContext.mSyncAdapter = iSyncAdapter;
            SyncOperation syncOperation = activeSyncContext.mSyncOperation;
            try {
                activeSyncContext.mIsLinkedToDeath = true;
                iSyncAdapter.asBinder().linkToDeath(activeSyncContext, 0);
                iSyncAdapter.startSync(activeSyncContext, syncOperation.authority, syncOperation.account, syncOperation.extras);
            } catch (RemoteException e) {
                Log.d(SyncManager.TAG, "maybeStartNextSync: caught a RemoteException, rescheduling", e);
                closeActiveSyncContext(activeSyncContext);
                SyncManager.this.increaseBackoffSetting(syncOperation);
                SyncManager.this.scheduleSyncOperation(new SyncOperation(syncOperation));
            } catch (RuntimeException e2) {
                closeActiveSyncContext(activeSyncContext);
                Log.e(SyncManager.TAG, "Caught RuntimeException while starting the sync " + syncOperation, e2);
            }
        }

        private void cancelActiveSyncLocked(Account account, int i, String str) {
            Iterator it = new ArrayList(SyncManager.this.mActiveSyncContexts).iterator();
            while (it.hasNext()) {
                ActiveSyncContext activeSyncContext = (ActiveSyncContext) it.next();
                if (activeSyncContext != null && (account == null || account.equals(activeSyncContext.mSyncOperation.account))) {
                    if (str == null || str.equals(activeSyncContext.mSyncOperation.authority)) {
                        if (i == -1 || i == activeSyncContext.mSyncOperation.userId) {
                            runSyncFinishedOrCanceledLocked(null, activeSyncContext);
                        }
                    }
                }
            }
        }

        private void runSyncFinishedOrCanceledLocked(SyncResult syncResult, ActiveSyncContext activeSyncContext) {
            String str;
            if (activeSyncContext.mIsLinkedToDeath) {
                activeSyncContext.mSyncAdapter.asBinder().unlinkToDeath(activeSyncContext, 0);
                activeSyncContext.mIsLinkedToDeath = false;
            }
            closeActiveSyncContext(activeSyncContext);
            SyncOperation syncOperation = activeSyncContext.mSyncOperation;
            long elapsedRealtime = SystemClock.elapsedRealtime() - activeSyncContext.mStartTime;
            if (syncResult != null) {
                Log.v(SyncManager.TAG, "runSyncFinishedOrCanceled [finished]: " + syncOperation + ", result " + syncResult);
                if (!syncResult.hasError()) {
                    str = "success";
                    SyncManager.this.clearBackoffSetting(syncOperation);
                } else {
                    Log.d(SyncManager.TAG, "failed sync operation " + syncOperation + ", " + syncResult);
                    if (!syncResult.syncAlreadyInProgress) {
                        SyncManager.this.increaseBackoffSetting(syncOperation);
                    }
                    SyncManager.this.maybeRescheduleSync(syncResult, syncOperation);
                    str = ContentResolverCompat.syncErrorToString(syncResultToErrorNumber(syncResult));
                }
                SyncManager.this.setDelayUntilTime(syncOperation, syncResult.delayUntil);
            } else {
                Log.v(SyncManager.TAG, "runSyncFinishedOrCanceled [canceled]: " + syncOperation);
                if (activeSyncContext.mSyncAdapter != null) {
                    try {
                        activeSyncContext.mSyncAdapter.cancelSync(activeSyncContext);
                    } catch (RemoteException unused) {
                    }
                }
                str = "canceled";
            }
            stopSyncEvent(activeSyncContext.mHistoryRowId, syncOperation, str, 0, 0, elapsedRealtime);
            if (syncResult != null && syncResult.fullSyncRequested) {
                SyncManager.this.scheduleSyncOperation(new SyncOperation(syncOperation.account, syncOperation.userId, syncOperation.reason, syncOperation.syncSource, syncOperation.authority, new Bundle(), 0L, 0L, syncOperation.backoff.longValue(), syncOperation.delayUntil, syncOperation.allowParallelSyncs));
            }
        }

        private void closeActiveSyncContext(ActiveSyncContext activeSyncContext) {
            activeSyncContext.close();
            SyncManager.this.mActiveSyncContexts.remove(activeSyncContext);
            SyncManager.this.mSyncStorageEngine.removeActiveSync(activeSyncContext.mSyncInfo, activeSyncContext.mSyncOperation.userId);
        }

        private int syncResultToErrorNumber(SyncResult syncResult) {
            if (syncResult.syncAlreadyInProgress) {
                return 1;
            }
            if (syncResult.stats.numAuthExceptions > 0) {
                return 2;
            }
            if (syncResult.stats.numIoExceptions > 0) {
                return 3;
            }
            if (syncResult.stats.numParseExceptions > 0) {
                return 4;
            }
            if (syncResult.stats.numConflictDetectedExceptions > 0) {
                return 5;
            }
            if (syncResult.tooManyDeletions) {
                return 6;
            }
            if (syncResult.tooManyRetries) {
                return 7;
            }
            if (syncResult.databaseError) {
                return 8;
            }
            throw new IllegalStateException("we are not in an error state, " + syncResult);
        }

        private void manageSyncNotificationLocked() {
            boolean z;
            boolean z2;
            if (!SyncManager.this.mActiveSyncContexts.isEmpty()) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (this.mSyncNotificationInfo.startTime == null) {
                    this.mSyncNotificationInfo.startTime = Long.valueOf(elapsedRealtime);
                }
                if (!this.mSyncNotificationInfo.isActive) {
                    if (!(elapsedRealtime > this.mSyncNotificationInfo.startTime.longValue() + SyncManager.SYNC_NOTIFICATION_DELAY)) {
                        Iterator<ActiveSyncContext> it = SyncManager.this.mActiveSyncContexts.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                if (it.next().mSyncOperation.extras.getBoolean("force", false)) {
                                    z2 = false;
                                    z = true;
                                    break;
                                }
                            } else {
                                z2 = false;
                                z = false;
                                break;
                            }
                        }
                    } else {
                        z2 = false;
                        z = true;
                    }
                } else {
                    z2 = false;
                    z = false;
                }
            } else {
                SyncNotificationInfo syncNotificationInfo = this.mSyncNotificationInfo;
                syncNotificationInfo.startTime = null;
                z2 = syncNotificationInfo.isActive;
                z = false;
            }
            if (z2 && !z) {
                sendSyncStateIntent();
                this.mSyncNotificationInfo.isActive = false;
            }
            if (z) {
                sendSyncStateIntent();
                this.mSyncNotificationInfo.isActive = true;
            }
        }

        private void manageSyncAlarmLocked(long j, long j2) {
            if (SyncManager.this.mDataConnectionIsConnected && !SyncManager.this.mStorageIsLow) {
                long longValue = (SyncManager.this.mSyncHandler.mSyncNotificationInfo.isActive || SyncManager.this.mSyncHandler.mSyncNotificationInfo.startTime == null) ? cjm.f20759b : SyncManager.this.mSyncHandler.mSyncNotificationInfo.startTime.longValue() + SyncManager.SYNC_NOTIFICATION_DELAY;
                Iterator<ActiveSyncContext> it = SyncManager.this.mActiveSyncContexts.iterator();
                long j3 = cjm.f20759b;
                while (it.hasNext()) {
                    long j4 = it.next().mTimeoutStartTime + SyncManager.MAX_TIME_PER_SYNC;
                    Log.v(SyncManager.TAG, "manageSyncAlarm: active sync, mTimeoutStartTime + MAX is " + j4);
                    if (j3 > j4) {
                        j3 = j4;
                    }
                }
                Log.v(SyncManager.TAG, "manageSyncAlarm: notificationTime is " + longValue);
                Log.v(SyncManager.TAG, "manageSyncAlarm: earliestTimeoutTime is " + j3);
                Log.v(SyncManager.TAG, "manageSyncAlarm: nextPeriodicEventElapsedTime is " + j);
                Log.v(SyncManager.TAG, "manageSyncAlarm: nextPendingEventElapsedTime is " + j2);
                long min = Math.min(Math.min(Math.min(longValue, j3), j), j2);
                long elapsedRealtime = SystemClock.elapsedRealtime();
                long j5 = C2664ab.f13498O + elapsedRealtime;
                if (min < j5) {
                    Log.v(SyncManager.TAG, "manageSyncAlarm: the alarmTime is too small, " + min + ", setting to " + j5);
                    min = j5;
                } else {
                    long j6 = SyncManager.SYNC_ALARM_TIMEOUT_MAX + elapsedRealtime;
                    if (min > j6) {
                        Log.v(SyncManager.TAG, "manageSyncAlarm: the alarmTime is too large, " + min + ", setting to " + j5);
                        min = j6;
                    }
                }
                Long l = this.mAlarmScheduleTime;
                boolean z = false;
                boolean z2 = l != null && elapsedRealtime < l.longValue();
                if (min != cjm.f20759b) {
                    if (!z2 || min < this.mAlarmScheduleTime.longValue()) {
                        z2 = false;
                        z = true;
                    } else {
                        z2 = false;
                    }
                }
                SyncManager.this.ensureAlarmService();
                if (z) {
                    Log.v(SyncManager.TAG, "requesting that the alarm manager wake us up at elapsed time " + min + ", now is " + elapsedRealtime + ", " + ((min - elapsedRealtime) / 1000) + " secs from now");
                    this.mAlarmScheduleTime = Long.valueOf(min);
                    SyncManager.this.mAlarmService.setExact(2, min, SyncManager.this.mSyncAlarmIntent);
                } else if (z2) {
                    this.mAlarmScheduleTime = null;
                    SyncManager.this.mAlarmService.cancel(SyncManager.this.mSyncAlarmIntent);
                }
            }
        }

        public long insertStartSyncEvent(SyncOperation syncOperation) {
            int i = syncOperation.syncSource;
            return SyncManager.this.mSyncStorageEngine.insertStartSyncEvent(syncOperation.account, syncOperation.userId, syncOperation.reason, syncOperation.authority, System.currentTimeMillis(), i, syncOperation.isInitialization(), syncOperation.extras);
        }

        public void stopSyncEvent(long j, SyncOperation syncOperation, String str, int i, int i2, long j2) {
            SyncManager.this.mSyncStorageEngine.stopSyncEvent(j, j2, str, i2, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSyncStillActive(ActiveSyncContext activeSyncContext) {
        Iterator<ActiveSyncContext> it = this.mActiveSyncContexts.iterator();
        while (it.hasNext()) {
            if (it.next() == activeSyncContext) {
                return true;
            }
        }
        return false;
    }
}
