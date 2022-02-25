package org.apache.tools.ant.taskdefs.optional.extension;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import org.apache.tools.ant.util.DeweyDecimal;
import org.apache.tools.ant.util.StringUtils;

/* loaded from: classes2.dex */
public final class Specification {
    private static final String MISSING = "Missing ";
    private String implementationTitle;
    private String implementationVendor;
    private String implementationVersion;
    private String[] sections;
    private String specificationTitle;
    private String specificationVendor;
    private DeweyDecimal specificationVersion;
    public static final Attributes.Name SPECIFICATION_TITLE = Attributes.Name.SPECIFICATION_TITLE;
    public static final Attributes.Name SPECIFICATION_VERSION = Attributes.Name.SPECIFICATION_VERSION;
    public static final Attributes.Name SPECIFICATION_VENDOR = Attributes.Name.SPECIFICATION_VENDOR;
    public static final Attributes.Name IMPLEMENTATION_TITLE = Attributes.Name.IMPLEMENTATION_TITLE;
    public static final Attributes.Name IMPLEMENTATION_VERSION = Attributes.Name.IMPLEMENTATION_VERSION;
    public static final Attributes.Name IMPLEMENTATION_VENDOR = Attributes.Name.IMPLEMENTATION_VENDOR;
    public static final Compatibility COMPATIBLE = new Compatibility("COMPATIBLE");
    public static final Compatibility REQUIRE_SPECIFICATION_UPGRADE = new Compatibility("REQUIRE_SPECIFICATION_UPGRADE");
    public static final Compatibility REQUIRE_VENDOR_SWITCH = new Compatibility("REQUIRE_VENDOR_SWITCH");
    public static final Compatibility REQUIRE_IMPLEMENTATION_CHANGE = new Compatibility("REQUIRE_IMPLEMENTATION_CHANGE");
    public static final Compatibility INCOMPATIBLE = new Compatibility("INCOMPATIBLE");

    public static Specification[] getSpecifications(Manifest manifest) throws ParseException {
        if (manifest == null) {
            return new Specification[0];
        }
        ArrayList arrayList = new ArrayList();
        Map<String, Attributes> entries = manifest.getEntries();
        for (String str : entries.keySet()) {
            Specification specification = getSpecification(str, entries.get(str));
            if (specification != null) {
                arrayList.add(specification);
            }
        }
        ArrayList removeDuplicates = removeDuplicates(arrayList);
        return (Specification[]) removeDuplicates.toArray(new Specification[removeDuplicates.size()]);
    }

    public Specification(String str, String str2, String str3, String str4, String str5, String str6) {
        this(str, str2, str3, str4, str5, str6, null);
    }

    public Specification(String str, String str2, String str3, String str4, String str5, String str6, String[] strArr) {
        this.specificationTitle = str;
        this.specificationVendor = str3;
        if (str2 != null) {
            try {
                this.specificationVersion = new DeweyDecimal(str2);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Bad specification version format '" + str2 + "' in '" + str + "'. (Reason: " + e + ")");
            }
        }
        this.implementationTitle = str4;
        this.implementationVendor = str6;
        this.implementationVersion = str5;
        if (this.specificationTitle != null) {
            String[] strArr2 = null;
            if (strArr != null) {
                strArr2 = new String[strArr.length];
                System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
            }
            this.sections = strArr2;
            return;
        }
        throw new NullPointerException("specificationTitle");
    }

    public String getSpecificationTitle() {
        return this.specificationTitle;
    }

    public String getSpecificationVendor() {
        return this.specificationVendor;
    }

    public String getImplementationTitle() {
        return this.implementationTitle;
    }

    public DeweyDecimal getSpecificationVersion() {
        return this.specificationVersion;
    }

    public String getImplementationVendor() {
        return this.implementationVendor;
    }

    public String getImplementationVersion() {
        return this.implementationVersion;
    }

    public String[] getSections() {
        String[] strArr = this.sections;
        if (strArr == null) {
            return null;
        }
        String[] strArr2 = new String[strArr.length];
        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
        return strArr2;
    }

    public Compatibility getCompatibilityWith(Specification specification) {
        if (!this.specificationTitle.equals(specification.getSpecificationTitle())) {
            return INCOMPATIBLE;
        }
        DeweyDecimal specificationVersion = specification.getSpecificationVersion();
        DeweyDecimal deweyDecimal = this.specificationVersion;
        if (deweyDecimal != null && (specificationVersion == null || !isCompatible(deweyDecimal, specificationVersion))) {
            return REQUIRE_SPECIFICATION_UPGRADE;
        }
        String implementationVendor = specification.getImplementationVendor();
        String str = this.implementationVendor;
        if (str != null && (implementationVendor == null || !str.equals(implementationVendor))) {
            return REQUIRE_VENDOR_SWITCH;
        }
        String implementationVersion = specification.getImplementationVersion();
        String str2 = this.implementationVersion;
        if (str2 == null || (implementationVersion != null && str2.equals(implementationVersion))) {
            return COMPATIBLE;
        }
        return REQUIRE_IMPLEMENTATION_CHANGE;
    }

    public boolean isCompatibleWith(Specification specification) {
        return COMPATIBLE == getCompatibilityWith(specification);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(SPECIFICATION_TITLE.toString());
        stringBuffer.append(": ");
        stringBuffer.append(this.specificationTitle);
        stringBuffer.append(StringUtils.LINE_SEP);
        if (this.specificationVersion != null) {
            stringBuffer.append(SPECIFICATION_VERSION);
            stringBuffer.append(": ");
            stringBuffer.append(this.specificationVersion);
            stringBuffer.append(StringUtils.LINE_SEP);
        }
        if (this.specificationVendor != null) {
            stringBuffer.append(SPECIFICATION_VENDOR);
            stringBuffer.append(": ");
            stringBuffer.append(this.specificationVendor);
            stringBuffer.append(StringUtils.LINE_SEP);
        }
        if (this.implementationTitle != null) {
            stringBuffer.append(IMPLEMENTATION_TITLE);
            stringBuffer.append(": ");
            stringBuffer.append(this.implementationTitle);
            stringBuffer.append(StringUtils.LINE_SEP);
        }
        if (this.implementationVersion != null) {
            stringBuffer.append(IMPLEMENTATION_VERSION);
            stringBuffer.append(": ");
            stringBuffer.append(this.implementationVersion);
            stringBuffer.append(StringUtils.LINE_SEP);
        }
        if (this.implementationVendor != null) {
            stringBuffer.append(IMPLEMENTATION_VENDOR);
            stringBuffer.append(": ");
            stringBuffer.append(this.implementationVendor);
            stringBuffer.append(StringUtils.LINE_SEP);
        }
        return stringBuffer.toString();
    }

    private boolean isCompatible(DeweyDecimal deweyDecimal, DeweyDecimal deweyDecimal2) {
        return deweyDecimal.isGreaterThanOrEqual(deweyDecimal2);
    }

    private static ArrayList removeDuplicates(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        while (arrayList.size() > 0) {
            Specification specification = (Specification) arrayList.remove(0);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Specification specification2 = (Specification) it.next();
                if (isEqual(specification, specification2)) {
                    String[] sections = specification2.getSections();
                    if (sections != null) {
                        arrayList3.addAll(Arrays.asList(sections));
                    }
                    it.remove();
                }
            }
            arrayList2.add(mergeInSections(specification, arrayList3));
            arrayList3.clear();
        }
        return arrayList2;
    }

    private static boolean isEqual(Specification specification, Specification specification2) {
        return specification.getSpecificationTitle().equals(specification2.getSpecificationTitle()) && specification.getSpecificationVersion().isEqual(specification2.getSpecificationVersion()) && specification.getSpecificationVendor().equals(specification2.getSpecificationVendor()) && specification.getImplementationTitle().equals(specification2.getImplementationTitle()) && specification.getImplementationVersion().equals(specification2.getImplementationVersion()) && specification.getImplementationVendor().equals(specification2.getImplementationVendor());
    }

    private static Specification mergeInSections(Specification specification, ArrayList arrayList) {
        if (arrayList.size() == 0) {
            return specification;
        }
        arrayList.addAll(Arrays.asList(specification.getSections()));
        return new Specification(specification.getSpecificationTitle(), specification.getSpecificationVersion().toString(), specification.getSpecificationVendor(), specification.getImplementationTitle(), specification.getImplementationVersion(), specification.getImplementationVendor(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    private static String getTrimmedString(String str) {
        if (str == null) {
            return null;
        }
        return str.trim();
    }

    private static Specification getSpecification(String str, Attributes attributes) throws ParseException {
        String trimmedString = getTrimmedString(attributes.getValue(SPECIFICATION_TITLE));
        if (trimmedString == null) {
            return null;
        }
        String trimmedString2 = getTrimmedString(attributes.getValue(SPECIFICATION_VENDOR));
        if (trimmedString2 != null) {
            String trimmedString3 = getTrimmedString(attributes.getValue(SPECIFICATION_VERSION));
            if (trimmedString3 != null) {
                String trimmedString4 = getTrimmedString(attributes.getValue(IMPLEMENTATION_TITLE));
                if (trimmedString4 != null) {
                    String trimmedString5 = getTrimmedString(attributes.getValue(IMPLEMENTATION_VERSION));
                    if (trimmedString5 != null) {
                        String trimmedString6 = getTrimmedString(attributes.getValue(IMPLEMENTATION_VENDOR));
                        if (trimmedString6 != null) {
                            return new Specification(trimmedString, trimmedString3, trimmedString2, trimmedString4, trimmedString5, trimmedString6, new String[]{str});
                        }
                        throw new ParseException(MISSING + IMPLEMENTATION_VENDOR, 0);
                    }
                    throw new ParseException(MISSING + IMPLEMENTATION_VERSION, 0);
                }
                throw new ParseException(MISSING + IMPLEMENTATION_TITLE, 0);
            }
            throw new ParseException(MISSING + SPECIFICATION_VERSION, 0);
        }
        throw new ParseException(MISSING + SPECIFICATION_VENDOR, 0);
    }
}
