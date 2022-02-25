package com.cyjh.ddy.net.bean.base;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.MetaDataUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.cyjh.ddy.base.p033a.NoProGuard;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.base.utils.DeviceImeiUtil;
import com.cyjh.ddy.base.utils.JsonUtil;
import com.cyjh.ddy.base.utils.SdkKeyUtil;
import com.cyjh.ddy.base.utils.SdkUtils;
import com.cyjh.ddy.base.utils.Utils;
import com.cyjh.ddy.p032a.C1118a;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import p110z1.C4745bt;
import p110z1.SimpleComparison;

/* loaded from: classes.dex */
public class BaseRequestInfo implements Parcelable, NoProGuard {
    public static final Parcelable.Creator<BaseRequestInfo> CREATOR = new Parcelable.Creator<BaseRequestInfo>() { // from class: com.cyjh.ddy.net.bean.base.BaseRequestInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseRequestInfo createFromParcel(Parcel parcel) {
            return new BaseRequestInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseRequestInfo[] newArray(int i) {
            return new BaseRequestInfo[i];
        }
    };
    public String AppId;
    public String AppPackageName;
    public String AppSign;
    public String AppVersion;
    public int AppVersionCode;
    public String ChannelName;
    public String IMEI;
    public String UCID;
    public String UserToken;
    public int signType;
    public long timestamp;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private String getUniqueID() {
        String b = SPUtils.m23341a().m23320b("UniqueID", "");
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        String uuid = UUID.randomUUID().toString();
        SPUtils.m23341a().m23332a("UniqueID", uuid);
        return uuid;
    }

    public String toPrames() throws Exception {
        String str = this.IMEI;
        if (str == null || str.equals("")) {
            this.IMEI = "123456789";
        }
        return getProperty();
    }

    public Map<String, String> getMapParams() throws Exception {
        new HashMap();
        String str = this.IMEI;
        if (str == null || str.equals("")) {
            this.IMEI = "123456789";
        }
        return getMapProperty();
    }

    protected Map<String, String> getMapProperty() throws Exception {
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

    public String toJson(String str) throws Exception {
        return TextUtils.isEmpty(str) ? "" : C1118a.m21981a(str);
    }

    public String getJson() {
        String a = JsonUtil.m21804a(this);
        CLog.m21884d("BaseRequestValueInfo", "getJson: = " + a);
        return a;
    }

    /* loaded from: classes.dex */
    public class SortComparator implements Comparator {
        public SortComparator() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ((ClassInfo) obj).key.compareTo(((ClassInfo) obj2).key);
        }
    }

    /* loaded from: classes.dex */
    public class ClassInfo {
        public String key;
        public String value;

        public ClassInfo() {
        }
    }

    public BaseRequestInfo() {
        this.IMEI = DeviceImeiUtil.m21867a();
        this.AppVersionCode = AppUtils.m22912m();
        this.AppVersion = AppUtils.m22914l();
        this.ChannelName = "default";
        this.AppId = "null";
        this.signType = 1;
        this.AppSign = Utils.m21722b();
        this.AppPackageName = AppUtils.m22920i();
        this.UCID = "";
        if (!SdkUtils.m21761a()) {
            this.ChannelName = MetaDataUtils.m23661a("UMENG_CHANNEL");
            this.AppId = MetaDataUtils.m23661a("DDYUN_APPKEY");
            this.signType = Integer.valueOf(MetaDataUtils.m23661a("DDY_SIGN_TYPE")).intValue();
        } else {
            this.ChannelName = "ddysdk";
            this.AppId = SdkKeyUtil.getInstance().getSdkKey();
            this.signType = 1;
        }
        this.timestamp = TimeUtils.m23128a();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.IMEI);
        parcel.writeInt(this.AppVersionCode);
        parcel.writeString(this.AppVersion);
        parcel.writeString(this.ChannelName);
        parcel.writeString(this.AppId);
        parcel.writeInt(this.signType);
        parcel.writeString(this.AppSign);
        parcel.writeString(this.AppPackageName);
        parcel.writeString(this.UCID);
    }

    protected BaseRequestInfo(Parcel parcel) {
        this.IMEI = DeviceImeiUtil.m21867a();
        this.AppVersionCode = AppUtils.m22912m();
        this.AppVersion = AppUtils.m22914l();
        this.ChannelName = "default";
        this.AppId = "null";
        this.signType = 1;
        this.AppSign = Utils.m21722b();
        this.AppPackageName = AppUtils.m22920i();
        this.UCID = "";
        this.IMEI = parcel.readString();
        this.AppVersionCode = parcel.readInt();
        this.AppVersion = parcel.readString();
        this.ChannelName = parcel.readString();
        this.AppId = parcel.readString();
        this.signType = parcel.readInt();
        this.AppSign = parcel.readString();
        this.AppPackageName = parcel.readString();
        this.UCID = parcel.readString();
    }
}
