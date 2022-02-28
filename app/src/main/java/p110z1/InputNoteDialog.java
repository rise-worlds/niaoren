package p110z1;

import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import com.nrzs.data.p066ft.bean.SessionInfo;
import com.nrzs.ft.C1990R;
import com.nrzs.ft.ui.base.FtBaseDialog;

/* renamed from: z1.aoa */
/* loaded from: classes3.dex */
public class InputNoteDialog extends FtBaseDialog {

    /* renamed from: a */
    private EditText f16940a;

    /* renamed from: b */
    private AbstractC3761a f16941b;

    /* renamed from: c */
    private SessionInfo f16942c;

    /* renamed from: d */
    private String f16943d;

    /* compiled from: InputNoteDialog.java */
    /* renamed from: z1.aoa$a */
    /* loaded from: classes3.dex */
    public interface AbstractC3761a {
        /* renamed from: a */
        void mo12133a(String str);
    }

    public InputNoteDialog(Context context, SessionInfo sessionInfo, boolean z, AbstractC3761a aVar, String str) {
        super(context, z, C1990R.style.nrzs_assist_dialog_theme);
        this.f16941b = aVar;
        this.f16942c = sessionInfo;
        this.f16943d = str;
    }

    @Override // com.nrzs.ft.ui.base.FtBaseDialog
    /* renamed from: a */
    protected void mo3014a() {
        setCancelable(false);
    }

    @Override // com.nrzs.ft.ui.base.FtBaseDialog
    /* renamed from: b */
    protected void mo3013b() {
        setContentView(C1990R.layout.nrzs_ft_dialog_edit_note);
    }

    @Override // com.nrzs.ft.ui.base.FtBaseDialog
    /* renamed from: c */
    protected void mo3012c() {
        findViewById(C1990R.C1992id.tv_sure).setOnClickListener(new View.OnClickListener() { // from class: z1.aoa.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                InputNoteDialog.this.dismiss();
                String trim = InputNoteDialog.this.f16940a.getText().toString().trim();
                if (!TextUtils.isEmpty(trim) && InputNoteDialog.this.f16941b != null) {
                    InputNoteDialog.this.f16941b.mo12133a(trim);
                }
            }
        });
        findViewById(C1990R.C1992id.iv_back).setOnClickListener(new View.OnClickListener() { // from class: z1.aoa.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                InputNoteDialog.this.dismiss();
            }
        });
    }

    @Override // com.nrzs.ft.ui.base.FtBaseDialog
    /* renamed from: d */
    protected void mo3011d() {
        this.f16940a = (EditText) findViewById(C1990R.C1992id.edit_remark);
        if (!TextUtils.isEmpty(this.f16943d)) {
            this.f16940a.setText(this.f16943d);
            m12138a(this.f16940a);
        }
    }

    /* renamed from: a */
    public void m12138a(EditText editText) {
        Editable text = editText.getText();
        if (text instanceof Spannable) {
            Selection.setSelection(text, text.length());
        }
    }
}
