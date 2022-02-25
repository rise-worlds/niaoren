package org.apache.tools.ant.taskdefs.optional.ejb;

import java.io.File;
import java.io.FilenameFilter;
import p110z1.Consts;

/* loaded from: classes2.dex */
public class InnerClassFilenameFilter implements FilenameFilter {
    private String baseClassName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public InnerClassFilenameFilter(String str) {
        int lastIndexOf = str.lastIndexOf(".class");
        this.baseClassName = str.substring(0, lastIndexOf == -1 ? str.length() - 1 : lastIndexOf);
    }

    @Override // java.io.FilenameFilter
    public boolean accept(File file, String str) {
        if (str.lastIndexOf(Consts.f23430h) != str.lastIndexOf(".class")) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.baseClassName);
        sb.append("$");
        return str.indexOf(sb.toString()) == 0;
    }
}
