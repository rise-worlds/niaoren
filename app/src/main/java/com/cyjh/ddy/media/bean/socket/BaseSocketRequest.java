package com.cyjh.ddy.media.bean.socket;

import com.blankj.utilcode.util.EncryptUtils;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.base.utils.JsonUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import p110z1.JsonElement;
import p110z1.JsonObject;
import p110z1.JsonParser;

/* loaded from: classes.dex */
public class BaseSocketRequest<T> {
    public T data;
    public String sign;
    public String token;
    public int type;
    public int from = 1;
    public long time = System.currentTimeMillis();

    public String getSign() {
        String valueOf = String.valueOf(this.time);
        return EncryptUtils.m22365b(valueOf.substring(0, 4) + getJsonSignStr(JsonUtil.m21804a(this.data)) + valueOf.substring(valueOf.length() - 4));
    }

    public String getJsonSignStr(String str) {
        if (str == null) {
            CLog.m21882i("httpserver", "getJsonSignStr: [json]=null ");
            return "";
        }
        JsonObject t = new JsonParser().m1463a(str).m1485t();
        HashSet hashSet = new HashSet();
        for (Map.Entry<String, JsonElement> entry : t.m1472b()) {
            hashSet.add(entry.getKey());
        }
        ArrayList arrayList = new ArrayList(hashSet);
        Collections.sort(arrayList, new Comparator<String>() { // from class: com.cyjh.ddy.media.bean.socket.BaseSocketRequest.1
            public int compare(String str2, String str3) {
                return str2.compareTo(str3);
            }
        });
        StringBuilder sb = new StringBuilder();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            if (!t.m1470c(str2).toString().contains("\",")) {
                sb.append(t.m1470c(str2));
            }
        }
        return sb.toString();
    }
}
