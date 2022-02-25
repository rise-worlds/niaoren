package org.apache.tools.ant.types;

/* loaded from: classes2.dex */
public final class Parameter {
    private String name = null;
    private String type = null;
    private String value = null;

    public void setName(String str) {
        this.name = str;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }
}
