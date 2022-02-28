package p110z1;

import android.app.ActivityManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.WindowManager;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.nrzs.ft.C1990R;
import java.util.List;
import p110z1.FtEvent;

/* renamed from: z1.anr */
/* loaded from: classes3.dex */
public class DragViewPresenter {

    /* renamed from: a */
    public static final long f16825a = 2000;

    /* renamed from: b */
    public static final long f16826b = 1000;

    /* renamed from: c */
    private WindowManager.LayoutParams f16827c;

    /* renamed from: f */
    private IDragViewRun f16830f;

    /* renamed from: d */
    private boolean f16828d = false;

    /* renamed from: e */
    private Handler f16829e = new Handler(Looper.getMainLooper()) { // from class: z1.anr.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1) {
                if (DragViewPresenter.this.m12217k()) {
                    DragViewPresenter.this.f16827c.x = ScreenUtils.m23306a();
                } else {
                    DragViewPresenter.this.f16827c.x = 0;
                }
                DragViewPresenter anrVar = DragViewPresenter.this;
                anrVar.m12236a(anrVar.f16827c);
                DragViewPresenter.this.m12216l();
                DragViewPresenter.this.f16829e.sendEmptyMessageDelayed(2, 1000L);
            } else if (message.what == 2) {
                DragViewPresenter.this.m12231a(DragViewPresenter.this.m12217k());
            }
        }
    };

    /* renamed from: g */
    private int f16831g = SizeUtils.m23262a(50.0f) / 2;

    public DragViewPresenter(WindowManager.LayoutParams layoutParams, IDragViewRun aodVar) {
        this.f16827c = layoutParams;
        this.f16830f = aodVar;
    }

    /* renamed from: a */
    public void m12238a() {
        if (!FloatAssistManager.m12397i().m12427a()) {
            this.f16829e.sendEmptyMessageDelayed(1, 2000L);
            this.f16830f.mo12124a(C1990R.C1991drawable.bird_logo_pop);
        } else {
            m12230b();
        }
        this.f16829e.sendEmptyMessageDelayed(1, 2000L);
    }

    /* renamed from: b */
    public void m12230b() {
        this.f16829e.sendEmptyMessageDelayed(1, 2000L);
        this.f16830f.mo12124a(C1990R.C1991drawable.bird_stop_pop);
    }

    /* renamed from: h */
    private void m12220h() {
        this.f16829e.sendEmptyMessageDelayed(1, 2000L);
        this.f16830f.mo12124a(C1990R.C1991drawable.bird_logo_pop);
        if (FloatDataManager.m12352j().m12371a() != 0 || !m12219i()) {
            Log.d("setCurrentStopType", "wdscstopScript-currentStopType:" + FloatDataManager.m12352j().m12371a(), new Exception());
            FloatDataManager.m12352j().m12370a(0);
            return;
        }
        Log.d("setCurrentStopType", "stopScript-currentStopType:" + FloatDataManager.m12352j().m12371a(), new Exception());
        Log.e("MyComponentDelegate", "DragViewPresenter");
        IntentToAssistService.m12812a(this.f16830f.getContext(), 4);
    }

    /* renamed from: i */
    private boolean m12219i() {
        String packageName = this.f16830f.getContext().getApplicationContext().getPackageName();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) this.f16830f.getContext().getApplicationContext().getSystemService(ServiceManagerNative.ACTIVITY)).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.processName.equals(packageName) && runningAppProcessInfo.importance == 100) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: c */
    public void m12227c() {
        this.f16829e.removeMessages(1);
        this.f16829e.removeMessages(2);
        m12237a(1);
    }

    /* renamed from: d */
    public void m12225d() {
        if (!this.f16828d) {
            this.f16828d = true;
        }
    }

    /* renamed from: e */
    public void m12223e() {
        if (!this.f16828d) {
            if (FloatAssistManager.m12397i().m12427a()) {
                FloatAssistManager.m12397i().m12414b();
            } else {
                m12218j();
            }
        }
        this.f16829e.sendEmptyMessageDelayed(1, 2000L);
        this.f16828d = false;
    }

    /* renamed from: j */
    private void m12218j() {
        FloatViewManager.m12346a(this.f16830f.getContext().getApplicationContext()).m12337b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m12231a(boolean z) {
        int i;
        if (!z) {
            if (FloatAssistManager.m12397i().m12427a()) {
                i = C1990R.C1991drawable.bird_stop_pop_left;
            } else {
                i = C1990R.C1991drawable.bird_logo_pop_left;
            }
        } else if (FloatAssistManager.m12397i().m12427a()) {
            i = C1990R.C1991drawable.bird_stop_pop_right;
        } else {
            i = C1990R.C1991drawable.bird_logo_pop_right;
        }
        this.f16830f.mo12124a(i);
    }

    /* renamed from: a */
    public void m12236a(WindowManager.LayoutParams layoutParams) {
        int a = ScreenUtils.m23306a();
        int b = ScreenUtils.m23302b() - 52;
        if (layoutParams.x <= 0) {
            layoutParams.x = 0;
        } else if (layoutParams.x > a) {
            layoutParams.x = a;
        }
        if (layoutParams.y <= -52) {
            layoutParams.y = -52;
        } else if (layoutParams.y > b) {
            layoutParams.y = b;
        }
        this.f16830f.mo12123f();
    }

    /* renamed from: b */
    public void m12229b(WindowManager.LayoutParams layoutParams) {
        m12231a(m12217k());
        m12236a(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k */
    public boolean m12217k() {
        return this.f16827c.x + this.f16831g >= ScreenUtils.m23300c() / 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l */
    public void m12216l() {
        int i;
        if (FloatAssistManager.m12397i().m12427a()) {
            i = C1990R.C1991drawable.bird_stop_pop;
        } else {
            i = C1990R.C1991drawable.bird_logo_pop;
        }
        this.f16830f.mo12124a(i);
    }

    /* renamed from: a */
    public void m12237a(int i) {
        int i2;
        if (FloatAssistManager.m12397i().m12427a()) {
            i2 = C1990R.C1991drawable.bird_stop_pop;
        } else {
            i2 = C1990R.C1991drawable.bird_logo_pop;
        }
        this.f16830f.mo12124a(i2);
        if (i == 2) {
            this.f16829e.sendEmptyMessageDelayed(1, 2000L);
        }
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m12234a(FtEvent.C3564b bVar) {
        if (bVar.f16159a == 1) {
            m12230b();
        } else {
            m12220h();
        }
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m12235a(FtEvent.C3563a aVar) {
        this.f16830f.setFloatViewPosition(aVar.f16157a);
    }

    /* renamed from: f */
    public void m12222f() {
        Log.d("setCurrentStopType", "1 DragViewPresenter-register:" + FloatDataManager.m12352j().m12371a());
        if (!EventBus.m3448a().m3434b(this)) {
            Log.d("setCurrentStopType", "2 DragViewPresenter-register:" + FloatDataManager.m12352j().m12371a());
            EventBus.m3448a().m3446a(this);
        }
    }

    /* renamed from: g */
    public void m12221g() {
        Log.d("setCurrentStopType", "DragViewPresenter-unRegister:" + FloatDataManager.m12352j().m12371a());
        EventBus.m3448a().m3430c(this);
    }
}
