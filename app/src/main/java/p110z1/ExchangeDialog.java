package p110z1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.p006v7.widget.LinearLayoutManager;
import android.support.p006v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.angel.nrzs.C0692R;
import com.angel.nrzs.ddy.adapter.ExchangeAdapter;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.ddy.bean.request.Getorderlist;
import com.nrzs.data.ddy.bean.respond.OrderDaileInfo;
import com.nrzs.data.other.bean.request.GetExchangeRequestInfo;
import com.nrzs.data.other.bean.response.ExchageResponseinfo;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import java.util.ArrayList;
import java.util.List;
import p110z1.BuysuccessEvent;
import p110z1.UserInfoEvent;

/* renamed from: z1.ee */
/* loaded from: classes3.dex */
public class ExchangeDialog extends Dialog {

    /* renamed from: a */
    private TextView f21413a;

    /* renamed from: b */
    private int f21414b;

    /* renamed from: c */
    private String f21415c;

    /* renamed from: d */
    private RecyclerView f21416d;

    /* renamed from: e */
    private ExchangeAdapter f21417e;

    /* renamed from: f */
    private LinearLayout f21418f;

    /* renamed from: g */
    private LinearLayout f21419g;

    /* renamed from: h */
    private ImageView f21420h;

    /* renamed from: i */
    private TextView f21421i;

    /* renamed from: j */
    private TextView f21422j;

    /* renamed from: k */
    private TextView f21423k;

    /* renamed from: l */
    private OrderDaileInfo f21424l;

    /* renamed from: n */
    private ExchangeRepository f21426n;

    /* renamed from: q */
    private DdyRepository f21429q;

    /* renamed from: m */
    private List<OrderDaileInfo> f21425m = new ArrayList();

    /* renamed from: o */
    private ThreadCallback<BaseResponse<ExchageResponseinfo>, String> f21427o = new ThreadCallback<BaseResponse<ExchageResponseinfo>, String>() { // from class: z1.ee.1
        /* renamed from: a */
        public BaseResponse<ExchageResponseinfo> onResponse(String str) {
            BaseResponse<ExchageResponseinfo> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<ExchageResponseinfo>>() { // from class: z1.ee.1.1
            });
            if (baseResponse != null) {
                return baseResponse;
            }
            return null;
        }
    };

    /* renamed from: p */
    private UICallback<BaseResponse<ExchageResponseinfo>> f21428p = new UICallback<BaseResponse<ExchageResponseinfo>>() { // from class: z1.ee.2
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            Toast.makeText(ExchangeDialog.this.getContext(), "兑换失败", 1).show();
            Log.e("Exchange", "兑换失败");
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<ExchageResponseinfo> baseResponse) {
            if (baseResponse == null) {
                Toast.makeText(ExchangeDialog.this.getContext(), "兑换失败", 1).show();
                Log.e("Exchange", "兑换失败-o为null");
            } else if (baseResponse.code == 1) {
                EventBus.m3448a().m3427d(new UserInfoEvent.C3561a());
                ExchageResponseinfo exchageResponseinfo = baseResponse.data;
                if (exchageResponseinfo.BatchType == 3 || exchageResponseinfo.BatchType == 4) {
                    ExchangeDialog.this.f21419g.setVisibility(8);
                    ExchangeDialog.this.f21418f.setVisibility(0);
                    if (exchageResponseinfo.BatchType == 4) {
                        ExchangeDialog.this.f21421i.setText("鸟人VIP Plus");
                    } else {
                        ExchangeDialog.this.f21421i.setText(exchageResponseinfo.TypeName);
                    }
                    TextView textView = ExchangeDialog.this.f21422j;
                    textView.setText(exchageResponseinfo.VIPDay + "天");
                    EventBus.m3448a().m3427d(new BuysuccessEvent.C3552a());
                    Log.e("Exchange", "兑换成功发送刷新列表");
                }
            } else {
                Toast.makeText(ExchangeDialog.this.getContext(), baseResponse.msg, 1).show();
                Log.e("Exchange", "兑换失败-code不为1");
            }
        }
    };

    /* renamed from: r */
    private UICallback<List<OrderDaileInfo>> f21430r = new UICallback<List<OrderDaileInfo>>() { // from class: z1.ee.3
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(List<OrderDaileInfo> list) {
            if (list != null && list.size() > 0) {
                ExchangeDialog.this.f21425m = list;
                OrderDaileInfo orderDaileInfo = new OrderDaileInfo();
                orderDaileInfo.isTop = true;
                ExchangeDialog.this.f21425m.add(0, orderDaileInfo);
                ExchangeDialog.this.m3150f();
            }
        }
    };

    /* renamed from: g */
    private void m3148g() {
    }

    public ExchangeDialog(Context context, int i, String str) {
        super(context);
        this.f21414b = i;
        this.f21415c = str;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().requestFeature(1);
        setContentView(C0692R.layout.nrzs_exchange_xf_view);
        setCancelable(true);
        m3165a();
        m3158b();
        m3156c();
        m3154d();
    }

    /* renamed from: a */
    protected void m3165a() {
        setCancelable(true);
        getWindow().setGravity(17);
        getWindow().getDecorView().setBackgroundColor(getContext().getResources().getColor(C0692R.color.f1440f2));
    }

    /* renamed from: b */
    protected void m3158b() {
        this.f21413a = (TextView) findViewById(C0692R.C0694id.manage_btn);
        this.f21416d = (RecyclerView) findViewById(C0692R.C0694id.recycler_group_list);
        this.f21418f = (LinearLayout) findViewById(C0692R.C0694id.ssuccess_lay);
        this.f21419g = (LinearLayout) findViewById(C0692R.C0694id.f2569dz);
        this.f21418f.setVisibility(8);
        this.f21421i = (TextView) findViewById(C0692R.C0694id.ssuccess_typename);
        this.f21422j = (TextView) findViewById(C0692R.C0694id.success_time);
        this.f21423k = (TextView) findViewById(C0692R.C0694id.success_ok);
        this.f21420h = (ImageView) findViewById(C0692R.C0694id.f2549dd);
    }

    /* renamed from: c */
    protected void m3156c() {
        this.f21420h.setOnClickListener(new View.OnClickListener() { // from class: z1.ee.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ExchangeDialog.this.dismiss();
            }
        });
        this.f21413a.setOnClickListener(new View.OnClickListener() { // from class: z1.ee.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ExchangeDialog.this.f21424l == null) {
                    Toast.makeText(ExchangeDialog.this.getContext(), "请选择一种设备", 1).show();
                } else if (ExchangeDialog.this.f21424l.isTop) {
                    ExchangeDialog eeVar = ExchangeDialog.this;
                    eeVar.m3164a(1, eeVar.f21424l.f10629Id);
                } else {
                    ExchangeDialog eeVar2 = ExchangeDialog.this;
                    eeVar2.m3164a(0, eeVar2.f21424l.f10629Id);
                }
            }
        });
        this.f21423k.setOnClickListener(new View.OnClickListener() { // from class: z1.ee.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ExchangeDialog.this.dismiss();
            }
        });
    }

    /* renamed from: d */
    protected void m3154d() {
        m3152e();
    }

    /* renamed from: e */
    public void m3152e() {
        if (this.f21429q == null) {
            this.f21429q = new DdyRepository();
        }
        Getorderlist getorderlist = new Getorderlist();
        getorderlist.UCID = LoginManager.m12620d().m12614j();
        getorderlist.TypeCode = this.f21414b;
        this.f21429q.m12873a(getorderlist, this.f21430r);
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m3164a(int i, long j) {
        if (this.f21426n == null) {
            this.f21426n = new ExchangeRepository();
        }
        GetExchangeRequestInfo getExchangeRequestInfo = new GetExchangeRequestInfo();
        getExchangeRequestInfo.DDY_OrderInfoID = j;
        getExchangeRequestInfo.IsAddNewDDYOrder = i;
        getExchangeRequestInfo.userid = LoginManager.m12620d().m12614j();
        getExchangeRequestInfo.cardnumber = this.f21415c;
        this.f21426n.m12582a(getExchangeRequestInfo, this.f21428p, this.f21427o);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public void m3150f() {
        this.f21416d.setLayoutManager(new LinearLayoutManager(getContext()));
        this.f21417e = new ExchangeAdapter(new ExchangeAdapter.AbstractC0740a() { // from class: z1.ee.7
            @Override // com.angel.nrzs.ddy.adapter.ExchangeAdapter.AbstractC0740a
            /* renamed from: a */
            public void mo3143a(OrderDaileInfo orderDaileInfo) {
                ExchangeDialog.this.f21424l = orderDaileInfo;
            }
        });
        this.f21417e.m25126a(this.f21425m);
        this.f21416d.setAdapter(this.f21417e);
    }

    /* renamed from: a */
    public void m3163a(List<OrderDaileInfo> list) {
        this.f21425m = list;
    }
}
