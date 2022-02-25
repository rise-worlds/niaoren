package org.apache.tools.ant.property;

import java.text.ParsePosition;
import org.apache.tools.ant.PropertyHelper;

/* loaded from: classes2.dex */
public interface PropertyExpander extends PropertyHelper.Delegate {
    String parsePropertyName(String str, ParsePosition parsePosition, ParseNextProperty parseNextProperty);
}
