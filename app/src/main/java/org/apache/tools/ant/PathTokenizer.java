package org.apache.tools.ant;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import org.apache.tools.ant.taskdefs.condition.C3209Os;
import p110z1.Consts;

/* loaded from: classes2.dex */
public class PathTokenizer {
    private boolean dosStyleFilesystem;
    private String lookahead = null;
    private boolean onNetWare = C3209Os.isFamily(C3209Os.FAMILY_NETWARE);
    private StringTokenizer tokenizer;

    public PathTokenizer(String str) {
        boolean z = true;
        if (this.onNetWare) {
            this.tokenizer = new StringTokenizer(str, ":;", true);
        } else {
            this.tokenizer = new StringTokenizer(str, ":;", false);
        }
        this.dosStyleFilesystem = File.pathSeparatorChar != ';' ? false : z;
    }

    public boolean hasMoreTokens() {
        if (this.lookahead != null) {
            return true;
        }
        return this.tokenizer.hasMoreTokens();
    }

    public String nextToken() throws NoSuchElementException {
        String str = this.lookahead;
        if (str != null) {
            this.lookahead = null;
        } else {
            str = this.tokenizer.nextToken().trim();
        }
        if (this.onNetWare) {
            if (str.equals(File.pathSeparator) || str.equals(":")) {
                str = this.tokenizer.nextToken().trim();
            }
            if (!this.tokenizer.hasMoreTokens()) {
                return str;
            }
            String trim = this.tokenizer.nextToken().trim();
            if (trim.equals(File.pathSeparator)) {
                return str;
            }
            if (!trim.equals(":")) {
                this.lookahead = trim;
                return str;
            } else if (str.startsWith("/") || str.startsWith("\\") || str.startsWith(Consts.f23430h) || str.startsWith("..")) {
                return str;
            } else {
                String trim2 = this.tokenizer.nextToken().trim();
                if (!trim2.equals(File.pathSeparator)) {
                    return str + ":" + trim2;
                }
                String str2 = str + ":";
                this.lookahead = trim2;
                return str2;
            }
        } else if (str.length() != 1 || !Character.isLetter(str.charAt(0)) || !this.dosStyleFilesystem || !this.tokenizer.hasMoreTokens()) {
            return str;
        } else {
            String trim3 = this.tokenizer.nextToken().trim();
            if (trim3.startsWith("\\") || trim3.startsWith("/")) {
                return str + ":" + trim3;
            }
            this.lookahead = trim3;
            return str;
        }
    }
}
