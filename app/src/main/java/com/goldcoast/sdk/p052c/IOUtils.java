package com.goldcoast.sdk.p052c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.goldcoast.sdk.c.f */
/* loaded from: classes.dex */
public final class IOUtils {
    /* renamed from: a */
    public static String m20323a(InputStream inputStream, Map map) {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        boolean z = map != null && map.size() > 0;
        while (true) {
            try {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        if (map != null && readLine.contains(":")) {
                            String[] split = readLine.split(":");
                            if (z) {
                                m20322a(map, split[0], split[1]);
                            } else if (split[0].trim().equals("processor")) {
                                if (split.length > 1) {
                                    map.put("processorcnt", split[1].trim());
                                } else {
                                    map.put("processorcnt", split[0].trim());
                                }
                            } else if (split.length > 1) {
                                map.put(split[0].trim(), split[1].trim());
                            } else {
                                map.put(split[0].trim(), split[0].trim());
                            }
                        }
                        sb.append(readLine);
                        sb.append("\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    break;
                } catch (IOException unused) {
                    return sb.toString();
                }
            } catch (Throwable th) {
                try {
                    bufferedReader.close();
                } catch (IOException unused2) {
                }
                throw th;
            }
        }
        bufferedReader.close();
    }

    /* renamed from: a */
    private static void m20322a(Map<String, String> map, String str, String str2) {
        String trim = str.replace("[", "").replace("]", "").trim();
        String trim2 = str2.replace("[", "").replace("]", "").trim();
        Iterator<String> it = map.keySet().iterator();
        if (it != null && it.hasNext()) {
            while (it.hasNext()) {
                String next = it.next();
                if (next.equals(trim)) {
                    map.put(next, trim2);
                    return;
                }
            }
        }
    }
}
