package p110z1;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import com.blankj.utilcode.util.FileUtils;
import com.nrzs.data.DataApp;
import java.io.File;
import org.apache.tools.ant.taskdefs.optional.vss.MSVSSConstants;

/* renamed from: z1.amh */
/* loaded from: classes3.dex */
public class NRZSFileConfig {

    /* renamed from: a */
    public static final String f16543a = ".uicfg";

    /* renamed from: b */
    public static final String f16544b = "a.uicfg";

    /* renamed from: c */
    public static final String f16545c = Environment.getExternalStorageDirectory().getPath() + File.separatorChar;

    /* renamed from: d */
    public static final String f16546d = f16545c + ShareVal.f16591a + File.separatorChar;

    /* renamed from: e */
    public static final String f16547e = f16546d + "welImg" + File.separatorChar;

    /* renamed from: f */
    public static final String f16548f = f16546d + "Crash" + File.separatorChar;

    /* renamed from: g */
    public static final String f16549g = f16546d + "Cache" + File.separatorChar;

    /* renamed from: h */
    public static final String f16550h = f16546d + "actiolLL" + File.separatorChar;

    /* renamed from: i */
    public static final String f16551i = f16546d + "script" + File.separatorChar;

    /* renamed from: j */
    public static final String f16552j = f16546d + "pxkjapp" + File.separatorChar;

    /* renamed from: k */
    public static final String f16553k = f16546d + MSVSSConstants.SS_EXE + File.separatorChar;

    /* renamed from: l */
    public static final String f16554l = f16546d + "anr" + File.separatorChar;

    /* renamed from: m */
    public static final String f16555m = f16546d + "log" + File.separatorChar;

    /* renamed from: n */
    public static final String f16556n = f16546d + "uploadtemp" + File.separatorChar;

    /* renamed from: o */
    public static final String f16557o = f16546d + "uploadtemp_zip" + File.separatorChar;

    /* renamed from: p */
    public static final String f16558p = f16551i + "file" + File.separatorChar;

    /* renamed from: q */
    public static final String f16559q = f16551i + "uiconfig" + File.separatorChar;

    /* renamed from: r */
    public static final String f16560r = f16546d + "xnkj" + File.separatorChar;

    /* renamed from: s */
    public static final String f16561s = f16560r + "rom" + File.separatorChar;

    /* renamed from: t */
    public static final String f16562t = f16560r + "patch" + File.separatorChar;

    /* renamed from: u */
    public static final String f16563u = f16560r + "apk" + File.separatorChar;

    /* renamed from: v */
    public static final String f16564v = f16546d + "stop_script_log" + File.separatorChar;

    /* renamed from: w */
    public static String f16565w = "";

    /* renamed from: a */
    public void m12517a() {
        if (!FileUtils.m22229b(f16546d)) {
            FileUtils.m22212e(f16546d);
        }
        if (!FileUtils.m22229b(f16555m)) {
            FileUtils.m22212e(f16555m);
        }
        if (!FileUtils.m22229b(f16556n)) {
            FileUtils.m22212e(f16556n);
        }
        if (!FileUtils.m22229b(f16557o)) {
            FileUtils.m22212e(f16557o);
        }
        if (!FileUtils.m22229b(f16547e)) {
            FileUtils.m22212e(f16547e);
        }
        if (!FileUtils.m22229b(f16548f)) {
            FileUtils.m22212e(f16548f);
        }
        if (!FileUtils.m22229b(f16549g)) {
            FileUtils.m22212e(f16549g);
        }
        if (!FileUtils.m22229b(f16551i)) {
            FileUtils.m22212e(f16551i);
        }
        if (!FileUtils.m22229b(f16550h)) {
            FileUtils.m22212e(f16550h);
        }
        if (!FileUtils.m22229b(f16552j)) {
            FileUtils.m22212e(f16552j);
        }
        if (!FileUtils.m22229b(f16553k)) {
            FileUtils.m22212e(f16553k);
        }
        if (!FileUtils.m22229b(f16554l)) {
            FileUtils.m22212e(f16554l);
        }
        if (!FileUtils.m22229b(f16558p)) {
            FileUtils.m22212e(f16558p);
        }
        if (!FileUtils.m22229b(f16559q)) {
            FileUtils.m22212e(f16559q);
        }
        if (!FileUtils.m22229b(f16561s)) {
            FileUtils.m22212e(f16561s);
        }
        if (!FileUtils.m22229b(f16562t)) {
            FileUtils.m22212e(f16562t);
        }
        if (!FileUtils.m22229b(f16563u)) {
            FileUtils.m22212e(f16563u);
        }
        if (!FileUtils.m22229b(f16564v)) {
            FileUtils.m22212e(f16564v);
        }
    }

    /* renamed from: a */
    public void m12516a(Context context) {
        f16565w = context.getFilesDir().getAbsolutePath() + File.separatorChar + "tessdata" + File.separatorChar;
        apf.m11842a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16585P, f16565w);
        StringBuilder sb = new StringBuilder();
        sb.append("去建文件");
        sb.append(f16565w);
        Log.e("解压", sb.toString());
        if (!FileUtils.m22229b(f16565w)) {
            FileUtils.m22212e(f16565w);
        }
    }
}
