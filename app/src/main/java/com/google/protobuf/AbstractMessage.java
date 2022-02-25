package com.google.protobuf;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Internal;
import com.google.protobuf.Message;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.p105io.FilenameUtils;

/* loaded from: classes.dex */
public abstract class AbstractMessage extends AbstractMessageLite implements Message {
    private int memoizedSize = -1;

    protected static int hashBoolean(boolean z) {
        return z ? 1231 : 1237;
    }

    protected static int hashLong(long j) {
        return (int) (j ^ (j >>> 32));
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder
    public boolean isInitialized() {
        for (Descriptors.FieldDescriptor fieldDescriptor : getDescriptorForType().getFields()) {
            if (fieldDescriptor.isRequired() && !hasField(fieldDescriptor)) {
                return false;
            }
        }
        for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : getAllFields().entrySet()) {
            Descriptors.FieldDescriptor key = entry.getKey();
            if (key.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                if (key.isRepeated()) {
                    for (Message message : (List) entry.getValue()) {
                        if (!message.isInitialized()) {
                            return false;
                        }
                    }
                    continue;
                } else if (!((Message) entry.getValue()).isInitialized()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public List<String> findInitializationErrors() {
        return Builder.findMissingFields(this);
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public String getInitializationErrorString() {
        return delimitWithCommas(findInitializationErrors());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String delimitWithCommas(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(str);
        }
        return sb.toString();
    }

    @Override // com.google.protobuf.Message
    public final String toString() {
        return TextFormat.printToString(this);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        boolean messageSetWireFormat = getDescriptorForType().getOptions().getMessageSetWireFormat();
        for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : getAllFields().entrySet()) {
            Descriptors.FieldDescriptor key = entry.getKey();
            Object value = entry.getValue();
            if (!messageSetWireFormat || !key.isExtension() || key.getType() != Descriptors.FieldDescriptor.Type.MESSAGE || key.isRepeated()) {
                FieldSet.writeField(key, value, codedOutputStream);
            } else {
                codedOutputStream.writeMessageSetExtension(key.getNumber(), (Message) value);
            }
        }
        UnknownFieldSet unknownFields = getUnknownFields();
        if (messageSetWireFormat) {
            unknownFields.writeAsMessageSetTo(codedOutputStream);
        } else {
            unknownFields.writeTo(codedOutputStream);
        }
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i;
        int i2 = this.memoizedSize;
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        boolean messageSetWireFormat = getDescriptorForType().getOptions().getMessageSetWireFormat();
        for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : getAllFields().entrySet()) {
            Descriptors.FieldDescriptor key = entry.getKey();
            Object value = entry.getValue();
            if (!messageSetWireFormat || !key.isExtension() || key.getType() != Descriptors.FieldDescriptor.Type.MESSAGE || key.isRepeated()) {
                i3 += FieldSet.computeFieldSize(key, value);
            } else {
                i3 += CodedOutputStream.computeMessageSetExtensionSize(key.getNumber(), (Message) value);
            }
        }
        UnknownFieldSet unknownFields = getUnknownFields();
        if (messageSetWireFormat) {
            i = i3 + unknownFields.getSerializedSizeAsMessageSet();
        } else {
            i = i3 + unknownFields.getSerializedSize();
        }
        this.memoizedSize = i;
        return i;
    }

    @Override // com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Message)) {
            return false;
        }
        Message message = (Message) obj;
        return getDescriptorForType() == message.getDescriptorForType() && getAllFields().equals(message.getAllFields()) && getUnknownFields().equals(message.getUnknownFields());
    }

    @Override // com.google.protobuf.Message
    public int hashCode() {
        return (hashFields(getDescriptorForType().hashCode() + 779, getAllFields()) * 29) + getUnknownFields().hashCode();
    }

    protected int hashFields(int i, Map<Descriptors.FieldDescriptor, Object> map) {
        for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : map.entrySet()) {
            Descriptors.FieldDescriptor key = entry.getKey();
            Object value = entry.getValue();
            int number = (i * 37) + key.getNumber();
            if (key.getType() != Descriptors.FieldDescriptor.Type.ENUM) {
                i = (number * 53) + value.hashCode();
            } else if (key.isRepeated()) {
                i = (number * 53) + hashEnumList((List) value);
            } else {
                i = (number * 53) + hashEnum((Internal.EnumLite) value);
            }
        }
        return i;
    }

    @Override // com.google.protobuf.AbstractMessageLite
    UninitializedMessageException newUninitializedMessageException() {
        return Builder.newUninitializedMessageException((Message) this);
    }

    protected static int hashEnum(Internal.EnumLite enumLite) {
        return enumLite.getNumber();
    }

    protected static int hashEnumList(List<? extends Internal.EnumLite> list) {
        int i = 1;
        for (Internal.EnumLite enumLite : list) {
            i = (i * 31) + hashEnum(enumLite);
        }
        return i;
    }

    /* loaded from: classes.dex */
    public static abstract class Builder<BuilderType extends Builder> extends AbstractMessageLite.Builder<BuilderType> implements Message.Builder {

        /* renamed from: $SWITCH_TABLE$com$google$protobuf$Descriptors$FieldDescriptor$Type */
        private static /* synthetic */ int[] f9078x792aeea3;

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public abstract BuilderType clone();

        /* renamed from: $SWITCH_TABLE$com$google$protobuf$Descriptors$FieldDescriptor$Type */
        static /* synthetic */ int[] m20273x792aeea3() {
            int[] iArr = f9078x792aeea3;
            if (iArr != null) {
                return iArr;
            }
            int[] iArr2 = new int[Descriptors.FieldDescriptor.Type.values().length];
            try {
                iArr2[Descriptors.FieldDescriptor.Type.BOOL.ordinal()] = 8;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr2[Descriptors.FieldDescriptor.Type.BYTES.ordinal()] = 12;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr2[Descriptors.FieldDescriptor.Type.DOUBLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[Descriptors.FieldDescriptor.Type.ENUM.ordinal()] = 14;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[Descriptors.FieldDescriptor.Type.FIXED32.ordinal()] = 7;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[Descriptors.FieldDescriptor.Type.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[Descriptors.FieldDescriptor.Type.FLOAT.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[Descriptors.FieldDescriptor.Type.GROUP.ordinal()] = 10;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr2[Descriptors.FieldDescriptor.Type.INT32.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr2[Descriptors.FieldDescriptor.Type.INT64.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr2[Descriptors.FieldDescriptor.Type.MESSAGE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr2[Descriptors.FieldDescriptor.Type.SFIXED32.ordinal()] = 15;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr2[Descriptors.FieldDescriptor.Type.SFIXED64.ordinal()] = 16;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr2[Descriptors.FieldDescriptor.Type.SINT32.ordinal()] = 17;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr2[Descriptors.FieldDescriptor.Type.SINT64.ordinal()] = 18;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr2[Descriptors.FieldDescriptor.Type.STRING.ordinal()] = 9;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr2[Descriptors.FieldDescriptor.Type.UINT32.ordinal()] = 13;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr2[Descriptors.FieldDescriptor.Type.UINT64.ordinal()] = 4;
            } catch (NoSuchFieldError unused18) {
            }
            f9078x792aeea3 = iArr2;
            return iArr2;
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType clear() {
            for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : getAllFields().entrySet()) {
                clearField(entry.getKey());
            }
            return this;
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public List<String> findInitializationErrors() {
            return findMissingFields(this);
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public String getInitializationErrorString() {
            return AbstractMessage.delimitWithCommas(findInitializationErrors());
        }

        public BuilderType mergeFrom(Message message) {
            if (message.getDescriptorForType() == getDescriptorForType()) {
                for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : message.getAllFields().entrySet()) {
                    Descriptors.FieldDescriptor key = entry.getKey();
                    if (key.isRepeated()) {
                        for (Object obj : (List) entry.getValue()) {
                            addRepeatedField(key, obj);
                        }
                    } else if (key.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                        Message message2 = (Message) getField(key);
                        if (message2 == message2.getDefaultInstanceForType()) {
                            setField(key, entry.getValue());
                        } else {
                            setField(key, message2.newBuilderForType().mergeFrom(message2).mergeFrom((Message) entry.getValue()).build());
                        }
                    } else {
                        setField(key, entry.getValue());
                    }
                }
                mergeUnknownFields(message.getUnknownFields());
                return this;
            }
            throw new IllegalArgumentException("mergeFrom(Message) can only merge messages of the same type.");
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(CodedInputStream codedInputStream) throws IOException {
            return mergeFrom(codedInputStream, (ExtensionRegistryLite) ExtensionRegistry.getEmptyRegistry());
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readTag;
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
            do {
                readTag = codedInputStream.readTag();
                if (readTag == 0) {
                    break;
                }
            } while (mergeFieldFrom(codedInputStream, newBuilder, extensionRegistryLite, getDescriptorForType(), this, null, readTag));
            setUnknownFields(newBuilder.build());
            return this;
        }

        private static void addRepeatedField(Message.Builder builder, FieldSet<Descriptors.FieldDescriptor> fieldSet, Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            if (builder != null) {
                builder.addRepeatedField(fieldDescriptor, obj);
            } else {
                fieldSet.addRepeatedField(fieldDescriptor, obj);
            }
        }

        private static void setField(Message.Builder builder, FieldSet<Descriptors.FieldDescriptor> fieldSet, Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            if (builder != null) {
                builder.setField(fieldDescriptor, obj);
            } else {
                fieldSet.setField(fieldDescriptor, obj);
            }
        }

        private static boolean hasOriginalMessage(Message.Builder builder, FieldSet<Descriptors.FieldDescriptor> fieldSet, Descriptors.FieldDescriptor fieldDescriptor) {
            if (builder != null) {
                return builder.hasField(fieldDescriptor);
            }
            return fieldSet.hasField(fieldDescriptor);
        }

        private static Message getOriginalMessage(Message.Builder builder, FieldSet<Descriptors.FieldDescriptor> fieldSet, Descriptors.FieldDescriptor fieldDescriptor) {
            if (builder != null) {
                return (Message) builder.getField(fieldDescriptor);
            }
            return (Message) fieldSet.getField(fieldDescriptor);
        }

        private static void mergeOriginalMessage(Message.Builder builder, FieldSet<Descriptors.FieldDescriptor> fieldSet, Descriptors.FieldDescriptor fieldDescriptor, Message.Builder builder2) {
            Message originalMessage = getOriginalMessage(builder, fieldSet, fieldDescriptor);
            if (originalMessage != null) {
                builder2.mergeFrom(originalMessage);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0066  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0088  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x008d  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static boolean mergeFieldFrom(com.google.protobuf.CodedInputStream r7, com.google.protobuf.UnknownFieldSet.Builder r8, com.google.protobuf.ExtensionRegistryLite r9, com.google.protobuf.Descriptors.Descriptor r10, com.google.protobuf.Message.Builder r11, com.google.protobuf.FieldSet<com.google.protobuf.Descriptors.FieldDescriptor> r12, int r13) throws java.io.IOException {
            /*
                Method dump skipped, instructions count: 340
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.AbstractMessage.Builder.mergeFieldFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.UnknownFieldSet$Builder, com.google.protobuf.ExtensionRegistryLite, com.google.protobuf.Descriptors$Descriptor, com.google.protobuf.Message$Builder, com.google.protobuf.FieldSet, int):boolean");
        }

        private static void mergeMessageSetExtensionFromCodedStream(CodedInputStream codedInputStream, UnknownFieldSet.Builder builder, ExtensionRegistryLite extensionRegistryLite, Descriptors.Descriptor descriptor, Message.Builder builder2, FieldSet<Descriptors.FieldDescriptor> fieldSet) throws IOException {
            int i = 0;
            ByteString byteString = null;
            ExtensionRegistry.ExtensionInfo extensionInfo = null;
            while (true) {
                int readTag = codedInputStream.readTag();
                if (readTag == 0) {
                    break;
                } else if (readTag == WireFormat.MESSAGE_SET_TYPE_ID_TAG) {
                    i = codedInputStream.readUInt32();
                    if (i != 0 && (extensionRegistryLite instanceof ExtensionRegistry)) {
                        extensionInfo = ((ExtensionRegistry) extensionRegistryLite).findExtensionByNumber(descriptor, i);
                    }
                } else if (readTag == WireFormat.MESSAGE_SET_MESSAGE_TAG) {
                    if (i == 0 || extensionInfo == null || !ExtensionRegistryLite.isEagerlyParseMessageSets()) {
                        byteString = codedInputStream.readBytes();
                    } else {
                        eagerlyMergeMessageSetExtension(codedInputStream, extensionInfo, extensionRegistryLite, builder2, fieldSet);
                        byteString = null;
                    }
                } else if (!codedInputStream.skipField(readTag)) {
                    break;
                }
            }
            codedInputStream.checkLastTagWas(WireFormat.MESSAGE_SET_ITEM_END_TAG);
            if (byteString != null && i != 0) {
                if (extensionInfo != null) {
                    mergeMessageSetExtensionFromBytes(byteString, extensionInfo, extensionRegistryLite, builder2, fieldSet);
                } else {
                    builder.mergeField(i, UnknownFieldSet.Field.newBuilder().addLengthDelimited(byteString).build());
                }
            }
        }

        private static void eagerlyMergeMessageSetExtension(CodedInputStream codedInputStream, ExtensionRegistry.ExtensionInfo extensionInfo, ExtensionRegistryLite extensionRegistryLite, Message.Builder builder, FieldSet<Descriptors.FieldDescriptor> fieldSet) throws IOException {
            Message message;
            Descriptors.FieldDescriptor fieldDescriptor = extensionInfo.descriptor;
            if (hasOriginalMessage(builder, fieldSet, fieldDescriptor)) {
                Message.Builder builder2 = getOriginalMessage(builder, fieldSet, fieldDescriptor).toBuilder();
                codedInputStream.readMessage(builder2, extensionRegistryLite);
                message = builder2.buildPartial();
            } else {
                message = (Message) codedInputStream.readMessage((Parser<MessageLite>) extensionInfo.defaultInstance.getParserForType(), extensionRegistryLite);
            }
            if (builder != null) {
                builder.setField(fieldDescriptor, message);
            } else {
                fieldSet.setField(fieldDescriptor, message);
            }
        }

        private static void mergeMessageSetExtensionFromBytes(ByteString byteString, ExtensionRegistry.ExtensionInfo extensionInfo, ExtensionRegistryLite extensionRegistryLite, Message.Builder builder, FieldSet<Descriptors.FieldDescriptor> fieldSet) throws IOException {
            Message message;
            Descriptors.FieldDescriptor fieldDescriptor = extensionInfo.descriptor;
            boolean hasOriginalMessage = hasOriginalMessage(builder, fieldSet, fieldDescriptor);
            if (hasOriginalMessage || ExtensionRegistryLite.isEagerlyParseMessageSets()) {
                if (hasOriginalMessage) {
                    Message.Builder builder2 = getOriginalMessage(builder, fieldSet, fieldDescriptor).toBuilder();
                    builder2.mergeFrom(byteString, extensionRegistryLite);
                    message = builder2.buildPartial();
                } else {
                    message = (Message) extensionInfo.defaultInstance.getParserForType().parsePartialFrom(byteString, extensionRegistryLite);
                }
                setField(builder, fieldSet, fieldDescriptor, message);
                return;
            }
            LazyField lazyField = new LazyField(extensionInfo.defaultInstance, extensionRegistryLite, byteString);
            if (builder == null) {
                fieldSet.setField(fieldDescriptor, lazyField);
            } else if (builder instanceof GeneratedMessage.ExtendableBuilder) {
                builder.setField(fieldDescriptor, lazyField);
            } else {
                builder.setField(fieldDescriptor, lazyField.getValue());
            }
        }

        @Override // com.google.protobuf.Message.Builder
        public BuilderType mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            setUnknownFields(UnknownFieldSet.newBuilder(getUnknownFields()).mergeFrom(unknownFieldSet).build());
            return this;
        }

        @Override // com.google.protobuf.Message.Builder
        public Message.Builder getFieldBuilder(Descriptors.FieldDescriptor fieldDescriptor) {
            throw new UnsupportedOperationException("getFieldBuilder() called on an unsupported message type.");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public static UninitializedMessageException newUninitializedMessageException(Message message) {
            return new UninitializedMessageException(findMissingFields(message));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static List<String> findMissingFields(MessageOrBuilder messageOrBuilder) {
            ArrayList arrayList = new ArrayList();
            findMissingFields(messageOrBuilder, "", arrayList);
            return arrayList;
        }

        private static void findMissingFields(MessageOrBuilder messageOrBuilder, String str, List<String> list) {
            for (Descriptors.FieldDescriptor fieldDescriptor : messageOrBuilder.getDescriptorForType().getFields()) {
                if (fieldDescriptor.isRequired() && !messageOrBuilder.hasField(fieldDescriptor)) {
                    list.add(String.valueOf(str) + fieldDescriptor.getName());
                }
            }
            for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : messageOrBuilder.getAllFields().entrySet()) {
                Descriptors.FieldDescriptor key = entry.getKey();
                Object value = entry.getValue();
                if (key.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                    if (key.isRepeated()) {
                        int i = 0;
                        for (MessageOrBuilder messageOrBuilder2 : (List) value) {
                            i++;
                            findMissingFields(messageOrBuilder2, subMessagePrefix(str, key, i), list);
                        }
                    } else if (messageOrBuilder.hasField(key)) {
                        findMissingFields((MessageOrBuilder) value, subMessagePrefix(str, key, -1), list);
                    }
                }
            }
        }

        private static String subMessagePrefix(String str, Descriptors.FieldDescriptor fieldDescriptor, int i) {
            StringBuilder sb = new StringBuilder(str);
            if (fieldDescriptor.isExtension()) {
                sb.append('(');
                sb.append(fieldDescriptor.getFullName());
                sb.append(')');
            } else {
                sb.append(fieldDescriptor.getName());
            }
            if (i != -1) {
                sb.append('[');
                sb.append(i);
                sb.append(']');
            }
            sb.append(FilenameUtils.EXTENSION_SEPARATOR);
            return sb.toString();
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (BuilderType) ((Builder) super.mergeFrom(byteString));
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BuilderType) ((Builder) super.mergeFrom(byteString, extensionRegistryLite));
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (BuilderType) ((Builder) super.mergeFrom(bArr));
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException {
            return (BuilderType) ((Builder) super.mergeFrom(bArr, i, i2));
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BuilderType) ((Builder) super.mergeFrom(bArr, extensionRegistryLite));
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BuilderType) ((Builder) super.mergeFrom(bArr, i, i2, extensionRegistryLite));
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(InputStream inputStream) throws IOException {
            return (BuilderType) ((Builder) super.mergeFrom(inputStream));
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BuilderType) ((Builder) super.mergeFrom(inputStream, extensionRegistryLite));
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public boolean mergeDelimitedFrom(InputStream inputStream) throws IOException {
            return super.mergeDelimitedFrom(inputStream);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public boolean mergeDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return super.mergeDelimitedFrom(inputStream, extensionRegistryLite);
        }
    }
}
