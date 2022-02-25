package com.kaopu.download.kernel;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.kaopu.download.BaseDownloadWorker;
import com.kaopu.download.abst.ADownloadWorker;
import com.kaopu.download.kernel.IDownloadService;
import com.kaopu.download.util.DownloadObjectUtil;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/* loaded from: classes.dex */
public class DownloadService extends Service {
    private boolean isServiceAlive = false;
    private IDownloadService.Stub mDownloadServiceImpl = new IDownloadService.Stub() { // from class: com.kaopu.download.kernel.DownloadService.1
        @Override // com.kaopu.download.kernel.IDownloadService
        public boolean isServiceAlive() throws RemoteException {
            return DownloadService.this.isServiceAlive;
        }

        @Override // com.kaopu.download.kernel.IDownloadService
        public boolean addNewDownloadTask(Map map) throws RemoteException {
            ADownloadWorker createDownloadWorker;
            BaseDownloadInfo map2DownloadInfo = DownloadObjectUtil.map2DownloadInfo(map);
            if (map2DownloadInfo == null || (createDownloadWorker = DownloadService.this.createDownloadWorker(map2DownloadInfo)) == null) {
                return false;
            }
            return createDownloadWorker.start();
        }

        @Override // com.kaopu.download.kernel.IDownloadService
        public boolean pauseDownloadTask(Map map) throws RemoteException {
            ADownloadWorker<? extends BaseDownloadInfo> downloadWorkerById;
            BaseDownloadInfo map2DownloadInfo = DownloadObjectUtil.map2DownloadInfo(map);
            if (map2DownloadInfo == null || (downloadWorkerById = DownloadWorkerSupervisor.getDownloadWorkerById(map2DownloadInfo.getIdentification())) == null) {
                return false;
            }
            return downloadWorkerById.pause();
        }

        @Override // com.kaopu.download.kernel.IDownloadService
        public boolean cancelDownloadTask(Map map) throws RemoteException {
            BaseDownloadInfo map2DownloadInfo = DownloadObjectUtil.map2DownloadInfo(map);
            if (map2DownloadInfo == null) {
                return false;
            }
            ADownloadWorker<? extends BaseDownloadInfo> downloadWorkerById = DownloadWorkerSupervisor.getDownloadWorkerById(map2DownloadInfo.getIdentification());
            if (downloadWorkerById == null) {
                downloadWorkerById = DownloadService.this.createDownloadWorker(map2DownloadInfo);
            }
            if (downloadWorkerById == null) {
                return false;
            }
            return downloadWorkerById.cancle();
        }

        @Override // com.kaopu.download.kernel.IDownloadService
        public void clearAllDownloadTask() throws RemoteException {
            DownloadWorkerSupervisor.clearAllDownloadTask();
        }

        @Override // com.kaopu.download.kernel.IDownloadService
        public Map getDownloadInfos() throws RemoteException {
            return DownloadWorkerSupervisor.getDownloadInfos();
        }

        @Override // com.kaopu.download.kernel.IDownloadService
        public int getTaskCount() throws RemoteException {
            return DownloadWorkerSupervisor.getTaskCount();
        }

        @Override // com.kaopu.download.kernel.IDownloadService
        public Map getDownloadInfo(String str) throws RemoteException {
            return DownloadObjectUtil.object2Map(DownloadWorkerSupervisor.getDownloadInfo(str));
        }

        @Override // com.kaopu.download.kernel.IDownloadService
        public boolean continueDownload(Map map) throws RemoteException {
            ADownloadWorker createDownloadWorker;
            BaseDownloadInfo map2DownloadInfo = DownloadObjectUtil.map2DownloadInfo(map);
            if (map2DownloadInfo == null || (createDownloadWorker = DownloadService.this.createDownloadWorker(map2DownloadInfo)) == null) {
                return false;
            }
            return createDownloadWorker.start();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public ADownloadWorker<? extends BaseDownloadInfo> createDownloadWorker(BaseDownloadInfo baseDownloadInfo) {
        if (baseDownloadInfo.getDownloadWorkerClassName() == null) {
            return new BaseDownloadWorker(this, baseDownloadInfo);
        }
        try {
            return (ADownloadWorker) Class.forName(baseDownloadInfo.getDownloadWorkerClassName()).getConstructor(Context.class, baseDownloadInfo.getClass()).newInstance(this, baseDownloadInfo);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
            return null;
        } catch (InstantiationException e4) {
            e4.printStackTrace();
            return null;
        } catch (NoSuchMethodException e5) {
            e5.printStackTrace();
            return null;
        } catch (InvocationTargetException e6) {
            e6.printStackTrace();
            return null;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mDownloadServiceImpl;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.isServiceAlive = true;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.isServiceAlive = false;
    }
}
