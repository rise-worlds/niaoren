package com.tencent.smtt.sdk;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import com.tencent.smtt.sdk.p078a.MttLoader;
import com.tencent.smtt.utils.Apn;
import org.apache.http.cookie.ClientCookie;

/* loaded from: classes2.dex */
public class TbsReaderView extends FrameLayout {
    public static final String IS_BAR_ANIMATING = "is_bar_animating";
    public static final String IS_BAR_SHOWING = "is_bar_show";
    public static final String IS_INTO_DOWNLOADING = "into_downloading";
    public static final String KEY_FILE_PATH = "filePath";
    public static final String KEY_TEMP_PATH = "tempPath";
    public static final int READER_CHANNEL_DOC_ID = 10965;
    public static final int READER_CHANNEL_PDF_ID = 10834;
    public static final int READER_CHANNEL_PPT_ID = 10833;
    public static final int READER_CHANNEL_TXT_ID = 10835;
    public static final String READER_STATISTICS_COUNT_CANCEL_LOADED_BTN = "AHNG802";
    public static final String READER_STATISTICS_COUNT_CLICK_LOADED_BTN = "AHNG801";
    public static final String READER_STATISTICS_COUNT_DOC_INTO_BROWSER = "AHNG829";
    public static final String READER_STATISTICS_COUNT_DOC_INTO_DIALOG = "AHNG827";
    public static final String READER_STATISTICS_COUNT_DOC_INTO_DOWNLOAD = "AHNG828";
    public static final String READER_STATISTICS_COUNT_DOC_SEARCH_BTN = "AHNG826";
    public static final String READER_STATISTICS_COUNT_PDF_FOLDER_BTN = "AHNG810";
    public static final String READER_STATISTICS_COUNT_PDF_INTO_BROWSER = "AHNG813";
    public static final String READER_STATISTICS_COUNT_PDF_INTO_DIALOG = "AHNG811";
    public static final String READER_STATISTICS_COUNT_PDF_INTO_DOWNLOAD = "AHNG812";
    public static final String READER_STATISTICS_COUNT_PPT_INTO_BROWSER = "AHNG809";
    public static final String READER_STATISTICS_COUNT_PPT_INTO_DIALOG = "AHNG807";
    public static final String READER_STATISTICS_COUNT_PPT_INTO_DOWNLOAD = "AHNG808";
    public static final String READER_STATISTICS_COUNT_PPT_PLAY_BTN = "AHNG806";
    public static final String READER_STATISTICS_COUNT_RETRY_BTN = "AHNG803";
    public static final String READER_STATISTICS_COUNT_TXT_INTO_BROWSER = "AHNG817";
    public static final String READER_STATISTICS_COUNT_TXT_INTO_DIALOG = "AHNG815";
    public static final String READER_STATISTICS_COUNT_TXT_INTO_DOWNLOAD = "AHNG816";
    public static final String READER_STATISTICS_COUNT_TXT_NOVEL_BTN = "AHNG814";
    public static final String TAG = "TbsReaderView";

    /* renamed from: f */
    static boolean f12965f = false;
    public static String gReaderPackName = "";
    public static String gReaderPackVersion = "";

    /* renamed from: a */
    Context f12966a;

    /* renamed from: b */
    ReaderWizard f12967b = null;

    /* renamed from: c */
    Object f12968c = null;

    /* renamed from: d */
    ReaderCallback f12969d;

    /* renamed from: e */
    ReaderCallback f12970e;

    /* loaded from: classes2.dex */
    public interface ReaderCallback {
        public static final int COPY_SELECT_TEXT = 5003;
        public static final int GET_BAR_ANIMATING = 5000;
        public static final int GET_BAR_ISSHOWING = 5024;
        public static final int HIDDEN_BAR = 5001;
        public static final int INSTALL_QB = 5011;
        public static final int NOTIFY_CANDISPLAY = 12;
        public static final int NOTIFY_ERRORCODE = 19;
        public static final int READER_OPEN_QQ_FILE_LIST = 5031;
        public static final int READER_PDF_LIST = 5008;
        public static final int READER_PLUGIN_ACTIVITY_PAUSE = 5032;
        public static final int READER_PLUGIN_CANLOAD = 5013;
        public static final int READER_PLUGIN_COMMAND_FIXSCREEN = 5015;
        public static final int READER_PLUGIN_COMMAND_PDF_LIST = 5036;
        public static final int READER_PLUGIN_COMMAND_PPT_PLAYER = 5035;
        public static final int READER_PLUGIN_COMMAND_ROTATESCREEN = 5018;
        public static final int READER_PLUGIN_COMMAND_TEXT_FIND = 5038;
        public static final int READER_PLUGIN_COMMAND_TEXT_FIND_CLEAR = 5041;
        public static final int READER_PLUGIN_COMMAND_TEXT_FIND_NEXT = 5039;
        public static final int READER_PLUGIN_COMMAND_TEXT_FIND_PREV = 5040;
        public static final int READER_PLUGIN_DOWNLOADING = 5014;
        public static final int READER_PLUGIN_RES_DOC_GUIDE = 5029;
        public static final int READER_PLUGIN_RES_FIXSCREEN_NORMAL = 5016;
        public static final int READER_PLUGIN_RES_FIXSCREEN_PRESS = 5017;
        public static final int READER_PLUGIN_RES_PDF_GUIDE = 5023;
        public static final int READER_PLUGIN_RES_PPT_GUIDE = 5021;
        public static final int READER_PLUGIN_RES_ROTATESCREEN_NORMAL = 5019;
        public static final int READER_PLUGIN_RES_ROTATESCREEN_PRESS = 5020;
        public static final int READER_PLUGIN_RES_TXT_GUIDE = 5022;
        public static final int READER_PLUGIN_SO_ERR = 5025;
        public static final int READER_PLUGIN_SO_INTO_START = 5027;
        public static final int READER_PLUGIN_SO_PROGRESS = 5028;
        public static final int READER_PLUGIN_SO_VERSION = 5030;
        public static final int READER_PLUGIN_STATUS = 5012;
        public static final int READER_PLUGIN_TEXT_FIND_RESULT = 5042;
        public static final int READER_PPT_PLAY_MODEL = 5009;
        public static final int READER_SEARCH_IN_DOCUMENT = 5026;
        public static final int READER_TOAST = 5005;
        public static final int READER_TXT_READING_MODEL = 5010;
        public static final int SEARCH_SELECT_TEXT = 5004;
        public static final int SHOW_BAR = 5002;
        public static final int SHOW_DIALOG = 5006;

        void onCallBackAction(Integer num, Object obj, Object obj2);
    }

    public TbsReaderView(Context context, ReaderCallback readerCallback) throws RuntimeException {
        super(context.getApplicationContext());
        this.f12966a = null;
        this.f12969d = null;
        this.f12970e = null;
        if (context instanceof Activity) {
            this.f12969d = readerCallback;
            this.f12966a = context;
            this.f12970e = new ReaderCallback() { // from class: com.tencent.smtt.sdk.TbsReaderView.1
                @Override // com.tencent.smtt.sdk.TbsReaderView.ReaderCallback
                public void onCallBackAction(Integer num, Object obj, Object obj2) {
                    Bundle bundle;
                    Bundle bundle2;
                    Bundle bundle3;
                    Bundle bundle4;
                    int intValue = num.intValue();
                    boolean z = true;
                    if (intValue != 5026) {
                        if (intValue != 5030) {
                            switch (intValue) {
                                case ReaderCallback.READER_PDF_LIST /* 5008 */:
                                    if (MttLoader.m16873c(TbsReaderView.this.f12966a)) {
                                        String str = "";
                                        if (obj != null) {
                                            Bundle bundle5 = (Bundle) obj;
                                            str = bundle5.getString("docpath");
                                            bundle2 = bundle5;
                                        } else {
                                            bundle2 = null;
                                        }
                                        QbSdk.startQBForDoc(TbsReaderView.this.f12966a, str, 4, 0, "pdf", bundle2);
                                        TbsReaderView.this.userStatistics(TbsReaderView.READER_STATISTICS_COUNT_PDF_INTO_BROWSER);
                                        break;
                                    } else {
                                        num = Integer.valueOf((int) ReaderCallback.INSTALL_QB);
                                        String resString = TbsReaderView.getResString(TbsReaderView.this.f12966a, ReaderCallback.READER_PLUGIN_RES_PDF_GUIDE);
                                        Bundle bundle6 = new Bundle();
                                        bundle6.putString("tip", resString);
                                        bundle6.putString("statistics", TbsReaderView.READER_STATISTICS_COUNT_PDF_INTO_DOWNLOAD);
                                        bundle6.putInt("channel_id", TbsReaderView.READER_CHANNEL_PDF_ID);
                                        TbsReaderView.this.userStatistics(TbsReaderView.READER_STATISTICS_COUNT_PDF_INTO_DIALOG);
                                        obj = bundle6;
                                        z = false;
                                        break;
                                    }
                                case ReaderCallback.READER_PPT_PLAY_MODEL /* 5009 */:
                                    if (MttLoader.m16873c(TbsReaderView.this.f12966a)) {
                                        String str2 = "";
                                        if (obj != null) {
                                            Bundle bundle7 = (Bundle) obj;
                                            str2 = bundle7.getString("docpath");
                                            bundle3 = bundle7;
                                        } else {
                                            bundle3 = null;
                                        }
                                        QbSdk.startQBForDoc(TbsReaderView.this.f12966a, str2, 4, 0, "", bundle3);
                                        TbsReaderView.this.userStatistics(TbsReaderView.READER_STATISTICS_COUNT_PPT_INTO_BROWSER);
                                        break;
                                    } else {
                                        num = Integer.valueOf((int) ReaderCallback.INSTALL_QB);
                                        String resString2 = TbsReaderView.getResString(TbsReaderView.this.f12966a, ReaderCallback.READER_PLUGIN_RES_PPT_GUIDE);
                                        Bundle bundle8 = new Bundle();
                                        bundle8.putString("tip", resString2);
                                        bundle8.putString("statistics", TbsReaderView.READER_STATISTICS_COUNT_PPT_INTO_DOWNLOAD);
                                        bundle8.putInt("channel_id", TbsReaderView.READER_CHANNEL_PPT_ID);
                                        TbsReaderView.this.userStatistics(TbsReaderView.READER_STATISTICS_COUNT_PPT_INTO_DIALOG);
                                        obj = bundle8;
                                        z = false;
                                        break;
                                    }
                                case ReaderCallback.READER_TXT_READING_MODEL /* 5010 */:
                                    if (MttLoader.m16873c(TbsReaderView.this.f12966a)) {
                                        String str3 = "";
                                        if (obj != null) {
                                            Bundle bundle9 = (Bundle) obj;
                                            str3 = bundle9.getString("docpath");
                                            bundle4 = bundle9;
                                        } else {
                                            bundle4 = null;
                                        }
                                        QbSdk.startQBForDoc(TbsReaderView.this.f12966a, str3, 4, 0, "txt", bundle4);
                                        break;
                                    } else {
                                        num = Integer.valueOf((int) ReaderCallback.INSTALL_QB);
                                        String resString3 = TbsReaderView.getResString(TbsReaderView.this.f12966a, ReaderCallback.READER_PLUGIN_RES_TXT_GUIDE);
                                        Bundle bundle10 = new Bundle();
                                        bundle10.putString("tip", resString3);
                                        bundle10.putString("statistics", TbsReaderView.READER_STATISTICS_COUNT_TXT_INTO_DOWNLOAD);
                                        bundle10.putInt("channel_id", TbsReaderView.READER_CHANNEL_TXT_ID);
                                        TbsReaderView.this.userStatistics(TbsReaderView.READER_STATISTICS_COUNT_TXT_INTO_DIALOG);
                                        obj = bundle10;
                                        z = false;
                                        break;
                                    }
                                default:
                                    z = false;
                                    break;
                            }
                        } else if (obj != null) {
                            Bundle bundle11 = (Bundle) obj;
                            TbsReaderView.gReaderPackName = bundle11.getString("name");
                            TbsReaderView.gReaderPackVersion = bundle11.getString(ClientCookie.VERSION_ATTR);
                        }
                    } else if (!MttLoader.m16873c(TbsReaderView.this.f12966a)) {
                        num = Integer.valueOf((int) ReaderCallback.INSTALL_QB);
                        String resString4 = TbsReaderView.getResString(TbsReaderView.this.f12966a, ReaderCallback.READER_PLUGIN_RES_DOC_GUIDE);
                        Bundle bundle12 = new Bundle();
                        bundle12.putString("tip", resString4);
                        bundle12.putString("statistics", TbsReaderView.READER_STATISTICS_COUNT_DOC_INTO_DOWNLOAD);
                        bundle12.putInt("channel_id", TbsReaderView.READER_CHANNEL_DOC_ID);
                        TbsReaderView.this.userStatistics(TbsReaderView.READER_STATISTICS_COUNT_DOC_INTO_DIALOG);
                        obj = bundle12;
                        z = false;
                    } else {
                        String str4 = "";
                        if (obj != null) {
                            Bundle bundle13 = (Bundle) obj;
                            str4 = bundle13.getString("docpath");
                            bundle = bundle13;
                        } else {
                            bundle = null;
                        }
                        QbSdk.startQBForDoc(TbsReaderView.this.f12966a, str4, 4, 0, "doc", bundle);
                        TbsReaderView.this.userStatistics(TbsReaderView.READER_STATISTICS_COUNT_DOC_INTO_BROWSER);
                    }
                    if (TbsReaderView.this.f12969d != null && !z) {
                        TbsReaderView.this.f12969d.onCallBackAction(num, obj, obj2);
                    }
                }
            };
            return;
        }
        throw new RuntimeException("error: unexpect context(none Activity)");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m16966a(Context context) {
        if (!f12965f) {
            SDKEngine.m16828a(true).m16830a(context.getApplicationContext(), true, false);
            f12965f = SDKEngine.m16828a(false).m16827b();
        }
        return f12965f;
    }

    public static boolean isSupportExt(Context context, String str) {
        return m16966a(context) && ReaderWizard.isSupportCurrentPlatform(context) && ReaderWizard.isSupportExt(str);
    }

    public boolean preOpen(String str, boolean z) {
        boolean z2 = false;
        if (!isSupportExt(this.f12966a, str)) {
            Log.e(TAG, "not supported by:" + str);
            return false;
        }
        boolean a = m16966a(this.f12966a);
        if (!a) {
            return a;
        }
        boolean a2 = m16967a();
        if (!z || !a2) {
            return a2;
        }
        if (Apn.getApnType(this.f12966a) == 3) {
            z2 = true;
        }
        return this.f12967b.checkPlugin(this.f12968c, this.f12966a, str, z2);
    }

    public boolean downloadPlugin(String str) {
        Object obj = this.f12968c;
        if (obj != null) {
            return this.f12967b.checkPlugin(obj, this.f12966a, str, true);
        }
        Log.e(TAG, "downloadPlugin failed!");
        return false;
    }

    public static Drawable getResDrawable(Context context, int i) {
        if (m16966a(context)) {
            return ReaderWizard.getResDrawable(i);
        }
        return null;
    }

    public static String getResString(Context context, int i) {
        if (m16966a(context)) {
            return ReaderWizard.getResString(i);
        }
        return "";
    }

    public void openFile(Bundle bundle) {
        if (this.f12968c == null || bundle == null) {
            Log.e(TAG, "init failed!");
            return;
        }
        bundle.putBoolean("browser6.0", MttLoader.m16873c(this.f12966a) | (!MttLoader.m16874b(this.f12966a)));
        bundle.putBoolean("browser6.1", MttLoader.m16882a(this.f12966a, 6101625L, 610000L) | (!MttLoader.m16874b(this.f12966a)));
        if (!this.f12967b.openFile(this.f12968c, this.f12966a, bundle, this)) {
            Log.e(TAG, "OpenFile failed!");
        }
    }

    public void doCommand(Integer num, Object obj, Object obj2) {
        Object obj3;
        ReaderWizard readerWizard = this.f12967b;
        if (readerWizard != null && (obj3 = this.f12968c) != null) {
            readerWizard.doCommand(obj3, num, obj, obj2);
        }
    }

    public void onSizeChanged(int i, int i2) {
        Object obj;
        ReaderWizard readerWizard = this.f12967b;
        if (readerWizard != null && (obj = this.f12968c) != null) {
            readerWizard.onSizeChanged(obj, i, i2);
        }
    }

    public void onStop() {
        ReaderWizard readerWizard = this.f12967b;
        if (readerWizard != null) {
            readerWizard.destroy(this.f12968c);
            this.f12968c = null;
        }
        this.f12966a = null;
        f12965f = false;
    }

    public void userStatistics(String str) {
        ReaderWizard readerWizard = this.f12967b;
        if (readerWizard != null) {
            readerWizard.userStatistics(this.f12968c, str);
        }
    }

    /* renamed from: a */
    boolean m16967a() {
        try {
            if (this.f12967b == null) {
                this.f12967b = new ReaderWizard(this.f12970e);
            }
            if (this.f12968c == null) {
                this.f12968c = this.f12967b.getTbsReader();
            }
            if (this.f12968c != null) {
                return this.f12967b.initTbsReader(this.f12968c, this.f12966a);
            }
            return false;
        } catch (NullPointerException unused) {
            Log.e(TAG, "Unexpect null object!");
            return false;
        }
    }
}
