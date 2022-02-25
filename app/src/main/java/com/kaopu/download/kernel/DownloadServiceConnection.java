package com.kaopu.download.kernel;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.kaopu.download.kernel.IDownloadService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class DownloadServiceConnection implements ServiceConnection {
    private static IDownloadService sAidlService;
    private Context mContext;
    private boolean mIsConnectionBind = false;

    public DownloadServiceConnection(Context context) {
        this.mContext = null;
        this.mContext = context;
        try {
            if (sAidlService == null || !sAidlService.isServiceAlive()) {
                context.startService(new Intent(this.mContext, DownloadService.class));
            }
        } catch (Exception unused) {
            context.startService(new Intent(this.mContext, DownloadService.class));
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            sAidlService = IDownloadService.Stub.asInterface(iBinder);
            this.mIsConnectionBind = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        sAidlService = null;
        this.mIsConnectionBind = false;
    }

    public boolean addDownloadTask(Map map) {
        IDownloadService iDownloadService;
        if (startBind() && (iDownloadService = sAidlService) != null) {
            try {
                return iDownloadService.addNewDownloadTask(map);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public boolean addDownloadTask(ArrayList<Map> arrayList) {
        if (!startBind()) {
            return false;
        }
        if (sAidlService == null) {
            return true;
        }
        try {
            Iterator<Map> it = arrayList.iterator();
            while (it.hasNext()) {
                sAidlService.addNewDownloadTask(it.next());
            }
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    public boolean pause(Map map) {
        IDownloadService iDownloadService;
        if (startBind() && (iDownloadService = sAidlService) != null) {
            try {
                return iDownloadService.pauseDownloadTask(map);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public boolean cancel(Map map) {
        IDownloadService iDownloadService;
        if (startBind() && (iDownloadService = sAidlService) != null) {
            try {
                return iDownloadService.cancelDownloadTask(map);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public boolean clearAllDownloadTask() {
        if (!startBind()) {
            return false;
        }
        try {
            sAidlService.clearAllDownloadTask();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public Map<String, BaseDownloadInfo> getDownloadInfos() {
        if (!startBind()) {
            return null;
        }
        try {
            return sAidlService.getDownloadInfos();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map getDownloadInfo(String str) {
        if (!startBind()) {
            return null;
        }
        try {
            return sAidlService.getDownloadInfo(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getTaskCount() {
        if (!startBind()) {
            return 0;
        }
        try {
            return sAidlService.getTaskCount();
        } catch (Exception unused) {
            return 0;
        }
    }

    public void bindDownloadService(ConnectionCallBack<Boolean> connectionCallBack) {
        if (!isBind()) {
            Context context = this.mContext;
            if (!context.bindService(new Intent(context, DownloadService.class), this, 1) && connectionCallBack != null) {
                connectionCallBack.invoke(false);
            }
        } else if (connectionCallBack != null) {
            connectionCallBack.invoke(true);
        }
    }

    public void unBindDownloadService() {
        try {
            if (this.mIsConnectionBind) {
                this.mContext.unbindService(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean startBind() {
        if (isBind()) {
            return true;
        }
        try {
            return this.mContext.bindService(new Intent(this.mContext, DownloadService.class), this, 1);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                this.mContext.unbindService(this);
                return false;
            } catch (Exception unused) {
                return false;
            }
        }
    }

    public boolean isBind() {
        try {
            if (sAidlService != null) {
                return sAidlService.isServiceAlive();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean continueDownload(Map map) {
        if (!startBind()) {
            return false;
        }
        try {
            return sAidlService.continueDownload(map);
        } catch (Exception unused) {
            return false;
        }
    }
}
