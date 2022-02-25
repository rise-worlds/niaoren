package p110z1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.tools.ant.util.DateUtils;

/* renamed from: z1.alz */
/* loaded from: classes3.dex */
public class WritLogUtil {

    /* renamed from: a */
    private static Boolean f16459a = true;

    /* renamed from: b */
    private static String f16460b = NRZSFileConfig.f16564v;

    /* renamed from: c */
    private static String f16461c = "Log.txt";

    /* renamed from: d */
    private static SimpleDateFormat f16462d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /* renamed from: e */
    private static SimpleDateFormat f16463e = new SimpleDateFormat(DateUtils.ISO8601_DATE_PATTERN);

    /* renamed from: a */
    public static void m12529a(String str) {
        Date date;
        String format = f16463e.format(new Date());
        String str2 = f16462d.format(date) + "    " + str;
        File file = new File(f16460b);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file.toString(), format + f16461c);
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (Exception unused) {
            }
        }
        try {
            FileWriter fileWriter = new FileWriter(file2, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(str2);
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
