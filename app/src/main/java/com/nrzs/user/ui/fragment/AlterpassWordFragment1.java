package com.nrzs.user.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.nrzs.user.C2222R;
import com.nrzs.user.ui.base.UserBaseFragment;
import com.nrzs.user.ui.view.AuthCodeView;
import p110z1.PhoneUtill;
import p110z1.aqg;

/* renamed from: com.nrzs.user.ui.fragment.AlterpassWordFragment1 */
/* loaded from: classes2.dex */
public class AlterpassWordFragment1 extends UserBaseFragment {

    /* renamed from: a */
    private TextView f11529a;

    /* renamed from: b */
    private EditText f11530b;

    /* renamed from: c */
    private EditText f11531c;

    /* renamed from: g */
    private AuthCodeView f11532g;

    /* renamed from: c */
    public static AlterpassWordFragment1 m18271c() {
        AlterpassWordFragment1 alterpassWordFragment1 = new AlterpassWordFragment1();
        alterpassWordFragment1.setArguments(new Bundle());
        return alterpassWordFragment1;
    }

    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: b */
    protected int mo18216b() {
        return C2222R.layout.fragment_phone_alterpw_layout1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: a */
    public void mo18221a(View view) {
        this.f11529a = (TextView) view.findViewById(C2222R.C2224id.nrzs_user_tv_nextstep);
        this.f11530b = (EditText) view.findViewById(C2222R.C2224id.nrzs_user_tv_regist_phone);
        this.f11531c = (EditText) view.findViewById(C2222R.C2224id.nrzs_user_edtext_regist_verification);
        this.f11532g = (AuthCodeView) view.findViewById(C2222R.C2224id.nrzs_user_img_regist_verification);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: e */
    public void mo18210e() {
        this.f11529a.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.fragment.AlterpassWordFragment1.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TextUtils.isEmpty(AlterpassWordFragment1.this.f11530b.getText().toString().trim())) {
                    aqg.m11586a(AlterpassWordFragment1.this.getContext(), "账号不存在");
                } else {
                    AlterpassWordFragment1.this.m18270c(view);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m18270c(View view) {
        if (getFragmentManager() != null && getFragmentManager() != null) {
            if (PhoneUtill.m11598a(this.f11530b.getText().toString().trim())) {
                getFragmentManager().beginTransaction().add(C2222R.C2224id.nrzs_user_fl_root, AlterpassWordFragmnet2.m18266a(this.f11530b.getText().toString().trim()), AlterpassWordFragmnet2.class.getName()).addToBackStack(null).commit();
            } else {
                getFragmentManager().beginTransaction().add(C2222R.C2224id.nrzs_user_fl_root, AlterpassWordZhFragmnet2.m18243a(this.f11530b.getText().toString().trim()), AlterpassWordFragmnet2.class.getName()).addToBackStack(null).commit();
            }
        }
    }
}
