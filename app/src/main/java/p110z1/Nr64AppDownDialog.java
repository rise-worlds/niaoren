package p110z1;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.graphics.drawable.PathInterpolatorCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.SPUtils;
import com.nrzs.game.C2029R;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: z1.aos */
/* loaded from: classes3.dex */
public class Nr64AppDownDialog extends Dialog {

    /* renamed from: a */
    private static int f17041a = 801;

    /* renamed from: b */
    private static int f17042b = 802;

    /* renamed from: c */
    private static int f17043c = 803;

    /* renamed from: d */
    private static int f17044d = 804;

    /* renamed from: e */
    private static String f17045e = "com.angel.nrzs64";

    /* renamed from: f */
    private Activity f17046f;

    /* renamed from: g */
    private RelativeLayout f17047g;

    /* renamed from: h */
    private ProgressBar f17048h;

    /* renamed from: i */
    private TextView f17049i;

    /* renamed from: j */
    private amx f17050j;

    /* renamed from: k */
    private DownLoadTask f17051k;

    /* renamed from: l */
    private ThreadPoolExecutor f17052l;

    /* renamed from: m */
    private Map<String, amx> f17053m;

    /* renamed from: n */
    private Gson f17054n;

    /* renamed from: o */
    private String f17055o;

    /* renamed from: p */
    private ImageView f17056p;

    /* renamed from: q */
    private TextView f17057q;

    /* renamed from: r */
    private Handler f17058r = new Handler() { // from class: z1.aos.6
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == Nr64AppDownDialog.f17041a) {
                Nr64AppDownDialog.this.f17050j = (amx) message.obj;
                Toast.makeText(Nr64AppDownDialog.this.f17046f, "下载完成", 1).show();
                File file = new File(GlobalConstants.f16479b, Nr64AppDownDialog.this.f17050j.getFileName());
                if (file.exists()) {
                    AppUtils.m22950a(file.getAbsolutePath());
                }
                try {
                    Nr64AppDownDialog.this.f17053m.remove(Nr64AppDownDialog.this.f17050j.getUrl());
                    SPUtils.m23341a().m23332a(Nr64AppDownDialog.class.getSimpleName(), Nr64AppDownDialog.this.f17054n.m1575b(Nr64AppDownDialog.this.f17053m));
                } catch (Exception unused) {
                }
                Nr64AppDownDialog.this.dismiss();
            }
            if (message.what == Nr64AppDownDialog.f17042b) {
                Nr64AppDownDialog.this.f17050j = (amx) message.obj;
                Toast.makeText(Nr64AppDownDialog.this.f17046f, "更新失败，请稍后重试", 1).show();
                Nr64AppDownDialog.this.f17047g.setVisibility(8);
                Nr64AppDownDialog.this.f17057q.setText("重试");
                Nr64AppDownDialog.this.f17057q.setVisibility(0);
            }
            if (message.what == Nr64AppDownDialog.f17043c) {
                Nr64AppDownDialog.this.f17050j = (amx) message.obj;
                if (!Nr64AppDownDialog.this.f17050j.isStop()) {
                    Nr64AppDownDialog.this.f17049i.setText("更新中");
                }
                Nr64AppDownDialog.this.f17048h.setMax(message.arg1);
                Nr64AppDownDialog.this.f17048h.setProgress(message.arg2);
                Nr64AppDownDialog.this.f17050j = (amx) message.obj;
            }
            if (message.what == Nr64AppDownDialog.f17044d) {
                Nr64AppDownDialog.this.f17050j = (amx) message.obj;
                try {
                    Nr64AppDownDialog.this.f17050j.setMD5(FileUtils.m22182t(new File(Nr64AppDownDialog.this.f17050j.getSaveUri())));
                    Nr64AppDownDialog.this.f17053m.put(Nr64AppDownDialog.this.f17050j.getUrl(), Nr64AppDownDialog.this.f17050j);
                    SPUtils.m23341a().m23332a(Nr64AppDownDialog.class.getSimpleName(), Nr64AppDownDialog.this.f17054n.m1575b(Nr64AppDownDialog.this.f17053m));
                } catch (Exception unused2) {
                }
            }
        }
    };

    /* renamed from: g */
    private void m11967g() {
    }

    public Nr64AppDownDialog(Activity activity, String str) {
        super(activity, C2029R.style.NRZS_Game_my_dialog);
        this.f17046f = activity;
        this.f17055o = str;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f17052l = new ThreadPoolExecutor(50, 50, 50L, TimeUnit.SECONDS, new ArrayBlockingQueue(PathInterpolatorCompat.MAX_NUM_POINTS));
        this.f17054n = new Gson();
        String b = SPUtils.m23341a().m23324b(Nr64AppDownDialog.class.getSimpleName());
        if (!b.isEmpty()) {
            this.f17053m = (Map) this.f17054n.m1588a(b, new TypeToken<HashMap<String, amx>>() { // from class: z1.aos.1
            }.getType());
        } else {
            this.f17053m = new HashMap();
        }
        setContentView(C2029R.layout.nrzs_game_va_64app_install);
        m11961j();
        m11969f();
        m11965h();
        m11967g();
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    /* renamed from: f */
    private void m11969f() {
        if (AppUtils.m22927e(f17045e)) {
            this.f17057q.setText("打开64位鸟人助手");
        }
    }

    /* renamed from: h */
    private void m11965h() {
        setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: z1.aos.2
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return i == 4 && keyEvent.getAction() == 1;
            }
        });
        this.f17056p.setOnClickListener(new View.OnClickListener() { // from class: z1.aos.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Nr64AppDownDialog.this.dismiss();
            }
        });
        this.f17047g.setOnClickListener(new View.OnClickListener() { // from class: z1.aos.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Nr64AppDownDialog.this.f17050j.isStop()) {
                    Nr64AppDownDialog.this.f17049i.setText("连接中");
                    Nr64AppDownDialog.this.m11959k();
                    return;
                }
                Nr64AppDownDialog.this.f17049i.setText("暂停更新");
                Nr64AppDownDialog.this.m11957l();
            }
        });
        this.f17057q.setOnClickListener(new View.OnClickListener() { // from class: z1.aos.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AppUtils.m22927e(Nr64AppDownDialog.f17045e)) {
                    AppUtils.m22917j(Nr64AppDownDialog.f17045e);
                    Nr64AppDownDialog.this.dismiss();
                    return;
                }
                Nr64AppDownDialog.this.f17047g.setVisibility(0);
                Nr64AppDownDialog.this.f17057q.setVisibility(8);
                Nr64AppDownDialog.this.f17048h.setMax(10000);
                Nr64AppDownDialog.this.f17048h.setProgress(1);
                view.post(new Runnable() { // from class: z1.aos.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        File file = new File(GlobalConstants.f16479b, Nr64AppDownDialog.this.m11963i());
                        if (file.exists()) {
                            AppUtils.m22950a(file.getAbsolutePath());
                            Nr64AppDownDialog.this.dismiss();
                            return;
                        }
                        if (Nr64AppDownDialog.this.f17053m != null && Nr64AppDownDialog.this.f17053m.size() > 0) {
                            Nr64AppDownDialog.this.f17050j = (amx) Nr64AppDownDialog.this.f17053m.get(Nr64AppDownDialog.this.f17055o);
                            if (Nr64AppDownDialog.this.f17050j != null) {
                                if (Nr64AppDownDialog.this.f17050j.getMD5() == null || Nr64AppDownDialog.this.f17050j.getMD5().isEmpty()) {
                                    String str = GlobalConstants.f16479b;
                                    File file2 = new File(str, Nr64AppDownDialog.this.f17050j.getFileName() + "-temp");
                                    if (file2.exists()) {
                                        file2.delete();
                                        Nr64AppDownDialog.this.f17053m.remove(Nr64AppDownDialog.this.f17050j);
                                        Nr64AppDownDialog.this.f17050j = null;
                                    }
                                } else {
                                    String str2 = GlobalConstants.f16479b;
                                    File file3 = new File(str2, Nr64AppDownDialog.this.f17050j.getFileName() + "-temp");
                                    if (file3.exists() && !FileUtils.m22182t(file3).equals(Nr64AppDownDialog.this.f17050j.getMD5())) {
                                        file3.delete();
                                        Nr64AppDownDialog.this.f17053m.remove(Nr64AppDownDialog.this.f17050j);
                                        Nr64AppDownDialog.this.f17050j = null;
                                    }
                                }
                            }
                        }
                        if (Nr64AppDownDialog.this.f17050j == null) {
                            Nr64AppDownDialog.this.f17050j = new amx();
                            Nr64AppDownDialog.this.f17050j.setSaveDir(GlobalConstants.f16479b);
                            Nr64AppDownDialog.this.f17050j.setFileName(Nr64AppDownDialog.this.m11963i());
                            Nr64AppDownDialog.this.f17050j.setUrl(Nr64AppDownDialog.this.f17055o);
                            Nr64AppDownDialog.this.f17053m.put(Nr64AppDownDialog.this.f17050j.getUrl(), Nr64AppDownDialog.this.f17050j);
                        }
                        Nr64AppDownDialog.this.m11959k();
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i */
    public String m11963i() {
        String str = f17045e;
        if (TextUtils.isEmpty(this.f17055o)) {
            return str;
        }
        try {
            return this.f17055o.substring(this.f17055o.lastIndexOf("/") + 1);
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    /* renamed from: j */
    private void m11961j() {
        this.f17057q = (TextView) findViewById(C2029R.C2031id.nrzs_game_tv_install_64);
        this.f17047g = (RelativeLayout) findViewById(C2029R.C2031id.rl_progressbar);
        this.f17048h = (ProgressBar) findViewById(C2029R.C2031id.progress);
        this.f17049i = (TextView) findViewById(C2029R.C2031id.tv_progressbar);
        this.f17056p = (ImageView) findViewById(C2029R.C2031id.nrzs_game_iv_install_64_close);
    }

    @Override // android.app.Dialog
    protected void onStop() {
        amx amxVar = this.f17050j;
        if (amxVar != null && !amxVar.isStop()) {
            this.f17050j.setStop(true);
        }
        DownLoadTask amwVar = this.f17051k;
        if (amwVar != null) {
            this.f17052l.remove(amwVar);
            this.f17051k = null;
        }
        SPUtils.m23341a().m23332a(Nr64AppDownDialog.class.getSimpleName(), this.f17054n.m1575b(this.f17053m));
        super.onStop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k */
    public void m11959k() {
        this.f17050j.setStop(false);
        if (this.f17051k == null) {
            this.f17051k = new DownLoadTask(getContext(), this.f17050j, new OnDownLoaderListener() { // from class: z1.aos.7
                @Override // p110z1.OnDownLoaderListener
                /* renamed from: a */
                public void mo3049a(int i, int i2, double d, amx amxVar) {
                    Message message = new Message();
                    message.what = Nr64AppDownDialog.f17043c;
                    message.arg1 = i;
                    message.arg2 = i2;
                    message.obj = amxVar;
                    Nr64AppDownDialog.this.f17058r.sendMessage(message);
                }

                @Override // p110z1.OnDownLoaderListener
                /* renamed from: a */
                public void mo3048a(amx amxVar) {
                    Message message = new Message();
                    message.what = Nr64AppDownDialog.f17044d;
                    message.obj = amxVar;
                    Nr64AppDownDialog.this.f17058r.sendMessage(message);
                }

                @Override // p110z1.OnDownLoaderListener
                /* renamed from: a */
                public void mo3047a(amx amxVar, String str) {
                    Message message = new Message();
                    message.what = Nr64AppDownDialog.f17042b;
                    message.obj = amxVar;
                    Nr64AppDownDialog.this.f17058r.sendMessage(message);
                }

                @Override // p110z1.OnDownLoaderListener
                /* renamed from: b */
                public void mo3046b(amx amxVar) {
                    Message message = new Message();
                    message.what = Nr64AppDownDialog.f17041a;
                    message.obj = amxVar;
                    Nr64AppDownDialog.this.f17058r.sendMessage(message);
                }
            });
            this.f17052l.execute(this.f17051k);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l */
    public void m11957l() {
        this.f17050j.setStop(true);
    }
}
