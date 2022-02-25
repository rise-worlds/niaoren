package com.lody.virtual.remote.vloc;

import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import com.lody.virtual.client.env.VirtualGPSSatalines;
import com.lody.virtual.helper.utils.Reflect;

/* loaded from: classes.dex */
public class VLocation implements Parcelable {
    public static final Parcelable.Creator<VLocation> CREATOR = new Parcelable.Creator<VLocation>() { // from class: com.lody.virtual.remote.vloc.VLocation.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VLocation createFromParcel(Parcel parcel) {
            return new VLocation(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VLocation[] newArray(int i) {
            return new VLocation[i];
        }
    };
    public float accuracy;
    public double altitude;
    public float bearing;
    public double latitude;
    public double longitude;
    public float speed;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.latitude);
        parcel.writeDouble(this.longitude);
        parcel.writeDouble(this.altitude);
        parcel.writeFloat(this.accuracy);
        parcel.writeFloat(this.speed);
        parcel.writeFloat(this.bearing);
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public VLocation() {
        this.latitude = 0.0d;
        this.longitude = 0.0d;
        this.altitude = 0.0d;
        this.accuracy = 0.0f;
    }

    public VLocation(double d, double d2) {
        this.latitude = 0.0d;
        this.longitude = 0.0d;
        this.altitude = 0.0d;
        this.accuracy = 0.0f;
        this.latitude = d;
        this.longitude = d2;
    }

    public VLocation(Parcel parcel) {
        this.latitude = 0.0d;
        this.longitude = 0.0d;
        this.altitude = 0.0d;
        this.accuracy = 0.0f;
        this.latitude = parcel.readDouble();
        this.longitude = parcel.readDouble();
        this.altitude = parcel.readDouble();
        this.accuracy = parcel.readFloat();
        this.speed = parcel.readFloat();
        this.bearing = parcel.readFloat();
    }

    public boolean isEmpty() {
        return this.latitude == 0.0d && this.longitude == 0.0d;
    }

    public String toString() {
        return "VLocation{latitude=" + this.latitude + ", longitude=" + this.longitude + ", altitude=" + this.altitude + ", accuracy=" + this.accuracy + ", speed=" + this.speed + ", bearing=" + this.bearing + '}';
    }

    public Location toSysLocation() {
        Location location = new Location("gps");
        location.setAccuracy(8.0f);
        Bundle bundle = new Bundle();
        location.setBearing(this.bearing);
        Reflect.m18998on(location).call("setIsFromMockProvider", false);
        location.setLatitude(this.latitude);
        location.setLongitude(this.longitude);
        location.setSpeed(this.speed);
        location.setTime(System.currentTimeMillis());
        int svCount = VirtualGPSSatalines.get().getSvCount();
        bundle.putInt("satellites", svCount);
        bundle.putInt("satellitesvalue", svCount);
        location.setExtras(bundle);
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                Reflect.m18998on(location).call("makeComplete");
            } catch (Exception unused) {
                location.setTime(System.currentTimeMillis());
                location.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
            }
        }
        return location;
    }
}
