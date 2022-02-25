package com.lody.virtual.client.stub;

import android.annotation.TargetApi;
import android.app.Service;
import android.app.job.IJobCallback;
import android.app.job.IJobService;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.lody.virtual.client.core.InvocationStubManager;
import com.lody.virtual.client.hook.proxies.p059am.ActivityManagerStub;
import com.lody.virtual.helper.collection.SparseArray;
import com.lody.virtual.helper.compat.JobWorkItemCompat;
import com.lody.virtual.p061os.VUserHandle;
import com.lody.virtual.server.job.VJobSchedulerService;
import java.util.Map;

@TargetApi(21)
/* loaded from: classes.dex */
public class ShadowJobWorkService extends Service {
    private static final String TAG = "ShadowJobWorkService";
    private static final boolean debug = false;
    private final SparseArray<JobSession> mJobSessions = new SparseArray<>();
    private JobScheduler mScheduler;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null) {
            return 2;
        }
        String action = intent.getAction();
        if ("action.startJob".equals(action)) {
            startJob((JobParameters) intent.getParcelableExtra("jobParams"));
            return 2;
        } else if (!"action.stopJob".equals(action)) {
            return 2;
        } else {
            stopJob((JobParameters) intent.getParcelableExtra("jobParams"));
            return 2;
        }
    }

    public static void startJob(Context context, JobParameters jobParameters) {
        Intent intent = new Intent("action.startJob");
        intent.setPackage(context.getPackageName());
        intent.putExtra("jobParams", jobParameters);
        context.startService(intent);
    }

    public static void stopJob(Context context, JobParameters jobParameters) {
        Intent intent = new Intent("action.stopJob");
        intent.setPackage(context.getPackageName());
        intent.putExtra("jobParams", jobParameters);
        context.startService(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void emptyCallback(IJobCallback iJobCallback, int i) {
        try {
            iJobCallback.acknowledgeStartMessage(i, false);
            iJobCallback.jobFinished(i, false);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        InvocationStubManager.getInstance().checkEnv(ActivityManagerStub.class);
        this.mScheduler = (JobScheduler) getSystemService("jobscheduler");
    }

    @Override // android.app.Service
    public void onDestroy() {
        synchronized (this.mJobSessions) {
            for (int size = this.mJobSessions.size() - 1; size >= 0; size--) {
                this.mJobSessions.valueAt(size).stopSessionLocked();
            }
            this.mJobSessions.clear();
        }
        super.onDestroy();
    }

    public void startJob(JobParameters jobParameters) {
        JobSession jobSession;
        boolean bindService;
        int jobId = jobParameters.getJobId();
        IJobCallback asInterface = IJobCallback.Stub.asInterface(p110z1.JobParameters.callback.get(jobParameters));
        Map.Entry<VJobSchedulerService.JobId, VJobSchedulerService.JobConfig> findJobByVirtualJobId = VJobSchedulerService.get().findJobByVirtualJobId(jobId);
        if (findJobByVirtualJobId == null) {
            emptyCallback(asInterface, jobId);
            this.mScheduler.cancel(jobId);
            return;
        }
        VJobSchedulerService.JobId key = findJobByVirtualJobId.getKey();
        VJobSchedulerService.JobConfig value = findJobByVirtualJobId.getValue();
        synchronized (this.mJobSessions) {
            jobSession = this.mJobSessions.get(jobId);
        }
        if (jobSession != null) {
            jobSession.startJob(true);
            return;
        }
        synchronized (this.mJobSessions) {
            p110z1.JobParameters.jobId.set(jobParameters, key.clientJobId);
            JobSession jobSession2 = new JobSession(jobId, asInterface, jobParameters, key.packageName);
            p110z1.JobParameters.callback.set(jobParameters, jobSession2.asBinder());
            this.mJobSessions.put(jobId, jobSession2);
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(key.packageName, value.serviceName));
            intent.putExtra("_VA_|_user_id_", VUserHandle.getUserId(key.vuid));
            bindService = bindService(intent, jobSession2, 5);
        }
        if (!bindService) {
            synchronized (this.mJobSessions) {
                this.mJobSessions.remove(jobId);
            }
            emptyCallback(asInterface, jobId);
            this.mScheduler.cancel(jobId);
            VJobSchedulerService.get().cancel(-1, jobId);
        }
    }

    public void stopJob(JobParameters jobParameters) {
        int jobId = jobParameters.getJobId();
        synchronized (this.mJobSessions) {
            JobSession jobSession = this.mJobSessions.get(jobId);
            if (jobSession != null) {
                jobSession.stopSessionLocked();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class JobSession extends IJobCallback.Stub implements ServiceConnection {
        private IJobCallback clientCallback;
        private IJobService clientJobService;
        private boolean isWorking;
        private int jobId;
        private JobParameters jobParams;
        private String packageName;

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }

        JobSession(int i, IJobCallback iJobCallback, JobParameters jobParameters, String str) {
            this.jobId = i;
            this.clientCallback = iJobCallback;
            this.jobParams = jobParameters;
            this.packageName = str;
        }

        @Override // android.app.job.IJobCallback
        public void acknowledgeStartMessage(int i, boolean z) throws RemoteException {
            this.isWorking = true;
            this.clientCallback.acknowledgeStartMessage(i, z);
        }

        @Override // android.app.job.IJobCallback
        public void acknowledgeStopMessage(int i, boolean z) throws RemoteException {
            this.isWorking = false;
            this.clientCallback.acknowledgeStopMessage(i, z);
        }

        @Override // android.app.job.IJobCallback
        public void jobFinished(int i, boolean z) throws RemoteException {
            this.isWorking = false;
            this.clientCallback.jobFinished(i, z);
        }

        @Override // android.app.job.IJobCallback
        public boolean completeWork(int i, int i2) throws RemoteException {
            return this.clientCallback.completeWork(i, i2);
        }

        @Override // android.app.job.IJobCallback
        public JobWorkItem dequeueWork(int i) throws RemoteException {
            JobWorkItem dequeueWork = this.clientCallback.dequeueWork(i);
            if (dequeueWork != null) {
                return JobWorkItemCompat.redirect(dequeueWork, this.packageName);
            }
            return null;
        }

        public void startJob(boolean z) {
            if (!this.isWorking) {
                IJobService iJobService = this.clientJobService;
                if (iJobService != null) {
                    try {
                        iJobService.startJob(this.jobParams);
                    } catch (RemoteException unused) {
                        forceFinishJob();
                    }
                } else if (!z) {
                    ShadowJobWorkService.this.emptyCallback(this.clientCallback, this.jobId);
                    synchronized (ShadowJobWorkService.this.mJobSessions) {
                        stopSessionLocked();
                    }
                }
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            this.clientJobService = IJobService.Stub.asInterface(iBinder);
            startJob(false);
        }

        void forceFinishJob() {
            try {
                try {
                    this.clientCallback.jobFinished(this.jobId, false);
                    synchronized (ShadowJobWorkService.this.mJobSessions) {
                        stopSessionLocked();
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                    synchronized (ShadowJobWorkService.this.mJobSessions) {
                        stopSessionLocked();
                    }
                }
            } catch (Throwable th) {
                synchronized (ShadowJobWorkService.this.mJobSessions) {
                    stopSessionLocked();
                    throw th;
                }
            }
        }

        void stopSessionLocked() {
            IJobService iJobService = this.clientJobService;
            if (iJobService != null) {
                try {
                    iJobService.stopJob(this.jobParams);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            ShadowJobWorkService.this.mJobSessions.remove(this.jobId);
            ShadowJobWorkService.this.unbindService(this);
        }
    }
}
