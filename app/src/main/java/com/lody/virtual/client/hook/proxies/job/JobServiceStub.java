package com.lody.virtual.client.hook.proxies.job;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobWorkItem;
import android.os.Build;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.MethodProxy;
import com.lody.virtual.client.ipc.VJobScheduler;
import com.lody.virtual.helper.compat.BuildCompat;
import com.lody.virtual.helper.compat.JobWorkItemCompat;
import java.lang.reflect.Method;
import java.util.List;
import p110z1.IJobScheduler;
import p110z1.ParceledListSlice;

@TargetApi(21)
/* loaded from: classes.dex */
public class JobServiceStub extends BinderInvocationProxy {
    public JobServiceStub() {
        super(IJobScheduler.C5127a.asInterface, "jobscheduler");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new schedule());
        addMethodProxy(new getAllPendingJobs());
        addMethodProxy(new cancelAll());
        addMethodProxy(new cancel());
        if (Build.VERSION.SDK_INT >= 24) {
            addMethodProxy(new getPendingJob());
        }
        if (Build.VERSION.SDK_INT >= 26) {
            addMethodProxy(new enqueue());
        }
    }

    /* loaded from: classes.dex */
    private class schedule extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "schedule";
        }

        private schedule() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return Integer.valueOf(VJobScheduler.get().schedule((JobInfo) objArr[0]));
        }
    }

    /* loaded from: classes.dex */
    private class getAllPendingJobs extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getAllPendingJobs";
        }

        private getAllPendingJobs() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            List<JobInfo> allPendingJobs = VJobScheduler.get().getAllPendingJobs();
            if (allPendingJobs == null) {
                return null;
            }
            return BuildCompat.isQ() ? ParceledListSlice.ctorQ.newInstance(allPendingJobs) : allPendingJobs;
        }
    }

    /* loaded from: classes.dex */
    private class cancelAll extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "cancelAll";
        }

        private cancelAll() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            VJobScheduler.get().cancelAll();
            return 0;
        }
    }

    /* loaded from: classes.dex */
    private class cancel extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "cancel";
        }

        private cancel() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            VJobScheduler.get().cancel(((Integer) objArr[0]).intValue());
            return 0;
        }
    }

    /* loaded from: classes.dex */
    private class getPendingJob extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getPendingJob";
        }

        private getPendingJob() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return VJobScheduler.get().getPendingJob(((Integer) objArr[0]).intValue());
        }
    }

    @TargetApi(26)
    /* loaded from: classes.dex */
    private class enqueue extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "enqueue";
        }

        private enqueue() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            JobWorkItem redirect = JobWorkItemCompat.redirect((JobWorkItem) objArr[1], getAppPkg());
            return Integer.valueOf(VJobScheduler.get().enqueue((JobInfo) objArr[0], redirect));
        }
    }
}
