package com.tendcloud.tenddata;

import android.R;
import android.content.Context;
import android.util.SparseArray;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.bj */
/* loaded from: classes2.dex */
abstract class AbstractC2713bj {
    private static final String LOGTAG = "TD.ResReader";
    private final Context mContext;
    private final Map mIdNameToId;
    private final SparseArray mIdToIdName;

    protected abstract String getLocalClassName(Context context);

    protected abstract Class getSystemClass();

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bj$b */
    /* loaded from: classes2.dex */
    static class C2715b extends AbstractC2713bj {
        private final String mResourcePackageName;

        /* JADX INFO: Access modifiers changed from: package-private */
        public C2715b(String str, Context context) {
            super(context);
            this.mResourcePackageName = str;
            initialize();
        }

        @Override // com.tendcloud.tenddata.AbstractC2713bj
        protected Class getSystemClass() {
            return R.id.class;
        }

        @Override // com.tendcloud.tenddata.AbstractC2713bj
        protected String getLocalClassName(Context context) {
            return this.mResourcePackageName + ".R$id";
        }
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bj$a */
    /* loaded from: classes2.dex */
    static class C2714a extends AbstractC2713bj {
        private final String mResourcePackageName;

        protected C2714a(String str, Context context) {
            super(context);
            this.mResourcePackageName = str;
            initialize();
        }

        @Override // com.tendcloud.tenddata.AbstractC2713bj
        protected Class getSystemClass() {
            return R.drawable.class;
        }

        @Override // com.tendcloud.tenddata.AbstractC2713bj
        protected String getLocalClassName(Context context) {
            return this.mResourcePackageName + ".R$drawable";
        }
    }

    private AbstractC2713bj(Context context) {
        this.mContext = context;
        this.mIdNameToId = new HashMap();
        this.mIdToIdName = new SparseArray();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean knownIdName(String str) {
        return this.mIdNameToId.containsKey(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int idFromName(String str) {
        return ((Integer) this.mIdNameToId.get(str)).intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String nameForId(int i) {
        return (String) this.mIdToIdName.get(i);
    }

    private static void readClassIds(Class cls, String str, Map map) {
        Field[] fields;
        try {
            for (Field field : cls.getFields()) {
                if (Modifier.isStatic(field.getModifiers()) && field.getType() == Integer.TYPE) {
                    String name = field.getName();
                    int i = field.getInt(null);
                    if (str != null) {
                        name = str + ":" + name;
                    }
                    map.put(name, Integer.valueOf(i));
                }
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    protected void initialize() {
        this.mIdNameToId.clear();
        this.mIdToIdName.clear();
        readClassIds(getSystemClass(), "android", this.mIdNameToId);
        String localClassName = getLocalClassName(this.mContext);
        try {
            readClassIds(Class.forName(localClassName), null, this.mIdNameToId);
        } catch (ClassNotFoundException unused) {
            C2811dq.eForInternal(LOGTAG, "Class not found from '" + localClassName);
        }
        for (Map.Entry entry : this.mIdNameToId.entrySet()) {
            this.mIdToIdName.put(((Integer) entry.getValue()).intValue(), entry.getKey());
        }
    }
}
