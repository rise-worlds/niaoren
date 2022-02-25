package com.lody.virtual.server.notification;

import android.app.NotificationManager;
import android.content.Context;
import android.text.TextUtils;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.lody.virtual.helper.utils.Singleton;
import com.lody.virtual.helper.utils.VLog;
import com.lody.virtual.server.interfaces.INotificationManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class VNotificationManagerService extends INotificationManager.Stub {
    private Context mContext;
    private NotificationManager mNotificationManager;
    private static final Singleton<VNotificationManagerService> gService = new Singleton<VNotificationManagerService>() { // from class: com.lody.virtual.server.notification.VNotificationManagerService.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.lody.virtual.helper.utils.Singleton
        public VNotificationManagerService create() {
            return new VNotificationManagerService();
        }
    };
    static final String TAG = NotificationCompat.class.getSimpleName();
    private final List<String> mDisables = new ArrayList();
    private final HashMap<String, List<NotificationInfo>> mNotifications = new HashMap<>();

    @Override // com.lody.virtual.server.interfaces.INotificationManager
    public int dealNotificationId(int i, String str, String str2, int i2) {
        return i;
    }

    private void init(Context context) {
        this.mContext = context;
        this.mNotificationManager = (NotificationManager) context.getSystemService(ServiceManagerNative.NOTIFICATION);
    }

    public static void systemReady(Context context) {
        get().init(context);
    }

    public static VNotificationManagerService get() {
        return gService.get();
    }

    @Override // com.lody.virtual.server.interfaces.INotificationManager
    public String dealNotificationTag(int i, String str, String str2, int i2) {
        if (TextUtils.equals(this.mContext.getPackageName(), str)) {
            return str2;
        }
        if (str2 == null) {
            return str + "@" + i2;
        }
        return str + ":" + str2 + "@" + i2;
    }

    @Override // com.lody.virtual.server.interfaces.INotificationManager
    public boolean areNotificationsEnabledForPackage(String str, int i) {
        List<String> list = this.mDisables;
        return !list.contains(str + ":" + i);
    }

    @Override // com.lody.virtual.server.interfaces.INotificationManager
    public void setNotificationsEnabledForPackage(String str, boolean z, int i) {
        String str2 = str + ":" + i;
        if (z) {
            if (this.mDisables.contains(str2)) {
                this.mDisables.remove(str2);
            }
        } else if (!this.mDisables.contains(str2)) {
            this.mDisables.add(str2);
        }
    }

    @Override // com.lody.virtual.server.interfaces.INotificationManager
    public void addNotification(int i, String str, String str2, int i2) {
        NotificationInfo notificationInfo = new NotificationInfo(i, str, str2, i2);
        synchronized (this.mNotifications) {
            List<NotificationInfo> list = this.mNotifications.get(str2);
            if (list == null) {
                list = new ArrayList<>();
                this.mNotifications.put(str2, list);
            }
            if (!list.contains(notificationInfo)) {
                list.add(notificationInfo);
            }
        }
    }

    @Override // com.lody.virtual.server.interfaces.INotificationManager
    public void cancelAllNotification(String str, int i) {
        ArrayList<NotificationInfo> arrayList = new ArrayList();
        synchronized (this.mNotifications) {
            List<NotificationInfo> list = this.mNotifications.get(str);
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    NotificationInfo notificationInfo = list.get(size);
                    if (notificationInfo.userId == i) {
                        arrayList.add(notificationInfo);
                        list.remove(size);
                    }
                }
            }
        }
        for (NotificationInfo notificationInfo2 : arrayList) {
            VLog.m18993d(TAG, "cancel " + notificationInfo2.tag + ExpandableTextView.f6958c + notificationInfo2.f10503id, new Object[0]);
            this.mNotificationManager.cancel(notificationInfo2.tag, notificationInfo2.f10503id);
        }
    }

    /* loaded from: classes.dex */
    private static class NotificationInfo {

        /* renamed from: id */
        int f10503id;
        String packageName;
        String tag;
        int userId;

        NotificationInfo(int i, String str, String str2, int i2) {
            this.f10503id = i;
            this.tag = str;
            this.packageName = str2;
            this.userId = i2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof NotificationInfo)) {
                return super.equals(obj);
            }
            NotificationInfo notificationInfo = (NotificationInfo) obj;
            return notificationInfo.f10503id == this.f10503id && TextUtils.equals(notificationInfo.tag, this.tag) && TextUtils.equals(this.packageName, notificationInfo.packageName) && notificationInfo.userId == this.userId;
        }
    }
}
