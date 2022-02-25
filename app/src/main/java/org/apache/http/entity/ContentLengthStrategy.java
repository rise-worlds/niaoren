package org.apache.http.entity;

import org.apache.http.HttpException;
import org.apache.http.HttpMessage;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public interface ContentLengthStrategy {
    public static final int CHUNKED = -2;
    public static final int IDENTITY = -1;

    long determineLength(HttpMessage httpMessage) throws HttpException;
}
