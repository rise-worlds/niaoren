package com.cyjh.ddysdk.order.base.constants;

import com.cyjh.ddy.base.p033a.NoProGuard;
import com.lody.virtual.client.env.Constants;

/* loaded from: classes.dex */
public enum DdyOrderErrorConstants implements NoProGuard {
    DOE_NET_WRAPPER_CODE_APP_100(-100),
    DOE_NET_WRAPPER_CODE_NULL(-1),
    DOE_NET_WRAPPER_CODE_0(0),
    DOE_NET_WRAPPER_CODE_1(1),
    DOE_NET_WRAPPER_CODE_2(2),
    DOE_NET_WRAPPER_CODE_3(3),
    DOE_NET_WRAPPER_CODE_4(4),
    DOE_NET_WRAPPER_CODE_5(5),
    DOE_NET_WRAPPER_CODE_6(6),
    DOE_NET_WRAPPER_CODE_7(7),
    DOE_NET_WRAPPER_CODE_8(8),
    DOE_NET_WRAPPER_CODE_9(9),
    DOE_NET_WRAPPER_CODE_10(10),
    DOE_NET_WRAPPER_CODE_15(15),
    DOE_NET_WRAPPER_CODE_20(20),
    DOE_NET_WRAPPER_CODE_21(21),
    DOE_NET_WRAPPER_CODE_22(22),
    DOE_NET_WRAPPER_CODE_24(24),
    DOE_NET_WRAPPER_CODE_41(41),
    DOE_NET_WRAPPER_CODE_UNKNOW(100),
    DOE_EXCEPTION(101),
    DOE_ORDER_SERVERTYPE(102),
    DOE_END(Constants.OUTSIDE_APP_UID);
    

    /* renamed from: a */
    private int f8100a;

    DdyOrderErrorConstants(int i) {
        this.f8100a = 0;
        this.f8100a = i;
    }

    public int value() {
        return this.f8100a;
    }

    public static DdyOrderErrorConstants valueOf(Integer num) {
        if (num == null) {
            return DOE_NET_WRAPPER_CODE_NULL;
        }
        int intValue = num.intValue();
        if (intValue == -100) {
            return DOE_NET_WRAPPER_CODE_APP_100;
        }
        if (intValue == 15) {
            return DOE_NET_WRAPPER_CODE_15;
        }
        if (intValue == 24) {
            return DOE_NET_WRAPPER_CODE_24;
        }
        if (intValue == 41) {
            return DOE_NET_WRAPPER_CODE_41;
        }
        switch (intValue) {
            case 0:
                return DOE_NET_WRAPPER_CODE_0;
            case 1:
                return DOE_NET_WRAPPER_CODE_1;
            case 2:
                return DOE_NET_WRAPPER_CODE_2;
            case 3:
                return DOE_NET_WRAPPER_CODE_3;
            case 4:
                return DOE_NET_WRAPPER_CODE_4;
            case 5:
                return DOE_NET_WRAPPER_CODE_5;
            case 6:
                return DOE_NET_WRAPPER_CODE_6;
            case 7:
                return DOE_NET_WRAPPER_CODE_7;
            case 8:
                return DOE_NET_WRAPPER_CODE_8;
            case 9:
                return DOE_NET_WRAPPER_CODE_9;
            case 10:
                return DOE_NET_WRAPPER_CODE_10;
            default:
                switch (intValue) {
                    case 20:
                        return DOE_NET_WRAPPER_CODE_20;
                    case 21:
                        return DOE_NET_WRAPPER_CODE_21;
                    case 22:
                        return DOE_NET_WRAPPER_CODE_22;
                    default:
                        return DOE_NET_WRAPPER_CODE_UNKNOW;
                }
        }
    }
}
