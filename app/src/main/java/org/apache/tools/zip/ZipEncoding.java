package org.apache.tools.zip;

import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public interface ZipEncoding {
    boolean canEncode(String str);

    String decode(byte[] bArr) throws IOException;

    ByteBuffer encode(String str) throws IOException;
}
