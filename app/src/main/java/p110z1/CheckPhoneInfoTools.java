package p110z1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.text.format.Formatter;
import com.common.utils.log.LogUtils;
import com.lbd.p054xj.C1467R;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import p110z1.aes;

/* renamed from: z1.aeb */
/* loaded from: classes3.dex */
public class CheckPhoneInfoTools {

    /* renamed from: a */
    public static final String f15425a = "MemFree";

    /* renamed from: b */
    public static final String f15426b = "MemTotal";

    /* renamed from: c */
    private static final String f15427c = "/proc/meminfo";

    /* renamed from: d */
    private static final String f15428d = "CheckPhoneInfoTools";

    /* renamed from: a */
    public static boolean m14202a() {
        String a = aes.C3399a.m13840a("ro.product.manufacturer");
        LogUtils.m22038d("SHENG", a);
        return a.equals("redfinger");
    }

    /* renamed from: a */
    public static String m14201a(Context context) {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        long blockSize = statFs.getBlockSize();
        statFs.getAvailableBlocks();
        return Formatter.formatFileSize(context, statFs.getBlockCount() * blockSize);
    }

    /* renamed from: b */
    public static Double m14199b(Context context) {
        String readLine;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(f15427c), 4096);
            do {
                readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
            } while (!readLine.contains(f15426b));
            bufferedReader.close();
            return Double.valueOf(new Float(Float.valueOf(readLine.split("\\s+")[1]).floatValue() / 1048576.0f).doubleValue());
        } catch (IOException e) {
            e.printStackTrace();
            return Double.valueOf(0.0d);
        } catch (Exception e2) {
            e2.printStackTrace();
            return Double.valueOf(0.0d);
        }
    }

    /* renamed from: a */
    public static int m14200a(Context context, String str) {
        String readLine;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(f15427c), 4096);
            do {
                readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
            } while (!readLine.contains(str));
            bufferedReader.close();
            return (int) Math.floor(new Float(Float.valueOf(readLine.split("\\s+")[1]).floatValue() / 1048576.0f).doubleValue());
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    /* renamed from: b */
    public static int m14198b(Context context, String str) {
        return m14200a(context, f15425a);
    }

    /* renamed from: c */
    public static int m14196c(Context context, String str) {
        return m14200a(context, f15426b);
    }

    /* renamed from: c */
    public static void m14197c(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle("提示");
        builder.setMessage("手机内存太小，无法启动虚拟大师");
        builder.setIcon(C1467R.mipmap.app_icon);
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() { // from class: z1.aeb.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Process.killProcess(Process.myPid());
            }
        });
        builder.create().show();
    }
}
