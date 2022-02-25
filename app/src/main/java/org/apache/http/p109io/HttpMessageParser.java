package org.apache.http.p109io;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;

@Deprecated
/* renamed from: org.apache.http.io.HttpMessageParser */
/* loaded from: assets/org.apache.http.legacy.boot */
public interface HttpMessageParser {
    HttpMessage parse() throws IOException, HttpException;
}
