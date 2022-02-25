package com.cyjh.mobileanjian.ipc.utils;

import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/* renamed from: com.cyjh.mobileanjian.ipc.utils.b */
/* loaded from: classes.dex */
public final class BuildProperties {

    /* renamed from: a */
    private final Properties f8678a = new Properties();

    /* JADX INFO: Access modifiers changed from: package-private */
    public BuildProperties() {
        try {
            this.f8678a.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
        } catch (IOException unused) {
        }
    }

    /* renamed from: a */
    public final boolean m20668a(Object obj) {
        return this.f8678a.containsKey(obj);
    }

    /* renamed from: b */
    private boolean m20664b(Object obj) {
        return this.f8678a.containsValue(obj);
    }

    /* renamed from: a */
    private Set<Map.Entry<Object, Object>> m20669a() {
        return this.f8678a.entrySet();
    }

    /* renamed from: a */
    public final String m20667a(String str) {
        return this.f8678a.getProperty(str);
    }

    /* renamed from: a */
    public final String m20666a(String str, String str2) {
        return this.f8678a.getProperty(str, str2);
    }

    /* renamed from: b */
    private boolean m20665b() {
        return this.f8678a.isEmpty();
    }

    /* renamed from: c */
    private Enumeration<Object> m20663c() {
        return this.f8678a.keys();
    }

    /* renamed from: d */
    private Set<Object> m20662d() {
        return this.f8678a.keySet();
    }

    /* renamed from: e */
    private int m20661e() {
        return this.f8678a.size();
    }

    /* renamed from: f */
    private Collection<Object> m20660f() {
        return this.f8678a.values();
    }

    /* renamed from: g */
    private static BuildProperties m20659g() {
        return new BuildProperties();
    }
}
