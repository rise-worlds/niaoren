package com.goldcoast.sdk.p052c;

import android.content.Context;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;

/* renamed from: com.goldcoast.sdk.c.c */
/* loaded from: classes.dex */
public final class CrashRepository {

    /* renamed from: c */
    private static CrashRepository f9005c;

    /* renamed from: d */
    private static Context f9006d;

    /* renamed from: b */
    private String f9008b = "noend.ini";

    /* renamed from: a */
    private String f9007a = f9006d.getFilesDir().getAbsolutePath();

    private CrashRepository() {
        File file = new File(this.f9007a);
        if (!file.exists()) {
            try {
                file.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        File file2 = new File(this.f9007a, this.f9008b);
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public static CrashRepository m20338a(Context context) {
        if (f9005c == null) {
            f9006d = context;
            f9005c = new CrashRepository();
        }
        return f9005c;
    }

    /* renamed from: a */
    public final void m20337a(String str) {
        try {
            FileWriter fileWriter = new FileWriter(new File(this.f9007a, this.f9008b));
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public final String m20339a() {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(this.f9007a, this.f9008b)));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /* renamed from: b */
    public final void m20336b(String str) {
        String a = m20339a();
        if (!TextUtils.isEmpty(a)) {
            try {
                JSONArray jSONArray = new JSONArray(a);
                if (jSONArray.length() > 0) {
                    JSONArray jSONArray2 = new JSONArray();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        if (!str.equals(jSONArray.get(i).toString())) {
                            jSONArray2.put(jSONArray.get(i).toString());
                        }
                    }
                    if (jSONArray2.length() > 0) {
                        m20337a(jSONArray2.toString());
                    } else {
                        m20337a("");
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: c */
    public final boolean m20335c(String str) {
        String a = m20339a();
        if (TextUtils.isEmpty(a)) {
            return false;
        }
        try {
            JSONArray jSONArray = new JSONArray(a);
            if (jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    if (str.equals(jSONArray.get(i).toString())) {
                        return true;
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
