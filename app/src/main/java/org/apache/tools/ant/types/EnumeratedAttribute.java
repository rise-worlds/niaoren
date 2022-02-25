package org.apache.tools.ant.types;

import org.apache.tools.ant.BuildException;

/* loaded from: classes2.dex */
public abstract class EnumeratedAttribute {
    private int index = -1;
    protected String value;

    public abstract String[] getValues();

    public static EnumeratedAttribute getInstance(Class<? extends EnumeratedAttribute> cls, String str) throws BuildException {
        if (EnumeratedAttribute.class.isAssignableFrom(cls)) {
            try {
                EnumeratedAttribute enumeratedAttribute = (EnumeratedAttribute) cls.newInstance();
                enumeratedAttribute.setValue(str);
                return enumeratedAttribute;
            } catch (Exception e) {
                throw new BuildException(e);
            }
        } else {
            throw new BuildException("You have to provide a subclass from EnumeratedAttribut as clazz-parameter.");
        }
    }

    public final void setValue(String str) throws BuildException {
        int indexOfValue = indexOfValue(str);
        if (indexOfValue != -1) {
            this.index = indexOfValue;
            this.value = str;
            return;
        }
        throw new BuildException(str + " is not a legal value for this attribute");
    }

    public final boolean containsValue(String str) {
        return indexOfValue(str) != -1;
    }

    public final int indexOfValue(String str) {
        String[] values = getValues();
        if (values == null || str == null) {
            return -1;
        }
        for (int i = 0; i < values.length; i++) {
            if (str.equals(values[i])) {
                return i;
            }
        }
        return -1;
    }

    public final String getValue() {
        return this.value;
    }

    public final int getIndex() {
        return this.index;
    }

    public String toString() {
        return getValue();
    }
}
