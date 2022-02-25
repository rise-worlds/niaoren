package com.lody.virtual.server.p062am;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.os.Binder;
import android.os.ConditionVariable;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Process;
import com.lody.virtual.client.IVClient;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.stub.StubManifest;
import com.lody.virtual.p061os.VUserHandle;
import com.lody.virtual.remote.ClientConfig;
import com.lody.virtual.server.bit64.V64BitHelper;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.lody.virtual.server.am.ProcessRecord */
/* loaded from: classes.dex */
public final class ProcessRecord extends Binder {
    public IInterface appThread;
    public int callingVUid;
    public IVClient client;
    public final ApplicationInfo info;
    public boolean is64bit;
    public int pid;
    public final String processName;
    public int userId;
    public int vpid;
    public int vuid;
    final Set<String> pkgList = Collections.synchronizedSet(new HashSet());
    public ConditionVariable initLock = new ConditionVariable();
    public final ServiceConnection conn = new ServiceConnection() { // from class: com.lody.virtual.server.am.ProcessRecord.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };

    public ProcessRecord(ApplicationInfo applicationInfo, String str, int i, int i2, int i3, boolean z) {
        this.info = applicationInfo;
        this.vuid = i;
        this.vpid = i2;
        this.userId = VUserHandle.getUserId(i);
        this.callingVUid = i3;
        this.processName = str;
        this.is64bit = z;
    }

    public int getCallingVUid() {
        return this.callingVUid;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ProcessRecord processRecord = (ProcessRecord) obj;
        String str = this.processName;
        return str != null ? str.equals(processRecord.processName) : processRecord.processName == null;
    }

    public String getProviderAuthority() {
        return StubManifest.getStubAuthority(this.vpid, this.is64bit);
    }

    public ClientConfig getClientConfig() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.is64Bit = this.is64bit;
        clientConfig.vuid = this.vuid;
        clientConfig.vpid = this.vpid;
        clientConfig.packageName = this.info.packageName;
        clientConfig.processName = this.processName;
        clientConfig.token = this;
        return clientConfig;
    }

    public void kill() {
        try {
            VirtualCore.get().getContext().unbindService(this.conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        VActivityManagerService.get().beforeProcessKilled(this);
        if (this.is64bit) {
            V64BitHelper.forceStop64(this.pid);
            return;
        }
        try {
            Process.killProcess(this.pid);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
