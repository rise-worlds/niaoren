package p110z1;

import org.apache.tools.ant.types.selectors.SizeSelector;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, m8860e = {"Lkotlin/text/ScreenFloatValueRegEx;", "", "()V", SizeSelector.SIZE_KEY, "Lkotlin/text/Regex;", "kotlin-stdlib"})
/* renamed from: z1.cpk */
/* loaded from: classes3.dex */
final class StringNumberConversionsJVM {
    @JvmPlatformAnnotations
    @NotNull

    /* renamed from: a */
    public static final cph f21025a;

    /* renamed from: b */
    public static final StringNumberConversionsJVM f21026b = new StringNumberConversionsJVM();

    static {
        String str = "[eE][+-]?(\\p{Digit}+)";
        f21025a = new cph("[\\x00-\\x20]*[+-]?(NaN|Infinity|((" + ("((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)(" + str + ")?)|(\\.((\\p{Digit}+))(" + str + ")?)|((" + ("(0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+))") + ")[pP][+-]?(\\p{Digit}+))") + ")[fFdD]?))[\\x00-\\x20]*");
    }

    private StringNumberConversionsJVM() {
    }
}
