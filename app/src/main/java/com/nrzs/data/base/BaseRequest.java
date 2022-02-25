package com.nrzs.data.base;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import com.nrzs.data.DataApp;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import p110z1.C4745bt;
import p110z1.CJU;
import p110z1.Config;
import p110z1.SimpleComparison;
import p110z1.aly;

/* loaded from: classes2.dex */
public class BaseRequest implements Parcelable {
    public static final Parcelable.Creator<BaseRequest> CREATOR = new Parcelable.Creator<BaseRequest>() { // from class: com.nrzs.data.base.BaseRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseRequest createFromParcel(Parcel parcel) {
            return new BaseRequest(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseRequest[] newArray(int i) {
            return new BaseRequest[i];
        }
    };

    /* renamed from: a */
    public String f10610a;

    /* renamed from: aa */
    public int f10611aa;

    /* renamed from: ab */
    public String f10612ab;

    /* renamed from: ad */
    public String f10613ad;

    /* renamed from: b */
    public String f10614b;

    /* renamed from: bc */
    public String f10615bc;

    /* renamed from: d */
    public int f10616d;

    /* renamed from: de */
    public long f10617de;
    public int isVa;

    /* renamed from: pg */
    public String f10618pg;

    /* renamed from: pv */
    public String f10619pv;

    /* renamed from: vc */
    public int f10620vc;

    /* renamed from: vs */
    public String f10621vs;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toGetUrl(String str) throws Exception {
        String str2 = this.f10614b;
        if (str2 == null || str2.equals("")) {
            this.f10614b = "123456789";
        }
        String str3 = str + "?" + getProperty();
        Random random = new Random();
        int nextInt = random.nextInt(8);
        CJU ajnVar = new CJU();
        return (str3 + "&Sign=" + ajnVar.m12909a(str3, nextInt) + "&R=" + nextInt) + "&K=" + random.nextInt(8);
    }

    public Map<String, String> toPostUrl(String str, Map<String, String> map) {
        new CJU().m12906a(str, map);
        return map;
    }

    public List<String> getSigin(String str, Map<String, String> map) {
        return new CJU().m12900b(str, map);
    }

    public String toPrames() throws Exception {
        String str = this.f10614b;
        if (str == null || str.equals("")) {
            this.f10614b = "123456789";
        }
        String property = getProperty();
        Random random = new Random();
        int nextInt = random.nextInt(8);
        CJU ajnVar = new CJU();
        return (property + "&Sign=" + ajnVar.m12909a(property, nextInt) + "&R=" + nextInt) + "&K=" + random.nextInt(8);
    }

    public String tojsPrames() throws Exception {
        String str = this.f10614b;
        if (str == null || str.equals("")) {
            this.f10614b = "123456789";
        }
        return getProperty();
    }

    public Map<String, String> getMapParams() throws Exception {
        new HashMap();
        String str = this.f10614b;
        if (str == null || str.equals("")) {
            this.f10614b = "123456789";
        }
        return getMapProperty();
    }

    private Map<String, String> getMapProperty() throws Exception {
        HashMap hashMap = new HashMap();
        List<ClassInfo> recursion = getRecursion(getClass(), new ArrayList());
        if (recursion.size() > 0) {
            Collections.sort(recursion, new SortComparator());
        }
        for (ClassInfo classInfo : recursion) {
            hashMap.put(classInfo.key, URLEncoder.encode(String.valueOf(classInfo.value), "UTF-8"));
        }
        return hashMap;
    }

    public Map<String, String> getNoencodeMapProperty() throws Exception {
        HashMap hashMap = new HashMap();
        List<ClassInfo> recursion = getRecursion(getClass(), new ArrayList());
        if (recursion.size() > 0) {
            Collections.sort(recursion, new SortComparator());
        }
        for (ClassInfo classInfo : recursion) {
            hashMap.put(classInfo.key, String.valueOf(classInfo.value));
        }
        return hashMap;
    }

    protected String getProperty() throws Exception {
        Class<?> cls = getClass();
        StringBuffer stringBuffer = new StringBuffer();
        List<ClassInfo> recursion = getRecursion(cls, new ArrayList());
        if (recursion.size() > 0) {
            Collections.sort(recursion, new SortComparator());
        }
        for (ClassInfo classInfo : recursion) {
            stringBuffer.append(C4745bt.f20071b);
            stringBuffer.append(classInfo.key + SimpleComparison.f23609c + URLEncoder.encode(String.valueOf(classInfo.value), "UTF-8"));
        }
        stringBuffer.deleteCharAt(0);
        return stringBuffer.toString();
    }

    private List<ClassInfo> getRecursion(Class cls, List<ClassInfo> list) throws Exception {
        Field[] declaredFields;
        for (Field field : cls.getDeclaredFields()) {
            field.setAccessible(true);
            if (!Modifier.isStatic(field.getModifiers())) {
                ClassInfo classInfo = new ClassInfo();
                classInfo.key = field.getName().toLowerCase();
                classInfo.value = String.valueOf(field.get(this));
                list.add(classInfo);
            }
        }
        return (cls.getSuperclass() == null || cls.getSuperclass() == Object.class) ? list : getRecursion(cls.getSuperclass(), list);
    }

    /* loaded from: classes2.dex */
    public class SortComparator implements Comparator {
        public SortComparator() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ((ClassInfo) obj).key.compareTo(((ClassInfo) obj2).key);
        }
    }

    /* loaded from: classes2.dex */
    public class ClassInfo {
        public String key;
        public String value;

        public ClassInfo() {
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f10610a);
        parcel.writeString(this.f10614b);
        parcel.writeString(this.f10612ab);
        parcel.writeString(this.f10615bc);
        parcel.writeInt(this.f10616d);
        parcel.writeLong(this.f10617de);
        parcel.writeString(this.f10621vs);
        parcel.writeInt(this.f10620vc);
        parcel.writeString(this.f10618pg);
        parcel.writeString(this.f10619pv);
        parcel.writeString(this.f10613ad);
        parcel.writeInt(this.f10611aa);
        parcel.writeInt(this.isVa);
    }

    public BaseRequest() {
        this.f10610a = "Android";
        this.f10614b = Config.m12527a();
        this.f10612ab = Config.m12522d();
        this.f10615bc = DataApp.m18939d().m18944b();
        this.f10616d = 2;
        this.f10617de = System.currentTimeMillis();
        this.f10621vs = Config.m12524b();
        this.f10620vc = Config.m12523c();
        this.f10618pg = Config.m12521e();
        this.f10619pv = Config.m12520f();
        this.f10613ad = "default";
        this.f10611aa = Build.VERSION.SDK_INT;
        this.isVa = aly.m12531a(1) - 1;
    }

    protected BaseRequest(Parcel parcel) {
        this.f10610a = "Android";
        this.f10614b = Config.m12527a();
        this.f10612ab = Config.m12522d();
        this.f10615bc = DataApp.m18939d().m18944b();
        this.f10616d = 2;
        this.f10617de = System.currentTimeMillis();
        this.f10621vs = Config.m12524b();
        this.f10620vc = Config.m12523c();
        this.f10618pg = Config.m12521e();
        this.f10619pv = Config.m12520f();
        this.f10613ad = "default";
        this.f10611aa = Build.VERSION.SDK_INT;
        this.isVa = aly.m12531a(1) - 1;
        this.f10610a = parcel.readString();
        this.f10614b = parcel.readString();
        this.f10612ab = parcel.readString();
        this.f10615bc = parcel.readString();
        this.f10616d = parcel.readInt();
        this.f10617de = parcel.readLong();
        this.f10621vs = parcel.readString();
        this.f10620vc = parcel.readInt();
        this.f10618pg = parcel.readString();
        this.f10619pv = parcel.readString();
        this.f10613ad = parcel.readString();
        this.f10611aa = parcel.readInt();
        this.isVa = parcel.readInt();
    }
}
