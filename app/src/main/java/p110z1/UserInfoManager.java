package p110z1;

import android.os.Process;
import android.text.TextUtils;
import com.nrzs.data.DataApp;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.user.bean.UserInfo;
import com.nrzs.data.user.bean.request.UserRInfo;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/* renamed from: z1.alg */
/* loaded from: classes3.dex */
public class UserInfoManager {

    /* renamed from: a */
    private UserRepository f16349a;

    /* renamed from: b */
    private String f16350b;

    private UserInfoManager() {
        this.f16349a = null;
        this.f16350b = m12593a(Process.myPid());
    }

    /* compiled from: UserInfoManager.java */
    /* renamed from: z1.alg$a */
    /* loaded from: classes3.dex */
    private static class C3651a {

        /* renamed from: a */
        private static final UserInfoManager f16354a = new UserInfoManager();

        private C3651a() {
        }
    }

    /* renamed from: a */
    public static UserInfoManager m12594a() {
        return C3651a.f16354a;
    }

    /* renamed from: a */
    public void m12590a(DoneCallback<UserInfo> daqVar) {
        if (!LoginManager.m12620d().m12606r()) {
            daqVar.onDone(null);
            return;
        }
        UserRInfo userRInfo = new UserRInfo();
        userRInfo.UserID = LoginManager.m12620d().m12630a().UserID;
        m12592a(userRInfo, daqVar);
    }

    /* renamed from: b */
    public void m12589b(DoneCallback<UserInfo> daqVar) {
        if (!FtUserManager.m12790g().m12791f()) {
            daqVar.onDone(null);
            return;
        }
        UserRInfo userRInfo = new UserRInfo();
        userRInfo.UserID = FtUserManager.m12790g().m12797a();
        m12592a(userRInfo, daqVar);
    }

    /* renamed from: a */
    private void m12592a(UserRInfo userRInfo, final DoneCallback<UserInfo> daqVar) {
        try {
            if (this.f16349a == null) {
                this.f16349a = new UserRepository();
            }
            this.f16349a.m12539a(userRInfo, new UICallback<BaseResponse<UserInfo>>() { // from class: z1.alg.1
                @Override // com.nrzs.http.UICallback
                /* renamed from: a */
                public void mo3021a(Throwable th) {
                    daqVar.onDone(null);
                }

                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo3022a(BaseResponse<UserInfo> baseResponse) {
                    daqVar.onDone(baseResponse.data);
                }
            }, new ThreadCallback() { // from class: z1.-$$Lambda$alg$A1q1lp4yD0pRx2tt5yfpRzzvGtY
                @Override // com.nrzs.http.ThreadCallback
                public final Object onResponse(String str) {
                    BaseResponse a;
                    a = UserInfoManager.this.m12591a(str);
                    return a;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ BaseResponse m12591a(String str) {
        return (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<UserInfo>>() { // from class: z1.alg.2
        });
    }

    /* renamed from: c */
    public void m12588c(DoneCallback<UserInfo> daqVar) {
        if (DataApp.m18939d().m18947a().getPackageName().equals(this.f16350b)) {
            m12590a(daqVar);
        } else {
            m12589b(daqVar);
        }
    }

    /* renamed from: a */
    public static String m12593a(int i) {
        Throwable th;
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/" + i + "/cmdline"));
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
        }
        try {
            String readLine = bufferedReader.readLine();
            if (!TextUtils.isEmpty(readLine)) {
                readLine = readLine.trim();
            }
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return readLine;
        } catch (Throwable th3) {
            th = th3;
            try {
                th.printStackTrace();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                return null;
            } catch (Throwable th4) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                throw th4;
            }
        }
    }
}
