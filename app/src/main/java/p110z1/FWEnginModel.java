package p110z1;

import com.cyjh.mobileanjian.ipc.interfaces.OnScriptListener;
import com.cyjh.p045mq.sdk.MqRunner;
import com.cyjh.p045mq.sdk.entity.Script4Run;

/* renamed from: z1.abu */
/* loaded from: classes3.dex */
public class FWEnginModel implements IEngineModel {

    /* renamed from: a */
    private EnginCallback f15132a;

    @Override // p110z1.IEngineModel
    /* renamed from: a */
    public void mo14418a(boolean z, int i, int i2, int i3, int i4) {
    }

    @Override // p110z1.IEngineModel
    /* renamed from: d */
    public void mo14414d() {
    }

    @Override // p110z1.IEngineModel
    /* renamed from: e */
    public void mo14413e() {
    }

    public FWEnginModel(EnginCallback abwVar) {
        this.f15132a = abwVar;
    }

    @Override // p110z1.IEngineModel
    /* renamed from: a */
    public void mo14421a() {
        MqRunner.getInstance().recordLog2File(EnginSdk.f15153a);
        MqRunner.getInstance().setOnScriptListener(new OnScriptListener() { // from class: z1.abu.1
            @Override // com.cyjh.mobileanjian.ipc.interfaces.OnScriptListener
            public void onUpdateControlBarPos(float f, int i, int i2) {
                if (FWEnginModel.this.f15132a != null) {
                    FWEnginModel.this.f15132a.mo14374a(f, i);
                }
            }

            @Override // com.cyjh.mobileanjian.ipc.interfaces.BasicScriptListener
            public void onStartScript() {
                if (FWEnginModel.this.f15132a != null) {
                    FWEnginModel.this.f15132a.mo14375a();
                }
            }

            @Override // com.cyjh.mobileanjian.ipc.interfaces.BasicScriptListener
            public void onPause() {
                if (FWEnginModel.this.f15132a != null) {
                    FWEnginModel.this.f15132a.mo14368b();
                }
            }

            @Override // com.cyjh.mobileanjian.ipc.interfaces.BasicScriptListener
            public void onResume() {
                if (FWEnginModel.this.f15132a != null) {
                    FWEnginModel.this.f15132a.mo14365c();
                }
            }

            @Override // com.cyjh.mobileanjian.ipc.interfaces.BasicScriptListener
            public void onStopScript(int i, String str) {
                if (FWEnginModel.this.f15132a != null) {
                    FWEnginModel.this.f15132a.mo14372a(i, str);
                }
            }

            @Override // com.cyjh.mobileanjian.ipc.interfaces.BasicScriptListener
            public void onScriptIsRunning() {
                if (FWEnginModel.this.f15132a != null) {
                    FWEnginModel.this.f15132a.mo14362d();
                }
            }
        });
    }

    @Override // p110z1.IEngineModel
    /* renamed from: b */
    public void mo14417b() {
        if (mo14415c()) {
            MqRunner.getInstance().stop();
        }
    }

    @Override // p110z1.IEngineModel
    /* renamed from: c */
    public boolean mo14415c() {
        return MqRunner.getInstance().isScriptStarted();
    }

    @Override // p110z1.IEngineModel
    /* renamed from: a */
    public void mo14420a(Script4Run script4Run) {
        if (!mo14415c()) {
            MqRunner.getInstance().setScript(script4Run);
            MqRunner.getInstance().start();
        }
    }

    @Override // p110z1.IEngineModel
    /* renamed from: a */
    public void mo14419a(String str) {
        MqRunner.getInstance().download(str);
    }

    @Override // p110z1.IEngineModel
    /* renamed from: b */
    public void mo14416b(String str) {
        MqRunner.getInstance().getScriptPerm(str);
    }
}
