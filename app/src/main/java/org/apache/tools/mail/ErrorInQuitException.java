package org.apache.tools.mail;

import java.io.IOException;

/* loaded from: classes2.dex */
public class ErrorInQuitException extends IOException {
    public ErrorInQuitException(IOException iOException) {
        super(iOException.getMessage());
    }
}
