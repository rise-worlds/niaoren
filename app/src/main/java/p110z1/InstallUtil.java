package p110z1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.p003v4.content.FileProvider;
import java.io.File;
import org.apache.tools.ant.taskdefs.XSLTLiaison;

/* renamed from: z1.ff */
/* loaded from: classes3.dex */
public class InstallUtil {

    /* renamed from: a */
    public static int f21622a = 2018;

    /* renamed from: b */
    private Activity f21623b;

    /* renamed from: c */
    private String f21624c;

    public InstallUtil(Activity activity, String str) {
        this.f21623b = activity;
        this.f21624c = str;
    }

    /* renamed from: a */
    public void m2900a() {
        if (Build.VERSION.SDK_INT >= 26) {
            m2896d();
        } else if (Build.VERSION.SDK_INT >= 24) {
            m2897c();
        } else {
            m2898b();
        }
    }

    /* renamed from: b */
    private void m2898b() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.parse(XSLTLiaison.FILE_PROTOCOL_PREFIX + this.f21624c), "application/vnd.android.package-archive");
        intent.addFlags(268435456);
        this.f21623b.startActivity(intent);
    }

    /* renamed from: c */
    private void m2897c() {
        Uri uriForFile = FileProvider.getUriForFile(this.f21623b, "com.angel.nrzs.fileprovider", new File(this.f21624c));
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setFlags(268435456);
        intent.addFlags(1);
        intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
        this.f21623b.startActivity(intent);
    }

    @RequiresApi(api = 26)
    /* renamed from: d */
    private void m2896d() {
        if (this.f21623b.getPackageManager().canRequestPackageInstalls()) {
            m2897c();
        } else {
            new AlertDialog.Builder(this.f21623b).setCancelable(false).setTitle("安装应用需要打开未知来源权限，请去设置中开启权限").setPositiveButton("确定", new DialogInterface.OnClickListener() { // from class: z1.ff.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    InstallUtil.this.f21623b.startActivityForResult(new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES"), InstallUtil.f21622a);
                }
            }).show();
        }
    }
}
