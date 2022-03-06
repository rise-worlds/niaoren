package com.lbd.xj.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.lbd.xj.C1467R;
import com.lbd.xj.app.XJApp;
import com.nrzs.base.router.provider.CarkeyProvider;
import com.nrzs.base.router.provider.ProviderFactory;
import com.nrzs.data.other.bean.AdResultInfoItem;
import p110z1.EventCollectManager;
import p110z1.EventConstants;
import p110z1.IntentToAssistService;
import p110z1.ShareVal;
import p110z1.apf;

/* renamed from: com.lbd.xj.ui.dialog.f */
/* loaded from: classes.dex */
public class XnkjRunDialog extends Dialog {

    /* renamed from: a */
    private static XnkjRunDialog f9757a;

    /* renamed from: b */
    private CheckBox f9758b;

    /* renamed from: c */
    private TextView f9759c;

    /* renamed from: d */
    private TextView f9760d;

    /* renamed from: e */
    private ImageView f9761e;

    /* renamed from: f */
    private Activity f9762f;

    /* renamed from: g */
    private DialogInterface.OnClickListener f9763g;

    /* renamed from: c */
    public void m19433c() {
    }

    public XnkjRunDialog(Context context, DialogInterface.OnClickListener onClickListener) {
        super(context, C1467R.style.MyDialog);
        if (context instanceof Activity) {
            this.f9762f = (Activity) context;
        }
        this.f9763g = onClickListener;
        m19434b();
        m19433c();
        m19432d();
        EventCollectManager.m12642a().m12640a(getContext(), "后台挂机弹窗", "后台挂机弹窗", EventConstants.f16401E);
    }

    /* renamed from: a */
    public static void m19436a(Context context, DialogInterface.OnClickListener onClickListener) {
        if (f9757a == null) {
            f9757a = new XnkjRunDialog(context, onClickListener);
        }
        f9757a.show();
    }

    /* renamed from: a */
    public static void m19437a() {
        XnkjRunDialog fVar = f9757a;
        if (fVar != null) {
            fVar.dismiss();
            f9757a = null;
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        Activity activity = this.f9762f;
        if (activity != null && !activity.isFinishing()) {
            super.dismiss();
        }
    }

    /* renamed from: b */
    public void m19434b() {
        setContentView(C1467R.layout.dialog_xnkj_run);
        this.f9761e = (ImageView) findViewById(C1467R.C1469id.iv_xnkj_video);
        this.f9759c = (TextView) findViewById(C1467R.C1469id.tv_xnkj_run_cancel);
        this.f9760d = (TextView) findViewById(C1467R.C1469id.tv_xnkj_run_sure);
        this.f9758b = (CheckBox) findViewById(C1467R.C1469id.cbox_xnkj_hint);
    }

    /* renamed from: d */
    public void m19432d() {
        this.f9761e.setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.dialog.f.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CarkeyProvider createCarkey = ProviderFactory.createCarkey();
                AdResultInfoItem adResultInfoItem = new AdResultInfoItem();
                adResultInfoItem.Title = "使用教程";
                adResultInfoItem.ExecArgs = createCarkey.getVaCourseUrl();
                IntentToAssistService.m12807a(XnkjRunDialog.this.getContext(), adResultInfoItem, 0);
            }
        });
        this.f9758b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.lbd.xj.ui.dialog.f.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                apf.m11841a(XJApp.getInstance().getApplicationContext(), ShareVal.f16591a, ShareVal.f16615y, z);
            }
        });
        this.f9760d.setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.dialog.f.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (XnkjRunDialog.this.f9763g != null) {
                    EventCollectManager.m12642a().m12640a(XnkjRunDialog.this.getContext(), "后台挂机弹窗-去试试", "后台挂机弹窗-去试试", EventConstants.f16402F);
                    XnkjRunDialog.this.f9763g.onClick(XnkjRunDialog.this, 1);
                }
            }
        });
        this.f9759c.setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.dialog.f.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (XnkjRunDialog.this.f9763g != null) {
                    XnkjRunDialog.this.f9763g.onClick(XnkjRunDialog.this, 0);
                }
            }
        });
    }
}
