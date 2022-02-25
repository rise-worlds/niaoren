package com.lody.virtual.server.job;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.text.TextUtils;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.ipc.VJobScheduler;
import com.lody.virtual.client.stub.StubManifest;
import com.lody.virtual.helper.utils.Singleton;
import com.lody.virtual.p061os.VEnvironment;
import com.lody.virtual.remote.VJobWorkItem;
import com.lody.virtual.server.interfaces.IJobService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

@TargetApi(21)
/* loaded from: classes.dex */
public class VJobSchedulerService extends IJobService.Stub {
    private static final int JOB_FILE_VERSION = 1;
    private static final String TAG = VJobScheduler.class.getSimpleName();
    private static final Singleton<VJobSchedulerService> gDefault = new Singleton<VJobSchedulerService>() { // from class: com.lody.virtual.server.job.VJobSchedulerService.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.lody.virtual.helper.utils.Singleton
        public VJobSchedulerService create() {
            return new VJobSchedulerService();
        }
    };
    private final ComponentName mJobProxyComponent;
    private final Map<JobId, JobConfig> mJobStore;
    private int mNextJobId;
    private final JobScheduler mScheduler;

    private VJobSchedulerService() {
        this.mJobStore = new HashMap();
        this.mNextJobId = 1;
        this.mScheduler = (JobScheduler) VirtualCore.get().getContext().getSystemService("jobscheduler");
        this.mJobProxyComponent = new ComponentName(VirtualCore.get().getHostPkg(), StubManifest.STUB_JOB);
        readJobs();
    }

    public static VJobSchedulerService get() {
        return gDefault.get();
    }

    /* loaded from: classes.dex */
    public static final class JobId implements Parcelable {
        public static final Parcelable.Creator<JobId> CREATOR = new Parcelable.Creator<JobId>() { // from class: com.lody.virtual.server.job.VJobSchedulerService.JobId.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public JobId createFromParcel(Parcel parcel) {
                return new JobId(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public JobId[] newArray(int i) {
                return new JobId[i];
            }
        };
        public int clientJobId;
        public String packageName;
        public int vuid;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        JobId(int i, String str, int i2) {
            this.vuid = i;
            this.packageName = str;
            this.clientJobId = i2;
        }

        JobId(Parcel parcel) {
            this.vuid = parcel.readInt();
            this.packageName = parcel.readString();
            this.clientJobId = parcel.readInt();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            JobId jobId = (JobId) obj;
            return this.vuid == jobId.vuid && this.clientJobId == jobId.clientJobId && TextUtils.equals(this.packageName, jobId.packageName);
        }

        public int hashCode() {
            int i = this.vuid * 31;
            String str = this.packageName;
            return ((i + (str != null ? str.hashCode() : 0)) * 31) + this.clientJobId;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.vuid);
            parcel.writeString(this.packageName);
            parcel.writeInt(this.clientJobId);
        }
    }

    /* loaded from: classes.dex */
    public static final class JobConfig implements Parcelable {
        public static final Parcelable.Creator<JobConfig> CREATOR = new Parcelable.Creator<JobConfig>() { // from class: com.lody.virtual.server.job.VJobSchedulerService.JobConfig.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public JobConfig createFromParcel(Parcel parcel) {
                return new JobConfig(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public JobConfig[] newArray(int i) {
                return new JobConfig[i];
            }
        };
        public PersistableBundle extras;
        public String serviceName;
        public int virtualJobId;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        JobConfig(int i, String str, PersistableBundle persistableBundle) {
            this.virtualJobId = i;
            this.serviceName = str;
            this.extras = persistableBundle;
        }

        JobConfig(Parcel parcel) {
            this.virtualJobId = parcel.readInt();
            this.serviceName = parcel.readString();
            this.extras = (PersistableBundle) parcel.readParcelable(PersistableBundle.class.getClassLoader());
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.virtualJobId);
            parcel.writeString(this.serviceName);
            parcel.writeParcelable(this.extras, i);
        }
    }

    @Override // com.lody.virtual.server.interfaces.IJobService
    public int schedule(int i, JobInfo jobInfo) {
        JobConfig jobConfig;
        int id = jobInfo.getId();
        ComponentName service = jobInfo.getService();
        JobId jobId = new JobId(i, service.getPackageName(), id);
        synchronized (this.mJobStore) {
            jobConfig = this.mJobStore.get(jobId);
            if (jobConfig == null) {
                int i2 = this.mNextJobId;
                this.mNextJobId++;
                JobConfig jobConfig2 = new JobConfig(i2, service.getClassName(), jobInfo.getExtras());
                this.mJobStore.put(jobId, jobConfig2);
                jobConfig = jobConfig2;
            }
        }
        jobConfig.serviceName = service.getClassName();
        jobConfig.extras = jobInfo.getExtras();
        saveJobs();
        p110z1.JobInfo.jobId.set(jobInfo, jobConfig.virtualJobId);
        p110z1.JobInfo.service.set(jobInfo, this.mJobProxyComponent);
        return this.mScheduler.schedule(jobInfo);
    }

    private void saveJobs() {
        File jobConfigFile = VEnvironment.getJobConfigFile();
        Parcel obtain = Parcel.obtain();
        try {
            try {
                obtain.writeInt(1);
                obtain.writeInt(this.mJobStore.size());
                for (Map.Entry<JobId, JobConfig> entry : this.mJobStore.entrySet()) {
                    entry.getKey().writeToParcel(obtain, 0);
                    entry.getValue().writeToParcel(obtain, 0);
                }
                FileOutputStream fileOutputStream = new FileOutputStream(jobConfigFile);
                fileOutputStream.write(obtain.marshall());
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            obtain.recycle();
        }
    }

    private void readJobs() {
        byte[] bArr;
        int read;
        File jobConfigFile = VEnvironment.getJobConfigFile();
        if (jobConfigFile.exists()) {
            Parcel obtain = Parcel.obtain();
            try {
                try {
                    FileInputStream fileInputStream = new FileInputStream(jobConfigFile);
                    bArr = new byte[(int) jobConfigFile.length()];
                    read = fileInputStream.read(bArr);
                    fileInputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (read == bArr.length) {
                    obtain.unmarshall(bArr, 0, bArr.length);
                    obtain.setDataPosition(0);
                    int readInt = obtain.readInt();
                    if (readInt == 1) {
                        if (!this.mJobStore.isEmpty()) {
                            this.mJobStore.clear();
                        }
                        int readInt2 = obtain.readInt();
                        int i = 0;
                        for (int i2 = 0; i2 < readInt2; i2++) {
                            JobId jobId = new JobId(obtain);
                            JobConfig jobConfig = new JobConfig(obtain);
                            this.mJobStore.put(jobId, jobConfig);
                            i = Math.max(i, jobConfig.virtualJobId);
                        }
                        this.mNextJobId = i + 1;
                        return;
                    }
                    throw new IOException("Bad version of job file: " + readInt);
                }
                throw new IOException("Unable to read job config.");
            } finally {
                obtain.recycle();
            }
        }
    }

    @Override // com.lody.virtual.server.interfaces.IJobService
    public void cancel(int i, int i2) {
        synchronized (this.mJobStore) {
            boolean z = false;
            Iterator<Map.Entry<JobId, JobConfig>> it = this.mJobStore.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<JobId, JobConfig> next = it.next();
                JobId key = next.getKey();
                JobConfig value = next.getValue();
                if (i == -1 || key.vuid == i) {
                    if (key.clientJobId == i2) {
                        z = true;
                        this.mScheduler.cancel(value.virtualJobId);
                        it.remove();
                        break;
                    }
                }
            }
            if (z) {
                saveJobs();
            }
        }
    }

    @Override // com.lody.virtual.server.interfaces.IJobService
    public void cancelAll(int i) {
        synchronized (this.mJobStore) {
            boolean z = false;
            Iterator<Map.Entry<JobId, JobConfig>> it = this.mJobStore.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<JobId, JobConfig> next = it.next();
                if (next.getKey().vuid == i) {
                    this.mScheduler.cancel(next.getValue().virtualJobId);
                    z = true;
                    it.remove();
                    break;
                }
            }
            if (z) {
                saveJobs();
            }
        }
    }

    @Override // com.lody.virtual.server.interfaces.IJobService
    public List<JobInfo> getAllPendingJobs(int i) {
        List<JobInfo> allPendingJobs = this.mScheduler.getAllPendingJobs();
        synchronized (this.mJobStore) {
            ListIterator<JobInfo> listIterator = allPendingJobs.listIterator();
            while (listIterator.hasNext()) {
                JobInfo next = listIterator.next();
                if (!StubManifest.STUB_JOB.equals(next.getService().getClassName())) {
                    listIterator.remove();
                } else {
                    Map.Entry<JobId, JobConfig> findJobByVirtualJobId = findJobByVirtualJobId(next.getId());
                    if (findJobByVirtualJobId == null) {
                        listIterator.remove();
                    } else {
                        JobId key = findJobByVirtualJobId.getKey();
                        JobConfig value = findJobByVirtualJobId.getValue();
                        if (key.vuid != i) {
                            listIterator.remove();
                        } else {
                            p110z1.JobInfo.jobId.set(next, key.clientJobId);
                            p110z1.JobInfo.service.set(next, new ComponentName(key.packageName, value.serviceName));
                        }
                    }
                }
            }
        }
        return allPendingJobs;
    }

    public Map.Entry<JobId, JobConfig> findJobByVirtualJobId(int i) {
        synchronized (this.mJobStore) {
            for (Map.Entry<JobId, JobConfig> entry : this.mJobStore.entrySet()) {
                if (entry.getValue().virtualJobId == i) {
                    return entry;
                }
            }
            return null;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IJobService
    @TargetApi(24)
    public JobInfo getPendingJob(int i, int i2) {
        JobInfo jobInfo;
        synchronized (this.mJobStore) {
            Iterator<Map.Entry<JobId, JobConfig>> it = this.mJobStore.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    jobInfo = null;
                    break;
                }
                JobId key = it.next().getKey();
                if (key.vuid == i && key.clientJobId == i2) {
                    jobInfo = this.mScheduler.getPendingJob(key.clientJobId);
                    break;
                }
            }
        }
        return jobInfo;
    }

    @Override // com.lody.virtual.server.interfaces.IJobService
    @TargetApi(26)
    public int enqueue(int i, JobInfo jobInfo, VJobWorkItem vJobWorkItem) {
        JobConfig jobConfig;
        if (vJobWorkItem.get() == null) {
            return -1;
        }
        int id = jobInfo.getId();
        ComponentName service = jobInfo.getService();
        JobId jobId = new JobId(i, service.getPackageName(), id);
        synchronized (this.mJobStore) {
            jobConfig = this.mJobStore.get(jobId);
            if (jobConfig == null) {
                int i2 = this.mNextJobId;
                this.mNextJobId++;
                JobConfig jobConfig2 = new JobConfig(i2, service.getClassName(), jobInfo.getExtras());
                this.mJobStore.put(jobId, jobConfig2);
                jobConfig = jobConfig2;
            }
        }
        jobConfig.serviceName = service.getClassName();
        jobConfig.extras = jobInfo.getExtras();
        saveJobs();
        p110z1.JobInfo.jobId.set(jobInfo, jobConfig.virtualJobId);
        p110z1.JobInfo.service.set(jobInfo, this.mJobProxyComponent);
        return this.mScheduler.enqueue(jobInfo, vJobWorkItem.get());
    }
}
