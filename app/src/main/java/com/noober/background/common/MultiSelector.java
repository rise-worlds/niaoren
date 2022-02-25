package com.noober.background.common;

/* loaded from: classes.dex */
public enum MultiSelector {
    State_Checkable("state_checkable", 16842911),
    State_Checked("state_checked", 16842912),
    State_Enabled("state_enabled", 16842910),
    State_Selected("state_selected", 16842913),
    State_Pressed("state_pressed", 16842919),
    State_Focused("state_focused", 16842908),
    State_Hovered("state_hovered", 16843623),
    State_Activated("state_activated", 16843518);
    

    /* renamed from: id */
    public int f10508id;
    public String value;

    MultiSelector(String str, int i) {
        this.value = str;
        this.f10508id = i;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static MultiSelector getMultiAttr(String str) {
        char c;
        switch (str.hashCode()) {
            case -1722420599:
                if (str.equals("state_selected")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1616325175:
                if (str.equals("state_focused")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1243548044:
                if (str.equals("state_pressed")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -182969863:
                if (str.equals("state_checked")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 64931747:
                if (str.equals("state_activated")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 175751469:
                if (str.equals("state_hovered")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 259503156:
                if (str.equals("state_checkable")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1760089491:
                if (str.equals("state_enabled")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return State_Checkable;
            case 1:
                return State_Checked;
            case 2:
                return State_Enabled;
            case 3:
                return State_Selected;
            case 4:
                return State_Pressed;
            case 5:
                return State_Focused;
            case 6:
                return State_Hovered;
            case 7:
                return State_Activated;
            default:
                return null;
        }
    }
}
