package p110z1;

import android.app.Application;
import android.content.Context;
import com.cyjh.mobileanjian.ipc.interfaces.OnEngineStartCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnKeyEventListener;
import com.cyjh.mobileanjian.ipc.interfaces.OnRequestCallback;
import com.cyjh.mobileanjian.ipc.interfaces.RootProgressListener;
import com.cyjh.mqm.MQLanguageStub;
import com.cyjh.mq.sdk.MqBridge;
import com.cyjh.mq.sdk.MqRunner;
import com.cyjh.mq.sdk.MqRunnerLite;
import com.cyjh.mq.sdk.entity.Script4Run;

/* renamed from: z1.abt */
/* loaded from: classes3.dex */
public class EnginModel {

    /* renamed from: a */
    private IEngineModel f15119a;

    /* renamed from: b */
    private EnginCallback f15120b;

    /* renamed from: c */
    private MQLanguageStub.MQAuxiliary f15121c;

    public EnginModel() {
        MQLanguageStub mQLanguageStub = new MQLanguageStub();
        mQLanguageStub.getClass();
        this.f15121c = new MQLanguageStub.MQAuxiliary();
    }

    /* renamed from: a */
    public void m14431a(Context context, final EnginCallback abwVar, String str) {
        this.f15120b = abwVar;
        MqRunner.getInstance().setRequestCallback(new OnRequestCallback() { // from class: z1.abt.1
            @Override // com.cyjh.mobileanjian.ipc.interfaces.OnRequestCallback
            public void onCallback(int i, String str2) {
                abwVar.mo14371a(i, str2, 1);
            }
        });
        MqRunnerLite.getInstance().setRequestCallback(new OnRequestCallback() { // from class: z1.abt.2
            @Override // com.cyjh.mobileanjian.ipc.interfaces.OnRequestCallback
            public void onCallback(int i, String str2) {
                abwVar.mo14371a(i, str2, 2);
            }
        });
        MqBridge.init((Application) context, new OnKeyEventListener() { // from class: z1.abt.3
            @Override // com.cyjh.mobileanjian.ipc.interfaces.OnKeyEventListener
            public void onKeyEvent(int i) {
                abwVar.mo14373a(i);
            }
        }, new RootProgressListener() { // from class: z1.abt.4
            @Override // com.cyjh.mobileanjian.ipc.interfaces.RootProgressListener
            public void onRootProgress(String str2, int i) {
                abwVar.mo14369a(str2, i);
            }
        }, new OnEngineStartCallback() { // from class: z1.abt.5
            @Override // com.cyjh.mobileanjian.ipc.interfaces.OnEngineStartCallback
            public void onEngineStart(int i) {
                abwVar.mo14367b(i);
            }
        });
    }

    /* renamed from: a */
    public void m14434a(int i) {
        if (i == 0) {
            this.f15119a = new FWEnginModel(this.f15120b);
        } else {
            this.f15119a = new PXKJEnginModel(this.f15120b);
        }
    }

    /* renamed from: a */
    public void m14436a() {
        IEngineModel abxVar = this.f15119a;
        if (abxVar != null) {
            abxVar.mo14421a();
        }
    }

    /* renamed from: a */
    public void m14430a(Script4Run script4Run) {
        IEngineModel abxVar = this.f15119a;
        if (abxVar != null) {
            abxVar.mo14420a(script4Run);
        }
    }

    /* renamed from: b */
    public void m14426b() {
        IEngineModel abxVar = this.f15119a;
        if (abxVar != null) {
            abxVar.mo14417b();
        }
    }

    /* renamed from: c */
    public boolean m14424c() {
        IEngineModel abxVar = this.f15119a;
        if (abxVar != null) {
            return abxVar.mo14415c();
        }
        return false;
    }

    /* renamed from: a */
    public void m14432a(Context context, String str) {
        Application application = (Application) context;
        MqRunnerLite.getInstance().init(application, str);
        MqRunner.getInstance().init(application, str);
    }

    /* renamed from: a */
    public void m14429a(String str) {
        IEngineModel abxVar = this.f15119a;
        if (abxVar != null) {
            abxVar.mo14416b(str);
        }
    }

    /* renamed from: b */
    public void m14425b(String str) {
        IEngineModel abxVar = this.f15119a;
        if (abxVar != null) {
            abxVar.mo14419a(str);
        }
    }

    /* renamed from: a */
    public void m14427a(boolean z, int i, int i2, int i3, int i4) {
        IEngineModel abxVar = this.f15119a;
        if (abxVar != null) {
            abxVar.mo14418a(z, i, i2, i3, i4);
        }
    }

    /* renamed from: a */
    public void m14428a(boolean z) {
        this.f15121c.KeepCapture(z);
    }

    /* renamed from: a */
    public void m14435a(float f, float f2) {
        this.f15121c.SetScreenScale(f, f2);
    }

    /* renamed from: a */
    public boolean m14433a(int i, int i2, int i3, int i4, String str, String str2, int i5, float f, int[] iArr) {
        return this.f15121c.FindMultiColor(i, i2, i3, i4, str, str2, i5, f, iArr);
    }
}
