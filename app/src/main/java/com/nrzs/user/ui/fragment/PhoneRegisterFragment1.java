package com.nrzs.user.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.user.bean.request.RegSmsinfo;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import com.nrzs.user.C2222R;
import com.nrzs.user.ui.base.UserBaseFragment;
import com.nrzs.user.ui.view.AuthCodeView;
import p110z1.PhoneUtill;
import p110z1.SwapFragmentUtil;
import p110z1.TypeToken;
import p110z1.UserRepository;
import p110z1.apa;
import p110z1.aqg;

/* renamed from: com.nrzs.user.ui.fragment.PhoneRegisterFragment1 */
/* loaded from: classes2.dex */
public class PhoneRegisterFragment1 extends UserBaseFragment {

    /* renamed from: a */
    private TextView f11589a;

    /* renamed from: b */
    private TextView f11590b;

    /* renamed from: c */
    private UserRepository f11591c;

    /* renamed from: g */
    private EditText f11592g;

    /* renamed from: h */
    private EditText f11593h;

    /* renamed from: i */
    private AuthCodeView f11594i;

    /* renamed from: j */
    private ThreadCallback f11595j = new ThreadCallback<BaseResponse<Object>, String>() { // from class: com.nrzs.user.ui.fragment.PhoneRegisterFragment1.1
        /* renamed from: a */
        public BaseResponse<Object> onResponse(String str) {
            BaseResponse<Object> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<Object>>() { // from class: com.nrzs.user.ui.fragment.PhoneRegisterFragment1.1.1
            });
            if (baseResponse != null) {
                return baseResponse;
            }
            return null;
        }
    };

    /* renamed from: k */
    private UICallback f11596k = new UICallback<BaseResponse<Object>>() { // from class: com.nrzs.user.ui.fragment.PhoneRegisterFragment1.2
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<Object> baseResponse) {
            if (baseResponse == null) {
                return;
            }
            if (baseResponse.code == 0) {
                aqg.m11586a(PhoneRegisterFragment1.this.getContext(), baseResponse.msg);
            } else {
                PhoneRegisterFragment1.this.m18224h();
            }
        }
    };

    /* renamed from: c */
    public static PhoneRegisterFragment1 m18227c() {
        PhoneRegisterFragment1 phoneRegisterFragment1 = new PhoneRegisterFragment1();
        phoneRegisterFragment1.setArguments(new Bundle());
        return phoneRegisterFragment1;
    }

    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: b */
    protected int mo18216b() {
        return C2222R.layout.fragment_phone_register_layout1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: a */
    public void mo18221a(View view) {
        this.f11589a = (TextView) view.findViewById(C2222R.C2224id.nrzs_user_tv_nextstep);
        this.f11590b = (TextView) view.findViewById(C2222R.C2224id.nrzs_user_tv_nophone_register);
        this.f11592g = (EditText) view.findViewById(C2222R.C2224id.nrzs_user_tv_regist_phone);
        this.f11593h = (EditText) view.findViewById(C2222R.C2224id.nrzs_user_edtext_regist_verification);
        this.f11594i = (AuthCodeView) view.findViewById(C2222R.C2224id.nrzs_user_img_regist_verification);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: e */
    public void mo18210e() {
        this.f11589a.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.fragment.PhoneRegisterFragment1.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TextUtils.isEmpty(PhoneRegisterFragment1.this.f11592g.getText().toString().trim())) {
                    aqg.m11586a(PhoneRegisterFragment1.this.getContext(), "请输入正确的手机号码");
                } else if (!PhoneUtill.m11598a(PhoneRegisterFragment1.this.f11592g.getText().toString().trim())) {
                    aqg.m11586a(PhoneRegisterFragment1.this.getContext(), "请输入正确的手机号码");
                } else {
                    PhoneRegisterFragment1.this.m18224h();
                }
            }
        });
        this.f11590b.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.fragment.PhoneRegisterFragment1.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PhoneRegisterFragment1.this.m18226c(view);
            }
        });
    }

    /* renamed from: g */
    public void m18225g() {
        try {
            this.f11591c = new UserRepository();
            RegSmsinfo regSmsinfo = new RegSmsinfo();
            regSmsinfo.Tel = this.f11592g.getText().toString().trim();
            this.f11591c.m12541a(regSmsinfo, this.f11596k, this.f11595j);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public void m18224h() {
        if (getFragmentManager() != null) {
            getFragmentManager().beginTransaction().add(C2222R.C2224id.nrzs_user_fl_root, PhoneRegisterFragment2.m18217a(this.f11592g.getText().toString().trim()), PhoneRegisterFragment2.class.getName()).addToBackStack(null).commit();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m18226c(View view) {
        try {
            if (getFragmentManager() != null) {
                SwapFragmentUtil.m11589a(AccountRegisterFragment.class.getName(), getClass().getName(), getFragmentManager(), C2222R.C2224id.nrzs_user_fl_root, true);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (InstantiationException e3) {
            e3.printStackTrace();
        }
    }
}
