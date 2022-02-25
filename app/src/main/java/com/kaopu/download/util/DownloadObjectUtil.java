package com.kaopu.download.util;

import com.kaopu.download.kernel.BaseDownloadInfo;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class DownloadObjectUtil {
    private static final String FILTER_CREATOR = "CREATOR";
    private static final String KEY_CLASS_NAME = "class_name";

    public static final Map object2Map(Object obj) {
        HashMap hashMap = new HashMap();
        Class<?> cls = obj.getClass();
        Field[] declaredFields = ReflectionUtils.getDeclaredFields(obj);
        hashMap.put(KEY_CLASS_NAME, cls.getName());
        for (Field field : declaredFields) {
            try {
                String name = field.getName();
                if (!name.equals(FILTER_CREATOR)) {
                    hashMap.put(name, ReflectionUtils.getFieldValue(obj, name));
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        return hashMap;
    }

    public static final Object map2Object(Map map) {
        try {
            Object newInstance = Class.forName((String) map.get(KEY_CLASS_NAME)).newInstance();
            for (Field field : ReflectionUtils.getDeclaredFields(newInstance)) {
                String name = field.getName();
                if (!name.equals(FILTER_CREATOR)) {
                    ReflectionUtils.setFieldValue(newInstance, name, map.get(name));
                }
            }
            return newInstance;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final BaseDownloadInfo map2DownloadInfo(Map map) {
        Object map2Object = map2Object(map);
        if (map2Object == null || !(map2Object instanceof BaseDownloadInfo)) {
            return null;
        }
        return (BaseDownloadInfo) map2Object;
    }
}
