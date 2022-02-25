package com.lody.virtual.client.stub;

import android.annotation.TargetApi;
import android.app.Service;
import android.app.job.IJobService;
import android.app.job.JobParameters;
import android.content.Intent;
import android.os.IBinder;

@TargetApi(21)
/* loaded from: classes.dex */
public class ShadowJobService extends Service {
    private final IJobService mService = new IJobService.Stub() { // from class: com.lody.virtual.client.stub.ShadowJobService.1
        @Override // android.app.job.IJobService
        public void startJob(JobParameters jobParameters) {
            ShadowJobWorkService.startJob(ShadowJobService.this, jobParameters);
        }

        @Override // android.app.job.IJobService
        public void stopJob(JobParameters jobParameters) {
            ShadowJobWorkService.stopJob(ShadowJobService.this, jobParameters);
        }
    };

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mService.asBinder();
    }
}
