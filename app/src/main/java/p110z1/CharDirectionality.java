package p110z1;

import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.tools.ant.types.selectors.SizeSelector;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0019\b\u0086\u0001\u0018\u0000 \u001b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001a¨\u0006\u001c"}, m8860e = {"Lkotlin/text/CharDirectionality;", "", SizeSelector.SIZE_KEY, "", "(Ljava/lang/String;II)V", "getValue", "()I", "UNDEFINED", "LEFT_TO_RIGHT", "RIGHT_TO_LEFT", "RIGHT_TO_LEFT_ARABIC", "EUROPEAN_NUMBER", "EUROPEAN_NUMBER_SEPARATOR", "EUROPEAN_NUMBER_TERMINATOR", "ARABIC_NUMBER", "COMMON_NUMBER_SEPARATOR", "NONSPACING_MARK", "BOUNDARY_NEUTRAL", "PARAGRAPH_SEPARATOR", "SEGMENT_SEPARATOR", "WHITESPACE", "OTHER_NEUTRALS", "LEFT_TO_RIGHT_EMBEDDING", "LEFT_TO_RIGHT_OVERRIDE", "RIGHT_TO_LEFT_EMBEDDING", "RIGHT_TO_LEFT_OVERRIDE", "POP_DIRECTIONAL_FORMAT", "Companion", "kotlin-stdlib"})
/* renamed from: z1.cou */
/* loaded from: classes3.dex */
public enum CharDirectionality {
    UNDEFINED(-1),
    LEFT_TO_RIGHT(0),
    RIGHT_TO_LEFT(1),
    RIGHT_TO_LEFT_ARABIC(2),
    EUROPEAN_NUMBER(3),
    EUROPEAN_NUMBER_SEPARATOR(4),
    EUROPEAN_NUMBER_TERMINATOR(5),
    ARABIC_NUMBER(6),
    COMMON_NUMBER_SEPARATOR(7),
    NONSPACING_MARK(8),
    BOUNDARY_NEUTRAL(9),
    PARAGRAPH_SEPARATOR(10),
    SEGMENT_SEPARATOR(11),
    WHITESPACE(12),
    OTHER_NEUTRALS(13),
    LEFT_TO_RIGHT_EMBEDDING(14),
    LEFT_TO_RIGHT_OVERRIDE(15),
    RIGHT_TO_LEFT_EMBEDDING(16),
    RIGHT_TO_LEFT_OVERRIDE(17),
    POP_DIRECTIONAL_FORMAT(18);
    
    private final int value;
    public static final C5065a Companion = new C5065a(null);
    private static final bvz directionalityMap$delegate = bwa.m8867a((chc) C5066b.INSTANCE);

    CharDirectionality(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }

    /* compiled from: CharDirectionality.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0005R'\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, m8860e = {"Lkotlin/text/CharDirectionality$Companion;", "", "()V", "directionalityMap", "", "", "Lkotlin/text/CharDirectionality;", "getDirectionalityMap", "()Ljava/util/Map;", "directionalityMap$delegate", "Lkotlin/Lazy;", "valueOf", "directionality", "kotlin-stdlib"})
    /* renamed from: z1.cou$a */
    /* loaded from: classes3.dex */
    public static final class C5065a {

        /* renamed from: a */
        static final /* synthetic */ cnf[] f20994a = {Reflection.m5112a(new PropertyReference1Impl(Reflection.m5109b(C5065a.class), "directionalityMap", "getDirectionalityMap()Ljava/util/Map;"))};

        /* renamed from: a */
        private final Map<Integer, CharDirectionality> m4250a() {
            bvz bvzVar = CharDirectionality.directionalityMap$delegate;
            C5065a aVar = CharDirectionality.Companion;
            cnf cnfVar = f20994a[0];
            return (Map) bvzVar.getValue();
        }

        private C5065a() {
        }

        public /* synthetic */ C5065a(DefaultConstructorMarker civVar) {
            this();
        }

        @NotNull
        /* renamed from: a */
        public final CharDirectionality m4249a(int i) {
            CharDirectionality couVar = m4250a().get(Integer.valueOf(i));
            if (couVar != null) {
                return couVar;
            }
            throw new IllegalArgumentException("Directionality #" + i + " is not defined.");
        }
    }

    /* compiled from: CharDirectionality.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0010\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\n¢\u0006\u0002\b\u0004"}, m8860e = {"<anonymous>", "", "", "Lkotlin/text/CharDirectionality;", "invoke"})
    /* renamed from: z1.cou$b */
    /* loaded from: classes3.dex */
    static final class C5066b extends Lambda implements chc<Map<Integer, ? extends CharDirectionality>> {
        public static final C5066b INSTANCE = new C5066b();

        C5066b() {
            super(0);
        }

        @Override // p110z1.chc
        @NotNull
        public final Map<Integer, ? extends CharDirectionality> invoke() {
            CharDirectionality[] values = CharDirectionality.values();
            LinkedHashMap linkedHashMap = new LinkedHashMap(cmi.m4715c(can.m6469a(values.length), 16));
            for (CharDirectionality couVar : values) {
                linkedHashMap.put(Integer.valueOf(couVar.getValue()), couVar);
            }
            return linkedHashMap;
        }
    }
}
