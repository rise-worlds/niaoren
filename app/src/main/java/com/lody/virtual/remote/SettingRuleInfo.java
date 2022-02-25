package com.lody.virtual.remote;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class SettingRuleInfo implements Parcelable {
    public static final Parcelable.Creator<SettingRuleInfo> CREATOR = new Parcelable.Creator<SettingRuleInfo>() { // from class: com.lody.virtual.remote.SettingRuleInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SettingRuleInfo createFromParcel(Parcel parcel) {
            return new SettingRuleInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SettingRuleInfo[] newArray(int i) {
            return new SettingRuleInfo[i];
        }
    };
    private transient Pattern pattern;
    public boolean regex;
    public int rule;
    public String word;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SettingRuleInfo settingRuleInfo = (SettingRuleInfo) obj;
        return this.rule == settingRuleInfo.rule && this.regex == settingRuleInfo.regex && TextUtils.equals(this.word, settingRuleInfo.word);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.rule), this.word, Boolean.valueOf(this.regex)});
    }

    public boolean matches(String str) {
        if (!this.regex) {
            return TextUtils.equals(str, this.word);
        }
        try {
            if (this.pattern == null) {
                this.pattern = Pattern.compile(this.word);
            }
            return this.pattern.matcher(str).matches();
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.rule);
        parcel.writeString(this.word);
        parcel.writeByte(this.regex ? (byte) 1 : (byte) 0);
    }

    public SettingRuleInfo() {
    }

    public SettingRuleInfo(int i, String str, boolean z) {
        this.rule = i;
        this.word = str;
        this.regex = z;
    }

    protected SettingRuleInfo(Parcel parcel) {
        this.rule = parcel.readInt();
        this.word = parcel.readString();
        this.regex = parcel.readByte() != 0;
    }
}
