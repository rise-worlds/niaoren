package org.apache.tools.ant.property;

import java.text.ParsePosition;
import java.util.Collection;
import org.apache.tools.ant.Project;

/* loaded from: classes2.dex */
public class ParseProperties implements ParseNextProperty {
    private final Collection<PropertyExpander> expanders;
    private final GetProperty getProperty;
    private final Project project;

    public ParseProperties(Project project, Collection<PropertyExpander> collection, GetProperty getProperty) {
        this.project = project;
        this.expanders = collection;
        this.getProperty = getProperty;
    }

    @Override // org.apache.tools.ant.property.ParseNextProperty
    public Project getProject() {
        return this.project;
    }

    public Object parseProperties(String str) {
        if (str == null || "".equals(str)) {
            return str;
        }
        int length = str.length();
        ParsePosition parsePosition = new ParsePosition(0);
        Object parseNextProperty = parseNextProperty(str, parsePosition);
        if (parseNextProperty != null && parsePosition.getIndex() >= length) {
            return parseNextProperty;
        }
        StringBuffer stringBuffer = new StringBuffer(length * 2);
        if (parseNextProperty == null) {
            stringBuffer.append(str.charAt(parsePosition.getIndex()));
            parsePosition.setIndex(parsePosition.getIndex() + 1);
        } else {
            stringBuffer.append(parseNextProperty);
        }
        while (parsePosition.getIndex() < length) {
            Object parseNextProperty2 = parseNextProperty(str, parsePosition);
            if (parseNextProperty2 == null) {
                stringBuffer.append(str.charAt(parsePosition.getIndex()));
                parsePosition.setIndex(parsePosition.getIndex() + 1);
            } else {
                stringBuffer.append(parseNextProperty2);
            }
        }
        return stringBuffer.toString();
    }

    public boolean containsProperties(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        ParsePosition parsePosition = new ParsePosition(0);
        while (parsePosition.getIndex() < length) {
            if (parsePropertyName(str, parsePosition) != null) {
                return true;
            }
            parsePosition.setIndex(parsePosition.getIndex() + 1);
        }
        return false;
    }

    @Override // org.apache.tools.ant.property.ParseNextProperty
    public Object parseNextProperty(String str, ParsePosition parsePosition) {
        String parsePropertyName;
        int index = parsePosition.getIndex();
        if (index > str.length() || (parsePropertyName = parsePropertyName(str, parsePosition)) == null) {
            return null;
        }
        Object property = getProperty(parsePropertyName);
        if (property != null) {
            return property;
        }
        Project project = this.project;
        if (project != null) {
            project.log("Property \"" + parsePropertyName + "\" has not been set", 3);
        }
        return str.substring(index, parsePosition.getIndex());
    }

    private String parsePropertyName(String str, ParsePosition parsePosition) {
        for (PropertyExpander propertyExpander : this.expanders) {
            String parsePropertyName = propertyExpander.parsePropertyName(str, parsePosition, this);
            if (parsePropertyName != null) {
                return parsePropertyName;
            }
        }
        return null;
    }

    private Object getProperty(String str) {
        return this.getProperty.getProperty(str);
    }
}
