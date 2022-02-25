package p110z1;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.blankj.utilcode.util.LogUtils;
import com.cyjh.mobileanjian.ipc.interfaces.OnScriptListener;
import com.cyjh.mobileanjian.ipc.interfaces.OnSpecialMqCmdCallback;
import com.cyjh.mqm.MQLanguageStub;
import com.cyjh.p045mq.sdk.MqRunnerLite;
import com.cyjh.p045mq.sdk.entity.Script4Run;

/* renamed from: z1.abv */
/* loaded from: classes3.dex */
public class PXKJEnginModel implements IEngineModel {

    /* renamed from: b */
    private MQLanguageStub.MQAuxiliary f15135b;

    /* renamed from: d */
    private EnginCallback f15137d;

    /* renamed from: a */
    private OnSpecialMqCmdCallback f15134a = new OnSpecialMqCmdCallback() { // from class: z1.abv.1
        @Override // com.cyjh.mobileanjian.ipc.interfaces.OnSpecialMqCmdCallback
        public void doSpecialFuction(int i, String str) {
        }

        @Override // com.cyjh.mobileanjian.ipc.interfaces.OnSpecialMqCmdCallback
        public void launchApp(String str) {
            if (PXKJEnginModel.this.f15137d != null) {
                PXKJEnginModel.this.f15137d.mo14370a(str);
            }
        }

        @Override // com.cyjh.mobileanjian.ipc.interfaces.OnSpecialMqCmdCallback
        public void killApp(String str) {
            if (PXKJEnginModel.this.f15137d != null) {
                PXKJEnginModel.this.f15137d.mo14366b(str);
            }
        }

        @Override // com.cyjh.mobileanjian.ipc.interfaces.OnSpecialMqCmdCallback
        public String getForegroundPackage() {
            if (PXKJEnginModel.this.f15137d != null) {
                return PXKJEnginModel.this.f15137d.mo14361e();
            }
            return null;
        }

        @Override // com.cyjh.mobileanjian.ipc.interfaces.OnSpecialMqCmdCallback
        public String getRunningPackages() {
            if (PXKJEnginModel.this.f15137d != null) {
                return PXKJEnginModel.this.f15137d.mo14360f();
            }
            return null;
        }

        @Override // com.cyjh.mobileanjian.ipc.interfaces.OnSpecialMqCmdCallback
        public void keyPress(int i) {
            if (PXKJEnginModel.this.f15137d != null) {
                PXKJEnginModel.this.f15137d.mo14364c(i);
            }
        }

        @Override // com.cyjh.mobileanjian.ipc.interfaces.OnSpecialMqCmdCallback
        public void inputText(String str) {
            if (PXKJEnginModel.this.f15137d != null) {
                PXKJEnginModel.this.f15137d.mo14363c(str);
            }
        }
    };

    /* renamed from: c */
    private boolean f15136c = true;

    /* renamed from: e */
    private Handler f15138e = new Handler(Looper.getMainLooper()) { // from class: z1.abv.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
        }
    };

    @Override // p110z1.IEngineModel
    /* renamed from: d */
    public void mo14414d() {
    }

    public PXKJEnginModel(EnginCallback abwVar) {
        MQLanguageStub mQLanguageStub = new MQLanguageStub();
        mQLanguageStub.getClass();
        this.f15135b = new MQLanguageStub.MQAuxiliary();
        this.f15137d = abwVar;
    }

    @Override // p110z1.IEngineModel
    /* renamed from: a */
    public void mo14421a() {
        MqRunnerLite.getInstance().setOnSpecialMqCmdCallback(this.f15134a);
        MqRunnerLite.getInstance().recordLog2File(EnginSdk.f15153a);
        MqRunnerLite.getInstance().setOnScriptListener(new OnScriptListener() { // from class: z1.abv.2
            @Override // com.cyjh.mobileanjian.ipc.interfaces.OnScriptListener
            public void onUpdateControlBarPos(float f, int i, int i2) {
                if (PXKJEnginModel.this.f15137d != null) {
                    PXKJEnginModel.this.f15137d.mo14374a(f, i);
                }
            }

            @Override // com.cyjh.mobileanjian.ipc.interfaces.BasicScriptListener
            public void onStartScript() {
                if (PXKJEnginModel.this.f15137d != null) {
                    PXKJEnginModel.this.f15137d.mo14375a();
                }
            }

            @Override // com.cyjh.mobileanjian.ipc.interfaces.BasicScriptListener
            public void onPause() {
                if (PXKJEnginModel.this.f15137d != null) {
                    PXKJEnginModel.this.f15137d.mo14368b();
                }
            }

            @Override // com.cyjh.mobileanjian.ipc.interfaces.BasicScriptListener
            public void onResume() {
                if (PXKJEnginModel.this.f15137d != null) {
                    PXKJEnginModel.this.f15137d.mo14365c();
                }
            }

            @Override // com.cyjh.mobileanjian.ipc.interfaces.BasicScriptListener
            public void onStopScript(int i, String str) {
                if (PXKJEnginModel.this.f15137d != null) {
                    PXKJEnginModel.this.f15137d.mo14372a(i, str);
                }
            }

            @Override // com.cyjh.mobileanjian.ipc.interfaces.BasicScriptListener
            public void onScriptIsRunning() {
                if (PXKJEnginModel.this.f15137d != null) {
                    PXKJEnginModel.this.f15137d.mo14362d();
                }
            }
        });
    }

    @Override // p110z1.IEngineModel
    /* renamed from: b */
    public void mo14417b() {
        if (mo14415c()) {
            MqRunnerLite.getInstance().stop();
        }
    }

    @Override // p110z1.IEngineModel
    /* renamed from: c */
    public boolean mo14415c() {
        return MqRunnerLite.getInstance().isScriptStarted();
    }

    @Override // p110z1.IEngineModel
    /* renamed from: a */
    public void mo14420a(Script4Run script4Run) {
        if (!mo14415c()) {
            MqRunnerLite.getInstance().setScript(script4Run);
            MqRunnerLite.getInstance().start();
        }
    }

    @Override // p110z1.IEngineModel
    /* renamed from: e */
    public void mo14413e() {
        this.f15136c = false;
    }

    @Override // p110z1.IEngineModel
    /* renamed from: a */
    public void mo14419a(String str) {
        MqRunnerLite.getInstance().download(str);
    }

    @Override // p110z1.IEngineModel
    /* renamed from: b */
    public void mo14416b(String str) {
        MqRunnerLite.getInstance().getScriptPerm(str);
    }

    @Override // p110z1.IEngineModel
    /* renamed from: a */
    public void mo14418a(boolean z, int i, int i2, int i3, int i4) {
        LogUtils.m23734c("updateEnginDis", "setImageCrop:flag->" + z + " ,sw->" + i + " ,sh->" + i2 + " ,pP->" + i3 + " ,pL->" + i4);
        MqRunnerLite.getInstance().setImageCrop(z, i, i2, i3, i4);
    }
}
