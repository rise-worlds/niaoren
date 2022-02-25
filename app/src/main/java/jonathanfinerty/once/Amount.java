package jonathanfinerty.once;

/* loaded from: classes2.dex */
public class Amount {
    public static CountChecker exactly(final int i) {
        return new CountChecker() { // from class: jonathanfinerty.once.Amount.1
            @Override // jonathanfinerty.once.CountChecker
            public boolean check(int i2) {
                return i == i2;
            }
        };
    }

    public static CountChecker moreThan(final int i) {
        return new CountChecker() { // from class: jonathanfinerty.once.Amount.2
            @Override // jonathanfinerty.once.CountChecker
            public boolean check(int i2) {
                return i2 > i;
            }
        };
    }

    public static CountChecker lessThan(final int i) {
        return new CountChecker() { // from class: jonathanfinerty.once.Amount.3
            @Override // jonathanfinerty.once.CountChecker
            public boolean check(int i2) {
                return i2 < i;
            }
        };
    }
}
