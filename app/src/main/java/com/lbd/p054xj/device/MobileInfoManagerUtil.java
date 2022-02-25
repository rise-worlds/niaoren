package com.lbd.p054xj.device;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.p003v4.app.ActivityCompat;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.common.utils.log.LogUtils;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.lbd.p054xj.p056ui.activity.XJBaseANativityctivity;
import com.tencent.smtt.sdk.WebView;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import p110z1.C4963cj;

/* renamed from: com.lbd.xj.device.MobileInfoManagerUtil */
/* loaded from: classes.dex */
public class MobileInfoManagerUtil {
    public static final String TAG = "MobileInfoManagerUtil";
    private boolean isPermission;
    public XJBaseANativityctivity mContext;
    private MyPhoneState mPhoneState1;
    private MyPhoneState mPhoneState2;
    private MyPhoneState mPhoneState3;
    public String[] mSingle = {"null", "null", "null", "null", "null", "null", "null", "null", "null", "null", "null", "null", "null", "null"};
    public int mcid = -1;
    public int mlac = -1;
    public TelephonyManager telephonyManager;

    /* renamed from: com.lbd.xj.device.MobileInfoManagerUtil$MyPhoneState */
    /* loaded from: classes.dex */
    private class MyPhoneState extends PhoneStateListener {
        private int PhoneType = -1;
        private int TypeRun;

        public MyPhoneState(int i, int i2) {
            this.TypeRun = i;
            if (this.TypeRun > 1) {
                FieldPhoneState(i2);
            }
        }

        private void FieldPhoneState(int i) {
            if (MobileInfoManagerUtil.this.isGetInfo()) {
                try {
                    Field declaredField = getClass().getSuperclass().getDeclaredField("mSubId");
                    declaredField.setAccessible(true);
                    declaredField.set(this, Integer.valueOf(i));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e2) {
                    e2.printStackTrace();
                }
                try {
                    this.PhoneType = ((Integer) Class.forName(MobileInfoManagerUtil.this.telephonyManager.getClass().getName()).getMethod("getCurrentPhoneType", Integer.TYPE).invoke(MobileInfoManagerUtil.this.telephonyManager, Integer.valueOf(i))).intValue();
                    LogUtils.m22034i(MobileInfoManagerUtil.TAG, "PhoneType = " + this.PhoneType);
                } catch (ClassNotFoundException e3) {
                    e3.printStackTrace();
                } catch (IllegalAccessException e4) {
                    e4.printStackTrace();
                } catch (NoSuchMethodException e5) {
                    e5.printStackTrace();
                } catch (InvocationTargetException e6) {
                    e6.printStackTrace();
                }
            }
        }

        @Override // android.telephony.PhoneStateListener
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            if (MobileInfoManagerUtil.this.isGetInfo()) {
                MobileInfoManagerUtil.this.mContext.f9609d.removeMessages(3);
                MobileInfoManagerUtil.this.mContext.f9609d.sendEmptyMessageDelayed(3, 9000L);
                if (this.TypeRun == 1 && signalStrength != null) {
                    try {
                        String[] split = signalStrength.toString().split(ExpandableTextView.f6958c);
                        if (split.length >= 13) {
                            for (int i = 0; i < 13; i++) {
                                MobileInfoManagerUtil.this.mSingle[i] = split[i];
                            }
                        }
                    } catch (Exception e) {
                        LogUtils.m22037e("获取手机信号信息失败");
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override // android.telephony.PhoneStateListener
        public void onCellLocationChanged(CellLocation cellLocation) {
            super.onCellLocationChanged(cellLocation);
            if (!MobileInfoManagerUtil.this.isGetInfo()) {
                LogUtils.m22034i(MobileInfoManagerUtil.TAG, "Ref_GetCellInfo getSystemService(Context.TELEPHONY_SERVICE) is  empty");
            } else if (this.TypeRun > 1) {
                try {
                    if (this.PhoneType == 2) {
                        CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                        if (cdmaCellLocation != null) {
                            MobileInfoManagerUtil.this.mcid = cdmaCellLocation.getBaseStationId();
                            MobileInfoManagerUtil.this.mlac = cdmaCellLocation.getNetworkId();
                            int systemId = cdmaCellLocation.getSystemId();
                            LogUtils.m22034i(MobileInfoManagerUtil.TAG, "Ref_GetCellInfo 电信CDMA :  基站识别标号BID=" + MobileInfoManagerUtil.this.mcid + " 网络编号NID=" + MobileInfoManagerUtil.this.mlac + " SID=" + systemId);
                        } else {
                            LogUtils.m22034i(MobileInfoManagerUtil.TAG, "Ref_GetCellInfo CdmaCellLocation telephonyManager.getCellLocation() = null");
                        }
                    } else {
                        GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                        if (gsmCellLocation != null) {
                            MobileInfoManagerUtil.this.mcid = gsmCellLocation.getCid();
                            MobileInfoManagerUtil.this.mlac = gsmCellLocation.getLac();
                            LogUtils.m22034i(MobileInfoManagerUtil.TAG, "Ref_GetCellInfo gsm移动和联通 :  cid=" + MobileInfoManagerUtil.this.mcid + " lac=" + MobileInfoManagerUtil.this.mlac);
                        } else {
                            LogUtils.m22034i(MobileInfoManagerUtil.TAG, "Ref_GetCellInfo GsmCellLocation telephonyManager.getCellLocation() = null");
                        }
                    }
                } catch (Exception e) {
                    LogUtils.m22034i(MobileInfoManagerUtil.TAG, "Ref_GetCellInfo error " + e.toString());
                }
            }
        }
    }

    /* renamed from: com.lbd.xj.device.MobileInfoManagerUtil$SingletonClassInstance */
    /* loaded from: classes.dex */
    private static class SingletonClassInstance {
        public static final MobileInfoManagerUtil manage = new MobileInfoManagerUtil();

        private SingletonClassInstance() {
        }
    }

    public static MobileInfoManagerUtil getInstance() {
        return SingletonClassInstance.manage;
    }

    public void initializeMobileInfoManager(Object obj, XJBaseANativityctivity xJBaseANativityctivity) {
        if (obj == null) {
            LogUtils.m22034i(TAG, "xunidashi getSystemService TelephonyManager  = null");
        } else if (Build.VERSION.SDK_INT > 28) {
            LogUtils.m22034i(TAG, "安卓Q无READ_PHONE_STATE权限");
        } else {
            if (ActivityCompat.checkSelfPermission(xJBaseANativityctivity, "android.permission.READ_PHONE_STATE") == 0 && ActivityCompat.checkSelfPermission(xJBaseANativityctivity, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                this.isPermission = true;
            }
            this.mContext = xJBaseANativityctivity;
            if (obj instanceof TelephonyManager) {
                this.telephonyManager = (TelephonyManager) obj;
            }
        }
    }

    private void showTelePhoneDial(String str) {
        LogUtils.m22036e("yuelogphone", "number 2= phoneNumber " + str);
        if (Build.MANUFACTURER.equalsIgnoreCase("xiaomi")) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(WebView.SCHEME_TEL + str));
        intent.setFlags(268435456);
    }

    public String getSingle(int i) {
        String str;
        synchronized (this) {
            str = this.mSingle[i];
        }
        return str;
    }

    public boolean isGetInfo() {
        XJBaseANativityctivity xJBaseANativityctivity;
        return this.telephonyManager != null && (xJBaseANativityctivity = this.mContext) != null && this.isPermission && !xJBaseANativityctivity.isFinishing();
    }

    public void startPhoneStateListener() {
        if (isGetInfo()) {
            try {
                if (this.mPhoneState1 == null) {
                    this.mPhoneState1 = new MyPhoneState(1, 0);
                }
                this.telephonyManager.listen(this.mPhoneState1, 256);
                int subidBySlotId = getSubidBySlotId(this.mContext, 0);
                int subidBySlotId2 = getSubidBySlotId(this.mContext, 1);
                LogUtils.m22034i("yuelog", "sim1Subid = " + subidBySlotId + ",sim2Subid = " + subidBySlotId2);
                if (this.mPhoneState2 == null) {
                    this.mPhoneState2 = new MyPhoneState(2, subidBySlotId);
                }
                if (this.mPhoneState3 == null) {
                    this.mPhoneState3 = new MyPhoneState(2, subidBySlotId2);
                }
                this.telephonyManager.listen(this.mPhoneState2, 16);
                this.telephonyManager.listen(this.mPhoneState3, 16);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private int getSubidBySlotId(Context context, int i) {
        if (Build.VERSION.SDK_INT < 22) {
            return -1;
        }
        try {
            SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService("telephony_subscription_service");
            Object invoke = Class.forName(subscriptionManager.getClass().getName()).getMethod("getSubId", Integer.TYPE).invoke(subscriptionManager, Integer.valueOf(i));
            if (invoke == null) {
                return -1;
            }
            LogUtils.m22038d(TAG, "slotId:" + i + C4963cj.f20745b + ((int[]) invoke)[0]);
            return ((int[]) invoke)[0];
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String Ref_GetPhoneNumber(int i, int i2) {
        if (!isGetInfo()) {
            return "null";
        }
        try {
            String line1Number = this.telephonyManager.getLine1Number();
            if (TextUtils.isEmpty(line1Number)) {
                return "null";
            }
            LogUtils.m22034i(TAG, "yuelog0608  Ref_GetPhoneNumber=" + line1Number);
            return line1Number;
        } catch (Exception e) {
            LogUtils.m22034i(TAG, "Ref_GetPhoneNumber " + e.toString());
            return "null";
        }
    }

    public String Ref_GetNetworkOperatorNumber(int i, int i2) {
        if (!isGetInfo()) {
            return "null";
        }
        try {
            String networkOperator = this.telephonyManager.getNetworkOperator();
            if (TextUtils.isEmpty(networkOperator)) {
                return "null";
            }
            LogUtils.m22034i(TAG, "yuelog0608  providersNumber=" + networkOperator);
            return networkOperator;
        } catch (Exception e) {
            LogUtils.m22034i(TAG, "GetProvidersName " + e.toString());
            return "null";
        }
    }

    public String Ref_GetIccid(int i, int i2) {
        if (!isGetInfo()) {
            return "null";
        }
        try {
            String simSerialNumber = this.telephonyManager.getSimSerialNumber();
            if (TextUtils.isEmpty(simSerialNumber)) {
                return "null";
            }
            LogUtils.m22034i(TAG, "yuelog0608  iccid=" + simSerialNumber);
            return simSerialNumber;
        } catch (Exception e) {
            LogUtils.m22034i(TAG, "Ref_GetIccid " + e.toString());
            return "null";
        }
    }

    public String Ref_Getimsi(int i, int i2) {
        if (!isGetInfo()) {
            return "null";
        }
        try {
            String subscriberId = this.telephonyManager.getSubscriberId();
            if (TextUtils.isEmpty(subscriberId)) {
                return "null";
            }
            LogUtils.m22034i(TAG, "yuelog0608  imsi=" + subscriberId);
            return subscriberId;
        } catch (Exception e) {
            LogUtils.m22034i(TAG, "Ref_Getimsi " + e.toString());
            return "null";
        }
    }

    public String Ref_Getimei(int i, int i2) {
        if (!isGetInfo()) {
            return "null";
        }
        try {
            String deviceId = this.telephonyManager.getDeviceId();
            if (TextUtils.isEmpty(deviceId)) {
                return "null";
            }
            LogUtils.m22034i(TAG, "yuelog0608  imei=" + deviceId);
            return deviceId;
        } catch (Exception e) {
            LogUtils.m22034i(TAG, "Ref_Getimei " + e.toString());
            return "null";
        }
    }

    public String Ref_GetNetworkType(int i, int i2) {
        if (!isGetInfo()) {
            return "null";
        }
        try {
            int networkType = this.telephonyManager.getNetworkType();
            String valueOf = String.valueOf(networkType);
            LogUtils.m22034i(TAG, "yuelog0608 1607 getNetworkType = " + networkType);
            return valueOf;
        } catch (Exception e) {
            LogUtils.m22034i(TAG, "Ref_GetNetworkType " + e.toString());
            return "null";
        }
    }

    public String Ref_GetCellInfo(int i, int i2) {
        if (isGetInfo()) {
            try {
                if (this.telephonyManager.getPhoneType() == 2) {
                    CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) this.telephonyManager.getCellLocation();
                    if (cdmaCellLocation != null) {
                        this.mcid = cdmaCellLocation.getBaseStationId();
                        this.mlac = cdmaCellLocation.getNetworkId();
                        LogUtils.m22034i(TAG, "Ref_GetCellInfo 电信CDMA 1:  基站识别标号BID=" + this.mcid + " 网络编号NID=" + this.mlac + " SID=");
                    } else {
                        LogUtils.m22034i(TAG, "Ref_GetCellInfo CdmaCellLocation telephonyManager.getCellLocation() = null");
                    }
                } else {
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) this.telephonyManager.getCellLocation();
                    if (gsmCellLocation != null) {
                        this.mcid = gsmCellLocation.getCid();
                        this.mlac = gsmCellLocation.getLac();
                        LogUtils.m22034i(TAG, "Ref_GetCellInfo gsm移动和联通 :  cid=" + this.mcid + " lac=" + this.mlac);
                    } else {
                        LogUtils.m22034i(TAG, "Ref_GetCellInfo GsmCellLocation telephonyManager.getCellLocation() = null");
                    }
                }
            } catch (Exception e) {
                LogUtils.m22034i(TAG, "Ref_GetCellInfo error " + e.toString());
            }
            return "getlaccid";
        }
        LogUtils.m22034i(TAG, "Ref_GetCellInfo getSystemService(Context.TELEPHONY_SERVICE) is  empty");
        return "getlaccid";
    }

    public String Ref_GetLac(int i, int i2) {
        if (this.mlac == -1) {
            return "null";
        }
        return "" + this.mlac;
    }

    public String Ref_GetCid(int i, int i2) {
        if (this.mcid == -1) {
            return "null";
        }
        return "" + this.mcid;
    }

    public String Ref_MyCallPhone(String str) {
        LogUtils.m22036e("yuelogphone", "number = " + str);
        XJBaseANativityctivity xJBaseANativityctivity = this.mContext;
        if (xJBaseANativityctivity == null || xJBaseANativityctivity.isFinishing()) {
            LogUtils.m22036e("yuelogphone", "Ref_MyCallPhone isFinishing");
            return "null";
        }
        showTelePhoneDial(str);
        return "null";
    }
}
