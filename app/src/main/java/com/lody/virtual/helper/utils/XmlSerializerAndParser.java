package com.lody.virtual.helper.utils;

import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* loaded from: classes.dex */
public interface XmlSerializerAndParser<T> {
    T createFromXml(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException;

    void writeAsXml(T t, XmlSerializer xmlSerializer) throws IOException;
}
