package com.lody.virtual.server.p062am;

import android.app.IServiceConnection;
import android.app.Notification;
import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.lody.virtual.server.am.ServiceRecord */
/* loaded from: classes.dex */
public class ServiceRecord extends Binder {
    long activeSince;
    final List<IntentBindRecord> bindings = new ArrayList();
    int foregroundId;
    Notification foregroundNoti;
    long lastActivityTime;
    ProcessRecord process;
    ServiceInfo serviceInfo;
    int startId;

    public boolean containConnection(IServiceConnection iServiceConnection) {
        synchronized (this.bindings) {
            for (IntentBindRecord intentBindRecord : this.bindings) {
                if (intentBindRecord.containConnectionLocked(iServiceConnection)) {
                    return true;
                }
            }
            return false;
        }
    }

    public int getClientCount() {
        int size;
        synchronized (this.bindings) {
            size = this.bindings.size();
        }
        return size;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getConnectionCountLocked() {
        int i = 0;
        for (IntentBindRecord intentBindRecord : this.bindings) {
            i += intentBindRecord.getConnectionCount();
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IntentBindRecord peekBindingLocked(Intent intent) {
        for (IntentBindRecord intentBindRecord : this.bindings) {
            if (intentBindRecord.intent.filterEquals(intent)) {
                return intentBindRecord;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addToBoundIntentLocked(Intent intent, IServiceConnection iServiceConnection) {
        IntentBindRecord peekBindingLocked = peekBindingLocked(intent);
        if (peekBindingLocked == null) {
            peekBindingLocked = new IntentBindRecord();
            peekBindingLocked.intent = intent;
            this.bindings.add(peekBindingLocked);
        }
        peekBindingLocked.addConnectionLocked(iServiceConnection);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.lody.virtual.server.am.ServiceRecord$IntentBindRecord */
    /* loaded from: classes.dex */
    public static class IntentBindRecord {
        IBinder binder;
        final List<IServiceConnection> connections = new ArrayList();
        boolean doRebind = false;
        Intent intent;

        IntentBindRecord() {
        }

        public boolean containConnectionLocked(IServiceConnection iServiceConnection) {
            for (IServiceConnection iServiceConnection2 : this.connections) {
                if (iServiceConnection2.asBinder() == iServiceConnection.asBinder()) {
                    return true;
                }
            }
            return false;
        }

        void addConnectionLocked(IServiceConnection iServiceConnection) {
            if (!containConnectionLocked(iServiceConnection)) {
                this.connections.add(iServiceConnection);
                try {
                    iServiceConnection.asBinder().linkToDeath(new DeathRecipient(this, iServiceConnection), 0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void removeConnectionLocked(IServiceConnection iServiceConnection) {
            Iterator<IServiceConnection> it = this.connections.iterator();
            while (it.hasNext()) {
                if (it.next().asBinder() == iServiceConnection.asBinder()) {
                    it.remove();
                }
            }
        }

        int getConnectionCount() {
            int size;
            synchronized (this.connections) {
                size = this.connections.size();
            }
            return size;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.lody.virtual.server.am.ServiceRecord$DeathRecipient */
    /* loaded from: classes.dex */
    public static class DeathRecipient implements IBinder.DeathRecipient {
        private final IntentBindRecord bindRecord;
        private final IServiceConnection connection;

        private DeathRecipient(IntentBindRecord intentBindRecord, IServiceConnection iServiceConnection) {
            this.bindRecord = intentBindRecord;
            this.connection = iServiceConnection;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            synchronized (this.bindRecord.connections) {
                this.bindRecord.removeConnectionLocked(this.connection);
            }
            this.connection.asBinder().unlinkToDeath(this, 0);
        }
    }
}
