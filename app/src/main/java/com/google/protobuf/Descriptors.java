package com.google.protobuf;

import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.FieldSet;
import com.google.protobuf.Internal;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.TextFormat;
import com.google.protobuf.WireFormat;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.p105io.FilenameUtils;
import p110z1.Consts;
import p110z1.Typography;

/* loaded from: classes.dex */
public final class Descriptors {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface GenericDescriptor {
        FileDescriptor getFile();

        String getFullName();

        String getName();

        Message toProto();
    }

    /* loaded from: classes.dex */
    public static final class FileDescriptor {
        private final FileDescriptor[] dependencies;
        private final EnumDescriptor[] enumTypes;
        private final FieldDescriptor[] extensions;
        private final Descriptor[] messageTypes;
        private final DescriptorPool pool;
        private DescriptorProtos.FileDescriptorProto proto;
        private final FileDescriptor[] publicDependencies;
        private final ServiceDescriptor[] services;

        /* loaded from: classes.dex */
        public interface InternalDescriptorAssigner {
            ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor);
        }

        public final DescriptorProtos.FileDescriptorProto toProto() {
            return this.proto;
        }

        public final String getName() {
            return this.proto.getName();
        }

        public final String getPackage() {
            return this.proto.getPackage();
        }

        public final DescriptorProtos.FileOptions getOptions() {
            return this.proto.getOptions();
        }

        public final List<Descriptor> getMessageTypes() {
            return Collections.unmodifiableList(Arrays.asList(this.messageTypes));
        }

        public final List<EnumDescriptor> getEnumTypes() {
            return Collections.unmodifiableList(Arrays.asList(this.enumTypes));
        }

        public final List<ServiceDescriptor> getServices() {
            return Collections.unmodifiableList(Arrays.asList(this.services));
        }

        public final List<FieldDescriptor> getExtensions() {
            return Collections.unmodifiableList(Arrays.asList(this.extensions));
        }

        public final List<FileDescriptor> getDependencies() {
            return Collections.unmodifiableList(Arrays.asList(this.dependencies));
        }

        public final List<FileDescriptor> getPublicDependencies() {
            return Collections.unmodifiableList(Arrays.asList(this.publicDependencies));
        }

        public final Descriptor findMessageTypeByName(String str) {
            if (str.indexOf(46) != -1) {
                return null;
            }
            if (getPackage().length() > 0) {
                str = String.valueOf(getPackage()) + FilenameUtils.EXTENSION_SEPARATOR + str;
            }
            GenericDescriptor findSymbol = this.pool.findSymbol(str);
            if (findSymbol == null || !(findSymbol instanceof Descriptor) || findSymbol.getFile() != this) {
                return null;
            }
            return (Descriptor) findSymbol;
        }

        public final EnumDescriptor findEnumTypeByName(String str) {
            if (str.indexOf(46) != -1) {
                return null;
            }
            if (getPackage().length() > 0) {
                str = String.valueOf(getPackage()) + FilenameUtils.EXTENSION_SEPARATOR + str;
            }
            GenericDescriptor findSymbol = this.pool.findSymbol(str);
            if (findSymbol == null || !(findSymbol instanceof EnumDescriptor) || findSymbol.getFile() != this) {
                return null;
            }
            return (EnumDescriptor) findSymbol;
        }

        public final ServiceDescriptor findServiceByName(String str) {
            if (str.indexOf(46) != -1) {
                return null;
            }
            if (getPackage().length() > 0) {
                str = String.valueOf(getPackage()) + FilenameUtils.EXTENSION_SEPARATOR + str;
            }
            GenericDescriptor findSymbol = this.pool.findSymbol(str);
            if (findSymbol == null || !(findSymbol instanceof ServiceDescriptor) || findSymbol.getFile() != this) {
                return null;
            }
            return (ServiceDescriptor) findSymbol;
        }

        public final FieldDescriptor findExtensionByName(String str) {
            if (str.indexOf(46) != -1) {
                return null;
            }
            if (getPackage().length() > 0) {
                str = String.valueOf(getPackage()) + FilenameUtils.EXTENSION_SEPARATOR + str;
            }
            GenericDescriptor findSymbol = this.pool.findSymbol(str);
            if (findSymbol == null || !(findSymbol instanceof FieldDescriptor) || findSymbol.getFile() != this) {
                return null;
            }
            return (FieldDescriptor) findSymbol;
        }

        public static FileDescriptor buildFrom(DescriptorProtos.FileDescriptorProto fileDescriptorProto, FileDescriptor[] fileDescriptorArr) throws DescriptorValidationException {
            FileDescriptor fileDescriptor = new FileDescriptor(fileDescriptorProto, fileDescriptorArr, new DescriptorPool(fileDescriptorArr));
            if (fileDescriptorArr.length == fileDescriptorProto.getDependencyCount()) {
                for (int i = 0; i < fileDescriptorProto.getDependencyCount(); i++) {
                    if (!fileDescriptorArr[i].getName().equals(fileDescriptorProto.getDependency(i))) {
                        throw new DescriptorValidationException(fileDescriptor, "Dependencies passed to FileDescriptor.buildFrom() don't match those listed in the FileDescriptorProto.", (DescriptorValidationException) null);
                    }
                }
                fileDescriptor.crossLink();
                return fileDescriptor;
            }
            throw new DescriptorValidationException(fileDescriptor, "Dependencies passed to FileDescriptor.buildFrom() don't match those listed in the FileDescriptorProto.", (DescriptorValidationException) null);
        }

        public static void internalBuildGeneratedFileFrom(String[] strArr, FileDescriptor[] fileDescriptorArr, InternalDescriptorAssigner internalDescriptorAssigner) {
            DescriptorProtos.FileDescriptorProto parseFrom;
            StringBuilder sb = new StringBuilder();
            for (String str : strArr) {
                sb.append(str);
            }
            try {
                byte[] bytes = sb.toString().getBytes("ISO-8859-1");
                try {
                    try {
                        FileDescriptor buildFrom = buildFrom(DescriptorProtos.FileDescriptorProto.parseFrom(bytes), fileDescriptorArr);
                        ExtensionRegistry assignDescriptors = internalDescriptorAssigner.assignDescriptors(buildFrom);
                        if (assignDescriptors != null) {
                            try {
                                buildFrom.setProto(DescriptorProtos.FileDescriptorProto.parseFrom(bytes, assignDescriptors));
                            } catch (InvalidProtocolBufferException e) {
                                throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e);
                            }
                        }
                    } catch (DescriptorValidationException e2) {
                        throw new IllegalArgumentException("Invalid embedded descriptor for \"" + parseFrom.getName() + "\".", e2);
                    }
                } catch (InvalidProtocolBufferException e3) {
                    throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e3);
                }
            } catch (UnsupportedEncodingException e4) {
                throw new RuntimeException("Standard encoding ISO-8859-1 not supported by JVM.", e4);
            }
        }

        private FileDescriptor(DescriptorProtos.FileDescriptorProto fileDescriptorProto, FileDescriptor[] fileDescriptorArr, DescriptorPool descriptorPool) throws DescriptorValidationException {
            this.pool = descriptorPool;
            this.proto = fileDescriptorProto;
            this.dependencies = (FileDescriptor[]) fileDescriptorArr.clone();
            this.publicDependencies = new FileDescriptor[fileDescriptorProto.getPublicDependencyCount()];
            for (int i = 0; i < fileDescriptorProto.getPublicDependencyCount(); i++) {
                int publicDependency = fileDescriptorProto.getPublicDependency(i);
                if (publicDependency >= 0) {
                    FileDescriptor[] fileDescriptorArr2 = this.dependencies;
                    if (publicDependency < fileDescriptorArr2.length) {
                        this.publicDependencies[i] = fileDescriptorArr2[fileDescriptorProto.getPublicDependency(i)];
                    }
                }
                throw new DescriptorValidationException(this, "Invalid public dependency index.", (DescriptorValidationException) null);
            }
            descriptorPool.addPackage(getPackage(), this);
            this.messageTypes = new Descriptor[fileDescriptorProto.getMessageTypeCount()];
            for (int i2 = 0; i2 < fileDescriptorProto.getMessageTypeCount(); i2++) {
                this.messageTypes[i2] = new Descriptor(fileDescriptorProto.getMessageType(i2), this, null, i2, null);
            }
            this.enumTypes = new EnumDescriptor[fileDescriptorProto.getEnumTypeCount()];
            for (int i3 = 0; i3 < fileDescriptorProto.getEnumTypeCount(); i3++) {
                this.enumTypes[i3] = new EnumDescriptor(fileDescriptorProto.getEnumType(i3), this, null, i3, null);
            }
            this.services = new ServiceDescriptor[fileDescriptorProto.getServiceCount()];
            for (int i4 = 0; i4 < fileDescriptorProto.getServiceCount(); i4++) {
                this.services[i4] = new ServiceDescriptor(fileDescriptorProto.getService(i4), this, i4, null);
            }
            this.extensions = new FieldDescriptor[fileDescriptorProto.getExtensionCount()];
            for (int i5 = 0; i5 < fileDescriptorProto.getExtensionCount(); i5++) {
                this.extensions[i5] = new FieldDescriptor(fileDescriptorProto.getExtension(i5), this, null, i5, true, null);
            }
        }

        private void crossLink() throws DescriptorValidationException {
            for (Descriptor descriptor : this.messageTypes) {
                descriptor.crossLink();
            }
            for (ServiceDescriptor serviceDescriptor : this.services) {
                serviceDescriptor.crossLink();
            }
            for (FieldDescriptor fieldDescriptor : this.extensions) {
                fieldDescriptor.crossLink();
            }
        }

        private void setProto(DescriptorProtos.FileDescriptorProto fileDescriptorProto) {
            this.proto = fileDescriptorProto;
            int i = 0;
            int i2 = 0;
            while (true) {
                Descriptor[] descriptorArr = this.messageTypes;
                if (i2 >= descriptorArr.length) {
                    break;
                }
                descriptorArr[i2].setProto(fileDescriptorProto.getMessageType(i2));
                i2++;
            }
            int i3 = 0;
            while (true) {
                EnumDescriptor[] enumDescriptorArr = this.enumTypes;
                if (i3 >= enumDescriptorArr.length) {
                    break;
                }
                enumDescriptorArr[i3].setProto(fileDescriptorProto.getEnumType(i3));
                i3++;
            }
            int i4 = 0;
            while (true) {
                ServiceDescriptor[] serviceDescriptorArr = this.services;
                if (i4 >= serviceDescriptorArr.length) {
                    break;
                }
                serviceDescriptorArr[i4].setProto(fileDescriptorProto.getService(i4));
                i4++;
            }
            while (true) {
                FieldDescriptor[] fieldDescriptorArr = this.extensions;
                if (i < fieldDescriptorArr.length) {
                    fieldDescriptorArr[i].setProto(fileDescriptorProto.getExtension(i));
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class Descriptor implements GenericDescriptor {
        private final Descriptor containingType;
        private final EnumDescriptor[] enumTypes;
        private final FieldDescriptor[] extensions;
        private final FieldDescriptor[] fields;
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private final Descriptor[] nestedTypes;
        private DescriptorProtos.DescriptorProto proto;

        public final int getIndex() {
            return this.index;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public final DescriptorProtos.DescriptorProto toProto() {
            return this.proto;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public final String getName() {
            return this.proto.getName();
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public final String getFullName() {
            return this.fullName;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public final FileDescriptor getFile() {
            return this.file;
        }

        public final Descriptor getContainingType() {
            return this.containingType;
        }

        public final DescriptorProtos.MessageOptions getOptions() {
            return this.proto.getOptions();
        }

        public final List<FieldDescriptor> getFields() {
            return Collections.unmodifiableList(Arrays.asList(this.fields));
        }

        public final List<FieldDescriptor> getExtensions() {
            return Collections.unmodifiableList(Arrays.asList(this.extensions));
        }

        public final List<Descriptor> getNestedTypes() {
            return Collections.unmodifiableList(Arrays.asList(this.nestedTypes));
        }

        public final List<EnumDescriptor> getEnumTypes() {
            return Collections.unmodifiableList(Arrays.asList(this.enumTypes));
        }

        public final boolean isExtensionNumber(int i) {
            for (DescriptorProtos.DescriptorProto.ExtensionRange extensionRange : this.proto.getExtensionRangeList()) {
                if (extensionRange.getStart() <= i && i < extensionRange.getEnd()) {
                    return true;
                }
            }
            return false;
        }

        public final FieldDescriptor findFieldByName(String str) {
            DescriptorPool descriptorPool = this.file.pool;
            GenericDescriptor findSymbol = descriptorPool.findSymbol(String.valueOf(this.fullName) + FilenameUtils.EXTENSION_SEPARATOR + str);
            if (findSymbol == null || !(findSymbol instanceof FieldDescriptor)) {
                return null;
            }
            return (FieldDescriptor) findSymbol;
        }

        public final FieldDescriptor findFieldByNumber(int i) {
            return (FieldDescriptor) this.file.pool.fieldsByNumber.get(new DescriptorPool.DescriptorIntPair(this, i));
        }

        public final Descriptor findNestedTypeByName(String str) {
            DescriptorPool descriptorPool = this.file.pool;
            GenericDescriptor findSymbol = descriptorPool.findSymbol(String.valueOf(this.fullName) + FilenameUtils.EXTENSION_SEPARATOR + str);
            if (findSymbol == null || !(findSymbol instanceof Descriptor)) {
                return null;
            }
            return (Descriptor) findSymbol;
        }

        public final EnumDescriptor findEnumTypeByName(String str) {
            DescriptorPool descriptorPool = this.file.pool;
            GenericDescriptor findSymbol = descriptorPool.findSymbol(String.valueOf(this.fullName) + FilenameUtils.EXTENSION_SEPARATOR + str);
            if (findSymbol == null || !(findSymbol instanceof EnumDescriptor)) {
                return null;
            }
            return (EnumDescriptor) findSymbol;
        }

        private Descriptor(DescriptorProtos.DescriptorProto descriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i) throws DescriptorValidationException {
            this.index = i;
            this.proto = descriptorProto;
            this.fullName = Descriptors.computeFullName(fileDescriptor, descriptor, descriptorProto.getName());
            this.file = fileDescriptor;
            this.containingType = descriptor;
            this.nestedTypes = new Descriptor[descriptorProto.getNestedTypeCount()];
            for (int i2 = 0; i2 < descriptorProto.getNestedTypeCount(); i2++) {
                this.nestedTypes[i2] = new Descriptor(descriptorProto.getNestedType(i2), fileDescriptor, this, i2);
            }
            this.enumTypes = new EnumDescriptor[descriptorProto.getEnumTypeCount()];
            for (int i3 = 0; i3 < descriptorProto.getEnumTypeCount(); i3++) {
                this.enumTypes[i3] = new EnumDescriptor(descriptorProto.getEnumType(i3), fileDescriptor, this, i3, null);
            }
            this.fields = new FieldDescriptor[descriptorProto.getFieldCount()];
            for (int i4 = 0; i4 < descriptorProto.getFieldCount(); i4++) {
                this.fields[i4] = new FieldDescriptor(descriptorProto.getField(i4), fileDescriptor, this, i4, false, null);
            }
            this.extensions = new FieldDescriptor[descriptorProto.getExtensionCount()];
            for (int i5 = 0; i5 < descriptorProto.getExtensionCount(); i5++) {
                this.extensions[i5] = new FieldDescriptor(descriptorProto.getExtension(i5), fileDescriptor, this, i5, true, null);
            }
            fileDescriptor.pool.addSymbol(this);
        }

        /* synthetic */ Descriptor(DescriptorProtos.DescriptorProto descriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i, Descriptor descriptor2) throws DescriptorValidationException {
            this(descriptorProto, fileDescriptor, descriptor, i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void crossLink() throws DescriptorValidationException {
            for (Descriptor descriptor : this.nestedTypes) {
                descriptor.crossLink();
            }
            for (FieldDescriptor fieldDescriptor : this.fields) {
                fieldDescriptor.crossLink();
            }
            for (FieldDescriptor fieldDescriptor2 : this.extensions) {
                fieldDescriptor2.crossLink();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.DescriptorProto descriptorProto) {
            this.proto = descriptorProto;
            int i = 0;
            int i2 = 0;
            while (true) {
                Descriptor[] descriptorArr = this.nestedTypes;
                if (i2 >= descriptorArr.length) {
                    break;
                }
                descriptorArr[i2].setProto(descriptorProto.getNestedType(i2));
                i2++;
            }
            int i3 = 0;
            while (true) {
                EnumDescriptor[] enumDescriptorArr = this.enumTypes;
                if (i3 >= enumDescriptorArr.length) {
                    break;
                }
                enumDescriptorArr[i3].setProto(descriptorProto.getEnumType(i3));
                i3++;
            }
            int i4 = 0;
            while (true) {
                FieldDescriptor[] fieldDescriptorArr = this.fields;
                if (i4 >= fieldDescriptorArr.length) {
                    break;
                }
                fieldDescriptorArr[i4].setProto(descriptorProto.getField(i4));
                i4++;
            }
            while (true) {
                FieldDescriptor[] fieldDescriptorArr2 = this.extensions;
                if (i < fieldDescriptorArr2.length) {
                    fieldDescriptorArr2[i].setProto(descriptorProto.getExtension(i));
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class FieldDescriptor implements GenericDescriptor, FieldSet.FieldDescriptorLite<FieldDescriptor>, Comparable<FieldDescriptor> {

        /* renamed from: $SWITCH_TABLE$com$google$protobuf$Descriptors$FieldDescriptor$JavaType */
        private static /* synthetic */ int[] f9100xfcc84f65;

        /* renamed from: $SWITCH_TABLE$com$google$protobuf$Descriptors$FieldDescriptor$Type */
        private static /* synthetic */ int[] f9101x792aeea3;
        private static final WireFormat.FieldType[] table = WireFormat.FieldType.values();
        private Descriptor containingType;
        private Object defaultValue;
        private EnumDescriptor enumType;
        private final Descriptor extensionScope;
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private Descriptor messageType;
        private DescriptorProtos.FieldDescriptorProto proto;
        private Type type;

        /* renamed from: $SWITCH_TABLE$com$google$protobuf$Descriptors$FieldDescriptor$JavaType */
        static /* synthetic */ int[] m20272xfcc84f65() {
            int[] iArr = f9100xfcc84f65;
            if (iArr != null) {
                return iArr;
            }
            int[] iArr2 = new int[JavaType.values().length];
            try {
                iArr2[JavaType.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr2[JavaType.BYTE_STRING.ordinal()] = 7;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr2[JavaType.DOUBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[JavaType.ENUM.ordinal()] = 8;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[JavaType.FLOAT.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[JavaType.INT.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[JavaType.LONG.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[JavaType.MESSAGE.ordinal()] = 9;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr2[JavaType.STRING.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            f9100xfcc84f65 = iArr2;
            return iArr2;
        }

        /* renamed from: $SWITCH_TABLE$com$google$protobuf$Descriptors$FieldDescriptor$Type */
        static /* synthetic */ int[] m20271x792aeea3() {
            int[] iArr = f9101x792aeea3;
            if (iArr != null) {
                return iArr;
            }
            int[] iArr2 = new int[Type.values().length];
            try {
                iArr2[Type.BOOL.ordinal()] = 8;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr2[Type.BYTES.ordinal()] = 12;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr2[Type.DOUBLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[Type.ENUM.ordinal()] = 14;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[Type.FIXED32.ordinal()] = 7;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[Type.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[Type.FLOAT.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[Type.GROUP.ordinal()] = 10;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr2[Type.INT32.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr2[Type.INT64.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr2[Type.MESSAGE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr2[Type.SFIXED32.ordinal()] = 15;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr2[Type.SFIXED64.ordinal()] = 16;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr2[Type.SINT32.ordinal()] = 17;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr2[Type.SINT64.ordinal()] = 18;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr2[Type.STRING.ordinal()] = 9;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr2[Type.UINT32.ordinal()] = 13;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr2[Type.UINT64.ordinal()] = 4;
            } catch (NoSuchFieldError unused18) {
            }
            f9101x792aeea3 = iArr2;
            return iArr2;
        }

        public final int getIndex() {
            return this.index;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public final DescriptorProtos.FieldDescriptorProto toProto() {
            return this.proto;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public final String getName() {
            return this.proto.getName();
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public final int getNumber() {
            return this.proto.getNumber();
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public final String getFullName() {
            return this.fullName;
        }

        public final JavaType getJavaType() {
            return this.type.getJavaType();
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public final WireFormat.JavaType getLiteJavaType() {
            return getLiteType().getJavaType();
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public final FileDescriptor getFile() {
            return this.file;
        }

        public final Type getType() {
            return this.type;
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public final WireFormat.FieldType getLiteType() {
            return table[this.type.ordinal()];
        }

        static {
            if (Type.values().length != DescriptorProtos.FieldDescriptorProto.Type.values().length) {
                throw new RuntimeException("descriptor.proto has a new declared type but Desrciptors.java wasn't updated.");
            }
        }

        public final boolean isRequired() {
            return this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_REQUIRED;
        }

        public final boolean isOptional() {
            return this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_OPTIONAL;
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public final boolean isRepeated() {
            return this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_REPEATED;
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public final boolean isPacked() {
            return getOptions().getPacked();
        }

        public final boolean isPackable() {
            return isRepeated() && getLiteType().isPackable();
        }

        public final boolean hasDefaultValue() {
            return this.proto.hasDefaultValue();
        }

        public final Object getDefaultValue() {
            if (getJavaType() != JavaType.MESSAGE) {
                return this.defaultValue;
            }
            throw new UnsupportedOperationException("FieldDescriptor.getDefaultValue() called on an embedded message field.");
        }

        public final DescriptorProtos.FieldOptions getOptions() {
            return this.proto.getOptions();
        }

        public final boolean isExtension() {
            return this.proto.hasExtendee();
        }

        public final Descriptor getContainingType() {
            return this.containingType;
        }

        public final Descriptor getExtensionScope() {
            if (isExtension()) {
                return this.extensionScope;
            }
            throw new UnsupportedOperationException("This field is not an extension.");
        }

        public final Descriptor getMessageType() {
            if (getJavaType() == JavaType.MESSAGE) {
                return this.messageType;
            }
            throw new UnsupportedOperationException("This field is not of message type.");
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public final EnumDescriptor getEnumType() {
            if (getJavaType() == JavaType.ENUM) {
                return this.enumType;
            }
            throw new UnsupportedOperationException("This field is not of enum type.");
        }

        public final int compareTo(FieldDescriptor fieldDescriptor) {
            if (fieldDescriptor.containingType == this.containingType) {
                return getNumber() - fieldDescriptor.getNumber();
            }
            throw new IllegalArgumentException("FieldDescriptors can only be compared to other FieldDescriptors for fields of the same message type.");
        }

        /* loaded from: classes.dex */
        public enum Type {
            DOUBLE(JavaType.DOUBLE),
            FLOAT(JavaType.FLOAT),
            INT64(JavaType.LONG),
            UINT64(JavaType.LONG),
            INT32(JavaType.INT),
            FIXED64(JavaType.LONG),
            FIXED32(JavaType.INT),
            BOOL(JavaType.BOOLEAN),
            STRING(JavaType.STRING),
            GROUP(JavaType.MESSAGE),
            MESSAGE(JavaType.MESSAGE),
            BYTES(JavaType.BYTE_STRING),
            UINT32(JavaType.INT),
            ENUM(JavaType.ENUM),
            SFIXED32(JavaType.INT),
            SFIXED64(JavaType.LONG),
            SINT32(JavaType.INT),
            SINT64(JavaType.LONG);
            
            private JavaType javaType;

            Type(JavaType javaType) {
                this.javaType = javaType;
            }

            public final DescriptorProtos.FieldDescriptorProto.Type toProto() {
                return DescriptorProtos.FieldDescriptorProto.Type.valueOf(ordinal() + 1);
            }

            public final JavaType getJavaType() {
                return this.javaType;
            }

            public static Type valueOf(DescriptorProtos.FieldDescriptorProto.Type type) {
                return values()[type.getNumber() - 1];
            }
        }

        /* loaded from: classes.dex */
        public enum JavaType {
            INT(0),
            LONG(0L),
            FLOAT(Float.valueOf(0.0f)),
            DOUBLE(Double.valueOf(0.0d)),
            BOOLEAN(false),
            STRING(""),
            BYTE_STRING(ByteString.EMPTY),
            ENUM(null),
            MESSAGE(null);
            
            private final Object defaultDefault;

            JavaType(Object obj) {
                this.defaultDefault = obj;
            }
        }

        private FieldDescriptor(DescriptorProtos.FieldDescriptorProto fieldDescriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i, boolean z) throws DescriptorValidationException {
            this.index = i;
            this.proto = fieldDescriptorProto;
            this.fullName = Descriptors.computeFullName(fileDescriptor, descriptor, fieldDescriptorProto.getName());
            this.file = fileDescriptor;
            if (fieldDescriptorProto.hasType()) {
                this.type = Type.valueOf(fieldDescriptorProto.getType());
            }
            if (getNumber() <= 0) {
                throw new DescriptorValidationException((GenericDescriptor) this, "Field numbers must be positive integers.", (DescriptorValidationException) null);
            } else if (!fieldDescriptorProto.getOptions().getPacked() || isPackable()) {
                if (z) {
                    if (fieldDescriptorProto.hasExtendee()) {
                        this.containingType = null;
                        if (descriptor != null) {
                            this.extensionScope = descriptor;
                        } else {
                            this.extensionScope = null;
                        }
                    } else {
                        throw new DescriptorValidationException((GenericDescriptor) this, "FieldDescriptorProto.extendee not set for extension field.", (DescriptorValidationException) null);
                    }
                } else if (!fieldDescriptorProto.hasExtendee()) {
                    this.containingType = descriptor;
                    this.extensionScope = null;
                } else {
                    throw new DescriptorValidationException((GenericDescriptor) this, "FieldDescriptorProto.extendee set for non-extension field.", (DescriptorValidationException) null);
                }
                fileDescriptor.pool.addSymbol(this);
            } else {
                throw new DescriptorValidationException((GenericDescriptor) this, "[packed = true] can only be specified for repeated primitive fields.", (DescriptorValidationException) null);
            }
        }

        /* synthetic */ FieldDescriptor(DescriptorProtos.FieldDescriptorProto fieldDescriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i, boolean z, FieldDescriptor fieldDescriptor) throws DescriptorValidationException {
            this(fieldDescriptorProto, fileDescriptor, descriptor, i, z);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void crossLink() throws DescriptorValidationException {
            if (this.proto.hasExtendee()) {
                GenericDescriptor lookupSymbol = this.file.pool.lookupSymbol(this.proto.getExtendee(), this, DescriptorPool.SearchFilter.TYPES_ONLY);
                if (lookupSymbol instanceof Descriptor) {
                    this.containingType = (Descriptor) lookupSymbol;
                    if (!getContainingType().isExtensionNumber(getNumber())) {
                        throw new DescriptorValidationException((GenericDescriptor) this, "\"" + getContainingType().getFullName() + "\" does not declare " + getNumber() + " as an extension number.", (DescriptorValidationException) null);
                    }
                } else {
                    throw new DescriptorValidationException((GenericDescriptor) this, "\"" + this.proto.getExtendee() + "\" is not a message type.", (DescriptorValidationException) null);
                }
            }
            if (this.proto.hasTypeName()) {
                GenericDescriptor lookupSymbol2 = this.file.pool.lookupSymbol(this.proto.getTypeName(), this, DescriptorPool.SearchFilter.TYPES_ONLY);
                if (!this.proto.hasType()) {
                    if (lookupSymbol2 instanceof Descriptor) {
                        this.type = Type.MESSAGE;
                    } else if (lookupSymbol2 instanceof EnumDescriptor) {
                        this.type = Type.ENUM;
                    } else {
                        throw new DescriptorValidationException((GenericDescriptor) this, "\"" + this.proto.getTypeName() + "\" is not a type.", (DescriptorValidationException) null);
                    }
                }
                if (getJavaType() == JavaType.MESSAGE) {
                    if (lookupSymbol2 instanceof Descriptor) {
                        this.messageType = (Descriptor) lookupSymbol2;
                        if (this.proto.hasDefaultValue()) {
                            throw new DescriptorValidationException((GenericDescriptor) this, "Messages can't have default values.", (DescriptorValidationException) null);
                        }
                    } else {
                        throw new DescriptorValidationException((GenericDescriptor) this, "\"" + this.proto.getTypeName() + "\" is not a message type.", (DescriptorValidationException) null);
                    }
                } else if (getJavaType() != JavaType.ENUM) {
                    throw new DescriptorValidationException((GenericDescriptor) this, "Field with primitive type has type_name.", (DescriptorValidationException) null);
                } else if (lookupSymbol2 instanceof EnumDescriptor) {
                    this.enumType = (EnumDescriptor) lookupSymbol2;
                } else {
                    throw new DescriptorValidationException((GenericDescriptor) this, "\"" + this.proto.getTypeName() + "\" is not an enum type.", (DescriptorValidationException) null);
                }
            } else if (getJavaType() == JavaType.MESSAGE || getJavaType() == JavaType.ENUM) {
                throw new DescriptorValidationException((GenericDescriptor) this, "Field with message or enum type missing type_name.", (DescriptorValidationException) null);
            }
            if (!this.proto.hasDefaultValue()) {
                if (!isRepeated()) {
                    switch (m20272xfcc84f65()[getJavaType().ordinal()]) {
                        case 8:
                            this.defaultValue = this.enumType.getValues().get(0);
                            break;
                        case 9:
                            this.defaultValue = null;
                            break;
                        default:
                            this.defaultValue = getJavaType().defaultDefault;
                            break;
                    }
                } else {
                    this.defaultValue = Collections.emptyList();
                }
            } else if (!isRepeated()) {
                try {
                    switch (m20271x792aeea3()[getType().ordinal()]) {
                        case 1:
                            if (!this.proto.getDefaultValue().equals("inf")) {
                                if (!this.proto.getDefaultValue().equals("-inf")) {
                                    if (!this.proto.getDefaultValue().equals("nan")) {
                                        this.defaultValue = Double.valueOf(this.proto.getDefaultValue());
                                        break;
                                    } else {
                                        this.defaultValue = Double.valueOf(Double.NaN);
                                        break;
                                    }
                                } else {
                                    this.defaultValue = Double.valueOf(Double.NEGATIVE_INFINITY);
                                    break;
                                }
                            } else {
                                this.defaultValue = Double.valueOf(Double.POSITIVE_INFINITY);
                                break;
                            }
                        case 2:
                            if (!this.proto.getDefaultValue().equals("inf")) {
                                if (!this.proto.getDefaultValue().equals("-inf")) {
                                    if (!this.proto.getDefaultValue().equals("nan")) {
                                        this.defaultValue = Float.valueOf(this.proto.getDefaultValue());
                                        break;
                                    } else {
                                        this.defaultValue = Float.valueOf(Float.NaN);
                                        break;
                                    }
                                } else {
                                    this.defaultValue = Float.valueOf(Float.NEGATIVE_INFINITY);
                                    break;
                                }
                            } else {
                                this.defaultValue = Float.valueOf(Float.POSITIVE_INFINITY);
                                break;
                            }
                        case 3:
                        case 16:
                        case 18:
                            this.defaultValue = Long.valueOf(TextFormat.parseInt64(this.proto.getDefaultValue()));
                            break;
                        case 4:
                        case 6:
                            this.defaultValue = Long.valueOf(TextFormat.parseUInt64(this.proto.getDefaultValue()));
                            break;
                        case 5:
                        case 15:
                        case 17:
                            this.defaultValue = Integer.valueOf(TextFormat.parseInt32(this.proto.getDefaultValue()));
                            break;
                        case 7:
                        case 13:
                            this.defaultValue = Integer.valueOf(TextFormat.parseUInt32(this.proto.getDefaultValue()));
                            break;
                        case 8:
                            this.defaultValue = Boolean.valueOf(this.proto.getDefaultValue());
                            break;
                        case 9:
                            this.defaultValue = this.proto.getDefaultValue();
                            break;
                        case 10:
                        case 11:
                            throw new DescriptorValidationException((GenericDescriptor) this, "Message type had default value.", (DescriptorValidationException) null);
                        case 12:
                            try {
                                this.defaultValue = TextFormat.unescapeBytes(this.proto.getDefaultValue());
                                break;
                            } catch (TextFormat.InvalidEscapeSequenceException e) {
                                throw new DescriptorValidationException(this, "Couldn't parse default value: " + e.getMessage(), e, null);
                            }
                        case 14:
                            this.defaultValue = this.enumType.findValueByName(this.proto.getDefaultValue());
                            if (this.defaultValue != null) {
                                break;
                            } else {
                                throw new DescriptorValidationException((GenericDescriptor) this, "Unknown enum default value: \"" + this.proto.getDefaultValue() + Typography.f21049a, (DescriptorValidationException) null);
                            }
                    }
                } catch (NumberFormatException e2) {
                    throw new DescriptorValidationException(this, "Could not parse default value: \"" + this.proto.getDefaultValue() + Typography.f21049a, e2, null);
                }
            } else {
                throw new DescriptorValidationException((GenericDescriptor) this, "Repeated fields cannot have default values.", (DescriptorValidationException) null);
            }
            if (!isExtension()) {
                this.file.pool.addFieldByNumber(this);
            }
            Descriptor descriptor = this.containingType;
            if (descriptor != null && descriptor.getOptions().getMessageSetWireFormat()) {
                if (!isExtension()) {
                    throw new DescriptorValidationException((GenericDescriptor) this, "MessageSets cannot have fields, only extensions.", (DescriptorValidationException) null);
                } else if (!isOptional() || getType() != Type.MESSAGE) {
                    throw new DescriptorValidationException((GenericDescriptor) this, "Extensions of MessageSets must be optional messages.", (DescriptorValidationException) null);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.FieldDescriptorProto fieldDescriptorProto) {
            this.proto = fieldDescriptorProto;
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public final MessageLite.Builder internalMergeFrom(MessageLite.Builder builder, MessageLite messageLite) {
            return ((Message.Builder) builder).mergeFrom((Message) messageLite);
        }
    }

    /* loaded from: classes.dex */
    public static final class EnumDescriptor implements GenericDescriptor, Internal.EnumLiteMap<EnumValueDescriptor> {
        private final Descriptor containingType;
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private DescriptorProtos.EnumDescriptorProto proto;
        private EnumValueDescriptor[] values;

        public final int getIndex() {
            return this.index;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public final DescriptorProtos.EnumDescriptorProto toProto() {
            return this.proto;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public final String getName() {
            return this.proto.getName();
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public final String getFullName() {
            return this.fullName;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public final FileDescriptor getFile() {
            return this.file;
        }

        public final Descriptor getContainingType() {
            return this.containingType;
        }

        public final DescriptorProtos.EnumOptions getOptions() {
            return this.proto.getOptions();
        }

        public final List<EnumValueDescriptor> getValues() {
            return Collections.unmodifiableList(Arrays.asList(this.values));
        }

        public final EnumValueDescriptor findValueByName(String str) {
            DescriptorPool descriptorPool = this.file.pool;
            GenericDescriptor findSymbol = descriptorPool.findSymbol(String.valueOf(this.fullName) + FilenameUtils.EXTENSION_SEPARATOR + str);
            if (findSymbol == null || !(findSymbol instanceof EnumValueDescriptor)) {
                return null;
            }
            return (EnumValueDescriptor) findSymbol;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.protobuf.Internal.EnumLiteMap
        public final EnumValueDescriptor findValueByNumber(int i) {
            return (EnumValueDescriptor) this.file.pool.enumValuesByNumber.get(new DescriptorPool.DescriptorIntPair(this, i));
        }

        private EnumDescriptor(DescriptorProtos.EnumDescriptorProto enumDescriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i) throws DescriptorValidationException {
            this.index = i;
            this.proto = enumDescriptorProto;
            this.fullName = Descriptors.computeFullName(fileDescriptor, descriptor, enumDescriptorProto.getName());
            this.file = fileDescriptor;
            this.containingType = descriptor;
            if (enumDescriptorProto.getValueCount() != 0) {
                this.values = new EnumValueDescriptor[enumDescriptorProto.getValueCount()];
                for (int i2 = 0; i2 < enumDescriptorProto.getValueCount(); i2++) {
                    this.values[i2] = new EnumValueDescriptor(enumDescriptorProto.getValue(i2), fileDescriptor, this, i2, null);
                }
                fileDescriptor.pool.addSymbol(this);
                return;
            }
            throw new DescriptorValidationException((GenericDescriptor) this, "Enums must contain at least one value.", (DescriptorValidationException) null);
        }

        /* synthetic */ EnumDescriptor(DescriptorProtos.EnumDescriptorProto enumDescriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i, EnumDescriptor enumDescriptor) throws DescriptorValidationException {
            this(enumDescriptorProto, fileDescriptor, descriptor, i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.EnumDescriptorProto enumDescriptorProto) {
            this.proto = enumDescriptorProto;
            int i = 0;
            while (true) {
                EnumValueDescriptor[] enumValueDescriptorArr = this.values;
                if (i < enumValueDescriptorArr.length) {
                    enumValueDescriptorArr[i].setProto(enumDescriptorProto.getValue(i));
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class EnumValueDescriptor implements GenericDescriptor, Internal.EnumLite {
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private DescriptorProtos.EnumValueDescriptorProto proto;
        private final EnumDescriptor type;

        public final int getIndex() {
            return this.index;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public final DescriptorProtos.EnumValueDescriptorProto toProto() {
            return this.proto;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public final String getName() {
            return this.proto.getName();
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.proto.getNumber();
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public final String getFullName() {
            return this.fullName;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public final FileDescriptor getFile() {
            return this.file;
        }

        public final EnumDescriptor getType() {
            return this.type;
        }

        public final DescriptorProtos.EnumValueOptions getOptions() {
            return this.proto.getOptions();
        }

        private EnumValueDescriptor(DescriptorProtos.EnumValueDescriptorProto enumValueDescriptorProto, FileDescriptor fileDescriptor, EnumDescriptor enumDescriptor, int i) throws DescriptorValidationException {
            this.index = i;
            this.proto = enumValueDescriptorProto;
            this.file = fileDescriptor;
            this.type = enumDescriptor;
            this.fullName = String.valueOf(enumDescriptor.getFullName()) + FilenameUtils.EXTENSION_SEPARATOR + enumValueDescriptorProto.getName();
            fileDescriptor.pool.addSymbol(this);
            fileDescriptor.pool.addEnumValueByNumber(this);
        }

        /* synthetic */ EnumValueDescriptor(DescriptorProtos.EnumValueDescriptorProto enumValueDescriptorProto, FileDescriptor fileDescriptor, EnumDescriptor enumDescriptor, int i, EnumValueDescriptor enumValueDescriptor) throws DescriptorValidationException {
            this(enumValueDescriptorProto, fileDescriptor, enumDescriptor, i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.EnumValueDescriptorProto enumValueDescriptorProto) {
            this.proto = enumValueDescriptorProto;
        }
    }

    /* loaded from: classes.dex */
    public static final class ServiceDescriptor implements GenericDescriptor {
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private MethodDescriptor[] methods;
        private DescriptorProtos.ServiceDescriptorProto proto;

        public final int getIndex() {
            return this.index;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public final DescriptorProtos.ServiceDescriptorProto toProto() {
            return this.proto;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public final String getName() {
            return this.proto.getName();
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public final String getFullName() {
            return this.fullName;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public final FileDescriptor getFile() {
            return this.file;
        }

        public final DescriptorProtos.ServiceOptions getOptions() {
            return this.proto.getOptions();
        }

        public final List<MethodDescriptor> getMethods() {
            return Collections.unmodifiableList(Arrays.asList(this.methods));
        }

        public final MethodDescriptor findMethodByName(String str) {
            DescriptorPool descriptorPool = this.file.pool;
            GenericDescriptor findSymbol = descriptorPool.findSymbol(String.valueOf(this.fullName) + FilenameUtils.EXTENSION_SEPARATOR + str);
            if (findSymbol == null || !(findSymbol instanceof MethodDescriptor)) {
                return null;
            }
            return (MethodDescriptor) findSymbol;
        }

        private ServiceDescriptor(DescriptorProtos.ServiceDescriptorProto serviceDescriptorProto, FileDescriptor fileDescriptor, int i) throws DescriptorValidationException {
            this.index = i;
            this.proto = serviceDescriptorProto;
            this.fullName = Descriptors.computeFullName(fileDescriptor, null, serviceDescriptorProto.getName());
            this.file = fileDescriptor;
            this.methods = new MethodDescriptor[serviceDescriptorProto.getMethodCount()];
            for (int i2 = 0; i2 < serviceDescriptorProto.getMethodCount(); i2++) {
                this.methods[i2] = new MethodDescriptor(serviceDescriptorProto.getMethod(i2), fileDescriptor, this, i2, null);
            }
            fileDescriptor.pool.addSymbol(this);
        }

        /* synthetic */ ServiceDescriptor(DescriptorProtos.ServiceDescriptorProto serviceDescriptorProto, FileDescriptor fileDescriptor, int i, ServiceDescriptor serviceDescriptor) throws DescriptorValidationException {
            this(serviceDescriptorProto, fileDescriptor, i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void crossLink() throws DescriptorValidationException {
            for (MethodDescriptor methodDescriptor : this.methods) {
                methodDescriptor.crossLink();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.ServiceDescriptorProto serviceDescriptorProto) {
            this.proto = serviceDescriptorProto;
            int i = 0;
            while (true) {
                MethodDescriptor[] methodDescriptorArr = this.methods;
                if (i < methodDescriptorArr.length) {
                    methodDescriptorArr[i].setProto(serviceDescriptorProto.getMethod(i));
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class MethodDescriptor implements GenericDescriptor {
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private Descriptor inputType;
        private Descriptor outputType;
        private DescriptorProtos.MethodDescriptorProto proto;
        private final ServiceDescriptor service;

        public final int getIndex() {
            return this.index;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public final DescriptorProtos.MethodDescriptorProto toProto() {
            return this.proto;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public final String getName() {
            return this.proto.getName();
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public final String getFullName() {
            return this.fullName;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public final FileDescriptor getFile() {
            return this.file;
        }

        public final ServiceDescriptor getService() {
            return this.service;
        }

        public final Descriptor getInputType() {
            return this.inputType;
        }

        public final Descriptor getOutputType() {
            return this.outputType;
        }

        public final DescriptorProtos.MethodOptions getOptions() {
            return this.proto.getOptions();
        }

        private MethodDescriptor(DescriptorProtos.MethodDescriptorProto methodDescriptorProto, FileDescriptor fileDescriptor, ServiceDescriptor serviceDescriptor, int i) throws DescriptorValidationException {
            this.index = i;
            this.proto = methodDescriptorProto;
            this.file = fileDescriptor;
            this.service = serviceDescriptor;
            this.fullName = String.valueOf(serviceDescriptor.getFullName()) + FilenameUtils.EXTENSION_SEPARATOR + methodDescriptorProto.getName();
            fileDescriptor.pool.addSymbol(this);
        }

        /* synthetic */ MethodDescriptor(DescriptorProtos.MethodDescriptorProto methodDescriptorProto, FileDescriptor fileDescriptor, ServiceDescriptor serviceDescriptor, int i, MethodDescriptor methodDescriptor) throws DescriptorValidationException {
            this(methodDescriptorProto, fileDescriptor, serviceDescriptor, i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void crossLink() throws DescriptorValidationException {
            GenericDescriptor lookupSymbol = this.file.pool.lookupSymbol(this.proto.getInputType(), this, DescriptorPool.SearchFilter.TYPES_ONLY);
            if (lookupSymbol instanceof Descriptor) {
                this.inputType = (Descriptor) lookupSymbol;
                GenericDescriptor lookupSymbol2 = this.file.pool.lookupSymbol(this.proto.getOutputType(), this, DescriptorPool.SearchFilter.TYPES_ONLY);
                if (lookupSymbol2 instanceof Descriptor) {
                    this.outputType = (Descriptor) lookupSymbol2;
                    return;
                }
                throw new DescriptorValidationException((GenericDescriptor) this, "\"" + this.proto.getOutputType() + "\" is not a message type.", (DescriptorValidationException) null);
            }
            throw new DescriptorValidationException((GenericDescriptor) this, "\"" + this.proto.getInputType() + "\" is not a message type.", (DescriptorValidationException) null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.MethodDescriptorProto methodDescriptorProto) {
            this.proto = methodDescriptorProto;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String computeFullName(FileDescriptor fileDescriptor, Descriptor descriptor, String str) {
        if (descriptor != null) {
            return String.valueOf(descriptor.getFullName()) + FilenameUtils.EXTENSION_SEPARATOR + str;
        } else if (fileDescriptor.getPackage().length() <= 0) {
            return str;
        } else {
            return String.valueOf(fileDescriptor.getPackage()) + FilenameUtils.EXTENSION_SEPARATOR + str;
        }
    }

    /* loaded from: classes.dex */
    public static class DescriptorValidationException extends Exception {
        private static final long serialVersionUID = 5750205775490483148L;
        private final String description;
        private final String name;
        private final Message proto;

        public String getProblemSymbolName() {
            return this.name;
        }

        public Message getProblemProto() {
            return this.proto;
        }

        public String getDescription() {
            return this.description;
        }

        /* synthetic */ DescriptorValidationException(GenericDescriptor genericDescriptor, String str, DescriptorValidationException descriptorValidationException) {
            this(genericDescriptor, str);
        }

        private DescriptorValidationException(GenericDescriptor genericDescriptor, String str) {
            super(String.valueOf(genericDescriptor.getFullName()) + ": " + str);
            this.name = genericDescriptor.getFullName();
            this.proto = genericDescriptor.toProto();
            this.description = str;
        }

        /* synthetic */ DescriptorValidationException(GenericDescriptor genericDescriptor, String str, Throwable th, DescriptorValidationException descriptorValidationException) {
            this(genericDescriptor, str, th);
        }

        private DescriptorValidationException(GenericDescriptor genericDescriptor, String str, Throwable th) {
            this(genericDescriptor, str);
            initCause(th);
        }

        /* synthetic */ DescriptorValidationException(FileDescriptor fileDescriptor, String str, DescriptorValidationException descriptorValidationException) {
            this(fileDescriptor, str);
        }

        private DescriptorValidationException(FileDescriptor fileDescriptor, String str) {
            super(String.valueOf(fileDescriptor.getName()) + ": " + str);
            this.name = fileDescriptor.getName();
            this.proto = fileDescriptor.toProto();
            this.description = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class DescriptorPool {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final Map<String, GenericDescriptor> descriptorsByName = new HashMap();
        private final Map<DescriptorIntPair, FieldDescriptor> fieldsByNumber = new HashMap();
        private final Map<DescriptorIntPair, EnumValueDescriptor> enumValuesByNumber = new HashMap();
        private final Set<FileDescriptor> dependencies = new HashSet();

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public enum SearchFilter {
            TYPES_ONLY,
            AGGREGATES_ONLY,
            ALL_SYMBOLS
        }

        DescriptorPool(FileDescriptor[] fileDescriptorArr) {
            for (int i = 0; i < fileDescriptorArr.length; i++) {
                this.dependencies.add(fileDescriptorArr[i]);
                importPublicDependencies(fileDescriptorArr[i]);
            }
            for (FileDescriptor fileDescriptor : this.dependencies) {
                try {
                    addPackage(fileDescriptor.getPackage(), fileDescriptor);
                } catch (DescriptorValidationException unused) {
                }
            }
        }

        private void importPublicDependencies(FileDescriptor fileDescriptor) {
            for (FileDescriptor fileDescriptor2 : fileDescriptor.getPublicDependencies()) {
                if (this.dependencies.add(fileDescriptor2)) {
                    importPublicDependencies(fileDescriptor2);
                }
            }
        }

        final GenericDescriptor findSymbol(String str) {
            return findSymbol(str, SearchFilter.ALL_SYMBOLS);
        }

        final GenericDescriptor findSymbol(String str, SearchFilter searchFilter) {
            GenericDescriptor genericDescriptor = this.descriptorsByName.get(str);
            if (genericDescriptor != null && (searchFilter == SearchFilter.ALL_SYMBOLS || ((searchFilter == SearchFilter.TYPES_ONLY && isType(genericDescriptor)) || (searchFilter == SearchFilter.AGGREGATES_ONLY && isAggregate(genericDescriptor))))) {
                return genericDescriptor;
            }
            for (FileDescriptor fileDescriptor : this.dependencies) {
                GenericDescriptor genericDescriptor2 = fileDescriptor.pool.descriptorsByName.get(str);
                if (genericDescriptor2 != null && (searchFilter == SearchFilter.ALL_SYMBOLS || ((searchFilter == SearchFilter.TYPES_ONLY && isType(genericDescriptor2)) || (searchFilter == SearchFilter.AGGREGATES_ONLY && isAggregate(genericDescriptor2))))) {
                    return genericDescriptor2;
                }
            }
            return null;
        }

        final boolean isType(GenericDescriptor genericDescriptor) {
            return (genericDescriptor instanceof Descriptor) || (genericDescriptor instanceof EnumDescriptor);
        }

        final boolean isAggregate(GenericDescriptor genericDescriptor) {
            return (genericDescriptor instanceof Descriptor) || (genericDescriptor instanceof EnumDescriptor) || (genericDescriptor instanceof PackageDescriptor) || (genericDescriptor instanceof ServiceDescriptor);
        }

        final GenericDescriptor lookupSymbol(String str, GenericDescriptor genericDescriptor, SearchFilter searchFilter) throws DescriptorValidationException {
            GenericDescriptor genericDescriptor2;
            if (str.startsWith(Consts.f23430h)) {
                genericDescriptor2 = findSymbol(str.substring(1), searchFilter);
            } else {
                int indexOf = str.indexOf(46);
                String substring = indexOf == -1 ? str : str.substring(0, indexOf);
                StringBuilder sb = new StringBuilder(genericDescriptor.getFullName());
                while (true) {
                    int lastIndexOf = sb.lastIndexOf(Consts.f23430h);
                    if (lastIndexOf == -1) {
                        genericDescriptor2 = findSymbol(str, searchFilter);
                        break;
                    }
                    int i = lastIndexOf + 1;
                    sb.setLength(i);
                    sb.append(substring);
                    GenericDescriptor findSymbol = findSymbol(sb.toString(), SearchFilter.AGGREGATES_ONLY);
                    if (findSymbol == null) {
                        sb.setLength(lastIndexOf);
                    } else if (indexOf != -1) {
                        sb.setLength(i);
                        sb.append(str);
                        genericDescriptor2 = findSymbol(sb.toString(), searchFilter);
                    } else {
                        genericDescriptor2 = findSymbol;
                    }
                }
            }
            if (genericDescriptor2 != null) {
                return genericDescriptor2;
            }
            throw new DescriptorValidationException(genericDescriptor, "\"" + str + "\" is not defined.", (DescriptorValidationException) null);
        }

        final void addSymbol(GenericDescriptor genericDescriptor) throws DescriptorValidationException {
            validateSymbolName(genericDescriptor);
            String fullName = genericDescriptor.getFullName();
            int lastIndexOf = fullName.lastIndexOf(46);
            GenericDescriptor put = this.descriptorsByName.put(fullName, genericDescriptor);
            if (put != null) {
                this.descriptorsByName.put(fullName, put);
                if (genericDescriptor.getFile() != put.getFile()) {
                    throw new DescriptorValidationException(genericDescriptor, "\"" + fullName + "\" is already defined in file \"" + put.getFile().getName() + "\".", (DescriptorValidationException) null);
                } else if (lastIndexOf == -1) {
                    throw new DescriptorValidationException(genericDescriptor, "\"" + fullName + "\" is already defined.", (DescriptorValidationException) null);
                } else {
                    throw new DescriptorValidationException(genericDescriptor, "\"" + fullName.substring(lastIndexOf + 1) + "\" is already defined in \"" + fullName.substring(0, lastIndexOf) + "\".", (DescriptorValidationException) null);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static final class PackageDescriptor implements GenericDescriptor {
            private final FileDescriptor file;
            private final String fullName;
            private final String name;

            @Override // com.google.protobuf.Descriptors.GenericDescriptor
            public final Message toProto() {
                return this.file.toProto();
            }

            @Override // com.google.protobuf.Descriptors.GenericDescriptor
            public final String getName() {
                return this.name;
            }

            @Override // com.google.protobuf.Descriptors.GenericDescriptor
            public final String getFullName() {
                return this.fullName;
            }

            @Override // com.google.protobuf.Descriptors.GenericDescriptor
            public final FileDescriptor getFile() {
                return this.file;
            }

            PackageDescriptor(String str, String str2, FileDescriptor fileDescriptor) {
                this.file = fileDescriptor;
                this.fullName = str2;
                this.name = str;
            }
        }

        final void addPackage(String str, FileDescriptor fileDescriptor) throws DescriptorValidationException {
            String str2;
            int lastIndexOf = str.lastIndexOf(46);
            if (lastIndexOf == -1) {
                str2 = str;
            } else {
                addPackage(str.substring(0, lastIndexOf), fileDescriptor);
                str2 = str.substring(lastIndexOf + 1);
            }
            GenericDescriptor put = this.descriptorsByName.put(str, new PackageDescriptor(str2, str, fileDescriptor));
            if (put != null) {
                this.descriptorsByName.put(str, put);
                if (!(put instanceof PackageDescriptor)) {
                    throw new DescriptorValidationException(fileDescriptor, "\"" + str2 + "\" is already defined (as something other than a package) in file \"" + put.getFile().getName() + "\".", (DescriptorValidationException) null);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static final class DescriptorIntPair {
            private final GenericDescriptor descriptor;
            private final int number;

            DescriptorIntPair(GenericDescriptor genericDescriptor, int i) {
                this.descriptor = genericDescriptor;
                this.number = i;
            }

            public final int hashCode() {
                return (this.descriptor.hashCode() * 65535) + this.number;
            }

            public final boolean equals(Object obj) {
                if (!(obj instanceof DescriptorIntPair)) {
                    return false;
                }
                DescriptorIntPair descriptorIntPair = (DescriptorIntPair) obj;
                return this.descriptor == descriptorIntPair.descriptor && this.number == descriptorIntPair.number;
            }
        }

        final void addFieldByNumber(FieldDescriptor fieldDescriptor) throws DescriptorValidationException {
            DescriptorIntPair descriptorIntPair = new DescriptorIntPair(fieldDescriptor.getContainingType(), fieldDescriptor.getNumber());
            FieldDescriptor put = this.fieldsByNumber.put(descriptorIntPair, fieldDescriptor);
            if (put != null) {
                this.fieldsByNumber.put(descriptorIntPair, put);
                throw new DescriptorValidationException((GenericDescriptor) fieldDescriptor, "Field number " + fieldDescriptor.getNumber() + "has already been used in \"" + fieldDescriptor.getContainingType().getFullName() + "\" by field \"" + put.getName() + "\".", (DescriptorValidationException) null);
            }
        }

        final void addEnumValueByNumber(EnumValueDescriptor enumValueDescriptor) {
            DescriptorIntPair descriptorIntPair = new DescriptorIntPair(enumValueDescriptor.getType(), enumValueDescriptor.getNumber());
            EnumValueDescriptor put = this.enumValuesByNumber.put(descriptorIntPair, enumValueDescriptor);
            if (put != null) {
                this.enumValuesByNumber.put(descriptorIntPair, put);
            }
        }

        static void validateSymbolName(GenericDescriptor genericDescriptor) throws DescriptorValidationException {
            String name = genericDescriptor.getName();
            if (name.length() != 0) {
                boolean z = true;
                for (int i = 0; i < name.length(); i++) {
                    char charAt = name.charAt(i);
                    if (charAt >= 128) {
                        z = false;
                    }
                    if (!Character.isLetter(charAt) && charAt != '_' && (!Character.isDigit(charAt) || i <= 0)) {
                        z = false;
                    }
                }
                if (!z) {
                    throw new DescriptorValidationException(genericDescriptor, "\"" + name + "\" is not a valid identifier.", (DescriptorValidationException) null);
                }
                return;
            }
            throw new DescriptorValidationException(genericDescriptor, "Missing name.", (DescriptorValidationException) null);
        }
    }
}
