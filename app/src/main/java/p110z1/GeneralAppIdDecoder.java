package p110z1;

import android.support.v7.widget.helper.ItemTouchHelper;
import com.tencent.smtt.sdk.TbsListener;
import org.apache.commons.p105io.FilenameUtils;
import org.apache.commons.p105io.IOUtils;
import p110z1.CurrentParsingState;

/* renamed from: z1.kt */
/* loaded from: classes3.dex */
public final class GeneralAppIdDecoder {

    /* renamed from: a */
    private final BitArray f22258a;

    /* renamed from: b */
    private final CurrentParsingState f22259b = new CurrentParsingState();

    /* renamed from: c */
    private final StringBuilder f22260c = new StringBuilder();

    /* JADX INFO: Access modifiers changed from: package-private */
    public GeneralAppIdDecoder(BitArray huVar) {
        this.f22258a = huVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final String m2184a(StringBuilder sb, int i) throws NotFoundException, FormatException {
        String str = null;
        while (true) {
            DecodedInformation a = m2185a(i, str);
            String a2 = FieldParser.m2190a(a.f22246a);
            if (a2 != null) {
                sb.append(a2);
            }
            if (a.f22248c) {
                str = String.valueOf(a.f22247b);
            } else {
                str = null;
            }
            if (i == a.f22252d) {
                return sb.toString();
            }
            i = a.f22252d;
        }
    }

    /* renamed from: a */
    private boolean m2187a(int i) {
        if (i + 7 > this.f22258a.f21908b) {
            return i + 4 <= this.f22258a.f21908b;
        }
        int i2 = i;
        while (true) {
            int i3 = i + 3;
            if (i2 >= i3) {
                return this.f22258a.m2551a(i3);
            }
            if (this.f22258a.m2551a(i2)) {
                return true;
            }
            i2++;
        }
    }

    /* renamed from: b */
    private DecodedNumeric m2181b(int i) throws FormatException {
        int i2 = i + 7;
        if (i2 > this.f22258a.f21908b) {
            int a = m2186a(i, 4);
            if (a == 0) {
                return new DecodedNumeric(this.f22258a.f21908b, 10, 10);
            }
            return new DecodedNumeric(this.f22258a.f21908b, a - 1, 10);
        }
        int a2 = m2186a(i, 7) - 8;
        return new DecodedNumeric(i2, a2 / 11, a2 % 11);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final int m2186a(int i, int i2) {
        return m2183a(this.f22258a, i, i2);
    }

    /* renamed from: a */
    public static int m2183a(BitArray huVar, int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            if (huVar.m2551a(i + i4)) {
                i3 |= 1 << ((i2 - i4) - 1);
            }
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00e4 A[SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p110z1.DecodedInformation m2185a(int r8, java.lang.String r9) throws p110z1.FormatException {
        /*
            Method dump skipped, instructions count: 396
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.GeneralAppIdDecoder.m2185a(int, java.lang.String):z1.kp");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00d4 A[SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private p110z1.DecodedInformation m2188a() throws p110z1.FormatException {
        /*
            Method dump skipped, instructions count: 340
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.GeneralAppIdDecoder.m2188a():z1.kp");
    }

    /* renamed from: b */
    private BlockParsedResult m2182b() throws FormatException {
        boolean z;
        DecodedNumeric kqVar;
        DecodedInformation kpVar;
        while (true) {
            int i = this.f22259b.f22238a;
            boolean z2 = false;
            if (i + 7 <= this.f22258a.f21908b) {
                int i2 = i;
                while (true) {
                    int i3 = i + 3;
                    if (i2 >= i3) {
                        z = this.f22258a.m2551a(i3);
                        break;
                    } else if (this.f22258a.m2551a(i2)) {
                        z = true;
                        break;
                    } else {
                        i2++;
                    }
                }
            } else {
                z = i + 4 <= this.f22258a.f21908b;
            }
            if (z) {
                int i4 = this.f22259b.f22238a;
                int i5 = i4 + 7;
                if (i5 > this.f22258a.f21908b) {
                    int a = m2186a(i4, 4);
                    if (a == 0) {
                        kqVar = new DecodedNumeric(this.f22258a.f21908b, 10, 10);
                    } else {
                        kqVar = new DecodedNumeric(this.f22258a.f21908b, a - 1, 10);
                    }
                } else {
                    int a2 = m2186a(i4, 7) - 8;
                    kqVar = new DecodedNumeric(i5, a2 / 11, a2 % 11);
                }
                this.f22259b.f22238a = kqVar.f22252d;
                if (kqVar.f22250a == 10) {
                    z2 = true;
                }
                if (z2) {
                    if (kqVar.m2198a()) {
                        kpVar = new DecodedInformation(this.f22259b.f22238a, this.f22260c.toString());
                    } else {
                        kpVar = new DecodedInformation(this.f22259b.f22238a, this.f22260c.toString(), kqVar.f22251b);
                    }
                    return new BlockParsedResult(kpVar, true);
                }
                this.f22260c.append(kqVar.f22250a);
                if (kqVar.m2198a()) {
                    return new BlockParsedResult(new DecodedInformation(this.f22259b.f22238a, this.f22260c.toString()), true);
                }
                this.f22260c.append(kqVar.f22251b);
            } else {
                int i6 = this.f22259b.f22238a;
                if (i6 + 1 <= this.f22258a.f21908b) {
                    for (int i7 = 0; i7 < 4; i7++) {
                        int i8 = i7 + i6;
                        if (i8 >= this.f22258a.f21908b) {
                            break;
                        } else if (this.f22258a.m2551a(i8)) {
                            break;
                        }
                    }
                    z2 = true;
                }
                if (z2) {
                    this.f22259b.f22239b = CurrentParsingState.EnumC5389a.f22241b;
                    this.f22259b.m2212a(4);
                }
                return new BlockParsedResult();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0115 A[SYNTHETIC] */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private p110z1.BlockParsedResult m2180c() throws p110z1.FormatException {
        /*
            Method dump skipped, instructions count: 394
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.GeneralAppIdDecoder.m2180c():z1.km");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00c2 A[SYNTHETIC] */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private p110z1.BlockParsedResult m2178d() {
        /*
            Method dump skipped, instructions count: 278
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.GeneralAppIdDecoder.m2178d():z1.km");
    }

    /* renamed from: c */
    private boolean m2179c(int i) {
        int a;
        if (i + 5 > this.f22258a.f21908b) {
            return false;
        }
        int a2 = m2186a(i, 5);
        if (a2 >= 5 && a2 < 16) {
            return true;
        }
        if (i + 7 > this.f22258a.f21908b) {
            return false;
        }
        int a3 = m2186a(i, 7);
        if (a3 < 64 || a3 >= 116) {
            return i + 8 <= this.f22258a.f21908b && (a = m2186a(i, 8)) >= 232 && a < 253;
        }
        return true;
    }

    /* renamed from: d */
    private DecodedChar m2177d(int i) throws FormatException {
        char c;
        int a = m2186a(i, 5);
        if (a == 15) {
            return new DecodedChar(i + 5, Typography.f21050b);
        }
        if (a >= 5 && a < 15) {
            return new DecodedChar(i + 5, (char) ((a + 48) - 5));
        }
        int a2 = m2186a(i, 7);
        if (a2 >= 64 && a2 < 90) {
            return new DecodedChar(i + 7, (char) (a2 + 1));
        }
        if (a2 >= 90 && a2 < 116) {
            return new DecodedChar(i + 7, (char) (a2 + 7));
        }
        switch (m2186a(i, 8)) {
            case TbsListener.ErrorCode.INSTALL_SUCCESS_AND_RELEASE_LOCK /* 232 */:
                c = '!';
                break;
            case TbsListener.ErrorCode.DECOUPLE_INSTLL_SUCCESS /* 233 */:
                c = Typography.f21049a;
                break;
            case TbsListener.ErrorCode.DECOUPLE_INCURUPDATE_SUCCESS /* 234 */:
                c = '%';
                break;
            case TbsListener.ErrorCode.DECOUPLE_INCURUPDATE_FAIL /* 235 */:
                c = Typography.f21051c;
                break;
            case TbsListener.ErrorCode.TPATCH_INSTALL_SUCCESS /* 236 */:
                c = '\'';
                break;
            case TbsListener.ErrorCode.DECOUPLE_TPATCH_INSTALL_SUCCESS /* 237 */:
                c = '(';
                break;
            case TbsListener.ErrorCode.TPATCH_FAIL /* 238 */:
                c = ')';
                break;
            case TbsListener.ErrorCode.DECOUPLE_TPATCH_FAIL /* 239 */:
                c = '*';
                break;
            case TbsListener.ErrorCode.TPATCH_VERSION_FAILED /* 240 */:
                c = '+';
                break;
            case TbsListener.ErrorCode.TPATCH_BACKUP_NOT_VALID /* 241 */:
                c = ',';
                break;
            case TbsListener.ErrorCode.TPATCH_ENABLE_EXCEPTION /* 242 */:
                c = '-';
                break;
            case 243:
                c = FilenameUtils.EXTENSION_SEPARATOR;
                break;
            case 244:
                c = IOUtils.DIR_SEPARATOR_UNIX;
                break;
            case 245:
                c = ':';
                break;
            case 246:
                c = ';';
                break;
            case 247:
                c = Typography.f21052d;
                break;
            case 248:
                c = '=';
                break;
            case 249:
                c = Typography.f21053e;
                break;
            case ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION /* 250 */:
                c = '?';
                break;
            case 251:
                c = '_';
                break;
            case 252:
                c = ' ';
                break;
            default:
                throw FormatException.m2059a();
        }
        return new DecodedChar(i + 8, c);
    }

    /* renamed from: e */
    private boolean m2176e(int i) {
        int a;
        if (i + 5 > this.f22258a.f21908b) {
            return false;
        }
        int a2 = m2186a(i, 5);
        if (a2 < 5 || a2 >= 16) {
            return i + 6 <= this.f22258a.f21908b && (a = m2186a(i, 6)) >= 16 && a < 63;
        }
        return true;
    }

    /* renamed from: f */
    private DecodedChar m2175f(int i) {
        char c;
        int a = m2186a(i, 5);
        if (a == 15) {
            return new DecodedChar(i + 5, Typography.f21050b);
        }
        if (a >= 5 && a < 15) {
            return new DecodedChar(i + 5, (char) ((a + 48) - 5));
        }
        int a2 = m2186a(i, 6);
        if (a2 >= 32 && a2 < 58) {
            return new DecodedChar(i + 6, (char) (a2 + 33));
        }
        switch (a2) {
            case 58:
                c = '*';
                break;
            case 59:
                c = ',';
                break;
            case 60:
                c = '-';
                break;
            case 61:
                c = FilenameUtils.EXTENSION_SEPARATOR;
                break;
            case 62:
                c = IOUtils.DIR_SEPARATOR_UNIX;
                break;
            default:
                throw new IllegalStateException("Decoding invalid alphanumeric value: ".concat(String.valueOf(a2)));
        }
        return new DecodedChar(i + 6, c);
    }

    /* renamed from: g */
    private boolean m2174g(int i) {
        int i2;
        if (i + 1 > this.f22258a.f21908b) {
            return false;
        }
        for (int i3 = 0; i3 < 5 && (i2 = i3 + i) < this.f22258a.f21908b; i3++) {
            if (i3 == 2) {
                if (!this.f22258a.m2551a(i + 2)) {
                    return false;
                }
            } else if (this.f22258a.m2551a(i2)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: h */
    private boolean m2173h(int i) {
        int i2 = i + 3;
        if (i2 > this.f22258a.f21908b) {
            return false;
        }
        while (i < i2) {
            if (this.f22258a.m2551a(i)) {
                return false;
            }
            i++;
        }
        return true;
    }

    /* renamed from: i */
    private boolean m2172i(int i) {
        int i2;
        if (i + 1 > this.f22258a.f21908b) {
            return false;
        }
        for (int i3 = 0; i3 < 4 && (i2 = i3 + i) < this.f22258a.f21908b; i3++) {
            if (this.f22258a.m2551a(i2)) {
                return false;
            }
        }
        return true;
    }
}
