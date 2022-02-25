package p110z1;

import android.support.p006v7.widget.helper.ItemTouchHelper;
import com.cyjh.mobileanjian.ipc.share.proto.IpcCommand;
import com.lody.virtual.helper.compat.IntentCompat;
import com.tencent.smtt.sdk.TbsListener;
import org.apache.tools.tar.TarConstants;

/* renamed from: z1.jf */
/* loaded from: classes3.dex */
public final class ErrorCorrection {

    /* renamed from: c */
    private static final int f22089c = 301;

    /* renamed from: a */
    private static final int[] f22087a = {5, 7, 10, 11, 12, 14, 18, 20, 24, 28, 36, 42, 48, 56, 62, 68};

    /* renamed from: b */
    private static final int[][] f22088b = {new int[]{TbsListener.ErrorCode.INCR_ERROR_DETAIL, 48, 15, 111, 62}, new int[]{23, 68, TbsListener.ErrorCode.NEEDDOWNLOAD_5, 134, TbsListener.ErrorCode.TPATCH_VERSION_FAILED, 92, 254}, new int[]{28, 24, 185, TbsListener.ErrorCode.STARTDOWNLOAD_7, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, 248, 116, 255, 110, 61}, new int[]{TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_5, 138, 205, 12, 194, TbsListener.ErrorCode.STARTDOWNLOAD_9, 39, 245, 60, 97, 120}, new int[]{41, 153, 158, 91, 61, 42, TbsListener.ErrorCode.NEEDDOWNLOAD_3, 213, 97, 178, 100, TbsListener.ErrorCode.TPATCH_ENABLE_EXCEPTION}, new int[]{156, 97, 192, 252, 95, 9, 157, 119, 138, 45, 18, 186, 83, 185}, new int[]{83, IntentCompat.IMMUTABLE_FLAGS, 100, 39, 188, 75, 66, 61, TbsListener.ErrorCode.TPATCH_BACKUP_NOT_VALID, 213, 109, IpcCommand.f8356aC, 94, 254, TbsListener.ErrorCode.CREATE_TEMP_CONF_ERROR, 48, 90, 188}, new int[]{15, IntentCompat.IMMUTABLE_FLAGS, 244, 9, TbsListener.ErrorCode.DECOUPLE_INSTLL_SUCCESS, 71, TbsListener.ErrorCode.STARTDOWNLOAD_9, 2, 188, TbsListener.ErrorCode.STARTDOWNLOAD_1, 153, TbsListener.ErrorCode.NEEDDOWNLOAD_6, 253, 79, 108, 82, 27, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_4, 186, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_2}, new int[]{52, 190, 88, 205, 109, 39, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_6, 21, TarConstants.PREFIXLEN, 197, 251, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, TarConstants.PREFIXLEN, 21, 5, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_2, 254, TbsListener.ErrorCode.DOWNLOAD_REDIRECT_EMPTY, 12, 181, 184, 96, 50, 193}, new int[]{211, TbsListener.ErrorCode.RENAME_FAIL, 43, 97, 71, 96, 103, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_4, 37, 151, TbsListener.ErrorCode.NEEDDOWNLOAD_TRUE, 53, 75, 34, 249, TbsListener.ErrorCode.THREAD_INIT_ERROR, 17, 138, 110, 213, TbsListener.ErrorCode.NEEDDOWNLOAD_2, 136, 120, 151, TbsListener.ErrorCode.DECOUPLE_INSTLL_SUCCESS, TbsListener.ErrorCode.STARTDOWNLOAD_9, 93, 255}, new int[]{245, 127, TbsListener.ErrorCode.TPATCH_ENABLE_EXCEPTION, TbsListener.ErrorCode.INCR_UPDATE_EXCEPTION, 130, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, TbsListener.ErrorCode.STARTDOWNLOAD_3, 181, 102, 120, 84, 179, TbsListener.ErrorCode.COPY_INSTALL_SUCCESS, 251, 80, 182, TbsListener.ErrorCode.INSTALL_FROM_UNZIP, 18, 2, 4, 68, 33, 101, 137, 95, 119, 115, 44, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_5, 184, 59, 25, TbsListener.ErrorCode.CREATE_TEMP_CONF_ERROR, 98, 81, 112}, new int[]{77, 193, 137, 31, 19, 38, 22, 153, 247, 105, TbsListener.ErrorCode.DOWNLOAD_HAS_COPY_TBS_ERROR, 2, 245, 133, TbsListener.ErrorCode.TPATCH_ENABLE_EXCEPTION, 8, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_5, 95, 100, 9, TbsListener.ErrorCode.STARTDOWNLOAD_8, 105, 214, 111, 57, TbsListener.ErrorCode.THREAD_INIT_ERROR, 21, 1, 253, 57, 54, 101, 248, 202, 69, 50, 150, TbsListener.ErrorCode.NONEEDDOWNLOAD_OTHER_PROCESS_DOWNLOADING, TbsListener.ErrorCode.DEXOAT_EXCEPTION, 5, 9, 5}, new int[]{245, 132, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_2, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, 96, 32, 117, 22, TbsListener.ErrorCode.TPATCH_FAIL, 133, TbsListener.ErrorCode.TPATCH_FAIL, TbsListener.ErrorCode.RENAME_FAIL, 205, 188, TbsListener.ErrorCode.DECOUPLE_TPATCH_INSTALL_SUCCESS, 87, 191, 106, 16, TbsListener.ErrorCode.NEEDDOWNLOAD_8, 118, 23, 37, 90, TbsListener.ErrorCode.NEEDDOWNLOAD_TRUE, 205, 131, 88, 120, 100, 66, 138, 186, TbsListener.ErrorCode.TPATCH_VERSION_FAILED, 82, 44, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_6, 87, 187, TbsListener.ErrorCode.NEEDDOWNLOAD_8, TbsListener.ErrorCode.STARTDOWNLOAD_1, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_5, 69, 213, 92, 253, TbsListener.ErrorCode.CREATE_TEMP_CONF_ERROR, 19}, new int[]{TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_5, 9, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, TbsListener.ErrorCode.TPATCH_FAIL, 12, 17, TbsListener.ErrorCode.COPY_INSTALL_SUCCESS, 208, 100, 29, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_5, TbsListener.ErrorCode.NEEDDOWNLOAD_TRUE, TbsListener.ErrorCode.RENAME_SUCCESS, 192, 215, TbsListener.ErrorCode.DECOUPLE_INCURUPDATE_FAIL, 150, 159, 36, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, 38, 200, 132, 54, TbsListener.ErrorCode.INCR_ERROR_DETAIL, TbsListener.ErrorCode.NEEDDOWNLOAD_7, TbsListener.ErrorCode.INCR_UPDATE_EXCEPTION, TbsListener.ErrorCode.DECOUPLE_INCURUPDATE_SUCCESS, 117, 203, 29, TbsListener.ErrorCode.INSTALL_SUCCESS_AND_RELEASE_LOCK, TbsListener.ErrorCode.NEEDDOWNLOAD_5, TbsListener.ErrorCode.TPATCH_FAIL, 22, 150, 201, 117, 62, 207, TbsListener.ErrorCode.STARTDOWNLOAD_5, 13, 137, 245, 127, 67, 247, 28, TarConstants.PREFIXLEN, 43, 203, 107, TbsListener.ErrorCode.DECOUPLE_INSTLL_SUCCESS, 53, TbsListener.ErrorCode.NEEDDOWNLOAD_4, 46}, new int[]{TbsListener.ErrorCode.TPATCH_ENABLE_EXCEPTION, 93, TbsListener.ErrorCode.STARTDOWNLOAD_10, 50, TbsListener.ErrorCode.NEEDDOWNLOAD_5, 210, 39, 118, 202, 188, 201, 189, TbsListener.ErrorCode.NEEDDOWNLOAD_4, 108, 196, 37, 185, 112, 134, TbsListener.ErrorCode.RENAME_SUCCESS, 245, 63, 197, 190, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 106, 185, TbsListener.ErrorCode.INCRUPDATE_INSTALL_SUCCESS, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_5, 64, 114, 71, TbsListener.ErrorCode.STARTDOWNLOAD_2, 44, TbsListener.ErrorCode.NEEDDOWNLOAD_8, 6, 27, TbsListener.ErrorCode.INCR_UPDATE_EXCEPTION, 51, 63, 87, 10, 40, 130, 188, 17, TbsListener.ErrorCode.STARTDOWNLOAD_4, 31, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_6, TbsListener.ErrorCode.NEEDDOWNLOAD_TRUE, 4, 107, TbsListener.ErrorCode.INSTALL_SUCCESS_AND_RELEASE_LOCK, 7, 94, TbsListener.ErrorCode.STARTDOWNLOAD_7, TbsListener.ErrorCode.EXCEED_INCR_UPDATE, TbsListener.ErrorCode.DOWNLOAD_REDIRECT_EMPTY, 86, 47, 11, 204}, new int[]{TbsListener.ErrorCode.COPY_INSTALL_SUCCESS, TbsListener.ErrorCode.INCR_ERROR_DETAIL, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_3, 89, 251, TbsListener.ErrorCode.NEEDDOWNLOAD_10, 159, 56, 89, 33, TbsListener.ErrorCode.NEEDDOWNLOAD_8, 244, 154, 36, 73, 127, 213, 136, 248, 180, TbsListener.ErrorCode.DECOUPLE_INCURUPDATE_SUCCESS, 197, 158, TbsListener.ErrorCode.NONEEDDOWNLOAD_OTHER_PROCESS_DOWNLOADING, 68, TbsListener.ErrorCode.DOWNLOAD_HAS_COPY_TBS_ERROR, 93, 213, 15, TbsListener.ErrorCode.STARTDOWNLOAD_1, TbsListener.ErrorCode.HOST_CONTEXT_IS_NULL, TbsListener.ErrorCode.TPATCH_INSTALL_SUCCESS, 66, 139, 153, 185, 202, TbsListener.ErrorCode.STARTDOWNLOAD_8, 179, 25, TbsListener.ErrorCode.COPY_INSTALL_SUCCESS, TbsListener.ErrorCode.INSTALL_SUCCESS_AND_RELEASE_LOCK, 96, 210, TbsListener.ErrorCode.RENAME_FAIL, 136, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, TbsListener.ErrorCode.DECOUPLE_TPATCH_FAIL, 181, TbsListener.ErrorCode.TPATCH_BACKUP_NOT_VALID, 59, 52, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_2, 25, 49, TbsListener.ErrorCode.INSTALL_SUCCESS_AND_RELEASE_LOCK, 211, 189, 64, 54, 108, 153, 132, 63, 96, 103, 82, 186}};

    /* renamed from: d */
    private static final int[] f22090d = new int[256];

    /* renamed from: e */
    private static final int[] f22091e = new int[255];

    static {
        int i = 1;
        for (int i2 = 0; i2 < 255; i2++) {
            f22091e[i2] = i;
            f22090d[i] = i2;
            i <<= 1;
            if (i >= 256) {
                i ^= 301;
            }
        }
    }

    private ErrorCorrection() {
    }

    /* renamed from: a */
    public static String m2310a(String str, SymbolInfo jhVar) {
        if (str.length() == jhVar.f22114b) {
            StringBuilder sb = new StringBuilder(jhVar.f22114b + jhVar.f22115c);
            sb.append(str);
            int a = jhVar.mo2293a();
            if (a == 1) {
                sb.append(m2312a(str, jhVar.f22115c));
            } else {
                sb.setLength(sb.capacity());
                int[] iArr = new int[a];
                int[] iArr2 = new int[a];
                int[] iArr3 = new int[a];
                int i = 0;
                while (i < a) {
                    int i2 = i + 1;
                    iArr[i] = jhVar.mo2292a(i2);
                    iArr2[i] = jhVar.f22118f;
                    iArr3[i] = 0;
                    if (i > 0) {
                        iArr3[i] = iArr3[i - 1] + iArr[i];
                    }
                    i = i2;
                }
                for (int i3 = 0; i3 < a; i3++) {
                    StringBuilder sb2 = new StringBuilder(iArr[i3]);
                    for (int i4 = i3; i4 < jhVar.f22114b; i4 += a) {
                        sb2.append(str.charAt(i4));
                    }
                    String a2 = m2312a(sb2.toString(), iArr2[i3]);
                    int i5 = 0;
                    for (int i6 = i3; i6 < iArr2[i3] * a; i6 += a) {
                        i5++;
                        sb.setCharAt(jhVar.f22114b + i6, a2.charAt(i5));
                    }
                }
            }
            return sb.toString();
        }
        throw new IllegalArgumentException("The number of codewords does not match the selected symbol");
    }

    /* renamed from: a */
    private static String m2312a(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        while (true) {
            int[] iArr = f22087a;
            if (i2 >= iArr.length) {
                i2 = -1;
                break;
            } else if (iArr[i2] == i) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 >= 0) {
            int[] iArr2 = f22088b[i2];
            char[] cArr = new char[i];
            for (int i3 = 0; i3 < i; i3++) {
                cArr[i3] = 0;
            }
            for (int i4 = 0; i4 < length + 0; i4++) {
                int i5 = i - 1;
                int charAt = cArr[i5] ^ charSequence.charAt(i4);
                while (i5 > 0) {
                    if (charAt == 0 || iArr2[i5] == 0) {
                        cArr[i5] = cArr[i5 - 1];
                    } else {
                        char c = cArr[i5 - 1];
                        int[] iArr3 = f22091e;
                        int[] iArr4 = f22090d;
                        cArr[i5] = (char) (c ^ iArr3[(iArr4[charAt] + iArr4[iArr2[i5]]) % 255]);
                    }
                    i5--;
                }
                if (charAt == 0 || iArr2[0] == 0) {
                    cArr[0] = 0;
                } else {
                    int[] iArr5 = f22091e;
                    int[] iArr6 = f22090d;
                    cArr[0] = (char) iArr5[(iArr6[charAt] + iArr6[iArr2[0]]) % 255];
                }
            }
            char[] cArr2 = new char[i];
            for (int i6 = 0; i6 < i; i6++) {
                cArr2[i6] = cArr[(i - i6) - 1];
            }
            return String.valueOf(cArr2);
        }
        throw new IllegalArgumentException("Illegal number of error correction codewords specified: ".concat(String.valueOf(i)));
    }

    /* renamed from: a */
    private static String m2311a(CharSequence charSequence, int i, int i2) {
        int i3 = 0;
        while (true) {
            int[] iArr = f22087a;
            if (i3 >= iArr.length) {
                i3 = -1;
                break;
            } else if (iArr[i3] == i2) {
                break;
            } else {
                i3++;
            }
        }
        if (i3 >= 0) {
            int[] iArr2 = f22088b[i3];
            char[] cArr = new char[i2];
            for (int i4 = 0; i4 < i2; i4++) {
                cArr[i4] = 0;
            }
            for (int i5 = 0; i5 < i + 0; i5++) {
                int i6 = i2 - 1;
                int charAt = cArr[i6] ^ charSequence.charAt(i5);
                while (i6 > 0) {
                    if (charAt == 0 || iArr2[i6] == 0) {
                        cArr[i6] = cArr[i6 - 1];
                    } else {
                        char c = cArr[i6 - 1];
                        int[] iArr3 = f22091e;
                        int[] iArr4 = f22090d;
                        cArr[i6] = (char) (c ^ iArr3[(iArr4[charAt] + iArr4[iArr2[i6]]) % 255]);
                    }
                    i6--;
                }
                if (charAt == 0 || iArr2[0] == 0) {
                    cArr[0] = 0;
                } else {
                    int[] iArr5 = f22091e;
                    int[] iArr6 = f22090d;
                    cArr[0] = (char) iArr5[(iArr6[charAt] + iArr6[iArr2[0]]) % 255];
                }
            }
            char[] cArr2 = new char[i2];
            for (int i7 = 0; i7 < i2; i7++) {
                cArr2[i7] = cArr[(i2 - i7) - 1];
            }
            return String.valueOf(cArr2);
        }
        throw new IllegalArgumentException("Illegal number of error correction codewords specified: ".concat(String.valueOf(i2)));
    }
}
