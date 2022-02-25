package com.lbd.p054xj.p056ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import com.lbd.p054xj.C1467R;
import com.lbd.p054xj.app.XJApp;
import com.lbd.p054xj.p056ui.view.BaseVideoView;

/* renamed from: com.lbd.xj.ui.dialog.e */
/* loaded from: classes.dex */
public class XnkjGuideDialog extends Dialog {

    /* renamed from: a */
    private static XnkjGuideDialog f9753a;

    /* renamed from: b */
    private BaseVideoView f9754b;

    public XnkjGuideDialog(Context context) {
        super(context, C1467R.style.Dialog_Fullscreen);
        m19440b();
        m19439c();
        m19438d();
    }

    /* renamed from: a */
    public static void m19441a(Context context) {
        if (f9753a == null) {
            f9753a = new XnkjGuideDialog(context);
        }
        f9753a.show();
    }

    /* renamed from: a */
    public static void m19442a() {
        XnkjGuideDialog eVar = f9753a;
        if (eVar != null) {
            eVar.dismiss();
            f9753a = null;
        }
    }

    /* renamed from: b */
    public void m19440b() {
        setContentView(C1467R.layout.dialog_xnkj_guide);
        this.f9754b = (BaseVideoView) findViewById(C1467R.C1469id.cvv_video_player);
    }

    /* renamed from: c */
    public void m19439c() {
        this.f9754b.m19296a("android.resource://" + XJApp.getInstance().getApplicationContext().getPackageName() + "/" + C1467R.raw.guide, new BaseVideoView.AbstractC1653a() { // from class: com.lbd.xj.ui.dialog.e.1
            @Override // com.lbd.p054xj.p056ui.view.BaseVideoView.AbstractC1653a
            /* renamed from: a */
            public void mo19294a() {
                XnkjGuideDialog.m19442a();
            }

            @Override // com.lbd.p054xj.p056ui.view.BaseVideoView.AbstractC1653a
            /* renamed from: b */
            public void mo19293b() {
                XnkjGuideDialog.m19442a();
            }
        });
    }

    /* renamed from: d */
    public void m19438d() {
        setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.lbd.xj.ui.dialog.e.2
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return i == 4 && keyEvent.getRepeatCount() == 0;
            }
        });
    }
}
