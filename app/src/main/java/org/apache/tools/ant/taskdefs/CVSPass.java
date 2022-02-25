package org.apache.tools.ant.taskdefs;

import java.io.File;
import org.apache.commons.p105io.FilenameUtils;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.Task;
import p110z1.Typography;

/* loaded from: classes2.dex */
public class CVSPass extends Task {
    private File passFile;
    private String cvsRoot = null;
    private String password = null;
    private final char[] shifts = {0, 1, 2, 3, 4, 5, 6, 7, '\b', '\t', '\n', 11, '\f', '\r', 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 'r', 'x', '5', 'O', '`', 'm', 'H', 'l', 'F', '@', 'L', 'C', 't', 'J', 'D', 'W', 'o', '4', 'K', 'w', '1', Typography.f21049a, 'R', 'Q', '_', 'A', 'p', 'V', 'v', 'n', 'z', 'i', ')', '9', 'S', '+', FilenameUtils.EXTENSION_SEPARATOR, 'f', '(', 'Y', Typography.f21051c, 'g', '-', '2', '*', '{', '[', '#', '}', '7', '6', 'B', '|', '~', ';', IOUtils.DIR_SEPARATOR_UNIX, IOUtils.DIR_SEPARATOR_WINDOWS, 'G', 's', 'N', 'X', 'k', 'j', '8', Typography.f21050b, 'y', 'u', 'h', 'e', 'd', 'E', 'I', 'c', '?', '^', ']', '\'', '%', '=', '0', ':', 'q', ' ', 'Z', ',', 'b', Typography.f21052d, '3', '!', 'a', Typography.f21053e, 'M', 'T', 'P', 'U', 223, 225, 216, Typography.f21061m, 166, 229, Typography.f21067s, 222, 188, 141, 249, 148, 200, 184, 136, 248, 190, 199, 170, 181, 204, 138, 232, 218, Typography.f21066r, 255, 234, 220, 247, 213, 203, 226, 193, Typography.f21062n, 172, 228, 252, 217, 201, 131, 230, 197, 211, 145, 238, 161, 179, Typography.f21054f, 212, 207, 221, 254, 173, 202, 146, 224, 151, 140, 196, 205, 130, 135, 133, 143, 246, 192, 159, 244, 239, 185, 168, Typography.f21055g, 144, 139, 165, 180, 157, 147, 186, 214, Typography.f21063o, 227, 231, 219, Typography.f21059k, 175, 156, 206, 198, 129, 164, 150, 210, 154, Typography.f21064p, 134, 127, Typography.f21065q, 128, 158, 208, Typography.f21056h, 132, Typography.f21058j, 209, 149, 241, 153, 251, 237, 236, Typography.f21060l, 195, 243, 233, 253, 240, 194, 250, 191, 155, 142, 137, 245, 235, Typography.f21057i, 242, 178, 152};

    public CVSPass() {
        this.passFile = null;
        this.passFile = new File(System.getProperty("cygwin.user.home", System.getProperty("user.home")) + File.separatorChar + ".cvspass");
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00ee A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // org.apache.tools.ant.Task
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void execute() throws org.apache.tools.ant.BuildException {
        /*
            Method dump skipped, instructions count: 261
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.CVSPass.execute():void");
    }

    private final String mangle(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            stringBuffer.append(this.shifts[str.charAt(i)]);
        }
        return stringBuffer.toString();
    }

    public void setCvsroot(String str) {
        this.cvsRoot = str;
    }

    public void setPassfile(File file) {
        this.passFile = file;
    }

    public void setPassword(String str) {
        this.password = str;
    }
}
