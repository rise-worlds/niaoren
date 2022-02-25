package com.google.protobuf;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.Message;
import com.google.protobuf.UnknownFieldSet;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.tendcloud.tenddata.C2771ci;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.p105io.FilenameUtils;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.tar.TarConstants;
import p110z1.C4963cj;
import p110z1.Consts;
import p110z1.SimpleComparison;
import p110z1.cjm;

/* loaded from: classes.dex */
public final class TextFormat {

    /* renamed from: $SWITCH_TABLE$com$google$protobuf$Descriptors$FieldDescriptor$Type */
    private static /* synthetic */ int[] f9103x792aeea3 = null;
    private static final int BUFFER_SIZE = 4096;
    private static final Printer DEFAULT_PRINTER = new Printer(null);
    private static final Printer SINGLE_LINE_PRINTER = new Printer(null).setSingleLineMode(true);
    private static final Printer UNICODE_PRINTER = new Printer(null).setEscapeNonAscii(false);

    private static int digitValue(byte b) {
        return (48 > b || b > 57) ? (97 > b || b > 122) ? (b - 65) + 10 : (b - 97) + 10 : b - TarConstants.LF_NORMAL;
    }

    private static boolean isHex(byte b) {
        if (48 <= b && b <= 57) {
            return true;
        }
        if (97 > b || b > 102) {
            return 65 <= b && b <= 70;
        }
        return true;
    }

    private static boolean isOctal(byte b) {
        return 48 <= b && b <= 55;
    }

    /* renamed from: $SWITCH_TABLE$com$google$protobuf$Descriptors$FieldDescriptor$Type */
    static /* synthetic */ int[] m20269x792aeea3() {
        int[] iArr = f9103x792aeea3;
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
        f9103x792aeea3 = iArr2;
        return iArr2;
    }

    private TextFormat() {
    }

    public static void print(MessageOrBuilder messageOrBuilder, Appendable appendable) throws IOException {
        DEFAULT_PRINTER.print(messageOrBuilder, new TextGenerator(appendable, null));
    }

    public static void print(UnknownFieldSet unknownFieldSet, Appendable appendable) throws IOException {
        DEFAULT_PRINTER.printUnknownFields(unknownFieldSet, new TextGenerator(appendable, null));
    }

    public static String shortDebugString(MessageOrBuilder messageOrBuilder) {
        try {
            StringBuilder sb = new StringBuilder();
            SINGLE_LINE_PRINTER.print(messageOrBuilder, new TextGenerator(sb, null));
            return sb.toString().trim();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String shortDebugString(UnknownFieldSet unknownFieldSet) {
        try {
            StringBuilder sb = new StringBuilder();
            SINGLE_LINE_PRINTER.printUnknownFields(unknownFieldSet, new TextGenerator(sb, null));
            return sb.toString().trim();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String printToString(MessageOrBuilder messageOrBuilder) {
        try {
            StringBuilder sb = new StringBuilder();
            print(messageOrBuilder, sb);
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String printToString(UnknownFieldSet unknownFieldSet) {
        try {
            StringBuilder sb = new StringBuilder();
            print(unknownFieldSet, sb);
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String printToUnicodeString(MessageOrBuilder messageOrBuilder) {
        try {
            StringBuilder sb = new StringBuilder();
            UNICODE_PRINTER.print(messageOrBuilder, new TextGenerator(sb, null));
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String printToUnicodeString(UnknownFieldSet unknownFieldSet) {
        try {
            StringBuilder sb = new StringBuilder();
            UNICODE_PRINTER.printUnknownFields(unknownFieldSet, new TextGenerator(sb, null));
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void printField(Descriptors.FieldDescriptor fieldDescriptor, Object obj, Appendable appendable) throws IOException {
        DEFAULT_PRINTER.printField(fieldDescriptor, obj, new TextGenerator(appendable, null));
    }

    public static String printFieldToString(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
        try {
            StringBuilder sb = new StringBuilder();
            printField(fieldDescriptor, obj, sb);
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void printFieldValue(Descriptors.FieldDescriptor fieldDescriptor, Object obj, Appendable appendable) throws IOException {
        DEFAULT_PRINTER.printFieldValue(fieldDescriptor, obj, new TextGenerator(appendable, null));
    }

    public static void printUnknownFieldValue(int i, Object obj, Appendable appendable) throws IOException {
        printUnknownFieldValue(i, obj, new TextGenerator(appendable, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void printUnknownFieldValue(int i, Object obj, TextGenerator textGenerator) throws IOException {
        int tagWireType = WireFormat.getTagWireType(i);
        if (tagWireType != 5) {
            switch (tagWireType) {
                case 0:
                    textGenerator.print(unsignedToString(((Long) obj).longValue()));
                    return;
                case 1:
                    textGenerator.print(String.format(null, "0x%016x", (Long) obj));
                    return;
                case 2:
                    textGenerator.print("\"");
                    textGenerator.print(escapeBytes((ByteString) obj));
                    textGenerator.print("\"");
                    return;
                case 3:
                    DEFAULT_PRINTER.printUnknownFields((UnknownFieldSet) obj, textGenerator);
                    return;
                default:
                    throw new IllegalArgumentException("Bad tag: " + i);
            }
        } else {
            textGenerator.print(String.format(null, "0x%08x", (Integer) obj));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class Printer {

        /* renamed from: $SWITCH_TABLE$com$google$protobuf$Descriptors$FieldDescriptor$Type */
        private static /* synthetic */ int[] f9104x792aeea3;
        boolean escapeNonAscii;
        boolean singleLineMode;

        /* renamed from: $SWITCH_TABLE$com$google$protobuf$Descriptors$FieldDescriptor$Type */
        static /* synthetic */ int[] m20268x792aeea3() {
            int[] iArr = f9104x792aeea3;
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
            f9104x792aeea3 = iArr2;
            return iArr2;
        }

        private Printer() {
            this.singleLineMode = false;
            this.escapeNonAscii = true;
        }

        /* synthetic */ Printer(Printer printer) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Printer setSingleLineMode(boolean z) {
            this.singleLineMode = z;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Printer setEscapeNonAscii(boolean z) {
            this.escapeNonAscii = z;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void print(MessageOrBuilder messageOrBuilder, TextGenerator textGenerator) throws IOException {
            for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : messageOrBuilder.getAllFields().entrySet()) {
                printField(entry.getKey(), entry.getValue(), textGenerator);
            }
            printUnknownFields(messageOrBuilder.getUnknownFields(), textGenerator);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void printField(Descriptors.FieldDescriptor fieldDescriptor, Object obj, TextGenerator textGenerator) throws IOException {
            if (fieldDescriptor.isRepeated()) {
                for (Object obj2 : (List) obj) {
                    printSingleField(fieldDescriptor, obj2, textGenerator);
                }
                return;
            }
            printSingleField(fieldDescriptor, obj, textGenerator);
        }

        private void printSingleField(Descriptors.FieldDescriptor fieldDescriptor, Object obj, TextGenerator textGenerator) throws IOException {
            if (fieldDescriptor.isExtension()) {
                textGenerator.print("[");
                if (!fieldDescriptor.getContainingType().getOptions().getMessageSetWireFormat() || fieldDescriptor.getType() != Descriptors.FieldDescriptor.Type.MESSAGE || !fieldDescriptor.isOptional() || fieldDescriptor.getExtensionScope() != fieldDescriptor.getMessageType()) {
                    textGenerator.print(fieldDescriptor.getFullName());
                } else {
                    textGenerator.print(fieldDescriptor.getMessageType().getFullName());
                }
                textGenerator.print("]");
            } else if (fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.GROUP) {
                textGenerator.print(fieldDescriptor.getMessageType().getName());
            } else {
                textGenerator.print(fieldDescriptor.getName());
            }
            if (fieldDescriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                textGenerator.print(": ");
            } else if (this.singleLineMode) {
                textGenerator.print(" { ");
            } else {
                textGenerator.print(" {\n");
                textGenerator.indent();
            }
            printFieldValue(fieldDescriptor, obj, textGenerator);
            if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                if (this.singleLineMode) {
                    textGenerator.print("} ");
                    return;
                }
                textGenerator.outdent();
                textGenerator.print("}\n");
            } else if (this.singleLineMode) {
                textGenerator.print(ExpandableTextView.f6958c);
            } else {
                textGenerator.print("\n");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void printFieldValue(Descriptors.FieldDescriptor fieldDescriptor, Object obj, TextGenerator textGenerator) throws IOException {
            String str;
            switch (m20268x792aeea3()[fieldDescriptor.getType().ordinal()]) {
                case 1:
                    textGenerator.print(((Double) obj).toString());
                    return;
                case 2:
                    textGenerator.print(((Float) obj).toString());
                    return;
                case 3:
                case 16:
                case 18:
                    textGenerator.print(((Long) obj).toString());
                    return;
                case 4:
                case 6:
                    textGenerator.print(TextFormat.unsignedToString(((Long) obj).longValue()));
                    return;
                case 5:
                case 15:
                case 17:
                    textGenerator.print(((Integer) obj).toString());
                    return;
                case 7:
                case 13:
                    textGenerator.print(TextFormat.unsignedToString(((Integer) obj).intValue()));
                    return;
                case 8:
                    textGenerator.print(((Boolean) obj).toString());
                    return;
                case 9:
                    textGenerator.print("\"");
                    if (this.escapeNonAscii) {
                        str = TextFormat.escapeText((String) obj);
                    } else {
                        str = (String) obj;
                    }
                    textGenerator.print(str);
                    textGenerator.print("\"");
                    return;
                case 10:
                case 11:
                    print((Message) obj, textGenerator);
                    return;
                case 12:
                    textGenerator.print("\"");
                    textGenerator.print(TextFormat.escapeBytes((ByteString) obj));
                    textGenerator.print("\"");
                    return;
                case 14:
                    textGenerator.print(((Descriptors.EnumValueDescriptor) obj).getName());
                    return;
                default:
                    return;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void printUnknownFields(UnknownFieldSet unknownFieldSet, TextGenerator textGenerator) throws IOException {
            for (Map.Entry<Integer, UnknownFieldSet.Field> entry : unknownFieldSet.asMap().entrySet()) {
                int intValue = entry.getKey().intValue();
                UnknownFieldSet.Field value = entry.getValue();
                printUnknownField(intValue, 0, value.getVarintList(), textGenerator);
                printUnknownField(intValue, 5, value.getFixed32List(), textGenerator);
                printUnknownField(intValue, 1, value.getFixed64List(), textGenerator);
                printUnknownField(intValue, 2, value.getLengthDelimitedList(), textGenerator);
                for (UnknownFieldSet unknownFieldSet2 : value.getGroupList()) {
                    textGenerator.print(entry.getKey().toString());
                    if (this.singleLineMode) {
                        textGenerator.print(" { ");
                    } else {
                        textGenerator.print(" {\n");
                        textGenerator.indent();
                    }
                    printUnknownFields(unknownFieldSet2, textGenerator);
                    if (this.singleLineMode) {
                        textGenerator.print("} ");
                    } else {
                        textGenerator.outdent();
                        textGenerator.print("}\n");
                    }
                }
            }
        }

        private void printUnknownField(int i, int i2, List<?> list, TextGenerator textGenerator) throws IOException {
            for (Object obj : list) {
                textGenerator.print(String.valueOf(i));
                textGenerator.print(": ");
                TextFormat.printUnknownFieldValue(i2, obj, textGenerator);
                textGenerator.print(this.singleLineMode ? ExpandableTextView.f6958c : "\n");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String unsignedToString(int i) {
        if (i >= 0) {
            return Integer.toString(i);
        }
        return Long.toString(i & 4294967295L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String unsignedToString(long j) {
        if (j >= 0) {
            return Long.toString(j);
        }
        return BigInteger.valueOf(j & cjm.f20759b).setBit(63).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class TextGenerator {
        private boolean atStartOfLine;
        private final StringBuilder indent;
        private final Appendable output;

        private TextGenerator(Appendable appendable) {
            this.indent = new StringBuilder();
            this.atStartOfLine = true;
            this.output = appendable;
        }

        /* synthetic */ TextGenerator(Appendable appendable, TextGenerator textGenerator) {
            this(appendable);
        }

        public final void indent() {
            this.indent.append("  ");
        }

        public final void outdent() {
            int length = this.indent.length();
            if (length != 0) {
                this.indent.delete(length - 2, length);
                return;
            }
            throw new IllegalArgumentException(" Outdent() without matching Indent().");
        }

        public final void print(CharSequence charSequence) throws IOException {
            int length = charSequence.length();
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                if (charSequence.charAt(i2) == '\n') {
                    write(charSequence.subSequence(i, length), (i2 - i) + 1);
                    i = i2 + 1;
                    this.atStartOfLine = true;
                }
            }
            write(charSequence.subSequence(i, length), length - i);
        }

        private void write(CharSequence charSequence, int i) throws IOException {
            if (i != 0) {
                if (this.atStartOfLine) {
                    this.atStartOfLine = false;
                    this.output.append(this.indent);
                }
                this.output.append(charSequence);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class Tokenizer {
        private int column;
        private String currentToken;
        private int line;
        private final Matcher matcher;
        private int pos;
        private int previousColumn;
        private int previousLine;
        private final CharSequence text;
        private static final Pattern WHITESPACE = Pattern.compile("(\\s|(#.*$))++", 8);
        private static final Pattern TOKEN = Pattern.compile("[a-zA-Z_][0-9a-zA-Z_+-]*+|[.]?[0-9+-][0-9a-zA-Z_.+-]*+|\"([^\"\n\\\\]|\\\\.)*+(\"|\\\\?$)|'([^'\n\\\\]|\\\\.)*+('|\\\\?$)", 8);
        private static final Pattern DOUBLE_INFINITY = Pattern.compile("-?inf(inity)?", 2);
        private static final Pattern FLOAT_INFINITY = Pattern.compile("-?inf(inity)?f?", 2);
        private static final Pattern FLOAT_NAN = Pattern.compile("nanf?", 2);

        private Tokenizer(CharSequence charSequence) {
            this.pos = 0;
            this.line = 0;
            this.column = 0;
            this.previousLine = 0;
            this.previousColumn = 0;
            this.text = charSequence;
            this.matcher = WHITESPACE.matcher(charSequence);
            skipWhitespace();
            nextToken();
        }

        /* synthetic */ Tokenizer(CharSequence charSequence, Tokenizer tokenizer) {
            this(charSequence);
        }

        public final boolean atEnd() {
            return this.currentToken.length() == 0;
        }

        public final void nextToken() {
            this.previousLine = this.line;
            this.previousColumn = this.column;
            while (this.pos < this.matcher.regionStart()) {
                if (this.text.charAt(this.pos) == '\n') {
                    this.line++;
                    this.column = 0;
                } else {
                    this.column++;
                }
                this.pos++;
            }
            if (this.matcher.regionStart() == this.matcher.regionEnd()) {
                this.currentToken = "";
                return;
            }
            this.matcher.usePattern(TOKEN);
            if (this.matcher.lookingAt()) {
                this.currentToken = this.matcher.group();
                Matcher matcher = this.matcher;
                matcher.region(matcher.end(), this.matcher.regionEnd());
            } else {
                this.currentToken = String.valueOf(this.text.charAt(this.pos));
                Matcher matcher2 = this.matcher;
                matcher2.region(this.pos + 1, matcher2.regionEnd());
            }
            skipWhitespace();
        }

        private void skipWhitespace() {
            this.matcher.usePattern(WHITESPACE);
            if (this.matcher.lookingAt()) {
                Matcher matcher = this.matcher;
                matcher.region(matcher.end(), this.matcher.regionEnd());
            }
        }

        public final boolean tryConsume(String str) {
            if (!this.currentToken.equals(str)) {
                return false;
            }
            nextToken();
            return true;
        }

        public final void consume(String str) throws ParseException {
            if (!tryConsume(str)) {
                throw parseException("Expected \"" + str + "\".");
            }
        }

        public final boolean lookingAtInteger() {
            if (this.currentToken.length() == 0) {
                return false;
            }
            char charAt = this.currentToken.charAt(0);
            return ('0' <= charAt && charAt <= '9') || charAt == '-' || charAt == '+';
        }

        public final String consumeIdentifier() throws ParseException {
            for (int i = 0; i < this.currentToken.length(); i++) {
                char charAt = this.currentToken.charAt(i);
                if (('a' > charAt || charAt > 'z') && (('A' > charAt || charAt > 'Z') && !(('0' <= charAt && charAt <= '9') || charAt == '_' || charAt == '.'))) {
                    throw parseException("Expected identifier.");
                }
            }
            String str = this.currentToken;
            nextToken();
            return str;
        }

        public final int consumeInt32() throws ParseException {
            try {
                int parseInt32 = TextFormat.parseInt32(this.currentToken);
                nextToken();
                return parseInt32;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        public final int consumeUInt32() throws ParseException {
            try {
                int parseUInt32 = TextFormat.parseUInt32(this.currentToken);
                nextToken();
                return parseUInt32;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        public final long consumeInt64() throws ParseException {
            try {
                long parseInt64 = TextFormat.parseInt64(this.currentToken);
                nextToken();
                return parseInt64;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        public final long consumeUInt64() throws ParseException {
            try {
                long parseUInt64 = TextFormat.parseUInt64(this.currentToken);
                nextToken();
                return parseUInt64;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        public final double consumeDouble() throws ParseException {
            if (DOUBLE_INFINITY.matcher(this.currentToken).matches()) {
                boolean startsWith = this.currentToken.startsWith("-");
                nextToken();
                return startsWith ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
            } else if (this.currentToken.equalsIgnoreCase("nan")) {
                nextToken();
                return Double.NaN;
            } else {
                try {
                    double parseDouble = Double.parseDouble(this.currentToken);
                    nextToken();
                    return parseDouble;
                } catch (NumberFormatException e) {
                    throw floatParseException(e);
                }
            }
        }

        public final float consumeFloat() throws ParseException {
            if (FLOAT_INFINITY.matcher(this.currentToken).matches()) {
                boolean startsWith = this.currentToken.startsWith("-");
                nextToken();
                return startsWith ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
            } else if (FLOAT_NAN.matcher(this.currentToken).matches()) {
                nextToken();
                return Float.NaN;
            } else {
                try {
                    float parseFloat = Float.parseFloat(this.currentToken);
                    nextToken();
                    return parseFloat;
                } catch (NumberFormatException e) {
                    throw floatParseException(e);
                }
            }
        }

        public final boolean consumeBoolean() throws ParseException {
            if (this.currentToken.equals("true") || this.currentToken.equals("t") || this.currentToken.equals("1")) {
                nextToken();
                return true;
            } else if (this.currentToken.equals("false") || this.currentToken.equals("f") || this.currentToken.equals(ResultTypeConstant.f7213z)) {
                nextToken();
                return false;
            } else {
                throw parseException("Expected \"true\" or \"false\".");
            }
        }

        public final String consumeString() throws ParseException {
            return consumeByteString().toStringUtf8();
        }

        public final ByteString consumeByteString() throws ParseException {
            ArrayList arrayList = new ArrayList();
            while (true) {
                consumeByteString(arrayList);
                if (!this.currentToken.startsWith("'") && !this.currentToken.startsWith("\"")) {
                    return ByteString.copyFrom(arrayList);
                }
            }
        }

        private void consumeByteString(List<ByteString> list) throws ParseException {
            char c = 0;
            if (this.currentToken.length() > 0) {
                c = this.currentToken.charAt(0);
            }
            if (c == '\"' || c == '\'') {
                if (this.currentToken.length() >= 2) {
                    String str = this.currentToken;
                    if (str.charAt(str.length() - 1) == c) {
                        try {
                            ByteString unescapeBytes = TextFormat.unescapeBytes(this.currentToken.substring(1, this.currentToken.length() - 1));
                            nextToken();
                            list.add(unescapeBytes);
                            return;
                        } catch (InvalidEscapeSequenceException e) {
                            throw parseException(e.getMessage());
                        }
                    }
                }
                throw parseException("String missing ending quote.");
            }
            throw parseException("Expected string.");
        }

        public final ParseException parseException(String str) {
            return new ParseException(this.line + 1, this.column + 1, str);
        }

        public final ParseException parseExceptionPreviousToken(String str) {
            return new ParseException(this.previousLine + 1, this.previousColumn + 1, str);
        }

        private ParseException integerParseException(NumberFormatException numberFormatException) {
            return parseException("Couldn't parse integer: " + numberFormatException.getMessage());
        }

        private ParseException floatParseException(NumberFormatException numberFormatException) {
            return parseException("Couldn't parse number: " + numberFormatException.getMessage());
        }
    }

    /* loaded from: classes.dex */
    public static class ParseException extends IOException {
        private static final long serialVersionUID = 3196188060225107702L;
        private final int column;
        private final int line;

        public ParseException(String str) {
            this(-1, -1, str);
        }

        public ParseException(int i, int i2, String str) {
            super(String.valueOf(Integer.toString(i)) + ":" + i2 + ": " + str);
            this.line = i;
            this.column = i2;
        }

        public int getLine() {
            return this.line;
        }

        public int getColumn() {
            return this.column;
        }
    }

    public static void merge(Readable readable, Message.Builder builder) throws IOException {
        merge(readable, ExtensionRegistry.getEmptyRegistry(), builder);
    }

    public static void merge(CharSequence charSequence, Message.Builder builder) throws ParseException {
        merge(charSequence, ExtensionRegistry.getEmptyRegistry(), builder);
    }

    public static void merge(Readable readable, ExtensionRegistry extensionRegistry, Message.Builder builder) throws IOException {
        merge(toStringBuilder(readable), extensionRegistry, builder);
    }

    private static StringBuilder toStringBuilder(Readable readable) throws IOException {
        StringBuilder sb = new StringBuilder();
        CharBuffer allocate = CharBuffer.allocate(4096);
        while (true) {
            int read = readable.read(allocate);
            if (read == -1) {
                return sb;
            }
            allocate.flip();
            sb.append((CharSequence) allocate, 0, read);
        }
    }

    public static void merge(CharSequence charSequence, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
        Tokenizer tokenizer = new Tokenizer(charSequence, null);
        while (!tokenizer.atEnd()) {
            mergeField(tokenizer, extensionRegistry, builder);
        }
    }

    private static void mergeField(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
        ExtensionRegistry.ExtensionInfo extensionInfo;
        Descriptors.FieldDescriptor fieldDescriptor;
        String str;
        Message.Builder builder2;
        Descriptors.Descriptor descriptorForType = builder.getDescriptorForType();
        Object obj = null;
        if (tokenizer.tryConsume("[")) {
            StringBuilder sb = new StringBuilder(tokenizer.consumeIdentifier());
            while (tokenizer.tryConsume(Consts.f23430h)) {
                sb.append(FilenameUtils.EXTENSION_SEPARATOR);
                sb.append(tokenizer.consumeIdentifier());
            }
            extensionInfo = extensionRegistry.findExtensionByName(sb.toString());
            if (extensionInfo == null) {
                throw tokenizer.parseExceptionPreviousToken("Extension \"" + ((Object) sb) + "\" not found in the ExtensionRegistry.");
            } else if (extensionInfo.descriptor.getContainingType() == descriptorForType) {
                tokenizer.consume("]");
                fieldDescriptor = extensionInfo.descriptor;
            } else {
                throw tokenizer.parseExceptionPreviousToken("Extension \"" + ((Object) sb) + "\" does not extend message type \"" + descriptorForType.getFullName() + "\".");
            }
        } else {
            String consumeIdentifier = tokenizer.consumeIdentifier();
            Descriptors.FieldDescriptor findFieldByName = descriptorForType.findFieldByName(consumeIdentifier);
            if (!(findFieldByName != null || (findFieldByName = descriptorForType.findFieldByName(consumeIdentifier.toLowerCase(Locale.US))) == null || findFieldByName.getType() == Descriptors.FieldDescriptor.Type.GROUP)) {
                findFieldByName = null;
            }
            if (findFieldByName != null && findFieldByName.getType() == Descriptors.FieldDescriptor.Type.GROUP && !findFieldByName.getMessageType().getName().equals(consumeIdentifier)) {
                findFieldByName = null;
            }
            if (findFieldByName != null) {
                fieldDescriptor = findFieldByName;
                extensionInfo = null;
            } else {
                throw tokenizer.parseExceptionPreviousToken("Message type \"" + descriptorForType.getFullName() + "\" has no field named \"" + consumeIdentifier + "\".");
            }
        }
        if (fieldDescriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            tokenizer.consume(":");
            switch (m20269x792aeea3()[fieldDescriptor.getType().ordinal()]) {
                case 1:
                    obj = Double.valueOf(tokenizer.consumeDouble());
                    break;
                case 2:
                    obj = Float.valueOf(tokenizer.consumeFloat());
                    break;
                case 3:
                case 16:
                case 18:
                    obj = Long.valueOf(tokenizer.consumeInt64());
                    break;
                case 4:
                case 6:
                    obj = Long.valueOf(tokenizer.consumeUInt64());
                    break;
                case 5:
                case 15:
                case 17:
                    obj = Integer.valueOf(tokenizer.consumeInt32());
                    break;
                case 7:
                case 13:
                    obj = Integer.valueOf(tokenizer.consumeUInt32());
                    break;
                case 8:
                    obj = Boolean.valueOf(tokenizer.consumeBoolean());
                    break;
                case 9:
                    obj = tokenizer.consumeString();
                    break;
                case 10:
                case 11:
                    throw new RuntimeException("Can't get here.");
                case 12:
                    obj = tokenizer.consumeByteString();
                    break;
                case 14:
                    Descriptors.EnumDescriptor enumType = fieldDescriptor.getEnumType();
                    if (tokenizer.lookingAtInteger()) {
                        int consumeInt32 = tokenizer.consumeInt32();
                        obj = enumType.findValueByNumber(consumeInt32);
                        if (obj == null) {
                            throw tokenizer.parseExceptionPreviousToken("Enum type \"" + enumType.getFullName() + "\" has no value with number " + consumeInt32 + FilenameUtils.EXTENSION_SEPARATOR);
                        }
                    } else {
                        String consumeIdentifier2 = tokenizer.consumeIdentifier();
                        obj = enumType.findValueByName(consumeIdentifier2);
                        if (obj == null) {
                            throw tokenizer.parseExceptionPreviousToken("Enum type \"" + enumType.getFullName() + "\" has no value named \"" + consumeIdentifier2 + "\".");
                        }
                    }
                    break;
            }
        } else {
            tokenizer.tryConsume(":");
            if (tokenizer.tryConsume(SimpleComparison.f23612f)) {
                str = SimpleComparison.f23610d;
            } else {
                tokenizer.consume("{");
                str = C4963cj.f20747d;
            }
            if (extensionInfo == null) {
                builder2 = builder.newBuilderForField(fieldDescriptor);
            } else {
                builder2 = extensionInfo.defaultInstance.newBuilderForType();
            }
            while (!tokenizer.tryConsume(str)) {
                if (!tokenizer.atEnd()) {
                    mergeField(tokenizer, extensionRegistry, builder2);
                } else {
                    throw tokenizer.parseException("Expected \"" + str + "\".");
                }
            }
            obj = builder2.buildPartial();
        }
        if (fieldDescriptor.isRepeated()) {
            builder.addRepeatedField(fieldDescriptor, obj);
        } else {
            builder.setField(fieldDescriptor, obj);
        }
    }

    static String escapeBytes(ByteString byteString) {
        StringBuilder sb = new StringBuilder(byteString.size());
        for (int i = 0; i < byteString.size(); i++) {
            byte byteAt = byteString.byteAt(i);
            if (byteAt == 34) {
                sb.append("\\\"");
            } else if (byteAt == 39) {
                sb.append("\\'");
            } else if (byteAt != 92) {
                switch (byteAt) {
                    case 7:
                        sb.append("\\a");
                        continue;
                    case 8:
                        sb.append("\\b");
                        continue;
                    case 9:
                        sb.append("\\t");
                        continue;
                    case 10:
                        sb.append("\\n");
                        continue;
                    case 11:
                        sb.append("\\v");
                        continue;
                    case 12:
                        sb.append("\\f");
                        continue;
                    case 13:
                        sb.append("\\r");
                        continue;
                    default:
                        if (byteAt < 32) {
                            sb.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                            sb.append((char) (((byteAt >>> 6) & 3) + 48));
                            sb.append((char) (((byteAt >>> 3) & 7) + 48));
                            sb.append((char) ((byteAt & 7) + 48));
                            break;
                        } else {
                            sb.append((char) byteAt);
                            continue;
                        }
                }
            } else {
                sb.append("\\\\");
            }
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ByteString unescapeBytes(CharSequence charSequence) throws InvalidEscapeSequenceException {
        ByteString copyFromUtf8 = ByteString.copyFromUtf8(charSequence.toString());
        byte[] bArr = new byte[copyFromUtf8.size()];
        int i = 0;
        int i2 = 0;
        while (i < copyFromUtf8.size()) {
            byte byteAt = copyFromUtf8.byteAt(i);
            if (byteAt == 92) {
                i++;
                if (i < copyFromUtf8.size()) {
                    byte byteAt2 = copyFromUtf8.byteAt(i);
                    if (isOctal(byteAt2)) {
                        int digitValue = digitValue(byteAt2);
                        int i3 = i + 1;
                        if (i3 < copyFromUtf8.size() && isOctal(copyFromUtf8.byteAt(i3))) {
                            digitValue = (digitValue * 8) + digitValue(copyFromUtf8.byteAt(i3));
                            i = i3;
                        }
                        int i4 = i + 1;
                        if (i4 < copyFromUtf8.size() && isOctal(copyFromUtf8.byteAt(i4))) {
                            digitValue = (digitValue * 8) + digitValue(copyFromUtf8.byteAt(i4));
                            i = i4;
                        }
                        i2++;
                        bArr[i2] = (byte) digitValue;
                    } else if (byteAt2 == 34) {
                        i2++;
                        bArr[i2] = 34;
                    } else if (byteAt2 == 39) {
                        i2++;
                        bArr[i2] = 39;
                    } else if (byteAt2 == 92) {
                        i2++;
                        bArr[i2] = 92;
                    } else if (byteAt2 == 102) {
                        i2++;
                        bArr[i2] = 12;
                    } else if (byteAt2 == 110) {
                        i2++;
                        bArr[i2] = 10;
                    } else if (byteAt2 == 114) {
                        i2++;
                        bArr[i2] = C2771ci.f13672f;
                    } else if (byteAt2 == 116) {
                        i2++;
                        bArr[i2] = 9;
                    } else if (byteAt2 == 118) {
                        i2++;
                        bArr[i2] = FileDownloadStatus.f10400b;
                    } else if (byteAt2 != 120) {
                        switch (byteAt2) {
                            case 97:
                                i2++;
                                bArr[i2] = 7;
                                continue;
                            case 98:
                                i2++;
                                bArr[i2] = 8;
                                continue;
                            default:
                                throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\" + ((char) byteAt2) + '\'');
                        }
                    } else {
                        i++;
                        if (i >= copyFromUtf8.size() || !isHex(copyFromUtf8.byteAt(i))) {
                            throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\x' with no digits");
                        }
                        int digitValue2 = digitValue(copyFromUtf8.byteAt(i));
                        int i5 = i + 1;
                        if (i5 < copyFromUtf8.size() && isHex(copyFromUtf8.byteAt(i5))) {
                            digitValue2 = (digitValue2 * 16) + digitValue(copyFromUtf8.byteAt(i5));
                            i = i5;
                        }
                        i2++;
                        bArr[i2] = (byte) digitValue2;
                    }
                } else {
                    throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\' at end of string.");
                }
            } else {
                i2++;
                bArr[i2] = byteAt;
            }
            i++;
        }
        return ByteString.copyFrom(bArr, 0, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class InvalidEscapeSequenceException extends IOException {
        private static final long serialVersionUID = -8164033650142593304L;

        InvalidEscapeSequenceException(String str) {
            super(str);
        }
    }

    static String escapeText(String str) {
        return escapeBytes(ByteString.copyFromUtf8(str));
    }

    static String unescapeText(String str) throws InvalidEscapeSequenceException {
        return unescapeBytes(str).toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int parseInt32(String str) throws NumberFormatException {
        return (int) parseInteger(str, true, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int parseUInt32(String str) throws NumberFormatException {
        return (int) parseInteger(str, false, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long parseInt64(String str) throws NumberFormatException {
        return parseInteger(str, true, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long parseUInt64(String str) throws NumberFormatException {
        return parseInteger(str, false, true);
    }

    private static long parseInteger(String str, boolean z, boolean z2) throws NumberFormatException {
        int i = 0;
        boolean z3 = true;
        if (!str.startsWith("-", 0)) {
            z3 = false;
        } else if (z) {
            i = 1;
        } else {
            throw new NumberFormatException("Number must be positive: " + str);
        }
        int i2 = 10;
        if (str.startsWith("0x", i)) {
            i += 2;
            i2 = 16;
        } else if (str.startsWith(ResultTypeConstant.f7213z, i)) {
            i2 = 8;
        }
        String substring = str.substring(i);
        if (substring.length() < 16) {
            long parseLong = Long.parseLong(substring, i2);
            if (z3) {
                parseLong = -parseLong;
            }
            if (z2) {
                return parseLong;
            }
            if (z) {
                if (parseLong <= 2147483647L && parseLong >= -2147483648L) {
                    return parseLong;
                }
                throw new NumberFormatException("Number out of range for 32-bit signed integer: " + str);
            } else if (parseLong < 4294967296L && parseLong >= 0) {
                return parseLong;
            } else {
                throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + str);
            }
        } else {
            BigInteger bigInteger = new BigInteger(substring, i2);
            if (z3) {
                bigInteger = bigInteger.negate();
            }
            if (!z2) {
                if (z) {
                    if (bigInteger.bitLength() > 31) {
                        throw new NumberFormatException("Number out of range for 32-bit signed integer: " + str);
                    }
                } else if (bigInteger.bitLength() > 32) {
                    throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + str);
                }
            } else if (z) {
                if (bigInteger.bitLength() > 63) {
                    throw new NumberFormatException("Number out of range for 64-bit signed integer: " + str);
                }
            } else if (bigInteger.bitLength() > 64) {
                throw new NumberFormatException("Number out of range for 64-bit unsigned integer: " + str);
            }
            return bigInteger.longValue();
        }
    }
}
