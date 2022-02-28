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
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.cyjh.ddy.media.media.ActionCode;
import com.cyjh.mobileanjian.ipc.uip.UisScriptRunner;
import com.nrzs.ft.C1990R;

/* renamed from: z1.adt */
/* loaded from: classes3.dex */
public class XNKJSetDialog extends Dialog {

    /* renamed from: a */
    private LinearLayout f15372a;

    /* renamed from: b */
    private ScrollView f15373b;

    /* renamed from: c */
    private LinearLayout f15374c;

    public XNKJSetDialog(Context context, LinearLayout linearLayout) {
        super(context, C1990R.style.nrzs_assist_set_theme);
        requestWindowFeature(1);
        m14262a();
        if (FloatAssistManager.m12397i().m12403e() && FloatAssistManager.m12397i().m12405d()) {
            UisScriptRunner instance = UisScriptRunner.getInstance();
            instance.startLoop(NRZSFileConfig.f16553k + "script.lc");
        }
        this.f15374c = linearLayout;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m14259c();
        m14258d();
        m14261a(ScreenUtils.m23292g());
        if (getWindow() != null) {
            getWindow().addFlags(32);
            getWindow().setSoftInputMode(18);
        }
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    /* renamed from: a */
    public void m14262a() {
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
    private void m14259c() {
        setContentView(C1990R.layout.nrzs_ft_dialog_assist_set);
        this.f15372a = (LinearLayout) findViewById(C1990R.C1992id.nrzs_ft_ll_script_ui);
        this.f15373b = (ScrollView) findViewById(C1990R.C1992id.nrzs_ft_script_uip);
    }

    /* renamed from: d */
    private void m14258d() {
        this.f15372a.removeAllViews();
        this.f15373b.removeAllViews();
        LinearLayout linearLayout = this.f15374c;
        if (linearLayout != null) {
            ViewGroup viewGroup = (ViewGroup) linearLayout.getParent();
            if (viewGroup != null) {
                viewGroup.removeAllViews();
            }
            if (FloatAssistManager.m12397i().f16722a) {
                this.f15372a.setVisibility(0);
                this.f15373b.setVisibility(8);
            } else {
                this.f15372a.setVisibility(8);
                this.f15373b.setVisibility(0);
            }
            if (FloatAssistManager.m12397i().f16722a) {
                this.f15372a.addView(this.f15374c);
            } else {
                this.f15373b.addView(this.f15374c);
            }
        } else {
            this.f15372a.setVisibility(8);
            this.f15373b.setVisibility(8);
        }
    }

    /* renamed from: a */
    public void m14261a(boolean z) {
        int i;
        int i2;
        final Window window = getWindow();
        if (window != null) {
            final WindowManager.LayoutParams attributes = window.getAttributes();
            int b = apf.m11838b(getContext(), "common_shared_file", ShareVal.f16607q, 0);
            if (z) {
                i = (ScreenUtils.m23306a() - SizeUtils.m23262a(60.0f)) - b;
                i2 = ScreenUtils.m23302b() - SizeUtils.m23262a(144.0f);
                attributes.x = b / 2;
                attributes.y = SizeUtils.m23262a(-2.0f);
            } else {
                i = ScreenUtils.m23306a() - SizeUtils.m23262a(60.0f);
                i2 = (ScreenUtils.m23302b() - SizeUtils.m23262a(144.0f)) - b;
                attributes.x = 0;
                attributes.y = b / 2;
            }
            attributes.width = i;
            attributes.height = i2;
            this.f15373b.post(new Runnable() { // from class: z1.adt.1
                @Override // java.lang.Runnable
                public void run() {
                    window.setAttributes(attributes);
                }
            });
        }
    }

    /* renamed from: b */
    public void m14260b() {
        new Thread(new Runnable() { // from class: z1.adt.2
            @Override // java.lang.Runnable
            public void run() {
                UisScriptRunner.getInstance().stopLoop();
            }
        }).start();
        FloatAssistManager.m12397i().m12408c();
        this.f15372a.removeAllViews();
        this.f15373b.removeAllViews();
        this.f15374c = null;
        dismiss();
    }

    @Override // android.app.Dialog
    public void show() {
        if (getWindow() != null) {
            getWindow().getDecorView().setSystemUiVisibility(2);
            getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: z1.adt.3
                @Override // android.view.View.OnSystemUiVisibilityChangeListener
                public void onSystemUiVisibilityChange(int i) {
                    XNKJSetDialog.this.getWindow().getDecorView().setSystemUiVisibility(Build.VERSION.SDK_INT >= 19 ? 5894 : 1799);
                }
            });
        }
        super.show();
    }
}
