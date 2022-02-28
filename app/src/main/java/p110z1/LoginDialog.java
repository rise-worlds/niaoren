package p110z1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.nrzs.moudleui.ui.state.LoadingView;
import com.nrzs.user.C2222R;

/* renamed from: z1.apy */
/* loaded from: classes3.dex */
public class LoginDialog extends Dialog {

    /* renamed from: a */
    private Context f17198a;

    /* renamed from: b */
    private LoadingView f17199b;

    /* renamed from: c */
    private LinearLayout f17200c;

    public LoginDialog(@NonNull Context context) {
        super(context);
        this.f17198a = context;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().requestFeature(1);
        setContentView(C2222R.layout.login_dialog);
        setCancelable(true);
        m11656c();
        m11658a();
        m11657b();
    }

    /* renamed from: a */
    protected void m11658a() {
        this.f17199b = new LoadingView(this.f17198a);
        this.f17200c = (LinearLayout) findViewById(C2222R.C2224id.home_1);
    }

    /* renamed from: b */
    protected void m11657b() {
        this.f17200c.addView(this.f17199b);
    }

    /* renamed from: c */
    public void m11656c() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        getWindow().setGravity(17);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().getDecorView().setBackgroundColor(getContext().getResources().getColor(C2222R.color.transparent));
        getWindow().setAttributes(attributes);
    }
}
