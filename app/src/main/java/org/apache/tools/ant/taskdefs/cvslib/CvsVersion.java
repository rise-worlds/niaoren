package org.apache.tools.ant.taskdefs.cvslib;

import java.util.StringTokenizer;
import org.apache.tools.ant.taskdefs.AbstractCvsTask;
import p110z1.Consts;

/* loaded from: classes2.dex */
public class CvsVersion extends AbstractCvsTask {
    static final long MULTIPLY = 100;
    static final long VERSION_1_11_2 = 11102;
    private String clientVersion;
    private String clientVersionProperty;
    private String serverVersion;
    private String serverVersionProperty;

    public String getClientVersion() {
        return this.clientVersion;
    }

    public String getServerVersion() {
        return this.serverVersion;
    }

    public void setClientVersionProperty(String str) {
        this.clientVersionProperty = str;
    }

    public void setServerVersionProperty(String str) {
        this.serverVersionProperty = str;
    }

    public boolean supportsCvsLogWithSOption() {
        String str = this.serverVersion;
        if (str == null) {
            return false;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str, Consts.f23430h);
        long j = 10000;
        long j2 = 0;
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            int i = 0;
            while (i < nextToken.length() && Character.isDigit(nextToken.charAt(i))) {
                i++;
            }
            j2 += Long.parseLong(nextToken.substring(0, i)) * j;
            if (j == 1) {
                break;
            }
            j /= MULTIPLY;
        }
        return j2 >= VERSION_1_11_2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x00ca, code lost:
        if (r7 == null) goto L_0x00ec;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00d0, code lost:
        if (r1.hasMoreTokens() == false) goto L_0x00e7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00d2, code lost:
        r10.clientVersion = r1.nextToken() + r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00e7, code lost:
        r7 = null;
        r3 = false;
        r5 = false;
     */
    @Override // org.apache.tools.ant.taskdefs.AbstractCvsTask, org.apache.tools.ant.Task
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute() {
        /*
            Method dump skipped, instructions count: 317
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.cvslib.CvsVersion.execute():void");
    }
}
