package org.apache.tools.ant.property;

import java.text.ParsePosition;
import org.apache.tools.ant.Project;

/* loaded from: classes2.dex */
public interface ParseNextProperty {
    Project getProject();

    Object parseNextProperty(String str, ParsePosition parsePosition);
}
