package com.cyjh.p045mq.sdk;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
import com.cyjh.mobileanjian.ipc.RootShell;
import com.cyjh.mobileanjian.ipc.interfaces.EngineStateObserver;
import com.cyjh.mobileanjian.ipc.interfaces.OnRootApplyCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnScriptListener;
import com.cyjh.mobileanjian.ipc.share.proto.Ipc;
import com.cyjh.mobileanjian.ipc.share.proto.IpcCommand;
import com.cyjh.mobileanjian.ipc.share.proto.IpcRaw;
import com.cyjh.mobileanjian.ipc.utils.RootUtil;
import com.cyjh.mqsdk.C1375R;
import com.cyjh.p045mq.p046a.MyApplication;
import com.cyjh.p045mq.p048c.IpcConnection;
import com.cyjh.p045mq.p049d.PackageUtils;
import com.cyjh.p045mq.sdk.entity.Script4Run;
import com.cyjh.p045mq.sdk.inf.IRunner;
import com.google.protobuf.ByteString;
import java.io.File;
import org.apache.commons.p105io.FileUtils;
import org.apache.commons.p105io.FilenameUtils;

/* renamed from: com.cyjh.mq.sdk.a */
/* loaded from: classes.dex */
public abstract class GeneralMqRunner implements EngineStateObserver, OnRootApplyCallback, IRunner {

    /* renamed from: a */
    protected static final int f8891a = 256;

    /* renamed from: c */
    protected Script4Run f8893c;

    /* renamed from: g */
    private OnScriptListener f8897g;

    /* renamed from: b */
    protected IpcConnection f8892b = null;

    /* renamed from: e */
    protected boolean f8895e = false;

    /* renamed from: h */
    private boolean f8898h = false;

    /* renamed from: f */
    protected boolean f8896f = false;

    /* renamed from: d */
    protected Handler f8894d = new Handler(Looper.getMainLooper()) { // from class: com.cyjh.mq.sdk.a.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 256) {
                Toast.makeText(MyApplication.f8788g.getApplicationContext(), MyApplication.f8788g.getApplicationContext().getString(C1375R.string.toast_script_engine_failed_start), 1).show();
                GeneralMqRunner.this.f8898h = false;
            } else if (MyApplication.f8790i != null) {
                if (message.what == 4) {
                    Toast.makeText(MyApplication.f8788g.getApplicationContext(), MyApplication.f8788g.getString(C1375R.string.toast_failed_got_root, new Object[]{PackageUtils.m20443a(MyApplication.f8788g.getApplicationContext())}), 1).show();
                }
                MyApplication.f8790i.onRootProgress((String) message.obj, message.what);
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: GeneralMqRunner.java */
    /* renamed from: com.cyjh.mq.sdk.a$a */
    /* loaded from: classes.dex */
    public class AsyncTaskC1371a extends AsyncTask<Script4Run, Void, Script4Run> {
        private AsyncTaskC1371a() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public /* synthetic */ AsyncTaskC1371a(GeneralMqRunner aVar, byte b) {
            this();
        }

        @Override // android.os.AsyncTask
        protected final /* synthetic */ Script4Run doInBackground(Script4Run[] script4RunArr) {
            return m20418a(script4RunArr);
        }

        @Override // android.os.AsyncTask
        protected final /* synthetic */ void onPostExecute(Script4Run script4Run) {
            Script4Run script4Run2 = script4Run;
            if (script4Run2 != null) {
                GeneralMqRunner.this.m20422a(script4Run2.toMessage(7));
                GeneralMqRunner.this.m20422a(script4Run2.toMessage(1));
            }
        }

        /* renamed from: a */
        private static Script4Run m20418a(Script4Run... script4RunArr) {
            Script4Run script4Run = script4RunArr[0];
            try {
                File file = new File(MyApplication.f8788g.getApplicationContext().getFilesDir(), "script");
                if (!file.exists()) {
                    file.mkdirs();
                    file.setReadable(true, false);
                    file.setExecutable(true, false);
                } else {
                    FileUtils.deleteDirectory(file);
                }
                File[] fileArr = {new File(script4Run.getLcPath()), new File(script4Run.getAtcPath()), new File(script4Run.getConfigPath())};
                File file2 = new File(file, FilenameUtils.getName(script4Run.getLcPath()));
                File file3 = new File(file, FilenameUtils.getName(script4Run.getAtcPath()));
                File file4 = new File(file, FilenameUtils.getName(script4Run.getConfigPath()));
                File[] fileArr2 = new File[3];
                fileArr2[0] = file2;
                fileArr2[1] = file3;
                fileArr2[2] = file4;
                for (int i = 0; i < 3; i++) {
                    if (fileArr[i].exists()) {
                        FileUtils.copyFile(fileArr[i], fileArr2[i]);
                        fileArr2[i].setReadable(true, false);
                    } else {
                        fileArr2[i] = fileArr[i];
                    }
                }
                Script4Run clone = script4Run.clone();
                clone.setLcPath(file2.getAbsolutePath()).setAtcPath(file3.getAbsolutePath()).setConfigPath(file4.getAbsolutePath());
                return clone;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* renamed from: a */
        private void m20419a(Script4Run script4Run) {
            if (script4Run != null) {
                GeneralMqRunner.this.m20422a(script4Run.toMessage(7));
                GeneralMqRunner.this.m20422a(script4Run.toMessage(1));
            }
        }
    }

    /* renamed from: a */
    private void m20420a(Script4Run script4Run) {
        new AsyncTaskC1371a(this, (byte) 0).execute(script4Run);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final void m20422a(Ipc.IpcMessage ipcMessage) {
        if (this.f8895e) {
            this.f8892b.m20531a(ipcMessage);
        }
    }

    @Override // com.cyjh.p045mq.sdk.inf.IRunner
    public IRunner setScript(Script4Run script4Run) {
        this.f8893c = script4Run;
        m20422a(script4Run.toMessage(7));
        return this;
    }

    @Override // com.cyjh.p045mq.sdk.inf.IRunner
    public IRunner setOnScriptListener(OnScriptListener onScriptListener) {
        this.f8897g = onScriptListener;
        IpcConnection bVar = this.f8892b;
        if (bVar != null) {
            bVar.f8816l = this.f8897g;
        }
        return this;
    }

    @Override // com.cyjh.p045mq.sdk.inf.IRunner
    public void pause() {
        m20422a(IpcRaw.m20933a(2));
    }

    @Override // com.cyjh.p045mq.sdk.inf.IRunner
    public void resume() {
        m20422a(IpcRaw.m20933a(9));
    }

    @Override // com.cyjh.p045mq.sdk.inf.IRunner
    public void stop() {
        Log.d("JAVA_RUNNER", "GeneralMqRunner stop");
        m20422a(IpcRaw.m20933a(3));
    }

    @Override // com.cyjh.p045mq.sdk.inf.IRunner
    public boolean isScriptStarted() {
        IpcConnection bVar = this.f8892b;
        if (bVar == null) {
            return false;
        }
        return bVar.f8809e;
    }

    @Override // com.cyjh.mobileanjian.ipc.interfaces.EngineStateObserver
    public void onConnected(IpcConnection bVar) {
        Log.i("IPC_ROOT", "onConnected");
        this.f8894d.removeMessages(256);
        this.f8895e = true;
        this.f8892b = bVar;
        this.f8898h = false;
        setOnScriptListener(this.f8897g);
    }

    @Override // com.cyjh.p045mq.sdk.inf.IRunnerOther
    /* renamed from: a */
    public final void mo20414a(ByteString byteString) {
        m20422a(Ipc.IpcMessage.newBuilder().setCmd(64).setFileData(byteString).build());
    }

    @Override // com.cyjh.p045mq.sdk.inf.IRunnerOther
    /* renamed from: b */
    public final void mo20411b(ByteString byteString) {
        m20422a(Ipc.IpcMessage.newBuilder().setCmd(65).setFileData(byteString).build());
    }

    @Override // com.cyjh.p045mq.sdk.inf.IRunnerOther
    /* renamed from: c */
    public final void mo20409c(ByteString byteString) {
        m20422a(Ipc.IpcMessage.newBuilder().setCmd(IpcCommand.f8361aH).setFileData(byteString).build());
    }

    @Override // com.cyjh.p045mq.sdk.inf.IRunnerOther
    /* renamed from: a */
    public final void mo20413a(String str) {
        m20422a(Ipc.IpcMessage.newBuilder().setCmd(IpcCommand.f8362aI).addArg2(str).build());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final void m20423a() {
        Log.i("GeneralMqRunnable", "startMqRunnerDaemon");
        if (!this.f8898h) {
            this.f8898h = true;
            File a = MyApplication.m20565a();
            RootShell.m21029a().m21022a("setenforce 0");
            RootShell.m21029a().m21022a("chmod 777 /dev/input/*");
            RootShell.m21029a().m21022a(a.getAbsolutePath());
            this.f8894d.sendEmptyMessageDelayed(256, 6000L);
        }
    }

    @Override // com.cyjh.mobileanjian.ipc.interfaces.OnRootApplyCallback
    public void onObtained() {
        m20423a();
        this.f8896f = false;
    }

    @Override // com.cyjh.mobileanjian.ipc.interfaces.OnRootApplyCallback
    public void onRefused() {
        if (RootUtil.isRoot()) {
            this.f8894d.obtainMessage(4, "User refused root.").sendToTarget();
        }
        this.f8896f = false;
    }

    @Override // com.cyjh.p045mq.sdk.inf.IRunner
    public void notifyRotationStatus() {
        m20422a(Ipc.IpcMessage.newBuilder().setCmd(15).addArg1(((WindowManager) MyApplication.f8788g.getSystemService("window")).getDefaultDisplay().getRotation()).build());
    }
}
