package p110z1;

import android.support.v4.view.InputDeviceCompat;
import android.support.v7.widget.helper.ItemTouchHelper;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.cyjh.mobileanjian.ipc.share.proto.IpcCommand;
import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;
import com.nrzs.data.C1963a;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.sdk.TbsMediaPlayer;
import org.apache.http.HttpStatus;
import org.apache.tools.tar.TarConstants;

/* renamed from: z1.nb */
/* loaded from: classes3.dex */
public final class PDF417ErrorCorrection {

    /* renamed from: a */
    private static final int[][] f22549a = {new int[]{27, 917}, new int[]{522, 568, 723, 809}, new int[]{TbsListener.ErrorCode.DECOUPLE_TPATCH_INSTALL_SUCCESS, 308, 436, 284, 646, 653, 428, 379}, new int[]{ResultTypeConstant.f7202o, 562, TbsListener.ErrorCode.INSTALL_SUCCESS_AND_RELEASE_LOCK, 755, 599, 524, 801, 132, 295, 116, 442, 428, 295, 42, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_6, 65}, new int[]{361, 575, 922, 525, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_6, 586, 640, TbsListener.ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_01, 536, 742, 677, 742, 687, 284, 193, 517, ResultTypeConstant.f7200m, 494, TarConstants.VERSION_OFFSET, TbsListener.ErrorCode.NEEDDOWNLOAD_8, 593, 800, 571, 320, 803, 133, TbsListener.ErrorCode.RENAME_FAIL, 390, 685, TbsListener.ErrorCode.ERROR_QBSDK_INIT_ERROR_RET_TYPE_NOT_BUNDLE, 63, 410}, new int[]{539, HttpStatus.SC_UNPROCESSABLE_ENTITY, 6, 93, 862, 771, 453, 106, UiMessage.CommandToUi.Command_Type.FW_SET_WIDTH_VALUE, 287, 107, 505, 733, 877, 381, UiMessage.CommandToUi.Command_Type.FW_SET_TEXT_VALUE, 723, 476, 462, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_2, 430, UiMessage.CommandToUi.Command_Type.FW_SET_TOP_VALUE, 858, 822, 543, 376, 511, 400, 672, 762, 283, 184, 440, 35, 519, 31, 460, 594, TbsListener.ErrorCode.CREATE_TEMP_CONF_ERROR, 535, 517, 352, UiMessage.CommandToUi.Command_Type.FW_ZORDER_VALUE, 158, 651, 201, 488, 502, 648, 733, 717, 83, 404, 97, 280, 771, 840, 629, 4, 381, 843, 623, 264, 543}, new int[]{521, UiMessage.CommandToUi.Command_Type.SET_FW_TEXT_COLOR_VALUE, 864, 547, 858, 580, 296, 379, 53, 779, 897, 444, 400, 925, 749, 415, 822, 93, 217, 208, PDF417Common.f22405b, 244, 583, 620, 246, TbsListener.ErrorCode.NEEDDOWNLOAD_9, 447, 631, 292, 908, 490, TbsListener.ErrorCode.INFO_COOKIE_SWITCH_TRANSFER, 516, 258, 457, 907, 594, 723, 674, 292, ResultTypeConstant.f7199l, 96, 684, 432, 686, UiMessage.CommandToUi.Command_Type.FW_OPACITY_VALUE, 860, 569, 193, TbsListener.ErrorCode.RENAME_EXCEPTION, IpcCommand.f8356aC, 186, TbsListener.ErrorCode.TPATCH_INSTALL_SUCCESS, 287, 192, 775, 278, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_3, 40, 379, 712, 463, 646, 776, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_1, 491, 297, 763, 156, 732, 95, 270, 447, 90, 507, 48, TbsListener.ErrorCode.INCR_ERROR_DETAIL, 821, 808, 898, 784, 663, 627, 378, 382, IpcCommand.f8363aJ, 380, UiMessage.CommandToUi.Command_Type.SAVE_PROFILE_VALUE, 754, 336, 89, 614, 87, 432, 670, 616, 157, 374, TbsListener.ErrorCode.TPATCH_ENABLE_EXCEPTION, 726, 600, 269, 375, 898, 845, 454, 354, 130, 814, 587, 804, 34, 211, TbsListener.ErrorCode.ERROR_QBSDK_INIT_ERROR_RET_TYPE_NOT_BUNDLE, 539, 297, 827, 865, 37, 517, 834, 315, 550, 86, 801, 4, 108, 539}, new int[]{524, 894, 75, 766, 882, 857, 74, 204, 82, 586, 708, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 905, 786, 138, 720, 858, 194, UiMessage.CommandToUi.Command_Type.SET_FW_TEXT_SIZE_VALUE, 913, ResultTypeConstant.f7207t, 190, 375, 850, 438, 733, 194, 280, 201, 280, 828, 757, 710, 814, 919, 89, 68, 569, 11, 204, 796, UiMessage.CommandToUi.Command_Type.FW_ZORDER_VALUE, 540, 913, 801, 700, 799, 137, 439, TbsListener.ErrorCode.INFO_CORE_EXIST_NOT_LOAD, 592, 668, 353, 859, 370, 694, TbsListener.ErrorCode.THROWABLE_INITX5CORE, TbsListener.ErrorCode.TPATCH_VERSION_FAILED, 216, 257, 284, 549, 209, 884, 315, 70, TbsListener.ErrorCode.ERROR_GETSTRINGARRAY_JARFILE, 793, 490, ResultTypeConstant.f7202o, 877, TbsListener.ErrorCode.STARTDOWNLOAD_3, 749, 812, 684, 461, 334, 376, 849, 521, 307, 291, 803, 712, 19, 358, 399, 908, 103, 511, 51, 8, 517, TbsListener.ErrorCode.CREATE_TEMP_CONF_ERROR, 289, 470, 637, 731, 66, 255, 917, 269, 463, 830, 730, 433, 848, 585, 136, 538, 906, 90, 2, 290, 743, 199, 655, 903, TbsListener.ErrorCode.ERROR_GETSTRINGARRAY_JARFILE, 49, 802, 580, 355, 588, 188, 462, 10, 134, 628, 320, 479, 130, 739, 71, TarConstants.VERSION_OFFSET, TbsListener.ErrorCode.ERROR_QBSDK_INIT_ISSUPPORT, 374, UiMessage.CommandToUi.Command_Type.LOAD_PROFILE_VALUE, 192, UiMessage.CommandToUi.Command_Type.FW_ZORDER_VALUE, TbsListener.ErrorCode.NEEDDOWNLOAD_3, 673, 687, TbsListener.ErrorCode.DECOUPLE_INCURUPDATE_SUCCESS, 722, 384, TbsListener.ErrorCode.NONEEDDOWNLOAD_OTHER_PROCESS_DOWNLOADING, TbsMediaPlayer.TbsMediaPlayerListener.MEDIA_INFO_HAVE_VIDEO_DATA, UiMessage.CommandToUi.Command_Type.FW_GET_VALUE_VALUE, 640, 455, 193, 689, 707, 805, 641, 48, 60, 732, 621, 895, 544, IpcCommand.f8362aI, 852, 655, UiMessage.CommandToUi.Command_Type.SET_FW_BG_VALUE, 697, 755, 756, 60, TbsListener.ErrorCode.RENAME_FAIL, 773, 434, 421, 726, 528, 503, 118, 49, 795, 32, TbsListener.ErrorCode.NEEDDOWNLOAD_5, 500, TbsListener.ErrorCode.TPATCH_FAIL, 836, 394, 280, 566, TbsListener.ErrorCode.ERROR_QBSDK_INIT_CANLOADX5, 9, 647, 550, 73, 914, 342, TbsListener.ErrorCode.PV_UPLOAD_ERROR, 32, 681, TbsListener.ErrorCode.ERROR_QBSDK_INIT_ERROR_EMPTY_BUNDLE, 792, 620, 60, UiMessage.CommandToUi.Command_Type.FW_SET_TOP_VALUE, 441, 180, 791, 893, 754, UiMessage.CommandToUi.Command_Type.FW_ZORDER_VALUE, 383, TbsListener.ErrorCode.INCR_ERROR_DETAIL, 749, 760, 213, 54, 297, 134, 54, 834, 299, 922, 191, 910, 532, UiMessage.CommandToUi.Command_Type.FW_SET_TOP_VALUE, 829, 189, 20, TbsListener.ErrorCode.STARTDOWNLOAD_8, 29, 872, 449, 83, 402, 41, 656, 505, 579, 481, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_3, 404, 251, 688, 95, 497, 555, 642, 543, 307, 159, 924, 558, 648, 55, 497, 10}, new int[]{352, 77, 373, 504, 35, 599, 428, 207, 409, 574, 118, 498, 285, 380, 350, 492, 197, 265, 920, TarConstants.PREFIXLEN, 914, 299, TbsListener.ErrorCode.INSTALL_FROM_UNZIP, 643, 294, 871, 306, 88, 87, 193, 352, 781, 846, 75, TbsListener.ErrorCode.TEST_THROWABLE_ISNOT_NULL, 520, 435, 543, 203, 666, 249, 346, 781, 621, 640, 268, 794, 534, 539, 781, 408, 390, 644, 102, 476, 499, 290, 632, 545, 37, 858, 916, 552, 41, 542, 289, TbsListener.ErrorCode.DOWNLOAD_HAS_COPY_TBS_ERROR, ResultTypeConstant.f7199l, 383, 800, 485, 98, TbsMediaPlayer.TbsMediaPlayerListener.MEDIA_INFO_HAVE_VIDEO_DATA, 472, 761, 107, 784, 860, 658, 741, 290, 204, 681, 407, 855, 85, 99, 62, 482, 180, 20, 297, 451, 593, 913, TbsListener.ErrorCode.NEEDDOWNLOAD_3, 808, 684, 287, 536, 561, 76, 653, 899, 729, 567, 744, 390, InputDeviceCompat.SOURCE_DPAD, 192, 516, 258, TbsListener.ErrorCode.TPATCH_VERSION_FAILED, 518, 794, 395, 768, 848, 51, UiMessage.CommandToUi.Command_Type.FW_SET_WIDTH_VALUE, 384, TbsListener.ErrorCode.STARTDOWNLOAD_9, 190, 826, TbsListener.ErrorCode.THROWABLE_INITTESRUNTIMEENVIRONMENT, 596, 786, 303, 570, 381, 415, 641, 156, TbsListener.ErrorCode.DECOUPLE_TPATCH_INSTALL_SUCCESS, 151, 429, 531, 207, 676, 710, 89, TbsListener.ErrorCode.STARTDOWNLOAD_9, 304, 402, 40, 708, 575, TbsListener.ErrorCode.STARTDOWNLOAD_3, 864, TbsListener.ErrorCode.INSTALL_FROM_UNZIP, 65, 861, 841, 512, TbsListener.ErrorCode.STARTDOWNLOAD_5, 477, TbsListener.ErrorCode.INCRUPDATE_INSTALL_SUCCESS, 92, 358, 785, 288, 357, 850, 836, 827, 736, 707, 94, 8, 494, 114, 521, 2, 499, 851, 543, 152, 729, 771, 95, 248, 361, 578, TbsListener.ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_03, 856, 797, 289, 51, 684, 466, 533, 820, 669, 45, TbsMediaPlayer.TbsMediaPlayerListener.MEDIA_INFO_SUBTITLE_TIMED_OUT, 452, TbsListener.ErrorCode.STARTDOWNLOAD_8, 342, 244, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_3, 35, 463, 651, 51, 699, 591, 452, 578, 37, TbsListener.ErrorCode.DOWNLOAD_REDIRECT_EMPTY, 298, 332, 552, 43, 427, 119, 662, 777, 475, 850, 764, 364, 578, 911, 283, 711, 472, 420, 245, 288, 594, 394, 511, TbsListener.ErrorCode.TEST_THROWABLE_ISNOT_NULL, 589, 777, 699, 688, 43, 408, 842, 383, 721, 521, 560, 644, 714, 559, 62, TbsListener.ErrorCode.NEEDDOWNLOAD_6, 873, 663, 713, 159, 672, 729, 624, 59, 193, 417, 158, 209, 563, 564, 343, 693, 109, UiMessage.CommandToUi.Command_Type.FW_SET_LEFT_VALUE, 563, 365, 181, 772, 677, UiMessage.CommandToUi.Command_Type.SET_FW_TEXT_COLOR_VALUE, 248, 353, 708, 410, 579, 870, 617, 841, 632, 860, 289, 536, 35, 777, 618, 586, HttpStatus.SC_FAILED_DEPENDENCY, 833, 77, 597, 346, 269, 757, 632, 695, TbsMediaPlayer.TbsMediaPlayerListener.MEDIA_INFO_NO_VIDEO_DATA, TbsListener.ErrorCode.ERROR_QBSDK_INIT_ERROR_EMPTY_BUNDLE, 247, 184, 45, 787, 680, 18, 66, 407, 369, 54, 492, TbsListener.ErrorCode.INCR_ERROR_DETAIL, 613, 830, 922, 437, 519, 644, 905, 789, 420, 305, 441, 207, 300, 892, 827, TbsListener.ErrorCode.NEEDDOWNLOAD_2, 537, 381, 662, InputDeviceCompat.SOURCE_DPAD, 56, 252, 341, TbsListener.ErrorCode.TPATCH_ENABLE_EXCEPTION, 797, 838, 837, 720, TbsListener.ErrorCode.EXCEED_INCR_UPDATE, 307, 631, 61, 87, 560, UiMessage.CommandToUi.Command_Type.SET_FW_TEXT_COLOR_VALUE, 756, 665, 397, 808, 851, UiMessage.CommandToUi.Command_Type.SET_FW_BG_VALUE, 473, 795, 378, 31, 647, 915, 459, 806, 590, 731, 425, 216, 548, 249, TbsListener.ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_01, 881, 699, 535, 673, 782, 210, 815, 905, 303, 843, 922, 281, 73, 469, 791, 660, TbsListener.ErrorCode.STARTDOWNLOAD_3, 498, 308, TarConstants.PREFIXLEN, HttpStatus.SC_UNPROCESSABLE_ENTITY, 907, 817, 187, 62, 16, 425, 535, 336, 286, 437, 375, ResultTypeConstant.f7200m, UiMessage.CommandToUi.Command_Type.FW_SET_WIDTH_VALUE, 296, 183, 923, 116, 667, TbsMediaPlayer.TbsMediaPlayerListener.MEDIA_INFO_NO_VIDEO_DATA, 353, 62, 366, 691, 379, 687, 842, 37, 357, 720, 742, TbsListener.ErrorCode.ERROR_QBSDK_INIT_ERROR_RET_TYPE_NOT_BUNDLE, 5, 39, 923, UiMessage.CommandToUi.Command_Type.SET_FW_TEXT_SIZE_VALUE, HttpStatus.SC_FAILED_DEPENDENCY, TbsListener.ErrorCode.TPATCH_ENABLE_EXCEPTION, 749, TbsListener.ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_01, 54, 669, 316, 342, 299, 534, 105, 667, 488, 640, 672, 576, 540, 316, 486, 721, UiMessage.CommandToUi.Command_Type.FW_SET_WIDTH_VALUE, 46, 656, 447, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_1, 616, 464, 190, 531, 297, TbsListener.ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_01, 762, TbsMediaPlayer.TbsMediaPlayerListener.MEDIA_INFO_HAVE_VIDEO_DATA, 533, TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_5, 134, 14, 381, 433, 717, 45, 111, 20, 596, 284, 736, 138, 646, 411, 877, 669, TbsListener.ErrorCode.NEEDDOWNLOAD_2, 919, 45, C1963a.f10596l, 407, TbsListener.ErrorCode.STARTDOWNLOAD_5, 332, 899, TbsListener.ErrorCode.STARTDOWNLOAD_6, 726, 600, TbsListener.ErrorCode.THROWABLE_INITX5CORE, 498, 655, 357, TbsMediaPlayer.TbsMediaPlayerListener.MEDIA_INFO_HAVE_VIDEO_DATA, 768, TbsListener.ErrorCode.EXCEED_LZMA_RETRY_NUM, 849, 647, 63, UiMessage.CommandToUi.Command_Type.SET_FW_TEXT_COLOR_VALUE, 863, 251, 366, 304, 282, 738, 675, 410, 389, 244, 31, TbsListener.ErrorCode.THREAD_INIT_ERROR, 303, TarConstants.VERSION_OFFSET}};

    private PDF417ErrorCorrection() {
    }

    /* renamed from: a */
    public static int m1872a(int i) {
        if (i >= 0 && i <= 8) {
            return 1 << (i + 1);
        }
        throw new IllegalArgumentException("Error correction level must be between 0 and 8!");
    }

    /* renamed from: b */
    private static int m1870b(int i) throws WriterException {
        if (i <= 0) {
            throw new IllegalArgumentException("n must be > 0");
        } else if (i <= 40) {
            return 2;
        } else {
            if (i <= 160) {
                return 3;
            }
            if (i <= 320) {
                return 4;
            }
            if (i <= 863) {
                return 5;
            }
            throw new WriterException("No recommendation possible");
        }
    }

    /* renamed from: a */
    public static String m1871a(CharSequence charSequence, int i) {
        int a = m1872a(i);
        char[] cArr = new char[a];
        int length = charSequence.length();
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = a - 1;
            int charAt = (charSequence.charAt(i2) + cArr[i3]) % PDF417Common.f22404a;
            while (i3 > 0) {
                cArr[i3] = (char) ((cArr[i3 - 1] + (929 - ((f22549a[i][i3] * charAt) % PDF417Common.f22404a))) % PDF417Common.f22404a);
                i3--;
            }
            cArr[0] = (char) ((929 - ((charAt * f22549a[i][0]) % PDF417Common.f22404a)) % PDF417Common.f22404a);
        }
        StringBuilder sb = new StringBuilder(a);
        for (int i4 = a - 1; i4 >= 0; i4--) {
            if (cArr[i4] != 0) {
                cArr[i4] = (char) (929 - cArr[i4]);
            }
            sb.append(cArr[i4]);
        }
        return sb.toString();
    }
}
