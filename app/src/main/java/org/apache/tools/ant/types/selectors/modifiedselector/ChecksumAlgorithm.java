package org.apache.tools.ant.types.selectors.modifiedselector;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.zip.Adler32;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.Checksum;
import org.apache.tools.ant.BuildException;
import p110z1.SimpleComparison;

/* loaded from: classes2.dex */
public class ChecksumAlgorithm implements Algorithm {
    private String algorithm = "CRC";
    private Checksum checksum = null;

    public void setAlgorithm(String str) {
        this.algorithm = str != null ? str.toUpperCase(Locale.ENGLISH) : null;
    }

    public void initChecksum() {
        if (this.checksum == null) {
            if ("CRC".equals(this.algorithm)) {
                this.checksum = new CRC32();
            } else if ("ADLER".equals(this.algorithm)) {
                this.checksum = new Adler32();
            } else {
                throw new BuildException(new NoSuchAlgorithmException());
            }
        }
    }

    @Override // org.apache.tools.ant.types.selectors.modifiedselector.Algorithm
    public boolean isValid() {
        return "CRC".equals(this.algorithm) || "ADLER".equals(this.algorithm);
    }

    @Override // org.apache.tools.ant.types.selectors.modifiedselector.Algorithm
    public String getValue(File file) {
        initChecksum();
        try {
            if (!file.canRead()) {
                return null;
            }
            this.checksum.reset();
            CheckedInputStream checkedInputStream = new CheckedInputStream(new FileInputStream(file), this.checksum);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(checkedInputStream);
            while (bufferedInputStream.read() != -1) {
            }
            String l = Long.toString(checkedInputStream.getChecksum().getValue());
            bufferedInputStream.close();
            return l;
        } catch (Exception unused) {
            return null;
        }
    }

    public String toString() {
        return "<ChecksumAlgorithm:algorithm=" + this.algorithm + SimpleComparison.f23610d;
    }
}
