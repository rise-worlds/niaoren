package p110z1;

import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;
import com.nrzs.data.C1963a;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.sdk.TbsMediaPlayer;
import java.util.ArrayList;
import java.util.List;

/* renamed from: z1.lp */
/* loaded from: classes3.dex */
final class EANManufacturerOrgSupport {

    /* renamed from: a */
    final List<int[]> f22364a = new ArrayList();

    /* renamed from: b */
    final List<String> f22365b = new ArrayList();

    /* renamed from: a */
    private String m2099a(String str) {
        int[] iArr;
        int i;
        m2100a();
        int parseInt = Integer.parseInt(str.substring(0, 3));
        int size = this.f22364a.size();
        for (int i2 = 0; i2 < size && parseInt >= (i = (iArr = this.f22364a.get(i2))[0]); i2++) {
            if (iArr.length != 1) {
                i = iArr[1];
            }
            if (parseInt <= i) {
                return this.f22365b.get(i2);
            }
        }
        return null;
    }

    /* renamed from: a */
    private void m2098a(int[] iArr, String str) {
        this.f22364a.add(iArr);
        this.f22365b.add(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final synchronized void m2100a() {
        if (this.f22364a.isEmpty()) {
            m2098a(new int[]{0, 19}, "US/CA");
            m2098a(new int[]{30, 39}, "US");
            m2098a(new int[]{60, 139}, "US/CA");
            m2098a(new int[]{300, 379}, "FR");
            m2098a(new int[]{380}, "BG");
            m2098a(new int[]{383}, "SI");
            m2098a(new int[]{385}, "HR");
            m2098a(new int[]{387}, "BA");
            m2098a(new int[]{400, 440}, "DE");
            m2098a(new int[]{450, 459}, "JP");
            m2098a(new int[]{460, 469}, "RU");
            m2098a(new int[]{471}, "TW");
            m2098a(new int[]{474}, "EE");
            m2098a(new int[]{475}, "LV");
            m2098a(new int[]{476}, "AZ");
            m2098a(new int[]{477}, "LT");
            m2098a(new int[]{478}, "UZ");
            m2098a(new int[]{479}, "LK");
            m2098a(new int[]{480}, "PH");
            m2098a(new int[]{481}, "BY");
            m2098a(new int[]{482}, "UA");
            m2098a(new int[]{484}, "MD");
            m2098a(new int[]{485}, "AM");
            m2098a(new int[]{486}, "GE");
            m2098a(new int[]{487}, "KZ");
            m2098a(new int[]{489}, "HK");
            m2098a(new int[]{490, 499}, "JP");
            m2098a(new int[]{500, TbsListener.ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_FILEPATHISNULL}, "GB");
            m2098a(new int[]{520}, "GR");
            m2098a(new int[]{528}, ExpandedProductParsedResult.f21855b);
            m2098a(new int[]{529}, "CY");
            m2098a(new int[]{531}, "MK");
            m2098a(new int[]{535}, "MT");
            m2098a(new int[]{539}, "IE");
            m2098a(new int[]{540, 549}, "BE/LU");
            m2098a(new int[]{560}, "PT");
            m2098a(new int[]{569}, "IS");
            m2098a(new int[]{570, 579}, "DK");
            m2098a(new int[]{590}, "PL");
            m2098a(new int[]{594}, "RO");
            m2098a(new int[]{599}, "HU");
            m2098a(new int[]{600, UiMessage.CommandToUi.Command_Type.LOAD_PROFILE_VALUE}, "ZA");
            m2098a(new int[]{UiMessage.CommandToUi.Command_Type.SET_FW_SMOOTH_VALUE}, "GH");
            m2098a(new int[]{UiMessage.CommandToUi.Command_Type.FW_SET_LEFT_VALUE}, "BH");
            m2098a(new int[]{UiMessage.CommandToUi.Command_Type.FW_SET_TOP_VALUE}, "MU");
            m2098a(new int[]{UiMessage.CommandToUi.Command_Type.FW_SET_HEIGHT_VALUE}, "MA");
            m2098a(new int[]{613}, "DZ");
            m2098a(new int[]{616}, "KE");
            m2098a(new int[]{618}, "CI");
            m2098a(new int[]{619}, "TN");
            m2098a(new int[]{621}, "SY");
            m2098a(new int[]{622}, "EG");
            m2098a(new int[]{624}, "LY");
            m2098a(new int[]{625}, "JO");
            m2098a(new int[]{626}, "IR");
            m2098a(new int[]{627}, "KW");
            m2098a(new int[]{628}, "SA");
            m2098a(new int[]{629}, "AE");
            m2098a(new int[]{640, 649}, "FI");
            m2098a(new int[]{690, 695}, "CN");
            m2098a(new int[]{700, 709}, "NO");
            m2098a(new int[]{729}, "IL");
            m2098a(new int[]{730, 739}, "SE");
            m2098a(new int[]{740}, "GT");
            m2098a(new int[]{741}, "SV");
            m2098a(new int[]{742}, "HN");
            m2098a(new int[]{743}, "NI");
            m2098a(new int[]{744}, "CR");
            m2098a(new int[]{745}, "PA");
            m2098a(new int[]{746}, "DO");
            m2098a(new int[]{750}, "MX");
            m2098a(new int[]{754, 755}, "CA");
            m2098a(new int[]{759}, "VE");
            m2098a(new int[]{760, 769}, "CH");
            m2098a(new int[]{770}, "CO");
            m2098a(new int[]{773}, "UY");
            m2098a(new int[]{775}, "PE");
            m2098a(new int[]{777}, "BO");
            m2098a(new int[]{779}, "AR");
            m2098a(new int[]{C1963a.f10596l}, "CL");
            m2098a(new int[]{784}, "PY");
            m2098a(new int[]{785}, "PE");
            m2098a(new int[]{786}, "EC");
            m2098a(new int[]{789, TbsMediaPlayer.TbsMediaPlayerListener.MEDIA_INFO_BUFFERING_PERCENTAGE}, "BR");
            m2098a(new int[]{800, 839}, "IT");
            m2098a(new int[]{840, 849}, "ES");
            m2098a(new int[]{850}, "CU");
            m2098a(new int[]{858}, "SK");
            m2098a(new int[]{859}, "CZ");
            m2098a(new int[]{860}, "YU");
            m2098a(new int[]{865}, "MN");
            m2098a(new int[]{867}, "KP");
            m2098a(new int[]{868, 869}, "TR");
            m2098a(new int[]{870, 879}, C5277dw.f21346b);
            m2098a(new int[]{880}, "KR");
            m2098a(new int[]{885}, "TH");
            m2098a(new int[]{888}, "SG");
            m2098a(new int[]{890}, "IN");
            m2098a(new int[]{893}, "VN");
            m2098a(new int[]{896}, "PK");
            m2098a(new int[]{899}, "ID");
            m2098a(new int[]{TbsMediaPlayer.TbsMediaPlayerListener.MEDIA_INFO_TIMED_TEXT_ERROR, 919}, "AT");
            m2098a(new int[]{930, 939}, "AU");
            m2098a(new int[]{940, 949}, "AZ");
            m2098a(new int[]{955}, "MY");
            m2098a(new int[]{958}, "MO");
        }
    }
}
