package p110z1;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.cyjh.ddy.media.media.ActionCode;
import com.cyjh.mobileanjian.ipc.uip.UisScriptRunner;
import com.nrzs.ft.C1990R;

/* renamed from: z1.ans */
/* loaded from: classes3.dex */
public class AssistSetDialog extends Dialog {

    /* renamed from: a */
    private LinearLayout f16833a;

    /* renamed from: b */
    private ScrollView f16834b;

    /* renamed from: c */
    private LinearLayout f16835c;

    public AssistSetDialog(Context context, LinearLayout linearLayout) {
        super(context, C1990R.style.nrzs_assist_set_theme);
        requestWindowFeature(1);
        m12215a();
        if (FloatAssistManager.m12397i().m12403e() && FloatAssistManager.m12397i().m12405d()) {
            UisScriptRunner instance = UisScriptRunner.getInstance();
            instance.startLoop(NRZSFileConfig.f16553k + "script.lc");
        }
        this.f16835c = linearLayout;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m12212c();
        m12211d();
        m12214a(ScreenUtils.m23292g());
        if (getWindow() != null) {
            getWindow().addFlags(32);
            getWindow().setSoftInputMode(18);
        }
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    /* renamed from: a */
    public void m12215a() {
        if (getWindow() == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 24) {
            getWindow().setType(2005);
        } else if (Build.VERSION.SDK_INT >= 26) {
            getWindow().setType(2038);
        } else {
            getWindow().setType(ActionCode.CtrlConnectRefuse_2002);
        }
    }

    /* renamed from: c */
    private void m12212c() {
        setContentView(C1990R.layout.nrzs_ft_dialog_assist_set);
        this.f16833a = (LinearLayout) findViewById(C1990R.C1992id.nrzs_ft_ll_script_ui);
        this.f16834b = (ScrollView) findViewById(C1990R.C1992id.nrzs_ft_script_uip);
    }

    /* renamed from: d */
    private void m12211d() {
        this.f16833a.removeAllViews();
        this.f16834b.removeAllViews();
        LinearLayout linearLayout = this.f16835c;
        if (linearLayout != null) {
            ViewGroup viewGroup = (ViewGroup) linearLayout.getParent();
            if (viewGroup != null) {
                viewGroup.removeAllViews();
            }
            if (FloatAssistManager.m12397i().f16722a) {
                this.f16833a.setVisibility(0);
                this.f16834b.setVisibility(8);
            } else {
                this.f16833a.setVisibility(8);
                this.f16834b.setVisibility(0);
            }
            if (FloatAssistManager.m12397i().f16722a) {
                this.f16833a.addView(this.f16835c);
            } else {
                this.f16834b.addView(this.f16835c);
            }
        } else {
            this.f16833a.setVisibility(8);
            this.f16834b.setVisibility(8);
        }
    }

    /* renamed from: a */
    public void m12214a(boolean z) {
        int i;
        int i2;
        final Window window = getWindow();
        if (window != null) {
            final WindowManager.LayoutParams attributes = window.getAttributes();
            int b = apf.m11838b(getContext(), "common_shared_file", ShareVal.f16607q, 0);
            LogUtils.m23734c("nrxsscreen", "屏幕高度：" + ScreenUtils.m23302b() + ",刘海屏高度：" + b);
            if (z) {
                i = (ScreenUtils.m23306a() - SizeUtils.m23262a(60.0f)) - b;
                i2 = ScreenUtils.m23302b() - SizeUtils.m23262a(144.0f);
                attributes.x = b / 2;
                attributes.y = SizeUtils.m23262a(-2.0f);
            } else {
                i = ScreenUtils.m23306a() - SizeUtils.m23262a(60.0f);
                i2 = (ScreenUtils.m23302b() - SizeUtils.m23262a(144.0f)) - b;
                attributes.x = 0;
                if (!apf.m11836b(getContext(), "common_shared_file", ShareVal.f16570A, false)) {
                    attributes.y = -(b / 2);
                }
            }
            attributes.width = i;
            attributes.height = i2;
            LogUtils.m23734c("nrxsscreen", "lp.y：" + attributes.y + "，lp.height：" + attributes.height);
            this.f16834b.post(new Runnable() { // from class: z1.ans.1
                @Override // java.lang.Runnable
                public void run() {
                    window.setAttributes(attributes);
                }
            });
        }
    }

    /* renamed from: b */
    public void m12213b() {
        new Thread(new Runnable() { // from class: z1.ans.2
            @Override // java.lang.Runnable
            public void run() {
                UisScriptRunner.getInstance().stopLoop();
            }
        }).start();
        FloatAssistManager.m12397i().m12408c();
        this.f16833a.removeAllViews();
        this.f16834b.removeAllViews();
        this.f16835c = null;
        dismiss();
    }

    @Override // android.app.Dialog
    public void show() {
        if (getWindow() != null) {
            getWindow().getDecorView().setSystemUiVisibility(2);
            getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: z1.ans.3
                @Override // android.view.View.OnSystemUiVisibilityChangeListener
                public void onSystemUiVisibilityChange(int i) {
                    AssistSetDialog.this.getWindow().getDecorView().setSystemUiVisibility(Build.VERSION.SDK_INT >= 19 ? 5894 : 1799);
                }
            });
        }
        super.show();
    }
}
