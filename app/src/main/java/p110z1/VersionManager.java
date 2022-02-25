package p110z1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Environment;
import android.widget.Toast;
import com.angel.nrzs.C0692R;
import com.nrzs.data.appupdate.bean.request.GetVersionRequest;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import java.io.File;
import p110z1.VersionEvent;

/* renamed from: z1.en */
/* loaded from: classes3.dex */
public class VersionManager {

    /* renamed from: a */
    public static final String f21479a = Environment.getExternalStorageDirectory() + GlobalConstants.f16480c + "/apkdownload";

    /* renamed from: b */
    public static boolean f21480b = true;

    /* renamed from: c */
    public static boolean f21481c = true;

    /* renamed from: d */
    public static boolean f21482d = false;

    /* renamed from: e */
    private static VersionManager f21483e;

    /* renamed from: g */
    private UpdateInfoResponse f21485g;

    /* renamed from: h */
    private Activity f21486h;

    /* renamed from: i */
    private DialogC5320es f21487i;

    /* renamed from: j */
    private AppUpdateModel f21488j;

    /* renamed from: f */
    private boolean f21484f = false;

    /* renamed from: k */
    private boolean f21489k = false;

    /* renamed from: l */
    private UICallback f21490l = new UICallback<UpdateInfoResponse>() { // from class: z1.en.1
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(UpdateInfoResponse ajlVar) {
            VersionManager.this.f21485g = ajlVar;
            UpdateBean fUpdate = VersionManager.this.f21485g.getFUpdate();
            UpdateBean oUpdate = VersionManager.this.f21485g.getOUpdate();
            if (fUpdate == null) {
                fUpdate = new UpdateBean();
            }
            if (oUpdate == null) {
                oUpdate = new UpdateBean();
            }
            if (VersionUtils.m2839a(oUpdate, VersionManager.this.f21486h) || VersionUtils.m2839a(fUpdate, VersionManager.this.f21486h)) {
                VersionManager.this.m3092a(fUpdate, oUpdate);
                VersionManager.this.f21489k = true;
                EventBus.m3448a().m3427d(new VersionEvent.C3562a(true));
            } else if (!VersionManager.this.f21484f) {
                new AlertDialog.Builder(VersionManager.this.f21486h).setTitle(VersionManager.this.f21486h.getString(C0692R.string.check_version_dialog_title)).setMessage(VersionManager.this.f21486h.getString(C0692R.string.check_version_dialog_msg)).setPositiveButton("确定", new DialogInterface.OnClickListener() { // from class: z1.en.1.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create().show();
                VersionManager.this.f21489k = false;
                EventBus.m3448a().m3427d(new VersionEvent.C3562a(false));
            }
        }
    };

    /* renamed from: m */
    private ThreadCallback f21491m = new ThreadCallback<UpdateInfoResponse, String>() { // from class: z1.en.2
        /* renamed from: a */
        public UpdateInfoResponse onResponse(String str) {
            BaseResponse baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<UpdateInfoResponse>>() { // from class: z1.en.2.1
            });
            if (baseResponse.code != 1) {
                Toast.makeText(VersionManager.this.f21486h, VersionManager.this.f21486h.getString(C0692R.string.check_version_fail), 1).show();
                VersionManager.f21480b = true;
            }
            if (baseResponse == null || !(baseResponse.data instanceof UpdateInfoResponse)) {
                return null;
            }
            return (UpdateInfoResponse) baseResponse.data;
        }
    };

    private VersionManager(Activity activity) {
        this.f21486h = activity;
    }

    /* renamed from: a */
    public static VersionManager m3095a(Activity activity) {
        if (f21483e == null) {
            synchronized (VersionManager.class) {
                if (f21483e == null) {
                    f21483e = new VersionManager(activity);
                }
            }
        }
        VersionManager enVar = f21483e;
        if (activity != enVar.f21486h) {
            enVar.m3084b(activity);
        }
        return f21483e;
    }

    /* renamed from: b */
    private void m3084b(Activity activity) {
        this.f21486h = activity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m3092a(UpdateBean ajkVar, UpdateBean ajkVar2) {
        Activity activity = this.f21486h;
        int a = VersionUtils.m2842a(activity, activity.getPackageName());
        int versionCode = ajkVar.getVersionCode();
        int versionCode2 = ajkVar2.getVersionCode();
        int isOpenBox = ajkVar2.getIsOpenBox();
        if (versionCode > a) {
            m3091a(ajkVar, true);
        } else if (versionCode2 <= a) {
        } else {
            if (isOpenBox == 1 || !this.f21484f) {
                m3091a(ajkVar2, false);
            }
        }
    }

    /* renamed from: b */
    private void m3083b(UpdateBean ajkVar, UpdateBean ajkVar2) {
        try {
            if (ajkVar.getVersionCode() <= 0 && ajkVar2.getVersionCode() > 0 && VersionUtils.m2839a(ajkVar2, this.f21486h) && f21481c) {
                m3091a(ajkVar2, false);
            } else if (ajkVar.getVersionCode() > 0 && ajkVar2.getVersionCode() <= 0 && VersionUtils.m2839a(ajkVar, this.f21486h)) {
                m3091a(ajkVar, true);
            } else if (VersionUtils.m2839a(ajkVar2, this.f21486h) && VersionUtils.m2839a(ajkVar, this.f21486h) && ajkVar2.getVersionCode() > ajkVar.getVersionCode()) {
                ajkVar2.setIsCompulsory(1);
                m3091a(ajkVar2, true);
            } else if (VersionUtils.m2839a(ajkVar2, this.f21486h) && VersionUtils.m2839a(ajkVar, this.f21486h) && ajkVar2.getVersionCode() < ajkVar.getVersionCode() && f21481c) {
                m3091a(ajkVar, true);
            } else if (VersionUtils.m2839a(ajkVar2, this.f21486h) && !VersionUtils.m2839a(ajkVar, this.f21486h)) {
                m3091a(ajkVar2, false);
            } else if (!VersionUtils.m2839a(ajkVar2, this.f21486h) && VersionUtils.m2839a(ajkVar, this.f21486h)) {
                m3091a(ajkVar, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private void m3091a(UpdateBean ajkVar, boolean z) {
        if (ajkVar != null) {
            DialogC5320es esVar = this.f21487i;
            if (esVar == null || !esVar.isShowing()) {
                this.f21487i = new DialogC5320es(this.f21486h, ajkVar, z);
                DialogC5320es esVar2 = this.f21487i;
                if (esVar2 != null && !esVar2.isShowing()) {
                    this.f21487i.show();
                }
            }
        }
    }

    /* renamed from: a */
    private String m3093a(String str) {
        File file = new File(f21479a);
        if (!file.exists()) {
            file.mkdir();
        }
        return f21479a + "/" + (str + ".apk");
    }

    /* renamed from: a */
    public void m3094a(UICallback oVar) {
        GetVersionRequest getVersionRequest = new GetVersionRequest();
        if (this.f21488j == null) {
            this.f21488j = new AppUpdateModel();
        }
        try {
            this.f21488j.m12912a(getVersionRequest, new UICallback<UpdateInfoResponse>() { // from class: z1.en.3
                @Override // com.nrzs.http.UICallback
                /* renamed from: a */
                public void mo3021a(Throwable th) {
                }

                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo3022a(UpdateInfoResponse ajlVar) {
                }
            }, this.f21491m);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m3086a(boolean z, boolean z2) {
        this.f21484f = z2;
        f21481c = z;
        GetVersionRequest getVersionRequest = new GetVersionRequest();
        if (this.f21488j == null) {
            this.f21488j = new AppUpdateModel();
        }
        try {
            this.f21488j.m12912a(getVersionRequest, this.f21490l, this.f21491m);
        } catch (Exception e) {
            e.printStackTrace();
        }
        f21480b = false;
    }

    /* renamed from: a */
    public boolean m3096a() {
        return this.f21489k;
    }

    /* renamed from: b */
    public void m3085b() {
        DialogC5320es esVar = this.f21487i;
        if (esVar != null && esVar.isShowing()) {
            this.f21487i.dismiss();
        }
    }
}
