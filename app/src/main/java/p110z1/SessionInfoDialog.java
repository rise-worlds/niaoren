package p110z1;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.nrzs.data.p066ft.bean.SessionInfo;
import com.nrzs.p067ft.C1990R;
import com.nrzs.p067ft.p068ui.base.FtBaseDialog;
import p110z1.InputNoteDialog;

/* renamed from: z1.aob */
/* loaded from: classes3.dex */
public class SessionInfoDialog extends FtBaseDialog {

    /* renamed from: a */
    private TextView f16946a;

    /* renamed from: b */
    private AbstractC3766a f16947b;

    /* renamed from: c */
    private SessionInfo f16948c;

    /* renamed from: d */
    private boolean f16949d;

    /* compiled from: SessionInfoDialog.java */
    /* renamed from: z1.aob$a */
    /* loaded from: classes3.dex */
    public interface AbstractC3766a {
        /* renamed from: a */
        void mo12132a();

        /* renamed from: a */
        void mo12131a(String str);
    }

    @Override // com.nrzs.p067ft.p068ui.base.FtBaseDialog
    /* renamed from: d */
    protected void mo3011d() {
    }

    public SessionInfoDialog(Context context, SessionInfo sessionInfo, AbstractC3766a aVar) {
        this(context, sessionInfo, true, aVar);
    }

    public SessionInfoDialog(Context context, SessionInfo sessionInfo, boolean z, AbstractC3766a aVar) {
        super(context, z, C1990R.style.nrzs_assist_dialog_theme);
        this.f16947b = aVar;
        this.f16948c = sessionInfo;
        this.f16949d = z;
    }

    @Override // com.nrzs.p067ft.p068ui.base.FtBaseDialog
    /* renamed from: a */
    protected void mo3014a() {
        setCancelable(false);
    }

    @Override // com.nrzs.p067ft.p068ui.base.FtBaseDialog
    /* renamed from: b */
    protected void mo3013b() {
        setContentView(C1990R.layout.nrzs_ft_dialog_session_info);
        TextView textView = (TextView) findViewById(C1990R.C1992id.tv_note_time);
        TextView textView2 = (TextView) findViewById(C1990R.C1992id.tv_note_phone);
        TextView textView3 = (TextView) findViewById(C1990R.C1992id.iv_imei);
        this.f16946a = (TextView) findViewById(C1990R.C1992id.tv_remark);
        SessionInfo sessionInfo = this.f16948c;
        if (sessionInfo != null) {
            textView.setText(sessionInfo.getLastLoginTime());
            textView2.setText(this.f16948c.getDeviceModel());
            textView3.setText(this.f16948c.getDeviceCode());
            this.f16946a.setText(this.f16948c.getDeviceNote());
        }
    }

    @Override // com.nrzs.p067ft.p068ui.base.FtBaseDialog
    /* renamed from: c */
    protected void mo3012c() {
        findViewById(C1990R.C1992id.tv_edit_remark).setOnClickListener(new View.OnClickListener() { // from class: z1.aob.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SessionInfoDialog.this.dismiss();
                SessionInfoDialog.this.m12134e();
            }
        });
        findViewById(C1990R.C1992id.tv_unOnline).setOnClickListener(new View.OnClickListener() { // from class: z1.aob.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SessionInfoDialog.this.dismiss();
                SessionInfoDialog.this.f16947b.mo12132a();
            }
        });
        findViewById(C1990R.C1992id.iv_back).setOnClickListener(new View.OnClickListener() { // from class: z1.aob.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SessionInfoDialog.this.dismiss();
            }
        });
    }

    /* renamed from: e */
    public void m12134e() {
        new InputNoteDialog(getContext(), this.f16948c, this.f16949d, new InputNoteDialog.AbstractC3761a() { // from class: z1.aob.4
            @Override // p110z1.InputNoteDialog.AbstractC3761a
            /* renamed from: a */
            public void mo12133a(String str) {
                if (!TextUtils.isEmpty(str) && SessionInfoDialog.this.f16947b != null) {
                    SessionInfoDialog.this.f16947b.mo12131a(str);
                }
            }
        }, "").show();
    }
}
