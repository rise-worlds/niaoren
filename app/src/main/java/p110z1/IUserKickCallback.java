package p110z1;

import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.p066ft.bean.response.SessionInfoResponse;
import com.nrzs.data.user.bean.respond.LoginResultV1Info;

/* renamed from: z1.alr */
/* loaded from: classes3.dex */
public interface IUserKickCallback {
    /* renamed from: a */
    void mo12181a(int i, String str);

    /* renamed from: a */
    void mo12180a(int i, boolean z);

    /* renamed from: a */
    void mo12178a(BaseResponse<LoginResultV1Info> baseResponse);

    /* renamed from: a */
    void mo12177a(SessionInfoResponse sessionInfoResponse);
}
