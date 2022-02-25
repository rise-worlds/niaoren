package org.apache.tools.ant.util.facade;

import org.apache.tools.ant.types.Commandline;

/* loaded from: classes2.dex */
public class ImplementationSpecificArgument extends Commandline.Argument {
    private String impl;

    public void setImplementation(String str) {
        this.impl = str;
    }

    public final String[] getParts(String str) {
        String str2 = this.impl;
        if (str2 == null || str2.equals(str)) {
            return super.getParts();
        }
        return new String[0];
    }
}
