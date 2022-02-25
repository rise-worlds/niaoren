package com.lody.virtual.client.hook.proxies.telephony;

import android.os.Bundle;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.NeighboringCellInfo;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.lody.virtual.client.hook.annotations.SkipInject;
import com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy;
import com.lody.virtual.client.hook.base.ReplaceLastPkgMethodProxy;
import com.lody.virtual.client.ipc.VirtualLocationManager;
import com.lody.virtual.remote.VDeviceConfig;
import com.lody.virtual.remote.vloc.VCell;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
class MethodProxies {

    /* loaded from: classes.dex */
    static class GetImeiForSlot extends GetDeviceId {
        @Override // com.lody.virtual.client.hook.base.StaticMethodProxy, com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getImeiForSlot";
        }
    }

    /* loaded from: classes.dex */
    static class GetMeidForSlot extends GetDeviceId {
        @Override // com.lody.virtual.client.hook.base.StaticMethodProxy, com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getMeidForSlot";
        }
    }

    MethodProxies() {
    }

    /* loaded from: classes.dex */
    static class GetDeviceId extends ReplaceLastPkgMethodProxy {
        public GetDeviceId() {
            super("getDeviceId");
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            VDeviceConfig deviceConfig = getDeviceConfig();
            if (deviceConfig.enable) {
                String str = deviceConfig.deviceId;
                if (!TextUtils.isEmpty(str)) {
                    return str;
                }
            }
            return super.call(obj, method, objArr);
        }
    }

    @SkipInject
    /* loaded from: classes.dex */
    static class GetCellLocation extends ReplaceCallingPkgMethodProxy {
        public GetCellLocation() {
            super("getCellLocation");
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (!isFakeLocationEnable()) {
                return super.call(obj, method, objArr);
            }
            VCell cell = VirtualLocationManager.get().getCell(getAppUserId(), getAppPkg());
            if (cell != null) {
                return MethodProxies.getCellLocationInternal(cell);
            }
            return null;
        }
    }

    /* loaded from: classes.dex */
    static class GetAllCellInfoUsingSubId extends ReplaceCallingPkgMethodProxy {
        public GetAllCellInfoUsingSubId() {
            super("getAllCellInfoUsingSubId");
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (isFakeLocationEnable()) {
                return null;
            }
            return super.call(obj, method, objArr);
        }
    }

    @SkipInject
    /* loaded from: classes.dex */
    static class GetAllCellInfo extends ReplaceCallingPkgMethodProxy {
        public GetAllCellInfo() {
            super("getAllCellInfo");
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (!isFakeLocationEnable()) {
                return super.call(obj, method, objArr);
            }
            List<VCell> allCell = VirtualLocationManager.get().getAllCell(getAppUserId(), getAppPkg());
            if (allCell == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (VCell vCell : allCell) {
                arrayList.add(MethodProxies.createCellInfo(vCell));
            }
            return arrayList;
        }
    }

    @SkipInject
    /* loaded from: classes.dex */
    static class GetNeighboringCellInfo extends ReplaceCallingPkgMethodProxy {
        public GetNeighboringCellInfo() {
            super("getNeighboringCellInfo");
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (!isFakeLocationEnable()) {
                return super.call(obj, method, objArr);
            }
            List<VCell> neighboringCell = VirtualLocationManager.get().getNeighboringCell(getAppUserId(), getAppPkg());
            if (neighboringCell == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (VCell vCell : neighboringCell) {
                NeighboringCellInfo neighboringCellInfo = new NeighboringCellInfo();
                p110z1.NeighboringCellInfo.mLac.set(neighboringCellInfo, vCell.lac);
                p110z1.NeighboringCellInfo.mCid.set(neighboringCellInfo, vCell.cid);
                p110z1.NeighboringCellInfo.mRssi.set(neighboringCellInfo, 6);
                arrayList.add(neighboringCellInfo);
            }
            return arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bundle getCellLocationInternal(VCell vCell) {
        if (vCell == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        if (vCell.type == 2) {
            try {
                CdmaCellLocation cdmaCellLocation = new CdmaCellLocation();
                cdmaCellLocation.setCellLocationData(vCell.baseStationId, Integer.MAX_VALUE, Integer.MAX_VALUE, vCell.systemId, vCell.networkId);
                cdmaCellLocation.fillInNotifierBundle(bundle);
            } catch (Throwable unused) {
                bundle.putInt("baseStationId", vCell.baseStationId);
                bundle.putInt("baseStationLatitude", Integer.MAX_VALUE);
                bundle.putInt("baseStationLongitude", Integer.MAX_VALUE);
                bundle.putInt("systemId", vCell.systemId);
                bundle.putInt("networkId", vCell.networkId);
            }
        } else {
            try {
                GsmCellLocation gsmCellLocation = new GsmCellLocation();
                gsmCellLocation.setLacAndCid(vCell.lac, vCell.cid);
                gsmCellLocation.fillInNotifierBundle(bundle);
            } catch (Throwable unused2) {
                bundle.putInt("lac", vCell.lac);
                bundle.putInt("cid", vCell.cid);
                bundle.putInt("psc", vCell.psc);
            }
        }
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CellInfo createCellInfo(VCell vCell) {
        if (vCell.type == 2) {
            CellInfoCdma newInstance = p110z1.CellInfoCdma.ctor.newInstance();
            CellIdentityCdma cellIdentityCdma = p110z1.CellInfoCdma.mCellIdentityCdma.get(newInstance);
            CellSignalStrengthCdma cellSignalStrengthCdma = p110z1.CellInfoCdma.mCellSignalStrengthCdma.get(newInstance);
            p110z1.CellIdentityCdma.mNetworkId.set(cellIdentityCdma, vCell.networkId);
            p110z1.CellIdentityCdma.mSystemId.set(cellIdentityCdma, vCell.systemId);
            p110z1.CellIdentityCdma.mBasestationId.set(cellIdentityCdma, vCell.baseStationId);
            p110z1.CellSignalStrengthCdma.mCdmaDbm.set(cellSignalStrengthCdma, -74);
            p110z1.CellSignalStrengthCdma.mCdmaEcio.set(cellSignalStrengthCdma, -91);
            p110z1.CellSignalStrengthCdma.mEvdoDbm.set(cellSignalStrengthCdma, -64);
            p110z1.CellSignalStrengthCdma.mEvdoSnr.set(cellSignalStrengthCdma, 7);
            return newInstance;
        }
        CellInfoGsm newInstance2 = p110z1.CellInfoGsm.ctor.newInstance();
        CellIdentityGsm cellIdentityGsm = p110z1.CellInfoGsm.mCellIdentityGsm.get(newInstance2);
        CellSignalStrengthGsm cellSignalStrengthGsm = p110z1.CellInfoGsm.mCellSignalStrengthGsm.get(newInstance2);
        p110z1.CellIdentityGsm.mMcc.set(cellIdentityGsm, vCell.mcc);
        p110z1.CellIdentityGsm.mMnc.set(cellIdentityGsm, vCell.mnc);
        p110z1.CellIdentityGsm.mLac.set(cellIdentityGsm, vCell.lac);
        p110z1.CellIdentityGsm.mCid.set(cellIdentityGsm, vCell.cid);
        p110z1.CellSignalStrengthGsm.mSignalStrength.set(cellSignalStrengthGsm, 20);
        p110z1.CellSignalStrengthGsm.mBitErrorRate.set(cellSignalStrengthGsm, 0);
        return newInstance2;
    }
}
