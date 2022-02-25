package com.cyjh.mobileanjian.ipc.utils;

import android.text.TextUtils;
import android.util.JsonReader;
import com.cyjh.mqm.MiscUtilities;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import org.apache.commons.p105io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class UipConfigUtil {
    public static void saveToConfigFile(String str, File file) {
        try {
            FileUtils.writeStringToFile(file, m20672a(new MiscUtilities().LoadUIFile(str, true)));
            file.setReadable(true, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private static String m20672a(String str) {
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(str)) {
            JsonReader jsonReader = new JsonReader(new StringReader(str));
            try {
                jsonReader.beginObject();
                if (jsonReader.nextName().startsWith("界面")) {
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        jsonReader.nextName();
                        m20673a(jsonReader, jSONObject);
                    }
                    jsonReader.endObject();
                }
                jsonReader.endObject();
                jsonReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jSONObject.toString();
    }

    /* renamed from: a */
    private static void m20673a(JsonReader jsonReader, JSONObject jSONObject) {
        try {
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (nextName.equals("水平布局")) {
                    m20673a(jsonReader, jSONObject);
                } else {
                    boolean z = false;
                    int i = 0;
                    String str = null;
                    if (nextName.equals("多选框")) {
                        jsonReader.beginObject();
                        while (jsonReader.hasNext()) {
                            String nextName2 = jsonReader.nextName();
                            if (nextName2.equals("名称")) {
                                str = jsonReader.nextString();
                            } else if (nextName2.equals("选中")) {
                                z = jsonReader.nextBoolean();
                            } else {
                                jsonReader.skipValue();
                            }
                        }
                        jsonReader.endObject();
                        if (str != null) {
                            jSONObject.put(str, z);
                        }
                    } else if (nextName.equals("输入框")) {
                        jsonReader.beginObject();
                        String str2 = null;
                        while (jsonReader.hasNext()) {
                            String nextName3 = jsonReader.nextName();
                            if (nextName3.equals("名称")) {
                                str = jsonReader.nextString();
                            } else if (nextName3.equals("初始文本")) {
                                str2 = jsonReader.nextString();
                            } else {
                                jsonReader.skipValue();
                            }
                        }
                        jsonReader.endObject();
                        if (str != null) {
                            jSONObject.put(str, str2);
                        }
                    } else if (nextName.equals("下拉框")) {
                        jsonReader.beginObject();
                        while (jsonReader.hasNext()) {
                            String nextName4 = jsonReader.nextName();
                            if (nextName4.equals("名称")) {
                                str = jsonReader.nextString();
                            } else if (nextName4.equals("初始选项")) {
                                i = jsonReader.nextInt();
                            } else {
                                jsonReader.skipValue();
                            }
                        }
                        jsonReader.endObject();
                        if (str != null) {
                            jSONObject.put(str, i);
                        }
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }
}
