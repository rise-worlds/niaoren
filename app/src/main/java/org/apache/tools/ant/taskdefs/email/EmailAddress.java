package org.apache.tools.ant.taskdefs.email;

import p110z1.SimpleComparison;

/* loaded from: classes2.dex */
public class EmailAddress {
    private String address;
    private String name;

    public EmailAddress() {
    }

    public EmailAddress(String str) {
        int length = str.length();
        if (length <= 9 || !((str.charAt(0) == '<' || str.charAt(1) == '<') && (str.charAt(length - 1) == '>' || str.charAt(length - 2) == '>'))) {
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            for (int i6 = 0; i6 < length; i6++) {
                char charAt = str.charAt(i6);
                if (charAt == '(') {
                    i3++;
                    if (i4 == 0) {
                        i5 = i6 + 1;
                        i = i6;
                    }
                } else if (charAt == ')') {
                    i3--;
                    if (i == 0) {
                        i4 = i6 + 1;
                        i2 = i6;
                    }
                } else if (i3 == 0 && charAt == '<') {
                    i2 = i4 == 0 ? i6 : i2;
                    i4 = i6 + 1;
                } else if (i3 == 0 && charAt == '>') {
                    if (i6 != length - 1) {
                        i5 = i6 + 1;
                        i = i6;
                    } else {
                        i = i6;
                    }
                }
            }
            i = i == 0 ? length : i;
            i2 = i2 == 0 ? length : i2;
            this.address = trim(str.substring(i4, i), true);
            this.name = trim(str.substring(i5, i2), false);
            if (this.name.length() + this.address.length() > length) {
                this.name = null;
                return;
            }
            return;
        }
        this.address = trim(str, true);
    }

    private String trim(String str, boolean z) {
        boolean z2;
        int length = str.length();
        int i = 0;
        do {
            int i2 = length - 1;
            if (str.charAt(i2) == ')' || ((str.charAt(i2) == '>' && z) || ((str.charAt(i2) == '\"' && str.charAt(length - 2) != '\\') || str.charAt(i2) <= ' '))) {
                length--;
                z2 = true;
            } else {
                z2 = false;
            }
            if (str.charAt(i) == '(' || ((str.charAt(i) == '<' && z) || str.charAt(i) == '\"' || str.charAt(i) <= ' ')) {
                i++;
                z2 = true;
                continue;
            }
        } while (z2);
        return str.substring(i, length);
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public String toString() {
        if (this.name == null) {
            return this.address;
        }
        return this.name + " <" + this.address + SimpleComparison.f23610d;
    }

    public String getAddress() {
        return this.address;
    }

    public String getName() {
        return this.name;
    }
}
