package org.apache.tools.ant.attribute;

import org.apache.tools.ant.UnknownElement;

/* loaded from: classes2.dex */
public class IfSetAttribute extends BaseIfAttribute {

    /* loaded from: classes2.dex */
    public static class Unless extends IfSetAttribute {
        public Unless() {
            setPositive(false);
        }
    }

    @Override // org.apache.tools.ant.attribute.EnableAttribute
    public boolean isEnabled(UnknownElement unknownElement, String str) {
        return convertResult(getProject().getProperty(str) != null);
    }
}
