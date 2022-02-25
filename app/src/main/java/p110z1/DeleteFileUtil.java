package p110z1;

import com.common.utils.log.LogUtils;
import java.io.File;

/* renamed from: z1.aec */
/* loaded from: classes3.dex */
public class DeleteFileUtil {

    /* renamed from: a */
    public static String f15429a = "SHENG_DeleteFileUtil";

    /* renamed from: b */
    private static boolean f15430b = true;

    /* renamed from: a */
    public static boolean m14195a(String str) {
        File file = new File(str);
        if (!file.exists()) {
            if (!f15430b) {
                return false;
            }
            String str2 = f15429a;
            LogUtils.m22038d(str2, "删除文件失败:" + str + "不存在！");
            return false;
        } else if (file.isDirectory()) {
            return m14194b(str);
        } else {
            return m14193c(str);
        }
    }

    /* renamed from: b */
    public static boolean m14194b(String str) {
        if (!str.endsWith(File.separator)) {
            str = str + File.separator;
        }
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            if (f15430b) {
                LogUtils.m22036e(f15429a, "删除目录失败：" + str + "不存在！");
            }
            return false;
        }
        File[] listFiles = file.listFiles();
        boolean z = true;
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].isFile()) {
                z = m14193c(listFiles[i].getAbsolutePath());
                if (!z) {
                    break;
                }
            } else {
                if (listFiles[i].isDirectory() && !(z = m14194b(listFiles[i].getAbsolutePath()))) {
                    break;
                }
            }
        }
        if (!z) {
            if (f15430b) {
                LogUtils.m22036e(f15429a, "删除目录失败！");
            }
            return false;
        } else if (!file.delete()) {
            return false;
        } else {
            if (f15430b) {
                LogUtils.m22036e(f15429a, "删除目录" + str + "成功！");
            }
            return true;
        }
    }

    /* renamed from: c */
    public static boolean m14193c(String str) {
        File file = new File(str);
        if (!file.exists()) {
            if (f15430b) {
                String str2 = f15429a;
                LogUtils.m22038d(str2, "删除单个文件失败：" + str + "不存在！");
            }
            return false;
        } else if (!file.delete()) {
            if (f15430b) {
                String str3 = f15429a;
                LogUtils.m22038d(str3, "删除单个文件" + str + "失败！");
            }
            return false;
        } else if (!f15430b) {
            return true;
        } else {
            String str4 = f15429a;
            LogUtils.m22038d(str4, "删除单个文件" + str + "成功！");
            return true;
        }
    }
}
