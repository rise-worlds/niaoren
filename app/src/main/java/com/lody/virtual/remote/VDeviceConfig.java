package com.lody.virtual.remote;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.lody.virtual.os.VEnvironment;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/* loaded from: classes.dex */
public class VDeviceConfig implements Parcelable {
    public static final int VERSION = 3;
    public String androidId;
    public String bluetoothMac;
    public final Map<String, String> buildProp = new HashMap();
    public String deviceId;
    public boolean enable;
    public String gmsAdId;
    public String iccId;
    public String serial;
    public String wifiMac;
    private static final UsedDeviceInfoPool mPool = new UsedDeviceInfoPool();
    public static final Parcelable.Creator<VDeviceConfig> CREATOR = new Parcelable.Creator<VDeviceConfig>() { // from class: com.lody.virtual.remote.VDeviceConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VDeviceConfig createFromParcel(Parcel parcel) {
            return new VDeviceConfig(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VDeviceConfig[] newArray(int i) {
            return new VDeviceConfig[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class UsedDeviceInfoPool {
        final List<String> androidIds;
        final List<String> bluetoothMacs;
        final List<String> deviceIds;
        final List<String> iccIds;
        final List<String> wifiMacs;

        private UsedDeviceInfoPool() {
            this.deviceIds = new ArrayList();
            this.androidIds = new ArrayList();
            this.wifiMacs = new ArrayList();
            this.bluetoothMacs = new ArrayList();
            this.iccIds = new ArrayList();
        }
    }

    public VDeviceConfig() {
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.enable ? (byte) 1 : (byte) 0);
        parcel.writeString(this.deviceId);
        parcel.writeString(this.androidId);
        parcel.writeString(this.wifiMac);
        parcel.writeString(this.bluetoothMac);
        parcel.writeString(this.iccId);
        parcel.writeString(this.serial);
        parcel.writeString(this.gmsAdId);
        parcel.writeInt(this.buildProp.size());
        for (Map.Entry<String, String> entry : this.buildProp.entrySet()) {
            parcel.writeString(entry.getKey());
            parcel.writeString(entry.getValue());
        }
    }

    public VDeviceConfig(Parcel parcel) {
        this.enable = parcel.readByte() != 0;
        this.deviceId = parcel.readString();
        this.androidId = parcel.readString();
        this.wifiMac = parcel.readString();
        this.bluetoothMac = parcel.readString();
        this.iccId = parcel.readString();
        this.serial = parcel.readString();
        this.gmsAdId = parcel.readString();
        int readInt = parcel.readInt();
        for (int i = 0; i < readInt; i++) {
            this.buildProp.put(parcel.readString(), parcel.readString());
        }
    }

    public String getProp(String str) {
        return this.buildProp.get(str);
    }

    public void setProp(String str, String str2) {
        this.buildProp.put(str, str2);
    }

    public void clear() {
        this.deviceId = null;
        this.androidId = null;
        this.wifiMac = null;
        this.bluetoothMac = null;
        this.iccId = null;
        this.serial = null;
        this.gmsAdId = null;
    }

    public static VDeviceConfig random() {
        String generateDeviceId;
        String generateHex;
        String generateMac;
        String generateMac2;
        String generate10;
        VDeviceConfig vDeviceConfig = new VDeviceConfig();
        do {
            generateDeviceId = generateDeviceId();
            vDeviceConfig.deviceId = generateDeviceId;
        } while (mPool.deviceIds.contains(generateDeviceId));
        do {
            generateHex = generateHex(System.currentTimeMillis(), 16);
            vDeviceConfig.androidId = generateHex;
        } while (mPool.androidIds.contains(generateHex));
        do {
            generateMac = generateMac();
            vDeviceConfig.wifiMac = generateMac;
        } while (mPool.wifiMacs.contains(generateMac));
        do {
            generateMac2 = generateMac();
            vDeviceConfig.bluetoothMac = generateMac2;
        } while (mPool.bluetoothMacs.contains(generateMac2));
        do {
            generate10 = generate10(System.currentTimeMillis(), 20);
            vDeviceConfig.iccId = generate10;
        } while (mPool.iccIds.contains(generate10));
        vDeviceConfig.serial = generateSerial();
        addToPool(vDeviceConfig);
        return vDeviceConfig;
    }

    public static String generateDeviceId() {
        return generate10(System.currentTimeMillis(), 15);
    }

    public static String generate10(long j, int i) {
        Random random = new Random(j);
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public static String generateHex(long j, int i) {
        Random random = new Random(j);
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            int nextInt = random.nextInt(16);
            if (nextInt < 10) {
                sb.append(nextInt);
            } else {
                sb.append((char) ((nextInt - 10) + 97));
            }
        }
        return sb.toString();
    }

    private static String generateMac() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (int i2 = 0; i2 < 12; i2++) {
            int nextInt = random.nextInt(16);
            if (nextInt < 10) {
                sb.append(nextInt);
            } else {
                sb.append((char) (nextInt + 87));
            }
            if (i2 == i && i2 != 11) {
                sb.append(":");
                i += 2;
            }
        }
        return sb.toString();
    }

    @SuppressLint({"HardwareIds"})
    private static String generateSerial() {
        String str = (Build.SERIAL == null || Build.SERIAL.length() <= 0) ? "0123456789ABCDEF" : Build.SERIAL;
        ArrayList<Character> arrayList = new ArrayList();
        for (char c : str.toCharArray()) {
            arrayList.add(Character.valueOf(c));
        }
        Collections.shuffle(arrayList);
        StringBuilder sb = new StringBuilder();
        for (Character ch : arrayList) {
            sb.append(ch.charValue());
        }
        return sb.toString();
    }

    public File getWifiFile(int i, boolean z) {
        if (TextUtils.isEmpty(this.wifiMac)) {
            return null;
        }
        File wifiMacFile = VEnvironment.getWifiMacFile(i, z);
        if (!wifiMacFile.exists()) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(wifiMacFile, "rws");
                randomAccessFile.write((this.wifiMac + "\n").getBytes());
                randomAccessFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return wifiMacFile;
    }

    public static void addToPool(VDeviceConfig vDeviceConfig) {
        mPool.deviceIds.add(vDeviceConfig.deviceId);
        mPool.androidIds.add(vDeviceConfig.androidId);
        mPool.wifiMacs.add(vDeviceConfig.wifiMac);
        mPool.bluetoothMacs.add(vDeviceConfig.bluetoothMac);
        mPool.iccIds.add(vDeviceConfig.iccId);
    }
}
