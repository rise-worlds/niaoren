package com.nrzs.game.p069ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.p006v7.widget.LinearLayoutManager;
import android.support.p006v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.SPUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.nrzs.base.router.RouterConstants;
import com.nrzs.data.game.bean.HotKeyBean;
import com.nrzs.data.game.bean.PagesBean;
import com.nrzs.data.game.bean.RdataBean;
import com.nrzs.data.game.bean.response.SearchKeyResponse;
import com.nrzs.data.game.bean.response.SearchResponse;
import com.nrzs.game.C2029R;
import com.nrzs.game.adapter.GameTopicListViewAdapter;
import com.nrzs.game.adapter.HotKeyAdapter;
import com.nrzs.game.p069ui.base.GameBaseActivity;
import com.nrzs.game.p069ui.view.NRZSGridView;
import com.nrzs.game.p069ui.view.SearchResultGameGridView;
import com.nrzs.http.UICallback;
import com.redwas.redwars.RedbgaIndextActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.tools.ant.taskdefs.optional.vss.MSVSSConstants;
import p110z1.EditextUtils;
import p110z1.EventCollectManager;
import p110z1.EventConstants;
import p110z1.GameSearchRepository;
import p110z1.GlideImageUtils;
import p110z1.Gson;
import p110z1.ILoadData;
import p110z1.LoadMoreForRecyclerView;
import p110z1.LocalLoadHelper;
import p110z1.PreSetListManager;
import p110z1.TypeToken;
import p110z1.apn;

@Route(path = RouterConstants.ModuleGame.SEARCH)
/* renamed from: com.nrzs.game.ui.activity.GameSearchActivity */
/* loaded from: classes2.dex */
public class GameSearchActivity extends GameBaseActivity {

    /* renamed from: a */
    public static String f11002a = "HOT_SEARCH_HISTROY";

    /* renamed from: A */
    private ImageView f11003A;

    /* renamed from: c */
    Runnable f11007c;

    /* renamed from: e */
    private NRZSGridView f11009e;

    /* renamed from: f */
    private NRZSGridView f11010f;

    /* renamed from: g */
    private HotKeyAdapter f11011g;

    /* renamed from: h */
    private List<HotKeyBean> f11012h;

    /* renamed from: i */
    private List<HotKeyBean> f11013i;

    /* renamed from: j */
    private EditText f11014j;

    /* renamed from: k */
    private RecyclerView f11015k;

    /* renamed from: l */
    private LinearLayout f11016l;

    /* renamed from: m */
    private LinearLayout f11017m;

    /* renamed from: n */
    private LinearLayout f11018n;

    /* renamed from: o */
    private LinearLayoutManager f11019o;

    /* renamed from: p */
    private GameTopicListViewAdapter f11020p;

    /* renamed from: q */
    private List<RdataBean> f11021q;

    /* renamed from: r */
    private SearchResultGameGridView f11022r;

    /* renamed from: s */
    private PagesBean f11023s;

    /* renamed from: t */
    private View f11024t;

    /* renamed from: u */
    private ImageView f11025u;

    /* renamed from: v */
    private apn f11026v;

    /* renamed from: w */
    private TextView f11027w;

    /* renamed from: x */
    private LinearLayout f11028x;

    /* renamed from: y */
    private TextView f11029y;

    /* renamed from: z */
    private TextView f11030z;

    /* renamed from: b */
    Handler f11006b = new Handler();

    /* renamed from: d */
    int f11008d = 1;

    /* renamed from: B */
    private UICallback f11004B = new UICallback() { // from class: com.nrzs.game.ui.activity.GameSearchActivity.3
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            GameSearchActivity.this.f11026v.mo11681l_();
        }

        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3022a(Object obj) {
            GameSearchActivity.this.f11026v.mo11679n_();
            SearchKeyResponse searchKeyResponse = (SearchKeyResponse) obj;
            if (searchKeyResponse != null) {
                GameSearchActivity.this.f11012h = searchKeyResponse.getRdata();
                GameSearchActivity gameSearchActivity = GameSearchActivity.this;
                gameSearchActivity.f11011g = new HotKeyAdapter(gameSearchActivity.getBaseContext(), GameSearchActivity.this.f11012h);
                GameSearchActivity.this.f11009e.setAdapter((ListAdapter) GameSearchActivity.this.f11011g);
            }
        }
    };

    /* renamed from: C */
    private UICallback f11005C = new UICallback() { // from class: com.nrzs.game.ui.activity.GameSearchActivity.4
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            GameSearchActivity.this.f11026v.mo11681l_();
        }

        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3022a(Object obj) {
            GameSearchActivity.this.f11026v.mo11679n_();
            GameSearchActivity.this.m18672a((SearchResponse) obj);
        }
    };

    /* renamed from: a */
    public static void m18673a(Context context) {
        Intent intent = new Intent(context, GameSearchActivity.class);
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    protected int mo18307a() {
        return C2029R.layout.nrzs_game_search;
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18303b() {
        ImmersionBar.m20080a(this).m20087a(C2029R.color.colorPrimary).m19974h(true).m19994f();
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    public void mo18305a(Bundle bundle) {
        this.f11009e = (NRZSGridView) findViewById(C2029R.C2031id.gv_hot_search_key);
        this.f11010f = (NRZSGridView) findViewById(C2029R.C2031id.gv_search_history);
        this.f11014j = (EditText) findViewById(C2029R.C2031id.et_search);
        if (PreSetListManager.m12116a().m12113c() != null && !PreSetListManager.m12116a().m12113c().equals("")) {
            this.f11014j.setHint(PreSetListManager.m12116a().m12113c());
        }
        this.f11015k = (RecyclerView) findViewById(C2029R.C2031id.rcv_search_result);
        this.f11016l = (LinearLayout) findViewById(C2029R.C2031id.ll_search_no_result);
        this.f11018n = (LinearLayout) findViewById(C2029R.C2031id.ll_search_hotkey);
        this.f11027w = (TextView) findViewById(C2029R.C2031id.entrance_redbag_sech);
        this.f11028x = (LinearLayout) findViewById(C2029R.C2031id.search_red_lay);
        this.f11029y = (TextView) findViewById(C2029R.C2031id.seaarch_red_title);
        this.f11030z = (TextView) findViewById(C2029R.C2031id.seaarch_red_title_count);
        this.f11003A = (ImageView) findViewById(C2029R.C2031id.search_red_log);
        this.f11024t = LayoutInflater.from(getBaseContext()).inflate(C2029R.layout.nrzs_game_search_result_head_textview, (ViewGroup) null);
        this.f11025u = (ImageView) findViewById(C2029R.C2031id.btn_back);
        this.f11026v = new apn(new LocalLoadHelper(getApplicationContext(), (LinearLayout) findViewById(C2029R.C2031id.ll_search), new View.OnClickListener() { // from class: com.nrzs.game.ui.activity.GameSearchActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameSearchActivity.this.m18658f();
            }
        }), new ILoadData() { // from class: com.nrzs.game.ui.activity.GameSearchActivity.7
            @Override // p110z1.ILoadData
            /* renamed from: a */
            public void mo11683a(int i) {
            }
        });
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18302b(Bundle bundle) {
        this.f11023s = new PagesBean();
        EditextUtils.m11915a(this.f11014j);
        this.f11012h = new ArrayList();
        this.f11009e.setAdapter((ListAdapter) new HotKeyAdapter(getBaseContext(), this.f11012h));
        m18658f();
        this.f11019o = new LinearLayoutManager(getBaseContext());
        this.f11022r = new SearchResultGameGridView(this);
        this.f11021q = new ArrayList();
        this.f11020p = new GameTopicListViewAdapter(this.f11021q);
        this.f11020p.m18491a((View) this.f11022r, 0, false);
        this.f11020p.m18491a(this.f11024t, 1, false);
        this.f11015k.setLayoutManager(this.f11019o);
        this.f11015k.setAdapter(this.f11020p);
        this.f11013i = m18663c();
        List<HotKeyBean> list = this.f11013i;
        if (list != null && list.size() > 0) {
            this.f11010f.setAdapter((ListAdapter) new HotKeyAdapter(getBaseContext(), this.f11013i));
        }
        m18660e();
    }

    /* renamed from: e */
    private void m18660e() {
        if (PreSetListManager.m12116a().m12103m().equals("1")) {
            this.f11028x.setVisibility(0);
            this.f11029y.setText(PreSetListManager.m12116a().m12102n());
            this.f11030z.setText(PreSetListManager.m12116a().m12098r());
            GlideImageUtils.m11880a(this.f11003A, this, C2029R.C2030drawable.bird_ic_red_bag, PreSetListManager.m12116a().m12097s());
            return;
        }
        this.f11028x.setVisibility(8);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: d */
    public void mo18300d() {
        this.f11027w.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.game.ui.activity.GameSearchActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RedbgaIndextActivity.m18176a(view.getContext());
            }
        });
        this.f11009e.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.nrzs.game.ui.activity.GameSearchActivity.9
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                GameSearchActivity.this.f11014j.setText(((HotKeyBean) GameSearchActivity.this.f11012h.get(i)).getKeyTitle().trim());
                GameSearchActivity.this.f11014j.setSelection(((HotKeyBean) GameSearchActivity.this.f11012h.get(i)).getKeyTitle().length());
            }
        });
        this.f11010f.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.nrzs.game.ui.activity.GameSearchActivity.10
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                GameSearchActivity.this.f11014j.setText(((HotKeyBean) GameSearchActivity.this.f11013i.get(i)).getKeyTitle().trim());
                GameSearchActivity.this.f11014j.setSelection(((HotKeyBean) GameSearchActivity.this.f11013i.get(i)).getKeyTitle().length());
            }
        });
        this.f11014j.addTextChangedListener(new TextWatcher() { // from class: com.nrzs.game.ui.activity.GameSearchActivity.11
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                GameSearchActivity.this.f11008d = 1;
                String trim = editable.toString().trim();
                if (trim.equals("")) {
                    GameSearchActivity.this.f11015k.setVisibility(8);
                    GameSearchActivity.this.f11018n.setVisibility(0);
                    GameSearchActivity.this.f11016l.setVisibility(8);
                    return;
                }
                if (GameSearchActivity.this.f11007c != null) {
                    GameSearchActivity.this.f11006b.removeCallbacks(GameSearchActivity.this.f11007c);
                }
                GameSearchActivity.this.f11007c = new Runnable() { // from class: com.nrzs.game.ui.activity.GameSearchActivity.11.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Log.d("lbsxxxx", "执行 -- 1");
                        if (GameSearchActivity.this.f11008d == 1) {
                            Log.d("lbsxxxx", "执行 -- 2");
                            if (TextUtils.isEmpty(GameSearchActivity.this.f11014j.getText().toString().trim())) {
                                GameSearchActivity.this.f11015k.setVisibility(8);
                                GameSearchActivity.this.f11018n.setVisibility(0);
                                GameSearchActivity.this.f11016l.setVisibility(8);
                                return;
                            }
                            GameSearchActivity.this.m18665a(GameSearchActivity.this.f11014j.getText().toString());
                            GameSearchActivity.this.f11021q.clear();
                            GameSearchActivity.this.m18674a(1, 8, GameSearchActivity.this.f11014j.getText().toString());
                        }
                    }
                };
                Log.d("lbsxxxx", "key:" + trim);
                GameSearchActivity.this.f11006b.postDelayed(GameSearchActivity.this.f11007c, 2000L);
            }
        });
        this.f11014j.setOnKeyListener(new View.OnKeyListener() { // from class: com.nrzs.game.ui.activity.GameSearchActivity.12
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                return false;
            }
        });
        this.f11014j.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.nrzs.game.ui.activity.GameSearchActivity.13
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                GameSearchActivity gameSearchActivity = GameSearchActivity.this;
                gameSearchActivity.f11008d = 2;
                if (TextUtils.isEmpty(gameSearchActivity.f11014j.getText()) || keyEvent.getAction() != 0) {
                    return true;
                }
                GameSearchActivity.this.f11014j.setSelection(GameSearchActivity.this.f11014j.getText().length());
                new Handler().postDelayed(new Runnable() { // from class: com.nrzs.game.ui.activity.GameSearchActivity.13.1
                    @Override // java.lang.Runnable
                    public void run() {
                        GameSearchActivity.this.m18665a(GameSearchActivity.this.f11014j.getText().toString());
                        GameSearchActivity.this.f11021q.clear();
                        GameSearchActivity.this.m18674a(1, 8, GameSearchActivity.this.f11014j.getText().toString());
                    }
                }, 1000L);
                return true;
            }
        });
        new LoadMoreForRecyclerView(this.f11015k, new LoadMoreForRecyclerView.AbstractC3827a() { // from class: com.nrzs.game.ui.activity.GameSearchActivity.14
            @Override // p110z1.LoadMoreForRecyclerView.AbstractC3827a
            /* renamed from: a */
            public void mo11902a() {
                if (GameSearchActivity.this.f11023s.getIsLastPage() != 1) {
                    GameSearchActivity gameSearchActivity = GameSearchActivity.this;
                    gameSearchActivity.m18674a(gameSearchActivity.f11023s.getCurrentPage() + 1, 8, GameSearchActivity.this.f11014j.getText().toString().trim());
                }
            }
        });
        this.f11025u.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.game.ui.activity.GameSearchActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameSearchActivity.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public synchronized void m18672a(SearchResponse searchResponse) {
        if (searchResponse != null) {
            this.f11023s = searchResponse.getPages();
            if (this.f11023s.getCurrentPage() == 1) {
                this.f11021q.clear();
            }
            if (searchResponse.getRdata() == null || searchResponse.getRdata().size() == 0) {
                this.f11024t.setVisibility(8);
                if (searchResponse.getTopicList() == null || searchResponse.getTopicList().size() == 0) {
                    this.f11016l.setVisibility(0);
                    this.f11018n.setVisibility(8);
                }
            }
            if (searchResponse.getTopicList() != null && searchResponse.getTopicList().size() > 0) {
                this.f11015k.setVisibility(0);
                this.f11022r.setData(searchResponse.getTopicList());
            }
            if (searchResponse.getRdata() != null && searchResponse.getRdata().size() > 0) {
                this.f11015k.setVisibility(0);
                this.f11024t.setVisibility(0);
                this.f11021q.addAll(searchResponse.getRdata());
                this.f11020p.notifyDataSetChanged();
            }
            if (this.f11014j.getText().toString().isEmpty() || this.f11014j.getText().toString().equals("")) {
                this.f11015k.setVisibility(8);
                this.f11018n.setVisibility(0);
                this.f11016l.setVisibility(8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public void m18658f() {
        try {
            this.f11026v.mo11682k_();
            new GameSearchRepository().m12709a(this.f11004B);
        } catch (Exception e) {
            e.printStackTrace();
            this.f11026v.mo11681l_();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m18674a(int i, int i2, String str) {
        try {
            this.f11026v.mo11682k_();
            new GameSearchRepository().m12710a(i, i2, str, this.f11005C);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m18665a(String str) {
        if (!TextUtils.isEmpty(str)) {
            Gson oxVar = new Gson();
            ArrayList arrayList = (ArrayList) oxVar.m1588a(SPUtils.m23341a().m23324b(f11002a), new TypeToken<List<String>>() { // from class: com.nrzs.game.ui.activity.GameSearchActivity.5
            }.getType());
            if (arrayList == null) {
                arrayList = new ArrayList();
                arrayList.add(str);
            } else if (!arrayList.contains(str)) {
                if (arrayList.size() == 10) {
                    arrayList.remove(0);
                }
                arrayList.add(str);
            }
            SPUtils.m23341a().m23331a(f11002a, oxVar.m1575b(arrayList), true);
        }
    }

    /* renamed from: c */
    public ArrayList<HotKeyBean> m18663c() {
        ArrayList<HotKeyBean> arrayList = new ArrayList<>();
        ArrayList arrayList2 = (ArrayList) new Gson().m1588a(SPUtils.m23341a().m23324b(f11002a), new TypeToken<List<String>>() { // from class: com.nrzs.game.ui.activity.GameSearchActivity.6
        }.getType());
        if (arrayList2 != null) {
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                HotKeyBean hotKeyBean = new HotKeyBean();
                hotKeyBean.setKeyTitle((String) it.next());
                hotKeyBean.setMark(MSVSSConstants.COMMAND_HISTORY);
                arrayList.add(hotKeyBean);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.game.p069ui.base.GameBaseActivity, com.nrzs.libcommon.BaseActivity, android.support.p006v7.app.AppCompatActivity, android.support.p003v4.app.FragmentActivity, android.support.p003v4.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        EventCollectManager.m12642a().m12640a(getBaseContext(), "搜索页面展示", "搜索页面展示", EventConstants.f16438f);
    }
}
