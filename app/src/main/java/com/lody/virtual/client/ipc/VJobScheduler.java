package com.lody.virtual.client.ipc;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobWorkItem;
import android.os.RemoteException;
import com.lody.virtual.client.VClient;
import com.lody.virtual.client.env.VirtualRuntime;
import com.lody.virtual.helper.compat.BuildCompat;
import com.lody.virtual.helper.utils.IInterfaceUtils;
import com.lody.virtual.remote.VJobWorkItem;
import com.lody.virtual.server.interfaces.IJobService;
import java.util.List;

/* loaded from: classes.dex */
public class VJobScheduler {
    private static final VJobScheduler sInstance = new VJobScheduler();
    private IJobService mService;

    public static VJobScheduler get() {
        return sInstance;
    }

    public IJobService getService() {
        IJobService iJobService = this.mService;
        if (iJobService == null || !IInterfaceUtils.isAlive(iJobService)) {
            synchronized (this) {
                this.mService = (IJobService) LocalProxyUtils.genProxy(IJobService.class, getRemoteInterface());
            }
        }
        return this.mService;
    }

    private Object getRemoteInterface() {
        return IJobService.Stub.asInterface(ServiceManagerNative.getService(ServiceManagerNative.JOB));
    }

    public int schedule(JobInfo jobInfo) {
        try {
            return getService().schedule(VClient.get().getVUid(), jobInfo);
        } catch (RemoteException e) {
            return ((Integer) VirtualRuntime.crash(e)).intValue();
        }
    }

    public List<JobInfo> getAllPendingJobs() {
        try {
            return getService().getAllPendingJobs(VClient.get().getVUid());
        } catch (RemoteException e) {
            return (List) VirtualRuntime.crash(e);
        }
    }

    public void cancelAll() {
        try {
            getService().cancelAll(VClient.get().getVUid());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void cancel(int i) {
        try {
            getService().cancel(VClient.get().getVUid(), i);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public JobInfo getPendingJob(int i) {
        try {
            return getService().getPendingJob(VClient.get().getVUid(), i);
        } catch (RemoteException e) {
            return (JobInfo) VirtualRuntime.crash(e);
        }
    }

    @TargetApi(26)
    public int enqueue(JobInfo jobInfo, JobWorkItem jobWorkItem) {
        if (jobWorkItem == null || !BuildCompat.isOreo()) {
            return -1;
        }
        try {
            return getService().enqueue(VClient.get().getVUid(), jobInfo, new VJobWorkItem(jobWorkItem));
        } catch (RemoteException e) {
            return ((Integer) VirtualRuntime.crash(e)).intValue();
        }
    }
}
