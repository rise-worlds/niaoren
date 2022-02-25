package p110z1;

import java.io.File;

/* renamed from: z1.aog */
/* loaded from: classes3.dex */
public class FileTools {
    /* renamed from: a */
    public static File m12118a(String str, String str2) {
        String[] split = str2.split("/");
        File file = new File(str);
        if (split.length <= 1) {
            return new File(file, str2);
        }
        int i = 0;
        while (i < split.length - 1) {
            i++;
            file = new File(file, split[i]);
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(file, split[split.length - 1]);
    }

    /* renamed from: a */
    public static void m12119a(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                file2.delete();
            }
        }
    }
}
