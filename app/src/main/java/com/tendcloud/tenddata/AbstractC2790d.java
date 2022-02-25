package com.tendcloud.tenddata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.d */
/* loaded from: classes2.dex */
public abstract class AbstractC2790d {
    public static final String MF_JSON = "UNIFIED_SDK_JSON";
    private static final String MF_MP = "MP";
    private static final String MF_PB = "PB";
    public static final String MF_STRING = "STRING";
    private int indexNum;
    private String nameString;
    private static volatile List FeaturesList = new ArrayList();
    public static final AbstractC2790d APP = new C2831e("APP", 0);
    public static final AbstractC2790d ENV = new C2864f("ENV", 2);
    public static final AbstractC2790d PUSH = new C2899g("PUSH", 4);
    private static final AbstractC2790d APP_SQL = new C2931h("APP_SQL", 7);
    private static final AbstractC2790d[] service = {APP, ENV, PUSH, APP_SQL};

    public abstract String getCert();

    public int getFileLimitType() {
        return 1;
    }

    public abstract String getHost();

    public abstract String getIP();

    public abstract String getMessageFormat();

    public String getRootFolder() {
        return C2663aa.f13453W;
    }

    public abstract String getUrl();

    public boolean needToSendData() {
        return true;
    }

    public void setUrl(String str) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractC2790d(String str, int i) {
        this.nameString = str;
        this.indexNum = i;
        addFeatures2List(str);
    }

    public static List getFeaturesNameList() {
        return FeaturesList;
    }

    protected AbstractC2790d(String str, int i, boolean z) {
        this.nameString = str;
        this.indexNum = i;
    }

    private void addFeatures2List(String str) {
        try {
            if (!C2855es.m15791b(str) && !FeaturesList.contains(str)) {
                FeaturesList.add(str);
            }
        } catch (Throwable unused) {
        }
    }

    public static AbstractC2790d valueOf(String str) {
        if (str.equals(APP.name())) {
            return APP;
        }
        if (str.equals(ENV.name())) {
            return ENV;
        }
        if (str.equals(PUSH.name())) {
            return PUSH;
        }
        if (str.equals(APP_SQL.name())) {
            return APP_SQL;
        }
        return null;
    }

    public static AbstractC2790d[] values() {
        AbstractC2790d[] dVarArr = service;
        return (AbstractC2790d[]) Arrays.copyOf(dVarArr, dVarArr.length);
    }

    public String name() {
        return this.nameString;
    }

    public int index() {
        return this.indexNum;
    }

    public String getDataFolder() {
        return "td_database" + index() + C2985ir.f14270c;
    }

    public static ArrayList getFeaturesList() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < FeaturesList.size(); i++) {
            try {
                if (valueOf((String) FeaturesList.get(i)) != null) {
                    arrayList.add(valueOf((String) FeaturesList.get(i)));
                }
            } catch (Throwable unused) {
            }
        }
        return arrayList;
    }
}
