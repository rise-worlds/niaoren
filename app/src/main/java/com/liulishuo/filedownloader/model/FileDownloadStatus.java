package com.liulishuo.filedownloader.model;

import p110z1.BaseDownloadTask;

/* renamed from: com.liulishuo.filedownloader.model.b */
/* loaded from: classes.dex */
public class FileDownloadStatus {

    /* renamed from: a */
    public static final byte f10399a = 10;

    /* renamed from: b */
    public static final byte f10400b = 11;

    /* renamed from: c */
    public static final byte f10401c = 1;

    /* renamed from: d */
    public static final byte f10402d = 6;

    /* renamed from: e */
    public static final byte f10403e = 2;

    /* renamed from: f */
    public static final byte f10404f = 3;

    /* renamed from: g */
    public static final byte f10405g = 4;

    /* renamed from: h */
    public static final byte f10406h = 5;

    /* renamed from: i */
    public static final byte f10407i = -1;

    /* renamed from: j */
    public static final byte f10408j = -2;

    /* renamed from: k */
    public static final byte f10409k = -3;

    /* renamed from: l */
    public static final byte f10410l = -4;

    /* renamed from: m */
    public static final byte f10411m = 0;

    /* renamed from: a */
    public static boolean m19081a(int i) {
        return i < 0;
    }

    /* renamed from: b */
    public static boolean m19078b(int i) {
        return i > 0;
    }

    /* renamed from: a */
    public static boolean m19080a(int i, int i2) {
        if ((i != 3 && i != 5 && i == i2) || m19081a(i)) {
            return false;
        }
        if (i >= 1 && i <= 6 && i2 >= 10 && i2 <= 11) {
            return false;
        }
        switch (i) {
            case 1:
                return i2 != 0;
            case 2:
                if (i2 != 6) {
                    switch (i2) {
                        case 0:
                        case 1:
                            break;
                        default:
                            return true;
                    }
                }
                return false;
            case 3:
                if (i2 != 6) {
                    switch (i2) {
                        case 0:
                        case 1:
                        case 2:
                            break;
                        default:
                            return true;
                    }
                }
                return false;
            case 4:
            default:
                return true;
            case 5:
                return (i2 == 1 || i2 == 6) ? false : true;
            case 6:
                switch (i2) {
                    case 0:
                    case 1:
                        return false;
                    default:
                        return true;
                }
        }
    }

    /* renamed from: b */
    public static boolean m19077b(int i, int i2) {
        if ((i != 3 && i != 5 && i == i2) || m19081a(i)) {
            return false;
        }
        if (i2 == -2 || i2 == -1) {
            return true;
        }
        switch (i) {
            case 0:
                return i2 == 10;
            case 1:
                return i2 == 6;
            case 2:
            case 3:
                return i2 == -3 || i2 == 3 || i2 == 5;
            case 4:
            case 7:
            case 8:
            case 9:
            default:
                return false;
            case 5:
            case 6:
                return i2 == 2 || i2 == 5;
            case 10:
                return i2 == 11;
            case 11:
                if (i2 != 1) {
                    switch (i2) {
                        case -4:
                        case -3:
                            break;
                        default:
                            return false;
                    }
                }
                return true;
        }
    }

    /* renamed from: a */
    public static boolean m19079a(BaseDownloadTask afaVar) {
        return afaVar.mo13815B() == 0 || afaVar.mo13815B() == 3;
    }
}
